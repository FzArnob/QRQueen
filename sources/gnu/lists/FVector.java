package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class FVector extends SimpleVector implements Externalizable, Consumable, Comparable {
    protected static Object[] empty = new Object[0];
    public Object[] data;

    public FVector() {
        this.data = empty;
    }

    public FVector(int i) {
        this.size = i;
        this.data = new Object[i];
    }

    public FVector(int i, Object obj) {
        Object[] objArr = new Object[i];
        if (obj != null) {
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = obj;
            }
        }
        this.data = objArr;
        this.size = i;
    }

    public FVector(Object[] objArr) {
        this.size = objArr.length;
        this.data = objArr;
    }

    public FVector(List list) {
        this.data = new Object[list.size()];
        addAll(list);
    }

    public static FVector make(Object... objArr) {
        return new FVector(objArr);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        Object[] objArr = this.data;
        int length = objArr.length;
        if (length != i) {
            Object[] objArr2 = new Object[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(objArr, 0, objArr2, 0, i);
            this.data = objArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public void shift(int i, int i2, int i3) {
        Object[] objArr = this.data;
        System.arraycopy(objArr, i, objArr, i2, i3);
    }

    public final Object getBuffer(int i) {
        return this.data[i];
    }

    public final Object get(int i) {
        if (i < this.size) {
            return this.data[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final Object setBuffer(int i, Object obj) {
        Object[] objArr = this.data;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        Object[] objArr = this.data;
        while (true) {
            i2--;
            if (i2 >= 0) {
                objArr[i] = null;
                i++;
            } else {
                return;
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof FVector)) {
            FVector fVector = (FVector) obj;
            int i = this.size;
            if (fVector.data != null && fVector.size == i) {
                Object[] objArr = this.data;
                Object[] objArr2 = fVector.data;
                for (int i2 = 0; i2 < i; i2++) {
                    if (!objArr[i2].equals(objArr2[i2])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int compareTo(Object obj) {
        FVector fVector = (FVector) obj;
        Object[] objArr = this.data;
        Object[] objArr2 = fVector.data;
        int i = this.size;
        int i2 = fVector.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            int compareTo = ((Comparable) objArr[i4]).compareTo((Comparable) objArr2[i4]);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return i - i2;
    }

    public final void setAll(Object obj) {
        Object[] objArr = this.data;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                objArr[i] = obj;
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
        consumer.writeObject(this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            if (i3 > this.size) {
                i3 = this.size;
            }
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeObject(this.data[i4]);
            }
        }
    }

    public void consume(Consumer consumer) {
        consumer.startElement("#vector");
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            consumer.writeObject(this.data[i2]);
        }
        consumer.endElement();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeObject(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        Object[] objArr = new Object[readInt];
        for (int i = 0; i < readInt; i++) {
            objArr[i] = objectInput.readObject();
        }
        this.size = readInt;
        this.data = objArr;
    }
}
