package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import java.math.BigDecimal;
import java.math.BigInteger;
import kawa.standard.Scheme;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.ReservedClaimNames;

/* compiled from: numbers.scm */
public class numbers extends ModuleBody {
    public static final numbers $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("signum").readResolve());
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final IntNum Lit2 = IntNum.make(1);
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod abs;
    public static final ModuleMethod acos;
    public static final ModuleMethod angle;
    public static final ModuleMethod asin;
    public static final GenericProc atan = null;
    public static final ModuleMethod bitwise$Mnbit$Mncount;
    public static final ModuleMethod bitwise$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
    public static final ModuleMethod bitwise$Mncopy$Mnbit;
    public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
    public static final ModuleMethod bitwise$Mnif;
    public static final ModuleMethod bitwise$Mnlength;
    public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
    public static final ModuleMethod ceiling;
    public static final ModuleMethod complex$Qu;
    public static final ModuleMethod cos;
    public static final ModuleMethod denominator;
    public static final ModuleMethod div$Mnand$Mnmod;
    public static final ModuleMethod div0$Mnand$Mnmod0;
    public static final ModuleMethod duration;
    public static final ModuleMethod exact;
    public static final ModuleMethod exact$Mn$Grinexact;
    public static final ModuleMethod exact$Qu;
    public static final ModuleMethod exp;
    public static final ModuleMethod floor;
    public static final ModuleMethod gcd;
    public static final ModuleMethod imag$Mnpart;
    public static final ModuleMethod inexact;
    public static final ModuleMethod inexact$Mn$Grexact;
    public static final ModuleMethod inexact$Qu;
    public static final ModuleMethod integer$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn4;
    public static final ModuleMethod lcm;
    public static final ModuleMethod log;
    public static final ModuleMethod logcount;
    public static final ModuleMethod logop;
    public static final ModuleMethod logtest;
    public static final ModuleMethod magnitude;
    public static final ModuleMethod make$Mnpolar;
    public static final ModuleMethod make$Mnquantity;
    public static final ModuleMethod make$Mnrectangular;
    public static final ModuleMethod max;
    public static final ModuleMethod min;
    public static final ModuleMethod negative$Qu;
    public static final ModuleMethod number$Mn$Grstring;
    public static final ModuleMethod number$Qu;
    public static final ModuleMethod numerator;
    public static final ModuleMethod positive$Qu;
    public static final ModuleMethod quantity$Mn$Grnumber;
    public static final ModuleMethod quantity$Mn$Grunit;
    public static final ModuleMethod quantity$Qu;
    public static final ModuleMethod rational$Qu;
    public static final ModuleMethod rationalize;
    public static final ModuleMethod real$Mnpart;
    public static final ModuleMethod real$Qu;
    public static final ModuleMethod round;
    public static final ModuleMethod sin;
    public static final GenericProc sqrt = null;
    public static final ModuleMethod string$Mn$Grnumber;
    public static final ModuleMethod tan;
    public static final ModuleMethod truncate;
    public static final ModuleMethod zero$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("duration").readResolve();
        Lit63 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("make-quantity").readResolve();
        Lit62 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("quantity->unit").readResolve();
        Lit61 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("quantity->number").readResolve();
        Lit60 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("string->number").readResolve();
        Lit59 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("number->string").readResolve();
        Lit58 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("bitwise-reverse-bit-field").readResolve();
        Lit57 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("bitwise-rotate-bit-field").readResolve();
        Lit56 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("bitwise-first-bit-set").readResolve();
        Lit55 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("bitwise-length").readResolve();
        Lit54 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-count").readResolve();
        Lit53 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("logcount").readResolve();
        Lit52 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("logtest").readResolve();
        Lit51 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("bitwise-if").readResolve();
        Lit50 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-field").readResolve();
        Lit49 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("bitwise-copy-bit-field").readResolve();
        Lit48 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("bitwise-copy-bit").readResolve();
        Lit47 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-set?").readResolve();
        Lit46 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("logop").readResolve();
        Lit45 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("inexact->exact").readResolve();
        Lit44 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("exact->inexact").readResolve();
        Lit43 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("exact").readResolve();
        Lit42 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("inexact").readResolve();
        Lit41 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol9;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("angle").readResolve();
        Lit40 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol10;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("magnitude").readResolve();
        Lit39 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol11;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("imag-part").readResolve();
        Lit38 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol12;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("real-part").readResolve();
        Lit37 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol13;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("make-polar").readResolve();
        Lit36 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol14;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("make-rectangular").readResolve();
        Lit35 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol15;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("acos").readResolve();
        Lit34 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = simpleSymbol17;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("asin").readResolve();
        Lit33 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = simpleSymbol19;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("tan").readResolve();
        Lit32 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = simpleSymbol21;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("cos").readResolve();
        Lit31 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = simpleSymbol23;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("sin").readResolve();
        Lit30 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol25;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("log").readResolve();
        Lit29 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = simpleSymbol27;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol(ReservedClaimNames.EXPIRATION_TIME).readResolve();
        Lit28 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = simpleSymbol29;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("rationalize").readResolve();
        Lit27 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = simpleSymbol31;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("round").readResolve();
        Lit26 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = simpleSymbol33;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("truncate").readResolve();
        Lit25 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = simpleSymbol35;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol("ceiling").readResolve();
        Lit24 = simpleSymbol65;
        SimpleSymbol simpleSymbol66 = simpleSymbol37;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("floor").readResolve();
        Lit23 = simpleSymbol67;
        SimpleSymbol simpleSymbol68 = simpleSymbol39;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("denominator").readResolve();
        Lit22 = simpleSymbol69;
        SimpleSymbol simpleSymbol70 = simpleSymbol41;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("numerator").readResolve();
        Lit21 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = simpleSymbol43;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("lcm").readResolve();
        Lit20 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = simpleSymbol45;
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("gcd").readResolve();
        Lit19 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = simpleSymbol47;
        SimpleSymbol simpleSymbol77 = (SimpleSymbol) new SimpleSymbol("div0-and-mod0").readResolve();
        Lit18 = simpleSymbol77;
        SimpleSymbol simpleSymbol78 = simpleSymbol49;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("div-and-mod").readResolve();
        Lit17 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = simpleSymbol51;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("abs").readResolve();
        Lit16 = simpleSymbol81;
        SimpleSymbol simpleSymbol82 = simpleSymbol53;
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) new SimpleSymbol("min").readResolve();
        Lit15 = simpleSymbol83;
        SimpleSymbol simpleSymbol84 = simpleSymbol55;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("max").readResolve();
        Lit14 = simpleSymbol85;
        SimpleSymbol simpleSymbol86 = simpleSymbol57;
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) new SimpleSymbol("negative?").readResolve();
        Lit13 = simpleSymbol87;
        SimpleSymbol simpleSymbol88 = simpleSymbol59;
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) new SimpleSymbol("positive?").readResolve();
        Lit12 = simpleSymbol89;
        SimpleSymbol simpleSymbol90 = simpleSymbol61;
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) new SimpleSymbol("zero?").readResolve();
        Lit11 = simpleSymbol91;
        SimpleSymbol simpleSymbol92 = simpleSymbol63;
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) new SimpleSymbol("inexact?").readResolve();
        Lit10 = simpleSymbol93;
        SimpleSymbol simpleSymbol94 = simpleSymbol65;
        SimpleSymbol simpleSymbol95 = (SimpleSymbol) new SimpleSymbol("exact?").readResolve();
        Lit9 = simpleSymbol95;
        SimpleSymbol simpleSymbol96 = simpleSymbol67;
        SimpleSymbol simpleSymbol97 = (SimpleSymbol) new SimpleSymbol("integer?").readResolve();
        Lit8 = simpleSymbol97;
        SimpleSymbol simpleSymbol98 = simpleSymbol69;
        SimpleSymbol simpleSymbol99 = (SimpleSymbol) new SimpleSymbol("rational?").readResolve();
        Lit7 = simpleSymbol99;
        SimpleSymbol simpleSymbol100 = simpleSymbol71;
        SimpleSymbol simpleSymbol101 = (SimpleSymbol) new SimpleSymbol("real?").readResolve();
        Lit6 = simpleSymbol101;
        SimpleSymbol simpleSymbol102 = simpleSymbol73;
        SimpleSymbol simpleSymbol103 = (SimpleSymbol) new SimpleSymbol("complex?").readResolve();
        Lit5 = simpleSymbol103;
        SimpleSymbol simpleSymbol104 = simpleSymbol75;
        SimpleSymbol simpleSymbol105 = (SimpleSymbol) new SimpleSymbol("quantity?").readResolve();
        Lit4 = simpleSymbol105;
        SimpleSymbol simpleSymbol106 = simpleSymbol77;
        SimpleSymbol simpleSymbol107 = (SimpleSymbol) new SimpleSymbol("number?").readResolve();
        Lit3 = simpleSymbol107;
        SimpleSymbol simpleSymbol108 = simpleSymbol79;
        numbers numbers = new numbers();
        $instance = numbers;
        number$Qu = new ModuleMethod(numbers, 1, simpleSymbol107, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Qu = new ModuleMethod(numbers, 2, simpleSymbol105, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        complex$Qu = new ModuleMethod(numbers, 3, simpleSymbol103, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        real$Qu = new ModuleMethod(numbers, 4, simpleSymbol101, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rational$Qu = new ModuleMethod(numbers, 5, simpleSymbol99, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        integer$Qu = new ModuleMethod(numbers, 6, simpleSymbol97, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Qu = new ModuleMethod(numbers, 7, simpleSymbol95, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Qu = new ModuleMethod(numbers, 8, simpleSymbol93, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        zero$Qu = new ModuleMethod(numbers, 9, simpleSymbol91, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        positive$Qu = new ModuleMethod(numbers, 10, simpleSymbol89, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        negative$Qu = new ModuleMethod(numbers, 11, simpleSymbol87, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        max = new ModuleMethod(numbers, 12, simpleSymbol85, -4096);
        min = new ModuleMethod(numbers, 13, simpleSymbol83, -4096);
        abs = new ModuleMethod(numbers, 14, simpleSymbol81, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        div$Mnand$Mnmod = new ModuleMethod(numbers, 15, simpleSymbol108, 8194);
        div0$Mnand$Mnmod0 = new ModuleMethod(numbers, 16, simpleSymbol106, 8194);
        gcd = new ModuleMethod(numbers, 17, simpleSymbol104, -4096);
        lcm = new ModuleMethod(numbers, 18, simpleSymbol102, -4096);
        numerator = new ModuleMethod(numbers, 19, simpleSymbol100, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        denominator = new ModuleMethod(numbers, 20, simpleSymbol98, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        floor = new ModuleMethod(numbers, 21, simpleSymbol96, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ceiling = new ModuleMethod(numbers, 22, simpleSymbol94, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        truncate = new ModuleMethod(numbers, 23, simpleSymbol92, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        round = new ModuleMethod(numbers, 24, simpleSymbol90, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rationalize = new ModuleMethod(numbers, 25, simpleSymbol88, 8194);
        exp = new ModuleMethod(numbers, 26, simpleSymbol86, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        log = new ModuleMethod(numbers, 27, simpleSymbol84, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sin = new ModuleMethod(numbers, 28, simpleSymbol82, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cos = new ModuleMethod(numbers, 29, simpleSymbol80, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tan = new ModuleMethod(numbers, 30, simpleSymbol78, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        asin = new ModuleMethod(numbers, 31, simpleSymbol76, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        acos = new ModuleMethod(numbers, 32, simpleSymbol74, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod = new ModuleMethod(numbers, 33, (Object) null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:146");
        lambda$Fn1 = moduleMethod;
        ModuleMethod moduleMethod2 = new ModuleMethod(numbers, 34, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:148");
        lambda$Fn2 = moduleMethod2;
        ModuleMethod moduleMethod3 = new ModuleMethod(numbers, 35, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:152");
        lambda$Fn3 = moduleMethod3;
        ModuleMethod moduleMethod4 = new ModuleMethod(numbers, 36, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:156");
        lambda$Fn4 = moduleMethod4;
        make$Mnrectangular = new ModuleMethod(numbers, 37, simpleSymbol72, 8194);
        make$Mnpolar = new ModuleMethod(numbers, 38, simpleSymbol70, 8194);
        real$Mnpart = new ModuleMethod(numbers, 39, simpleSymbol68, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        imag$Mnpart = new ModuleMethod(numbers, 40, simpleSymbol66, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        magnitude = new ModuleMethod(numbers, 41, simpleSymbol64, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        angle = new ModuleMethod(numbers, 42, simpleSymbol62, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact = new ModuleMethod(numbers, 43, simpleSymbol60, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact = new ModuleMethod(numbers, 44, simpleSymbol58, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Mn$Grinexact = new ModuleMethod(numbers, 45, simpleSymbol56, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Mn$Grexact = new ModuleMethod(numbers, 46, simpleSymbol54, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        logop = new ModuleMethod(numbers, 47, simpleSymbol52, 12291);
        bitwise$Mnbit$Mnset$Qu = new ModuleMethod(numbers, 48, simpleSymbol50, 8194);
        bitwise$Mncopy$Mnbit = new ModuleMethod(numbers, 49, simpleSymbol48, 12291);
        bitwise$Mncopy$Mnbit$Mnfield = new ModuleMethod(numbers, 50, simpleSymbol46, 16388);
        bitwise$Mnbit$Mnfield = new ModuleMethod(numbers, 51, simpleSymbol44, 12291);
        bitwise$Mnif = new ModuleMethod(numbers, 52, simpleSymbol42, 12291);
        logtest = new ModuleMethod(numbers, 53, simpleSymbol40, 8194);
        logcount = new ModuleMethod(numbers, 54, simpleSymbol38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnbit$Mncount = new ModuleMethod(numbers, 55, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnlength = new ModuleMethod(numbers, 56, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnfirst$Mnbit$Mnset = new ModuleMethod(numbers, 57, simpleSymbol32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnrotate$Mnbit$Mnfield = new ModuleMethod(numbers, 58, simpleSymbol30, 16388);
        bitwise$Mnreverse$Mnbit$Mnfield = new ModuleMethod(numbers, 59, simpleSymbol28, 12291);
        number$Mn$Grstring = new ModuleMethod(numbers, 60, simpleSymbol26, 8193);
        string$Mn$Grnumber = new ModuleMethod(numbers, 62, simpleSymbol24, 8193);
        quantity$Mn$Grnumber = new ModuleMethod(numbers, 64, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Mn$Grunit = new ModuleMethod(numbers, 65, simpleSymbol20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnquantity = new ModuleMethod(numbers, 66, simpleSymbol18, 8194);
        duration = new ModuleMethod(numbers, 67, simpleSymbol16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        numbers.run();
    }

    public numbers() {
        ModuleInfo.register(this);
    }

    public static CharSequence number$To$String(Number number) {
        return number$To$String(number, 10);
    }

    public static Object string$To$Number(CharSequence charSequence) {
        return string$To$Number(charSequence, 10);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        GenericProc genericProc = new GenericProc("atan");
        atan = genericProc;
        genericProc.setProperties(new Object[]{lambda$Fn1, lambda$Fn2});
        GenericProc genericProc2 = new GenericProc("sqrt");
        sqrt = genericProc2;
        genericProc2.setProperties(new Object[]{lambda$Fn3, lambda$Fn4});
    }

    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 14:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                if (RatNum.asRatNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                if (RatNum.asRatNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 22:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 31:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 32:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 34:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 35:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 36:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 39:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 40:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 41:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 42:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 43:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 45:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 46:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 54:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 55:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 56:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 57:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 60:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 62:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 64:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 65:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 67:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static boolean isQuantity(Object obj) {
        return obj instanceof Quantity;
    }

    public static boolean isComplex(Object obj) {
        return obj instanceof Complex;
    }

    public static boolean isReal(Object obj) {
        return RealNum.asRealNumOrNull(obj) != null;
    }

    public static boolean isRational(Object obj) {
        return RatNum.asRatNumOrNull(obj) != null;
    }

    public static boolean isInteger(Object obj) {
        boolean z = obj instanceof IntNum;
        if (z) {
            return z;
        }
        boolean z2 = obj instanceof DFloNum;
        if (z2) {
            z2 = true;
            try {
                if (Math.IEEEremainder(((DFloNum) obj).doubleValue(), 1.0d) != 0.0d) {
                    z2 = false;
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.math.DFloNum.doubleValue()", 1, obj);
            }
        }
        if (z2) {
            return z2;
        }
        boolean z3 = obj instanceof Number;
        if (!z3) {
            return z3;
        }
        boolean z4 = obj instanceof Long;
        if (z4) {
            return z4;
        }
        boolean z5 = obj instanceof Integer;
        if (z5) {
            return z5;
        }
        boolean z6 = obj instanceof Short;
        return z6 ? z6 : obj instanceof BigInteger;
    }

    public static boolean isExact(Object obj) {
        boolean z = obj instanceof Number;
        return z ? Arithmetic.isExact((Number) obj) : z;
    }

    public static boolean isInexact(Object obj) {
        boolean z = obj instanceof Number;
        return z ? ((Arithmetic.isExact((Number) obj) ? 1 : 0) + true) & true : z;
    }

    public static boolean isZero(Number number) {
        if (number instanceof Numeric) {
            return ((Numeric) number).isZero();
        }
        if (number instanceof BigInteger) {
            if (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigInteger) number, Lit1)) != Boolean.FALSE) {
                return true;
            }
        } else if (number instanceof BigDecimal) {
            if (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigDecimal) number, Lit1)) != Boolean.FALSE) {
                return true;
            }
        } else if (number.doubleValue() == 0.0d) {
            return true;
        }
        return false;
    }

    public static boolean isPositive(RealNum realNum) {
        return realNum.sign() > 0;
    }

    public static boolean isNegative(RealNum realNum) {
        return realNum.isNegative();
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 12) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 13) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 17) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 18) {
            return super.matchN(moduleMethod, objArr, callContext);
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Object max(Object... objArr) {
        int length = objArr.length;
        Object obj = objArr[0];
        try {
            RealNum coerceRealNum = LangObjType.coerceRealNum(obj);
            int i = 1;
            while (i < length) {
                Object obj2 = objArr[i];
                try {
                    coerceRealNum = coerceRealNum.max(LangObjType.coerceRealNum(obj2));
                    i++;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.math.RealNum.max(real)", 2, obj2);
                }
            }
            return coerceRealNum;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "result", -2, obj);
        }
    }

    public static Object min(Object... objArr) {
        int length = objArr.length;
        int i = 0;
        Object obj = objArr[0];
        try {
            RealNum coerceRealNum = LangObjType.coerceRealNum(obj);
            while (i < length) {
                Object obj2 = objArr[i];
                try {
                    coerceRealNum = coerceRealNum.min(LangObjType.coerceRealNum(obj2));
                    i++;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.math.RealNum.min(real)", 2, obj2);
                }
            }
            return coerceRealNum;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "result", -2, obj);
        }
    }

    public static Number abs(Number number) {
        if (number instanceof Numeric) {
            return ((Numeric) number).abs();
        }
        return Scheme.numGEq.apply2(number, Lit0) != Boolean.FALSE ? number : (Number) AddOp.$Mn.apply1(number);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 15) {
            if (i != 16) {
                if (i != 25) {
                    if (i == 33) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    } else if (i != 48) {
                        if (i != 53) {
                            if (i != 60) {
                                if (i != 62) {
                                    if (i == 66) {
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    } else if (i != 37) {
                                        if (i != 38) {
                                            return super.match2(moduleMethod, obj, obj2, callContext);
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    } else if (RealNum.asRealNumOrNull(obj) == null) {
                                        return -786431;
                                    } else {
                                        callContext.value1 = obj;
                                        if (RealNum.asRealNumOrNull(obj2) == null) {
                                            return -786430;
                                        }
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    }
                                } else if (!(obj instanceof CharSequence)) {
                                    return -786431;
                                } else {
                                    callContext.value1 = obj;
                                    callContext.value2 = obj2;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 2;
                                    return 0;
                                }
                            } else if (!(obj instanceof Number)) {
                                return -786431;
                            } else {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            }
                        } else if (IntNum.asIntNumOrNull(obj) == null) {
                            return -786431;
                        } else {
                            callContext.value1 = obj;
                            if (IntNum.asIntNumOrNull(obj2) == null) {
                                return -786430;
                            }
                            callContext.value2 = obj2;
                            callContext.proc = moduleMethod;
                            callContext.pc = 2;
                            return 0;
                        }
                    } else if (IntNum.asIntNumOrNull(obj) == null) {
                        return -786431;
                    } else {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                } else if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    if (RealNum.asRealNumOrNull(obj2) == null) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
            } else if (RealNum.asRealNumOrNull(obj) == null) {
                return -786431;
            } else {
                callContext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (RealNum.asRealNumOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (RealNum.asRealNumOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object divAndMod(RealNum realNum, RealNum realNum2) {
        Object apply2 = DivideOp.div.apply2(realNum, realNum2);
        try {
            RealNum coerceRealNum = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(realNum, MultiplyOp.$St.apply2(coerceRealNum, realNum2));
            try {
                return misc.values(coerceRealNum, LangObjType.coerceRealNum(apply22));
            } catch (ClassCastException e) {
                throw new WrongType(e, "r", -2, apply22);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, -2, apply2);
        }
    }

    public static Object div0AndMod0(RealNum realNum, RealNum realNum2) {
        Object apply2 = DivideOp.div0.apply2(realNum, realNum2);
        try {
            RealNum coerceRealNum = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(realNum, MultiplyOp.$St.apply2(coerceRealNum, realNum2));
            try {
                return misc.values(coerceRealNum, LangObjType.coerceRealNum(apply22));
            } catch (ClassCastException e) {
                throw new WrongType(e, "r", -2, apply22);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, -2, apply2);
        }
    }

    public static IntNum gcd(IntNum... intNumArr) {
        int length = intNumArr.length;
        if (length == 0) {
            return Lit0;
        }
        IntNum intNum = intNumArr[0];
        for (int i = 1; i < length; i++) {
            intNum = IntNum.gcd(intNum, intNumArr[i]);
        }
        return intNum;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 12) {
            return max(objArr);
        }
        if (i == 13) {
            return min(objArr);
        }
        if (i == 17) {
            int length = objArr.length;
            IntNum[] intNumArr = new IntNum[length];
            while (true) {
                length--;
                if (length < 0) {
                    return gcd(intNumArr);
                }
                Object obj = objArr[length];
                try {
                    intNumArr[length] = LangObjType.coerceIntNum(obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gcd", 0, obj);
                }
            }
        } else if (i != 18) {
            return super.applyN(moduleMethod, objArr);
        } else {
            int length2 = objArr.length;
            IntNum[] intNumArr2 = new IntNum[length2];
            while (true) {
                length2--;
                if (length2 < 0) {
                    return lcm(intNumArr2);
                }
                Object obj2 = objArr[length2];
                try {
                    intNumArr2[length2] = LangObjType.coerceIntNum(obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lcm", 0, obj2);
                }
            }
        }
    }

    public static IntNum lcm(IntNum... intNumArr) {
        int length = intNumArr.length;
        if (length == 0) {
            return Lit2;
        }
        IntNum abs2 = IntNum.abs(intNumArr[0]);
        for (int i = 1; i < length; i++) {
            abs2 = IntNum.lcm(abs2, intNumArr[i]);
        }
        return abs2;
    }

    public static IntNum numerator(RatNum ratNum) {
        return ratNum.numerator();
    }

    public static IntNum denominator(RatNum ratNum) {
        return ratNum.denominator();
    }

    public static RealNum floor(RealNum realNum) {
        return realNum.toInt(Numeric.FLOOR);
    }

    public static RealNum ceiling(RealNum realNum) {
        return realNum.toInt(Numeric.CEILING);
    }

    public static RealNum truncate(RealNum realNum) {
        return realNum.toInt(Numeric.TRUNCATE);
    }

    public static RealNum round(RealNum realNum) {
        return realNum.toInt(Numeric.ROUND);
    }

    public static RealNum rationalize(RealNum realNum, RealNum realNum2) {
        return RatNum.rationalize(LangObjType.coerceRealNum(realNum.sub(realNum2)), LangObjType.coerceRealNum(realNum.add(realNum2)));
    }

    public static Complex exp(Complex complex) {
        return complex.exp();
    }

    public static Complex log(Complex complex) {
        return complex.log();
    }

    public static double sin(double d) {
        return Math.sin(d);
    }

    public static double cos(double d) {
        return Math.cos(d);
    }

    public static double tan(double d) {
        return Math.tan(d);
    }

    public static double asin(double d) {
        return Math.asin(d);
    }

    public static double acos(double d) {
        return Math.acos(d);
    }

    static double lambda1(double d, double d2) {
        return Math.atan2(d, d2);
    }

    static double lambda2(double d) {
        return Math.atan(d);
    }

    static Quantity lambda3(Quantity quantity) {
        return Quantity.make(quantity.number().sqrt(), quantity.unit().sqrt());
    }

    static double lambda4(double d) {
        return Math.sqrt(d);
    }

    public static Complex makeRectangular(RealNum realNum, RealNum realNum2) {
        return Complex.make(realNum, realNum2);
    }

    public static DComplex makePolar(double d, double d2) {
        return Complex.polar(d, d2);
    }

    public static RealNum realPart(Complex complex) {
        return complex.re();
    }

    public static RealNum imagPart(Complex complex) {
        return complex.im();
    }

    public static Number magnitude(Number number) {
        return abs(number);
    }

    public static RealNum angle(Complex complex) {
        return complex.angle();
    }

    public static Number inexact(Number number) {
        return Arithmetic.toInexact(number);
    }

    public static Number exact(Number number) {
        return Arithmetic.toExact(number);
    }

    public static Number exact$To$Inexact(Number number) {
        return Arithmetic.toInexact(number);
    }

    public static Number inexact$To$Exact(Number number) {
        return Arithmetic.toExact(number);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 47) {
            callContext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            if (IntNum.asIntNumOrNull(obj3) == null) {
                return -786429;
            }
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i != 49) {
            if (i != 59) {
                if (i != 51) {
                    if (i != 52) {
                        return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                    }
                    if (IntNum.asIntNumOrNull(obj) == null) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (IntNum.asIntNumOrNull(obj2) == null) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (IntNum.asIntNumOrNull(obj3) == null) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                } else if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                }
            } else if (IntNum.asIntNumOrNull(obj) == null) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (IntNum.asIntNumOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public static IntNum logop(int i, IntNum intNum, IntNum intNum2) {
        return BitOps.bitOp(i, intNum, intNum2);
    }

    public static boolean isBitwiseBitSet(IntNum intNum, int i) {
        return BitOps.bitValue(intNum, i);
    }

    public static IntNum bitwiseCopyBit(IntNum intNum, int i, int i2) {
        return BitOps.setBitValue(intNum, i, i2);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 50) {
            if (i != 58) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            if (IntNum.asIntNumOrNull(obj) == null) {
                return -786431;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (IntNum.asIntNumOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            if (IntNum.asIntNumOrNull(obj4) == null) {
                return -786428;
            }
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static IntNum bitwiseCopyBitField(IntNum intNum, int i, int i2, IntNum intNum2) {
        int shift = IntNum.shift(-1, i);
        return bitwiseIf(BitOps.and(IntNum.make(shift), BitOps.not(IntNum.make(IntNum.shift(-1, i2)))), IntNum.shift(intNum2, i), intNum);
    }

    public static IntNum bitwiseBitField(IntNum intNum, int i, int i2) {
        return BitOps.extract(intNum, i, i2);
    }

    public static IntNum bitwiseIf(IntNum intNum, IntNum intNum2, IntNum intNum3) {
        return BitOps.ior(BitOps.and(intNum, intNum2), BitOps.and(BitOps.not(intNum), intNum3));
    }

    public static boolean logtest(IntNum intNum, IntNum intNum2) {
        return BitOps.test(intNum, intNum2);
    }

    public static int logcount(IntNum intNum) {
        if (IntNum.compare(intNum, 0) < 0) {
            intNum = BitOps.not(intNum);
        }
        return BitOps.bitCount(intNum);
    }

    public static int bitwiseBitCount(IntNum intNum) {
        if (IntNum.compare(intNum, 0) >= 0) {
            return BitOps.bitCount(intNum);
        }
        return -1 - BitOps.bitCount(BitOps.not(intNum));
    }

    public static int bitwiseLength(IntNum intNum) {
        return intNum.intLength();
    }

    public static int bitwiseFirstBitSet(IntNum intNum) {
        return BitOps.lowestBitSet(intNum);
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        if (i == 50) {
            try {
                try {
                    try {
                        try {
                            return bitwiseCopyBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue(), LangObjType.coerceIntNum(obj4));
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "bitwise-copy-bit-field", 4, obj4);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "bitwise-copy-bit-field", 3, obj3);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "bitwise-copy-bit-field", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "bitwise-copy-bit-field", 1, obj);
            }
        } else if (i != 58) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        } else {
            try {
                try {
                    try {
                        try {
                            return bitwiseRotateBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "bitwise-rotate-bit-field", 4, obj4);
                        }
                    } catch (ClassCastException e6) {
                        throw new WrongType(e6, "bitwise-rotate-bit-field", 3, obj3);
                    }
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "bitwise-rotate-bit-field", 2, obj2);
                }
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "bitwise-rotate-bit-field", 1, obj);
            }
        }
    }

    public static IntNum bitwiseRotateBitField(IntNum intNum, int i, int i2, int i3) {
        int i4 = i2 - i;
        if (i4 <= 0) {
            return intNum;
        }
        int i5 = i3 % i4;
        if (i5 < 0) {
            i5 += i4;
        }
        IntNum bitwiseBitField = bitwiseBitField(intNum, i, i2);
        return bitwiseCopyBitField(intNum, i, i2, BitOps.ior(IntNum.shift(bitwiseBitField, i5), IntNum.shift(bitwiseBitField, i5 - i4)));
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 47) {
            try {
                try {
                    try {
                        return logop(((Number) obj).intValue(), LangObjType.coerceIntNum(obj2), LangObjType.coerceIntNum(obj3));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "logop", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "logop", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "logop", 1, obj);
            }
        } else if (i == 49) {
            try {
                try {
                    try {
                        return bitwiseCopyBit(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "bitwise-copy-bit", 3, obj3);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "bitwise-copy-bit", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "bitwise-copy-bit", 1, obj);
            }
        } else if (i == 59) {
            try {
                try {
                    try {
                        return bitwiseReverseBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "bitwise-reverse-bit-field", 3, obj3);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "bitwise-reverse-bit-field", 2, obj2);
                }
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "bitwise-reverse-bit-field", 1, obj);
            }
        } else if (i == 51) {
            try {
                try {
                    try {
                        return bitwiseBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                    } catch (ClassCastException e10) {
                        throw new WrongType(e10, "bitwise-bit-field", 3, obj3);
                    }
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "bitwise-bit-field", 2, obj2);
                }
            } catch (ClassCastException e12) {
                throw new WrongType(e12, "bitwise-bit-field", 1, obj);
            }
        } else if (i != 52) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        } else {
            try {
                try {
                    try {
                        return bitwiseIf(LangObjType.coerceIntNum(obj), LangObjType.coerceIntNum(obj2), LangObjType.coerceIntNum(obj3));
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "bitwise-if", 3, obj3);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "bitwise-if", 2, obj2);
                }
            } catch (ClassCastException e15) {
                throw new WrongType(e15, "bitwise-if", 1, obj);
            }
        }
    }

    public static IntNum bitwiseReverseBitField(IntNum intNum, int i, int i2) {
        return BitOps.reverseBits(intNum, i, i2);
    }

    public static CharSequence number$To$String(Number number, int i) {
        return new FString((CharSequence) Arithmetic.toString(number, i));
    }

    public static Object string$To$Number(CharSequence charSequence, int i) {
        Object parseNumber = LispReader.parseNumber(charSequence, i);
        return parseNumber instanceof Numeric ? parseNumber : Boolean.FALSE;
    }

    public static Complex quantity$To$Number(Quantity quantity) {
        quantity.unit();
        if (quantity.doubleValue() == 1.0d) {
            return quantity.number();
        }
        return Complex.make(quantity.reValue(), quantity.imValue());
    }

    public static Unit quantity$To$Unit(Quantity quantity) {
        return quantity.unit();
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Object obj3 = obj;
        Object obj4 = obj2;
        int i = moduleMethod.selector;
        if (i == 15) {
            try {
                try {
                    return divAndMod(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "div-and-mod", 2, obj4);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "div-and-mod", 1, obj3);
            }
        } else if (i == 16) {
            try {
                try {
                    return div0AndMod0(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "div0-and-mod0", 2, obj4);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "div0-and-mod0", 1, obj3);
            }
        } else if (i == 25) {
            try {
                try {
                    return rationalize(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "rationalize", 2, obj4);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "rationalize", 1, obj3);
            }
        } else if (i == 33) {
            try {
                try {
                    return Double.valueOf(lambda1(((Number) obj3).doubleValue(), ((Number) obj4).doubleValue()));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "lambda", 2, obj4);
                }
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "lambda", 1, obj3);
            }
        } else if (i == 48) {
            try {
                try {
                    return isBitwiseBitSet(LangObjType.coerceIntNum(obj), ((Number) obj4).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "bitwise-bit-set?", 2, obj4);
                }
            } catch (ClassCastException e10) {
                throw new WrongType(e10, "bitwise-bit-set?", 1, obj3);
            }
        } else if (i == 53) {
            try {
                try {
                    return logtest(LangObjType.coerceIntNum(obj), LangObjType.coerceIntNum(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "logtest", 2, obj4);
                }
            } catch (ClassCastException e12) {
                throw new WrongType(e12, "logtest", 1, obj3);
            }
        } else if (i == 60) {
            try {
                try {
                    return number$To$String((Number) obj3, ((Number) obj4).intValue());
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "number->string", 2, obj4);
                }
            } catch (ClassCastException e14) {
                throw new WrongType(e14, "number->string", 1, obj3);
            }
        } else if (i == 62) {
            try {
                try {
                    return string$To$Number((CharSequence) obj3, ((Number) obj4).intValue());
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "string->number", 2, obj4);
                }
            } catch (ClassCastException e16) {
                throw new WrongType(e16, "string->number", 1, obj3);
            }
        } else if (i == 66) {
            return makeQuantity(obj, obj2);
        } else {
            if (i == 37) {
                try {
                    try {
                        return makeRectangular(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                    } catch (ClassCastException e17) {
                        throw new WrongType(e17, "make-rectangular", 2, obj4);
                    }
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "make-rectangular", 1, obj3);
                }
            } else if (i != 38) {
                return super.apply2(moduleMethod, obj, obj2);
            } else {
                try {
                    try {
                        return makePolar(((Number) obj3).doubleValue(), ((Number) obj4).doubleValue());
                    } catch (ClassCastException e19) {
                        throw new WrongType(e19, "make-polar", 2, obj4);
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "make-polar", 1, obj3);
                }
            }
        }
    }

    public static Quantity makeQuantity(Object obj, Object obj2) {
        Unit unit;
        String str;
        if (obj2 instanceof Unit) {
            try {
                unit = (Unit) obj2;
            } catch (ClassCastException e) {
                throw new WrongType(e, "u", -2, obj2);
            }
        } else {
            if (obj2 == null) {
                str = null;
            } else {
                str = obj2.toString();
            }
            unit = Unit.lookup(str);
        }
        if (unit != null) {
            try {
                return Quantity.make((Complex) obj, unit);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, obj);
            }
        } else {
            throw new IllegalArgumentException(Format.formatToString(0, "unknown unit: ~s", obj2).toString());
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isNumber(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isQuantity(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return isComplex(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isReal(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return isRational(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return isInteger(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return isExact(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return isInexact(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                try {
                    return isZero((Number) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "zero?", 1, obj);
                }
            case 10:
                try {
                    return isPositive(LangObjType.coerceRealNum(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "positive?", 1, obj);
                }
            case 11:
                try {
                    return isNegative(LangObjType.coerceRealNum(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "negative?", 1, obj);
                }
            case 14:
                try {
                    return abs((Number) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "abs", 1, obj);
                }
            case 19:
                try {
                    return numerator(LangObjType.coerceRatNum(obj));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "numerator", 1, obj);
                }
            case 20:
                try {
                    return denominator(LangObjType.coerceRatNum(obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "denominator", 1, obj);
                }
            case 21:
                try {
                    return floor(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "floor", 1, obj);
                }
            case 22:
                try {
                    return ceiling(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "ceiling", 1, obj);
                }
            case 23:
                try {
                    return truncate(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "truncate", 1, obj);
                }
            case 24:
                try {
                    return round(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "round", 1, obj);
                }
            case 26:
                try {
                    return exp((Complex) obj);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, ReservedClaimNames.EXPIRATION_TIME, 1, obj);
                }
            case 27:
                try {
                    return log((Complex) obj);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "log", 1, obj);
                }
            case 28:
                try {
                    return Double.valueOf(sin(((Number) obj).doubleValue()));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "sin", 1, obj);
                }
            case 29:
                try {
                    return Double.valueOf(cos(((Number) obj).doubleValue()));
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "cos", 1, obj);
                }
            case 30:
                try {
                    return Double.valueOf(tan(((Number) obj).doubleValue()));
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "tan", 1, obj);
                }
            case 31:
                try {
                    return Double.valueOf(asin(((Number) obj).doubleValue()));
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "asin", 1, obj);
                }
            case 32:
                try {
                    return Double.valueOf(acos(((Number) obj).doubleValue()));
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "acos", 1, obj);
                }
            case 34:
                try {
                    return Double.valueOf(lambda2(((Number) obj).doubleValue()));
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "lambda", 1, obj);
                }
            case 35:
                try {
                    return lambda3((Quantity) obj);
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "lambda", 1, obj);
                }
            case 36:
                try {
                    return Double.valueOf(lambda4(((Number) obj).doubleValue()));
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "lambda", 1, obj);
                }
            case 39:
                try {
                    return realPart((Complex) obj);
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "real-part", 1, obj);
                }
            case 40:
                try {
                    return imagPart((Complex) obj);
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "imag-part", 1, obj);
                }
            case 41:
                try {
                    return magnitude((Number) obj);
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "magnitude", 1, obj);
                }
            case 42:
                try {
                    return angle((Complex) obj);
                } catch (ClassCastException e24) {
                    throw new WrongType(e24, "angle", 1, obj);
                }
            case 43:
                try {
                    return inexact((Number) obj);
                } catch (ClassCastException e25) {
                    throw new WrongType(e25, "inexact", 1, obj);
                }
            case 44:
                try {
                    return exact((Number) obj);
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "exact", 1, obj);
                }
            case 45:
                try {
                    return exact$To$Inexact((Number) obj);
                } catch (ClassCastException e27) {
                    throw new WrongType(e27, "exact->inexact", 1, obj);
                }
            case 46:
                try {
                    return inexact$To$Exact((Number) obj);
                } catch (ClassCastException e28) {
                    throw new WrongType(e28, "inexact->exact", 1, obj);
                }
            case 54:
                try {
                    return Integer.valueOf(logcount(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e29) {
                    throw new WrongType(e29, "logcount", 1, obj);
                }
            case 55:
                try {
                    return Integer.valueOf(bitwiseBitCount(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e30) {
                    throw new WrongType(e30, "bitwise-bit-count", 1, obj);
                }
            case 56:
                try {
                    return Integer.valueOf(bitwiseLength(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e31) {
                    throw new WrongType(e31, "bitwise-length", 1, obj);
                }
            case 57:
                try {
                    return Integer.valueOf(bitwiseFirstBitSet(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e32) {
                    throw new WrongType(e32, "bitwise-first-bit-set", 1, obj);
                }
            case 60:
                try {
                    return number$To$String((Number) obj);
                } catch (ClassCastException e33) {
                    throw new WrongType(e33, "number->string", 1, obj);
                }
            case 62:
                try {
                    return string$To$Number((CharSequence) obj);
                } catch (ClassCastException e34) {
                    throw new WrongType(e34, "string->number", 1, obj);
                }
            case 64:
                try {
                    return quantity$To$Number((Quantity) obj);
                } catch (ClassCastException e35) {
                    throw new WrongType(e35, "quantity->number", 1, obj);
                }
            case 65:
                try {
                    return quantity$To$Unit((Quantity) obj);
                } catch (ClassCastException e36) {
                    throw new WrongType(e36, "quantity->unit", 1, obj);
                }
            case 67:
                return duration(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static Duration duration(Object obj) {
        return Duration.parseDuration(obj == null ? null : obj.toString());
    }
}
