package gnu.lists;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class PrintConsumer extends PrintWriter implements Appendable, Consumer {
    public void endAttribute() {
    }

    public void endDocument() {
    }

    public void endElement() {
    }

    /* access modifiers changed from: protected */
    public void endNumber() {
    }

    public boolean ignoring() {
        return false;
    }

    public void startAttribute(Object obj) {
    }

    public void startDocument() {
    }

    public void startElement(Object obj) {
    }

    /* access modifiers changed from: protected */
    public void startNumber() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PrintConsumer(Consumer consumer, boolean z) {
        super(consumer instanceof Writer ? (Writer) consumer : new ConsumerWriter(consumer), z);
    }

    public PrintConsumer(OutputStream outputStream, boolean z) {
        super(outputStream, z);
    }

    public PrintConsumer(Writer writer, boolean z) {
        super(writer, z);
    }

    public PrintConsumer(Writer writer) {
        super(writer);
    }

    public PrintConsumer append(char c) {
        print(c);
        return this;
    }

    public PrintConsumer append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        append(charSequence, 0, charSequence.length());
        return this;
    }

    public PrintConsumer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        while (i < i2) {
            append(charSequence.charAt(i));
            i++;
        }
        return this;
    }

    public void write(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof String) {
            write((String) charSequence, i, i2);
            return;
        }
        while (i < i2) {
            write((int) charSequence.charAt(i));
            i++;
        }
    }

    public void writeBoolean(boolean z) {
        print(z);
    }

    public void writeFloat(float f) {
        startNumber();
        print(f);
        endNumber();
    }

    public void writeDouble(double d) {
        startNumber();
        print(d);
        endNumber();
    }

    public void writeInt(int i) {
        startNumber();
        print(i);
        endNumber();
    }

    public void writeLong(long j) {
        startNumber();
        print(j);
        endNumber();
    }

    public void writeObject(Object obj) {
        print(obj);
    }
}
