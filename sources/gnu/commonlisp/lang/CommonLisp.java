package gnu.commonlisp.lang;

import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractFormat;
import gnu.mapping.Environment;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.SimpleEnvironment;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import kawa.lang.Lambda;
import kawa.standard.Scheme;
import kawa.standard.begin;
import org.jose4j.jwk.RsaJsonWebKey;

public class CommonLisp extends Lisp2 {
    static boolean charIsInt = false;
    public static final Environment clispEnvironment;
    static final AbstractFormat displayFormat = new DisplayFormat(false, Access.CLASS_CONTEXT);
    public static final CommonLisp instance;
    public static final NumberCompare numEqu;
    public static final NumberCompare numGEq;
    public static final NumberCompare numGrt;
    public static final NumberCompare numLEq;
    public static final NumberCompare numLss;
    static final AbstractFormat writeFormat = new DisplayFormat(true, Access.CLASS_CONTEXT);
    LangPrimType booleanType;

    public String getName() {
        return "CommonLisp";
    }

    public static Object getCharacter(int i) {
        if (charIsInt) {
            return IntNum.make(i);
        }
        return Char.make((char) i);
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

    /* JADX INFO: finally extract failed */
    static {
        SimpleEnvironment make = Environment.make("clisp-environment");
        clispEnvironment = make;
        CommonLisp commonLisp = new CommonLisp();
        instance = commonLisp;
        commonLisp.define(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, TRUE);
        commonLisp.define("nil", FALSE);
        numEqu = NumberCompare.make(commonLisp, "=", 8);
        numGrt = NumberCompare.make(commonLisp, ">", 16);
        numGEq = NumberCompare.make(commonLisp, ">=", 24);
        numLss = NumberCompare.make(commonLisp, "<", 4);
        numLEq = NumberCompare.make(commonLisp, "<=", 12);
        Environment saveCurrent = Environment.setSaveCurrent(make);
        try {
            commonLisp.initLisp();
            Environment.restoreCurrent(saveCurrent);
        } catch (Throwable th) {
            Environment.restoreCurrent(saveCurrent);
            throw th;
        }
    }

    public CommonLisp() {
        this.environ = clispEnvironment;
    }

    /* access modifiers changed from: package-private */
    public void initLisp() {
        LocationEnumeration enumerateAllLocations = Scheme.builtin().enumerateAllLocations();
        while (enumerateAllLocations.hasMoreElements()) {
            importLocation(enumerateAllLocations.nextLocation());
        }
        try {
            loadClass("kawa.lib.prim_syntax");
            loadClass("kawa.lib.std_syntax");
            loadClass("kawa.lib.lists");
            loadClass("kawa.lib.strings");
            loadClass("gnu.commonlisp.lisp.PrimOps");
        } catch (ClassNotFoundException unused) {
        }
        Lambda lambda = new Lambda();
        lambda.setKeywords(asSymbol("&optional"), asSymbol("&rest"), asSymbol("&key"));
        lambda.defaultDefault = nilExpr;
        defun("lambda", (Object) lambda);
        defun("defun", (Object) new defun(lambda));
        defun("defvar", (Object) new defvar(false));
        defun("defconst", (Object) new defvar(true));
        defun("defsubst", (Object) new defun(lambda));
        defun("function", (Object) new function(lambda));
        defun("setq", (Object) new setq());
        defun("prog1", (Object) new prog1("prog1", 1));
        defun("prog2", (Object) prog1.prog2);
        defun("progn", (Object) new begin());
        defun("unwind-protect", (Object) new UnwindProtect());
        Not not = new Not(this);
        defun("not", (Object) not);
        defun("null", (Object) not);
        defun("eq", (Object) new IsEq(this, "eq"));
        defun("equal", (Object) new IsEqual(this, "equal"));
        defun("typep", (Object) new InstanceOf(this));
        defun("princ", (Object) displayFormat);
        defun("prin1", (Object) writeFormat);
        defProcStFld("=", "gnu.commonlisp.lang.CommonLisp", "numEqu");
        defProcStFld("<", "gnu.commonlisp.lang.CommonLisp", "numLss");
        defProcStFld(">", "gnu.commonlisp.lang.CommonLisp", "numGrt");
        defProcStFld("<=", "gnu.commonlisp.lang.CommonLisp", "numLEq");
        defProcStFld(">=", "gnu.commonlisp.lang.CommonLisp", "numGEq");
        defProcStFld("functionp", "gnu.commonlisp.lisp.PrimOps");
    }

    public static CommonLisp getInstance() {
        return instance;
    }

    public static void registerEnvironment() {
        Language.setDefaults(instance);
    }

    public AbstractFormat getFormat(boolean z) {
        return z ? writeFormat : displayFormat;
    }

    public Type getTypeFor(String str) {
        if (str == RsaJsonWebKey.FACTOR_CRT_COEFFICIENT) {
            str = "java.lang.Object";
        }
        return Scheme.string2Type(str);
    }

    public Type getTypeFor(Class cls) {
        if (!cls.isPrimitive()) {
            return Type.make(cls);
        }
        String name = cls.getName();
        if (!name.equals("boolean")) {
            return Scheme.getNamedType(name);
        }
        if (this.booleanType == null) {
            this.booleanType = new LangPrimType(Type.booleanType, this);
        }
        return this.booleanType;
    }
}
