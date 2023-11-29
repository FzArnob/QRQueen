package gnu.expr;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceLocator;

public class Declaration implements SourceLocator {
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CAN_WRITE = 8;
    public static final long CLASS_ACCESS_FLAGS = 25820135424L;
    public static final int EARLY_INIT = 536870912;
    public static final long ENUM_ACCESS = 8589934592L;
    public static final int EXPORT_SPECIFIED = 1024;
    public static final int EXTERNAL_ACCESS = 524288;
    public static final long FIELD_ACCESS_FLAGS = 32463912960L;
    public static final int FIELD_OR_METHOD = 1048576;
    public static final long FINAL_ACCESS = 17179869184L;
    static final int INDIRECT_BINDING = 1;
    public static final int IS_ALIAS = 256;
    public static final int IS_CONSTANT = 16384;
    public static final int IS_DYNAMIC = 268435456;
    static final int IS_FLUID = 16;
    public static final int IS_IMPORTED = 131072;
    public static final int IS_NAMESPACE_PREFIX = 2097152;
    static final int IS_SIMPLE = 64;
    public static final int IS_SINGLE_VALUE = 262144;
    public static final int IS_SYNTAX = 32768;
    public static final int IS_UNKNOWN = 65536;
    public static final long METHOD_ACCESS_FLAGS = 17431527424L;
    public static final int MODULE_REFERENCE = 1073741824;
    public static final int NONSTATIC_SPECIFIED = 4096;
    public static final int NOT_DEFINING = 512;
    public static final int PACKAGE_ACCESS = 134217728;
    static final int PRIVATE = 32;
    public static final int PRIVATE_ACCESS = 16777216;
    public static final String PRIVATE_PREFIX = "$Prvt$";
    public static final int PRIVATE_SPECIFIED = 16777216;
    static final int PROCEDURE = 128;
    public static final int PROTECTED_ACCESS = 33554432;
    public static final int PUBLIC_ACCESS = 67108864;
    public static final int STATIC_SPECIFIED = 2048;
    public static final long TRANSIENT_ACCESS = 4294967296L;
    public static final int TYPE_SPECIFIED = 8192;
    static final String UNKNOWN_PREFIX = "loc$";
    public static final long VOLATILE_ACCESS = 2147483648L;
    static int counter;
    public Declaration base;
    public ScopeExp context;
    int evalIndex;
    public Field field;
    String filename;
    public ApplyExp firstCall;
    protected long flags;
    protected int id;
    Method makeLocationMethod;
    Declaration next;
    Declaration nextCapturedVar;
    int position;
    Object symbol;
    protected Type type;
    protected Expression typeExp;
    protected Expression value;
    Variable var;

