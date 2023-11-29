package gnu.expr;

import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class ExpVisitor<R, D> implements SourceLocator {
    Compilation comp;
    protected LambdaExp currentLambda = null;
    protected Object exitValue = null;
    protected SourceMessages messages;

    /* access modifiers changed from: protected */
    public R defaultValue(Expression expression, D d) {
        return null;
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Expression update(Expression expression, R r) {
        return expression;
    }

    public Compilation getCompilation() {
        return this.comp;
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    /* access modifiers changed from: protected */
    public R visitExpression(Expression expression, D d) {
        expression.visitChildren(this, d);
        return defaultValue(expression, d);
    }

    public void setContext(Compilation compilation) {
        this.comp = compilation;
        this.messages = compilation.getMessages();
    }

    public R visit(Expression expression, D d) {
        int lineNumber = expression.getLineNumber();
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages == null || lineNumber <= 0) {
            return expression.visit(this, d);
        }
        String fileName = sourceMessages.getFileName();
        int lineNumber2 = this.messages.getLineNumber();
        int columnNumber = this.messages.getColumnNumber();
        this.messages.setLine(expression.getFileName(), lineNumber, expression.getColumnNumber());
        R visit = expression.visit(this, d);
        this.messages.setLine(fileName, lineNumber2, columnNumber);
        return visit;
    }

    /* access modifiers changed from: protected */
    public R visitApplyExp(ApplyExp applyExp, D d) {
        return visitExpression(applyExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitIfExp(IfExp ifExp, D d) {
        return visitExpression(ifExp, d);
    }

    /* access modifiers changed from: protected */
    public final void visitDeclarationType(Declaration declaration) {
        Expression visitAndUpdate;
        Expression expression = declaration.typeExp;
        if (expression != null && (visitAndUpdate = visitAndUpdate(expression, (Object) null)) != expression) {
            declaration.setTypeExp(visitAndUpdate);
        }
    }

    /* access modifiers changed from: protected */
    public final void visitDeclarationTypes(ScopeExp scopeExp) {
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            visitDeclarationType(firstDecl);
        }
    }

    /* access modifiers changed from: protected */
    public R visitScopeExp(ScopeExp scopeExp, D d) {
        visitDeclarationTypes(scopeExp);
        return visitExpression(scopeExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLetExp(LetExp letExp, D d) {
        return visitScopeExp(letExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLambdaExp(LambdaExp lambdaExp, D d) {
        return visitScopeExp(lambdaExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitClassExp(ClassExp classExp, D d) {
        return visitLambdaExp(classExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitObjectExp(ObjectExp objectExp, D d) {
        return visitClassExp(objectExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitModuleExp(ModuleExp moduleExp, D d) {
        return visitLambdaExp(moduleExp, d);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExpValue(Expression expression, D d, Declaration declaration) {
        return visitAndUpdate(expression, d);
    }

    /* access modifiers changed from: protected */
    public R visitSetExp(SetExp setExp, D d) {
        Declaration declaration = setExp.binding;
        boolean z = declaration != null && declaration.value == setExp.new_value;
        setExp.new_value = visitSetExpValue(setExp.new_value, d, setExp.getBinding());
        if (z && setExp.isDefining()) {
            declaration.value = setExp.new_value;
            if (setExp.new_value instanceof LambdaExp) {
                ((LambdaExp) setExp.new_value).nameDecl = declaration;
            }
        }
        return defaultValue(setExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitTryExp(TryExp tryExp, D d) {
        return visitExpression(tryExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitBeginExp(BeginExp beginExp, D d) {
        return visitExpression(beginExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitQuoteExp(QuoteExp quoteExp, D d) {
        return visitExpression(quoteExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitReferenceExp(ReferenceExp referenceExp, D d) {
        return visitExpression(referenceExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitThisExp(ThisExp thisExp, D d) {
        return visitReferenceExp(thisExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitSynchronizedExp(SynchronizedExp synchronizedExp, D d) {
        return visitExpression(synchronizedExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitBlockExp(BlockExp blockExp, D d) {
        return visitExpression(blockExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitExitExp(ExitExp exitExp, D d) {
        return visitExpression(exitExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitFluidLetExp(FluidLetExp fluidLetExp, D d) {
        return visitLetExp(fluidLetExp, d);
    }

    /* access modifiers changed from: protected */
    public R visitLangExp(LangExp langExp, D d) {
        return visitExpression(langExp, d);
    }

    public Object getExitValue() {
        return this.exitValue;
    }

    public final LambdaExp getCurrentLambda() {
        return this.currentLambda;
    }

    public Expression visitAndUpdate(Expression expression, D d) {
        return update(expression, visit(expression, d));
    }

    public Expression[] visitExps(Expression[] expressionArr, D d) {
        if (expressionArr == null) {
            return null;
        }
        return visitExps(expressionArr, expressionArr.length, d);
    }

    public Expression[] visitExps(Expression[] expressionArr, int i, D d) {
        for (int i2 = 0; i2 < i && this.exitValue == null; i2++) {
            expressionArr[i2] = visitAndUpdate(expressionArr[i2], d);
        }
        return expressionArr;
    }

    public void visitDefaultArgs(LambdaExp lambdaExp, D d) {
        lambdaExp.defaultArgs = visitExps(lambdaExp.defaultArgs, d);
    }

    public void error(char c, String str) {
        if (c == 'w' && this.comp.warnAsError()) {
            c = 'e';
        }
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages != null) {
            sourceMessages.error(c, str);
            return;
        }
        new Error("internal error: " + str);
    }

    public Expression noteError(String str) {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages != null) {
            sourceMessages.error('e', str);
        }
        return new ErrorExp(str);
    }

    public final String getFileName() {
        return this.messages.getFileName();
    }

    public final int getLineNumber() {
        return this.messages.getLineNumber();
    }

    public final int getColumnNumber() {
        return this.messages.getColumnNumber();
    }

    public String getPublicId() {
        return this.messages.getPublicId();
    }

    public String getSystemId() {
        return this.messages.getSystemId();
    }

    public void setFile(String str) {
        this.messages.setFile(str);
    }

    public void setLine(int i) {
        this.messages.setLine(i);
    }

    public void setColumn(int i) {
        this.messages.setColumn(i);
    }

    public void setLine(String str, int i, int i2) {
        this.messages.setLine(str, i, i2);
    }
}
