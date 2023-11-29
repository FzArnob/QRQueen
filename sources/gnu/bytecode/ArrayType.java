package gnu.bytecode;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class ArrayType extends ObjectType implements Externalizable {
    public Type elements;

    public ArrayType(Type type) {
        this(type, type.getName() + "[]");
    }

    ArrayType(Type type, String str) {
        this.this_name = str;
        this.elements = type;
    }

    public String getSignature() {
        if (this.signature == null) {
            setSignature("[" + this.elements.getSignature());
        }
        return this.signature;
    }

    public Type getImplementationType() {
        Type implementationType = this.elements.getImplementationType();
        return this.elements == implementationType ? this : make(implementationType);
    }

    static ArrayType make(String str) {
        Type type = Type.getType(str.substring(0, str.length() - 2));
        ArrayType arrayType = type.array_type;
        if (arrayType != null) {
            return arrayType;
        }
        ArrayType arrayType2 = new ArrayType(type, str);
        type.array_type = arrayType2;
        return arrayType2;
    }

    public static ArrayType make(Type type) {
        ArrayType arrayType = type.array_type;
        if (arrayType != null) {
            return arrayType;
        }
        ArrayType arrayType2 = new ArrayType(type, type.getName() + "[]");
        type.array_type = arrayType2;
        return arrayType2;
    }

    public Type getComponentType() {
        return this.elements;
    }

    public String getInternalName() {
        return getSignature();
    }

    public Class getReflectClass() {
        try {
            if (this.reflectClass == null) {
                this.reflectClass = Class.forName(getInternalName().replace('/', '.'), false, this.elements.getReflectClass().getClassLoader());
            }
            this.flags |= 16;
        } catch (ClassNotFoundException e) {
            if ((this.flags & 16) != 0) {
                RuntimeException runtimeException = new RuntimeException("no such array class: " + getName());
                runtimeException.initCause(e);
                throw runtimeException;
            }
        }
        return this.reflectClass;
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        if (i <= 0) {
            return 0;
        }
        int methods = Type.objectType.getMethods(filter, 0, list);
        if (i <= 1 || !filter.select(Type.clone_method)) {
            return methods;
        }
        if (list != null) {
            list.add(Type.clone_method);
        }
        return methods + 1;
    }

    public int compare(Type type) {
        if (type == nullType) {
            return 1;
        }
        if (type instanceof ArrayType) {
            return this.elements.compare(((ArrayType) type).elements);
        }
        return (type.getName().equals("java.lang.Object") || type == toStringType) ? -1 : -3;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.elements);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.elements = (Type) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        ArrayType arrayType = this.elements.array_type;
        if (arrayType != null) {
            return arrayType;
        }
        this.elements.array_type = this;
        return this;
    }
}
