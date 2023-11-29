package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolString extends CpoolEntry {
    CpoolUtf8 str;

    public int getTag() {
        return 8;
    }

    CpoolString() {
    }

    CpoolString(ConstantPool constantPool, int i, CpoolUtf8 cpoolUtf8) {
        super(constantPool, i);
        this.str = cpoolUtf8;
    }

    public final CpoolUtf8 getString() {
        return this.str;
    }

    static final int hashCode(CpoolUtf8 cpoolUtf8) {
        return cpoolUtf8.hashCode() ^ 62223;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.str);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(8);
        dataOutputStream.writeShort(this.str.index);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        if (i > 0) {
            classTypeWriter.print("String ");
            if (i == 2) {
                classTypeWriter.printOptionalIndex((CpoolEntry) this.str);
            }
        }
        classTypeWriter.printConstantTersely(this.str.index, 1);
    }
}
