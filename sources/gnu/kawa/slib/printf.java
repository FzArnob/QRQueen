package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.slf4j.Marker;

/* compiled from: printf.scm */
public class printf extends ModuleBody {
    public static final printf $instance;
    static final IntNum Lit0 = IntNum.make(-15);
    static final IntNum Lit1 = IntNum.make(0);
    static final PairWithPosition Lit10;
    static final Char Lit11 = Char.make(46);
    static final Char Lit12;
    static final Char Lit13;
    static final IntNum Lit14 = IntNum.make(2);
    static final IntNum Lit15 = IntNum.make(5);
    static final IntNum Lit16 = IntNum.make(9);
    static final IntNum Lit17 = IntNum.make(-1);
    static final Char Lit18 = Char.make(92);
    static final Char Lit19 = Char.make(110);
    static final PairWithPosition Lit2;
    static final Char Lit20 = Char.make(78);
    static final Char Lit21 = Char.make(10);
    static final Char Lit22 = Char.make(116);
    static final Char Lit23 = Char.make(84);
    static final Char Lit24 = Char.make(9);
    static final Char Lit25;
    static final Char Lit26;
    static final Char Lit27 = Char.make(12);
    static final Char Lit28 = Char.make(37);
    static final Char Lit29 = Char.make(32);
    static final Char Lit3;
    static final Char Lit30;
    static final Char Lit31;
    static final Char Lit32 = Char.make(104);
    static final PairWithPosition Lit33;
    static final SimpleSymbol Lit34;
    static final Char Lit35;
    static final Char Lit36 = Char.make(67);
    static final Char Lit37;
    static final Char Lit38;
    static final Char Lit39;
    static final Char Lit4 = Char.make(64);
    static final Char Lit40 = Char.make(65);
    static final Char Lit41;
    static final Char Lit42 = Char.make(73);
    static final Char Lit43;
    static final Char Lit44 = Char.make(85);
    static final IntNum Lit45 = IntNum.make(10);
    static final Char Lit46;
    static final Char Lit47 = Char.make(79);
    static final IntNum Lit48 = IntNum.make(8);
    static final Char Lit49;
    static final Char Lit5;
    static final IntNum Lit50 = IntNum.make(16);
    static final Char Lit51 = Char.make(88);
    static final Char Lit52;
    static final Char Lit53 = Char.make(66);
    static final Char Lit54;
    static final Char Lit55;
    static final Char Lit56 = Char.make(71);
    static final Char Lit57;
    static final Char Lit58 = Char.make(75);
    static final IntNum Lit59 = IntNum.make(6);
    static final Char Lit6;
    static final IntNum Lit60 = IntNum.make(-10);
    static final IntNum Lit61 = IntNum.make(3);
    static final FVector Lit62 = FVector.make(EllipticCurveJsonWebKey.Y_MEMBER_NAME, "z", "a", "f", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, RsaJsonWebKey.MODULUS_MEMBER_NAME, "u", "m", "", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "M", "G", "T", "P", "E", "Z", "Y");
    static final PairWithPosition Lit63 = PairWithPosition.make("i", LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1634315);
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("format-real").readResolve());
    static final Char Lit65 = Char.make(63);
    static final Char Lit66 = Char.make(42);
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("pad").readResolve());
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7 = IntNum.make(1);
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final Char Lit8 = Char.make(35);
    static final Char Lit9 = Char.make(48);
    public static final ModuleMethod fprintf;
    public static final ModuleMethod printf;
    public static final ModuleMethod sprintf;
    public static final boolean stdio$Clhex$Mnupper$Mncase$Qu = false;
    public static final ModuleMethod stdio$Cliprintf;
    public static final ModuleMethod stdio$Clparse$Mnfloat;
    public static final ModuleMethod stdio$Clround$Mnstring;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("fprintf").readResolve();
        Lit72 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("stdio:iprintf").readResolve();
        Lit71 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("stdio:round-string").readResolve();
        Lit70 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("stdio:parse-float").readResolve();
        Lit69 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("sprintf").readResolve();
        Lit68 = simpleSymbol5;
        Char make = Char.make(107);
        Lit57 = make;
        Char make2 = Char.make(103);
        Lit55 = make2;
        Char make3 = Char.make(69);
        Lit54 = make3;
        Char make4 = Char.make(98);
        Lit52 = make4;
        Char make5 = Char.make(120);
        Lit49 = make5;
        Char make6 = Char.make(111);
        Lit46 = make6;
        Char make7 = Char.make(117);
        Lit43 = make7;
        Char make8 = Char.make(68);
        Lit41 = make8;
        Char make9 = Char.make(97);
        Lit39 = make9;
        Char make10 = Char.make(83);
        Lit38 = make10;
        SimpleSymbol simpleSymbol6 = simpleSymbol5;
        Char make11 = Char.make(115);
        Lit37 = make11;
        SimpleSymbol simpleSymbol7 = simpleSymbol;
        Char make12 = Char.make(99);
        Lit35 = make12;
        SimpleSymbol simpleSymbol8 = simpleSymbol2;
        SimpleSymbol simpleSymbol9 = simpleSymbol3;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("printf").readResolve();
        Lit34 = simpleSymbol10;
        Char make13 = Char.make(100);
        Lit12 = make13;
        SimpleSymbol simpleSymbol11 = simpleSymbol10;
        Char make14 = Char.make(105);
        Lit3 = make14;
        SimpleSymbol simpleSymbol12 = simpleSymbol4;
        Char make15 = Char.make(102);
        Lit25 = make15;
        Char charR = make3;
        Char make16 = Char.make(101);
        Lit13 = make16;
        Lit33 = PairWithPosition.make(make12, PairWithPosition.make(make11, PairWithPosition.make(make9, PairWithPosition.make(make13, PairWithPosition.make(make14, PairWithPosition.make(make7, PairWithPosition.make(make6, PairWithPosition.make(make5, PairWithPosition.make(make4, PairWithPosition.make(make15, PairWithPosition.make(make16, PairWithPosition.make(make2, PairWithPosition.make(make, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781780), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781776), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781772), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781768), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777704), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777700), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777692), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777688), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777684), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777676), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777671);
        Char make17 = Char.make(76);
        Lit31 = make17;
        Char make18 = Char.make(108);
        Lit30 = make18;
        Char make19 = Char.make(70);
        Lit26 = make19;
        Char charR2 = make10;
        Lit10 = PairWithPosition.make(make16, PairWithPosition.make(make11, PairWithPosition.make(make15, PairWithPosition.make(make13, PairWithPosition.make(make18, PairWithPosition.make(charR, PairWithPosition.make(charR2, PairWithPosition.make(make19, PairWithPosition.make(make8, PairWithPosition.make(make17, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266280), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266276), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266272), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266268), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266264), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266260), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266252), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266247);
        Char make20 = Char.make(43);
        Lit6 = make20;
        Char make21 = Char.make(45);
        Lit5 = make21;
        Lit2 = PairWithPosition.make(make20, PairWithPosition.make(make21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446498);
        printf printf2 = new printf();
        $instance = printf2;
        stdio$Clparse$Mnfloat = new ModuleMethod(printf2, 22, simpleSymbol12, 8194);
        stdio$Clround$Mnstring = new ModuleMethod(printf2, 23, simpleSymbol9, 12291);
        stdio$Cliprintf = new ModuleMethod(printf2, 24, simpleSymbol8, -4094);
        fprintf = new ModuleMethod(printf2, 25, simpleSymbol7, -4094);
        printf = new ModuleMethod(printf2, 26, simpleSymbol11, -4095);
        sprintf = new ModuleMethod(printf2, 27, simpleSymbol6, -4094);
        printf2.run();
    }

    public printf() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String(Lit0, 16));
    }

    public static Object stdio$ClParseFloat(Object obj, Object obj2) {
        frame frame14 = new frame();
        frame14.str = obj;
        frame14.proc = obj2;
        Object obj3 = frame14.str;
        try {
            frame14.n = strings.stringLength((CharSequence) obj3);
            return frame14.lambda4real(Lit1, frame14.lambda$Fn1);
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, obj3);
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 22 ? stdio$ClParseFloat(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 22) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    /* compiled from: printf.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        int n;
        Object proc;
        Object str;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 12, (Object) null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:106");
            this.lambda$Fn1 = moduleMethod;
        }

        public static Boolean lambda1parseError() {
            return Boolean.FALSE;
        }

        public Object lambda2sign(Object obj, Object obj2) {
            if (Scheme.numLss.apply2(obj, Integer.valueOf(this.n)) == Boolean.FALSE) {
                return Values.empty;
            }
            Object obj3 = this.str;
            try {
                try {
                    char stringRef = strings.stringRef((CharSequence) obj3, ((Number) obj).intValue());
                    Object apply2 = Scheme.isEqv.apply2(Char.make(stringRef), printf.Lit5);
                    if (apply2 == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(stringRef), printf.Lit6) == Boolean.FALSE : apply2 == Boolean.FALSE) {
                        return Scheme.applyToArgs.apply3(obj2, obj, printf.Lit6);
                    }
                    return Scheme.applyToArgs.apply3(obj2, AddOp.$Pl.apply2(obj, printf.Lit7), Char.make(stringRef));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj3);
            }
        }

        public Object lambda3digits(Object obj, Object obj2) {
            Object obj3;
            Object obj4 = obj;
            while (true) {
                Object apply2 = Scheme.numGEq.apply2(obj4, Integer.valueOf(this.n));
                try {
                    boolean booleanValue = ((Boolean) apply2).booleanValue();
                    if (booleanValue) {
                        if (booleanValue) {
                            break;
                        }
                    } else {
                        Object obj5 = this.str;
                        try {
                            try {
                                boolean isCharNumeric = unicode.isCharNumeric(Char.make(strings.stringRef((CharSequence) obj5, ((Number) obj4).intValue())));
                                if (isCharNumeric) {
                                    if (!isCharNumeric) {
                                        break;
                                    }
                                } else {
                                    Char charR = printf.Lit8;
                                    Object obj6 = this.str;
                                    try {
                                        try {
                                            if (!characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj6, ((Number) obj4).intValue())))) {
                                                break;
                                            }
                                        } catch (ClassCastException e) {
                                            throw new WrongType(e, "string-ref", 2, obj4);
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "string-ref", 1, obj6);
                                    }
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-ref", 2, obj4);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-ref", 1, obj5);
                        }
                    }
                    obj4 = AddOp.$Pl.apply2(obj4, printf.Lit7);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
                }
            }
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            if (Scheme.numEqu.apply2(obj, obj4) != Boolean.FALSE) {
                obj3 = "0";
            } else {
                Object obj7 = this.str;
                try {
                    try {
                        try {
                            obj3 = strings.substring((CharSequence) obj7, ((Number) obj).intValue(), ((Number) obj4).intValue());
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "substring", 3, obj4);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "substring", 2, obj);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "substring", 1, obj7);
                }
            }
            return applyToArgs.apply3(obj2, obj4, obj3);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ba, code lost:
            if (kawa.standard.Scheme.isEqv.apply2(gnu.text.Char.make(r0), gnu.kawa.slib.printf.Lit11) == java.lang.Boolean.FALSE) goto L_0x00c3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
            return kawa.standard.Scheme.applyToArgs.apply2(r8, r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            return lambda1parseError();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda4real(java.lang.Object r7, java.lang.Object r8) {
            /*
                r6 = this;
                gnu.kawa.slib.printf$frame2 r0 = new gnu.kawa.slib.printf$frame2
                r0.<init>()
                r0.staticLink = r6
                r0.cont = r8
                gnu.expr.ModuleMethod r8 = r0.lambda$Fn5
            L_0x000b:
                gnu.kawa.functions.NumberCompare r0 = kawa.standard.Scheme.numLss
                int r1 = r6.n
                r2 = 1
                int r1 = r1 - r2
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                java.lang.Object r0 = r0.apply2(r7, r1)
                r1 = r0
                java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ ClassCastException -> 0x00dd }
                boolean r0 = r1.booleanValue()     // Catch:{ ClassCastException -> 0x00dd }
                r1 = 2
                java.lang.String r3 = "string-ref"
                if (r0 == 0) goto L_0x004f
                gnu.text.Char r0 = gnu.kawa.slib.printf.Lit8
                java.lang.Object r4 = r6.str
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0048 }
                r5 = r7
                java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x0041 }
                int r5 = r5.intValue()     // Catch:{ ClassCastException -> 0x0041 }
                char r4 = kawa.lib.strings.stringRef(r4, r5)
                gnu.text.Char r4 = gnu.text.Char.make(r4)
                boolean r0 = kawa.lib.characters.isChar$Eq(r0, r4)
                if (r0 == 0) goto L_0x00d6
                goto L_0x0051
            L_0x0041:
                r8 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r8, (java.lang.String) r3, (int) r1, (java.lang.Object) r7)
                throw r0
            L_0x0048:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r3, (int) r2, (java.lang.Object) r4)
                throw r8
            L_0x004f:
                if (r0 == 0) goto L_0x00d6
            L_0x0051:
                java.lang.Object r0 = r6.str
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ ClassCastException -> 0x00cf }
                gnu.kawa.functions.AddOp r2 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r4 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r2 = r2.apply2(r7, r4)
                r4 = r2
                java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ ClassCastException -> 0x00c8 }
                int r1 = r4.intValue()     // Catch:{ ClassCastException -> 0x00c8 }
                char r0 = kawa.lib.strings.stringRef(r0, r1)
                gnu.kawa.functions.IsEqv r1 = kawa.standard.Scheme.isEqv
                gnu.text.Char r2 = gnu.text.Char.make(r0)
                gnu.text.Char r3 = gnu.kawa.slib.printf.Lit12
                java.lang.Object r1 = r1.apply2(r2, r3)
                java.lang.Boolean r2 = java.lang.Boolean.FALSE
                if (r1 == r2) goto L_0x007d
                java.lang.Boolean r2 = java.lang.Boolean.FALSE
                if (r1 == r2) goto L_0x00ac
                goto L_0x00a2
            L_0x007d:
                gnu.kawa.functions.IsEqv r1 = kawa.standard.Scheme.isEqv
                gnu.text.Char r2 = gnu.text.Char.make(r0)
                gnu.text.Char r3 = gnu.kawa.slib.printf.Lit3
                java.lang.Object r1 = r1.apply2(r2, r3)
                java.lang.Boolean r2 = java.lang.Boolean.FALSE
                if (r1 == r2) goto L_0x0092
                java.lang.Boolean r2 = java.lang.Boolean.FALSE
                if (r1 == r2) goto L_0x00ac
                goto L_0x00a2
            L_0x0092:
                gnu.kawa.functions.IsEqv r1 = kawa.standard.Scheme.isEqv
                gnu.text.Char r2 = gnu.text.Char.make(r0)
                gnu.text.Char r3 = gnu.kawa.slib.printf.Lit13
                java.lang.Object r1 = r1.apply2(r2, r3)
                java.lang.Boolean r2 = java.lang.Boolean.FALSE
                if (r1 == r2) goto L_0x00ac
            L_0x00a2:
                gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r1 = gnu.kawa.slib.printf.Lit14
                java.lang.Object r7 = r0.apply2(r7, r1)
                goto L_0x000b
            L_0x00ac:
                gnu.kawa.functions.IsEqv r1 = kawa.standard.Scheme.isEqv
                gnu.text.Char r0 = gnu.text.Char.make(r0)
                gnu.text.Char r2 = gnu.kawa.slib.printf.Lit11
                java.lang.Object r0 = r1.apply2(r0, r2)
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r0 == r1) goto L_0x00c3
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                java.lang.Object r7 = r0.apply2(r8, r7)
                goto L_0x00dc
            L_0x00c3:
                java.lang.Boolean r7 = lambda1parseError()
                goto L_0x00dc
            L_0x00c8:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r3, (int) r1, (java.lang.Object) r2)
                throw r8
            L_0x00cf:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r3, (int) r2, (java.lang.Object) r0)
                throw r8
            L_0x00d6:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                java.lang.Object r7 = r0.apply2(r8, r7)
            L_0x00dc:
                return r7
            L_0x00dd:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r1 = -2
                java.lang.String r2 = "x"
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r2, (int) r1, (java.lang.Object) r0)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame.lambda4real(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 12 ? lambda5(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda5(Object obj, Object obj2, Object obj3, Object obj4) {
            frame0 frame0 = new frame0();
            frame0.staticLink = this;
            frame0.sgn = obj2;
            frame0.digs = obj3;
            frame0.ex = obj4;
            if (Scheme.numEqu.apply2(obj, Integer.valueOf(this.n)) != Boolean.FALSE) {
                return Scheme.applyToArgs.apply4(this.proc, frame0.sgn, frame0.digs, frame0.ex);
            }
            Object obj5 = this.str;
            try {
                try {
                    if (lists.memv(Char.make(strings.stringRef((CharSequence) obj5, ((Number) obj).intValue())), printf.Lit2) != Boolean.FALSE) {
                        return lambda4real(obj, frame0.lambda$Fn2);
                    }
                    IsEqv isEqv = Scheme.isEqv;
                    Object obj6 = this.str;
                    try {
                        try {
                            if (isEqv.apply2(Char.make(strings.stringRef((CharSequence) obj6, ((Number) obj).intValue())), printf.Lit4) == Boolean.FALSE) {
                                return Boolean.FALSE;
                            }
                            Object obj7 = this.str;
                            try {
                                frame0.num = numbers.string$To$Number((CharSequence) obj7);
                                if (frame0.num == Boolean.FALSE) {
                                    return lambda1parseError();
                                }
                                Object obj8 = frame0.num;
                                try {
                                    return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart((Complex) obj8)), frame0.lambda$Fn3);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "real-part", 1, obj8);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string->number", 1, obj7);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 2, obj);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-ref", 1, obj6);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-ref", 2, obj);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-ref", 1, obj5);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame6 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn11;
        frame staticLink;

        public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 5 ? lambda15(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15(Object obj, Object obj2) {
            frame7 frame7 = new frame7();
            frame7.staticLink = this;
            frame7.sgn = obj2;
            return this.staticLink.lambda3digits(obj, frame7.lambda$Fn12);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        Object sgn;
        frame6 staticLink;

        public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 4 ? lambda16(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object obj, Object obj2) {
            Object obj3;
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object obj4 = this.staticLink.cont;
            Char charR = printf.Lit5;
            Object obj5 = this.sgn;
            try {
                if (characters.isChar$Eq(charR, (Char) obj5)) {
                    try {
                        obj3 = AddOp.$Mn.apply1(numbers.string$To$Number((CharSequence) obj2));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string->number", 1, obj2);
                    }
                } else {
                    try {
                        obj3 = numbers.string$To$Number((CharSequence) obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string->number", 1, obj2);
                    }
                }
                return applyToArgs.apply3(obj4, obj, obj3);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "char=?", 2, obj5);
            }
        }
    }

    /* compiled from: printf.scm */
    public class frame2 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        frame staticLink;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 10, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:81");
            this.lambda$Fn6 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:78");
            this.lambda$Fn5 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 11 ? lambda9(obj) : super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda9(Object obj) {
            return this.staticLink.lambda2sign(obj, this.lambda$Fn6);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 10 ? lambda10(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object obj, Object obj2) {
            frame3 frame3 = new frame3();
            frame3.staticLink = this;
            frame3.sgn = obj2;
            return this.staticLink.lambda3digits(obj, frame3.lambda$Fn7);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        Object sgn;
        frame2 staticLink;

        public frame3() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 9, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
            this.lambda$Fn7 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 9 ? lambda11(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
            if (kawa.lib.characters.isChar$Eq(r1, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r2, ((java.lang.Number) r5).intValue()))) != false) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
            if (r1 != false) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return kawa.standard.Scheme.applyToArgs.apply2(r6, r5);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda11(java.lang.Object r5, java.lang.Object r6) {
            /*
                r4 = this;
                java.lang.String r0 = "string-ref"
                gnu.kawa.slib.printf$frame4 r1 = new gnu.kawa.slib.printf$frame4
                r1.<init>()
                r1.staticLink = r4
                r1.idigs = r6
                gnu.expr.ModuleMethod r6 = r1.lambda$Fn8
                gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numLss
                gnu.kawa.slib.printf$frame2 r2 = r4.staticLink
                gnu.kawa.slib.printf$frame r2 = r2.staticLink
                int r2 = r2.n
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                java.lang.Object r1 = r1.apply2(r5, r2)
                r2 = r1
                java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ ClassCastException -> 0x006e }
                boolean r1 = r2.booleanValue()     // Catch:{ ClassCastException -> 0x006e }
                if (r1 == 0) goto L_0x0056
                gnu.text.Char r1 = gnu.kawa.slib.printf.Lit11
                gnu.kawa.slib.printf$frame2 r2 = r4.staticLink
                gnu.kawa.slib.printf$frame r2 = r2.staticLink
                java.lang.Object r2 = r2.str
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ ClassCastException -> 0x004e }
                r3 = r5
                java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ ClassCastException -> 0x0046 }
                int r0 = r3.intValue()     // Catch:{ ClassCastException -> 0x0046 }
                char r0 = kawa.lib.strings.stringRef(r2, r0)
                gnu.text.Char r0 = gnu.text.Char.make(r0)
                boolean r0 = kawa.lib.characters.isChar$Eq(r1, r0)
                if (r0 == 0) goto L_0x0067
                goto L_0x0058
            L_0x0046:
                r6 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r2 = 2
                r1.<init>((java.lang.ClassCastException) r6, (java.lang.String) r0, (int) r2, (java.lang.Object) r5)
                throw r1
            L_0x004e:
                r5 = move-exception
                gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
                r1 = 1
                r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r0, (int) r1, (java.lang.Object) r2)
                throw r6
            L_0x0056:
                if (r1 == 0) goto L_0x0067
            L_0x0058:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r2 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r5 = r1.apply2(r5, r2)
                java.lang.Object r5 = r0.apply2(r6, r5)
                goto L_0x006d
            L_0x0067:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                java.lang.Object r5 = r0.apply2(r6, r5)
            L_0x006d:
                return r5
            L_0x006e:
                r5 = move-exception
                gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
                r0 = -2
                java.lang.String r2 = "x"
                r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r2, (int) r0, (java.lang.Object) r1)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame3.lambda11(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame4 extends ModuleBody {
        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        frame3 staticLink;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 7, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
            this.lambda$Fn9 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 8, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
            this.lambda$Fn8 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 8 ? lambda12(obj) : super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda12(Object obj) {
            return this.staticLink.staticLink.staticLink.lambda3digits(obj, this.lambda$Fn9);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 7 ? lambda13(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda13(Object obj, Object obj2) {
            frame5 frame5 = new frame5();
            frame5.staticLink = this;
            frame5.fdigs = obj2;
            ModuleMethod moduleMethod = frame5.lambda$Fn10;
            frame frame = this.staticLink.staticLink.staticLink;
            frame6 frame6 = new frame6();
            frame6.staticLink = frame;
            frame6.cont = moduleMethod;
            if (Scheme.numGEq.apply2(obj, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE) {
                return Scheme.applyToArgs.apply3(frame6.cont, obj, printf.Lit1);
            }
            Object obj3 = this.staticLink.staticLink.staticLink.str;
            try {
                try {
                    if (lists.memv(Char.make(strings.stringRef((CharSequence) obj3, ((Number) obj).intValue())), printf.Lit10) != Boolean.FALSE) {
                        return this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(obj, printf.Lit7), frame6.lambda$Fn11);
                    }
                    return Scheme.applyToArgs.apply3(frame6.cont, obj, printf.Lit1);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj3);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 7) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame5 extends ModuleBody {
        Object fdigs;
        final ModuleMethod lambda$Fn10;
        frame4 staticLink;

        public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 6 ? lambda14(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 6) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda14(Object obj, Object obj2) {
            FString stringAppend = strings.stringAppend("0", this.staticLink.idigs, this.fdigs);
            int stringLength = strings.stringLength(stringAppend);
            Object obj3 = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object obj4 = this.staticLink.idigs;
            try {
                Object apply2 = addOp.apply2(obj2, Integer.valueOf(strings.stringLength((CharSequence) obj4)));
                while (Scheme.numGEq.apply2(obj3, Integer.valueOf(stringLength)) == Boolean.FALSE) {
                    try {
                        if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef(stringAppend, ((Number) obj3).intValue())))) {
                            obj3 = AddOp.$Pl.apply2(obj3, printf.Lit7);
                            apply2 = AddOp.$Mn.apply2(apply2, printf.Lit7);
                        } else {
                            ApplyToArgs applyToArgs = Scheme.applyToArgs;
                            Object[] objArr = new Object[5];
                            objArr[0] = this.staticLink.staticLink.staticLink.cont;
                            objArr[1] = obj;
                            objArr[2] = this.staticLink.staticLink.sgn;
                            Object apply22 = AddOp.$Mn.apply2(obj3, printf.Lit7);
                            try {
                                objArr[3] = strings.substring(stringAppend, ((Number) apply22).intValue(), stringLength);
                                objArr[4] = apply2;
                                return applyToArgs.applyN(objArr);
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 2, apply22);
                            }
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-ref", 2, obj3);
                    }
                }
                return Scheme.applyToArgs.applyN(new Object[]{this.staticLink.staticLink.staticLink.cont, obj, this.staticLink.staticLink.sgn, "0", printf.Lit7});
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-length", 1, obj4);
            }
        }
    }

    /* compiled from: printf.scm */
    public class frame0 extends ModuleBody {
        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        Object num;
        Object sgn;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, (Object) null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:111");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 3, (Object) null, 12291);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:123");
            this.lambda$Fn3 = moduleMethod2;
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 2 ? lambda6(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
            if (kawa.lib.rnrs.unicode.isCharCi$Eq(r1, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r4, ((java.lang.Number) r7).intValue()))) != false) goto L_0x004a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
            if (r1 != false) goto L_0x004a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            return gnu.kawa.slib.printf.frame.lambda1parseError();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda6(java.lang.Object r7, java.lang.Object r8, java.lang.Object r9, java.lang.Object r10) {
            /*
                r6 = this;
                java.lang.String r0 = "string-ref"
                gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numEqu
                gnu.kawa.slib.printf$frame r2 = r6.staticLink
                int r2 = r2.n
                r3 = 1
                int r2 = r2 - r3
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                java.lang.Object r1 = r1.apply2(r7, r2)
                r2 = r1
                java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ ClassCastException -> 0x0076 }
                boolean r1 = r2.booleanValue()     // Catch:{ ClassCastException -> 0x0076 }
                r2 = 2
                if (r1 == 0) goto L_0x0048
                gnu.text.Char r1 = gnu.kawa.slib.printf.Lit3
                gnu.kawa.slib.printf$frame r4 = r6.staticLink
                java.lang.Object r4 = r4.str
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0041 }
                r5 = r7
                java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x003a }
                int r7 = r5.intValue()     // Catch:{ ClassCastException -> 0x003a }
                char r7 = kawa.lib.strings.stringRef(r4, r7)
                gnu.text.Char r7 = gnu.text.Char.make(r7)
                boolean r7 = kawa.lib.rnrs.unicode.isCharCi$Eq(r1, r7)
                if (r7 == 0) goto L_0x0071
                goto L_0x004a
            L_0x003a:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r9.<init>((java.lang.ClassCastException) r8, (java.lang.String) r0, (int) r2, (java.lang.Object) r7)
                throw r9
            L_0x0041:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r0, (int) r3, (java.lang.Object) r4)
                throw r8
            L_0x0048:
                if (r1 == 0) goto L_0x0071
            L_0x004a:
                gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
                r0 = 7
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r1 = 0
                gnu.kawa.slib.printf$frame r4 = r6.staticLink
                java.lang.Object r4 = r4.proc
                r0[r1] = r4
                java.lang.Object r1 = r6.sgn
                r0[r3] = r1
                java.lang.Object r1 = r6.digs
                r0[r2] = r1
                r1 = 3
                java.lang.Object r2 = r6.ex
                r0[r1] = r2
                r1 = 4
                r0[r1] = r8
                r8 = 5
                r0[r8] = r9
                r8 = 6
                r0[r8] = r10
                java.lang.Object r7 = r7.applyN(r0)
                goto L_0x0075
            L_0x0071:
                java.lang.Boolean r7 = gnu.kawa.slib.printf.frame.lambda1parseError()
            L_0x0075:
                return r7
            L_0x0076:
                r7 = move-exception
                gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
                r9 = -2
                java.lang.String r10 = "x"
                r8.<init>((java.lang.ClassCastException) r7, (java.lang.String) r10, (int) r9, (java.lang.Object) r1)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame0.lambda6(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 3 ? lambda7(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        /* access modifiers changed from: package-private */
        public Object lambda7(Object obj, Object obj2, Object obj3) {
            frame1 frame1 = new frame1();
            frame1.staticLink = this;
            frame1.sgn = obj;
            frame1.digs = obj2;
            frame1.ex = obj3;
            Object obj4 = this.num;
            try {
                return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart((Complex) obj4)), frame1.lambda$Fn4);
            } catch (ClassCastException e) {
                throw new WrongType(e, "imag-part", 1, obj4);
            }
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 3) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    public class frame1 extends ModuleBody {
        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn4;
        Object sgn;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
            this.lambda$Fn4 = moduleMethod;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 1 ? lambda8(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda8(Object obj, Object obj2, Object obj3) {
            return Scheme.applyToArgs.applyN(new Object[]{this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, obj, obj2, obj3});
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e5, code lost:
        if (r8 != false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0118, code lost:
        if ((((java.lang.Number) r3.lambda17dig(r0)).intValue() & 1) != 0) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0138, code lost:
        if (r7 != false) goto L_0x013a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:152:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stdio$ClRoundString(java.lang.CharSequence r17, java.lang.Object r18, java.lang.Object r19) {
        /*
            r0 = r18
            r1 = r19
            java.lang.String r2 = "string-ref"
            gnu.kawa.slib.printf$frame8 r3 = new gnu.kawa.slib.printf$frame8
            r3.<init>()
            r4 = r17
            r3.str = r4
            java.lang.CharSequence r4 = r3.str
            int r4 = kawa.lib.strings.stringLength(r4)
            r5 = 1
            int r4 = r4 - r5
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numLss
            gnu.math.IntNum r7 = Lit1
            java.lang.Object r6 = r6.apply2(r0, r7)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            r9 = 3
            java.lang.String r11 = "x"
            java.lang.String r12 = "substring"
            r13 = 2
            r14 = 0
            if (r6 == r8) goto L_0x002f
            java.lang.String r0 = ""
        L_0x002c:
            r6 = r0
            goto L_0x01ad
        L_0x002f:
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numEqu
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.Object r6 = r6.apply2(r8, r0)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            if (r6 == r8) goto L_0x0040
            java.lang.CharSequence r0 = r3.str
            goto L_0x002c
        L_0x0040:
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numLss
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.Object r6 = r6.apply2(r8, r0)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            java.lang.String r15 = "zero?"
            if (r6 == r8) goto L_0x00b3
            java.lang.Object[] r6 = new java.lang.Object[r13]
            r6[r14] = r7
            gnu.kawa.functions.AddOp r7 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            if (r1 == r8) goto L_0x005b
            r0 = r1
        L_0x005b:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.Object r0 = r7.apply2(r0, r8)
            r6[r5] = r0
            java.lang.Object r6 = kawa.lib.numbers.max(r6)
            r0 = r6
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x00ab }
            boolean r0 = kawa.lib.numbers.isZero(r0)
            if (r0 == 0) goto L_0x0075
            java.lang.CharSequence r0 = r3.str
            goto L_0x002c
        L_0x0075:
            java.lang.Object[] r0 = new java.lang.Object[r13]
            java.lang.CharSequence r7 = r3.str
            r0[r14] = r7
            r7 = r6
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x00a2 }
            int r6 = r7.intValue()     // Catch:{ ClassCastException -> 0x00a2 }
            java.lang.CharSequence r3 = r3.str
            char r3 = kawa.lib.strings.stringRef(r3, r4)
            gnu.text.Char r3 = gnu.text.Char.make(r3)
            boolean r3 = kawa.lib.rnrs.unicode.isCharNumeric(r3)
            if (r3 == 0) goto L_0x0095
            gnu.text.Char r3 = Lit9
            goto L_0x0097
        L_0x0095:
            gnu.text.Char r3 = Lit8
        L_0x0097:
            java.lang.CharSequence r3 = kawa.lib.strings.makeString(r6, r3)
            r0[r5] = r3
            gnu.lists.FString r0 = kawa.lib.strings.stringAppend(r0)
            goto L_0x002c
        L_0x00a2:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            java.lang.String r2 = "make-string"
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r5, (java.lang.Object) r6)
            throw r1
        L_0x00ab:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r15, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x00b3:
            java.lang.CharSequence r6 = r3.str
            gnu.kawa.functions.AddOp r7 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r8 = Lit7
            java.lang.Object r7 = r7.apply2(r0, r8)
            r16 = r7
            java.lang.Number r16 = (java.lang.Number) r16     // Catch:{ ClassCastException -> 0x024a }
            int r7 = r16.intValue()     // Catch:{ ClassCastException -> 0x024a }
            java.lang.CharSequence r6 = kawa.lib.strings.substring(r6, r14, r7)
            gnu.kawa.functions.AddOp r7 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r7 = r7.apply2(r8, r0)
            java.lang.Object r7 = r3.lambda17dig(r7)
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numGrt
            gnu.math.IntNum r10 = Lit15
            java.lang.Object r8 = r8.apply2(r7, r10)
            r16 = r8
            java.lang.Boolean r16 = (java.lang.Boolean) r16     // Catch:{ ClassCastException -> 0x0242 }
            boolean r8 = r16.booleanValue()     // Catch:{ ClassCastException -> 0x0242 }
            if (r8 == 0) goto L_0x00e8
            if (r8 == 0) goto L_0x01ad
            goto L_0x013a
        L_0x00e8:
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numEqu
            java.lang.Object r7 = r8.apply2(r7, r10)
            r8 = r7
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ ClassCastException -> 0x023a }
            boolean r7 = r8.booleanValue()     // Catch:{ ClassCastException -> 0x023a }
            if (r7 == 0) goto L_0x0138
            gnu.kawa.functions.AddOp r7 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r8 = Lit14
            java.lang.Object r7 = r7.apply2(r8, r0)
        L_0x00ff:
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numGrt
            java.lang.Integer r10 = java.lang.Integer.valueOf(r4)
            java.lang.Object r8 = r8.apply2(r7, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r8 == r10) goto L_0x011b
            java.lang.Object r4 = r3.lambda17dig(r0)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r4 = r4 & r5
            if (r4 == 0) goto L_0x01ad
            goto L_0x013a
        L_0x011b:
            java.lang.Object r8 = r3.lambda17dig(r7)
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ ClassCastException -> 0x0130 }
            boolean r8 = kawa.lib.numbers.isZero(r8)
            if (r8 == 0) goto L_0x013a
            gnu.kawa.functions.AddOp r8 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r10 = Lit7
            java.lang.Object r7 = r8.apply2(r7, r10)
            goto L_0x00ff
        L_0x0130:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r15, (int) r5, (java.lang.Object) r8)
            throw r0
        L_0x0138:
            if (r7 == 0) goto L_0x01ad
        L_0x013a:
            r4 = r0
        L_0x013b:
            java.lang.Object r0 = r3.lambda17dig(r4)
            gnu.kawa.functions.NumberCompare r7 = kawa.standard.Scheme.numLss
            gnu.math.IntNum r8 = Lit16
            java.lang.Object r7 = r7.apply2(r0, r8)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            java.lang.String r10 = "string-set!"
            if (r7 == r8) goto L_0x0186
            r3 = r6
            gnu.lists.CharSeq r3 = (gnu.lists.CharSeq) r3     // Catch:{ ClassCastException -> 0x017e }
            r7 = r4
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x0177 }
            int r4 = r7.intValue()     // Catch:{ ClassCastException -> 0x0177 }
            gnu.kawa.functions.AddOp r7 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r8 = Lit7
            java.lang.Object r7 = r7.apply2(r0, r8)
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x016d }
            java.lang.CharSequence r0 = kawa.lib.numbers.number$To$String(r7)
            char r0 = kawa.lib.strings.stringRef(r0, r14)
            kawa.lib.strings.stringSet$Ex(r3, r4, r0)
            goto L_0x01ad
        L_0x016d:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "number->string"
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r5, (java.lang.Object) r7)
            throw r0
        L_0x0177:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r13, (java.lang.Object) r4)
            throw r1
        L_0x017e:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r10, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x0186:
            r0 = r6
            gnu.lists.CharSeq r0 = (gnu.lists.CharSeq) r0     // Catch:{ ClassCastException -> 0x01a5 }
            r7 = r4
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x019e }
            int r7 = r7.intValue()     // Catch:{ ClassCastException -> 0x019e }
            r8 = 48
            kawa.lib.strings.stringSet$Ex(r0, r7, r8)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r7 = Lit7
            java.lang.Object r4 = r0.apply2(r4, r7)
            goto L_0x013b
        L_0x019e:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r13, (java.lang.Object) r4)
            throw r1
        L_0x01a5:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r10, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x01ad:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            if (r1 == r0) goto L_0x0239
            r0 = r6
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ ClassCastException -> 0x022f }
            int r0 = kawa.lib.strings.stringLength(r0)
            int r0 = r0 - r5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3 = r0
        L_0x01be:
            gnu.kawa.functions.NumberCompare r0 = kawa.standard.Scheme.numLEq
            java.lang.Object r4 = r0.apply2(r3, r1)
            r0 = r4
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ ClassCastException -> 0x0227 }
            boolean r0 = r0.booleanValue()     // Catch:{ ClassCastException -> 0x0227 }
            if (r0 == 0) goto L_0x01d0
            if (r0 == 0) goto L_0x020f
            goto L_0x01ea
        L_0x01d0:
            gnu.text.Char r0 = Lit9
            r4 = r6
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x021f }
            r7 = r3
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x0218 }
            int r7 = r7.intValue()     // Catch:{ ClassCastException -> 0x0218 }
            char r4 = kawa.lib.strings.stringRef(r4, r7)
            gnu.text.Char r4 = gnu.text.Char.make(r4)
            boolean r0 = kawa.lib.characters.isChar$Eq(r0, r4)
            if (r0 != 0) goto L_0x020f
        L_0x01ea:
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x0207 }
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r1 = Lit7
            java.lang.Object r1 = r0.apply2(r3, r1)
            r0 = r1
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x0200 }
            int r0 = r0.intValue()     // Catch:{ ClassCastException -> 0x0200 }
            java.lang.CharSequence r6 = kawa.lib.strings.substring(r6, r14, r0)
            goto L_0x0239
        L_0x0200:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r12, (int) r9, (java.lang.Object) r1)
            throw r2
        L_0x0207:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r12, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x020f:
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r4 = Lit7
            java.lang.Object r3 = r0.apply2(r3, r4)
            goto L_0x01be
        L_0x0218:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r13, (java.lang.Object) r3)
            throw r1
        L_0x021f:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x0227:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = -2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r11, (int) r2, (java.lang.Object) r4)
            throw r1
        L_0x022f:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "string-length"
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r5, (java.lang.Object) r6)
            throw r0
        L_0x0239:
            return r6
        L_0x023a:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = -2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r11, (int) r2, (java.lang.Object) r7)
            throw r1
        L_0x0242:
            r0 = move-exception
            r2 = -2
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r11, (int) r2, (java.lang.Object) r8)
            throw r1
        L_0x024a:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r12, (int) r9, (java.lang.Object) r7)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClRoundString(java.lang.CharSequence, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 23) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            return stdio$ClRoundString((CharSequence) obj, obj2, obj3);
        } catch (ClassCastException e) {
            throw new WrongType(e, "stdio:round-string", 1, obj);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 23) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof CharSequence)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    /* compiled from: printf.scm */
    public class frame8 extends ModuleBody {
        CharSequence str;

        public Object lambda17dig(Object obj) {
            try {
                char stringRef = strings.stringRef(this.str, ((Number) obj).intValue());
                if (!unicode.isCharNumeric(Char.make(stringRef))) {
                    return printf.Lit1;
                }
                return numbers.string$To$Number(strings.$make$string$(Char.make(stringRef)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-ref", 2, obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:148:0x034a, code lost:
        if (r13 == false) goto L_0x0364;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0354, code lost:
        r13 = kawa.standard.Scheme.numGEq.apply2(r8.precision, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
        r6 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0362, code lost:
        if (r13 != java.lang.Boolean.FALSE) goto L_0x0384;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:?, code lost:
        r6 = (java.lang.CharSequence) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0366, code lost:
        r13 = r8.precision;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x036f, code lost:
        r6 = kawa.lib.strings.substring(r6, r5, ((java.lang.Number) r13).intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0374, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x037b, code lost:
        throw new gnu.mapping.WrongType(r0, "substring", 3, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x037c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0383, code lost:
        throw new gnu.mapping.WrongType(r0, "substring", r9, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x038b, code lost:
        r13 = kawa.standard.Scheme.numLEq.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
        r6 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0399, code lost:
        if (r13 == java.lang.Boolean.FALSE) goto L_0x039c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03a0, code lost:
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L_0x03d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03a9, code lost:
        r12 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03bc, code lost:
        r6 = gnu.lists.LList.list2(r6, kawa.lib.strings.makeString(((java.lang.Number) r12).intValue(), r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03c5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03cb, code lost:
        throw new gnu.mapping.WrongType(r0, "make-string", r9, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03cc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03d3, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03db, code lost:
        r13 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:?, code lost:
        r10 = ((java.lang.Number) r13).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03f2, code lost:
        if (r8.leading$Mn0s == java.lang.Boolean.FALSE) goto L_0x03f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03f4, code lost:
        r11 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03f5, code lost:
        r6 = gnu.lists.LList.list2(kawa.lib.strings.makeString(r10, r11), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x03fd, code lost:
        r6 = r0.lambda21out$St(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0403, code lost:
        if (r6 == java.lang.Boolean.FALSE) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0405, code lost:
        r6 = kawa.lib.lists.cdr.apply1(r8.args);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x040f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0415, code lost:
        throw new gnu.mapping.WrongType(r0, "make-string", r9, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0416, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x041d, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x041e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0425, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0426, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x042d, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x047e, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)) != false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x048b, code lost:
        if (r14 != java.lang.Boolean.FALSE) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0498, code lost:
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L_0x049d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x049a, code lost:
        r14 = r8.lambda$Fn14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x049d, code lost:
        r14 = r8.pr;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x04a7, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)) == false) goto L_0x04b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x04a9, code lost:
        r8.pr = r8.width;
        r14 = r8.lambda$Fn15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x04b0, code lost:
        r14 = r8.lambda$Fn16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x04c5, code lost:
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r6)) != false) goto L_0x04d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x04d2, code lost:
        if (r6 != java.lang.Boolean.FALSE) goto L_0x04d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x050b, code lost:
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L_0x0552;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0521, code lost:
        if (kawa.standard.Scheme.numGrt.apply2(r8.width, gnu.kawa.functions.AddOp.$Mn.apply2(r8.precision, r8.pr)) == java.lang.Boolean.FALSE) goto L_0x0550;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0523, code lost:
        r6 = kawa.standard.Scheme.applyToArgs;
        r12 = r0.out;
        r5 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, gnu.kawa.functions.AddOp.$Mn.apply2(r8.precision, r8.pr));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x0540, code lost:
        r6.apply2(r12, kawa.lib.strings.makeString(((java.lang.Number) r5).intValue(), r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0548, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x054f, code lost:
        throw new gnu.mapping.WrongType(r0, "make-string", 1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0552, code lost:
        r5 = r8.os;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0557, code lost:
        if (r5 == java.lang.Boolean.FALSE) goto L_0x055b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x0559, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x055b, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x055e, code lost:
        if (((r5 + 1) & 1) == 0) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x0561, code lost:
        r5 = kawa.standard.Scheme.numLEq;
        r6 = r8.width;
        r9 = r8.os;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x0577, code lost:
        if (r5.apply2(r6, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r9))) == java.lang.Boolean.FALSE) goto L_0x0583;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0579, code lost:
        kawa.standard.Scheme.applyToArgs.apply2(r0.out, r8.os);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0583, code lost:
        r5 = kawa.standard.Scheme.applyToArgs;
        r6 = r0.out;
        r9 = gnu.kawa.functions.AddOp.$Mn;
        r12 = r8.width;
        r13 = r8.os;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x058f, code lost:
        r9 = r9.apply2(r12, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r13)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x05ac, code lost:
        if (r5.apply2(r6, kawa.lib.strings.makeString(((java.lang.Number) r9).intValue(), r11)) == java.lang.Boolean.FALSE) goto L_0x05b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x05ae, code lost:
        kawa.standard.Scheme.applyToArgs.apply2(r0.out, r8.os);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x05c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x05c8, code lost:
        throw new gnu.mapping.WrongType(r0, "make-string", 1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x05c9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x05d1, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", 1, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x05d2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x05da, code lost:
        throw new gnu.mapping.WrongType(r0, "string-length", 1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x05db, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x05e5, code lost:
        throw new gnu.mapping.WrongType(r0, org.jose4j.jwk.EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x05e6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x05ee, code lost:
        throw new gnu.mapping.WrongType(r0, "negative?", 1, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:433:0x0837, code lost:
        if (kawa.lib.rnrs.unicode.isCharCi$Eq((gnu.text.Char) r6, Lit55) != false) goto L_0x0847;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x0845, code lost:
        if (r6 != false) goto L_0x0847;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:?, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stdio$ClIprintf$V(java.lang.Object r16, java.lang.Object r17, java.lang.Object[] r18) {
        /*
            java.lang.String r1 = "substring"
            java.lang.String r2 = "string-ref"
            java.lang.String r3 = "negative?"
            java.lang.String r4 = "string-length"
            gnu.kawa.slib.printf$frame9 r0 = new gnu.kawa.slib.printf$frame9
            r0.<init>()
            r5 = r16
            r0.out = r5
            r5 = r17
            r0.format$Mnstring = r5
            r5 = 0
            r6 = r18
            gnu.lists.LList r6 = gnu.lists.LList.makeList(r6, r5)
            r0.args = r6
            java.lang.Object r6 = r0.format$Mnstring
            java.lang.String r7 = ""
            boolean r6 = gnu.kawa.functions.IsEqual.apply(r7, r6)
            if (r6 != 0) goto L_0x0939
            gnu.math.IntNum r6 = Lit17
            java.lang.Object r8 = r0.format$Mnstring
            r9 = 1
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x0930 }
            int r8 = kawa.lib.strings.stringLength(r8)
            java.lang.Object r10 = r0.format$Mnstring
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0927 }
            char r10 = kawa.lib.strings.stringRef(r10, r5)
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r0.fc = r10
            r0.fl = r8
            r0.pos = r6
            gnu.lists.LList r6 = r0.args
        L_0x0047:
            gnu.kawa.slib.printf$frame10 r8 = new gnu.kawa.slib.printf$frame10
            r8.<init>()
            r8.staticLink = r0
            r8.args = r6
            gnu.kawa.functions.AddOp r6 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r10 = Lit7
            java.lang.Object r11 = r0.pos
            java.lang.Object r6 = r6.apply2(r10, r11)
            r0.pos = r6
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numGEq
            java.lang.Object r10 = r0.pos
            int r11 = r0.fl
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r6 = r6.apply2(r10, r11)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x0073
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r0.fc = r6
            goto L_0x008a
        L_0x0073:
            java.lang.Object r6 = r0.format$Mnstring
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x091e }
            java.lang.Object r10 = r0.pos
            r12 = r10
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x0916 }
            int r10 = r12.intValue()     // Catch:{ ClassCastException -> 0x0916 }
            char r6 = kawa.lib.strings.stringRef(r6, r10)
            gnu.text.Char r6 = gnu.text.Char.make(r6)
            r0.fc = r6
        L_0x008a:
            boolean r6 = r0.lambda19isEndOfFormat()
            if (r6 == 0) goto L_0x009a
            if (r6 == 0) goto L_0x0096
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x093b
        L_0x0096:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            goto L_0x093b
        L_0x009a:
            gnu.kawa.functions.IsEqv r6 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit18
            java.lang.Object r12 = r0.fc
            java.lang.Object r6 = r6.apply2(r10, r12)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x0146
            r0.lambda18mustAdvance()
            java.lang.Object r6 = r0.fc
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit19
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00be
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00d5
            goto L_0x00ca
        L_0x00be:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit20
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00d5
        L_0x00ca:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            gnu.text.Char r11 = Lit21
            java.lang.Object r6 = r6.apply2(r10, r11)
            goto L_0x013e
        L_0x00d5:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit22
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00e6
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00fd
            goto L_0x00f2
        L_0x00e6:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit23
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00fd
        L_0x00f2:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            gnu.text.Char r11 = Lit24
            java.lang.Object r6 = r6.apply2(r10, r11)
            goto L_0x013e
        L_0x00fd:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit25
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x010e
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x0125
            goto L_0x011a
        L_0x010e:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit26
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x0125
        L_0x011a:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            gnu.text.Char r11 = Lit27
            java.lang.Object r6 = r6.apply2(r10, r11)
            goto L_0x013e
        L_0x0125:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit21
            java.lang.Object r6 = r10.apply2(r6, r11)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x0134
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            goto L_0x013e
        L_0x0134:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            java.lang.Object r11 = r0.fc
            java.lang.Object r6 = r6.apply2(r10, r11)
        L_0x013e:
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x02de
            java.lang.Object r6 = r8.args
            goto L_0x0047
        L_0x0146:
            gnu.kawa.functions.IsEqv r6 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit28
            java.lang.Object r12 = r0.fc
            java.lang.Object r6 = r6.apply2(r10, r12)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x0903
            r0.lambda18mustAdvance()
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            gnu.math.IntNum r15 = Lit1
            gnu.math.IntNum r11 = Lit17
            r8.precision = r11
            r8.width = r15
            r8.leading$Mn0s = r14
            r8.alternate$Mnform = r13
            r8.blank = r12
            r8.signed = r10
            r8.left$Mnadjust = r6
            gnu.mapping.Procedure r6 = r8.pad
            r8.pad = r6
        L_0x0177:
            java.lang.Object r6 = r0.fc
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit5
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x018a
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.left$Mnadjust = r6
            goto L_0x01cd
        L_0x018a:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit6
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x019b
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.signed = r6
            goto L_0x01cd
        L_0x019b:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r11 = Lit29
            java.lang.Object r10 = r10.apply2(r6, r11)
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r10 == r12) goto L_0x01ac
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.blank = r6
            goto L_0x01cd
        L_0x01ac:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r12 = Lit8
            java.lang.Object r10 = r10.apply2(r6, r12)
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r10 == r12) goto L_0x01bd
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.alternate$Mnform = r6
            goto L_0x01cd
        L_0x01bd:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r12 = Lit9
            java.lang.Object r6 = r10.apply2(r6, r12)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x01d1
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.leading$Mn0s = r6
        L_0x01cd:
            r0.lambda18mustAdvance()
            goto L_0x0177
        L_0x01d1:
            java.lang.Object r6 = r8.left$Mnadjust
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x01db
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r8.leading$Mn0s = r6
        L_0x01db:
            java.lang.Object r6 = r8.signed
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x01e5
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r8.blank = r6
        L_0x01e5:
            java.lang.Object r6 = r8.lambda22readFormatNumber()
            r8.width = r6
            java.lang.Object r6 = r8.width
            gnu.math.RealNum r6 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r6)     // Catch:{ ClassCastException -> 0x08fa }
            boolean r6 = kawa.lib.numbers.isNegative(r6)
            if (r6 == 0) goto L_0x0205
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r8.left$Mnadjust = r6
            gnu.kawa.functions.AddOp r6 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r10 = r8.width
            java.lang.Object r6 = r6.apply1(r10)
            r8.width = r6
        L_0x0205:
            gnu.kawa.functions.IsEqv r6 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit11
            java.lang.Object r13 = r0.fc
            java.lang.Object r6 = r6.apply2(r10, r13)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x021c
            r0.lambda18mustAdvance()
            java.lang.Object r6 = r8.lambda22readFormatNumber()
            r8.precision = r6
        L_0x021c:
            java.lang.Object r6 = r0.fc
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r13 = Lit30
            java.lang.Object r10 = r10.apply2(r6, r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r10 == r13) goto L_0x022f
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r10 == r6) goto L_0x024f
            goto L_0x024c
        L_0x022f:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r13 = Lit31
            java.lang.Object r10 = r10.apply2(r6, r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r10 == r13) goto L_0x0240
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r10 == r6) goto L_0x024f
            goto L_0x024c
        L_0x0240:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            gnu.text.Char r13 = Lit32
            java.lang.Object r6 = r10.apply2(r6, r13)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x024f
        L_0x024c:
            r0.lambda18mustAdvance()
        L_0x024f:
            java.lang.Object r6 = r8.args
            boolean r6 = kawa.lib.lists.isNull(r6)
            r10 = 3
            if (r6 == 0) goto L_0x0291
            java.lang.Object r6 = r0.fc
            gnu.text.Char r6 = (gnu.text.Char) r6     // Catch:{ ClassCastException -> 0x0287 }
            gnu.text.Char r6 = kawa.lib.rnrs.unicode.charDowncase(r6)
            gnu.lists.PairWithPosition r13 = Lit33
            java.lang.Object r6 = kawa.lib.lists.memv(r6, r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r6 == r13) goto L_0x0291
            gnu.mapping.SimpleSymbol r6 = Lit34
            java.lang.Object[] r13 = new java.lang.Object[r10]
            java.lang.String r14 = "wrong number of arguments"
            r13[r5] = r14
            gnu.lists.LList r14 = r0.args
            int r14 = kawa.lib.lists.length(r14)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r13[r9] = r14
            java.lang.Object r14 = r0.format$Mnstring
            r15 = 2
            r13[r15] = r14
            kawa.lib.misc.error$V(r6, r13)
            goto L_0x0291
        L_0x0287:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "char-downcase"
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x0291:
            java.lang.Object r6 = r0.fc
            gnu.kawa.functions.IsEqv r13 = kawa.standard.Scheme.isEqv
            gnu.text.Char r14 = Lit35
            java.lang.Object r13 = r13.apply2(r6, r14)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x02a4
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x02e1
            goto L_0x02b0
        L_0x02a4:
            gnu.kawa.functions.IsEqv r13 = kawa.standard.Scheme.isEqv
            gnu.text.Char r14 = Lit36
            java.lang.Object r13 = r13.apply2(r6, r14)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x02e1
        L_0x02b0:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            gnu.expr.GenericProc r11 = kawa.lib.lists.car
            java.lang.Object r12 = r8.args
            java.lang.Object r11 = r11.apply1(r12)
            boolean r12 = r11 instanceof java.lang.Object[]
            if (r12 == 0) goto L_0x02c3
            java.lang.Object[] r11 = (java.lang.Object[]) r11
            goto L_0x02c8
        L_0x02c3:
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r12[r5] = r11
            r11 = r12
        L_0x02c8:
            java.lang.CharSequence r11 = kawa.lib.strings.$make$string$(r11)
            java.lang.Object r6 = r6.apply2(r10, r11)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x02de
            gnu.expr.GenericProc r6 = kawa.lib.lists.cdr
            java.lang.Object r8 = r8.args
            java.lang.Object r6 = r6.apply1(r8)
            goto L_0x0047
        L_0x02de:
            r0 = r6
            goto L_0x093b
        L_0x02e1:
            gnu.kawa.functions.IsEqv r13 = kawa.standard.Scheme.isEqv
            gnu.text.Char r14 = Lit37
            java.lang.Object r13 = r13.apply2(r6, r14)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            java.lang.String r15 = "symbol->string"
            java.lang.String r10 = "make-string"
            if (r13 == r14) goto L_0x02f6
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x0436
            goto L_0x0302
        L_0x02f6:
            gnu.kawa.functions.IsEqv r13 = kawa.standard.Scheme.isEqv
            gnu.text.Char r14 = Lit38
            java.lang.Object r13 = r13.apply2(r6, r14)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x0436
        L_0x0302:
            gnu.expr.GenericProc r6 = kawa.lib.lists.car
            java.lang.Object r13 = r8.args
            java.lang.Object r6 = r6.apply1(r13)
            boolean r6 = kawa.lib.misc.isSymbol(r6)
            if (r6 == 0) goto L_0x0327
            gnu.expr.GenericProc r6 = kawa.lib.lists.car
            java.lang.Object r13 = r8.args
            java.lang.Object r6 = r6.apply1(r13)
            gnu.mapping.Symbol r6 = (gnu.mapping.Symbol) r6     // Catch:{ ClassCastException -> 0x031f }
            java.lang.String r6 = kawa.lib.misc.symbol$To$String(r6)
            goto L_0x033e
        L_0x031f:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r15, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x0327:
            gnu.expr.GenericProc r6 = kawa.lib.lists.car
            java.lang.Object r13 = r8.args
            java.lang.Object r6 = r6.apply1(r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r6 != r13) goto L_0x0336
            java.lang.String r6 = "(NULL)"
            goto L_0x033e
        L_0x0336:
            gnu.expr.GenericProc r6 = kawa.lib.lists.car
            java.lang.Object r13 = r8.args
            java.lang.Object r6 = r6.apply1(r13)
        L_0x033e:
            java.lang.Object r13 = r8.precision
            gnu.math.RealNum r13 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r13)     // Catch:{ ClassCastException -> 0x042e }
            boolean r13 = kawa.lib.numbers.isNegative(r13)
            if (r13 == 0) goto L_0x034d
            if (r13 != 0) goto L_0x0384
            goto L_0x0364
        L_0x034d:
            gnu.kawa.functions.NumberCompare r13 = kawa.standard.Scheme.numGEq
            java.lang.Object r14 = r8.precision
            r15 = r6
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x0426 }
            int r15 = kawa.lib.strings.stringLength(r15)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            java.lang.Object r13 = r13.apply2(r14, r15)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 != r14) goto L_0x0384
        L_0x0364:
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x037c }
            java.lang.Object r13 = r8.precision
            r14 = r13
            java.lang.Number r14 = (java.lang.Number) r14     // Catch:{ ClassCastException -> 0x0374 }
            int r13 = r14.intValue()     // Catch:{ ClassCastException -> 0x0374 }
            java.lang.CharSequence r6 = kawa.lib.strings.substring(r6, r5, r13)
            goto L_0x0384
        L_0x0374:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r3 = 3
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r1, (int) r3, (java.lang.Object) r13)
            throw r2
        L_0x037c:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x0384:
            gnu.kawa.functions.NumberCompare r13 = kawa.standard.Scheme.numLEq
            java.lang.Object r14 = r8.width
            r15 = r6
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x041e }
            int r15 = kawa.lib.strings.stringLength(r15)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            java.lang.Object r13 = r13.apply2(r14, r15)
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x039c
            goto L_0x03fd
        L_0x039c:
            java.lang.Object r13 = r8.left$Mnadjust
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x03d4
            gnu.kawa.functions.AddOp r12 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r13 = r8.width
            r14 = r6
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14     // Catch:{ ClassCastException -> 0x03cc }
            int r14 = kawa.lib.strings.stringLength(r14)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.Object r12 = r12.apply2(r13, r14)
            r13 = r12
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ ClassCastException -> 0x03c5 }
            int r10 = r13.intValue()     // Catch:{ ClassCastException -> 0x03c5 }
            java.lang.CharSequence r10 = kawa.lib.strings.makeString(r10, r11)
            gnu.lists.Pair r6 = gnu.lists.LList.list2(r6, r10)
            goto L_0x03fd
        L_0x03c5:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r9, (java.lang.Object) r12)
            throw r1
        L_0x03cc:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x03d4:
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r14 = r8.width
            r15 = r6
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x0416 }
            int r15 = kawa.lib.strings.stringLength(r15)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            java.lang.Object r13 = r13.apply2(r14, r15)
            r14 = r13
            java.lang.Number r14 = (java.lang.Number) r14     // Catch:{ ClassCastException -> 0x040f }
            int r10 = r14.intValue()     // Catch:{ ClassCastException -> 0x040f }
            java.lang.Object r13 = r8.leading$Mn0s
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 == r14) goto L_0x03f5
            r11 = r12
        L_0x03f5:
            java.lang.CharSequence r10 = kawa.lib.strings.makeString(r10, r11)
            gnu.lists.Pair r6 = gnu.lists.LList.list2(r10, r6)
        L_0x03fd:
            java.lang.Object r6 = r0.lambda21out$St(r6)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r6 == r10) goto L_0x02de
            gnu.expr.GenericProc r6 = kawa.lib.lists.cdr
            java.lang.Object r8 = r8.args
            java.lang.Object r6 = r6.apply1(r8)
            goto L_0x0047
        L_0x040f:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r9, (java.lang.Object) r13)
            throw r1
        L_0x0416:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x041e:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x0426:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x042e:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r9, (java.lang.Object) r13)
            throw r0
        L_0x0436:
            gnu.kawa.functions.IsEqv r12 = kawa.standard.Scheme.isEqv
            gnu.text.Char r13 = Lit39
            java.lang.Object r12 = r12.apply2(r6, r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r12 == r13) goto L_0x0447
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r12 == r13) goto L_0x05ef
            goto L_0x0453
        L_0x0447:
            gnu.kawa.functions.IsEqv r12 = kawa.standard.Scheme.isEqv
            gnu.text.Char r13 = Lit40
            java.lang.Object r12 = r12.apply2(r6, r13)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r12 == r13) goto L_0x05ef
        L_0x0453:
            java.lang.Object r6 = r8.precision
            r8.pr = r6
            r8.os = r7
            gnu.expr.GenericProc r6 = kawa.lib.lists.car
            java.lang.Object r12 = r8.args
            java.lang.Object r6 = r6.apply1(r12)
            java.lang.Object r12 = r8.alternate$Mnform
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r12 == r13) goto L_0x046a
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            goto L_0x046c
        L_0x046a:
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
        L_0x046c:
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            java.lang.Object r14 = r8.left$Mnadjust
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            if (r14 == r15) goto L_0x0489
            java.lang.Object r14 = r8.pr
            gnu.math.RealNum r14 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)     // Catch:{ ClassCastException -> 0x0481 }
            boolean r14 = kawa.lib.numbers.isNegative(r14)
            if (r14 == 0) goto L_0x0494
            goto L_0x048d
        L_0x0481:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r9, (java.lang.Object) r14)
            throw r0
        L_0x0489:
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            if (r14 == r15) goto L_0x0494
        L_0x048d:
            gnu.math.IntNum r14 = Lit1
            r8.pr = r14
            gnu.expr.ModuleMethod r14 = r8.lambda$Fn13
            goto L_0x04b2
        L_0x0494:
            java.lang.Object r14 = r8.left$Mnadjust
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            if (r14 == r15) goto L_0x049d
            gnu.expr.ModuleMethod r14 = r8.lambda$Fn14
            goto L_0x04b2
        L_0x049d:
            java.lang.Object r14 = r8.pr
            gnu.math.RealNum r14 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)     // Catch:{ ClassCastException -> 0x05e6 }
            boolean r14 = kawa.lib.numbers.isNegative(r14)
            if (r14 == 0) goto L_0x04b0
            java.lang.Object r14 = r8.width
            r8.pr = r14
            gnu.expr.ModuleMethod r14 = r8.lambda$Fn15
            goto L_0x04b2
        L_0x04b0:
            gnu.expr.ModuleMethod r14 = r8.lambda$Fn16
        L_0x04b2:
            gnu.kawa.slib.genwrite.genericWrite(r6, r12, r13, r14)
            java.lang.Object r6 = r8.left$Mnadjust
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r6 == r12) goto L_0x04d0
            java.lang.Object r6 = r8.precision
            gnu.math.RealNum r6 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r6)     // Catch:{ ClassCastException -> 0x04c8 }
            boolean r6 = kawa.lib.numbers.isNegative(r6)
            if (r6 == 0) goto L_0x0507
            goto L_0x04d4
        L_0x04c8:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r9, (java.lang.Object) r6)
            throw r0
        L_0x04d0:
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r6 == r12) goto L_0x0507
        L_0x04d4:
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numGrt
            java.lang.Object r12 = r8.width
            java.lang.Object r13 = r8.pr
            java.lang.Object r6 = r6.apply2(r12, r13)
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r6 == r12) goto L_0x05b7
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r12 = r0.out
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r14 = r8.width
            java.lang.Object r15 = r8.pr
            java.lang.Object r13 = r13.apply2(r14, r15)
            r14 = r13
            java.lang.Number r14 = (java.lang.Number) r14     // Catch:{ ClassCastException -> 0x0500 }
            int r10 = r14.intValue()     // Catch:{ ClassCastException -> 0x0500 }
            java.lang.CharSequence r10 = kawa.lib.strings.makeString(r10, r11)
            r6.apply2(r12, r10)
            goto L_0x05b7
        L_0x0500:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r9, (java.lang.Object) r13)
            throw r1
        L_0x0507:
            java.lang.Object r6 = r8.left$Mnadjust
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r6 == r12) goto L_0x0552
            gnu.kawa.functions.NumberCompare r6 = kawa.standard.Scheme.numGrt
            java.lang.Object r12 = r8.width
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r14 = r8.precision
            java.lang.Object r15 = r8.pr
            java.lang.Object r13 = r13.apply2(r14, r15)
            java.lang.Object r6 = r6.apply2(r12, r13)
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
            if (r6 == r12) goto L_0x0550
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r12 = r0.out
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r14 = r8.width
            gnu.kawa.functions.AddOp r15 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r5 = r8.precision
            java.lang.Object r9 = r8.pr
            java.lang.Object r5 = r15.apply2(r5, r9)
            java.lang.Object r5 = r13.apply2(r14, r5)
            r9 = r5
            java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ ClassCastException -> 0x0548 }
            int r5 = r9.intValue()     // Catch:{ ClassCastException -> 0x0548 }
            java.lang.CharSequence r5 = kawa.lib.strings.makeString(r5, r11)
            r6.apply2(r12, r5)
            goto L_0x0550
        L_0x0548:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r6 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r6, (java.lang.Object) r5)
            throw r1
        L_0x0550:
            r6 = 1
            goto L_0x05b7
        L_0x0552:
            r6 = 1
            java.lang.Object r5 = r8.os
            java.lang.Boolean r9 = java.lang.Boolean.FALSE     // Catch:{ ClassCastException -> 0x05db }
            if (r5 == r9) goto L_0x055b
            r5 = 1
            goto L_0x055c
        L_0x055b:
            r5 = 0
        L_0x055c:
            int r5 = r5 + r6
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0561
            goto L_0x05b7
        L_0x0561:
            gnu.kawa.functions.NumberCompare r5 = kawa.standard.Scheme.numLEq
            java.lang.Object r6 = r8.width
            java.lang.Object r9 = r8.os
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x05d2 }
            int r9 = kawa.lib.strings.stringLength(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0583
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r6 = r0.out
            java.lang.Object r9 = r8.os
            r5.apply2(r6, r9)
            goto L_0x05b7
        L_0x0583:
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r6 = r0.out
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r12 = r8.width
            java.lang.Object r13 = r8.os
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13     // Catch:{ ClassCastException -> 0x05c9 }
            int r13 = kawa.lib.strings.stringLength(r13)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            java.lang.Object r9 = r9.apply2(r12, r13)
            r12 = r9
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x05c1 }
            int r9 = r12.intValue()     // Catch:{ ClassCastException -> 0x05c1 }
            java.lang.CharSequence r9 = kawa.lib.strings.makeString(r9, r11)
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x05b7
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r6 = r0.out
            java.lang.Object r9 = r8.os
            r5.apply2(r6, r9)
        L_0x05b7:
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
            goto L_0x0670
        L_0x05c1:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r10, (int) r2, (java.lang.Object) r9)
            throw r1
        L_0x05c9:
            r0 = move-exception
            r2 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r2, (java.lang.Object) r13)
            throw r0
        L_0x05d2:
            r0 = move-exception
            r2 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r2, (java.lang.Object) r9)
            throw r0
        L_0x05db:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = -2
            java.lang.String r3 = "x"
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r5)
            throw r0
        L_0x05e6:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r14)
            throw r0
        L_0x05ef:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit12
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0600
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
            goto L_0x0650
        L_0x0600:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit41
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0611
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
            goto L_0x0650
        L_0x0611:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit3
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0622
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
            goto L_0x0650
        L_0x0622:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit42
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0633
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
            goto L_0x0650
        L_0x0633:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit43
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0644
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
            goto L_0x0650
        L_0x0644:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit44
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0676
        L_0x0650:
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            gnu.math.IntNum r6 = Lit45
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            java.lang.Object r5 = r8.lambda24integerConvert(r5, r6, r9)
            java.lang.Object r5 = r0.lambda21out$St(r5)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
        L_0x0670:
            r5 = 0
            goto L_0x0913
        L_0x0673:
            r0 = r5
            goto L_0x093b
        L_0x0676:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit46
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x0687
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x06b4
            goto L_0x0693
        L_0x0687:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit47
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x06b4
        L_0x0693:
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            gnu.math.IntNum r6 = Lit48
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            java.lang.Object r5 = r8.lambda24integerConvert(r5, r6, r9)
            java.lang.Object r5 = r0.lambda21out$St(r5)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
            goto L_0x0670
        L_0x06b4:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit49
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x06e8
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            gnu.math.IntNum r6 = Lit50
            boolean r9 = stdio$Clhex$Mnupper$Mncase$Qu
            if (r9 == 0) goto L_0x06d1
            gnu.expr.ModuleMethod r9 = kawa.lib.rnrs.unicode.string$Mndowncase
            goto L_0x06d3
        L_0x06d1:
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
        L_0x06d3:
            java.lang.Object r5 = r8.lambda24integerConvert(r5, r6, r9)
            java.lang.Object r5 = r0.lambda21out$St(r5)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
            goto L_0x0670
        L_0x06e8:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit51
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x071d
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            gnu.math.IntNum r6 = Lit50
            boolean r9 = stdio$Clhex$Mnupper$Mncase$Qu
            if (r9 == 0) goto L_0x0705
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            goto L_0x0707
        L_0x0705:
            gnu.expr.ModuleMethod r9 = kawa.lib.rnrs.unicode.string$Mnupcase
        L_0x0707:
            java.lang.Object r5 = r8.lambda24integerConvert(r5, r6, r9)
            java.lang.Object r5 = r0.lambda21out$St(r5)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
            goto L_0x0670
        L_0x071d:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit52
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x072e
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x075c
            goto L_0x073a
        L_0x072e:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit53
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r5 == r9) goto L_0x075c
        L_0x073a:
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            gnu.math.IntNum r6 = Lit14
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            java.lang.Object r5 = r8.lambda24integerConvert(r5, r6, r9)
            java.lang.Object r5 = r0.lambda21out$St(r5)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r6 = r8.args
            java.lang.Object r6 = r5.apply1(r6)
            goto L_0x0670
        L_0x075c:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r9 = Lit28
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x0778
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r6 = r0.out
            java.lang.Object r5 = r5.apply2(r6, r9)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0673
            java.lang.Object r6 = r8.args
            goto L_0x0670
        L_0x0778:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit25
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x078d
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x078a:
            r5 = 0
            goto L_0x08c4
        L_0x078d:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit26
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x079e
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x079e:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit13
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x07af
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x07af:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit54
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x07c0
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x07c0:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit55
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x07d1
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x07d1:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit56
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x07e2
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x07e2:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit57
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r5 == r10) goto L_0x07f3
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
            goto L_0x07ff
        L_0x07f3:
            gnu.kawa.functions.IsEqv r5 = kawa.standard.Scheme.isEqv
            gnu.text.Char r10 = Lit58
            java.lang.Object r5 = r5.apply2(r6, r10)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x078a
        L_0x07ff:
            gnu.expr.GenericProc r5 = kawa.lib.lists.car
            java.lang.Object r6 = r8.args
            java.lang.Object r5 = r5.apply1(r6)
            java.lang.Object r6 = r0.fc
            gnu.kawa.slib.printf$frame11 r9 = new gnu.kawa.slib.printf$frame11
            r9.<init>()
            r9.staticLink = r8
            r9.fc = r6
            java.lang.Object r6 = r8.precision
            gnu.math.RealNum r6 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r6)     // Catch:{ ClassCastException -> 0x08bb }
            boolean r6 = kawa.lib.numbers.isNegative(r6)
            if (r6 == 0) goto L_0x0823
            gnu.math.IntNum r6 = Lit59
            r8.precision = r6
            goto L_0x084b
        L_0x0823:
            java.lang.Object r6 = r8.precision
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ ClassCastException -> 0x08b0 }
            boolean r6 = kawa.lib.numbers.isZero(r6)
            if (r6 == 0) goto L_0x0845
            java.lang.Object r6 = r9.fc
            gnu.text.Char r6 = (gnu.text.Char) r6     // Catch:{ ClassCastException -> 0x083a }
            gnu.text.Char r10 = Lit55
            boolean r6 = kawa.lib.rnrs.unicode.isCharCi$Eq(r6, r10)
            if (r6 == 0) goto L_0x084b
            goto L_0x0847
        L_0x083a:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "char-ci=?"
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r6)
            throw r0
        L_0x0845:
            if (r6 == 0) goto L_0x084b
        L_0x0847:
            gnu.math.IntNum r6 = Lit7
            r8.precision = r6
        L_0x084b:
            boolean r6 = kawa.lib.numbers.isNumber(r5)
            java.lang.String r10 = "???"
            if (r6 == 0) goto L_0x0869
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x085e }
            java.lang.Number r5 = kawa.lib.numbers.exact$To$Inexact(r5)
            java.lang.CharSequence r5 = kawa.lib.numbers.number$To$String(r5)
            goto L_0x0887
        L_0x085e:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "exact->inexact"
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r5)
            throw r0
        L_0x0869:
            boolean r6 = kawa.lib.strings.isString(r5)
            if (r6 == 0) goto L_0x0870
            goto L_0x0887
        L_0x0870:
            boolean r6 = kawa.lib.misc.isSymbol(r5)
            if (r6 == 0) goto L_0x0886
            gnu.mapping.Symbol r5 = (gnu.mapping.Symbol) r5     // Catch:{ ClassCastException -> 0x087d }
            java.lang.String r5 = kawa.lib.misc.symbol$To$String(r5)
            goto L_0x0887
        L_0x087d:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r15, (int) r2, (java.lang.Object) r5)
            throw r0
        L_0x0886:
            r5 = r10
        L_0x0887:
            gnu.mapping.Procedure r6 = r9.format$Mnreal
            r9.format$Mnreal = r6
            gnu.expr.ModuleMethod r6 = r9.lambda$Fn17
            java.lang.Object r5 = stdio$ClParseFloat(r5, r6)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x0898
            r6 = r5
            r5 = 0
            goto L_0x089f
        L_0x0898:
            r5 = 0
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.Object r6 = r8.lambda23pad$V(r10, r6)
        L_0x089f:
            java.lang.Object r6 = r0.lambda21out$St(r6)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r6 == r9) goto L_0x02de
            gnu.expr.GenericProc r6 = kawa.lib.lists.cdr
            java.lang.Object r8 = r8.args
            java.lang.Object r6 = r6.apply1(r8)
            goto L_0x0913
        L_0x08b0:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "zero?"
            r4 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r4, (java.lang.Object) r6)
            throw r0
        L_0x08bb:
            r0 = move-exception
            r4 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r4, (java.lang.Object) r6)
            throw r0
        L_0x08c4:
            boolean r6 = r0.lambda19isEndOfFormat()
            if (r6 == 0) goto L_0x08cf
            java.lang.Object r0 = r0.lambda20incomplete()
            goto L_0x093b
        L_0x08cf:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r0.out
            java.lang.Object r6 = r6.apply2(r10, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r6 == r9) goto L_0x02de
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r9 = r0.out
            java.lang.Object r10 = r0.fc
            java.lang.Object r6 = r6.apply2(r9, r10)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r6 == r9) goto L_0x02de
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r9 = r0.out
            gnu.text.Char r10 = Lit65
            java.lang.Object r6 = r6.apply2(r9, r10)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r6 == r9) goto L_0x02de
            java.lang.Object r6 = r8.args
            goto L_0x0913
        L_0x08fa:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r6)
            throw r0
        L_0x0903:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r9 = r0.out
            java.lang.Object r10 = r0.fc
            java.lang.Object r6 = r6.apply2(r9, r10)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r6 == r9) goto L_0x02de
            java.lang.Object r6 = r8.args
        L_0x0913:
            r9 = 1
            goto L_0x0047
        L_0x0916:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r10)
            throw r1
        L_0x091e:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r6)
            throw r0
        L_0x0927:
            r0 = move-exception
            r3 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r10)
            throw r0
        L_0x0930:
            r0 = move-exception
            r3 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r3, (java.lang.Object) r8)
            throw r0
        L_0x0939:
            gnu.mapping.Values r0 = gnu.mapping.Values.empty
        L_0x093b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClIprintf$V(java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 24:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 25:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 26:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 27:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    /* compiled from: printf.scm */
    public class frame9 extends ModuleBody {
        LList args;
        Object fc;
        int fl;
        Object format$Mnstring;
        Object out;
        Object pos;

        public Object lambda18mustAdvance() {
            this.pos = AddOp.$Pl.apply2(printf.Lit7, this.pos);
            if (Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl)) != Boolean.FALSE) {
                return lambda20incomplete();
            }
            Object obj = this.format$Mnstring;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object obj2 = this.pos;
                try {
                    this.fc = Char.make(strings.stringRef(charSequence, ((Number) obj2).intValue()));
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        }

        public boolean lambda19isEndOfFormat() {
            return ((Boolean) Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl))).booleanValue();
        }

        public Object lambda20incomplete() {
            return misc.error$V(printf.Lit34, new Object[]{"conversion specification incomplete", this.format$Mnstring});
        }

        public Object lambda21out$St(Object obj) {
            if (strings.isString(obj)) {
                return Scheme.applyToArgs.apply2(this.out, obj);
            }
            while (true) {
                boolean isNull = lists.isNull(obj);
                if (isNull) {
                    return isNull ? Boolean.TRUE : Boolean.FALSE;
                }
                Object apply2 = Scheme.applyToArgs.apply2(this.out, lists.car.apply1(obj));
                if (apply2 == Boolean.FALSE) {
                    return apply2;
                }
                obj = lists.cdr.apply1(obj);
            }
        }
    }

    /* compiled from: printf.scm */
    public class frame10 extends ModuleBody {
        Object alternate$Mnform;
        Object args;
        Object blank;
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object leading$Mn0s;
        Object left$Mnadjust;
        Object os;
        Procedure pad = new ModuleMethod(this, 15, printf.Lit67, -4095);
        Object pr;
        Object precision;
        Object signed;
        frame9 staticLink;
        Object width;

        public frame10() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:472");
            this.lambda$Fn13 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 17, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:476");
            this.lambda$Fn14 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:484");
            this.lambda$Fn15 = moduleMethod3;
            ModuleMethod moduleMethod4 = new ModuleMethod(this, 19, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:494");
            this.lambda$Fn16 = moduleMethod4;
        }

        public Object lambda22readFormatNumber() {
            Object[] objArr;
            if (Scheme.isEqv.apply2(printf.Lit66, this.staticLink.fc) != Boolean.FALSE) {
                this.staticLink.lambda18mustAdvance();
                Object apply1 = lists.car.apply1(this.args);
                this.args = lists.cdr.apply1(this.args);
                return apply1;
            }
            Object obj = this.staticLink.fc;
            Object obj2 = obj;
            Object obj3 = printf.Lit1;
            while (true) {
                Object obj4 = this.staticLink.fc;
                try {
                    if (!unicode.isCharNumeric((Char) obj4)) {
                        return obj3;
                    }
                    this.staticLink.lambda18mustAdvance();
                    Object obj5 = this.staticLink.fc;
                    AddOp addOp = AddOp.$Pl;
                    Object apply2 = MultiplyOp.$St.apply2(obj3, printf.Lit45);
                    if (obj2 instanceof Object[]) {
                        objArr = (Object[]) obj2;
                    } else {
                        objArr = new Object[]{obj2};
                    }
                    obj3 = addOp.apply2(apply2, numbers.string$To$Number(strings.$make$string$(objArr)));
                    obj2 = obj5;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "char-numeric?", 1, obj4);
                }
            }
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            if (moduleMethod.selector != 15) {
                return super.applyN(moduleMethod, objArr);
            }
            Object obj = objArr[0];
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            while (true) {
                length--;
                if (length < 0) {
                    return lambda23pad$V(obj, objArr2);
                }
                objArr2[length] = objArr[length + 1];
            }
        }

        public Object lambda23pad$V(Object obj, Object[] objArr) {
            LList makeList = LList.makeList(objArr, 0);
            try {
                Object valueOf = Integer.valueOf(strings.stringLength((CharSequence) obj));
                Object obj2 = makeList;
                while (Scheme.numGEq.apply2(valueOf, this.width) == Boolean.FALSE) {
                    if (!lists.isNull(obj2)) {
                        AddOp addOp = AddOp.$Pl;
                        Object apply1 = lists.car.apply1(obj2);
                        try {
                            valueOf = addOp.apply2(valueOf, Integer.valueOf(strings.stringLength((CharSequence) apply1)));
                            obj2 = lists.cdr.apply1(obj2);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-length", 1, apply1);
                        }
                    } else if (this.left$Mnadjust != Boolean.FALSE) {
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = makeList;
                        Object apply2 = AddOp.$Mn.apply2(this.width, valueOf);
                        try {
                            objArr2[1] = LList.list1(strings.makeString(((Number) apply2).intValue(), printf.Lit29));
                            return lists.cons(obj, append.append$V(objArr2));
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "make-string", 1, apply2);
                        }
                    } else if (this.leading$Mn0s != Boolean.FALSE) {
                        Object apply22 = AddOp.$Mn.apply2(this.width, valueOf);
                        try {
                            return lists.cons(obj, lists.cons(strings.makeString(((Number) apply22).intValue(), printf.Lit9), makeList));
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "make-string", 1, apply22);
                        }
                    } else {
                        Object apply23 = AddOp.$Mn.apply2(this.width, valueOf);
                        try {
                            return lists.cons(strings.makeString(((Number) apply23).intValue(), printf.Lit29), lists.cons(obj, makeList));
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "make-string", 1, apply23);
                        }
                    }
                }
                return lists.cons(obj, makeList);
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, obj);
            }
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:96:0x012f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda24integerConvert(java.lang.Object r9, java.lang.Object r10, java.lang.Object r11) {
            /*
                r8 = this;
                java.lang.String r0 = "number->string"
                java.lang.Object r1 = r8.precision
                r2 = 1
                gnu.math.RealNum r1 = gnu.kawa.lispexpr.LangObjType.coerceRealNum(r1)     // Catch:{ ClassCastException -> 0x0183 }
                boolean r1 = kawa.lib.numbers.isNegative(r1)
                java.lang.String r3 = ""
                if (r1 != 0) goto L_0x0039
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                r8.leading$Mn0s = r1
                java.lang.Object r1 = r8.precision
                java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ ClassCastException -> 0x0030 }
                boolean r1 = kawa.lib.numbers.isZero(r1)
                if (r1 == 0) goto L_0x002c
                gnu.kawa.functions.IsEqv r1 = kawa.standard.Scheme.isEqv
                gnu.math.IntNum r4 = gnu.kawa.slib.printf.Lit1
                java.lang.Object r1 = r1.apply2(r4, r9)
                java.lang.Boolean r4 = java.lang.Boolean.FALSE
                if (r1 == r4) goto L_0x0039
                goto L_0x002e
            L_0x002c:
                if (r1 == 0) goto L_0x0039
            L_0x002e:
                r9 = r3
                goto L_0x0039
            L_0x0030:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                java.lang.String r11 = "zero?"
                r10.<init>((java.lang.ClassCastException) r9, (java.lang.String) r11, (int) r2, (java.lang.Object) r1)
                throw r10
            L_0x0039:
                boolean r1 = kawa.lib.misc.isSymbol(r9)
                java.lang.String r4 = "0"
                r5 = 2
                r6 = 0
                if (r1 == 0) goto L_0x0053
                gnu.mapping.Symbol r9 = (gnu.mapping.Symbol) r9     // Catch:{ ClassCastException -> 0x004a }
                java.lang.String r9 = kawa.lib.misc.symbol$To$String(r9)
                goto L_0x0094
            L_0x004a:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                java.lang.String r0 = "symbol->string"
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x0053:
                boolean r1 = kawa.lib.numbers.isNumber(r9)
                if (r1 == 0) goto L_0x0075
                java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ ClassCastException -> 0x006e }
                r1 = r10
                java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ ClassCastException -> 0x0067 }
                int r0 = r1.intValue()     // Catch:{ ClassCastException -> 0x0067 }
                java.lang.CharSequence r9 = kawa.lib.numbers.number$To$String(r9, r0)
                goto L_0x0094
            L_0x0067:
                r9 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r11.<init>((java.lang.ClassCastException) r9, (java.lang.String) r0, (int) r5, (java.lang.Object) r10)
                throw r11
            L_0x006e:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x0075:
                java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ ClassCastException -> 0x0179 }
                if (r9 == r0) goto L_0x007b
                r0 = 1
                goto L_0x007c
            L_0x007b:
                r0 = 0
            L_0x007c:
                int r0 = r0 + r2
                r0 = r0 & r2
                if (r0 == 0) goto L_0x0083
                if (r0 == 0) goto L_0x008b
                goto L_0x0089
            L_0x0083:
                boolean r0 = kawa.lib.lists.isNull(r9)
                if (r0 == 0) goto L_0x008b
            L_0x0089:
                r9 = r4
                goto L_0x0094
            L_0x008b:
                boolean r0 = kawa.lib.strings.isString(r9)
                if (r0 == 0) goto L_0x0092
                goto L_0x0094
            L_0x0092:
                java.lang.String r9 = "1"
            L_0x0094:
                java.lang.Boolean r0 = java.lang.Boolean.FALSE
                if (r11 == r0) goto L_0x009e
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                java.lang.Object r9 = r0.apply2(r11, r9)
            L_0x009e:
                boolean r11 = gnu.kawa.functions.IsEqual.apply(r3, r9)
                java.lang.String r0 = "string-length"
                if (r11 == 0) goto L_0x00a9
            L_0x00a6:
                r10 = r3
                goto L_0x0116
            L_0x00a9:
                gnu.kawa.functions.IsEqv r11 = kawa.standard.Scheme.isEqv
                gnu.text.Char r1 = gnu.kawa.slib.printf.Lit5
                r7 = r9
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ ClassCastException -> 0x0170 }
                char r7 = kawa.lib.strings.stringRef(r7, r6)
                gnu.text.Char r7 = gnu.text.Char.make(r7)
                java.lang.Object r11 = r11.apply2(r1, r7)
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r11 == r1) goto L_0x00e0
                r10 = r9
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x00d7 }
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x00d0 }
                int r9 = kawa.lib.strings.stringLength(r9)
                java.lang.CharSequence r9 = kawa.lib.strings.substring(r10, r2, r9)
                java.lang.String r10 = "-"
                goto L_0x0116
            L_0x00d0:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x00d7:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                java.lang.String r0 = "substring"
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x00e0:
                java.lang.Object r11 = r8.signed
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r11 == r1) goto L_0x00e9
                java.lang.String r10 = "+"
                goto L_0x0116
            L_0x00e9:
                java.lang.Object r11 = r8.blank
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r11 == r1) goto L_0x00f2
                java.lang.String r10 = " "
                goto L_0x0116
            L_0x00f2:
                java.lang.Object r11 = r8.alternate$Mnform
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r11 == r1) goto L_0x00a6
                gnu.kawa.functions.IsEqv r11 = kawa.standard.Scheme.isEqv
                gnu.math.IntNum r1 = gnu.kawa.slib.printf.Lit48
                java.lang.Object r11 = r11.apply2(r10, r1)
                java.lang.Boolean r1 = java.lang.Boolean.FALSE
                if (r11 == r1) goto L_0x0105
                goto L_0x0115
            L_0x0105:
                gnu.kawa.functions.IsEqv r11 = kawa.standard.Scheme.isEqv
                gnu.math.IntNum r1 = gnu.kawa.slib.printf.Lit50
                java.lang.Object r10 = r11.apply2(r10, r1)
                java.lang.Boolean r11 = java.lang.Boolean.FALSE
                if (r10 == r11) goto L_0x0114
                java.lang.String r4 = "0x"
                goto L_0x0115
            L_0x0114:
                r4 = r3
            L_0x0115:
                r10 = r4
            L_0x0116:
                java.lang.Object[] r11 = new java.lang.Object[r5]
                gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numLss
                r4 = r9
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0169 }
                int r4 = kawa.lib.strings.stringLength(r4)
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                java.lang.Object r5 = r8.precision
                java.lang.Object r1 = r1.apply2(r4, r5)
                java.lang.Boolean r4 = java.lang.Boolean.FALSE
                if (r1 == r4) goto L_0x0160
                gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Mn
                java.lang.Object r3 = r8.precision
                r4 = r9
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0159 }
                int r0 = kawa.lib.strings.stringLength(r4)
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                java.lang.Object r0 = r1.apply2(r3, r0)
                r1 = r0
                java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ ClassCastException -> 0x0150 }
                int r0 = r1.intValue()     // Catch:{ ClassCastException -> 0x0150 }
                gnu.text.Char r1 = gnu.kawa.slib.printf.Lit9
                java.lang.CharSequence r3 = kawa.lib.strings.makeString(r0, r1)
                goto L_0x0160
            L_0x0150:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                java.lang.String r11 = "make-string"
                r10.<init>((java.lang.ClassCastException) r9, (java.lang.String) r11, (int) r2, (java.lang.Object) r0)
                throw r10
            L_0x0159:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x0160:
                r11[r6] = r3
                r11[r2] = r9
                java.lang.Object r9 = r8.lambda23pad$V(r10, r11)
                return r9
            L_0x0169:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x0170:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                java.lang.String r0 = "string-ref"
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r0, (int) r2, (java.lang.Object) r9)
                throw r11
            L_0x0179:
                r10 = move-exception
                gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
                r0 = -2
                java.lang.String r1 = "x"
                r11.<init>((java.lang.ClassCastException) r10, (java.lang.String) r1, (int) r0, (java.lang.Object) r9)
                throw r11
            L_0x0183:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                java.lang.String r11 = "negative?"
                r10.<init>((java.lang.ClassCastException) r9, (java.lang.String) r11, (int) r2, (java.lang.Object) r1)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame10.lambda24integerConvert(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 16:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 18:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 19:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda25(Object obj) {
            try {
                this.pr = AddOp.$Pl.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                return Scheme.applyToArgs.apply2(this.staticLink.out, obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda26(Object obj) {
            Object obj2;
            Special special = Special.undefined;
            try {
                Object apply2 = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(apply2))) {
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        Object obj3 = this.staticLink.out;
                        try {
                            CharSequence charSequence = (CharSequence) obj;
                            Object obj4 = this.pr;
                            try {
                                applyToArgs.apply2(obj3, strings.substring(charSequence, 0, ((Number) obj4).intValue()));
                                obj2 = printf.Lit1;
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 3, obj4);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 1, obj);
                        }
                    } else {
                        Scheme.applyToArgs.apply2(this.staticLink.out, obj);
                        obj2 = apply2;
                    }
                    this.pr = obj2;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(apply2));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "positive?", 1, apply2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "negative?", 1, apply2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda27(Object obj) {
            try {
                this.pr = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                if (this.os == Boolean.FALSE) {
                    Scheme.applyToArgs.apply2(this.staticLink.out, obj);
                } else {
                    Object obj2 = this.pr;
                    try {
                        if (numbers.isNegative(LangObjType.coerceRealNum(obj2))) {
                            Scheme.applyToArgs.apply2(this.staticLink.out, this.os);
                            this.os = Boolean.FALSE;
                            Scheme.applyToArgs.apply2(this.staticLink.out, obj);
                        } else {
                            this.os = strings.stringAppend(this.os, obj);
                        }
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "negative?", 1, obj2);
                    }
                }
                return Boolean.TRUE;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-length", 1, obj);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 16:
                    return lambda25(obj);
                case 17:
                    return lambda26(obj) ? Boolean.TRUE : Boolean.FALSE;
                case 18:
                    return lambda27(obj);
                case 19:
                    return lambda28(obj) ? Boolean.TRUE : Boolean.FALSE;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda28(Object obj) {
            Special special = Special.undefined;
            try {
                Object apply2 = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(apply2))) {
                        Object[] objArr = new Object[2];
                        objArr[0] = this.os;
                        try {
                            CharSequence charSequence = (CharSequence) obj;
                            Object obj2 = this.pr;
                            try {
                                objArr[1] = strings.substring(charSequence, 0, ((Number) obj2).intValue());
                                this.os = strings.stringAppend(objArr);
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 3, obj2);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 1, obj);
                        }
                    } else {
                        this.os = strings.stringAppend(this.os, obj);
                    }
                    this.pr = apply2;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(apply2));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "positive?", 1, apply2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "negative?", 1, apply2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, obj);
            }
        }
    }

    /* compiled from: printf.scm */
    public class frame11 extends ModuleBody {
        Object fc;
        Procedure format$Mnreal = new ModuleMethod(this, 13, printf.Lit64, -4092);
        final ModuleMethod lambda$Fn17;
        frame10 staticLink;

        public frame11() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 14, (Object) null, -4093);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:401");
            this.lambda$Fn17 = moduleMethod;
        }

        public Object lambda29f(Object obj, Object obj2, Object obj3) {
            IntNum intNum;
            try {
                Object stdio$ClRoundString = printf.stdio$ClRoundString((CharSequence) obj, AddOp.$Pl.apply2(obj2, this.staticLink.precision), obj3 != Boolean.FALSE ? obj2 : obj3);
                if (Scheme.numGEq.apply2(obj2, printf.Lit1) != Boolean.FALSE) {
                    try {
                        if (numbers.isZero((Number) obj2)) {
                            intNum = printf.Lit1;
                        } else {
                            try {
                                if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef((CharSequence) stdio$ClRoundString, 0)))) {
                                    intNum = printf.Lit7;
                                } else {
                                    intNum = printf.Lit1;
                                }
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-ref", 1, stdio$ClRoundString);
                            }
                        }
                        Object max = numbers.max(printf.Lit7, AddOp.$Pl.apply2(printf.Lit7, obj2));
                        try {
                            try {
                                try {
                                    CharSequence substring = strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue(), ((Number) max).intValue());
                                    try {
                                        try {
                                            try {
                                                CharSequence substring2 = strings.substring((CharSequence) stdio$ClRoundString, ((Number) max).intValue(), strings.stringLength((CharSequence) stdio$ClRoundString));
                                                boolean isString$Eq = strings.isString$Eq(substring2, "");
                                                return lists.cons(substring, (!isString$Eq ? !isString$Eq : this.staticLink.alternate$Mnform != Boolean.FALSE) ? LList.list2(".", substring2) : LList.Empty);
                                            } catch (ClassCastException e2) {
                                                throw new WrongType(e2, "string-length", 1, stdio$ClRoundString);
                                            }
                                        } catch (ClassCastException e3) {
                                            throw new WrongType(e3, "substring", 2, max);
                                        }
                                    } catch (ClassCastException e4) {
                                        throw new WrongType(e4, "substring", 1, stdio$ClRoundString);
                                    }
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "substring", 3, max);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "substring", 2, (Object) intNum);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "substring", 1, stdio$ClRoundString);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "zero?", 1, obj2);
                    }
                } else {
                    Object obj4 = this.staticLink.precision;
                    try {
                        String str = "0.";
                        if (numbers.isZero((Number) obj4)) {
                            if (this.staticLink.alternate$Mnform == Boolean.FALSE) {
                                str = "0";
                            }
                            return LList.list1(str);
                        }
                        if (obj3 != Boolean.FALSE) {
                            boolean isString$Eq2 = strings.isString$Eq(stdio$ClRoundString, "");
                            obj3 = isString$Eq2 ? LList.list1("0") : isString$Eq2 ? Boolean.TRUE : Boolean.FALSE;
                        }
                        if (obj3 != Boolean.FALSE) {
                            return obj3;
                        }
                        Object min = numbers.min(this.staticLink.precision, AddOp.$Mn.apply2(printf.Lit17, obj2));
                        try {
                            return LList.list3(str, strings.makeString(((Number) min).intValue(), printf.Lit9), stdio$ClRoundString);
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "make-string", 1, min);
                        }
                    } catch (ClassCastException e10) {
                        throw new WrongType(e10, "zero?", 1, obj4);
                    }
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "stdio:round-string", 0, obj);
            }
        }

        public Object lambda30formatReal$V(Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
            String str;
            Values values;
            Object obj5;
            Object obj6;
            Object obj7 = obj2;
            Object obj8 = obj3;
            Object obj9 = obj4;
            LList makeList = LList.makeList(objArr, 0);
            if (lists.isNull(makeList)) {
                try {
                    boolean isChar$Eq = characters.isChar$Eq(printf.Lit5, (Char) obj7);
                    String str2 = " ";
                    String str3 = Marker.ANY_NON_NULL_MARKER;
                    String str4 = "";
                    if (isChar$Eq) {
                        str = "-";
                    } else {
                        str = obj != Boolean.FALSE ? str3 : this.staticLink.blank != Boolean.FALSE ? str2 : str4;
                    }
                    Object apply2 = Scheme.isEqv.apply2(this.fc, printf.Lit13);
                    if (apply2 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit54) == Boolean.FALSE : apply2 == Boolean.FALSE) {
                        Object apply22 = Scheme.isEqv.apply2(this.fc, printf.Lit25);
                        if (apply22 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit26) == Boolean.FALSE : apply22 == Boolean.FALSE) {
                            Object apply23 = Scheme.isEqv.apply2(this.fc, printf.Lit55);
                            if (apply23 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit56) == Boolean.FALSE : apply23 == Boolean.FALSE) {
                                if (Scheme.isEqv.apply2(this.fc, printf.Lit57) != Boolean.FALSE) {
                                    str2 = str4;
                                } else if (Scheme.isEqv.apply2(this.fc, printf.Lit58) == Boolean.FALSE) {
                                    values = Values.empty;
                                    return lists.cons(str, values);
                                }
                                try {
                                    if (numbers.isNegative(LangObjType.coerceRealNum(obj4))) {
                                        obj6 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(obj9, printf.Lit61), printf.Lit61);
                                    } else {
                                        obj6 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(obj9, printf.Lit7), printf.Lit61);
                                    }
                                    Object apply3 = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(obj6, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
                                    try {
                                        boolean booleanValue = ((Boolean) apply3).booleanValue();
                                        if (!booleanValue) {
                                            obj6 = booleanValue ? Boolean.TRUE : Boolean.FALSE;
                                        }
                                        if (obj6 != Boolean.FALSE) {
                                            Object apply24 = AddOp.$Mn.apply2(obj9, MultiplyOp.$St.apply2(printf.Lit61, obj6));
                                            this.staticLink.precision = numbers.max(printf.Lit1, AddOp.$Mn.apply2(this.staticLink.precision, apply24));
                                            Object[] objArr2 = new Object[2];
                                            objArr2[0] = lambda29f(obj8, apply24, Boolean.FALSE);
                                            FVector fVector = printf.Lit62;
                                            Object apply25 = AddOp.$Pl.apply2(obj6, printf.Lit48);
                                            try {
                                                objArr2[1] = LList.list2(str2, vectors.vectorRef(fVector, ((Number) apply25).intValue()));
                                                values = append.append$V(objArr2);
                                                return lists.cons(str, values);
                                            } catch (ClassCastException e) {
                                                throw new WrongType(e, "vector-ref", 2, apply25);
                                            }
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply3);
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "negative?", 1, obj9);
                                }
                            }
                            Object obj10 = this.staticLink.alternate$Mnform;
                            try {
                                int i = ((obj10 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                                this.staticLink.alternate$Mnform = Boolean.FALSE;
                                if (Scheme.numLEq.apply3(AddOp.$Mn.apply2(printf.Lit7, this.staticLink.precision), obj9, this.staticLink.precision) != Boolean.FALSE) {
                                    this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, obj9);
                                    values = lambda29f(obj8, obj9, i != 0 ? Boolean.TRUE : Boolean.FALSE);
                                    return lists.cons(str, values);
                                }
                                this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, printf.Lit7);
                                if (i != 0) {
                                    obj5 = Boolean.TRUE;
                                } else {
                                    obj5 = Boolean.FALSE;
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "strip-0s", -2, obj10);
                            }
                        } else {
                            values = lambda29f(obj8, obj9, Boolean.FALSE);
                            return lists.cons(str, values);
                        }
                    } else {
                        obj5 = Boolean.FALSE;
                    }
                    try {
                        CharSequence charSequence = (CharSequence) obj8;
                        Object apply26 = AddOp.$Pl.apply2(printf.Lit7, this.staticLink.precision);
                        if (obj5 != Boolean.FALSE) {
                            obj5 = printf.Lit1;
                        }
                        Object stdio$ClRoundString = printf.stdio$ClRoundString(charSequence, apply26, obj5);
                        try {
                            IntNum intNum = characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef((CharSequence) stdio$ClRoundString, 0))) ? printf.Lit7 : printf.Lit1;
                            try {
                                try {
                                    CharSequence substring = strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue() + 1, strings.stringLength((CharSequence) stdio$ClRoundString));
                                    if (!numbers.isZero(intNum)) {
                                        obj9 = AddOp.$Mn.apply2(obj9, printf.Lit7);
                                    }
                                    try {
                                        try {
                                            Pair list1 = LList.list1(strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue(), intNum.intValue() + 1));
                                            boolean isString$Eq = strings.isString$Eq(substring, str4);
                                            String str5 = (!isString$Eq ? !isString$Eq : this.staticLink.alternate$Mnform != Boolean.FALSE) ? "." : str4;
                                            Object obj11 = this.fc;
                                            try {
                                                String str6 = unicode.isCharUpperCase((Char) obj11) ? "E" : RsaJsonWebKey.EXPONENT_MEMBER_NAME;
                                                try {
                                                    if (numbers.isNegative(LangObjType.coerceRealNum(obj9))) {
                                                        str3 = "-";
                                                    }
                                                    Pair chain4 = LList.chain4(list1, str5, substring, str6, str3);
                                                    if (Scheme.numLss.apply3(printf.Lit60, obj9, printf.Lit45) != Boolean.FALSE) {
                                                        str4 = "0";
                                                    }
                                                    try {
                                                        LList.chain1(LList.chain1(chain4, str4), numbers.number$To$String(numbers.abs((Number) obj9)));
                                                        values = list1;
                                                        return lists.cons(str, values);
                                                    } catch (ClassCastException e5) {
                                                        throw new WrongType(e5, "abs", 1, obj9);
                                                    }
                                                } catch (ClassCastException e6) {
                                                    throw new WrongType(e6, "negative?", 1, obj9);
                                                }
                                            } catch (ClassCastException e7) {
                                                throw new WrongType(e7, "char-upper-case?", 1, obj11);
                                            }
                                        } catch (ClassCastException e8) {
                                            throw new WrongType(e8, "substring", 2, (Object) intNum);
                                        }
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "substring", 1, stdio$ClRoundString);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "string-length", 1, stdio$ClRoundString);
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, "substring", 1, stdio$ClRoundString);
                            }
                        } catch (ClassCastException e12) {
                            throw new WrongType(e12, "string-ref", 1, stdio$ClRoundString);
                        }
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "stdio:round-string", 0, obj8);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "char=?", 2, obj7);
                }
            } else {
                Object obj12 = obj;
                return append.append$V(new Object[]{lambda30formatReal$V(obj, obj2, obj3, obj4, new Object[0]), Scheme.apply.apply3(this.format$Mnreal, Boolean.TRUE, makeList), printf.Lit63});
            }
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i == 13) {
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            } else if (i != 14) {
                return super.matchN(moduleMethod, objArr, callContext);
            } else {
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            }
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            int i = moduleMethod.selector;
            if (i == 13) {
                Object obj = objArr[0];
                Object obj2 = objArr[1];
                Object obj3 = objArr[2];
                Object obj4 = objArr[3];
                int length = objArr.length - 4;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return lambda30formatReal$V(obj, obj2, obj3, obj4, objArr2);
                    }
                    objArr2[length] = objArr[length + 4];
                }
            } else if (i != 14) {
                return super.applyN(moduleMethod, objArr);
            } else {
                Object obj5 = objArr[0];
                Object obj6 = objArr[1];
                Object obj7 = objArr[2];
                int length2 = objArr.length - 3;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return lambda31$V(obj5, obj6, obj7, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 3];
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda31$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
            LList makeList = LList.makeList(objArr, 0);
            return Scheme.apply.apply2(this.staticLink.pad, Scheme.apply.applyN(new Object[]{this.format$Mnreal, this.staticLink.signed, obj, obj2, obj3, makeList}));
        }
    }

    public static Object fprintf$V(Object obj, Object obj2, Object[] objArr) {
        frame12 frame122 = new frame12();
        frame122.port = obj;
        LList makeList = LList.makeList(objArr, 0);
        frame122.cnt = Lit1;
        Scheme.apply.apply4(stdio$Cliprintf, frame122.lambda$Fn18, obj2, makeList);
        return frame122.cnt;
    }

    /* compiled from: printf.scm */
    public class frame12 extends ModuleBody {
        Object cnt;
        final ModuleMethod lambda$Fn18;
        Object port;

        public frame12() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 20, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:546");
            this.lambda$Fn18 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 20 ? lambda32(obj) : super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 20) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda32(Object obj) {
            if (strings.isString(obj)) {
                try {
                    this.cnt = AddOp.$Pl.apply2(Integer.valueOf(strings.stringLength((CharSequence) obj)), this.cnt);
                    ports.display(obj, this.port);
                    return Boolean.TRUE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-length", 1, obj);
                }
            } else {
                this.cnt = AddOp.$Pl.apply2(printf.Lit7, this.cnt);
                ports.display(obj, this.port);
                return Boolean.TRUE;
            }
        }
    }

    public static Object printf$V(Object obj, Object[] objArr) {
        return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), obj, LList.makeList(objArr, 0));
    }

    public static Object sprintf$V(Object obj, Object obj2, Object[] objArr) {
        Object obj3;
        frame13 frame132 = new frame13();
        frame132.str = obj;
        LList makeList = LList.makeList(objArr, 0);
        frame132.cnt = Lit1;
        if (strings.isString(frame132.str)) {
            obj3 = frame132.str;
        } else if (numbers.isNumber(frame132.str)) {
            Object obj4 = frame132.str;
            try {
                obj3 = strings.makeString(((Number) obj4).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-string", 1, obj4);
            }
        } else if (frame132.str == Boolean.FALSE) {
            obj3 = strings.makeString(100);
        } else {
            obj3 = misc.error$V(Lit68, new Object[]{"first argument not understood", frame132.str});
        }
        frame132.s = obj3;
        Object obj5 = frame132.s;
        try {
            frame132.end = Integer.valueOf(strings.stringLength((CharSequence) obj5));
            Scheme.apply.apply4(stdio$Cliprintf, frame132.lambda$Fn19, obj2, makeList);
            if (strings.isString(frame132.str)) {
                return frame132.cnt;
            }
            if (Scheme.isEqv.apply2(frame132.end, frame132.cnt) != Boolean.FALSE) {
                return frame132.s;
            }
            Object obj6 = frame132.s;
            try {
                CharSequence charSequence = (CharSequence) obj6;
                Object obj7 = frame132.cnt;
                try {
                    return strings.substring(charSequence, 0, ((Number) obj7).intValue());
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 3, obj7);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj6);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "string-length", 1, obj5);
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 24:
                Object obj = objArr[0];
                Object obj2 = objArr[1];
                int length = objArr.length - 2;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return stdio$ClIprintf$V(obj, obj2, objArr2);
                    }
                    objArr2[length] = objArr[length + 2];
                }
            case 25:
                Object obj3 = objArr[0];
                Object obj4 = objArr[1];
                int length2 = objArr.length - 2;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return fprintf$V(obj3, obj4, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 2];
                }
            case 26:
                Object obj5 = objArr[0];
                int length3 = objArr.length - 1;
                Object[] objArr4 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return printf$V(obj5, objArr4);
                    }
                    objArr4[length3] = objArr[length3 + 1];
                }
            case 27:
                Object obj6 = objArr[0];
                Object obj7 = objArr[1];
                int length4 = objArr.length - 2;
                Object[] objArr5 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return sprintf$V(obj6, obj7, objArr5);
                    }
                    objArr5[length4] = objArr[length4 + 2];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    /* compiled from: printf.scm */
    public class frame13 extends ModuleBody {
        Object cnt;
        Object end;
        final ModuleMethod lambda$Fn19;
        Object s;
        Object str;

        public frame13() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 21, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:564");
            this.lambda$Fn19 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 21) {
                return lambda33(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 21) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda33(Object obj) {
            char c;
            int i = 0;
            if (strings.isString(obj)) {
                if (this.str == Boolean.FALSE) {
                    try {
                        if (Scheme.numGEq.apply2(AddOp.$Mn.apply2(this.end, this.cnt), Integer.valueOf(strings.stringLength((CharSequence) obj))) == Boolean.FALSE) {
                            Object[] objArr = new Object[2];
                            Object obj2 = this.s;
                            try {
                                CharSequence charSequence = (CharSequence) obj2;
                                Object obj3 = this.cnt;
                                try {
                                    objArr[0] = strings.substring(charSequence, 0, ((Number) obj3).intValue());
                                    objArr[1] = obj;
                                    FString stringAppend = strings.stringAppend(objArr);
                                    this.s = stringAppend;
                                    try {
                                        Integer valueOf = Integer.valueOf(strings.stringLength(stringAppend));
                                        this.cnt = valueOf;
                                        this.end = valueOf;
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "string-length", 1, (Object) stringAppend);
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "substring", 3, obj3);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "substring", 1, obj2);
                            }
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-length", 1, obj);
                    }
                }
                Object[] objArr2 = new Object[2];
                try {
                    objArr2[0] = Integer.valueOf(strings.stringLength((CharSequence) obj));
                    objArr2[1] = AddOp.$Mn.apply2(this.end, this.cnt);
                    Object min = numbers.min(objArr2);
                    Object obj4 = printf.Lit1;
                    while (Scheme.numGEq.apply2(obj4, min) == Boolean.FALSE) {
                        Object obj5 = this.s;
                        try {
                            CharSeq charSeq = (CharSeq) obj5;
                            Object obj6 = this.cnt;
                            try {
                                try {
                                    try {
                                        strings.stringSet$Ex(charSeq, ((Number) obj6).intValue(), strings.stringRef((CharSequence) obj, ((Number) obj4).intValue()));
                                        this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                                        obj4 = AddOp.$Pl.apply2(obj4, printf.Lit7);
                                    } catch (ClassCastException e5) {
                                        throw new WrongType(e5, "string-ref", 2, obj4);
                                    }
                                } catch (ClassCastException e6) {
                                    throw new WrongType(e6, "string-ref", 1, obj);
                                }
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "string-set!", 2, obj6);
                            }
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "string-set!", 1, obj5);
                        }
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "string-length", 1, obj);
                }
            } else {
                if ((this.str != Boolean.FALSE ? Scheme.numGEq.apply2(this.cnt, this.end) : this.str) == Boolean.FALSE) {
                    Object obj7 = this.str;
                    try {
                        int i2 = ((obj7 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                        if (i2 == 0 ? i2 != 0 : Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE) {
                            FString stringAppend2 = strings.stringAppend(this.s, strings.makeString(100));
                            this.s = stringAppend2;
                            try {
                                this.end = Integer.valueOf(strings.stringLength(stringAppend2));
                            } catch (ClassCastException e10) {
                                throw new WrongType(e10, "string-length", 1, (Object) stringAppend2);
                            }
                        }
                        Object obj8 = this.s;
                        try {
                            CharSeq charSeq2 = (CharSeq) obj8;
                            Object obj9 = this.cnt;
                            try {
                                int intValue = ((Number) obj9).intValue();
                                if (characters.isChar(obj)) {
                                    try {
                                        c = ((Char) obj).charValue();
                                    } catch (ClassCastException e11) {
                                        throw new WrongType(e11, "string-set!", 3, obj);
                                    }
                                } else {
                                    c = '?';
                                }
                                strings.stringSet$Ex(charSeq2, intValue, c);
                                this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                            } catch (ClassCastException e12) {
                                throw new WrongType(e12, "string-set!", 2, obj9);
                            }
                        } catch (ClassCastException e13) {
                            throw new WrongType(e13, "string-set!", 1, obj8);
                        }
                    } catch (ClassCastException e14) {
                        throw new WrongType(e14, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, obj7);
                    }
                }
            }
            if (this.str == Boolean.FALSE ? this.str != Boolean.FALSE : Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE) {
                i = 1;
            }
            return (i + 1) & true;
        }
    }
}
