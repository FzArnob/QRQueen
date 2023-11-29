package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CQuantity extends Quantity implements Externalizable {
    Complex num;
    Unit unt;

    public CQuantity(Complex complex, Unit unit) {
        this.num = complex;
        this.unt = unit;
    }

    public CQuantity(RealNum realNum, RealNum realNum2, Unit unit) {
        this.num = new CComplex(realNum, realNum2);
        this.unt = unit;
    }

    public Complex number() {
        return this.num;
    }

    public Unit unit() {
        return this.unt;
    }

    public boolean isExact() {
        return this.num.isExact();
    }

    public boolean isZero() {
        return this.num.isZero();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.num);
        objectOutput.writeObject(this.unt);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.num = (Complex) objectInput.readObject();
        this.unt = (Unit) objectInput.readObject();
    }
}
