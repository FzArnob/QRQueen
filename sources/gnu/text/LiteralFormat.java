package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class LiteralFormat extends ReportFormat {
    char[] text;

    public LiteralFormat(char[] cArr) {
        this.text = cArr;
    }

    public LiteralFormat(String str) {
        this.text = str.toCharArray();
    }

    public LiteralFormat(StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        char[] cArr = new char[length];
        this.text = cArr;
        stringBuffer.getChars(0, length, cArr, 0);
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        writer.write(this.text);
        return i;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new Error("LiteralFormat.parseObject - not implemented");
    }

    public String content() {
        return new String(this.text);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("LiteralFormat[\"");
        stringBuffer.append(this.text);
        stringBuffer.append("\"]");
        return stringBuffer.toString();
    }
}
