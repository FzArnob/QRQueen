package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispPrettyFormat extends ReportFormat {
    Format body;
    boolean perLine;
    String prefix;
    boolean seenAt;
    Format[] segments;
    String suffix;

    LispPrettyFormat() {
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int i2;
        String str = this.prefix;
        String str2 = this.suffix;
        OutPort outPort = writer instanceof OutPort ? (OutPort) writer : null;
        try {
            if (this.seenAt) {
                if (outPort != null) {
                    outPort.startLogicalBlock(str, this.perLine, str2);
                }
                i2 = ReportFormat.format(this.body, objArr, i, writer, fieldPosition);
            } else {
                Object obj = objArr[i];
                Object[] asArray = LispFormat.asArray(obj);
                if (asArray == null) {
                    str = "";
                    str2 = str;
                }
                if (outPort != null) {
                    outPort.startLogicalBlock(str, this.perLine, this.suffix);
                }
                if (asArray == null) {
                    ObjectFormat.format(obj, writer, -1, true);
                } else {
                    ReportFormat.format(this.body, asArray, 0, writer, fieldPosition);
                }
                i2 = i + 1;
            }
            return i2;
        } finally {
            if (outPort != null) {
                outPort.endLogicalBlock(str2);
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LispPrettyFormat[");
        stringBuffer.append("prefix: \"");
        stringBuffer.append(this.prefix);
        stringBuffer.append("\", suffix: \"");
        stringBuffer.append(this.suffix);
        stringBuffer.append("\", body: ");
        stringBuffer.append(this.body);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
