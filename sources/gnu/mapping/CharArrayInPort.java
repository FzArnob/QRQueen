package gnu.mapping;

import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.text.NullReader;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

public class CharArrayInPort extends InPort {
    static final Path stringPath = Path.valueOf("<string>");

    public CharArrayInPort make(CharSequence charSequence) {
        if (charSequence instanceof FString) {
            FString fString = (FString) charSequence;
            return new CharArrayInPort(fString.data, fString.size);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        if (charSequence instanceof String) {
            ((String) charSequence).getChars(0, length, cArr, 0);
        } else if (!(charSequence instanceof CharSeq)) {
            int i = length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                cArr[i] = charSequence.charAt(i);
            }
        } else {
            ((CharSeq) charSequence).getChars(0, length, cArr, 0);
        }
        return new CharArrayInPort(cArr, length);
    }

    public CharArrayInPort(char[] cArr, int i) {
        super((Reader) NullReader.nullReader, stringPath);
        try {
            setBuffer(cArr);
            this.limit = i;
        } catch (IOException e) {
            throw new Error(e.toString());
        }
    }

    public CharArrayInPort(char[] cArr) {
        this(cArr, cArr.length);
    }

    public CharArrayInPort(String str) {
        this(str.toCharArray());
    }

    public int read() throws IOException {
        if (this.pos >= this.limit) {
            return -1;
        }
        return super.read();
    }
}
