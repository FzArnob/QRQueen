package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispFreshlineFormat extends ReportFormat {
    int count;

    public LispFreshlineFormat(int i) {
        this.count = i;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.count, 1, objArr, i);
        if (this.count == -1610612736) {
            i++;
        }
        if (param > 0) {
            if (writer instanceof OutPort) {
                ((OutPort) writer).freshLine();
                param--;
            }
            while (true) {
                param--;
                if (param < 0) {
                    break;
                }
                writer.write(10);
            }
        }
        return i;
    }
}
