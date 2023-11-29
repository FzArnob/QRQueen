package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.math.BaseUnit;
import gnu.math.Quantity;
import gnu.math.Unit;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_unit extends Syntax {
    public static final define_unit define_base_unit;
    public static final define_unit define_unit;
    boolean base;

    static {
        define_unit define_unit2 = new define_unit(false);
        define_unit = define_unit2;
        define_unit2.setName("define-unit");
        define_unit define_unit3 = new define_unit(true);
        define_base_unit = define_unit3;
        define_unit3.setName("define-base-unit");
    }

    public define_unit(boolean z) {
        this.base = z;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        if (pair.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) pair.getCdr();
            Object car = pair2.getCar();
            if (car instanceof SimpleSymbol) {
                String obj = car.toString();
                Declaration define = scopeExp.getDefine(Scheme.unitNamespace.getSymbol(obj), 'w', translator);
                translator.push(define);
                Translator.setLine(define, (Object) pair2);
                define.setFlag(16384);
                if (scopeExp instanceof ModuleExp) {
                    define.setCanRead(true);
                }
                Object obj2 = null;
                if (this.base && pair2.getCdr() == LList.Empty) {
                    String str = null;
                    obj2 = BaseUnit.make(obj, (String) null);
                } else if (pair2.getCdr() instanceof Pair) {
                    Object car2 = ((Pair) pair2.getCdr()).getCar();
                    boolean z = this.base;
                    if (z && (car2 instanceof CharSequence)) {
                        obj2 = BaseUnit.make(obj, car2.toString());
                    } else if (!z && (car2 instanceof Quantity)) {
                        obj2 = Unit.make(obj, (Quantity) car2);
                    }
                }
                if (obj2 != null) {
                    define.noteValue(new QuoteExp(obj2));
                }
                vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair2, define, pair2.getCdr())));
                return true;
            }
        }
        translator.error('e', "missing name in define-unit");
        return false;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Object cdr = pair.getCdr();
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            if (pair2.getCar() instanceof Declaration) {
                Declaration declaration = (Declaration) pair2.getCar();
                String localPart = ((Symbol) declaration.getSymbol()).getLocalPart();
                ClassType make = ClassType.make("gnu.math.Unit");
                declaration.setType(make);
                Expression value = declaration.getValue();
                if (!(value instanceof QuoteExp) || !(((QuoteExp) value).getValue() instanceof Unit)) {
                    if (this.base) {
                        String str = null;
                        if (pair2.getCdr() != LList.Empty) {
                            str = ((Pair) pair2.getCdr()).getCar().toString();
                        }
                        value = new QuoteExp(BaseUnit.make(localPart, str));
                    } else if (!(pair2.getCdr() instanceof Pair)) {
                        return translator.syntaxError("missing value for define-unit");
                    } else {
                        Expression rewrite = translator.rewrite(((Pair) pair2.getCdr()).getCar());
                        if (rewrite instanceof QuoteExp) {
                            Object value2 = ((QuoteExp) rewrite).getValue();
                            if (value2 instanceof Quantity) {
                                value = new QuoteExp(Unit.make(localPart, (Quantity) value2));
                            }
                        }
                        value = Invoke.makeInvokeStatic(make, "make", new Expression[]{new QuoteExp(localPart), rewrite});
                    }
                }
                SetExp setExp = new SetExp(declaration, value);
                setExp.setDefining(true);
                declaration.noteValue(value);
                return setExp;
            }
        }
        return translator.syntaxError("invalid syntax for " + getName());
    }
}
