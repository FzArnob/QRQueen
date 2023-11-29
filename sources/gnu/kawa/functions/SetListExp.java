package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;

/* compiled from: CompilationHelpers */
class SetListExp extends ApplyExp {
    public SetListExp(Expression expression, Expression[] expressionArr) {
        super(expression, expressionArr);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length != 2) {
            return applyExp;
        }
        return Compilation.makeCoercion(inlineCalls.visitApplyOnly(new ApplyExp((Procedure) Invoke.invoke, getArgs()[0], QuoteExp.getInstance("set"), Compilation.makeCoercion(args[0], (Type) Type.intType), args[1]), type), (Type) Type.voidType);
    }
}
