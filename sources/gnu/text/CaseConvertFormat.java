package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

public class CaseConvertFormat extends ReportFormat {
    Format baseFormat;
    char code;

    public CaseConvertFormat(Format format, char c) {
        this.baseFormat = format;
        this.code = c;
    }

    public Format getBaseFormat() {
        return this.baseFormat;
    }

    public void setBaseFormat(Format format) {
        this.baseFormat = format;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(100);
        int format = format(this.baseFormat, objArr, i, stringBuffer, fieldPosition);
        int length = stringBuffer.length();
        char c = ' ';
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = stringBuffer.charAt(i2);
            char c2 = this.code;
            if (c2 == 'U') {
                c = Character.toUpperCase(charAt);
            } else if (!(c2 == 'T' && i2 == 0) && (c2 != 'C' || Character.isLetterOrDigit(c))) {
                c = Character.toLowerCase(charAt);
            } else {
                c = Character.toTitleCase(charAt);
            }
            writer.write(c);
        }
        return format;
    }
}
