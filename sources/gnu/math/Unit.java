package gnu.math;

public abstract class Unit extends Quantity {
    public static BaseUnit Empty = new BaseUnit();
    public static double NON_COMBINABLE = 0.0d;
    public static final Unit cm;
    public static final NamedUnit date;
    public static final BaseUnit duration;
    public static final BaseUnit gram = new BaseUnit("g", "Mass");
    public static final Unit hour;
    public static final Unit in;
    public static final BaseUnit meter;
    public static final Unit minute;
    public static final Unit mm;
    public static final NamedUnit month;
    public static final Unit pica;
    public static final Unit pt;
    public static final Unit radian = define("rad", 1.0d, Empty);
    public static final NamedUnit second;
    static NamedUnit[] table = new NamedUnit[100];
    Unit base;
    Dimensions dims;
    double factor = 1.0d;
    MulUnit products;

    public String getName() {
        return null;
    }

    public boolean isExact() {
        return false;
    }

    public final boolean isZero() {
        return false;
    }

    public Unit unit() {
        return this;
    }

    static {
        Dimensions.Empty.bases[0] = Empty;
        BaseUnit baseUnit = new BaseUnit("m", "Length");
        meter = baseUnit;
        BaseUnit baseUnit2 = new BaseUnit("duration", "Time");
        duration = baseUnit2;
        Unit define = define("cm", 0.01d, baseUnit);
        cm = define;
        mm = define("mm", 0.1d, define);
        in = define("in", 0.0254d, baseUnit);
        pt = define("pt", 3.527778E-4d, baseUnit);
        pica = define("pica", 0.004233333d, baseUnit);
        date = new NamedUnit("date", NON_COMBINABLE, baseUnit2);
        NamedUnit namedUnit = new NamedUnit("s", NON_COMBINABLE, baseUnit2);
        second = namedUnit;
        month = new NamedUnit("month", NON_COMBINABLE, baseUnit2);
        Unit define2 = define("min", 60.0d, namedUnit);
        minute = define2;
        hour = define("hour", 60.0d, define2);
    }

    public final Dimensions dimensions() {
        return this.dims;
    }

    public final double doubleValue() {
        return this.factor;
    }

    public int hashCode() {
        return this.dims.hashCode();
    }

    static Unit times(Unit unit, int i, BaseUnit baseUnit, int i2) {
        int i3 = 0;
        if (unit == baseUnit) {
            i += i2;
            baseUnit = Empty;
            i2 = 0;
        }
        if (i == 0 || unit == Empty) {
            i = i2;
            Unit unit2 = baseUnit;
            baseUnit = Empty;
            unit = unit2;
        } else {
            i3 = i2;
        }
        if (i3 == 0 || baseUnit == Empty) {
            if (i == 1) {
                return unit;
            }
            if (i == 0) {
                return Empty;
            }
        }
        if (unit instanceof MulUnit) {
            MulUnit mulUnit = (MulUnit) unit;
            if (mulUnit.unit1 == baseUnit) {
                return times(baseUnit, (mulUnit.power1 * i) + i3, mulUnit.unit2, mulUnit.power2 * i);
            }
            if (mulUnit.unit2 == baseUnit) {
                return times(mulUnit.unit1, mulUnit.power1 * i, baseUnit, (mulUnit.power2 * i) + i3);
            }
            if (baseUnit instanceof MulUnit) {
                MulUnit mulUnit2 = (MulUnit) baseUnit;
                if (mulUnit.unit1 == mulUnit2.unit1 && mulUnit.unit2 == mulUnit2.unit2) {
                    return times(mulUnit.unit1, (mulUnit.power1 * i) + (mulUnit2.power1 * i3), mulUnit.unit2, (mulUnit.power2 * i) + (mulUnit2.power2 * i3));
                }
                if (mulUnit.unit1 == mulUnit2.unit2 && mulUnit.unit2 == mulUnit2.unit1) {
                    return times(mulUnit.unit1, (mulUnit.power1 * i) + (mulUnit2.power2 * i3), mulUnit.unit2, (mulUnit.power2 * i) + (mulUnit2.power1 * i3));
                }
            }
        }
        if (baseUnit instanceof MulUnit) {
            MulUnit mulUnit3 = (MulUnit) baseUnit;
            if (mulUnit3.unit1 == unit) {
                return times(unit, i + (mulUnit3.power1 * i3), mulUnit3.unit2, mulUnit3.power2 * i3);
            }
            if (mulUnit3.unit2 == unit) {
                return times(mulUnit3.unit1, mulUnit3.power1 * i3, unit, i + (mulUnit3.power2 * i3));
            }
        }
        return MulUnit.make(unit, i, baseUnit, i3);
    }

    public static Unit times(Unit unit, Unit unit2) {
        return times(unit, 1, unit2, 1);
    }

    public static Unit divide(Unit unit, Unit unit2) {
        return times(unit, 1, unit2, -1);
    }

    public static Unit pow(Unit unit, int i) {
        return times(unit, i, Empty, 0);
    }

    Unit() {
    }

    public static NamedUnit make(String str, Quantity quantity) {
        return NamedUnit.make(str, quantity);
    }

    public static Unit define(String str, DQuantity dQuantity) {
        return new NamedUnit(str, dQuantity);
    }

    public static Unit define(String str, double d, Unit unit) {
        return new NamedUnit(str, d, unit);
    }

    public Complex number() {
        return DFloNum.one();
    }

    public Numeric power(IntNum intNum) {
        if (intNum.words == null) {
            return pow(this, intNum.ival);
        }
        throw new ArithmeticException("Unit raised to bignum power");
    }

    public Unit sqrt() {
        if (this == Empty) {
            return this;
        }
        throw new RuntimeException("unimplemented Unit.sqrt");
    }

    public static NamedUnit lookup(String str) {
        return NamedUnit.lookup(str);
    }

    public String toString(double d) {
        String d2 = Double.toString(d);
        if (this == Empty) {
            return d2;
        }
        return d2 + toString();
    }

    public String toString(RealNum realNum) {
        return toString(realNum.doubleValue());
    }

    public String toString() {
        String name = getName();
        if (name != null) {
            return name;
        }
        if (this == Empty) {
            return "unit";
        }
        return Double.toString(this.factor) + "<unnamed unit>";
    }
}
