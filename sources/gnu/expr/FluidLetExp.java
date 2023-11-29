package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;

public class FluidLetExp extends LetExp {
    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return true;
    }

    public FluidLetExp(Expression[] expressionArr) {
        super(expressionArr);
    }

    public void compile(Compilation compilation, Target target) {
        Target target2;
        CodeAttr code = compilation.getCode();
        Type type = target instanceof IgnoreTarget ? null : getType();
        if (type == null) {
            target2 = Target.Ignore;
        } else if (type == Type.pointer_type) {
            target2 = Target.pushObject;
        } else {
            target2 = new StackTarget(type);
        }
        Scope varScope = getVarScope();
        code.enterScope(varScope);
        Variable addVariable = varScope.addVariable(code, Compilation.typeCallContext, (String) null);
        compilation.loadCallContext();
        code.emitStore(addVariable);
        Variable[] variableArr = new Variable[this.inits.length];
        Declaration firstDecl = firstDecl();
        doInits(firstDecl, 0, variableArr, compilation, addVariable);
        code.emitTryStart(true, type);
        this.body.compileWithPosition(compilation, target2);
        code.emitFinallyStart();
        int i = 0;
        while (i < this.inits.length) {
            firstDecl.load((AccessExp) null, 2, compilation, Target.pushObject);
            code.emitLoad(variableArr[i]);
            code.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setRestore", 1));
            i++;
            firstDecl = firstDecl.nextDecl();
        }
        code.emitTryCatchEnd();
        popScope(code);
        if (type != null) {
            target.compileFromStack(compilation, type);
        }
    }

    private void doInits(Declaration declaration, int i, Variable[] variableArr, Compilation compilation, Variable variable) {
        if (i < this.inits.length) {
            CodeAttr code = compilation.getCode();
            variableArr[i] = code.addLocal(Type.pointer_type);
            declaration.allocateVariable(code);
            declaration.base.load((AccessExp) null, 2, compilation, Target.pushObject);
            code.emitDup();
            code.emitStore(declaration.getVariable());
            this.inits[i].compile(compilation, Target.pushObject);
            doInits(declaration.nextDecl(), i + 1, variableArr, compilation, variable);
            code.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setWithSave", 1));
            code.emitStore(variableArr[i]);
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitFluidLetExp(this, d);
    }

    public void print(OutPort outPort) {
        print(outPort, "(FluidLet", ")");
    }
}
