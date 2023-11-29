package kawa.lang;

import gnu.lists.Consumer;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AnyPat extends Pattern implements Printable, Externalizable {
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
    }

    public int varCount() {
        return 1;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
    }

    public static AnyPat make() {
        return new AnyPat();
    }

    public void print(Consumer consumer) {
        consumer.write("#<match any>");
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        objArr[i] = obj;
        return true;
    }
}
