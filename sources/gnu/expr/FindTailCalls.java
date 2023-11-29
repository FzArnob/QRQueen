package gnu.expr;

import gnu.bytecode.Type;

public class FindTailCalls extends ExpExpVisitor<Expression> {
    public static void findTailCalls(Expression expression, Compilation compilation) {
        FindTailCalls findTailCalls = new FindTailCalls();
        findTailCalls.setContext(compilation);
        findTailCalls.visit(expression, expression);
    }

    /* access modifiers changed from: protected */
    public Expression visitExpression(Expression expression, Expression expression2) {
        return (Expression) super.visitExpression(expression, expression);
    }

    public Expression[] visitExps(Expression[] expressionArr) {
        int length = expressionArr.length;
        for (int i = 0; i < length; i++) {
            Expression expression = expressionArr[i];
            expressionArr[i] = (Expression) visit(expression, expression);
        }
        return expressionArr;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression visitApplyExp(gnu.expr.ApplyExp r6, gnu.expr.Expression r7) {
        /*
            r5 = this;
            gnu.expr.LambdaExp r0 = r5.currentLambda
            gnu.expr.Expression r0 = r0.body
            r1 = 0
            r2 = 1
            if (r7 != r0) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            if (r0 == 0) goto L_0x0010
            r6.setTailCall(r2)
        L_0x0010:
            gnu.expr.LambdaExp r3 = r5.currentLambda
            r6.context = r3
            gnu.expr.Expression r3 = r6.func
            boolean r3 = r3 instanceof gnu.expr.ReferenceExp
            r4 = 0
            if (r3 == 0) goto L_0x004e
            gnu.expr.Expression r1 = r6.func
            gnu.expr.ReferenceExp r1 = (gnu.expr.ReferenceExp) r1
            gnu.expr.Declaration r1 = r1.binding
            gnu.expr.Declaration r1 = gnu.expr.Declaration.followAliases(r1)
            if (r1 == 0) goto L_0x0083
            r2 = 2048(0x800, double:1.0118E-320)
            boolean r2 = r1.getFlag(r2)
            if (r2 != 0) goto L_0x0035
            gnu.expr.ApplyExp r2 = r1.firstCall
            r6.nextCall = r2
            r1.firstCall = r6
        L_0x0035:
            gnu.expr.Compilation r2 = r5.getCompilation()
            r1.setCanCall()
            boolean r2 = r2.mustCompile
            if (r2 != 0) goto L_0x0043
            r1.setCanRead()
        L_0x0043:
            gnu.expr.Expression r1 = r1.getValue()
            boolean r2 = r1 instanceof gnu.expr.LambdaExp
            if (r2 == 0) goto L_0x0083
            gnu.expr.LambdaExp r1 = (gnu.expr.LambdaExp) r1
            goto L_0x0084
        L_0x004e:
            gnu.expr.Expression r3 = r6.func
            boolean r3 = r3 instanceof gnu.expr.LambdaExp
            if (r3 == 0) goto L_0x0066
            gnu.expr.Expression r3 = r6.func
            boolean r3 = r3 instanceof gnu.expr.ClassExp
            if (r3 != 0) goto L_0x0066
            gnu.expr.Expression r3 = r6.func
            gnu.expr.LambdaExp r3 = (gnu.expr.LambdaExp) r3
            r5.visitLambdaExp((gnu.expr.LambdaExp) r3, (boolean) r1)
            r3.setCanCall(r2)
            r1 = r3
            goto L_0x0084
        L_0x0066:
            gnu.expr.Expression r1 = r6.func
            boolean r1 = r1 instanceof gnu.expr.QuoteExp
            if (r1 == 0) goto L_0x0079
            gnu.expr.Expression r1 = r6.func
            gnu.expr.QuoteExp r1 = (gnu.expr.QuoteExp) r1
            java.lang.Object r1 = r1.getValue()
            gnu.kawa.functions.AppendValues r2 = gnu.kawa.functions.AppendValues.appendValues
            if (r1 != r2) goto L_0x0079
            goto L_0x0083
        L_0x0079:
            gnu.expr.Expression r1 = r6.func
            gnu.expr.Expression r2 = r6.func
            gnu.expr.Expression r1 = r5.visitExpression((gnu.expr.Expression) r1, (gnu.expr.Expression) r2)
            r6.func = r1
        L_0x0083:
            r1 = r4
        L_0x0084:
            if (r1 == 0) goto L_0x00b8
            gnu.expr.Expression r2 = r1.returnContinuation
            if (r2 != r7) goto L_0x008b
            goto L_0x00b8
        L_0x008b:
            gnu.expr.LambdaExp r2 = r5.currentLambda
            if (r1 != r2) goto L_0x0092
            if (r0 == 0) goto L_0x0092
            goto L_0x00b8
        L_0x0092:
            if (r0 == 0) goto L_0x00a7
            java.util.Set<gnu.expr.LambdaExp> r7 = r1.tailCallers
            if (r7 != 0) goto L_0x009f
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            r1.tailCallers = r7
        L_0x009f:
            java.util.Set<gnu.expr.LambdaExp> r7 = r1.tailCallers
            gnu.expr.LambdaExp r0 = r5.currentLambda
            r7.add(r0)
            goto L_0x00b8
        L_0x00a7:
            gnu.expr.Expression r0 = r1.returnContinuation
            if (r0 != 0) goto L_0x00b2
            r1.returnContinuation = r7
            gnu.expr.LambdaExp r7 = r5.currentLambda
            r1.inlineHome = r7
            goto L_0x00b8
        L_0x00b2:
            gnu.expr.ApplyExp r7 = gnu.expr.LambdaExp.unknownContinuation
            r1.returnContinuation = r7
            r1.inlineHome = r4
        L_0x00b8:
            gnu.expr.Expression[] r7 = r6.args
            gnu.expr.Expression[] r7 = r5.visitExps(r7)
            r6.args = r7
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.FindTailCalls.visitApplyExp(gnu.expr.ApplyExp, gnu.expr.Expression):gnu.expr.Expression");
    }

    /* access modifiers changed from: protected */
    public Expression visitBlockExp(BlockExp blockExp, Expression expression) {
        blockExp.body = (Expression) blockExp.body.visit(this, expression);
        if (blockExp.exitBody != null) {
            blockExp.exitBody = (Expression) blockExp.exitBody.visit(this, blockExp.exitBody);
        }
        return blockExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitBeginExp(BeginExp beginExp, Expression expression) {
        int i = beginExp.length - 1;
        int i2 = 0;
        while (i2 <= i) {
            beginExp.exps[i2] = (Expression) beginExp.exps[i2].visit(this, i2 == i ? expression : beginExp.exps[i2]);
            i2++;
        }
        return beginExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitFluidLetExp(FluidLetExp fluidLetExp, Expression expression) {
        for (Declaration firstDecl = fluidLetExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            firstDecl.setCanRead(true);
            if (firstDecl.base != null) {
                firstDecl.base.setCanRead(true);
            }
        }
        visitLetDecls(fluidLetExp);
        fluidLetExp.body = (Expression) fluidLetExp.body.visit(this, fluidLetExp.body);
        postVisitDecls(fluidLetExp);
        return fluidLetExp;
    }

    /* access modifiers changed from: package-private */
    public void visitLetDecls(LetExp letExp) {
        Declaration firstDecl = letExp.firstDecl();
        int length = letExp.inits.length;
        int i = 0;
        while (i < length) {
            Expression visitSetExp = visitSetExp(firstDecl, letExp.inits[i]);
            if (visitSetExp == QuoteExp.undefined_exp) {
                Expression value = firstDecl.getValue();
                if ((value instanceof LambdaExp) || (value != visitSetExp && (value instanceof QuoteExp))) {
                    visitSetExp = value;
                }
            }
            letExp.inits[i] = visitSetExp;
            i++;
            firstDecl = firstDecl.nextDecl();
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Expression expression) {
        visitLetDecls(letExp);
        letExp.body = (Expression) letExp.body.visit(this, expression);
        postVisitDecls(letExp);
        return letExp;
    }

    public void postVisitDecls(ScopeExp scopeExp) {
        Declaration contextDecl;
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            Expression value = firstDecl.getValue();
            if (value instanceof LambdaExp) {
                LambdaExp lambdaExp = (LambdaExp) value;
                if (firstDecl.getCanRead()) {
                    lambdaExp.setCanRead(true);
                }
                if (firstDecl.getCanCall()) {
                    lambdaExp.setCanCall(true);
                }
            }
            if (firstDecl.getFlag(1024) && (value instanceof ReferenceExp) && (contextDecl = ((ReferenceExp) value).contextDecl()) != null && contextDecl.isPrivate()) {
                contextDecl.setFlag(524288);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitIfExp(IfExp ifExp, Expression expression) {
        ifExp.test = (Expression) ifExp.test.visit(this, ifExp.test);
        ifExp.then_clause = (Expression) ifExp.then_clause.visit(this, expression);
        Expression expression2 = ifExp.else_clause;
        if (expression2 != null) {
            ifExp.else_clause = (Expression) expression2.visit(this, expression);
        }
        return ifExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Expression expression) {
        visitLambdaExp(lambdaExp, true);
        return lambdaExp;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final void visitLambdaExp(LambdaExp lambdaExp, boolean z) {
        LambdaExp lambdaExp2 = this.currentLambda;
        this.currentLambda = lambdaExp;
        if (z) {
            lambdaExp.setCanRead(true);
        }
        try {
            if (lambdaExp.defaultArgs != null) {
                lambdaExp.defaultArgs = visitExps(lambdaExp.defaultArgs);
            }
            if (this.exitValue == null && lambdaExp.body != null) {
                lambdaExp.body = (Expression) lambdaExp.body.visit(this, lambdaExp.getInlineOnly() ? lambdaExp : lambdaExp.body);
            }
            this.currentLambda = lambdaExp2;
            postVisitDecls(lambdaExp);
        } catch (Throwable th) {
            this.currentLambda = lambdaExp2;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, Expression expression) {
        LambdaExp lambdaExp = this.currentLambda;
        this.currentLambda = classExp;
        try {
            for (LambdaExp lambdaExp2 = classExp.firstChild; lambdaExp2 != null && this.exitValue == null; lambdaExp2 = lambdaExp2.nextSibling) {
                visitLambdaExp(lambdaExp2, false);
            }
            return classExp;
        } finally {
            this.currentLambda = lambdaExp;
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Expression expression) {
        Declaration followAliases = Declaration.followAliases(referenceExp.binding);
        if (followAliases != null) {
            Type type = followAliases.type;
            if (type != null && type.isVoid()) {
                return QuoteExp.voidExp;
            }
            followAliases.setCanRead(true);
        }
        Declaration contextDecl = referenceExp.contextDecl();
        if (contextDecl != null) {
            contextDecl.setCanRead(true);
        }
        return referenceExp;
    }

    /* access modifiers changed from: package-private */
    public final Expression visitSetExp(Declaration declaration, Expression expression) {
        if (declaration == null || declaration.getValue() != expression || !(expression instanceof LambdaExp) || (expression instanceof ClassExp) || declaration.isPublic()) {
            return (Expression) expression.visit(this, expression);
        }
        LambdaExp lambdaExp = (LambdaExp) expression;
        visitLambdaExp(lambdaExp, false);
        return lambdaExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Expression expression) {
        Declaration declaration = setExp.binding;
        if (declaration != null && declaration.isAlias()) {
            if (setExp.isDefining()) {
                setExp.new_value = (Expression) setExp.new_value.visit(this, setExp.new_value);
                return setExp;
            }
            declaration = Declaration.followAliases(declaration);
        }
        Declaration contextDecl = setExp.contextDecl();
        if (contextDecl != null) {
            contextDecl.setCanRead(true);
        }
        Expression visitSetExp = visitSetExp(declaration, setExp.new_value);
        if (declaration != null && (declaration.context instanceof LetExp) && visitSetExp == declaration.getValue() && ((visitSetExp instanceof LambdaExp) || (visitSetExp instanceof QuoteExp))) {
            return QuoteExp.voidExp;
        }
        setExp.new_value = visitSetExp;
        return setExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitTryExp(TryExp tryExp, Expression expression) {
        tryExp.try_clause = (Expression) tryExp.try_clause.visit(this, tryExp.finally_clause == null ? expression : tryExp.try_clause);
        CatchClause catchClause = tryExp.catch_clauses;
        while (this.exitValue == null && catchClause != null) {
            catchClause.body = (Expression) catchClause.body.visit(this, tryExp.finally_clause == null ? expression : catchClause.body);
            catchClause = catchClause.getNext();
        }
        Expression expression2 = tryExp.finally_clause;
        if (expression2 != null) {
            tryExp.finally_clause = (Expression) expression2.visit(this, expression2);
        }
        return tryExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSynchronizedExp(SynchronizedExp synchronizedExp, Expression expression) {
        synchronizedExp.object = (Expression) synchronizedExp.object.visit(this, synchronizedExp.object);
        synchronizedExp.body = (Expression) synchronizedExp.body.visit(this, synchronizedExp.body);
        return synchronizedExp;
    }
}
