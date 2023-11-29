package gnu.kawa.servlet;

import gnu.kawa.xml.HttpPrinter;
import java.io.IOException;
import java.io.OutputStream;

public class ServletPrinter extends HttpPrinter {
    HttpRequestContext hctx;

    public void printHeaders() {
    }

    public ServletPrinter(HttpRequestContext httpRequestContext, int i) throws IOException {
        super((OutputStream) new HttpOutputStream(httpRequestContext, i));
        this.hctx = httpRequestContext;
    }

    public void addHeader(String str, String str2) {
        if (str.equalsIgnoreCase("Content-type")) {
            this.sawContentType = str2;
            this.hctx.setContentType(str2);
        } else if (str.equalsIgnoreCase("Status")) {
            int length = str2.length();
            int i = 0;
            int i2 = 0;
            while (i < length) {
                if (i >= length) {
                    this.hctx.statusCode = i2;
                    return;
                }
                char charAt = str2.charAt(i);
                int digit = Character.digit(charAt, 10);
                if (digit >= 0) {
                    i2 = (i2 * 10) + digit;
                    i++;
                } else {
                    if (charAt == ' ') {
                        i++;
                    }
                    this.hctx.statusCode = i2;
                    this.hctx.statusReasonPhrase = str2.substring(i);
                    return;
                }
            }
        } else {
            this.hctx.setResponseHeader(str, str2);
        }
    }

    public boolean reset(boolean z) {
        return super.reset(z) & ((HttpOutputStream) this.ostream).reset();
    }
}
