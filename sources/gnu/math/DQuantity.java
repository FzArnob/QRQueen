package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DQuantity extends Quantity implements Externalizable {
    double factor;
    Unit unt;

    public boolean isExact() {
        return false;
    }

    public final Unit unit() {
        return this.unt;
    }

    public final Complex number() {
        return new DFloNum(this.factor);
    }

    public final RealNum re() {
        return new DFloNum(this.factor);
    }

    public final double doubleValue() {
        return this.factor * this.unt.factor;
    }

    public DQuantity(double d, Unit unit) {
        this.factor = d;
        this.unt = unit;
    }

    public boolean isZero() {
        return this.factor == 0.0d;
    }

    public static DQuantity add(DQuantity dQuantity, DQuantity dQuantity2, double d) {
        if (dQuantity.dimensions() == dQuantity2.dimensions()) {
            return new DQuantity(dQuantity.factor + (d * (dQuantity2.unit().factor / dQuantity.unit().factor) * dQuantity2.factor), dQuantity.unit());
        }
        throw new ArithmeticException("units mis-match");
    }

    public static DQuantity times(DQuantity dQuantity, DQuantity dQuantity2) {
        return new DQuantity(dQuantity.factor * dQuantity2.factor, Unit.times(dQuantity.unit(), dQuantity2.unit()));
    }

    public static DQuantity divide(DQuantity dQuantity, DQuantity dQuantity2) {
        return new DQuantity(dQuantity.factor / dQuantity2.factor, Unit.divide(dQuantity.unit(), dQuantity2.unit()));
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof DQuantity) {
            return add(this, (DQuantity) obj, (double) i);
        }
        if (dimensions() == Dimensions.Empty && (obj instanceof RealNum)) {
            return new DQuantity(this.factor + (((double) i) * ((RealNum) obj).doubleValue()), unit());
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).addReversed(this, i);
        }
        throw new IllegalArgumentException();
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if (dimensions() == Dimensions.Empty && (numeric instanceof RealNum)) {
            return new DFloNum(((RealNum) numeric).doubleValue() + (((double) i) * this.factor));
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object obj) {
        if (obj instanceof DQuantity) {
            return times(this, (DQuantity) obj);
        }
        if (obj instanceof RealNum) {
            return new DQuantity(this.factor * ((RealNum) obj).doubleValue(), unit());
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).mulReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof RealNum) {
            return new DQuantity(((RealNum) numeric).doubleValue() * this.factor, unit());
        }
        throw new IllegalArgumentException();
    }

    public Numeric div(Object obj) {
        if (obj instanceof DQuantity) {
            DQuantity dQuantity = (DQuantity) obj;
            if (dimensions() == dQuantity.dimensions()) {
                return new DFloNum((this.factor * unit().doubleValue()) / (dQuantity.factor * dQuantity.unit().factor));
            }
            return divide(this, dQuantity);
        } else if (obj instanceof RealNum) {
            return new DQuantity(this.factor / ((RealNum) obj).doubleValue(), unit());
        } else {
            if (obj instanceof Numeric) {
                return ((Numeric) obj).divReversed(this);
            }
            throw new IllegalArgumentException();
        }
    }

    public Numeric divReversed(Numeric numeric) {
        if (numeric instanceof RealNum) {
            return new DQuantity(((RealNum) numeric).doubleValue() / this.factor, Unit.divide(Unit.Empty, unit()));
        }
        throw new IllegalArgumentException();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeDouble(this.factor);
        objectOutput.writeObject(this.unt);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.factor = objectInput.readDouble();
        this.unt = (Unit) objectInput.readObject();
    }
}
