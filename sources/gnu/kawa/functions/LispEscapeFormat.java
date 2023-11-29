package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;

/* compiled from: LispFormat */
class LispEscapeFormat extends ReportFormat {
    public static final int ESCAPE_ALL = 242;
    public static final int ESCAPE_NORMAL = 241;
    public static final LispEscapeFormat alwaysTerminate = new LispEscapeFormat(0, -1073741824);
    boolean escapeAll;
    int param1;
    int param2;
    int param3;

    public LispEscapeFormat(int i, int i2) {
        this.param1 = i;
        this.param2 = i2;
        this.param3 = -1073741824;
    }

    public LispEscapeFormat(int i, int i2, int i3) {
        this.param1 = i;
        this.param2 = i2;
        this.param3 = i3;
    }

    static Numeric getParam(int i, Object[] objArr, int i2) {
        if (i == -1342177280) {
            return IntNum.make(objArr.length - i2);
        }
        if (i != -1610612736) {
            return IntNum.make(i);
        }
        Numeric numeric = objArr[i2];
        if (numeric instanceof Numeric) {
            return numeric;
        }
        if (numeric instanceof Number) {
            if ((numeric instanceof Float) || (numeric instanceof Double)) {
                return new DFloNum(numeric.doubleValue());
            }
            return IntNum.make(numeric.longValue());
        } else if (numeric instanceof Char) {
            return new IntNum(((Char) numeric).intValue());
        } else {
            if (numeric instanceof Character) {
                return new IntNum(((Character) numeric).charValue());
            }
            return new DFloNum(Double.NaN);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0051, code lost:
        if (r6.geq(r2) != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r7 == r6.length) goto L_0x0053;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int format(java.lang.Object[] r6, int r7, java.io.Writer r8, java.text.FieldPosition r9) throws java.io.IOException {
        /*
            r5 = this;
            int r8 = r5.param1
            r9 = 0
            r0 = -1073741824(0xffffffffc0000000, float:-2.0)
            r1 = 1
            if (r8 != r0) goto L_0x000e
            int r6 = r6.length
            if (r7 != r6) goto L_0x000c
            goto L_0x0053
        L_0x000c:
            r1 = 0
            goto L_0x0053
        L_0x000e:
            int r2 = r5.param2
            if (r2 != r0) goto L_0x0015
            if (r8 != 0) goto L_0x0015
            goto L_0x0053
        L_0x0015:
            gnu.math.Numeric r8 = getParam(r8, r6, r7)
            int r2 = r5.param1
            r3 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            if (r2 != r3) goto L_0x0021
            int r7 = r7 + 1
        L_0x0021:
            int r2 = r5.param2
            if (r2 != r0) goto L_0x002a
            boolean r1 = r8.isZero()
            goto L_0x0053
        L_0x002a:
            gnu.math.Numeric r2 = getParam(r2, r6, r7)
            int r4 = r5.param2
            if (r4 != r3) goto L_0x0034
            int r7 = r7 + 1
        L_0x0034:
            int r4 = r5.param3
            if (r4 != r0) goto L_0x003d
            boolean r1 = r8.equals(r2)
            goto L_0x0053
        L_0x003d:
            gnu.math.Numeric r6 = getParam(r4, r6, r7)
            int r0 = r5.param3
            if (r0 != r3) goto L_0x0047
            int r7 = r7 + 1
        L_0x0047:
            boolean r8 = r2.geq(r8)
            if (r8 == 0) goto L_0x000c
            boolean r6 = r6.geq(r2)
            if (r6 == 0) goto L_0x000c
        L_0x0053:
            if (r1 != 0) goto L_0x0056
            goto L_0x005f
        L_0x0056:
            boolean r6 = r5.escapeAll
            if (r6 == 0) goto L_0x005d
            r9 = 242(0xf2, float:3.39E-43)
            goto L_0x005f
        L_0x005d:
            r9 = 241(0xf1, float:3.38E-43)
        L_0x005f:
            int r6 = result(r9, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.LispEscapeFormat.format(java.lang.Object[], int, java.io.Writer, java.text.FieldPosition):int");
    }
}
