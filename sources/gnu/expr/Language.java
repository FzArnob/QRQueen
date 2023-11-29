package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.AbstractFormat;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.Convert;
import gnu.lists.FString;
import gnu.lists.PrintConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import kawa.repl;

public abstract class Language {
    public static final int FUNCTION_NAMESPACE = 2;
    public static final int NAMESPACE_PREFIX_NAMESPACE = 4;
    public static final int PARSE_CURRENT_NAMES = 2;
    public static final int PARSE_EXPLICIT = 64;
    public static final int PARSE_FOR_APPLET = 16;
    public static final int PARSE_FOR_EVAL = 3;
    public static final int PARSE_FOR_SERVLET = 32;
    public static final int PARSE_IMMEDIATE = 1;
    public static final int PARSE_ONE_LINE = 4;
    public static final int PARSE_PROLOG = 8;
    public static final int VALUE_NAMESPACE = 1;
    protected static final InheritableThreadLocal<Language> current = new InheritableThreadLocal<>();
    static int envCounter;
    protected static int env_counter = 0;
    protected static Language global;
    static String[][] languages = {new String[]{"scheme", ".scm", ".sc", "kawa.standard.Scheme"}, new String[]{"krl", ".krl", "gnu.kawa.brl.BRL"}, new String[]{"brl", ".brl", "gnu.kawa.brl.BRL"}, new String[]{"emacs", "elisp", "emacs-lisp", ".el", "gnu.jemacs.lang.ELisp"}, new String[]{"xquery", ".xquery", ".xq", ".xql", "gnu.xquery.lang.XQuery"}, new String[]{"q2", ".q2", "gnu.q2.lang.Q2"}, new String[]{"xslt", "xsl", ".xsl", "gnu.kawa.xslt.XSLT"}, new String[]{"commonlisp", "common-lisp", "clisp", "lisp", ".lisp", ".lsp", ".cl", "gnu.commonlisp.lang.CommonLisp"}};
    public static boolean requirePedantic;
    protected Environment environ;
    protected Environment userEnv;

    public AbstractFormat getFormat(boolean z) {
        return null;
    }

    public abstract Lexer getLexer(InPort inPort, SourceMessages sourceMessages);

    public int getNamespaceOf(Declaration declaration) {
        return 1;
    }

    public boolean hasSeparateFunctionNamespace() {
        return false;
    }

    public abstract boolean parse(Compilation compilation, int i) throws IOException, SyntaxException;

    public void resolve(Compilation compilation) {
    }

    static {
        Environment.setGlobal(BuiltinEnvironment.getInstance());
    }

    public static Language getDefaultLanguage() {
        Language language = (Language) current.get();
        return language != null ? language : global;
    }

    public static void setCurrentLanguage(Language language) {
        current.set(language);
    }

    public static Language setSaveCurrent(Language language) {
        InheritableThreadLocal<Language> inheritableThreadLocal = current;
        Language language2 = (Language) inheritableThreadLocal.get();
        inheritableThreadLocal.set(language);
        return language2;
    }

    public static void restoreCurrent(Language language) {
        current.set(language);
    }

    public static String[][] getLanguages() {
        return languages;
    }

    public static void registerLanguage(String[] strArr) {
        String[][] strArr2 = languages;
        int length = strArr2.length + 1;
        String[][] strArr3 = new String[length][];
        System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
        strArr3[length - 1] = strArr;
        languages = strArr3;
    }

    public static Language detect(InputStream inputStream) throws IOException {
        int read;
        if (!inputStream.markSupported()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        inputStream.mark(200);
        while (stringBuffer.length() < 200 && (read = inputStream.read()) >= 0 && read != 10 && read != 13) {
            stringBuffer.append((char) read);
        }
        inputStream.reset();
        return detect(stringBuffer.toString());
    }

    public static Language detect(InPort inPort) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        inPort.mark(300);
        inPort.readLine(stringBuffer, 'P');
        inPort.reset();
        return detect(stringBuffer.toString());
    }

