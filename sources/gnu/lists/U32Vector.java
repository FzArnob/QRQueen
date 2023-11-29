package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import net.lingala.zip4j.util.InternalZipConstants;

public class U32Vector extends SimpleVector implements Externalizable, Comparable {
    int[] data;

    public int getElementKind() {
        return 19;
    }

    public String getTag() {
        return "u32";
    }

    public U32Vector() {
        this.data = S32Vector.empty;
    }

    public U32Vector(int i, int i2) {
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

    public U32Vector(int i) {
        this.data = new int[i];
        this.size = i;
    }

    public U32Vector(int[] iArr) {
        this.data = iArr;
        this.size = iArr.length;
    }

    public U32Vector(Sequence sequence) {
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

    public final int intAtBuffer(int i) {
        return this.data[i];
    }

    public final long longAtBuffer(int i) {
        return ((long) this.data[i]) & InternalZipConstants.ZIP_64_LIMIT;
    }

    public final long longAt(int i) {
        if (i <= this.size) {
            return longAtBuffer(i);
        }
        throw new IndexOutOfBoundsException();
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
        int[] iArr = this.data;
        int i2 = iArr[i];
        iArr[i] = Convert.toIntUnsigned(obj);
        return Convert.toObjectUnsigned(i2);
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
        return compareToLong(this, (U32Vector) obj);
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
