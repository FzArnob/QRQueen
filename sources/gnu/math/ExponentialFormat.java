package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class ExponentialFormat extends Format {
    static final double LOG10 = Math.log(10.0d);
    public int expDigits;
    public char exponentChar = 'E';
    public boolean exponentShowSign;
    public int fracDigits = -1;
    public boolean general;
    public int intDigits;
    public char overflowChar;
    public char padChar;
    public boolean showPlus;
    public int width;

    static boolean addOne(StringBuffer stringBuffer, int i, int i2) {
        while (i2 != i) {
            i2--;
            char charAt = stringBuffer.charAt(i2);
            if (charAt != '9') {
                stringBuffer.setCharAt(i2, (char) (charAt + 1));
                return false;
            }
            stringBuffer.setCharAt(i2, '0');
        }
        stringBuffer.insert(i2, '1');
        return true;
    }

    public StringBuffer format(float f, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format((double) f, this.fracDigits < 0 ? Float.toString(f) : null, stringBuffer, fieldPosition);
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(d, this.fracDigits < 0 ? Double.toString(d) : null, stringBuffer, fieldPosition);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0158 A[LOOP:1: B:113:0x0152->B:115:0x0158, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x018d A[LOOP:3: B:133:0x018d->B:135:0x0193, LOOP_START, PHI: r8 
      PHI: (r8v5 int) = (r8v4 int), (r8v6 int) binds: [B:127:0x017e, B:135:0x0193] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x023b A[LOOP:6: B:186:0x0237->B:188:0x023b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0247 A[LOOP:7: B:189:0x0243->B:191:0x0247, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0243 A[EDGE_INSN: B:199:0x0243->B:201:0x0243 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x024d A[EDGE_INSN: B:200:0x024d->B:192:0x024d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(double r22, java.lang.String r24, java.lang.StringBuffer r25, java.text.FieldPosition r26) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r3 = r25
            int r4 = r0.intDigits
            int r5 = r0.fracDigits
            r7 = 1
            r8 = 0
            int r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0013
            r8 = 1
            goto L_0x0014
        L_0x0013:
            r8 = 0
        L_0x0014:
            if (r8 == 0) goto L_0x0017
            double r1 = -r1
        L_0x0017:
            int r9 = r25.length()
            r10 = 43
            r11 = 45
            if (r8 == 0) goto L_0x0027
            if (r5 < 0) goto L_0x002e
            r3.append(r11)
            goto L_0x002e
        L_0x0027:
            boolean r12 = r0.showPlus
            if (r12 == 0) goto L_0x0030
            r3.append(r10)
        L_0x002e:
            r12 = 1
            goto L_0x0031
        L_0x0030:
            r12 = 0
        L_0x0031:
            int r13 = r25.length()
            boolean r14 = java.lang.Double.isNaN(r1)
            if (r14 != 0) goto L_0x0044
            boolean r14 = java.lang.Double.isInfinite(r1)
            if (r14 == 0) goto L_0x0042
            goto L_0x0044
        L_0x0042:
            r14 = 0
            goto L_0x0045
        L_0x0044:
            r14 = 1
        L_0x0045:
            r15 = 1000(0x3e8, float:1.401E-42)
            r6 = 10
            r16 = 2
            if (r5 < 0) goto L_0x007c
            if (r14 == 0) goto L_0x0050
            goto L_0x007c
        L_0x0050:
            if (r4 <= 0) goto L_0x0054
            r8 = 1
            goto L_0x0055
        L_0x0054:
            r8 = r4
        L_0x0055:
            int r8 = r8 + r5
            double r17 = java.lang.Math.log(r1)
            double r19 = LOG10
            double r17 = r17 / r19
            r19 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r10 = r17 + r19
            int r10 = (int) r10
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r10 != r11) goto L_0x006c
            r10 = 0
            goto L_0x006d
        L_0x006c:
            int r10 = r10 - r15
        L_0x006d:
            int r10 = r8 - r10
            int r10 = r10 - r7
            gnu.math.IntNum r1 = gnu.math.RealNum.toScaledInt((double) r1, (int) r10)
            r1.format((int) r6, (java.lang.StringBuffer) r3)
            int r1 = r8 + -1
            int r1 = r1 - r10
            goto L_0x00ec
        L_0x007c:
            if (r24 != 0) goto L_0x0083
            java.lang.String r1 = java.lang.Double.toString(r1)
            goto L_0x0085
        L_0x0083:
            r1 = r24
        L_0x0085:
            r2 = 69
            int r2 = r1.indexOf(r2)
            if (r2 < 0) goto L_0x00c1
            r3.append(r1)
            int r2 = r2 + r13
            int r10 = r2 + 1
            char r1 = r1.charAt(r10)
            r10 = 45
            if (r1 != r10) goto L_0x009d
            r1 = 1
            goto L_0x009e
        L_0x009d:
            r1 = 0
        L_0x009e:
            if (r1 == 0) goto L_0x00a2
            r11 = 2
            goto L_0x00a3
        L_0x00a2:
            r11 = 1
        L_0x00a3:
            int r11 = r11 + r2
            r10 = 0
        L_0x00a5:
            int r6 = r25.length()
            if (r11 >= r6) goto L_0x00b9
            int r10 = r10 * 10
            char r6 = r3.charAt(r11)
            r18 = 48
            int r6 = r6 + -48
            int r10 = r10 + r6
            int r11 = r11 + 1
            goto L_0x00a5
        L_0x00b9:
            if (r1 == 0) goto L_0x00bc
            int r10 = -r10
        L_0x00bc:
            r3.setLength(r2)
            r1 = r10
            goto L_0x00c5
        L_0x00c1:
            int r1 = gnu.math.RealNum.toStringScientific(r1, r3)
        L_0x00c5:
            if (r8 == 0) goto L_0x00c9
            int r13 = r13 + 1
        L_0x00c9:
            int r2 = r13 + 1
            r3.deleteCharAt(r2)
            int r2 = r25.length()
            int r2 = r2 - r13
            if (r2 <= r7) goto L_0x00e7
            int r6 = r13 + r2
            int r6 = r6 - r7
            char r6 = r3.charAt(r6)
            r8 = 48
            if (r6 != r8) goto L_0x00e7
            int r2 = r2 + -1
            int r6 = r13 + r2
            r3.setLength(r6)
        L_0x00e7:
            r8 = r2
            int r2 = r8 - r1
            int r10 = r2 + -1
        L_0x00ec:
            int r2 = r4 + -1
            int r1 = r1 - r2
            if (r1 >= 0) goto L_0x00f3
            int r2 = -r1
            goto L_0x00f4
        L_0x00f3:
            r2 = r1
        L_0x00f4:
            r6 = 3
            if (r2 < r15) goto L_0x00f9
            r15 = 4
            goto L_0x0106
        L_0x00f9:
            r15 = 100
            if (r2 < r15) goto L_0x00ff
            r15 = 3
            goto L_0x0106
        L_0x00ff:
            r15 = 10
            if (r2 < r15) goto L_0x0105
            r15 = 2
            goto L_0x0106
        L_0x0105:
            r15 = 1
        L_0x0106:
            int r11 = r0.expDigits
            if (r11 <= r15) goto L_0x010b
            r15 = r11
        L_0x010b:
            boolean r7 = r0.general
            if (r7 != 0) goto L_0x0111
            r11 = 0
            goto L_0x0117
        L_0x0111:
            if (r11 <= 0) goto L_0x0116
            int r11 = r11 + 2
            goto L_0x0117
        L_0x0116:
            r11 = 4
        L_0x0117:
            if (r5 >= 0) goto L_0x011c
            r16 = 1
            goto L_0x011e
        L_0x011c:
            r16 = 0
        L_0x011e:
            if (r7 != 0) goto L_0x0122
            if (r16 == 0) goto L_0x0150
        L_0x0122:
            int r10 = r8 - r10
            if (r16 == 0) goto L_0x012d
            r5 = 7
            if (r10 >= r5) goto L_0x012a
            r5 = r10
        L_0x012a:
            if (r8 <= r5) goto L_0x012d
            r5 = r8
        L_0x012d:
            int r18 = r5 - r10
            if (r7 == 0) goto L_0x0139
            if (r10 < 0) goto L_0x0139
            if (r18 < 0) goto L_0x0139
            r8 = r5
            r4 = r10
            r5 = 0
            goto L_0x0151
        L_0x0139:
            if (r16 == 0) goto L_0x0150
            int r7 = r0.width
            if (r7 > 0) goto L_0x0140
            goto L_0x0148
        L_0x0140:
            int r7 = r7 - r12
            int r7 = r7 - r15
            int r7 = r7 - r6
            if (r4 >= 0) goto L_0x0146
            int r7 = r7 - r4
        L_0x0146:
            if (r7 <= r5) goto L_0x014a
        L_0x0148:
            r8 = r5
            goto L_0x014b
        L_0x014a:
            r8 = r7
        L_0x014b:
            if (r8 > 0) goto L_0x0150
            r5 = 1
            r8 = 1
            goto L_0x0151
        L_0x0150:
            r5 = 1
        L_0x0151:
            int r8 = r8 + r13
        L_0x0152:
            int r6 = r25.length()
            if (r6 >= r8) goto L_0x015e
            r6 = 48
            r3.append(r6)
            goto L_0x0152
        L_0x015e:
            int r6 = r25.length()
            if (r8 != r6) goto L_0x0167
            r6 = 48
            goto L_0x016b
        L_0x0167:
            char r6 = r3.charAt(r8)
        L_0x016b:
            r7 = 53
            if (r6 < r7) goto L_0x0171
            r6 = 1
            goto L_0x0172
        L_0x0171:
            r6 = 0
        L_0x0172:
            if (r6 == 0) goto L_0x0178
            boolean r6 = addOne(r3, r13, r8)
        L_0x0178:
            r25.length()
            r3.setLength(r8)
            if (r4 >= 0) goto L_0x018d
            r6 = r4
        L_0x0181:
            r7 = 1
            int r6 = r6 + r7
            if (r6 > 0) goto L_0x018b
            r7 = 48
            r3.insert(r13, r7)
            goto L_0x0181
        L_0x018b:
            r6 = r13
            goto L_0x0199
        L_0x018d:
            r7 = 48
            int r6 = r13 + r4
            if (r6 <= r8) goto L_0x0199
            r3.append(r7)
            int r8 = r8 + 1
            goto L_0x018d
        L_0x0199:
            if (r14 == 0) goto L_0x019d
            r5 = 0
            goto L_0x01a2
        L_0x019d:
            r7 = 46
            r3.insert(r6, r7)
        L_0x01a2:
            if (r5 == 0) goto L_0x01d4
            char r7 = r0.exponentChar
            r3.append(r7)
            boolean r7 = r0.exponentShowSign
            if (r7 != 0) goto L_0x01af
            if (r1 >= 0) goto L_0x01b9
        L_0x01af:
            if (r1 < 0) goto L_0x01b4
            r10 = 43
            goto L_0x01b6
        L_0x01b4:
            r10 = 45
        L_0x01b6:
            r3.append(r10)
        L_0x01b9:
            int r1 = r25.length()
            r3.append(r2)
            int r2 = r25.length()
            int r7 = r0.expDigits
            int r2 = r2 - r1
            int r7 = r7 - r2
            if (r7 <= 0) goto L_0x01d5
        L_0x01ca:
            int r7 = r7 + -1
            if (r7 < 0) goto L_0x01d5
            r2 = 48
            r3.insert(r1, r2)
            goto L_0x01ca
        L_0x01d4:
            r15 = 0
        L_0x01d5:
            int r1 = r25.length()
            int r1 = r1 - r9
            int r2 = r0.width
            int r2 = r2 - r1
            if (r16 == 0) goto L_0x01fc
            r1 = 1
            int r6 = r6 + r1
            int r1 = r25.length()
            if (r6 == r1) goto L_0x01ef
            char r1 = r3.charAt(r6)
            char r7 = r0.exponentChar
            if (r1 != r7) goto L_0x01fc
        L_0x01ef:
            int r1 = r0.width
            if (r1 <= 0) goto L_0x01f5
            if (r2 <= 0) goto L_0x01fc
        L_0x01f5:
            int r2 = r2 + -1
            r1 = 48
            r3.insert(r6, r1)
        L_0x01fc:
            if (r2 >= 0) goto L_0x0202
            int r1 = r0.width
            if (r1 > 0) goto L_0x020f
        L_0x0202:
            if (r5 == 0) goto L_0x0222
            int r1 = r0.expDigits
            if (r15 <= r1) goto L_0x0222
            if (r1 <= 0) goto L_0x0222
            char r1 = r0.overflowChar
            if (r1 != 0) goto L_0x020f
            goto L_0x0222
        L_0x020f:
            char r1 = r0.overflowChar
            if (r1 == 0) goto L_0x024d
            r3.setLength(r9)
            int r1 = r0.width
        L_0x0218:
            int r1 = r1 + -1
            if (r1 < 0) goto L_0x024d
            char r2 = r0.overflowChar
            r3.append(r2)
            goto L_0x0218
        L_0x0222:
            if (r4 > 0) goto L_0x0231
            if (r2 > 0) goto L_0x022a
            int r1 = r0.width
            if (r1 > 0) goto L_0x0231
        L_0x022a:
            r1 = 48
            r3.insert(r13, r1)
            int r2 = r2 + -1
        L_0x0231:
            if (r5 != 0) goto L_0x0243
            int r1 = r0.width
            if (r1 <= 0) goto L_0x0243
        L_0x0237:
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x0243
            r1 = 32
            r3.append(r1)
            int r2 = r2 + -1
            goto L_0x0237
        L_0x0243:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x024d
            char r1 = r0.padChar
            r3.insert(r9, r1)
            goto L_0x0243
        L_0x024d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.ExponentialFormat.format(double, java.lang.String, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format((double) j, stringBuffer, fieldPosition);
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(((RealNum) obj).doubleValue(), stringBuffer, fieldPosition);
    }

    public Number parse(String str, ParsePosition parsePosition) {
        throw new Error("ExponentialFormat.parse - not implemented");
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new Error("ExponentialFormat.parseObject - not implemented");
    }
}
