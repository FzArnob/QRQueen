package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

class MulUnit extends Unit implements Externalizable {
    MulUnit next;
    int power1;
    int power2;
    Unit unit1;
    Unit unit2;

    MulUnit(Unit unit, int i, Unit unit3, int i2) {
        this.unit1 = unit;
        this.unit2 = unit3;
        this.power1 = i;
        this.power2 = i2;
        this.dims = Dimensions.product(unit.dims, i, unit3.dims, i2);
        if (i == 1) {
            this.factor = unit.factor;
        } else {
            this.factor = Math.pow(unit.factor, (double) i);
        }
        if (i2 >= 0) {
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                this.factor *= unit3.factor;
            }
        } else {
            int i3 = -i2;
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                this.factor /= unit3.factor;
            }
        }
        this.next = unit.products;
        unit.products = this;
    }

    MulUnit(Unit unit, Unit unit3, int i) {
        this(unit, 1, unit3, i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append(this.unit1);
        if (this.power1 != 1) {
            stringBuffer.append('^');
            stringBuffer.append(this.power1);
        }
        if (this.power2 != 0) {
            stringBuffer.append('*');
            stringBuffer.append(this.unit2);
            if (this.power2 != 1) {
                stringBuffer.append('^');
                stringBuffer.append(this.power2);
            }
        }
        return stringBuffer.toString();
    }

    public Unit sqrt() {
        int i = this.power1;
        if ((i & 1) == 0) {
            int i2 = this.power2;
            if ((i2 & 1) == 0) {
                return times(this.unit1, i >> 1, this.unit2, i2 >> 1);
            }
        }
        return super.sqrt();
    }

    static MulUnit lookup(Unit unit, int i, Unit unit3, int i2) {
        for (MulUnit mulUnit = unit.products; mulUnit != null; mulUnit = mulUnit.next) {
            if (mulUnit.unit1 == unit && mulUnit.unit2 == unit3 && mulUnit.power1 == i && mulUnit.power2 == i2) {
                return mulUnit;
            }
        }
        return null;
    }

    public static MulUnit make(Unit unit, int i, Unit unit3, int i2) {
        MulUnit lookup = lookup(unit, i, unit3, i2);
        if (lookup != null) {
            return lookup;
        }
        return new MulUnit(unit, i, unit3, i2);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.unit1);
        objectOutput.writeInt(this.power1);
        objectOutput.writeObject(this.unit2);
        objectOutput.writeInt(this.power2);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.unit1 = (Unit) objectInput.readObject();
        this.power1 = objectInput.readInt();
        this.unit2 = (Unit) objectInput.readObject();
        this.power2 = objectInput.readInt();
    }

    public Object readResolve() throws ObjectStreamException {
        MulUnit lookup = lookup(this.unit1, this.power1, this.unit2, this.power2);
        return lookup != null ? lookup : this;
    }
}
