package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import java.util.Set;
import java.util.Vector;

public class LambdaExp extends ScopeExp {
    public static final int ATTEMPT_INLINE = 4096;
    static final int CANNOT_INLINE = 32;
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CLASS_METHOD = 64;
    static final int DEFAULT_CAPTURES_ARG = 512;
    static final int IMPORTS_LEX_VARS = 8;
    static final int INLINE_ONLY = 8192;
    static final int METHODS_COMPILED = 128;
    static final int NEEDS_STATIC_LINK = 16;
    protected static final int NEXT_AVAIL_FLAG = 16384;
    public static final int NO_FIELD = 256;
    public static final int OVERLOADABLE_FIELD = 2048;
    public static final int SEQUENCE_RESULT = 1024;
    static Method searchForKeywordMethod3;
    static Method searchForKeywordMethod4;
    static final ApplyExp unknownContinuation = new ApplyExp((Expression) null, (Expression[]) null);
    Vector applyMethods;
    Variable argsArray;
    public Expression body;
    Declaration capturedVars;
    Variable closureEnv;
    public Field closureEnvField;
    public Expression[] defaultArgs;
    private Declaration firstArgsArrayArg;
    public LambdaExp firstChild;
    Variable heapFrame;
    Initializer initChain;
    public LambdaExp inlineHome;
    public Keyword[] keywords;
    public int max_args;
    public int min_args;
    public Declaration nameDecl;
    public LambdaExp nextSibling;
    Method[] primBodyMethods;
    Method[] primMethods;
    Object[] properties;
    public Expression returnContinuation;
    public Type returnType;
    int selectorValue;
    public Field staticLinkField;
    Set<LambdaExp> tailCallers;
    Procedure thisValue;
    Variable thisVariable;
    Expression[] throwsSpecification;
    ClassType type = Compilation.typeProcedure;

    public boolean side_effects() {
        return false;
    }

    public void capture(Declaration declaration) {
        if (declaration.isSimple()) {
            if (this.capturedVars == null && !declaration.isStatic() && !(this instanceof ModuleExp) && !(this instanceof ClassExp)) {
                this.heapFrame = new Variable("heapFrame");
            }
            declaration.setSimple(false);
            if (!declaration.isPublic()) {
                declaration.nextCapturedVar = this.capturedVars;
                this.capturedVars = declaration;
            }
        }
    }

    static {
        Expression expression = null;
        Expression[] expressionArr = null;
    }

    public void setExceptions(Expression[] expressionArr) {
        this.throwsSpecification = expressionArr;
    }

    public final boolean getInlineOnly() {
        return (this.flags & 8192) != 0;
    }

    public final void setInlineOnly(boolean z) {
        setFlag(z, 8192);
    }

    public final boolean getNeedsClosureEnv() {
        return (this.flags & 24) != 0;
    }

    public final boolean getNeedsStaticLink() {
        return (this.flags & 16) != 0;
    }

