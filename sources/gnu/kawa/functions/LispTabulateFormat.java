package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispTabulateFormat extends ReportFormat {
    int colinc;
    int colnum;
    int padChar;
    boolean relative;

    public LispTabulateFormat(int i, int i2, int i3, boolean z) {
        this.colnum = i;
        this.colinc = i2;
        this.relative = z;
        this.padChar = i3;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.colnum, 1, objArr, i);
        if (this.colnum == -1610612736) {
            i++;
        }
        int param2 = getParam(this.colinc, 1, objArr, i);
        if (this.colinc == -1610612736) {
            i++;
        }
        char param3 = getParam(this.padChar, ' ', objArr, i);
        if (this.padChar == -1610612736) {
            i++;
        }
        int columnNumber = writer instanceof OutPort ? ((OutPort) writer).getColumnNumber() : -1;
        if (columnNumber >= 0) {
            if (!this.relative) {
                param = columnNumber < param ? param - columnNumber : param2 <= 0 ? 0 : param2 - ((columnNumber - param) % param2);
            } else {
                param = (param + param2) - ((columnNumber + param) % param2);
            }
        } else if (!this.relative) {
            param = 2;
        }
        while (true) {
            param--;
            if (param < 0) {
                return i;
            }
            writer.write(param3);
        }
    }
}
