package gnu.xquery.util;

import androidx.appcompat.widget.ActivityChooserView;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class IntegerRange extends MethodProc {
    public static final IntNum MAX_INT = IntNum.make((int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    public static final IntNum MIN_INT = IntNum.make(Integer.MIN_VALUE);
    public static final IntegerRange integerRange = new IntegerRange("to");

    public IntegerRange(String str) {
        setName(str);
    }

    public static void integerRange(IntNum intNum, IntNum intNum2, Consumer consumer) {
        if (IntNum.compare(intNum, MIN_INT) < 0 || IntNum.compare(intNum2, MAX_INT) > 0) {
            while (IntNum.compare(intNum, intNum2) <= 0) {
                consumer.writeObject(intNum);
                intNum = IntNum.add(intNum, 1);
            }
            return;
        }
        int intValue = intNum.intValue();
        int intValue2 = intNum2.intValue();
        if (intValue <= intValue2) {
            while (true) {
                consumer.writeInt(intValue);
                if (intValue != intValue2) {
                    intValue++;
                } else {
                    return;
                }
            }
        }
    }

    public void apply(CallContext callContext) {
        Object nextArg = callContext.getNextArg();
        Object nextArg2 = callContext.getNextArg();
        callContext.lastArg();
        Object atomicValue = KNode.atomicValue(nextArg);
        Object atomicValue2 = KNode.atomicValue(nextArg2);
        if (atomicValue != Values.empty && atomicValue != null && atomicValue2 != Values.empty && atomicValue2 != null) {
            if (atomicValue instanceof UntypedAtomic) {
                atomicValue = IntNum.valueOf(atomicValue.toString().trim(), 10);
            }
            if (atomicValue2 instanceof UntypedAtomic) {
                atomicValue2 = IntNum.valueOf(atomicValue2.toString().trim(), 10);
            }
            integerRange((IntNum) atomicValue, (IntNum) atomicValue2, callContext.consumer);
        }
    }
}
