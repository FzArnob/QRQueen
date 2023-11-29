package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;
import kawa.lang.Macro;

public class StaticFieldLocation extends FieldLocation {
    public StaticFieldLocation(String str, String str2) {
        super((Object) null, ClassType.make(str), str2);
    }

    public StaticFieldLocation(ClassType classType, String str) {
        super((Object) null, classType, str);
    }

    public StaticFieldLocation(Field field) {
        super((Object) null, field);
    }

    public Object get(Object obj) {
        Object obj2 = super.get(obj);
        if (obj2 instanceof Macro) {
            getDeclaration();
        }
        return obj2;
    }

    public static StaticFieldLocation define(Environment environment, Symbol symbol, Object obj, String str, String str2) {
        StaticFieldLocation staticFieldLocation = new StaticFieldLocation(str, str2);
        environment.addLocation(symbol, obj, staticFieldLocation);
        return staticFieldLocation;
    }

    public static StaticFieldLocation make(Declaration declaration) {
        gnu.bytecode.Field field = declaration.field;
        StaticFieldLocation staticFieldLocation = new StaticFieldLocation(field.getDeclaringClass(), field.getName());
        staticFieldLocation.setDeclaration(declaration);
        return staticFieldLocation;
    }

    public static StaticFieldLocation make(String str, String str2) {
        return new StaticFieldLocation(str, str2);
    }
}
