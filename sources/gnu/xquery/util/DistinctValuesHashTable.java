package gnu.xquery.util;

import gnu.kawa.util.GeneralHashTable;
import gnu.math.Numeric;
import gnu.math.RealNum;

/* compiled from: DistinctValues */
class DistinctValuesHashTable extends GeneralHashTable {
    NamedCollator collator;

    public DistinctValuesHashTable(NamedCollator namedCollator) {
        this.collator = namedCollator;
    }

    public int hash(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (!(obj instanceof Number) || (!(obj instanceof RealNum) && (obj instanceof Numeric))) {
            return obj.hashCode();
        }
        int floatToIntBits = Float.floatToIntBits(((Number) obj).floatValue());
        if (floatToIntBits == Integer.MIN_VALUE) {
            return 0;
        }
        return floatToIntBits;
    }

    public boolean matches(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (!NumberValue.isNaN(obj) || !NumberValue.isNaN(obj2)) {
            return Compare.apply(72, obj, obj2, this.collator);
        }
        return true;
    }
}
