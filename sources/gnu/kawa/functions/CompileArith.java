package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class CompileArith implements Inlineable {
    public static CompileArith $Mn = new CompileArith(AddOp.$Mn, 2);
    public static CompileArith $Pl = new CompileArith(AddOp.$Pl, 1);
    int op;
    Procedure proc;

    static int adjustReturnKind(int i, int i2) {
        if (i2 < 4 || i2 > 7 || i <= 0) {
            return i;
        }
        if (i2 != 4) {
            if (i2 != 5) {
                if (i2 == 7 && i <= 10) {
                    return 4;
                }
                return i;
            } else if (i > 10 || i == 7) {
                return i;
            } else {
                return 8;
            }
        } else if (i <= 4) {
            return 6;
        } else {
            return i;
        }
    }

    public static int getReturnKind(int i, int i2, int i3) {
        return ((i3 < 9 || i3 > 12) && i > 0) ? (i <= i2 || i2 <= 0) ? i2 : i : i;
    }

    CompileArith(Object obj, int i) {
        this.proc = (Procedure) obj;
        this.op = i;
    }

    public static CompileArith forMul(Object obj) {
        return new CompileArith(obj, 3);
    }

    public static CompileArith forDiv(Object obj) {
        return new CompileArith(obj, ((DivideOp) obj).op);
    }

    public static CompileArith forBitwise(Object obj) {
        return new CompileArith(obj, ((BitwiseOp) obj).op);
    }

    public static boolean appropriateIntConstant(Expression[] expressionArr, int i, InlineCalls inlineCalls) {
        QuoteExp fixIntValue = inlineCalls.fixIntValue(expressionArr[i]);
        if (fixIntValue == null) {
            return false;
        }
        expressionArr[i] = fixIntValue;
        return true;
    }

    public static boolean appropriateLongConstant(Expression[] expressionArr, int i, InlineCalls inlineCalls) {
        QuoteExp fixLongValue = inlineCalls.fixLongValue(expressionArr[i]);
        if (fixLongValue == null) {
            return false;
        }
        expressionArr[i] = fixLongValue;
        return true;
    }

    public static Expression validateApplyArithOp(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        int i = ((ArithOp) procedure).op;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length > 2) {
            return pairwise(procedure, applyExp.getFunction(), args, inlineCalls);
        }
        Expression inlineIfConstant = applyExp.inlineIfConstant(procedure, inlineCalls);
        if (inlineIfConstant != applyExp) {
            return inlineIfConstant;
        }
        int i2 = 0;
        if (args.length == 2 || args.length == 1) {
            int classifyType = Arithmetic.classifyType(args[0].getType());
            if (args.length == 2 && (i < 9 || i > 12)) {
                int classifyType2 = Arithmetic.classifyType(args[1].getType());
                int returnKind = getReturnKind(classifyType, classifyType2, i);
                if (returnKind == 4) {
                    if ((classifyType == 1 && appropriateIntConstant(args, 1, inlineCalls)) || (classifyType2 == 1 && appropriateIntConstant(args, 0, inlineCalls))) {
                        classifyType = 1;
                    } else if ((classifyType == 2 && appropriateLongConstant(args, 1, inlineCalls)) || (classifyType2 == 2 && appropriateLongConstant(args, 0, inlineCalls))) {
                        classifyType = 2;
                    }
                }
                classifyType = returnKind;
            }
            i2 = adjustReturnKind(classifyType, i);
            applyExp.setType(Arithmetic.kindType(i2));
        }
        if (!inlineCalls.getCompilation().mustCompile) {
            return applyExp;
        }
        if (i == 1 || i == 2) {
            return validateApplyAdd((AddOp) procedure, applyExp, inlineCalls);
        }
        if (i != 16) {
            switch (i) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return validateApplyDiv((DivideOp) procedure, applyExp, inlineCalls);
                default:
                    return applyExp;
            }
        } else if (i2 > 0) {
            return validateApplyNot(applyExp, i2, inlineCalls);
        } else {
            return applyExp;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01a3, code lost:
        if (r9 != 8) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cd, code lost:
        if (r9 != 8) goto L_0x00d0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.ApplyExp r19, gnu.expr.Compilation r20, gnu.expr.Target r21) {
        /*
            r18 = this;
            r6 = r18
            r7 = r20
            r8 = r21
            gnu.expr.Expression[] r0 = r19.getArgs()
            int r1 = r0.length
            if (r1 != 0) goto L_0x0019
            gnu.mapping.Procedure r0 = r6.proc
            gnu.kawa.functions.ArithOp r0 = (gnu.kawa.functions.ArithOp) r0
            java.lang.Object r0 = r0.defaultResult()
            r7.compileConstant(r0, r8)
            return
        L_0x0019:
            r2 = 1
            if (r1 == r2) goto L_0x01ec
            boolean r3 = r8 instanceof gnu.expr.IgnoreTarget
            if (r3 == 0) goto L_0x0022
            goto L_0x01ec
        L_0x0022:
            r3 = 0
            r4 = r0[r3]
            gnu.bytecode.Type r4 = r4.getType()
            int r4 = gnu.kawa.functions.Arithmetic.classifyType(r4)
            r5 = r0[r2]
            gnu.bytecode.Type r5 = r5.getType()
            int r5 = gnu.kawa.functions.Arithmetic.classifyType(r5)
            int r9 = r6.op
            int r9 = getReturnKind(r4, r5, r9)
            gnu.bytecode.Type r10 = gnu.kawa.functions.Arithmetic.kindType(r9)
            if (r9 == 0) goto L_0x01e8
            r11 = 2
            if (r1 == r11) goto L_0x0048
            goto L_0x01e8
        L_0x0048:
            gnu.bytecode.Type r12 = r21.getType()
            int r12 = gnu.kawa.functions.Arithmetic.classifyType(r12)
            r13 = 10
            r15 = 7
            r3 = 4
            r14 = 8
            if (r12 == r2) goto L_0x005a
            if (r12 != r11) goto L_0x0068
        L_0x005a:
            if (r9 < r2) goto L_0x0068
            if (r9 > r3) goto L_0x0068
            if (r12 != r2) goto L_0x0063
            gnu.bytecode.PrimType r9 = gnu.kawa.lispexpr.LangPrimType.intType
            goto L_0x0065
        L_0x0063:
            gnu.bytecode.PrimType r9 = gnu.kawa.lispexpr.LangPrimType.longType
        L_0x0065:
            r10 = r9
            r9 = r12
            goto L_0x0087
        L_0x0068:
            if (r12 == r14) goto L_0x006c
            if (r12 != r15) goto L_0x0078
        L_0x006c:
            if (r9 <= r11) goto L_0x0078
            if (r9 > r13) goto L_0x0078
            if (r12 != r15) goto L_0x0075
            gnu.bytecode.PrimType r9 = gnu.kawa.lispexpr.LangPrimType.floatType
            goto L_0x0065
        L_0x0075:
            gnu.bytecode.PrimType r9 = gnu.kawa.lispexpr.LangPrimType.doubleType
            goto L_0x0065
        L_0x0078:
            if (r9 != r15) goto L_0x007d
            gnu.bytecode.PrimType r10 = gnu.kawa.lispexpr.LangPrimType.floatType
            goto L_0x0087
        L_0x007d:
            if (r9 == r14) goto L_0x0083
            r12 = 9
            if (r9 != r12) goto L_0x0087
        L_0x0083:
            gnu.bytecode.PrimType r10 = gnu.kawa.lispexpr.LangPrimType.doubleType
            r9 = 8
        L_0x0087:
            int r12 = r6.op
            r11 = 6
            if (r12 < r3) goto L_0x00e1
            if (r12 > r14) goto L_0x00e1
            gnu.mapping.Procedure r12 = r6.proc
            gnu.kawa.functions.DivideOp r12 = (gnu.kawa.functions.DivideOp) r12
            int r14 = r12.op
            if (r14 != r3) goto L_0x009f
            if (r9 <= r3) goto L_0x00e1
            if (r9 >= r11) goto L_0x00e1
            r14 = 9
            if (r9 > r14) goto L_0x009f
            goto L_0x00e1
        L_0x009f:
            int r14 = r12.op
            r2 = 5
            if (r14 != r2) goto L_0x00a8
            if (r9 > r13) goto L_0x00a8
            if (r9 != r15) goto L_0x00ae
        L_0x00a8:
            int r2 = r12.op
            if (r2 != r3) goto L_0x00b1
            if (r9 != r13) goto L_0x00b1
        L_0x00ae:
            r9 = 8
            goto L_0x00e1
        L_0x00b1:
            int r2 = r12.op
            if (r2 == r15) goto L_0x00c0
            int r2 = r12.op
            if (r2 != r11) goto L_0x00bc
            if (r9 > r3) goto L_0x00bc
            goto L_0x00c0
        L_0x00bc:
            r2 = 8
            r14 = 3
            goto L_0x00d0
        L_0x00c0:
            int r2 = r12.getRoundingMode()
            r14 = 3
            if (r2 == r14) goto L_0x00e1
            if (r9 == r3) goto L_0x00e1
            if (r9 == r15) goto L_0x00e1
            r2 = 8
            if (r9 != r2) goto L_0x00d0
            goto L_0x00e1
        L_0x00d0:
            int r11 = r12.op
            if (r11 != r2) goto L_0x00dd
            int r2 = r12.getRoundingMode()
            if (r2 == r14) goto L_0x00e1
            if (r9 != r3) goto L_0x00dd
            goto L_0x00e1
        L_0x00dd:
            gnu.expr.ApplyExp.compile(r19, r20, r21)
            return
        L_0x00e1:
            int r2 = r6.op
            if (r2 != r3) goto L_0x0128
            if (r9 > r13) goto L_0x0128
            r11 = 8
            if (r9 == r11) goto L_0x0128
            if (r9 == r15) goto L_0x0128
            r11 = 6
            if (r9 == r11) goto L_0x0100
            if (r9 <= r3) goto L_0x00f3
            goto L_0x0100
        L_0x00f3:
            gnu.kawa.lispexpr.LangObjType r1 = gnu.kawa.functions.Arithmetic.typeIntNum
            gnu.kawa.lispexpr.LangObjType r2 = gnu.kawa.functions.Arithmetic.typeRatNum
            java.lang.String r3 = "make"
            r4 = 2
            gnu.bytecode.Method r2 = r2.getDeclaredMethod(r3, r4)
        L_0x00fe:
            r10 = r1
            goto L_0x010f
        L_0x0100:
            r4 = 2
            if (r9 != r11) goto L_0x0106
            gnu.kawa.lispexpr.LangObjType r1 = gnu.kawa.functions.Arithmetic.typeRatNum
            goto L_0x0108
        L_0x0106:
            gnu.kawa.lispexpr.LangObjType r1 = gnu.kawa.functions.Arithmetic.typeRealNum
        L_0x0108:
            java.lang.String r2 = "divide"
            gnu.bytecode.Method r2 = r1.getDeclaredMethod(r2, r4)
            goto L_0x00fe
        L_0x010f:
            gnu.expr.Target r1 = gnu.expr.StackTarget.getInstance(r10)
            r3 = 0
            r3 = r0[r3]
            r3.compile((gnu.expr.Compilation) r7, (gnu.expr.Target) r1)
            r11 = 1
            r0 = r0[r11]
            r0.compile((gnu.expr.Compilation) r7, (gnu.expr.Target) r1)
            gnu.bytecode.CodeAttr r0 = r20.getCode()
            r0.emitInvokeStatic(r2)
            goto L_0x01e4
        L_0x0128:
            r11 = 1
            r12 = 13
            if (r9 != r3) goto L_0x015e
            if (r2 == r11) goto L_0x014d
            r3 = 3
            if (r2 == r3) goto L_0x014d
            r3 = 2
            if (r2 == r3) goto L_0x014d
            if (r2 == r12) goto L_0x014d
            r3 = 14
            if (r2 == r3) goto L_0x014d
            r3 = 15
            if (r2 == r3) goto L_0x014d
            if (r2 == r15) goto L_0x014d
            r3 = 8
            if (r2 == r3) goto L_0x014d
            r3 = 9
            if (r2 < r3) goto L_0x015e
            r3 = 11
            if (r2 > r3) goto L_0x015e
        L_0x014d:
            r1 = 0
            r1 = r0[r1]
            r3 = 1
            r2 = r0[r3]
            r0 = r18
            r3 = r4
            r4 = r5
            r5 = r20
            r0.compileIntNum(r1, r2, r3, r4, r5)
            goto L_0x01e4
        L_0x015e:
            r3 = 1
            if (r9 == r3) goto L_0x0173
            r3 = 2
            if (r9 == r3) goto L_0x0173
            r3 = 8
            if (r9 == r15) goto L_0x016a
            if (r9 != r3) goto L_0x016f
        L_0x016a:
            if (r2 <= r3) goto L_0x0173
            if (r2 < r12) goto L_0x016f
            goto L_0x0173
        L_0x016f:
            gnu.expr.ApplyExp.compile(r19, r20, r21)
            return
        L_0x0173:
            gnu.expr.Target r2 = gnu.expr.StackTarget.getInstance(r10)
            gnu.bytecode.CodeAttr r3 = r20.getCode()
            r4 = 0
        L_0x017c:
            if (r4 >= r1) goto L_0x01e4
            r5 = 1
            if (r4 != r5) goto L_0x0191
            int r5 = r6.op
            r11 = 9
            if (r5 < r11) goto L_0x0191
            r11 = 12
            if (r5 > r11) goto L_0x0191
            gnu.bytecode.PrimType r2 = gnu.bytecode.Type.intType
            gnu.expr.Target r2 = gnu.expr.StackTarget.getInstance(r2)
        L_0x0191:
            r5 = r0[r4]
            r5.compile((gnu.expr.Compilation) r7, (gnu.expr.Target) r2)
            if (r4 != 0) goto L_0x0199
            goto L_0x01a5
        L_0x0199:
            r5 = 1
            if (r9 == r5) goto L_0x01ae
            r5 = 2
            if (r9 == r5) goto L_0x01ab
            if (r9 == r15) goto L_0x01ab
            r11 = 8
            if (r9 == r11) goto L_0x01b0
        L_0x01a5:
            r13 = 9
            r14 = 0
            r17 = 1
            goto L_0x01e1
        L_0x01ab:
            r11 = 8
            goto L_0x01b0
        L_0x01ae:
            r5 = 2
            goto L_0x01ab
        L_0x01b0:
            int r12 = r6.op
            r13 = 9
            if (r12 != r13) goto L_0x01d1
            gnu.bytecode.Type[] r12 = new gnu.bytecode.Type[r5]
            r14 = 0
            r12[r14] = r10
            gnu.bytecode.PrimType r16 = gnu.bytecode.Type.intType
            r17 = 1
            r12[r17] = r16
            java.lang.String r16 = "gnu.math.IntNum"
            gnu.bytecode.ClassType r5 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r11 = "shift"
            gnu.bytecode.Method r5 = r5.getDeclaredMethod((java.lang.String) r11, (gnu.bytecode.Type[]) r12)
            r3.emitInvokeStatic(r5)
            goto L_0x01e1
        L_0x01d1:
            r14 = 0
            r17 = 1
            int r5 = r18.primitiveOpcode()
            gnu.bytecode.Type r11 = r10.getImplementationType()
            gnu.bytecode.PrimType r11 = (gnu.bytecode.PrimType) r11
            r3.emitBinop((int) r5, (gnu.bytecode.Type) r11)
        L_0x01e1:
            int r4 = r4 + 1
            goto L_0x017c
        L_0x01e4:
            r8.compileFromStack(r7, r10)
            return
        L_0x01e8:
            gnu.expr.ApplyExp.compile(r19, r20, r21)
            return
        L_0x01ec:
            gnu.expr.ApplyExp.compile(r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ed, code lost:
        if (r0 != null) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ef, code lost:
        r0 = "ior";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f1, code lost:
        if (r0 != null) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f3, code lost:
        r0 = "xor";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00f5, code lost:
        r5 = gnu.bytecode.ClassType.make("gnu.math.BitOps");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0146, code lost:
        if (r10 != null) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0148, code lost:
        r10 = new gnu.bytecode.Type[]{r8, r9};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x014e, code lost:
        r1.emitInvokeStatic(r5.getMethod(r0, r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0155, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean compileIntNum(gnu.expr.Expression r16, gnu.expr.Expression r17, int r18, int r19, gnu.expr.Compilation r20) {
        /*
            r15 = this;
            r6 = r15
            r1 = r17
            r5 = r20
            int r0 = r6.op
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 != r2) goto L_0x005d
            boolean r0 = r1 instanceof gnu.expr.QuoteExp
            if (r0 == 0) goto L_0x005d
            java.lang.Object r0 = r17.valueIfConstant()
            r7 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r9 = r19
            if (r9 > r2) goto L_0x002e
            java.lang.Number r0 = (java.lang.Number) r0
            long r10 = r0.longValue()
            r12 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 <= 0) goto L_0x0042
            int r0 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x002e:
            boolean r10 = r0 instanceof gnu.math.IntNum
            if (r10 == 0) goto L_0x0040
            gnu.math.IntNum r0 = (gnu.math.IntNum) r0
            long r10 = r0.longValue()
            r12 = -2147483647(0xffffffff80000001, double:NaN)
            boolean r0 = r0.inRange(r12, r7)
            goto L_0x0043
        L_0x0040:
            r10 = 0
        L_0x0042:
            r0 = 0
        L_0x0043:
            if (r0 == 0) goto L_0x005f
            gnu.kawa.functions.CompileArith r0 = $Pl
            long r1 = -r10
            int r2 = (int) r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            gnu.expr.QuoteExp r2 = gnu.expr.QuoteExp.getInstance(r1)
            r4 = 1
            r1 = r16
            r3 = r18
            r5 = r20
            boolean r0 = r0.compileIntNum(r1, r2, r3, r4, r5)
            return r0
        L_0x005d:
            r9 = r19
        L_0x005f:
            int r0 = r6.op
            r7 = 3
            if (r0 == r4) goto L_0x0069
            if (r0 != r7) goto L_0x0067
            goto L_0x0069
        L_0x0067:
            r8 = 0
            goto L_0x006a
        L_0x0069:
            r8 = 1
        L_0x006a:
            if (r8 == 0) goto L_0x00b1
            java.lang.Integer r0 = gnu.expr.InlineCalls.checkIntValue(r16)
            if (r0 == 0) goto L_0x0074
            r8 = 1
            goto L_0x0076
        L_0x0074:
            r8 = r18
        L_0x0076:
            java.lang.Integer r0 = gnu.expr.InlineCalls.checkIntValue(r17)
            if (r0 == 0) goto L_0x007d
            r9 = 1
        L_0x007d:
            if (r8 != r4) goto L_0x0083
            if (r9 == r4) goto L_0x0083
            r0 = 1
            goto L_0x0084
        L_0x0083:
            r0 = 0
        L_0x0084:
            if (r0 == 0) goto L_0x00a0
            boolean r10 = r16.side_effects()
            if (r10 == 0) goto L_0x0092
            boolean r10 = r17.side_effects()
            if (r10 != 0) goto L_0x00a0
        L_0x0092:
            r0 = r15
            r1 = r17
            r2 = r16
            r3 = r9
            r4 = r8
            r5 = r20
            boolean r0 = r0.compileIntNum(r1, r2, r3, r4, r5)
            return r0
        L_0x00a0:
            if (r8 != r4) goto L_0x00a5
            gnu.bytecode.PrimType r8 = gnu.bytecode.Type.intType
            goto L_0x00a7
        L_0x00a5:
            gnu.kawa.lispexpr.LangObjType r8 = gnu.kawa.functions.Arithmetic.typeIntNum
        L_0x00a7:
            if (r9 != r4) goto L_0x00ac
            gnu.bytecode.PrimType r9 = gnu.bytecode.Type.intType
            goto L_0x00ae
        L_0x00ac:
            gnu.kawa.lispexpr.LangObjType r9 = gnu.kawa.functions.Arithmetic.typeIntNum
        L_0x00ae:
            r10 = r16
            goto L_0x00c6
        L_0x00b1:
            r8 = 9
            if (r0 < r8) goto L_0x00c0
            r8 = 12
            if (r0 > r8) goto L_0x00c0
            gnu.kawa.lispexpr.LangObjType r8 = gnu.kawa.functions.Arithmetic.typeIntNum
            gnu.bytecode.PrimType r9 = gnu.bytecode.Type.intType
            r10 = r16
            goto L_0x00c5
        L_0x00c0:
            gnu.kawa.lispexpr.LangObjType r8 = gnu.kawa.functions.Arithmetic.typeIntNum
            r10 = r16
            r9 = r8
        L_0x00c5:
            r0 = 0
        L_0x00c6:
            r10.compile((gnu.expr.Compilation) r5, (gnu.bytecode.Type) r8)
            r1.compile((gnu.expr.Compilation) r5, (gnu.bytecode.Type) r9)
            gnu.bytecode.CodeAttr r1 = r20.getCode()
            if (r0 == 0) goto L_0x00d9
            r1.emitSwap()
            gnu.kawa.lispexpr.LangObjType r8 = gnu.kawa.functions.Arithmetic.typeIntNum
            gnu.bytecode.PrimType r9 = gnu.kawa.lispexpr.LangPrimType.intType
        L_0x00d9:
            gnu.kawa.lispexpr.LangObjType r0 = gnu.kawa.functions.Arithmetic.typeIntNum
            int r5 = r6.op
            r10 = 0
            switch(r5) {
                case 1: goto L_0x0141;
                case 2: goto L_0x013e;
                case 3: goto L_0x013b;
                case 4: goto L_0x010f;
                case 5: goto L_0x010f;
                case 6: goto L_0x010f;
                case 7: goto L_0x010f;
                case 8: goto L_0x010f;
                case 9: goto L_0x010c;
                case 10: goto L_0x00fc;
                case 11: goto L_0x00fc;
                case 12: goto L_0x00e1;
                case 13: goto L_0x00eb;
                case 14: goto L_0x00e9;
                case 15: goto L_0x00e7;
                default: goto L_0x00e1;
            }
        L_0x00e1:
            java.lang.Error r0 = new java.lang.Error
            r0.<init>()
            throw r0
        L_0x00e7:
            r0 = r10
            goto L_0x00f1
        L_0x00e9:
            r0 = r10
            goto L_0x00ed
        L_0x00eb:
            java.lang.String r0 = "and"
        L_0x00ed:
            if (r0 != 0) goto L_0x00f1
            java.lang.String r0 = "ior"
        L_0x00f1:
            if (r0 != 0) goto L_0x00f5
            java.lang.String r0 = "xor"
        L_0x00f5:
            java.lang.String r5 = "gnu.math.BitOps"
            gnu.bytecode.ClassType r5 = gnu.bytecode.ClassType.make(r5)
            goto L_0x0146
        L_0x00fc:
            r0 = 10
            if (r5 != r0) goto L_0x0103
            java.lang.String r0 = "shiftLeft"
            goto L_0x0105
        L_0x0103:
            java.lang.String r0 = "shiftRight"
        L_0x0105:
            java.lang.String r5 = "gnu.kawa.functions.BitwiseOp"
            gnu.bytecode.ClassType r5 = gnu.bytecode.ClassType.make(r5)
            goto L_0x0146
        L_0x010c:
            java.lang.String r5 = "shift"
            goto L_0x0143
        L_0x010f:
            r11 = 8
            if (r5 != r11) goto L_0x0116
            java.lang.String r12 = "remainder"
            goto L_0x0118
        L_0x0116:
            java.lang.String r12 = "quotient"
        L_0x0118:
            gnu.mapping.Procedure r13 = r6.proc
            gnu.kawa.functions.DivideOp r13 = (gnu.kawa.functions.DivideOp) r13
            if (r5 != r11) goto L_0x0125
            int r5 = r13.rounding_mode
            if (r5 != r4) goto L_0x0125
            java.lang.String r5 = "modulo"
            goto L_0x0143
        L_0x0125:
            int r5 = r13.rounding_mode
            if (r5 == r7) goto L_0x0138
            int r5 = r13.rounding_mode
            r1.emitPushInt(r5)
            gnu.bytecode.Type[] r10 = new gnu.bytecode.Type[r7]
            r10[r3] = r8
            r10[r4] = r9
            gnu.bytecode.PrimType r5 = gnu.bytecode.Type.intType
            r10[r2] = r5
        L_0x0138:
            r5 = r0
            r0 = r12
            goto L_0x0146
        L_0x013b:
            java.lang.String r5 = "times"
            goto L_0x0143
        L_0x013e:
            java.lang.String r5 = "sub"
            goto L_0x0143
        L_0x0141:
            java.lang.String r5 = "add"
        L_0x0143:
            r14 = r5
            r5 = r0
            r0 = r14
        L_0x0146:
            if (r10 != 0) goto L_0x014e
            gnu.bytecode.Type[] r10 = new gnu.bytecode.Type[r2]
            r10[r3] = r8
            r10[r4] = r9
        L_0x014e:
            gnu.bytecode.Method r0 = r5.getMethod(r0, r10)
            r1.emitInvokeStatic(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compileIntNum(gnu.expr.Expression, gnu.expr.Expression, int, int, gnu.expr.Compilation):boolean");
    }

    public int getReturnKind(Expression[] expressionArr) {
        int length = expressionArr.length;
        if (length == 0) {
            return 4;
        }
        ClassType classType = Type.pointer_type;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int classifyType = Arithmetic.classifyType(expressionArr[i2].getType());
            if (i2 == 0 || classifyType == 0 || classifyType > i) {
                i = classifyType;
            }
        }
        return i;
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Arithmetic.kindType(adjustReturnKind(getReturnKind(expressionArr), this.op));
    }

    public static Expression validateApplyAdd(AddOp addOp, ApplyExp applyExp, InlineCalls inlineCalls) {
        Expression[] args = applyExp.getArgs();
        if (args.length != 1 || addOp.plusOrMinus >= 0) {
            return applyExp;
        }
        int i = 0;
        Type type = args[0].getType();
        if (!(type instanceof PrimType)) {
            return applyExp;
        }
        char charAt = type.getSignature().charAt(0);
        PrimType primType = null;
        if (!(charAt == 'V' || charAt == 'Z' || charAt == 'C')) {
            if (charAt == 'D') {
                i = 119;
                primType = LangPrimType.doubleType;
            } else if (charAt == 'F') {
                i = 118;
                primType = LangPrimType.floatType;
            } else if (charAt == 'J') {
                i = 117;
                primType = LangPrimType.longType;
            } else {
                i = 116;
                primType = LangPrimType.intType;
            }
        }
        return primType != null ? new ApplyExp((Procedure) PrimProcedure.makeBuiltinUnary(i, primType), args) : applyExp;
    }

    public static Expression validateApplyDiv(DivideOp divideOp, ApplyExp applyExp, InlineCalls inlineCalls) {
        Expression[] args = applyExp.getArgs();
        if (args.length != 1) {
            return applyExp;
        }
        return new ApplyExp(applyExp.getFunction(), QuoteExp.getInstance(IntNum.one()), args[0]);
    }

    public static Expression validateApplyNot(ApplyExp applyExp, int i, InlineCalls inlineCalls) {
        if (applyExp.getArgCount() != 1) {
            return applyExp;
        }
        Expression arg = applyExp.getArg(0);
        String str = null;
        if (i == 1 || i == 2) {
            return inlineCalls.visitApplyOnly(new ApplyExp((Procedure) BitwiseOp.xor, arg, QuoteExp.getInstance(IntNum.minusOne())), (Type) null);
        }
        if (i == 4) {
            str = "gnu.math.BitOps";
        } else if (i == 3) {
            str = "java.meth.BigInteger";
        }
        if (str != null) {
            return new ApplyExp(ClassType.make(str).getDeclaredMethod("not", 1), applyExp.getArgs());
        }
        return applyExp;
    }

    public static Expression validateApplyNumberCompare(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression inlineIfConstant = applyExp.inlineIfConstant(procedure, inlineCalls);
        return inlineIfConstant != applyExp ? inlineIfConstant : applyExp;
    }

    public int primitiveOpcode() {
        switch (this.op) {
            case 1:
                return 96;
            case 2:
                return 100;
            case 3:
                return 104;
            case 4:
            case 5:
            case 6:
            case 7:
                return 108;
            case 8:
                return 112;
            case 10:
                return 120;
            case 11:
                return 122;
            case 12:
                return 124;
            case 13:
                return 126;
            case 14:
                return 128;
            case 15:
                return 130;
            default:
                return -1;
        }
    }

    public static Expression pairwise(Procedure procedure, Expression expression, Expression[] expressionArr, InlineCalls inlineCalls) {
        int length = expressionArr.length;
        int i = 1;
        Expression expression2 = expressionArr[0];
        while (i < length) {
            ApplyExp applyExp = new ApplyExp(expression, expression2, expressionArr[i]);
            Expression maybeInline = inlineCalls.maybeInline(applyExp, (Type) null, procedure);
            i++;
            expression2 = maybeInline != null ? maybeInline : applyExp;
        }
        return expression2;
    }

    public static Expression validateApplyNumberPredicate(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        int i = ((NumberPredicate) procedure).op;
        Expression[] args = applyExp.getArgs();
        args[0] = inlineCalls.visit(args[0], (Type) LangObjType.integerType);
        applyExp.setType(Type.booleanType);
        return applyExp;
    }
}
