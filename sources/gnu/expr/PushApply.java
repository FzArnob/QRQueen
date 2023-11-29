package gnu.expr;

public class PushApply extends ExpVisitor<Expression, Void> {
    /* access modifiers changed from: protected */
    public Expression defaultValue(Expression expression, Void voidR) {
        return expression;
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression expression, Expression expression2) {
        return expression2;
    }

    public static void pushApply(Expression expression) {
        new PushApply().visit(expression, null);
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Void voidR) {
        Expression expression = applyExp.func;
        if ((expression instanceof LetExp) && !(expression instanceof FluidLetExp)) {
            LetExp letExp = (LetExp) expression;
            Expression expression2 = letExp.body;
            letExp.body = applyExp;
            applyExp.func = expression2;
            return (Expression) visit(letExp, voidR);
        } else if (expression instanceof BeginExp) {
            BeginExp beginExp = (BeginExp) expression;
            Expression[] expressionArr = beginExp.exps;
            int length = beginExp.exps.length - 1;
            applyExp.func = expressionArr[length];
            expressionArr[length] = applyExp;
            return (Expression) visit(beginExp, voidR);
        } else {
            applyExp.visitChildren(this, voidR);
            return applyExp;
        }
    }
}
