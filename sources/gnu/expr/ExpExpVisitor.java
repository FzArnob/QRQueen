package gnu.expr;

public abstract class ExpExpVisitor<D> extends ExpVisitor<Expression, D> {
    /* access modifiers changed from: protected */
    public Expression defaultValue(Expression expression, D d) {
        return expression;
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression expression, Expression expression2) {
        return expression2;
    }
}
