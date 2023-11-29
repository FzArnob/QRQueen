package gnu.xquery.util;

import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Average extends Procedure1 {
    public static final Average avg = new Average("avg");

    public Average(String str) {
        super(str);
    }

    public Object apply1(Object obj) throws Throwable {
        int i;
        Values values = Values.empty;
        if (obj instanceof Values) {
            TreeList treeList = (TreeList) obj;
            int i2 = 0;
            Values values2 = values;
            i = 0;
            while (true) {
                Object posNext = treeList.getPosNext(i2);
                if (posNext == Sequence.eofValue) {
                    break;
                }
                i++;
                values2 = values2 == Values.empty ? posNext : ArithOp.add.apply2(values2, posNext);
                i2 = treeList.nextPos(i2);
            }
            obj = values2;
        } else {
            i = 1;
        }
        if (obj == Values.empty) {
            return obj;
        }
        return ArithOp.div.apply2(obj, IntNum.make(i));
    }
}
