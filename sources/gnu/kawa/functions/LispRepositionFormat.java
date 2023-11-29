package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispRepositionFormat extends ReportFormat {
    boolean absolute;
    boolean backwards;
    int count;

    public LispRepositionFormat(int i, boolean z, boolean z2) {
        this.count = i;
        this.backwards = z;
        this.absolute = z2;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.count, this.absolute ^ true ? 1 : 0, objArr, i);
        if (!this.absolute) {
            if (this.backwards) {
                param = -param;
            }
            param += i;
        }
        if (param < 0) {
            return 0;
        }
        return param > objArr.length ? objArr.length : param;
    }
}
