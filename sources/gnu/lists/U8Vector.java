package gnu.lists;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public class U8Vector extends ByteVector {
    public int getElementKind() {
        return 17;
    }

    public String getTag() {
        return "u8";
    }

    public U8Vector() {
        this.data = ByteVector.empty;
    }

    public U8Vector(int i, byte b) {
        byte[] bArr = new byte[i];
        this.data = bArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                bArr[i] = b;
            } else {
                return;
            }
        }
    }

    public U8Vector(int i) {
        this.data = new byte[i];
        this.size = i;
    }

    public U8Vector(byte[] bArr) {
        this.data = bArr;
        this.size = bArr.length;
    }

    public U8Vector(Sequence sequence) {
        this.data = new byte[sequence.size()];
        addAll(sequence);
    }

    public final int intAtBuffer(int i) {
        return this.data[i] & Ev3Constants.Opcode.TST;
    }

    public final Object get(int i) {
        if (i <= this.size) {
            return Convert.toObjectUnsigned(this.data[i]);
        }
        throw new IndexOutOfBoundsException();
    }

    public final Object getBuffer(int i) {
        return Convert.toObjectUnsigned(this.data[i]);
    }

    public Object setBuffer(int i, Object obj) {
        byte b = this.data[i];
        this.data[i] = Convert.toByteUnsigned(obj);
        return Convert.toObjectUnsigned(b);
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (U8Vector) obj);
    }
}
