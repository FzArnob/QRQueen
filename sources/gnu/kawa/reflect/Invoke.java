package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

public class Invoke extends ProcedureN {
    public static final Invoke invoke = new Invoke("invoke", '*');
    public static final Invoke invokeSpecial = new Invoke("invoke-special", 'P');
    public static final Invoke invokeStatic = new Invoke("invoke-static", 'S');
    public static final Invoke make = new Invoke("make", 'N');
    char kind;
    Language language;

    public Invoke(String str, char c) {
        this(str, c, Language.getDefaultLanguage());
    }

    public Invoke(String str, char c, Language language2) {
        super(str);
        this.kind = c;
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileInvoke:validateApplyInvoke");
    }

    public static Object invoke$V(Object[] objArr) throws Throwable {
        return invoke.applyN(objArr);
    }

    public static Object invokeStatic$V(Object[] objArr) throws Throwable {
        return invokeStatic.applyN(objArr);
    }

    public static Object make$V(Object[] objArr) throws Throwable {
        return make.applyN(objArr);
    }

    private static ObjectType typeFrom(Object obj, Invoke invoke2) {
        if (obj instanceof Class) {
            obj = Type.make((Class) obj);
        }
        if (obj instanceof ObjectType) {
            return (ObjectType) obj;
        }
        if ((obj instanceof String) || (obj instanceof FString)) {
            return ClassType.make(obj.toString());
        }
        if (obj instanceof Symbol) {
            return ClassType.make(((Symbol) obj).getName());
        }
        if (obj instanceof ClassNamespace) {
            return ((ClassNamespace) obj).getClassType();
        }
        throw new WrongType((Procedure) invoke2, 0, obj, "class-specifier");
    }

