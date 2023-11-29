package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U64Vector extends SimpleVector implements Externalizable, Comparable {
    long[] data;

    public int getElementKind() {
        return 23;
    }

    public String getTag() {
        return "u64";
    }

    public U64Vector() {
        this.data = S64Vector.empty;
    }

    public U64Vector(int i, long j) {
        long[] jArr = new long[i];
        this.data = jArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                jArr[i] = j;
            } else {
                return;
            }
        }
    }

    public U64Vector(int i) {
        this.data = new long[i];
        this.size = i;
    }

    public U64Vector(long[] jArr) {
        this.data = jArr;
        this.size = jArr.length;
    }

    public U64Vector(Sequence sequence) {
        this.data = new long[sequence.size()];
        addAll(sequence);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        long[] jArr = this.data;
        int length = jArr.length;
        if (length != i) {
            long[] jArr2 = new long[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.data = jArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final int intAtBuffer(int i) {
        return (int) this.data[i];
    }

    public final long longAt(int i) {
        if (i <= this.size) {
            return this.data[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public final long longAtBuffer(int i) {
        return this.data[i];
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
        long[] jArr = this.data;
        long j = jArr[i];
        jArr[i] = Convert.toLongUnsigned(obj);
        return Convert.toObjectUnsigned(j);
    }

    public final void setLongAt(int i, long j) {
        if (i <= this.size) {
            this.data[i] = j;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setLongAtBuffer(int i, long j) {
        this.data[i] = j;
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
        consumer.writeLong(this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            if (i3 > this.size) {
                i3 = this.size;
            }
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeLong(this.data[i4]);
            }
        }
    }

    public int compareTo(Object obj) {
        U64Vector u64Vector = (U64Vector) obj;
        long[] jArr = this.data;
        long[] jArr2 = u64Vector.data;
        int i = this.size;
        int i2 = u64Vector.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            long j = jArr[i4];
            long j2 = jArr2[i4];
            if (j != j2) {
                return (j ^ Long.MIN_VALUE) > (Long.MIN_VALUE ^ j2) ? 1 : -1;
            }
        }
        return i - i2;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeLong(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        long[] jArr = new long[readInt];
        for (int i = 0; i < readInt; i++) {
            jArr[i] = objectInput.readLong();
        }
        this.data = jArr;
        this.size = readInt;
    }
}
