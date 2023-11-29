package kawa.standard;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class append extends ProcedureN {
    public static final append append;

    static {
        append append2 = new append();
        append = append2;
        append2.setName("append");
    }

    public Object applyN(Object[] objArr) {
        return append$V(objArr);
    }

    public static Object append$V(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return LList.Empty;
        }
        int i = length - 1;
        Pair pair = objArr[i];
        while (true) {
            i--;
            if (i < 0) {
                return pair;
            }
            Object obj = objArr[i];
            Pair pair2 = null;
            Pair pair3 = null;
            while (obj instanceof Pair) {
                Pair pair4 = (Pair) obj;
                Pair pair5 = new Pair(pair4.getCar(), (Object) null);
                if (pair2 == null) {
                    pair3 = pair5;
                } else {
                    pair2.setCdr(pair5);
                }
                obj = pair4.getCdr();
                pair2 = pair5;
            }
            if (obj != LList.Empty) {
                throw new WrongType((Procedure) append, i + 1, objArr[i], "list");
            } else if (pair2 != null) {
                pair2.setCdr(pair);
                pair = pair3;
            }
        }
    }
}
