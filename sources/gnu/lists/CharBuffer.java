package gnu.lists;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;

public class CharBuffer extends StableVector implements CharSeq, Serializable {
    private FString string;

    public CharBuffer(FString fString) {
        super(fString);
        this.string = fString;
    }

    public CharBuffer(int i) {
        this(new FString(i));
    }

    protected CharBuffer() {
    }

    public int length() {
        return size();
    }

    public char charAt(int i) {
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        return this.string.charAt(i);
    }

    public int indexOf(int i, int i2) {
        char c;
        char c2;
        if (i >= 65536) {
            c2 = (char) (((i - 65536) >> 10) + 55296);
            c = (char) ((i & 1023) + 56320);
        } else {
            c2 = (char) i;
            c = 0;
        }
        char[] array = getArray();
        int i3 = this.gapStart;
        if (i2 >= i3) {
            i2 += this.gapEnd - this.gapStart;
            i3 = array.length;
        }
        while (true) {
            if (i2 == i3) {
                i3 = array.length;
                if (i2 >= i3) {
                    return -1;
                }
                i2 = this.gapEnd;
            }
            if (array[i2] == c2) {
                if (c != 0) {
                    int i4 = i2 + 1;
                    if (i4 >= i3) {
                        if (this.gapEnd < array.length && array[this.gapEnd] == c) {
                            break;
                        }
                    } else if (array[i4] == c) {
                        break;
                    }
                } else {
                    break;
                }
            }
            i2++;
        }
        return i2 > this.gapStart ? i2 - (this.gapEnd - this.gapStart) : i2;
    }

    public int lastIndexOf(int i, int i2) {
        char c;
        int i3;
        if (i >= 65536) {
            c = (char) (((i - 65536) >> 10) + 55296);
            i = (i & 1023) + 56320;
        } else {
            c = 0;
        }
        char c2 = (char) i;
        while (true) {
            i3 = -1;
            i2--;
            if (i2 < 0) {
                break;
            } else if (charAt(i2) == c2) {
                if (c == 0) {
                    return i2;
                }
                if (i2 > 0) {
                    i3 = i2 - 1;
                    if (charAt(i3) == c) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return i3;
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        char[] cArr2 = this.string.data;
        if (i < this.gapStart) {
            int i4 = (i2 < this.gapStart ? i2 : this.gapStart) - i;
            if (i4 > 0) {
                System.arraycopy(cArr2, i, cArr, i3, i4);
                i += i4;
                i3 += i4;
            }
        }
        int i5 = this.gapEnd - this.gapStart;
        int i6 = i + i5;
        int i7 = (i2 + i5) - i6;
        if (i7 > 0) {
            System.arraycopy(cArr2, i6, cArr, i3, i7);
        }
    }

    public void setCharAt(int i, char c) {
        if (i >= this.gapStart) {
            i += this.gapEnd - this.gapStart;
        }
        this.string.setCharAt(i, c);
    }

    public String substring(int i, int i2) {
        int size = size();
        if (i < 0 || i2 < i || i2 > size) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        return new String(getArray(), getSegment(i, i3), i3);
    }

    public CharSequence subSequence(int i, int i2) {
        int size = size();
        if (i >= 0 && i2 >= i && i2 <= size) {
            return new SubCharSeq(this, this.base.createPos(i, false), this.base.createPos(i2, true));
        }
        throw new IndexOutOfBoundsException();
    }

    public void fill(int i, int i2, char c) {
        char[] cArr = this.string.data;
        int i3 = this.gapStart < i2 ? this.gapStart : i2;
        while (i < i3) {
            cArr[i] = c;
            i++;
        }
        int i4 = i3 + i2;
        for (int i5 = (this.gapEnd - this.gapStart) + i3; i5 < i4; i5++) {
            cArr[i5] = c;
        }
    }

    public final void fill(char c) {
        char[] cArr = this.string.data;
        int length = cArr.length;
        while (true) {
            length--;
            if (length < this.gapEnd) {
                break;
            }
            cArr[length] = c;
        }
        int i = this.gapStart;
        while (true) {
            i--;
            if (i >= 0) {
                cArr[i] = c;
            } else {
                return;
            }
        }
    }

    public char[] getArray() {
        return (char[]) this.base.getBuffer();
    }

    public void delete(int i, int i2) {
        int createPos = createPos(i, false);
        removePos(createPos, i2);
        releasePos(createPos);
    }

    public void insert(int i, String str, boolean z) {
        int length = str.length();
        gapReserve(i, length);
        str.getChars(0, length, this.string.data, i);
        this.gapStart += length;
    }

    public void consume(int i, int i2, Consumer consumer) {
        char[] cArr = this.string.data;
        if (i < this.gapStart) {
            int i3 = this.gapStart - i;
            if (i3 > i2) {
                i3 = i2;
            }
            consumer.write(cArr, i, i3);
            i2 -= i3;
            i += i2;
        }
        if (i2 > 0) {
            consumer.write(cArr, i + (this.gapEnd - this.gapStart), i2);
        }
    }

    public String toString() {
        int size = size();
        return new String(getArray(), getSegment(0, size), size);
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        if (appendable instanceof Writer) {
            writeTo(i, i2, (Writer) appendable);
        } else {
            appendable.append(this, i, i2 + i);
        }
    }

    public void writeTo(Appendable appendable) throws IOException {
        writeTo(0, size(), appendable);
    }

    public void writeTo(int i, int i2, Writer writer) throws IOException {
        char[] cArr = this.string.data;
        if (i < this.gapStart) {
            int i3 = this.gapStart - i;
            if (i3 > i2) {
                i3 = i2;
            }
            writer.write(cArr, i, i3);
            i2 -= i3;
            i += i2;
        }
        if (i2 > 0) {
            writer.write(cArr, i + (this.gapEnd - this.gapStart), i2);
        }
    }

    public void writeTo(Writer writer) throws IOException {
        char[] cArr = this.string.data;
        writer.write(cArr, 0, this.gapStart);
        writer.write(cArr, this.gapEnd, cArr.length - this.gapEnd);
    }

    public void dump() {
        PrintStream printStream = System.err;
        printStream.println("Buffer Content dump.  size:" + size() + "  buffer:" + getArray().length);
        System.err.print("before gap: \"");
        System.err.print(new String(getArray(), 0, this.gapStart));
        PrintStream printStream2 = System.err;
        printStream2.println("\" (gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + ')');
        System.err.print("after gap: \"");
        System.err.print(new String(getArray(), this.gapEnd, getArray().length - this.gapEnd));
        System.err.println("\"");
        int length = this.positions == null ? 0 : this.positions.length;
        PrintStream printStream3 = System.err;
        printStream3.println("Positions (size: " + length + " free:" + this.free + "):");
        boolean[] zArr = null;
        if (this.free != -2) {
            zArr = new boolean[this.positions.length];
            int i = this.free;
            while (i >= 0) {
                zArr[i] = true;
                i = this.positions[i];
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = this.positions[i2];
            if (this.free == -2) {
                if (i3 == -2) {
                }
            } else if (zArr[i2]) {
            }
            PrintStream printStream4 = System.err;
            printStream4.println("position#" + i2 + ": " + (i3 >> 1) + " isAfter:" + (i3 & 1));
        }
    }
}
