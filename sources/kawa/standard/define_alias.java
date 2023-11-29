package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_alias extends Syntax {
    public static final define_alias define_alias;

    static {
        define_alias define_alias2 = new define_alias();
        define_alias = define_alias2;
        define_alias2.setName("define-alias");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        SyntaxForm syntaxForm = null;
        while (cdr instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) cdr;
            cdr = syntaxForm.getDatum();
        }
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            SyntaxForm syntaxForm2 = syntaxForm;
            Object obj = pair2.getCar();
            while (obj instanceof SyntaxForm) {
                syntaxForm2 = (SyntaxForm) obj;
                obj = syntaxForm2.getDatum();
            }
            Object obj2 = pair2.getCdr();
            while (obj2 instanceof SyntaxForm) {
                syntaxForm = (SyntaxForm) obj2;
                obj2 = syntaxForm.getDatum();
            }
            if (((obj instanceof String) || (obj instanceof Symbol)) && (obj2 instanceof Pair)) {
                Pair pair3 = (Pair) obj2;
                if (pair3.getCdr() == LList.Empty) {
                    Declaration define = translator.define(obj, syntaxForm2, scopeExp);
                    define.setIndirectBinding(true);
                    define.setAlias(true);
                    Expression rewrite_car = translator.rewrite_car(pair3, syntaxForm);
                    if (rewrite_car instanceof ReferenceExp) {
                        ReferenceExp referenceExp = (ReferenceExp) rewrite_car;
                        Declaration followAliases = Declaration.followAliases(referenceExp.getBinding());
                        if (followAliases != null) {
                            Expression value = followAliases.getValue();
                            if ((value instanceof ClassExp) || (value instanceof ModuleExp)) {
                                define.setIndirectBinding(false);
                                define.setFlag(16384);
                            }
                        }
                        referenceExp.setDontDereference(true);
                    } else if (rewrite_car instanceof QuoteExp) {
                        define.setIndirectBinding(false);
                        define.setFlag(16384);
                    } else {
                        rewrite_car = location.rewrite(rewrite_car, translator);
                        define.setType(ClassType.make("gnu.mapping.Location"));
                    }
                    translator.mustCompileHere();
                    translator.push(define);
                    SetExp setExp = new SetExp(define, rewrite_car);
                    translator.setLineOf(setExp);
                    define.noteValue(rewrite_car);
                    setExp.setDefining(true);
                    vector.addElement(setExp);
                    return true;
                }
            }
        }
        translator.error('e', "invalid syntax for define-alias");
        return false;
    }

    public Expression rewrite(Object obj, Translator translator) {
        return translator.syntaxError("define-alias is only allowed in a <body>");
    }
}
