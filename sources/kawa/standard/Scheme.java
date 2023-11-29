package kawa.standard;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.NumberPredicate;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.kawa.lispexpr.ReaderParens;
import gnu.kawa.lispexpr.ReaderQuote;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.AbstractFormat;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.InheritingEnvironment;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleEnvironment;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kawa.lang.Eval;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.jwt.ReservedClaimNames;
import org.slf4j.Marker;

public class Scheme extends LispLanguage {
    public static final Apply apply;
    static final Declaration applyFieldDecl;
    public static final ApplyToArgs applyToArgs;
    public static LangPrimType booleanType;
    public static final AbstractFormat displayFormat = new DisplayFormat(false, 'S');
    public static final Map forEach;
    public static final Scheme instance;
    public static final InstanceOf instanceOf;
    public static final IsEq isEq;
    public static final IsEqual isEqual;
    public static final IsEqv isEqv;
    public static final NumberPredicate isEven;
    public static final NumberPredicate isOdd;
    protected static final SimpleEnvironment kawaEnvironment;
    public static final Map map;
    public static final Not not;
    public static final Environment nullEnvironment;
    public static final NumberCompare numEqu;
    public static final NumberCompare numGEq;
    public static final NumberCompare numGrt;
    public static final NumberCompare numLEq;
    public static final NumberCompare numLss;
    public static final Environment r4Environment;
    public static final Environment r5Environment;
    static HashMap<Type, String> typeToStringMap;
    static HashMap<String, Type> types;
    public static final Namespace unitNamespace = Namespace.valueOf("http://kawa.gnu.org/unit", "unit");
    public static final AbstractFormat writeFormat = new DisplayFormat(true, 'S');

    public String getName() {
        return "Scheme";
    }

    public int getNamespaceOf(Declaration declaration) {
        return 3;
    }

    static {
        SimpleEnvironment make = Environment.make("null-environment");
        nullEnvironment = make;
        InheritingEnvironment make2 = Environment.make("r4rs-environment", make);
        r4Environment = make2;
        InheritingEnvironment make3 = Environment.make("r5rs-environment", make2);
        r5Environment = make3;
        InheritingEnvironment make4 = Environment.make("kawa-environment", make3);
        kawaEnvironment = make4;
        Scheme scheme = new Scheme(make4);
        instance = scheme;
        instanceOf = new InstanceOf(scheme, GetNamedPart.INSTANCEOF_METHOD_NAME);
        not = new Not(scheme, "not");
        ApplyToArgs applyToArgs2 = new ApplyToArgs("apply-to-args", scheme);
        applyToArgs = applyToArgs2;
        Declaration declarationFromStatic = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
        applyFieldDecl = declarationFromStatic;
        apply = new Apply("apply", applyToArgs2);
        IsEq isEq2 = new IsEq(scheme, "eq?");
        isEq = isEq2;
        isEqv = new IsEqv(scheme, "eqv?", isEq2);
        isEqual = new IsEqual(scheme, "equal?");
        map = new Map(true, applyToArgs2, declarationFromStatic, isEq2);
        forEach = new Map(false, applyToArgs2, declarationFromStatic, isEq2);
        numEqu = NumberCompare.make(scheme, "=", 8);
        numGrt = NumberCompare.make(scheme, ">", 16);
        numGEq = NumberCompare.make(scheme, ">=", 24);
        numLss = NumberCompare.make(scheme, "<", 4);
        numLEq = NumberCompare.make(scheme, "<=", 12);
        isOdd = new NumberPredicate(scheme, "odd?", 1);
        isEven = new NumberPredicate(scheme, "even?", 2);
        scheme.initScheme();
        int i = HttpRequestContext.importServletDefinitions;
        if (i > 0) {
            try {
                scheme.loadClass(i > 1 ? "gnu.kawa.servlet.servlets" : "gnu.kawa.servlet.HTTP");
            } catch (Throwable unused) {
            }
        }
    }

    public static Scheme getInstance() {
        return instance;
    }

