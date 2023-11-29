package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F32Vector extends SimpleVector implements Externalizable, Comparable {
    protected static float[] empty = new float[0];
    float[] data;

    public int getElementKind() {
        return 25;
    }

    public String getTag() {
        return "f32";
    }

    public F32Vector() {
        this.data = empty;
    }

    public F32Vector(int i, float f) {
        float[] fArr = new float[i];
        this.data = fArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                fArr[i] = f;
            } else {
                return;
            }
        }
    }

    public F32Vector(int i) {
        this.data = new float[i];
        this.size = i;
    }

    public F32Vector(float[] fArr) {
        this.data = fArr;
        this.size = fArr.length;
    }

    public F32Vector(Sequence sequence) {
        this.data = new float[sequence.size()];
        addAll(sequence);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        float[] fArr = this.data;
        int length = fArr.length;
        if (length != i) {
            float[] fArr2 = new float[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.data = fArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final int intAtBuffer(int i) {
        return (int) this.data[i];
    }

    public final float floatAt(int i) {
        if (i < this.size) {
            return this.data[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final float floatAtBuffer(int i) {
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

    public final void setFloatAt(int i, float f) {
        if (i <= this.size) {
            this.data[i] = f;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setFloatAtBuffer(int i, float f) {
        this.data[i] = f;
    }

    public final Object setBuffer(int i, Object obj) {
        Object object = Convert.toObject(this.data[i]);
        this.data[i] = Convert.toFloat(obj);
        return object;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        while (true) {
            i2--;
            if (i2 >= 0) {
                this.data[i] = 0.0f;
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
        consumer.writeFloat(this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeFloat(this.data[i4]);
            }
        }
    }

    public int compareTo(Object obj) {
        F32Vector f32Vector = (F32Vector) obj;
        float[] fArr = this.data;
        float[] fArr2 = f32Vector.data;
        int i = this.size;
        int i2 = f32Vector.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (fArr[i4] > fArr2[i4] ? 1 : (fArr[i4] == fArr2[i4] ? 0 : -1));
            if (i5 != 0) {
                return i5 > 0 ? 1 : -1;
            }
        }
        return i - i2;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeFloat(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        float[] fArr = new float[readInt];
        for (int i = 0; i < readInt; i++) {
            fArr[i] = objectInput.readFloat();
        }
        this.data = fArr;
        this.size = readInt;
    }
}
