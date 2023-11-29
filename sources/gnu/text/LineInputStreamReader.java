package gnu.text;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class LineInputStreamReader extends LineBufferedReader {
    byte[] barr;
    ByteBuffer bbuf;
    char[] carr;
    CharBuffer cbuf = null;
    Charset cset;
    CharsetDecoder decoder;
    InputStream istrm;

    public void markStart() throws IOException {
    }

    public void setCharset(Charset charset) {
        this.cset = charset;
        this.decoder = charset.newDecoder();
    }

    public void setCharset(String str) {
        Charset forName = Charset.forName(str);
        Charset charset = this.cset;
        if (charset == null) {
            setCharset(forName);
        } else if (!forName.equals(charset)) {
            throw new RuntimeException("encoding " + str + " does not match previous " + this.cset);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LineInputStreamReader(InputStream inputStream) {
        super((Reader) null);
        Reader reader = null;
        byte[] bArr = new byte[8192];
        this.barr = bArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.bbuf = wrap;
        wrap.position(this.barr.length);
        this.istrm = inputStream;
    }

    public void close() throws IOException {
        if (this.in != null) {
            this.in.close();
        }
        this.istrm.close();
    }

    private int fillBytes(int i) throws IOException {
        InputStream inputStream = this.istrm;
        byte[] bArr = this.barr;
        int read = inputStream.read(bArr, i, bArr.length - i);
        int i2 = 0;
        this.bbuf.position(0);
        ByteBuffer byteBuffer = this.bbuf;
        if (read >= 0) {
            i2 = read;
        }
        byteBuffer.limit(i + i2);
        return read;
    }

    public void resetStart(int i) throws IOException {
        this.bbuf.position(i);
    }

    public int getByte() throws IOException {
        if (this.bbuf.hasRemaining() || fillBytes(0) > 0) {
            return this.bbuf.get() & Ev3Constants.Opcode.TST;
        }
        return -1;
    }

    public int fill(int i) throws IOException {
        int position;
        if (this.cset == null) {
            setCharset("UTF-8");
        }
        if (this.buffer != this.carr) {
            this.cbuf = CharBuffer.wrap(this.buffer);
            this.carr = this.buffer;
        }
        this.cbuf.limit(this.pos + i);
        this.cbuf.position(this.pos);
        boolean z = false;
        while (true) {
            CoderResult decode = this.decoder.decode(this.bbuf, this.cbuf, false);
            position = this.cbuf.position() - this.pos;
            if (position > 0 || !decode.isUnderflow()) {
                break;
            }
            int remaining = this.bbuf.remaining();
            if (remaining > 0) {
                this.bbuf.compact();
            }
            if (fillBytes(remaining) < 0) {
                z = true;
                break;
            }
        }
        if (position != 0 || !z) {
            return position;
        }
        return -1;
    }

    public boolean ready() throws IOException {
        return this.pos < this.limit || this.bbuf.hasRemaining() || this.istrm.available() > 0;
    }
}
