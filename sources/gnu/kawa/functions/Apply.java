package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class Apply extends ProcedureN {
    ApplyToArgs applyToArgs;

    public Apply(String str, ApplyToArgs applyToArgs2) {
        super(str);
        this.applyToArgs = applyToArgs2;
    }

    public static Object[] getArguments(Object[] objArr, int i, Procedure procedure) {
        int i2;
        int length = objArr.length;
        if (length >= i + 1) {
            Object obj = objArr[length - 1];
            boolean z = obj instanceof Object[];
            if (z) {
                Object[] objArr2 = (Object[]) obj;
                if (length == 2) {
                    return objArr2;
                }
                i2 = objArr2.length;
            } else {
                i2 = obj instanceof Sequence ? ((Sequence) obj).size() : -1;
            }
            if (i2 >= 0) {
                int i3 = (length - i) - 1;
                Object[] objArr3 = new Object[(i2 + i3)];
                int i4 = 0;
                int i5 = 0;
                while (i5 < i3) {
                    objArr3[i5] = objArr[i5 + i];
                    i5++;
                }
                if (z) {
                    System.arraycopy((Object[]) obj, 0, objArr3, i5, i2);
                } else {
                    while (obj instanceof Pair) {
                        Pair pair = (Pair) obj;
                        objArr3[i5] = pair.getCar();
                        obj = pair.getCdr();
                        i2--;
                        i5++;
                    }
                    if (i2 > 0) {
                        Sequence sequence = (Sequence) obj;
                        while (i4 < i2) {
                            objArr3[i5] = sequence.get(i4);
                            i4++;
                            i5++;
                        }
                    }
                }
                return objArr3;
            }
            throw new WrongType(procedure, length, obj, "sequence or array");
        }
        throw new WrongArguments("apply", 2, "(apply proc [args] args) [count:" + length + " skip:" + i + "]");
    }

    public Object applyN(Object[] objArr) throws Throwable {
        return this.applyToArgs.applyN(getArguments(objArr, 0, this));
    }

    public void apply(CallContext callContext) throws Throwable {
        this.applyToArgs.checkN(getArguments(callContext.getArgs(), 0, this), callContext);
    }
}
