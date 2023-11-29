package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.util.List;

/* compiled from: Setter */
class SetList extends Procedure2 {
    Type elementType;
    List list;

    public SetList(List list2) {
        this.list = list2;
    }

    public Object apply2(Object obj, Object obj2) {
        this.list.set(((Number) obj).intValue(), obj2);
        return Values.empty;
    }
}
