package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArrayNew extends Procedure1 implements Externalizable {
    Type element_type;

    public boolean isSideEffectFree() {
        return true;
    }

    public ArrayNew(Type type) {
        this.element_type = type;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArrayNew");
        Procedure.compilerKey.set(this, "*gnu.kawa.reflect.CompileArrays:getForArrayNew");
    }

    public Object apply1(Object obj) {
        return Array.newInstance(this.element_type.getImplementationType().getReflectClass(), ((Number) obj).intValue());
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.element_type);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.element_type = (Type) objectInput.readObject();
    }
}
