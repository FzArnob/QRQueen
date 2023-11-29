package kawa;

import java.io.Writer;
import javax.swing.text.AttributeSet;

/* compiled from: ReplPaneOutPort */
class TextPaneWriter extends Writer {
    ReplDocument document;
    String str = "";
    AttributeSet style;

    public TextPaneWriter(ReplDocument replDocument, AttributeSet attributeSet) {
        this.document = replDocument;
        this.style = attributeSet;
    }

    public synchronized void write(int i) {
        this.str += ((char) i);
        if (i == 10) {
            flush();
        }
    }

    public void write(String str2) {
        this.document.write(str2, this.style);
    }

    public synchronized void write(char[] cArr, int i, int i2) {
        flush();
        if (i2 != 0) {
            write(new String(cArr, i, i2));
        }
    }

    public synchronized void flush() {
        String str2 = this.str;
        if (!str2.equals("")) {
            this.str = "";
            write(str2);
        }
    }

    public void close() {
        flush();
    }
}
