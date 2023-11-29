package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrappedException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Vector;

public class Record {
    public String getTypeName() {
        return getClass().getName();
    }

    public static boolean isRecord(Object obj) {
        return obj instanceof Record;
    }

    public int hashCode() {
        Field[] fields = getClass().getFields();
        int i = 12345;
        for (Field field : fields) {
            try {
                Object obj = field.get(this);
                if (obj != null) {
                    i ^= obj.hashCode();
                }
            } catch (IllegalAccessException unused) {
            }
        }
        return i;
    }

    static Field getField(Class cls, String str) throws NoSuchFieldException {
        for (gnu.bytecode.Field fields = ((ClassType) Type.make(cls)).getFields(); fields != null; fields = fields.getNext()) {
            if ((fields.getModifiers() & 9) == 1 && fields.getSourceName().equals(str)) {
                return fields.getReflectField();
            }
        }
        throw new NoSuchFieldException();
    }

    public Object get(String str, Object obj) {
        Class<?> cls = getClass();
        try {
            return getField(cls, str).get(this);
        } catch (NoSuchFieldException unused) {
            throw new GenericError("no such field " + str + " in " + cls.getName());
        } catch (IllegalAccessException unused2) {
            throw new GenericError("illegal access for field " + str);
        }
    }

    public Object put(String str, Object obj) {
        return set1(this, str, obj);
    }

