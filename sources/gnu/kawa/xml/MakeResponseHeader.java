package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class MakeResponseHeader extends MethodProc {
    public static MakeResponseHeader makeResponseHeader = new MakeResponseHeader();

    public void apply(CallContext callContext) {
        String obj = callContext.getNextArg().toString();
        Object nextArg = callContext.getNextArg();
        callContext.lastArg();
        Consumer consumer = callContext.consumer;
        consumer.startAttribute(obj);
        consumer.write(nextArg.toString());
        consumer.endAttribute();
    }
}
