package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class ArraySet extends ProcedureN {
    public static final ArraySet arraySet = new ArraySet();

    public static void arraySet(Array array, Sequence sequence, Object obj) {
        int size = sequence.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Number) sequence.get(i)).intValue();
        }
        array.set(iArr, obj);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        if (!(obj2 instanceof Sequence)) {
            return super.apply3(obj, obj2, obj3);
        }
        arraySet((Array) obj, (Sequence) obj2, obj3);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Array array = objArr[0];
        if (objArr.length == 3) {
            Sequence sequence = objArr[1];
            if (sequence instanceof Sequence) {
                arraySet(array, sequence, objArr[2]);
                return Values.empty;
            }
        }
        int length = objArr.length - 2;
        int[] iArr = new int[length];
        int i = length;
        while (true) {
            i--;
            if (i >= 0) {
                iArr[i] = objArr[i + 1].intValue();
            } else {
                array.set(iArr, objArr[length + 1]);
                return Values.empty;
            }
        }
    }
}
