package gnu.kawa.functions;

import gnu.math.RealNum;
import gnu.text.EnglishIntegerFormat;
import gnu.text.RomanIntegerFormat;
import java.text.Format;

public class IntegerFormat extends gnu.text.IntegerFormat {
    private static IntegerFormat plainDecimalFormat;

    public static IntegerFormat getInstance() {
        if (plainDecimalFormat == null) {
            plainDecimalFormat = new IntegerFormat();
        }
        return plainDecimalFormat;
    }

    public static Format getInstance(int i, int i2, int i3, int i4, int i5, int i6) {
        boolean z = true;
        if (i == -1073741824) {
            if (i3 == -1073741824 && i3 == -1073741824 && i4 == -1073741824 && i5 == -1073741824) {
                if ((i6 & 1) == 0) {
                    z = false;
                }
                if ((i6 & 2) != 0) {
                    return RomanIntegerFormat.getInstance(z);
                }
                return EnglishIntegerFormat.getInstance(z);
            }
            i = 10;
        }
        if (i2 == -1073741824) {
            i2 = 1;
        }
        if (i3 == -1073741824) {
            i3 = 32;
        }
        if (i4 == -1073741824) {
            i4 = 44;
        }
        if (i5 == -1073741824) {
            i5 = 3;
        }
        if (i == 10 && i2 == 1 && i3 == 32 && i4 == 44 && i5 == 3 && i6 == 0) {
            return getInstance();
        }
        IntegerFormat integerFormat = new IntegerFormat();
        integerFormat.base = i;
        integerFormat.minWidth = i2;
        integerFormat.padChar = i3;
        integerFormat.commaChar = i4;
        integerFormat.commaInterval = i5;
        integerFormat.flags = i6;
        return integerFormat;
    }

    public String convertToIntegerString(Object obj, int i) {
        if (obj instanceof RealNum) {
            return ((RealNum) obj).toExactInt(4).toString(i);
        }
        return super.convertToIntegerString(obj, i);
    }
}