    public static Language detect(String str) {
        Language instance;
        String trim = str.trim();
        int indexOf = trim.indexOf("kawa:");
        if (indexOf >= 0) {
            int i = indexOf + 5;
            int i2 = i;
            while (i2 < trim.length() && Character.isJavaIdentifierPart(trim.charAt(i2))) {
                i2++;
            }
            if (i2 > i && (instance = getInstance(trim.substring(i, i2))) != null) {
                return instance;
            }
        }
        if (trim.indexOf("-*- scheme -*-") >= 0) {
            return getInstance("scheme");
        }
        if (trim.indexOf("-*- xquery -*-") >= 0) {
            return getInstance("xquery");
        }
        if (trim.indexOf("-*- emacs-lisp -*-") >= 0) {
            return getInstance("elisp");
        }
        if (trim.indexOf("-*- common-lisp -*-") >= 0 || trim.indexOf("-*- lisp -*-") >= 0) {
            return getInstance("common-lisp");
        }
        if ((trim.charAt(0) == '(' && trim.charAt(1) == ':') || (trim.length() >= 7 && trim.substring(0, 7).equals("xquery "))) {
            return getInstance("xquery");
        }
        if (trim.charAt(0) == ';' && trim.charAt(1) == ';') {
            return getInstance("scheme");
        }
        return null;
    }

