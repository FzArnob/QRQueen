package kawa.lang;

import gnu.mapping.CallContext;

public class CalledContinuation extends RuntimeException {
    public Continuation continuation;
    public CallContext ctx;
    public Object[] values;

    CalledContinuation(Object[] objArr, Continuation continuation2, CallContext callContext) {
        super("call/cc called");
        this.values = objArr;
        this.continuation = continuation2;
        this.ctx = callContext;
    }
}
