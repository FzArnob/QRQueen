package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispNewlineFormat extends ReportFormat {
    static final String line_separator = System.getProperty("line.separator", "\n");
    int count;
    int kind;

    LispNewlineFormat() {
    }

    public static LispNewlineFormat getInstance(int i, int i2) {
        LispNewlineFormat lispNewlineFormat = new LispNewlineFormat();
        lispNewlineFormat.count = i;
        lispNewlineFormat.kind = i2;
        return lispNewlineFormat;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.count, 1, objArr, i);
        if (this.count == -1610612736) {
            i++;
        }
        while (true) {
            param--;
            if (param < 0) {
                return i;
            }
            printNewline(this.kind, writer);
        }
    }

    public static void printNewline(int i, Writer writer) throws IOException {
        if ((writer instanceof OutPort) && i != 76) {
            ((OutPort) writer).writeBreak(i);
        } else if (writer instanceof PrintWriter) {
            ((PrintWriter) writer).println();
        } else {
            writer.write(line_separator);
        }
    }
}
