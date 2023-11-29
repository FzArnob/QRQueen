package gnu.lists;

import java.util.Collection;

public abstract class SimpleVector extends AbstractSequence implements Sequence, Array {
    public int size;

    /* access modifiers changed from: protected */
    public abstract void clearBuffer(int i, int i2);

    public int createPos(int i, boolean z) {
        return (i << 1) | z ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public abstract Object getBuffer();

    /* access modifiers changed from: protected */
    public abstract Object getBuffer(int i);

    public abstract int getBufferLength();

    public int getElementKind() {
        return 32;
    }

    public String getTag() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return (i & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Object setBuffer(int i, Object obj);

    public abstract void setBufferLength(int i);

    public final int size() {
        return this.size;
    }

    public void setSize(int i) {
        int i2 = this.size;
        this.size = i;
        if (i < i2) {
            clearBuffer(i, i2 - i);
            return;
        }
        int bufferLength = getBufferLength();
        if (i > bufferLength) {
            int i3 = 16;
            if (bufferLength >= 16) {
                i3 = bufferLength * 2;
            }
            if (i <= i3) {
                i = i3;
            }
            setBufferLength(i);
        }
    }

    /* access modifiers changed from: protected */
    public void resizeShift(int i, int i2, int i3, int i4) {
        int i5 = i4 - i3;
        int bufferLength = getBufferLength();
        int i6 = (bufferLength - (i2 - i)) + i5;
        if (i6 > bufferLength) {
            setBufferLength(i6);
            this.size = i6;
        }
        int i7 = i - i3;
        if (i7 >= 0) {
            int i8 = bufferLength - i2;
            shift(i2, i6 - i8, i8);
            if (i7 > 0) {
                shift(i3, i4, i7);
            }
        } else {
            int i9 = i6 - i4;
            shift(bufferLength - i9, i4, i9);
            shift(i2, i, i3 - i);
        }
        clearBuffer(i3, i5);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        return i == -1 ? this.size : i >>> 1;
    }

    public int nextPos(int i) {
        int i2;
        if (i == -1 || (i2 = i >>> 1) == this.size) {
            return 0;
        }
        return (i2 << 1) + 3;
    }

    public Object get(int i) {
        if (i < this.size) {
            return getBuffer(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public Object getPosNext(int i) {
        int i2 = i >>> 1;
        return i2 >= this.size ? eofValue : getBuffer(i2);
    }

    public int intAtBuffer(int i) {
        return Convert.toInt(getBuffer(i));
    }

    public int intAt(int i) {
        if (i < this.size) {
            return intAtBuffer(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public long longAt(int i) {
        if (i < this.size) {
            return longAtBuffer(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public long longAtBuffer(int i) {
        return Convert.toLong(getBuffer(i));
    }

    public Object getRowMajor(int i) {
        return get(i);
    }

    public Object set(int i, Object obj) {
        if (i < this.size) {
            Object buffer = getBuffer(i);
            setBuffer(i, obj);
            return buffer;
        }
        throw new IndexOutOfBoundsException();
    }

    public void fill(Object obj) {
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                setBuffer(i, obj);
            } else {
                return;
            }
        }
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int i3 = i2 == -1 ? this.size : i2 >>> 1;
        for (int i4 = i == -1 ? this.size : i >>> 1; i4 < i3; i4++) {
            setBuffer(i4, obj);
        }
    }

    public void fill(int i, int i2, Object obj) {
        if (i < 0 || i2 > this.size) {
            throw new IndexOutOfBoundsException();
        }
        while (i < i2) {
            setBuffer(i, obj);
            i++;
        }
    }

    public void shift(int i, int i2, int i3) {
        Object buffer = getBuffer();
        System.arraycopy(buffer, i, buffer, i2, i3);
    }

    public boolean add(Object obj) {
        add(this.size, obj);
        return true;
    }

    /* access modifiers changed from: protected */
    public int addPos(int i, Object obj) {
        int i2 = i >>> 1;
        add(i2, obj);
        return (i2 << 1) + 3;
    }

    public void add(int i, Object obj) {
        int i2 = this.size + 1;
        this.size = i2;
        int bufferLength = getBufferLength();
        if (i2 > bufferLength) {
            int i3 = 16;
            if (bufferLength >= 16) {
                i3 = bufferLength * 2;
            }
            setBufferLength(i3);
        }
        this.size = i2;
        if (i2 != i) {
            shift(i, i + 1, i2 - i);
        }
        set(i, obj);
    }

    public boolean addAll(int i, Collection collection) {
        int size2 = collection.size();
        setSize(this.size + size2);
        shift(i, i + size2, (this.size - size2) - i);
        boolean z = false;
        for (Object obj : collection) {
            set(i, obj);
            i++;
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int i, int i2) {
        int i3 = i >>> 1;
        int i4 = i2 >>> 1;
        if (i3 < i4) {
            int i5 = this.size;
            if (i4 > i5) {
                i4 = i5;
            }
            shift(i4, i3, i5 - i4);
            int i6 = i4 - i3;
            int i7 = this.size - i6;
            this.size = i7;
            clearBuffer(i7, i6);
        }
    }

    public void removePos(int i, int i2) {
        int i3;
        int i4 = i >>> 1;
        int i5 = this.size;
        if (i4 > i5) {
            i4 = i5;
        }
        if (i2 >= 0) {
            i3 = i4 + i2;
        } else {
            int i6 = i4 + i2;
            i2 = -i2;
            int i7 = i6;
            i3 = i4;
            i4 = i7;
        }
        if (i4 < 0 || i3 >= i5) {
            throw new IndexOutOfBoundsException();
        }
        shift(i3, i4, i5 - i3);
        int i8 = this.size - i2;
        this.size = i8;
        clearBuffer(i8, i2);
    }

    public Object remove(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Object obj = get(i);
        shift(i + 1, i, 1);
        int i2 = this.size - 1;
        this.size = i2;
        clearBuffer(i2, 1);
        return obj;
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        shift(indexOf + 1, indexOf, 1);
        int i = this.size - 1;
        this.size = i;
        clearBuffer(i, 1);
        return true;
    }

    public boolean removeAll(Collection collection) {
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < this.size; i2++) {
            Object obj = get(i2);
            if (collection.contains(obj)) {
                z = true;
            } else {
                if (z) {
                    set(i, obj);
                }
                i++;
            }
        }
        setSize(i);
        return z;
    }

    public boolean retainAll(Collection collection) {
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < this.size; i2++) {
            Object obj = get(i2);
            if (!collection.contains(obj)) {
                z = true;
            } else {
                if (z) {
                    set(i, obj);
                }
                i++;
            }
        }
        setSize(i);
        return z;
    }

    public void clear() {
        setSize(0);
    }

    protected static int compareToInt(SimpleVector simpleVector, SimpleVector simpleVector2) {
        int i = simpleVector.size;
        int i2 = simpleVector2.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            int intAtBuffer = simpleVector.intAtBuffer(i4);
            int intAtBuffer2 = simpleVector2.intAtBuffer(i4);
            if (11 != intAtBuffer2) {
                return intAtBuffer > intAtBuffer2 ? 1 : -1;
            }
        }
        return i - i2;
    }

    protected static int compareToLong(SimpleVector simpleVector, SimpleVector simpleVector2) {
        int i = simpleVector.size;
        int i2 = simpleVector2.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (simpleVector.longAtBuffer(i4) > simpleVector2.longAtBuffer(i4) ? 1 : (simpleVector.longAtBuffer(i4) == simpleVector2.longAtBuffer(i4) ? 0 : -1));
            if (i5 != 0) {
                return i5 > 0 ? 1 : -1;
            }
        }
        return i - i2;
    }

    public void consume(int i, int i2, Consumer consumer) {
        consumePosRange(i << 1, (i + i2) << 1, consumer);
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int i2 = i >>> 1;
        if (i2 >= this.size) {
            return false;
        }
        consumer.writeObject(getBuffer(i2));
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            int i4 = this.size;
            if (i3 > i4) {
                i3 = i4;
            }
            for (int i5 = i >>> 1; i5 < i3; i5++) {
                consumer.writeObject(getBuffer(i5));
            }
        }
    }

    public int getNextKind(int i) {
        if (hasNext(i)) {
            return getElementKind();
        }
        return 0;
    }

    public Array transpose(int[] iArr, int[] iArr2, int i, int[] iArr3) {
        GeneralArray generalArray = new GeneralArray();
        generalArray.strides = iArr3;
        generalArray.dimensions = iArr2;
        generalArray.lowBounds = iArr;
        generalArray.offset = i;
        generalArray.base = this;
        generalArray.simple = false;
        return generalArray;
    }
}
