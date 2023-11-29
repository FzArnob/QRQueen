package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GetNamedInstancePart extends ProcedureN implements Externalizable, HasSetter {
    boolean isField;
    String pname;

    public GetNamedInstancePart() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedInstancePart");
    }

    public GetNamedInstancePart(String str) {
        this();
        setPartName(str);
    }

    public void setPartName(String str) {
        setName("get-instance-part:" + str);
        if (str.length() <= 1 || str.charAt(0) != '.') {
            this.isField = false;
            this.pname = str;
            return;
        }
        this.isField = true;
        this.pname = str.substring(1);
    }

    public int numArgs() {
        if (this.isField) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return -4095;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        checkArgCount(this, objArr.length);
        if (this.isField) {
            return SlotGet.field(objArr[0], this.pname);
        }
        Object[] objArr2 = new Object[(objArr.length + 1)];
        objArr2[0] = objArr[0];
        objArr2[1] = this.pname;
        System.arraycopy(objArr, 1, objArr2, 2, objArr.length - 1);
        return Invoke.invoke.applyN(objArr2);
    }

    public Procedure getSetter() {
        if (this.isField) {
            return new SetNamedInstancePart(this.pname);
        }
        throw new RuntimeException("no setter for instance method call");
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        String str;
        if (this.isField) {
            str = "." + this.pname;
        } else {
            str = this.pname;
        }
        objectOutput.writeObject(str);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setPartName((String) objectInput.readObject());
    }
}
