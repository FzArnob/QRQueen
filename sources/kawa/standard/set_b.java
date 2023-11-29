package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompilationHelpers;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class set_b extends Syntax {
    public static final set_b set;

    static {
        set_b set_b = new set_b();
        set = set_b;
        set_b.setName("set!");
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        int i;
        Object cdr = pair.getCdr();
        SyntaxForm syntaxForm = null;
        while (cdr instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) cdr;
            cdr = syntaxForm.getDatum();
        }
        if (!(cdr instanceof Pair)) {
            return translator.syntaxError("missing name");
        }
        Pair pair2 = (Pair) cdr;
        Expression rewrite_car = translator.rewrite_car(pair2, syntaxForm);
        Object obj = pair2.getCdr();
        while (obj instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) obj;
            obj = syntaxForm.getDatum();
        }
        if (obj instanceof Pair) {
            Pair pair3 = (Pair) obj;
            if (pair3.getCdr() == LList.Empty) {
                Expression rewrite_car2 = translator.rewrite_car(pair3, syntaxForm);
                if (rewrite_car instanceof ApplyExp) {
                    ApplyExp applyExp = (ApplyExp) rewrite_car;
                    Expression[] args = applyExp.getArgs();
                    int length = args.length;
                    Expression function = applyExp.getFunction();
                    if (args.length <= 0 || !(function instanceof ReferenceExp) || ((ReferenceExp) function).getBinding() != Scheme.applyFieldDecl) {
                        i = 0;
                    } else {
                        length--;
                        function = args[0];
                        i = 1;
                    }
                    Expression[] expressionArr = {function};
                    Expression[] expressionArr2 = new Expression[(length + 1)];
                    System.arraycopy(args, i, expressionArr2, 0, length);
                    expressionArr2[length] = rewrite_car2;
                    return new ApplyExp((Expression) new ApplyExp((Expression) new ReferenceExp(CompilationHelpers.setterDecl), expressionArr), expressionArr2);
                } else if (!(rewrite_car instanceof ReferenceExp)) {
                    return translator.syntaxError("first set! argument is not a variable name");
                } else {
                    ReferenceExp referenceExp = (ReferenceExp) rewrite_car;
                    Declaration binding = referenceExp.getBinding();
                    SetExp setExp = new SetExp(referenceExp.getSymbol(), rewrite_car2);
                    setExp.setContextDecl(referenceExp.contextDecl());
                    if (binding != null) {
                        binding.setCanWrite(true);
                        setExp.setBinding(binding);
                        Declaration followAliases = Declaration.followAliases(binding);
                        if (followAliases != null) {
                            followAliases.noteValue(rewrite_car2);
                        }
                        if (followAliases.getFlag(16384)) {
                            return translator.syntaxError("constant variable " + followAliases.getName() + " is set!");
                        } else if (followAliases.context != translator.mainLambda && (followAliases.context instanceof ModuleExp) && !followAliases.getFlag(268435456) && !followAliases.context.getFlag(1048576)) {
                            translator.error('w', followAliases, "imported variable ", " is set!");
                        }
                    }
                    return setExp;
                }
            }
        }
        return translator.syntaxError("missing or extra arguments to set!");
    }
}
