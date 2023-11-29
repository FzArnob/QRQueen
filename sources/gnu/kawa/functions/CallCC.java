package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import kawa.lang.Continuation;

public class CallCC extends MethodProc implements Inlineable {
    public static final CallCC callcc = new CallCC();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    CallCC() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyCallCC");
    }

    public int match1(Object obj, CallContext callContext) {
        if (!(obj instanceof Procedure)) {
            return MethodProc.NO_MATCH_BAD_TYPE;
        }
        return super.match1(obj, callContext);
    }

    public void apply(CallContext callContext) throws Throwable {
        Continuation continuation = new Continuation(callContext);
        ((Procedure) callContext.value1).check1(continuation, callContext);
        Procedure procedure = callContext.proc;
        callContext.proc = null;
        try {
            procedure.apply(callContext);
            callContext.runUntilDone();
            continuation.invoked = true;
        } catch (Throwable th) {
            Continuation.handleException$X(th, continuation, callContext);
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        CompileMisc.compileCallCC(applyExp, compilation, target, this);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }
}
