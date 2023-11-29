package gnu.xquery.util;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class ValuesEvery extends MethodProc {
    public static final ValuesEvery every = new ValuesEvery(true);
    public static final ValuesEvery some = new ValuesEvery(false);
    boolean matchAll;

    public int numArgs() {
        return 8194;
    }

    public ValuesEvery(boolean z) {
        this.matchAll = z;
    }

    public void apply(CallContext callContext) throws Throwable {
        Procedure procedure = (Procedure) callContext.getNextArg();
        Object nextArg = callContext.getNextArg();
        boolean z = this.matchAll;
        Procedure.checkArgCount(procedure, 1);
        if (nextArg instanceof Values) {
            int i = 0;
            Values values = (Values) nextArg;
            do {
                i = values.nextPos(i);
                if (i == 0) {
                    break;
                }
                procedure.check1(values.getPosPrevious(i), callContext);
                z = BooleanValue.booleanValue(callContext.runUntilValue());
            } while (z == this.matchAll);
        } else {
            procedure.check1(nextArg, callContext);
            z = BooleanValue.booleanValue(callContext.runUntilValue());
        }
        callContext.consumer.writeBoolean(z);
    }
}
