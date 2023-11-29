package gnu.math;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class NamedUnit extends Unit implements Externalizable {
    Unit base;
    NamedUnit chain;
    String name;
    double scale;

    public NamedUnit() {
    }

    public NamedUnit(String str, DQuantity dQuantity) {
        this.name = str.intern();
        this.scale = dQuantity.factor;
        this.base = dQuantity.unt;
        init();
    }

    public NamedUnit(String str, double d, Unit unit) {
        this.name = str;
        this.base = unit;
        this.scale = d;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.factor = this.scale * this.base.factor;
        this.dims = this.base.dims;
        String intern = this.name.intern();
        this.name = intern;
        int hashCode = (intern.hashCode() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % table.length;
        this.chain = table[hashCode];
        table[hashCode] = this;
    }

    public String getName() {
        return this.name;
    }

    public static NamedUnit lookup(String str) {
        String intern = str.intern();
        for (NamedUnit namedUnit = table[(intern.hashCode() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % table.length]; namedUnit != null; namedUnit = namedUnit.chain) {
            if (namedUnit.name == intern) {
                return namedUnit;
            }
        }
        return null;
    }

    public static NamedUnit lookup(String str, double d, Unit unit) {
        String intern = str.intern();
        for (NamedUnit namedUnit = table[(intern.hashCode() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % table.length]; namedUnit != null; namedUnit = namedUnit.chain) {
            if (namedUnit.name == intern && namedUnit.scale == d && namedUnit.base == unit) {
                return namedUnit;
            }
        }
        return null;
    }

    public static NamedUnit make(String str, double d, Unit unit) {
        NamedUnit lookup = lookup(str, d, unit);
        return lookup == null ? new NamedUnit(str, d, unit) : lookup;
    }

    public static NamedUnit make(String str, Quantity quantity) {
        double d;
        if (quantity instanceof DQuantity) {
            d = ((DQuantity) quantity).factor;
        } else if (quantity.imValue() == 0.0d) {
            d = quantity.re().doubleValue();
        } else {
            throw new ArithmeticException("defining " + str + " using complex value");
        }
        Unit unit = quantity.unit();
        NamedUnit lookup = lookup(str, d, unit);
        return lookup == null ? new NamedUnit(str, d, unit) : lookup;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.name);
        objectOutput.writeDouble(this.scale);
        objectOutput.writeObject(this.base);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = objectInput.readUTF();
        this.scale = objectInput.readDouble();
        this.base = (Unit) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        NamedUnit lookup = lookup(this.name, this.scale, this.base);
        if (lookup != null) {
            return lookup;
        }
        init();
        return this;
    }
}
