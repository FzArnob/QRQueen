package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import org.slf4j.Marker;

public class MultiplyOp extends ArithOp {
    public static final MultiplyOp $St = new MultiplyOp(Marker.ANY_MARKER);

    public MultiplyOp(String str) {
        super(str, 3);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forMul");
    }

    public Object defaultResult() {
        return IntNum.one();
    }

    public static Object apply(Object obj, Object obj2) {
        return ((Numeric) obj).mul(obj2);
    }

    public Object applyN(Object[] objArr) {
        Number number;
        int length = objArr.length;
        if (length == 0) {
            return IntNum.one();
        }
        Number number2 = objArr[0];
        int classifyValue = Arithmetic.classifyValue(number2);
        for (int i = 1; i < length; i++) {
            Object obj = objArr[i];
            int classifyValue2 = Arithmetic.classifyValue(obj);
            if (classifyValue < classifyValue2) {
                classifyValue = classifyValue2;
            }
            switch (classifyValue) {
                case 1:
                    number = new Integer(Arithmetic.asInt(number2) * Arithmetic.asInt(obj));
                    break;
                case 2:
                    number2 = new Long(Arithmetic.asLong(number2) * Arithmetic.asLong(obj));
                    continue;
                case 3:
                    number2 = Arithmetic.asBigInteger(number2).multiply(Arithmetic.asBigInteger(obj));
                    continue;
                case 4:
                    number2 = IntNum.times(Arithmetic.asIntNum((Object) number2), Arithmetic.asIntNum(obj));
                    continue;
                case 5:
                    number2 = Arithmetic.asBigDecimal(number2).multiply(Arithmetic.asBigDecimal(obj));
                    continue;
                case 6:
                    number2 = RatNum.times(Arithmetic.asRatNum(number2), Arithmetic.asRatNum(obj));
                    continue;
                case 7:
                    number = new Float(Arithmetic.asFloat(number2) * Arithmetic.asFloat(obj));
                    break;
                case 8:
                    number2 = new Double(Arithmetic.asDouble(number2) * Arithmetic.asDouble(obj));
                    continue;
                case 9:
                    number2 = new DFloNum(Arithmetic.asDouble(number2) * Arithmetic.asDouble(obj));
                    continue;
                default:
                    number2 = Arithmetic.asNumeric(number2).mul(Arithmetic.asNumeric(obj));
                    continue;
            }
            number2 = number;
        }
        return number2;
    }
}
