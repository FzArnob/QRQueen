package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LineBufferedReader extends Reader {
    public static final int BUFFER_SIZE = 8192;
    private static final int CONVERT_CR = 1;
    private static final int DONT_KEEP_FULL_LINES = 8;
    private static final int PREV_WAS_CR = 4;
    private static final int USER_BUFFER = 2;
    public char[] buffer;
    private int flags;
    int highestPos;
    protected Reader in;
    public int limit;
    protected int lineNumber;
    private int lineStartPos;
    protected int markPos;
    Path path;
    public int pos;
    protected int readAheadLimit = 0;
    public char readState = 10;

    public void lineStart(boolean z) throws IOException {
    }

    public boolean markSupported() {
        return true;
    }

    public void close() throws IOException {
        this.in.close();
    }

    public char getReadState() {
        return this.readState;
    }

    public void setKeepFullLines(boolean z) {
        if (z) {
            this.flags &= -9;
        } else {
            this.flags |= 8;
        }
    }

    public final boolean getConvertCR() {
        return (this.flags & 1) != 0;
    }

    public final void setConvertCR(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public LineBufferedReader(InputStream inputStream) {
        this.in = new InputStreamReader(inputStream);
    }

    public LineBufferedReader(Reader reader) {
        this.in = reader;
    }

    public int fill(int i) throws IOException {
        return this.in.read(this.buffer, this.pos, i);
    }

    private void clearMark() {
        int i = 0;
        this.readAheadLimit = 0;
        int i2 = this.lineStartPos;
        if (i2 >= 0) {
            i = i2;
        }
        while (true) {
            i++;
            if (i < this.pos) {
                char c = this.buffer[i - 1];
                if (c == 10 || (c == 13 && (!getConvertCR() || this.buffer[i] != 10))) {
                    this.lineNumber++;
                    this.lineStartPos = i;
                }
            } else {
                return;
            }
        }
    }

    public void setBuffer(char[] cArr) throws IOException {
        if (cArr == null) {
            char[] cArr2 = this.buffer;
            if (cArr2 != null) {
                char[] cArr3 = new char[cArr2.length];
                System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                this.buffer = cArr3;
            }
            this.flags &= -3;
        } else if (this.limit - this.pos <= cArr.length) {
            this.flags |= 2;
            reserve(cArr, 0);
        } else {
            throw new IOException("setBuffer - too short");
        }
    }

    private void reserve(char[] cArr, int i) throws IOException {
        int i2;
        int i3;
        int i4 = i + this.limit;
        if (i4 <= cArr.length) {
            i2 = 0;
        } else {
            i2 = this.pos;
            int i5 = this.readAheadLimit;
            if (i5 > 0 && (i3 = this.markPos) < i2) {
                if (i2 - i3 > i5 || ((this.flags & 2) != 0 && i4 - i3 > cArr.length)) {
                    clearMark();
                } else {
                    i2 = i3;
                }
            }
            int length = i4 - cArr.length;
            if (length > i2 || (i2 > this.lineStartPos && (this.flags & 8) == 0)) {
                int i6 = this.lineStartPos;
                if (length <= i6 && i2 > i6) {
                    i2 = i6;
                } else if ((this.flags & 2) != 0) {
                    i2 -= (i2 - length) >> 2;
                } else {
                    if (i6 >= 0) {
                        i2 = i6;
                    }
                    cArr = new char[(cArr.length * 2)];
                }
            }
            this.lineStartPos -= i2;
            this.limit -= i2;
            this.markPos -= i2;
            this.pos -= i2;
            this.highestPos -= i2;
        }
        int i7 = this.limit;
        if (i7 > 0) {
            System.arraycopy(this.buffer, i2, cArr, 0, i7);
        }
        this.buffer = cArr;
    }

    public int read() throws IOException {
        char c;
        int i = this.pos;
        boolean z = false;
        if (i > 0) {
            c = this.buffer[i - 1];
        } else if ((this.flags & 4) != 0) {
            c = 13;
        } else {
            c = this.lineStartPos >= 0 ? (char) 10 : 0;
        }
        if (c == 13 || c == 10) {
            if (this.lineStartPos < i && (this.readAheadLimit == 0 || i <= this.markPos)) {
                this.lineStartPos = i;
                this.lineNumber++;
            }
            if (i < this.highestPos) {
                z = true;
            }
            if (c != 10 || (i > 1 ? this.buffer[i - 2] != 13 : (this.flags & 4) == 0)) {
                lineStart(z);
            }
            if (!z) {
                this.highestPos = this.pos + 1;
            }
        }
        int i2 = this.pos;
        int i3 = this.limit;
        if (i2 >= i3) {
            char[] cArr = this.buffer;
            if (cArr == null) {
                this.buffer = new char[8192];
            } else if (i3 == cArr.length) {
                reserve(cArr, 1);
            }
            int i4 = this.pos;
            if (i4 == 0) {
                if (c == 13) {
                    this.flags |= 4;
                } else {
                    this.flags &= -5;
                }
            }
            int fill = fill(this.buffer.length - i4);
            if (fill <= 0) {
                return -1;
            }
            this.limit += fill;
        }
        char[] cArr2 = this.buffer;
        int i5 = this.pos;
        int i6 = i5 + 1;
        this.pos = i6;
        char c2 = cArr2[i5];
        if (c2 == 10) {
            if (c == 13) {
                int i7 = this.lineStartPos;
                if (i7 == i6 - 1) {
                    this.lineNumber--;
                    this.lineStartPos = i7 - 1;
                }
                if (getConvertCR()) {
                    return read();
                }
            }
        } else if (c2 != 13 || !getConvertCR()) {
            return c2;
        } else {
            return 10;
        }
        return c2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(char[] r9, int r10, int r11) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r8.pos
            int r1 = r8.limit
            r2 = 0
            r3 = 10
            if (r0 < r1) goto L_0x000a
            goto L_0x001f
        L_0x000a:
            if (r0 <= 0) goto L_0x0013
            char[] r1 = r8.buffer
            int r0 = r0 + -1
            char r2 = r1[r0]
            goto L_0x001f
        L_0x0013:
            int r0 = r8.flags
            r0 = r0 & 4
            if (r0 != 0) goto L_0x001d
            int r0 = r8.lineStartPos
            if (r0 < 0) goto L_0x001f
        L_0x001d:
            r2 = 10
        L_0x001f:
            r0 = r11
        L_0x0020:
            if (r0 <= 0) goto L_0x006b
            int r1 = r8.pos
            int r4 = r8.limit
            if (r1 >= r4) goto L_0x0051
            if (r2 == r3) goto L_0x0051
            r5 = 13
            if (r2 != r5) goto L_0x002f
            goto L_0x0051
        L_0x002f:
            int r6 = r4 - r1
            if (r0 >= r6) goto L_0x0035
            int r4 = r1 + r0
        L_0x0035:
            if (r1 >= r4) goto L_0x0049
            char[] r2 = r8.buffer
            char r2 = r2[r1]
            if (r2 == r3) goto L_0x0049
            if (r2 != r5) goto L_0x0040
            goto L_0x0049
        L_0x0040:
            int r6 = r10 + 1
            char r7 = (char) r2
            r9[r10] = r7
            int r1 = r1 + 1
            r10 = r6
            goto L_0x0035
        L_0x0049:
            int r4 = r8.pos
            int r4 = r1 - r4
            int r0 = r0 - r4
            r8.pos = r1
            goto L_0x0020
        L_0x0051:
            if (r1 < r4) goto L_0x0057
            if (r0 >= r11) goto L_0x0057
            int r11 = r11 - r0
            return r11
        L_0x0057:
            int r2 = r8.read()
            if (r2 >= 0) goto L_0x0062
            int r11 = r11 - r0
            if (r11 > 0) goto L_0x0061
            r11 = -1
        L_0x0061:
            return r11
        L_0x0062:
            int r1 = r10 + 1
            char r4 = (char) r2
            r9[r10] = r4
            int r0 = r0 + -1
            r10 = r1
            goto L_0x0020
        L_0x006b:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.read(char[], int, int):int");
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path2) {
        this.path = path2;
    }

    public String getName() {
        Path path2 = this.path;
        if (path2 == null) {
            return null;
        }
        return path2.toString();
    }

    public void setName(Object obj) {
        setPath(Path.valueOf(obj));
    }

    public int getLineNumber() {
        int i = this.lineNumber;
        if (this.readAheadLimit == 0) {
            int i2 = this.pos;
            if (i2 <= 0 || i2 <= this.lineStartPos) {
                return i;
            }
            char c = this.buffer[i2 - 1];
            if (c == 10 || c == 13) {
                return i + 1;
            }
            return i;
        }
        char[] cArr = this.buffer;
        int i3 = this.lineStartPos;
        if (i3 < 0) {
            i3 = 0;
        }
        return i + countLines(cArr, i3, this.pos);
    }

    public void setLineNumber(int i) {
        this.lineNumber += i - getLineNumber();
    }

    public void incrLineNumber(int i, int i2) {
        this.lineNumber += i;
        this.lineStartPos = i2;
    }

    public int getColumnNumber() {
        int i;
        char c;
        int i2 = this.pos;
        int i3 = 0;
        if (i2 > 0 && ((c = this.buffer[i2 - 1]) == 10 || c == 13)) {
            return 0;
        }
        if (this.readAheadLimit <= 0) {
            return i2 - this.lineStartPos;
        }
        int i4 = this.lineStartPos;
        if (i4 >= 0) {
            i3 = i4;
        }
        int i5 = i3;
        while (true) {
            i = this.pos;
            if (i3 >= i) {
                break;
            }
            int i6 = i3 + 1;
            char c2 = this.buffer[i3];
            if (c2 == 10 || c2 == 13) {
                i5 = i6;
            }
            i3 = i6;
        }
        int i7 = i - i5;
        int i8 = this.lineStartPos;
        return i8 < 0 ? i7 - i8 : i7;
    }

    public synchronized void mark(int i) {
        if (this.readAheadLimit > 0) {
            clearMark();
        }
        this.readAheadLimit = i;
        this.markPos = this.pos;
    }

    public void reset() throws IOException {
        if (this.readAheadLimit > 0) {
            int i = this.pos;
            if (i > this.highestPos) {
                this.highestPos = i;
            }
            this.pos = this.markPos;
            this.readAheadLimit = 0;
            return;
        }
        throw new IOException("mark invalid");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
        r7.append(r6.buffer, r0, r1 - r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readLine(java.lang.StringBuffer r7, char r8) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r6.read()
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            int r0 = r6.pos
            int r0 = r0 + -1
            r6.pos = r0
        L_0x000d:
            int r1 = r6.pos
            int r2 = r6.limit
            if (r1 >= r2) goto L_0x005c
            char[] r2 = r6.buffer
            int r3 = r1 + 1
            r6.pos = r3
            char r1 = r2[r1]
            r4 = 13
            r5 = 10
            if (r1 == r4) goto L_0x0023
            if (r1 != r5) goto L_0x000d
        L_0x0023:
            int r3 = r3 + -1
            int r3 = r3 - r0
            r7.append(r2, r0, r3)
            r0 = 80
            if (r8 != r0) goto L_0x0034
            int r7 = r6.pos
            int r7 = r7 + -1
            r6.pos = r7
            return
        L_0x0034:
            boolean r0 = r6.getConvertCR()
            r2 = 73
            if (r0 != 0) goto L_0x0056
            if (r1 != r5) goto L_0x003f
            goto L_0x0056
        L_0x003f:
            if (r8 == r2) goto L_0x0044
            r7.append(r4)
        L_0x0044:
            int r0 = r6.read()
            if (r0 != r5) goto L_0x0050
            if (r8 == r2) goto L_0x005b
            r7.append(r5)
            goto L_0x005b
        L_0x0050:
            if (r0 < 0) goto L_0x005b
            r6.unread_quick()
            goto L_0x005b
        L_0x0056:
            if (r8 == r2) goto L_0x005b
            r7.append(r5)
        L_0x005b:
            return
        L_0x005c:
            char[] r2 = r6.buffer
            int r1 = r1 - r0
            r7.append(r2, r0, r1)
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.readLine(java.lang.StringBuffer, char):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r5 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r3 == 10) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (getConvertCR() != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r1 = r6.pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r1 < r6.limit) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r6.pos = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        if (r6.buffer[r1] != 10) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        r6.pos = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        return new java.lang.String(r6.buffer, r0, r5 - r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String readLine() throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r6.read()
            if (r0 >= 0) goto L_0x0008
            r0 = 0
            return r0
        L_0x0008:
            r1 = 13
            if (r0 == r1) goto L_0x0068
            r2 = 10
            if (r0 != r2) goto L_0x0011
            goto L_0x0068
        L_0x0011:
            int r0 = r6.pos
            int r0 = r0 + -1
        L_0x0015:
            int r3 = r6.pos
            int r4 = r6.limit
            if (r3 >= r4) goto L_0x004f
            char[] r4 = r6.buffer
            int r5 = r3 + 1
            r6.pos = r5
            char r3 = r4[r3]
            if (r3 == r1) goto L_0x0027
            if (r3 != r2) goto L_0x0015
        L_0x0027:
            int r5 = r5 + -1
            if (r3 == r2) goto L_0x0046
            boolean r1 = r6.getConvertCR()
            if (r1 != 0) goto L_0x0046
            int r1 = r6.pos
            int r3 = r6.limit
            if (r1 < r3) goto L_0x003c
            int r1 = r1 + -1
            r6.pos = r1
            goto L_0x004f
        L_0x003c:
            char[] r3 = r6.buffer
            char r3 = r3[r1]
            if (r3 != r2) goto L_0x0046
            int r1 = r1 + 1
            r6.pos = r1
        L_0x0046:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.buffer
            int r5 = r5 - r0
            r1.<init>(r2, r0, r5)
            return r1
        L_0x004f:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 100
            r1.<init>(r2)
            char[] r2 = r6.buffer
            int r3 = r6.pos
            int r3 = r3 - r0
            r1.append(r2, r0, r3)
            r0 = 73
            r6.readLine(r1, r0)
            java.lang.String r0 = r1.toString()
            return r0
        L_0x0068:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.readLine():java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int skip(int r8) throws java.io.IOException {
        /*
            r7 = this;
            if (r8 >= 0) goto L_0x0011
            int r0 = -r8
        L_0x0003:
            if (r0 <= 0) goto L_0x000f
            int r1 = r7.pos
            if (r1 <= 0) goto L_0x000f
            r7.unread()
            int r0 = r0 + -1
            goto L_0x0003
        L_0x000f:
            int r8 = r8 + r0
            return r8
        L_0x0011:
            int r0 = r7.pos
            int r1 = r7.limit
            r2 = 0
            r3 = 10
            if (r0 < r1) goto L_0x001c
        L_0x001a:
            r0 = r8
            goto L_0x0032
        L_0x001c:
            if (r0 <= 0) goto L_0x0025
            char[] r1 = r7.buffer
            int r0 = r0 + -1
            char r2 = r1[r0]
            goto L_0x001a
        L_0x0025:
            int r0 = r7.flags
            r0 = r0 & 4
            if (r0 != 0) goto L_0x002f
            int r0 = r7.lineStartPos
            if (r0 < 0) goto L_0x001a
        L_0x002f:
            r0 = r8
            r2 = 10
        L_0x0032:
            if (r0 <= 0) goto L_0x0068
            if (r2 == r3) goto L_0x005d
            r1 = 13
            if (r2 == r1) goto L_0x005d
            int r4 = r7.pos
            int r5 = r7.limit
            if (r4 < r5) goto L_0x0041
            goto L_0x005d
        L_0x0041:
            int r6 = r5 - r4
            if (r0 >= r6) goto L_0x0047
            int r5 = r4 + r0
        L_0x0047:
            if (r4 >= r5) goto L_0x0055
            char[] r2 = r7.buffer
            char r2 = r2[r4]
            if (r2 == r3) goto L_0x0055
            if (r2 != r1) goto L_0x0052
            goto L_0x0055
        L_0x0052:
            int r4 = r4 + 1
            goto L_0x0047
        L_0x0055:
            int r1 = r7.pos
            int r1 = r4 - r1
            int r0 = r0 - r1
            r7.pos = r4
            goto L_0x0032
        L_0x005d:
            int r2 = r7.read()
            if (r2 >= 0) goto L_0x0065
            int r8 = r8 - r0
            return r8
        L_0x0065:
            int r0 = r0 + -1
            goto L_0x0032
        L_0x0068:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.skip(int):int");
    }

    public boolean ready() throws IOException {
        return this.pos < this.limit || this.in.ready();
    }

    public final void skip_quick() throws IOException {
        this.pos++;
    }

    public void skip() throws IOException {
        read();
    }

    static int countLines(char[] cArr, int i, int i2) {
        int i3 = 0;
        char c = 0;
        while (i < i2) {
            char c2 = cArr[i];
            if ((c2 == 10 && c != 13) || c2 == 13) {
                i3++;
            }
            i++;
            c = c2;
        }
        return i3;
    }

    public void skipRestOfLine() throws IOException {
        int read;
        do {
            read = read();
            if (read >= 0) {
                if (read == 13) {
                    int read2 = read();
                    if (read2 >= 0 && read2 != 10) {
                        unread();
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        } while (read != 10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unread() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            if (r0 == 0) goto L_0x0049
            int r0 = r0 + -1
            r5.pos = r0
            char[] r1 = r5.buffer
            char r1 = r1[r0]
            r2 = 13
            r3 = 10
            if (r1 == r3) goto L_0x0014
            if (r1 != r2) goto L_0x0048
        L_0x0014:
            if (r0 <= 0) goto L_0x002c
            if (r1 != r3) goto L_0x002c
            boolean r0 = r5.getConvertCR()
            if (r0 == 0) goto L_0x002c
            char[] r0 = r5.buffer
            int r1 = r5.pos
            int r4 = r1 + -1
            char r0 = r0[r4]
            if (r0 != r2) goto L_0x002c
            int r1 = r1 + -1
            r5.pos = r1
        L_0x002c:
            int r0 = r5.pos
            int r1 = r5.lineStartPos
            if (r0 >= r1) goto L_0x0048
            int r1 = r5.lineNumber
            int r1 = r1 + -1
            r5.lineNumber = r1
        L_0x0038:
            if (r0 <= 0) goto L_0x0046
            char[] r1 = r5.buffer
            int r0 = r0 + -1
            char r1 = r1[r0]
            if (r1 == r2) goto L_0x0044
            if (r1 != r3) goto L_0x0038
        L_0x0044:
            int r0 = r0 + 1
        L_0x0046:
            r5.lineStartPos = r0
        L_0x0048:
            return
        L_0x0049:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "unread too much"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.unread():void");
    }

    public void unread_quick() {
        this.pos--;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r1 = r5.buffer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int peek() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r0 >= r1) goto L_0x0023
            if (r0 <= 0) goto L_0x0023
            char[] r1 = r5.buffer
            int r2 = r0 + -1
            char r2 = r1[r2]
            r3 = 10
            if (r2 == r3) goto L_0x0023
            r4 = 13
            if (r2 == r4) goto L_0x0023
            char r0 = r1[r0]
            if (r0 != r4) goto L_0x0021
            boolean r1 = r5.getConvertCR()
            if (r1 == 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r3 = r0
        L_0x0022:
            return r3
        L_0x0023:
            int r0 = r5.read()
            if (r0 < 0) goto L_0x002c
            r5.unread_quick()
        L_0x002c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.peek():int");
    }
}