    public void apply(CallContext callContext) throws Throwable {
        Object[] args = callContext.getArgs();
        char c = this.kind;
        if (c == 'S' || c == 'V' || c == 's' || c == '*') {
            int length = args.length;
            Procedure.checkArgCount(this, length);
            int i = 0;
            Object obj = args[0];
            char c2 = this.kind;
            ObjectType objectType = (ObjectType) ((c2 == 'S' || c2 == 's') ? typeFrom(obj, this) : Type.make(obj.getClass()));
            ObjectType objectType2 = objectType;
            MethodProc lookupMethods = lookupMethods(objectType, args[1]);
            char c3 = this.kind;
            Object[] objArr = new Object[(length - (c3 == 'S' ? 2 : 1))];
            if (c3 == 'V' || c3 == '*') {
                objArr[0] = args[0];
                i = 1;
            }
            System.arraycopy(args, 2, objArr, i, length - 2);
            lookupMethods.checkN(objArr, callContext);
            return;
        }
        callContext.writeValue(applyN(args));
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object applyN(java.lang.Object[] r12) throws java.lang.Throwable {
        /*
            r11 = this;
            char r0 = r11.kind
            r1 = 80
            if (r0 == r1) goto L_0x018d
            int r0 = r12.length
            gnu.mapping.Procedure.checkArgCount(r11, r0)
            r1 = 0
            r2 = r12[r1]
            char r3 = r11.kind
            r4 = 42
            r5 = 86
            if (r3 == r5) goto L_0x001c
            if (r3 == r4) goto L_0x001c
            gnu.bytecode.ObjectType r2 = typeFrom(r2, r11)
            goto L_0x0026
        L_0x001c:
            java.lang.Class r2 = r2.getClass()
            gnu.bytecode.Type r2 = gnu.bytecode.Type.make(r2)
            gnu.bytecode.ObjectType r2 = (gnu.bytecode.ObjectType) r2
        L_0x0026:
            char r3 = r11.kind
            r6 = -1
            r7 = 78
            r8 = 2
            r9 = 1
            if (r3 != r7) goto L_0x00d2
            r3 = 0
            boolean r10 = r2 instanceof gnu.expr.TypeValue
            if (r10 == 0) goto L_0x0048
            r10 = r2
            gnu.expr.TypeValue r10 = (gnu.expr.TypeValue) r10
            gnu.mapping.Procedure r10 = r10.getConstructor()
            if (r10 == 0) goto L_0x0048
            int r0 = r0 + r6
            java.lang.Object[] r2 = new java.lang.Object[r0]
            java.lang.System.arraycopy(r12, r9, r2, r1, r0)
            java.lang.Object r12 = r10.applyN(r2)
            return r12
        L_0x0048:
            boolean r10 = r2 instanceof gnu.expr.PairClassType
            if (r10 == 0) goto L_0x0050
            gnu.expr.PairClassType r2 = (gnu.expr.PairClassType) r2
            gnu.bytecode.ClassType r2 = r2.instanceType
        L_0x0050:
            boolean r10 = r2 instanceof gnu.bytecode.ArrayType
            if (r10 == 0) goto L_0x00d4
            gnu.bytecode.ArrayType r2 = (gnu.bytecode.ArrayType) r2
            gnu.bytecode.Type r0 = r2.getComponentType()
            int r2 = r12.length
            int r2 = r2 - r9
            if (r2 < r8) goto L_0x0085
            r3 = r12[r9]
            boolean r4 = r3 instanceof gnu.expr.Keyword
            if (r4 == 0) goto L_0x0085
            gnu.expr.Keyword r3 = (gnu.expr.Keyword) r3
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = "length"
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x007a
            java.lang.String r4 = "size"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0085
        L_0x007a:
            r3 = r12[r8]
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r4 = 3
            r5 = 1
            goto L_0x0088
        L_0x0085:
            r3 = r2
            r4 = 1
            r5 = 0
        L_0x0088:
            java.lang.Class r6 = r0.getReflectClass()
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r6, r3)
        L_0x0090:
            if (r4 > r2) goto L_0x00d1
            r6 = r12[r4]
            if (r5 == 0) goto L_0x00c7
            boolean r7 = r6 instanceof gnu.expr.Keyword
            if (r7 == 0) goto L_0x00c7
            if (r4 >= r2) goto L_0x00c7
            gnu.expr.Keyword r6 = (gnu.expr.Keyword) r6
            java.lang.String r1 = r6.getName()
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00ab }
            int r4 = r4 + 1
            r6 = r12[r4]
            goto L_0x00c7
        L_0x00ab:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "non-integer keyword '"
            r0.append(r2)
            r0.append(r1)
            java.lang.String r1 = "' in array constructor"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        L_0x00c7:
            java.lang.Object r6 = r0.coerceFromObject(r6)
            java.lang.reflect.Array.set(r3, r1, r6)
            int r1 = r1 + r9
            int r4 = r4 + r9
            goto L_0x0090
        L_0x00d1:
            return r3
        L_0x00d2:
            r3 = r12[r9]
        L_0x00d4:
            gnu.mapping.MethodProc r3 = r11.lookupMethods(r2, r3)
            char r10 = r11.kind
            if (r10 == r7) goto L_0x00fe
            r2 = 83
            if (r10 == r2) goto L_0x00e7
            r2 = 115(0x73, float:1.61E-43)
            if (r10 != r2) goto L_0x00e5
            goto L_0x00e7
        L_0x00e5:
            r2 = 1
            goto L_0x00e8
        L_0x00e7:
            r2 = 2
        L_0x00e8:
            int r2 = r0 - r2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            if (r10 == r5) goto L_0x00f0
            if (r10 != r4) goto L_0x00f5
        L_0x00f0:
            r4 = r12[r1]
            r2[r1] = r4
            r1 = 1
        L_0x00f5:
            int r0 = r0 - r8
            java.lang.System.arraycopy(r12, r8, r2, r1, r0)
            java.lang.Object r12 = r3.applyN(r2)
            return r12
        L_0x00fe:
            gnu.mapping.CallContext r4 = gnu.mapping.CallContext.getInstance()
            r5 = 0
        L_0x0103:
            int r7 = r12.length
            if (r5 >= r7) goto L_0x010f
            r7 = r12[r5]
            boolean r7 = r7 instanceof gnu.expr.Keyword
            if (r7 != 0) goto L_0x010f
            int r5 = r5 + 1
            goto L_0x0103
        L_0x010f:
            int r7 = r12.length
            if (r5 != r7) goto L_0x0143
            int r6 = r3.matchN(r12, r4)
            if (r6 != 0) goto L_0x011d
            java.lang.Object r12 = r4.runUntilValue()
            return r12
        L_0x011d:
            r7 = r2
            gnu.bytecode.ClassType r7 = (gnu.bytecode.ClassType) r7
            gnu.expr.Language r8 = r11.language
            java.lang.String r10 = "valueOf"
            gnu.mapping.MethodProc r7 = gnu.kawa.reflect.ClassMethods.apply(r7, r10, r1, r8)
            if (r7 == 0) goto L_0x013c
            int r0 = r0 - r9
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.System.arraycopy(r12, r9, r6, r1, r0)
            int r0 = r7.matchN(r6, r4)
            if (r0 != 0) goto L_0x013b
            java.lang.Object r12 = r4.runUntilValue()
            return r12
        L_0x013b:
            r6 = r0
        L_0x013c:
            r0 = r12[r1]
            java.lang.Object r0 = r3.apply1(r0)
            goto L_0x014c
        L_0x0143:
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.System.arraycopy(r12, r1, r0, r1, r5)
            java.lang.Object r0 = r3.applyN(r0)
        L_0x014c:
            r4 = r5
        L_0x014d:
            int r7 = r4 + 1
            int r8 = r12.length
            if (r7 >= r8) goto L_0x0167
            r8 = r12[r4]
            boolean r10 = r8 instanceof gnu.expr.Keyword
            if (r10 != 0) goto L_0x0159
            goto L_0x0167
        L_0x0159:
            gnu.expr.Keyword r8 = (gnu.expr.Keyword) r8
            r7 = r12[r7]
            java.lang.String r8 = r8.getName()
            gnu.kawa.reflect.SlotSet.apply(r1, r0, r8, r7)
            int r4 = r4 + 2
            goto L_0x014d
        L_0x0167:
            int r7 = r12.length
            if (r5 != r7) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r9 = r4
        L_0x016c:
            int r4 = r12.length
            if (r9 == r4) goto L_0x018c
            gnu.bytecode.ClassType r2 = (gnu.bytecode.ClassType) r2
            gnu.expr.Language r4 = r11.language
            java.lang.String r5 = "add"
            gnu.mapping.MethodProc r1 = gnu.kawa.reflect.ClassMethods.apply(r2, r5, r1, r4)
            if (r1 == 0) goto L_0x0187
        L_0x017b:
            int r2 = r12.length
            if (r9 >= r2) goto L_0x018c
            int r2 = r9 + 1
            r3 = r12[r9]
            r1.apply2(r0, r3)
            r9 = r2
            goto L_0x017b
        L_0x0187:
            java.lang.RuntimeException r12 = gnu.mapping.MethodProc.matchFailAsException(r6, r3, r12)
            throw r12
        L_0x018c:
            return r0
        L_0x018d:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r11.getName()
            r0.append(r1)
            java.lang.String r1 = ": invoke-special not allowed at run time"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.Invoke.applyN(java.lang.Object[]):java.lang.Object");
    }

    public int numArgs() {
        return (this.kind == 'N' ? 1 : 2) | -4096;
    }

    /* access modifiers changed from: protected */
    public MethodProc lookupMethods(ObjectType objectType, Object obj) {
        String str;
        String str2;
        if (this.kind == 'N') {
            str = "<init>";
        } else {
            if ((obj instanceof String) || (obj instanceof FString)) {
                str2 = obj.toString();
            } else if (obj instanceof Symbol) {
                str2 = ((Symbol) obj).getName();
            } else {
                throw new WrongType((Procedure) this, 1, (ClassCastException) null);
            }
            str = Compilation.mangleName(str2);
        }
        char c = this.kind;
        char c2 = 'V';
        if (c == 'P') {
            c2 = 'P';
        } else if (!(c == '*' || c == 'V')) {
            c2 = 0;
        }
        MethodProc apply = ClassMethods.apply(objectType, str, c2, this.language);
        if (apply != null) {
            return apply;
        }
        throw new RuntimeException(getName() + ": no method named `" + str + "' in class " + objectType.getName());
    }

    public static synchronized ApplyExp makeInvokeStatic(ClassType classType, String str, Expression[] expressionArr) {
        ApplyExp applyExp;
        synchronized (Invoke.class) {
            PrimProcedure staticMethod = getStaticMethod(classType, str, expressionArr);
            if (staticMethod != null) {
                applyExp = new ApplyExp((Procedure) staticMethod, expressionArr);
            } else {
                throw new RuntimeException("missing or ambiguous method `" + str + "' in " + classType.getName());
            }
        }
        return applyExp;
    }

    public static synchronized PrimProcedure getStaticMethod(ClassType classType, String str, Expression[] expressionArr) {
        PrimProcedure staticMethod;
        synchronized (Invoke.class) {
            staticMethod = CompileInvoke.getStaticMethod(classType, str, expressionArr);
        }
        return staticMethod;
    }
}
