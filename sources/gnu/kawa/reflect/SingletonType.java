package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.Values;

public class SingletonType extends ObjectType {
    static final SingletonType instance = new SingletonType("singleton");

    public SingletonType(String str) {
        super(str);
    }

    public static final SingletonType getInstance() {
        return instance;
    }

    public Class getReflectClass() {
        return getImplementationType().getReflectClass();
    }

    public Type getImplementationType() {
        return Type.pointer_type;
    }

    public int compare(Type type) {
        int itemCountRange = OccurrenceType.itemCountRange(type);
        int i = itemCountRange & 4095;
        int i2 = itemCountRange >> 12;
        if (i2 == 0 || i > 1) {
            return -3;
        }
        if (i == 1 && i2 == 1) {
            return Type.pointer_type.compare(type);
        }
        int compare = Type.pointer_type.compare(type);
        return (compare == 0 || compare == -1) ? -1 : -2;
    }

    public static Object coerceToSingleton(Object obj) {
        if (obj instanceof Values) {
            obj = ((Values) obj).canonicalize();
        }
        if (obj != null && !(obj instanceof Values)) {
            return obj;
        }
        throw new ClassCastException("value is not a singleton");
    }

    public Object coerceFromObject(Object obj) {
        return coerceToSingleton(obj);
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        codeAttr.emitInvokeStatic(ClassType.make("gnu.kawa.reflect.SingletonType").getDeclaredMethod("coerceToSingleton", 1));
    }

    public boolean isInstance(Object obj) {
        return obj != null && !(obj instanceof Values);
    }
}
