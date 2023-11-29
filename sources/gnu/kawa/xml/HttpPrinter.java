package gnu.kawa.xml;

import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.UnescapedData;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.xml.XMLPrinter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class HttpPrinter extends FilterConsumer {
    Object currentHeader;
    private int elementNesting;
    Vector headers = new Vector();
    protected OutputStream ostream;
    protected String sawContentType;
    StringBuilder sbuf = new StringBuilder(100);
    private int seenStartDocument;
    boolean seenXmlHeader;
    OutPort writer;

    public HttpPrinter(OutputStream outputStream) {
        super((Consumer) null);
        this.ostream = outputStream;
    }

    public HttpPrinter(OutPort outPort) {
        super((Consumer) null);
        this.writer = outPort;
    }

    public static HttpPrinter make(OutPort outPort) {
        return new HttpPrinter(outPort);
    }

    private void writeRaw(String str) throws IOException {
        OutPort outPort = this.writer;
        if (outPort != null) {
            outPort.write(str);
            return;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            this.ostream.write((byte) str.charAt(i));
        }
    }

    /* access modifiers changed from: protected */
    public void beforeNode() {
        if (this.sawContentType == null) {
            addHeader("Content-type", NanoHTTPD.MIME_XML);
        }
        beginData();
    }

    public void printHeader(String str, String str2) throws IOException {
        writeRaw(str);
        writeRaw(": ");
        writeRaw(str2);
        writeRaw("\n");
    }

    public void printHeaders() throws IOException {
        int size = this.headers.size();
        for (int i = 0; i < size; i += 2) {
            printHeader(this.headers.elementAt(i).toString(), this.headers.elementAt(i + 1).toString());
        }
        writeRaw("\n");
    }

    public void addHeader(String str, String str2) {
        if (str.equalsIgnoreCase("Content-type")) {
            this.sawContentType = str2;
        }
        this.headers.addElement(str);
        this.headers.addElement(str2);
    }

    public void startAttribute(Object obj) {
        if (this.base == null) {
            this.currentHeader = obj;
        } else {
            this.base.startAttribute(obj);
        }
    }

    public void endAttribute() {
        Object obj = this.currentHeader;
        if (obj != null) {
            addHeader(obj.toString(), this.sbuf.toString());
            this.sbuf.setLength(0);
            this.currentHeader = null;
            return;
        }
        this.base.endAttribute();
    }

    public void beginData() {
        if (this.base == null) {
            if (this.sawContentType == null) {
                addHeader("Content-type", "text/plain");
            }
            if (this.writer == null) {
                this.writer = new OutPort(this.ostream);
            }
            String str = null;
            if (NanoHTTPD.MIME_HTML.equalsIgnoreCase(this.sawContentType)) {
                str = "html";
            } else if ("application/xhtml+xml".equalsIgnoreCase(this.sawContentType)) {
                str = "xhtml";
            } else if ("text/plain".equalsIgnoreCase(this.sawContentType)) {
                str = "plain";
            }
            this.base = XMLPrinter.make(this.writer, str);
            if (this.seenStartDocument == 0) {
                this.base.startDocument();
                this.seenStartDocument = 1;
            }
            try {
                printHeaders();
            } catch (Throwable th) {
                throw new RuntimeException(th.toString());
            }
        }
        append((CharSequence) this.sbuf);
        this.sbuf.setLength(0);
    }

    public void startElement(Object obj) {
        String str;
        if (this.sawContentType == null) {
            if (!this.seenXmlHeader) {
                str = NanoHTTPD.MIME_HTML;
            } else {
                str = (!(obj instanceof Symbol) || !"html".equals(((Symbol) obj).getLocalPart())) ? NanoHTTPD.MIME_XML : "text/xhtml";
            }
            addHeader("Content-type", str);
        }
        beginData();
        this.base.startElement(obj);
        this.elementNesting++;
    }

    public void endElement() {
        super.endElement();
        int i = this.elementNesting - 1;
        this.elementNesting = i;
        if (i == 0 && this.seenStartDocument == 1) {
            endDocument();
        }
    }

    public void writeObject(Object obj) {
        if (!(obj instanceof Consumable) || (obj instanceof UnescapedData)) {
            beginData();
            super.writeObject(obj);
            return;
        }
        ((Consumable) obj).consume(this);
    }

    public void write(CharSequence charSequence, int i, int i2) {
        if (this.base == null) {
            this.sbuf.append(charSequence, i, i2 + i);
        } else {
            this.base.write(charSequence, i, i2);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        if (this.base == null) {
            this.sbuf.append(cArr, i, i2);
        } else {
            this.base.write(cArr, i, i2);
        }
    }

    public void startDocument() {
        if (this.base != null) {
            this.base.startDocument();
        }
        this.seenStartDocument = 2;
    }

    public void endDocument() {
        if (this.base != null) {
            this.base.endDocument();
        }
        try {
            if (this.sawContentType == null) {
                addHeader("Content-type", "text/plain");
            }
            if (this.sbuf.length() > 0) {
                String sb = this.sbuf.toString();
                this.sbuf.setLength(0);
                OutPort outPort = this.writer;
                if (outPort != null) {
                    outPort.write(sb);
                } else {
                    this.ostream.write(sb.getBytes());
                }
            }
            OutPort outPort2 = this.writer;
            if (outPort2 != null) {
                outPort2.close();
            }
            OutputStream outputStream = this.ostream;
            if (outputStream != null) {
                outputStream.flush();
            }
        } catch (Throwable unused) {
        }
    }

    public boolean reset(boolean z) {
        boolean z2 = false;
        if (z) {
            this.headers.clear();
            this.sawContentType = null;
            this.currentHeader = null;
            this.elementNesting = 0;
        }
        this.sbuf.setLength(0);
        this.base = null;
        if (this.ostream == null) {
            return true;
        }
        if (this.writer == null) {
            z2 = true;
        }
        this.writer = null;
        return z2;
    }
}
