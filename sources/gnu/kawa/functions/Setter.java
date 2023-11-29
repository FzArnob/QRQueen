package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import java.util.List;

public class Setter extends Procedure1 implements HasSetter {
    public static final Setter setter;

    static {
        Setter setter2 = new Setter();
        setter = setter2;
        setter2.setName("setter");
        setter2.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateSetter");
    }

    public static Object setter(Procedure procedure) {
        return procedure.getSetter();
    }

    public Object apply1(Object obj) {
        if (!(obj instanceof Procedure)) {
            if (obj instanceof List) {
                return new SetList((List) obj);
            }
            if (obj.getClass().isArray()) {
                return new SetArray(obj, Language.getDefaultLanguage());
            }
        }
        return ((Procedure) obj).getSetter();
    }

    public void set1(Object obj, Object obj2) throws Throwable {
        ((Procedure) obj).setSetter((Procedure) obj2);
    }
}
