package kawa.lang;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Continuation extends MethodProc {
    static int counter;
    int id;
    public boolean invoked;

    public Continuation(CallContext callContext) {
    }

    public void apply(CallContext callContext) {
        if (this.invoked) {
            throw new GenericError("implementation restriction: continuation can only be used once");
        }
        throw new CalledContinuation(callContext.values, this, callContext);
    }

    public static void handleException$X(Throwable th, Continuation continuation, CallContext callContext) throws Throwable {
        if (th instanceof CalledContinuation) {
            CalledContinuation calledContinuation = (CalledContinuation) th;
            if (calledContinuation.continuation == continuation) {
                continuation.invoked = true;
                for (Object writeObject : calledContinuation.values) {
                    callContext.consumer.writeObject(writeObject);
                }
                return;
            }
        }
        throw th;
    }

    public static Object handleException(Throwable th, Continuation continuation) throws Throwable {
        if (th instanceof CalledContinuation) {
            CalledContinuation calledContinuation = (CalledContinuation) th;
            if (calledContinuation.continuation == continuation) {
                continuation.invoked = true;
                return Values.make(calledContinuation.values);
            }
        }
        throw th;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#<continuation ");
        sb.append(this.id);
        sb.append(this.invoked ? " (invoked)>" : ">");
        return sb.toString();
    }
}
