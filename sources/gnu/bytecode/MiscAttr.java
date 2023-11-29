package gnu.bytecode;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.DataOutputStream;
import java.io.IOException;

public class MiscAttr extends Attribute {
    byte[] data;
    int dataLength;
    int offset;

    public MiscAttr(String str, byte[] bArr, int i, int i2) {
        super(str);
        this.data = bArr;
        this.offset = i;
        this.dataLength = i2;
    }

    public MiscAttr(String str, byte[] bArr) {
        this(str, bArr, 0, bArr.length);
    }

    public int getLength() {
        return this.dataLength;
    }

    /* access modifiers changed from: protected */
    public int u1(int i) {
        return this.data[i] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: protected */
    public int u2(int i) {
        byte[] bArr = this.data;
        return ((bArr[i] & Ev3Constants.Opcode.TST) << 8) + (bArr[i + 1] & Ev3Constants.Opcode.TST);
    }

    /* access modifiers changed from: protected */
    public int u1() {
        int i = this.offset;
        this.offset = i + 1;
        return u1(i);
    }

    /* access modifiers changed from: protected */
    public int u2() {
        int u2 = u2(this.offset);
        this.offset += 2;
        return u2;
    }

    /* access modifiers changed from: protected */
    public void put1(int i) {
        byte[] bArr = this.data;
        if (bArr == null) {
            this.data = new byte[20];
        } else {
            int i2 = this.dataLength;
            if (i2 >= bArr.length) {
                byte[] bArr2 = new byte[(bArr.length * 2)];
                System.arraycopy(bArr, 0, bArr2, 0, i2);
                this.data = bArr2;
            }
        }
        byte[] bArr3 = this.data;
        int i3 = this.dataLength;
        this.dataLength = i3 + 1;
        bArr3[i3] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public void put2(int i) {
        put1((byte) (i >> 8));
        put1((byte) i);
    }

    /* access modifiers changed from: protected */
    public void put2(int i, int i2) {
        byte[] bArr = this.data;
        bArr[i] = (byte) (i2 >> 8);
        bArr[i + 1] = (byte) i2;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.data, this.offset, this.dataLength);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        super.print(classTypeWriter);
        int length = getLength();
        int i = 0;
        while (i < length) {
            byte b = this.data[i];
            if (i % 20 == 0) {
                classTypeWriter.print(' ');
            }
            classTypeWriter.print(' ');
            classTypeWriter.print(Character.forDigit((b >> 4) & 15, 16));
            classTypeWriter.print(Character.forDigit(b & 15, 16));
            i++;
            if (i % 20 == 0 || i == length) {
                classTypeWriter.println();
            }
        }
    }
}
