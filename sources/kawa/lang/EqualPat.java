package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class EqualPat extends Pattern implements Printable, Externalizable {
    Object value;

    public int varCount() {
        return 0;
    }

    public EqualPat() {
    }

    public EqualPat(Object obj) {
        this.value = obj;
    }

    public static EqualPat make(Object obj) {
        return new EqualPat(obj);
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        if ((this.value instanceof String) && (obj instanceof Symbol)) {
            obj = ((Symbol) obj).getName();
        }
        return this.value.equals(obj);
    }

    public void print(Consumer consumer) {
        consumer.write("#<equals: ");
        ReportFormat.print(this.value, consumer);
        consumer.write(62);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.value);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.value = objectInput.readObject();
    }
}