    public String getPublicId() {
        return null;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public void setCode(int i) {
        if (i < 0) {
            this.id = i;
            return;
        }
        throw new Error("code must be negative");
    }

    public int getCode() {
        return this.id;
    }

    public final Expression getTypeExp() {
        if (this.typeExp == null) {
            setType(Type.objectType);
        }
        return this.typeExp;
    }

    public final Type getType() {
        if (this.type == null) {
            setType(Type.objectType);
        }
        return this.type;
    }

    public final void setType(Type type2) {
        this.type = type2;
        Variable variable = this.var;
        if (variable != null) {
            variable.setType(type2);
        }
        this.typeExp = QuoteExp.getInstance(type2);
    }

    public final void setTypeExp(Expression expression) {
        Type type2;
        this.typeExp = expression;
        if (expression instanceof TypeValue) {
            type2 = ((TypeValue) expression).getImplementationType();
        } else {
            type2 = Language.getDefaultLanguage().getTypeFor(expression, false);
        }
        if (type2 == null) {
            type2 = Type.pointer_type;
        }
        this.type = type2;
        Variable variable = this.var;
        if (variable != null) {
            variable.setType(type2);
        }
    }

    public final String getName() {
        Object obj = this.symbol;
        if (obj == null) {
            return null;
        }
        return obj instanceof Symbol ? ((Symbol) obj).getName() : obj.toString();
    }

    public final void setName(Object obj) {
        this.symbol = obj;
    }

    public final Object getSymbol() {
        return this.symbol;
    }

    public final void setSymbol(Object obj) {
        this.symbol = obj;
    }

    public final Declaration nextDecl() {
        return this.next;
    }

    public final void setNext(Declaration declaration) {
        this.next = declaration;
    }

    public Variable getVariable() {
        return this.var;
    }

    public final boolean isSimple() {
        return (this.flags & 64) != 0;
    }

    public final void setSimple(boolean z) {
        setFlag(z, 64);
        Variable variable = this.var;
        if (variable != null && !variable.isParameter()) {
            this.var.setSimple(z);
        }
    }

    public final void setSyntax() {
        setSimple(false);
        setFlag(536920064);
    }

    public final ScopeExp getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public void loadOwningObject(Declaration declaration, Compilation compilation) {
        if (declaration == null) {
            declaration = this.base;
        }
        if (declaration != null) {
            declaration.load((AccessExp) null, 0, compilation, Target.pushObject);
        } else {
            getContext().currentLambda().loadHeapFrame(compilation);
        }
    }

    public void load(AccessExp accessExp, int i, Compilation compilation, Target target) {
        Declaration declaration;
        String fileName;
        int lineNumber;
        Object constantValue;
        ClassType classType;
        Method method;
        ReferenceExp referenceExp;
        Declaration declaration2;
        if (!(target instanceof IgnoreTarget)) {
            Object obj = null;
            if (accessExp == null) {
                declaration = null;
            } else {
                declaration = accessExp.contextDecl();
            }
            if (isAlias()) {
                Expression expression = this.value;
                if ((expression instanceof ReferenceExp) && (declaration2 = referenceExp.binding) != null && (((i & 2) == 0 || declaration2.isIndirectBinding()) && (declaration == null || !declaration2.needsContext()))) {
                    declaration2.load((referenceExp = (ReferenceExp) expression), i, compilation, target);
                    return;
                }
            }
            if (!isFluid() || !(this.context instanceof FluidLetExp)) {
                CodeAttr code = compilation.getCode();
                Type type2 = getType();
                int i2 = 3;
                int i3 = 2;
                if (isIndirectBinding() || (i & 2) == 0) {
                    Field field2 = this.field;
                    int i4 = 0;
                    if (field2 != null) {
                        compilation.usedClass(field2.getDeclaringClass());
                        compilation.usedClass(this.field.getType());
                        if (!this.field.getStaticFlag()) {
                            loadOwningObject(declaration, compilation);
                            code.emitGetField(this.field);
                        } else {
                            code.emitGetStatic(this.field);
                        }
                    } else if (isIndirectBinding() && compilation.immediate && getVariable() == null) {
                        Environment current = Environment.getCurrent();
                        Object obj2 = this.symbol;
                        Symbol symbol2 = obj2 instanceof Symbol ? (Symbol) obj2 : current.getSymbol(obj2.toString());
                        if (isProcedureDecl() && compilation.getLanguage().hasSeparateFunctionNamespace()) {
                            obj = EnvironmentKey.FUNCTION;
                        }
                        compilation.compileConstant(current.getLocation(symbol2, obj), Target.pushValue(Compilation.typeLocation));
                    } else if (!compilation.immediate || (constantValue = getConstantValue()) == null) {
                        if (this.value != QuoteExp.undefined_exp && ignorable()) {
                            Expression expression2 = this.value;
                            if (!(expression2 instanceof LambdaExp) || !(((LambdaExp) expression2).outer instanceof ModuleExp)) {
                                this.value.compile(compilation, target);
                                return;
                            }
                        }
                        Variable variable = getVariable();
                        if ((this.context instanceof ClassExp) && variable == null && !getFlag(128)) {
                            ClassExp classExp = (ClassExp) this.context;
                            if (classExp.isMakingClassPair()) {
                                Method declaredMethod = classExp.type.getDeclaredMethod(ClassExp.slotToMethodName("get", getName()), 0);
                                classExp.loadHeapFrame(compilation);
                                code.emitInvoke(declaredMethod);
                            }
                        }
                        if (variable == null) {
                            variable = allocateVariable(code);
                        }
                        code.emitLoad(variable);
                    } else {
                        compilation.compileConstant(constantValue, target);
                        return;
                    }
                    if (isIndirectBinding() && (i & 2) == 0) {
                        if (accessExp == null || (fileName = accessExp.getFileName()) == null || (lineNumber = accessExp.getLineNumber()) <= 0) {
                            code.emitInvokeVirtual(Compilation.getLocationMethod);
                        } else {
                            ClassType make = ClassType.make("gnu.mapping.UnboundLocationException");
                            boolean isInTry = code.isInTry();
                            int columnNumber = accessExp.getColumnNumber();
                            Label label = new Label(code);
                            label.define(code);
                            code.emitInvokeVirtual(Compilation.getLocationMethod);
                            Label label2 = new Label(code);
                            label2.define(code);
                            Label label3 = new Label(code);
                            label3.setTypes(code);
                            if (isInTry) {
                                code.emitGoto(label3);
                            } else {
                                code.setUnreachable();
                            }
                            if (!isInTry) {
                                i4 = code.beginFragment(label3);
                            }
                            code.addHandler(label, label2, make);
                            code.emitDup((Type) make);
                            code.emitPushString(fileName);
                            code.emitPushInt(lineNumber);
                            code.emitPushInt(columnNumber);
                            code.emitInvokeVirtual(make.getDeclaredMethod("setLine", 3));
                            code.emitThrow();
                            if (isInTry) {
                                label3.define(code);
                            } else {
                                code.endFragment(i4);
                            }
                        }
                        type2 = Type.pointer_type;
                    }
                } else if (this.field != null) {
                    boolean z = compilation.immediate;
                    if (this.field.getStaticFlag()) {
                        classType = ClassType.make("gnu.kawa.reflect.StaticFieldLocation");
                        if (z) {
                            i3 = 1;
                        }
                        method = classType.getDeclaredMethod("make", i3);
                    } else {
                        classType = ClassType.make("gnu.kawa.reflect.FieldLocation");
                        if (z) {
                            i2 = 2;
                        }
                        method = classType.getDeclaredMethod("make", i2);
                        loadOwningObject(declaration, compilation);
                    }
                    type2 = classType;
                    if (z) {
                        compilation.compileConstant(this);
                    } else {
                        compilation.compileConstant(this.field.getDeclaringClass().getName());
                        compilation.compileConstant(this.field.getName());
                    }
                    code.emitInvokeStatic(method);
                } else {
                    throw new Error("internal error: cannot take location of " + this);
                }
                target.compileFromStack(compilation, type2);
                return;
            }
            this.base.load(accessExp, i, compilation, target);
        }
    }

    public void compileStore(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (isSimple()) {
            code.emitStore(getVariable());
        } else if (!this.field.getStaticFlag()) {
            loadOwningObject((Declaration) null, compilation);
            code.emitSwap();
            code.emitPutField(this.field);
        } else {
            code.emitPutStatic(this.field);
        }
    }

    public final Expression getValue() {
        if (this.value == QuoteExp.undefined_exp) {
            Field field2 = this.field;
            if (field2 != null && (field2.getModifiers() & 24) == 24 && !isIndirectBinding()) {
                try {
                    this.value = new QuoteExp(this.field.getReflectField().get((Object) null));
                } catch (Throwable unused) {
                }
            }
        } else if ((this.value instanceof QuoteExp) && getFlag(8192) && this.value.getType() != this.type) {
            Object value2 = ((QuoteExp) this.value).getValue();
            Type type2 = getType();
            this.value = new QuoteExp(type2.coerceFromObject(value2), type2);
        }
        return this.value;
    }

    public final void setValue(Expression expression) {
        this.value = expression;
    }

    public final Object getConstantValue() {
        Expression value2 = getValue();
        if (!(value2 instanceof QuoteExp) || value2 == QuoteExp.undefined_exp) {
            return null;
        }
        return ((QuoteExp) value2).getValue();
    }

    public final boolean hasConstantValue() {
        Expression value2 = getValue();
        return (value2 instanceof QuoteExp) && value2 != QuoteExp.undefined_exp;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldEarlyInit() {
        return getFlag(536870912) || isCompiletimeConstant();
    }

    public boolean isCompiletimeConstant() {
        return getFlag(16384) && hasConstantValue();
    }

    public final boolean needsExternalAccess() {
        long j = this.flags;
        return (j & 524320) == 524320 || (j & 2097184) == 2097184;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.field;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean needsContext() {
        /*
            r1 = this;
            gnu.expr.Declaration r0 = r1.base
            if (r0 != 0) goto L_0x0010
            gnu.bytecode.Field r0 = r1.field
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.getStaticFlag()
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.needsContext():boolean");
    }

    public final boolean getFlag(long j) {
        return (j & this.flags) != 0;
    }

    public final void setFlag(boolean z, long j) {
        if (z) {
            this.flags |= j;
            return;
        }
        this.flags = (~j) & this.flags;
    }

    public final void setFlag(long j) {
        this.flags = j | this.flags;
    }

    public final boolean isPublic() {
        return (this.context instanceof ModuleExp) && (this.flags & 32) == 0;
    }

    public final boolean isPrivate() {
        return (this.flags & 32) != 0;
    }

    public final void setPrivate(boolean z) {
        setFlag(z, 32);
    }

    public short getAccessFlags(short s) {
        if (getFlag(251658240)) {
            s = 0;
            if (getFlag(16777216)) {
                s = (short) 2;
            }
            if (getFlag(33554432)) {
                s = (short) (s | 4);
            }
            if (getFlag(67108864)) {
                s = (short) (s | 1);
            }
        }
        if (getFlag(VOLATILE_ACCESS)) {
            s = (short) (s | 64);
        }
        if (getFlag(TRANSIENT_ACCESS)) {
            s = (short) (s | 128);
        }
        if (getFlag(ENUM_ACCESS)) {
            s = (short) (s | Access.ENUM);
        }
        return getFlag(FINAL_ACCESS) ? (short) (s | 16) : s;
    }

    public final boolean isAlias() {
        return (this.flags & 256) != 0;
    }

    public final void setAlias(boolean z) {
        setFlag(z, 256);
    }

    public final boolean isFluid() {
        return (this.flags & 16) != 0;
    }

    public final void setFluid(boolean z) {
        setFlag(z, 16);
    }

    public final boolean isProcedureDecl() {
        return (this.flags & 128) != 0;
    }

    public final void setProcedureDecl(boolean z) {
        setFlag(z, 128);
    }

    public final boolean isNamespaceDecl() {
        return (this.flags & 2097152) != 0;
    }

    public final boolean isIndirectBinding() {
        return (this.flags & 1) != 0;
    }

    public final void setIndirectBinding(boolean z) {
        setFlag(z, 1);
    }

    public void maybeIndirectBinding(Compilation compilation) {
        if ((isLexical() && !(this.context instanceof ModuleExp)) || this.context == compilation.mainLambda) {
            setIndirectBinding(true);
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean z) {
        setFlag(z, 2);
    }

    public final void setCanRead() {
        setFlag(true, 2);
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean z) {
        setFlag(z, 4);
    }

    public final void setCanCall() {
        setFlag(true, 4);
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean getCanWrite() {
        return (this.flags & 8) != 0;
    }

    public final void setCanWrite(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void setCanWrite() {
        this.flags |= 8;
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean isThisParameter() {
        return this.symbol == ThisExp.THIS_NAME;
    }

    public boolean ignorable() {
        if (getCanRead() || isPublic()) {
            return false;
        }
        if (getCanWrite() && getFlag(65536)) {
            return false;
        }
        if (!getCanCall()) {
            return true;
        }
        Expression value2 = getValue();
        if (value2 == null || !(value2 instanceof LambdaExp)) {
            return false;
        }
        LambdaExp lambdaExp = (LambdaExp) value2;
        if (!lambdaExp.isHandlingTailCalls() || lambdaExp.getInlineOnly()) {
            return true;
        }
        return false;
    }

    public boolean needsInit() {
        return !ignorable() && (this.value != QuoteExp.nullExp || this.base == null);
    }

    public boolean isStatic() {
        Field field2 = this.field;
        if (field2 != null) {
            return field2.getStaticFlag();
        }
        if (getFlag(2048) || isCompiletimeConstant()) {
            return true;
        }
        if (getFlag(4096)) {
            return false;
        }
        LambdaExp currentLambda = this.context.currentLambda();
        if (!(currentLambda instanceof ModuleExp) || !((ModuleExp) currentLambda).isStatic()) {
            return false;
        }
        return true;
    }

    public final boolean isLexical() {
        return (this.flags & 268501008) == 0;
    }

    public static final boolean isUnknown(Declaration declaration) {
        return declaration == null || declaration.getFlag(65536);
    }

    public void noteValue(Expression expression) {
        if (this.value == QuoteExp.undefined_exp) {
            if (expression instanceof LambdaExp) {
                ((LambdaExp) expression).nameDecl = this;
            }
            this.value = expression;
            return;
        }
        Expression expression2 = this.value;
        if (expression2 != expression) {
            if (expression2 instanceof LambdaExp) {
                ((LambdaExp) expression2).nameDecl = null;
            }
            this.value = null;
        }
    }

    protected Declaration() {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
    }

    public Declaration(Variable variable) {
        this((Object) variable.getName(), variable.getType());
        this.var = variable;
    }

    public Declaration(Object obj) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
        setName(obj);
    }

    public Declaration(Object obj, Type type2) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64;
        this.makeLocationMethod = null;
        setName(obj);
        setType(type2);
    }

    public Declaration(Object obj, Field field2) {
        this(obj, field2.getType());
        this.field = field2;
        setSimple(false);
    }

    public void pushIndirectBinding(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        code.emitPushString(getName());
        if (this.makeLocationMethod == null) {
            this.makeLocationMethod = Compilation.typeLocation.addMethod("make", new Type[]{Type.pointer_type, Type.string_type}, (Type) Compilation.typeLocation, 9);
        }
        code.emitInvokeStatic(this.makeLocationMethod);
    }

    public final Variable allocateVariable(CodeAttr codeAttr) {
        if (!isSimple() || this.var == null) {
            Variable variable = null;
            String mangleNameIfNeeded = this.symbol != null ? Compilation.mangleNameIfNeeded(getName()) : null;
            if (!isAlias() || !(getValue() instanceof ReferenceExp)) {
                this.var = this.context.getVarScope().addVariable(codeAttr, isIndirectBinding() ? Compilation.typeLocation : getType().getImplementationType(), mangleNameIfNeeded);
            } else {
                Declaration followAliases = followAliases(this);
                if (followAliases != null) {
                    variable = followAliases.var;
                }
                this.var = variable;
            }
        }
        return this.var;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        this.filename = sourceLocator.getFileName();
        setLine(sourceLocator.getLineNumber(), sourceLocator.getColumnNumber());
    }

    public final void setFile(String str) {
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.position = (i << 12) + i2;
    }

    public final void setLine(int i) {
        setLine(i, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int i = this.position >> 12;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public final int getColumnNumber() {
        int i = this.position & 4095;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public void printInfo(OutPort outPort) {
        StringBuffer stringBuffer = new StringBuffer();
        printInfo(stringBuffer);
        outPort.print(stringBuffer.toString());
    }

    public void printInfo(StringBuffer stringBuffer) {
        stringBuffer.append(this.symbol);
        stringBuffer.append('/');
        stringBuffer.append(this.id);
        stringBuffer.append("/fl:");
        stringBuffer.append(Long.toHexString(this.flags));
        if (ignorable()) {
            stringBuffer.append("(ignorable)");
        }
        Expression expression = this.typeExp;
        Type type2 = getType();
        if (expression != null && !(expression instanceof QuoteExp)) {
            stringBuffer.append("::");
            stringBuffer.append(expression);
        } else if (!(this.type == null || type2 == Type.pointer_type)) {
            stringBuffer.append("::");
            stringBuffer.append(type2.getName());
        }
        if (this.base != null) {
            stringBuffer.append("(base:#");
            stringBuffer.append(this.base.id);
            stringBuffer.append(')');
        }
    }

    public String toString() {
        return "Declaration[" + this.symbol + '/' + this.id + ']';
    }

    public static Declaration followAliases(Declaration declaration) {
        Declaration declaration2;
        while (declaration != null && declaration.isAlias()) {
            Expression value2 = declaration.getValue();
            if (!(value2 instanceof ReferenceExp) || (declaration2 = ((ReferenceExp) value2).binding) == null) {
                break;
            }
            declaration = declaration2;
        }
        return declaration;
    }

    public void makeField(Compilation compilation, Expression expression) {
        setSimple(false);
        makeField(compilation.mainClass, compilation, expression);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0077, code lost:
        if (((gnu.expr.ModuleExp) r1).staticInitRun() != false) goto L_0x0079;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void makeField(gnu.bytecode.ClassType r9, gnu.expr.Compilation r10, gnu.expr.Expression r11) {
        /*
            r8 = this;
            boolean r0 = r8.needsExternalAccess()
            r1 = 16384(0x4000, double:8.0948E-320)
            boolean r1 = r8.getFlag(r1)
            r2 = 8192(0x2000, double:4.0474E-320)
            boolean r2 = r8.getFlag(r2)
            boolean r3 = r10.immediate
            r4 = 1
            if (r3 == 0) goto L_0x0022
            gnu.expr.ScopeExp r3 = r8.context
            boolean r3 = r3 instanceof gnu.expr.ModuleExp
            if (r3 == 0) goto L_0x0022
            if (r1 != 0) goto L_0x0022
            if (r2 != 0) goto L_0x0022
            r8.setIndirectBinding(r4)
        L_0x0022:
            boolean r2 = r8.isPublic()
            r3 = 0
            if (r2 != 0) goto L_0x0032
            if (r0 != 0) goto L_0x0032
            boolean r2 = r10.immediate
            if (r2 == 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r2 = 0
            goto L_0x0033
        L_0x0032:
            r2 = 1
        L_0x0033:
            boolean r5 = r8.isStatic()
            if (r5 != 0) goto L_0x005b
            r5 = 268501008(0x10010010, double:1.32657124E-315)
            boolean r5 = r8.getFlag(r5)
            if (r5 == 0) goto L_0x004e
            boolean r5 = r8.isIndirectBinding()
            if (r5 == 0) goto L_0x004e
            boolean r5 = r8.isAlias()
            if (r5 == 0) goto L_0x005b
        L_0x004e:
            boolean r5 = r11 instanceof gnu.expr.ClassExp
            if (r5 == 0) goto L_0x005d
            r5 = r11
            gnu.expr.LambdaExp r5 = (gnu.expr.LambdaExp) r5
            boolean r5 = r5.getNeedsClosureEnv()
            if (r5 != 0) goto L_0x005d
        L_0x005b:
            r2 = r2 | 8
        L_0x005d:
            boolean r5 = r8.isIndirectBinding()
            if (r5 != 0) goto L_0x0079
            if (r1 == 0) goto L_0x0085
            boolean r1 = r8.shouldEarlyInit()
            if (r1 != 0) goto L_0x0079
            gnu.expr.ScopeExp r1 = r8.context
            boolean r5 = r1 instanceof gnu.expr.ModuleExp
            if (r5 == 0) goto L_0x0085
            gnu.expr.ModuleExp r1 = (gnu.expr.ModuleExp) r1
            boolean r1 = r1.staticInitRun()
            if (r1 == 0) goto L_0x0085
        L_0x0079:
            gnu.expr.ScopeExp r1 = r8.context
            boolean r5 = r1 instanceof gnu.expr.ClassExp
            if (r5 != 0) goto L_0x0083
            boolean r1 = r1 instanceof gnu.expr.ModuleExp
            if (r1 == 0) goto L_0x0085
        L_0x0083:
            r2 = r2 | 16
        L_0x0085:
            gnu.bytecode.Type r1 = r8.getType()
            gnu.bytecode.Type r1 = r1.getImplementationType()
            boolean r5 = r8.isIndirectBinding()
            if (r5 == 0) goto L_0x009d
            gnu.bytecode.ClassType r5 = gnu.expr.Compilation.typeLocation
            boolean r5 = r1.isSubtype(r5)
            if (r5 != 0) goto L_0x009d
            gnu.bytecode.ClassType r1 = gnu.expr.Compilation.typeLocation
        L_0x009d:
            boolean r5 = r8.ignorable()
            if (r5 != 0) goto L_0x016f
            java.lang.String r5 = r8.getName()
            if (r5 != 0) goto L_0x00ae
            r0 = 8
            java.lang.String r5 = "$unnamed$0"
            goto L_0x00ed
        L_0x00ae:
            java.lang.String r5 = gnu.expr.Compilation.mangleNameIfNeeded(r5)
            r6 = 65536(0x10000, double:3.2379E-319)
            boolean r6 = r8.getFlag(r6)
            if (r6 == 0) goto L_0x00cc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "loc$"
            r6.append(r7)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
        L_0x00cc:
            if (r0 == 0) goto L_0x00e9
            r6 = 1073741824(0x40000000, double:5.304989477E-315)
            boolean r0 = r8.getFlag(r6)
            if (r0 != 0) goto L_0x00e9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "$Prvt$"
            r0.append(r6)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r5 = r0
        L_0x00e9:
            int r0 = r5.length()
        L_0x00ed:
            r6 = 0
        L_0x00ee:
            gnu.bytecode.Field r7 = r9.getDeclaredField(r5)
            if (r7 == 0) goto L_0x010e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r5 = r5.substring(r3, r0)
            r7.append(r5)
            r5 = 36
            r7.append(r5)
            int r6 = r6 + r4
            r7.append(r6)
            java.lang.String r5 = r7.toString()
            goto L_0x00ee
        L_0x010e:
            gnu.bytecode.Field r0 = r9.addField(r5, r1, r2)
            r8.field = r0
            boolean r0 = r11 instanceof gnu.expr.QuoteExp
            if (r0 == 0) goto L_0x016f
            r0 = r11
            gnu.expr.QuoteExp r0 = (gnu.expr.QuoteExp) r0
            java.lang.Object r0 = r0.getValue()
            gnu.bytecode.Field r2 = r8.field
            boolean r2 = r2.getStaticFlag()
            if (r2 == 0) goto L_0x014b
            java.lang.Class r2 = r0.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.String r3 = r1.getName()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x014b
            gnu.expr.LitTable r9 = r10.litTable
            gnu.expr.Literal r9 = r9.findLiteral(r0)
            gnu.bytecode.Field r0 = r9.field
            if (r0 != 0) goto L_0x016f
            gnu.bytecode.Field r0 = r8.field
            gnu.expr.LitTable r1 = r10.litTable
            r9.assign((gnu.bytecode.Field) r0, (gnu.expr.LitTable) r1)
            goto L_0x016f
        L_0x014b:
            boolean r2 = r1 instanceof gnu.bytecode.PrimType
            if (r2 != 0) goto L_0x015b
            java.lang.String r1 = r1.getName()
            java.lang.String r2 = "java.lang.String"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x016f
        L_0x015b:
            boolean r10 = r0 instanceof gnu.text.Char
            if (r10 == 0) goto L_0x0169
            gnu.text.Char r0 = (gnu.text.Char) r0
            int r10 = r0.intValue()
            gnu.math.IntNum r0 = gnu.math.IntNum.make((int) r10)
        L_0x0169:
            gnu.bytecode.Field r10 = r8.field
            r10.setConstantValue(r0, r9)
            return
        L_0x016f:
            boolean r9 = r8.shouldEarlyInit()
            if (r9 != 0) goto L_0x0184
            boolean r9 = r8.isIndirectBinding()
            if (r9 != 0) goto L_0x0181
            if (r11 == 0) goto L_0x0184
            boolean r9 = r11 instanceof gnu.expr.ClassExp
            if (r9 != 0) goto L_0x0184
        L_0x0181:
            gnu.expr.BindingInitializer.create(r8, r11, r10)
        L_0x0184:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.makeField(gnu.bytecode.ClassType, gnu.expr.Compilation, gnu.expr.Expression):void");
    }

    /* access modifiers changed from: package-private */
    public Location makeIndirectLocationFor() {
        Object obj = this.symbol;
        return Location.make(obj instanceof Symbol ? (Symbol) obj : Namespace.EmptyNamespace.getSymbol(this.symbol.toString().intern()));
    }

    public static Declaration getDeclarationFromStatic(String str, String str2) {
        Declaration declaration = new Declaration((Object) str2, ClassType.make(str).getDeclaredField(str2));
        declaration.setFlag(18432);
        return declaration;
    }

    public static Declaration getDeclarationValueFromStatic(String str, String str2, String str3) {
        try {
            Object obj = Class.forName(str).getDeclaredField(str2).get((Object) null);
            Declaration declaration = new Declaration((Object) str3, ClassType.make(str).getDeclaredField(str2));
            declaration.noteValue(new QuoteExp(obj));
            declaration.setFlag(18432);
            return declaration;
        } catch (Exception e) {
            throw new WrappedException((Throwable) e);
        }
    }

    public static Declaration getDeclaration(Named named) {
        return getDeclaration(named, named.getName());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r1 = gnu.expr.PrimProcedure.getProcedureClass(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Declaration getDeclaration(java.lang.Object r4, java.lang.String r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x0018
            java.lang.Class r1 = gnu.expr.PrimProcedure.getProcedureClass(r4)
            if (r1 == 0) goto L_0x0018
            gnu.bytecode.Type r1 = gnu.bytecode.Type.make(r1)
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            java.lang.String r2 = gnu.expr.Compilation.mangleNameIfNeeded(r5)
            gnu.bytecode.Field r1 = r1.getDeclaredField(r2)
            goto L_0x0019
        L_0x0018:
            r1 = r0
        L_0x0019:
            if (r1 == 0) goto L_0x0039
            int r2 = r1.getModifiers()
            r3 = r2 & 8
            if (r3 == 0) goto L_0x0039
            gnu.expr.Declaration r0 = new gnu.expr.Declaration
            r0.<init>((java.lang.Object) r5, (gnu.bytecode.Field) r1)
            gnu.expr.QuoteExp r5 = new gnu.expr.QuoteExp
            r5.<init>(r4)
            r0.noteValue(r5)
            r4 = r2 & 16
            if (r4 == 0) goto L_0x0039
            r4 = 16384(0x4000, double:8.0948E-320)
            r0.setFlag(r4)
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.getDeclaration(java.lang.Object, java.lang.String):gnu.expr.Declaration");
    }
}
