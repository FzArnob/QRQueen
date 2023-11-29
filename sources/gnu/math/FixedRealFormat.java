package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FixedRealFormat extends Format {
    private int d;
    private int i;
    public boolean internalPad;
    public char overflowChar;
    public char padChar;
    public int scale;
    public boolean showPlus;
    public int width;

    public int getMaximumFractionDigits() {
        return this.d;
    }

    public int getMinimumIntegerDigits() {
        return this.i;
    }

    public void setMaximumFractionDigits(int i2) {
        this.d = i2;
    }

    public void setMinimumIntegerDigits(int i2) {
        this.i = i2;
    }

    public void format(RealNum realNum, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int maximumFractionDigits;
        int i2;
        if (!(realNum instanceof RatNum) || (maximumFractionDigits = getMaximumFractionDigits()) < 0) {
            format(realNum.doubleValue(), stringBuffer, fieldPosition);
            return;
        }
        RatNum ratNum = (RatNum) realNum;
        boolean isNegative = ratNum.isNegative();
        if (isNegative) {
            ratNum = ratNum.rneg();
        }
        int length = stringBuffer.length();
        if (isNegative) {
            stringBuffer.append('-');
        } else if (this.showPlus) {
            stringBuffer.append('+');
        } else {
            i2 = 0;
            String intNum = RealNum.toScaledInt(ratNum, this.scale + maximumFractionDigits).toString();
            stringBuffer.append(intNum);
            int length2 = intNum.length();
            format(stringBuffer, fieldPosition, length2, length2 - maximumFractionDigits, maximumFractionDigits, i2, length);
        }
        i2 = 1;
        String intNum2 = RealNum.toScaledInt(ratNum, this.scale + maximumFractionDigits).toString();
        stringBuffer.append(intNum2);
        int length22 = intNum2.length();
        format(stringBuffer, fieldPosition, length22, length22 - maximumFractionDigits, maximumFractionDigits, i2, length);
    }

    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        format((RealNum) IntNum.make(j), stringBuffer, fieldPosition);
        return stringBuffer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a8 A[LOOP:0: B:29:0x009c->B:33:0x00a8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8 A[LOOP:1: B:38:0x00b8->B:39:0x00ba, LOOP_START, PHI: r11 
      PHI: (r11v9 int) = (r11v5 int), (r11v10 int) binds: [B:37:0x00b6, B:39:0x00ba] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d5 A[LOOP:2: B:48:0x00d3->B:49:0x00d5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0106 A[LOOP:3: B:61:0x00fc->B:64:0x0106, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x012e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(double r17, java.lang.StringBuffer r19, java.text.FieldPosition r20) {
        /*
            r16 = this;
            r8 = r16
            r0 = r17
            r9 = r19
            boolean r2 = java.lang.Double.isNaN(r17)
            if (r2 != 0) goto L_0x0140
            boolean r2 = java.lang.Double.isInfinite(r17)
            if (r2 == 0) goto L_0x0014
            goto L_0x0140
        L_0x0014:
            int r2 = r16.getMaximumFractionDigits()
            if (r2 < 0) goto L_0x0025
            gnu.math.RatNum r0 = gnu.math.DFloNum.toExact(r17)
            r2 = r20
            r8.format((gnu.math.RealNum) r0, (java.lang.StringBuffer) r9, (java.text.FieldPosition) r2)
            goto L_0x013f
        L_0x0025:
            r2 = r20
            r3 = 0
            r5 = 0
            r6 = 1
            int r7 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x0032
            double r0 = -r0
            r7 = 1
            goto L_0x0033
        L_0x0032:
            r7 = 0
        L_0x0033:
            int r10 = r19.length()
            r3 = 43
            if (r7 == 0) goto L_0x0041
            r4 = 45
            r9.append(r4)
            goto L_0x0048
        L_0x0041:
            boolean r4 = r8.showPlus
            if (r4 == 0) goto L_0x004a
            r9.append(r3)
        L_0x0048:
            r4 = 1
            goto L_0x004b
        L_0x004a:
            r4 = 0
        L_0x004b:
            java.lang.String r0 = java.lang.Double.toString(r0)
            int r1 = r8.scale
            r11 = 69
            int r11 = r0.indexOf(r11)
            if (r11 < 0) goto L_0x0070
            int r12 = r11 + 1
            char r13 = r0.charAt(r12)
            if (r13 != r3) goto L_0x0063
            int r12 = r12 + 1
        L_0x0063:
            java.lang.String r3 = r0.substring(r12)
            int r3 = java.lang.Integer.parseInt(r3)
            int r1 = r1 + r3
            java.lang.String r0 = r0.substring(r5, r11)
        L_0x0070:
            r3 = 46
            int r3 = r0.indexOf(r3)
            int r11 = r0.length()
            if (r3 < 0) goto L_0x0097
            int r11 = r11 - r3
            int r11 = r11 - r6
            int r1 = r1 - r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = r0.substring(r5, r3)
            r11.append(r12)
            int r3 = r3 + r6
            java.lang.String r0 = r0.substring(r3)
            r11.append(r0)
            java.lang.String r0 = r11.toString()
        L_0x0097:
            int r3 = r0.length()
            r11 = 0
        L_0x009c:
            int r12 = r3 + -1
            r13 = 48
            if (r11 >= r12) goto L_0x00ab
            char r12 = r0.charAt(r11)
            if (r12 != r13) goto L_0x00ab
            int r11 = r11 + 1
            goto L_0x009c
        L_0x00ab:
            if (r11 <= 0) goto L_0x00b2
            java.lang.String r0 = r0.substring(r11)
            int r3 = r3 - r11
        L_0x00b2:
            int r11 = r3 + r1
            int r12 = r8.width
            if (r12 <= 0) goto L_0x00c6
        L_0x00b8:
            if (r11 >= 0) goto L_0x00c0
            r9.append(r13)
            int r11 = r11 + 1
            goto L_0x00b8
        L_0x00c0:
            int r3 = r8.width
            int r3 = r3 - r4
            int r3 = r3 - r6
        L_0x00c4:
            int r3 = r3 - r11
            goto L_0x00cd
        L_0x00c6:
            r12 = 16
            if (r3 <= r12) goto L_0x00c4
            r3 = 16
            goto L_0x00c4
        L_0x00cd:
            if (r3 >= 0) goto L_0x00d0
            r3 = 0
        L_0x00d0:
            r9.append(r0)
        L_0x00d3:
            if (r1 <= 0) goto L_0x00db
            r9.append(r13)
            int r1 = r1 + -1
            goto L_0x00d3
        L_0x00db:
            int r0 = r10 + r4
            int r1 = r0 + r11
            int r3 = r3 + r1
            int r12 = r19.length()
            if (r3 < r12) goto L_0x00ea
            r3 = r12
            r12 = 48
            goto L_0x00ee
        L_0x00ea:
            char r12 = r9.charAt(r3)
        L_0x00ee:
            r14 = 53
            if (r12 < r14) goto L_0x00f4
            r12 = 1
            goto L_0x00f5
        L_0x00f4:
            r12 = 0
        L_0x00f5:
            if (r12 == 0) goto L_0x00fa
            r14 = 57
            goto L_0x00fc
        L_0x00fa:
            r14 = 48
        L_0x00fc:
            if (r3 <= r1) goto L_0x0109
            int r15 = r3 + -1
            char r15 = r9.charAt(r15)
            if (r15 != r14) goto L_0x0109
            int r3 = r3 + -1
            goto L_0x00fc
        L_0x0109:
            int r1 = r3 - r0
            int r14 = r1 - r11
            if (r12 == 0) goto L_0x0119
            boolean r3 = gnu.math.ExponentialFormat.addOne(r9, r0, r3)
            if (r3 == 0) goto L_0x0119
            int r1 = r11 + 1
            r11 = r1
            goto L_0x011a
        L_0x0119:
            r5 = r14
        L_0x011a:
            if (r5 != 0) goto L_0x012e
            int r3 = r8.width
            if (r3 <= 0) goto L_0x0124
            int r4 = r4 + r11
            int r4 = r4 + r6
            if (r4 >= r3) goto L_0x012e
        L_0x0124:
            int r1 = r1 + 1
            int r3 = r0 + r11
            r9.insert(r3, r13)
            r3 = r1
            r5 = 1
            goto L_0x012f
        L_0x012e:
            r3 = r1
        L_0x012f:
            int r0 = r0 + r3
            r9.setLength(r0)
            r0 = r16
            r1 = r19
            r2 = r20
            r4 = r11
            r6 = r7
            r7 = r10
            r0.format(r1, r2, r3, r4, r5, r6, r7)
        L_0x013f:
            return r9
        L_0x0140:
            java.lang.StringBuffer r0 = r9.append(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.FixedRealFormat.format(double, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        RealNum asRealNumOrNull = RealNum.asRealNumOrNull(obj);
        if (asRealNumOrNull == null) {
            if (obj instanceof Complex) {
                String obj2 = obj.toString();
                int length = this.width - obj2.length();
                while (true) {
                    length--;
                    if (length >= 0) {
                        stringBuffer.append(' ');
                    } else {
                        stringBuffer.append(obj2);
                        return stringBuffer;
                    }
                }
            } else {
                asRealNumOrNull = (RealNum) obj;
            }
        }
        return format(asRealNumOrNull.doubleValue(), stringBuffer, fieldPosition);
    }

    private void format(StringBuffer stringBuffer, FieldPosition fieldPosition, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int minimumIntegerDigits = getMinimumIntegerDigits();
        int i8 = (i3 < 0 || i3 <= minimumIntegerDigits) ? minimumIntegerDigits - i3 : 0;
        if (i3 + i8 <= 0 && ((i7 = this.width) <= 0 || i7 > i4 + 1 + i5)) {
            i8++;
        }
        int i9 = this.width - (((i2 + i5) + i8) + 1);
        while (true) {
            i8--;
            if (i8 < 0) {
                break;
            }
            stringBuffer.insert(i6 + i5, '0');
        }
        if (i9 >= 0) {
            if (this.internalPad && i5 > 0) {
                i6++;
            }
            while (true) {
                i9--;
                if (i9 < 0) {
                    break;
                }
                stringBuffer.insert(i6, this.padChar);
            }
        } else if (this.overflowChar != 0) {
            stringBuffer.setLength(i6);
            this.i = this.width;
            while (true) {
                int i10 = this.i - 1;
                this.i = i10;
                if (i10 >= 0) {
                    stringBuffer.append(this.overflowChar);
                } else {
                    return;
                }
            }
        }
        stringBuffer.insert(stringBuffer.length() - i4, '.');
    }

    public Number parse(String str, ParsePosition parsePosition) {
        throw new Error("RealFixedFormat.parse - not implemented");
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new Error("RealFixedFormat.parseObject - not implemented");
    }
}
