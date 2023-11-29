package gnu.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Numeric extends Number {
    public static final int CEILING = 2;
    public static final int FLOOR = 1;
    public static final int NONNEG_MOD = 5;
    public static final int ROUND = 4;
    public static final int TRUNCATE = 3;

    public abstract Numeric abs();

    public abstract Numeric add(Object obj, int i);

    public int compare(Object obj) {
        return -3;
    }

    public abstract Numeric div(Object obj);

    public abstract boolean isExact();

    public abstract boolean isZero();

    public abstract Numeric mul(Object obj);

    public abstract Numeric neg();

    public Numeric toExact() {
        return this;
    }

    public Numeric toInexact() {
        return this;
    }

    public abstract String toString(int i);

    public float floatValue() {
        return (float) doubleValue();
    }

    public int intValue() {
        return (int) longValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public final Numeric add(Object obj) {
        return add(obj, 1);
    }

    public final Numeric sub(Object obj) {
        return add(obj, -1);
    }

    public String toString() {
        return toString(10);
    }

    public static Numeric asNumericOrNull(Object obj) {
        if (obj instanceof Numeric) {
            return (Numeric) obj;
        }
        if ((obj instanceof BigInteger) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof Byte) || (obj instanceof Integer)) {
            return IntNum.asIntNumOrNull(obj);
        }
        if (obj instanceof BigDecimal) {
            return RatNum.asRatNumOrNull(obj);
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return new DFloNum(((Number) obj).doubleValue());
        }
        return null;
    }

    public int compareReversed(Numeric numeric) {
        throw new IllegalArgumentException();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Numeric) || compare(obj) != 0) {
            return false;
        }
        return true;
    }

    public boolean grt(Object obj) {
        return compare(obj) > 0;
    }

    public boolean geq(Object obj) {
        return compare(obj) >= 0;
    }

    public Numeric addReversed(Numeric numeric, int i) {
        throw new IllegalArgumentException();
    }

    public Numeric mulReversed(Numeric numeric) {
        throw new IllegalArgumentException();
    }

    public Numeric divReversed(Numeric numeric) {
        throw new IllegalArgumentException();
    }

    public Numeric div_inv() {
        return IntNum.one().div(this);
    }

    public Numeric mul_ident() {
        return IntNum.one();
    }

    public Numeric power(IntNum intNum) {
        if (intNum.isNegative()) {
            return power(IntNum.neg(intNum)).div_inv();
        }
        Numeric numeric = null;
        Numeric numeric2 = this;
        while (true) {
            if (intNum.isOdd()) {
                if (numeric == null) {
                    numeric = numeric2;
                } else {
                    numeric = numeric.mul(numeric2);
                }
            }
            intNum = IntNum.shift(intNum, -1);
            if (intNum.isZero()) {
                break;
            }
            numeric2 = numeric2.mul(numeric2);
        }
        return numeric == null ? mul_ident() : numeric;
    }
}
