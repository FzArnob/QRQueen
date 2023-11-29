package gnu.kawa.functions;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

/* compiled from: CallCC */
class CompileTimeContinuation extends ProcedureN implements Inlineable {
    Target blockTarget;
    ExitableBlock exitableBlock;

    CompileTimeContinuation() {
    }

    public Object applyN(Object[] objArr) throws Throwable {
        throw new Error("internal error");
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        compilation.getCode();
        Expression[] args = applyExp.getArgs();
        Target target2 = this.blockTarget;
        boolean z = (target2 instanceof IgnoreTarget) || (target2 instanceof ConsumerTarget);
        if (!z) {
            target.getType();
        }
        if (z || r0 == 1) {
            for (Expression compileWithPosition : args) {
                compileWithPosition.compileWithPosition(compilation, this.blockTarget);
            }
        } else {
            AppendValues appendValues = AppendValues.appendValues;
            appendValues.compile(new ApplyExp((Procedure) appendValues, args), compilation, this.blockTarget);
        }
        this.exitableBlock.exit();
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.neverReturnsType;
    }
}
