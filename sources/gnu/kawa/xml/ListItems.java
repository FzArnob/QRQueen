package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.List;

public class ListItems extends MethodProc {
    public static ListItems listItems = new ListItems();

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        Object nextArg = callContext.getNextArg();
        callContext.lastArg();
        List<Object> list = (List) nextArg;
        if (nextArg instanceof AbstractSequence) {
            ((AbstractSequence) nextArg).consumePosRange(0, -1, consumer);
            return;
        }
        for (Object writeValues : list) {
            Values.writeValues(writeValues, consumer);
        }
    }
}
