package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class Convert extends Procedure2 {
    public static final Convert as;

    static {
        Convert convert = new Convert();
        as = convert;
        convert.setName("as");
        convert.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConvert");
        Procedure.compilerKey.set(convert, "*gnu.kawa.functions.CompileMisc:forConvert");
    }

    public static Convert getInstance() {
        return as;
    }

    public Object apply2(Object obj, Object obj2) {
        Type type;
        if (obj instanceof Class) {
            type = Type.make((Class) obj);
        } else {
            type = (Type) obj;
        }
        return type.coerceFromObject(obj2);
    }
}
