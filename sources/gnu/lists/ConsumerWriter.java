package gnu.lists;

import java.io.Writer;

public class ConsumerWriter extends Writer {
    protected Consumer out;

    public void flush() {
    }

    public ConsumerWriter(Consumer consumer) {
        this.out = consumer;
    }

    public void write(char[] cArr, int i, int i2) {
        this.out.write(cArr, i, i2);
    }

    public void close() {
        flush();
    }

    public void finalize() {
        close();
    }
}
