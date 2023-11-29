package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.lists.GeneralArray;
import gnu.lists.SimpleVector;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Arrays {
    static final int[] shapeStrides = {2, 1};
    static final int[] zeros2 = new int[2];

    public static Array shape(Object[] objArr) {
        int length = objArr.length;
        if ((length & 1) == 0) {
            return new FVector(objArr).transpose(zeros2, new int[]{length >> 1, 2}, 0, shapeStrides);
        }
        throw new RuntimeException("shape: not an even number of arguments");
    }

    public static Array make(Array array, Object obj) {
        int size = array.getSize(0);
        int[] iArr = new int[size];
        int[] iArr2 = null;
        int i = size;
        int i2 = 1;
        while (true) {
            i--;
            if (i < 0) {
                return GeneralArray.makeSimple(iArr2, iArr, new FVector(i2, obj));
            }
            int i3 = i * 2;
            int intValue = ((Number) array.getRowMajor(i3)).intValue();
            int intValue2 = ((Number) array.getRowMajor(i3 + 1)).intValue() - intValue;
            iArr[i] = intValue2;
            if (intValue != 0) {
                if (iArr2 == null) {
                    iArr2 = new int[size];
                }
                iArr2[i] = intValue;
            }
            i2 *= intValue2;
        }
    }

    public static Array makeSimple(Array array, SimpleVector simpleVector) {
        int size = array.getSize(0);
        int[] iArr = new int[size];
        int[] iArr2 = null;
        int i = size;
        while (true) {
            i--;
            if (i < 0) {
                return GeneralArray.makeSimple(iArr2, iArr, simpleVector);
            }
            int i2 = i * 2;
            int intValue = ((Number) array.getRowMajor(i2)).intValue();
            iArr[i] = ((Number) array.getRowMajor(i2 + 1)).intValue() - intValue;
            if (intValue != 0) {
                if (iArr2 == null) {
                    iArr2 = new int[size];
                }
                iArr2[i] = intValue;
            }
        }
    }

    public static int effectiveIndex(Array array, Procedure procedure, Object[] objArr, int[] iArr) throws Throwable {
        Object applyN = procedure.applyN(objArr);
        int i = 0;
        if (applyN instanceof Values) {
            Values values = (Values) applyN;
            int i2 = 0;
            while (true) {
                i = values.nextPos(i);
                if (i == 0) {
                    break;
                }
                iArr[i2] = ((Number) values.getPosPrevious(i)).intValue();
                i2++;
            }
        } else {
            iArr[0] = ((Number) applyN).intValue();
        }
        return array.getEffectiveIndex(iArr);
    }

    public static Array shareArray(Array array, Array array2, Procedure procedure) throws Throwable {
        int i = 0;
        int size = array2.getSize(0);
        Object[] objArr = new Object[size];
        int[] iArr = new int[size];
        int[] iArr2 = new int[size];
        int i2 = size;
        boolean z = false;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            int i3 = i2 * 2;
            Object rowMajor = array2.getRowMajor(i3);
            objArr[i2] = rowMajor;
            int intValue = ((Number) rowMajor).intValue();
            iArr2[i2] = intValue;
            int intValue2 = ((Number) array2.getRowMajor(i3 + 1)).intValue() - intValue;
            iArr[i2] = intValue2;
            if (intValue2 <= 0) {
                z = true;
            }
        }
        int rank = array.rank();
        int[] iArr3 = new int[size];
        if (!z) {
            int[] iArr4 = new int[rank];
            int effectiveIndex = effectiveIndex(array, procedure, objArr, iArr4);
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                int i4 = iArr[size];
                int i5 = iArr2[size];
                if (i4 <= 1) {
                    iArr3[size] = 0;
                } else {
                    Object obj = objArr[size];
                    objArr[size] = IntNum.make(i5 + 1);
                    iArr3[size] = effectiveIndex(array, procedure, objArr, iArr4) - effectiveIndex;
                    objArr[size] = obj;
                }
            }
            i = effectiveIndex;
        }
        return array.transpose(iArr2, iArr, i, iArr3);
    }
}
