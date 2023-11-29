package gnu.math;

import java.math.BigDecimal;

public abstract class RatNum extends RealNum {
    public static final IntNum ten_exp_9 = IntNum.make(1000000000);

    public abstract IntNum denominator();

    public boolean isExact() {
        return true;
    }

    public abstract IntNum numerator();

    public final RatNum toExact() {
        return this;
    }

    public static RatNum make(IntNum intNum, IntNum intNum2) {
        IntNum gcd = IntNum.gcd(intNum, intNum2);
        if (intNum2.isNegative()) {
            gcd = IntNum.neg(gcd);
        }
        if (!gcd.isOne()) {
            intNum = IntNum.quotient(intNum, gcd);
            intNum2 = IntNum.quotient(intNum2, gcd);
        }
        return intNum2.isOne() ? intNum : new IntFraction(intNum, intNum2);
    }

    public static RatNum valueOf(BigDecimal bigDecimal) {
        IntNum intNum;
        RatNum valueOf = IntNum.valueOf(bigDecimal.unscaledValue().toString(), 10);
        int scale = bigDecimal.scale();
        while (scale >= 9) {
            valueOf = divide(valueOf, ten_exp_9);
            scale -= 9;
        }
        while (scale <= -9) {
            valueOf = times(valueOf, ten_exp_9);
            scale += 9;
        }
        switch (scale > 0 ? scale : -scale) {
            case 1:
                intNum = IntNum.make(10);
                break;
            case 2:
                intNum = IntNum.make(100);
                break;
            case 3:
                intNum = IntNum.make(1000);
                break;
            case 4:
                intNum = IntNum.make(10000);
                break;
            case 5:
                intNum = IntNum.make(100000);
                break;
            case 6:
                intNum = IntNum.make(1000000);
                break;
            case 7:
                intNum = IntNum.make(10000000);
                break;
            case 8:
                intNum = IntNum.make(100000000);
                break;
            default:
                return valueOf;
        }
        if (scale > 0) {
            return divide(valueOf, intNum);
        }
        return times(valueOf, intNum);
    }

    public static RatNum asRatNumOrNull(Object obj) {
        if (obj instanceof RatNum) {
            return (RatNum) obj;
        }
        if (obj instanceof BigDecimal) {
            return valueOf((BigDecimal) obj);
        }
        return IntNum.asIntNumOrNull(obj);
    }

    public boolean isZero() {
        return numerator().isZero();
    }

    public final RatNum rneg() {
        return neg(this);
    }

    public static RatNum infinity(int i) {
        return new IntFraction(IntNum.make(i), IntNum.zero());
    }

    public static int compare(RatNum ratNum, RatNum ratNum2) {
        return IntNum.compare(IntNum.times(ratNum.numerator(), ratNum2.denominator()), IntNum.times(ratNum2.numerator(), ratNum.denominator()));
    }

    public static boolean equals(RatNum ratNum, RatNum ratNum2) {
        return IntNum.equals(ratNum.numerator(), ratNum2.numerator()) && IntNum.equals(ratNum.denominator(), ratNum2.denominator());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RatNum)) {
            return false;
        }
        return equals(this, (RatNum) obj);
    }

    public static RatNum add(RatNum ratNum, RatNum ratNum2, int i) {
        IntNum numerator = ratNum.numerator();
        IntNum denominator = ratNum.denominator();
        IntNum numerator2 = ratNum2.numerator();
        IntNum denominator2 = ratNum2.denominator();
        if (IntNum.equals(denominator, denominator2)) {
            return make(IntNum.add(numerator, numerator2, i), denominator);
        }
        return make(IntNum.add(IntNum.times(denominator2, numerator), IntNum.times(numerator2, denominator), i), IntNum.times(denominator, denominator2));
    }

    public static RatNum neg(RatNum ratNum) {
        IntNum numerator = ratNum.numerator();
        return make(IntNum.neg(numerator), ratNum.denominator());
    }

    public static RatNum times(RatNum ratNum, RatNum ratNum2) {
        return make(IntNum.times(ratNum.numerator(), ratNum2.numerator()), IntNum.times(ratNum.denominator(), ratNum2.denominator()));
    }

    public static RatNum divide(RatNum ratNum, RatNum ratNum2) {
        return make(IntNum.times(ratNum.numerator(), ratNum2.denominator()), IntNum.times(ratNum.denominator(), ratNum2.numerator()));
    }

    public Numeric power(IntNum intNum) {
        boolean z;
        boolean z2 = true;
        if (intNum.isNegative()) {
            intNum = IntNum.neg(intNum);
            z = true;
        } else {
            z = false;
        }
        if (intNum.words == null) {
            IntNum power = IntNum.power(numerator(), intNum.ival);
            IntNum power2 = IntNum.power(denominator(), intNum.ival);
            return z ? make(power2, power) : make(power, power2);
        }
        double doubleValue = doubleValue();
        if (doubleValue >= 0.0d || !intNum.isOdd()) {
            z2 = false;
        }
        double pow = Math.pow(doubleValue, intNum.doubleValue());
        if (z) {
            pow = 1.0d / pow;
        }
        if (z2) {
            pow = -pow;
        }
        return new DFloNum(pow);
    }

    public RealNum toInt(int i) {
        return IntNum.quotient(numerator(), denominator(), i);
    }

    public IntNum toExactInt(int i) {
        return IntNum.quotient(numerator(), denominator(), i);
    }

    public static RealNum rationalize(RealNum realNum, RealNum realNum2) {
        if (realNum.grt(realNum2)) {
            return simplest_rational2(realNum2, realNum);
        }
        if (!realNum2.grt(realNum)) {
            return realNum;
        }
        if (realNum.sign() > 0) {
            return simplest_rational2(realNum, realNum2);
        }
        if (realNum2.isNegative()) {
            return (RealNum) simplest_rational2((RealNum) realNum2.neg(), (RealNum) realNum.neg()).neg();
        }
        return IntNum.zero();
    }

    private static RealNum simplest_rational2(RealNum realNum, RealNum realNum2) {
        RealNum realNum3 = realNum.toInt(1);
        RealNum realNum4 = realNum2.toInt(1);
        if (!realNum.grt(realNum3)) {
            return realNum3;
        }
        if (realNum3.equals(realNum4)) {
            return (RealNum) realNum3.add(IntNum.one().div(simplest_rational2((RealNum) IntNum.one().div(realNum2.sub(realNum4)), (RealNum) IntNum.one().div(realNum.sub(realNum3)))), 1);
        }
        return (RealNum) realNum3.add(IntNum.one(), 1);
    }
}
