package gnu.kawa.servlet;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ServletPrinter */
class HttpOutputStream extends OutputStream {
    byte[] buffer;
    HttpRequestContext context;
    int count;
    OutputStream out;

    public HttpOutputStream(HttpRequestContext httpRequestContext, int i) {
        this.context = httpRequestContext;
        this.buffer = new byte[i];
    }

    public void write(int i) throws IOException {
        if (this.count >= this.buffer.length) {
            flush();
        }
        byte[] bArr = this.buffer;
        int i2 = this.count;
        this.count = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        int length = this.buffer.length - this.count;
        while (i2 > length) {
            System.arraycopy(bArr, i, this.buffer, this.count, length);
            this.count += length;
            flush();
            i += length;
            i2 -= length;
            length = this.buffer.length;
        }
        if (i2 > 0) {
            System.arraycopy(bArr, i, this.buffer, this.count, i2);
            this.count += i2;
        }
    }

    public boolean reset() {
        this.count = 0;
        if (this.out == null) {
            return true;
        }
        return false;
    }

    public void flush() throws IOException {
        if (this.out == null) {
            maybeSendResponseHeaders(-1);
            this.out = this.context.getResponseStream();
        }
        int i = this.count;
        if (i > 0) {
            this.out.write(this.buffer, 0, i);
            this.count = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeSendResponseHeaders(int i) throws IOException {
        int i2 = this.context.statusCode;
        if (i2 != -999) {
            HttpRequestContext httpRequestContext = this.context;
            httpRequestContext.sendResponseHeaders(i2, httpRequestContext.statusReasonPhrase, (long) i);
            this.context.statusCode = -999;
        }
    }

    public void close() throws IOException {
        if (this.out == null) {
            maybeSendResponseHeaders(this.count);
            this.out = this.context.getResponseStream();
        }
        flush();
        this.out.close();
    }
}