    public static Environment builtin() {
        return kawaEnvironment;
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [kawa.standard.Scheme] */
    private void initScheme() {
        Environment environment = nullEnvironment;
        this.environ = environment;
        this.environ.addLocation(LispLanguage.lookup_sym, (Object) null, getNamedPartLocation);
        defSntxStFld("lambda", "kawa.standard.SchemeCompilation", "lambda");
        defSntxStFld(LispLanguage.quote_sym, "kawa.lang.Quote", "plainQuote");
        defSntxStFld("%define", "kawa.standard.define", "defineRaw");
        defSntxStFld("define", "kawa.lib.prim_syntax");
        defSntxStFld("if", "kawa.lib.prim_syntax");
        defSntxStFld("set!", "kawa.standard.set_b", "set");
        defSntxStFld("cond", "kawa.lib.std_syntax");
        defSntxStFld("case", "kawa.lib.std_syntax");
        defSntxStFld("and", "kawa.lib.std_syntax");
        defSntxStFld("or", "kawa.lib.std_syntax");
        defSntxStFld("%let", "kawa.standard.let", "let");
        defSntxStFld("let", "kawa.lib.std_syntax");
        defSntxStFld("let*", "kawa.lib.std_syntax");
        defSntxStFld("letrec", "kawa.lib.prim_syntax");
        defSntxStFld("begin", "kawa.standard.begin", "begin");
        defSntxStFld("do", "kawa.lib.std_syntax");
        defSntxStFld("delay", "kawa.lib.std_syntax");
        defProcStFld("%make-promise", "kawa.lib.std_syntax");
        defSntxStFld(LispLanguage.quasiquote_sym, "kawa.lang.Quote", "quasiQuote");
        defSntxStFld("define-syntax", "kawa.lib.prim_syntax");
        defSntxStFld("let-syntax", "kawa.standard.let_syntax", "let_syntax");
        defSntxStFld("letrec-syntax", "kawa.standard.let_syntax", "letrec_syntax");
        defSntxStFld("syntax-rules", "kawa.standard.syntax_rules", "syntax_rules");
        environment.setLocked();
        Environment environment2 = r4Environment;
        this.environ = environment2;
        defProcStFld("not", "kawa.standard.Scheme");
        defProcStFld("boolean?", "kawa.lib.misc");
        defProcStFld("eq?", "kawa.standard.Scheme", "isEq");
        defProcStFld("eqv?", "kawa.standard.Scheme", "isEqv");
        defProcStFld("equal?", "kawa.standard.Scheme", "isEqual");
        defProcStFld("pair?", "kawa.lib.lists");
        defProcStFld("cons", "kawa.lib.lists");
        defProcStFld("car", "kawa.lib.lists");
        defProcStFld("cdr", "kawa.lib.lists");
        defProcStFld("set-car!", "kawa.lib.lists");
        defProcStFld("set-cdr!", "kawa.lib.lists");
        defProcStFld("caar", "kawa.lib.lists");
        defProcStFld("cadr", "kawa.lib.lists");
        defProcStFld("cdar", "kawa.lib.lists");
        defProcStFld("cddr", "kawa.lib.lists");
        defProcStFld("caaar", "kawa.lib.lists");
        defProcStFld("caadr", "kawa.lib.lists");
        defProcStFld("cadar", "kawa.lib.lists");
        defProcStFld("caddr", "kawa.lib.lists");
        defProcStFld("cdaar", "kawa.lib.lists");
        defProcStFld("cdadr", "kawa.lib.lists");
        defProcStFld("cddar", "kawa.lib.lists");
        defProcStFld("cdddr", "kawa.lib.lists");
        defProcStFld("caaaar", "kawa.lib.lists");
        defProcStFld("caaadr", "kawa.lib.lists");
        defProcStFld("caadar", "kawa.lib.lists");
        defProcStFld("caaddr", "kawa.lib.lists");
        defProcStFld("cadaar", "kawa.lib.lists");
        defProcStFld("cadadr", "kawa.lib.lists");
        defProcStFld("caddar", "kawa.lib.lists");
        defProcStFld("cadddr", "kawa.lib.lists");
        defProcStFld("cdaaar", "kawa.lib.lists");
        defProcStFld("cdaadr", "kawa.lib.lists");
        defProcStFld("cdadar", "kawa.lib.lists");
        defProcStFld("cdaddr", "kawa.lib.lists");
        defProcStFld("cddaar", "kawa.lib.lists");
        defProcStFld("cddadr", "kawa.lib.lists");
        defProcStFld("cdddar", "kawa.lib.lists");
        defProcStFld("cddddr", "kawa.lib.lists");
        defProcStFld("null?", "kawa.lib.lists");
        defProcStFld("list?", "kawa.lib.lists");
        defProcStFld("length", "kawa.lib.lists");
        defProcStFld("append", "kawa.standard.append", "append");
        defProcStFld("reverse", "kawa.lib.lists");
        defProcStFld("reverse!", "kawa.lib.lists");
        defProcStFld("list-tail", "kawa.lib.lists");
        defProcStFld("list-ref", "kawa.lib.lists");
        defProcStFld("memq", "kawa.lib.lists");
        defProcStFld("memv", "kawa.lib.lists");
        defProcStFld("member", "kawa.lib.lists");
        defProcStFld("assq", "kawa.lib.lists");
        defProcStFld("assv", "kawa.lib.lists");
        defProcStFld("assoc", "kawa.lib.lists");
        defProcStFld("symbol?", "kawa.lib.misc");
        defProcStFld("symbol->string", "kawa.lib.misc");
        defProcStFld("string->symbol", "kawa.lib.misc");
        defProcStFld("symbol=?", "kawa.lib.misc");
        defProcStFld("symbol-local-name", "kawa.lib.misc");
        defProcStFld("symbol-namespace", "kawa.lib.misc");
        defProcStFld("symbol-namespace-uri", "kawa.lib.misc");
        defProcStFld("symbol-prefix", "kawa.lib.misc");
        defProcStFld("namespace-uri", "kawa.lib.misc");
        defProcStFld("namespace-prefix", "kawa.lib.misc");
        defProcStFld("number?", "kawa.lib.numbers");
        defProcStFld("quantity?", "kawa.lib.numbers");
        defProcStFld("complex?", "kawa.lib.numbers");
        defProcStFld("real?", "kawa.lib.numbers");
        defProcStFld("rational?", "kawa.lib.numbers");
        defProcStFld("integer?", "kawa.lib.numbers");
        defProcStFld("exact?", "kawa.lib.numbers");
        defProcStFld("inexact?", "kawa.lib.numbers");
        defProcStFld("=", "kawa.standard.Scheme", "numEqu");
        defProcStFld("<", "kawa.standard.Scheme", "numLss");
        defProcStFld(">", "kawa.standard.Scheme", "numGrt");
        defProcStFld("<=", "kawa.standard.Scheme", "numLEq");
        defProcStFld(">=", "kawa.standard.Scheme", "numGEq");
        defProcStFld("zero?", "kawa.lib.numbers");
        defProcStFld("positive?", "kawa.lib.numbers");
        defProcStFld("negative?", "kawa.lib.numbers");
        defProcStFld("odd?", "kawa.standard.Scheme", "isOdd");
        defProcStFld("even?", "kawa.standard.Scheme", "isEven");
        defProcStFld("max", "kawa.lib.numbers");
        defProcStFld("min", "kawa.lib.numbers");
        defProcStFld(Marker.ANY_NON_NULL_MARKER, "gnu.kawa.functions.AddOp", "$Pl");
        defProcStFld("-", "gnu.kawa.functions.AddOp", "$Mn");
        defProcStFld(Marker.ANY_MARKER, "gnu.kawa.functions.MultiplyOp", "$St");
        defProcStFld(InternalZipConstants.ZIP_FILE_SEPARATOR, "gnu.kawa.functions.DivideOp", "$Sl");
        defProcStFld("abs", "kawa.lib.numbers");
        defProcStFld("quotient", "gnu.kawa.functions.DivideOp", "quotient");
        defProcStFld("remainder", "gnu.kawa.functions.DivideOp", "remainder");
        defProcStFld("modulo", "gnu.kawa.functions.DivideOp", "modulo");
        defProcStFld("div", "gnu.kawa.functions.DivideOp", "div");
        defProcStFld("mod", "gnu.kawa.functions.DivideOp", "mod");
        defProcStFld("div0", "gnu.kawa.functions.DivideOp", "div0");
        defProcStFld("mod0", "gnu.kawa.functions.DivideOp", "mod0");
        defProcStFld("div-and-mod", "kawa.lib.numbers");
        defProcStFld("div0-and-mod0", "kawa.lib.numbers");
        defProcStFld("gcd", "kawa.lib.numbers");
        defProcStFld("lcm", "kawa.lib.numbers");
        defProcStFld("numerator", "kawa.lib.numbers");
        defProcStFld("denominator", "kawa.lib.numbers");
        defProcStFld("floor", "kawa.lib.numbers");
        defProcStFld("ceiling", "kawa.lib.numbers");
        defProcStFld("truncate", "kawa.lib.numbers");
        defProcStFld("round", "kawa.lib.numbers");
        defProcStFld("rationalize", "kawa.lib.numbers");
        defProcStFld(ReservedClaimNames.EXPIRATION_TIME, "kawa.lib.numbers");
        defProcStFld("log", "kawa.lib.numbers");
        defProcStFld("sin", "kawa.lib.numbers");
        defProcStFld("cos", "kawa.lib.numbers");
        defProcStFld("tan", "kawa.lib.numbers");
        defProcStFld("asin", "kawa.lib.numbers");
        defProcStFld("acos", "kawa.lib.numbers");
        defProcStFld("atan", "kawa.lib.numbers");
        defProcStFld("sqrt", "kawa.lib.numbers");
        defProcStFld("expt", "kawa.standard.expt");
        defProcStFld("make-rectangular", "kawa.lib.numbers");
        defProcStFld("make-polar", "kawa.lib.numbers");
        defProcStFld("real-part", "kawa.lib.numbers");
        defProcStFld("imag-part", "kawa.lib.numbers");
        defProcStFld("magnitude", "kawa.lib.numbers");
        defProcStFld("angle", "kawa.lib.numbers");
        defProcStFld("inexact", "kawa.lib.numbers");
        defProcStFld("exact", "kawa.lib.numbers");
        defProcStFld("exact->inexact", "kawa.lib.numbers");
        defProcStFld("inexact->exact", "kawa.lib.numbers");
        defProcStFld("number->string", "kawa.lib.numbers");
        defProcStFld("string->number", "kawa.lib.numbers");
        defProcStFld("char?", "kawa.lib.characters");
        defProcStFld("char=?", "kawa.lib.characters");
        defProcStFld("char<?", "kawa.lib.characters");
        defProcStFld("char>?", "kawa.lib.characters");
        defProcStFld("char<=?", "kawa.lib.characters");
        defProcStFld("char>=?", "kawa.lib.characters");
        defProcStFld("char-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-alphabetic?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-numeric?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-whitespace?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-upper-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-lower-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-title-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char->integer", "kawa.lib.characters");
        defProcStFld("integer->char", "kawa.lib.characters");
        defProcStFld("char-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-general-category", "kawa.lib.rnrs.unicode");
        defProcStFld("string?", "kawa.lib.strings");
        defProcStFld("make-string", "kawa.lib.strings");
        defProcStFld("string-length", "kawa.lib.strings");
        defProcStFld("string-ref", "kawa.lib.strings");
        defProcStFld("string-set!", "kawa.lib.strings");
        defProcStFld("string=?", "kawa.lib.strings");
        defProcStFld("string<?", "kawa.lib.strings");
        defProcStFld("string>?", "kawa.lib.strings");
        defProcStFld("string<=?", "kawa.lib.strings");
        defProcStFld("string>=?", "kawa.lib.strings");
        defProcStFld("string-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfc", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkc", "kawa.lib.rnrs.unicode");
        defProcStFld("substring", "kawa.lib.strings");
        defProcStFld("string-append", "kawa.lib.strings");
        defProcStFld("string-append/shared", "kawa.lib.strings");
        defProcStFld("string->list", "kawa.lib.strings");
        defProcStFld("list->string", "kawa.lib.strings");
        defProcStFld("string-copy", "kawa.lib.strings");
        defProcStFld("string-fill!", "kawa.lib.strings");
        defProcStFld("vector?", "kawa.lib.vectors");
        defProcStFld("make-vector", "kawa.lib.vectors");
        defProcStFld("vector-length", "kawa.lib.vectors");
        defProcStFld("vector-ref", "kawa.lib.vectors");
        defProcStFld("vector-set!", "kawa.lib.vectors");
        defProcStFld("list->vector", "kawa.lib.vectors");
        defProcStFld("vector->list", "kawa.lib.vectors");
        defProcStFld("vector-fill!", "kawa.lib.vectors");
        defProcStFld("vector-append", "kawa.standard.vector_append", "vectorAppend");
        defProcStFld("values-append", "gnu.kawa.functions.AppendValues", "appendValues");
        defProcStFld("procedure?", "kawa.lib.misc");
        defProcStFld("apply", "kawa.standard.Scheme", "apply");
        defProcStFld("map", "kawa.standard.Scheme", "map");
        defProcStFld("for-each", "kawa.standard.Scheme", "forEach");
        defProcStFld("call-with-current-continuation", "gnu.kawa.functions.CallCC", "callcc");
        defProcStFld("call/cc", "kawa.standard.callcc", "callcc");
        defProcStFld("force", "kawa.lib.misc");
        defProcStFld("call-with-input-file", "kawa.lib.ports");
        defProcStFld("call-with-output-file", "kawa.lib.ports");
        defProcStFld("input-port?", "kawa.lib.ports");
        defProcStFld("output-port?", "kawa.lib.ports");
        defProcStFld("current-input-port", "kawa.lib.ports");
        defProcStFld("current-output-port", "kawa.lib.ports");
        defProcStFld("with-input-from-file", "kawa.lib.ports");
        defProcStFld("with-output-to-file", "kawa.lib.ports");
        defProcStFld("open-input-file", "kawa.lib.ports");
        defProcStFld("open-output-file", "kawa.lib.ports");
        defProcStFld("close-input-port", "kawa.lib.ports");
        defProcStFld("close-output-port", "kawa.lib.ports");
        defProcStFld("read", "kawa.lib.ports");
        defProcStFld("read-line", "kawa.lib.ports");
        defProcStFld("read-char", "kawa.standard.readchar", "readChar");
        defProcStFld("peek-char", "kawa.standard.readchar", "peekChar");
        defProcStFld("eof-object?", "kawa.lib.ports");
        defProcStFld("char-ready?", "kawa.lib.ports");
        defProcStFld("write", "kawa.lib.ports");
        defProcStFld("display", "kawa.lib.ports");
        defProcStFld("print-as-xml", "gnu.xquery.lang.XQuery", "writeFormat");
        defProcStFld("write-char", "kawa.lib.ports");
        defProcStFld("newline", "kawa.lib.ports");
        defProcStFld("load", "kawa.standard.load", "load");
        defProcStFld("load-relative", "kawa.standard.load", "loadRelative");
        defProcStFld("transcript-off", "kawa.lib.ports");
        defProcStFld("transcript-on", "kawa.lib.ports");
        defProcStFld("call-with-input-string", "kawa.lib.ports");
        defProcStFld("open-input-string", "kawa.lib.ports");
        defProcStFld("open-output-string", "kawa.lib.ports");
        defProcStFld("get-output-string", "kawa.lib.ports");
        defProcStFld("call-with-output-string", "kawa.lib.ports");
        defProcStFld("force-output", "kawa.lib.ports");
        defProcStFld("port-line", "kawa.lib.ports");
        defProcStFld("set-port-line!", "kawa.lib.ports");
        defProcStFld("port-column", "kawa.lib.ports");
        defProcStFld("current-error-port", "kawa.lib.ports");
        defProcStFld("input-port-line-number", "kawa.lib.ports");
        defProcStFld("set-input-port-line-number!", "kawa.lib.ports");
        defProcStFld("input-port-column-number", "kawa.lib.ports");
        defProcStFld("input-port-read-state", "kawa.lib.ports");
        defProcStFld("default-prompter", "kawa.lib.ports");
        defProcStFld("input-port-prompter", "kawa.lib.ports");
        defProcStFld("set-input-port-prompter!", "kawa.lib.ports");
        defProcStFld("base-uri", "kawa.lib.misc");
        defProcStFld("%syntax-error", "kawa.standard.syntax_error", "syntax_error");
        defProcStFld("syntax-error", "kawa.lib.prim_syntax");
        environment2.setLocked();
        Environment environment3 = r5Environment;
        this.environ = environment3;
        defProcStFld("values", "kawa.lib.misc");
        defProcStFld("call-with-values", "kawa.standard.call_with_values", "callWithValues");
        defSntxStFld("let-values", "kawa.lib.syntax");
        defSntxStFld("let*-values", "kawa.lib.syntax");
        defSntxStFld("case-lambda", "kawa.lib.syntax");
        defSntxStFld("receive", "kawa.lib.syntax");
        defProcStFld("eval", "kawa.lang.Eval");
        defProcStFld("repl", "kawa.standard.SchemeCompilation", "repl");
        defProcStFld("scheme-report-environment", "kawa.lib.misc");
        defProcStFld("null-environment", "kawa.lib.misc");
        defProcStFld("interaction-environment", "kawa.lib.misc");
        defProcStFld("dynamic-wind", "kawa.lib.misc");
        environment3.setLocked();
        SimpleEnvironment simpleEnvironment = kawaEnvironment;
        this.environ = simpleEnvironment;
        defSntxStFld("define-private", "kawa.lib.prim_syntax");
        defSntxStFld("define-constant", "kawa.lib.prim_syntax");
        defSntxStFld("define-autoload", "kawa.standard.define_autoload", "define_autoload");
        defSntxStFld("define-autoloads-from-file", "kawa.standard.define_autoload", "define_autoloads_from_file");
        defProcStFld("exit", "kawa.lib.rnrs.programs");
        defProcStFld("command-line", "kawa.lib.rnrs.programs");
        defProcStFld("bitwise-arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("ash", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("bitwise-arithmetic-shift-left", "gnu.kawa.functions.BitwiseOp", "ashiftl");
        defProcStFld("bitwise-arithmetic-shift-right", "gnu.kawa.functions.BitwiseOp", "ashiftr");
        defProcStFld("bitwise-and", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("logand", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("bitwise-ior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("logior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("bitwise-xor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("logxor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("bitwise-if", "kawa.lib.numbers");
        defProcStFld("bitwise-not", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("lognot", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("logop", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-set?", "kawa.lib.numbers");
        defProcStFld("logbit?", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-set?"));
        defProcStFld("logtest", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-count", "kawa.lib.numbers");
        defProcStFld("logcount", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-field", "kawa.lib.numbers");
        defProcStFld("bit-extract", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-field"));
        defProcStFld("bitwise-length", "kawa.lib.numbers");
        defProcStFld("integer-length", "kawa.lib.numbers", "bitwise$Mnlength");
        defProcStFld("bitwise-first-bit-set", "kawa.lib.numbers");
        defProcStFld("bitwise-rotate-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-reverse-bit-field", "kawa.lib.numbers");
        defProcStFld("string-upcase!", "kawa.lib.strings");
        defProcStFld("string-downcase!", "kawa.lib.strings");
        defProcStFld("string-capitalize!", "kawa.lib.strings");
        defProcStFld("string-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-capitalize", "kawa.lib.strings");
        defSntxStFld("primitive-virtual-method", "kawa.standard.prim_method", "virtual_method");
        defSntxStFld("primitive-static-method", "kawa.standard.prim_method", "static_method");
        defSntxStFld("primitive-interface-method", "kawa.standard.prim_method", "interface_method");
        defSntxStFld("primitive-constructor", "kawa.lib.reflection");
        defSntxStFld("primitive-op1", "kawa.standard.prim_method", "op1");
        defSntxStFld("primitive-get-field", "kawa.lib.reflection");
        defSntxStFld("primitive-set-field", "kawa.lib.reflection");
        defSntxStFld("primitive-get-static", "kawa.lib.reflection");
        defSntxStFld("primitive-set-static", "kawa.lib.reflection");
        defSntxStFld("primitive-array-new", "kawa.lib.reflection");
        defSntxStFld("primitive-array-get", "kawa.lib.reflection");
        defSntxStFld("primitive-array-set", "kawa.lib.reflection");
        defSntxStFld("primitive-array-length", "kawa.lib.reflection");
        defProcStFld("subtype?", "kawa.lib.reflection");
        defProcStFld("primitive-throw", "kawa.standard.prim_throw", "primitiveThrow");
        defSntxStFld("try-finally", "kawa.lib.syntax");
        defSntxStFld("try-catch", "kawa.lib.prim_syntax");
        defProcStFld("throw", "kawa.standard.throw_name", "throwName");
        defProcStFld("catch", "kawa.lib.system");
        defProcStFld("error", "kawa.lib.misc");
        defProcStFld("as", "gnu.kawa.functions.Convert", "as");
        defProcStFld(GetNamedPart.INSTANCEOF_METHOD_NAME, "kawa.standard.Scheme", "instanceOf");
        defSntxStFld("synchronized", "kawa.lib.syntax");
        defSntxStFld("object", "kawa.standard.object", "objectSyntax");
        defSntxStFld("define-class", "kawa.standard.define_class", "define_class");
        defSntxStFld("define-simple-class", "kawa.standard.define_class", "define_simple_class");
        defSntxStFld("this", "kawa.standard.thisRef", "thisSyntax");
        defProcStFld("make", "gnu.kawa.reflect.Invoke", "make");
        defProcStFld("slot-ref", "gnu.kawa.reflect.SlotGet", "slotRef");
        defProcStFld("slot-set!", "gnu.kawa.reflect.SlotSet", "set$Mnfield$Ex");
        defProcStFld("field", "gnu.kawa.reflect.SlotGet");
        defProcStFld("class-methods", "gnu.kawa.reflect.ClassMethods", "classMethods");
        defProcStFld("static-field", "gnu.kawa.reflect.SlotGet", "staticField");
        defProcStFld("invoke", "gnu.kawa.reflect.Invoke", "invoke");
        defProcStFld("invoke-static", "gnu.kawa.reflect.Invoke", "invokeStatic");
        defProcStFld("invoke-special", "gnu.kawa.reflect.Invoke", "invokeSpecial");
        defSntxStFld("define-macro", "kawa.lib.syntax");
        defSntxStFld("%define-macro", "kawa.standard.define_syntax", "define_macro");
        defSntxStFld("define-syntax-case", "kawa.lib.syntax");
        defSntxStFld("syntax-case", "kawa.standard.syntax_case", "syntax_case");
        defSntxStFld("%define-syntax", "kawa.standard.define_syntax", "define_syntax");
        defSntxStFld("syntax", "kawa.standard.syntax", "syntax");
        defSntxStFld("quasisyntax", "kawa.standard.syntax", "quasiSyntax");
        defProcStFld("syntax-object->datum", "kawa.lib.std_syntax");
        defProcStFld("datum->syntax-object", "kawa.lib.std_syntax");
        defProcStFld("syntax->expression", "kawa.lib.prim_syntax");
        defProcStFld("syntax-body->expression", "kawa.lib.prim_syntax");
        defProcStFld("generate-temporaries", "kawa.lib.std_syntax");
        defSntxStFld("with-syntax", "kawa.lib.std_syntax");
        defProcStFld("identifier?", "kawa.lib.std_syntax");
        defProcStFld("free-identifier=?", "kawa.lib.std_syntax");
        defProcStFld("syntax-source", "kawa.lib.std_syntax");
        defProcStFld("syntax-line", "kawa.lib.std_syntax");
        defProcStFld("syntax-column", "kawa.lib.std_syntax");
        defSntxStFld("begin-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("define-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("include", "kawa.lib.misc_syntax");
        defSntxStFld("include-relative", "kawa.lib.misc_syntax");
        defProcStFld("file-exists?", "kawa.lib.files");
        defProcStFld("file-directory?", "kawa.lib.files");
        defProcStFld("file-readable?", "kawa.lib.files");
        defProcStFld("file-writable?", "kawa.lib.files");
        defProcStFld("delete-file", "kawa.lib.files");
        defProcStFld("system-tmpdir", "kawa.lib.files");
        defProcStFld("make-temporary-file", "kawa.lib.files");
        defProcStFld("rename-file", "kawa.lib.files");
        defProcStFld("copy-file", "kawa.lib.files");
        defProcStFld("create-directory", "kawa.lib.files");
        defProcStFld("->pathname", "kawa.lib.files");
        define("port-char-encoding", Boolean.TRUE);
        define("symbol-read-case", "P");
        defProcStFld("system", "kawa.lib.system");
        defProcStFld("make-process", "kawa.lib.system");
        defProcStFld("tokenize-string-to-string-array", "kawa.lib.system");
        defProcStFld("tokenize-string-using-shell", "kawa.lib.system");
        defProcStFld("command-parse", "kawa.lib.system");
        defProcStFld("process-command-line-assignments", "kawa.lib.system");
        defProcStFld("record-accessor", "kawa.lib.reflection");
        defProcStFld("record-modifier", "kawa.lib.reflection");
        defProcStFld("record-predicate", "kawa.lib.reflection");
        defProcStFld("record-constructor", "kawa.lib.reflection");
        defProcStFld("make-record-type", "kawa.lib.reflection");
        defProcStFld("record-type-descriptor", "kawa.lib.reflection");
        defProcStFld("record-type-name", "kawa.lib.reflection");
        defProcStFld("record-type-field-names", "kawa.lib.reflection");
        defProcStFld("record?", "kawa.lib.reflection");
        defSntxStFld("define-record-type", "gnu.kawa.slib.DefineRecordType");
        defSntxStFld("when", "kawa.lib.syntax");
        defSntxStFld("unless", "kawa.lib.syntax");
        defSntxStFld("fluid-let", "kawa.standard.fluid_let", "fluid_let");
        defSntxStFld("constant-fold", "kawa.standard.constant_fold", "constant_fold");
        defProcStFld("make-parameter", "kawa.lib.parameters");
        defSntxStFld("parameterize", "kawa.lib.parameters");
        defProcStFld("compile-file", "kawa.lib.system");
        defProcStFld("environment-bound?", "kawa.lib.misc");
        defProcStFld("scheme-implementation-version", "kawa.lib.misc");
        defProcStFld("scheme-window", "kawa.lib.windows");
        defSntxStFld("define-procedure", "kawa.lib.std_syntax");
        defProcStFld("add-procedure-properties", "kawa.lib.misc");
        defProcStFld("make-procedure", "gnu.kawa.functions.MakeProcedure", "makeProcedure");
        defProcStFld("procedure-property", "kawa.lib.misc");
        defProcStFld("set-procedure-property!", "kawa.lib.misc");
        defSntxStFld("provide", "kawa.lib.misc_syntax");
        defSntxStFld("test-begin", "kawa.lib.misc_syntax");
        defProcStFld("quantity->number", "kawa.lib.numbers");
        defProcStFld("quantity->unit", "kawa.lib.numbers");
        defProcStFld("make-quantity", "kawa.lib.numbers");
        defSntxStFld("define-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_namespace");
        defSntxStFld("define-xml-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_xml_namespace");
        defSntxStFld("define-private-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_private_namespace");
        defSntxStFld("define-unit", "kawa.standard.define_unit", "define_unit");
        defSntxStFld("define-base-unit", "kawa.standard.define_unit", "define_base_unit");
        defProcStFld("duration", "kawa.lib.numbers");
        defProcStFld("gentemp", "kawa.lib.misc");
        defSntxStFld("defmacro", "kawa.lib.syntax");
        defProcStFld("setter", "gnu.kawa.functions.Setter", "setter");
        defSntxStFld("resource-url", "kawa.lib.misc_syntax");
        defSntxStFld("module-uri", "kawa.lib.misc_syntax");
        defSntxStFld("future", "kawa.lib.thread");
        defProcStFld("sleep", "kawa.lib.thread");
        defProcStFld("runnable", "kawa.lib.thread");
        defSntxStFld("trace", "kawa.lib.trace");
        defSntxStFld("untrace", "kawa.lib.trace");
        defSntxStFld("disassemble", "kawa.lib.trace");
        defProcStFld("format", "gnu.kawa.functions.Format");
        defProcStFld("parse-format", "gnu.kawa.functions.ParseFormat", "parseFormat");
        defProcStFld("make-element", "gnu.kawa.xml.MakeElement", "makeElement");
        defProcStFld("make-attribute", "gnu.kawa.xml.MakeAttribute", "makeAttribute");
        defProcStFld("map-values", "gnu.kawa.functions.ValuesMap", "valuesMap");
        defProcStFld("children", "gnu.kawa.xml.Children", "children");
        defProcStFld("attributes", "gnu.kawa.xml.Attributes");
        defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
        defProcStFld("keyword?", "kawa.lib.keywords");
        defProcStFld("keyword->string", "kawa.lib.keywords");
        defProcStFld("string->keyword", "kawa.lib.keywords");
        defSntxStFld("location", "kawa.standard.location", "location");
        defSntxStFld("define-alias", "kawa.standard.define_alias", "define_alias");
        defSntxStFld("define-variable", "kawa.standard.define_variable", "define_variable");
        defSntxStFld("define-member-alias", "kawa.standard.define_member_alias", "define_member_alias");
        defSntxStFld("define-enum", "gnu.kawa.slib.enums");
        defSntxStFld("import", "kawa.lib.syntax");
        defSntxStFld("require", "kawa.standard.require", "require");
        defSntxStFld("module-name", "kawa.standard.module_name", "module_name");
        defSntxStFld("module-extends", "kawa.standard.module_extends", "module_extends");
        defSntxStFld("module-implements", "kawa.standard.module_implements", "module_implements");
        defSntxStFld("module-static", "kawa.standard.module_static", "module_static");
        defSntxStFld("module-export", "kawa.standard.export", "module_export");
        defSntxStFld("export", "kawa.standard.export", "export");
        defSntxStFld("module-compile-options", "kawa.standard.module_compile_options", "module_compile_options");
        defSntxStFld("with-compile-options", "kawa.standard.with_compile_options", "with_compile_options");
        defProcStFld("array?", "kawa.lib.arrays");
        defProcStFld("array-rank", "kawa.lib.arrays");
        defProcStFld("make-array", "kawa.lib.arrays");
        defProcStFld("array", "kawa.lib.arrays");
        defProcStFld("array-start", "kawa.lib.arrays");
        defProcStFld("array-end", "kawa.lib.arrays");
        defProcStFld("shape", "kawa.lib.arrays");
        defProcStFld("array-ref", "gnu.kawa.functions.ArrayRef", "arrayRef");
        defProcStFld("array-set!", "gnu.kawa.functions.ArraySet", "arraySet");
        defProcStFld("share-array", "kawa.lib.arrays");
        defProcStFld("s8vector?", "kawa.lib.uniform");
        defProcStFld("make-s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector-length", "kawa.lib.uniform");
        defProcStFld("s8vector-ref", "kawa.lib.uniform");
        defProcStFld("s8vector-set!", "kawa.lib.uniform");
        defProcStFld("s8vector->list", "kawa.lib.uniform");
        defProcStFld("list->s8vector", "kawa.lib.uniform");
        defProcStFld("u8vector?", "kawa.lib.uniform");
        defProcStFld("make-u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector-length", "kawa.lib.uniform");
        defProcStFld("u8vector-ref", "kawa.lib.uniform");
        defProcStFld("u8vector-set!", "kawa.lib.uniform");
        defProcStFld("u8vector->list", "kawa.lib.uniform");
        defProcStFld("list->u8vector", "kawa.lib.uniform");
        defProcStFld("s16vector?", "kawa.lib.uniform");
        defProcStFld("make-s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector-length", "kawa.lib.uniform");
        defProcStFld("s16vector-ref", "kawa.lib.uniform");
        defProcStFld("s16vector-set!", "kawa.lib.uniform");
        defProcStFld("s16vector->list", "kawa.lib.uniform");
        defProcStFld("list->s16vector", "kawa.lib.uniform");
        defProcStFld("u16vector?", "kawa.lib.uniform");
        defProcStFld("make-u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector-length", "kawa.lib.uniform");
        defProcStFld("u16vector-ref", "kawa.lib.uniform");
        defProcStFld("u16vector-set!", "kawa.lib.uniform");
        defProcStFld("u16vector->list", "kawa.lib.uniform");
        defProcStFld("list->u16vector", "kawa.lib.uniform");
        defProcStFld("s32vector?", "kawa.lib.uniform");
        defProcStFld("make-s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector-length", "kawa.lib.uniform");
        defProcStFld("s32vector-ref", "kawa.lib.uniform");
        defProcStFld("s32vector-set!", "kawa.lib.uniform");
        defProcStFld("s32vector->list", "kawa.lib.uniform");
        defProcStFld("list->s32vector", "kawa.lib.uniform");
        defProcStFld("u32vector?", "kawa.lib.uniform");
        defProcStFld("make-u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector-length", "kawa.lib.uniform");
        defProcStFld("u32vector-ref", "kawa.lib.uniform");
        defProcStFld("u32vector-set!", "kawa.lib.uniform");
        defProcStFld("u32vector->list", "kawa.lib.uniform");
        defProcStFld("list->u32vector", "kawa.lib.uniform");
        defProcStFld("s64vector?", "kawa.lib.uniform");
        defProcStFld("make-s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector-length", "kawa.lib.uniform");
        defProcStFld("s64vector-ref", "kawa.lib.uniform");
        defProcStFld("s64vector-set!", "kawa.lib.uniform");
        defProcStFld("s64vector->list", "kawa.lib.uniform");
        defProcStFld("list->s64vector", "kawa.lib.uniform");
        defProcStFld("u64vector?", "kawa.lib.uniform");
        defProcStFld("make-u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector-length", "kawa.lib.uniform");
        defProcStFld("u64vector-ref", "kawa.lib.uniform");
        defProcStFld("u64vector-set!", "kawa.lib.uniform");
        defProcStFld("u64vector->list", "kawa.lib.uniform");
        defProcStFld("list->u64vector", "kawa.lib.uniform");
        defProcStFld("f32vector?", "kawa.lib.uniform");
        defProcStFld("make-f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector-length", "kawa.lib.uniform");
        defProcStFld("f32vector-ref", "kawa.lib.uniform");
        defProcStFld("f32vector-set!", "kawa.lib.uniform");
        defProcStFld("f32vector->list", "kawa.lib.uniform");
        defProcStFld("list->f32vector", "kawa.lib.uniform");
        defProcStFld("f64vector?", "kawa.lib.uniform");
        defProcStFld("make-f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector-length", "kawa.lib.uniform");
        defProcStFld("f64vector-ref", "kawa.lib.uniform");
        defProcStFld("f64vector-set!", "kawa.lib.uniform");
        defProcStFld("f64vector->list", "kawa.lib.uniform");
        defProcStFld("list->f64vector", "kawa.lib.uniform");
        defSntxStFld("cut", "gnu.kawa.slib.cut");
        defSntxStFld("cute", "gnu.kawa.slib.cut");
        defSntxStFld("cond-expand", "kawa.lib.syntax");
        defSntxStFld("%cond-expand", "kawa.lib.syntax");
        defAliasStFld("*print-base*", "gnu.kawa.functions.DisplayFormat", "outBase");
        defAliasStFld("*print-radix*", "gnu.kawa.functions.DisplayFormat", "outRadix");
        defAliasStFld("*print-right-margin*", "gnu.text.PrettyWriter", "lineLengthLoc");
        defAliasStFld("*print-miser-width*", "gnu.text.PrettyWriter", "miserWidthLoc");
        defAliasStFld("html", "gnu.kawa.xml.XmlNamespace", "HTML");
        defAliasStFld("unit", "kawa.standard.Scheme", "unitNamespace");
        defAliasStFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
        defAliasStFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        defAliasStFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        defProcStFld("resolve-uri", "kawa.lib.files");
        defAliasStFld("vector", "gnu.kawa.lispexpr.LangObjType", "vectorType");
        defAliasStFld("string", "gnu.kawa.lispexpr.LangObjType", "stringType");
        defAliasStFld("list", "gnu.kawa.lispexpr.LangObjType", "listType");
        defAliasStFld("regex", "gnu.kawa.lispexpr.LangObjType", "regexType");
        defProcStFld("path?", "kawa.lib.files");
        defProcStFld("filepath?", "kawa.lib.files");
        defProcStFld("URI?", "kawa.lib.files");
        defProcStFld("absolute-path?", "kawa.lib.files");
        defProcStFld("path-scheme", "kawa.lib.files");
        defProcStFld("path-authority", "kawa.lib.files");
        defProcStFld("path-user-info", "kawa.lib.files");
        defProcStFld("path-host", "kawa.lib.files");
        defProcStFld("path-port", "kawa.lib.files");
        defProcStFld("path-file", "kawa.lib.files");
        defProcStFld("path-parent", "kawa.lib.files");
        defProcStFld("path-directory", "kawa.lib.files");
        defProcStFld("path-last", "kawa.lib.files");
        defProcStFld("path-extension", "kawa.lib.files");
        defProcStFld("path-fragment", "kawa.lib.files");
        defProcStFld("path-query", "kawa.lib.files");
        simpleEnvironment.setLocked();
    }

    public Scheme() {
        this.environ = kawaEnvironment;
        this.userEnv = getNewEnvironment();
    }

    protected Scheme(Environment environment) {
        this.environ = environment;
    }

    public static Object eval(String str, Environment environment) {
        return eval((InPort) new CharArrayInPort(str), environment);
    }

    public static Object eval(InPort inPort, Environment environment) {
        SourceMessages sourceMessages = new SourceMessages();
        try {
            Object readList = ReaderParens.readList((LispReader) Language.getDefaultLanguage().getLexer(inPort, sourceMessages), 0, 1, -1);
            if (!sourceMessages.seenErrors()) {
                return Eval.evalBody(readList, environment, sourceMessages);
            }
            throw new SyntaxException(sourceMessages);
        } catch (SyntaxException e) {
            throw new RuntimeException("eval: errors while compiling:\n" + e.getMessages().toString(20));
        } catch (IOException e2) {
            throw new RuntimeException("eval: I/O exception: " + e2.toString());
        } catch (RuntimeException e3) {
            throw e3;
        } catch (Error e4) {
            throw e4;
        } catch (Throwable th) {
            throw new WrappedException(th);
        }
    }

    public static Object eval(Object obj, Environment environment) {
        try {
            return Eval.eval(obj, environment);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th) {
            throw new WrappedException(th);
        }
    }

    public AbstractFormat getFormat(boolean z) {
        return z ? writeFormat : displayFormat;
    }

    public static Type getTypeValue(Expression expression) {
        return getInstance().getTypeFor(expression);
    }

    static synchronized HashMap<String, Type> getTypeMap() {
        HashMap<String, Type> hashMap;
        synchronized (Scheme.class) {
            if (types == null) {
                booleanType = new LangPrimType(Type.booleanType, getInstance());
                HashMap<String, Type> hashMap2 = new HashMap<>();
                types = hashMap2;
                hashMap2.put("void", LangPrimType.voidType);
                types.put("int", LangPrimType.intType);
                types.put("char", LangPrimType.charType);
                types.put("boolean", booleanType);
                types.put("byte", LangPrimType.byteType);
                types.put("short", LangPrimType.shortType);
                types.put(LongTypedProperty.TYPE, LangPrimType.longType);
                types.put(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, LangPrimType.floatType);
                types.put(DoubleTypedProperty.TYPE, LangPrimType.doubleType);
                types.put("never-returns", Type.neverReturnsType);
                types.put("Object", Type.objectType);
                types.put("String", Type.toStringType);
                types.put("object", Type.objectType);
                types.put("number", LangObjType.numericType);
                types.put("quantity", ClassType.make("gnu.math.Quantity"));
                types.put("complex", ClassType.make("gnu.math.Complex"));
                types.put("real", LangObjType.realType);
                types.put("rational", LangObjType.rationalType);
                types.put(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, LangObjType.integerType);
                types.put("symbol", ClassType.make("gnu.mapping.Symbol"));
                types.put("namespace", ClassType.make("gnu.mapping.Namespace"));
                types.put("keyword", ClassType.make("gnu.expr.Keyword"));
                types.put("pair", ClassType.make("gnu.lists.Pair"));
                types.put("pair-with-position", ClassType.make("gnu.lists.PairWithPosition"));
                types.put("constant-string", ClassType.make("java.lang.String"));
                types.put("abstract-string", ClassType.make("gnu.lists.CharSeq"));
                types.put("character", ClassType.make("gnu.text.Char"));
                types.put("vector", LangObjType.vectorType);
                types.put("string", LangObjType.stringType);
                types.put("list", LangObjType.listType);
                types.put("function", ClassType.make("gnu.mapping.Procedure"));
                types.put("procedure", ClassType.make("gnu.mapping.Procedure"));
                types.put("input-port", ClassType.make("gnu.mapping.InPort"));
                types.put("output-port", ClassType.make("gnu.mapping.OutPort"));
                types.put("string-output-port", ClassType.make("gnu.mapping.CharArrayOutPort"));
                types.put("record", ClassType.make("kawa.lang.Record"));
                types.put(CommonProperties.TYPE, LangObjType.typeType);
                types.put("class-type", LangObjType.typeClassType);
                types.put("class", LangObjType.typeClass);
                types.put("s8vector", ClassType.make("gnu.lists.S8Vector"));
                types.put("u8vector", ClassType.make("gnu.lists.U8Vector"));
                types.put("s16vector", ClassType.make("gnu.lists.S16Vector"));
                types.put("u16vector", ClassType.make("gnu.lists.U16Vector"));
                types.put("s32vector", ClassType.make("gnu.lists.S32Vector"));
                types.put("u32vector", ClassType.make("gnu.lists.U32Vector"));
                types.put("s64vector", ClassType.make("gnu.lists.S64Vector"));
                types.put("u64vector", ClassType.make("gnu.lists.U64Vector"));
                types.put("f32vector", ClassType.make("gnu.lists.F32Vector"));
                types.put("f64vector", ClassType.make("gnu.lists.F64Vector"));
                types.put("document", ClassType.make("gnu.kawa.xml.KDocument"));
                types.put("readtable", ClassType.make("gnu.kawa.lispexpr.ReadTable"));
            }
            hashMap = types;
        }
        return hashMap;
    }

    public static Type getNamedType(String str) {
        getTypeMap();
        Type type = types.get(str);
        if (type == null && (str.startsWith("elisp:") || str.startsWith("clisp:"))) {
            int indexOf = str.indexOf(58);
            Class reflectClass = getNamedType(str.substring(indexOf + 1)).getReflectClass();
            String substring = str.substring(0, indexOf);
            Language instance2 = Language.getInstance(substring);
            if (instance2 != null) {
                type = instance2.getTypeFor(reflectClass);
                if (type != null) {
                    types.put(str, type);
                }
            } else {
                throw new RuntimeException("unknown type '" + str + "' - unknown language '" + substring + '\'');
            }
        }
        return type;
    }

    public Type getTypeFor(Class cls) {
        String name = cls.getName();
        if (cls.isPrimitive()) {
            return getNamedType(name);
        }
        if ("java.lang.String".equals(name)) {
            return Type.toStringType;
        }
        if ("gnu.math.IntNum".equals(name)) {
            return LangObjType.integerType;
        }
        if ("gnu.math.DFloNum".equals(name)) {
            return LangObjType.dflonumType;
        }
        if ("gnu.math.RatNum".equals(name)) {
            return LangObjType.rationalType;
        }
        if ("gnu.math.RealNum".equals(name)) {
            return LangObjType.realType;
        }
        if ("gnu.math.Numeric".equals(name)) {
            return LangObjType.numericType;
        }
        if ("gnu.lists.FVector".equals(name)) {
            return LangObjType.vectorType;
        }
        if ("gnu.lists.LList".equals(name)) {
            return LangObjType.listType;
        }
        if ("gnu.text.Path".equals(name)) {
            return LangObjType.pathType;
        }
        if ("gnu.text.URIPath".equals(name)) {
            return LangObjType.URIType;
        }
        if ("gnu.text.FilePath".equals(name)) {
            return LangObjType.filepathType;
        }
        if ("java.lang.Class".equals(name)) {
            return LangObjType.typeClass;
        }
        if ("gnu.bytecode.Type".equals(name)) {
            return LangObjType.typeType;
        }
        if ("gnu.bytecode.ClassType".equals(name)) {
            return LangObjType.typeClassType;
        }
        return Type.make(cls);
    }

    public String formatType(Type type) {
        if (typeToStringMap == null) {
            typeToStringMap = new HashMap<>();
            for (Map.Entry next : getTypeMap().entrySet()) {
                String str = (String) next.getKey();
                Type type2 = (Type) next.getValue();
                typeToStringMap.put(type2, str);
                Type implementationType = type2.getImplementationType();
                if (implementationType != type2) {
                    typeToStringMap.put(implementationType, str);
                }
            }
        }
        String str2 = typeToStringMap.get(type);
        if (str2 != null) {
            return str2;
        }
        return super.formatType(type);
    }

    public static Type string2Type(String str) {
        Type type;
        if (str.endsWith("[]")) {
            type = string2Type(str.substring(0, str.length() - 2));
            if (type != null) {
                type = ArrayType.make(type);
            }
        } else {
            type = getNamedType(str);
        }
        if (type != null) {
            return type;
        }
        Type string2Type = Language.string2Type(str);
        if (string2Type != null) {
            types.put(str, string2Type);
        }
        return string2Type;
    }

    public Type getTypeFor(String str) {
        return string2Type(str);
    }

    public static Type exp2Type(Expression expression) {
        return getInstance().getTypeFor(expression);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f1, code lost:
        r8 = 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0295 A[LOOP:5: B:141:0x027f->B:146:0x0295, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x029c  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02c2 A[Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02cc A[Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }, LOOP:6: B:159:0x02cc->B:161:0x02d0, LOOP_START, PHI: r2 r7 
      PHI: (r2v5 gnu.bytecode.Type) = (r2v3 gnu.bytecode.Type), (r2v6 gnu.bytecode.Type) binds: [B:158:0x02ca, B:161:0x02d0] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r7v5 int) = (r7v2 int), (r7v6 int) binds: [B:158:0x02ca, B:161:0x02d0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02da A[Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x01d0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x01ba A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression checkDefaultBinding(gnu.mapping.Symbol r20, kawa.lang.Translator r21) {
        /*
            r19 = this;
            r0 = r21
            gnu.mapping.Namespace r1 = r20.getNamespace()
            java.lang.String r2 = r20.getLocalPart()
            boolean r3 = r1 instanceof gnu.kawa.xml.XmlNamespace
            if (r3 == 0) goto L_0x0019
            gnu.kawa.xml.XmlNamespace r1 = (gnu.kawa.xml.XmlNamespace) r1
            java.lang.Object r0 = r1.get(r2)
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)
            return r0
        L_0x0019:
            java.lang.String r3 = r1.getName()
            gnu.mapping.Namespace r4 = unitNamespace
            java.lang.String r4 = r4.getName()
            if (r3 != r4) goto L_0x0030
            gnu.math.NamedUnit r3 = gnu.math.Unit.lookup(r2)
            if (r3 == 0) goto L_0x0030
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r3)
            return r0
        L_0x0030:
            java.lang.String r3 = r20.toString()
            int r4 = r3.length()
            r5 = 0
            if (r4 != 0) goto L_0x003c
            return r5
        L_0x003c:
            r6 = 2
            r7 = 0
            r8 = 1
            if (r4 <= r8) goto L_0x00a6
            int r9 = r4 + -1
            char r9 = r3.charAt(r9)
            r10 = 63
            if (r9 != r10) goto L_0x00a6
            int r9 = r2.length()
            if (r9 <= r8) goto L_0x00a6
            int r9 = r9 - r8
            java.lang.String r2 = r2.substring(r7, r9)
            java.lang.String r2 = r2.intern()
            gnu.mapping.Symbol r2 = r1.getSymbol(r2)
            gnu.expr.Expression r2 = r0.rewrite(r2, r7)
            boolean r9 = r2 instanceof gnu.expr.ReferenceExp
            if (r9 == 0) goto L_0x0079
            r9 = r2
            gnu.expr.ReferenceExp r9 = (gnu.expr.ReferenceExp) r9
            gnu.expr.Declaration r9 = r9.getBinding()
            if (r9 == 0) goto L_0x007d
            r10 = 65536(0x10000, double:3.2379E-319)
            boolean r9 = r9.getFlag(r10)
            if (r9 == 0) goto L_0x007e
            goto L_0x007d
        L_0x0079:
            boolean r9 = r2 instanceof gnu.expr.QuoteExp
            if (r9 != 0) goto L_0x007e
        L_0x007d:
            r2 = r5
        L_0x007e:
            if (r2 == 0) goto L_0x00a6
            gnu.expr.LambdaExp r0 = new gnu.expr.LambdaExp
            r0.<init>((int) r8)
            r1 = r20
            r0.setSymbol(r1)
            r1 = r5
            java.lang.Object r1 = (java.lang.Object) r1
            gnu.expr.Declaration r1 = r0.addDeclaration((java.lang.Object) r5)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.InstanceOf r4 = instanceOf
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r6]
            gnu.expr.ReferenceExp r6 = new gnu.expr.ReferenceExp
            r6.<init>((gnu.expr.Declaration) r1)
            r5[r7] = r6
            r5[r8] = r2
            r3.<init>((gnu.mapping.Procedure) r4, (gnu.expr.Expression[]) r5)
            r0.body = r3
            return r0
        L_0x00a6:
            char r2 = r3.charAt(r7)
            r9 = 46
            r10 = 43
            r11 = 45
            r12 = 10
            if (r2 == r11) goto L_0x00bc
            if (r2 == r10) goto L_0x00bc
            int r13 = java.lang.Character.digit(r2, r12)
            if (r13 < 0) goto L_0x0264
        L_0x00bc:
            r13 = 0
            r14 = 0
        L_0x00be:
            if (r13 >= r4) goto L_0x011a
            char r15 = r3.charAt(r13)
            int r16 = java.lang.Character.digit(r15, r12)
            r5 = 4
            r7 = 5
            r8 = 3
            if (r16 < 0) goto L_0x00d8
            if (r14 >= r8) goto L_0x00d1
            r5 = 2
            goto L_0x00d5
        L_0x00d1:
            if (r14 >= r7) goto L_0x00d4
            goto L_0x00d5
        L_0x00d4:
            r5 = 5
        L_0x00d5:
            r14 = r5
            r8 = 1
            goto L_0x0116
        L_0x00d8:
            if (r15 == r10) goto L_0x00dc
            if (r15 != r11) goto L_0x00e1
        L_0x00dc:
            if (r14 != 0) goto L_0x00e1
            r8 = 1
            r14 = 1
            goto L_0x0116
        L_0x00e1:
            if (r15 != r9) goto L_0x00e8
            if (r14 >= r8) goto L_0x00e8
            r8 = 1
            r14 = 3
            goto L_0x0116
        L_0x00e8:
            r8 = 101(0x65, float:1.42E-43)
            if (r15 == r8) goto L_0x00f3
            r8 = 69
            if (r15 != r8) goto L_0x00f1
            goto L_0x00f3
        L_0x00f1:
            r8 = 1
            goto L_0x011a
        L_0x00f3:
            if (r14 == r6) goto L_0x00f7
            if (r14 != r5) goto L_0x00f1
        L_0x00f7:
            int r5 = r13 + 1
            if (r5 >= r4) goto L_0x00f1
            char r8 = r3.charAt(r5)
            if (r8 == r11) goto L_0x0103
            if (r8 != r10) goto L_0x010b
        L_0x0103:
            int r5 = r5 + 1
            if (r5 >= r4) goto L_0x010b
            char r8 = r3.charAt(r5)
        L_0x010b:
            int r8 = java.lang.Character.digit(r8, r12)
            if (r8 >= 0) goto L_0x0112
            goto L_0x00f1
        L_0x0112:
            r8 = 1
            int r13 = r5 + 1
            r14 = 5
        L_0x0116:
            int r13 = r13 + r8
            r5 = 0
            r7 = 0
            goto L_0x00be
        L_0x011a:
            if (r13 >= r4) goto L_0x0264
            if (r14 <= r8) goto L_0x0264
            gnu.math.DFloNum r5 = new gnu.math.DFloNum
            r7 = 0
            java.lang.String r8 = r3.substring(r7, r13)
            r5.<init>((java.lang.String) r8)
            java.util.Vector r7 = new java.util.Vector
            r7.<init>()
            r8 = 0
        L_0x012e:
            if (r13 >= r4) goto L_0x01f2
            int r14 = r13 + 1
            char r13 = r3.charAt(r13)
            r15 = 42
            if (r13 != r15) goto L_0x014a
            if (r14 != r4) goto L_0x013e
            goto L_0x0264
        L_0x013e:
            int r13 = r14 + 1
            char r14 = r3.charAt(r14)
            r18 = r14
            r14 = r13
            r13 = r18
            goto L_0x015c
        L_0x014a:
            r15 = 47
            if (r13 != r15) goto L_0x015c
            if (r14 == r4) goto L_0x0264
            if (r8 == 0) goto L_0x0154
            goto L_0x0264
        L_0x0154:
            int r8 = r14 + 1
            char r13 = r3.charAt(r14)
            r14 = r8
            r8 = 1
        L_0x015c:
            int r15 = r14 + -1
        L_0x015e:
            boolean r16 = java.lang.Character.isLetter(r13)
            if (r16 != 0) goto L_0x016a
            int r9 = r14 + -1
            if (r9 != r15) goto L_0x016f
            goto L_0x0264
        L_0x016a:
            if (r14 != r4) goto L_0x01e7
            r13 = 49
            r9 = r14
        L_0x016f:
            java.lang.String r9 = r3.substring(r15, r9)
            r7.addElement(r9)
            r9 = 94
            if (r13 != r9) goto L_0x0187
            if (r14 != r4) goto L_0x017e
            goto L_0x0264
        L_0x017e:
            int r9 = r14 + 1
            char r13 = r3.charAt(r14)
            r14 = r9
            r9 = 1
            goto L_0x0188
        L_0x0187:
            r9 = 0
        L_0x0188:
            if (r13 != r10) goto L_0x0198
            if (r14 != r4) goto L_0x018e
            goto L_0x0264
        L_0x018e:
            int r9 = r14 + 1
            char r13 = r3.charAt(r14)
            r14 = r9
            r16 = 1
            goto L_0x01b0
        L_0x0198:
            if (r13 != r11) goto L_0x01ae
            if (r14 != r4) goto L_0x019e
            goto L_0x0264
        L_0x019e:
            int r9 = r14 + 1
            char r13 = r3.charAt(r14)
            r14 = r8 ^ 1
            r16 = 1
            r18 = r14
            r14 = r9
            r9 = r18
            goto L_0x01b1
        L_0x01ae:
            r16 = r9
        L_0x01b0:
            r9 = r8
        L_0x01b1:
            r15 = 0
            r17 = 0
        L_0x01b4:
            int r13 = java.lang.Character.digit(r13, r12)
            if (r13 > 0) goto L_0x01be
            int r14 = r14 + -1
        L_0x01bc:
            r13 = r14
            goto L_0x01c6
        L_0x01be:
            int r15 = r15 * 10
            int r15 = r15 + r13
            int r17 = r17 + 1
            if (r14 != r4) goto L_0x01db
            goto L_0x01bc
        L_0x01c6:
            if (r17 != 0) goto L_0x01cd
            if (r16 == 0) goto L_0x01cc
            goto L_0x0264
        L_0x01cc:
            r15 = 1
        L_0x01cd:
            if (r9 == 0) goto L_0x01d0
            int r15 = -r15
        L_0x01d0:
            gnu.math.IntNum r9 = gnu.math.IntNum.make((int) r15)
            r7.addElement(r9)
            r9 = 46
            goto L_0x012e
        L_0x01db:
            int r13 = r14 + 1
            char r14 = r3.charAt(r14)
            r18 = r14
            r14 = r13
            r13 = r18
            goto L_0x01b4
        L_0x01e7:
            int r9 = r14 + 1
            char r13 = r3.charAt(r14)
            r14 = r9
            r9 = 46
            goto L_0x015e
        L_0x01f2:
            if (r13 != r4) goto L_0x0264
            int r1 = r7.size()
            r2 = 1
            int r1 = r1 >> r2
            gnu.expr.Expression[] r2 = new gnu.expr.Expression[r1]
            r3 = 0
        L_0x01fd:
            if (r3 >= r1) goto L_0x0243
            int r4 = r3 * 2
            java.lang.Object r8 = r7.elementAt(r4)
            java.lang.String r8 = (java.lang.String) r8
            gnu.mapping.Namespace r9 = unitNamespace
            java.lang.String r8 = r8.intern()
            gnu.mapping.Symbol r8 = r9.getSymbol(r8)
            gnu.expr.Expression r8 = r0.rewrite(r8)
            r9 = 1
            int r4 = r4 + r9
            java.lang.Object r4 = r7.elementAt(r4)
            gnu.math.IntNum r4 = (gnu.math.IntNum) r4
            long r9 = r4.longValue()
            r11 = 1
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x023c
            gnu.expr.ApplyExp r9 = new gnu.expr.ApplyExp
            kawa.standard.expt r10 = kawa.standard.expt.expt
            gnu.expr.Expression[] r11 = new gnu.expr.Expression[r6]
            r12 = 0
            r11[r12] = r8
            gnu.expr.QuoteExp r4 = gnu.expr.QuoteExp.getInstance(r4)
            r13 = 1
            r11[r13] = r4
            r9.<init>((gnu.mapping.Procedure) r10, (gnu.expr.Expression[]) r11)
            r8 = r9
            goto L_0x023e
        L_0x023c:
            r12 = 0
            r13 = 1
        L_0x023e:
            r2[r3] = r8
            int r3 = r3 + 1
            goto L_0x01fd
        L_0x0243:
            r12 = 0
            r13 = 1
            if (r1 != r13) goto L_0x024a
            r0 = r2[r12]
            goto L_0x0251
        L_0x024a:
            gnu.expr.ApplyExp r0 = new gnu.expr.ApplyExp
            gnu.kawa.functions.MultiplyOp r1 = gnu.kawa.functions.MultiplyOp.$St
            r0.<init>((gnu.mapping.Procedure) r1, (gnu.expr.Expression[]) r2)
        L_0x0251:
            gnu.expr.ApplyExp r1 = new gnu.expr.ApplyExp
            gnu.kawa.functions.MultiplyOp r2 = gnu.kawa.functions.MultiplyOp.$St
            gnu.expr.Expression[] r3 = new gnu.expr.Expression[r6]
            gnu.expr.QuoteExp r4 = gnu.expr.QuoteExp.getInstance(r5)
            r3[r12] = r4
            r4 = 1
            r3[r4] = r0
            r1.<init>((gnu.mapping.Procedure) r2, (gnu.expr.Expression[]) r3)
            return r1
        L_0x0264:
            if (r4 <= r6) goto L_0x027d
            r5 = 60
            if (r2 != r5) goto L_0x027d
            int r2 = r4 + -1
            char r5 = r3.charAt(r2)
            r7 = 62
            if (r5 != r7) goto L_0x027d
            r5 = 1
            java.lang.String r3 = r3.substring(r5, r2)
            int r4 = r4 + -2
            r8 = 1
            goto L_0x027e
        L_0x027d:
            r8 = 0
        L_0x027e:
            r7 = 0
        L_0x027f:
            if (r4 <= r6) goto L_0x029a
            int r2 = r4 + -2
            char r2 = r3.charAt(r2)
            r5 = 91
            if (r2 != r5) goto L_0x029a
            int r2 = r4 + -1
            char r2 = r3.charAt(r2)
            r5 = 93
            if (r2 != r5) goto L_0x029a
            int r4 = r4 + -2
            int r7 = r7 + 1
            goto L_0x027f
        L_0x029a:
            if (r7 == 0) goto L_0x02a2
            r2 = 0
            java.lang.String r4 = r3.substring(r2, r4)
            goto L_0x02a3
        L_0x02a2:
            r4 = r3
        L_0x02a3:
            gnu.bytecode.Type r2 = getNamedType(r4)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            if (r7 <= 0) goto L_0x02ca
            if (r8 == 0) goto L_0x02ad
            if (r2 != 0) goto L_0x02ca
        L_0x02ad:
            java.lang.String r5 = r4.intern()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            gnu.mapping.Symbol r1 = r1.getSymbol(r5)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            r5 = 0
            gnu.expr.Expression r1 = r0.rewrite(r1, r5)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            gnu.expr.Expression r1 = gnu.expr.InlineCalls.inlineCalls(r1, r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            boolean r5 = r1 instanceof gnu.expr.ErrorExp     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            if (r5 != 0) goto L_0x02ca
            gnu.expr.Language r2 = r21.getLanguage()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            gnu.bytecode.Type r2 = r2.getTypeFor((gnu.expr.Expression) r1)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
        L_0x02ca:
            if (r2 == 0) goto L_0x02da
        L_0x02cc:
            int r7 = r7 + -1
            if (r7 < 0) goto L_0x02d5
            gnu.bytecode.ArrayType r2 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r2)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            goto L_0x02cc
        L_0x02d5:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r2)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            return r0
        L_0x02da:
            gnu.bytecode.Type r1 = gnu.bytecode.Type.lookupType(r4)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            boolean r2 = r1 instanceof gnu.bytecode.PrimType     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            if (r2 == 0) goto L_0x02e7
            java.lang.Class r0 = r1.getReflectClass()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            goto L_0x0308
        L_0x02e7:
            r1 = 46
            int r1 = r4.indexOf(r1)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            if (r1 >= 0) goto L_0x0304
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            r1.<init>()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            java.lang.String r0 = r0.classPrefix     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            r1.append(r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            java.lang.String r0 = gnu.expr.Compilation.mangleNameIfNeeded(r4)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            r1.append(r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            java.lang.String r4 = r1.toString()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
        L_0x0304:
            java.lang.Class r0 = gnu.bytecode.ClassType.getContextClass(r4)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
        L_0x0308:
            if (r0 == 0) goto L_0x0322
            if (r7 <= 0) goto L_0x031d
            gnu.bytecode.Type r0 = gnu.bytecode.Type.make(r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
        L_0x0310:
            int r7 = r7 + -1
            if (r7 < 0) goto L_0x0319
            gnu.bytecode.ArrayType r0 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            goto L_0x0310
        L_0x0319:
            java.lang.Class r0 = r0.getReflectClass()     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
        L_0x031d:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)     // Catch:{ ClassNotFoundException -> 0x0324, all -> 0x0322 }
            return r0
        L_0x0322:
            r0 = 0
            goto L_0x032f
        L_0x0324:
            java.lang.Package r0 = gnu.bytecode.ArrayClassLoader.getContextPackage(r3)
            if (r0 == 0) goto L_0x0322
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)
        L_0x032f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.Scheme.checkDefaultBinding(gnu.mapping.Symbol, kawa.lang.Translator):gnu.expr.Expression");
    }

    public Expression makeApply(Expression expression, Expression[] expressionArr) {
        Expression[] expressionArr2 = new Expression[(expressionArr.length + 1)];
        expressionArr2[0] = expression;
        System.arraycopy(expressionArr, 0, expressionArr2, 1, expressionArr.length);
        return new ApplyExp((Expression) new ReferenceExp(applyFieldDecl), expressionArr2);
    }

    public Symbol asSymbol(String str) {
        return Namespace.EmptyNamespace.getSymbol(str);
    }

    public ReadTable createReadTable() {
        ReadTable createInitial = ReadTable.createInitial();
        createInitial.postfixLookupOperator = ':';
        ReaderDispatch readerDispatch = (ReaderDispatch) createInitial.lookup(35);
        readerDispatch.set(39, new ReaderQuote(asSymbol("syntax")));
        readerDispatch.set(96, new ReaderQuote(asSymbol("quasisyntax")));
        readerDispatch.set(44, ReaderDispatchMisc.getInstance());
        createInitial.putReaderCtorFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
        createInitial.putReaderCtorFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        createInitial.putReaderCtorFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        createInitial.putReaderCtor("symbol", (Type) ClassType.make("gnu.mapping.Symbol"));
        createInitial.putReaderCtor("namespace", (Type) ClassType.make("gnu.mapping.Namespace"));
        createInitial.putReaderCtorFld("duration", "kawa.lib.numbers", "duration");
        createInitial.setFinalColonIsKeyword(true);
        return createInitial;
    }

    public static void registerEnvironment() {
        Language.setDefaults(getInstance());
    }
}
