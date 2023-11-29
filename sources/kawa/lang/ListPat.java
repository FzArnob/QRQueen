package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.ReportFormat;

public class ListPat extends Pattern {
    Object default_value;
    int max_length;
    int min_length;

    public ListPat(int i) {
        this.min_length = i;
        this.max_length = i;
    }

    public ListPat(int i, int i2) {
        this.min_length = i;
        this.max_length = i2;
    }

    public ListPat(int i, int i2, Object obj) {
        this.min_length = i;
        this.max_length = i2;
        this.default_value = obj;
    }

    public static boolean match(int i, int i2, Object obj, Object obj2, Object[] objArr, int i3) {
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                break;
            } else if (obj2 instanceof Pair) {
                Pair pair = (Pair) obj2;
                objArr[i3 + i4] = pair.getCar();
                obj2 = pair.getCdr();
                i4++;
            } else if (i4 < i) {
                return false;
            }
        }
        if (i4 == i2 && obj2 != LList.Empty) {
            return false;
        }
        while (i4 < i2) {
            objArr[i3 + i4] = obj;
            i4++;
        }
        return true;
    }

    public static Object[] match(int i, int i2, Object obj, Object obj2) {
        Object[] objArr = new Object[i2];
        if (match(i, i2, obj, obj2, objArr, 0)) {
            return objArr;
        }
        return null;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        return match(this.min_length, this.max_length, this.default_value, obj, objArr, i);
    }

    public int varCount() {
        return this.max_length;
    }

    public void print(Consumer consumer) {
        consumer.write("#<list-pattern min:");
        consumer.write(Integer.toString(this.min_length));
        consumer.write(" max:");
        consumer.write(Integer.toString(this.max_length));
        consumer.write(" default:");
        ReportFormat.print(this.default_value, consumer);
        consumer.write(62);
    }
}
