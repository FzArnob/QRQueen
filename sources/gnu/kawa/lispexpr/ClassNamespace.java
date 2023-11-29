package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.kawa.functions.GetNamedPart;
import gnu.mapping.Namespace;
import gnu.mapping.WrappedException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class ClassNamespace extends Namespace implements Externalizable {
    ClassType ctype;

    public ClassType getClassType() {
        return this.ctype;
    }

    public static ClassNamespace getInstance(String str, ClassType classType) {
        synchronized (nsTable) {
            Object obj = nsTable.get(str);
            if (obj instanceof ClassNamespace) {
                ClassNamespace classNamespace = (ClassNamespace) obj;
                return classNamespace;
            }
            ClassNamespace classNamespace2 = new ClassNamespace(classType);
            nsTable.put(str, classNamespace2);
            return classNamespace2;
        }
    }

    public ClassNamespace() {
    }

    public ClassNamespace(ClassType classType) {
        setName("class:" + classType.getName());
        this.ctype = classType;
    }

    public Object get(String str) {
        try {
            return GetNamedPart.getTypePart(this.ctype, str);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.ctype);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.ctype = (ClassType) objectInput.readObject();
        setName("class:" + this.ctype.getName());
    }

    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        if (name != null) {
            Namespace namespace = (Namespace) nsTable.get(name);
            if (namespace instanceof ClassNamespace) {
                return namespace;
            }
            nsTable.put(name, this);
        }
        return this;
    }
}
