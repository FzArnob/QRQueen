package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.MessageFormat;

public class Format extends ProcedureN {
    public static final Format format;

    static {
        Format format2 = new Format();
        format = format2;
        format2.setName("format");
        format2.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyFormat");
    }

    public static void format(Writer writer, Object[] objArr, int i) {
        int i2 = i + 1;
        Object obj = objArr[i];
        int length = objArr.length - i2;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, i2, objArr2, 0, length);
        formatToWriter(writer, obj, objArr2);
    }

    public static void formatToWriter(Writer writer, Object obj, Object... objArr) {
        if (writer == null) {
            writer = OutPort.outDefault();
        }
        try {
            if (obj instanceof MessageFormat) {
                writer.write(((MessageFormat) obj).format(objArr));
                return;
            }
            if (!(obj instanceof ReportFormat)) {
                obj = ParseFormat.parseFormat.apply1(obj);
            }
            ((ReportFormat) obj).format(objArr, 0, writer, (FieldPosition) null);
        } catch (IOException e) {
            throw new RuntimeException("Error in format: " + e);
        }
    }

    public static void formatToOutputStream(OutputStream outputStream, Object obj, Object... objArr) {
        OutPort outPort = new OutPort(outputStream);
        format(outPort, obj, objArr);
        outPort.closeThis();
    }

    public static String formatToString(int i, Object... objArr) {
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        format(charArrayOutPort, objArr, i);
        String charArrayOutPort2 = charArrayOutPort.toString();
        charArrayOutPort.close();
        return charArrayOutPort2;
    }

    public static FString formatToFString(char c, Object obj, Object[] objArr) {
        ReportFormat asFormat = ParseFormat.asFormat(obj, c);
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        try {
            asFormat.format(objArr, 0, (Writer) charArrayOutPort, (FieldPosition) null);
            char[] charArray = charArrayOutPort.toCharArray();
            charArrayOutPort.close();
            return new FString(charArray);
        } catch (IOException e) {
            throw new RuntimeException("Error in format: " + e);
        }
    }

    public Object applyN(Object[] objArr) {
        return format(objArr);
    }

    public static Object format(Object... objArr) {
        Writer writer = objArr[0];
        if (writer == Boolean.TRUE) {
            format(OutPort.outDefault(), objArr, 1);
            return Values.empty;
        } else if (writer == Boolean.FALSE) {
            return formatToString(1, objArr);
        } else {
            if ((writer instanceof MessageFormat) || (writer instanceof CharSequence) || (writer instanceof ReportFormat)) {
                return formatToString(0, objArr);
            }
            if (writer instanceof Writer) {
                format(writer, objArr, 1);
                return Values.empty;
            } else if (writer instanceof OutputStream) {
                formatToOutputStream((OutputStream) writer, objArr[1], drop2(objArr));
                return Values.empty;
            } else {
                throw new RuntimeException("bad first argument to format");
            }
        }
    }

    static Object[] drop2(Object[] objArr) {
        int length = objArr.length - 2;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 2, objArr2, 0, length);
        return objArr2;
    }
}
