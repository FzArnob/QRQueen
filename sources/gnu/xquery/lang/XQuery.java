package gnu.xquery.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.functions.ConstantFunction0;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XIntegerType;
import gnu.kawa.xml.XStringType;
import gnu.kawa.xml.XTimeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.MethodProc;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.XMLPrinter;
import gnu.xquery.util.BooleanValue;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Vector;
import kawa.standard.Scheme;

public class XQuery extends Language {
    public static final String DEFAULT_ELEMENT_PREFIX = null;
    public static final String DEFAULT_FUNCTION_PREFIX = "(functions)";
    public static final String KAWA_FUNCTION_NAMESPACE = "http://kawa.gnu.org/";
    public static final String LOCAL_NAMESPACE = "http://www.w3.org/2005/xquery-local-functions";
    public static final int PARSE_WITH_FOCUS = 65536;
    public static final String QEXO_FUNCTION_NAMESPACE = "http://qexo.gnu.org/";
    public static final String SCHEMA_INSTANCE_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
    public static final int VARIADIC_FUNCTION_NAMESPACE = -2;
    public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
    public static final String XQUERY_FUNCTION_NAMESPACE = "http://www.w3.org/2005/xpath-functions";
    static boolean charIsInt = false;
    public static final Namespace[] defaultFunctionNamespacePath;
    static int envCounter = 0;
    public static Environment extensionsEnvEnv = Environment.getInstance(KAWA_FUNCTION_NAMESPACE);
    public static QuoteExp falseExp = new QuoteExp(Boolean.FALSE, XDataType.booleanType);
    public static final ConstantFunction0 falseFunction = new ConstantFunction0("false", falseExp);
    public static final XQuery instance;
    public static final Namespace kawaFunctionNamespace;
    public static final Namespace qexoFunctionNamespace;
    public static QuoteExp trueExp = new QuoteExp(Boolean.TRUE, XDataType.booleanType);
    public static final ConstantFunction0 trueFunction = new ConstantFunction0("true", trueExp);
    static Object[] typeMap = {"string", XDataType.stringType, "untypedAtomic", XDataType.untypedAtomicType, "boolean", XDataType.booleanType, PropertyTypeConstants.PROPERTY_TYPE_INTEGER, XIntegerType.integerType, LongTypedProperty.TYPE, XIntegerType.longType, "int", XIntegerType.intType, "short", XIntegerType.shortType, "byte", XIntegerType.byteType, "unsignedLong", XIntegerType.unsignedLongType, "unsignedInt", XIntegerType.unsignedIntType, "unsignedShort", XIntegerType.unsignedShortType, "unsignedByte", XIntegerType.unsignedByteType, "positiveInteger", XIntegerType.positiveIntegerType, "nonPositiveInteger", XIntegerType.nonPositiveIntegerType, "negativeInteger", XIntegerType.negativeIntegerType, "nonNegativeInteger", XIntegerType.nonNegativeIntegerType, "date", XTimeType.dateType, DateTimeTypedProperty.TYPE, XTimeType.dateTimeType, "time", XTimeType.timeType, "duration", XTimeType.durationType, "yearMonthDuration", XTimeType.yearMonthDurationType, "dayTimeDuration", XTimeType.dayTimeDurationType, "gYearMonth", XTimeType.gYearMonthType, "gYear", XTimeType.gYearType, "gMonthDay", XTimeType.gMonthDayType, "gDay", XTimeType.gDayType, "gMonth", XTimeType.gMonthType, "decimal", XDataType.decimalType, PropertyTypeConstants.PROPERTY_TYPE_FLOAT, XDataType.floatType, DoubleTypedProperty.TYPE, XDataType.doubleType, "anyURI", XDataType.anyURIType, "hexBinary", XDataType.hexBinaryType, "base64Binary", XDataType.base64BinaryType, "NOTATION", XDataType.NotationType, "QName", "gnu.mapping.Symbol", "normalizedString", XStringType.normalizedStringType, "token", XStringType.tokenType, "language", XStringType.languageType, "NMTOKEN", XStringType.NMTOKENType, "Name", XStringType.NameType, "NCName", XStringType.NCNameType, "ID", XStringType.IDType, "IDREF", XStringType.IDREFType, "ENTITY", XStringType.ENTITYType, "anyAtomicType", XDataType.anyAtomicType, "anySimpleType", XDataType.anySimpleType, "untyped", XDataType.untypedType, "anyType", Type.objectType};
    public static final Environment xqEnvironment = Environment.make(XQUERY_FUNCTION_NAMESPACE);
    public static final Namespace xqueryFunctionNamespace;
    Namespace defaultNamespace = xqueryFunctionNamespace;

