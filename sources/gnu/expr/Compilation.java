package gnu.expr;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LispReader;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.Path;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import kawa.Shell;

public class Compilation implements SourceLocator {
    public static final int BODY_PARSED = 4;
    public static final int CALL_WITH_CONSUMER = 2;
    public static final int CALL_WITH_CONTINUATIONS = 4;
    public static final int CALL_WITH_RETURN = 1;
    public static final int CALL_WITH_TAILCALLS = 3;
    public static final int CALL_WITH_UNSPECIFIED = 0;
    public static final int CLASS_WRITTEN = 14;
    public static final int COMPILED = 12;
    public static final int COMPILE_SETUP = 10;
    public static final int ERROR_SEEN = 100;
    public static final int MODULE_NONSTATIC = -1;
    public static final int MODULE_STATIC = 1;
    public static final int MODULE_STATIC_DEFAULT = 0;
    public static final int MODULE_STATIC_RUN = 2;
    public static final int PROLOG_PARSED = 2;
    public static final int PROLOG_PARSING = 1;
    public static final int RESOLVED = 6;
    public static final int WALKED = 8;
    public static Type[] apply0args = null;
    public static Method apply0method = null;
    public static Type[] apply1args = null;
    public static Method apply1method = typeProcedure.addMethod("apply1", apply1args, (Type) typeObject, 1);
    public static Type[] apply2args = null;
    public static Method apply2method = typeProcedure.addMethod("apply2", apply2args, (Type) typeObject, 1);
    public static Method apply3method = null;
    public static Method apply4method = null;
    private static Type[] applyCpsArgs = null;
    public static Method applyCpsMethod = null;
    public static Type[] applyNargs = null;
    public static Method applyNmethod = typeProcedure.addMethod("applyN", applyNargs, (Type) typeObject, 1);
    public static Method[] applymethods = {apply0method, apply1method, apply2method, apply3method, apply4method, applyNmethod};
    public static Field argsCallContextField = typeCallContext.getDeclaredField("values");
    private static Compilation chainUninitialized = null;
    static Method checkArgCountMethod = typeProcedure.addMethod("checkArgCount", new Type[]{typeProcedure, Type.intType}, (Type) Type.voidType, 9);
    public static String classPrefixDefault = "";
    private static final ThreadLocal<Compilation> current = new InheritableThreadLocal();
    public static boolean debugPrintExpr = false;
    public static boolean debugPrintFinalExpr;
    public static int defaultCallConvention;
    public static int defaultClassFileVersion = ClassType.JDK_1_5_VERSION;
    public static boolean emitSourceDebugExtAttr = true;
    public static final Field falseConstant = scmBooleanType.getDeclaredField("FALSE");
    public static boolean generateMainDefault = false;
    public static Method getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
    public static Method getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, (Type) typeEnvironment, 9);
    public static final Method getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
    public static final Method getLocation2EnvironmentMethod = typeEnvironment.addMethod("getLocation", new Type[]{typeSymbol, Type.objectType}, (Type) typeLocation, 17);
    public static final Method getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, (Type) Type.objectType, 1);
    public static final Method getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, (Type) typeProcedure, 1);
    public static final Method getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
    public static final Method getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
    public static boolean inlineOk = true;
    public static final Type[] int1Args = {Type.intType};
    public static ClassType javaStringType;
    static Method makeListMethod;
    public static int moduleStatic = 0;
    public static Field noArgsField;
    public static final ArrayType objArrayType;
    public static Options options;
    public static Field pcCallContextField = typeCallContext.getDeclaredField("pc");
    public static Field procCallContextField = typeCallContext.getDeclaredField("proc");
    public static ClassType scmBooleanType = ClassType.make("java.lang.Boolean");
    public static ClassType scmKeywordType = ClassType.make("gnu.expr.Keyword");
    public static ClassType scmListType = ClassType.make("gnu.lists.LList");
    public static ClassType scmSequenceType = ClassType.make("gnu.lists.Sequence");
    static final Method setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
    public static final Type[] string1Arg;
    public static final Type[] sym1Arg;
    public static final Field trueConstant = scmBooleanType.getDeclaredField("TRUE");
    public static ClassType typeApplet = ClassType.make("java.applet.Applet");
    public static ClassType typeCallContext = ClassType.make("gnu.mapping.CallContext");
    public static ClassType typeClass = Type.javalangClassType;
    public static ClassType typeClassType = ClassType.make("gnu.bytecode.ClassType");
    public static final ClassType typeConsumer = ClassType.make("gnu.lists.Consumer");
    public static ClassType typeEnvironment = ClassType.make("gnu.mapping.Environment");
    public static ClassType typeLanguage = ClassType.make("gnu.expr.Language");
    public static ClassType typeLocation = ClassType.make("gnu.mapping.Location");
    public static ClassType typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
    public static ClassType typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
    public static ClassType typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
    public static ClassType typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
    public static ClassType typeObject = Type.objectType;
    public static ClassType typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
    public static ClassType typePair = ClassType.make("gnu.lists.Pair");
    public static ClassType typeProcedure = ClassType.make("gnu.mapping.Procedure");
    public static ClassType typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
    public static ClassType typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
    public static ClassType typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
    public static ClassType typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
    public static ClassType typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
    public static ClassType[] typeProcedureArray = {typeProcedure0, typeProcedure1, typeProcedure2, typeProcedure3, typeProcedure4};
    public static ClassType typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
    public static ClassType typeRunnable = ClassType.make("java.lang.Runnable");
    public static ClassType typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
    public static ClassType typeString;
    public static ClassType typeSymbol = ClassType.make("gnu.mapping.Symbol");
    public static ClassType typeType = ClassType.make("gnu.bytecode.Type");
    public static ClassType typeValues;
    public static Options.OptionInfo warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
    public static Options.OptionInfo warnInvokeUnknownMethod;
    public static Options.OptionInfo warnUndefinedVariable;
    public static Options.OptionInfo warnUnknownMember;
    Variable callContextVar;
    Variable callContextVarForInit;
    public String classPrefix = classPrefixDefault;
    ClassType[] classes;
    Initializer clinitChain;
    Method clinitMethod;
    public ClassType curClass;
    public LambdaExp curLambda;
    public Options currentOptions = new Options(options);
    protected ScopeExp current_scope;
    public boolean explicit;
    public Stack<Expression> exprStack;
    Method forNameHelper;
    SwitchState fswitch;
    Field fswitchIndex;
    public boolean generateMain = generateMainDefault;
    public boolean immediate;
    private int keyUninitialized;
    int langOptions;
    protected Language language;
    public Lexer lexer;
    public NameLookup lexical;
    LitTable litTable;
    ArrayClassLoader loader;
    int localFieldIndex;
    public ClassType mainClass;
    public ModuleExp mainLambda;
    int maxSelectorValue;
    protected SourceMessages messages;
    public Method method;
    int method_counter;
    public ModuleInfo minfo;
    public ClassType moduleClass;
    Field moduleInstanceMainField;
    Variable moduleInstanceVar;
    public boolean mustCompile = ModuleExp.alwaysCompile;
    private Compilation nextUninitialized;
    int numClasses;
    boolean pedantic;
    public Stack<Object> pendingImports;
    private int state;
    public Variable thisDecl;

    public static char demangle2(char c, char c2) {
        switch ((c << 16) | c2) {
            case 'm':
                return '&';
            case 't':
                return '@';
            case 'l':
                return ':';
            case 'm':
                return ',';
            case 'q':
                return '\"';
            case 't':
                return '.';
            case 'q':
                return '=';
            case 'x':
                return '!';
            case 'r':
                return '>';
            case 'B':
                return '[';
            case 'C':
                return '{';
            case 'P':
                return '(';
            case 's':
                return '<';
            case 'c':
                return '%';
            case 'n':
                return '-';
            case 'm':
                return '#';
            case 'c':
                return '%';
            case 'l':
                return '+';
            case 'u':
                return '?';
            case 'B':
                return ']';
            case 'C':
                return '}';
            case 'P':
                return ')';
            case 'C':
                return ';';
            case 'l':
                return '/';
            case 'q':
                return '\\';
            case 't':
                return '*';
            case 'l':
                return '~';
            case 'p':
                return '^';
            case 'B':
                return '|';
            default:
                return LispReader.TOKEN_ESCAPE_CHAR;
        }
    }

    public void freeLocalField(Field field) {
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public boolean isPedantic() {
        return this.pedantic;
    }

    public void pushPendingImport(ModuleInfo moduleInfo, ScopeExp scopeExp, int i) {
        if (this.pendingImports == null) {
            this.pendingImports = new Stack<>();
        }
        this.pendingImports.push(moduleInfo);
        this.pendingImports.push(scopeExp);
        Object obj = null;
        ReferenceExp referenceExp = new ReferenceExp((Object) null);
        referenceExp.setLine(this);
        this.pendingImports.push(referenceExp);
        this.pendingImports.push(Integer.valueOf(i));
    }

    static {
        Options options2 = new Options();
        options = options2;
        warnUndefinedVariable = options2.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
        Options.OptionInfo add = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
        warnUnknownMember = add;
        warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, add, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
        ClassType make = ClassType.make("java.lang.String");
        typeString = make;
        javaStringType = make;
        ArrayType make2 = ArrayType.make((Type) typeObject);
        objArrayType = make2;
        Type[] typeArr = {javaStringType};
        string1Arg = typeArr;
        sym1Arg = typeArr;
        Type[] typeArr2 = {make2, Type.intType};
        ClassType classType = scmListType;
        makeListMethod = classType.addMethod("makeList", typeArr2, (Type) classType, 9);
        Type[] typeArr3 = Type.typeArray0;
        apply0args = typeArr3;
        ClassType classType2 = typeObject;
        apply1args = new Type[]{classType2};
        apply2args = new Type[]{classType2, classType2};
        applyNargs = new Type[]{make2};
        apply0method = typeProcedure.addMethod("apply0", typeArr3, (Type) classType2, 17);
        ClassType classType3 = typeObject;
        apply3method = typeProcedure.addMethod("apply3", new Type[]{classType3, classType3, classType3}, (Type) classType3, 1);
        ClassType classType4 = typeObject;
        apply4method = typeProcedure.addMethod("apply4", new Type[]{classType4, classType4, classType4, classType4}, (Type) classType4, 1);
        ClassType make3 = ClassType.make("gnu.mapping.Values");
        typeValues = make3;
        noArgsField = make3.getDeclaredField("noArgs");
        Type[] typeArr4 = {typeCallContext};
        applyCpsArgs = typeArr4;
        applyCpsMethod = typeProcedure.addMethod("apply", typeArr4, (Type) Type.voidType, 1);
    }

    public boolean warnUndefinedVariable() {
        return this.currentOptions.getBoolean(warnUndefinedVariable);
    }

    public boolean warnUnknownMember() {
        return this.currentOptions.getBoolean(warnUnknownMember);
    }

    public boolean warnInvokeUnknownMethod() {
        return this.currentOptions.getBoolean(warnInvokeUnknownMethod);
    }

    public boolean warnAsError() {
        return this.currentOptions.getBoolean(warnAsError);
    }

    public final boolean getBooleanOption(String str, boolean z) {
        return this.currentOptions.getBoolean(str, z);
    }

    public final boolean getBooleanOption(String str) {
        return this.currentOptions.getBoolean(str);
    }

    public boolean usingCPStyle() {
        return defaultCallConvention == 4;
    }

    public boolean usingTailCalls() {
        return defaultCallConvention >= 3;
    }

    public final CodeAttr getCode() {
        return this.method.getCode();
    }

    public boolean generatingApplet() {
        return (this.langOptions & 16) != 0;
    }

    public boolean generatingServlet() {
        return (this.langOptions & 32) != 0;
    }

    public boolean sharedModuleDefs() {
        return (this.langOptions & 2) != 0;
    }

    public void setSharedModuleDefs(boolean z) {
        if (z) {
            this.langOptions |= 2;
        } else {
            this.langOptions &= -3;
        }
    }

    public final ClassType getModuleType() {
        return defaultCallConvention >= 2 ? typeModuleWithContext : typeModuleBody;
    }

    public void compileConstant(Object obj) {
        CodeAttr code = getCode();
        if (obj == null) {
            code.emitPushNull();
        } else if (!(obj instanceof String) || this.immediate) {
            code.emitGetStatic(compileConstantToField(obj));
        } else {
            code.emitPushString((String) obj);
        }
    }

    public Field compileConstantToField(Object obj) {
        Literal findLiteral = this.litTable.findLiteral(obj);
        if (findLiteral.field == null) {
            findLiteral.assign(this.litTable);
        }
        return findLiteral.field;
    }

    public boolean inlineOk(Expression expression) {
        if (expression instanceof LambdaExp) {
            LambdaExp lambdaExp = (LambdaExp) expression;
            Declaration declaration = lambdaExp.nameDecl;
            if (declaration == null || declaration.getSymbol() == null || !(declaration.context instanceof ModuleExp)) {
                return true;
            }
            if (this.immediate && declaration.isPublic() && !lambdaExp.getFlag(2048) && (this.curLambda == null || lambdaExp.topLevel() != this.curLambda.topLevel())) {
                return false;
            }
        }
        return inlineOk;
    }

    public boolean inlineOk(Procedure procedure) {
        if (!this.immediate || !(procedure instanceof ModuleMethod) || !(((ModuleMethod) procedure).module.getClass().getClassLoader() instanceof ArrayClassLoader)) {
            return inlineOk;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006a A[Catch:{ ClassCastException -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ba A[Catch:{ ClassCastException -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c5 A[Catch:{ ClassCastException -> 0x00d4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compileConstant(java.lang.Object r8, gnu.expr.Target r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof gnu.expr.IgnoreTarget
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r8 instanceof gnu.mapping.Values
            r1 = 0
            if (r0 == 0) goto L_0x0021
            r0 = r8
            gnu.mapping.Values r0 = (gnu.mapping.Values) r0
            java.lang.Object[] r0 = r0.getValues()
            int r2 = r0.length
            boolean r3 = r9 instanceof gnu.expr.ConsumerTarget
            if (r3 == 0) goto L_0x0021
        L_0x0016:
            if (r1 >= r2) goto L_0x0020
            r8 = r0[r1]
            r7.compileConstant(r8, r9)
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0020:
            return
        L_0x0021:
            boolean r0 = r9 instanceof gnu.expr.ConditionalTarget
            if (r0 == 0) goto L_0x003e
            gnu.expr.ConditionalTarget r9 = (gnu.expr.ConditionalTarget) r9
            gnu.bytecode.CodeAttr r0 = r7.getCode()
            gnu.expr.Language r1 = r7.getLanguage()
            boolean r8 = r1.isTrue(r8)
            if (r8 == 0) goto L_0x0038
            gnu.bytecode.Label r8 = r9.ifTrue
            goto L_0x003a
        L_0x0038:
            gnu.bytecode.Label r8 = r9.ifFalse
        L_0x003a:
            r0.emitGoto(r8)
            return
        L_0x003e:
            boolean r0 = r9 instanceof gnu.expr.StackTarget
            if (r0 == 0) goto L_0x0124
            r0 = r9
            gnu.expr.StackTarget r0 = (gnu.expr.StackTarget) r0
            gnu.bytecode.Type r0 = r0.getType()
            boolean r2 = r0 instanceof gnu.bytecode.PrimType
            if (r2 == 0) goto L_0x00d5
            java.lang.String r2 = r0.getSignature()     // Catch:{ ClassCastException -> 0x00d4 }
            gnu.bytecode.CodeAttr r3 = r7.getCode()     // Catch:{ ClassCastException -> 0x00d4 }
            r4 = 1
            if (r2 == 0) goto L_0x0064
            int r5 = r2.length()     // Catch:{ ClassCastException -> 0x00d4 }
            if (r5 == r4) goto L_0x005f
            goto L_0x0064
        L_0x005f:
            char r2 = r2.charAt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            goto L_0x0066
        L_0x0064:
            r2 = 32
        L_0x0066:
            boolean r5 = r8 instanceof java.lang.Number     // Catch:{ ClassCastException -> 0x00d4 }
            if (r5 == 0) goto L_0x00b6
            r5 = r8
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x00d4 }
            r6 = 66
            if (r2 == r6) goto L_0x00ae
            r6 = 68
            if (r2 == r6) goto L_0x00a6
            r6 = 70
            if (r2 == r6) goto L_0x009e
            r6 = 83
            if (r2 == r6) goto L_0x0096
            r6 = 73
            if (r2 == r6) goto L_0x008e
            r6 = 74
            if (r2 == r6) goto L_0x0086
            goto L_0x00b6
        L_0x0086:
            long r1 = r5.longValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushLong(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x008e:
            int r1 = r5.intValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushInt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x0096:
            short r1 = r5.shortValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushInt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x009e:
            float r1 = r5.floatValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushFloat(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x00a6:
            double r1 = r5.doubleValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushDouble(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x00ae:
            byte r1 = r5.byteValue()     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushInt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x00b6:
            r5 = 67
            if (r2 != r5) goto L_0x00c5
            r1 = r0
            gnu.bytecode.PrimType r1 = (gnu.bytecode.PrimType) r1     // Catch:{ ClassCastException -> 0x00d4 }
            char r1 = r1.charValue(r8)     // Catch:{ ClassCastException -> 0x00d4 }
            r3.emitPushInt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x00c5:
            r5 = 90
            if (r2 != r5) goto L_0x00d5
            boolean r2 = gnu.bytecode.PrimType.booleanValue(r8)     // Catch:{ ClassCastException -> 0x00d4 }
            if (r2 == 0) goto L_0x00d0
            r1 = 1
        L_0x00d0:
            r3.emitPushInt(r1)     // Catch:{ ClassCastException -> 0x00d4 }
            return
        L_0x00d4:
        L_0x00d5:
            gnu.bytecode.ClassType r1 = typeClass
            if (r0 != r1) goto L_0x00e3
            boolean r1 = r8 instanceof gnu.bytecode.ClassType
            if (r1 == 0) goto L_0x00e3
            gnu.bytecode.ClassType r8 = (gnu.bytecode.ClassType) r8
            r7.loadClassRef(r8)
            return
        L_0x00e3:
            java.lang.Object r8 = r0.coerceFromObject(r8)     // Catch:{ Exception -> 0x00e8 }
            goto L_0x0124
        L_0x00e8:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            gnu.mapping.Values r2 = gnu.mapping.Values.empty
            if (r8 != r2) goto L_0x00f7
            java.lang.String r2 = "cannot convert void to "
            r1.append(r2)
            goto L_0x0114
        L_0x00f7:
            java.lang.String r2 = "cannot convert literal (of type "
            r1.append(r2)
            if (r8 != 0) goto L_0x0104
            java.lang.String r2 = "<null>"
            r1.append(r2)
            goto L_0x010f
        L_0x0104:
            java.lang.Class r2 = r8.getClass()
            java.lang.String r2 = r2.getName()
            r1.append(r2)
        L_0x010f:
            java.lang.String r2 = ") to "
            r1.append(r2)
        L_0x0114:
            java.lang.String r0 = r0.getName()
            r1.append(r0)
            r0 = 119(0x77, float:1.67E-43)
            java.lang.String r1 = r1.toString()
            r7.error(r0, r1)
        L_0x0124:
            r7.compileConstant(r8)
            if (r8 != 0) goto L_0x012e
            gnu.bytecode.Type r8 = r9.getType()
            goto L_0x0136
        L_0x012e:
            java.lang.Class r8 = r8.getClass()
            gnu.bytecode.Type r8 = gnu.bytecode.Type.make(r8)
        L_0x0136:
            r9.compileFromStack(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.compileConstant(java.lang.Object, gnu.expr.Target):void");
    }

    private void dumpInitializers(Initializer initializer) {
        for (Initializer reverse = Initializer.reverse(initializer); reverse != null; reverse = reverse.next) {
            reverse.emit(this);
        }
    }

    public ClassType findNamedClass(String str) {
        for (int i = 0; i < this.numClasses; i++) {
            if (str.equals(this.classes[i].getName())) {
                return this.classes[i];
            }
        }
        return null;
    }

    private static void putURLWords(String str, StringBuffer stringBuffer) {
        int indexOf = str.indexOf(46);
        if (indexOf > 0) {
            putURLWords(str.substring(indexOf + 1), stringBuffer);
            stringBuffer.append('.');
            str = str.substring(0, indexOf);
        }
        stringBuffer.append(str);
    }

    public static String mangleURI(String str) {
        int lastIndexOf;
        int i;
        boolean z = str.indexOf(47) >= 0;
        int length = str.length();
        if (length > 6 && str.startsWith("class:")) {
            return str.substring(6);
        }
        if (length > 5 && str.charAt(4) == ':' && str.substring(0, 4).equalsIgnoreCase("http")) {
            str = str.substring(5);
            length -= 5;
            z = true;
        } else if (length > 4 && str.charAt(3) == ':' && str.substring(0, 3).equalsIgnoreCase("uri")) {
            str = str.substring(4);
            length -= 4;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(47, i2);
            int i3 = indexOf < 0 ? length : indexOf;
            boolean z2 = stringBuffer.length() == 0;
            if (z2 && z) {
                String substring = str.substring(i2, i3);
                if (i3 - i2 > 4 && substring.startsWith("www.")) {
                    substring = substring.substring(4);
                }
                putURLWords(substring, stringBuffer);
            } else if (i2 != i3) {
                if (!z2) {
                    stringBuffer.append('.');
                }
                if (i3 == length && (lastIndexOf = str.lastIndexOf(46, length)) > i2 + 1 && !z2 && ((i = length - lastIndexOf) <= 4 || (i == 5 && str.endsWith("html")))) {
                    length -= i;
                    str = str.substring(0, length);
                    i3 = length;
                }
                stringBuffer.append(str.substring(i2, i3));
            }
            if (indexOf < 0) {
                return stringBuffer.toString();
            }
            i2 = indexOf + 1;
        }
    }

    public static String mangleName(String str) {
        return mangleName(str, -1);
    }

    public static String mangleNameIfNeeded(String str) {
        return (str == null || isValidJavaName(str)) ? str : mangleName(str, 0);
    }

    public static boolean isValidJavaName(String str) {
        int length = str.length();
        if (length == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        do {
            length--;
            if (length <= 0) {
                return true;
            }
        } while (Character.isJavaIdentifierPart(str.charAt(length)));
        return false;
    }

    public static String mangleName(String str, boolean z) {
        return mangleName(str, z ? 1 : -1);
    }

    public static String mangleName(String str, int i) {
        boolean z = i >= 0;
        int length = str.length();
        if (length == 6 && str.equals("*init*")) {
            return "<init>";
        }
        StringBuffer stringBuffer = new StringBuffer(length);
        int i2 = 0;
        boolean z2 = false;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (z2) {
                charAt = Character.toTitleCase(charAt);
                z2 = false;
            }
            if (Character.isDigit(charAt)) {
                if (i2 == 0) {
                    stringBuffer.append("$N");
                }
                stringBuffer.append(charAt);
            } else if (Character.isLetter(charAt) || charAt == '_') {
                stringBuffer.append(charAt);
            } else if (charAt == '$') {
                stringBuffer.append(i > 1 ? "$$" : "$");
            } else {
                if (charAt == '[') {
                    stringBuffer.append("$LB");
                } else if (charAt == ']') {
                    stringBuffer.append("$RB");
                } else if (charAt != '^') {
                    switch (charAt) {
                        case '!':
                            stringBuffer.append("$Ex");
                            break;
                        case '\"':
                            stringBuffer.append("$Dq");
                            break;
                        case '#':
                            stringBuffer.append("$Nm");
                            break;
                        default:
                            switch (charAt) {
                                case '%':
                                    stringBuffer.append("$Pc");
                                    break;
                                case '&':
                                    stringBuffer.append("$Am");
                                    break;
                                case '\'':
                                    stringBuffer.append("$Sq");
                                    break;
                                case '(':
                                    stringBuffer.append("$LP");
                                    break;
                                case ')':
                                    stringBuffer.append("$RP");
                                    break;
                                case '*':
                                    stringBuffer.append("$St");
                                    break;
                                case '+':
                                    stringBuffer.append("$Pl");
                                    break;
                                case ',':
                                    stringBuffer.append("$Cm");
                                    break;
                                case '-':
                                    if (!z) {
                                        int i3 = i2 + 1;
                                        char charAt2 = i3 < length ? str.charAt(i3) : 0;
                                        if (charAt2 != '>') {
                                            if (!Character.isLowerCase(charAt2)) {
                                                stringBuffer.append("$Mn");
                                                break;
                                            }
                                        } else {
                                            stringBuffer.append("$To$");
                                            i2 = i3;
                                            break;
                                        }
                                    } else {
                                        stringBuffer.append("$Mn");
                                        break;
                                    }
                                    break;
                                case '.':
                                    stringBuffer.append("$Dt");
                                    break;
                                case '/':
                                    stringBuffer.append("$Sl");
                                    break;
                                default:
                                    switch (charAt) {
                                        case ':':
                                            stringBuffer.append("$Cl");
                                            break;
                                        case ';':
                                            stringBuffer.append("$SC");
                                            break;
                                        case '<':
                                            stringBuffer.append("$Ls");
                                            break;
                                        case '=':
                                            stringBuffer.append("$Eq");
                                            break;
                                        case '>':
                                            stringBuffer.append("$Gr");
                                            break;
                                        case '?':
                                            char charAt3 = stringBuffer.length() > 0 ? stringBuffer.charAt(0) : 0;
                                            if (!z && i2 + 1 == length && Character.isLowerCase(charAt3)) {
                                                stringBuffer.setCharAt(0, Character.toTitleCase(charAt3));
                                                stringBuffer.insert(0, "is");
                                                break;
                                            } else {
                                                stringBuffer.append("$Qu");
                                                break;
                                            }
                                            break;
                                        case '@':
                                            stringBuffer.append("$At");
                                            break;
                                        default:
                                            switch (charAt) {
                                                case '{':
                                                    stringBuffer.append("$LC");
                                                    break;
                                                case '|':
                                                    stringBuffer.append("$VB");
                                                    break;
                                                case ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH /*125*/:
                                                    stringBuffer.append("$RC");
                                                    break;
                                                case '~':
                                                    stringBuffer.append("$Tl");
                                                    break;
                                                default:
                                                    stringBuffer.append('$');
                                                    stringBuffer.append(Character.forDigit((charAt >> 12) & 15, 16));
                                                    stringBuffer.append(Character.forDigit((charAt >> 8) & 15, 16));
                                                    stringBuffer.append(Character.forDigit((charAt >> 4) & 15, 16));
                                                    stringBuffer.append(Character.forDigit(charAt & 15, 16));
                                                    break;
                                            }
                                    }
                            }
                    }
                } else {
                    stringBuffer.append("$Up");
                }
                if (!z) {
                    z2 = true;
                }
            }
            i2++;
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.equals(str) ? str : stringBuffer2;
    }

    public static String demangleName(String str) {
        return demangleName(str, false);
    }

    public static String demangleName(String str, boolean z) {
        int i;
        int i2;
        String str2 = str;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (i < length) {
            char charAt = str2.charAt(i);
            if (z3 && !z) {
                charAt = Character.toLowerCase(charAt);
                z3 = false;
            }
            if (!z && charAt == 'i' && i == 0 && length > 2) {
                int i4 = i + 1;
                if (str2.charAt(i4) == 's') {
                    char charAt2 = str2.charAt(i + 2);
                    if (!Character.isLowerCase(charAt2)) {
                        if (Character.isUpperCase(charAt2) || Character.isTitleCase(charAt2)) {
                            stringBuffer.append(Character.toLowerCase(charAt2));
                            i = i4 + 1;
                        } else {
                            i = i4;
                        }
                        z2 = true;
                        z4 = true;
                        i3 = i + 1;
                    }
                }
            }
            if (charAt == '$' && (i2 = i + 2) < length) {
                char charAt3 = str2.charAt(i + 1);
                char charAt4 = str2.charAt(i2);
                char demangle2 = demangle2(charAt3, charAt4);
                if (demangle2 != 65535) {
                    stringBuffer.append(demangle2);
                } else if (charAt3 == 'T' && charAt4 == 'o' && (i2 = i + 3) < length && str2.charAt(i2) == '$') {
                    stringBuffer.append("->");
                }
                i = i2;
                z3 = true;
                z4 = true;
                i3 = i + 1;
            } else if (!z && i > 1 && ((Character.isUpperCase(charAt) || Character.isTitleCase(charAt)) && Character.isLowerCase(str2.charAt(i - 1)))) {
                stringBuffer.append('-');
                charAt = Character.toLowerCase(charAt);
                z4 = true;
            }
            stringBuffer.append(charAt);
            i3 = i + 1;
        }
        if (z2) {
            stringBuffer.append('?');
        }
        return z4 ? stringBuffer.toString() : str2;
    }

    public String generateClassName(String str) {
        String mangleName = mangleName(str, true);
        if (this.mainClass != null) {
            mangleName = this.mainClass.getName() + '$' + mangleName;
        } else if (this.classPrefix != null) {
            mangleName = this.classPrefix + mangleName;
        }
        if (findNamedClass(mangleName) == null) {
            return mangleName;
        }
        int i = 0;
        while (true) {
            String str2 = mangleName + i;
            if (findNamedClass(str2) == null) {
                return str2;
            }
            i++;
        }
    }

    public Compilation(Language language2, SourceMessages sourceMessages, NameLookup nameLookup) {
        this.language = language2;
        this.messages = sourceMessages;
        this.lexical = nameLookup;
    }

    public void walkModule(ModuleExp moduleExp) {
        if (debugPrintExpr) {
            OutPort errDefault = OutPort.errDefault();
            errDefault.println("[Module:" + moduleExp.getName());
            moduleExp.print(errDefault);
            errDefault.println(']');
            errDefault.flush();
        }
        InlineCalls.inlineCalls(moduleExp, this);
        PushApply.pushApply(moduleExp);
        ChainLambdas.chainLambdas(moduleExp, this);
        FindTailCalls.findTailCalls(moduleExp, this);
    }

    public void outputClass(String str) throws IOException {
        char c = File.separatorChar;
        for (int i = 0; i < this.numClasses; i++) {
            ClassType classType = this.classes[i];
            String str2 = str + classType.getName().replace('.', c) + ".class";
            String parent = new File(str2).getParent();
            if (parent != null) {
                new File(parent).mkdirs();
            }
            classType.writeToFile(str2);
        }
        this.minfo.cleanupAfterCompilation();
    }

    public void cleanupAfterCompilation() {
        for (int i = 0; i < this.numClasses; i++) {
            this.classes[i].cleanupAfterCompilation();
        }
        this.classes = null;
        this.minfo.comp = null;
        if (this.minfo.exp != null) {
            this.minfo.exp.body = null;
        }
        this.mainLambda.body = null;
        this.mainLambda = null;
        if (!this.immediate) {
            this.litTable = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005d A[LOOP:0: B:14:0x0059->B:16:0x005d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compileToArchive(gnu.expr.ModuleExp r9, java.lang.String r10) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r9 = ".zip"
            boolean r0 = r10.endsWith(r9)
            r1 = 0
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            r9 = 0
            goto L_0x0025
        L_0x000b:
            java.lang.String r0 = ".jar"
            boolean r0 = r10.endsWith(r0)
            if (r0 == 0) goto L_0x0015
            r9 = 1
            goto L_0x0025
        L_0x0015:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            r0.append(r9)
            java.lang.String r10 = r0.toString()
            goto L_0x0009
        L_0x0025:
            r0 = 12
            r8.process(r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r10)
            boolean r10 = r0.exists()
            if (r10 == 0) goto L_0x0038
            r0.delete()
        L_0x0038:
            if (r9 == 0) goto L_0x0045
            java.util.jar.JarOutputStream r9 = new java.util.jar.JarOutputStream
            java.io.FileOutputStream r10 = new java.io.FileOutputStream
            r10.<init>(r0)
            r9.<init>(r10)
            goto L_0x004f
        L_0x0045:
            java.util.zip.ZipOutputStream r9 = new java.util.zip.ZipOutputStream
            java.io.FileOutputStream r10 = new java.io.FileOutputStream
            r10.<init>(r0)
            r9.<init>(r10)
        L_0x004f:
            int r10 = r8.numClasses
            byte[][] r10 = new byte[r10][]
            java.util.zip.CRC32 r0 = new java.util.zip.CRC32
            r0.<init>()
            r2 = 0
        L_0x0059:
            int r3 = r8.numClasses
            if (r2 >= r3) goto L_0x00ab
            gnu.bytecode.ClassType[] r3 = r8.classes
            r3 = r3[r2]
            byte[] r4 = r3.writeToArray()
            r10[r2] = r4
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = r3.getName()
            r6 = 46
            r7 = 47
            java.lang.String r3 = r3.replace(r6, r7)
            r5.append(r3)
            java.lang.String r3 = ".class"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.<init>(r3)
            r3 = r10[r2]
            int r3 = r3.length
            long r5 = (long) r3
            r4.setSize(r5)
            r0.reset()
            r3 = r10[r2]
            int r5 = r3.length
            r0.update(r3, r1, r5)
            long r5 = r0.getValue()
            r4.setCrc(r5)
            r9.putNextEntry(r4)
            r3 = r10[r2]
            r9.write(r3)
            int r2 = r2 + 1
            goto L_0x0059
        L_0x00ab:
            r9.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.compileToArchive(gnu.expr.ModuleExp, java.lang.String):void");
    }

    private void registerClass(ClassType classType) {
        ClassType[] classTypeArr = this.classes;
        if (classTypeArr == null) {
            this.classes = new ClassType[20];
        } else {
            int i = this.numClasses;
            if (i >= classTypeArr.length) {
                ClassType[] classTypeArr2 = new ClassType[(classTypeArr.length * 2)];
                System.arraycopy(classTypeArr, 0, classTypeArr2, 0, i);
                this.classes = classTypeArr2;
            }
        }
        classType.addModifiers(classType.isInterface() ? 1 : 33);
        ClassType classType2 = this.mainClass;
        if (classType == classType2 && this.numClasses > 0) {
            ClassType[] classTypeArr3 = this.classes;
            ClassType classType3 = classTypeArr3[0];
            classTypeArr3[0] = classType2;
            classType = classType3;
        }
        ClassType[] classTypeArr4 = this.classes;
        int i2 = this.numClasses;
        this.numClasses = i2 + 1;
        classTypeArr4[i2] = classType;
    }

    public void addClass(ClassType classType) {
        if (this.mainLambda.filename != null) {
            if (emitSourceDebugExtAttr) {
                classType.setStratum(getLanguage().getName());
            }
            classType.setSourceFile(this.mainLambda.filename);
        }
        registerClass(classType);
        classType.setClassfileVersion(defaultClassFileVersion);
    }

    public boolean makeRunnable() {
        return !generatingServlet() && !generatingApplet() && !getModule().staticInitRun();
    }

    public void addMainClass(ModuleExp moduleExp) {
        ClassType classFor = moduleExp.classFor(this);
        this.mainClass = classFor;
        ClassType[] interfaces = moduleExp.getInterfaces();
        if (interfaces != null) {
            classFor.setInterfaces(interfaces);
        }
        ClassType superType = moduleExp.getSuperType();
        if (superType == null) {
            if (generatingApplet()) {
                superType = typeApplet;
            } else if (generatingServlet()) {
                superType = typeServlet;
            } else {
                superType = getModuleType();
            }
        }
        if (makeRunnable()) {
            classFor.addInterface(typeRunnable);
        }
        classFor.setSuper(superType);
        moduleExp.type = classFor;
        addClass(classFor);
        getConstructor(this.mainClass, moduleExp);
    }

    public final Method getConstructor(LambdaExp lambdaExp) {
        return getConstructor(lambdaExp.getHeapFrameType(), lambdaExp);
    }

    public static final Method getConstructor(ClassType classType, LambdaExp lambdaExp) {
        Method declaredMethod = classType.getDeclaredMethod("<init>", 0);
        if (declaredMethod != null) {
            return declaredMethod;
        }
        return classType.addMethod("<init>", 1, (!(lambdaExp instanceof ClassExp) || lambdaExp.staticLinkField == null) ? apply0args : new Type[]{lambdaExp.staticLinkField.getType()}, (Type) Type.voidType);
    }

    public final void generateConstructor(LambdaExp lambdaExp) {
        generateConstructor(lambdaExp.getHeapFrameType(), lambdaExp);
    }

    public final void generateConstructor(ClassType classType, LambdaExp lambdaExp) {
        ModuleInfo moduleInfo;
        Method method2 = this.method;
        Variable variable = this.callContextVar;
        this.callContextVar = null;
        ClassType classType2 = this.curClass;
        this.curClass = classType;
        Method constructor = getConstructor(classType, lambdaExp);
        classType.constructor = constructor;
        this.method = constructor;
        CodeAttr startCode = constructor.startCode();
        boolean z = lambdaExp instanceof ClassExp;
        if (z && lambdaExp.staticLinkField != null) {
            startCode.emitPushThis();
            startCode.emitLoad(startCode.getCurrentScope().getVariable(1));
            startCode.emitPutField(lambdaExp.staticLinkField);
        }
        ClassExp.invokeDefaultSuperConstructor(classType.getSuperclass(), this, lambdaExp);
        if (!(this.curClass != this.mainClass || (moduleInfo = this.minfo) == null || moduleInfo.sourcePath == null)) {
            startCode.emitPushThis();
            startCode.emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
        }
        if (!(lambdaExp == null || lambdaExp.initChain == null)) {
            LambdaExp lambdaExp2 = this.curLambda;
            LambdaExp lambdaExp3 = new LambdaExp();
            this.curLambda = lambdaExp3;
            lambdaExp3.closureEnv = startCode.getArg(0);
            this.curLambda.outer = lambdaExp2;
            while (true) {
                Initializer initializer = lambdaExp.initChain;
                if (initializer == null) {
                    break;
                }
                lambdaExp.initChain = null;
                dumpInitializers(initializer);
            }
            this.curLambda = lambdaExp2;
        }
        if (z) {
            callInitMethods(((ClassExp) lambdaExp).getCompiledClassType(this), new Vector(10));
        }
        startCode.emitReturn();
        this.method = method2;
        this.curClass = classType2;
        this.callContextVar = variable;
    }

    /* access modifiers changed from: package-private */
    public void callInitMethods(ClassType classType, Vector<ClassType> vector) {
        if (classType != null) {
            String name = classType.getName();
            if (!"java.lang.Object".equals(name)) {
                int size = vector.size();
                do {
                    size--;
                    if (size < 0) {
                        vector.addElement(classType);
                        ClassType[] interfaces = classType.getInterfaces();
                        int i = 0;
                        if (interfaces != null) {
                            for (ClassType callInitMethods : interfaces) {
                                callInitMethods(callInitMethods, vector);
                            }
                        }
                        if (classType.isInterface()) {
                            if (classType instanceof PairClassType) {
                                classType = ((PairClassType) classType).instanceType;
                            } else {
                                try {
                                    classType = (ClassType) Type.make(Class.forName(classType.getName() + "$class"));
                                } catch (Throwable unused) {
                                    return;
                                }
                            }
                            i = 1;
                        }
                        Method declaredMethod = classType.getDeclaredMethod("$finit$", i);
                        if (declaredMethod != null) {
                            CodeAttr code = getCode();
                            code.emitPushThis();
                            code.emitInvoke(declaredMethod);
                            return;
                        }
                        return;
                    }
                } while (vector.elementAt(size).getName() != name);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r1 == false) goto L_0x007b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0213  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void generateMatchMethods(gnu.expr.LambdaExp r25) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            java.util.Vector r2 = r1.applyMethods
            if (r2 != 0) goto L_0x000a
            r2 = 0
            goto L_0x0010
        L_0x000a:
            java.util.Vector r2 = r1.applyMethods
            int r2 = r2.size()
        L_0x0010:
            if (r2 != 0) goto L_0x0013
            return
        L_0x0013:
            gnu.bytecode.Method r4 = r0.method
            gnu.bytecode.ClassType r5 = r0.curClass
            gnu.bytecode.ClassType r6 = typeModuleMethod
            gnu.bytecode.ClassType r7 = r25.getHeapFrameType()
            r0.curClass = r7
            gnu.bytecode.ClassType r7 = r7.getSuperclass()
            gnu.bytecode.ClassType r8 = typeModuleBody
            boolean r7 = r7.isSubtype(r8)
            if (r7 != 0) goto L_0x002f
            gnu.bytecode.ClassType r7 = r0.moduleClass
            r0.curClass = r7
        L_0x002f:
            r8 = 0
            r9 = 0
        L_0x0031:
            r10 = 5
            if (r8 > r10) goto L_0x026d
            r11 = r2
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0039:
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x0230
            java.util.Vector r7 = r1.applyMethods
            java.lang.Object r7 = r7.elementAt(r11)
            gnu.expr.LambdaExp r7 = (gnu.expr.LambdaExp) r7
            gnu.bytecode.Method[] r3 = r7.primMethods
            int r3 = r3.length
            int r10 = r7.max_args
            if (r10 < 0) goto L_0x0056
            int r10 = r7.max_args
            int r1 = r7.min_args
            int r1 = r1 + r3
            if (r10 < r1) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r1 = 0
            goto L_0x0057
        L_0x0056:
            r1 = 1
        L_0x0057:
            r10 = 5
            if (r8 >= r10) goto L_0x0069
            int r10 = r7.min_args
            int r10 = r8 - r10
            if (r10 < 0) goto L_0x0075
            if (r10 >= r3) goto L_0x0075
            int r3 = r3 + -1
            if (r10 != r3) goto L_0x007b
            if (r1 == 0) goto L_0x007b
            goto L_0x0075
        L_0x0069:
            int r10 = r7.min_args
            r16 = 5
            int r10 = 5 - r10
            if (r10 <= 0) goto L_0x0079
            if (r3 > r10) goto L_0x0079
            if (r1 != 0) goto L_0x0079
        L_0x0075:
            r1 = r25
        L_0x0077:
            r10 = 5
            goto L_0x0039
        L_0x0079:
            int r10 = r3 + -1
        L_0x007b:
            r1 = 3
            if (r12 != 0) goto L_0x00e4
            r3 = 5
            if (r8 >= r3) goto L_0x00ad
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r9 = "match"
            r3.append(r9)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            int r9 = r8 + 2
            gnu.bytecode.Type[] r9 = new gnu.bytecode.Type[r9]
            r12 = r8
        L_0x0097:
            if (r12 < 0) goto L_0x00a2
            int r13 = r12 + 1
            gnu.bytecode.ClassType r14 = typeObject
            r9[r13] = r14
            int r12 = r12 + -1
            goto L_0x0097
        L_0x00a2:
            int r12 = r8 + 1
            gnu.bytecode.ClassType r13 = typeCallContext
            r9[r12] = r13
            r14 = r3
            r15 = r9
            r3 = 0
            r12 = 1
            goto L_0x00be
        L_0x00ad:
            gnu.bytecode.Type[] r3 = new gnu.bytecode.Type[r1]
            gnu.bytecode.ArrayType r9 = objArrayType
            r12 = 1
            r3[r12] = r9
            gnu.bytecode.ClassType r9 = typeCallContext
            r13 = 2
            r3[r13] = r9
            java.lang.String r9 = "matchN"
            r15 = r3
            r14 = r9
            r3 = 0
        L_0x00be:
            r15[r3] = r6
            gnu.bytecode.ClassType r3 = r0.curClass
            gnu.bytecode.PrimType r9 = gnu.bytecode.Type.intType
            gnu.bytecode.Method r3 = r3.addMethod((java.lang.String) r14, (gnu.bytecode.Type[]) r15, (gnu.bytecode.Type) r9, (int) r12)
            r0.method = r3
            gnu.bytecode.CodeAttr r3 = r3.startCode()
            gnu.bytecode.Variable r9 = r3.getArg(r12)
            r3.emitLoad(r9)
            java.lang.String r9 = "selector"
            gnu.bytecode.Field r9 = r6.getField(r9)
            r3.emitGetField(r9)
            gnu.bytecode.SwitchState r13 = r3.startSwitch()
            r9 = r3
            r12 = 1
        L_0x00e4:
            int r3 = r7.getSelectorValue(r0)
            r13.addCase(r3, r9)
            int r3 = r7.getLineNumber()
            if (r3 <= 0) goto L_0x00f8
            java.lang.String r1 = r7.getFileName()
            r9.putLineNumber(r1, r3)
        L_0x00f8:
            r1 = 5
            if (r8 != r1) goto L_0x00fd
            r3 = 3
            goto L_0x00ff
        L_0x00fd:
            int r3 = r8 + 2
        L_0x00ff:
            gnu.bytecode.Variable r3 = r9.getArg(r3)
            if (r8 >= r1) goto L_0x01c5
            gnu.expr.Declaration r16 = r7.firstDecl()
            r1 = 1
        L_0x010a:
            if (r1 > r8) goto L_0x01b8
            r9.emitLoad(r3)
            r17 = r2
            int r2 = r1 + 1
            r18 = r6
            gnu.bytecode.Variable r6 = r9.getArg(r2)
            r9.emitLoad(r6)
            gnu.bytecode.Type r6 = r16.getType()
            r19 = r2
            gnu.bytecode.ClassType r2 = gnu.bytecode.Type.objectType
            if (r6 == r2) goto L_0x0185
            boolean r2 = r6 instanceof gnu.expr.TypeValue
            r20 = -786432(0xfffffffffff40000, float:NaN)
            if (r2 == 0) goto L_0x015d
            gnu.bytecode.Label r2 = new gnu.bytecode.Label
            r2.<init>((gnu.bytecode.CodeAttr) r9)
            r21 = r11
            gnu.bytecode.Label r11 = new gnu.bytecode.Label
            r11.<init>((gnu.bytecode.CodeAttr) r9)
            r22 = r12
            gnu.expr.ConditionalTarget r12 = new gnu.expr.ConditionalTarget
            r23 = r13
            gnu.expr.Language r13 = r24.getLanguage()
            r12.<init>(r2, r11, r13)
            r9.emitDup()
            gnu.expr.TypeValue r6 = (gnu.expr.TypeValue) r6
            r13 = 0
            r6.emitIsInstance(r13, r0, r12)
            r11.define(r9)
            r6 = r1 | r20
            r9.emitPushInt(r6)
            r9.emitReturn()
            r2.define(r9)
            goto L_0x018c
        L_0x015d:
            r21 = r11
            r22 = r12
            r23 = r13
            r13 = 0
            boolean r2 = r6 instanceof gnu.bytecode.ClassType
            if (r2 == 0) goto L_0x018c
            gnu.bytecode.ClassType r2 = gnu.bytecode.Type.objectType
            if (r6 == r2) goto L_0x018c
            gnu.bytecode.ClassType r2 = gnu.bytecode.Type.toStringType
            if (r6 == r2) goto L_0x018c
            r9.emitDup()
            r6.emitIsInstance(r9)
            r9.emitIfIntEqZero()
            r2 = r1 | r20
            r9.emitPushInt(r2)
            r9.emitReturn()
            r9.emitFi()
            goto L_0x018c
        L_0x0185:
            r21 = r11
            r22 = r12
            r23 = r13
            r13 = 0
        L_0x018c:
            gnu.bytecode.ClassType r2 = typeCallContext
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r11 = "value"
            r6.append(r11)
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            gnu.bytecode.Field r1 = r2.getField(r1)
            r9.emitPutField(r1)
            gnu.expr.Declaration r16 = r16.nextDecl()
            r2 = r17
            r6 = r18
            r1 = r19
            r11 = r21
            r12 = r22
            r13 = r23
            goto L_0x010a
        L_0x01b8:
            r17 = r2
            r18 = r6
            r21 = r11
            r22 = r12
            r23 = r13
            r13 = 0
            r1 = 2
            goto L_0x01e6
        L_0x01c5:
            r17 = r2
            r18 = r6
            r21 = r11
            r22 = r12
            r23 = r13
            r13 = 0
            r9.emitLoad(r3)
            r1 = 2
            gnu.bytecode.Variable r2 = r9.getArg(r1)
            r9.emitLoad(r2)
            gnu.bytecode.ClassType r2 = typeCallContext
            java.lang.String r6 = "values"
            gnu.bytecode.Field r2 = r2.getField(r6)
            r9.emitPutField(r2)
        L_0x01e6:
            r9.emitLoad(r3)
            int r2 = defaultCallConvention
            if (r2 >= r1) goto L_0x01f6
            r2 = 1
            gnu.bytecode.Variable r2 = r9.getArg(r2)
            r9.emitLoad(r2)
            goto L_0x01fe
        L_0x01f6:
            r2 = 0
            gnu.bytecode.Variable r6 = r9.getArg(r2)
            r9.emitLoad(r6)
        L_0x01fe:
            gnu.bytecode.Field r2 = procCallContextField
            r9.emitPutField(r2)
            r9.emitLoad(r3)
            int r2 = defaultCallConvention
            if (r2 < r1) goto L_0x0213
            int r1 = r7.getSelectorValue(r0)
            int r1 = r1 + r10
            r9.emitPushInt(r1)
            goto L_0x0216
        L_0x0213:
            r9.emitPushInt(r8)
        L_0x0216:
            gnu.bytecode.Field r1 = pcCallContextField
            r9.emitPutField(r1)
            r3 = 0
            r9.emitPushInt(r3)
            r9.emitReturn()
            r1 = r25
            r2 = r17
            r6 = r18
            r11 = r21
            r12 = r22
            r13 = r23
            goto L_0x0077
        L_0x0230:
            r17 = r2
            r18 = r6
            r1 = 2
            r2 = 0
            r3 = 0
            if (r12 == 0) goto L_0x0263
            r13.addDefault(r9)
            r6 = 4
            if (r8 <= r6) goto L_0x0242
            r1 = 1
            r7 = 2
            goto L_0x0245
        L_0x0242:
            int r7 = r8 + 1
            r1 = 1
        L_0x0245:
            int r7 = r7 + r1
            r1 = 0
        L_0x0247:
            if (r1 > r7) goto L_0x0253
            gnu.bytecode.Variable r6 = r9.getArg(r1)
            r9.emitLoad(r6)
            int r1 = r1 + 1
            goto L_0x0247
        L_0x0253:
            gnu.bytecode.ClassType r1 = typeModuleBody
            int r6 = r15.length
            gnu.bytecode.Method r1 = r1.getDeclaredMethod((java.lang.String) r14, (int) r6)
            r9.emitInvokeSpecial(r1)
            r9.emitReturn()
            r13.finish(r9)
        L_0x0263:
            int r8 = r8 + 1
            r1 = r25
            r2 = r17
            r6 = r18
            goto L_0x0031
        L_0x026d:
            r0.method = r4
            r0.curClass = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.generateMatchMethods(gnu.expr.LambdaExp):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0230 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x019a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0222 A[EDGE_INSN: B:105:0x0222->B:90:0x0222 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x021e A[LOOP:3: B:87:0x021a->B:89:0x021e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0227  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void generateApplyMethodsWithContext(gnu.expr.LambdaExp r27) {
        /*
            r26 = this;
            r6 = r26
            r7 = r27
            java.util.Vector r0 = r7.applyMethods
            r8 = 0
            if (r0 != 0) goto L_0x000b
            r9 = 0
            goto L_0x0012
        L_0x000b:
            java.util.Vector r0 = r7.applyMethods
            int r0 = r0.size()
            r9 = r0
        L_0x0012:
            if (r9 != 0) goto L_0x0015
            return
        L_0x0015:
            gnu.bytecode.ClassType r10 = r6.curClass
            gnu.bytecode.ClassType r0 = r27.getHeapFrameType()
            r6.curClass = r0
            gnu.bytecode.ClassType r0 = r0.getSuperclass()
            gnu.bytecode.ClassType r1 = typeModuleWithContext
            boolean r0 = r0.isSubtype(r1)
            if (r0 != 0) goto L_0x002d
            gnu.bytecode.ClassType r0 = r6.moduleClass
            r6.curClass = r0
        L_0x002d:
            gnu.bytecode.Method r11 = r6.method
            r12 = 1
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r12]
            gnu.bytecode.ClassType r1 = typeCallContext
            r0[r8] = r1
            gnu.bytecode.ClassType r1 = r6.curClass
            gnu.bytecode.PrimType r2 = gnu.bytecode.Type.voidType
            java.lang.String r3 = "apply"
            gnu.bytecode.Method r0 = r1.addMethod((java.lang.String) r3, (gnu.bytecode.Type[]) r0, (gnu.bytecode.Type) r2, (int) r12)
            r6.method = r0
            gnu.bytecode.CodeAttr r13 = r0.startCode()
            gnu.bytecode.Variable r14 = r13.getArg(r12)
            r13.emitLoad(r14)
            gnu.bytecode.Field r0 = pcCallContextField
            r13.emitGetField(r0)
            gnu.bytecode.SwitchState r15 = r13.startSwitch()
            r5 = 0
        L_0x0057:
            if (r5 >= r9) goto L_0x0260
            java.util.Vector r0 = r7.applyMethods
            java.lang.Object r0 = r0.elementAt(r5)
            r4 = r0
            gnu.expr.LambdaExp r4 = (gnu.expr.LambdaExp) r4
            gnu.bytecode.Method[] r3 = r4.primMethods
            int r2 = r3.length
            r1 = 0
        L_0x0066:
            if (r1 >= r2) goto L_0x024d
            int r0 = r2 + -1
            if (r1 != r0) goto L_0x0079
            int r0 = r4.max_args
            if (r0 < 0) goto L_0x0077
            int r0 = r4.max_args
            int r8 = r4.min_args
            int r8 = r8 + r2
            if (r0 < r8) goto L_0x0079
        L_0x0077:
            r0 = 1
            goto L_0x007a
        L_0x0079:
            r0 = 0
        L_0x007a:
            int r8 = r4.getSelectorValue(r6)
            int r8 = r8 + r1
            r15.addCase(r8, r13)
            gnu.text.SourceMessages r8 = r6.messages
            gnu.text.SourceLocator r8 = r8.swapSourceLocator(r4)
            int r12 = r4.getLineNumber()
            r16 = r5
            if (r12 <= 0) goto L_0x0097
            java.lang.String r5 = r4.getFileName()
            r13.putLineNumber(r5, r12)
        L_0x0097:
            r12 = r3[r1]
            gnu.bytecode.Type[] r5 = r12.getParameterTypes()
            int r7 = r4.min_args
            int r7 = r7 + r1
            r17 = 0
            r18 = r9
            r9 = 4
            if (r1 <= r9) goto L_0x00d7
            r9 = 1
            if (r2 <= r9) goto L_0x00d7
            gnu.bytecode.PrimType r9 = gnu.bytecode.Type.intType
            gnu.bytecode.Variable r9 = r13.addLocal(r9)
            r13.emitLoad(r14)
            r20 = r1
            gnu.bytecode.ClassType r1 = typeCallContext
            r21 = r2
            java.lang.String r2 = "getArgCount"
            r22 = r10
            r10 = 0
            gnu.bytecode.Method r1 = r1.getDeclaredMethod((java.lang.String) r2, (int) r10)
            r13.emitInvoke(r1)
            int r1 = r4.min_args
            if (r1 == 0) goto L_0x00d3
            int r1 = r4.min_args
            r13.emitPushInt(r1)
            gnu.bytecode.PrimType r1 = gnu.bytecode.Type.intType
            r13.emitSub((gnu.bytecode.PrimType) r1)
        L_0x00d3:
            r13.emitStore(r9)
            goto L_0x00df
        L_0x00d7:
            r20 = r1
            r21 = r2
            r22 = r10
            r9 = r17
        L_0x00df:
            boolean r1 = r12.getStaticFlag()
            r2 = 1
            r1 = r1 ^ r2
            if (r0 == 0) goto L_0x00e9
            r2 = 2
            goto L_0x00ea
        L_0x00e9:
            r2 = 1
        L_0x00ea:
            int r2 = r2 + r7
            int r10 = r5.length
            if (r2 >= r10) goto L_0x00f0
            r2 = 1
            goto L_0x00f1
        L_0x00f0:
            r2 = 0
        L_0x00f1:
            int r1 = r1 + r2
            if (r1 <= 0) goto L_0x0106
            r13.emitPushThis()
            gnu.bytecode.ClassType r1 = r6.curClass
            gnu.bytecode.ClassType r10 = r6.moduleClass
            if (r1 != r10) goto L_0x0106
            gnu.bytecode.ClassType r1 = r6.mainClass
            if (r1 == r10) goto L_0x0106
            gnu.bytecode.Field r1 = r6.moduleInstanceMainField
            r13.emitGetField(r1)
        L_0x0106:
            gnu.expr.Declaration r1 = r4.firstDecl()
            if (r1 == 0) goto L_0x0116
            boolean r10 = r1.isThisParameter()
            if (r10 == 0) goto L_0x0116
            gnu.expr.Declaration r1 = r1.nextDecl()
        L_0x0116:
            r24 = r11
            r10 = 0
            r23 = 0
        L_0x011b:
            if (r10 >= r7) goto L_0x01a6
            if (r9 == 0) goto L_0x013e
            int r11 = r4.min_args
            if (r10 < r11) goto L_0x013e
            r13.emitLoad(r9)
            r13.emitIfIntLEqZero()
            r13.emitLoad(r14)
            int r11 = r4.min_args
            int r11 = r10 - r11
            r11 = r3[r11]
            r13.emitInvoke(r11)
            r13.emitElse()
            int r23 = r23 + 1
            r11 = -1
            r13.emitInc(r9, r11)
        L_0x013e:
            r13.emitLoad(r14)
            r11 = 4
            if (r10 > r11) goto L_0x016b
            if (r0 != 0) goto L_0x016b
            r19 = r3
            int r3 = r4.max_args
            if (r3 > r11) goto L_0x016d
            gnu.bytecode.ClassType r3 = typeCallContext
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r25 = r15
            java.lang.String r15 = "value"
            r11.append(r15)
            int r15 = r10 + 1
            r11.append(r15)
            java.lang.String r11 = r11.toString()
            gnu.bytecode.Field r3 = r3.getDeclaredField(r11)
            r13.emitGetField(r3)
            goto L_0x0182
        L_0x016b:
            r19 = r3
        L_0x016d:
            r25 = r15
            gnu.bytecode.ClassType r3 = typeCallContext
            java.lang.String r11 = "values"
            gnu.bytecode.Field r3 = r3.getDeclaredField(r11)
            r13.emitGetField(r3)
            r13.emitPushInt(r10)
            gnu.bytecode.ClassType r3 = gnu.bytecode.Type.objectType
            r13.emitArrayLoad(r3)
        L_0x0182:
            gnu.bytecode.Type r3 = r1.getType()
            gnu.bytecode.ClassType r11 = gnu.bytecode.Type.objectType
            if (r3 == r11) goto L_0x019a
            gnu.text.SourceMessages r11 = r6.messages
            gnu.text.SourceLocator r11 = r11.swapSourceLocator(r1)
            int r15 = r10 + 1
            gnu.expr.CheckedTarget.emitCheckedCoerce((gnu.expr.Compilation) r6, (gnu.expr.LambdaExp) r4, (int) r15, (gnu.bytecode.Type) r3)
            gnu.text.SourceMessages r3 = r6.messages
            r3.swapSourceLocator(r11)
        L_0x019a:
            gnu.expr.Declaration r1 = r1.nextDecl()
            int r10 = r10 + 1
            r3 = r19
            r15 = r25
            goto L_0x011b
        L_0x01a6:
            r19 = r3
            r25 = r15
            if (r0 == 0) goto L_0x020b
            int r2 = r2 + r7
            r5 = r5[r2]
            boolean r0 = r5 instanceof gnu.bytecode.ArrayType
            if (r0 == 0) goto L_0x01c5
            r0 = r26
            r10 = r20
            r1 = r4
            r11 = r21
            r2 = r7
            r15 = r19
            r3 = r9
            r9 = r4
            r4 = r5
            r5 = r14
            r0.varArgsToArray(r1, r2, r3, r4, r5)
            goto L_0x0212
        L_0x01c5:
            r9 = r4
            r15 = r19
            r10 = r20
            r11 = r21
            java.lang.String r0 = r5.getName()
            java.lang.String r1 = "gnu.lists.LList"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x01eb
            r13.emitLoad(r14)
            r13.emitPushInt(r7)
            gnu.bytecode.ClassType r0 = typeCallContext
            java.lang.String r1 = "getRestArgsList"
            r2 = 1
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r1, (int) r2)
            r13.emitInvokeVirtual(r0)
            goto L_0x0213
        L_0x01eb:
            r2 = 1
            gnu.bytecode.ClassType r0 = typeCallContext
            if (r5 != r0) goto L_0x01f4
            r13.emitLoad(r14)
            goto L_0x0213
        L_0x01f4:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unsupported #!rest type:"
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x020b:
            r9 = r4
            r15 = r19
            r10 = r20
            r11 = r21
        L_0x0212:
            r2 = 1
        L_0x0213:
            r13.emitLoad(r14)
            r13.emitInvoke(r12)
            r0 = -1
        L_0x021a:
            int r23 = r23 + -1
            if (r23 < 0) goto L_0x0222
            r13.emitFi()
            goto L_0x021a
        L_0x0222:
            int r0 = defaultCallConvention
            r1 = 2
            if (r0 >= r1) goto L_0x0230
            gnu.expr.Target r0 = gnu.expr.Target.pushObject
            gnu.bytecode.Type r1 = r9.getReturnType()
            r0.compileFromStack(r6, r1)
        L_0x0230:
            gnu.text.SourceMessages r0 = r6.messages
            r0.swapSourceLocator(r8)
            r13.emitReturn()
            int r1 = r10 + 1
            r7 = r27
            r4 = r9
            r2 = r11
            r3 = r15
            r5 = r16
            r9 = r18
            r10 = r22
            r11 = r24
            r15 = r25
            r8 = 0
            r12 = 1
            goto L_0x0066
        L_0x024d:
            r16 = r5
            r18 = r9
            r22 = r10
            r24 = r11
            r25 = r15
            r2 = 1
            int r5 = r16 + 1
            r7 = r27
            r8 = 0
            r12 = 1
            goto L_0x0057
        L_0x0260:
            r22 = r10
            r24 = r11
            r0 = r15
            r0.addDefault(r13)
            gnu.bytecode.ClassType r1 = typeModuleMethod
            java.lang.String r2 = "applyError"
            r3 = 0
            gnu.bytecode.Method r1 = r1.getDeclaredMethod((java.lang.String) r2, (int) r3)
            r13.emitInvokeStatic(r1)
            r13.emitReturn()
            r0.finish(r13)
            r0 = r24
            r6.method = r0
            r0 = r22
            r6.curClass = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.generateApplyMethodsWithContext(gnu.expr.LambdaExp):void");
    }

    public void generateApplyMethodsWithoutContext(LambdaExp lambdaExp) {
        int i;
        int i2;
        boolean z;
        ClassType classType;
        int i3;
        int i4;
        int i5;
        Type[] typeArr;
        String str;
        boolean z2;
        CodeAttr codeAttr;
        SwitchState switchState;
        SwitchState switchState2;
        Variable variable;
        int i6;
        SwitchState switchState3;
        SourceLocator sourceLocator;
        Method method2;
        int i7;
        Variable variable2;
        int i8;
        int i9;
        String str2;
        Type[] typeArr2;
        int i10;
        LambdaExp lambdaExp2 = lambdaExp;
        int size = lambdaExp2.applyMethods == null ? 0 : lambdaExp2.applyMethods.size();
        if (size != 0) {
            ClassType classType2 = this.curClass;
            ClassType heapFrameType = lambdaExp.getHeapFrameType();
            this.curClass = heapFrameType;
            ClassType classType3 = typeModuleMethod;
            if (!heapFrameType.getSuperclass().isSubtype(typeModuleBody)) {
                this.curClass = this.moduleClass;
            }
            Method method3 = this.method;
            int i11 = defaultCallConvention >= 2 ? 5 : 0;
            CodeAttr codeAttr2 = null;
            while (i11 < 6) {
                boolean z3 = false;
                SwitchState switchState4 = null;
                String str3 = null;
                int i12 = 0;
                Type[] typeArr3 = null;
                while (i12 < size) {
                    LambdaExp lambdaExp3 = (LambdaExp) lambdaExp2.applyMethods.elementAt(i12);
                    Method[] methodArr = lambdaExp3.primMethods;
                    int length = methodArr.length;
                    int i13 = i12;
                    int i14 = (lambdaExp3.max_args < 0 || lambdaExp3.max_args >= lambdaExp3.min_args + length) ? 1 : 0;
                    if (i11 < 5) {
                        int i15 = i11 - lambdaExp3.min_args;
                        z = i15 < 0 || i15 >= length || (i15 == length + -1 && i14 != 0);
                        i = i15;
                        i2 = 0;
                        length = 1;
                    } else {
                        int i16 = 5 - lambdaExp3.min_args;
                        i = length - 1;
                        boolean z4 = i16 > 0 && length <= i16 && i14 == 0;
                        i2 = i14;
                        z = z4;
                    }
                    if (z) {
                        i4 = i11;
                        i3 = size;
                        classType = classType3;
                        i5 = i13;
                    } else {
                        if (!z3) {
                            if (i11 < 5) {
                                String str4 = "apply" + i11;
                                typeArr2 = new Type[(i11 + 1)];
                                for (int i17 = i11; i17 > 0; i17--) {
                                    typeArr2[i17] = typeObject;
                                }
                                str2 = str4;
                                i10 = 2;
                                i9 = 1;
                            } else {
                                i10 = 2;
                                typeArr2 = new Type[2];
                                i9 = 1;
                                typeArr2[1] = objArrayType;
                                str2 = "applyN";
                            }
                            typeArr2[0] = classType3;
                            Method addMethod = this.curClass.addMethod(str2, typeArr2, defaultCallConvention >= i10 ? Type.voidType : Type.objectType, i9);
                            this.method = addMethod;
                            CodeAttr startCode = addMethod.startCode();
                            startCode.emitLoad(startCode.getArg(i9));
                            startCode.emitGetField(classType3.getField("selector"));
                            codeAttr = startCode;
                            typeArr = typeArr2;
                            str = str2;
                            switchState = startCode.startSwitch();
                            z2 = true;
                        } else {
                            z2 = z3;
                            switchState = switchState4;
                            str = str3;
                            typeArr = typeArr3;
                            codeAttr = codeAttr2;
                        }
                        switchState.addCase(lambdaExp3.getSelectorValue(this), codeAttr);
                        SourceLocator swapSourceLocator = this.messages.swapSourceLocator(lambdaExp3);
                        int lineNumber = lambdaExp3.getLineNumber();
                        if (lineNumber > 0) {
                            codeAttr.putLineNumber(lambdaExp3.getFileName(), lineNumber);
                        }
                        Method method4 = methodArr[i];
                        Type[] parameterTypes = method4.getParameterTypes();
                        int i18 = lambdaExp3.min_args + i;
                        SourceLocator sourceLocator2 = swapSourceLocator;
                        if (i11 <= 4 || length <= 1) {
                            switchState2 = switchState;
                            variable = null;
                        } else {
                            variable = codeAttr.addLocal(Type.intType);
                            switchState2 = switchState;
                            codeAttr.emitLoad(codeAttr.getArg(2));
                            codeAttr.emitArrayLength();
                            if (lambdaExp3.min_args != 0) {
                                codeAttr.emitPushInt(lambdaExp3.min_args);
                                codeAttr.emitSub(Type.intType);
                            }
                            codeAttr.emitStore(variable);
                        }
                        boolean z5 = !method4.getStaticFlag();
                        Method method5 = method4;
                        boolean z6 = i18 + i2 < parameterTypes.length;
                        if ((z5 ? 1 : 0) + (z6 ? 1 : 0) > 0) {
                            codeAttr.emitPushThis();
                            ClassType classType4 = this.curClass;
                            ClassType classType5 = this.moduleClass;
                            if (classType4 == classType5 && this.mainClass != classType5) {
                                codeAttr.emitGetField(this.moduleInstanceMainField);
                            }
                        }
                        Declaration firstDecl = lambdaExp3.firstDecl();
                        if (firstDecl != null && firstDecl.isThisParameter()) {
                            firstDecl = firstDecl.nextDecl();
                        }
                        i3 = size;
                        int i19 = 0;
                        int i20 = 0;
                        while (i19 < i18) {
                            if (variable != null && i19 >= lambdaExp3.min_args) {
                                codeAttr.emitLoad(variable);
                                codeAttr.emitIfIntLEqZero();
                                codeAttr.emitInvoke(methodArr[i19 - lambdaExp3.min_args]);
                                codeAttr.emitElse();
                                i20++;
                                codeAttr.emitInc(variable, -1);
                            }
                            if (i11 <= 4) {
                                variable2 = codeAttr.getArg(i19 + 2);
                                codeAttr.emitLoad(variable2);
                                i7 = i11;
                            } else {
                                i7 = i11;
                                codeAttr.emitLoad(codeAttr.getArg(2));
                                codeAttr.emitPushInt(i19);
                                codeAttr.emitArrayLoad(Type.objectType);
                                variable2 = null;
                            }
                            Type type = firstDecl.getType();
                            ClassType classType6 = classType3;
                            if (type != Type.objectType) {
                                SourceLocator swapSourceLocator2 = this.messages.swapSourceLocator(firstDecl);
                                i8 = i20;
                                CheckedTarget.emitCheckedCoerce(this, lambdaExp3, i19 + 1, type, variable2);
                                this.messages.swapSourceLocator(swapSourceLocator2);
                            } else {
                                i8 = i20;
                            }
                            firstDecl = firstDecl.nextDecl();
                            i19++;
                            i20 = i8;
                            i11 = i7;
                            classType3 = classType6;
                        }
                        int i21 = i11;
                        classType = classType3;
                        if (i2 != 0) {
                            Type type2 = parameterTypes[z6 + i18];
                            if (type2 instanceof ArrayType) {
                                method2 = method5;
                                sourceLocator = sourceLocator2;
                                i5 = i13;
                                switchState3 = switchState2;
                                i6 = i20;
                                i4 = i21;
                                varArgsToArray(lambdaExp3, i18, variable, type2, (Variable) null);
                            } else {
                                int i22 = i18;
                                i6 = i20;
                                i5 = i13;
                                sourceLocator = sourceLocator2;
                                switchState3 = switchState2;
                                method2 = method5;
                                i4 = i21;
                                if ("gnu.lists.LList".equals(type2.getName())) {
                                    codeAttr.emitLoad(codeAttr.getArg(2));
                                    codeAttr.emitPushInt(i22);
                                    codeAttr.emitInvokeStatic(makeListMethod);
                                } else if (type2 == typeCallContext) {
                                    codeAttr.emitLoad(codeAttr.getArg(2));
                                } else {
                                    throw new RuntimeException("unsupported #!rest type:" + type2);
                                }
                            }
                        } else {
                            i6 = i20;
                            i5 = i13;
                            sourceLocator = sourceLocator2;
                            switchState3 = switchState2;
                            method2 = method5;
                            i4 = i21;
                        }
                        codeAttr.emitInvoke(method2);
                        while (true) {
                            i6--;
                            if (i6 < 0) {
                                break;
                            }
                            codeAttr.emitFi();
                        }
                        if (defaultCallConvention < 2) {
                            Target.pushObject.compileFromStack(this, lambdaExp3.getReturnType());
                        }
                        this.messages.swapSourceLocator(sourceLocator);
                        codeAttr.emitReturn();
                        switchState4 = switchState3;
                        codeAttr2 = codeAttr;
                        z3 = z2;
                        str3 = str;
                        typeArr3 = typeArr;
                    }
                    i12 = i5 + 1;
                    lambdaExp2 = lambdaExp;
                    i11 = i4;
                    size = i3;
                    classType3 = classType;
                }
                int i23 = i11;
                int i24 = size;
                ClassType classType7 = classType3;
                if (z3) {
                    switchState4.addDefault(codeAttr2);
                    if (defaultCallConvention >= 2) {
                        codeAttr2.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
                    } else {
                        int i25 = (i23 > 4 ? 2 : i23 + 1) + 1;
                        for (int i26 = 0; i26 < i25; i26++) {
                            codeAttr2.emitLoad(codeAttr2.getArg(i26));
                        }
                        codeAttr2.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(str3, typeArr3));
                    }
                    codeAttr2.emitReturn();
                    switchState4.finish(codeAttr2);
                }
                lambdaExp2 = lambdaExp;
                i11 = i23 + 1;
                size = i24;
                classType3 = classType7;
            }
            this.method = method3;
            this.curClass = classType2;
        }
    }

    private void varArgsToArray(LambdaExp lambdaExp, int i, Variable variable, Type type, Variable variable2) {
        CodeAttr code = getCode();
        Type componentType = ((ArrayType) type).getComponentType();
        boolean z = !"java.lang.Object".equals(componentType.getName());
        if (variable2 != null && !z) {
            code.emitLoad(variable2);
            code.emitPushInt(i);
            code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
        } else if (i != 0 || z) {
            code.pushScope();
            if (variable == null) {
                variable = code.addLocal(Type.intType);
                if (variable2 != null) {
                    code.emitLoad(variable2);
                    code.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
                } else {
                    code.emitLoad(code.getArg(2));
                    code.emitArrayLength();
                }
                if (i != 0) {
                    code.emitPushInt(i);
                    code.emitSub(Type.intType);
                }
                code.emitStore(variable);
            }
            code.emitLoad(variable);
            code.emitNewArray(componentType.getImplementationType());
            Label label = new Label(code);
            Label label2 = new Label(code);
            label2.setTypes(code);
            code.emitGoto(label);
            label2.define(code);
            code.emitDup(1);
            code.emitLoad(variable);
            if (variable2 != null) {
                code.emitLoad(variable2);
            } else {
                code.emitLoad(code.getArg(2));
            }
            code.emitLoad(variable);
            if (i != 0) {
                code.emitPushInt(i);
                code.emitAdd(Type.intType);
            }
            if (variable2 != null) {
                code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
            } else {
                code.emitArrayLoad(Type.objectType);
            }
            if (z) {
                CheckedTarget.emitCheckedCoerce(this, lambdaExp, lambdaExp.getName(), 0, componentType, (Variable) null);
            }
            code.emitArrayStore(componentType);
            label.define(code);
            code.emitInc(variable, -1);
            code.emitLoad(variable);
            code.emitGotoIfIntGeZero(label2);
            code.popScope();
        } else {
            code.emitLoad(code.getArg(2));
        }
    }

    private Method startClassInit() {
        Method declaredMethod;
        Method addMethod = this.curClass.addMethod("<clinit>", apply0args, (Type) Type.voidType, 9);
        this.method = addMethod;
        CodeAttr startCode = addMethod.startCode();
        if ((this.generateMain || generatingApplet() || generatingServlet()) && (declaredMethod = ((ClassType) Type.make(getLanguage().getClass())).getDeclaredMethod("registerEnvironment", 0)) != null) {
            startCode.emitInvokeStatic(declaredMethod);
        }
        return this.method;
    }

    public void process(int i) {
        Compilation saveCurrent = setSaveCurrent(this);
        try {
            ModuleExp module = getModule();
            int i2 = 4;
            if (i >= 4 && getState() < 3) {
                setState(3);
                this.language.parse(this, 0);
                this.lexer.close();
                this.lexer = null;
                if (this.messages.seenErrors()) {
                    i2 = 100;
                }
                setState(i2);
                if (this.pendingImports != null) {
                    restoreCurrent(saveCurrent);
                    return;
                }
            }
            int i3 = 6;
            if (i >= 6) {
                if (getState() < 6) {
                    addMainClass(module);
                    this.language.resolve(this);
                    if (this.messages.seenErrors()) {
                        i3 = 100;
                    }
                    setState(i3);
                }
            }
            if (!this.explicit && !this.immediate && this.minfo.checkCurrent(ModuleManager.getInstance(), System.currentTimeMillis())) {
                this.minfo.cleanupAfterCompilation();
                setState(14);
            }
            int i4 = 8;
            if (i >= 8 && getState() < 8) {
                walkModule(module);
                if (this.messages.seenErrors()) {
                    i4 = 100;
                }
                setState(i4);
            }
            int i5 = 10;
            if (i >= 10 && getState() < 10) {
                this.litTable = new LitTable(this);
                module.setCanRead(true);
                FindCapturedVars.findCapturedVars(module, this);
                module.allocFields(this);
                module.allocChildMethods(this);
                if (this.messages.seenErrors()) {
                    i5 = 100;
                }
                setState(i5);
            }
            int i6 = 12;
            if (i >= 12 && getState() < 12) {
                if (this.immediate) {
                    this.loader = new ArrayClassLoader(ObjectType.getContextClassLoader());
                }
                generateBytecode();
                if (this.messages.seenErrors()) {
                    i6 = 100;
                }
                setState(i6);
            }
            if (i >= 14 && getState() < 14) {
                outputClass(ModuleManager.getInstance().getCompilationDirectory());
                setState(14);
            }
        } catch (SyntaxException e) {
            setState(100);
            if (e.getMessages() != getMessages()) {
                throw new RuntimeException("confussing syntax error: " + e);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            error('f', "caught " + e2);
            setState(100);
        } catch (Throwable th) {
            restoreCurrent(saveCurrent);
            throw th;
        }
        restoreCurrent(saveCurrent);
    }

    /* access modifiers changed from: package-private */
    public void generateBytecode() {
        Type[] typeArr;
        Label label;
        Method method2;
        String str;
        int i;
        ModuleExp module = getModule();
        if (debugPrintFinalExpr) {
            OutPort errDefault = OutPort.errDefault();
            errDefault.println("[Compiling final " + module.getName() + " to " + this.mainClass.getName() + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            module.print(errDefault);
            errDefault.println(']');
            errDefault.flush();
        }
        ClassType moduleType = getModuleType();
        Label label2 = null;
        if (this.mainClass.getSuperclass().isSubtype(moduleType)) {
            this.moduleClass = this.mainClass;
        } else {
            ClassType classType = new ClassType(generateClassName("frame"));
            this.moduleClass = classType;
            classType.setSuper(moduleType);
            addClass(this.moduleClass);
            generateConstructor(this.moduleClass, (LambdaExp) null);
        }
        this.curClass = module.type;
        LambdaExp lambdaExp = this.curLambda;
        this.curLambda = module;
        if (module.isHandlingTailCalls()) {
            typeArr = new Type[]{typeCallContext};
        } else if (module.min_args != module.max_args || module.min_args > 4) {
            typeArr = new Type[]{new ArrayType(typeObject)};
        } else {
            int i2 = module.min_args;
            Type[] typeArr2 = new Type[i2];
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                typeArr2[i2] = typeObject;
            }
            typeArr = typeArr2;
        }
        Variable variable = module.heapFrame;
        boolean isStatic = module.isStatic();
        Method addMethod = this.curClass.addMethod("run", typeArr, (Type) Type.voidType, 17);
        this.method = addMethod;
        addMethod.initCode();
        CodeAttr code = getCode();
        this.thisDecl = this.method.getStaticFlag() ? null : module.declareThis(module.type);
        module.closureEnv = module.thisVariable;
        module.heapFrame = module.isStatic() ? null : module.thisVariable;
        module.allocChildClasses(this);
        if (module.isHandlingTailCalls() || usingCPStyle()) {
            this.callContextVar = new Variable("$ctx", typeCallContext);
            module.getVarScope().addVariableAfter(this.thisDecl, this.callContextVar);
            this.callContextVar.setParameter(true);
        }
        int lineNumber = module.getLineNumber();
        if (lineNumber > 0) {
            code.putLineNumber(module.getFileName(), lineNumber);
        }
        module.allocParameters(this);
        module.enterFunction(this);
        if (usingCPStyle()) {
            loadCallContext();
            code.emitGetField(pcCallContextField);
            SwitchState startSwitch = code.startSwitch();
            this.fswitch = startSwitch;
            startSwitch.addCase(0, code);
        }
        module.compileBody(this);
        module.compileEnd(this);
        if (this.curClass == this.mainClass) {
            Method method3 = this.method;
            Variable variable2 = this.callContextVar;
            this.callContextVar = null;
            method2 = startClassInit();
            this.clinitMethod = method2;
            CodeAttr code2 = getCode();
            Label label3 = new Label(code2);
            label = new Label(code2);
            code2.fixupChain(label, label3);
            if (isStatic) {
                generateConstructor(module);
                code2.emitNew(this.moduleClass);
                code2.emitDup((Type) this.moduleClass);
                code2.emitInvokeSpecial(this.moduleClass.constructor);
                ClassType classType2 = this.moduleClass;
                Field addField = classType2.addField("$instance", classType2, 25);
                this.moduleInstanceMainField = addField;
                code2.emitPutStatic(addField);
            }
            while (true) {
                Initializer initializer = this.clinitChain;
                if (initializer == null) {
                    break;
                }
                this.clinitChain = null;
                dumpInitializers(initializer);
            }
            if (module.staticInitRun()) {
                code2.emitGetStatic(this.moduleInstanceMainField);
                code2.emitInvoke(typeModuleBody.getDeclaredMethod("run", 0));
            }
            code2.emitReturn();
            if (this.moduleClass != this.mainClass && !isStatic && !this.generateMain && !this.immediate) {
                Method addMethod2 = this.curClass.addMethod("run", 1, Type.typeArray0, (Type) Type.voidType);
                this.method = addMethod2;
                CodeAttr startCode = addMethod2.startCode();
                Variable addLocal = startCode.addLocal(typeCallContext);
                Variable addLocal2 = startCode.addLocal(typeConsumer);
                Variable addLocal3 = startCode.addLocal(Type.javalangThrowableType);
                startCode.emitInvokeStatic(getCallContextInstanceMethod);
                startCode.emitStore(addLocal);
                Field declaredField = typeCallContext.getDeclaredField("consumer");
                startCode.emitLoad(addLocal);
                startCode.emitGetField(declaredField);
                startCode.emitStore(addLocal2);
                startCode.emitLoad(addLocal);
                startCode.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
                startCode.emitPutField(declaredField);
                startCode.emitTryStart(false, Type.voidType);
                startCode.emitPushThis();
                startCode.emitLoad(addLocal);
                startCode.emitInvokeVirtual(method3);
                startCode.emitPushNull();
                startCode.emitStore(addLocal3);
                startCode.emitTryEnd();
                startCode.emitCatchStart(addLocal3);
                startCode.emitCatchEnd();
                startCode.emitTryCatchEnd();
                startCode.emitLoad(addLocal);
                startCode.emitLoad(addLocal3);
                startCode.emitLoad(addLocal2);
                startCode.emitInvokeStatic(typeModuleBody.getDeclaredMethod("runCleanup", 3));
                startCode.emitReturn();
            }
            this.method = method3;
            this.callContextVar = variable2;
            label2 = label3;
        } else {
            method2 = null;
            label = null;
        }
        module.generateApplyMethods(this);
        this.curLambda = lambdaExp;
        module.heapFrame = variable;
        if (usingCPStyle()) {
            this.fswitch.finish(getCode());
        }
        if (!(label2 == null && this.callContextVar == null)) {
            this.method = method2;
            CodeAttr code3 = getCode();
            Label label4 = new Label(code3);
            code3.fixupChain(label2, label4);
            if (this.callContextVarForInit != null) {
                code3.emitInvokeStatic(getCallContextInstanceMethod);
                code3.emitStore(this.callContextVarForInit);
            }
            try {
                if (this.immediate) {
                    code3.emitPushInt(registerForImmediateLiterals(this));
                    code3.emitInvokeStatic(ClassType.make("gnu.expr.Compilation").getDeclaredMethod("setupLiterals", 1));
                } else {
                    this.litTable.emit();
                }
            } catch (Throwable th) {
                error('e', "Literals: Internal error:" + th);
            }
            code3.fixupChain(label4, label);
        }
        if (this.generateMain && this.curClass == this.mainClass) {
            Method addMethod3 = this.curClass.addMethod("main", 9, new Type[]{new ArrayType(javaStringType)}, (Type) Type.voidType);
            this.method = addMethod3;
            CodeAttr startCode2 = addMethod3.startCode();
            if (Shell.defaultFormatName != null) {
                startCode2.emitPushString(Shell.defaultFormatName);
                i = 1;
                startCode2.emitInvokeStatic(ClassType.make("kawa.Shell").getDeclaredMethod("setDefaultFormat", 1));
            } else {
                i = 1;
            }
            startCode2.emitLoad(startCode2.getArg(0));
            startCode2.emitInvokeStatic(ClassType.make("gnu.expr.ApplicationMainSupport").getDeclaredMethod("processArgs", i));
            Field field = this.moduleInstanceMainField;
            if (field != null) {
                startCode2.emitGetStatic(field);
            } else {
                startCode2.emitNew(this.curClass);
                startCode2.emitDup((Type) this.curClass);
                startCode2.emitInvokeSpecial(this.curClass.constructor);
            }
            startCode2.emitInvokeVirtual(typeModuleBody.getDeclaredMethod("runAsMain", 0));
            startCode2.emitReturn();
        }
        ModuleInfo moduleInfo = this.minfo;
        if (moduleInfo != null && moduleInfo.getNamespaceUri() != null) {
            ModuleManager instance = ModuleManager.getInstance();
            String name = this.mainClass.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf < 0) {
                str = "";
            } else {
                String substring = name.substring(0, lastIndexOf);
                try {
                    instance.loadPackageInfo(substring);
                } catch (ClassNotFoundException unused) {
                } catch (Throwable th2) {
                    error('e', "error loading map for " + substring + " - " + th2);
                }
                str = name.substring(0, lastIndexOf + 1);
            }
            ClassType classType3 = new ClassType(str + ModuleSet.MODULES_MAP);
            ClassType make = ClassType.make("gnu.expr.ModuleSet");
            classType3.setSuper(make);
            registerClass(classType3);
            this.method = classType3.addMethod("<init>", 1, apply0args, (Type) Type.voidType);
            Method addMethod4 = make.addMethod("<init>", 1, apply0args, (Type) Type.voidType);
            CodeAttr startCode3 = this.method.startCode();
            startCode3.emitPushThis();
            startCode3.emitInvokeSpecial(addMethod4);
            startCode3.emitReturn();
            ClassType make2 = ClassType.make("gnu.expr.ModuleManager");
            Method addMethod5 = classType3.addMethod("register", new Type[]{make2}, (Type) Type.voidType, 1);
            this.method = addMethod5;
            CodeAttr startCode4 = addMethod5.startCode();
            Method declaredMethod = make2.getDeclaredMethod("register", 3);
            int i3 = instance.numModules;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    ModuleInfo moduleInfo2 = instance.modules[i3];
                    String className = moduleInfo2.getClassName();
                    if (className != null && className.startsWith(str)) {
                        String str2 = moduleInfo2.sourcePath;
                        String namespaceUri = moduleInfo2.getNamespaceUri();
                        startCode4.emitLoad(startCode4.getArg(1));
                        compileConstant(className);
                        if (!Path.valueOf(str2).isAbsolute()) {
                            try {
                                char c = File.separatorChar;
                                String url = Path.toURL(instance.getCompilationDirectory() + str.replace('.', c)).toString();
                                int length = url.length();
                                if (length > 0 && url.charAt(length - 1) != c) {
                                    url = url + c;
                                }
                                str2 = Path.relativize(moduleInfo2.getSourceAbsPathname(), url);
                            } catch (Throwable th3) {
                                throw new WrappedException("exception while fixing up '" + str2 + '\'', th3);
                            }
                        }
                        compileConstant(str2);
                        compileConstant(namespaceUri);
                        startCode4.emitInvokeVirtual(declaredMethod);
                    }
                } else {
                    startCode4.emitReturn();
                    return;
                }
            }
        }
    }

    public Field allocLocalField(Type type, String str) {
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("tmp_");
            int i = this.localFieldIndex + 1;
            this.localFieldIndex = i;
            sb.append(i);
            str = sb.toString();
        }
        return this.curClass.addField(str, type, 0);
    }

    public final void loadCallContext() {
        CodeAttr code = getCode();
        Variable variable = this.callContextVar;
        if (variable != null && !variable.dead()) {
            code.emitLoad(this.callContextVar);
        } else if (this.method == this.clinitMethod) {
            Variable variable2 = new Variable("$ctx", typeCallContext);
            this.callContextVar = variable2;
            variable2.reserveLocal(code.getMaxLocals(), code);
            code.emitLoad(this.callContextVar);
            this.callContextVarForInit = this.callContextVar;
        } else {
            code.emitInvokeStatic(getCallContextInstanceMethod);
            code.emitDup();
            this.callContextVar = new Variable("$ctx", typeCallContext);
            code.getCurrentScope().addVariable(code, this.callContextVar);
            code.emitStore(this.callContextVar);
        }
    }

    public Expression parse(Object obj) {
        throw new Error("unimeplemented parse");
    }

    public Language getLanguage() {
        return this.language;
    }

    public LambdaExp currentLambda() {
        return this.current_scope.currentLambda();
    }

    public final ModuleExp getModule() {
        return this.mainLambda;
    }

    public void setModule(ModuleExp moduleExp) {
        this.mainLambda = moduleExp;
    }

    public boolean isStatic() {
        return this.mainLambda.isStatic();
    }

    public ModuleExp currentModule() {
        return this.current_scope.currentModule();
    }

    public void mustCompileHere() {
        if (this.mustCompile || ModuleExp.compilerAvailable) {
            this.mustCompile = true;
        } else {
            error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
        }
    }

    public ScopeExp currentScope() {
        return this.current_scope;
    }

    public void setCurrentScope(ScopeExp scopeExp) {
        int nesting = ScopeExp.nesting(scopeExp);
        int nesting2 = ScopeExp.nesting(this.current_scope);
        while (nesting2 > nesting) {
            pop(this.current_scope);
            nesting2--;
        }
        ScopeExp scopeExp2 = scopeExp;
        while (nesting > nesting2) {
            scopeExp2 = scopeExp2.outer;
            nesting--;
        }
        while (true) {
            ScopeExp scopeExp3 = this.current_scope;
            if (scopeExp2 != scopeExp3) {
                pop(scopeExp3);
                scopeExp2 = scopeExp2.outer;
            } else {
                pushChain(scopeExp, scopeExp2);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void pushChain(ScopeExp scopeExp, ScopeExp scopeExp2) {
        if (scopeExp != scopeExp2) {
            pushChain(scopeExp.outer, scopeExp2);
            pushScope(scopeExp);
            this.lexical.push(scopeExp);
        }
    }

    public ModuleExp pushNewModule(Lexer lexer2) {
        this.lexer = lexer2;
        return pushNewModule(lexer2.getName());
    }

    public ModuleExp pushNewModule(String str) {
        ModuleExp moduleExp = new ModuleExp();
        if (str != null) {
            moduleExp.setFile(str);
        }
        if (generatingApplet() || generatingServlet()) {
            moduleExp.setFlag(131072);
        }
        if (this.immediate) {
            moduleExp.setFlag(1048576);
            new ModuleInfo().setCompilation(this);
        }
        this.mainLambda = moduleExp;
        push((ScopeExp) moduleExp);
        return moduleExp;
    }

    public void push(ScopeExp scopeExp) {
        pushScope(scopeExp);
        this.lexical.push(scopeExp);
    }

    public final void pushScope(ScopeExp scopeExp) {
        if (!this.mustCompile && (scopeExp.mustCompile() || (ModuleExp.compilerAvailable && (scopeExp instanceof LambdaExp) && !(scopeExp instanceof ModuleExp)))) {
            mustCompileHere();
        }
        scopeExp.outer = this.current_scope;
        this.current_scope = scopeExp;
    }

    public void pop(ScopeExp scopeExp) {
        this.lexical.pop(scopeExp);
        this.current_scope = scopeExp.outer;
    }

    public final void pop() {
        pop(this.current_scope);
    }

    public void push(Declaration declaration) {
        this.lexical.push(declaration);
    }

    public Declaration lookup(Object obj, int i) {
        return this.lexical.lookup(obj, i);
    }

    public void usedClass(Type type) {
        while (type instanceof ArrayType) {
            type = ((ArrayType) type).getComponentType();
        }
        if (this.immediate && (type instanceof ClassType)) {
            this.loader.addClass((ClassType) type);
        }
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages sourceMessages) {
        this.messages = sourceMessages;
    }

    public void error(char c, String str, SourceLocator sourceLocator) {
        String fileName = sourceLocator.getFileName();
        int lineNumber = sourceLocator.getLineNumber();
        int columnNumber = sourceLocator.getColumnNumber();
        if (fileName == null || lineNumber <= 0) {
            fileName = getFileName();
            lineNumber = getLineNumber();
            columnNumber = getColumnNumber();
        }
        this.messages.error((c != 'w' || !warnAsError()) ? c : 'e', fileName, lineNumber, columnNumber, str);
    }

    public void error(char c, String str) {
        if (c == 'w' && warnAsError()) {
            c = 'e';
        }
        this.messages.error(c, (SourceLocator) this, str);
    }

    public void error(char c, Declaration declaration, String str, String str2) {
        error(c, str + declaration.getName() + str2, (String) null, declaration);
    }

    public void error(char c, String str, String str2, Declaration declaration) {
        int i;
        String str3;
        char c2 = (c != 'w' || !warnAsError()) ? c : 'e';
        String fileName = getFileName();
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        int lineNumber2 = declaration.getLineNumber();
        if (lineNumber2 > 0) {
            str3 = declaration.getFileName();
            i = declaration.getColumnNumber();
        } else {
            lineNumber2 = lineNumber;
            i = columnNumber;
            str3 = fileName;
        }
        this.messages.error(c2, str3, lineNumber2, i, str, str2);
    }

    public Expression syntaxError(String str) {
        error('e', str);
        return new ErrorExp(str);
    }

    public final int getLineNumber() {
        return this.messages.getLineNumber();
    }

    public final int getColumnNumber() {
        return this.messages.getColumnNumber();
    }

    public final String getFileName() {
        return this.messages.getFileName();
    }

    public String getPublicId() {
        return this.messages.getPublicId();
    }

    public String getSystemId() {
        return this.messages.getSystemId();
    }

    public void setFile(String str) {
        this.messages.setFile(str);
    }

    public void setLine(int i) {
        this.messages.setLine(i);
    }

    public void setColumn(int i) {
        this.messages.setColumn(i);
    }

    public final void setLine(Expression expression) {
        this.messages.setLocation(expression);
    }

    public void setLine(Object obj) {
        if (obj instanceof SourceLocator) {
            this.messages.setLocation((SourceLocator) obj);
        }
    }

    public final void setLocation(SourceLocator sourceLocator) {
        this.messages.setLocation(sourceLocator);
    }

    public void setLine(String str, int i, int i2) {
        this.messages.setLine(str, i, i2);
    }

    public void letStart() {
        pushScope(new LetExp((Expression[]) null));
    }

    public Declaration letVariable(Object obj, Type type, Expression expression) {
        Declaration addDeclaration = ((LetExp) this.current_scope).addDeclaration(obj, type);
        addDeclaration.noteValue(expression);
        return addDeclaration;
    }

    public void letEnter() {
        LetExp letExp = (LetExp) this.current_scope;
        Expression[] expressionArr = new Expression[letExp.countDecls()];
        Declaration firstDecl = letExp.firstDecl();
        int i = 0;
        while (firstDecl != null) {
            expressionArr[i] = firstDecl.getValue();
            firstDecl = firstDecl.nextDecl();
            i++;
        }
        letExp.inits = expressionArr;
        this.lexical.push((ScopeExp) letExp);
    }

    public LetExp letDone(Expression expression) {
        LetExp letExp = (LetExp) this.current_scope;
        letExp.body = expression;
        pop(letExp);
        return letExp;
    }

    private void checkLoop() {
        if (((LambdaExp) this.current_scope).getName() != "%do%loop") {
            throw new Error("internal error - bad loop state");
        }
    }

    public void loopStart() {
        LambdaExp lambdaExp = new LambdaExp();
        LetExp letExp = new LetExp(new Expression[]{lambdaExp});
        letExp.addDeclaration((Object) "%do%loop").noteValue(lambdaExp);
        lambdaExp.setName("%do%loop");
        letExp.outer = this.current_scope;
        lambdaExp.outer = letExp;
        this.current_scope = lambdaExp;
    }

    public Declaration loopVariable(Object obj, Type type, Expression expression) {
        checkLoop();
        LambdaExp lambdaExp = (LambdaExp) this.current_scope;
        Declaration addDeclaration = lambdaExp.addDeclaration(obj, type);
        if (this.exprStack == null) {
            this.exprStack = new Stack<>();
        }
        this.exprStack.push(expression);
        lambdaExp.min_args++;
        return addDeclaration;
    }

    public void loopEnter() {
        checkLoop();
        LambdaExp lambdaExp = (LambdaExp) this.current_scope;
        int i = lambdaExp.min_args;
        lambdaExp.max_args = i;
        Expression[] expressionArr = new Expression[i];
        while (true) {
            i--;
            if (i >= 0) {
                expressionArr[i] = this.exprStack.pop();
            } else {
                LetExp letExp = (LetExp) lambdaExp.outer;
                letExp.setBody(new ApplyExp((Expression) new ReferenceExp(letExp.firstDecl()), expressionArr));
                this.lexical.push((ScopeExp) lambdaExp);
                return;
            }
        }
    }

    public void loopCond(Expression expression) {
        checkLoop();
        this.exprStack.push(expression);
    }

    public void loopBody(Expression expression) {
        ((LambdaExp) this.current_scope).body = expression;
    }

    public Expression loopRepeat(Expression[] expressionArr) {
        LambdaExp lambdaExp = (LambdaExp) this.current_scope;
        ScopeExp scopeExp = lambdaExp.outer;
        lambdaExp.body = new IfExp(this.exprStack.pop(), new BeginExp(lambdaExp.body, new ApplyExp((Expression) new ReferenceExp(scopeExp.firstDecl()), expressionArr)), QuoteExp.voidExp);
        this.lexical.pop((ScopeExp) lambdaExp);
        this.current_scope = scopeExp.outer;
        return scopeExp;
    }

    public Expression loopRepeat() {
        return loopRepeat(Expression.noExpressions);
    }

    public Expression loopRepeat(Expression expression) {
        return loopRepeat(new Expression[]{expression});
    }

    public static ApplyExp makeCoercion(Expression expression, Expression expression2) {
        return new ApplyExp((Expression) new QuoteExp(Convert.getInstance()), expression2, expression);
    }

    public static Expression makeCoercion(Expression expression, Type type) {
        return makeCoercion(expression, (Expression) new QuoteExp(type));
    }

    public void loadClassRef(ObjectType objectType) {
        Field field;
        CodeAttr code = getCode();
        if (this.curClass.getClassfileVersion() >= 3211264) {
            code.emitPushClass(objectType);
        } else if (objectType != this.mainClass || !this.mainLambda.isStatic() || (field = this.moduleInstanceMainField) == null) {
            code.emitPushString(objectType instanceof ClassType ? objectType.getName() : objectType.getInternalName().replace('/', '.'));
            code.emitInvokeStatic(getForNameHelper());
        } else {
            code.emitGetStatic(field);
            code.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
        }
    }

    public Method getForNameHelper() {
        if (this.forNameHelper == null) {
            Method method2 = this.method;
            Method addMethod = this.curClass.addMethod("class$", 9, string1Arg, (Type) typeClass);
            this.method = addMethod;
            this.forNameHelper = addMethod;
            CodeAttr startCode = addMethod.startCode();
            startCode.emitLoad(startCode.getArg(0));
            startCode.emitPushInt(0);
            startCode.emitPushString(this.mainClass.getName());
            startCode.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
            startCode.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
            startCode.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
            startCode.emitReturn();
            this.method = method2;
        }
        return this.forNameHelper;
    }

    public Object resolve(Object obj, boolean z) {
        Symbol symbol;
        Environment current2 = Environment.getCurrent();
        if (obj instanceof String) {
            symbol = current2.defaultNamespace().lookup((String) obj);
        } else {
            symbol = (Symbol) obj;
        }
        if (symbol == null) {
            return null;
        }
        if (!z || !getLanguage().hasSeparateFunctionNamespace()) {
            return current2.get((EnvironmentKey) symbol, (Object) null);
        }
        return current2.getFunction(symbol, (Object) null);
    }

    public static void setupLiterals(int i) {
        Compilation findForImmediateLiterals = findForImmediateLiterals(i);
        try {
            Class loadClass = findForImmediateLiterals.loader.loadClass(findForImmediateLiterals.mainClass.getName());
            for (Literal literal = findForImmediateLiterals.litTable.literalsChain; literal != null; literal = literal.next) {
                loadClass.getDeclaredField(literal.field.getName()).set((Object) null, literal.value);
            }
            findForImmediateLiterals.litTable = null;
        } catch (Throwable th) {
            throw new WrappedException("internal error", th);
        }
    }

    public static synchronized int registerForImmediateLiterals(Compilation compilation) {
        int i;
        synchronized (Compilation.class) {
            i = 0;
            for (Compilation compilation2 = chainUninitialized; compilation2 != null; compilation2 = compilation2.nextUninitialized) {
                int i2 = compilation2.keyUninitialized;
                if (i <= i2) {
                    i = i2 + 1;
                }
            }
            compilation.keyUninitialized = i;
            compilation.nextUninitialized = chainUninitialized;
            chainUninitialized = compilation;
        }
        return i;
    }

    public static synchronized Compilation findForImmediateLiterals(int i) {
        Compilation compilation;
        Compilation compilation2;
        synchronized (Compilation.class) {
            compilation = chainUninitialized;
            Compilation compilation3 = null;
            while (true) {
                compilation2 = compilation.nextUninitialized;
                if (compilation.keyUninitialized == i) {
                    break;
                }
                compilation3 = compilation;
                compilation = compilation2;
            }
            if (compilation3 == null) {
                chainUninitialized = compilation2;
            } else {
                compilation3.nextUninitialized = compilation2;
            }
            compilation.nextUninitialized = null;
        }
        return compilation;
    }

    public static Compilation getCurrent() {
        return current.get();
    }

    public static void setCurrent(Compilation compilation) {
        current.set(compilation);
    }

    public static Compilation setSaveCurrent(Compilation compilation) {
        ThreadLocal<Compilation> threadLocal = current;
        Compilation compilation2 = threadLocal.get();
        threadLocal.set(compilation);
        return compilation2;
    }

    public static void restoreCurrent(Compilation compilation) {
        current.set(compilation);
    }

    public String toString() {
        return "<compilation " + this.mainLambda + ">";
    }
}
