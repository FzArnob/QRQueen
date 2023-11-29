package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassFileInput;
import gnu.bytecode.ClassType;
import gnu.bytecode.ClassTypeWriter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.net.URL;

public class PrimProcedure extends MethodProc implements Inlineable {
    private static ClassLoader systemClassLoader = PrimProcedure.class.getClassLoader();
    Type[] argTypes;
    Member member;
    Method method;
    char mode;
    int op_code;
    Type retType;
    boolean sideEffectFree;
    LambdaExp source;

    public final int opcode() {
        return this.op_code;
    }

    public Type getReturnType() {
        return this.retType;
    }

    public void setReturnType(Type type) {
        this.retType = type;
    }

    public boolean isSpecial() {
        return this.mode == 'P';
    }

    public Type getReturnType(Expression[] expressionArr) {
        return this.retType;
    }

    public Method getMethod() {
        return this.method;
    }

    public boolean isSideEffectFree() {
        return this.sideEffectFree;
    }

    public void setSideEffectFree() {
        this.sideEffectFree = true;
    }

    public boolean takesVarArgs() {
        Method method2 = this.method;
        if (method2 == null) {
            return false;
        }
        if ((method2.getModifiers() & 128) != 0) {
            return true;
        }
        String name = this.method.getName();
        if (name.endsWith("$V") || name.endsWith("$V$X")) {
            return true;
        }
        return false;
    }

    public boolean takesContext() {
        Method method2 = this.method;
        return method2 != null && takesContext(method2);
    }

    public static boolean takesContext(Method method2) {
        return method2.getName().endsWith("$X");
    }

    public int isApplicable(Type[] typeArr) {
        Method method2;
        int isApplicable = super.isApplicable(typeArr);
        int length = typeArr.length;
        if (isApplicable == -1 && (method2 = this.method) != null && (method2.getModifiers() & 128) != 0 && length > 0) {
            int i = length - 1;
            if (typeArr[i] instanceof ArrayType) {
                Type[] typeArr2 = new Type[length];
                System.arraycopy(typeArr, 0, typeArr2, 0, i);
                typeArr2[i] = typeArr[i].getComponentType();
                return super.isApplicable(typeArr2);
            }
        }
        return isApplicable;
    }

    public final boolean isConstructor() {
        return opcode() == 183 && this.mode != 'P';
    }

    public boolean takesTarget() {
        return this.mode != 0;
    }

    public int numArgs() {
        int length = this.argTypes.length;
        if (takesTarget()) {
            length++;
        }
        if (takesContext()) {
            length--;
        }
        return takesVarArgs() ? (length - 1) - 4096 : length + (length << 12);
    }

    public int match0(CallContext callContext) {
        return matchN(ProcedureN.noArgs, callContext);
    }

