package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Writer;

public class FString extends SimpleVector implements Comparable, Appendable, CharSeq, Externalizable, Consumable {
    protected static char[] empty = new char[0];
    public char[] data;

    public int getElementKind() {
        return 29;
    }

    public FString() {
        this.data = empty;
    }

    public FString(int i) {
        this.size = i;
        this.data = new char[i];
    }

    public FString(int i, char c) {
        char[] cArr = new char[i];
        this.data = cArr;
        this.size = i;
        while (true) {
            i--;
            if (i >= 0) {
                cArr[i] = c;
            } else {
                return;
            }
        }
    }

    public FString(char[] cArr) {
        this.size = cArr.length;
        this.data = cArr;
    }

    public FString(String str) {
        char[] charArray = str.toCharArray();
        this.data = charArray;
        this.size = charArray.length;
    }

    public FString(StringBuffer stringBuffer) {
        this(stringBuffer, 0, stringBuffer.length());
    }

    public FString(StringBuffer stringBuffer, int i, int i2) {
        this.size = i2;
        char[] cArr = new char[i2];
        this.data = cArr;
        if (i2 > 0) {
            stringBuffer.getChars(i, i2 + i, cArr, 0);
        }
    }

    public FString(char[] cArr, int i, int i2) {
        this.size = i2;
        char[] cArr2 = new char[i2];
        this.data = cArr2;
        System.arraycopy(cArr, i, cArr2, 0, i2);
    }

    public FString(Sequence sequence) {
        this.data = new char[sequence.size()];
        addAll(sequence);
    }

    public FString(CharSeq charSeq) {
        this(charSeq, 0, charSeq.size());
    }

    public FString(CharSeq charSeq, int i, int i2) {
        char[] cArr = new char[i2];
        charSeq.getChars(i, i + i2, cArr, 0);
        this.data = cArr;
        this.size = i2;
    }

    public FString(CharSequence charSequence) {
        this(charSequence, 0, charSequence.length());
    }

    public FString(CharSequence charSequence, int i, int i2) {
        char[] cArr = new char[i2];
        int i3 = i2;
        while (true) {
            i3--;
            if (i3 >= 0) {
                cArr[i3] = charSequence.charAt(i + i3);
            } else {
                this.data = cArr;
                this.size = i2;
                return;
            }
        }
    }

    public int length() {
        return this.size;
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        char[] cArr = this.data;
        int length = cArr.length;
        if (length != i) {
            char[] cArr2 = new char[i];
            if (length < i) {
                i = length;
            }
            System.arraycopy(cArr, 0, cArr2, 0, i);
            this.data = cArr2;
        }
    }

