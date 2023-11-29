package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArraySet extends Procedure3 implements Externalizable {
    Type element_type;

    public ArraySet(Type type) {
        this.element_type = type;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArraySet");
        Procedure.compilerKey.set(this, "*gnu.kawa.reflect.CompileArrays:getForArraySet");
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        Array.set(obj, ((Number) obj2).intValue(), this.element_type.coerceFromObject(obj3));
        return Values.empty;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.element_type);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.element_type = (Type) objectInput.readObject();
    }
}