    public static int namespaceForFunctions(int i) {
        return (i << 2) | 2;
    }

    public String getName() {
        return "XQuery";
    }

    public boolean hasSeparateFunctionNamespace() {
        return true;
    }

    public void resolve(Compilation compilation) {
    }

    static {
        Namespace valueOf = Namespace.valueOf(XQUERY_FUNCTION_NAMESPACE);
        xqueryFunctionNamespace = valueOf;
        Namespace valueOf2 = Namespace.valueOf(KAWA_FUNCTION_NAMESPACE);
        kawaFunctionNamespace = valueOf2;
        Namespace valueOf3 = Namespace.valueOf(QEXO_FUNCTION_NAMESPACE);
        qexoFunctionNamespace = valueOf3;
        defaultFunctionNamespacePath = new Namespace[]{valueOf3, valueOf, Namespace.EmptyNamespace, valueOf2};
        XQuery xQuery = new XQuery();
        instance = xQuery;
        xQuery.initXQuery();
    }

    public static Numeric asNumber(Object obj) {
        if (obj instanceof Char) {
            return IntNum.make(((Char) obj).intValue());
        }
        return (Numeric) obj;
    }

    public static char asChar(Object obj) {
        if (obj instanceof Char) {
            return ((Char) obj).charValue();
        }
        int intValue = obj instanceof Numeric ? ((Numeric) obj).intValue() : -1;
        if (intValue >= 0 && intValue <= 65535) {
            return (char) intValue;
        }
        throw new ClassCastException("not a character value");
    }

    public boolean isTrue(Object obj) {
        return BooleanValue.booleanValue(obj);
    }

    public Lexer getLexer(InPort inPort, SourceMessages sourceMessages) {
        return new XQParser(inPort, sourceMessages, this);
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages sourceMessages, NameLookup nameLookup) {
        return new Compilation(this, sourceMessages, nameLookup);
    }

