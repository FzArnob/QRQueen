package gnu.lists;

import java.io.IOException;
import java.util.List;

public class SubCharSeq extends SubSequence implements CharSeq {
    public SubCharSeq(AbstractSequence abstractSequence, int i, int i2) {
        super(abstractSequence, i, i2);
    }

    public int length() {
        return size();
    }

    public char charAt(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return ((CharSeq) this.base).charAt(this.base.nextIndex(this.ipos0) + i);
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        while (i < i2) {
            cArr[i3] = charAt(i);
            i++;
            i3++;
        }
    }

    public void setCharAt(int i, char c) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        ((CharSeq) this.base).setCharAt(this.base.nextIndex(this.ipos0) + i, c);
    }

    public void fill(char c) {
        ((CharSeq) this.base).fill(this.base.nextIndex(this.ipos0), this.base.nextIndex(this.ipos0), c);
    }

    public void fill(int i, int i2, char c) {
        int i3;
        int nextIndex = this.base.nextIndex(this.ipos0);
        int nextIndex2 = this.base.nextIndex(this.ipos0);
        if (i < 0 || i2 < i || (i3 = i2 + nextIndex) > nextIndex2) {
            throw new IndexOutOfBoundsException();
        }
        ((CharSeq) this.base).fill(nextIndex + i, i3, c);
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        int nextIndex = this.base.nextIndex(this.ipos0);
        int nextIndex2 = this.base.nextIndex(this.ipos0);
        if (i >= 0 && i2 >= 0) {
            int i3 = nextIndex + i;
            if (i3 + i2 <= nextIndex2) {
                ((CharSeq) this.base).writeTo(i3, i2, appendable);
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void writeTo(Appendable appendable) throws IOException {
        ((CharSeq) this.base).writeTo(this.base.nextIndex(this.ipos0), size(), appendable);
    }

    public void consume(int i, int i2, Consumer consumer) {
        int nextIndex = this.base.nextIndex(this.ipos0);
        int nextIndex2 = this.base.nextIndex(this.ipos0);
        if (i >= 0 && i2 >= 0) {
            int i3 = nextIndex + i;
            if (i3 + i2 <= nextIndex2) {
                ((CharSeq) this.base).consume(i3, i2, consumer);
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public String toString() {
        int size = size();
        StringBuffer stringBuffer = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            stringBuffer.append(charAt(i));
        }
        return stringBuffer.toString();
    }

    private SubCharSeq subCharSeq(int i, int i2) {
        int size = size();
        if (i >= 0 && i2 >= i && i2 <= size) {
            return new SubCharSeq(this.base, this.base.createRelativePos(this.ipos0, i, false), this.base.createRelativePos(this.ipos0, i2, true));
        }
        throw new IndexOutOfBoundsException();
    }

    public List subList(int i, int i2) {
        return subCharSeq(i, i2);
    }

    public CharSequence subSequence(int i, int i2) {
        return subCharSeq(i, i2);
    }
}
