package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class AppendValues extends MethodProc implements Inlineable {
    public static final AppendValues appendValues = new AppendValues();

    public AppendValues() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyAppendValues");
    }

    public void apply(CallContext callContext) {
        Special special = Special.dfault;
        while (true) {
            Object nextArg = callContext.getNextArg(special);
            if (nextArg != special) {
                if (nextArg instanceof Consumable) {
                    ((Consumable) nextArg).consume(callContext.consumer);
                } else {
                    callContext.writeValue(nextArg);
                }
            } else {
                return;
            }
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        if ((target instanceof ConsumerTarget) || (target instanceof IgnoreTarget)) {
            for (Expression compileWithPosition : applyExp.getArgs()) {
                compileWithPosition.compileWithPosition(compilation, target);
            }
            return;
        }
        ConsumerTarget.compileUsingConsumer(applyExp, compilation, target);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Compilation.typeObject;
    }
}
