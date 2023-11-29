package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_member_alias extends Syntax {
    public static final define_member_alias define_member_alias;

    static {
        define_member_alias define_member_alias2 = new define_member_alias();
        define_member_alias = define_member_alias2;
        define_member_alias2.setName("define-member-alias");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        if ((pair.getCdr() instanceof Pair) && !(translator.currentScope() instanceof ModuleExp)) {
            Pair pair2 = (Pair) pair.getCdr();
            if (pair2.getCar() instanceof String) {
                Declaration addDeclaration = scopeExp.addDeclaration((String) pair2.getCar(), Compilation.typeSymbol);
                addDeclaration.setIndirectBinding(true);
                vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair2, addDeclaration, pair2.getCdr())));
                return true;
            }
        }
        return super.scanForDefinitions(pair, vector, scopeExp, translator);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        String str;
        Object cdr = pair.getCdr();
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            if ((pair2.getCar() instanceof String) || (pair2.getCar() instanceof Declaration)) {
                if (pair2.getCdr() instanceof Pair) {
                    Object car = pair2.getCar();
                    if (car instanceof Declaration) {
                        str = ((Declaration) car).getName();
                    } else {
                        str = (String) car;
                    }
                    Pair pair3 = (Pair) pair2.getCdr();
                    Expression expression = null;
                    Expression rewrite = translator.rewrite(pair3.getCar());
                    Object cdr2 = pair3.getCdr();
                    if (cdr2 == LList.Empty) {
                        expression = new QuoteExp(Compilation.mangleName(str));
                    } else if (cdr2 instanceof Pair) {
                        Pair pair4 = (Pair) cdr2;
                        if (pair4.getCdr() == LList.Empty) {
                            expression = translator.rewrite(pair4.getCar());
                        }
                    }
                    if (expression != null) {
                        return Invoke.makeInvokeStatic(ClassType.make("gnu.kawa.reflect.ClassMemberConstraint"), "define", new Expression[]{new QuoteExp(str), rewrite, expression});
                    }
                }
                return translator.syntaxError("invalid syntax for " + getName());
            }
        }
        return translator.syntaxError("missing name in " + getName());
    }
}
