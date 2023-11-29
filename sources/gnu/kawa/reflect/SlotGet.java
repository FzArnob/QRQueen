package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.WrongArguments;

public class SlotGet extends Procedure2 implements HasSetter, Inlineable {
    public static final SlotGet field = new SlotGet("field", false, SlotSet.set$Mnfield$Ex);
    static Class[] noClasses = new Class[0];
    public static final SlotGet slotRef = new SlotGet("slot-ref", false, SlotSet.set$Mnfield$Ex);
    public static final SlotGet staticField = new SlotGet("static-field", true, SlotSet.set$Mnstatic$Mnfield$Ex);
    boolean isStatic;
    Procedure setter;

    public SlotGet(String str, boolean z) {
        super(str);
        this.isStatic = z;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotGet");
    }

    public SlotGet(String str, boolean z, Procedure procedure) {
        this(str, z);
        this.setter = procedure;
    }

    public static Object field(Object obj, String str) {
        return field.apply2(obj, str);
    }

    public static Object staticField(Object obj, String str) {
        return staticField.apply2(obj, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object apply2(java.lang.Object r10, java.lang.Object r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof gnu.bytecode.Field
            r1 = 0
            if (r0 == 0) goto L_0x0015
            gnu.bytecode.Field r11 = (gnu.bytecode.Field) r11
            java.lang.String r11 = r11.getName()
            r0 = 1
            java.lang.String r0 = gnu.expr.Compilation.demangleName(r11, r0)
        L_0x0010:
            r4 = r0
            r6 = r1
            r7 = r6
            r1 = r11
            goto L_0x005a
        L_0x0015:
            boolean r0 = r11 instanceof gnu.bytecode.Method
            if (r0 == 0) goto L_0x003f
            gnu.bytecode.Method r11 = (gnu.bytecode.Method) r11
            java.lang.String r11 = r11.getName()
            r0 = 0
            java.lang.String r0 = gnu.expr.Compilation.demangleName(r11, r0)
            java.lang.String r2 = "get"
            boolean r2 = r11.startsWith(r2)
            if (r2 == 0) goto L_0x002e
            r2 = r1
            goto L_0x003b
        L_0x002e:
            java.lang.String r2 = "is"
            boolean r2 = r11.startsWith(r2)
            if (r2 == 0) goto L_0x0039
            r2 = r11
            r11 = r1
            goto L_0x003b
        L_0x0039:
            r11 = r1
            r2 = r11
        L_0x003b:
            r6 = r11
            r4 = r0
            r7 = r2
            goto L_0x005a
        L_0x003f:
            boolean r0 = r11 instanceof gnu.mapping.SimpleSymbol
            if (r0 != 0) goto L_0x0051
            boolean r0 = r11 instanceof java.lang.CharSequence
            if (r0 == 0) goto L_0x0048
            goto L_0x0051
        L_0x0048:
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r0 = 2
            java.lang.String r1 = "string"
            r10.<init>((gnu.mapping.Procedure) r9, (int) r0, (java.lang.Object) r11, (java.lang.String) r1)
            throw r10
        L_0x0051:
            java.lang.String r0 = r11.toString()
            java.lang.String r11 = gnu.expr.Compilation.mangleNameIfNeeded(r0)
            goto L_0x0010
        L_0x005a:
            java.lang.String r11 = "class"
            boolean r0 = r11.equals(r1)
            java.lang.String r2 = "length"
            if (r0 == 0) goto L_0x0066
            r5 = r11
            goto L_0x006f
        L_0x0066:
            boolean r11 = r2.equals(r1)
            if (r11 == 0) goto L_0x006e
            r5 = r2
            goto L_0x006f
        L_0x006e:
            r5 = r1
        L_0x006f:
            boolean r2 = r9.isStatic
            gnu.expr.Language r8 = gnu.expr.Language.getDefaultLanguage()
            r3 = r10
            java.lang.Object r10 = getSlotValue(r2, r3, r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotGet.apply2(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object getSlotValue(boolean r6, java.lang.Object r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, gnu.expr.Language r12) {
        /*
            if (r6 == 0) goto L_0x0007
            java.lang.Class r0 = coerceToClass(r7)
            goto L_0x000b
        L_0x0007:
            java.lang.Class r0 = r7.getClass()
        L_0x000b:
            java.lang.String r1 = "length"
            if (r9 != r1) goto L_0x001e
            boolean r1 = r0.isArray()
            if (r1 == 0) goto L_0x001e
            int r6 = java.lang.reflect.Array.getLength(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            return r6
        L_0x001e:
            java.lang.String r1 = "class"
            if (r9 != r1) goto L_0x0023
            return r0
        L_0x0023:
            r1 = 0
            r2 = 39
            r3 = 1
            if (r9 == 0) goto L_0x0069
            java.lang.reflect.Field r4 = r0.getField(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x002f
        L_0x002e:
            r4 = 0
        L_0x002f:
            if (r4 == 0) goto L_0x0069
            if (r6 == 0) goto L_0x0056
            int r5 = r4.getModifiers()
            r5 = r5 & 8
            if (r5 == 0) goto L_0x003c
            goto L_0x0056
        L_0x003c:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "cannot access non-static field '"
            r7.append(r8)
            r7.append(r9)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0056:
            java.lang.Class r5 = r4.getType()     // Catch:{ IllegalAccessException -> 0x0068, Exception -> 0x0063 }
            java.lang.Object r4 = r4.get(r7)     // Catch:{ IllegalAccessException -> 0x0068, Exception -> 0x0063 }
            java.lang.Object r6 = r12.coerceToObject(r5, r4)     // Catch:{ IllegalAccessException -> 0x0068, Exception -> 0x0063 }
            return r6
        L_0x0063:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0069
        L_0x0068:
            r1 = 1
        L_0x0069:
            if (r10 == 0) goto L_0x006c
            goto L_0x0072
        L_0x006c:
            java.lang.String r10 = "get"
            java.lang.String r10 = gnu.expr.ClassExp.slotToMethodName(r10, r8)     // Catch:{ Exception -> 0x007e }
        L_0x0072:
            java.lang.Class[] r4 = noClasses     // Catch:{ Exception -> 0x007e }
            java.lang.reflect.Method r8 = r0.getMethod(r10, r4)     // Catch:{ Exception -> 0x007e }
            goto L_0x008f
        L_0x0079:
            goto L_0x00c4
        L_0x007b:
            r6 = move-exception
            goto L_0x0100
        L_0x007e:
            if (r11 == 0) goto L_0x0082
            goto L_0x0088
        L_0x0082:
            java.lang.String r10 = "is"
            java.lang.String r11 = gnu.expr.ClassExp.slotToMethodName(r10, r8)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
        L_0x0088:
            java.lang.Class[] r8 = noClasses     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.reflect.Method r8 = r0.getMethod(r11, r8)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r10 = r11
        L_0x008f:
            if (r6 == 0) goto L_0x00b4
            int r6 = r8.getModifiers()     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r6 = r6 & 8
            if (r6 == 0) goto L_0x009a
            goto L_0x00b4
        L_0x009a:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r7.<init>()     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.String r8 = "cannot call non-static getter method '"
            r7.append(r8)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r7.append(r10)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r7.append(r2)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.String r7 = r7.toString()     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            r6.<init>(r7)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            throw r6     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
        L_0x00b4:
            java.lang.Object[] r6 = gnu.mapping.Values.noArgs     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.Object r6 = r8.invoke(r7, r6)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.Class r7 = r8.getReturnType()     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            java.lang.Object r6 = r12.coerceToObject(r7, r6)     // Catch:{ InvocationTargetException -> 0x007b, IllegalAccessException -> 0x0079, NoSuchMethodException -> 0x00c3 }
            return r6
        L_0x00c3:
            r3 = r1
        L_0x00c4:
            if (r3 == 0) goto L_0x00dd
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "illegal access for field "
            r7.append(r8)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x00dd:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "no such field "
            r7.append(r8)
            r7.append(r9)
            java.lang.String r8 = " in "
            r7.append(r8)
            java.lang.String r8 = r0.getName()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0100:
            java.lang.Throwable r6 = r6.getTargetException()
            java.lang.RuntimeException r6 = gnu.mapping.WrappedException.wrapIfNeeded(r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.SlotGet.getSlotValue(boolean, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String, gnu.expr.Language):java.lang.Object");
    }

    static Class coerceToClass(Object obj) {
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (obj instanceof Type) {
            return ((Type) obj).getReflectClass();
        }
        throw new RuntimeException("argument is neither Class nor Type");
    }

    public void setN(Object[] objArr) {
        int length = objArr.length;
        if (length == 3) {
            set2(objArr[0], objArr[1], objArr[2]);
            return;
        }
        throw new WrongArguments(getSetter(), length);
    }

    public void set2(Object obj, Object obj2, Object obj3) {
        SlotSet.apply(this.isStatic, obj, (String) obj2, obj3);
    }

    public static Member lookupMember(ObjectType objectType, String str, ClassType classType) {
        Field field2 = objectType.getField(Compilation.mangleNameIfNeeded(str), -1);
        if (field2 != null) {
            if (classType == null) {
                classType = Type.pointer_type;
            }
            if (classType.isAccessible(field2, objectType)) {
                return field2;
            }
        }
        Method method = objectType.getMethod(ClassExp.slotToMethodName("get", str), Type.typeArray0);
        return method == null ? field2 : method;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Expression[] args = applyExp.getArgs();
        Expression expression = args[0];
        boolean z = true;
        Expression expression2 = args[1];
        Language language = compilation.getLanguage();
        Type typeFor = this.isStatic ? language.getTypeFor(expression) : expression.getType();
        CodeAttr code = compilation.getCode();
        if ((typeFor instanceof ObjectType) && (expression2 instanceof QuoteExp)) {
            ObjectType objectType = (ObjectType) typeFor;
            Object value = ((QuoteExp) expression2).getValue();
            if (value instanceof Field) {
                Field field2 = (Field) value;
                if ((field2.getModifiers() & 8) == 0) {
                    z = false;
                }
                args[0].compile(compilation, z ? Target.Ignore : Target.pushValue(objectType));
                if (z) {
                    code.emitGetStatic(field2);
                } else {
                    code.emitGetField(field2);
                }
                target.compileFromStack(compilation, language.getLangTypeFor(field2.getType()));
                return;
            } else if (value instanceof Method) {
                Method method = (Method) value;
                method.getModifiers();
                boolean staticFlag = method.getStaticFlag();
                args[0].compile(compilation, staticFlag ? Target.Ignore : Target.pushValue(objectType));
                if (staticFlag) {
                    code.emitInvokeStatic(method);
                } else {
                    code.emitInvoke(method);
                }
                target.compileFromStack(compilation, method.getReturnType());
                return;
            }
        }
        String checkName = ClassMethods.checkName(expression2);
        if (!(typeFor instanceof ArrayType) || !"length".equals(checkName) || this.isStatic) {
            ApplyExp.compile(applyExp, compilation, target);
            return;
        }
        args[0].compile(compilation, Target.pushValue(typeFor));
        code.emitArrayLength();
        target.compileFromStack(compilation, LangPrimType.intType);
    }

    public Type getReturnType(Expression[] expressionArr) {
        if (expressionArr.length == 2) {
            Expression expression = expressionArr[0];
            QuoteExp quoteExp = expressionArr[1];
            if (quoteExp instanceof QuoteExp) {
                Object value = quoteExp.getValue();
                if (value instanceof Field) {
                    return ((Field) value).getType();
                }
                if (value instanceof Method) {
                    return ((Method) value).getReturnType();
                }
                if (!this.isStatic && (expression.getType() instanceof ArrayType) && "length".equals(ClassMethods.checkName(quoteExp, true))) {
                    return LangPrimType.intType;
                }
            }
        }
        return Type.pointer_type;
    }

    public Procedure getSetter() {
        Procedure procedure = this.setter;
        return procedure == null ? super.getSetter() : procedure;
    }

    public static ApplyExp makeGetField(Expression expression, String str) {
        return new ApplyExp((Procedure) field, expression, new QuoteExp(str));
    }
}