    public static Language getInstanceFromFilenameExtension(String str) {
        Language instance;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0 || (instance = getInstance(str.substring(lastIndexOf))) == null) {
            return null;
        }
        return instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0013 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Language getInstance(java.lang.String r7) {
        /*
            java.lang.String[][] r0 = languages
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r2 >= r0) goto L_0x002d
            java.lang.String[][] r3 = languages
            r3 = r3[r2]
            int r4 = r3.length
            int r4 = r4 + -1
            r5 = r4
        L_0x000f:
            int r5 = r5 + -1
            if (r5 < 0) goto L_0x002a
            if (r7 == 0) goto L_0x001d
            r6 = r3[r5]
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x000f
        L_0x001d:
            r4 = r3[r4]     // Catch:{ ClassNotFoundException -> 0x002a }
            java.lang.Class r7 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x002a }
            r0 = r3[r1]
            gnu.expr.Language r7 = getInstance(r0, r7)
            return r7
        L_0x002a:
            int r2 = r2 + 1
            goto L_0x0005
        L_0x002d:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Language.getInstance(java.lang.String):gnu.expr.Language");
    }

    protected Language() {
        Convert.setInstance(KawaConvert.getInstance());
    }

    public static Language getInstance(String str, Class cls) {
        Method method;
        try {
            Class[] clsArr = new Class[0];
            try {
                method = cls.getDeclaredMethod("get" + (Character.toTitleCase(str.charAt(0)) + str.substring(1).toLowerCase()) + "Instance", clsArr);
            } catch (Exception unused) {
                method = cls.getDeclaredMethod("getInstance", clsArr);
            }
            return (Language) method.invoke((Object) null, Values.noArgs);
        } catch (Exception e) {
            e = e;
            String name = cls.getName();
            if (e instanceof InvocationTargetException) {
                e = ((InvocationTargetException) e).getTargetException();
            }
            throw new WrappedException("getInstance for '" + name + "' failed", e);
        }
    }

    public boolean isTrue(Object obj) {
        return obj != Boolean.FALSE;
    }

    public Object booleanObject(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public Object noValue() {
        return Values.empty;
    }

    public final Environment getEnvironment() {
        Environment environment = this.userEnv;
        return environment != null ? environment : Environment.getCurrent();
    }

    public final Environment getNewEnvironment() {
        StringBuilder sb = new StringBuilder();
        sb.append("environment-");
        int i = envCounter + 1;
        envCounter = i;
        sb.append(i);
        return Environment.make(sb.toString(), this.environ);
    }

    public Environment getLangEnvironment() {
        return this.environ;
    }

    public NamedLocation lookupBuiltin(Symbol symbol, Object obj, int i) {
        Environment environment = this.environ;
        if (environment == null) {
            return null;
        }
        return environment.lookup(symbol, obj, i);
    }

    public void define(String str, Object obj) {
        this.environ.define(getSymbol(str), (Object) null, obj);
    }

    /* access modifiers changed from: protected */
    public void defAliasStFld(String str, String str2, String str3) {
        StaticFieldLocation.define(this.environ, getSymbol(str), (Object) null, str2, str3);
    }

    /* access modifiers changed from: protected */
    public void defProcStFld(String str, String str2, String str3) {
        StaticFieldLocation.define(this.environ, getSymbol(str), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, str2, str3).setProcedure();
    }

    /* access modifiers changed from: protected */
    public void defProcStFld(String str, String str2) {
        defProcStFld(str, str2, Compilation.mangleNameIfNeeded(str));
    }

    public final void defineFunction(Named named) {
        Object symbol = named.getSymbol();
        this.environ.define(symbol instanceof Symbol ? (Symbol) symbol : getSymbol(symbol.toString()), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, named);
    }

    public void defineFunction(String str, Object obj) {
        this.environ.define(getSymbol(str), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, obj);
    }

    public Object getEnvPropertyFor(Field field, Object obj) {
        if (hasSeparateFunctionNamespace() && Compilation.typeProcedure.getReflectClass().isAssignableFrom(field.getType())) {
            return EnvironmentKey.FUNCTION;
        }
        return null;
    }

    public Object getEnvPropertyFor(Declaration declaration) {
        if (!hasSeparateFunctionNamespace() || !declaration.isProcedureDecl()) {
            return null;
        }
        return EnvironmentKey.FUNCTION;
    }

    public void loadClass(String str) throws ClassNotFoundException {
        try {
            try {
                Object newInstance = Class.forName(str).newInstance();
                ClassMemberLocation.defineAll(newInstance, this, Environment.getCurrent());
                if (newInstance instanceof ModuleBody) {
                    ((ModuleBody) newInstance).run();
                }
            } catch (Exception e) {
                throw new WrappedException("cannot load " + str, e);
            }
        } catch (ClassNotFoundException e2) {
            throw e2;
        }
    }

    public Symbol getSymbol(String str) {
        return this.environ.getSymbol(str);
    }

    public Object lookup(String str) {
        return this.environ.get((Object) str);
    }

    public Consumer getOutputConsumer(Writer writer) {
        OutPort outPort = writer instanceof OutPort ? (OutPort) writer : new OutPort(writer);
        outPort.objectFormat = getFormat(false);
        return outPort;
    }

    public String getName() {
        String name = getClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf >= 0 ? name.substring(lastIndexOf + 1) : name;
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages sourceMessages, NameLookup nameLookup) {
        return new Compilation(this, sourceMessages, nameLookup);
    }

    public final Compilation parse(InPort inPort, SourceMessages sourceMessages, int i) throws IOException, SyntaxException {
        return parse(getLexer(inPort, sourceMessages), i, (ModuleInfo) null);
    }

    public final Compilation parse(InPort inPort, SourceMessages sourceMessages, ModuleInfo moduleInfo) throws IOException, SyntaxException {
        return parse(getLexer(inPort, sourceMessages), 8, moduleInfo);
    }

    public final Compilation parse(InPort inPort, SourceMessages sourceMessages, int i, ModuleInfo moduleInfo) throws IOException, SyntaxException {
        return parse(getLexer(inPort, sourceMessages), i, moduleInfo);
    }

    public final Compilation parse(Lexer lexer, int i, ModuleInfo moduleInfo) throws IOException, SyntaxException {
        SourceMessages messages = lexer.getMessages();
        NameLookup instance = (i & 2) != 0 ? NameLookup.getInstance(getEnvironment(), this) : new NameLookup(this);
        boolean z = (i & 1) != 0;
        Compilation compilation = getCompilation(lexer, messages, instance);
        if (requirePedantic) {
            compilation.pedantic = true;
        }
        if (!z) {
            compilation.mustCompile = true;
        }
        compilation.immediate = z;
        compilation.langOptions = i;
        if ((i & 64) != 0) {
            compilation.explicit = true;
        }
        if ((i & 8) != 0) {
            compilation.setState(1);
        }
        compilation.pushNewModule(lexer);
        if (moduleInfo != null) {
            moduleInfo.setCompilation(compilation);
        }
        if (!parse(compilation, i)) {
            return null;
        }
        if (compilation.getState() == 1) {
            compilation.setState(2);
        }
        return compilation;
    }

    public Type getTypeFor(Class cls) {
        return Type.make(cls);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r2.getReflectClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final gnu.bytecode.Type getLangTypeFor(gnu.bytecode.Type r2) {
        /*
            r1 = this;
            boolean r0 = r2.isExisting()
            if (r0 == 0) goto L_0x0010
            java.lang.Class r0 = r2.getReflectClass()
            if (r0 == 0) goto L_0x0010
            gnu.bytecode.Type r2 = r1.getTypeFor((java.lang.Class) r0)
        L_0x0010:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Language.getLangTypeFor(gnu.bytecode.Type):gnu.bytecode.Type");
    }

    public String formatType(Type type) {
        return type.getName();
    }

    public static Type string2Type(String str) {
        if (str.endsWith("[]")) {
            Type string2Type = string2Type(str.substring(0, str.length() - 2));
            if (string2Type == null) {
                return null;
            }
            return ArrayType.make(string2Type);
        } else if (Type.isValidJavaTypeName(str)) {
            return Type.getType(str);
        } else {
            return null;
        }
    }

    public Type getTypeFor(String str) {
        return string2Type(str);
    }

    public final Type getTypeFor(Object obj, boolean z) {
        String name;
        if (obj instanceof Type) {
            return (Type) obj;
        }
        if (obj instanceof Class) {
            return getTypeFor((Class) obj);
        }
        if (z && ((obj instanceof FString) || (obj instanceof String) || (((obj instanceof Symbol) && ((Symbol) obj).hasEmptyNamespace()) || (obj instanceof CharSeq)))) {
            return getTypeFor(obj.toString());
        }
        if (!(obj instanceof Namespace) || (name = ((Namespace) obj).getName()) == null || !name.startsWith("class:")) {
            return null;
        }
        return getLangTypeFor(string2Type(name.substring(6)));
    }

    public final Type asType(Object obj) {
        Type typeFor = getTypeFor(obj, true);
        return typeFor == null ? (Type) obj : typeFor;
    }

    public final Type getTypeFor(Expression expression) {
        return getTypeFor(expression, true);
    }

    public Type getTypeFor(Expression expression, boolean z) {
        if (expression instanceof QuoteExp) {
            Object value = ((QuoteExp) expression).getValue();
            if (value instanceof Type) {
                return (Type) value;
            }
            if (value instanceof Class) {
                return Type.make((Class) value);
            }
            return getTypeFor(value, z);
        }
        if (expression instanceof ReferenceExp) {
            ReferenceExp referenceExp = (ReferenceExp) expression;
            Declaration followAliases = Declaration.followAliases(referenceExp.getBinding());
            String name = referenceExp.getName();
            if (followAliases != null) {
                Expression value2 = followAliases.getValue();
                boolean z2 = value2 instanceof QuoteExp;
                if (z2 && followAliases.getFlag(16384) && !followAliases.isIndirectBinding()) {
                    return getTypeFor(((QuoteExp) value2).getValue(), z);
                }
                if ((value2 instanceof ClassExp) || (value2 instanceof ModuleExp)) {
                    followAliases.setCanRead(true);
                    return ((LambdaExp) value2).getClassType();
                } else if (followAliases.isAlias() && z2) {
                    Object value3 = ((QuoteExp) value2).getValue();
                    if (value3 instanceof Location) {
                        Location location = (Location) value3;
                        if (location.isBound()) {
                            return getTypeFor(location.get(), z);
                        }
                        if (!(location instanceof Named)) {
                            return null;
                        }
                        name = ((Named) location).getName();
                    }
                } else if (!followAliases.getFlag(65536)) {
                    return getTypeFor(value2, z);
                }
            }
            Object obj = getEnvironment().get((Object) name);
            if (obj instanceof Type) {
                return (Type) obj;
            }
            if (obj instanceof ClassNamespace) {
                return ((ClassNamespace) obj).getClassType();
            }
            int length = name.length();
            if (length > 2 && name.charAt(0) == '<') {
                int i = length - 1;
                if (name.charAt(i) == '>') {
                    return getTypeFor(name.substring(1, i));
                }
            }
        } else if ((expression instanceof ClassExp) || (expression instanceof ModuleExp)) {
            return ((LambdaExp) expression).getClassType();
        }
        return null;
    }

    public static Type unionType(Type type, Type type2) {
        if (type == Type.toStringType) {
            type = Type.javalangStringType;
        }
        if (type2 == Type.toStringType) {
            type2 = Type.javalangStringType;
        }
        if (type == type2) {
            return type;
        }
        if (!(type instanceof PrimType) || !(type2 instanceof PrimType)) {
            return Type.objectType;
        }
        char charAt = type.getSignature().charAt(0);
        char charAt2 = type2.getSignature().charAt(0);
        if (charAt == charAt2) {
            return type;
        }
        if ((charAt == 'B' || charAt == 'S' || charAt == 'I') && (charAt2 == 'I' || charAt2 == 'J')) {
            return type2;
        }
        if ((charAt2 == 'B' || charAt2 == 'S' || charAt2 == 'I') && (charAt == 'I' || charAt == 'J')) {
            return type;
        }
        if (charAt == 'F' && charAt2 == 'D') {
            return type2;
        }
        return (charAt2 == 'F' && charAt == 'D') ? type : Type.objectType;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Declaration declFromField(gnu.expr.ModuleExp r10, java.lang.Object r11, gnu.bytecode.Field r12) {
        /*
            r9 = this;
            java.lang.String r0 = r12.getName()
            gnu.bytecode.Type r1 = r12.getType()
            gnu.bytecode.ClassType r2 = gnu.expr.Compilation.typeLocation
            boolean r2 = r1.isSubtype(r2)
            int r3 = r12.getModifiers()
            r3 = r3 & 16
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x001a
            r3 = 1
            goto L_0x001b
        L_0x001a:
            r3 = 0
        L_0x001b:
            java.lang.String r6 = "$instance"
            boolean r6 = r0.endsWith(r6)
            if (r6 == 0) goto L_0x0025
        L_0x0023:
            r11 = 0
            goto L_0x004a
        L_0x0025:
            if (r3 == 0) goto L_0x0032
            boolean r7 = r11 instanceof gnu.mapping.Named
            if (r7 == 0) goto L_0x0032
            gnu.mapping.Named r11 = (gnu.mapping.Named) r11
            java.lang.Object r0 = r11.getSymbol()
            goto L_0x0023
        L_0x0032:
            java.lang.String r11 = "$Prvt$"
            boolean r11 = r0.startsWith(r11)
            if (r11 == 0) goto L_0x0041
            r11 = 6
            java.lang.String r0 = r0.substring(r11)
            r11 = 1
            goto L_0x0042
        L_0x0041:
            r11 = 0
        L_0x0042:
            java.lang.String r0 = gnu.expr.Compilation.demangleName(r0, r5)
            java.lang.String r0 = r0.intern()
        L_0x004a:
            boolean r7 = r0 instanceof java.lang.String
            if (r7 == 0) goto L_0x005f
            java.lang.String r7 = r10.getNamespaceUri()
            java.lang.String r0 = (java.lang.String) r0
            if (r7 != 0) goto L_0x005b
            gnu.mapping.SimpleSymbol r0 = gnu.mapping.SimpleSymbol.valueOf(r0)
            goto L_0x005f
        L_0x005b:
            gnu.mapping.Symbol r0 = gnu.mapping.Symbol.make(r7, r0)
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            gnu.bytecode.ClassType r7 = gnu.bytecode.Type.objectType
            goto L_0x006c
        L_0x0064:
            java.lang.Class r7 = r1.getReflectClass()
            gnu.bytecode.Type r7 = r9.getTypeFor((java.lang.Class) r7)
        L_0x006c:
            gnu.expr.Declaration r10 = r10.addDeclaration(r0, r7)
            int r0 = r12.getModifiers()
            r0 = r0 & 8
            if (r0 == 0) goto L_0x007a
            r0 = 1
            goto L_0x007b
        L_0x007a:
            r0 = 0
        L_0x007b:
            if (r2 == 0) goto L_0x0095
            r10.setIndirectBinding(r5)
            boolean r5 = r1 instanceof gnu.bytecode.ClassType
            if (r5 == 0) goto L_0x00b7
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            java.lang.String r5 = "gnu.mapping.ThreadLocation"
            boolean r1 = r1.isSubclass((java.lang.String) r5)
            if (r1 == 0) goto L_0x00b7
            r7 = 268435456(0x10000000, double:1.32624737E-315)
            r10.setFlag(r7)
            goto L_0x00b7
        L_0x0095:
            if (r3 == 0) goto L_0x00b7
            boolean r7 = r1 instanceof gnu.bytecode.ClassType
            if (r7 == 0) goto L_0x00b7
            gnu.bytecode.ClassType r7 = gnu.expr.Compilation.typeProcedure
            boolean r7 = r1.isSubtype(r7)
            if (r7 == 0) goto L_0x00a7
            r10.setProcedureDecl(r5)
            goto L_0x00b7
        L_0x00a7:
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            java.lang.String r5 = "gnu.mapping.Namespace"
            boolean r1 = r1.isSubclass((java.lang.String) r5)
            if (r1 == 0) goto L_0x00b7
            r7 = 2097152(0x200000, double:1.0361308E-317)
            r10.setFlag(r7)
        L_0x00b7:
            if (r0 == 0) goto L_0x00be
            r0 = 2048(0x800, double:1.0118E-320)
            r10.setFlag(r0)
        L_0x00be:
            r10.field = r12
            if (r3 == 0) goto L_0x00c9
            if (r2 != 0) goto L_0x00c9
            r0 = 16384(0x4000, double:8.0948E-320)
            r10.setFlag(r0)
        L_0x00c9:
            if (r6 == 0) goto L_0x00d1
            r0 = 1073741824(0x40000000, double:5.304989477E-315)
            r10.setFlag(r0)
        L_0x00d1:
            r10.setSimple(r4)
            if (r11 == 0) goto L_0x00dc
            r11 = 524320(0x80020, double:2.590485E-318)
            r10.setFlag(r11)
        L_0x00dc:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Language.declFromField(gnu.expr.ModuleExp, java.lang.Object, gnu.bytecode.Field):gnu.expr.Declaration");
    }

    public boolean hasNamespace(Declaration declaration, int i) {
        return (getNamespaceOf(declaration) & i) != 0;
    }

    public void emitPushBoolean(boolean z, CodeAttr codeAttr) {
        codeAttr.emitGetStatic(z ? Compilation.trueConstant : Compilation.falseConstant);
    }

    public void emitCoerceToBoolean(CodeAttr codeAttr) {
        emitPushBoolean(false, codeAttr);
        codeAttr.emitIfNEq();
        codeAttr.emitPushInt(1);
        codeAttr.emitElse();
        codeAttr.emitPushInt(0);
        codeAttr.emitFi();
    }

    public Object coerceFromObject(Class cls, Object obj) {
        return getTypeFor(cls).coerceFromObject(obj);
    }

    public Object coerceToObject(Class cls, Object obj) {
        return getTypeFor(cls).coerceToObject(obj);
    }

    public static synchronized void setDefaults(Language language) {
        synchronized (Language.class) {
            setCurrentLanguage(language);
            global = language;
            if (Environment.getGlobal() == BuiltinEnvironment.getInstance()) {
                Environment.setGlobal(Environment.getCurrent());
            }
        }
    }

    public Procedure getPrompter() {
        Procedure procedure = (Procedure) getEnvironment().get(getSymbol("default-prompter"), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, (Object) null);
        if (procedure != null) {
            return procedure;
        }
        return new SimplePrompter();
    }

    public final Object eval(String str) throws Throwable {
        return eval((InPort) new CharArrayInPort(str));
    }

    public final Object eval(Reader reader) throws Throwable {
        return eval(reader instanceof InPort ? (InPort) reader : new InPort(reader));
    }

    public final Object eval(InPort inPort) throws Throwable {
        CallContext instance = CallContext.getInstance();
        int startFromContext = instance.startFromContext();
        try {
            eval(inPort, instance);
            return instance.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public final void eval(String str, Writer writer) throws Throwable {
        eval((Reader) new CharArrayInPort(str), writer);
    }

    public final void eval(String str, PrintConsumer printConsumer) throws Throwable {
        eval(str, getOutputConsumer(printConsumer));
    }

    public final void eval(String str, Consumer consumer) throws Throwable {
        eval((Reader) new CharArrayInPort(str), consumer);
    }

    public final void eval(Reader reader, Writer writer) throws Throwable {
        eval(reader, getOutputConsumer(writer));
    }

    /* JADX INFO: finally extract failed */
    public void eval(Reader reader, Consumer consumer) throws Throwable {
        InPort inPort = reader instanceof InPort ? (InPort) reader : new InPort(reader);
        CallContext instance = CallContext.getInstance();
        Consumer consumer2 = instance.consumer;
        try {
            instance.consumer = consumer;
            eval(inPort, instance);
            instance.consumer = consumer2;
        } catch (Throwable th) {
            instance.consumer = consumer2;
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void eval(InPort inPort, CallContext callContext) throws Throwable {
        SourceMessages sourceMessages = new SourceMessages();
        Language saveCurrent = setSaveCurrent(this);
        try {
            ModuleExp.evalModule(getEnvironment(), callContext, parse(inPort, sourceMessages, 3), (URL) null, (OutPort) null);
            restoreCurrent(saveCurrent);
            if (sourceMessages.seenErrors()) {
                throw new RuntimeException("invalid syntax in eval form:\n" + sourceMessages.toString(20));
            }
        } catch (Throwable th) {
            restoreCurrent(saveCurrent);
            throw th;
        }
    }

    public void runAsApplication(String[] strArr) {
        setDefaults(this);
        repl.main(strArr);
    }
}
