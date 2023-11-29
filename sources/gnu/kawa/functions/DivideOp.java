package gnu.kawa.functions;

import gnu.mapping.Procedure;
import net.lingala.zip4j.util.InternalZipConstants;

public class DivideOp extends ArithOp {
    public static final DivideOp $Sl = new DivideOp(InternalZipConstants.ZIP_FILE_SEPARATOR, 4);
    public static final DivideOp div;
    public static final DivideOp div0;
    public static final DivideOp idiv;
    public static final DivideOp mod;
    public static final DivideOp mod0;
    public static final DivideOp modulo;
    public static final DivideOp quotient;
    public static final DivideOp remainder;
    int rounding_mode;

    public int getRoundingMode() {
        return this.rounding_mode;
    }

    static {
        DivideOp divideOp = new DivideOp("idiv", 7);
        idiv = divideOp;
        DivideOp divideOp2 = new DivideOp("quotient", 6);
        quotient = divideOp2;
        DivideOp divideOp3 = new DivideOp("remainder", 8);
        remainder = divideOp3;
        DivideOp divideOp4 = new DivideOp("modulo", 8);
        modulo = divideOp4;
        DivideOp divideOp5 = new DivideOp("div", 6);
        div = divideOp5;
        DivideOp divideOp6 = new DivideOp("mod", 8);
        mod = divideOp6;
        DivideOp divideOp7 = new DivideOp("div0", 6);
        div0 = divideOp7;
        DivideOp divideOp8 = new DivideOp("mod0", 8);
        mod0 = divideOp8;
        divideOp.rounding_mode = 3;
        divideOp2.rounding_mode = 3;
        divideOp3.rounding_mode = 3;
        divideOp4.rounding_mode = 1;
        divideOp5.rounding_mode = 5;
        divideOp6.rounding_mode = 5;
        divideOp7.rounding_mode = 4;
        divideOp8.rounding_mode = 4;
    }