    public static Object set1(Object obj, String str, Object obj2) {
        Class<?> cls = obj.getClass();
        try {
            Field field = getField(cls, str);
            Object obj3 = field.get(obj);
            field.set(obj, obj2);
            return obj3;
        } catch (NoSuchFieldException unused) {
            throw new GenericError("no such field " + str + " in " + cls.getName());
        } catch (IllegalAccessException unused2) {
            throw new GenericError("illegal access for field " + str);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Class<?> cls = getClass();
        if (obj == null || obj.getClass() != cls) {
            return false;
        }
        for (gnu.bytecode.Field fields = ((ClassType) Type.make(cls)).getFields(); fields != null; fields = fields.getNext()) {
            if ((fields.getModifiers() & 9) == 1) {
                try {
                    Field reflectField = fields.getReflectField();
                    if (!reflectField.get(this).equals(reflectField.get(obj))) {
                        return false;
                    }
                } catch (Exception e) {
                    throw new WrappedException((Throwable) e);
                }
            }
        }
        return true;
    }

    public String toString() {
        Object obj;
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("#<");
        stringBuffer.append(getTypeName());
        for (gnu.bytecode.Field fields = ((ClassType) Type.make(getClass())).getFields(); fields != null; fields = fields.getNext()) {
            if ((fields.getModifiers() & 9) == 1) {
                try {
                    obj = fields.getReflectField().get(this);
                } catch (Exception unused) {
                    obj = "#<illegal-access>";
                }
                stringBuffer.append(' ');
                stringBuffer.append(fields.getSourceName());
                stringBuffer.append(": ");
                stringBuffer.append(obj);
            }
        }
        stringBuffer.append(">");
        return stringBuffer.toString();
    }

    public void print(PrintWriter printWriter) {
        printWriter.print(toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: gnu.lists.LList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.bytecode.ClassType makeRecordType(java.lang.String r8, gnu.lists.LList r9) {
        /*
            java.lang.String r0 = "kawa.lang.Record"
            gnu.bytecode.ClassType r0 = gnu.bytecode.ClassType.make(r0)
            java.lang.String r1 = gnu.expr.Compilation.mangleNameIfNeeded(r8)
            gnu.bytecode.ClassType r2 = new gnu.bytecode.ClassType
            r2.<init>(r1)
            r2.setSuper((gnu.bytecode.ClassType) r0)
            r3 = 33
            r2.setModifiers(r3)
            gnu.bytecode.Type[] r3 = gnu.bytecode.Type.typeArray0
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.voidType
            java.lang.String r5 = "<init>"
            r6 = 1
            gnu.bytecode.Method r3 = r2.addMethod((java.lang.String) r5, (gnu.bytecode.Type[]) r3, (gnu.bytecode.Type) r4, (int) r6)
            gnu.bytecode.Type[] r4 = gnu.bytecode.Type.typeArray0
            gnu.bytecode.PrimType r7 = gnu.bytecode.Type.voidType
            gnu.bytecode.Method r0 = r0.addMethod((java.lang.String) r5, (gnu.bytecode.Type[]) r4, (gnu.bytecode.Type) r7, (int) r6)
            gnu.bytecode.CodeAttr r3 = r3.startCode()
            r3.emitPushThis()
            r3.emitInvokeSpecial(r0)
            r3.emitReturn()
            boolean r0 = r8.equals(r1)
            if (r0 != 0) goto L_0x0051
            gnu.bytecode.Type[] r0 = gnu.bytecode.Type.typeArray0
            gnu.bytecode.ClassType r3 = gnu.expr.Compilation.typeString
            java.lang.String r4 = "getTypeName"
            gnu.bytecode.Method r0 = r2.addMethod((java.lang.String) r4, (gnu.bytecode.Type[]) r0, (gnu.bytecode.Type) r3, (int) r6)
            gnu.bytecode.CodeAttr r0 = r0.startCode()
            r0.emitPushString(r8)
            r0.emitReturn()
        L_0x0051:
            gnu.lists.LList r8 = gnu.lists.LList.Empty
            if (r9 == r8) goto L_0x0078
            gnu.lists.Pair r9 = (gnu.lists.Pair) r9
            java.lang.Object r8 = r9.getCar()
            java.lang.String r8 = r8.toString()
            java.lang.String r0 = gnu.expr.Compilation.mangleNameIfNeeded(r8)
            gnu.bytecode.ClassType r3 = gnu.bytecode.Type.pointer_type
            gnu.bytecode.Field r0 = r2.addField(r0, r3, r6)
            java.lang.String r8 = r8.intern()
            r0.setSourceName(r8)
            java.lang.Object r8 = r9.getCdr()
            r9 = r8
            gnu.lists.LList r9 = (gnu.lists.LList) r9
            goto L_0x0051
        L_0x0078:
            byte[][] r8 = new byte[r6][]
            java.lang.String[] r9 = new java.lang.String[r6]
            r0 = 0
            r9[r0] = r1
            byte[] r3 = r2.writeToArray()
            r8[r0] = r3
            gnu.bytecode.ArrayClassLoader r0 = new gnu.bytecode.ArrayClassLoader
            r0.<init>(r9, r8)
            java.lang.Class r8 = r0.loadClass(r1)     // Catch:{ ClassNotFoundException -> 0x0092 }
            gnu.bytecode.Type.registerTypeForClass(r8, r2)     // Catch:{ ClassNotFoundException -> 0x0092 }
            return r2
        L_0x0092:
            r8 = move-exception
            java.lang.InternalError r9 = new java.lang.InternalError
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Record.makeRecordType(java.lang.String, gnu.lists.LList):gnu.bytecode.ClassType");
    }

    public static LList typeFieldNames(Class cls) {
        LList lList = LList.Empty;
        Vector vector = new Vector(100);
        for (gnu.bytecode.Field fields = ((ClassType) Type.make(cls)).getFields(); fields != null; fields = fields.getNext()) {
            if ((fields.getModifiers() & 9) == 1) {
                vector.addElement(SimpleSymbol.valueOf(fields.getSourceName()));
            }
        }
        int size = vector.size();
        while (true) {
            size--;
            if (size < 0) {
                return lList;
            }
            lList = new Pair(vector.elementAt(size), lList);
        }
    }

    public static LList typeFieldNames(ClassType classType) {
        return typeFieldNames(classType.getReflectClass());
    }
}
