package gnu.xquery.util;

import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.text.Path;

public class BooleanValue extends Procedure1 {
    public static final BooleanValue booleanValue = new BooleanValue("boolean-value");

    public BooleanValue(String str) {
        super(str);
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateBooleanValue");
    }

    public static boolean booleanValue(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if ((obj instanceof Number) && ((obj instanceof RealNum) || !(obj instanceof Numeric))) {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue == 0.0d || Double.isNaN(doubleValue)) {
                return false;
            }
            return true;
        } else if (obj instanceof SeqPosition) {
            return true;
        } else {
            if (!(obj instanceof String) && !(obj instanceof Path) && !(obj instanceof UntypedAtomic)) {
                if (obj instanceof Values) {
                    Values values = (Values) obj;
                    Object posNext = values.getPosNext(0);
                    if (posNext == Sequence.eofValue) {
                        return false;
                    }
                    if (values.nextDataIndex(0) < 0) {
                        return booleanValue(posNext);
                    }
                    if (posNext instanceof SeqPosition) {
                        return true;
                    }
                }
                throw new WrongType("fn:boolean", 1, obj, "boolean-convertible-value");
            } else if (obj.toString().length() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean not(Object obj) {
        return !booleanValue(obj);
    }

    public Object apply1(Object obj) {
        return booleanValue(obj) ? Boolean.TRUE : Boolean.FALSE;
    }
}
