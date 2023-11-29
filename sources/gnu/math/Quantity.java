package gnu.math;

public abstract class Quantity extends Numeric {
    public abstract Complex number();

    public Unit unit() {
        return Unit.Empty;
    }

    public Dimensions dimensions() {
        return unit().dimensions();
    }

    public RealNum re() {
        return number().re();
    }

    public RealNum im() {
        return number().im();
    }

    public final double reValue() {
        return doubleValue();
    }

    public final double imValue() {
        return doubleImagValue();
    }

    public double doubleValue() {
        return unit().doubleValue() * re().doubleValue();
    }

    public double doubleImagValue() {
        return unit().doubleValue() * im().doubleValue();
    }

    public static Quantity make(Complex complex, Unit unit) {
        if (unit == Unit.Empty) {
            return complex;
        }
        if (complex instanceof DFloNum) {
            return new DQuantity(complex.doubleValue(), unit);
        }
        return new CQuantity(complex, unit);
    }

    public static Quantity make(RealNum realNum, RealNum realNum2, Unit unit) {
        if (unit == Unit.Empty) {
            return Complex.make(realNum, realNum2);
        }
        if (!realNum2.isZero() || (realNum.isExact() && realNum2.isExact())) {
            return new CQuantity(realNum, realNum2, unit);
        }
        return new DQuantity(realNum.doubleValue(), unit);
    }

    public static Quantity make(double d, double d2, Unit unit) {
        if (unit == Unit.Empty) {
            return Complex.make(d, d2);
        }
        if (d2 == 0.0d) {
            return new DQuantity(d, unit);
        }
        return new CQuantity(new DFloNum(d), new DFloNum(d2), unit);
    }

    public Numeric neg() {
        return make((Complex) number().neg(), unit());
    }

    public Numeric abs() {
        return make((Complex) number().abs(), unit());
    }

    public static int compare(Quantity quantity, Quantity quantity2) {
        if (quantity.unit() == quantity2.unit()) {
            return Complex.compare(quantity.number(), quantity2.number());
        }
        if (quantity.dimensions() == quantity2.dimensions() && quantity.imValue() == quantity2.imValue()) {
            return DFloNum.compare(quantity.reValue(), quantity2.reValue());
        }
        return -3;
    }

    public int compare(Object obj) {
        if (!(obj instanceof Quantity)) {
            return ((Numeric) obj).compareReversed(this);
        }
        return compare(this, (Quantity) obj);
    }

    public int compareReversed(Numeric numeric) {
        if (numeric instanceof Quantity) {
            return compare((Quantity) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity add(Quantity quantity, Quantity quantity2, int i) {
        if (quantity.unit() == quantity2.unit()) {
            return make(Complex.add(quantity.number(), quantity2.number(), i), quantity.unit());
        }
        if (quantity.dimensions() == quantity2.dimensions()) {
            double doubleValue = quantity.unit().doubleValue();
            double d = (double) i;
            return make((quantity.reValue() + (quantity2.reValue() * d)) / doubleValue, (quantity.imValue() + (d * quantity2.imValue())) / doubleValue, quantity.unit());
        }
        throw new ArithmeticException("units mis-match");
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof Quantity) {
            return add(this, (Quantity) obj, i);
        }
        return ((Numeric) obj).addReversed(this, i);
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if (numeric instanceof Quantity) {
            return add((Quantity) numeric, this, i);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity times(Quantity quantity, Quantity quantity2) {
        return make((Complex) quantity.number().mul(quantity2.number()), Unit.times(quantity.unit(), quantity2.unit()));
    }

    public Numeric mul(Object obj) {
        if (obj instanceof Quantity) {
            return times(this, (Quantity) obj);
        }
        return ((Numeric) obj).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof Quantity) {
            return times((Quantity) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity divide(Quantity quantity, Quantity quantity2) {
        return make((Complex) quantity.number().div(quantity2.number()), Unit.divide(quantity.unit(), quantity2.unit()));
    }

    public Numeric div(Object obj) {
        if (obj instanceof Quantity) {
            return divide(this, (Quantity) obj);
        }
        return ((Numeric) obj).divReversed(this);
    }

    public Numeric divReversed(Numeric numeric) {
        if (numeric instanceof Quantity) {
            return divide((Quantity) numeric, this);
        }
        throw new IllegalArgumentException();
    }

    public String toString(int i) {
        String complex = number().toString(i);
        if (unit() == Unit.Empty) {
            return complex;
        }
        return complex + unit().toString();
    }
}