    public void ensureBufferLength(int i) {
        char[] cArr = this.data;
        if (i > cArr.length) {
            char[] cArr2 = new char[(i < 60 ? 120 : i * 2)];
            System.arraycopy(cArr, 0, cArr2, 0, i);
            this.data = cArr2;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final Object getBuffer(int i) {
        return Convert.toObject(this.data[i]);
    }

    public final Object setBuffer(int i, Object obj) {
        Object object = Convert.toObject(this.data[i]);
        this.data[i] = Convert.toChar(obj);
        return object;
    }

    public final Object get(int i) {
        if (i < this.size) {
            return Convert.toObject(this.data[i]);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final char charAt(int i) {
        if (i < this.size) {
            return this.data[i];
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public final char charAtBuffer(int i) {
        return this.data[i];
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (i < 0 || i > i2) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 > this.size) {
            throw new StringIndexOutOfBoundsException(i2);
        } else if ((i3 + i2) - i > cArr.length) {
            throw new StringIndexOutOfBoundsException(i3);
        } else if (i < i2) {
            System.arraycopy(this.data, i, cArr, i3, i2 - i);
        }
    }

    public void getChars(int i, int i2, StringBuffer stringBuffer) {
        if (i < 0 || i > i2) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 > this.size) {
            throw new StringIndexOutOfBoundsException(i2);
        } else if (i < i2) {
            stringBuffer.append(this.data, i, i2 - i);
        }
    }

    public void getChars(StringBuffer stringBuffer) {
        stringBuffer.append(this.data, 0, this.size);
    }

    public char[] toCharArray() {
        int length = this.data.length;
        int i = this.size;
        if (i == length) {
            return this.data;
        }
        char[] cArr = new char[i];
        System.arraycopy(this.data, 0, cArr, 0, i);
        return cArr;
    }

    public void shift(int i, int i2, int i3) {
        char[] cArr = this.data;
        System.arraycopy(cArr, i, cArr, i2, i3);
    }

    public FString copy(int i, int i2) {
        char[] cArr = new char[(i2 - i)];
        char[] cArr2 = this.data;
        for (int i3 = i; i3 < i2; i3++) {
            cArr[i3 - i] = cArr2[i3];
        }
        return new FString(cArr);
    }

    public boolean addAll(FString fString) {
        int i = this.size + fString.size;
        if (this.data.length < i) {
            setBufferLength(i);
        }
        System.arraycopy(fString.data, 0, this.data, this.size, fString.size);
        this.size = i;
        if (fString.size > 0) {
            return true;
        }
        return false;
    }

    public boolean addAll(CharSequence charSequence) {
        int length = charSequence.length();
        int i = this.size + length;
        if (this.data.length < i) {
            setBufferLength(i);
        }
        if (!(charSequence instanceof FString)) {
            if (!(charSequence instanceof String)) {
                if (!(charSequence instanceof CharSeq)) {
                    int i2 = length;
                    while (true) {
                        i2--;
                        if (i2 < 0) {
                            break;
                        }
                        this.data[this.size + i2] = charSequence.charAt(i2);
                    }
                } else {
                    ((CharSeq) charSequence).getChars(0, length, this.data, this.size);
                }
            } else {
                ((String) charSequence).getChars(0, length, this.data, this.size);
            }
        } else {
            System.arraycopy(((FString) charSequence).data, 0, this.data, this.size, length);
        }
        this.size = i;
        if (length > 0) {
            return true;
        }
        return false;
    }

    public void addAllStrings(Object[] objArr, int i) {
        int i2 = this.size;
        for (int i3 = i; i3 < objArr.length; i3++) {
            i2 += objArr[i3].length();
        }
        if (this.data.length < i2) {
            setBufferLength(i2);
        }
        while (i < objArr.length) {
            addAll(objArr[i]);
            i++;
        }
    }

    public String toString() {
        return new String(this.data, 0, this.size);
    }

    public String substring(int i, int i2) {
        return new String(this.data, i, i2 - i);
    }

    public CharSequence subSequence(int i, int i2) {
        return new FString(this.data, i, i2 - i);
    }

    public void setCharAt(int i, char c) {
        if (i < 0 || i >= this.size) {
            throw new StringIndexOutOfBoundsException(i);
        }
        this.data[i] = c;
    }

    public void setCharAtBuffer(int i, char c) {
        this.data[i] = c;
    }

    public final void fill(char c) {
        char[] cArr = this.data;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                cArr[i] = c;
            } else {
                return;
            }
        }
    }

    public void fill(int i, int i2, char c) {
        if (i < 0 || i2 > this.size) {
            throw new IndexOutOfBoundsException();
        }
        char[] cArr = this.data;
        while (i < i2) {
            cArr[i] = c;
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        char[] cArr = this.data;
        while (true) {
            i2--;
            if (i2 >= 0) {
                cArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    public void replace(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(cArr, i2, this.data, i, i3);
    }

    public void replace(int i, String str) {
        str.getChars(0, str.length(), this.data, i);
    }

    public int hashCode() {
        char[] cArr = this.data;
        int i = this.size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + cArr[i3];
        }
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof FString)) {
            char[] cArr = ((FString) obj).data;
            int i = this.size;
            if (cArr != null && cArr.length == i) {
                char[] cArr2 = this.data;
                do {
                    i--;
                    if (i < 0) {
                        return true;
                    }
                } while (cArr2[i] == cArr[i]);
                return false;
            }
        }
        return false;
    }

    public int compareTo(Object obj) {
        FString fString = (FString) obj;
        char[] cArr = this.data;
        char[] cArr2 = fString.data;
        int i = this.size;
        int i2 = fString.size;
        int i3 = i > i2 ? i2 : i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = cArr[i4] - cArr2[i4];
            if (i5 != 0) {
                return i5;
            }
        }
        return i - i2;
    }

    public void consume(Consumer consumer) {
        char[] cArr = this.data;
        consumer.write(cArr, 0, cArr.length);
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int i2 = i >>> 1;
        if (i2 >= this.size) {
            return false;
        }
        consumer.write((int) this.data[i2]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i >>> 1;
            int i4 = i2 >>> 1;
            if (i4 > this.size) {
                i4 = this.size;
            }
            if (i4 > i3) {
                consumer.write(this.data, i3, i4 - i3);
            }
        }
    }

    public FString append(char c) {
        int i = this.size;
        if (i >= this.data.length) {
            ensureBufferLength(i + 1);
        }
        this.data[i] = c;
        this.size = i + 1;
        return this;
    }

    public FString append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        return append(charSequence, 0, charSequence.length());
    }

    public FString append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        int i3 = this.size;
        int i4 = (i2 - i) + i3;
        if (i4 > this.data.length) {
            ensureBufferLength(i4);
        }
        char[] cArr = this.data;
        if (charSequence instanceof String) {
            ((String) charSequence).getChars(i, i2, cArr, i3);
        } else if (charSequence instanceof CharSeq) {
            ((CharSeq) charSequence).getChars(i, i2, cArr, i3);
        } else {
            int i5 = i3;
            while (i < i2) {
                cArr[i5] = charSequence.charAt(i);
                i++;
                i5++;
            }
        }
        this.size = i3;
        return this;
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        if (appendable instanceof Writer) {
            try {
                ((Writer) appendable).write(this.data, i, i2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            appendable.append(this, i, i2 + i);
        }
    }

    public void writeTo(Appendable appendable) throws IOException {
        writeTo(0, this.size, appendable);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.size;
        objectOutput.writeInt(i);
        char[] cArr = this.data;
        for (int i2 = 0; i2 < i; i2++) {
            objectOutput.writeChar(cArr[i2]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        char[] cArr = new char[readInt];
        for (int i = 0; i < readInt; i++) {
            cArr[i] = objectInput.readChar();
        }
        this.data = cArr;
        this.size = readInt;
    }
}
