package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class BitVector extends SimpleVector implements Externalizable {
    protected static boolean[] empty = new boolean[0];
    boolean[] data;

    public int getElementKind() {
        return 27;
    }

    public String getTag() {
        return "b";
    }

    public BitVector() {
        this.data = empty;
    }

    public BitVector(int i, boolean z) {
        boolean[] zArr = new boolean[i];
        this.data = zArr;
        this.size = i;
        if (z) {
            while (true) {
                i--;
                if (i >= 0) {
                    zArr[i] = true;
                } else {
                    return;
                }
            }
        }
    }

    public BitVector(int i) {
        this.data = new boolean[i];
        this.size = i;
    }

    public BitVector(boolean[] zArr) {
        this.data = zArr;
        this.size = zArr.length;
    }

    public BitVector(Sequence sequence) {
        this.data = new boolean[sequence.size()];
        addAll(sequence);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        boolean[] zArr = this.data;
        int length = zArr.length;
        if (length != i) {
            boolean[] zArr2 = new boolean[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.data = zArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final boolean booleanAt(int i) {
        if (i <= this.size) {
            return this.data[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public final boolean booleanAtBuffer(int i) {
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
        boolean[] zArr = this.data;
        boolean z = zArr[i];
        zArr[i] = Convert.toBoolean(obj);
        return Convert.toObject(z);
    }

    public final void setBooleanAt(int i, boolean z) {
        if (i <= this.size) {
            this.data[i] = z;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setBooleanAtBuffer(int i, boolean z) {
        this.data[i] = z;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        while (true) {
            i2--;
            if (i2 >= 0) {
                this.data[i] = false;
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
        consumer.writeBoolean(this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeBoolean(this.data[i4]);
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeBoolean(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        boolean[] zArr = new boolean[readInt];
        for (int i = 0; i < readInt; i++) {
            zArr[i] = objectInput.readBoolean();
        }
        this.data = zArr;
        this.size = readInt;
    }
}
