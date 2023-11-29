package gnu.xquery.util;

import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class ItemAt extends Procedure2 {
    public static final ItemAt itemAt = new ItemAt();

    public static Object itemAt(Object obj, int i) {
        if (obj instanceof Values) {
            Values values = (Values) obj;
            if (values.isEmpty()) {
                return Values.empty;
            }
            return values.get(i - 1);
        } else if (i == 1) {
            return obj;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object apply2(Object obj, Object obj2) {
        return itemAt(obj, ((Number) obj2).intValue());
    }
}
