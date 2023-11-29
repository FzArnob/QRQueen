package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class ByteVector extends SimpleVector implements Externalizable, Comparable {
    protected static byte[] empty = new byte[0];
    byte[] data;

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        byte[] bArr = this.data;
        int length = bArr.length;
        if (length != i) {
            byte[] bArr2 = new byte[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(bArr, 0, bArr2, 0, i);
            this.data = bArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final byte byteAt(int i) {
        if (i <= this.size) {
            return this.data[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public final byte byteAtBuffer(int i) {
        return this.data[i];
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int i2 = i >>> 1;
        if (i2 >= this.size) {
            return false;
        }
        consumer.writeInt(intAtBuffer(i2));
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i2 >>> 1;
            if (i3 > this.size) {
                i3 = this.size;
            }
            for (int i4 = i >>> 1; i4 < i3; i4++) {
                consumer.writeInt(intAtBuffer(i4));
            }
        }
    }

    public final void setByteAt(int i, byte b) {
        if (i <= this.size) {
            this.data[i] = b;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setByteAtBuffer(int i, byte b) {
        this.data[i] = b;
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

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeByte(this.data[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        byte[] bArr = new byte[readInt];
        for (int i = 0; i < readInt; i++) {
            bArr[i] = objectInput.readByte();
        }
        this.data = bArr;
        this.size = readInt;
    }
}
