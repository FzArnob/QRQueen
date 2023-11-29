package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class SubList extends MethodProc {
    public static final SubList subList = new SubList();

    public int numArgs() {
        return 12290;
    }

    public static void subList(Object obj, double d, double d2, Consumer consumer) {
        int nextDataIndex;
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int i = 0;
            int i2 = 0;
            do {
                i++;
                if (((double) i) < d) {
                    i2 = values.nextDataIndex(i2);
                } else {
                    int i3 = i2;
                    while (true) {
                        int i4 = i + 1;
                        if (((double) i) >= d2 || (nextDataIndex = values.nextDataIndex(i3)) < 0) {
                            values.consumeIRange(i2, i3, consumer);
                        } else {
                            i3 = nextDataIndex;
                            i = i4;
                        }
                    }
                    values.consumeIRange(i2, i3, consumer);
                    return;
                }
            } while (i2 >= 0);
        } else if (d <= 1.0d && d2 >= 2.0d) {
            consumer.writeObject(obj);
        }
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        Object nextArg = callContext.getNextArg();
        double round = (double) Math.round(StringUtils.asDouble(callContext.getNextArg()));
        Object obj = Sequence.eofValue;
        Object nextArg2 = callContext.getNextArg(obj);
        callContext.lastArg();
        subList(nextArg, round, (nextArg2 != obj ? (double) Math.round(StringUtils.asDouble(nextArg2)) : Double.POSITIVE_INFINITY) + round, consumer);
    }
}
