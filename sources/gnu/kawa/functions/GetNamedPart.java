package gnu.kawa.functions;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.SlotGet;
import gnu.mapping.HasNamedParts;
import gnu.mapping.HasSetter;
import gnu.mapping.MethodProc;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class GetNamedPart extends Procedure2 implements HasSetter {
    public static final String CAST_METHOD_NAME = "@";
    public static final String CLASSTYPE_FOR = "<>";
    public static final String INSTANCEOF_METHOD_NAME = "instance?";
    public static final GetNamedPart getNamedPart;

    static {
        GetNamedPart getNamedPart2 = new GetNamedPart();
        getNamedPart = getNamedPart2;
        getNamedPart2.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedPart");
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Symbol symbol;
        if (obj instanceof Values) {
            Object[] values = ((Values) obj).getValues();
            Values values2 = new Values();
            for (Object apply2 : values) {
                Values.writeValues(apply2(apply2, obj2), values2);
            }
            return values2.canonicalize();
        }
        if (obj2 instanceof Symbol) {
            symbol = (Symbol) obj2;
        } else {
            symbol = Namespace.EmptyNamespace.getSymbol(obj2.toString().intern());
        }
        return getNamedPart(obj, symbol);
    }

    public static Object getTypePart(Type type, String str) throws Throwable {
        if (str.equals(CLASSTYPE_FOR)) {
            return type;
        }
        if (type instanceof ObjectType) {
            if (str.equals(INSTANCEOF_METHOD_NAME)) {
                return new NamedPart(type, str, Access.INNERCLASS_CONTEXT);
            }
            if (str.equals(CAST_METHOD_NAME)) {
                return new NamedPart(type, str, Access.CLASS_CONTEXT);
            }
            if (str.equals("new")) {
                return new NamedPart(type, str, 'N');
            }
            if (str.equals(".length") || (str.length() > 1 && str.charAt(0) == '.' && (type instanceof ClassType))) {
                return new NamedPart(type, str, 'D');
            }
        }
        if (!(type instanceof ClassType)) {
            return getMemberPart(type, str);
        }
        try {
            return SlotGet.staticField(type, str);
        } catch (Throwable unused) {
            return ClassMethods.apply(ClassMethods.classMethods, type, str);
        }
    }

    public static Object getNamedPart(Object obj, Symbol symbol) throws Throwable {
        String name = symbol.getName();
        if (obj instanceof HasNamedParts) {
            return ((HasNamedParts) obj).get(name);
        }
        if (obj instanceof Class) {
            obj = Type.make((Class) obj);
        }
        if (obj instanceof Package) {
            try {
                String name2 = ((Package) obj).getName();
                return ClassType.getContextClass(name2 + '.' + name);
            } catch (Throwable unused) {
            }
        }
        if (obj instanceof Type) {
            return getTypePart((Type) obj, name);
        }
        return getMemberPart(obj, symbol.toString());
    }

    public static Object getMemberPart(Object obj, String str) throws Throwable {
        try {
            return SlotGet.field(obj, str);
        } catch (Throwable unused) {
            MethodProc apply = ClassMethods.apply((ClassType) ClassType.make(obj.getClass()), Compilation.mangleName(str), 0, Language.getDefaultLanguage());
            if (apply != null) {
                return new NamedPart(obj, str, Access.METHOD_CONTEXT, apply);
            }
            throw new RuntimeException("no part '" + str + "' in " + obj);
        }
    }

    public Procedure getSetter() {
        return SetNamedPart.setNamedPart;
    }
}
