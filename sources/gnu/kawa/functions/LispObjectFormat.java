package gnu.kawa.functions;

import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispObjectFormat extends ReportFormat {
    ReportFormat base;
    int colInc;
    int minPad;
    int minWidth;
    int padChar;
    int where;

    public LispObjectFormat(ReportFormat reportFormat, int i, int i2, int i3, int i4, int i5) {
        this.base = reportFormat;
        this.minWidth = i;
        this.colInc = i2;
        this.minPad = i3;
        this.padChar = i4;
        this.where = i5;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.minWidth, 0, objArr, i);
        int i2 = this.minWidth == -1610612736 ? i + 1 : i;
        int param2 = getParam(this.colInc, 1, objArr, i2);
        if (this.colInc == -1610612736) {
            i2++;
        }
        int param3 = getParam(this.minPad, 0, objArr, i2);
        if (this.minPad == -1610612736) {
            i2++;
        }
        char param4 = getParam(this.padChar, ' ', objArr, i2);
        if (this.padChar == -1610612736) {
            i2++;
        }
        return PadFormat.format(this.base, objArr, i2, writer, param4, param, param2, param3, this.where, fieldPosition);
    }
}
