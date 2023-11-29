package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class CompoundFormat extends ReportFormat {
    protected Format[] formats;
    protected int length;

    public CompoundFormat(Format[] formatArr, int i) {
        this.formats = formatArr;
        this.length = i;
    }

    public CompoundFormat(Format[] formatArr) {
        this.formats = formatArr;
        this.length = formatArr.length;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        for (int i2 = 0; i2 < this.length; i2++) {
            Format format = this.formats[i2];
            if (format instanceof ReportFormat) {
                i = ((ReportFormat) format).format(objArr, i, writer, fieldPosition);
                if (i < 0) {
                    return i;
                }
            } else if (i >= objArr.length) {
                writer.write("#<missing format argument>");
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                format.format(objArr[i], stringBuffer, fieldPosition);
                writer.write(stringBuffer.toString());
                i++;
            }
        }
        return i;
    }

    public final int format(Object[] objArr, int i, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        for (int i2 = 0; i2 < this.length; i2++) {
            Format format = this.formats[i2];
            if (format instanceof ReportFormat) {
                i = ((ReportFormat) format).format(objArr, i, stringBuffer, fieldPosition);
                if (i < 0) {
                    return i;
                }
            } else {
                format.format(objArr[i], stringBuffer, fieldPosition);
                i++;
            }
        }
        return i;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new Error("CompoundFormat.parseObject - not implemented");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CompoundFormat[");
        for (int i = 0; i < this.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.formats[i]);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
