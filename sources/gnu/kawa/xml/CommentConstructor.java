package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class CommentConstructor extends MethodProc {
    public static final CommentConstructor commentConstructor = new CommentConstructor();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        XMLFilter pushNodeContext = NodeConstructor.pushNodeContext(callContext);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            String str = Location.UNBOUND;
            boolean z = true;
            while (true) {
                Object nextArg = callContext.getNextArg(str);
                if (nextArg == str) {
                    int length = stringBuffer.length();
                    char[] cArr = new char[length];
                    stringBuffer.getChars(0, length, cArr, 0);
                    pushNodeContext.writeComment(cArr, 0, length);
                    return;
                } else if (nextArg instanceof Values) {
                    Values values = (Values) nextArg;
                    int i = 0;
                    while (true) {
                        i = values.nextPos(i);
                        if (i == 0) {
                            break;
                        }
                        if (!z) {
                            stringBuffer.append(' ');
                        }
                        TextUtils.stringValue(values.getPosPrevious(i), stringBuffer);
                        z = false;
                    }
                } else {
                    if (!z) {
                        stringBuffer.append(' ');
                    }
                    TextUtils.stringValue(nextArg, stringBuffer);
                    z = false;
                }
            }
        } finally {
            NodeConstructor.popNodeContext(consumer, callContext);
        }
    }
}
