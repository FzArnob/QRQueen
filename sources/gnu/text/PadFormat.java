package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;

public class PadFormat extends ReportFormat {
    Format fmt;
    int minWidth;
    char padChar;
    int where;

    public static int padNeeded(int i, int i2, int i3, int i4) {
        int i5 = i4 + i;
        if (i3 <= 1) {
            i3 = i2 - i5;
        }
        while (i5 < i2) {
            i5 += i3;
        }
        return i5 - i;
    }

    public PadFormat(Format format, int i, char c, int i2) {
        this.fmt = format;
        this.minWidth = i;
        this.padChar = c;
        this.where = i2;
    }

    public PadFormat(Format format, int i) {
        this(format, i, ' ', 100);
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        return format(this.fmt, objArr, i, writer, this.padChar, this.minWidth, 1, 0, this.where, fieldPosition);
    }

    public static int format(Format format, Object[] objArr, int i, Writer writer, char c, int i2, int i3, int i4, int i5, FieldPosition fieldPosition) throws IOException {
        int i6;
        StringBuffer stringBuffer = new StringBuffer(200);
        int i7 = 1;
        if (format instanceof ReportFormat) {
            i6 = ((ReportFormat) format).format(objArr, i, stringBuffer, fieldPosition);
        } else if (format instanceof MessageFormat) {
            format.format(objArr, stringBuffer, fieldPosition);
            i6 = objArr.length;
        } else {
            format.format(objArr[i], stringBuffer, fieldPosition);
            i6 = i + 1;
        }
        int length = stringBuffer.length();
        int padNeeded = padNeeded(length, i2, i3, i4);
        String stringBuffer2 = stringBuffer.toString();
        if (padNeeded > 0) {
            if (i5 == -1) {
                if (length > 0) {
                    char charAt = stringBuffer2.charAt(0);
                    if (charAt == '-' || charAt == '+') {
                        writer.write(charAt);
                    } else {
                        i7 = 0;
                    }
                    if (length - i7 > 2 && stringBuffer2.charAt(i7) == '0') {
                        writer.write(48);
                        i7++;
                        char charAt2 = stringBuffer2.charAt(i7);
                        if (charAt2 == 'x' || charAt2 == 'X') {
                            i7++;
                            writer.write(charAt2);
                        }
                    }
                    if (i7 > 0) {
                        stringBuffer2 = stringBuffer2.substring(i7);
                    }
                }
                i5 = 0;
            }
            int i8 = (i5 * padNeeded) / 100;
            int i9 = padNeeded - i8;
            while (true) {
                i9--;
                if (i9 < 0) {
                    break;
                }
                writer.write(c);
            }
            writer.write(stringBuffer2);
            while (true) {
                i8--;
                if (i8 < 0) {
                    break;
                }
                writer.write(c);
            }
        } else {
            writer.write(stringBuffer2);
        }
        return i6;
    }
}
