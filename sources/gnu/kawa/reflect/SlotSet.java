package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.CheckedTarget;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Values;

public class SlotSet extends Procedure3 implements Inlineable {
    public static final SlotSet set$Mnfield$Ex = new SlotSet("set-field!", false);
    public static final SlotSet set$Mnstatic$Mnfield$Ex = new SlotSet("set-static-field!", true);
    public static final SlotSet setFieldReturnObject;
    static final Type[] type1Array = new Type[1];
    boolean isStatic;
    boolean returnSelf;

    static {
        SlotSet slotSet = new SlotSet("set-field-return-object!", false);
        setFieldReturnObject = slotSet;
        slotSet.returnSelf = true;
    }

    public SlotSet(String str, boolean z) {
        super(str);
        this.isStatic = z;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotSet");
    }

    public static void setField(Object obj, String str, Object obj2) {
        apply(false, obj, str, obj2);
    }

    public static void setStaticField(Object obj, String str, Object obj2) {
        apply(true, obj, str, obj2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a A[SYNTHETIC, Splitter:B:28:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006c A[SYNTHETIC, Splitter:B:36:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[Catch:{ Exception -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void apply(boolean r10, java.lang.Object r11, java.lang.Object r12, java.lang.Object r13) {
        /*
            gnu.expr.Language r0 = gnu.expr.Language.getDefaultLanguage()
            boolean r1 = r12 instanceof java.lang.String
            if (r1 != 0) goto L_0x001c
            boolean r1 = r12 instanceof gnu.lists.FString
            if (r1 != 0) goto L_0x001c
            boolean r1 = r12 instanceof gnu.mapping.Symbol
            if (r1 == 0) goto L_0x0011
            goto L_0x001c
        L_0x0011:
            r10 = r12
            gnu.bytecode.Member r10 = (gnu.bytecode.Member) r10
            java.lang.String r10 = r10.getName()
            r1 = 0
            r2 = r1
            r1 = r10
            goto L_0x0032
        L_0x001c:
            java.lang.String r1 = r12.toString()
            java.lang.String r2 = gnu.expr.Compilation.mangleNameIfNeeded(r1)
            if (r10 == 0) goto L_0x002b
            java.lang.Class r10 = gnu.kawa.reflect.SlotGet.coerceToClass(r11)
            goto L_0x002f
        L_0x002b:
            java.lang.Class r10 = r11.getClass()
        L_0x002f:
            r9 = r2
            r2 = r10
            r10 = r9
        L_0x0032:
            r3 = 1
            r4 = 0
            boolean r5 = r12 instanceof gnu.bytecode.Field     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            if (r5 == 0) goto L_0x0040
            r5 = r12
            gnu.bytecode.Field r5 = (gnu.bytecode.Field) r5     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            java.lang.reflect.Field r5 = r5.getReflectField()     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            goto L_0x0044
        L_0x0040:
            java.lang.reflect.Field r5 = r2.getField(r10)     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
        L_0x0044:
            java.lang.Class r6 = r5.getType()     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            java.lang.Object r6 = r0.coerceFromObject(r6, r13)     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            r5.set(r11, r6)     // Catch:{ NoSuchFieldException -> 0x0052, IllegalAccessException -> 0x0050 }
            return
        L_0x0050:
            r5 = 1
            goto L_0x0053
        L_0x0052:
            r5 = 0
        L_0x0053:
            boolean r12 = r12 instanceof gnu.bytecode.Method     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.String r6 = "set"
            if (r12 == 0) goto L_0x005a
            goto L_0x005e
        L_0x005a:
            java.lang.String r10 = gnu.expr.ClassExp.slotToMethodName(r6, r1)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
        L_0x005e:
            if (r12 == 0) goto L_0x0067
            boolean r6 = r10.startsWith(r6)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            if (r6 != 0) goto L_0x0067
            r12 = 0
        L_0x0067:
            r6 = 3
            java.lang.String r7 = "get"
            if (r12 == 0) goto L_0x0082
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0080 }
            r8.<init>()     // Catch:{ Exception -> 0x0080 }
            r8.append(r7)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r7 = r10.substring(r6)     // Catch:{ Exception -> 0x0080 }
            r8.append(r7)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x0080 }
            goto L_0x0086
        L_0x0080:
            goto L_0x008d
        L_0x0082:
            java.lang.String r7 = gnu.expr.ClassExp.slotToMethodName(r7, r1)     // Catch:{ Exception -> 0x0080 }
        L_0x0086:
            java.lang.Class[] r8 = gnu.kawa.reflect.SlotGet.noClasses     // Catch:{ Exception -> 0x0080 }
            java.lang.reflect.Method r12 = r2.getMethod(r7, r8)     // Catch:{ Exception -> 0x0080 }
            goto L_0x00af
        L_0x008d:
            java.lang.String r7 = "is"
            if (r12 == 0) goto L_0x00a5
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r12.<init>()     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r12.append(r7)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.String r6 = r10.substring(r6)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r12.append(r6)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.String r12 = r12.toString()     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            goto L_0x00a9
        L_0x00a5:
            java.lang.String r12 = gnu.expr.ClassExp.slotToMethodName(r7, r1)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
        L_0x00a9:
            java.lang.Class[] r6 = gnu.kawa.reflect.SlotGet.noClasses     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.reflect.Method r12 = r2.getMethod(r12, r6)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
        L_0x00af:
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.Class r12 = r12.getReturnType()     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r6[r4] = r12     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.reflect.Method r10 = r2.getMethod(r10, r6)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r6 = r6[r4]     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            java.lang.Object r13 = r0.coerceFromObject(r6, r13)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r12[r4] = r13     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            r10.invoke(r11, r12)     // Catch:{ InvocationTargetException -> 0x0108, IllegalAccessException -> 0x00cb, NoSuchMethodException -> 0x00c9 }
            return
        L_0x00c9:
            r3 = r5
            goto L_0x00cc
        L_0x00cb:
        L_0x00cc:
            if (r3 == 0) goto L_0x00e5
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "illegal access for field "
            r11.append(r12)
            r11.append(r1)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x00e5:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "no such field "
            r11.append(r12)
            r11.append(r1)
            java.lang.String r12 = " in "
            r11.append(r12)
            java.lang.String r12 = r2.getName()
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x0108:
            r10 = move-exception
            java.lang.Throwable r10 = r10.getTargetException()
            java.lang.RuntimeException r10 = gnu.mapping.WrappedException.wrapIfNeeded(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotSet.apply(boolean, java.lang.Object, java.lang.Object, java.lang.Object):void");
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        apply(this.isStatic, obj, obj2, obj3);
        return this.returnSelf ? obj : Values.empty;
    }

    public static Member lookupMember(ObjectType objectType, String str, ClassType classType) {
        Field field = objectType.getField(Compilation.mangleNameIfNeeded(str), -1);
        if (field != null) {
            if (classType == null) {
                classType = Type.pointer_type;
            }
            if (classType.isAccessible(field, objectType)) {
                return field;
            }
        }
        Method method = objectType.getMethod(ClassExp.slotToMethodName("set", str), type1Array);
        return method == null ? field : method;
    }

    static void compileSet(Procedure procedure, ObjectType objectType, Expression expression, Object obj, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        Language language = compilation.getLanguage();
        boolean z = (procedure instanceof SlotSet) && ((SlotSet) procedure).isStatic;
        if (obj instanceof Field) {
            Field field = (Field) obj;
            boolean staticFlag = field.getStaticFlag();
            Type langTypeFor = language.getLangTypeFor(field.getType());
            if (z && !staticFlag) {
                compilation.error('e', "cannot access non-static field `" + field.getName() + "' using `" + procedure.getName() + '\'');
            }
            expression.compile(compilation, CheckedTarget.getInstance(langTypeFor));
            if (staticFlag) {
                code.emitPutStatic(field);
            } else {
                code.emitPutField(field);
            }
        } else if (obj instanceof Method) {
            Method method = (Method) obj;
            boolean staticFlag2 = method.getStaticFlag();
            if (z && !staticFlag2) {
                compilation.error('e', "cannot call non-static getter method `" + method.getName() + "' using `" + procedure.getName() + '\'');
            }
            expression.compile(compilation, CheckedTarget.getInstance(language.getLangTypeFor(method.getParameterTypes()[0])));
            if (staticFlag2) {
                code.emitInvokeStatic(method);
            } else {
                code.emitInvoke(method);
            }
            if (!method.getReturnType().isVoid()) {
                code.emitPop(1);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: gnu.bytecode.Member} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.ApplyExp r17, gnu.expr.Compilation r18, gnu.expr.Target r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            r2 = r19
            gnu.expr.Expression[] r3 = r17.getArgs()
            int r4 = r3.length
            r5 = 101(0x65, float:1.42E-43)
            r6 = 3
            r7 = 0
            if (r4 == r6) goto L_0x003c
            if (r4 >= r6) goto L_0x0016
            java.lang.String r3 = "too few"
            goto L_0x0018
        L_0x0016:
            java.lang.String r3 = "too many"
        L_0x0018:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = " arguments to `"
            r4.append(r3)
            java.lang.String r3 = r16.getName()
            r4.append(r3)
            r3 = 39
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r1.error(r5, r3)
            r1.compileConstant(r7, r2)
            return
        L_0x003c:
            r4 = 0
            r6 = r3[r4]
            r8 = 1
            r9 = r3[r8]
            r10 = 2
            r11 = r3[r10]
            boolean r11 = r0.isStatic
            if (r11 == 0) goto L_0x004e
            gnu.bytecode.Type r6 = kawa.standard.Scheme.exp2Type(r6)
            goto L_0x0052
        L_0x004e:
            gnu.bytecode.Type r6 = r6.getType()
        L_0x0052:
            boolean r11 = r6 instanceof gnu.bytecode.ObjectType
            if (r11 == 0) goto L_0x012a
            boolean r11 = r9 instanceof gnu.expr.QuoteExp
            if (r11 == 0) goto L_0x012a
            gnu.expr.QuoteExp r9 = (gnu.expr.QuoteExp) r9
            java.lang.Object r9 = r9.getValue()
            r11 = r6
            gnu.bytecode.ObjectType r11 = (gnu.bytecode.ObjectType) r11
            gnu.bytecode.ClassType r12 = r1.curClass
            if (r12 == 0) goto L_0x006a
            gnu.bytecode.ClassType r12 = r1.curClass
            goto L_0x006c
        L_0x006a:
            gnu.bytecode.ClassType r12 = r1.mainClass
        L_0x006c:
            boolean r13 = r9 instanceof java.lang.String
            java.lang.String r14 = "' in "
            if (r13 != 0) goto L_0x0089
            boolean r13 = r9 instanceof gnu.lists.FString
            if (r13 != 0) goto L_0x0089
            boolean r13 = r9 instanceof gnu.mapping.Symbol
            if (r13 == 0) goto L_0x007b
            goto L_0x0089
        L_0x007b:
            boolean r6 = r9 instanceof gnu.bytecode.Member
            if (r6 == 0) goto L_0x0087
            r7 = r9
            gnu.bytecode.Member r7 = (gnu.bytecode.Member) r7
            java.lang.String r6 = r7.getName()
            goto L_0x00bf
        L_0x0087:
            r6 = r7
            goto L_0x00bf
        L_0x0089:
            java.lang.String r7 = r9.toString()
            gnu.bytecode.Member r9 = lookupMember(r11, r7, r12)
            if (r9 != 0) goto L_0x00bd
            gnu.bytecode.ClassType r13 = gnu.bytecode.Type.pointer_type
            if (r6 == r13) goto L_0x00bd
            boolean r6 = r18.warnUnknownMember()
            if (r6 == 0) goto L_0x00bd
            r6 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r15 = "no slot `"
            r13.append(r15)
            r13.append(r7)
            r13.append(r14)
            java.lang.String r15 = r11.getName()
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            r1.error(r6, r13)
        L_0x00bd:
            r6 = r7
            r7 = r9
        L_0x00bf:
            if (r7 == 0) goto L_0x012a
            int r9 = r7.getModifiers()
            r9 = r9 & 8
            if (r9 == 0) goto L_0x00ca
            goto L_0x00cb
        L_0x00ca:
            r8 = 0
        L_0x00cb:
            if (r12 == 0) goto L_0x00fa
            boolean r9 = r12.isAccessible(r7, r11)
            if (r9 != 0) goto L_0x00fa
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "slot '"
            r9.append(r12)
            r9.append(r6)
            r9.append(r14)
            gnu.bytecode.ClassType r6 = r7.getDeclaringClass()
            java.lang.String r6 = r6.getName()
            r9.append(r6)
            java.lang.String r6 = " not accessible here"
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            r1.error(r5, r6)
        L_0x00fa:
            r4 = r3[r4]
            if (r8 == 0) goto L_0x0101
            gnu.expr.Target r5 = gnu.expr.Target.Ignore
            goto L_0x0105
        L_0x0101:
            gnu.expr.Target r5 = gnu.expr.Target.pushValue(r11)
        L_0x0105:
            r4.compile((gnu.expr.Compilation) r1, (gnu.expr.Target) r5)
            boolean r4 = r0.returnSelf
            if (r4 == 0) goto L_0x0117
            gnu.bytecode.CodeAttr r4 = r18.getCode()
            gnu.bytecode.Type r5 = r11.getImplementationType()
            r4.emitDup((gnu.bytecode.Type) r5)
        L_0x0117:
            r3 = r3[r10]
            compileSet(r0, r11, r3, r7, r1)
            boolean r3 = r0.returnSelf
            if (r3 == 0) goto L_0x0124
            r2.compileFromStack(r1, r11)
            goto L_0x0129
        L_0x0124:
            gnu.mapping.Values r3 = gnu.mapping.Values.empty
            r1.compileConstant(r3, r2)
        L_0x0129:
            return
        L_0x012a:
            gnu.expr.ApplyExp.compile(r17, r18, r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotSet.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }
}
