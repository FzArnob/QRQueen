package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class S32Vector extends SimpleVector implements Externalizable, Comparable {
    protected static int[] empty = new int[0];
    int[] data;

    public int getElementKind() {
        return 22;
    }

    public String getTag() {
        return "s32";
    }

    public S32Vector() {
        this.data = empty;
    }

    public S32Vector(int i, int i2) {
        int[] iArr = new int[i];
        this.data = iArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                iArr[i] = i2;
            } else {
                return;
            }
        }
    }

    public S32Vector(int i) {
        this.data = new int[i];
        this.size = i;
    }

    public S32Vector(int[] iArr) {
        this.data = iArr;
        this.size = iArr.length;
    }

    public S32Vector(Sequence sequence) {
        this.data = new int[sequence.size()];
        addAll(sequence);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int[] iArr = this.data;
        int length = iArr.length;
        if (length != i) {
            int[] iArr2 = new int[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.data = iArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final int intAt(int i) {
        if (i <= this.size) {
            return this.data[i];
        }
        throw new IndexOutOfBoundsException();
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
        int[] iArr = this.data;
        int i2 = iArr[i];
        iArr[i] = Convert.toInt(obj);
        return Convert.toObject(i2);
    }

    public final void setIntAt(int i, int i2) {
        if (i <= this.size) {
            this.data[i] = i2;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setIntAtBuffer(int i, int i2) {
        this.data[i] = i2;
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
        consumer.writeInt(this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            if (i3 > this.size) {
                i3 = this.size;
            }
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeInt(this.data[i4]);
            }
        }
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (S32Vector) obj);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeInt(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        int[] iArr = new int[readInt];
        for (int i = 0; i < readInt; i++) {
            iArr[i] = objectInput.readInt();
        }
        this.data = iArr;
        this.size = readInt;
    }
}