    public int match1(Object obj, CallContext callContext) {
        return matchN(new Object[]{obj}, callContext);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        return matchN(new Object[]{obj, obj2}, callContext);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        return matchN(new Object[]{obj, obj2, obj3}, callContext);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        return matchN(new Object[]{obj, obj2, obj3, obj4}, callContext);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int matchN(java.lang.Object[] r13, gnu.mapping.CallContext r14) {
        /*
            r12 = this;
            int r0 = r13.length
            boolean r1 = r12.takesVarArgs()
            int r2 = r12.minArgs()
            if (r0 >= r2) goto L_0x000f
            r13 = -983040(0xfffffffffff10000, float:NaN)
            r13 = r13 | r2
            return r13
        L_0x000f:
            if (r1 != 0) goto L_0x0017
            if (r0 <= r2) goto L_0x0017
            r13 = -917504(0xfffffffffff20000, float:NaN)
            r13 = r13 | r2
            return r13
        L_0x0017:
            gnu.bytecode.Type[] r3 = r12.argTypes
            int r3 = r3.length
            boolean r4 = r12.takesTarget()
            r5 = 0
            r6 = 1
            if (r4 != 0) goto L_0x002b
            boolean r4 = r12.isConstructor()
            if (r4 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r4 = 0
            goto L_0x002c
        L_0x002b:
            r4 = 1
        L_0x002c:
            boolean r7 = r12.takesContext()
            java.lang.Object[] r8 = new java.lang.Object[r3]
            if (r7 == 0) goto L_0x0038
            int r3 = r3 + -1
            r8[r3] = r14
        L_0x0038:
            r7 = 0
            if (r1 == 0) goto L_0x0069
            gnu.bytecode.Type[] r1 = r12.argTypes
            int r3 = r3 - r6
            r1 = r1[r3]
            gnu.bytecode.ClassType r9 = gnu.expr.Compilation.scmListType
            if (r1 == r9) goto L_0x005f
            gnu.kawa.lispexpr.LangObjType r9 = gnu.kawa.lispexpr.LangObjType.listType
            if (r1 != r9) goto L_0x0049
            goto L_0x005f
        L_0x0049:
            gnu.bytecode.ArrayType r1 = (gnu.bytecode.ArrayType) r1
            gnu.bytecode.Type r1 = r1.getComponentType()
            java.lang.Class r9 = r1.getReflectClass()
            int r0 = r0 - r2
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r9, r0)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r8[r3] = r0
            goto L_0x006b
        L_0x005f:
            gnu.lists.LList r0 = gnu.lists.LList.makeList(r13, r2)
            r8[r3] = r0
            gnu.bytecode.ClassType r1 = gnu.bytecode.Type.objectType
            r0 = r7
            goto L_0x006b
        L_0x0069:
            r0 = r7
            r1 = r0
        L_0x006b:
            boolean r3 = r12.isConstructor()
            if (r3 == 0) goto L_0x0074
            r7 = r13[r5]
            goto L_0x0087
        L_0x0074:
            if (r4 == 0) goto L_0x0087
            gnu.bytecode.Method r3 = r12.method     // Catch:{ ClassCastException -> 0x0083 }
            gnu.bytecode.ClassType r3 = r3.getDeclaringClass()     // Catch:{ ClassCastException -> 0x0083 }
            r7 = r13[r5]     // Catch:{ ClassCastException -> 0x0083 }
            java.lang.Object r7 = r3.coerceFromObject(r7)     // Catch:{ ClassCastException -> 0x0083 }
            goto L_0x0087
        L_0x0083:
            r13 = -786431(0xfffffffffff40001, float:NaN)
            return r13
        L_0x0087:
            r3 = r4
        L_0x0088:
            int r9 = r13.length
            if (r3 >= r9) goto L_0x00b5
            r9 = r13[r3]
            if (r3 >= r2) goto L_0x0096
            gnu.bytecode.Type[] r10 = r12.argTypes
            int r11 = r3 - r4
            r10 = r10[r11]
            goto L_0x0097
        L_0x0096:
            r10 = r1
        L_0x0097:
            gnu.bytecode.ClassType r11 = gnu.bytecode.Type.objectType
            if (r10 == r11) goto L_0x00a5
            java.lang.Object r9 = r10.coerceFromObject(r9)     // Catch:{ ClassCastException -> 0x00a0 }
            goto L_0x00a5
        L_0x00a0:
            r13 = -786432(0xfffffffffff40000, float:NaN)
            int r3 = r3 + r6
            r13 = r13 | r3
            return r13
        L_0x00a5:
            if (r3 >= r2) goto L_0x00ac
            int r10 = r3 - r4
            r8[r10] = r9
            goto L_0x00b2
        L_0x00ac:
            if (r0 == 0) goto L_0x00b2
            int r10 = r3 - r2
            r0[r10] = r9
        L_0x00b2:
            int r3 = r3 + 1
            goto L_0x0088
        L_0x00b5:
            r14.value1 = r7
            r14.values = r8
            r14.proc = r12
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.matchN(java.lang.Object[], gnu.mapping.CallContext):int");
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        int length = this.argTypes.length;
        boolean isConstructor = isConstructor();
        boolean z = isConstructor && this.method.getDeclaringClass().hasOuterLink();
        try {
            if (this.member == null) {
                Class reflectClass = this.method.getDeclaringClass().getReflectClass();
                Class[] clsArr = new Class[((z ? 1 : 0) + length)];
                while (true) {
                    length--;
                    if (length < 0) {
                        break;
                    }
                    clsArr[(z ? 1 : 0) + length] = this.argTypes[length].getReflectClass();
                }
                if (z) {
                    clsArr[0] = this.method.getDeclaringClass().getOuterLinkType().getReflectClass();
                }
                if (isConstructor) {
                    this.member = reflectClass.getConstructor(clsArr);
                } else if (this.method != Type.clone_method) {
                    this.member = reflectClass.getMethod(this.method.getName(), clsArr);
                }
            }
            if (isConstructor) {
                Object[] objArr = callContext.values;
                if (z) {
                    int length2 = objArr.length + 1;
                    Object[] objArr2 = new Object[length2];
                    System.arraycopy(objArr, 0, objArr2, 1, length2 - 1);
                    objArr2[0] = ((PairClassType) callContext.value1).staticLink;
                    objArr = objArr2;
                }
                obj = ((Constructor) this.member).newInstance(objArr);
            } else if (this.method == Type.clone_method) {
                Object obj2 = callContext.value1;
                Class<?> componentType = obj2.getClass().getComponentType();
                int length3 = Array.getLength(obj2);
                Object newInstance = Array.newInstance(componentType, length3);
                System.arraycopy(obj2, 0, newInstance, 0, length3);
                obj = newInstance;
            } else {
                obj = this.retType.coerceToObject(((java.lang.reflect.Method) this.member).invoke(callContext.value1, callContext.values));
            }
            if (!takesContext()) {
                callContext.consumer.writeObject(obj);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    public PrimProcedure(String str, String str2, int i) {
        this(ClassType.make(str).getDeclaredMethod(str2, i));
    }

    public PrimProcedure(java.lang.reflect.Method method2, Language language) {
        this(((ClassType) language.getTypeFor((Class) method2.getDeclaringClass())).getMethod(method2), language);
    }

    public PrimProcedure(Method method2) {
        init(method2);
        this.retType = method2.getName().endsWith("$X") ? Type.objectType : method2.getReturnType();
    }

    public PrimProcedure(Method method2, Language language) {
        this(method2, 0, language);
    }

    public PrimProcedure(Method method2, char c, Language language) {
        this.mode = c;
        init(method2);
        Type[] typeArr = this.argTypes;
        int length = typeArr.length;
        this.argTypes = null;
        int i = length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Type type = typeArr[i];
            Type langTypeFor = language.getLangTypeFor(type);
            if (type != langTypeFor) {
                if (this.argTypes == null) {
                    Type[] typeArr2 = new Type[length];
                    this.argTypes = typeArr2;
                    System.arraycopy(typeArr, 0, typeArr2, 0, length);
                }
                this.argTypes[i] = langTypeFor;
            }
        }
        if (this.argTypes == null) {
            this.argTypes = typeArr;
        }
        if (isConstructor()) {
            this.retType = method2.getDeclaringClass();
        } else if (method2.getName().endsWith("$X")) {
            this.retType = Type.objectType;
        } else {
            Type langTypeFor2 = language.getLangTypeFor(method2.getReturnType());
            this.retType = langTypeFor2;
            if (langTypeFor2 == Type.toStringType) {
                this.retType = Type.javalangStringType;
            }
        }
    }

    private void init(Method method2) {
        this.method = method2;
        if ((method2.getModifiers() & 8) != 0) {
            this.op_code = 184;
        } else {
            ClassType declaringClass = method2.getDeclaringClass();
            if (this.mode == 'P') {
                this.op_code = 183;
            } else {
                this.mode = 'V';
                if ("<init>".equals(method2.getName())) {
                    this.op_code = 183;
                } else if ((declaringClass.getModifiers() & 512) != 0) {
                    this.op_code = 185;
                } else {
                    this.op_code = 182;
                }
            }
        }
        Type[] parameterTypes = method2.getParameterTypes();
        if (isConstructor() && method2.getDeclaringClass().hasOuterLink()) {
            int length = parameterTypes.length - 1;
            Type[] typeArr = new Type[length];
            System.arraycopy(parameterTypes, 1, typeArr, 0, length);
            parameterTypes = typeArr;
        }
        this.argTypes = parameterTypes;
    }

    public PrimProcedure(Method method2, LambdaExp lambdaExp) {
        this(method2);
        this.retType = lambdaExp.getReturnType();
        this.source = lambdaExp;
    }

    public PrimProcedure(int i, Type type, Type[] typeArr) {
        this.op_code = i;
        this.retType = type;
        this.argTypes = typeArr;
    }

    public static PrimProcedure makeBuiltinUnary(int i, Type type) {
        return new PrimProcedure(i, type, new Type[]{type});
    }

    public static PrimProcedure makeBuiltinBinary(int i, Type type) {
        return new PrimProcedure(i, type, new Type[]{type, type});
    }

    public PrimProcedure(int i, ClassType classType, String str, Type type, Type[] typeArr) {
        this.op_code = i;
        char c = 0;
        this.method = classType.addMethod(str, i == 184 ? 8 : 0, typeArr, type);
        this.retType = type;
        this.argTypes = typeArr;
        this.mode = i != 184 ? 'V' : c;
    }

    public final boolean getStaticFlag() {
        Method method2 = this.method;
        return method2 == null || method2.getStaticFlag() || isConstructor();
    }

    public final Type[] getParameterTypes() {
        return this.argTypes;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bc A[EDGE_INSN: B:85:0x00bc->B:47:0x00bc ?: BREAK  
    EDGE_INSN: B:86:0x00bc->B:47:0x00bc ?: BREAK  ] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void compileArgs(gnu.expr.Expression[] r21, int r22, gnu.bytecode.Type r23, gnu.expr.Compilation r24) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            r3 = r24
            boolean r4 = r20.takesVarArgs()
            java.lang.String r5 = r20.getName()
            gnu.bytecode.CodeAttr r6 = r24.getCode()
            gnu.bytecode.PrimType r7 = gnu.bytecode.Type.voidType
            r9 = 1
            if (r2 != r7) goto L_0x001b
            r7 = 1
            goto L_0x001c
        L_0x001b:
            r7 = 0
        L_0x001c:
            gnu.bytecode.Type[] r10 = r0.argTypes
            int r10 = r10.length
            int r10 = r10 - r7
            boolean r11 = r20.takesContext()
            if (r11 == 0) goto L_0x0028
            int r10 = r10 + -1
        L_0x0028:
            int r11 = r1.length
            int r11 = r11 - r22
            if (r2 == 0) goto L_0x0032
            if (r7 == 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r12 = 0
            goto L_0x0033
        L_0x0032:
            r12 = 1
        L_0x0033:
            if (r4 == 0) goto L_0x0071
            gnu.bytecode.Method r13 = r0.method
            int r13 = r13.getModifiers()
            r13 = r13 & 128(0x80, float:1.794E-43)
            if (r13 == 0) goto L_0x0071
            if (r11 <= 0) goto L_0x0071
            gnu.bytecode.Type[] r13 = r0.argTypes
            int r13 = r13.length
            if (r13 <= 0) goto L_0x0071
            r13 = r12 ^ 1
            int r13 = r13 + r10
            if (r11 != r13) goto L_0x0071
            int r13 = r1.length
            int r13 = r13 - r9
            r13 = r1[r13]
            gnu.bytecode.Type r13 = r13.getType()
            gnu.bytecode.Type[] r14 = r0.argTypes
            int r15 = r14.length
            int r15 = r15 - r9
            r14 = r14[r15]
            boolean r15 = r13 instanceof gnu.bytecode.ObjectType
            if (r15 == 0) goto L_0x0071
            boolean r15 = r14 instanceof gnu.bytecode.ArrayType
            if (r15 == 0) goto L_0x0071
            gnu.bytecode.ArrayType r14 = (gnu.bytecode.ArrayType) r14
            gnu.bytecode.Type r14 = r14.getComponentType()
            boolean r14 = r14 instanceof gnu.bytecode.ArrayType
            if (r14 != 0) goto L_0x0071
            boolean r4 = r13 instanceof gnu.bytecode.ArrayType
            r4 = r4 ^ r9
            r13 = r4
            r4 = 0
            goto L_0x0072
        L_0x0071:
            r13 = 0
        L_0x0072:
            if (r4 == 0) goto L_0x0077
            int r14 = r10 - r12
            goto L_0x007a
        L_0x0077:
            int r14 = r1.length
            int r14 = r14 - r22
        L_0x007a:
            gnu.expr.LambdaExp r15 = r0.source
            r16 = 0
            if (r15 != 0) goto L_0x0083
            r15 = r16
            goto L_0x0087
        L_0x0083:
            gnu.expr.Declaration r15 = r15.firstDecl()
        L_0x0087:
            if (r15 == 0) goto L_0x0093
            boolean r17 = r15.isThisParameter()
            if (r17 == 0) goto L_0x0093
            gnu.expr.Declaration r15 = r15.nextDecl()
        L_0x0093:
            r8 = 0
        L_0x0094:
            if (r4 == 0) goto L_0x00c2
            if (r8 != r14) goto L_0x00c2
            gnu.bytecode.Type[] r2 = r0.argTypes
            int r16 = r10 + -1
            int r16 = r16 + r7
            r2 = r2[r16]
            gnu.bytecode.ClassType r9 = gnu.expr.Compilation.scmListType
            if (r2 == r9) goto L_0x00bc
            gnu.kawa.lispexpr.LangObjType r9 = gnu.kawa.lispexpr.LangObjType.listType
            if (r2 != r9) goto L_0x00a9
            goto L_0x00bc
        L_0x00a9:
            int r9 = r1.length
            int r9 = r9 - r22
            int r9 = r9 - r14
            r6.emitPushInt(r9)
            gnu.bytecode.ArrayType r2 = (gnu.bytecode.ArrayType) r2
            gnu.bytecode.Type r2 = r2.getComponentType()
            r6.emitNewArray((gnu.bytecode.Type) r2)
            r16 = r2
            goto L_0x00c2
        L_0x00bc:
            int r2 = r22 + r8
            gnu.kawa.functions.MakeList.compile((gnu.expr.Expression[]) r1, (int) r2, (gnu.expr.Compilation) r3)
            goto L_0x00c4
        L_0x00c2:
            if (r8 < r11) goto L_0x00c5
        L_0x00c4:
            return
        L_0x00c5:
            if (r13 == 0) goto L_0x00cd
            int r2 = r8 + 1
            if (r2 != r11) goto L_0x00cd
            r2 = 1
            goto L_0x00ce
        L_0x00cd:
            r2 = 0
        L_0x00ce:
            if (r8 < r14) goto L_0x00dc
            r9 = 1
            r6.emitDup((int) r9)
            int r9 = r8 - r14
            r6.emitPushInt(r9)
            r9 = r16
            goto L_0x00fb
        L_0x00dc:
            if (r15 == 0) goto L_0x00e7
            if (r12 != 0) goto L_0x00e2
            if (r8 <= 0) goto L_0x00e7
        L_0x00e2:
            gnu.bytecode.Type r9 = r15.getType()
            goto L_0x00fb
        L_0x00e7:
            if (r12 == 0) goto L_0x00f0
            gnu.bytecode.Type[] r9 = r0.argTypes
            int r16 = r8 + r7
            r9 = r9[r16]
            goto L_0x00fb
        L_0x00f0:
            if (r8 != 0) goto L_0x00f5
            r9 = r23
            goto L_0x00fb
        L_0x00f5:
            gnu.bytecode.Type[] r9 = r0.argTypes
            int r16 = r8 + -1
            r9 = r9[r16]
        L_0x00fb:
            r3.usedClass(r9)
            if (r2 == 0) goto L_0x010b
            gnu.bytecode.ClassType r16 = gnu.bytecode.Type.objectType
            r18 = r7
            r19 = r16
            r16 = r4
            r4 = r19
            goto L_0x0110
        L_0x010b:
            r16 = r4
            r18 = r7
            r4 = r9
        L_0x0110:
            gnu.expr.LambdaExp r7 = r0.source
            if (r7 != 0) goto L_0x011b
            int r7 = r8 + 1
            gnu.expr.Target r4 = gnu.expr.CheckedTarget.getInstance((gnu.bytecode.Type) r4, (java.lang.String) r5, (int) r7)
            goto L_0x011f
        L_0x011b:
            gnu.expr.Target r4 = gnu.expr.CheckedTarget.getInstance((gnu.bytecode.Type) r4, (gnu.expr.LambdaExp) r7, (int) r8)
        L_0x011f:
            int r7 = r22 + r8
            r7 = r1[r7]
            r7.compileNotePosition(r3, r4, r7)
            if (r2 == 0) goto L_0x015c
            r2 = r9
            gnu.bytecode.ArrayType r2 = (gnu.bytecode.ArrayType) r2
            gnu.bytecode.Type r2 = r2.getComponentType()
            r6.emitDup()
            r6.emitInstanceof(r9)
            r6.emitIfIntNotZero()
            r6.emitCheckcast(r9)
            r6.emitElse()
            r4 = 1
            r6.emitPushInt(r4)
            r6.emitNewArray((gnu.bytecode.Type) r2)
            r6.emitDupX()
            r6.emitSwap()
            r7 = 0
            r6.emitPushInt(r7)
            r6.emitSwap()
            r2.emitCoerceFromObject(r6)
            r6.emitArrayStore(r9)
            r6.emitFi()
            goto L_0x015e
        L_0x015c:
            r4 = 1
            r7 = 0
        L_0x015e:
            if (r8 < r14) goto L_0x0163
            r6.emitArrayStore(r9)
        L_0x0163:
            if (r15 == 0) goto L_0x016e
            if (r12 != 0) goto L_0x0169
            if (r8 <= 0) goto L_0x016e
        L_0x0169:
            gnu.expr.Declaration r2 = r15.nextDecl()
            r15 = r2
        L_0x016e:
            int r8 = r8 + 1
            r2 = r23
            r4 = r16
            r7 = r18
            r16 = r9
            r9 = 1
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.compileArgs(gnu.expr.Expression[], int, gnu.bytecode.Type, gnu.expr.Compilation):void");
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        Method method2 = this.method;
        ClassType classType = null;
        ClassType declaringClass = method2 == null ? null : method2.getDeclaringClass();
        Expression[] args = applyExp.getArgs();
        if (isConstructor()) {
            if (applyExp.getFlag(8)) {
                int length = args.length;
                compilation.letStart();
                Expression[] expressionArr = new Expression[length];
                expressionArr[0] = args[0];
                for (int i = 1; i < length; i++) {
                    Expression expression = args[i];
                    Declaration letVariable = compilation.letVariable((Object) null, expression.getType(), expression);
                    letVariable.setCanRead(true);
                    expressionArr[i] = new ReferenceExp(letVariable);
                }
                compilation.letEnter();
                compilation.letDone(new ApplyExp(applyExp.func, expressionArr)).compile(compilation, target);
                return;
            }
            code.emitNew(declaringClass);
            code.emitDup((Type) declaringClass);
        }
        String checkArgCount = WrongArguments.checkArgCount(this, args.length);
        if (checkArgCount != null) {
            compilation.error('e', checkArgCount);
        }
        if (!getStaticFlag()) {
            classType = declaringClass;
        }
        compile(classType, applyExp, compilation, target);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0071, code lost:
        if (r8.method.getStaticFlag() != false) goto L_0x0073;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.bytecode.Type r9, gnu.expr.ApplyExp r10, gnu.expr.Compilation r11, gnu.expr.Target r12) {
        /*
            r8 = this;
            gnu.expr.Expression[] r0 = r10.getArgs()
            gnu.bytecode.CodeAttr r1 = r11.getCode()
            gnu.bytecode.Type r7 = r8.retType
            boolean r2 = r8.isConstructor()
            r3 = 0
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x002a
            gnu.bytecode.Method r9 = r8.method
            if (r9 != 0) goto L_0x0019
            r9 = r5
            goto L_0x001d
        L_0x0019:
            gnu.bytecode.ClassType r9 = r9.getDeclaringClass()
        L_0x001d:
            boolean r2 = r9.hasOuterLink()
            if (r2 == 0) goto L_0x0028
            r2 = r0[r3]
            gnu.expr.ClassExp.loadSuperStaticLink(r2, r9, r11)
        L_0x0028:
            r9 = r5
            goto L_0x0073
        L_0x002a:
            int r2 = r8.opcode()
            r6 = 183(0xb7, float:2.56E-43)
            if (r2 != r6) goto L_0x0065
            char r2 = r8.mode
            r6 = 80
            if (r2 != r6) goto L_0x0065
            gnu.bytecode.Method r2 = r8.method
            java.lang.String r2 = r2.getName()
            java.lang.String r6 = "<init>"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0065
            gnu.bytecode.Method r2 = r8.method
            if (r2 != 0) goto L_0x004c
            r2 = r5
            goto L_0x0050
        L_0x004c:
            gnu.bytecode.ClassType r2 = r2.getDeclaringClass()
        L_0x0050:
            boolean r2 = r2.hasOuterLink()
            if (r2 == 0) goto L_0x0074
            r1.emitPushThis()
            gnu.bytecode.Scope r9 = r1.getCurrentScope()
            gnu.bytecode.Variable r9 = r9.getVariable(r4)
            r1.emitLoad(r9)
            goto L_0x0028
        L_0x0065:
            boolean r2 = r8.takesTarget()
            if (r2 == 0) goto L_0x0074
            gnu.bytecode.Method r2 = r8.method
            boolean r2 = r2.getStaticFlag()
            if (r2 == 0) goto L_0x0074
        L_0x0073:
            r3 = 1
        L_0x0074:
            r8.compileArgs(r0, r3, r9, r11)
            gnu.bytecode.Method r3 = r8.method
            if (r3 != 0) goto L_0x0089
            int r9 = r8.opcode()
            int r10 = r0.length
            gnu.bytecode.Type r0 = r8.retType
            r1.emitPrimop(r9, r10, r0)
            r12.compileFromStack(r11, r7)
            goto L_0x0094
        L_0x0089:
            boolean r5 = r10.isTailCall()
            int r6 = r8.op_code
            r2 = r11
            r4 = r12
            compileInvoke(r2, r3, r4, r5, r6, r7)
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.compile(gnu.bytecode.Type, gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    public static void compileInvoke(Compilation compilation, Method method2, Target target, boolean z, int i, Type type) {
        Field field;
        CodeAttr code = compilation.getCode();
        compilation.usedClass(method2.getDeclaringClass());
        compilation.usedClass(method2.getReturnType());
        if (!takesContext(method2)) {
            code.emitInvokeMethod(method2, i);
        } else {
            boolean z2 = target instanceof IgnoreTarget;
            Variable variable = null;
            if (z2 || ((target instanceof ConsumerTarget) && ((ConsumerTarget) target).isContextTarget())) {
                compilation.loadCallContext();
                if (z2) {
                    ClassType classType = Compilation.typeCallContext;
                    Field declaredField = classType.getDeclaredField("consumer");
                    code.pushScope();
                    Variable addLocal = code.addLocal(classType);
                    code.emitDup();
                    code.emitGetField(declaredField);
                    code.emitStore(addLocal);
                    code.emitDup();
                    code.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
                    code.emitPutField(declaredField);
                    Field field2 = declaredField;
                    variable = addLocal;
                    field = field2;
                } else {
                    field = null;
                }
                code.emitInvokeMethod(method2, i);
                if (z) {
                    compilation.loadCallContext();
                    code.emitInvoke(Compilation.typeCallContext.getDeclaredMethod("runUntilDone", 0));
                }
                if (z2) {
                    compilation.loadCallContext();
                    code.emitLoad(variable);
                    code.emitPutField(field);
                    code.popScope();
                    return;
                }
                return;
            }
            compilation.loadCallContext();
            type = Type.objectType;
            code.pushScope();
            Variable addLocal2 = code.addLocal(Type.intType);
            compilation.loadCallContext();
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("startFromContext", 0));
            code.emitStore(addLocal2);
            code.emitWithCleanupStart();
            code.emitInvokeMethod(method2, i);
            code.emitWithCleanupCatch((Variable) null);
            compilation.loadCallContext();
            code.emitLoad(addLocal2);
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("cleanupFromContext", 1));
            code.emitWithCleanupDone();
            compilation.loadCallContext();
            code.emitLoad(addLocal2);
            code.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("getFromContext", 1));
            code.popScope();
        }
        target.compileFromStack(compilation, type);
    }

    public Type getParameterType(int i) {
        if (takesTarget()) {
            if (i == 0) {
                return isConstructor() ? Type.objectType : this.method.getDeclaringClass();
            }
            i--;
        }
        Type[] typeArr = this.argTypes;
        int length = typeArr.length;
        int i2 = length - 1;
        if (i < i2) {
            return typeArr[i];
        }
        boolean takesVarArgs = takesVarArgs();
        if (i < length && !takesVarArgs) {
            return this.argTypes[i];
        }
        Type type = this.argTypes[i2];
        if (type instanceof ArrayType) {
            return ((ArrayType) type).getComponentType();
        }
        return Type.objectType;
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Expression[] expressionArr) {
        return getMethodFor(procedure, (Declaration) null, expressionArr, Language.getDefaultLanguage());
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Expression[] expressionArr, Language language) {
        int length = expressionArr.length;
        Type[] typeArr = new Type[length];
        while (true) {
            length--;
            if (length < 0) {
                return getMethodFor(procedure, declaration, typeArr, language);
            }
            typeArr[length] = expressionArr[length].getType();
        }
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Type[] typeArr, Language language) {
        if (procedure instanceof GenericProc) {
            GenericProc genericProc = (GenericProc) procedure;
            MethodProc[] methodProcArr = genericProc.methods;
            int i = genericProc.count;
            MethodProc methodProc = null;
            while (true) {
                i--;
                if (i >= 0) {
                    if (methodProcArr[i].isApplicable(typeArr) >= 0) {
                        if (methodProc != null) {
                            return null;
                        }
                        methodProc = methodProcArr[i];
                    }
                } else if (methodProc == null) {
                    return null;
                } else {
                    procedure = methodProc;
                }
            }
        }
        if (procedure instanceof PrimProcedure) {
            PrimProcedure primProcedure = (PrimProcedure) procedure;
            if (primProcedure.isApplicable(typeArr) >= 0) {
                return primProcedure;
            }
        }
        Class procedureClass = getProcedureClass(procedure);
        if (procedureClass == null) {
            return null;
        }
        return getMethodFor((ClassType) Type.make(procedureClass), procedure.getName(), declaration, typeArr, language);
    }

    public static void disassemble$X(Procedure procedure, CallContext callContext) throws Exception {
        Consumer consumer = callContext.consumer;
        disassemble(procedure, consumer instanceof Writer ? (Writer) consumer : new ConsumerWriter(consumer));
    }

    public static void disassemble(Procedure procedure, Writer writer) throws Exception {
        disassemble(procedure, new ClassTypeWriter((ClassType) null, writer, 0));
    }

    public static void disassemble(Procedure procedure, ClassTypeWriter classTypeWriter) throws Exception {
        Method method2;
        if (procedure instanceof GenericProc) {
            GenericProc genericProc = (GenericProc) procedure;
            int methodCount = genericProc.getMethodCount();
            classTypeWriter.print("Generic procedure with ");
            classTypeWriter.print(methodCount);
            classTypeWriter.println(methodCount == 1 ? " method." : "methods.");
            for (int i = 0; i < methodCount; i++) {
                MethodProc method3 = genericProc.getMethod(i);
                if (method3 != null) {
                    classTypeWriter.println();
                    disassemble((Procedure) method3, classTypeWriter);
                }
            }
            return;
        }
        String str = null;
        Class<?> cls = procedure.getClass();
        if (procedure instanceof ModuleMethod) {
            cls = ((ModuleMethod) procedure).module.getClass();
        } else if ((procedure instanceof PrimProcedure) && (method2 = ((PrimProcedure) procedure).method) != null) {
            cls = method2.getDeclaringClass().getReflectClass();
            str = method2.getName();
        }
        ClassLoader classLoader = cls.getClassLoader();
        String name = cls.getName();
        String str2 = name.replace('.', '/') + ".class";
        ClassType classType = new ClassType();
        InputStream resourceAsStream = classLoader.getResourceAsStream(str2);
        if (resourceAsStream != null) {
            new ClassFileInput(classType, resourceAsStream);
            classTypeWriter.setClass(classType);
            URL resource = classLoader.getResource(str2);
            classTypeWriter.print("In class ");
            classTypeWriter.print(name);
            if (resource != null) {
                classTypeWriter.print(" at ");
                classTypeWriter.print(resource);
            }
            classTypeWriter.println();
            if (str == null) {
                String name2 = procedure.getName();
                if (name2 == null) {
                    classTypeWriter.println("Anonymous function - unknown method.");
                    return;
                }
                str = Compilation.mangleName(name2);
            }
            for (Method methods = classType.getMethods(); methods != null; methods = methods.getNext()) {
                if (methods.getName().equals(str)) {
                    classTypeWriter.printMethod(methods);
                }
            }
            classTypeWriter.flush();
            return;
        }
        throw new RuntimeException("missing resource " + str2);
    }

    public static Class getProcedureClass(Object obj) {
        Class<?> cls;
        if (obj instanceof ModuleMethod) {
            cls = ((ModuleMethod) obj).module.getClass();
        } else {
            cls = obj.getClass();
        }
        try {
            if (cls.getClassLoader() == systemClassLoader) {
                return cls;
            }
            return null;
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static PrimProcedure getMethodFor(Class cls, String str, Declaration declaration, Expression[] expressionArr, Language language) {
        return getMethodFor((ClassType) Type.make(cls), str, declaration, expressionArr, language);
    }

    public static PrimProcedure getMethodFor(ClassType classType, String str, Declaration declaration, Expression[] expressionArr, Language language) {
        int length = expressionArr.length;
        Type[] typeArr = new Type[length];
        while (true) {
            length--;
            if (length < 0) {
                return getMethodFor(classType, str, declaration, typeArr, language);
            }
            typeArr[length] = expressionArr[length].getType();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0093 A[Catch:{ SecurityException -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ad A[Catch:{ SecurityException -> 0x00d1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.PrimProcedure getMethodFor(gnu.bytecode.ClassType r16, java.lang.String r17, gnu.expr.Declaration r18, gnu.bytecode.Type[] r19, gnu.expr.Language r20) {
        /*
            r0 = r17
            r1 = r18
            r2 = 0
            if (r0 != 0) goto L_0x0008
            return r2
        L_0x0008:
            java.lang.String r3 = gnu.expr.Compilation.mangleName(r17)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00d3 }
            r4.<init>()     // Catch:{ SecurityException -> 0x00d3 }
            r4.append(r3)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r5 = "$V"
            r4.append(r5)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00d3 }
            r5.<init>()     // Catch:{ SecurityException -> 0x00d3 }
            r5.append(r3)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r6 = "$V$X"
            r5.append(r6)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r5 = r5.toString()     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00d3 }
            r6.<init>()     // Catch:{ SecurityException -> 0x00d3 }
            r6.append(r3)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r7 = "$X"
            r6.append(r7)     // Catch:{ SecurityException -> 0x00d3 }
            java.lang.String r6 = r6.toString()     // Catch:{ SecurityException -> 0x00d3 }
            gnu.bytecode.Method r7 = r16.getDeclaredMethods()     // Catch:{ SecurityException -> 0x00d3 }
            r11 = r2
            r12 = 1
            r13 = -1
            r14 = 0
        L_0x0047:
            if (r7 == 0) goto L_0x00d5
            int r15 = r7.getModifiers()     // Catch:{ SecurityException -> 0x00d1 }
            r8 = 9
            r15 = r15 & r8
            if (r15 == r8) goto L_0x0059
            if (r1 == 0) goto L_0x008a
            gnu.expr.Declaration r8 = r1.base     // Catch:{ SecurityException -> 0x00d1 }
            if (r8 != 0) goto L_0x0059
            goto L_0x008a
        L_0x0059:
            java.lang.String r8 = r7.getName()     // Catch:{ SecurityException -> 0x00d1 }
            boolean r15 = r8.equals(r3)     // Catch:{ SecurityException -> 0x00d1 }
            if (r15 != 0) goto L_0x0090
            boolean r15 = r8.equals(r4)     // Catch:{ SecurityException -> 0x00d1 }
            if (r15 != 0) goto L_0x0090
            boolean r15 = r8.equals(r6)     // Catch:{ SecurityException -> 0x00d1 }
            if (r15 != 0) goto L_0x0090
            boolean r15 = r8.equals(r5)     // Catch:{ SecurityException -> 0x00d1 }
            if (r15 == 0) goto L_0x0076
            goto L_0x0090
        L_0x0076:
            if (r12 == 0) goto L_0x008a
            java.lang.String r15 = "apply"
            boolean r15 = r8.equals(r15)     // Catch:{ SecurityException -> 0x00d1 }
            if (r15 != 0) goto L_0x0088
            java.lang.String r15 = "apply$V"
            boolean r8 = r8.equals(r15)     // Catch:{ SecurityException -> 0x00d1 }
            if (r8 == 0) goto L_0x008a
        L_0x0088:
            r8 = 1
            goto L_0x0091
        L_0x008a:
            r10 = r19
            r9 = r20
            r15 = r2
            goto L_0x00ca
        L_0x0090:
            r8 = 0
        L_0x0091:
            if (r8 != 0) goto L_0x009b
            if (r14 == 0) goto L_0x009a
            r11 = r2
            r12 = 0
            r13 = -1
            r14 = 0
            goto L_0x009b
        L_0x009a:
            r12 = 0
        L_0x009b:
            gnu.expr.PrimProcedure r15 = new gnu.expr.PrimProcedure     // Catch:{ SecurityException -> 0x00d1 }
            r9 = r20
            r15.<init>((gnu.bytecode.Method) r7, (gnu.expr.Language) r9)     // Catch:{ SecurityException -> 0x00d1 }
            r15.setName(r0)     // Catch:{ SecurityException -> 0x00d1 }
            r10 = r19
            int r2 = r15.isApplicable(r10)     // Catch:{ SecurityException -> 0x00d1 }
            if (r2 < 0) goto L_0x00c9
            if (r2 >= r13) goto L_0x00b0
            goto L_0x00c9
        L_0x00b0:
            if (r2 <= r13) goto L_0x00b4
            r11 = r15
            goto L_0x00c5
        L_0x00b4:
            if (r11 == 0) goto L_0x00c5
            gnu.mapping.MethodProc r14 = gnu.mapping.MethodProc.mostSpecific((gnu.mapping.MethodProc) r11, (gnu.mapping.MethodProc) r15)     // Catch:{ SecurityException -> 0x00d1 }
            gnu.expr.PrimProcedure r14 = (gnu.expr.PrimProcedure) r14     // Catch:{ SecurityException -> 0x00d1 }
            if (r14 != 0) goto L_0x00c2
            if (r13 <= 0) goto L_0x00c2
            r15 = 0
            return r15
        L_0x00c2:
            r15 = 0
            r11 = r14
            goto L_0x00c6
        L_0x00c5:
            r15 = 0
        L_0x00c6:
            r13 = r2
            r14 = r8
            goto L_0x00ca
        L_0x00c9:
            r15 = 0
        L_0x00ca:
            gnu.bytecode.Method r7 = r7.getNext()     // Catch:{ SecurityException -> 0x00d1 }
            r2 = r15
            goto L_0x0047
        L_0x00d1:
            r2 = r11
            goto L_0x00d4
        L_0x00d3:
            r15 = r2
        L_0x00d4:
            r11 = r2
        L_0x00d5:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PrimProcedure.getMethodFor(gnu.bytecode.ClassType, java.lang.String, gnu.expr.Declaration, gnu.bytecode.Type[], gnu.expr.Language):gnu.expr.PrimProcedure");
    }

    public String getName() {
        String name = super.getName();
        if (name != null) {
            return name;
        }
        String verboseName = getVerboseName();
        setName(verboseName);
        return verboseName;
    }

    public String getVerboseName() {
        StringBuffer stringBuffer = new StringBuffer(100);
        Method method2 = this.method;
        if (method2 == null) {
            stringBuffer.append("<op ");
            stringBuffer.append(this.op_code);
            stringBuffer.append('>');
        } else {
            stringBuffer.append(method2.getDeclaringClass().getName());
            stringBuffer.append('.');
            stringBuffer.append(this.method.getName());
        }
        stringBuffer.append('(');
        for (int i = 0; i < this.argTypes.length; i++) {
            if (i > 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append(this.argTypes[i].getName());
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        Type type = this.retType;
        stringBuffer.append(type == null ? "<unknown>" : type.getName());
        stringBuffer.append(' ');
        stringBuffer.append(getVerboseName());
        return stringBuffer.toString();
    }

    public void print(PrintWriter printWriter) {
        printWriter.print("#<primitive procedure ");
        printWriter.print(toString());
        printWriter.print('>');
    }
}
