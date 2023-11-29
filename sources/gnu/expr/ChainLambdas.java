package gnu.expr;

public class ChainLambdas extends ExpExpVisitor<ScopeExp> {
    public static void chainLambdas(Expression expression, Compilation compilation) {
        ChainLambdas chainLambdas = new ChainLambdas();
        chainLambdas.setContext(compilation);
        chainLambdas.visit(expression, null);
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, ScopeExp scopeExp2) {
        scopeExp.outer = scopeExp2;
        scopeExp.visitChildren(this, scopeExp);
        scopeExp.setIndexes();
        if (scopeExp.mustCompile()) {
            this.comp.mustCompileHere();
        }
        return scopeExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, ScopeExp scopeExp) {
        LambdaExp lambdaExp2 = this.currentLambda;
        if (lambdaExp2 != null && !(lambdaExp2 instanceof ClassExp)) {
            lambdaExp.nextSibling = lambdaExp2.firstChild;
            lambdaExp2.firstChild = lambdaExp;
        }
        lambdaExp.outer = scopeExp;
        lambdaExp.firstChild = null;
        lambdaExp.visitChildrenOnly(this, lambdaExp);
        lambdaExp.visitProperties(this, lambdaExp);
        LambdaExp lambdaExp3 = null;
        LambdaExp lambdaExp4 = lambdaExp.firstChild;
        while (lambdaExp4 != null) {
            LambdaExp lambdaExp5 = lambdaExp4.nextSibling;
            lambdaExp4.nextSibling = lambdaExp3;
            lambdaExp3 = lambdaExp4;
            lambdaExp4 = lambdaExp5;
        }
        lambdaExp.firstChild = lambdaExp3;
        if (lambdaExp.getName() == null && lambdaExp.nameDecl != null) {
            lambdaExp.setName(lambdaExp.nameDecl.getName());
        }
        lambdaExp.setIndexes();
        if (lambdaExp.mustCompile()) {
            this.comp.mustCompileHere();
        }
        return lambdaExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, ScopeExp scopeExp) {
        LambdaExp lambdaExp = this.currentLambda;
        if (lambdaExp != null && !(lambdaExp instanceof ClassExp)) {
            classExp.nextSibling = lambdaExp.firstChild;
            lambdaExp.firstChild = classExp;
        }
        visitScopeExp((ScopeExp) classExp, scopeExp);
        return classExp;
    }
}
