package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;
import gnu.mapping.Values;

public class StackTarget extends Target {
    Type type;

    public StackTarget(Type type2) {
        this.type = type2;
    }

    public Type getType() {
        return this.type;
    }

    public static Target getInstance(Type type2) {
        if (type2.isVoid()) {
            return Target.Ignore;
        }
        return type2 == Type.pointer_type ? Target.pushObject : new StackTarget(type2);
    }

    /* access modifiers changed from: protected */
    public boolean compileFromStack0(Compilation compilation, Type type2) {
        return compileFromStack0(compilation, type2, this.type);
    }

    static boolean compileFromStack0(Compilation compilation, Type type2, Type type3) {
        if (type3 == type2) {
            return true;
        }
        CodeAttr code = compilation.getCode();
        if (type2.isVoid()) {
            compilation.compileConstant(Values.empty);
            type2 = Type.pointer_type;
        } else if ((type2 instanceof PrimType) && (type3 instanceof PrimType)) {
            code.emitConvert(type2, type3);
            return true;
        }
        if (!(type2 instanceof ArrayType)) {
            type3.emitConvertFromPrimitive(type2, code);
            type2 = code.topType();
        } else if (type3 == Type.pointer_type || "java.lang.Cloneable".equals(type3.getName())) {
            return true;
        }
        return !CodeAttr.castNeeded(type2.getImplementationType(), type3.getImplementationType());
    }

    public static void convert(Compilation compilation, Type type2, Type type3) {
        if (!compileFromStack0(compilation, type2, type3)) {
            emitCoerceFromObject(type3, compilation);
        }
    }

    protected static void emitCoerceFromObject(Type type2, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (type2 instanceof OccurrenceType) {
            compilation.compileConstant(type2, Target.pushObject);
            code.emitSwap();
            code.emitInvokeVirtual(ClassType.make("gnu.bytecode.Type").getDeclaredMethod("coerceFromObject", 1));
            return;
        }
        compilation.usedClass(type2);
        type2.emitCoerceFromObject(code);
    }

    public void compileFromStack(Compilation compilation, Type type2) {
        if (!compileFromStack0(compilation, type2)) {
            emitCoerceFromObject(this.type, compilation);
        }
    }
}
