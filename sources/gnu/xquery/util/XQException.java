package gnu.xquery.util;

import androidx.core.app.NotificationCompat;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class XQException extends RuntimeException {
    public static Symbol FOER0000_QNAME = Symbol.make("http://www.w3.org/2005/xqt-errors", "FOER0000", NotificationCompat.CATEGORY_ERROR);
    public Symbol code;
    public String description;
    public Object errorValue;

    public XQException(Symbol symbol, String str, Object obj) {
        super(str);
        this.code = symbol;
        this.description = str;
        this.errorValue = obj;
    }

    public static void error() {
        throw new XQException(FOER0000_QNAME, (String) null, (Object) null);
    }

    public static void error(Symbol symbol) {
        throw new XQException(symbol, (String) null, (Object) null);
    }

    public static void error(Object obj, String str) {
        if (obj == null || obj == Values.empty) {
            obj = FOER0000_QNAME;
        }
        throw new XQException((Symbol) obj, str, (Object) null);
    }

    public static void error(Object obj, String str, Object obj2) {
        if (obj == null || obj == Values.empty) {
            obj = FOER0000_QNAME;
        }
        throw new XQException((Symbol) obj, str, obj2);
    }

    public String getMessage() {
        StringBuffer stringBuffer = new StringBuffer(100);
        String str = this.description;
        if (str == null) {
            stringBuffer.append("XQuery-error");
        } else {
            stringBuffer.append(str);
        }
        if (this.code != null) {
            stringBuffer.append(" [");
            String prefix = this.code.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                stringBuffer.append(prefix);
                stringBuffer.append(':');
            }
            stringBuffer.append(this.code.getLocalName());
            stringBuffer.append(']');
        }
        Object obj = this.errorValue;
        if (!(obj == null || obj == Values.empty)) {
            stringBuffer.append(" value: ");
            stringBuffer.append(this.errorValue);
        }
        return stringBuffer.toString();
    }
}
