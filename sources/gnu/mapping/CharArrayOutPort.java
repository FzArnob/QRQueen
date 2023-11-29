package gnu.mapping;

import gnu.lists.Consumer;
import java.io.Writer;

public class CharArrayOutPort extends OutPort {
    public void close() {
    }

    /* access modifiers changed from: protected */
    public boolean closeOnExit() {
        return false;
    }

    public CharArrayOutPort() {
        super((Writer) null, false, CharArrayInPort.stringPath);
    }

    public int length() {
        return this.bout.bufferFillPointer;
    }

    public void setLength(int i) {
        this.bout.bufferFillPointer = i;
    }

    public void reset() {
        this.bout.bufferFillPointer = 0;
    }

    public char[] toCharArray() {
        int i = this.bout.bufferFillPointer;
        char[] cArr = new char[i];
        System.arraycopy(this.bout.buffer, 0, cArr, 0, i);
        return cArr;
    }

    public String toString() {
        return new String(this.bout.buffer, 0, this.bout.bufferFillPointer);
    }

    public String toSubString(int i, int i2) {
        if (i2 <= this.bout.bufferFillPointer) {
            return new String(this.bout.buffer, i, i2 - i);
        }
        throw new IndexOutOfBoundsException();
    }

    public String toSubString(int i) {
        return new String(this.bout.buffer, i, this.bout.bufferFillPointer - i);
    }

    public void writeTo(Consumer consumer) {
        consumer.write(this.bout.buffer, 0, this.bout.bufferFillPointer);
    }

    public void writeTo(int i, int i2, Consumer consumer) {
        consumer.write(this.bout.buffer, i, i2);
    }
}
