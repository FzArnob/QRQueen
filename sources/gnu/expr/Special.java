package gnu.expr;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Special implements Printable, Externalizable {
    public static final Special abstractSpecial = new Special("abstract");
    public static final Special dfault = new Special("default");
    public static final Object eof = Sequence.eofValue;
    public static final Special key = new Special("key");
    public static final Special optional = new Special("optional");
    public static final Special rest = new Special("rest");
    public static final Special undefined = new Special("undefined");
    private String name;

    public Special() {
    }

    private Special(String str) {
        this.name = new String(str);
    }

    public static Special make(String str) {
        if (str == "optional") {
            return optional;
        }
        if (str == "rest") {
            return rest;
        }
        if (str == "key") {
            return key;
        }
        if (str == "default") {
            return dfault;
        }
        return new Special(str);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        return "#!" + this.name;
    }

    public void print(Consumer consumer) {
        consumer.write("#!");
        consumer.write(this.name);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.name);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = objectInput.readUTF();
    }

    public Object readResolve() throws ObjectStreamException {
        return make(this.name);
    }
}
