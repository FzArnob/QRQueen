package gnu.mapping;

public class Future extends Thread {
    public RunnableClosure closure;

    public Future(Procedure procedure, CallContext callContext) {
        this.closure = new RunnableClosure(procedure, callContext);
    }

    public Future(Procedure procedure, InPort inPort, OutPort outPort, OutPort outPort2) {
        this.closure = new RunnableClosure(procedure, inPort, outPort, outPort2);
    }

    public Future(Procedure procedure) {
        this.closure = new RunnableClosure(procedure);
    }

    public static Future make(Procedure procedure, Environment environment, InPort inPort, OutPort outPort, OutPort outPort2) {
        Environment saveCurrent = Environment.setSaveCurrent(environment);
        try {
            return new Future(procedure, inPort, outPort, outPort2);
        } finally {
            Environment.restoreCurrent(saveCurrent);
        }
    }

    public final CallContext getCallContext() {
        return this.closure.getCallContext();
    }

    public void run() {
        this.closure.run();
    }

    public Object waitForResult() throws Throwable {
        try {
            join();
            Throwable th = this.closure.exception;
            if (th == null) {
                return this.closure.result;
            }
            throw th;
        } catch (InterruptedException unused) {
            throw new RuntimeException("thread join [force] was interrupted");
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#<future ");
        stringBuffer.append(getName());
        stringBuffer.append(">");
        return stringBuffer.toString();
    }
}
