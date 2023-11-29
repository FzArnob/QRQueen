package gnu.text;

import java.io.Reader;

public class NullReader extends Reader {
    public static final NullReader nullReader = new NullReader();

    public void close() {
    }

    public int read(char[] cArr, int i, int i2) {
        return -1;
    }

    public boolean ready() {
        return true;
    }
}
