package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class SimpleSymbol extends Symbol {
    public SimpleSymbol() {
    }

    public SimpleSymbol(String str) {
        super(Namespace.EmptyNamespace, str);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = ((String) objectInput.readObject()).intern();
    }

    public Object readResolve() throws ObjectStreamException {
        return Namespace.EmptyNamespace.getSymbol(getName().intern());
    }
}
