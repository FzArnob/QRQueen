package gnu.xquery.util;

import gnu.kawa.functions.AddOp;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Reduce {
    public static Object sum(Object obj) throws Throwable {
        return sum(obj, IntNum.zero());
    }

    public static Object sum(Object obj, Object obj2) throws Throwable {
        if (!(obj instanceof Values)) {
            return (Number) MinMax.convert(obj);
        }
        TreeList treeList = (TreeList) obj;
        int i = 0;
        Object posNext = treeList.getPosNext(0);
        if (posNext == Sequence.eofValue) {
            return obj2;
        }
        Object convert = MinMax.convert(posNext);
        while (true) {
            i = treeList.nextPos(i);
            Object posNext2 = treeList.getPosNext(i);
            if (posNext2 == Sequence.eofValue) {
                return convert;
            }
            convert = AddOp.apply2(1, convert, MinMax.convert(posNext2));
        }
    }
}
