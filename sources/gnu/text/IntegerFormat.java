package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.text.FieldPosition;

public class IntegerFormat extends ReportFormat {
    public static final int MIN_DIGITS = 64;
    public static final int PAD_RIGHT = 16;
    public static final int SHOW_BASE = 8;
    public static final int SHOW_GROUPS = 1;
    public static final int SHOW_PLUS = 2;
    public static final int SHOW_SPACE = 4;
    public static final int UPPERCASE = 32;
    public int base = 10;
    public int commaChar = 44;
    public int commaInterval = 3;
    public int flags = 0;
    public int minWidth = 1;
    public int padChar = 32;

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        return format((Object) objArr, i, writer, fieldPosition);
    }

    public int format(Object obj, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int i2;
        int i3;
        int i4;
        Object obj2 = obj;
        int i5 = i;
        Writer writer2 = writer;
        Object[] objArr = obj2 instanceof Object[] ? (Object[]) obj2 : null;
        int param = getParam(this.minWidth, 1, objArr, i5);
        if (this.minWidth == -1610612736) {
            i5++;
        }
        char param2 = getParam(this.padChar, ' ', objArr, i5);
        if (this.padChar == -1610612736) {
            i5++;
        }
        char param3 = getParam(this.commaChar, ',', objArr, i5);
        if (this.commaChar == -1610612736) {
            i5++;
        }
        int param4 = getParam(this.commaInterval, 3, objArr, i5);
        if (this.commaInterval == -1610612736) {
            i5++;
        }
        int i6 = this.flags;
        boolean z = (i6 & 1) != 0;
        boolean z2 = (i6 & 16) != 0;
        boolean z3 = param2 == '0';
        if (objArr != null) {
            if (i5 >= objArr.length) {
                writer2.write("#<missing format argument>");
                return i5;
            }
            obj2 = objArr[i5];
        }
        String convertToIntegerString = convertToIntegerString(obj2, this.base);
        if (convertToIntegerString != null) {
            char charAt = convertToIntegerString.charAt(0);
            boolean z4 = charAt == '-';
            int length = convertToIntegerString.length();
            int i7 = z4 ? length - 1 : length;
            int i8 = i7 + (z ? (i7 - 1) / param4 : 0);
            if (z4 || (this.flags & 6) != 0) {
                i8++;
            }
            int i9 = this.flags;
            if ((i9 & 8) != 0) {
                int i10 = this.base;
                if (i10 == 16) {
                    i8 += 2;
                } else if (i10 == 8 && charAt != '0') {
                    i8++;
                }
            }
            if ((i9 & 64) == 0) {
                i2 = i8;
            } else if (length == 1 && charAt == '0' && param == 0) {
                i2 = i7;
                length = 0;
            } else {
                i2 = i7;
            }
            if (!z2 && !z3) {
                while (param > i2) {
                    writer2.write(param2);
                    param--;
                }
            }
            if (z4) {
                writer2.write(45);
                length--;
                i4 = 32;
                i3 = 1;
            } else {
                int i11 = this.flags;
                if ((i11 & 2) != 0) {
                    writer2.write(43);
                } else if ((i11 & 4) != 0) {
                    i4 = 32;
                    writer2.write(32);
                    i3 = 0;
                }
                i4 = 32;
                i3 = 0;
            }
            int i12 = this.base;
            boolean z5 = i12 > 10 && (this.flags & i4) != 0;
            if ((this.flags & 8) != 0) {
                if (i12 == 16) {
                    writer2.write(48);
                    writer2.write(z5 ? 88 : 120);
                } else if (i12 == 8 && charAt != '0') {
                    writer2.write(48);
                }
            }
            if (z3) {
                while (param > i2) {
                    writer2.write(param2);
                    param--;
                }
            }
            while (true) {
                int i13 = i3;
                if (length == 0) {
                    break;
                }
                i3 = i13 + 1;
                char charAt2 = convertToIntegerString.charAt(i13);
                if (z5) {
                    charAt2 = Character.toUpperCase(charAt2);
                }
                writer2.write(charAt2);
                length--;
                if (z && length > 0 && length % param4 == 0) {
                    writer2.write(param3);
                }
            }
            if (z2) {
                while (param > i2) {
                    writer2.write(param2);
                    param--;
                }
            }
        } else {
            print(writer2, obj2.toString());
        }
        return i5 + 1;
    }

    public String convertToIntegerString(Object obj, int i) {
        if (!(obj instanceof Number)) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return ((BigInteger) obj).toString(i);
        }
        return Long.toString(((Number) obj).longValue(), i);
    }
}
