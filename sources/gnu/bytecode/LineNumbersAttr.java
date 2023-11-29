package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumbersAttr extends Attribute {
    int linenumber_count;
    short[] linenumber_table;

    public LineNumbersAttr(CodeAttr codeAttr) {
        super("LineNumberTable");
        addToFrontOf(codeAttr);
        codeAttr.lines = this;
    }

    public LineNumbersAttr(short[] sArr, CodeAttr codeAttr) {
        this(codeAttr);
        this.linenumber_table = sArr;
        this.linenumber_count = sArr.length >> 1;
    }

    public void put(int i, int i2) {
        short[] sArr = this.linenumber_table;
        if (sArr == null) {
            this.linenumber_table = new short[32];
        } else {
            int i3 = this.linenumber_count;
            if (i3 * 2 >= sArr.length) {
                short[] sArr2 = new short[(sArr.length * 2)];
                System.arraycopy(sArr, 0, sArr2, 0, i3 * 2);
                this.linenumber_table = sArr2;
            }
        }
        short[] sArr3 = this.linenumber_table;
        int i4 = this.linenumber_count;
        sArr3[i4 * 2] = (short) i2;
        sArr3[(i4 * 2) + 1] = (short) i;
        this.linenumber_count = i4 + 1;
    }

    public final int getLength() {
        return (this.linenumber_count * 4) + 2;
    }

    public int getLineCount() {
        return this.linenumber_count;
    }

    public short[] getLineNumberTable() {
        return this.linenumber_table;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.linenumber_count);
        int i = this.linenumber_count * 2;
        for (int i2 = 0; i2 < i; i2++) {
            dataOutputStream.writeShort(this.linenumber_table[i2]);
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", count: ");
        classTypeWriter.println(this.linenumber_count);
        for (int i = 0; i < this.linenumber_count; i++) {
            classTypeWriter.print("  line: ");
            int i2 = i * 2;
            classTypeWriter.print(this.linenumber_table[i2 + 1] & 65535);
            classTypeWriter.print(" at pc: ");
            classTypeWriter.println(this.linenumber_table[i2] & 65535);
        }
    }
}
