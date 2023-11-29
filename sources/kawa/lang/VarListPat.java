package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.Pair;

public class VarListPat extends Pattern {
    int min_length;

    public VarListPat(int i) {
        this.min_length = i;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        int i2 = 0;
        while (i2 < this.min_length) {
            if (!(obj instanceof Pair)) {
                return false;
            }
            Pair pair = (Pair) obj;
            objArr[i + i2] = pair.getCar();
            obj = pair.getCdr();
            i2++;
        }
        objArr[i + i2] = obj;
        return true;
    }

    public int varCount() {
        return this.min_length + 1;
    }

    public void print(Consumer consumer) {
        consumer.write("#<varlist-pattern min:");
        consumer.writeInt(this.min_length);
        consumer.write(62);
    }
}
