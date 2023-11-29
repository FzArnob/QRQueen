package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispIterationFormat extends ReportFormat {
    boolean atLeastOnce;
    Format body;
    int maxIterations;
    boolean seenAt;
    boolean seenColon;

    LispIterationFormat() {
    }

    public static int format(Format format, int i, Object[] objArr, int i2, Writer writer, boolean z, boolean z2) throws IOException {
        int i3 = 0;
        while (true) {
            if (i3 == i && i != -1) {
                return i2;
            }
            if (i2 == objArr.length && (i3 > 0 || !z2)) {
                return i2;
            }
            if (z) {
                i2++;
                if (ReportFormat.resultCode(ReportFormat.format(format, LispFormat.asArray(objArr[i2]), 0, writer, (FieldPosition) null)) == 242) {
                    return i2;
                }
            } else {
                i2 = ReportFormat.format(format, objArr, i2, writer, (FieldPosition) null);
                if (i2 < 0) {
                    return ReportFormat.nextArg(i2);
                }
            }
            i3++;
        }
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int i2;
        LispFormat lispFormat;
        int param = getParam(this.maxIterations, -1, objArr, i);
        if (this.maxIterations == -1610612736) {
            i++;
        }
        Format format = this.body;
        if (format == null) {
            i2 = i + 1;
            Format format2 = objArr[i];
            if (format2 instanceof Format) {
                lispFormat = format2;
            } else {
                try {
                    lispFormat = new LispFormat(format2.toString());
                } catch (Exception unused) {
                    print(writer, "<invalid argument for \"~{~}\" format>");
                    return objArr.length;
                }
            }
        } else {
            lispFormat = format;
            i2 = i;
        }
        if (this.seenAt) {
            return format(lispFormat, param, objArr, i2, writer, this.seenColon, this.atLeastOnce);
        }
        Object obj = objArr[i2];
        Object[] asArray = LispFormat.asArray(obj);
        if (asArray == null) {
            writer.write("{" + obj + "}");
        } else {
            format(lispFormat, param, asArray, 0, writer, this.seenColon, this.atLeastOnce);
        }
        return i2 + 1;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LispIterationFormat[");
        stringBuffer.append(this.body);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
