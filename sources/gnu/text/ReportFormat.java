package gnu.text;

import gnu.lists.Consumer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;

public abstract class ReportFormat extends Format {
    public static final int PARAM_FROM_COUNT = -1342177280;
    public static final int PARAM_FROM_LIST = -1610612736;
    public static final int PARAM_UNSPECIFIED = -1073741824;

    public static int nextArg(int i) {
        return i & 16777215;
    }

    public static int result(int i, int i2) {
        return (i << 24) | i2;
    }

    public static int resultCode(int i) {
        return i >>> 24;
    }

    public abstract int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException;

    public int format(Object obj, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        if (obj instanceof Object[]) {
            return format((Object[]) obj, i, writer, fieldPosition);
        }
        return format(new Object[]{obj}, i, writer, fieldPosition);
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        format((Object[]) obj, 0, stringBuffer, fieldPosition);
        return stringBuffer;
    }

    public int format(Object[] objArr, int i, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        try {
            int format = format(objArr, i, (Writer) charArrayWriter, fieldPosition);
            if (format < 0) {
                return format;
            }
            stringBuffer.append(charArrayWriter.toCharArray());
            return format;
        } catch (IOException e) {
            throw new Error("unexpected exception: " + e);
        }
    }

    public static int format(Format format, Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int i2;
        if (format instanceof ReportFormat) {
            return ((ReportFormat) format).format(objArr, i, writer, fieldPosition);
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (format instanceof MessageFormat) {
            i2 = format(format, objArr, i, stringBuffer, fieldPosition);
        } else {
            format.format(objArr[i], stringBuffer, fieldPosition);
            i2 = i + 1;
        }
        int length = stringBuffer.length();
        char[] cArr = new char[length];
        stringBuffer.getChars(0, length, cArr, 0);
        writer.write(cArr);
        return i2;
    }

    public static int format(Format format, Object[] objArr, int i, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Object[] objArr2;
        int i2;
        if (format instanceof ReportFormat) {
            return ((ReportFormat) format).format(objArr, i, stringBuffer, fieldPosition);
        }
        if (format instanceof MessageFormat) {
            i2 = objArr.length - i;
            objArr2 = objArr;
            if (i > 0) {
                int length = objArr.length - i;
                Object[] objArr3 = new Object[length];
                System.arraycopy(objArr, i, objArr3, 0, length);
                objArr2 = objArr3;
            }
        } else {
            i2 = 1;
            objArr2 = objArr[i];
        }
        format.format(objArr2, stringBuffer, fieldPosition);
        return i + i2;
    }

    public static void print(Writer writer, String str) throws IOException {
        if (writer instanceof PrintWriter) {
            ((PrintWriter) writer).print(str);
        } else {
            writer.write(str.toCharArray());
        }
    }

    public static void print(Object obj, Consumer consumer) {
        String str;
        if (obj instanceof Printable) {
            ((Printable) obj).print(consumer);
            return;
        }
        if (obj == null) {
            str = "null";
        } else {
            str = obj.toString();
        }
        consumer.write(str);
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new Error("ReportFormat.parseObject - not implemented");
    }

    public static int getParam(Object obj, int i) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof Character) {
            return ((Character) obj).charValue();
        }
        return obj instanceof Char ? ((Char) obj).charValue() : i;
    }

    protected static int getParam(int i, int i2, Object[] objArr, int i3) {
        if (i == -1342177280) {
            return objArr.length - i3;
        }
        return i == -1610612736 ? objArr == null ? i2 : getParam(objArr[i3], i2) : i == -1073741824 ? i2 : i;
    }

    protected static char getParam(int i, char c, Object[] objArr, int i2) {
        return (char) getParam(i, (int) c, objArr, i2);
    }
}
