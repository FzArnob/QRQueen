package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolValue2 extends CpoolEntry {
    int tag;
    long value;

    static int hashCode(long j) {
        return (int) j;
    }

    CpoolValue2(int i) {
        this.tag = i;
    }

    CpoolValue2(ConstantPool constantPool, int i, int i2, long j) {
        super(constantPool, i2);
        this.tag = i;
        this.value = j;
        constantPool.count++;
    }

    public int getTag() {
        return this.tag;
    }

    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = hashCode(this.value);
        }
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.tag);
        dataOutputStream.writeLong(this.value);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        if (this.tag == 5) {
            if (i > 0) {
                classTypeWriter.print("Long ");
            }
            classTypeWriter.print(this.value);
            if (i > 1 && this.value != 0) {
                classTypeWriter.print("=0x");
                classTypeWriter.print(Long.toHexString(this.value));
                return;
            }
            return;
        }
        if (i > 0) {
            classTypeWriter.print("Double ");
        }
        classTypeWriter.print(Double.longBitsToDouble(this.value));
        if (i > 1) {
            classTypeWriter.print("=0x");
            classTypeWriter.print(Long.toHexString(this.value));
        }
    }
}
