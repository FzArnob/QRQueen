package gnu.bytecode;

import java.util.List;
import java.util.Vector;

public class ObjectType extends Type {
    static final int ADD_ENCLOSING_DONE = 8;
    static final int ADD_FIELDS_DONE = 1;
    static final int ADD_MEMBERCLASSES_DONE = 4;
    static final int ADD_METHODS_DONE = 2;
    static final int EXISTING_CLASS = 16;
    static final int HAS_OUTER_LINK = 32;
    public int flags;

    public Field getField(String str, int i) {
        return null;
    }

    protected ObjectType() {
        this.size = 4;
    }

    public ObjectType(String str) {
        this.this_name = str;
        this.size = 4;
    }

    public final boolean isExisting() {
        Type implementationType = getImplementationType();
        if (implementationType instanceof ArrayType) {
            implementationType = ((ArrayType) implementationType).getComponentType();
        }
        if (implementationType == this) {
            if ((this.flags & 16) != 0) {
                return true;
            }
            return false;
        } else if (!(implementationType instanceof ObjectType) || ((ObjectType) implementationType).isExisting()) {
            return true;
        } else {
            return false;
        }
    }

    public final void setExisting(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public String getInternalName() {
        return getName().replace('.', '/');
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        return java.lang.Class.forName(r2, false, getThreadContextClassLoader());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        return java.lang.Class.forName(r2, false, getContextClassLoader());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class getContextClass(java.lang.String r2) throws java.lang.ClassNotFoundException {
        /*
            r0 = 0
            java.lang.Class<gnu.bytecode.ObjectType> r1 = gnu.bytecode.ObjectType.class
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x000c }
            java.lang.Class r2 = java.lang.Class.forName(r2, r0, r1)     // Catch:{ ClassNotFoundException -> 0x000c }
            return r2
        L_0x000c:
            java.lang.ClassLoader r1 = getThreadContextClassLoader()     // Catch:{ ClassNotFoundException -> 0x0015 }
            java.lang.Class r2 = java.lang.Class.forName(r2, r0, r1)     // Catch:{ ClassNotFoundException -> 0x0015 }
            return r2
        L_0x0015:
            java.lang.ClassLoader r1 = getContextClassLoader()
            java.lang.Class r2 = java.lang.Class.forName(r2, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ObjectType.getContextClass(java.lang.String):java.lang.Class");
    }

    public static ClassLoader getThreadContextClassLoader() {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (SecurityException unused) {
            return ObjectType.class.getClassLoader();
        }
    }

    public static ClassLoader getContextClassLoader() {
        try {
            return ClassLoader.getSystemClassLoader();
        } catch (SecurityException unused) {
            return ObjectType.class.getClassLoader();
        }
    }

    public Class getReflectClass() {
        try {
            if (this.reflectClass == null) {
                this.reflectClass = getContextClass(getInternalName().replace('/', '.'));
            }
            this.flags |= 16;
        } catch (ClassNotFoundException e) {
            if ((this.flags & 16) != 0) {
                RuntimeException runtimeException = new RuntimeException("no such class: " + getName());
                runtimeException.initCause(e);
                throw runtimeException;
            }
        }
        return this.reflectClass;
    }

    public Type getImplementationType() {
        if (this == nullType) {
            return objectType;
        }
        return this == toStringType ? javalangStringType : this;
    }

    public Type promote() {
        return this == nullType ? objectType : this;
    }

    public boolean isInstance(Object obj) {
        if (this == nullType) {
            return obj == null;
        }
        return super.isInstance(obj);
    }

    public Method getMethod(String str, Type[] typeArr) {
        return Type.objectType.getMethod(str, typeArr);
    }

    public final int getMethods(Filter filter, int i, Vector vector, String str) {
        return Type.objectType.getMethods(filter, i, vector, str);
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        return Type.objectType.getMethods(filter, i, list);
    }

    public int compare(Type type) {
        return type == nullType ? 0 : -1;
    }

    public Object coerceFromObject(Object obj) {
        if (obj != null) {
            if (this == Type.toStringType) {
                return obj.toString();
            }
            Class reflectClass = getReflectClass();
            Class<?> cls = obj.getClass();
            if (!reflectClass.isAssignableFrom(cls)) {
                throw new ClassCastException("don't know how to coerce " + cls.getName() + " to " + getName());
            }
        }
        return obj;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        if (this == Type.toStringType) {
            codeAttr.emitDup();
            codeAttr.emitIfNull();
            codeAttr.emitPop(1);
            codeAttr.emitPushNull();
            codeAttr.emitElse();
            codeAttr.emitInvokeVirtual(Type.toString_method);
            codeAttr.emitFi();
        } else if (this != Type.objectType) {
            codeAttr.emitCheckcast(this);
        }
    }
}
