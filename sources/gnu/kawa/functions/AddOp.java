package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.slf4j.Marker;

public class AddOp extends ArithOp {
    public static final AddOp $Mn = new AddOp("-", -1);
    public static final AddOp $Pl = new AddOp(Marker.ANY_NON_NULL_MARKER, 1);
    int plusOrMinus;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddOp(String str, int i) {
        super(str, i > 0 ? 1 : 2);
        this.plusOrMinus = i;
        Procedure.compilerKey.set(this, i > 0 ? "gnu.kawa.functions.CompileArith:$Pl" : "gnu.kawa.functions.CompileArith:$Mn");
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    }

    public static Object apply2(int i, Object obj, Object obj2) {
        int classifyValue = Arithmetic.classifyValue(obj);
        int classifyValue2 = Arithmetic.classifyValue(obj2);
        if (classifyValue < classifyValue2) {
            classifyValue = classifyValue2;
        }
        switch (classifyValue) {
            case 1:
                int asInt = Arithmetic.asInt(obj);
                int asInt2 = Arithmetic.asInt(obj2);
                return new Integer(i > 0 ? asInt + asInt2 : asInt - asInt2);
            case 2:
                long asLong = Arithmetic.asLong(obj);
                long asLong2 = Arithmetic.asLong(obj2);
                return new Long(i > 0 ? asLong + asLong2 : asLong - asLong2);
            case 3:
                BigInteger asBigInteger = Arithmetic.asBigInteger(obj);
                BigInteger asBigInteger2 = Arithmetic.asBigInteger(obj2);
                if (i > 0) {
                    return asBigInteger.add(asBigInteger2);
                }
                return asBigInteger.subtract(asBigInteger2);
            case 4:
                return IntNum.add(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj2), i);
            case 5:
                BigDecimal asBigDecimal = Arithmetic.asBigDecimal(obj);
                BigDecimal asBigDecimal2 = Arithmetic.asBigDecimal(obj2);
                if (i > 0) {
                    return asBigDecimal.add(asBigDecimal2);
                }
                return asBigDecimal.subtract(asBigDecimal2);
            case 6:
                return RatNum.add(Arithmetic.asRatNum(obj), Arithmetic.asRatNum(obj2), i);
            case 7:
                float asFloat = Arithmetic.asFloat(obj);
                float asFloat2 = Arithmetic.asFloat(obj2);
                return new Float(i > 0 ? asFloat + asFloat2 : asFloat - asFloat2);
            case 8:
                double asDouble = Arithmetic.asDouble(obj);
                double asDouble2 = Arithmetic.asDouble(obj2);
                return new Double(i > 0 ? asDouble + asDouble2 : asDouble - asDouble2);
            case 9:
                double asDouble3 = Arithmetic.asDouble(obj);
                double asDouble4 = Arithmetic.asDouble(obj2);
                return new DFloNum(i > 0 ? asDouble3 + asDouble4 : asDouble3 - asDouble4);
            default:
                return Arithmetic.asNumeric(obj).add(Arithmetic.asNumeric(obj2), i);
        }
    }

    public static Object $Pl(Object obj, Object obj2) {
        return apply2(1, obj, obj2);
    }

    public static Object $Mn(Object obj, Object obj2) {
        return apply2(-1, obj, obj2);
    }

    public static Object $Mn(Object obj) {
        switch (Arithmetic.classifyValue(obj)) {
            case 1:
                return new Integer(-Arithmetic.asInt(obj));
            case 2:
                return new Long(-Arithmetic.asLong(obj));
            case 3:
                return Arithmetic.asBigInteger(obj).negate();
            case 4:
                return IntNum.neg(Arithmetic.asIntNum(obj));
            case 5:
                return Arithmetic.asBigDecimal(obj).negate();
            case 6:
                return RatNum.neg(Arithmetic.asRatNum(obj));
            case 7:
                return new Float(-Arithmetic.asFloat(obj));
            case 8:
                return new Double(-Arithmetic.asDouble(obj));
            case 9:
                return new DFloNum(-Arithmetic.asDouble(obj));
            default:
                return Arithmetic.asNumeric(obj).neg();
        }
    }

    public static Object $Pl$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        return applyN(1, apply2(1, apply2(1, obj, obj2), obj3), objArr);
    }

    public static Object $Mn$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        return applyN(-1, apply2(-1, apply2(-1, obj, obj2), obj3), objArr);
    }

    public static Object applyN(int i, Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return IntNum.zero();
        }
        Object obj = objArr[0];
        if (length == 1 && i < 0) {
            return $Mn(obj);
        }
        for (int i2 = 1; i2 < length; i2++) {
            obj = apply2(i, obj, objArr[i2]);
        }
        return obj;
    }

    public static Object applyN(int i, Object obj, Object[] objArr) {
        for (Object apply2 : objArr) {
            obj = apply2(i, obj, apply2);
        }
        return obj;
    }

    public Object applyN(Object[] objArr) {
        return applyN(this.plusOrMinus, objArr);
    }
}
