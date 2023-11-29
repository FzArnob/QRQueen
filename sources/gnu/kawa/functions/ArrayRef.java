package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;

public class ArrayRef extends ProcedureN {
    public static final ArrayRef arrayRef = new ArrayRef();

    public static Object arrayRef(Array array, Sequence sequence) {
        int size = sequence.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Number) sequence.get(i)).intValue();
        }
        return array.get(iArr);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        if (obj2 instanceof Sequence) {
            return arrayRef((Array) obj, (Sequence) obj2);
        }
        return super.apply2(obj, obj2);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Array array = objArr[0];
        if (objArr.length == 2) {
            Sequence sequence = objArr[1];
            if (sequence instanceof Sequence) {
                return arrayRef(array, sequence);
            }
        }
        int[] iArr = new int[(objArr.length - 1)];
        int length = objArr.length - 1;
        while (true) {
            length--;
            if (length < 0) {
                return array.get(iArr);
            }
            iArr[length] = objArr[length + 1].intValue();
        }
    }
}
