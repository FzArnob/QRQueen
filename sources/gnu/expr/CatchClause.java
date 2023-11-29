package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class CatchClause extends LetExp {
    CatchClause next;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public CatchClause() {
        super(new Expression[]{QuoteExp.voidExp});
    }

    public CatchClause(Object obj, ClassType classType) {
        this();
        addDeclaration(obj, classType);
    }

    public CatchClause(LambdaExp lambdaExp) {
        this();
        Declaration firstDecl = lambdaExp.firstDecl();
        lambdaExp.remove((Declaration) null, firstDecl);
        add(firstDecl);
        this.body = lambdaExp.body;
    }

    public final CatchClause getNext() {
        return this.next;
    }

    public final void setNext(CatchClause catchClause) {
        this.next = catchClause;
    }

    public final Expression getBody() {
        return this.body;
    }

    public final void setBody(Expression expression) {
        this.body = expression;
    }

    /* access modifiers changed from: protected */
    public Object evalVariable(int i, CallContext callContext) throws Throwable {
        return callContext.value1;
    }

    public void compile(Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        Variable allocateVariable = firstDecl().allocateVariable(code);
        code.enterScope(getVarScope());
        code.emitCatchStart(allocateVariable);
        this.body.compileWithPosition(compilation, target);
        code.emitCatchEnd();
        code.popScope();
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        this.body = expVisitor.visitAndUpdate(this.body, d);
    }

    public void print(OutPort outPort) {
        outPort.writeSpaceLinear();
        outPort.startLogicalBlock("(Catch", ")", 2);
        outPort.writeSpaceFill();
        this.decls.printInfo(outPort);
        outPort.writeSpaceLinear();
        this.body.print(outPort);
        outPort.endLogicalBlock(")");
    }
}
