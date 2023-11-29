package gnu.xquery.util;

import gnu.kawa.functions.Arithmetic;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XIntegerType;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class NumberValue extends Procedure1 {
    public static final Double NaN = new Double(Double.NaN);
    public static final NumberValue numberValue = new NumberValue();

    public static boolean isNaN(Object obj) {
        return ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof DFloNum)) && Double.isNaN(((Number) obj).doubleValue());
    }

    public Object apply1(Object obj) {
        if (!(obj == Values.empty || obj == null)) {
            try {
                return numberValue(obj);
            } catch (Throwable unused) {
            }
        }
        return NaN;
    }

    public static Number numberCast(Object obj) {
        if (obj == Values.empty || obj == null) {
            return null;
        }
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int startPos = values.startPos();
            int i = 0;
            while (true) {
                startPos = values.nextPos(startPos);
                if (startPos == 0) {
                    break;
                } else if (i <= 0) {
                    obj = values.getPosPrevious(startPos);
                    i++;
                } else {
                    throw new ClassCastException("non-singleton sequence cast to number");
                }
            }
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            return (Double) XDataType.doubleType.valueOf(TextUtils.stringValue(obj));
        }
        return (Number) obj;
    }

    public static Object numberValue(Object obj) {
        Object atomicValue = KNode.atomicValue(obj);
        double d = Double.NaN;
        if ((atomicValue instanceof UntypedAtomic) || (atomicValue instanceof String)) {
            try {
                return XDataType.doubleType.valueOf(TextUtils.stringValue(atomicValue));
            } catch (Throwable unused) {
            }
        } else {
            if ((atomicValue instanceof Number) && ((atomicValue instanceof RealNum) || !(atomicValue instanceof Numeric))) {
                d = ((Number) atomicValue).doubleValue();
            }
            return XDataType.makeDouble(d);
        }
    }

    public static Object abs(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        Number numberCast = numberCast(obj);
        if (numberCast instanceof Double) {
            Double d = (Double) numberCast;
            long doubleToRawLongBits = Double.doubleToRawLongBits(d.doubleValue());
            if (doubleToRawLongBits >= 0) {
                return d;
            }
            return Double.valueOf(Double.longBitsToDouble(doubleToRawLongBits & Long.MAX_VALUE));
        } else if (numberCast instanceof Float) {
            Float f = (Float) numberCast;
            int floatToRawIntBits = Float.floatToRawIntBits(f.floatValue());
            if (floatToRawIntBits >= 0) {
                return f;
            }
            return Float.valueOf(Float.intBitsToFloat(Integer.MAX_VALUE & floatToRawIntBits));
        } else if (!(numberCast instanceof BigDecimal)) {
            return ((Numeric) numberCast).abs();
        } else {
            BigDecimal bigDecimal = (BigDecimal) numberCast;
            return bigDecimal.signum() < 0 ? bigDecimal.negate() : bigDecimal;
        }
    }

    public static Object floor(Object obj) {
        Number numberCast = numberCast(obj);
        if (numberCast == null) {
            return obj;
        }
        if (numberCast instanceof Double) {
            return XDataType.makeDouble(Math.floor(((Double) numberCast).doubleValue()));
        }
        if (numberCast instanceof Float) {
            return XDataType.makeFloat((float) Math.floor((double) ((Float) numberCast).floatValue()));
        }
        if (numberCast instanceof BigDecimal) {
            return Arithmetic.asIntNum(((BigDecimal) numberCast).divide(XDataType.DECIMAL_ONE, 0, 3).toBigInteger());
        }
        return ((RealNum) numberCast).toInt(1);
    }

    public static Object ceiling(Object obj) {
        Number numberCast = numberCast(obj);
        if (numberCast == null) {
            return obj;
        }
        if (numberCast instanceof Double) {
            return XDataType.makeDouble(Math.ceil(((Double) numberCast).doubleValue()));
        }
        if (numberCast instanceof Float) {
            return XDataType.makeFloat((float) Math.ceil((double) ((Float) numberCast).floatValue()));
        }
        if (numberCast instanceof BigDecimal) {
            return Arithmetic.asIntNum(((BigDecimal) numberCast).divide(XDataType.DECIMAL_ONE, 0, 2).toBigInteger());
        }
        return ((RealNum) numberCast).toInt(2);
    }

    public static Object round(Object obj) {
        float f;
        int i;
        double d;
        int i2;
        Number numberCast = numberCast(obj);
        if (numberCast == null) {
            return obj;
        }
        if (numberCast instanceof Double) {
            double doubleValue = ((Double) numberCast).doubleValue();
            if (doubleValue < -0.5d || doubleValue > 0.0d || (i2 >= 0 && Double.doubleToLongBits(doubleValue) >= 0)) {
                d = Math.floor(doubleValue + 0.5d);
            } else {
                d = -0.0d;
            }
            return XDataType.makeDouble(d);
        } else if (numberCast instanceof Float) {
            float floatValue = ((Float) numberCast).floatValue();
            double d2 = (double) floatValue;
            if (d2 < -0.5d || d2 > 0.0d || (i >= 0 && Float.floatToIntBits(floatValue) >= 0)) {
                f = (float) Math.floor(d2 + 0.5d);
            } else {
                f = -0.0f;
            }
            return XDataType.makeFloat(f);
        } else {
            int i3 = 4;
            if (!(numberCast instanceof BigDecimal)) {
                return ((RealNum) numberCast).toInt(4);
            }
            BigDecimal bigDecimal = (BigDecimal) numberCast;
            if (bigDecimal.signum() < 0) {
                i3 = 5;
            }
            return Arithmetic.asIntNum(bigDecimal.divide(XDataType.DECIMAL_ONE, 0, i3).toBigInteger());
        }
    }

    public static Object roundHalfToEven(Object obj, IntNum intNum) {
        Number numberCast = numberCast(obj);
        if (numberCast == null) {
            return obj;
        }
        if ((obj instanceof Double) || (obj instanceof Float)) {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue == 0.0d || Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) {
                return obj;
            }
        }
        BigDecimal scale = ((BigDecimal) XDataType.decimalType.cast(numberCast)).setScale(intNum.intValue(), 6);
        if (numberCast instanceof Double) {
            return XDataType.makeDouble(scale.doubleValue());
        }
        if (numberCast instanceof Float) {
            return XDataType.makeFloat(scale.floatValue());
        }
        return numberCast instanceof IntNum ? XIntegerType.integerType.cast(scale) : scale;
    }

    public static Object roundHalfToEven(Object obj) {
        return roundHalfToEven(obj, IntNum.zero());
    }
}
