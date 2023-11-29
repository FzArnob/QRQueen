package gnu.lists;

public class S8Vector extends ByteVector {
    public int getElementKind() {
        return 18;
    }

    public String getTag() {
        return "s8";
    }

    public S8Vector() {
        this.data = ByteVector.empty;
    }

    public S8Vector(int i, byte b) {
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

    public S8Vector(int i) {
        this.data = new byte[i];
        this.size = i;
    }

    public S8Vector(byte[] bArr) {
        this.data = bArr;
        this.size = bArr.length;
    }

    public S8Vector(Sequence sequence) {
        this.data = new byte[sequence.size()];
        addAll(sequence);
    }

    public final int intAtBuffer(int i) {
        return this.data[i];
    }

    public final Object get(int i) {
        if (i <= this.size) {
            return Convert.toObject(this.data[i]);
        }
        throw new IndexOutOfBoundsException();
    }

    public final Object getBuffer(int i) {
        return Convert.toObject(this.data[i]);
    }

    public Object setBuffer(int i, Object obj) {
        byte b = this.data[i];
        this.data[i] = Convert.toByte(obj);
        return Convert.toObject(b);
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (S8Vector) obj);
    }
}
