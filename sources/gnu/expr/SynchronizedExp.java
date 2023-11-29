package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class SynchronizedExp extends Expression {
    Expression body;
    Expression object;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public SynchronizedExp(Expression expression, Expression expression2) {
        this.object = expression;
        this.body = expression2;
    }

    public void apply(CallContext callContext) throws Throwable {
        Object eval;
        synchronized (this.object.eval(callContext)) {
            eval = this.body.eval(callContext);
        }
        callContext.writeValue(eval);
    }

    public void compile(Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        this.object.compile(compilation, Target.pushObject);
        code.emitDup(1);
        Variable addVariable = code.pushScope().addVariable(code, Type.pointer_type, (String) null);
        code.emitStore(addVariable);
        code.emitMonitorEnter();
        code.emitTryStart(false, ((target instanceof IgnoreTarget) || (target instanceof ConsumerTarget)) ? null : target.getType());
        this.body.compileWithPosition(compilation, target);
        code.emitLoad(addVariable);
        code.emitMonitorExit();
        code.emitTryEnd();
        code.emitCatchStart((Variable) null);
        code.emitLoad(addVariable);
        code.emitMonitorExit();
        code.emitThrow();
        code.emitCatchEnd();
        code.emitTryCatchEnd();
        code.popScope();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitSynchronizedExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        this.object = expVisitor.visitAndUpdate(this.object, d);
        if (expVisitor.exitValue == null) {
            this.body = expVisitor.visitAndUpdate(this.body, d);
        }
    }

    public void print(OutPort outPort) {
        outPort.print("(Synchronized ");
        this.object.print(outPort);
        outPort.print(" ");
        this.body.print(outPort);
        outPort.print(")");
    }
}
