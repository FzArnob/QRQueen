package gnu.text;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class Lexer extends Reader {
    protected boolean interactive;
    SourceMessages messages;
    protected int nesting;
    protected LineBufferedReader port;
    private int saveTokenBufferLength;
    public char[] tokenBuffer;
    public int tokenBufferLength;

    public Lexer(LineBufferedReader lineBufferedReader) {
        this.messages = null;
        this.tokenBuffer = new char[100];
        this.tokenBufferLength = 0;
        this.saveTokenBufferLength = -1;
        this.port = lineBufferedReader;
    }

    public Lexer(LineBufferedReader lineBufferedReader, SourceMessages sourceMessages) {
        this.tokenBuffer = new char[100];
        this.tokenBufferLength = 0;
        this.saveTokenBufferLength = -1;
        this.port = lineBufferedReader;
        this.messages = sourceMessages;
    }

    public char pushNesting(char c) {
        this.nesting++;
        LineBufferedReader port2 = getPort();
        char c2 = port2.readState;
        port2.readState = c;
        return c2;
    }

    public void popNesting(char c) {
        getPort().readState = c;
        this.nesting--;
    }

    public final LineBufferedReader getPort() {
        return this.port;
    }

    public void close() throws IOException {
        this.port.close();
    }

    public int read() throws IOException {
        return this.port.read();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r2 = r5.port.read();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readUnicodeChar() throws java.io.IOException {
        /*
            r5 = this;
            gnu.text.LineBufferedReader r0 = r5.port
            int r0 = r0.read()
            r1 = 55296(0xd800, float:7.7486E-41)
            if (r0 < r1) goto L_0x0029
            r2 = 56319(0xdbff, float:7.892E-41)
            if (r0 >= r2) goto L_0x0029
            gnu.text.LineBufferedReader r2 = r5.port
            int r2 = r2.read()
            r3 = 56320(0xdc00, float:7.8921E-41)
            if (r2 < r3) goto L_0x0029
            r4 = 57343(0xdfff, float:8.0355E-41)
            if (r2 > r4) goto L_0x0029
            int r1 = r0 - r1
            int r1 = r1 << 10
            int r0 = r0 - r3
            int r1 = r1 + r0
            r0 = 65536(0x10000, float:9.18355E-41)
            int r0 = r0 + r1
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Lexer.readUnicodeChar():int");
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        return this.port.read(cArr, i, i2);
    }

    public void unread(int i) throws IOException {
        if (i >= 0) {
            this.port.unread();
        }
    }

    public int peek() throws IOException {
        return this.port.peek();
    }

    public void skip() throws IOException {
        this.port.skip();
    }

    /* access modifiers changed from: protected */
    public void unread() throws IOException {
        this.port.unread();
    }

    /* access modifiers changed from: protected */
    public void unread_quick() throws IOException {
        this.port.unread_quick();
    }

    public boolean checkNext(char c) throws IOException {
        int read = this.port.read();
        if (read == c) {
            return true;
        }
        if (read < 0) {
            return false;
        }
        this.port.unread_quick();
        return false;
    }

    /* access modifiers changed from: protected */
    public void skip_quick() throws IOException {
        this.port.skip_quick();
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages sourceMessages) {
        this.messages = sourceMessages;
    }

    public boolean checkErrors(PrintWriter printWriter, int i) {
        SourceMessages sourceMessages = this.messages;
        return sourceMessages != null && sourceMessages.checkErrors(printWriter, i);
    }

    public SourceError getErrors() {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages == null) {
            return null;
        }
        return sourceMessages.getErrors();
    }

    public boolean seenErrors() {
        SourceMessages sourceMessages = this.messages;
        return sourceMessages != null && sourceMessages.seenErrors();
    }

    public void clearErrors() {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages != null) {
            sourceMessages.clearErrors();
        }
    }

    public void error(char c, String str, int i, int i2, String str2) {
        if (this.messages == null) {
            this.messages = new SourceMessages();
        }
        this.messages.error(c, str, i, i2, str2);
    }

    public void error(char c, String str) {
        int lineNumber = this.port.getLineNumber();
        int columnNumber = this.port.getColumnNumber();
        error(c, this.port.getName(), lineNumber + 1, columnNumber >= 0 ? columnNumber + 1 : 0, str);
    }

    public void error(String str) {
        error('e', str);
    }

    public void fatal(String str) throws SyntaxException {
        error('f', str);
        throw new SyntaxException(this.messages);
    }

    public void eofError(String str) throws SyntaxException {
        fatal(str);
    }

    public void eofError(String str, int i, int i2) throws SyntaxException {
        error('f', this.port.getName(), i, i2, str);
        throw new SyntaxException(this.messages);
    }

    public int readOptionalExponent() throws IOException {
        int i;
        int digit;
        int read = read();
        boolean z = false;
        if (read == 43 || read == 45) {
            i = read;
            read = read();
        } else {
            i = 0;
        }
        int i2 = 1;
        if (read >= 0 && (digit = Character.digit((char) read, 10)) >= 0) {
            while (true) {
                read = read();
                int digit2 = Character.digit((char) read, 10);
                if (digit2 < 0) {
                    break;
                }
                if (digit > 214748363) {
                    z = true;
                }
                digit = (digit * 10) + digit2;
            }
            i2 = digit;
        } else if (i != 0) {
            error("exponent sign not followed by digit");
        }
        if (read >= 0) {
            unread(read);
        }
        if (i == 45) {
            i2 = -i2;
        }
        if (!z) {
            return i2;
        }
        if (i == 45) {
            return Integer.MIN_VALUE;
        }
        return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    public boolean readDelimited(String str) throws IOException, SyntaxException {
        int i;
        this.tokenBufferLength = 0;
        int length = str.length() - 1;
        char charAt = str.charAt(length);
        while (true) {
            int read = read();
            if (read < 0) {
                return false;
            }
            if (read != charAt || (i = this.tokenBufferLength - length) < 0) {
                tokenBufferAppend((char) read);
            } else {
                int i2 = length;
                while (i2 != 0) {
                    i2--;
                    if (this.tokenBuffer[i + i2] != str.charAt(i2)) {
                        tokenBufferAppend((char) read);
                    }
                }
                this.tokenBufferLength = i;
                return true;
            }
        }
    }

    public static long readDigitsInBuffer(LineBufferedReader lineBufferedReader, int i) {
        long j = (long) i;
        long j2 = Long.MAX_VALUE / j;
        int i2 = lineBufferedReader.pos;
        if (i2 >= lineBufferedReader.limit) {
            return 0;
        }
        boolean z = false;
        long j3 = 0;
        do {
            int digit = Character.digit(lineBufferedReader.buffer[i2], i);
            if (digit < 0) {
                break;
            }
            if (j3 > j2) {
                z = true;
            } else {
                j3 = (j3 * j) + ((long) digit);
            }
            if (j3 < 0) {
                z = true;
            }
            i2++;
        } while (i2 < lineBufferedReader.limit);
        lineBufferedReader.pos = i2;
        if (z) {
            return -1;
        }
        return j3;
    }

    public String getName() {
        return this.port.getName();
    }

    public int getLineNumber() {
        return this.port.getLineNumber();
    }

    public int getColumnNumber() {
        return this.port.getColumnNumber();
    }

    public boolean isInteractive() {
        return this.interactive;
    }

    public void setInteractive(boolean z) {
        this.interactive = z;
    }

    public void tokenBufferAppend(int i) {
        if (i >= 65536) {
            tokenBufferAppend(((i - 65536) >> 10) + 55296);
            i = (i & 1023) + 56320;
        }
        int i2 = this.tokenBufferLength;
        char[] cArr = this.tokenBuffer;
        if (i2 == cArr.length) {
            char[] cArr2 = new char[(i2 * 2)];
            this.tokenBuffer = cArr2;
            System.arraycopy(cArr, 0, cArr2, 0, i2);
            cArr = this.tokenBuffer;
        }
        cArr[i2] = (char) i;
        this.tokenBufferLength = i2 + 1;
    }

    public String tokenBufferString() {
        return new String(this.tokenBuffer, 0, this.tokenBufferLength);
    }

    public void mark() throws IOException {
        if (this.saveTokenBufferLength < 0) {
            this.port.mark(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.saveTokenBufferLength = this.tokenBufferLength;
            return;
        }
        throw new Error("internal error: recursive call to mark not allowed");
    }

    public void reset() throws IOException {
        if (this.saveTokenBufferLength >= 0) {
            this.port.reset();
            this.saveTokenBufferLength = -1;
            return;
        }
        throw new Error("internal error: reset called without prior mark");
    }
}
