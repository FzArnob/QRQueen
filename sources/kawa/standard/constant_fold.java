package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class constant_fold extends Syntax {
    public static final constant_fold constant_fold;

    static {
        constant_fold constant_fold2 = new constant_fold();
        constant_fold = constant_fold2;
        constant_fold2.setName("constant-fold");
    }

    static Object checkConstant(Expression expression, Translator translator) {
        if (expression instanceof QuoteExp) {
            return ((QuoteExp) expression).getValue();
        }
        if (!(expression instanceof ReferenceExp)) {
            return null;
        }
        ReferenceExp referenceExp = (ReferenceExp) expression;
        Declaration binding = referenceExp.getBinding();
        if (binding == null || binding.getFlag(65536)) {
            return Environment.user().get(referenceExp.getName(), (Object) null);
        }
        return Declaration.followAliases(binding).getConstantValue();
    }

    public Expression rewrite(Object obj, Translator translator) {
        Expression rewrite = translator.rewrite(obj);
        if (!(rewrite instanceof ApplyExp)) {
            return rewrite;
        }
        ApplyExp applyExp = (ApplyExp) rewrite;
        Object checkConstant = checkConstant(applyExp.getFunction(), translator);
        if (!(checkConstant instanceof Procedure)) {
            return rewrite;
        }
        Expression[] args = applyExp.getArgs();
        int length = args.length;
        Object[] objArr = new Object[length];
        while (true) {
            length--;
            if (length >= 0) {
                Object checkConstant2 = checkConstant(args[length], translator);
                if (checkConstant2 == null) {
                    return rewrite;
                }
                objArr[length] = checkConstant2;
            } else {
                try {
                    return new QuoteExp(((Procedure) checkConstant).applyN(objArr));
                } catch (Throwable th) {
                    Expression syntaxError = translator.syntaxError("caught exception in constant-fold:");
                    translator.syntaxError(th.toString());
                    return syntaxError;
                }
            }
        }
    }
}
