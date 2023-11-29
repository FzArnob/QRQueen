package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class IntFraction extends RatNum implements Externalizable {
    IntNum den;
    IntNum num;

    IntFraction() {
    }

    public IntFraction(IntNum intNum, IntNum intNum2) {
        this.num = intNum;
        this.den = intNum2;
    }

    public final IntNum numerator() {
        return this.num;
    }

    public final IntNum denominator() {
        return this.den;
    }

    public final boolean isNegative() {
        return this.num.isNegative();
    }

    public final int sign() {
        return this.num.sign();
    }

    public final int compare(Object obj) {
        if (obj instanceof RatNum) {
            return RatNum.compare(this, (RatNum) obj);
        }
        return ((RealNum) obj).compareReversed(this);
    }

    public int compareReversed(Numeric numeric) {
        return RatNum.compare((RatNum) numeric, this);
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof RatNum) {
            return RatNum.add(this, (RatNum) obj, i);
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).addReversed(this, i);
        }
        throw new IllegalArgumentException();
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if (numeric instanceof RatNum) {
            return RatNum.add((RatNum) numeric, this, i);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object obj) {
        if (obj instanceof RatNum) {
            return RatNum.times(this, (RatNum) obj);
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).mulReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof RatNum) {
            return RatNum.times((RatNum) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric div(Object obj) {
        if (obj instanceof RatNum) {
            return RatNum.divide(this, (RatNum) obj);
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).divReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric divReversed(Numeric numeric) {
        if (numeric instanceof RatNum) {
            return RatNum.divide((RatNum) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public static IntFraction neg(IntFraction intFraction) {
        return new IntFraction(IntNum.neg(intFraction.numerator()), intFraction.denominator());
    }

    public Numeric neg() {
        return neg(this);
    }

    public long longValue() {
        return toExactInt(4).longValue();
    }

    public double doubleValue() {
        boolean isNegative = this.num.isNegative();
        if (!this.den.isZero()) {
            IntNum intNum = this.num;
            if (isNegative) {
                intNum = IntNum.neg(intNum);
            }
            int intLength = intNum.intLength();
            int i = 0;
            int intLength2 = this.den.intLength() + 54;
            if (intLength < intLength2) {
                int i2 = intLength2 - intLength;
                intNum = IntNum.shift(intNum, i2);
                i = -i2;
            }
            IntNum intNum2 = new IntNum();
            IntNum intNum3 = new IntNum();
            IntNum.divide(intNum, this.den, intNum2, intNum3, 3);
            return intNum2.canonicalize().roundToDouble(i, isNegative, !intNum3.canonicalize().isZero());
        } else if (isNegative) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return this.num.isZero() ? Double.NaN : Double.POSITIVE_INFINITY;
        }
    }

    public String toString(int i) {
        return this.num.toString(i) + '/' + this.den.toString(i);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.num);
        objectOutput.writeObject(this.den);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.num = (IntNum) objectInput.readObject();
        this.den = (IntNum) objectInput.readObject();
    }
}