    public final void setNeedsStaticLink(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final boolean getImportsLexVars() {
        return (this.flags & 8) != 0;
    }

    public final void setImportsLexVars(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void setImportsLexVars() {
        int i = this.flags;
        this.flags |= 8;
        if ((i & 8) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    public final void setNeedsStaticLink() {
        int i = this.flags;
        this.flags |= 16;
        if ((i & 16) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    /* access modifiers changed from: package-private */
    public void setCallersNeedStaticLink() {
        LambdaExp outerLambda = outerLambda();
        for (ApplyExp applyExp = this.nameDecl.firstCall; applyExp != null; applyExp = applyExp.nextCall) {
            LambdaExp lambdaExp = applyExp.context;
            while (lambdaExp != outerLambda && !(lambdaExp instanceof ModuleExp)) {
                lambdaExp.setNeedsStaticLink();
                lambdaExp = lambdaExp.outerLambda();
            }
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean z) {
        if (z) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final boolean isClassMethod() {
        return (this.flags & 64) != 0;
    }

    public final void setClassMethod(boolean z) {
        if (z) {
            this.flags |= 64;
        } else {
            this.flags &= -65;
        }
    }

    public final boolean isModuleBody() {
        return this instanceof ModuleExp;
    }

    public final boolean isClassGenerated() {
        return isModuleBody() || (this instanceof ClassExp);
    }

    public boolean isAbstract() {
        return this.body == QuoteExp.abstractExp;
    }

    public int getCallConvention() {
        if (isModuleBody()) {
            if (Compilation.defaultCallConvention >= 2) {
                return Compilation.defaultCallConvention;
            }
            return 2;
        } else if (!isClassMethod() && Compilation.defaultCallConvention != 0) {
            return Compilation.defaultCallConvention;
        } else {
            return 1;
        }
    }

    public final boolean isHandlingTailCalls() {
        return isModuleBody() || (Compilation.defaultCallConvention >= 3 && !isClassMethod());
    }

    public final boolean variable_args() {
        return this.max_args < 0;
    }

    /* access modifiers changed from: protected */
    public ClassType getCompiledClassType(Compilation compilation) {
        if (this.type != Compilation.typeProcedure) {
            return this.type;
        }
        throw new Error("internal error: getCompiledClassType");
    }

    public Type getType() {
        return this.type;
    }

    public ClassType getClassType() {
        return this.type;
    }

    public void setType(ClassType classType) {
        this.type = classType;
    }

    public int incomingArgs() {
        int i = this.min_args;
        int i2 = this.max_args;
        if (i != i2 || i2 > 4 || i2 <= 0) {
            return 1;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int getSelectorValue(Compilation compilation) {
        int i = this.selectorValue;
        if (i != 0) {
            return i;
        }
        int i2 = compilation.maxSelectorValue;
        compilation.maxSelectorValue = this.primMethods.length + i2;
        int i3 = i2 + 1;
        this.selectorValue = i3;
        return i3;
    }

    public final Method getMethod(int i) {
        int i2;
        int i3;
        Method[] methodArr = this.primMethods;
        if (methodArr == null || (((i2 = this.max_args) >= 0 && i > i2) || (i3 = i - this.min_args) < 0)) {
            return null;
        }
        int length = methodArr.length;
        if (i3 >= length) {
            i3 = length - 1;
        }
        return methodArr[i3];
    }

    public final Method getMainMethod() {
        Method[] methodArr = this.primBodyMethods;
        if (methodArr == null) {
            return null;
        }
        return methodArr[methodArr.length - 1];
    }

    public final Type restArgType() {
        int i = this.min_args;
        int i2 = this.max_args;
        if (i == i2) {
            return null;
        }
        Method[] methodArr = this.primMethods;
        if (methodArr == null) {
            throw new Error("internal error - restArgType");
        } else if (i2 >= 0 && methodArr.length > i2 - i) {
            return null;
        } else {
            Method method = methodArr[methodArr.length - 1];
            Type[] parameterTypes = method.getParameterTypes();
            int length = parameterTypes.length - 1;
            if (method.getName().endsWith("$X")) {
                length--;
            }
            return parameterTypes[length];
        }
    }

    public LambdaExp outerLambda() {
        if (this.outer == null) {
            return null;
        }
        return this.outer.currentLambda();
    }

    public LambdaExp outerLambdaNotInline() {
        ScopeExp scopeExp = this;
        while (true) {
            scopeExp = scopeExp.outer;
            if (scopeExp == null) {
                return null;
            }
            if (scopeExp instanceof LambdaExp) {
                LambdaExp lambdaExp = (LambdaExp) scopeExp;
                if (!lambdaExp.getInlineOnly()) {
                    return lambdaExp;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean inlinedIn(LambdaExp lambdaExp) {
        for (LambdaExp lambdaExp2 = this; lambdaExp2.getInlineOnly(); lambdaExp2 = lambdaExp2.getCaller()) {
            if (lambdaExp2 == lambdaExp) {
                return true;
            }
        }
        return false;
    }

    public LambdaExp getCaller() {
        return this.inlineHome;
    }

    public Variable declareThis(ClassType classType) {
        if (this.thisVariable == null) {
            this.thisVariable = new Variable("this");
            getVarScope().addVariableAfter((Variable) null, this.thisVariable);
            this.thisVariable.setParameter(true);
        }
        if (this.thisVariable.getType() == null) {
            this.thisVariable.setType(classType);
        }
        if (this.decls != null && this.decls.isThisParameter()) {
            this.decls.var = this.thisVariable;
        }
        return this.thisVariable;
    }

    public Variable declareClosureEnv() {
        if (this.closureEnv == null && getNeedsClosureEnv()) {
            LambdaExp outerLambda = outerLambda();
            if (outerLambda instanceof ClassExp) {
                outerLambda = outerLambda.outerLambda();
            }
            Variable variable = outerLambda.heapFrame;
            if (variable == null) {
                variable = outerLambda.closureEnv;
            }
            if (!isClassMethod() || "*init*".equals(getName())) {
                Variable variable2 = null;
                if (outerLambda.heapFrame == null && !outerLambda.getNeedsStaticLink() && !(outerLambda instanceof ModuleExp)) {
                    this.closureEnv = null;
                } else if (!isClassGenerated() && !getInlineOnly()) {
                    Method mainMethod = getMainMethod();
                    boolean equals = "*init*".equals(getName());
                    if (mainMethod.getStaticFlag() || equals) {
                        this.closureEnv = new Variable("closureEnv", mainMethod.getParameterTypes()[0]);
                        if (equals) {
                            variable2 = declareThis(mainMethod.getDeclaringClass());
                        }
                        getVarScope().addVariableAfter(variable2, this.closureEnv);
                        this.closureEnv.setParameter(true);
                    } else {
                        this.closureEnv = declareThis(mainMethod.getDeclaringClass());
                    }
                } else if (inlinedIn(outerLambda)) {
                    this.closureEnv = variable;
                } else {
                    this.closureEnv = new Variable("closureEnv", variable.getType());
                    getVarScope().addVariable(this.closureEnv);
                }
            } else {
                this.closureEnv = declareThis(this.type);
            }
        }
        return this.closureEnv;
    }

    public LambdaExp() {
    }

    public LambdaExp(int i) {
        this.min_args = i;
        this.max_args = i;
    }

    public LambdaExp(Expression expression) {
        this.body = expression;
    }

    public void loadHeapFrame(Compilation compilation) {
        ClassType classType;
        LambdaExp lambdaExp = compilation.curLambda;
        while (lambdaExp != this && lambdaExp.getInlineOnly()) {
            lambdaExp = lambdaExp.getCaller();
        }
        CodeAttr code = compilation.getCode();
        Variable variable = lambdaExp.heapFrame;
        if (variable == null || this != lambdaExp) {
            Variable variable2 = lambdaExp.closureEnv;
            if (variable2 != null) {
                code.emitLoad(variable2);
                classType = (ClassType) lambdaExp.closureEnv.getType();
            } else {
                code.emitPushThis();
                classType = compilation.curClass;
            }
            while (lambdaExp != this) {
                Field field = lambdaExp.staticLinkField;
                if (field != null && field.getDeclaringClass() == classType) {
                    code.emitGetField(field);
                    classType = (ClassType) field.getType();
                }
                lambdaExp = lambdaExp.outerLambda();
            }
            return;
        }
        code.emitLoad(variable);
    }

    /* access modifiers changed from: package-private */
    public Declaration getArg(int i) {
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (i == 0) {
                return firstDecl;
            }
            i--;
        }
        throw new Error("internal error - getArg");
    }

    public void compileEnd(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (!getInlineOnly()) {
            if (compilation.method.reachableHere() && (Compilation.defaultCallConvention < 3 || isModuleBody() || isClassMethod() || isHandlingTailCalls())) {
                code.emitReturn();
            }
            popScope(code);
            code.popScope();
        }
        for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
            if (!lambdaExp.getCanRead() && !lambdaExp.getInlineOnly()) {
                lambdaExp.compileAsMethod(compilation);
            }
        }
        if (this.heapFrame != null) {
            compilation.generateConstructor(this);
        }
    }

    public void generateApplyMethods(Compilation compilation) {
        compilation.generateMatchMethods(this);
        if (Compilation.defaultCallConvention >= 2) {
            compilation.generateApplyMethodsWithContext(this);
        } else {
            compilation.generateApplyMethodsWithoutContext(this);
        }
    }

    /* access modifiers changed from: package-private */
    public Field allocFieldFor(Compilation compilation) {
        String str;
        String sb;
        Declaration declaration = this.nameDecl;
        if (declaration != null && declaration.field != null) {
            return this.nameDecl.field;
        }
        boolean needsClosureEnv = getNeedsClosureEnv();
        ClassType heapFrameType = needsClosureEnv ? getOwningLambda().getHeapFrameType() : compilation.mainClass;
        String name = getName();
        if (name == null) {
            str = "lambda";
        } else {
            str = Compilation.mangleNameIfNeeded(name);
        }
        int i = 16;
        Declaration declaration2 = this.nameDecl;
        int i2 = 1;
        if (declaration2 == null || !(declaration2.context instanceof ModuleExp)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("$Fn");
            int i3 = compilation.localFieldIndex + 1;
            compilation.localFieldIndex = i3;
            sb2.append(i3);
            str = sb2.toString();
            if (!needsClosureEnv) {
                i = 24;
            }
        } else {
            boolean needsExternalAccess = this.nameDecl.needsExternalAccess();
            if (needsExternalAccess) {
                str = Declaration.PRIVATE_PREFIX + str;
            }
            if (this.nameDecl.getFlag(2048)) {
                i = !((ModuleExp) this.nameDecl.context).isStatic() ? 8 : 24;
            }
            if (!this.nameDecl.isPrivate() || needsExternalAccess || compilation.immediate) {
                i |= 1;
            }
            if ((this.flags & 2048) != 0) {
                int i4 = this.min_args;
                if (i4 == this.max_args) {
                    i2 = i4;
                }
                while (true) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append('$');
                    int i5 = i2 + 1;
                    sb3.append(i2);
                    sb = sb3.toString();
                    if (heapFrameType.getDeclaredField(sb) == null) {
                        break;
                    }
                    i2 = i5;
                }
                str = sb;
            }
        }
        Field addField = heapFrameType.addField(str, Compilation.typeModuleMethod, i);
        Declaration declaration3 = this.nameDecl;
        if (declaration3 != null) {
            declaration3.field = addField;
        }
        return addField;
    }

    /* access modifiers changed from: package-private */
    public final void addApplyMethod(Compilation compilation, Field field) {
        LambdaExp lambdaExp;
        if (field == null || !field.getStaticFlag()) {
            LambdaExp lambdaExp2 = this;
            do {
                lambdaExp2 = lambdaExp2.outerLambda();
                if ((lambdaExp2 instanceof ModuleExp) || lambdaExp2.heapFrame != null) {
                }
                lambdaExp2 = lambdaExp2.outerLambda();
                break;
            } while (lambdaExp2.heapFrame != null);
            lambdaExp = !lambdaExp2.getHeapFrameType().getSuperclass().isSubtype(Compilation.typeModuleBody) ? compilation.getModule() : lambdaExp2;
        } else {
            lambdaExp = compilation.getModule();
        }
        if (lambdaExp.applyMethods == null) {
            lambdaExp.applyMethods = new Vector();
        }
        lambdaExp.applyMethods.addElement(this);
    }

    public Field compileSetField(Compilation compilation) {
        if (this.primMethods == null) {
            allocMethod(outerLambda(), compilation);
        }
        Field allocFieldFor = allocFieldFor(compilation);
        if (compilation.usingCPStyle()) {
            compile(compilation, (Type) Type.objectType);
        } else {
            compileAsMethod(compilation);
            addApplyMethod(compilation, allocFieldFor);
        }
        return new ProcInitializer(this, compilation, allocFieldFor).field;
    }

    public void compile(Compilation compilation, Target target) {
        if (!(target instanceof IgnoreTarget)) {
            CodeAttr code = compilation.getCode();
            LambdaExp outerLambda = outerLambda();
            ClassType classType = Compilation.typeModuleMethod;
            if ((this.flags & 256) != 0 || (compilation.immediate && (outerLambda instanceof ModuleExp))) {
                if (this.primMethods == null) {
                    allocMethod(outerLambda(), compilation);
                }
                compileAsMethod(compilation);
                addApplyMethod(compilation, (Field) null);
                ProcInitializer.emitLoadModuleMethod(this, compilation);
            } else {
                Field compileSetField = compileSetField(compilation);
                if (compileSetField.getStaticFlag()) {
                    code.emitGetStatic(compileSetField);
                } else {
                    LambdaExp lambdaExp = compilation.curLambda;
                    Variable variable = lambdaExp.heapFrame;
                    if (variable == null) {
                        variable = lambdaExp.closureEnv;
                    }
                    code.emitLoad(variable);
                    code.emitGetField(compileSetField);
                }
            }
            target.compileFromStack(compilation, classType);
        }
    }

    public ClassType getHeapFrameType() {
        if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
            return (ClassType) getType();
        }
        return (ClassType) this.heapFrame.getType();
    }

    public LambdaExp getOwningLambda() {
        for (ScopeExp scopeExp = this.outer; scopeExp != null; scopeExp = scopeExp.outer) {
            if ((scopeExp instanceof ModuleExp) || (((scopeExp instanceof ClassExp) && getNeedsClosureEnv()) || ((scopeExp instanceof LambdaExp) && ((LambdaExp) scopeExp).heapFrame != null))) {
                return (LambdaExp) scopeExp;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void addMethodFor(Compilation compilation, ObjectType objectType) {
        ClassType classType;
        ScopeExp scopeExp = this;
        while (scopeExp != null && !(scopeExp instanceof ClassExp)) {
            scopeExp = scopeExp.outer;
        }
        if (scopeExp != null) {
            classType = ((ClassExp) scopeExp).instanceType;
        } else {
            classType = getOwningLambda().getHeapFrameType();
        }
        addMethodFor(classType, compilation, objectType);
    }

    /* JADX WARNING: type inference failed for: r31v0, types: [gnu.bytecode.ObjectType] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x017c, code lost:
        if (r4.getFlag(8192) != false) goto L_0x016f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01fd A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0209 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0217  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0122 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0129  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addMethodFor(gnu.bytecode.ClassType r29, gnu.expr.Compilation r30, gnu.bytecode.ObjectType r31) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r31
            java.lang.String r4 = r28.getName()
            gnu.expr.LambdaExp r5 = r28.outerLambda()
            gnu.expr.Keyword[] r6 = r0.keywords
            if (r6 != 0) goto L_0x0016
            r6 = 0
            goto L_0x0017
        L_0x0016:
            int r6 = r6.length
        L_0x0017:
            gnu.expr.Expression[] r8 = r0.defaultArgs
            if (r8 != 0) goto L_0x001d
            r8 = 0
            goto L_0x001f
        L_0x001d:
            int r8 = r8.length
            int r8 = r8 - r6
        L_0x001f:
            int r9 = r0.flags
            r9 = r9 & 512(0x200, float:7.175E-43)
            if (r9 == 0) goto L_0x0027
            r9 = 0
            goto L_0x0028
        L_0x0027:
            r9 = r8
        L_0x0028:
            int r10 = r0.max_args
            r11 = 1
            if (r10 < 0) goto L_0x0035
            int r12 = r0.min_args
            int r12 = r12 + r9
            if (r12 >= r10) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r10 = 0
            goto L_0x0036
        L_0x0035:
            r10 = 1
        L_0x0036:
            int r12 = r9 + 1
            gnu.bytecode.Method[] r12 = new gnu.bytecode.Method[r12]
            r0.primBodyMethods = r12
            gnu.bytecode.Method[] r13 = r0.primMethods
            if (r13 != 0) goto L_0x0042
            r0.primMethods = r12
        L_0x0042:
            gnu.expr.Declaration r13 = r0.nameDecl
            r16 = r8
            if (r13 == 0) goto L_0x0053
            r7 = 4096(0x1000, double:2.0237E-320)
            boolean r7 = r13.getFlag(r7)
            if (r7 == 0) goto L_0x0053
        L_0x0050:
            r7 = 0
            r14 = 0
            goto L_0x00af
        L_0x0053:
            gnu.expr.Declaration r7 = r0.nameDecl
            if (r7 == 0) goto L_0x0062
            r14 = 2048(0x800, double:1.0118E-320)
            boolean r7 = r7.getFlag(r14)
            if (r7 == 0) goto L_0x0062
        L_0x005f:
            r7 = 0
        L_0x0060:
            r14 = 1
            goto L_0x00af
        L_0x0062:
            boolean r7 = r28.isClassMethod()
            if (r7 == 0) goto L_0x0088
            boolean r7 = r5 instanceof gnu.expr.ClassExp
            if (r7 == 0) goto L_0x0050
            r7 = r5
            gnu.expr.ClassExp r7 = (gnu.expr.ClassExp) r7
            boolean r14 = r7.isMakingClassPair()
            if (r14 == 0) goto L_0x0079
            if (r3 == 0) goto L_0x0079
            r14 = 1
            goto L_0x007a
        L_0x0079:
            r14 = 0
        L_0x007a:
            gnu.expr.LambdaExp r15 = r7.initMethod
            if (r0 != r15) goto L_0x0081
            r7 = 73
            goto L_0x00af
        L_0x0081:
            gnu.expr.LambdaExp r7 = r7.clinitMethod
            if (r0 != r7) goto L_0x00ae
            r7 = 67
            goto L_0x0060
        L_0x0088:
            gnu.bytecode.Variable r7 = r0.thisVariable
            if (r7 != 0) goto L_0x0050
            if (r3 != r1) goto L_0x008f
            goto L_0x0050
        L_0x008f:
            gnu.expr.Declaration r7 = r0.nameDecl
            if (r7 == 0) goto L_0x005f
            gnu.expr.ScopeExp r7 = r7.context
            boolean r7 = r7 instanceof gnu.expr.ModuleExp
            if (r7 == 0) goto L_0x005f
            gnu.expr.Declaration r7 = r0.nameDecl
            gnu.expr.ScopeExp r7 = r7.context
            gnu.expr.ModuleExp r7 = (gnu.expr.ModuleExp) r7
            gnu.bytecode.ClassType r14 = r7.getSuperType()
            if (r14 != 0) goto L_0x00ad
            gnu.bytecode.ClassType[] r7 = r7.getInterfaces()
            if (r7 != 0) goto L_0x00ad
            r14 = 1
            goto L_0x00ae
        L_0x00ad:
            r14 = 0
        L_0x00ae:
            r7 = 0
        L_0x00af:
            java.lang.StringBuffer r15 = new java.lang.StringBuffer
            r8 = 60
            r15.<init>(r8)
            if (r14 == 0) goto L_0x00bb
            r8 = 8
            goto L_0x00bc
        L_0x00bb:
            r8 = 0
        L_0x00bc:
            gnu.expr.Declaration r13 = r0.nameDecl
            if (r13 == 0) goto L_0x00dd
            boolean r13 = r13.needsExternalAccess()
            if (r13 == 0) goto L_0x00c9
            r8 = r8 | 1
            goto L_0x00dd
        L_0x00c9:
            gnu.expr.Declaration r13 = r0.nameDecl
            boolean r13 = r13.isPrivate()
            r13 = r13 ^ r11
            boolean r19 = r28.isClassMethod()
            if (r19 == 0) goto L_0x00dc
            gnu.expr.Declaration r11 = r0.nameDecl
            short r13 = r11.getAccessFlags(r13)
        L_0x00dc:
            r8 = r8 | r13
        L_0x00dd:
            boolean r11 = r5.isModuleBody()
            if (r11 != 0) goto L_0x00e7
            boolean r11 = r5 instanceof gnu.expr.ClassExp
            if (r11 == 0) goto L_0x00e9
        L_0x00e7:
            if (r4 != 0) goto L_0x00f7
        L_0x00e9:
            java.lang.String r11 = "lambda"
            r15.append(r11)
            int r11 = r2.method_counter
            r13 = 1
            int r11 = r11 + r13
            r2.method_counter = r11
            r15.append(r11)
        L_0x00f7:
            r11 = 67
            if (r7 != r11) goto L_0x0101
            java.lang.String r4 = "<clinit>"
            r15.append(r4)
            goto L_0x010e
        L_0x0101:
            java.lang.Object r11 = r28.getSymbol()
            if (r11 == 0) goto L_0x010e
            java.lang.String r4 = gnu.expr.Compilation.mangleName(r4)
            r15.append(r4)
        L_0x010e:
            r4 = 1024(0x400, float:1.435E-42)
            boolean r11 = r0.getFlag(r4)
            if (r11 == 0) goto L_0x011b
            java.lang.String r11 = "$C"
            r15.append(r11)
        L_0x011b:
            int r11 = r28.getCallConvention()
            r13 = 2
            if (r11 < r13) goto L_0x0126
            if (r7 != 0) goto L_0x0126
            r11 = 1
            goto L_0x0127
        L_0x0126:
            r11 = 0
        L_0x0127:
            if (r7 == 0) goto L_0x0133
            if (r14 == 0) goto L_0x0130
            r8 = r8 & -3
            r14 = 1
            int r8 = r8 + r14
            goto L_0x0133
        L_0x0130:
            r8 = r8 & 2
            int r8 = r8 + r13
        L_0x0133:
            boolean r14 = r29.isInterface()
            if (r14 != 0) goto L_0x013f
            boolean r14 = r28.isAbstract()
            if (r14 == 0) goto L_0x0141
        L_0x013f:
            r8 = r8 | 1024(0x400, float:1.435E-42)
        L_0x0141:
            boolean r14 = r28.isClassMethod()
            r17 = 0
            if (r14 == 0) goto L_0x01de
            boolean r14 = r5 instanceof gnu.expr.ClassExp
            if (r14 == 0) goto L_0x01de
            int r14 = r0.min_args
            int r4 = r0.max_args
            if (r14 != r4) goto L_0x01d7
            gnu.expr.Declaration r4 = r28.firstDecl()
            r21 = r17
            r14 = 0
        L_0x015a:
            if (r4 != 0) goto L_0x0165
            gnu.bytecode.Type r13 = r0.returnType
            if (r13 == 0) goto L_0x0162
            goto L_0x01d7
        L_0x0162:
            r22 = r12
            goto L_0x017f
        L_0x0165:
            boolean r13 = r4.isThisParameter()
            if (r13 == 0) goto L_0x0174
            int r14 = r14 + -1
            r22 = r12
        L_0x016f:
            r24 = r21
            r21 = r8
            goto L_0x01c9
        L_0x0174:
            r22 = r12
            r12 = 8192(0x2000, double:4.0474E-320)
            boolean r12 = r4.getFlag(r12)
            if (r12 == 0) goto L_0x017f
            goto L_0x016f
        L_0x017f:
            if (r21 != 0) goto L_0x018f
            java.lang.String r12 = r15.toString()
            gnu.expr.LambdaExp$1 r13 = new gnu.expr.LambdaExp$1
            r13.<init>(r12)
            r12 = 2
            gnu.bytecode.Method[] r21 = r1.getMethods((gnu.bytecode.Filter) r13, (int) r12)
        L_0x018f:
            r12 = r21
            int r13 = r12.length
            r21 = r8
            r8 = r17
        L_0x0196:
            int r13 = r13 + -1
            if (r13 < 0) goto L_0x01b9
            r23 = r12[r13]
            if (r4 != 0) goto L_0x01a3
            gnu.bytecode.Type r23 = r23.getReturnType()
            goto L_0x01a9
        L_0x01a3:
            gnu.bytecode.Type[] r23 = r23.getParameterTypes()
            r23 = r23[r14]
        L_0x01a9:
            r24 = r12
            r12 = r23
            if (r8 != 0) goto L_0x01b1
            r8 = r12
            goto L_0x01b6
        L_0x01b1:
            if (r12 == r8) goto L_0x01b6
            if (r4 != 0) goto L_0x01c9
            goto L_0x01c8
        L_0x01b6:
            r12 = r24
            goto L_0x0196
        L_0x01b9:
            r24 = r12
            if (r8 == 0) goto L_0x01c6
            if (r4 == 0) goto L_0x01c3
            r4.setType(r8)
            goto L_0x01c6
        L_0x01c3:
            r0.setCoercedReturnType(r8)
        L_0x01c6:
            if (r4 != 0) goto L_0x01c9
        L_0x01c8:
            goto L_0x01db
        L_0x01c9:
            gnu.expr.Declaration r4 = r4.nextDecl()
            r8 = 1
            int r14 = r14 + r8
            r8 = r21
            r12 = r22
            r21 = r24
            r13 = 2
            goto L_0x015a
        L_0x01d7:
            r21 = r8
            r22 = r12
        L_0x01db:
            r4 = 1024(0x400, float:1.435E-42)
            goto L_0x01e2
        L_0x01de:
            r21 = r8
            r22 = r12
        L_0x01e2:
            boolean r4 = r0.getFlag(r4)
            if (r4 != 0) goto L_0x01f9
            int r4 = r28.getCallConvention()
            r8 = 2
            if (r4 < r8) goto L_0x01f0
            goto L_0x01f9
        L_0x01f0:
            gnu.bytecode.Type r4 = r28.getReturnType()
            gnu.bytecode.Type r4 = r4.getImplementationType()
            goto L_0x01fb
        L_0x01f9:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.voidType
        L_0x01fb:
            if (r3 == 0) goto L_0x0201
            if (r3 == r1) goto L_0x0201
            r13 = 1
            goto L_0x0202
        L_0x0201:
            r13 = 0
        L_0x0202:
            int r8 = r28.getCallConvention()
            r12 = 2
            if (r8 < r12) goto L_0x020d
            if (r7 != 0) goto L_0x020d
            r7 = 1
            goto L_0x020e
        L_0x020d:
            r7 = 0
        L_0x020e:
            int r8 = r15.length()
            r14 = r21
            r12 = 0
        L_0x0215:
            if (r12 > r9) goto L_0x0413
            r15.setLength(r8)
            r20 = r8
            int r8 = r0.min_args
            int r8 = r8 + r12
            if (r12 != r9) goto L_0x022c
            if (r10 == 0) goto L_0x022c
            int r21 = r8 + 1
            r27 = r21
            r21 = r10
            r10 = r27
            goto L_0x022f
        L_0x022c:
            r21 = r10
            r10 = r8
        L_0x022f:
            int r23 = r13 + r10
            int r2 = r23 + r7
            r23 = r12
            gnu.bytecode.Type[] r12 = new gnu.bytecode.Type[r2]
            r18 = 0
            if (r13 <= 0) goto L_0x023d
            r12[r18] = r3
        L_0x023d:
            gnu.expr.Declaration r24 = r28.firstDecl()
            if (r24 == 0) goto L_0x024d
            boolean r25 = r24.isThisParameter()
            if (r25 == 0) goto L_0x024d
            gnu.expr.Declaration r24 = r24.nextDecl()
        L_0x024d:
            r3 = 0
        L_0x024e:
            if (r3 >= r8) goto L_0x0264
            int r25 = r3 + 1
            int r3 = r3 + r13
            gnu.bytecode.Type r26 = r24.getType()
            gnu.bytecode.Type r26 = r26.getImplementationType()
            r12[r3] = r26
            gnu.expr.Declaration r24 = r24.nextDecl()
            r3 = r25
            goto L_0x024e
        L_0x0264:
            if (r7 == 0) goto L_0x026c
            int r3 = r2 + -1
            gnu.bytecode.ClassType r25 = gnu.expr.Compilation.typeCallContext
            r12[r3] = r25
        L_0x026c:
            if (r8 >= r10) goto L_0x02c0
            gnu.bytecode.Type r3 = r24.getType()
            java.lang.String r8 = r3.getName()
            int r10 = r29.getClassfileVersion()
            r25 = r7
            r7 = 3211264(0x310000, float:4.49994E-39)
            if (r10 < r7) goto L_0x0287
            boolean r7 = r3 instanceof gnu.bytecode.ArrayType
            if (r7 == 0) goto L_0x0287
            r14 = r14 | 128(0x80, float:1.794E-43)
            goto L_0x028c
        L_0x0287:
            java.lang.String r7 = "$V"
            r15.append(r7)
        L_0x028c:
            r7 = r16
            if (r6 > 0) goto L_0x029e
            if (r9 < r7) goto L_0x029e
            java.lang.String r10 = "gnu.lists.LList"
            boolean r8 = r10.equals(r8)
            if (r8 != 0) goto L_0x02b3
            boolean r8 = r3 instanceof gnu.bytecode.ArrayType
            if (r8 != 0) goto L_0x02b3
        L_0x029e:
            gnu.bytecode.ArrayType r3 = gnu.expr.Compilation.objArrayType
            gnu.bytecode.Variable r8 = new gnu.bytecode.Variable
            gnu.bytecode.ArrayType r10 = gnu.expr.Compilation.objArrayType
            r16 = r3
            java.lang.String r3 = "argsArray"
            r8.<init>(r3, r10)
            r0.argsArray = r8
            r3 = 1
            r8.setParameter(r3)
            r3 = r16
        L_0x02b3:
            r8 = r24
            r0.firstArgsArrayArg = r8
            if (r11 == 0) goto L_0x02bb
            r8 = 2
            goto L_0x02bc
        L_0x02bb:
            r8 = 1
        L_0x02bc:
            int r2 = r2 - r8
            r12[r2] = r3
            goto L_0x02c4
        L_0x02c0:
            r25 = r7
            r7 = r16
        L_0x02c4:
            if (r11 == 0) goto L_0x02cb
            java.lang.String r2 = "$X"
            r15.append(r2)
        L_0x02cb:
            boolean r2 = r5 instanceof gnu.expr.ClassExp
            if (r2 != 0) goto L_0x02e1
            boolean r2 = r5 instanceof gnu.expr.ModuleExp
            if (r2 == 0) goto L_0x02df
            r2 = r5
            gnu.expr.ModuleExp r2 = (gnu.expr.ModuleExp) r2
            r3 = 131072(0x20000, float:1.83671E-40)
            boolean r2 = r2.getFlag(r3)
            if (r2 == 0) goto L_0x02df
            goto L_0x02e1
        L_0x02df:
            r2 = 0
            goto L_0x02e2
        L_0x02e1:
            r2 = 1
        L_0x02e2:
            java.lang.String r3 = r15.toString()
            int r8 = r15.length()
            r10 = 0
        L_0x02eb:
            r16 = r5
            r5 = r1
        L_0x02ee:
            if (r5 == 0) goto L_0x0316
            gnu.bytecode.Method r24 = r5.getDeclaredMethod((java.lang.String) r3, (gnu.bytecode.Type[]) r12)
            if (r24 == 0) goto L_0x030c
            r15.setLength(r8)
            r3 = 36
            r15.append(r3)
            r19 = 1
            int r10 = r10 + 1
            r15.append(r10)
            java.lang.String r3 = r15.toString()
            r5 = r16
            goto L_0x02eb
        L_0x030c:
            r19 = 1
            if (r2 == 0) goto L_0x0311
            goto L_0x0318
        L_0x0311:
            gnu.bytecode.ClassType r5 = r5.getSuperclass()
            goto L_0x02ee
        L_0x0316:
            r19 = 1
        L_0x0318:
            gnu.bytecode.Method r2 = r1.addMethod((java.lang.String) r3, (gnu.bytecode.Type[]) r12, (gnu.bytecode.Type) r4, (int) r14)
            r22[r23] = r2
            gnu.expr.Expression[] r3 = r0.throwsSpecification
            if (r3 == 0) goto L_0x03fc
            int r5 = r3.length
            if (r5 <= 0) goto L_0x03fc
            int r3 = r3.length
            gnu.bytecode.ClassType[] r5 = new gnu.bytecode.ClassType[r3]
            r8 = 0
        L_0x0329:
            if (r8 >= r3) goto L_0x03f1
            gnu.expr.Expression[] r10 = r0.throwsSpecification
            r10 = r10[r8]
            boolean r12 = r10 instanceof gnu.expr.ReferenceExp
            if (r12 == 0) goto L_0x038d
            r12 = r10
            gnu.expr.ReferenceExp r12 = (gnu.expr.ReferenceExp) r12
            gnu.expr.Declaration r24 = r12.getBinding()
            if (r24 == 0) goto L_0x0371
            gnu.expr.Expression r12 = r24.getValue()
            boolean r0 = r12 instanceof gnu.expr.ClassExp
            if (r0 == 0) goto L_0x034f
            gnu.expr.ClassExp r12 = (gnu.expr.ClassExp) r12
            r0 = r30
            gnu.bytecode.ClassType r12 = r12.getCompiledClassType(r0)
            r1 = r17
            goto L_0x036d
        L_0x034f:
            r0 = r30
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r1 = "throws specification "
            r12.append(r1)
            java.lang.String r1 = r24.getName()
            r12.append(r1)
            java.lang.String r1 = " has non-class lexical binding"
            r12.append(r1)
            java.lang.String r1 = r12.toString()
            r12 = r17
        L_0x036d:
            r24 = r3
            goto L_0x03d6
        L_0x0371:
            r0 = r30
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r24 = r3
            java.lang.String r3 = "unknown class "
            r1.append(r3)
            java.lang.String r3 = r12.getName()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r12 = r17
            goto L_0x03d6
        L_0x038d:
            r0 = r30
            r24 = r3
            boolean r1 = r10 instanceof gnu.expr.QuoteExp
            if (r1 == 0) goto L_0x03d3
            r1 = r10
            gnu.expr.QuoteExp r1 = (gnu.expr.QuoteExp) r1
            java.lang.Object r1 = r1.getValue()
            boolean r3 = r1 instanceof java.lang.Class
            if (r3 == 0) goto L_0x03a6
            java.lang.Class r1 = (java.lang.Class) r1
            gnu.bytecode.Type r1 = gnu.bytecode.Type.make(r1)
        L_0x03a6:
            boolean r3 = r1 instanceof gnu.bytecode.ClassType
            if (r3 == 0) goto L_0x03ae
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            r12 = r1
            goto L_0x03b0
        L_0x03ae:
            r12 = r17
        L_0x03b0:
            if (r12 == 0) goto L_0x03d0
            gnu.bytecode.ClassType r1 = gnu.bytecode.Type.javalangThrowableType
            boolean r1 = r12.isSubtype(r1)
            if (r1 != 0) goto L_0x03d0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r12.getName()
            r1.append(r3)
            java.lang.String r3 = " does not extend Throwable"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            goto L_0x03d6
        L_0x03d0:
            r1 = r17
            goto L_0x03d6
        L_0x03d3:
            r1 = r17
            r12 = r1
        L_0x03d6:
            if (r12 != 0) goto L_0x03dc
            if (r1 != 0) goto L_0x03dc
            java.lang.String r1 = "invalid throws specification"
        L_0x03dc:
            if (r1 == 0) goto L_0x03e5
            r3 = 101(0x65, float:1.42E-43)
            r0.error(r3, r1, r10)
            gnu.bytecode.ClassType r12 = gnu.bytecode.Type.javalangThrowableType
        L_0x03e5:
            r5[r8] = r12
            int r8 = r8 + 1
            r0 = r28
            r1 = r29
            r3 = r24
            goto L_0x0329
        L_0x03f1:
            r0 = r30
            gnu.bytecode.ExceptionsAttr r1 = new gnu.bytecode.ExceptionsAttr
            r1.<init>(r2)
            r1.setExceptions(r5)
            goto L_0x03fe
        L_0x03fc:
            r0 = r30
        L_0x03fe:
            int r12 = r23 + 1
            r1 = r29
            r3 = r31
            r2 = r0
            r5 = r16
            r8 = r20
            r10 = r21
            r0 = r28
            r16 = r7
            r7 = r25
            goto L_0x0215
        L_0x0413:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.addMethodFor(gnu.bytecode.ClassType, gnu.expr.Compilation, gnu.bytecode.ObjectType):void");
    }

    public void allocChildClasses(Compilation compilation) {
        Declaration declaration;
        Method mainMethod = getMainMethod();
        if (mainMethod != null && !mainMethod.getStaticFlag()) {
            declareThis(mainMethod.getDeclaringClass());
        }
        Declaration firstDecl = firstDecl();
        while (true) {
            if (firstDecl == this.firstArgsArrayArg && this.argsArray != null) {
                getVarScope().addVariable(this.argsArray);
            }
            if (!getInlineOnly() && getCallConvention() >= 2 && ((declaration = this.firstArgsArrayArg) != null ? !(this.argsArray == null ? firstDecl != declaration.nextDecl() : firstDecl != declaration) : firstDecl == null)) {
                getVarScope().addVariable((CodeAttr) null, Compilation.typeCallContext, "$ctx").setParameter(true);
            }
            if (firstDecl == null) {
                declareClosureEnv();
                allocFrame(compilation);
                allocChildMethods(compilation);
                return;
            }
            if (firstDecl.var == null && (!getInlineOnly() || !firstDecl.ignorable())) {
                if (!firstDecl.isSimple() || firstDecl.isIndirectBinding()) {
                    String intern = Compilation.mangleName(firstDecl.getName()).intern();
                    Variable addVariable = getVarScope().addVariable((CodeAttr) null, firstDecl.getType().getImplementationType(), intern);
                    firstDecl.var = addVariable;
                    addVariable.setParameter(true);
                } else {
                    firstDecl.allocateVariable((CodeAttr) null);
                }
            }
            firstDecl = firstDecl.nextDecl();
        }
    }

    /* access modifiers changed from: package-private */
    public void allocMethod(LambdaExp lambdaExp, Compilation compilation) {
        ClassType classType;
        Variable variable;
        if (!getNeedsClosureEnv()) {
            classType = null;
        } else if ((lambdaExp instanceof ClassExp) || (lambdaExp instanceof ModuleExp)) {
            classType = lambdaExp.getCompiledClassType(compilation);
        } else {
            while (true) {
                variable = lambdaExp.heapFrame;
                if (variable != null) {
                    break;
                }
                lambdaExp = lambdaExp.outerLambda();
            }
            classType = (ClassType) variable.getType();
        }
        addMethodFor(compilation, classType);
    }

    /* access modifiers changed from: package-private */
    public void allocChildMethods(Compilation compilation) {
        ClassType classType;
        for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
            if (!lambdaExp.isClassGenerated() && !lambdaExp.getInlineOnly() && lambdaExp.nameDecl != null) {
                lambdaExp.allocMethod(this, compilation);
            }
            if (lambdaExp instanceof ClassExp) {
                ClassExp classExp = (ClassExp) lambdaExp;
                if (classExp.getNeedsClosureEnv()) {
                    if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                        classType = (ClassType) getType();
                    } else {
                        Variable variable = this.heapFrame;
                        if (variable == null) {
                            variable = this.closureEnv;
                        }
                        classType = (ClassType) variable.getType();
                    }
                    Field outerLink = classExp.instanceType.setOuterLink(classType);
                    classExp.staticLinkField = outerLink;
                    classExp.closureEnvField = outerLink;
                }
            }
        }
    }

    public void allocFrame(Compilation compilation) {
        ClassType classType;
        if (this.heapFrame != null) {
            if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                classType = getCompiledClassType(compilation);
            } else {
                classType = new ClassType(compilation.generateClassName("frame"));
                classType.setSuper(compilation.getModuleType());
                compilation.addClass(classType);
            }
            this.heapFrame.setType(classType);
        }
    }

    /* access modifiers changed from: package-private */
    public void allocParameters(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        code.locals.enterScope(getVarScope());
        int lineNumber = getLineNumber();
        if (lineNumber > 0) {
            code.putLineNumber(getFileName(), lineNumber);
        }
        Variable variable = this.heapFrame;
        if (variable != null) {
            variable.allocateLocal(code);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: gnu.expr.Declaration} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: gnu.expr.Declaration} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: gnu.bytecode.Variable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: gnu.expr.Declaration} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: gnu.bytecode.Variable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: gnu.bytecode.Variable} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0296  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enterFunction(gnu.expr.Compilation r25) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            gnu.bytecode.CodeAttr r2 = r25.getCode()
            gnu.bytecode.Scope r3 = r24.getVarScope()
            r3.noteStartFunction(r2)
            gnu.bytecode.Variable r3 = r0.closureEnv
            if (r3 == 0) goto L_0x0051
            boolean r3 = r3.isParameter()
            if (r3 != 0) goto L_0x0051
            boolean r3 = r25.usingCPStyle()
            if (r3 != 0) goto L_0x0051
            boolean r3 = r24.getInlineOnly()
            if (r3 != 0) goto L_0x003b
            r2.emitPushThis()
            gnu.bytecode.Field r3 = r0.closureEnvField
            if (r3 != 0) goto L_0x0032
            gnu.expr.LambdaExp r3 = r24.outerLambda()
            gnu.bytecode.Field r3 = r3.closureEnvField
        L_0x0032:
            r2.emitGetField(r3)
            gnu.bytecode.Variable r3 = r0.closureEnv
            r2.emitStore(r3)
            goto L_0x0051
        L_0x003b:
            gnu.expr.LambdaExp r3 = r24.outerLambda()
            boolean r3 = r0.inlinedIn(r3)
            if (r3 != 0) goto L_0x0051
            gnu.expr.LambdaExp r3 = r24.outerLambda()
            r3.loadHeapFrame(r1)
            gnu.bytecode.Variable r3 = r0.closureEnv
            r2.emitStore(r3)
        L_0x0051:
            boolean r3 = r25.usingCPStyle()
            r4 = 0
            if (r3 != 0) goto L_0x007a
            gnu.bytecode.Variable r3 = r0.heapFrame
            if (r3 != 0) goto L_0x0065
            gnu.expr.ModuleExp r3 = r24.currentModule()
            gnu.bytecode.ClassType r3 = r3.getCompiledClassType(r1)
            goto L_0x006b
        L_0x0065:
            gnu.bytecode.Type r3 = r3.getType()
            gnu.bytecode.ClassType r3 = (gnu.bytecode.ClassType) r3
        L_0x006b:
            gnu.expr.Declaration r5 = r0.capturedVars
        L_0x006d:
            if (r5 == 0) goto L_0x007a
            gnu.bytecode.Field r6 = r5.field
            if (r6 == 0) goto L_0x0074
            goto L_0x0077
        L_0x0074:
            r5.makeField(r3, r1, r4)
        L_0x0077:
            gnu.expr.Declaration r5 = r5.nextCapturedVar
            goto L_0x006d
        L_0x007a:
            gnu.bytecode.Variable r3 = r0.heapFrame
            if (r3 == 0) goto L_0x00d0
            boolean r3 = r25.usingCPStyle()
            if (r3 != 0) goto L_0x00d0
            gnu.bytecode.Variable r3 = r0.heapFrame
            gnu.bytecode.Type r3 = r3.getType()
            gnu.bytecode.ClassType r3 = (gnu.bytecode.ClassType) r3
            gnu.bytecode.Variable r5 = r0.closureEnv
            if (r5 == 0) goto L_0x00a0
            boolean r6 = r0 instanceof gnu.expr.ModuleExp
            if (r6 != 0) goto L_0x00a0
            gnu.bytecode.Type r5 = r5.getType()
            java.lang.String r6 = "staticLink"
            gnu.bytecode.Field r5 = r3.addField(r6, r5)
            r0.staticLinkField = r5
        L_0x00a0:
            boolean r5 = r0 instanceof gnu.expr.ModuleExp
            if (r5 != 0) goto L_0x00d0
            boolean r5 = r0 instanceof gnu.expr.ClassExp
            if (r5 != 0) goto L_0x00d0
            gnu.bytecode.Method r5 = r1.method
            r3.setEnclosingMember(r5)
            r2.emitNew(r3)
            r2.emitDup((gnu.bytecode.Type) r3)
            gnu.bytecode.Method r5 = gnu.expr.Compilation.getConstructor(r3, r0)
            r2.emitInvokeSpecial(r5)
            gnu.bytecode.Field r5 = r0.staticLinkField
            if (r5 == 0) goto L_0x00cb
            r2.emitDup((gnu.bytecode.Type) r3)
            gnu.bytecode.Variable r3 = r0.closureEnv
            r2.emitLoad(r3)
            gnu.bytecode.Field r3 = r0.staticLinkField
            r2.emitPutField(r3)
        L_0x00cb:
            gnu.bytecode.Variable r3 = r0.heapFrame
            r2.emitStore(r3)
        L_0x00d0:
            gnu.bytecode.Variable r3 = r0.argsArray
            int r5 = r0.min_args
            int r6 = r0.max_args
            r7 = 2
            if (r5 != r6) goto L_0x00e4
            gnu.bytecode.Method[] r5 = r0.primMethods
            if (r5 != 0) goto L_0x00e4
            int r5 = r24.getCallConvention()
            if (r5 >= r7) goto L_0x00e4
            r3 = r4
        L_0x00e4:
            gnu.expr.Keyword[] r5 = r0.keywords
            if (r5 != 0) goto L_0x00ea
            r5 = 0
            goto L_0x00eb
        L_0x00ea:
            int r5 = r5.length
        L_0x00eb:
            gnu.expr.Expression[] r8 = r0.defaultArgs
            if (r8 != 0) goto L_0x00f1
            r8 = 0
            goto L_0x00f3
        L_0x00f1:
            int r8 = r8.length
            int r8 = r8 - r5
        L_0x00f3:
            boolean r5 = r0 instanceof gnu.expr.ModuleExp
            if (r5 == 0) goto L_0x00f8
            return
        L_0x00f8:
            r5 = -1
            r24.getMainMethod()
            gnu.bytecode.Variable r9 = r1.callContextVar
            gnu.expr.Declaration r10 = r24.firstDecl()
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0106:
            if (r10 == 0) goto L_0x02ab
            int r15 = r24.getCallConvention()
            if (r15 >= r7) goto L_0x0110
            r7 = r4
            goto L_0x011a
        L_0x0110:
            gnu.bytecode.Scope r15 = r24.getVarScope()
            java.lang.String r7 = "$ctx"
            gnu.bytecode.Variable r7 = r15.lookup(r7)
        L_0x011a:
            r1.callContextVar = r7
            gnu.expr.Declaration r7 = r0.firstArgsArrayArg
            if (r10 != r7) goto L_0x012e
            if (r3 == 0) goto L_0x012e
            gnu.bytecode.Method[] r5 = r0.primMethods
            if (r5 == 0) goto L_0x012c
            int r5 = r0.min_args
            int r12 = r11 - r5
            r5 = r11
            goto L_0x012e
        L_0x012c:
            r5 = 0
            r12 = 0
        L_0x012e:
            if (r5 >= 0) goto L_0x0147
            boolean r7 = r10.isSimple()
            if (r7 == 0) goto L_0x0147
            boolean r7 = r10.isIndirectBinding()
            if (r7 == 0) goto L_0x013d
            goto L_0x0147
        L_0x013d:
            r19 = r3
            r20 = r5
            r23 = r8
            r16 = 2
            goto L_0x029b
        L_0x0147:
            gnu.bytecode.Type r7 = r10.getType()
            if (r5 < 0) goto L_0x0150
            gnu.bytecode.ClassType r15 = gnu.bytecode.Type.objectType
            goto L_0x0151
        L_0x0150:
            r15 = r7
        L_0x0151:
            boolean r17 = r10.isSimple()
            if (r17 != 0) goto L_0x015a
            r10.loadOwningObject(r4, r1)
        L_0x015a:
            if (r5 >= 0) goto L_0x0164
            gnu.bytecode.Variable r4 = r10.getVariable()
            r2.emitLoad(r4)
            goto L_0x0173
        L_0x0164:
            int r4 = r0.min_args
            if (r11 >= r4) goto L_0x017d
            r2.emitLoad(r3)
            r2.emitPushInt(r11)
            gnu.bytecode.ClassType r4 = gnu.bytecode.Type.objectType
            r2.emitArrayLoad(r4)
        L_0x0173:
            r19 = r3
            r20 = r5
        L_0x0177:
            r23 = r8
            r16 = 2
            goto L_0x026d
        L_0x017d:
            int r6 = r4 + r8
            if (r11 >= r6) goto L_0x01ae
            int r4 = r11 - r5
            r2.emitPushInt(r4)
            r2.emitLoad(r3)
            r2.emitArrayLength()
            r2.emitIfIntLt()
            r2.emitLoad(r3)
            r2.emitPushInt(r4)
            r2.emitArrayLoad()
            r2.emitElse()
            gnu.expr.Expression[] r4 = r0.defaultArgs
            int r6 = r13 + 1
            int r13 = r13 + r12
            r4 = r4[r13]
            r4.compile((gnu.expr.Compilation) r1, (gnu.bytecode.Type) r7)
            r2.emitFi()
            r19 = r3
            r20 = r5
            r13 = r6
            goto L_0x0177
        L_0x01ae:
            int r6 = r0.max_args
            if (r6 >= 0) goto L_0x01c5
            int r4 = r4 + r8
            if (r11 != r4) goto L_0x01c5
            r2.emitLoad(r3)
            int r4 = r11 - r5
            r2.emitPushInt(r4)
            gnu.bytecode.Method r4 = gnu.expr.Compilation.makeListMethod
            r2.emitInvokeStatic(r4)
            gnu.bytecode.ClassType r15 = gnu.expr.Compilation.scmListType
            goto L_0x0173
        L_0x01c5:
            r2.emitLoad(r3)
            int r4 = r0.min_args
            int r4 = r4 + r8
            int r4 = r4 - r5
            r2.emitPushInt(r4)
            gnu.expr.Keyword[] r4 = r0.keywords
            int r6 = r14 + 1
            r4 = r4[r14]
            r1.compileConstant(r4)
            gnu.expr.Expression[] r4 = r0.defaultArgs
            int r14 = r13 + 1
            int r13 = r13 + r12
            r4 = r4[r13]
            boolean r13 = r4 instanceof gnu.expr.QuoteExp
            r19 = r3
            java.lang.String r3 = "searchForKeyword"
            r20 = r5
            r5 = 1
            if (r13 == 0) goto L_0x0227
            gnu.bytecode.Method r13 = searchForKeywordMethod4
            if (r13 != 0) goto L_0x0218
            r13 = 4
            gnu.bytecode.Type[] r13 = new gnu.bytecode.Type[r13]
            gnu.bytecode.ArrayType r22 = gnu.expr.Compilation.objArrayType
            r18 = 0
            r13[r18] = r22
            gnu.bytecode.PrimType r22 = gnu.bytecode.Type.intType
            r13[r5] = r22
            gnu.bytecode.ClassType r5 = gnu.bytecode.Type.objectType
            r16 = 2
            r13[r16] = r5
            gnu.bytecode.ClassType r5 = gnu.bytecode.Type.objectType
            r21 = 3
            r13[r21] = r5
            gnu.bytecode.ClassType r5 = gnu.expr.Compilation.scmKeywordType
            r22 = r6
            gnu.bytecode.ClassType r6 = gnu.bytecode.Type.objectType
            r23 = r8
            r8 = 9
            gnu.bytecode.Method r3 = r5.addMethod((java.lang.String) r3, (gnu.bytecode.Type[]) r13, (gnu.bytecode.Type) r6, (int) r8)
            searchForKeywordMethod4 = r3
            goto L_0x021c
        L_0x0218:
            r22 = r6
            r23 = r8
        L_0x021c:
            r4.compile((gnu.expr.Compilation) r1, (gnu.bytecode.Type) r7)
            gnu.bytecode.Method r3 = searchForKeywordMethod4
            r2.emitInvokeStatic(r3)
            r16 = 2
            goto L_0x026a
        L_0x0227:
            r22 = r6
            r23 = r8
            gnu.bytecode.Method r6 = searchForKeywordMethod3
            if (r6 != 0) goto L_0x024e
            r6 = 3
            gnu.bytecode.Type[] r6 = new gnu.bytecode.Type[r6]
            gnu.bytecode.ArrayType r8 = gnu.expr.Compilation.objArrayType
            r13 = 0
            r6[r13] = r8
            gnu.bytecode.PrimType r8 = gnu.bytecode.Type.intType
            r6[r5] = r8
            gnu.bytecode.ClassType r8 = gnu.bytecode.Type.objectType
            r16 = 2
            r6[r16] = r8
            gnu.bytecode.ClassType r8 = gnu.expr.Compilation.scmKeywordType
            gnu.bytecode.ClassType r13 = gnu.bytecode.Type.objectType
            r5 = 9
            gnu.bytecode.Method r3 = r8.addMethod((java.lang.String) r3, (gnu.bytecode.Type[]) r6, (gnu.bytecode.Type) r13, (int) r5)
            searchForKeywordMethod3 = r3
            goto L_0x0250
        L_0x024e:
            r16 = 2
        L_0x0250:
            gnu.bytecode.Method r3 = searchForKeywordMethod3
            r2.emitInvokeStatic(r3)
            r3 = 1
            r2.emitDup((int) r3)
            gnu.expr.Special r5 = gnu.expr.Special.dfault
            r1.compileConstant(r5)
            r2.emitIfEq()
            r2.emitPop(r3)
            r4.compile((gnu.expr.Compilation) r1, (gnu.bytecode.Type) r7)
            r2.emitFi()
        L_0x026a:
            r13 = r14
            r14 = r22
        L_0x026d:
            if (r7 == r15) goto L_0x0274
            int r3 = r11 + 1
            gnu.expr.CheckedTarget.emitCheckedCoerce((gnu.expr.Compilation) r1, (gnu.expr.LambdaExp) r0, (int) r3, (gnu.bytecode.Type) r7)
        L_0x0274:
            boolean r3 = r10.isIndirectBinding()
            if (r3 == 0) goto L_0x027d
            r10.pushIndirectBinding(r1)
        L_0x027d:
            boolean r3 = r10.isSimple()
            if (r3 == 0) goto L_0x0296
            gnu.bytecode.Variable r3 = r10.getVariable()
            boolean r4 = r10.isIndirectBinding()
            if (r4 == 0) goto L_0x0292
            gnu.bytecode.ClassType r4 = gnu.expr.Compilation.typeLocation
            r3.setType(r4)
        L_0x0292:
            r2.emitStore(r3)
            goto L_0x029b
        L_0x0296:
            gnu.bytecode.Field r3 = r10.field
            r2.emitPutField(r3)
        L_0x029b:
            int r11 = r11 + 1
            gnu.expr.Declaration r10 = r10.nextDecl()
            r3 = r19
            r5 = r20
            r8 = r23
            r4 = 0
            r7 = 2
            goto L_0x0106
        L_0x02ab:
            r1.callContextVar = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.enterFunction(gnu.expr.Compilation):void");
    }

    /* access modifiers changed from: package-private */
    public void compileAsMethod(Compilation compilation) {
        long[] jArr;
        Method method;
        LambdaExp lambdaExp;
        long[] jArr2;
        QuoteExp quoteExp;
        Compilation compilation2 = compilation;
        if ((this.flags & 128) == 0 && !isAbstract()) {
            this.flags |= 128;
            if (this.primMethods != null) {
                Method method2 = compilation2.method;
                LambdaExp lambdaExp2 = compilation2.curLambda;
                compilation2.curLambda = this;
                int i = 0;
                boolean staticFlag = this.primMethods[0].getStaticFlag();
                int length = this.primMethods.length - 1;
                Type restArgType = restArgType();
                if (length > 0) {
                    jArr = new long[(this.min_args + length)];
                    Declaration firstDecl = firstDecl();
                    for (int i2 = 0; i2 < this.min_args + length; i2++) {
                        jArr[i2] = firstDecl.flags;
                        firstDecl = firstDecl.nextDecl();
                    }
                } else {
                    jArr = null;
                }
                boolean z = getCallConvention() >= 2;
                int i3 = 0;
                while (i3 <= length) {
                    compilation2.method = this.primMethods[i3];
                    if (i3 < length) {
                        CodeAttr startCode = compilation2.method.startCode();
                        int i4 = i3 + 1;
                        while (i4 < length && (this.defaultArgs[i4] instanceof QuoteExp)) {
                            i4++;
                        }
                        boolean z2 = i4 == length && restArgType != null;
                        Variable variable = compilation2.callContextVar;
                        Variable arg = startCode.getArg(i);
                        if (!staticFlag) {
                            startCode.emitPushThis();
                            if (getNeedsClosureEnv()) {
                                this.closureEnv = arg;
                            }
                            arg = startCode.getArg(1);
                        }
                        method = method2;
                        lambdaExp = lambdaExp2;
                        Declaration firstDecl2 = firstDecl();
                        int i5 = 0;
                        while (i5 < this.min_args + i3) {
                            firstDecl2.flags |= 64;
                            firstDecl2.var = arg;
                            startCode.emitLoad(arg);
                            arg = arg.nextVar();
                            i5++;
                            firstDecl2 = firstDecl2.nextDecl();
                            jArr = jArr;
                            variable = variable;
                        }
                        Variable variable2 = variable;
                        jArr2 = jArr;
                        compilation2.callContextVar = z ? arg : null;
                        int i6 = i3;
                        while (i6 < i4) {
                            this.defaultArgs[i6].compile(compilation2, StackTarget.getInstance(firstDecl2.getType()));
                            i6++;
                            firstDecl2 = firstDecl2.nextDecl();
                        }
                        if (z2) {
                            String name = restArgType.getName();
                            if ("gnu.lists.LList".equals(name)) {
                                quoteExp = new QuoteExp(LList.Empty);
                            } else if ("java.lang.Object[]".equals(name)) {
                                quoteExp = new QuoteExp(Values.noArgs);
                            } else {
                                throw new Error("unimplemented #!rest type " + name);
                            }
                            quoteExp.compile(compilation2, restArgType);
                        }
                        if (z) {
                            startCode.emitLoad(arg);
                        }
                        if (staticFlag) {
                            startCode.emitInvokeStatic(this.primMethods[i4]);
                        } else {
                            startCode.emitInvokeVirtual(this.primMethods[i4]);
                        }
                        startCode.emitReturn();
                        this.closureEnv = null;
                        compilation2.callContextVar = variable2;
                    } else {
                        method = method2;
                        lambdaExp = lambdaExp2;
                        jArr2 = jArr;
                        if (jArr2 != null) {
                            Declaration firstDecl3 = firstDecl();
                            for (int i7 = 0; i7 < this.min_args + length; i7++) {
                                firstDecl3.flags = jArr2[i7];
                                firstDecl3.var = null;
                                firstDecl3 = firstDecl3.nextDecl();
                            }
                        }
                        compilation2.method.initCode();
                        allocChildClasses(compilation);
                        allocParameters(compilation);
                        enterFunction(compilation);
                        compileBody(compilation);
                        compileEnd(compilation);
                        generateApplyMethods(compilation);
                    }
                    i3++;
                    jArr = jArr2;
                    lambdaExp2 = lambdaExp;
                    method2 = method;
                    i = 0;
                }
                compilation2.method = method2;
                compilation2.curLambda = lambdaExp2;
            }
        }
    }

    public void compileBody(Compilation compilation) {
        Target target;
        Variable variable = compilation.callContextVar;
        compilation.callContextVar = null;
        if (getCallConvention() >= 2) {
            Variable lookup = getVarScope().lookup("$ctx");
            if (lookup != null && lookup.getType() == Compilation.typeCallContext) {
                compilation.callContextVar = lookup;
            }
            target = ConsumerTarget.makeContextTarget(compilation);
        } else {
            target = Target.pushValue(getReturnType());
        }
        Expression expression = this.body;
        expression.compileWithPosition(compilation, target, expression.getLineNumber() > 0 ? this.body : this);
        compilation.callContextVar = variable;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        LambdaExp lambdaExp;
        Compilation compilation = expVisitor.getCompilation();
        if (compilation == null) {
            lambdaExp = null;
        } else {
            lambdaExp = compilation.curLambda;
            compilation.curLambda = this;
        }
        try {
            return expVisitor.visitLambdaExp(this, d);
        } finally {
            if (compilation != null) {
                compilation.curLambda = lambdaExp;
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        visitChildrenOnly(expVisitor, d);
        visitProperties(expVisitor, d);
    }

    /* access modifiers changed from: protected */
    public final <R, D> void visitChildrenOnly(ExpVisitor<R, D> expVisitor, D d) {
        Expression expression;
        LambdaExp lambdaExp = expVisitor.currentLambda;
        expVisitor.currentLambda = this;
        try {
            this.throwsSpecification = expVisitor.visitExps(this.throwsSpecification, d);
            expVisitor.visitDefaultArgs(this, d);
            if (expVisitor.exitValue == null && (expression = this.body) != null) {
                this.body = expVisitor.update(expression, expVisitor.visit(expression, d));
            }
        } finally {
            expVisitor.currentLambda = lambdaExp;
        }
    }

    /* access modifiers changed from: protected */
    public final <R, D> void visitProperties(ExpVisitor<R, D> expVisitor, D d) {
        Object[] objArr = this.properties;
        if (objArr != null) {
            int length = objArr.length;
            for (int i = 1; i < length; i += 2) {
                Object[] objArr2 = this.properties;
                Object obj = objArr2[i];
                if (obj instanceof Expression) {
                    objArr2[i] = expVisitor.visitAndUpdate((Expression) obj, d);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        Keyword[] keywordArr = this.keywords;
        if (keywordArr != null && keywordArr.length > 0) {
            return true;
        }
        Expression[] expressionArr = this.defaultArgs;
        if (expressionArr == null) {
            return false;
        }
        int length = expressionArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return false;
            }
            Expression expression = this.defaultArgs[length];
            if (expression != null && !(expression instanceof QuoteExp)) {
                return true;
            }
        }
    }

    public void apply(CallContext callContext) throws Throwable {
        setIndexes();
        callContext.writeValue(new Closure(this, callContext));
    }

    /* access modifiers changed from: package-private */
    public Object evalDefaultArg(int i, CallContext callContext) {
        try {
            return this.defaultArgs[i].eval(callContext);
        } catch (Throwable th) {
            throw new WrappedException("error evaluating default argument", th);
        }
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type2, Declaration declaration) {
        Method method;
        Expression[] expressionArr;
        Expression inlineCall;
        Expression[] args = applyExp.getArgs();
        if ((this.flags & 4096) != 0 && (inlineCall = InlineCalls.inlineCall(this, args, true)) != null) {
            return inlineCalls.visit(inlineCall, type2);
        }
        applyExp.visitArgs(inlineCalls);
        int length = applyExp.args.length;
        String checkArgCount = WrongArguments.checkArgCount(getName(), this.min_args, this.max_args, length);
        if (checkArgCount != null) {
            return inlineCalls.noteError(checkArgCount);
        }
        int callConvention = getCallConvention();
        if (!inlineCalls.getCompilation().inlineOk((Expression) this) || !isClassMethod() || ((callConvention > 2 && callConvention != 3) || (method = getMethod(length)) == null)) {
            return applyExp;
        }
        boolean isStatic = this.nameDecl.isStatic();
        if (!isStatic && (this.outer instanceof ClassExp)) {
            ((ClassExp) this.outer).isMakingClassPair();
        }
        PrimProcedure primProcedure = new PrimProcedure(method, this);
        if (isStatic) {
            expressionArr = applyExp.args;
        } else {
            LambdaExp currentLambda = inlineCalls.getCurrentLambda();
            while (currentLambda != null) {
                if (currentLambda.outer == this.outer) {
                    Declaration firstDecl = currentLambda.firstDecl();
                    if (firstDecl == null || !firstDecl.isThisParameter()) {
                        return inlineCalls.noteError("calling non-static method " + getName() + " from static method " + currentLambda.getName());
                    }
                    int argCount = applyExp.getArgCount();
                    Expression[] expressionArr2 = new Expression[(argCount + 1)];
                    System.arraycopy(applyExp.getArgs(), 0, expressionArr2, 1, argCount);
                    expressionArr2[0] = new ThisExp(firstDecl);
                    expressionArr = expressionArr2;
                } else {
                    currentLambda = currentLambda.outerLambda();
                }
            }
            return inlineCalls.noteError("internal error: missing " + this);
        }
        return new ApplyExp((Procedure) primProcedure, expressionArr).setLine((Expression) applyExp);
    }

    public void print(OutPort outPort) {
        Special special;
        Special special2;
        int i;
        Expression expression;
        outPort.startLogicalBlock("(Lambda/", ")", 2);
        Object symbol = getSymbol();
        if (symbol != null) {
            outPort.print(symbol);
            outPort.print('/');
        }
        outPort.print(this.id);
        outPort.print('/');
        outPort.print("fl:");
        outPort.print(Integer.toHexString(this.flags));
        outPort.writeSpaceFill();
        printLineColumn(outPort);
        int i2 = 0;
        outPort.startLogicalBlock("(", false, ")");
        Keyword[] keywordArr = this.keywords;
        int length = keywordArr == null ? 0 : keywordArr.length;
        Expression[] expressionArr = this.defaultArgs;
        int length2 = expressionArr == null ? 0 : expressionArr.length - length;
        Declaration firstDecl = firstDecl();
        if (firstDecl == null || !firstDecl.isThisParameter()) {
            special = null;
        } else {
            special = null;
            i2 = -1;
        }
        int i3 = 0;
        while (firstDecl != null) {
            int i4 = this.min_args;
            if (i2 < i4) {
                special2 = null;
            } else if (i2 < i4 + length2) {
                special2 = Special.optional;
            } else if (this.max_args >= 0 || i2 != i4 + length2) {
                special2 = Special.key;
            } else {
                special2 = Special.rest;
            }
            if (firstDecl != firstDecl()) {
                outPort.writeSpaceFill();
            }
            if (special2 != special) {
                outPort.print((Object) special2);
                outPort.writeSpaceFill();
            }
            if (special2 == Special.optional || special2 == Special.key) {
                i = i3 + 1;
                expression = this.defaultArgs[i3];
            } else {
                i = i3;
                expression = null;
            }
            if (expression != null) {
                outPort.print('(');
            }
            firstDecl.printInfo(outPort);
            if (!(expression == null || expression == QuoteExp.falseExp)) {
                outPort.print(' ');
                expression.print(outPort);
                outPort.print(')');
            }
            i2++;
            firstDecl = firstDecl.nextDecl();
            special = special2;
            i3 = i;
        }
        outPort.endLogicalBlock(")");
        outPort.writeSpaceLinear();
        Expression expression2 = this.body;
        if (expression2 == null) {
            outPort.print("<null body>");
        } else {
            expression2.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }

    /* access modifiers changed from: protected */
    public final String getExpClassName() {
        String name = getClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf >= 0 ? name.substring(lastIndexOf + 1) : name;
    }

    public String toString() {
        Expression expression;
        String str = getExpClassName() + ':' + getSymbol() + '/' + this.id + '/';
        int lineNumber = getLineNumber();
        if (lineNumber <= 0 && (expression = this.body) != null) {
            lineNumber = expression.getLineNumber();
        }
        if (lineNumber <= 0) {
            return str;
        }
        return str + "l:" + lineNumber;
    }

    public Object getProperty(Object obj, Object obj2) {
        Object[] objArr;
        Object[] objArr2 = this.properties;
        if (objArr2 != null) {
            int length = objArr2.length;
            do {
                length -= 2;
                if (length >= 0) {
                    objArr = this.properties;
                }
            } while (objArr[length] != obj);
            return objArr[length + 1];
        }
        return obj2;
    }

    public synchronized void setProperty(Object obj, Object obj2) {
        this.properties = PropertySet.setProperty(this.properties, obj, obj2);
    }

    public final Type getReturnType() {
        if (this.returnType == null) {
            this.returnType = Type.objectType;
            if (this.body != null && !isAbstract()) {
                this.returnType = this.body.getType();
            }
        }
        return this.returnType;
    }

    public final void setReturnType(Type type2) {
        this.returnType = type2;
    }

    public final void setCoercedReturnType(Type type2) {
        this.returnType = type2;
        if (type2 != null && type2 != Type.objectType && type2 != Type.voidType && this.body != QuoteExp.abstractExp) {
            Expression expression = this.body;
            Expression makeCoercion = Compilation.makeCoercion(expression, type2);
            this.body = makeCoercion;
            makeCoercion.setLine(expression);
        }
    }

    public final void setCoercedReturnValue(Expression expression, Language language) {
        if (!isAbstract()) {
            Expression expression2 = this.body;
            ApplyExp makeCoercion = Compilation.makeCoercion(expression2, expression);
            this.body = makeCoercion;
            makeCoercion.setLine(expression2);
        }
        Type typeFor = language.getTypeFor(expression);
        if (typeFor != null) {
            setReturnType(typeFor);
        }
    }
}
