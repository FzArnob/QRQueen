package gnu.text;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class RomanIntegerFormat extends NumberFormat {
    static final String codes = "IVXLCDM";
    private static RomanIntegerFormat newRoman;
    private static RomanIntegerFormat oldRoman;
    public boolean oldStyle;

    public RomanIntegerFormat(boolean z) {
        this.oldStyle = z;
    }

    public RomanIntegerFormat() {
    }

    public static RomanIntegerFormat getInstance(boolean z) {
        if (z) {
            if (oldRoman == null) {
                oldRoman = new RomanIntegerFormat(true);
            }
            return oldRoman;
        }
        if (newRoman == null) {
            newRoman = new RomanIntegerFormat(false);
        }
        return newRoman;
    }

    public static String format(int i, boolean z) {
        if (i <= 0 || i >= 4999) {
            return Integer.toString(i);
        }
        StringBuffer stringBuffer = new StringBuffer(20);
        int i2 = 1000;
        for (int i3 = 3; i3 >= 0; i3--) {
            int i4 = i / i2;
            i -= i4 * i2;
            if (i4 != 0) {
                if (z || !(i4 == 4 || i4 == 9)) {
                    if (i4 >= 5) {
                        stringBuffer.append(codes.charAt((i3 * 2) + 1));
                        i4 -= 5;
                    }
                    while (true) {
                        i4--;
                        if (i4 < 0) {
                            break;
                        }
                        stringBuffer.append(codes.charAt(i3 * 2));
                    }
                } else {
                    int i5 = i3 * 2;
                    stringBuffer.append(codes.charAt(i5));
                    stringBuffer.append(codes.charAt(i5 + ((i4 + 1) / 5)));
                }
            }
            i2 /= 10;
        }
        return stringBuffer.toString();
    }

    public static String format(int i) {
        return format(i, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(long r6, java.lang.StringBuffer r8, java.text.FieldPosition r9) {
        /*
            r5 = this;
            r0 = 0
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x001a
            boolean r0 = r5.oldStyle
            if (r0 == 0) goto L_0x000d
            r1 = 4999(0x1387, float:7.005E-42)
            goto L_0x000f
        L_0x000d:
            r1 = 3999(0xf9f, float:5.604E-42)
        L_0x000f:
            long r1 = (long) r1
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x001a
            int r7 = (int) r6
            java.lang.String r6 = format(r7, r0)
            goto L_0x001e
        L_0x001a:
            java.lang.String r6 = java.lang.Long.toString(r6)
        L_0x001e:
            if (r9 == 0) goto L_0x0042
            r0 = 1
            int r7 = r6.length()
            r2 = r7
        L_0x0027:
            int r2 = r2 + -1
            if (r2 <= 0) goto L_0x0033
            r3 = 10
            long r0 = r0 * r3
            r3 = 9
            long r0 = r0 + r3
            goto L_0x0027
        L_0x0033:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>(r7)
            java.text.DecimalFormat r7 = new java.text.DecimalFormat
            java.lang.String r3 = "0"
            r7.<init>(r3)
            r7.format(r0, r2, r9)
        L_0x0042:
            r8.append(r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.RomanIntegerFormat.format(long, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        long j = (long) d;
        if (((double) j) == d) {
            return format(j, stringBuffer, fieldPosition);
        }
        stringBuffer.append(Double.toString(d));
        return stringBuffer;
    }

    public Number parse(String str, ParsePosition parsePosition) {
        throw new Error("RomanIntegerFormat.parseObject - not implemented");
    }
}
