package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class TryExp extends Expression {
    CatchClause catch_clauses;
    Expression finally_clause;
    Expression try_clause;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public final CatchClause getCatchClauses() {
        return this.catch_clauses;
    }

    public final Expression getFinallyClause() {
        return this.finally_clause;
    }

    public final void setCatchClauses(CatchClause catchClause) {
        this.catch_clauses = catchClause;
    }

    public TryExp(Expression expression, Expression expression2) {
        this.try_clause = expression;
        this.finally_clause = expression2;
    }

    public void apply(CallContext callContext) throws Throwable {
        try {
            this.try_clause.apply(callContext);
            callContext.runUntilDone();
            Expression expression = this.finally_clause;
            if (expression != null) {
                expression.eval(callContext);
            }
        } catch (Throwable th) {
            Expression expression2 = this.finally_clause;
            if (expression2 != null) {
                expression2.eval(callContext);
            }
            throw th;
        }
    }

    public void compile(Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        Expression expression = this.finally_clause;
        boolean z = expression != null;
        Target pushValue = ((target instanceof StackTarget) || (target instanceof ConsumerTarget) || (target instanceof IgnoreTarget) || ((target instanceof ConditionalTarget) && expression == null)) ? target : Target.pushValue(target.getType());
        code.emitTryStart(z, pushValue instanceof StackTarget ? pushValue.getType() : null);
        this.try_clause.compileWithPosition(compilation, pushValue);
        for (CatchClause catchClause = this.catch_clauses; catchClause != null; catchClause = catchClause.getNext()) {
            catchClause.compile(compilation, pushValue);
        }
        if (this.finally_clause != null) {
            code.emitFinallyStart();
            this.finally_clause.compileWithPosition(compilation, Target.Ignore);
            code.emitFinallyEnd();
        }
        code.emitTryCatchEnd();
        if (pushValue != target) {
            target.compileFromStack(compilation, target.getType());
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitTryExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        Expression expression;
        this.try_clause = expVisitor.visitAndUpdate(this.try_clause, d);
        CatchClause catchClause = this.catch_clauses;
        while (expVisitor.exitValue == null && catchClause != null) {
            expVisitor.visit(catchClause, d);
            catchClause = catchClause.getNext();
        }
        if (expVisitor.exitValue == null && (expression = this.finally_clause) != null) {
            this.finally_clause = expVisitor.visitAndUpdate(expression, d);
        }
    }

    public Type getType() {
        if (this.catch_clauses == null) {
            return this.try_clause.getType();
        }
        return super.getType();
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Try", ")", 2);
        outPort.writeSpaceFill();
        this.try_clause.print(outPort);
        for (CatchClause catchClause = this.catch_clauses; catchClause != null; catchClause = catchClause.getNext()) {
            catchClause.print(outPort);
        }
        if (this.finally_clause != null) {
            outPort.writeSpaceLinear();
            outPort.print(" finally: ");
            this.finally_clause.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }
}
