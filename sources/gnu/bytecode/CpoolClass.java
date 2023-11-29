package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolClass extends CpoolEntry {
    ObjectType clas;
    CpoolUtf8 name;

    public int getTag() {
        return 7;
    }

    CpoolClass() {
    }

    CpoolClass(ConstantPool constantPool, int i, CpoolUtf8 cpoolUtf8) {
        super(constantPool, i);
        this.name = cpoolUtf8;
    }

    public final CpoolUtf8 getName() {
        return this.name;
    }

    public final String getStringName() {
        return this.name.string;
    }

    public final String getClassName() {
        return this.name.string.replace('/', '.');
    }

    public final ObjectType getClassType() {
        ObjectType objectType;
        ObjectType objectType2 = this.clas;
        if (objectType2 != null) {
            return objectType2;
        }
        String str = this.name.string;
        if (str.charAt(0) == '[') {
            objectType = (ObjectType) Type.signatureToType(str);
        } else {
            objectType = ClassType.make(str.replace('/', '.'));
        }
        this.clas = objectType;
        return objectType;
    }

    static final int hashCode(CpoolUtf8 cpoolUtf8) {
        return cpoolUtf8.hashCode() ^ 3855;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.name);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(7);
        dataOutputStream.writeShort(this.name.index);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        if (i == 1) {
            classTypeWriter.print("Class ");
        } else if (i > 1) {
            classTypeWriter.print("Class name: ");
            classTypeWriter.printOptionalIndex((CpoolEntry) this.name);
        }
        String str = this.name.string;
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '[') {
            classTypeWriter.print(str.replace('/', '.'));
        } else {
            Type.printSignature(str, 0, length, classTypeWriter);
        }
    }
}
