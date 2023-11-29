package gnu.expr;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.OutPort;
import java.util.Hashtable;
import java.util.Vector;
import net.lingala.zip4j.util.InternalZipConstants;

public class ClassExp extends LambdaExp {
    public static final int CLASS_SPECIFIED = 65536;
    public static final int HAS_SUBCLASS = 131072;
    public static final int INTERFACE_SPECIFIED = 32768;
    public static final int IS_ABSTRACT = 16384;
    public String classNameSpecifier;
    public LambdaExp clinitMethod;
    boolean explicitInit;
    public LambdaExp initMethod;
    ClassType instanceType;
    boolean partsDeclared;
    boolean simple;
    public int superClassIndex = -1;
    public Expression[] supers;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return true;
    }

    public boolean isSimple() {
        return this.simple;
    }

    public void setSimple(boolean z) {
        this.simple = z;
    }

    public final boolean isAbstract() {
        return getFlag(16384);
    }

    public boolean isMakingClassPair() {
        return this.type != this.instanceType;
    }

    public Type getType() {
        return this.simple ? Compilation.typeClass : Compilation.typeClassType;
    }

    public ClassType getClassType() {
        return this.type;
    }

    public ClassExp() {
    }

    public ClassExp(boolean z) {
        this.simple = z;
        ClassType classType = new ClassType();
        this.type = classType;
        this.instanceType = classType;
    }

    public void compile(Compilation compilation, Target target) {
        if (!(target instanceof IgnoreTarget)) {
            compileMembers(compilation);
            compilePushClass(compilation, target);
        }
    }

    public void compilePushClass(Compilation compilation, Target target) {
        int i;
        ClassType classType;
        ClassType classType2 = this.type;
        CodeAttr code = compilation.getCode();
        compilation.loadClassRef(classType2);
        boolean needsClosureEnv = getNeedsClosureEnv();
        if (!isSimple() || needsClosureEnv) {
            if (isMakingClassPair() || needsClosureEnv) {
                ClassType classType3 = this.instanceType;
                if (classType2 == classType3) {
                    code.emitDup((Type) classType3);
                } else {
                    compilation.loadClassRef(classType3);
                }
                classType = ClassType.make("gnu.expr.PairClassType");
                i = needsClosureEnv ? 3 : 2;
            } else {
                classType = ClassType.make("gnu.bytecode.Type");
                i = 1;
            }
            Type[] typeArr = new Type[i];
            if (needsClosureEnv) {
                getOwningLambda().loadHeapFrame(compilation);
                i--;
                typeArr[i] = Type.pointer_type;
            }
            ClassType make = ClassType.make("java.lang.Class");
            while (true) {
                i--;
                if (i >= 0) {
                    typeArr[i] = make;
                } else {
                    code.emitInvokeStatic(classType.addMethod("make", typeArr, (Type) classType, 9));
                    target.compileFromStack(compilation, classType);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ClassType getCompiledClassType(Compilation compilation) {
        return this.type;
    }

    public void setTypes(Compilation compilation) {
        String str;
        int i;
        int length;
        int i2;
        Expression[] expressionArr = this.supers;
        int i3 = 0;
        int length2 = expressionArr == null ? 0 : expressionArr.length;
        ClassType[] classTypeArr = new ClassType[length2];
        String str2 = null;
        ClassType classType = null;
        int i4 = 0;
        for (int i5 = 0; i5 < length2; i5++) {
            Type typeFor = Language.getDefaultLanguage().getTypeFor(this.supers[i5]);
            if (!(typeFor instanceof ClassType)) {
                compilation.setLine(this.supers[i5]);
                compilation.error('e', "invalid super type");
            } else {
                ClassType classType2 = (ClassType) typeFor;
                try {
                    i2 = classType2.getModifiers();
                } catch (RuntimeException unused) {
                    if (compilation != null) {
                        compilation.error('e', "unknown super-type " + classType2.getName());
                    }
                    i2 = 0;
                }
                if ((i2 & 512) == 0) {
                    if (i4 < i5) {
                        compilation.error('e', "duplicate superclass for " + this);
                    }
                    this.superClassIndex = i5;
                    classType = classType2;
                } else {
                    classTypeArr[i4] = classType2;
                    i4++;
                }
            }
        }
        if (!(classType == null || (this.flags & 32768) == 0)) {
            compilation.error('e', "cannot be interface since has superclass");
        }
        if (!this.simple && classType == null && (this.flags & 65536) == 0 && (getFlag(131072) || (this.nameDecl != null && this.nameDecl.isPublic()))) {
            PairClassType pairClassType = new PairClassType();
            this.type = pairClassType;
            pairClassType.setInterface(true);
            pairClassType.instanceType = this.instanceType;
            ClassType[] classTypeArr2 = {this.type};
            this.instanceType.setSuper(Type.pointer_type);
            this.instanceType.setInterfaces(classTypeArr2);
        } else if (getFlag(32768)) {
            this.instanceType.setInterface(true);
        }
        ClassType classType3 = this.type;
        if (classType == null) {
            classType = Type.pointer_type;
        }
        classType3.setSuper(classType);
        if (i4 != length2) {
            ClassType[] classTypeArr3 = new ClassType[i4];
            System.arraycopy(classTypeArr, 0, classTypeArr3, 0, i4);
            classTypeArr = classTypeArr3;
        }
        this.type.setInterfaces(classTypeArr);
        if (this.type.getName() == null) {
            String str3 = this.classNameSpecifier;
            if (str3 == null && (str3 = getName()) != null && (length = str3.length()) > 2 && str3.charAt(0) == '<') {
                int i6 = length - 1;
                if (str3.charAt(i6) == '>') {
                    str3 = str3.substring(1, i6);
                }
            }
            if (str3 == null) {
                StringBuffer stringBuffer = new StringBuffer(100);
                compilation.getModule().classFor(compilation);
                stringBuffer.append(compilation.mainClass.getName());
                stringBuffer.append('$');
                int length3 = stringBuffer.length();
                while (true) {
                    stringBuffer.append(i3);
                    str = stringBuffer.toString();
                    if (compilation.findNamedClass(str) == null) {
                        break;
                    }
                    stringBuffer.setLength(length3);
                    i3++;
                }
            } else if (!isSimple() || (this instanceof ObjectExp)) {
                str = compilation.generateClassName(str3);
            } else {
                StringBuffer stringBuffer2 = new StringBuffer(100);
                int i7 = 0;
                while (true) {
                    int indexOf = str3.indexOf(46, i7);
                    if (indexOf < 0) {
                        break;
                    }
                    stringBuffer2.append(Compilation.mangleNameIfNeeded(str3.substring(i7, indexOf)));
                    i7 = indexOf + 1;
                    if (i7 < str3.length()) {
                        stringBuffer2.append('.');
                    }
                }
                if (i7 == 0) {
                    if (compilation.mainClass != null) {
                        str2 = compilation.mainClass.getName();
                    }
                    if (str2 == null) {
                        i = -1;
                    } else {
                        i = str2.lastIndexOf(46);
                    }
                    if (i > 0) {
                        stringBuffer2.append(str2.substring(0, i + 1));
                    } else if (compilation.classPrefix != null) {
                        stringBuffer2.append(compilation.classPrefix);
                    }
                } else if (i7 == 1 && i7 < str3.length()) {
                    stringBuffer2.setLength(0);
                    stringBuffer2.append(compilation.mainClass.getName());
                    stringBuffer2.append('$');
                }
                if (i7 < str3.length()) {
                    stringBuffer2.append(Compilation.mangleNameIfNeeded(str3.substring(i7)));
                }
                str = stringBuffer2.toString();
            }
            this.type.setName(str);
            compilation.addClass(this.type);
            if (isMakingClassPair()) {
                this.instanceType.setName(this.type.getName() + "$class");
                compilation.addClass(this.instanceType);
            }
        }
    }

    public void declareParts(Compilation compilation) {
        if (!this.partsDeclared) {
            this.partsDeclared = true;
            Hashtable hashtable = new Hashtable();
            for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
                if (firstDecl.getCanRead()) {
                    short accessFlags = firstDecl.getAccessFlags(1);
                    if (firstDecl.getFlag(2048)) {
                        accessFlags |= 8;
                    }
                    if (isMakingClassPair()) {
                        short s = accessFlags | Access.ABSTRACT;
                        Type implementationType = firstDecl.getType().getImplementationType();
                        this.type.addMethod(slotToMethodName("get", firstDecl.getName()), (int) s, Type.typeArray0, implementationType);
                        this.type.addMethod(slotToMethodName("set", firstDecl.getName()), (int) s, new Type[]{implementationType}, (Type) Type.voidType);
                    } else {
                        String mangleNameIfNeeded = Compilation.mangleNameIfNeeded(firstDecl.getName());
                        firstDecl.field = this.instanceType.addField(mangleNameIfNeeded, firstDecl.getType(), accessFlags);
                        firstDecl.setSimple(false);
                        Declaration declaration = (Declaration) hashtable.get(mangleNameIfNeeded);
                        if (declaration != null) {
                            duplicateDeclarationError(declaration, firstDecl, compilation);
                        }
                        hashtable.put(mangleNameIfNeeded, firstDecl);
                    }
                }
            }
            for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
                if (lambdaExp.isAbstract()) {
                    setFlag(16384);
                }
                if ("*init*".equals(lambdaExp.getName())) {
                    this.explicitInit = true;
                    if (lambdaExp.isAbstract()) {
                        compilation.error('e', "*init* method cannot be abstract", lambdaExp);
                    }
                    if (this.type instanceof PairClassType) {
                        compilation.error('e', "'*init*' methods only supported for simple classes");
                    }
                }
                lambdaExp.outer = this;
                if (!(lambdaExp == this.initMethod || lambdaExp == this.clinitMethod || lambdaExp.nameDecl == null || lambdaExp.nameDecl.getFlag(2048)) || !isMakingClassPair()) {
                    lambdaExp.addMethodFor(this.type, compilation, (ObjectType) null);
                }
                if (isMakingClassPair()) {
                    lambdaExp.addMethodFor(this.instanceType, compilation, this.type);
                }
            }
            if (!this.explicitInit && !this.instanceType.isInterface()) {
                Compilation.getConstructor(this.instanceType, this);
            }
            if (isAbstract()) {
                ClassType classType = this.instanceType;
                classType.setModifiers(classType.getModifiers() | 1024);
            }
            if (this.nameDecl != null) {
                ClassType classType2 = this.instanceType;
                classType2.setModifiers(this.nameDecl.getAccessFlags(1) | (classType2.getModifiers() & -2));
            }
        }
    }

    static void getImplMethods(ClassType classType, String str, Type[] typeArr, Vector vector) {
        ClassType classType2;
        if (classType instanceof PairClassType) {
            classType2 = ((PairClassType) classType).instanceType;
        } else if (classType.isInterface()) {
            try {
                Class reflectClass = classType.getReflectClass();
                if (reflectClass != null) {
                    classType2 = (ClassType) Type.make(Class.forName(classType.getName() + "$class", false, reflectClass.getClassLoader()));
                } else {
                    return;
                }
            } catch (Throwable unused) {
                return;
            }
        } else {
            return;
        }
        Type[] typeArr2 = new Type[(typeArr.length + 1)];
        typeArr2[0] = classType;
        System.arraycopy(typeArr, 0, typeArr2, 1, typeArr.length);
        Method declaredMethod = classType2.getDeclaredMethod(str, typeArr2);
        if (declaredMethod != null) {
            int size = vector.size();
            if (size == 0 || !vector.elementAt(size - 1).equals(declaredMethod)) {
                vector.addElement(declaredMethod);
                return;
            }
            return;
        }
        ClassType[] interfaces = classType.getInterfaces();
        for (ClassType implMethods : interfaces) {
            getImplMethods(implMethods, str, typeArr, vector);
        }
    }

    private static void usedSuperClasses(ClassType classType, Compilation compilation) {
        compilation.usedClass(classType.getSuperclass());
        ClassType[] interfaces = classType.getInterfaces();
        if (interfaces != null) {
            int length = interfaces.length;
            while (true) {
                length--;
                if (length >= 0) {
                    compilation.usedClass(interfaces[length]);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0133 A[Catch:{ all -> 0x02dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0146 A[Catch:{ all -> 0x02dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0152 A[Catch:{ all -> 0x02dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0162 A[Catch:{ all -> 0x02dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0168 A[Catch:{ all -> 0x02dd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.ClassType compileMembers(gnu.expr.Compilation r18) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            gnu.bytecode.ClassType r3 = r2.curClass
            gnu.bytecode.Method r4 = r2.method
            gnu.bytecode.ClassType r0 = r17.getCompiledClassType(r18)     // Catch:{ all -> 0x02dd }
            r2.curClass = r0     // Catch:{ all -> 0x02dd }
            gnu.expr.LambdaExp r5 = r17.outerLambda()     // Catch:{ all -> 0x02dd }
            boolean r6 = r5 instanceof gnu.expr.ClassExp     // Catch:{ all -> 0x02dd }
            if (r6 == 0) goto L_0x0019
            gnu.bytecode.ClassType r5 = r5.type     // Catch:{ all -> 0x02dd }
            goto L_0x0037
        L_0x0019:
            if (r5 == 0) goto L_0x0021
            boolean r6 = r5 instanceof gnu.expr.ModuleExp     // Catch:{ all -> 0x02dd }
            if (r6 != 0) goto L_0x0021
            r5 = r4
            goto L_0x0037
        L_0x0021:
            boolean r6 = r5 instanceof gnu.expr.ModuleExp     // Catch:{ all -> 0x02dd }
            if (r6 == 0) goto L_0x0036
            gnu.bytecode.ClassType r6 = r1.type     // Catch:{ all -> 0x02dd }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x02dd }
            r8 = 36
            int r6 = r6.indexOf(r8)     // Catch:{ all -> 0x02dd }
            if (r6 <= 0) goto L_0x0036
            gnu.bytecode.ClassType r5 = r5.type     // Catch:{ all -> 0x02dd }
            goto L_0x0037
        L_0x0036:
            r5 = 0
        L_0x0037:
            if (r5 == 0) goto L_0x0045
            r0.setEnclosingMember(r5)     // Catch:{ all -> 0x02dd }
            boolean r6 = r5 instanceof gnu.bytecode.ClassType     // Catch:{ all -> 0x02dd }
            if (r6 == 0) goto L_0x0045
            gnu.bytecode.ClassType r5 = (gnu.bytecode.ClassType) r5     // Catch:{ all -> 0x02dd }
            r5.addMemberClass(r0)     // Catch:{ all -> 0x02dd }
        L_0x0045:
            gnu.bytecode.ClassType r5 = r1.instanceType     // Catch:{ all -> 0x02dd }
            if (r5 == r0) goto L_0x0055
            gnu.bytecode.ClassType r6 = r1.type     // Catch:{ all -> 0x02dd }
            r5.setEnclosingMember(r6)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r5 = r1.type     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r6 = r1.instanceType     // Catch:{ all -> 0x02dd }
            r5.addMemberClass(r6)     // Catch:{ all -> 0x02dd }
        L_0x0055:
            gnu.bytecode.ClassType r5 = r1.type     // Catch:{ all -> 0x02dd }
            usedSuperClasses(r5, r2)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r5 = r1.type     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r6 = r1.instanceType     // Catch:{ all -> 0x02dd }
            if (r5 == r6) goto L_0x0063
            usedSuperClasses(r6, r2)     // Catch:{ all -> 0x02dd }
        L_0x0063:
            java.lang.String r5 = r17.getFileName()     // Catch:{ all -> 0x02dd }
            if (r5 == 0) goto L_0x006c
            r0.setSourceFile(r5)     // Catch:{ all -> 0x02dd }
        L_0x006c:
            gnu.expr.LambdaExp r5 = r2.curLambda     // Catch:{ all -> 0x02dd }
            r2.curLambda = r1     // Catch:{ all -> 0x02dd }
            r17.allocFrame(r18)     // Catch:{ all -> 0x02dd }
            gnu.expr.LambdaExp r6 = r1.firstChild     // Catch:{ all -> 0x02dd }
        L_0x0075:
            r10 = 1
            if (r6 == 0) goto L_0x0185
            boolean r11 = r6.isAbstract()     // Catch:{ all -> 0x02dd }
            if (r11 == 0) goto L_0x0080
            goto L_0x0181
        L_0x0080:
            gnu.bytecode.Method r11 = r2.method     // Catch:{ all -> 0x02dd }
            gnu.expr.LambdaExp r12 = r2.curLambda     // Catch:{ all -> 0x02dd }
            java.lang.String r13 = r18.getFileName()     // Catch:{ all -> 0x02dd }
            int r14 = r18.getLineNumber()     // Catch:{ all -> 0x02dd }
            int r15 = r18.getColumnNumber()     // Catch:{ all -> 0x02dd }
            r2.setLine((gnu.expr.Expression) r6)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method r7 = r6.getMainMethod()     // Catch:{ all -> 0x02dd }
            r2.method = r7     // Catch:{ all -> 0x02dd }
            gnu.expr.Declaration r7 = r6.nameDecl     // Catch:{ all -> 0x02dd }
            if (r7 == 0) goto L_0x00a5
            r8 = 2048(0x800, double:1.0118E-320)
            boolean r7 = r7.getFlag(r8)     // Catch:{ all -> 0x02dd }
            if (r7 != 0) goto L_0x00aa
        L_0x00a5:
            gnu.bytecode.ClassType r7 = r2.curClass     // Catch:{ all -> 0x02dd }
            r6.declareThis(r7)     // Catch:{ all -> 0x02dd }
        L_0x00aa:
            gnu.bytecode.ClassType r7 = r1.instanceType     // Catch:{ all -> 0x02dd }
            r2.curClass = r7     // Catch:{ all -> 0x02dd }
            r2.curLambda = r6     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method r7 = r2.method     // Catch:{ all -> 0x02dd }
            r7.initCode()     // Catch:{ all -> 0x02dd }
            r6.allocChildClasses(r2)     // Catch:{ all -> 0x02dd }
            r6.allocParameters(r2)     // Catch:{ all -> 0x02dd }
            java.lang.String r7 = "*init*"
            java.lang.String r8 = r6.getName()     // Catch:{ all -> 0x02dd }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x02dd }
            if (r7 == 0) goto L_0x016c
            gnu.bytecode.CodeAttr r7 = r18.getCode()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Field r8 = r1.staticLinkField     // Catch:{ all -> 0x02dd }
            if (r8 == 0) goto L_0x00e2
            r7.emitPushThis()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Scope r8 = r7.getCurrentScope()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Variable r8 = r8.getVariable(r10)     // Catch:{ all -> 0x02dd }
            r7.emitLoad(r8)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Field r8 = r1.staticLinkField     // Catch:{ all -> 0x02dd }
            r7.emitPutField(r8)     // Catch:{ all -> 0x02dd }
        L_0x00e2:
            gnu.expr.Expression r7 = r6.body     // Catch:{ all -> 0x02dd }
        L_0x00e4:
            boolean r8 = r7 instanceof gnu.expr.BeginExp     // Catch:{ all -> 0x02dd }
            if (r8 == 0) goto L_0x00f6
            gnu.expr.BeginExp r7 = (gnu.expr.BeginExp) r7     // Catch:{ all -> 0x02dd }
            int r8 = r7.length     // Catch:{ all -> 0x02dd }
            if (r8 != 0) goto L_0x00f0
            r7 = 0
            goto L_0x00e4
        L_0x00f0:
            gnu.expr.Expression[] r7 = r7.exps     // Catch:{ all -> 0x02dd }
            r8 = 0
            r7 = r7[r8]     // Catch:{ all -> 0x02dd }
            goto L_0x00e4
        L_0x00f6:
            boolean r8 = r7 instanceof gnu.expr.ApplyExp     // Catch:{ all -> 0x02dd }
            if (r8 == 0) goto L_0x012a
            r8 = r7
            gnu.expr.ApplyExp r8 = (gnu.expr.ApplyExp) r8     // Catch:{ all -> 0x02dd }
            gnu.expr.Expression r8 = r8.func     // Catch:{ all -> 0x02dd }
            boolean r9 = r8 instanceof gnu.expr.QuoteExp     // Catch:{ all -> 0x02dd }
            if (r9 == 0) goto L_0x012a
            gnu.expr.QuoteExp r8 = (gnu.expr.QuoteExp) r8     // Catch:{ all -> 0x02dd }
            java.lang.Object r8 = r8.getValue()     // Catch:{ all -> 0x02dd }
            boolean r9 = r8 instanceof gnu.expr.PrimProcedure     // Catch:{ all -> 0x02dd }
            if (r9 == 0) goto L_0x012a
            gnu.expr.PrimProcedure r8 = (gnu.expr.PrimProcedure) r8     // Catch:{ all -> 0x02dd }
            boolean r9 = r8.isSpecial()     // Catch:{ all -> 0x02dd }
            if (r9 == 0) goto L_0x012a
            java.lang.String r9 = "<init>"
            gnu.bytecode.Method r10 = r8.method     // Catch:{ all -> 0x02dd }
            java.lang.String r10 = r10.getName()     // Catch:{ all -> 0x02dd }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x02dd }
            if (r9 == 0) goto L_0x012a
            gnu.bytecode.Method r8 = r8.method     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r8 = r8.getDeclaringClass()     // Catch:{ all -> 0x02dd }
            goto L_0x012b
        L_0x012a:
            r8 = 0
        L_0x012b:
            gnu.bytecode.ClassType r9 = r1.instanceType     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r9 = r9.getSuperclass()     // Catch:{ all -> 0x02dd }
            if (r8 == 0) goto L_0x0146
            gnu.expr.Target r10 = gnu.expr.Target.Ignore     // Catch:{ all -> 0x02dd }
            r7.compileWithPosition(r2, r10)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r7 = r1.instanceType     // Catch:{ all -> 0x02dd }
            if (r8 == r7) goto L_0x014b
            if (r8 == r9) goto L_0x014b
            java.lang.String r7 = "call to <init> for not this or super class"
            r9 = 101(0x65, float:1.42E-43)
            r2.error(r9, r7)     // Catch:{ all -> 0x02dd }
            goto L_0x014b
        L_0x0146:
            if (r9 == 0) goto L_0x014b
            invokeDefaultSuperConstructor(r9, r2, r1)     // Catch:{ all -> 0x02dd }
        L_0x014b:
            r6.enterFunction(r2)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r7 = r1.instanceType     // Catch:{ all -> 0x02dd }
            if (r8 == r7) goto L_0x0160
            gnu.bytecode.ClassType r7 = r17.getCompiledClassType(r18)     // Catch:{ all -> 0x02dd }
            java.util.Vector r9 = new java.util.Vector     // Catch:{ all -> 0x02dd }
            r10 = 10
            r9.<init>(r10)     // Catch:{ all -> 0x02dd }
            r2.callInitMethods(r7, r9)     // Catch:{ all -> 0x02dd }
        L_0x0160:
            if (r8 == 0) goto L_0x0168
            gnu.expr.Expression r7 = r6.body     // Catch:{ all -> 0x02dd }
            gnu.expr.Expression.compileButFirst(r7, r2)     // Catch:{ all -> 0x02dd }
            goto L_0x0172
        L_0x0168:
            r6.compileBody(r2)     // Catch:{ all -> 0x02dd }
            goto L_0x0172
        L_0x016c:
            r6.enterFunction(r2)     // Catch:{ all -> 0x02dd }
            r6.compileBody(r2)     // Catch:{ all -> 0x02dd }
        L_0x0172:
            r6.compileEnd(r2)     // Catch:{ all -> 0x02dd }
            r6.generateApplyMethods(r2)     // Catch:{ all -> 0x02dd }
            r2.method = r11     // Catch:{ all -> 0x02dd }
            r2.curClass = r0     // Catch:{ all -> 0x02dd }
            r2.curLambda = r12     // Catch:{ all -> 0x02dd }
            r2.setLine(r13, r14, r15)     // Catch:{ all -> 0x02dd }
        L_0x0181:
            gnu.expr.LambdaExp r6 = r6.nextSibling     // Catch:{ all -> 0x02dd }
            goto L_0x0075
        L_0x0185:
            boolean r6 = r1.explicitInit     // Catch:{ all -> 0x02dd }
            if (r6 != 0) goto L_0x0197
            gnu.bytecode.ClassType r6 = r1.instanceType     // Catch:{ all -> 0x02dd }
            boolean r6 = r6.isInterface()     // Catch:{ all -> 0x02dd }
            if (r6 != 0) goto L_0x0197
            gnu.bytecode.ClassType r6 = r1.instanceType     // Catch:{ all -> 0x02dd }
            r2.generateConstructor(r6, r1)     // Catch:{ all -> 0x02dd }
            goto L_0x01a2
        L_0x0197:
            gnu.expr.Initializer r6 = r1.initChain     // Catch:{ all -> 0x02dd }
            if (r6 == 0) goto L_0x01a2
            gnu.expr.Initializer r6 = r1.initChain     // Catch:{ all -> 0x02dd }
            java.lang.String r7 = "unimplemented: explicit constructor cannot initialize "
            r6.reportError(r7, r2)     // Catch:{ all -> 0x02dd }
        L_0x01a2:
            boolean r6 = r17.isAbstract()     // Catch:{ all -> 0x02dd }
            if (r6 == 0) goto L_0x01ab
            r7 = 0
            r8 = 0
            goto L_0x01b3
        L_0x01ab:
            gnu.bytecode.ClassType r6 = r1.type     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method[] r7 = r6.getAbstractMethods()     // Catch:{ all -> 0x02dd }
            int r6 = r7.length     // Catch:{ all -> 0x02dd }
            r8 = r6
        L_0x01b3:
            r6 = 0
        L_0x01b4:
            if (r6 >= r8) goto L_0x02d3
            r9 = r7[r6]     // Catch:{ all -> 0x02dd }
            java.lang.String r11 = r9.getName()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Type[] r12 = r9.getParameterTypes()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Type r13 = r9.getReturnType()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r14 = r1.instanceType     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method r14 = r14.getMethod(r11, r12)     // Catch:{ all -> 0x02dd }
            if (r14 == 0) goto L_0x01da
            boolean r14 = r14.isAbstract()     // Catch:{ all -> 0x02dd }
            if (r14 != 0) goto L_0x01da
        L_0x01d2:
            r16 = r7
        L_0x01d4:
            r9 = 101(0x65, float:1.42E-43)
        L_0x01d6:
            r11 = 0
            r14 = 1
            goto L_0x02cc
        L_0x01da:
            int r14 = r11.length()     // Catch:{ all -> 0x02dd }
            r15 = 3
            if (r14 <= r15) goto L_0x026c
            r14 = 2
            char r14 = r11.charAt(r14)     // Catch:{ all -> 0x02dd }
            r15 = 116(0x74, float:1.63E-43)
            if (r14 != r15) goto L_0x026c
            char r14 = r11.charAt(r10)     // Catch:{ all -> 0x02dd }
            r15 = 101(0x65, float:1.42E-43)
            if (r14 != r15) goto L_0x026c
            r14 = 0
            char r15 = r11.charAt(r14)     // Catch:{ all -> 0x02dd }
            r14 = 115(0x73, float:1.61E-43)
            r10 = 103(0x67, float:1.44E-43)
            if (r15 == r10) goto L_0x01ff
            if (r15 != r14) goto L_0x026c
        L_0x01ff:
            if (r15 != r14) goto L_0x020f
            boolean r9 = r13.isVoid()     // Catch:{ all -> 0x02dd }
            if (r9 == 0) goto L_0x020f
            int r9 = r12.length     // Catch:{ all -> 0x02dd }
            r14 = 1
            if (r9 != r14) goto L_0x020f
            r9 = 0
            r14 = r12[r9]     // Catch:{ all -> 0x02dd }
            goto L_0x0215
        L_0x020f:
            if (r15 != r10) goto L_0x01d2
            int r9 = r12.length     // Catch:{ all -> 0x02dd }
            if (r9 != 0) goto L_0x01d2
            r14 = r13
        L_0x0215:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x02dd }
            r9.<init>()     // Catch:{ all -> 0x02dd }
            r10 = 3
            char r10 = r11.charAt(r10)     // Catch:{ all -> 0x02dd }
            char r10 = java.lang.Character.toLowerCase(r10)     // Catch:{ all -> 0x02dd }
            r9.append(r10)     // Catch:{ all -> 0x02dd }
            r10 = 4
            java.lang.String r10 = r11.substring(r10)     // Catch:{ all -> 0x02dd }
            r9.append(r10)     // Catch:{ all -> 0x02dd }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r10 = r1.instanceType     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Field r10 = r10.getField(r9)     // Catch:{ all -> 0x02dd }
            if (r10 != 0) goto L_0x0244
            gnu.bytecode.ClassType r10 = r1.instanceType     // Catch:{ all -> 0x02dd }
            r16 = r7
            r7 = 1
            gnu.bytecode.Field r10 = r10.addField(r9, r14, r7)     // Catch:{ all -> 0x02dd }
            goto L_0x0247
        L_0x0244:
            r16 = r7
            r7 = 1
        L_0x0247:
            gnu.bytecode.ClassType r9 = r1.instanceType     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method r9 = r9.addMethod((java.lang.String) r11, (int) r7, (gnu.bytecode.Type[]) r12, (gnu.bytecode.Type) r13)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.CodeAttr r7 = r9.startCode()     // Catch:{ all -> 0x02dd }
            r7.emitPushThis()     // Catch:{ all -> 0x02dd }
            r9 = 103(0x67, float:1.44E-43)
            if (r15 != r9) goto L_0x025c
            r7.emitGetField(r10)     // Catch:{ all -> 0x02dd }
            goto L_0x0267
        L_0x025c:
            r9 = 1
            gnu.bytecode.Variable r11 = r7.getArg(r9)     // Catch:{ all -> 0x02dd }
            r7.emitLoad(r11)     // Catch:{ all -> 0x02dd }
            r7.emitPutField(r10)     // Catch:{ all -> 0x02dd }
        L_0x0267:
            r7.emitReturn()     // Catch:{ all -> 0x02dd }
            goto L_0x01d4
        L_0x026c:
            r16 = r7
            java.util.Vector r7 = new java.util.Vector     // Catch:{ all -> 0x02dd }
            r7.<init>()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.ClassType r10 = r1.type     // Catch:{ all -> 0x02dd }
            getImplMethods(r10, r11, r12, r7)     // Catch:{ all -> 0x02dd }
            int r10 = r7.size()     // Catch:{ all -> 0x02dd }
            r14 = 1
            if (r10 == r14) goto L_0x02a0
            int r7 = r7.size()     // Catch:{ all -> 0x02dd }
            if (r7 != 0) goto L_0x0288
            java.lang.String r7 = "missing implementation for "
            goto L_0x028a
        L_0x0288:
            java.lang.String r7 = "ambiguous implementation for "
        L_0x028a:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x02dd }
            r10.<init>()     // Catch:{ all -> 0x02dd }
            r10.append(r7)     // Catch:{ all -> 0x02dd }
            r10.append(r9)     // Catch:{ all -> 0x02dd }
            java.lang.String r7 = r10.toString()     // Catch:{ all -> 0x02dd }
            r9 = 101(0x65, float:1.42E-43)
            r2.error(r9, r7)     // Catch:{ all -> 0x02dd }
            goto L_0x01d6
        L_0x02a0:
            r9 = 101(0x65, float:1.42E-43)
            gnu.bytecode.ClassType r10 = r1.instanceType     // Catch:{ all -> 0x02dd }
            r14 = 1
            gnu.bytecode.Method r10 = r10.addMethod((java.lang.String) r11, (int) r14, (gnu.bytecode.Type[]) r12, (gnu.bytecode.Type) r13)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.CodeAttr r10 = r10.startCode()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Scope r11 = r10.getCurrentScope()     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Variable r11 = r11.firstVar()     // Catch:{ all -> 0x02dd }
        L_0x02b5:
            if (r11 == 0) goto L_0x02bf
            r10.emitLoad(r11)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Variable r11 = r11.nextVar()     // Catch:{ all -> 0x02dd }
            goto L_0x02b5
        L_0x02bf:
            r11 = 0
            java.lang.Object r7 = r7.elementAt(r11)     // Catch:{ all -> 0x02dd }
            gnu.bytecode.Method r7 = (gnu.bytecode.Method) r7     // Catch:{ all -> 0x02dd }
            r10.emitInvokeStatic(r7)     // Catch:{ all -> 0x02dd }
            r10.emitReturn()     // Catch:{ all -> 0x02dd }
        L_0x02cc:
            int r6 = r6 + 1
            r7 = r16
            r10 = 1
            goto L_0x01b4
        L_0x02d3:
            r17.generateApplyMethods(r18)     // Catch:{ all -> 0x02dd }
            r2.curLambda = r5     // Catch:{ all -> 0x02dd }
            r2.curClass = r3
            r2.method = r4
            return r0
        L_0x02dd:
            r0 = move-exception
            r2.curClass = r3
            r2.method = r4
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ClassExp.compileMembers(gnu.expr.Compilation):gnu.bytecode.ClassType");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        Compilation compilation = expVisitor.getCompilation();
        if (compilation == null) {
            return expVisitor.visitClassExp(this, d);
        }
        ClassType classType = compilation.curClass;
        try {
            compilation.curClass = this.type;
            R visitClassExp = expVisitor.visitClassExp(this, d);
            compilation.curClass = classType;
            return visitClassExp;
        } catch (Throwable th) {
            compilation.curClass = classType;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        Declaration firstDecl;
        LambdaExp lambdaExp = expVisitor.currentLambda;
        expVisitor.currentLambda = this;
        Expression[] expressionArr = this.supers;
        this.supers = expVisitor.visitExps(expressionArr, expressionArr.length, d);
        try {
            for (LambdaExp lambdaExp2 = this.firstChild; lambdaExp2 != null && expVisitor.exitValue == null; lambdaExp2 = lambdaExp2.nextSibling) {
                if (!(this.instanceType == null || (firstDecl = lambdaExp2.firstDecl()) == null || !firstDecl.isThisParameter())) {
                    firstDecl.setType(this.type);
                }
                expVisitor.visitLambdaExp(lambdaExp2, d);
            }
        } finally {
            expVisitor.currentLambda = lambdaExp;
        }
    }

    static void loadSuperStaticLink(Expression expression, ClassType classType, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        expression.compile(compilation, Target.pushValue(Compilation.typeClassType));
        code.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
        code.emitCheckcast(classType.getOuterLinkType());
    }

    static void invokeDefaultSuperConstructor(ClassType classType, Compilation compilation, LambdaExp lambdaExp) {
        CodeAttr code = compilation.getCode();
        Method declaredMethod = classType.getDeclaredMethod("<init>", 0);
        if (declaredMethod == null) {
            compilation.error('e', "super class does not have a default constructor");
            return;
        }
        code.emitPushThis();
        if (classType.hasOuterLink() && (lambdaExp instanceof ClassExp)) {
            ClassExp classExp = (ClassExp) lambdaExp;
            loadSuperStaticLink(classExp.supers[classExp.superClassIndex], classType, compilation);
        }
        code.emitInvokeSpecial(declaredMethod);
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(" + getExpClassName() + InternalZipConstants.ZIP_FILE_SEPARATOR, ")", 2);
        Object symbol = getSymbol();
        if (symbol != null) {
            outPort.print(symbol);
            outPort.print('/');
        }
        outPort.print(this.id);
        outPort.print("/fl:");
        outPort.print(Integer.toHexString(this.flags));
        int i = 0;
        if (this.supers.length > 0) {
            outPort.writeSpaceFill();
            outPort.startLogicalBlock("supers:", "", 2);
            int i2 = 0;
            while (true) {
                Expression[] expressionArr = this.supers;
                if (i2 >= expressionArr.length) {
                    break;
                }
                expressionArr[i2].print(outPort);
                outPort.writeSpaceFill();
                i2++;
            }
            outPort.endLogicalBlock("");
        }
        outPort.print('(');
        if (this.keywords != null) {
            int length = this.keywords.length;
        }
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (i > 0) {
                outPort.print(' ');
            }
            firstDecl.printInfo(outPort);
            i++;
        }
        outPort.print(") ");
        for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
            outPort.writeBreakLinear();
            lambdaExp.print(outPort);
        }
        if (this.body != null) {
            outPort.writeBreakLinear();
            this.body.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }

    public Field compileSetField(Compilation compilation) {
        return new ClassInitializer(this, compilation).field;
    }

    public static String slotToMethodName(String str, String str2) {
        if (!Compilation.isValidJavaName(str2)) {
            str2 = Compilation.mangleName(str2, false);
        }
        int length = str2.length();
        StringBuffer stringBuffer = new StringBuffer(length + 3);
        stringBuffer.append(str);
        if (length > 0) {
            stringBuffer.append(Character.toTitleCase(str2.charAt(0)));
            stringBuffer.append(str2.substring(1));
        }
        return stringBuffer.toString();
    }

    public Declaration addMethod(LambdaExp lambdaExp, Object obj) {
        Declaration addDeclaration = addDeclaration(obj, Compilation.typeProcedure);
        lambdaExp.outer = this;
        lambdaExp.setClassMethod(true);
        addDeclaration.noteValue(lambdaExp);
        addDeclaration.setFlag(1048576);
        addDeclaration.setProcedureDecl(true);
        lambdaExp.setSymbol(obj);
        return addDeclaration;
    }
}
