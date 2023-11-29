package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;

public class NumberCompare extends ProcedureN implements Inlineable {
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int RESULT_NAN = -2;
    static final int RESULT_NEQ = -3;
    public static final int TRUE_IF_EQU = 8;
    public static final int TRUE_IF_GRT = 16;
    public static final int TRUE_IF_LSS = 4;
    public static final int TRUE_IF_NAN = 2;
    public static final int TRUE_IF_NEQ = 1;
    int flags;
    Language language;

    public static boolean checkCompareCode(int i, int i2) {
        return ((1 << (i + 3)) & i2) != 0;
    }

    public int numArgs() {
        return -4094;
    }

    public static boolean $Eq(Object obj, Object obj2) {
        return apply2(8, obj, obj2);
    }

    public static boolean $Gr(Object obj, Object obj2) {
        return apply2(16, obj, obj2);
    }

    public static boolean $Gr$Eq(Object obj, Object obj2) {
        return apply2(24, obj, obj2);
    }

    public static boolean $Ls(Object obj, Object obj2) {
        return apply2(4, obj, obj2);
    }

    public static boolean $Ls$Eq(Object obj, Object obj2) {
        return apply2(12, obj, obj2);
    }

    public static boolean $Eq$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (!$Eq(obj, obj2) || !$Eq(obj2, obj3)) {
            return false;
        }
        return objArr.length == 0 || ($Eq(obj3, objArr[0]) && applyN(8, objArr));
    }

    public static boolean $Gr$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (!$Gr(obj, obj2) || !$Gr(obj2, obj3)) {
            return false;
        }
        return objArr.length == 0 || ($Gr(obj3, objArr[0]) && applyN(16, objArr));
    }

    public static boolean $Gr$Eq$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (!$Gr$Eq(obj, obj2) || !$Gr$Eq(obj2, obj3)) {
            return false;
        }
        return objArr.length == 0 || ($Gr$Eq(obj3, objArr[0]) && applyN(24, objArr));
    }

    public static boolean $Ls$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (!$Ls(obj, obj2) || !$Ls(obj2, obj3)) {
            return false;
        }
        return objArr.length == 0 || ($Ls(obj3, objArr[0]) && applyN(4, objArr));
    }

    public static boolean $Ls$Eq$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (!$Ls$Eq(obj, obj2) || !$Ls$Eq(obj2, obj3)) {
            return false;
        }
        return objArr.length == 0 || ($Ls$Eq(obj3, objArr[0]) && applyN(12, objArr));
    }

    public static NumberCompare make(Language language2, String str, int i) {
        NumberCompare numberCompare = new NumberCompare();
        numberCompare.language = language2;
        numberCompare.setName(str);
        numberCompare.flags = i;
        numberCompare.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberCompare");
        return numberCompare;
    }

    /* access modifiers changed from: protected */
    public final Language getLanguage() {
        return this.language;
    }

    public Object apply2(Object obj, Object obj2) {
        return getLanguage().booleanObject(apply2(this.flags, obj, obj2));
    }

    public static boolean apply2(int i, Object obj, Object obj2) {
        return (i & (1 << (compare(obj, obj2, true) + 3))) != 0;
    }

    public static boolean applyWithPromotion(int i, Object obj, Object obj2) {
        return checkCompareCode(compare(obj, obj2, false), i);
    }

    public static int compare(Object obj, Object obj2, boolean z) {
        return compare(obj, Arithmetic.classifyValue(obj), obj2, Arithmetic.classifyValue(obj2), z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r10 == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r7 <= 6) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r9 <= 6) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        r6 = gnu.kawa.functions.Arithmetic.asDouble(r6);
        r8 = gnu.kawa.functions.Arithmetic.asDouble(r8);
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r10 <= 0) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r6 >= r8) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r10 != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0093, code lost:
        if (r10 > 0) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a0, code lost:
        r2 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a2, code lost:
        if (r6 > r7) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return gnu.kawa.functions.Arithmetic.asNumeric(r6).compare(gnu.kawa.functions.Arithmetic.asNumeric(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int compare(java.lang.Object r6, int r7, java.lang.Object r8, int r9, boolean r10) {
        /*
            if (r7 < 0) goto L_0x00b4
            if (r9 >= 0) goto L_0x0006
            goto L_0x00b4
        L_0x0006:
            if (r7 >= r9) goto L_0x000a
            r0 = r9
            goto L_0x000b
        L_0x000a:
            r0 = r7
        L_0x000b:
            r1 = -2
            r2 = 0
            r3 = 1
            r4 = -1
            r5 = 6
            switch(r0) {
                case 1: goto L_0x0096;
                case 2: goto L_0x0086;
                case 3: goto L_0x0079;
                case 4: goto L_0x006c;
                case 5: goto L_0x005f;
                case 6: goto L_0x0052;
                case 7: goto L_0x0015;
                case 8: goto L_0x001c;
                case 9: goto L_0x001c;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x00a7
        L_0x0015:
            if (r10 == 0) goto L_0x003d
            if (r7 <= r5) goto L_0x001c
            if (r9 <= r5) goto L_0x001c
            goto L_0x003d
        L_0x001c:
            if (r10 == 0) goto L_0x0022
            if (r7 <= r5) goto L_0x00a7
            if (r9 <= r5) goto L_0x00a7
        L_0x0022:
            double r6 = gnu.kawa.functions.Arithmetic.asDouble(r6)
            double r8 = gnu.kawa.functions.Arithmetic.asDouble(r8)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x0031
        L_0x002e:
            r1 = 1
            goto L_0x00b3
        L_0x0031:
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x0038
        L_0x0035:
            r1 = -1
            goto L_0x00b3
        L_0x0038:
            if (r10 != 0) goto L_0x00b3
        L_0x003a:
            r1 = 0
            goto L_0x00b3
        L_0x003d:
            float r6 = gnu.kawa.functions.Arithmetic.asFloat(r6)
            float r7 = gnu.kawa.functions.Arithmetic.asFloat(r8)
            int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x004a
            goto L_0x002e
        L_0x004a:
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x004f
            goto L_0x0035
        L_0x004f:
            if (r8 != 0) goto L_0x00b3
            goto L_0x003a
        L_0x0052:
            gnu.math.RatNum r6 = gnu.kawa.functions.Arithmetic.asRatNum(r6)
            gnu.math.RatNum r7 = gnu.kawa.functions.Arithmetic.asRatNum(r8)
            int r1 = gnu.math.RatNum.compare(r6, r7)
            goto L_0x00b3
        L_0x005f:
            java.math.BigDecimal r6 = gnu.kawa.functions.Arithmetic.asBigDecimal(r6)
            java.math.BigDecimal r7 = gnu.kawa.functions.Arithmetic.asBigDecimal(r8)
            int r1 = r6.compareTo(r7)
            goto L_0x00b3
        L_0x006c:
            gnu.math.IntNum r6 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r6)
            gnu.math.IntNum r7 = gnu.kawa.functions.Arithmetic.asIntNum((java.lang.Object) r8)
            int r1 = gnu.math.IntNum.compare((gnu.math.IntNum) r6, (gnu.math.IntNum) r7)
            goto L_0x00b3
        L_0x0079:
            java.math.BigInteger r6 = gnu.kawa.functions.Arithmetic.asBigInteger(r6)
            java.math.BigInteger r7 = gnu.kawa.functions.Arithmetic.asBigInteger(r8)
            int r1 = r6.compareTo(r7)
            goto L_0x00b3
        L_0x0086:
            long r6 = gnu.kawa.functions.Arithmetic.asLong(r6)
            long r8 = gnu.kawa.functions.Arithmetic.asLong(r8)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0093
            goto L_0x00a0
        L_0x0093:
            if (r10 <= 0) goto L_0x00a5
            goto L_0x00a4
        L_0x0096:
            int r6 = gnu.kawa.functions.Arithmetic.asInt(r6)
            int r7 = gnu.kawa.functions.Arithmetic.asInt(r8)
            if (r6 >= r7) goto L_0x00a2
        L_0x00a0:
            r2 = -1
            goto L_0x00a5
        L_0x00a2:
            if (r6 <= r7) goto L_0x00a5
        L_0x00a4:
            r2 = 1
        L_0x00a5:
            r1 = r2
            goto L_0x00b3
        L_0x00a7:
            gnu.math.Numeric r6 = gnu.kawa.functions.Arithmetic.asNumeric(r6)
            gnu.math.Numeric r7 = gnu.kawa.functions.Arithmetic.asNumeric(r8)
            int r1 = r6.compare(r7)
        L_0x00b3:
            return r1
        L_0x00b4:
            r6 = -3
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.NumberCompare.compare(java.lang.Object, int, java.lang.Object, int, boolean):int");
    }

    static boolean applyN(int i, Object[] objArr) {
        int i2 = 0;
        while (i2 < objArr.length - 1) {
            Object obj = objArr[i2];
            i2++;
            if (!apply2(i, obj, objArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Object applyN(Object[] objArr) {
        return getLanguage().booleanObject(applyN(this.flags, objArr));
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        PrimType primType;
        Compilation compilation2 = compilation;
        Target target2 = target;
        Expression[] args = applyExp.getArgs();
        if (args.length == 2) {
            int i = 0;
            Expression expression = args[0];
            Expression expression2 = args[1];
            int classify = classify(expression);
            int classify2 = classify(expression2);
            CodeAttr code = compilation.getCode();
            if (classify > 0 && classify2 > 0 && classify <= 10 && classify2 <= 10 && !(classify == 6 && classify2 == 6)) {
                if (!(target2 instanceof ConditionalTarget)) {
                    IfExp.compile(applyExp, QuoteExp.trueExp, QuoteExp.falseExp, compilation2, target2);
                    return;
                }
                int i2 = this.flags;
                if (i2 == 1) {
                    i2 = 20;
                }
                if (classify <= 4 && classify2 <= 4 && (classify > 2 || classify2 > 2)) {
                    Type[] typeArr = new Type[2];
                    typeArr[0] = Arithmetic.typeIntNum;
                    if (classify2 <= 2) {
                        typeArr[1] = Type.longType;
                    } else if (classify > 2 || (!(expression instanceof QuoteExp) && !(expression2 instanceof QuoteExp) && !(expression instanceof ReferenceExp) && !(expression2 instanceof ReferenceExp))) {
                        typeArr[1] = Arithmetic.typeIntNum;
                    } else {
                        typeArr[1] = Type.longType;
                        args = new Expression[]{expression2, expression};
                        if (!(i2 == 8 || i2 == 20)) {
                            i2 ^= 20;
                        }
                    }
                    expression = new ApplyExp((Procedure) new PrimProcedure(Arithmetic.typeIntNum.getMethod("compare", typeArr)), args);
                    expression2 = new QuoteExp(IntNum.zero());
                    classify = 1;
                    classify2 = 1;
                }
                if (classify <= 1 && classify2 <= 1) {
                    primType = Type.intType;
                } else if (classify > 2 || classify2 > 2) {
                    primType = Type.doubleType;
                } else {
                    primType = Type.longType;
                }
                StackTarget stackTarget = new StackTarget(primType);
                ConditionalTarget conditionalTarget = (ConditionalTarget) target2;
                if ((expression instanceof QuoteExp) && !(expression2 instanceof QuoteExp)) {
                    if (!(i2 == 8 || i2 == 20)) {
                        i2 ^= 20;
                    }
                    Expression expression3 = expression2;
                    expression2 = expression;
                    expression = expression3;
                }
                Label label = conditionalTarget.trueBranchComesFirst ? conditionalTarget.ifFalse : conditionalTarget.ifTrue;
                if (conditionalTarget.trueBranchComesFirst) {
                    i2 ^= 28;
                }
                if (i2 == 4) {
                    i = 155;
                } else if (i2 == 8) {
                    i = 153;
                } else if (i2 == 12) {
                    i = 158;
                } else if (i2 == 16) {
                    i = 157;
                } else if (i2 == 20) {
                    i = 154;
                } else if (i2 == 24) {
                    i = 156;
                }
                expression.compile(compilation2, (Target) stackTarget);
                if (classify <= 1 && classify2 <= 1 && (expression2 instanceof QuoteExp)) {
                    Object value = ((QuoteExp) expression2).getValue();
                    if ((value instanceof IntNum) && ((IntNum) value).isZero()) {
                        code.emitGotoIfCompare1(label, i);
                        conditionalTarget.emitGotoFirstBranch(code);
                        return;
                    }
                }
                expression2.compile(compilation2, (Target) stackTarget);
                code.emitGotoIfCompare2(label, i);
                conditionalTarget.emitGotoFirstBranch(code);
                return;
            }
        }
        ApplyExp applyExp2 = applyExp;
        ApplyExp.compile(applyExp, compilation, target);
    }

    static int classify(Expression expression) {
        int classifyType = Arithmetic.classifyType(expression.getType());
        if (classifyType == 4 && (expression instanceof QuoteExp)) {
            Object value = ((QuoteExp) expression).getValue();
            if (value instanceof IntNum) {
                int intLength = ((IntNum) value).intLength();
                if (intLength < 32) {
                    return 1;
                }
                if (intLength < 64) {
                    return 2;
                }
            }
        }
        return classifyType;
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.booleanType;
    }
}
