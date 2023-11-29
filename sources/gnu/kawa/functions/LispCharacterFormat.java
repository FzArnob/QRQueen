package gnu.kawa.functions;

import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispCharacterFormat extends ReportFormat {
    int charVal;
    int count;
    boolean seenAt;
    boolean seenColon;

    LispCharacterFormat() {
    }

    public static LispCharacterFormat getInstance(int i, int i2, boolean z, boolean z2) {
        LispCharacterFormat lispCharacterFormat = new LispCharacterFormat();
        lispCharacterFormat.count = i2;
        lispCharacterFormat.charVal = i;
        lispCharacterFormat.seenAt = z;
        lispCharacterFormat.seenColon = z2;
        return lispCharacterFormat;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int param = getParam(this.count, 1, objArr, i);
        if (this.count == -1610612736) {
            i++;
        }
        char param2 = getParam(this.charVal, '?', objArr, i);
        if (this.charVal == -1610612736) {
            i++;
        }
        while (true) {
            param--;
            if (param < 0) {
                return i;
            }
            printChar(param2, this.seenAt, this.seenColon, writer);
        }
    }

    public static void printChar(int i, boolean z, boolean z2, Writer writer) throws IOException {
        if (z) {
            print(writer, Char.toScmReadableString(i));
        } else if (!z2) {
            writer.write(i);
        } else if (i < 32) {
            writer.write(94);
            writer.write(i + 64);
        } else if (i >= 127) {
            print(writer, "#\\x");
            print(writer, Integer.toString(i, 16));
        } else {
            writer.write(i);
        }
    }
}