    public boolean parse(Compilation compilation, int i) throws IOException, SyntaxException {
        ModuleExp moduleExp = compilation.mainLambda;
        Compilation.defaultCallConvention = 2;
        compilation.mustCompileHere();
        XQParser xQParser = (XQParser) compilation.lexer;
        if (xQParser.isInteractive()) {
            Expression parse = xQParser.parse(compilation);
            if (parse == null) {
                return false;
            }
            moduleExp.body = parse;
        } else if ((i & 65536) != 0) {
            LambdaExp lambdaExp = new LambdaExp(3);
            Declaration addDeclaration = lambdaExp.addDeclaration((Object) XQParser.DOT_VARNAME);
            addDeclaration.setFlag(262144);
            addDeclaration.noteValue((Expression) null);
            lambdaExp.addDeclaration(XQParser.POSITION_VARNAME, Type.intType);
            lambdaExp.addDeclaration(XQParser.LAST_VARNAME, Type.intType);
            compilation.push((ScopeExp) lambdaExp);
            lambdaExp.body = xQParser.parse(compilation);
            compilation.pop(lambdaExp);
            moduleExp.body = lambdaExp;
        } else {
            Vector vector = new Vector(10);
            Expression expression = moduleExp.body;
            if (expression instanceof BeginExp) {
                BeginExp beginExp = (BeginExp) expression;
                int expressionCount = beginExp.getExpressionCount();
                Expression[] expressions = beginExp.getExpressions();
                for (int i2 = 0; i2 < expressionCount; i2++) {
                    vector.addElement(expressions[i2]);
                }
            } else if (!(expression == null || expression == QuoteExp.voidExp)) {
                vector.addElement(expression);
            }
            while (true) {
                Expression parse2 = xQParser.parse(compilation);
                if (parse2 == null) {
                    break;
                }
                vector.addElement(parse2);
            }
            if (xQParser.parseCount != 0 || xQParser.isInteractive()) {
                int size = vector.size();
                if (size == 0) {
                    moduleExp.body = QuoteExp.voidExp;
                } else if (size == 1) {
                    moduleExp.body = (Expression) vector.elementAt(0);
                } else {
                    Expression[] expressionArr = new Expression[size];
                    vector.copyInto(expressionArr);
                    moduleExp.body = new BeginExp(expressionArr);
                }
            } else {
                xQParser.error('e', "empty module", "XPST0003");
                return false;
            }
        }
        compilation.pop(moduleExp);
        XQResolveNames xQResolveNames = new XQResolveNames(compilation);
        xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
        xQResolveNames.parser = xQParser;
        xQResolveNames.resolveModule(moduleExp);
        compilation.setState(4);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r4 = (gnu.mapping.Procedure) r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getNamespaceOf(gnu.expr.Declaration r4) {
        /*
            r3 = this;
            boolean r0 = r4.isProcedureDecl()
            if (r0 == 0) goto L_0x0054
            int r0 = r4.getCode()
            r1 = -2
            if (r0 >= 0) goto L_0x000e
            return r1
        L_0x000e:
            gnu.expr.Expression r4 = r4.getValue()
            boolean r0 = r4 instanceof gnu.expr.LambdaExp
            if (r0 == 0) goto L_0x0025
            gnu.expr.LambdaExp r4 = (gnu.expr.LambdaExp) r4
            int r0 = r4.min_args
            int r2 = r4.max_args
            if (r0 != r2) goto L_0x0053
            int r4 = r4.min_args
            int r4 = namespaceForFunctions(r4)
            return r4
        L_0x0025:
            boolean r0 = r4 instanceof gnu.expr.QuoteExp
            if (r0 == 0) goto L_0x0044
            gnu.expr.QuoteExp r4 = (gnu.expr.QuoteExp) r4
            java.lang.Object r4 = r4.getValue()
            boolean r0 = r4 instanceof gnu.mapping.Procedure
            if (r0 == 0) goto L_0x0053
            gnu.mapping.Procedure r4 = (gnu.mapping.Procedure) r4
            int r0 = r4.minArgs()
            int r4 = r4.maxArgs()
            if (r0 != r4) goto L_0x0053
            int r4 = namespaceForFunctions(r0)
            return r4
        L_0x0044:
            boolean r0 = r4 instanceof gnu.expr.ReferenceExp
            if (r0 == 0) goto L_0x0053
            gnu.expr.ReferenceExp r4 = (gnu.expr.ReferenceExp) r4
            gnu.expr.Declaration r4 = r4.getBinding()
            int r4 = r3.getNamespaceOf(r4)
            return r4
        L_0x0053:
            return r1
        L_0x0054:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQuery.getNamespaceOf(gnu.expr.Declaration):int");
    }

    public boolean hasNamespace(Declaration declaration, int i) {
        int namespaceOf = getNamespaceOf(declaration);
        return namespaceOf == i || (namespaceOf == -2 && (i & 2) != 0) || (i == -2 && (namespaceOf & 2) != 0);
    }

    public Symbol getSymbol(String str) {
        return Symbol.make(this.defaultNamespace, str);
    }

    public void define(String str, Object obj) {
        this.environ.define(Symbol.make(this.defaultNamespace, str), obj instanceof Procedure ? EnvironmentKey.FUNCTION : null, obj);
    }

    /* access modifiers changed from: protected */
    public void define_method(String str, String str2, String str3) {
        Symbol make = Symbol.make(this.defaultNamespace, str);
        MethodProc apply = ClassMethods.apply(ClassType.make(str2), str3, 0, this);
        apply.setSymbol(make);
        this.environ.define(make, EnvironmentKey.FUNCTION, apply);
    }

    /* JADX INFO: finally extract failed */
    public void applyWithFocus(Procedure procedure, Object obj, int i, int i2, Consumer consumer) throws Throwable {
        CallContext instance2 = CallContext.getInstance();
        procedure.check3(obj, IntNum.make(i), IntNum.make(i2), instance2);
        Consumer consumer2 = instance2.consumer;
        try {
            instance2.consumer = consumer;
            instance2.runUntilDone();
            instance2.consumer = consumer2;
        } catch (Throwable th) {
            instance2.consumer = consumer2;
            throw th;
        }
    }

    public Object applyWithFocus(Procedure procedure, Object obj, int i, int i2) throws Throwable {
        CallContext instance2 = CallContext.getInstance();
        int startFromContext = instance2.startFromContext();
        try {
            procedure.check3(obj, IntNum.make(i), IntNum.make(i2), instance2);
            return instance2.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance2.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void applyWithFocus(Procedure procedure, Object obj, Consumer consumer) throws Throwable {
        CallContext instance2 = CallContext.getInstance();
        Consumer consumer2 = instance2.consumer;
        try {
            instance2.consumer = consumer;
            applyWithFocus$X(procedure, obj, instance2);
            instance2.consumer = consumer2;
        } catch (Throwable th) {
            instance2.consumer = consumer2;
            throw th;
        }
    }

    public Object applyWithFocus(Procedure procedure, Object obj) throws Throwable {
        CallContext instance2 = CallContext.getInstance();
        int startFromContext = instance2.startFromContext();
        try {
            applyWithFocus$X(procedure, obj, instance2);
            return instance2.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance2.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public void applyWithFocus$X(Procedure procedure, Object obj, CallContext callContext) throws Throwable {
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int size = values.size();
            if (size != 0) {
                int i = 0;
                IntNum make = IntNum.make(size);
                int i2 = 1;
                while (true) {
                    procedure.check3(values.getPosNext(i), IntNum.make(i2), make, callContext);
                    callContext.runUntilDone();
                    if (i2 != size) {
                        i = values.nextPos(i);
                        i2++;
                    } else {
                        return;
                    }
                }
            }
        } else {
            IntNum one = IntNum.one();
            procedure.check3(obj, one, one, callContext);
            callContext.runUntilDone();
        }
    }

    public Procedure evalToFocusProc(String str) throws Throwable {
        SourceMessages sourceMessages = new SourceMessages();
        Procedure evalToFocusProc = evalToFocusProc(new CharArrayInPort(str), sourceMessages);
        if (!sourceMessages.seenErrors()) {
            return evalToFocusProc;
        }
        throw new RuntimeException("invalid syntax in eval form:\n" + sourceMessages.toString(20));
    }

    public Procedure evalToFocusProc(Reader reader, SourceMessages sourceMessages) throws Throwable {
        Compilation parse = parse(reader instanceof InPort ? (InPort) reader : new InPort(reader), sourceMessages, 65537);
        CallContext instance2 = CallContext.getInstance();
        int startFromContext = instance2.startFromContext();
        try {
            ModuleExp.evalModule(Environment.getCurrent(), instance2, parse, (URL) null, (OutPort) null);
            return (Procedure) instance2.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance2.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public void evalWithFocus(Reader reader, SourceMessages sourceMessages, Object obj, Consumer consumer) throws Throwable {
        applyWithFocus(evalToFocusProc(reader, sourceMessages), obj, consumer);
    }

    public Object evalWithFocus(String str, Object obj) throws Throwable {
        return applyWithFocus(evalToFocusProc(str), obj);
    }

    public Object evalWithFocus(String str, Object obj, int i, int i2) throws Throwable {
        return applyWithFocus(evalToFocusProc(str), obj, i, i2);
    }

    public void evalWithFocus(Reader reader, SourceMessages sourceMessages, Object obj, int i, int i2, Consumer consumer) throws Throwable {
        applyWithFocus(evalToFocusProc(reader, sourceMessages), obj, i, i2, consumer);
    }

    public void eval_with_focus$X(String str, Object obj, CallContext callContext) throws Throwable {
        applyWithFocus$X(evalToFocusProc(str), obj, callContext);
    }

    public void eval_with_focus$X(String str, Object obj, int i, int i2, CallContext callContext) throws Throwable {
        evalToFocusProc(str).check3(obj, IntNum.make(i), IntNum.make(i2), callContext);
    }

    public XQuery() {
        this.environ = xqEnvironment;
    }

    private void initXQuery() {
        ModuleBody.setMainPrintValues(true);
        defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
        defProcStFld("item-at", "gnu.xquery.util.ItemAt", "itemAt");
        defProcStFld("count", "gnu.kawa.functions.CountValues", "countValues");
        define_method("sum", "gnu.xquery.util.Reduce", "sum");
        defProcStFld("avg", "gnu.xquery.util.Average", "avg");
        defProcStFld("sublist", "gnu.xquery.util.SubList", "subList");
        defProcStFld("subsequence", "gnu.xquery.util.SubList", "subList");
        define_method("empty", "gnu.xquery.util.SequenceUtils", "isEmptySequence");
        define_method("exists", "gnu.xquery.util.SequenceUtils", "exists");
        define_method("insert-before", "gnu.xquery.util.SequenceUtils", "insertBefore$X");
        define_method("remove", "gnu.xquery.util.SequenceUtils", "remove$X");
        define_method("reverse", "gnu.xquery.util.SequenceUtils", "reverse$X");
        defProcStFld("false", "gnu.xquery.lang.XQuery", "falseFunction");
        defProcStFld("true", "gnu.xquery.lang.XQuery", "trueFunction");
        defProcStFld("boolean", "gnu.xquery.util.BooleanValue", "booleanValue");
        define_method("trace", "gnu.xquery.util.Debug", "trace");
        define_method("error", "gnu.xquery.util.XQException", "error");
        defProcStFld("write-to", "gnu.kawa.xml.WriteTo", "writeTo");
        defProcStFld("write-to-if-changed", "gnu.kawa.xml.WriteTo", "writeToIfChanged");
        defProcStFld("iterator-items", "gnu.kawa.xml.IteratorItems", "iteratorItems");
        defProcStFld("list-items", "gnu.kawa.xml.ListItems", "listItems");
        define_method("node-name", "gnu.xquery.util.NodeUtils", "nodeName");
        define_method("nilled", "gnu.xquery.util.NodeUtils", "nilled");
        define_method("data", "gnu.xquery.util.NodeUtils", "data$X");
        define_method("lower-case", "gnu.xquery.util.StringUtils", "lowerCase");
        define_method("upper-case", "gnu.xquery.util.StringUtils", "upperCase");
        define_method("substring", "gnu.xquery.util.StringUtils", "substring");
        define_method("string-length", "gnu.xquery.util.StringUtils", "stringLength");
        define_method("substring-before", "gnu.xquery.util.StringUtils", "substringBefore");
        define_method("substring-after", "gnu.xquery.util.StringUtils", "substringAfter");
        define_method("translate", "gnu.xquery.util.StringUtils", "translate");
        define_method("encode-for-uri", "gnu.xquery.util.StringUtils", "encodeForUri");
        define_method("iri-to-uri", "gnu.xquery.util.StringUtils", "iriToUri");
        define_method("escape-html-uri", "gnu.xquery.util.StringUtils", "escapeHtmlUri");
        define_method("contains", "gnu.xquery.util.StringUtils", "contains");
        define_method("starts-with", "gnu.xquery.util.StringUtils", "startsWith");
        define_method("ends-with", "gnu.xquery.util.StringUtils", "endsWith");
        define_method("codepoint-equal", "gnu.xquery.util.StringUtils", "codepointEqual");
        define_method("normalize-unicode", "gnu.xquery.util.StringUtils", "normalizeUnicode");
        define_method("string-join", "gnu.xquery.util.StringUtils", "stringJoin");
        define_method("concat", "gnu.xquery.util.StringUtils", "concat$V");
        define_method("matches", "gnu.xquery.util.StringUtils", "matches");
        define_method("replace", "gnu.xquery.util.StringUtils", "replace");
        define_method("tokenize", "gnu.xquery.util.StringUtils", "tokenize$X");
        define_method("string-to-codepoints", "gnu.xquery.util.StringUtils", "stringToCodepoints$X");
        define_method("codepoints-to-string", "gnu.xquery.util.StringUtils", "codepointsToString");
        define_method("abs", "gnu.xquery.util.NumberValue", "abs");
        define_method("floor", "gnu.xquery.util.NumberValue", "floor");
        define_method("ceiling", "gnu.xquery.util.NumberValue", "ceiling");
        define_method("round", "gnu.xquery.util.NumberValue", "round");
        define_method("round-half-to-even", "gnu.xquery.util.NumberValue", "roundHalfToEven");
        define_method("QName", "gnu.xquery.util.QNameUtils", "makeQName");
        define_method("resolve-QName", "gnu.xquery.util.QNameUtils", "resolveQNameUsingElement");
        define_method("prefix-from-QName", "gnu.xquery.util.QNameUtils", "prefixFromQName");
        define_method("local-name-from-QName", "gnu.xquery.util.QNameUtils", "localNameFromQName");
        define_method("namespace-uri-from-QName", "gnu.xquery.util.QNameUtils", "namespaceURIFromQName");
        define_method("namespace-uri-for-prefix", "gnu.xquery.util.QNameUtils", "namespaceURIForPrefix");
        define_method("in-scope-prefixes", "gnu.xquery.util.NodeUtils", "inScopePrefixes$X");
        define_method("document-uri", "gnu.xquery.util.NodeUtils", "documentUri");
        define_method("years-from-duration", "gnu.xquery.util.TimeUtils", "yearsFromDuration");
        define_method("months-from-duration", "gnu.xquery.util.TimeUtils", "monthsFromDuration");
        define_method("days-from-duration", "gnu.xquery.util.TimeUtils", "daysFromDuration");
        define_method("hours-from-duration", "gnu.xquery.util.TimeUtils", "hoursFromDuration");
        define_method("minutes-from-duration", "gnu.xquery.util.TimeUtils", "minutesFromDuration");
        define_method("seconds-from-duration", "gnu.xquery.util.TimeUtils", "secondsFromDuration");
        define_method("year-from-dateTime", "gnu.xquery.util.TimeUtils", "yearFromDateTime");
        define_method("month-from-dateTime", "gnu.xquery.util.TimeUtils", "monthFromDateTime");
        define_method("day-from-dateTime", "gnu.xquery.util.TimeUtils", "dayFromDateTime");
        define_method("hours-from-dateTime", "gnu.xquery.util.TimeUtils", "hoursFromDateTime");
        define_method("minutes-from-dateTime", "gnu.xquery.util.TimeUtils", "minutesFromDateTime");
        define_method("seconds-from-dateTime", "gnu.xquery.util.TimeUtils", "secondsFromDateTime");
        define_method("timezone-from-dateTime", "gnu.xquery.util.TimeUtils", "timezoneFromDateTime");
        define_method("year-from-date", "gnu.xquery.util.TimeUtils", "yearFromDate");
        define_method("month-from-date", "gnu.xquery.util.TimeUtils", "monthFromDate");
        define_method("day-from-date", "gnu.xquery.util.TimeUtils", "dayFromDate");
        define_method("timezone-from-date", "gnu.xquery.util.TimeUtils", "timezoneFromDate");
        define_method("hours-from-time", "gnu.xquery.util.TimeUtils", "hoursFromTime");
        define_method("minutes-from-time", "gnu.xquery.util.TimeUtils", "minutesFromTime");
        define_method("seconds-from-time", "gnu.xquery.util.TimeUtils", "secondsFromTime");
        define_method("timezone-from-time", "gnu.xquery.util.TimeUtils", "timezoneFromTime");
        define_method("adjust-dateTime-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateTimeToTimezone");
        define_method("adjust-date-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateToTimezone");
        define_method("adjust-time-to-timezone", "gnu.xquery.util.TimeUtils", "adjustTimeToTimezone");
        define_method(DateTimeTypedProperty.TYPE, "gnu.xquery.util.TimeUtils", DateTimeTypedProperty.TYPE);
        define_method("current-dateTime", "gnu.xquery.util.TimeUtils", "currentDateTime");
        define_method("current-date", "gnu.xquery.util.TimeUtils", "currentDate");
        define_method("current-time", "gnu.xquery.util.TimeUtils", "currentTime");
        define_method("implicit-timezone", "gnu.xquery.util.TimeUtils", "implicitTimezone");
        define_method("zero-or-one", "gnu.xquery.util.SequenceUtils", "zeroOrOne");
        define_method("one-or-more", "gnu.xquery.util.SequenceUtils", "oneOrMore");
        define_method("exactly-one", "gnu.xquery.util.SequenceUtils", "exactlyOne");
        defProcStFld("distinct-nodes", "gnu.kawa.xml.SortNodes", "sortNodes");
        defProcStFld("children", "gnu.kawa.xml.Children", "children");
        define_method("not", "gnu.xquery.util.BooleanValue", "not");
        this.defaultNamespace = qexoFunctionNamespace;
        defProcStFld("response-header", "gnu.kawa.servlet.HTTP");
        defProcStFld("response-content-type", "gnu.kawa.servlet.HTTP");
        defProcStFld("response-status", "gnu.kawa.servlet.HTTP");
        defProcStFld("error-response", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet-context", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet-config", "gnu.kawa.servlet.HTTP");
        defProcStFld("servlet-context-realpath", "gnu.kawa.servlet.HTTP");
        defProcStFld("get-response", "gnu.kawa.servlet.HTTP");
        defProcStFld("get-request", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-method", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-uri", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-url", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-path-info", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-path-translated", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-servlet-path", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-query-string", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-parameter", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-parameters", "gnu.kawa.servlet.HTTP");
        this.defaultNamespace = xqueryFunctionNamespace;
    }

    public static XQuery getInstance() {
        return instance;
    }

    public static void registerEnvironment() {
        ApplicationMainSupport.processCommandLinePropertyAssignments = true;
        Language.setDefaults(instance);
    }

    public Consumer getOutputConsumer(Writer writer) {
        return new XMLPrinter(writer, false);
    }

    public static Type getStandardType(String str) {
        int length = typeMap.length;
        do {
            length -= 2;
            if (length < 0) {
                return null;
            }
        } while (!typeMap[length].equals(str));
        Object obj = typeMap[length + 1];
        if (obj instanceof String) {
            return Scheme.string2Type((String) obj);
        }
        return (Type) obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.Type getTypeFor(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "xs:"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x000e
            r0 = 3
        L_0x0009:
            java.lang.String r0 = r2.substring(r0)
            goto L_0x0019
        L_0x000e:
            java.lang.String r0 = "xdt:"
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x0018
            r0 = 4
            goto L_0x0009
        L_0x0018:
            r0 = r2
        L_0x0019:
            gnu.bytecode.Type r0 = getStandardType(r0)
            if (r0 == 0) goto L_0x0020
            goto L_0x0024
        L_0x0020:
            gnu.bytecode.Type r0 = kawa.standard.Scheme.string2Type(r2)
        L_0x0024:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQuery.getTypeFor(java.lang.String):gnu.bytecode.Type");
    }

    public String formatType(Type type) {
        String name = type.getName();
        if ("gnu.math.IntNum".equals(name)) {
            return "xs:integer";
        }
        return ("java.lang.String".equals(name) || "java.lang.CharSequence".equals(name)) ? "xs:string" : type.toString();
    }

    public Type getTypeFor(Class cls) {
        if (cls.isPrimitive()) {
            String name = cls.getName();
            if (name.equals("boolean")) {
                return XDataType.booleanType;
            }
            return Scheme.getNamedType(name);
        }
        if (!cls.isArray()) {
            String name2 = cls.getName();
            if (name2.equals("java.lang.String")) {
                return XDataType.stringStringType;
            }
            if (name2.equals("gnu.kawa.xml.UntypedAtomic")) {
                return XDataType.untypedAtomicType;
            }
            if (name2.equals("java.lang.Boolean")) {
                return XDataType.booleanType;
            }
            if (name2.equals("java.lang.Float")) {
                return XDataType.floatType;
            }
            if (name2.equals("java.lang.Double")) {
                return XDataType.doubleType;
            }
            if (name2.equals("java.math.BigDecimal")) {
                return XDataType.decimalType;
            }
            if (name2.equals("gnu.math.Duration")) {
                return XDataType.durationType;
            }
            if (name2.equals("gnu.text.Path")) {
                return XDataType.anyURIType;
            }
        }
        return Type.make(cls);
    }

    public Procedure getPrompter() {
        return new Prompter();
    }

    static void mangle(String str, int i, int i2, StringBuffer stringBuffer, char c) {
        String str2 = str;
        int i3 = i2;
        StringBuffer stringBuffer2 = stringBuffer;
        char c2 = c;
        int length = stringBuffer.length();
        int i4 = 0;
        while (true) {
            char c3 = 'P';
            while (true) {
                if (i4 < i3) {
                    char charAt = str2.charAt(i + i4);
                    i4++;
                    boolean z = true;
                    if (Character.isUpperCase(charAt)) {
                        if (c3 == 'U' && (i4 >= i3 || !Character.isLowerCase(str2.charAt(i + i4)))) {
                            z = false;
                        }
                        c3 = 'U';
                    } else if (Character.isLowerCase(charAt)) {
                        if (c3 == 'L' && c3 == 'U') {
                            z = false;
                        }
                        c3 = 'L';
                    } else if (Character.isLetter(charAt)) {
                        if (c3 == 'O') {
                            z = false;
                        }
                        c3 = 'O';
                    } else if (Character.isDigit(charAt)) {
                        if (c3 == 'D') {
                            z = false;
                        }
                        c3 = 'D';
                    } else if (Character.isJavaIdentifierPart(charAt)) {
                        if (c3 == 'D' || c3 == 'M') {
                            z = false;
                        }
                        c3 = Access.METHOD_CONTEXT;
                    }
                    if (z || c2 == '_') {
                        if (z && c2 == '_' && stringBuffer.length() > length) {
                            stringBuffer2.append('_');
                        }
                        charAt = Character.toUpperCase(charAt);
                    }
                    stringBuffer2.append(charAt);
                } else {
                    return;
                }
            }
        }
    }

    public static String mangle(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        mangle(str, 0, str.length(), stringBuffer, 'U');
        return stringBuffer.toString();
    }

    public static String makeClassName(String str) {
        String replace = str.replace(File.separatorChar, '/');
        int lastIndexOf = replace.lastIndexOf(47);
        if (lastIndexOf >= 0) {
            replace = replace.substring(lastIndexOf + 1);
        }
        int lastIndexOf2 = replace.lastIndexOf(46);
        if (lastIndexOf2 >= 0) {
            replace = replace.substring(0, lastIndexOf2);
        }
        return Compilation.mangleNameIfNeeded(replace);
    }

    public static Object getExternal(Symbol symbol, Object obj) {
        Environment current = Environment.getCurrent();
        Object obj2 = current.get(symbol, (Object) null, (Object) null);
        if (obj2 == null) {
            obj2 = current.get(Symbol.makeWithUnknownNamespace(symbol.getLocalName(), symbol.getPrefix()), (Object) null, (Object) null);
        }
        if (obj2 == null) {
            throw new RuntimeException("unbound external " + symbol);
        } else if (obj == null) {
            return obj2;
        } else {
            if (obj instanceof XDataType) {
                return ((XDataType) obj).cast(obj2);
            }
            if (obj instanceof ClassType) {
                String name = ((ClassType) obj).getName();
                if ("gnu.math.IntNum".equals(name)) {
                    return IntNum.valueOf(obj2.toString());
                }
                if ("gnu.math.RealNum".equals(name)) {
                    return DFloNum.make(Double.parseDouble(obj2.toString()));
                }
            }
            try {
                return ((Type) obj).coerceFromObject(obj2);
            } catch (ClassCastException unused) {
                throw new WrongType(symbol.toString(), -2, obj2, obj.toString());
            }
        }
    }
}
