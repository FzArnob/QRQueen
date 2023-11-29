package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.GenericProc;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.util.Vector;

public class ClassMethods extends Procedure2 {
    public static final ClassMethods classMethods;

    static {
        ClassMethods classMethods2 = new ClassMethods();
        classMethods = classMethods2;
        classMethods2.setName("class-methods");
    }

    public Object apply2(Object obj, Object obj2) {
        return apply(this, obj, obj2);
    }

    public static MethodProc apply(Procedure procedure, Object obj, Object obj2) {
        ClassType classType;
        if (obj instanceof Class) {
            obj = Type.make((Class) obj);
        }
        if (obj instanceof ClassType) {
            classType = (ClassType) obj;
        } else if ((obj instanceof String) || (obj instanceof FString) || (obj instanceof Symbol)) {
            classType = ClassType.make(obj.toString());
        } else {
            throw new WrongType(procedure, 0, (ClassCastException) null);
        }
        if ((obj2 instanceof String) || (obj2 instanceof FString) || (obj2 instanceof Symbol)) {
            String obj3 = obj2.toString();
            if (!"<init>".equals(obj3)) {
                obj3 = Compilation.mangleName(obj3);
            }
            MethodProc apply = apply(classType, obj3, 0, Language.getDefaultLanguage());
            if (apply != null) {
                return apply;
            }
            throw new RuntimeException("no applicable method named `" + obj3 + "' in " + classType.getName());
        }
        throw new WrongType(procedure, 1, (ClassCastException) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0037 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0034 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int removeRedundantMethods(java.util.Vector r12) {
        /*
            int r0 = r12.size()
            r1 = 1
        L_0x0005:
            if (r1 >= r0) goto L_0x0053
            java.lang.Object r2 = r12.elementAt(r1)
            gnu.bytecode.Method r2 = (gnu.bytecode.Method) r2
            gnu.bytecode.ClassType r3 = r2.getDeclaringClass()
            gnu.bytecode.Type[] r4 = r2.getParameterTypes()
            int r5 = r4.length
            r6 = 0
        L_0x0017:
            if (r6 >= r1) goto L_0x0050
            java.lang.Object r7 = r12.elementAt(r6)
            gnu.bytecode.Method r7 = (gnu.bytecode.Method) r7
            gnu.bytecode.Type[] r8 = r7.getParameterTypes()
            int r9 = r8.length
            if (r5 == r9) goto L_0x0027
            goto L_0x0034
        L_0x0027:
            r9 = r5
        L_0x0028:
            int r9 = r9 + -1
            if (r9 < 0) goto L_0x0032
            r10 = r4[r9]
            r11 = r8[r9]
            if (r10 == r11) goto L_0x0028
        L_0x0032:
            if (r9 < 0) goto L_0x0037
        L_0x0034:
            int r6 = r6 + 1
            goto L_0x0017
        L_0x0037:
            gnu.bytecode.ClassType r4 = r7.getDeclaringClass()
            boolean r3 = r3.isSubtype(r4)
            if (r3 == 0) goto L_0x0044
            r12.setElementAt(r2, r6)
        L_0x0044:
            int r2 = r0 + -1
            java.lang.Object r2 = r12.elementAt(r2)
            r12.setElementAt(r2, r1)
            int r0 = r0 + -1
            goto L_0x0005
        L_0x0050:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.ClassMethods.removeRedundantMethods(java.util.Vector):int");
    }

    public static PrimProcedure[] getMethods(ObjectType objectType, String str, char c, ClassType classType, Language language) {
        if (objectType == Type.tostring_type) {
            objectType = Type.string_type;
        }
        MethodFilter methodFilter = new MethodFilter(str, 0, 0, classType, c == 'P' ? null : objectType);
        int i = 0;
        boolean z = c == 'P' || "<init>".equals(str);
        Vector vector = new Vector();
        objectType.getMethods(methodFilter, z ? 0 : 2, vector);
        if (!z && (!(objectType instanceof ClassType) || ((ClassType) objectType).isInterface())) {
            Type.pointer_type.getMethods(methodFilter, 0, vector);
        }
        int size = z ? vector.size() : removeRedundantMethods(vector);
        PrimProcedure[] primProcedureArr = new PrimProcedure[size];
        while (true) {
            size--;
            if (size < 0) {
                return primProcedureArr;
            }
            Method method = (Method) vector.elementAt(size);
            if (!z && method.getDeclaringClass() != objectType) {
                Type implementationType = objectType.getImplementationType();
                if (implementationType instanceof ClassType) {
                    method = new Method(method, (ClassType) implementationType);
                }
            }
            primProcedureArr[i] = new PrimProcedure(method, c, language);
            i++;
        }
    }

    public static long selectApplicable(PrimProcedure[] primProcedureArr, Type[] typeArr) {
        int length = primProcedureArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int isApplicable = primProcedureArr[i].isApplicable(typeArr);
            if (isApplicable < 0) {
                int i4 = length - 1;
                PrimProcedure primProcedure = primProcedureArr[i4];
                primProcedureArr[i4] = primProcedureArr[i];
                primProcedureArr[i] = primProcedure;
                length--;
            } else {
                if (isApplicable > 0) {
                    PrimProcedure primProcedure2 = primProcedureArr[i2];
                    primProcedureArr[i2] = primProcedureArr[i];
                    primProcedureArr[i] = primProcedure2;
                    i2++;
                } else {
                    i3++;
                }
                i++;
            }
        }
        return (((long) i2) << 32) + ((long) i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int selectApplicable(gnu.expr.PrimProcedure[] r9, int r10) {
        /*
            int r0 = r9.length
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0006:
            if (r2 >= r0) goto L_0x0038
            r6 = r9[r2]
            int r6 = r6.numArgs()
            int r7 = gnu.mapping.Procedure.minArgs(r6)
            int r6 = gnu.mapping.Procedure.maxArgs(r6)
            if (r10 >= r7) goto L_0x001c
            int r4 = r4 + 1
        L_0x001a:
            r6 = 0
            goto L_0x0024
        L_0x001c:
            if (r10 <= r6) goto L_0x0023
            if (r6 < 0) goto L_0x0023
            int r5 = r5 + 1
            goto L_0x001a
        L_0x0023:
            r6 = 1
        L_0x0024:
            if (r6 == 0) goto L_0x002b
            int r3 = r3 + 1
            int r2 = r2 + 1
            goto L_0x0006
        L_0x002b:
            int r6 = r0 + -1
            r7 = r9[r6]
            r8 = r9[r2]
            r9[r6] = r8
            r9[r2] = r7
            int r0 = r0 + -1
            goto L_0x0006
        L_0x0038:
            if (r3 <= 0) goto L_0x003c
            r1 = r3
            goto L_0x0045
        L_0x003c:
            if (r4 <= 0) goto L_0x0041
            r1 = -983040(0xfffffffffff10000, float:NaN)
            goto L_0x0045
        L_0x0041:
            if (r5 <= 0) goto L_0x0045
            r1 = -917504(0xfffffffffff20000, float:NaN)
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.ClassMethods.selectApplicable(gnu.expr.PrimProcedure[], int):int");
    }

    public static MethodProc apply(ObjectType objectType, String str, char c, Language language) {
        GenericProc genericProc = null;
        PrimProcedure[] methods = getMethods(objectType, str, c, (ClassType) null, language);
        int i = 0;
        PrimProcedure primProcedure = null;
        while (i < methods.length) {
            PrimProcedure primProcedure2 = methods[i];
            if (primProcedure != null && genericProc == null) {
                genericProc = new GenericProc();
                genericProc.add((MethodProc) primProcedure);
            }
            if (genericProc != null) {
                genericProc.add((MethodProc) primProcedure2);
            }
            i++;
            primProcedure = primProcedure2;
        }
        if (genericProc == null) {
            return primProcedure;
        }
        genericProc.setName(objectType.getName() + "." + str);
        return genericProc;
    }

    static String checkName(Expression expression, boolean z) {
        String str;
        if (!(expression instanceof QuoteExp)) {
            return null;
        }
        Object value = ((QuoteExp) expression).getValue();
        if ((value instanceof FString) || (value instanceof String)) {
            str = value.toString();
        } else if (!(value instanceof Symbol)) {
            return null;
        } else {
            str = ((Symbol) value).getName();
        }
        if (Compilation.isValidJavaName(str)) {
            return str;
        }
        return Compilation.mangleName(str, z);
    }

    static String checkName(Expression expression) {
        if (!(expression instanceof QuoteExp)) {
            return null;
        }
        Object value = ((QuoteExp) expression).getValue();
        if ((value instanceof FString) || (value instanceof String)) {
            return value.toString();
        }
        if (value instanceof Symbol) {
            return ((Symbol) value).getName();
        }
        return null;
    }
}
