package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.IntNum;
import java.math.BigInteger;

public class BitwiseOp extends ArithOp {
    public static final BitwiseOp and = new BitwiseOp("bitwise-and", 13);
    public static final BitwiseOp ashift = new BitwiseOp("bitwise-arithmetic-shift", 9);
    public static final BitwiseOp ashiftl = new BitwiseOp("bitwise-arithmetic-shift-left", 10);
    public static final BitwiseOp ashiftr = new BitwiseOp("bitwise-arithmetic-shift-right", 11);
    public static final BitwiseOp ior = new BitwiseOp("bitwise-ior", 14);
    public static final BitwiseOp not = new BitwiseOp("bitwise-not", 16);
    public static final BitwiseOp xor = new BitwiseOp("bitwise-xor", 15);

    public BitwiseOp(String str, int i) {
        super(str, i);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forBitwise");
    }

    public Object defaultResult() {
        if (this.op == 13) {
            return IntNum.minusOne();
        }
        return IntNum.zero();
    }

    public Object adjustResult(IntNum intNum, int i) {
        if (i == 1) {
            return Integer.valueOf(intNum.intValue());
        }
        if (i != 2) {
            return i != 3 ? intNum : new BigInteger(intNum.toString());
        }
        return Long.valueOf(intNum.longValue());
    }

    public Object apply1(Object obj) {
        if (this.op != 16) {
            return apply2(defaultResult(), obj);
        }
        return adjustResult(BitOps.not(LangObjType.coerceIntNum(obj)), Arithmetic.classifyValue(obj));
    }

    public Object apply2(Object obj, Object obj2) {
        IntNum intNum;
        int classifyValue = Arithmetic.classifyValue(obj);
        int classifyValue2 = Arithmetic.classifyValue(obj2);
        if ((this.op < 9 || this.op > 12) && classifyValue > 0 && (classifyValue <= classifyValue2 || classifyValue2 <= 0)) {
            classifyValue = classifyValue2;
        }
        IntNum coerceIntNum = LangObjType.coerceIntNum(obj);
        IntNum coerceIntNum2 = LangObjType.coerceIntNum(obj2);
        switch (this.op) {
            case 9:
            case 10:
            case 11:
                int intValue = coerceIntNum2.intValue();
                if (this.op == 11 || this.op == 10) {
                    checkNonNegativeShift(this, intValue);
                    if (this.op == 11) {
                        intValue = -intValue;
                    }
                }
                intNum = IntNum.shift(coerceIntNum, intValue);
                break;
            case 13:
                intNum = BitOps.and(coerceIntNum, coerceIntNum2);
                break;
            case 14:
                intNum = BitOps.ior(coerceIntNum, coerceIntNum2);
                break;
            case 15:
                intNum = BitOps.xor(coerceIntNum, coerceIntNum2);
                break;
            default:
                throw new Error();
        }
        return adjustResult(intNum, classifyValue);
    }

    public Object applyN(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return defaultResult();
        }
        if (length == 1) {
            return apply1(objArr[0]);
        }
        Object obj = objArr[0];
        for (int i = 1; i < length; i++) {
            obj = apply2(obj, objArr[i]);
        }
        return obj;
    }

    public static int checkNonNegativeShift(Procedure procedure, int i) {
        if (i >= 0) {
            return i;
        }
        throw new WrongType(procedure, 2, (Object) Integer.valueOf(i), "non-negative integer");
    }

    public static IntNum shiftLeft(IntNum intNum, int i) {
        return IntNum.shift(intNum, checkNonNegativeShift(ashiftl, i));
    }

    public static IntNum shiftRight(IntNum intNum, int i) {
        return IntNum.shift(intNum, -checkNonNegativeShift(ashiftr, i));
    }

    public int numArgs() {
        if (this.op >= 9 && this.op <= 12) {
            return 8194;
        }
        if (this.op == 16) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return -4096;
    }
}
