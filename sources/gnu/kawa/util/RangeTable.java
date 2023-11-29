package gnu.kawa.util;

import java.util.Hashtable;

public class RangeTable implements Cloneable {
    Hashtable hash = new Hashtable(200);
    Object[] index = new Object[128];

    public Object lookup(int i, Object obj) {
        if ((i & 127) == i) {
            return this.index[i];
        }
        return this.hash.get(new Integer(i));
    }

    public void set(int i, int i2, Object obj) {
        if (i <= i2) {
            while (true) {
                if ((i & 127) == i) {
                    this.index[i] = obj;
                } else {
                    this.hash.put(new Integer(i), obj);
                }
                if (i != i2) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void set(int i, Object obj) {
        set(i, i, obj);
    }

    public void remove(int i, int i2) {
        if (i <= i2) {
            while (true) {
                if ((i & 127) == i) {
                    this.index[i] = null;
                } else {
                    this.hash.remove(new Integer(i));
                }
                if (i != i2) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void remove(int i) {
        remove(i, i);
    }

    public RangeTable copy() {
        RangeTable rangeTable = new RangeTable();
        rangeTable.index = (Object[]) this.index.clone();
        rangeTable.hash = (Hashtable) this.hash.clone();
        return rangeTable;
    }

    public Object clone() {
        return copy();
    }
}
