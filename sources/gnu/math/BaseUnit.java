package gnu.math;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class BaseUnit extends NamedUnit implements Externalizable {
    static int base_count = 0;
    private static final String unitName = "(name)";
    String dimension;
    int index;

    public Unit unit() {
        return this;
    }

    public String getDimension() {
        return this.dimension;
    }

    public BaseUnit() {
        this.name = unitName;
        this.index = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.dims = Dimensions.Empty;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.base = this;
        this.scale = 1.0d;
        this.dims = new Dimensions(this);
        super.init();
        int i = base_count;
        base_count = i + 1;
        this.index = i;
    }

    public BaseUnit(String str) {
        this.name = str;
        init();
    }

    public BaseUnit(String str, String str2) {
        this.name = str;
        this.dimension = str2;
        init();
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public static BaseUnit lookup(String str, String str2) {
        String intern = str.intern();
        if (intern == unitName && str2 == null) {
            return Unit.Empty;
        }
        for (NamedUnit namedUnit = table[(intern.hashCode() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % table.length]; namedUnit != null; namedUnit = namedUnit.chain) {
            if (namedUnit.name == intern && (namedUnit instanceof BaseUnit)) {
                BaseUnit baseUnit = (BaseUnit) namedUnit;
                if (baseUnit.dimension == str2) {
                    return baseUnit;
                }
            }
        }
        return null;
    }

    public static BaseUnit make(String str, String str2) {
        BaseUnit lookup = lookup(str, str2);
        return lookup == null ? new BaseUnit(str, str2) : lookup;
    }

    public static int compare(BaseUnit baseUnit, BaseUnit baseUnit2) {
        int compareTo = baseUnit.name.compareTo(baseUnit2.name);
        if (compareTo != 0) {
            return compareTo;
        }
        String str = baseUnit.dimension;
        String str2 = baseUnit2.dimension;
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.name);
        objectOutput.writeObject(this.dimension);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = objectInput.readUTF();
        this.dimension = (String) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        BaseUnit lookup = lookup(this.name, this.dimension);
        if (lookup != null) {
            return lookup;
        }
        init();
        return this;
    }
}
