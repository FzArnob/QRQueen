package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DFloNum extends RealNum implements Externalizable {
    private static final DFloNum one = new DFloNum(1.0d);
    double value;

    public static int compare(double d, double d2) {
        int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        if (d < d2) {
            return -1;
        }
        return i == 0 ? 0 : -2;
    }

    public boolean isExact() {
        return false;
    }

    public DFloNum() {
    }

    public DFloNum(double d) {
        this.value = d;
    }

    public DFloNum(String str) throws NumberFormatException {
        double doubleValue = Double.valueOf(str).doubleValue();
        this.value = doubleValue;
        if (doubleValue == 0.0d && str.charAt(0) == '-') {
            this.value = -0.0d;
        }
    }

    public static DFloNum make(double d) {
        return new DFloNum(d);
    }

    public static DFloNum asDFloNumOrNull(Object obj) {
        if (obj instanceof DFloNum) {
            return (DFloNum) obj;
        }
        if ((obj instanceof RealNum) || (obj instanceof Number)) {
            return new DFloNum(((Number) obj).doubleValue());
        }
        return null;
    }

    public final double doubleValue() {
        return this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof DFloNum) && Double.doubleToLongBits(((DFloNum) obj).value) == Double.doubleToLongBits(this.value);
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof RealNum) {
            return new DFloNum(this.value + (((double) i) * ((RealNum) obj).doubleValue()));
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).addReversed(this, i);
        }
        throw new IllegalArgumentException();
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if (numeric instanceof RealNum) {
            return new DFloNum(((RealNum) numeric).doubleValue() + (((double) i) * this.value));
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object obj) {
        if (obj instanceof RealNum) {
            return new DFloNum(this.value * ((RealNum) obj).doubleValue());
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).mulReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof RealNum) {
            return new DFloNum(((RealNum) numeric).doubleValue() * this.value);
        }
        throw new IllegalArgumentException();
    }

    public static final DFloNum one() {
        return one;
    }

    public Numeric div(Object obj) {
        if (obj instanceof RealNum) {
            return new DFloNum(this.value / ((RealNum) obj).doubleValue());
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).divReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric divReversed(Numeric numeric) {
        if (numeric instanceof RealNum) {
            return new DFloNum(((RealNum) numeric).doubleValue() / this.value);
        }
        throw new IllegalArgumentException();
    }

    public Numeric power(IntNum intNum) {
        return new DFloNum(Math.pow(doubleValue(), intNum.doubleValue()));
    }

    public boolean isNegative() {
        return this.value < 0.0d;
    }

    public Numeric neg() {
        return new DFloNum(-this.value);
    }

    public int sign() {
        double d = this.value;
        if (d > 0.0d) {
            return 1;
        }
        if (d < 0.0d) {
            return -1;
        }
        return d == 0.0d ? 0 : -2;
    }

    public static int compare(IntNum intNum, IntNum intNum2, double d) {
        if (Double.isNaN(d)) {
            return -2;
        }
        if (Double.isInfinite(d)) {
            int i = d >= 0.0d ? -1 : 1;
            if (!intNum2.isZero()) {
                return i;
            }
            if (intNum.isZero()) {
                return -2;
            }
            int i2 = i >> 1;
            return intNum.isNegative() ? i2 : ~i2;
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        boolean z = doubleToLongBits < 0;
        int i3 = ((int) (doubleToLongBits >> 52)) & 2047;
        long j = doubleToLongBits & 4503599627370495L;
        long j2 = i3 == 0 ? j << 1 : j | 4503599627370496L;
        if (z) {
            j2 = -j2;
        }
        IntNum make = IntNum.make(j2);
        if (i3 >= 1075) {
            make = IntNum.shift(make, i3 - 1075);
        } else {
            intNum = IntNum.shift(intNum, 1075 - i3);
        }
        return IntNum.compare(intNum, IntNum.times(make, intNum2));
    }

    public int compare(Object obj) {
        if (!(obj instanceof RatNum)) {
            return compare(this.value, ((RealNum) obj).doubleValue());
        }
        RatNum ratNum = (RatNum) obj;
        int compare = compare(ratNum.numerator(), ratNum.denominator(), this.value);
        return compare < -1 ? compare : -compare;
    }

    public int compareReversed(Numeric numeric) {
        if (!(numeric instanceof RatNum)) {
            return compare(((RealNum) numeric).doubleValue(), this.value);
        }
        RatNum ratNum = (RatNum) numeric;
        return compare(ratNum.numerator(), ratNum.denominator(), this.value);
    }

    public boolean isZero() {
        return this.value == 0.0d;
    }

    public static RatNum toExact(double d) {
        int i = 1;
        if (Double.isInfinite(d)) {
            if (d < 0.0d) {
                i = -1;
            }
            return RatNum.infinity(i);
        } else if (!Double.isNaN(d)) {
            long doubleToLongBits = Double.doubleToLongBits(d);
            boolean z = doubleToLongBits < 0;
            int i2 = ((int) (doubleToLongBits >> 52)) & 2047;
            long j = doubleToLongBits & 4503599627370495L;
            long j2 = i2 == 0 ? j << 1 : j | 4503599627370496L;
            if (z) {
                j2 = -j2;
            }
            IntNum make = IntNum.make(j2);
            if (i2 >= 1075) {
                return IntNum.shift(make, i2 - 1075);
            }
            return RatNum.make(make, IntNum.shift(IntNum.one(), 1075 - i2));
        } else {
            throw new ArithmeticException("cannot convert NaN to exact rational");
        }
    }

    public String toString() {
        double d = this.value;
        if (d == Double.POSITIVE_INFINITY) {
            return "+inf.0";
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return "-inf.0";
        }
        return Double.isNaN(d) ? "+nan.0" : Double.toString(this.value);
    }

    public String toString(int i) {
        if (i == 10) {
            return toString();
        }
        return "#d" + toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeDouble(this.value);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.value = objectInput.readDouble();
    }
}
