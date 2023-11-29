package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolUtf8 extends CpoolEntry {
    String string;

    public int getTag() {
        return 1;
    }

    CpoolUtf8() {
    }

    CpoolUtf8(ConstantPool constantPool, int i, String str) {
        super(constantPool, i);
        this.string = str;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = this.string.hashCode();
        }
        return this.hash;
    }

    public final void intern() {
        this.string = this.string.intern();
    }

    public final String getString() {
        return this.string;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(1);
        dataOutputStream.writeUTF(this.string);
    }

    public void print(ClassTypeWriter classTypeWriter, int i) {
        if (i > 0) {
            classTypeWriter.print("Utf8: ");
        }
        classTypeWriter.printQuotedString(this.string);
    }
}
