package gnu.kawa.functions;

import gnu.math.ExponentialFormat;
import gnu.math.FixedRealFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispRealFormat extends ReportFormat {
    int arg1;
    int arg2;
    int arg3;
    int arg4;
    int arg5;
    int arg6;
    int arg7;
    int argsUsed;
    boolean internalPad;
    char op;
    boolean showPlus;

    LispRealFormat() {
        int i = this.arg1;
        int i2 = (i == -1342177280 || this.arg2 == -1342177280 || this.arg3 == -1342177280 || this.arg4 == -1342177280 || this.arg5 == -1342177280 || this.arg6 == -1342177280 || this.arg7 == -1342177280) ? 1 : 0;
        this.argsUsed = i2;
        if (i == -1610612736) {
            this.argsUsed = i2 + 2;
        }
        if (this.arg2 == -1610612736) {
            this.argsUsed += 2;
        }
        if (this.arg3 == -1610612736) {
            this.argsUsed += 2;
        }
        if (this.arg4 == -1610612736) {
            this.argsUsed += 2;
        }
        if (this.arg5 == -1610612736) {
            this.argsUsed += 2;
        }
        if (this.arg6 == -1610612736) {
            this.argsUsed += 2;
        }
        if (this.arg7 == -1610612736) {
            this.argsUsed += 2;
        }
    }

    public Format resolve(Object[] objArr, int i) {
        char c = this.op;
        boolean z = false;
        if (c == '$') {
            FixedRealFormat fixedRealFormat = new FixedRealFormat();
            int param = getParam(this.arg1, 2, objArr, i);
            if (this.arg1 == -1610612736) {
                i++;
            }
            int param2 = getParam(this.arg2, 1, objArr, i);
            if (this.arg2 == -1610612736) {
                i++;
            }
            int param3 = getParam(this.arg3, 0, objArr, i);
            if (this.arg3 == -1610612736) {
                i++;
            }
            char param4 = getParam(this.arg4, ' ', objArr, i);
            fixedRealFormat.setMaximumFractionDigits(param);
            fixedRealFormat.setMinimumIntegerDigits(param2);
            fixedRealFormat.width = param3;
            fixedRealFormat.padChar = param4;
            fixedRealFormat.internalPad = this.internalPad;
            fixedRealFormat.showPlus = this.showPlus;
            return fixedRealFormat;
        } else if (c == 'F') {
            FixedRealFormat fixedRealFormat2 = new FixedRealFormat();
            int param5 = getParam(this.arg1, 0, objArr, i);
            if (this.arg1 == -1610612736) {
                i++;
            }
            int param6 = getParam(this.arg2, -1, objArr, i);
            if (this.arg2 == -1610612736) {
                i++;
            }
            int param7 = getParam(this.arg3, 0, objArr, i);
            if (this.arg3 == -1610612736) {
                i++;
            }
            fixedRealFormat2.overflowChar = getParam(this.arg4, 0, objArr, i);
            if (this.arg4 == -1610612736) {
                i++;
            }
            char param8 = getParam(this.arg5, ' ', objArr, i);
            fixedRealFormat2.setMaximumFractionDigits(param6);
            fixedRealFormat2.setMinimumIntegerDigits(0);
            fixedRealFormat2.width = param5;
            fixedRealFormat2.scale = param7;
            fixedRealFormat2.padChar = param8;
            fixedRealFormat2.internalPad = this.internalPad;
            fixedRealFormat2.showPlus = this.showPlus;
            return fixedRealFormat2;
        } else {
            ExponentialFormat exponentialFormat = new ExponentialFormat();
            exponentialFormat.exponentShowSign = true;
            exponentialFormat.width = getParam(this.arg1, 0, objArr, i);
            if (this.arg1 == -1610612736) {
                i++;
            }
            exponentialFormat.fracDigits = getParam(this.arg2, -1, objArr, i);
            if (this.arg2 == -1610612736) {
                i++;
            }
            exponentialFormat.expDigits = getParam(this.arg3, 0, objArr, i);
            if (this.arg3 == -1610612736) {
                i++;
            }
            exponentialFormat.intDigits = getParam(this.arg4, 1, objArr, i);
            if (this.arg4 == -1610612736) {
                i++;
            }
            exponentialFormat.overflowChar = getParam(this.arg5, 0, objArr, i);
            if (this.arg5 == -1610612736) {
                i++;
            }
            exponentialFormat.padChar = getParam(this.arg6, ' ', objArr, i);
            if (this.arg6 == -1610612736) {
                i++;
            }
            exponentialFormat.exponentChar = getParam(this.arg7, 'E', objArr, i);
            if (this.op == 'G') {
                z = true;
            }
            exponentialFormat.general = z;
            exponentialFormat.showPlus = this.showPlus;
            return exponentialFormat;
        }
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(100);
        Format resolve = resolve(objArr, i);
        int i2 = i + (this.argsUsed >> 1);
        int i3 = i2 + 1;
        resolve.format(objArr[i2], stringBuffer, fieldPosition);
        writer.write(stringBuffer.toString());
        return i3;
    }
}
