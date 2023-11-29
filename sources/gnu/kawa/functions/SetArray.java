package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.lang.reflect.Array;

/* compiled from: Setter */
class SetArray extends Procedure2 {
    Object array;
    Type elementType;

    public SetArray(Object obj, Language language) {
        this.elementType = language.getTypeFor((Class) obj.getClass().getComponentType());
        this.array = obj;
    }

    public Object apply2(Object obj, Object obj2) {
        Array.set(this.array, ((Number) obj).intValue(), this.elementType.coerceFromObject(obj2));
        return Values.empty;
    }
}
