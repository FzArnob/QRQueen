package kawa.standard;

import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class call_with_values extends Procedure2 {
    public static final call_with_values callWithValues;

    static {
        call_with_values call_with_values = new call_with_values();
        callWithValues = call_with_values;
        call_with_values.setName("call-with-values");
    }

    public static Object callWithValues(Procedure procedure, Procedure procedure2) throws Throwable {
        Object apply0 = procedure.apply0();
        if (apply0 instanceof Values) {
            return ((Values) apply0).call_with(procedure2);
        }
        return procedure2.apply1(apply0);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        return callWithValues((Procedure) obj, (Procedure) obj2);
    }

    public void apply(CallContext callContext) throws Throwable {
        Procedure.checkArgCount(this, 2);
        Object[] args = callContext.getArgs();
        Object apply0 = ((Procedure) args[0]).apply0();
        Procedure procedure = (Procedure) args[1];
        if (apply0 instanceof Values) {
            procedure.checkN(((Values) apply0).getValues(), callContext);
        } else {
            procedure.check1(apply0, callContext);
        }
    }
}