    public DivideOp(String str, int i) {
        super(str, i);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forDiv");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        if (r6 == 2) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c0, code lost:
        r15 = ((gnu.math.RealNum) r15).toInt(r0.rounding_mode);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00db, code lost:
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0115, code lost:
        r15 = r4;
        r6 = r12;
     */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object applyN(java.lang.Object[] r19) throws java.lang.Throwable {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            int r2 = r1.length
            if (r2 != 0) goto L_0x000c
            gnu.math.IntNum r1 = gnu.math.IntNum.one()
            return r1
        L_0x000c:
            r3 = 0
            r4 = r1[r3]
            java.lang.Number r4 = (java.lang.Number) r4
            r5 = 1
            if (r2 != r5) goto L_0x001d
            gnu.math.IntNum r1 = gnu.math.IntNum.one()
            java.lang.Object r1 = r0.apply2(r1, r4)
            return r1
        L_0x001d:
            int r6 = gnu.kawa.functions.Arithmetic.classifyValue(r4)
            r7 = 1
        L_0x0022:
            if (r7 >= r2) goto L_0x0229
            r8 = r1[r7]
            int r9 = gnu.kawa.functions.Arithmetic.classifyValue(r8)
            if (r6 >= r9) goto L_0x002d
            r6 = r9
        L_0x002d:
            r9 = 2
            r10 = 3
            r11 = 5
            r12 = 4
            if (r6 >= r12) goto L_0x0045
            int r13 = r0.op
            if (r13 == r12) goto L_0x0042
            if (r13 == r11) goto L_0x0042
            int r13 = r0.rounding_mode
            if (r13 != r10) goto L_0x0043
            if (r6 == r5) goto L_0x0045
            if (r6 != r9) goto L_0x0043
            goto L_0x0045
        L_0x0042:
            r6 = 4
        L_0x0043:
            r13 = 4
            goto L_0x0046
        L_0x0045:
            r13 = r6
        L_0x0046:
            int r14 = r0.op
            r15 = 10
            r10 = 7
            r3 = 8
            if (r14 != r11) goto L_0x005a
            if (r6 > r15) goto L_0x005a
            if (r6 == r3) goto L_0x0057
            if (r6 == r10) goto L_0x0057
            r6 = 9
        L_0x0057:
            r13 = 10
            goto L_0x0066
        L_0x005a:
            if (r13 == r3) goto L_0x005e
            if (r13 != r10) goto L_0x0066
        L_0x005e:
            int r13 = r0.op
            if (r13 != r10) goto L_0x0064
            r6 = 9
        L_0x0064:
            r13 = 9
        L_0x0066:
            if (r13 == r5) goto L_0x01d2
            if (r13 == r9) goto L_0x01bb
            r14 = 6
            if (r13 == r12) goto L_0x0174
            if (r13 == r11) goto L_0x011a
            r15 = 9
            if (r13 == r15) goto L_0x00ce
            gnu.math.Numeric r4 = gnu.kawa.functions.Arithmetic.asNumeric(r4)
            gnu.math.Numeric r8 = gnu.kawa.functions.Arithmetic.asNumeric(r8)
            int r15 = r0.op
            if (r15 != r3) goto L_0x0091
            boolean r15 = r8.isZero()
            if (r15 == 0) goto L_0x0091
            boolean r1 = r8.isExact()
            if (r1 == 0) goto L_0x008c
            goto L_0x0090
        L_0x008c:
            gnu.math.Numeric r4 = r4.toInexact()
        L_0x0090:
            return r4
        L_0x0091:
            gnu.math.Numeric r15 = r4.div(r8)
            int r12 = r0.op
            if (r12 != r3) goto L_0x00ab
            gnu.math.RealNum r15 = (gnu.math.RealNum) r15
            int r12 = r18.getRoundingMode()
            gnu.math.RealNum r12 = r15.toInt(r12)
            gnu.math.Numeric r8 = r12.mul(r8)
            gnu.math.Numeric r15 = r4.sub(r8)
        L_0x00ab:
            int r4 = r0.op
            if (r4 == r11) goto L_0x00c9
            if (r4 == r14) goto L_0x00c0
            if (r4 == r10) goto L_0x00b4
            goto L_0x0117
        L_0x00b4:
            gnu.math.RealNum r15 = (gnu.math.RealNum) r15
            int r4 = r0.rounding_mode
            gnu.math.IntNum r15 = r15.toExactInt((int) r4)
            r6 = 4
            r12 = 4
            goto L_0x01e9
        L_0x00c0:
            gnu.math.RealNum r15 = (gnu.math.RealNum) r15
            int r4 = r0.rounding_mode
            gnu.math.RealNum r15 = r15.toInt(r4)
            goto L_0x0117
        L_0x00c9:
            gnu.math.Numeric r15 = r15.toInexact()
            goto L_0x0117
        L_0x00ce:
            double r11 = gnu.kawa.functions.Arithmetic.asDouble(r4)
            double r14 = gnu.kawa.functions.Arithmetic.asDouble(r8)
            int r8 = r0.op
            switch(r8) {
                case 4: goto L_0x010f;
                case 5: goto L_0x010f;
                case 6: goto L_0x0101;
                case 7: goto L_0x00f5;
                case 8: goto L_0x00dd;
                default: goto L_0x00db;
            }
        L_0x00db:
            r12 = r6
            goto L_0x0115
        L_0x00dd:
            r16 = 0
            int r4 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x00f0
            double r3 = r11 / r14
            int r8 = r18.getRoundingMode()
            double r3 = gnu.math.RealNum.toInt(r3, r8)
            double r3 = r3 * r14
            double r11 = r11 - r3
        L_0x00f0:
            gnu.math.DFloNum r4 = gnu.math.DFloNum.make(r11)
            goto L_0x00db
        L_0x00f5:
            double r11 = r11 / r14
            int r3 = r18.getRoundingMode()
            gnu.math.IntNum r4 = gnu.math.RealNum.toExactInt(r11, r3)
            r12 = 4
            r13 = 4
            goto L_0x0115
        L_0x0101:
            double r11 = r11 / r14
            int r3 = r18.getRoundingMode()
            double r3 = gnu.math.RealNum.toInt(r11, r3)
            java.lang.Double r4 = java.lang.Double.valueOf(r3)
            goto L_0x00db
        L_0x010f:
            double r11 = r11 / r14
            gnu.math.DFloNum r4 = gnu.math.DFloNum.make(r11)
            goto L_0x00db
        L_0x0115:
            r15 = r4
            r6 = r12
        L_0x0117:
            r12 = r13
            goto L_0x01e9
        L_0x011a:
            java.math.BigDecimal r3 = gnu.kawa.functions.Arithmetic.asBigDecimal(r4)
            java.math.BigDecimal r8 = gnu.kawa.functions.Arithmetic.asBigDecimal(r8)
            int r12 = r18.getRoundingMode()
            if (r12 == r5) goto L_0x0144
            if (r12 == r9) goto L_0x0141
            r15 = 3
            if (r12 == r15) goto L_0x013e
            if (r12 == r11) goto L_0x0130
            goto L_0x013b
        L_0x0130:
            int r11 = r8.signum()
            if (r11 >= 0) goto L_0x0139
            java.math.RoundingMode r11 = java.math.RoundingMode.CEILING
            goto L_0x013b
        L_0x0139:
            java.math.RoundingMode r11 = java.math.RoundingMode.FLOOR
        L_0x013b:
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_EVEN
            goto L_0x0146
        L_0x013e:
            java.math.RoundingMode r11 = java.math.RoundingMode.DOWN
            goto L_0x0146
        L_0x0141:
            java.math.RoundingMode r11 = java.math.RoundingMode.CEILING
            goto L_0x0146
        L_0x0144:
            java.math.RoundingMode r11 = java.math.RoundingMode.FLOOR
        L_0x0146:
            java.math.MathContext r12 = new java.math.MathContext
            r15 = 0
            r12.<init>(r15, r11)
            int r11 = r0.op
            r15 = 4
            if (r11 == r15) goto L_0x016f
            if (r11 == r14) goto L_0x016a
            if (r11 == r10) goto L_0x015f
            r14 = 8
            if (r11 == r14) goto L_0x015a
        L_0x0159:
            goto L_0x0181
        L_0x015a:
            java.math.BigDecimal r4 = r3.remainder(r8, r12)
            goto L_0x0159
        L_0x015f:
            java.math.BigDecimal r3 = r3.divideToIntegralValue(r8, r12)
            java.math.BigInteger r4 = r3.toBigInteger()
            r6 = 3
            r12 = 3
            goto L_0x01b9
        L_0x016a:
            java.math.BigDecimal r4 = r3.divideToIntegralValue(r8, r12)
            goto L_0x0159
        L_0x016f:
            java.math.BigDecimal r4 = r3.divide(r8)
            goto L_0x0159
        L_0x0174:
            int r3 = r0.op
            r11 = 4
            if (r3 == r11) goto L_0x01a5
            if (r3 == r14) goto L_0x0194
            if (r3 == r10) goto L_0x0194
            r11 = 8
            if (r3 == r11) goto L_0x0183
        L_0x0181:
            r12 = r13
            goto L_0x01b9
        L_0x0183:
            gnu.math.IntNum r3 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r4)
            gnu.math.IntNum r4 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r8)
            int r8 = r18.getRoundingMode()
            gnu.math.IntNum r4 = gnu.math.IntNum.remainder(r3, r4, r8)
            goto L_0x0181
        L_0x0194:
            gnu.math.IntNum r3 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r4)
            gnu.math.IntNum r4 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r8)
            int r8 = r18.getRoundingMode()
            gnu.math.IntNum r4 = gnu.math.IntNum.quotient(r3, r4, r8)
            goto L_0x0181
        L_0x01a5:
            gnu.math.IntNum r3 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r4)
            gnu.math.IntNum r4 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r8)
            gnu.math.RatNum r4 = gnu.math.RatNum.make(r3, r4)
            boolean r3 = r4 instanceof gnu.math.IntNum
            if (r3 == 0) goto L_0x01b7
            r12 = 4
            goto L_0x01b8
        L_0x01b7:
            r12 = 6
        L_0x01b8:
            r6 = r12
        L_0x01b9:
            r15 = r4
            goto L_0x01e9
        L_0x01bb:
            long r3 = gnu.kawa.functions.Arithmetic.asLong(r4)
            long r11 = gnu.kawa.functions.Arithmetic.asLong(r8)
            int r8 = r0.op
            r14 = 8
            if (r8 == r14) goto L_0x01cb
            long r3 = r3 / r11
            goto L_0x01cc
        L_0x01cb:
            long r3 = r3 % r11
        L_0x01cc:
            java.lang.Long r15 = java.lang.Long.valueOf(r3)
            goto L_0x0117
        L_0x01d2:
            r14 = 8
            int r3 = gnu.kawa.functions.Arithmetic.asInt(r4)
            int r4 = gnu.kawa.functions.Arithmetic.asInt(r8)
            int r8 = r0.op
            if (r8 == r14) goto L_0x01e2
            int r3 = r3 / r4
            goto L_0x01e3
        L_0x01e2:
            int r3 = r3 % r4
        L_0x01e3:
            java.lang.Integer r15 = java.lang.Integer.valueOf(r3)
            goto L_0x0117
        L_0x01e9:
            if (r6 == r12) goto L_0x0223
            if (r6 == r5) goto L_0x0219
            if (r6 == r9) goto L_0x0210
            r3 = 3
            if (r6 == r3) goto L_0x020b
            if (r6 == r10) goto L_0x0202
            r3 = 8
            if (r6 == r3) goto L_0x01f9
            goto L_0x0223
        L_0x01f9:
            double r3 = r15.doubleValue()
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x0221
        L_0x0202:
            float r3 = r15.floatValue()
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0221
        L_0x020b:
            java.math.BigInteger r3 = gnu.kawa.functions.Arithmetic.asBigInteger(r15)
            goto L_0x0221
        L_0x0210:
            long r3 = r15.longValue()
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x0221
        L_0x0219:
            int r3 = r15.intValue()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0221:
            r4 = r3
            goto L_0x0224
        L_0x0223:
            r4 = r15
        L_0x0224:
            int r7 = r7 + 1
            r3 = 0
            goto L_0x0022
        L_0x0229:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.DivideOp.applyN(java.lang.Object[]):java.lang.Object");
    }

    public int numArgs() {
        return this.op == 4 ? -4095 : 8194;
    }
}
