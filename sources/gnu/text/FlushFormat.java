package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

public class FlushFormat extends ReportFormat {
    private static FlushFormat flush;

    public static FlushFormat getInstance() {
        if (flush == null) {
            flush = new FlushFormat();
        }
        return flush;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        writer.flush();
        return i;
    }
}
