package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U16Vector extends SimpleVector implements Externalizable, Comparable {
    short[] data;

    public int getElementKind() {
        return 19;
    }

    public String getTag() {
        return "u16";
    }

    public U16Vector() {
        this.data = S16Vector.empty;
    }

    public U16Vector(int i, short s) {
        short[] sArr = new short[i];
        this.data = sArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                sArr[i] = s;
            } else {
                return;
            }
        }
    }

    public U16Vector(int i) {
        this.data = new short[i];
        this.size = i;
    }

    public U16Vector(short[] sArr) {
        this.data = sArr;
        this.size = sArr.length;
    }

    public U16Vector(Sequence sequence) {
        this.data = new short[sequence.size()];
        addAll(sequence);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        short[] sArr = this.data;
        int length = sArr.length;
        if (length != i) {
            short[] sArr2 = new short[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(sArr, 0, sArr2, 0, i);
            this.data = sArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final short shortAt(int i) {
        if (i <= this.size) {
            return this.data[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public final short shortAtBuffer(int i) {
        return this.data[i];
    }

    public final int intAtBuffer(int i) {
        return this.data[i] & 65535;
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
        short[] sArr = this.data;
        short s = sArr[i];
        sArr[i] = Convert.toShortUnsigned(obj);
        return Convert.toObjectUnsigned(s);
    }

    public final void setShortAt(int i, short s) {
        if (i <= this.size) {
            this.data[i] = s;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setShortAtBuffer(int i, short s) {
        this.data[i] = s;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        while (true) {
            i2--;
            if (i2 >= 0) {
                this.data[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int i2 = i >>> 1;
        if (i2 >= this.size) {
            return false;
        }
        consumer.writeInt(this.data[i2] & 65535);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            if (i3 > this.size) {
                i3 = this.size;
            }
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeInt(this.data[i4] & 65535);
            }
        }
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (U16Vector) obj);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeShort(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        short[] sArr = new short[readInt];
        for (int i = 0; i < readInt; i++) {
            sArr[i] = objectInput.readShort();
        }
        this.data = sArr;
        this.size = readInt;
    }
}
