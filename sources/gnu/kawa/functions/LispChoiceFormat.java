package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispChoiceFormat extends ReportFormat {
    Format[] choices;
    boolean lastIsDefault;
    int param;
    boolean skipIfFalse;
    boolean testBoolean;

    LispChoiceFormat() {
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Format format;
        char c = 0;
        if (this.testBoolean) {
            Format[] formatArr = this.choices;
            if (objArr[i] != Boolean.FALSE) {
                c = 1;
            }
            format = formatArr[c];
            i++;
        } else if (!this.skipIfFalse) {
            int param2 = getParam(this.param, -1610612736, objArr, i);
            if (this.param == -1610612736) {
                i++;
            }
            if (param2 < 0 || param2 >= this.choices.length) {
                if (!this.lastIsDefault) {
                    return i;
                }
                param2 = this.choices.length - 1;
            }
            format = this.choices[param2];
        } else if (objArr[i] == Boolean.FALSE) {
            return i + 1;
        } else {
            format = this.choices[0];
        }
        return ReportFormat.format(format, objArr, i, writer, fieldPosition);
    }
}
