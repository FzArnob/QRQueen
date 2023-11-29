package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class ExitExp extends Expression {
    BlockExp block;
    Expression result;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public ExitExp(Expression expression, BlockExp blockExp) {
        this.result = expression;
        this.block = blockExp;
    }

    public ExitExp(BlockExp blockExp) {
        this.result = QuoteExp.voidExp;
        this.block = blockExp;
    }

    public void apply(CallContext callContext) throws Throwable {
        throw new BlockExitException(this, this.result.eval(callContext));
    }

    public void compile(Compilation compilation, Target target) {
        compilation.getCode();
        Expression expression = this.result;
        if (expression == null) {
            expression = QuoteExp.voidExp;
        }
        expression.compileWithPosition(compilation, this.block.exitTarget);
        this.block.exitableBlock.exit();
    }

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        Expression deepCopy = deepCopy(this.result, identityHashTable);
        if (deepCopy == null && this.result != null) {
            return null;
        }
        Object obj = identityHashTable.get(this.block);
        ExitExp exitExp = new ExitExp(deepCopy, obj == null ? this.block : (BlockExp) obj);
        exitExp.flags = getFlags();
        return exitExp;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitExitExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        this.result = expVisitor.visitAndUpdate(this.result, d);
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Exit", false, ")");
        outPort.writeSpaceFill();
        BlockExp blockExp = this.block;
        if (blockExp == null || blockExp.label == null) {
            outPort.print("<unknown>");
        } else {
            outPort.print(this.block.label.getName());
        }
        if (this.result != null) {
            outPort.writeSpaceLinear();
            this.result.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }

    public Type getType() {
        return Type.neverReturnsType;
    }
}
