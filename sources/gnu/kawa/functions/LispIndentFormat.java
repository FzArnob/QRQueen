package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispIndentFormat extends ReportFormat {
    int columns;
    boolean current;

    LispIndentFormat() {
    }

    public static LispIndentFormat getInstance(int i, boolean z) {
        LispIndentFormat lispIndentFormat = new LispIndentFormat();
        lispIndentFormat.columns = i;
        lispIndentFormat.current = z;
        return lispIndentFormat;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.columns, 0, objArr, i);
        if (this.columns == -1610612736) {
            i++;
        }
        if (writer instanceof OutPort) {
            ((OutPort) writer).setIndentation(param, this.current);
        }
        return i;
    }
}
