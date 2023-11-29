package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasSetter;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class SetNamedPart extends Procedure3 implements HasSetter {
    public static final SetNamedPart setNamedPart;

    static {
        SetNamedPart setNamedPart2 = new SetNamedPart();
        setNamedPart = setNamedPart2;
        setNamedPart2.setName("setNamedPart");
        setNamedPart2.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedPart");
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        if (obj instanceof Namespace) {
            Namespace namespace = (Namespace) obj;
            String name = namespace.getName();
            if (name.startsWith("class:")) {
                obj = ClassType.make(name.substring(6));
            } else {
                Symbol symbol = namespace.getSymbol(obj2.toString());
                Environment.getCurrent();
                Environment.getCurrent().put(symbol, obj3);
                return Values.empty;
            }
        }
        if (obj instanceof Class) {
            obj = (ClassType) Type.make((Class) obj);
        }
        if (obj instanceof ClassType) {
            try {
                SlotSet.setStaticField(obj, obj2.toString(), obj3);
                return Values.empty;
            } catch (Throwable unused) {
            }
        }
        SlotSet.setField(obj, obj2.toString(), obj3);
        return Values.empty;
    }
}
