package kawa.standard;

import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class vector_append extends ProcedureN {
    public static final vector_append vectorAppend = new vector_append("vector-append");

    public vector_append(String str) {
        super(str);
    }

    public Object applyN(Object[] objArr) {
        return apply$V(objArr);
    }

    public static FVector apply$V(Object[] objArr) {
        int length = objArr.length;
        int i = length;
        int i2 = 0;
        while (true) {
            i--;
            if (i >= 0) {
                FVector fVector = objArr[i];
                if (fVector instanceof FVector) {
                    i2 += fVector.size();
                } else {
                    int listLength = LList.listLength(fVector, false);
                    if (listLength >= 0) {
                        i2 += listLength;
                    } else {
                        throw new WrongType((Procedure) vectorAppend, i, (Object) fVector, "list or vector");
                    }
                }
            } else {
                Object[] objArr2 = new Object[i2];
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    Object obj = objArr[i4];
                    if (obj instanceof FVector) {
                        FVector fVector2 = (FVector) obj;
                        int size = fVector2.size();
                        int i5 = 0;
                        while (i5 < size) {
                            objArr2[i3] = fVector2.get(i5);
                            i5++;
                            i3++;
                        }
                    } else if (obj instanceof Pair) {
                        while (obj != LList.Empty) {
                            Pair pair = (Pair) obj;
                            objArr2[i3] = pair.getCar();
                            obj = pair.getCdr();
                            i3++;
                        }
                    }
                }
                return new FVector(objArr2);
            }
        }
    }
}
