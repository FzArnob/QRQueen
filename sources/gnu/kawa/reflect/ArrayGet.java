package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArrayGet extends Procedure2 implements Externalizable {
    Type element_type;

    public boolean isSideEffectFree() {
        return true;
    }

    public ArrayGet(Type type) {
        this.element_type = type;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArrayGet");
        Procedure.compilerKey.set(this, "*gnu.kawa.reflect.CompileArrays:getForArrayGet");
    }

    public Object apply2(Object obj, Object obj2) {
        return this.element_type.coerceToObject(Array.get(obj, ((Number) obj2).intValue()));
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.element_type);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.element_type = (Type) objectInput.readObject();
    }
}
