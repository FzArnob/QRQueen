package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Label;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class BlockExp extends Expression {
    Expression body;
    Expression exitBody;
    Target exitTarget;
    ExitableBlock exitableBlock;
    Declaration label;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void setBody(Expression expression) {
        this.body = expression;
    }

    public void setBody(Expression expression, Expression expression2) {
        this.body = expression;
        this.exitBody = expression2;
    }

    public void setLabel(Declaration declaration) {
        this.label = declaration;
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        try {
            obj = this.body.eval(callContext);
        } catch (BlockExitException e) {
            if (e.exit.block == this) {
                obj = e.exit.result;
                Expression expression = this.exitBody;
                if (expression != null) {
                    obj = expression.eval(callContext);
                }
            } else {
                throw e;
            }
        }
        callContext.writeValue(obj);
    }

    public void compile(Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        this.exitableBlock = code.startExitableBlock((this.exitBody != null || !(target instanceof StackTarget)) ? null : target.getType(), true);
        this.exitTarget = this.exitBody == null ? target : Target.Ignore;
        this.body.compileWithPosition(compilation, target);
        if (this.exitBody != null) {
            Label label2 = new Label(code);
            code.emitGoto(label2);
            code.endExitableBlock();
            this.exitBody.compileWithPosition(compilation, target);
            label2.define(code);
        } else {
            code.endExitableBlock();
        }
        this.exitableBlock = null;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitBlockExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        Expression expression;
        this.body = expVisitor.visitAndUpdate(this.body, d);
        if (expVisitor.exitValue == null && (expression = this.exitBody) != null) {
            this.exitBody = expVisitor.visitAndUpdate(expression, d);
        }
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Block", ")", 2);
        if (this.label != null) {
            outPort.print(' ');
            outPort.print(this.label.getName());
        }
        outPort.writeSpaceLinear();
        this.body.print(outPort);
        if (this.exitBody != null) {
            outPort.writeSpaceLinear();
            outPort.print("else ");
            this.exitBody.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }
}
