package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: LispFormat */
class LispPluralFormat extends ReportFormat {
    boolean backup;
    boolean y;

    LispPluralFormat() {
    }

    public static LispPluralFormat getInstance(boolean z, boolean z2) {
        LispPluralFormat lispPluralFormat = new LispPluralFormat();
        lispPluralFormat.backup = z;
        lispPluralFormat.y = z2;
        return lispPluralFormat;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        if (this.backup) {
            i--;
        }
        int i2 = i + 1;
        boolean z = objArr[i] != IntNum.one();
        if (this.y) {
            print(writer, z ? "ies" : EllipticCurveJsonWebKey.Y_MEMBER_NAME);
        } else if (z) {
            writer.write(115);
        }
        return i2;
    }
}
