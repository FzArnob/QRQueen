package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.text.Printable;

public class Promise implements Printable {
    Object result;
    Procedure thunk;

    public Promise(Procedure procedure) {
        this.thunk = procedure;
    }

    public Object force() throws Throwable {
        if (this.result == null) {
            Object apply0 = this.thunk.apply0();
            if (this.result == null) {
                this.result = apply0;
            }
        }
        return this.result;
    }

    public static Object force(Object obj) throws Throwable {
        if (obj instanceof Promise) {
            return ((Promise) obj).force();
        }
        if (obj instanceof Future) {
            return ((Future) obj).waitForResult();
        }
        return obj instanceof java.util.concurrent.Future ? ((java.util.concurrent.Future) obj).get() : obj;
    }

    public void print(Consumer consumer) {
        if (this.result == null) {
            consumer.write("#<promise - not forced yet>");
            return;
        }
        consumer.write("#<promise - forced to a ");
        consumer.write(this.result.getClass().getName());
        consumer.write(62);
    }
}
