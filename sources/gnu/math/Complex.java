package gnu.math;

import org.slf4j.Marker;

public abstract class Complex extends Quantity {
    private static CComplex imMinusOne;
    private static CComplex imOne;

    public Complex number() {
        return this;
    }

    public boolean isExact() {
        return re().isExact() && im().isExact();
    }

    public Complex toExact() {
        RealNum re = re();
        RealNum im = im();
        RatNum exact = re.toExact();
        RatNum exact2 = im.toExact();
        if (exact == re && exact2 == im) {
            return this;
        }
        return new CComplex(exact, exact2);
    }

    public Complex toInexact() {
        if (isExact()) {
            return this;
        }
        return new DComplex(re().doubleValue(), im().doubleValue());
    }

    public static CComplex imOne() {
        if (imOne == null) {
            imOne = new CComplex(IntNum.zero(), IntNum.one());
        }
        return imOne;
    }

    public static CComplex imMinusOne() {
        if (imMinusOne == null) {
            imMinusOne = new CComplex(IntNum.zero(), IntNum.minusOne());
        }
        return imMinusOne;
    }

    public double doubleValue() {
        return re().doubleValue();
    }

    public double doubleImagValue() {
        return im().doubleValue();
    }

    public final double doubleRealValue() {
        return doubleValue();
    }

    public long longValue() {
        return re().longValue();
    }

    public static Complex make(RealNum realNum, RealNum realNum2) {
        if (realNum2.isZero()) {
            return realNum;
        }
        if (!realNum.isExact() || !realNum2.isExact()) {
            return new DComplex(realNum.doubleValue(), realNum2.doubleValue());
        }
        return new CComplex(realNum, realNum2);
    }

    public static Complex make(double d, double d2) {
        if (d2 == 0.0d) {
            return new DFloNum(d);
        }
        return new DComplex(d, d2);
    }

    public static DComplex polar(double d, double d2) {
        return new DComplex(Math.cos(d2) * d, d * Math.sin(d2));
    }

    public static DComplex polar(RealNum realNum, RealNum realNum2) {
        return polar(realNum.doubleValue(), realNum2.doubleValue());
    }

    public static Complex power(Complex complex, Complex complex2) {
        if (complex2 instanceof IntNum) {
            return (Complex) complex.power((IntNum) complex2);
        }
        double doubleRealValue = complex.doubleRealValue();
        double doubleImagValue = complex.doubleImagValue();
        double doubleRealValue2 = complex2.doubleRealValue();
        double doubleImagValue2 = complex2.doubleImagValue();
        if (doubleImagValue == 0.0d && doubleImagValue2 == 0.0d && (doubleRealValue >= 0.0d || Double.isInfinite(doubleRealValue) || Double.isNaN(doubleRealValue))) {
            return new DFloNum(Math.pow(doubleRealValue, doubleRealValue2));
        }
        return DComplex.power(doubleRealValue, doubleImagValue, doubleRealValue2, doubleImagValue2);
    }

    public Numeric abs() {
        return new DFloNum(Math.hypot(doubleRealValue(), doubleImagValue()));
    }

    public RealNum angle() {
        return new DFloNum(Math.atan2(doubleImagValue(), doubleRealValue()));
    }

    public static boolean equals(Complex complex, Complex complex2) {
        return complex.re().equals(complex2.re()) && complex.im().equals(complex.im());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Complex)) {
            return false;
        }
        return equals(this, (Complex) obj);
    }

    public static int compare(Complex complex, Complex complex2) {
        int compare = complex.im().compare(complex2.im());
        if (compare != 0) {
            return compare;
        }
        return complex.re().compare(complex2.re());
    }

    public int compare(Object obj) {
        if (!(obj instanceof Complex)) {
            return ((Numeric) obj).compareReversed(this);
        }
        return compare(this, (Complex) obj);
    }

    public boolean isZero() {
        return re().isZero() && im().isZero();
    }

    public String toString(int i) {
        if (im().isZero()) {
            return re().toString(i);
        }
        String str = im().toString(i) + "i";
        if (str.charAt(0) != '-') {
            str = Marker.ANY_NON_NULL_MARKER + str;
        }
        if (re().isZero()) {
            return str;
        }
        return re().toString(i) + str;
    }

    public static Complex neg(Complex complex) {
        return make(complex.re().rneg(), complex.im().rneg());
    }

    public Numeric neg() {
        return neg(this);
    }

    public static Complex add(Complex complex, Complex complex2, int i) {
        return make(RealNum.add(complex.re(), complex2.re(), i), RealNum.add(complex.im(), complex2.im(), i));
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof Complex) {
            return add(this, (Complex) obj, i);
        }
        return ((Numeric) obj).addReversed(this, i);
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if (numeric instanceof Complex) {
            return add((Complex) numeric, this, i);
        }
        throw new IllegalArgumentException();
    }

    public static Complex times(Complex complex, Complex complex2) {
        RealNum re = complex.re();
        RealNum im = complex.im();
        RealNum re2 = complex2.re();
        RealNum im2 = complex2.im();
        return make(RealNum.add(RealNum.times(re, re2), RealNum.times(im, im2), -1), RealNum.add(RealNum.times(re, im2), RealNum.times(im, re2), 1));
    }

    public Numeric mul(Object obj) {
        if (obj instanceof Complex) {
            return times(this, (Complex) obj);
        }
        return ((Numeric) obj).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof Complex) {
            return times((Complex) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public static Complex divide(Complex complex, Complex complex2) {
        if (!complex.isExact() || !complex2.isExact()) {
            return DComplex.div(complex.doubleRealValue(), complex.doubleImagValue(), complex2.doubleRealValue(), complex2.doubleImagValue());
        }
        RealNum re = complex.re();
        RealNum im = complex.im();
        RealNum re2 = complex2.re();
        RealNum im2 = complex2.im();
        RealNum add = RealNum.add(RealNum.times(re2, re2), RealNum.times(im2, im2), 1);
        return make(RealNum.divide(RealNum.add(RealNum.times(re, re2), RealNum.times(im, im2), 1), add), RealNum.divide(RealNum.add(RealNum.times(im, re2), RealNum.times(re, im2), -1), add));
    }

    public Numeric div(Object obj) {
        if (obj instanceof Complex) {
            return divide(this, (Complex) obj);
        }
        return ((Numeric) obj).divReversed(this);
    }

    public Numeric divReversed(Numeric numeric) {
        if (numeric instanceof Complex) {
            return divide((Complex) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public Complex exp() {
        return polar(Math.exp(doubleRealValue()), doubleImagValue());
    }

    public Complex log() {
        return DComplex.log(doubleRealValue(), doubleImagValue());
    }

    public Complex sqrt() {
        return DComplex.sqrt(doubleRealValue(), doubleImagValue());
    }
}
