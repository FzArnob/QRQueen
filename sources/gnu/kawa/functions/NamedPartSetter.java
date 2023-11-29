package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.Setter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* compiled from: GetNamedPart */
class NamedPartSetter extends Setter implements Externalizable {
    public NamedPartSetter(NamedPart namedPart) {
        super(namedPart);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPartSetter");
    }

    public int numArgs() {
        return ((NamedPart) this.getter).kind == 'D' ? 8193 : -4096;
    }

    /* access modifiers changed from: package-private */
    public Procedure getGetter() {
        return this.getter;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.getter);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.getter = (Procedure) objectInput.readObject();
    }
}
