package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeCDATA extends MethodProc {
    public static final MakeCDATA makeCDATA = new MakeCDATA();

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        XMLFilter pushNodeContext = NodeConstructor.pushNodeContext(callContext);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            String str = Location.UNBOUND;
            while (true) {
                Object nextArg = callContext.getNextArg(str);
                if (nextArg == str) {
                    int length = stringBuffer.length();
                    char[] cArr = new char[length];
                    stringBuffer.getChars(0, length, cArr, 0);
                    pushNodeContext.writeCDATA(cArr, 0, length);
                    return;
                }
                TextUtils.stringValue(nextArg, stringBuffer);
            }
        } finally {
            NodeConstructor.popNodeContext(consumer, callContext);
        }
    }
}
