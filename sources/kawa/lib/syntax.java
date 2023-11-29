package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.SynchronizedExp;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.IfFeature;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: syntax.scm */
public class syntax extends ModuleBody {
    public static final Macro $Pccond$Mnexpand;
    public static final Macro $Pcimport;
    public static final Location $Prvt$and = StaticFieldLocation.make("kawa.lib.std_syntax", "and");
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final Location $Prvt$define$Mnsyntax = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnsyntax");
    public static final Location $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    public static final Location $Prvt$let = StaticFieldLocation.make("kawa.lib.std_syntax", "let");
    public static final Location $Prvt$or = StaticFieldLocation.make("kawa.lib.std_syntax", "or");
    public static final Location $Prvt$try$Mncatch = StaticFieldLocation.make("kawa.lib.prim_syntax", "try$Mncatch");
    public static final syntax $instance;
    static final SyntaxPattern Lit0 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
    static final SyntaxTemplate Lit1;
    static final SyntaxPattern Lit10 = new SyntaxPattern("\u0003", new Object[0], 1);
    static final SimpleSymbol Lit100;
    static final SyntaxRules Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final SimpleSymbol Lit106;
    static final SimpleSymbol Lit107;
    static final SimpleSymbol Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final SimpleSymbol Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final SimpleSymbol Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SyntaxRules Lit18;
    static final SimpleSymbol Lit19;
    static final SyntaxTemplate Lit2;
    static final SyntaxRules Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
    static final SyntaxTemplate Lit23;
    static final SyntaxTemplate Lit24 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26 = new SyntaxPattern("\f\u0007\f\u000f\u0013", new Object[0], 3);
    static final SyntaxTemplate Lit27;
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxPattern Lit3 = new SyntaxPattern("\b", new Object[0], 0);
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxPattern Lit39 = new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) new SimpleSymbol("rename").readResolve()}, 4);
    static final SyntaxPattern Lit4 = new SyntaxPattern("\u0003", new Object[0], 1);
    static final SyntaxTemplate Lit40;
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42;
    static final SyntaxTemplate Lit43 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
    static final SyntaxTemplate Lit44 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
    static final SyntaxTemplate Lit45;
    static final SyntaxPattern Lit46 = new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) new SimpleSymbol("only").readResolve()}, 4);
    static final SyntaxTemplate Lit47;
    static final SyntaxTemplate Lit48;
    static final SyntaxTemplate Lit49;
    static final SyntaxPattern Lit5 = new SyntaxPattern(",\f\u0007\f\u000f\b\u0013", new Object[0], 3);
    static final SyntaxTemplate Lit50 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
    static final SyntaxTemplate Lit51 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
    static final SyntaxTemplate Lit52;
    static final SyntaxPattern Lit53 = new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) new SimpleSymbol("except").readResolve()}, 4);
    static final SyntaxTemplate Lit54;
    static final SyntaxTemplate Lit55;
    static final SyntaxTemplate Lit56;
    static final SyntaxTemplate Lit57 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
    static final SyntaxTemplate Lit58 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
    static final SyntaxTemplate Lit59;
    static final SyntaxTemplate Lit6 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0003", new Object[0], 0);
    static final SyntaxPattern Lit60;
    static final SyntaxTemplate Lit61;
    static final SyntaxTemplate Lit62 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SyntaxTemplate Lit63 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0);
    static final SyntaxTemplate Lit64 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0010", new Object[0], 0);
    static final SyntaxPattern Lit65;
    static final SyntaxTemplate Lit66;
    static final SyntaxPattern Lit67 = new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\b\f\u0017\b", new Object[]{(SimpleSymbol) new SimpleSymbol("library").readResolve()}, 3);
    static final SyntaxTemplate Lit68;
    static final SyntaxTemplate Lit69 = new SyntaxTemplate("\u0001\u0001\u0001", "\b\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit7;
    static final SyntaxTemplate Lit70 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SyntaxTemplate Lit71 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0010", new Object[0], 0);
    static final SyntaxPattern Lit72 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
    static final SyntaxTemplate Lit73;
    static final SyntaxTemplate Lit74 = new SyntaxTemplate("\u0001\u0001\u0001", "\b\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit75 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SyntaxTemplate Lit76 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0010", new Object[0], 0);
    static final SimpleSymbol Lit77;
    static final SyntaxRules Lit78;
    static final SimpleSymbol Lit79;
    static final SyntaxTemplate Lit8;
    static final SyntaxRules Lit80;
    static final SimpleSymbol Lit81;
    static final SyntaxPattern Lit82 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
    static final SyntaxTemplate Lit83;
    static final SyntaxTemplate Lit84 = new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
    static final SyntaxPattern Lit85 = new SyntaxPattern("\u001c\f\u0017\u001b#", new Object[0], 5);
    static final SyntaxTemplate Lit86;
    static final SyntaxTemplate Lit87 = new SyntaxTemplate("\u0001\u0000\u0001\u0000\u0000", "\"", new Object[0], 0);
    static final SyntaxPattern Lit88 = new SyntaxPattern("\b", new Object[0], 2);
    static final SyntaxPattern Lit89 = new SyntaxPattern("\u0013", new Object[0], 3);
    static final SyntaxPattern Lit9 = new SyntaxPattern("\b", new Object[0], 0);
    static final SyntaxTemplate Lit90 = new SyntaxTemplate("\u0001\u0000\u0000", "\u0012", new Object[0], 0);
    static final SimpleSymbol Lit91;
    static final SyntaxRules Lit92;
    static final SimpleSymbol Lit93;
    static final SyntaxPattern Lit94 = new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\u001b", new Object[0], 4);
    static final SyntaxTemplate Lit95 = new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit96;
    static final SyntaxTemplate Lit97;
    static final SimpleSymbol Lit98;
    static final SyntaxRules Lit99;
    public static final Macro case$Mnlambda;
    public static final Macro cond$Mnexpand;
    public static final Macro define$Mnalias$Mnparameter;
    public static final Macro define$Mnmacro;
    public static final Macro define$Mnsyntax$Mncase;
    public static final Macro defmacro;
    public static final ModuleMethod identifier$Mnlist$Qu;
    public static final ModuleMethod identifier$Mnpair$Mnlist$Qu;

    /* renamed from: import  reason: not valid java name */
    public static final Macro f341import;
    public static final ModuleMethod import$Mnhandle$Mnexcept;
    public static final ModuleMethod import$Mnhandle$Mnonly;
    public static final ModuleMethod import$Mnhandle$Mnprefix;
    public static final ModuleMethod import$Mnhandle$Mnrename;
    public static final ModuleMethod import$Mnmapper;
    public static final Macro let$Mnvalues;
    public static final Macro let$St$Mnvalues;
    public static final Macro receive;

    /* renamed from: synchronized  reason: not valid java name */
    public static final Macro f342synchronized;
    public static final Macro try$Mnfinally;
    public static final Macro unless;
    public static final Macro when;

    static {
        Procedure procedure;
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("%define-macro").readResolve();
        Lit123 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("form").readResolve();
        Lit122 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit121 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("prefix").readResolve();
        Lit120 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("instance").readResolve();
        Lit119 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("kawa.standard.ImportFromLibrary").readResolve();
        Lit118 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol(EllipticCurveJsonWebKey.X_MEMBER_NAME).readResolve();
        Lit117 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("call-with-values").readResolve();
        Lit116 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit115 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("not").readResolve();
        Lit114 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit113 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit112 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit111 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit110 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit109 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit108 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("wt").readResolve();
        Lit107 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("as").readResolve();
        Lit106 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("arg").readResolve();
        Lit105 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol6;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit104 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol5;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.LocationProc").readResolve();
        Lit103 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit102 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol13;
        SimpleSymbol simpleSymbol31 = simpleSymbol10;
        SimpleSymbol simpleSymbol32 = simpleSymbol11;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("define-alias-parameter").readResolve();
        Lit100 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol33;
        SimpleSymbol simpleSymbol35 = simpleSymbol12;
        SimpleSymbol simpleSymbol36 = simpleSymbol8;
        SimpleSymbol simpleSymbol37 = simpleSymbol14;
        SimpleSymbol simpleSymbol38 = simpleSymbol9;
        Lit101 = new SyntaxRules(new Object[]{simpleSymbol33}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004¹\u0011\u0018\f\t\u0003\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$)\u0011\u0018,\b\u0003\b\u0013\b\u0011\u00184\t\u0003\b\u0011\u0018<\u0011\u0018D\b\u0011\u0018L9\u0011\u0018T\t\u000b\u0018\\\b\u0011\u0018d\u0011\u0018l\b\u0011\u0018ty\b\u0011\u0018|\b\u0011\u0018\u0011\u0018d\t\u0003\u0018A\u0011\u0018\u0011\u0018\b\u000b\u0018¤", new Object[]{simpleSymbol14, (SimpleSymbol) new SimpleSymbol("define-constant").readResolve(), (SimpleSymbol) new SimpleSymbol("::").readResolve(), (SimpleSymbol) new SimpleSymbol("<gnu.mapping.LocationProc>").readResolve(), PairWithPosition.make(simpleSymbol29, Pair.make(simpleSymbol27, Pair.make(Pair.make(simpleSymbol25, Pair.make((SimpleSymbol) new SimpleSymbol("makeNamed").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1069060), simpleSymbol17, PairWithPosition.make(simpleSymbol29, Pair.make(simpleSymbol27, Pair.make(Pair.make(simpleSymbol25, Pair.make((SimpleSymbol) new SimpleSymbol("pushConverter").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1073161), simpleSymbol15, PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1081354), (SimpleSymbol) new SimpleSymbol("try-catch").readResolve(), simpleSymbol21, PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1089550), (SimpleSymbol) new SimpleSymbol("ex").readResolve(), (SimpleSymbol) new SimpleSymbol("<java.lang.ClassCastException>").readResolve(), simpleSymbol9, simpleSymbol19, PairWithPosition.make(simpleSymbol29, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.mapping.WrongType").readResolve(), Pair.make(Pair.make(simpleSymbol25, Pair.make((SimpleSymbol) new SimpleSymbol("make").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1097748), PairWithPosition.make(PairWithPosition.make(simpleSymbol21, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<int>").readResolve(), PairWithPosition.make(IntNum.make(1), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101846), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101840), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836), PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101849), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836), (SimpleSymbol) new SimpleSymbol("set!").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("field").readResolve(), PairWithPosition.make(simpleSymbol19, PairWithPosition.make(PairWithPosition.make(simpleSymbol17, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("expectedType").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105940), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105937), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105930), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("primitive-throw").readResolve(), PairWithPosition.make(simpleSymbol19, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110037), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020)}, 0)}, 3);
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("receive").readResolve();
        Lit98 = simpleSymbol39;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol39}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u000b\b\u0011\u0018\f\t\u0003\b\u0015\u0013", new Object[]{simpleSymbol36, simpleSymbol15}, 1)}, 3);
        Lit99 = syntaxRules;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("cond-expand").readResolve();
        Lit91 = simpleSymbol40;
        Lit97 = new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004\u001a", new Object[]{simpleSymbol40}, 0);
        Lit96 = new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004\u0012", new Object[]{simpleSymbol37}, 0);
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("%cond-expand").readResolve();
        Lit93 = simpleSymbol41;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol42 = simpleSymbol39;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol40, simpleSymbol35, simpleSymbol32, simpleSymbol31, simpleSymbol30}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\b", new Object[0], 0), "", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("%syntax-error").readResolve(), PairWithPosition.make("Unfulfilled cond-expand", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802851), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802836)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0002\r\u0007\u0000\b\b\b", new Object[]{simpleSymbol30}, 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{simpleSymbol37}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\u001c\f\u0002\b\r\u0007\u0000\b\b\r\u000f\b\b\b", new Object[]{simpleSymbol35}, 2), "\u0003\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{simpleSymbol37}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018|L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol35}, 4), "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004¡\t\u0003\b\u0011\u0018\u0004Q1\u0011\u0018\f\b\r\u000b\b\u0015\u0013\b\u001d\u001b\b\u001d\u001b", new Object[]{simpleSymbol40, simpleSymbol35}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\u001c\f\u0002\b\r\u0007\u0000\b\b\r\u000f\b\b\b", new Object[]{simpleSymbol32}, 2), "\u0003\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{simpleSymbol40}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018|L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol32}, 4), "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004I\t\u0003\b\u0011\u0018\f\b\u0015\u0013\b\u0011\u0018\u0014\b\u0011\u0018\u0004Q1\u0011\u0018\u001c\b\r\u000b\b\u0015\u0013\b\u001d\u001b", new Object[]{simpleSymbol40, simpleSymbol37, simpleSymbol30, simpleSymbol32}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\\,\f\u0002\f\u0007\b\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{simpleSymbol31}, 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004I\t\u0003\b\u0011\u0018\u0004\b\u0015\u0013\b\u0011\u0018\f\b\r\u000b", new Object[]{simpleSymbol40, simpleSymbol30}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\u0019\t\u0003\n\u0012", new Object[]{simpleSymbol41}, 0)}, 4);
        Lit92 = syntaxRules3;
        Lit86 = new SyntaxTemplate("\u0001\u0000\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0013\u001a", new Object[]{simpleSymbol15}, 0);
        Lit83 = new SyntaxTemplate("\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol29, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.expr.GenericProc").readResolve(), Pair.make(Pair.make(simpleSymbol25, Pair.make((SimpleSymbol) new SimpleSymbol("makeWithoutSorting").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 651273)}, 0);
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("case-lambda").readResolve();
        Lit81 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("let*-values").readResolve();
        Lit79 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = simpleSymbol41;
        SyntaxRules syntaxRules4 = syntaxRules3;
        SimpleSymbol simpleSymbol46 = simpleSymbol40;
        SimpleSymbol simpleSymbol47 = simpleSymbol43;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018<\f\u0007\r\u000f\b\b\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4);
        SimpleSymbol simpleSymbol48 = (SimpleSymbol) new SimpleSymbol("let-values").readResolve();
        Lit77 = simpleSymbol48;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol44}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{simpleSymbol37}, 1), new SyntaxRule(syntaxPattern, "\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\u0011\b\u0003\b\u0011\u0018\f\u0019\b\r\u000b\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol48, simpleSymbol44}, 1)}, 4);
        Lit80 = syntaxRules5;
        SyntaxRules syntaxRules6 = syntaxRules5;
        SimpleSymbol simpleSymbol49 = simpleSymbol44;
        SimpleSymbol simpleSymbol50 = simpleSymbol29;
        SimpleSymbol simpleSymbol51 = simpleSymbol28;
        SyntaxRules syntaxRules7 = new SyntaxRules(new Object[]{simpleSymbol48}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0003\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\u0019\b\u0005\u0003\t\u0010\b\u0011\u0018\u0014\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol48, "bind", simpleSymbol37}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0002\f\b\f\u0007\f\u000f\b", new Object[]{"bind"}, 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol38}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0002\\,\f\u0007\f\u000f\b\r\u0017\u0010\b\b\f\u001f\f'\b", new Object[]{"bind"}, 5), "\u0001\u0001\u0003\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u0003\t\u000b\t\u0010\u0019\b\u0015\u0013\t\u001b\b#", new Object[]{simpleSymbol48, "mktmp"}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0002\f\b\f\u0007\f\u000f\f\u0017\f\u001f\f'\b", new Object[]{"mktmp"}, 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u0003\b\u0011\u0018\f\t\u000b\b\u0011\u0018\u0014\u0011\u0018\u001c\t\u0013\t\u001b\b#", new Object[]{simpleSymbol36, simpleSymbol15, simpleSymbol48, "bind"}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0002\u001c\f\u0007\u000b\f\u0017,\r\u001f\u0018\b\b\f',\r/(\b\b\f7\b", new Object[]{"mktmp"}, 7), "\u0001\u0000\u0001\u0003\u0001\u0003\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\n\t\u0013)\u0011\u001d\u001b\u0018\u0014\t#A\u0011-+\b\t\u0003\u0018\u001c\b3", new Object[]{simpleSymbol48, "mktmp", PairWithPosition.make(simpleSymbol51, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569387), PairWithPosition.make(simpleSymbol51, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569414)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0002\f\u0007\f\u000f,\r\u0017\u0010\b\b\f\u001f,\r' \b\b\f/\b", new Object[]{"mktmp"}, 6), "\u0001\u0001\u0003\u0001\u0003\u0001", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u000b\b\u0011\u0018\f)\u0011\u0015\u0013\u0018\u0014\b\u0011\u0018\u001c\u0011\u0018$\t\u001bA\u0011%#\b\t\u0003\u0018,\b+", new Object[]{simpleSymbol36, simpleSymbol15, simpleSymbol51, simpleSymbol48, "bind", PairWithPosition.make(simpleSymbol51, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 593973)}, 1)}, 7);
        Lit78 = syntaxRules7;
        SimpleSymbol simpleSymbol52 = simpleSymbol26;
        SimpleSymbol simpleSymbol53 = simpleSymbol24;
        SimpleSymbol simpleSymbol54 = simpleSymbol50;
        Object[] objArr = {PairWithPosition.make(simpleSymbol54, Pair.make(simpleSymbol53, Pair.make(Pair.make(simpleSymbol25, Pair.make(simpleSymbol52, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 466951)};
        String str = "\u0018\u0004";
        Lit73 = new SyntaxTemplate("\u0001\u0001\u0001", str, objArr, 0);
        Lit68 = new SyntaxTemplate("\u0001\u0001\u0001", str, new Object[]{PairWithPosition.make(simpleSymbol54, Pair.make(simpleSymbol53, Pair.make(Pair.make(simpleSymbol25, Pair.make(simpleSymbol52, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 458759)}, 0);
        String str2 = "\u0012";
        Lit66 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit65 = new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{simpleSymbol22}, 4);
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("%import").readResolve();
        Lit38 = simpleSymbol55;
        Lit61 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{simpleSymbol55}, 0);
        Lit60 = new SyntaxPattern("\f\u0007<\f\u0002\f\u000f\f\u0017\b\f\u001f\b", new Object[]{simpleSymbol22}, 4);
        Lit59 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit56 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit55 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{simpleSymbol55}, 0);
        Lit54 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit52 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit49 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit48 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{simpleSymbol55}, 0);
        Lit47 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit45 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str, new Object[]{(SimpleSymbol) new SimpleSymbol("rest").readResolve()}, 0);
        Lit42 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        Lit41 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{simpleSymbol55}, 0);
        Lit40 = new SyntaxTemplate("\u0001\u0001\u0000\u0001", str2, new Object[0], 0);
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("import").readResolve();
        Lit36 = simpleSymbol56;
        SyntaxRules syntaxRules8 = syntaxRules7;
        SimpleSymbol simpleSymbol57 = simpleSymbol48;
        SyntaxRules syntaxRules9 = new SyntaxRules(new Object[]{simpleSymbol56}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0003\u0018\u0014", new Object[]{simpleSymbol37, simpleSymbol55, PairWithPosition.make(LList.Empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 376866)}, 1)}, 1);
        Lit37 = syntaxRules9;
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) new SimpleSymbol("import-mapper").readResolve();
        Lit35 = simpleSymbol58;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("import-handle-rename").readResolve();
        Lit34 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = (SimpleSymbol) new SimpleSymbol("import-handle-prefix").readResolve();
        Lit33 = simpleSymbol60;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("import-handle-except").readResolve();
        Lit32 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = (SimpleSymbol) new SimpleSymbol("import-handle-only").readResolve();
        Lit31 = simpleSymbol62;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("identifier-pair-list?").readResolve();
        Lit30 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = (SimpleSymbol) new SimpleSymbol("identifier-list?").readResolve();
        Lit29 = simpleSymbol64;
        SyntaxRules syntaxRules10 = syntaxRules9;
        SimpleSymbol simpleSymbol65 = simpleSymbol55;
        SimpleSymbol simpleSymbol66 = simpleSymbol56;
        Lit28 = new SyntaxTemplate("\u0001\u0001\u0000", str2, new Object[0], 0);
        SimpleSymbol simpleSymbol67 = simpleSymbol58;
        String str3 = "\u000b";
        Lit27 = new SyntaxTemplate("\u0001\u0001\u0000", str3, new Object[0], 0);
        SimpleSymbol simpleSymbol68 = simpleSymbol59;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("synchronized").readResolve();
        Lit25 = simpleSymbol69;
        Lit23 = new SyntaxTemplate("\u0001\u0001\u0001", str3, new Object[0], 0);
        SimpleSymbol simpleSymbol70 = (SimpleSymbol) new SimpleSymbol("try-finally").readResolve();
        Lit21 = simpleSymbol70;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("when").readResolve();
        Lit17 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = simpleSymbol60;
        SimpleSymbol simpleSymbol73 = simpleSymbol61;
        SimpleSymbol simpleSymbol74 = simpleSymbol62;
        SimpleSymbol simpleSymbol75 = simpleSymbol63;
        SyntaxRules syntaxRules11 = new SyntaxRules(new Object[]{simpleSymbol71}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\b\r\u000b", new Object[]{simpleSymbol20, simpleSymbol31, simpleSymbol37}, 1)}, 2);
        Lit20 = syntaxRules11;
        SimpleSymbol simpleSymbol76 = (SimpleSymbol) new SimpleSymbol("unless").readResolve();
        Lit19 = simpleSymbol76;
        SimpleSymbol simpleSymbol77 = simpleSymbol64;
        SimpleSymbol simpleSymbol78 = simpleSymbol69;
        SyntaxRules syntaxRules12 = new SyntaxRules(new Object[]{simpleSymbol71}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\r\u000b", new Object[]{simpleSymbol20, simpleSymbol37}, 1)}, 2);
        Lit18 = syntaxRules12;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("define-syntax-case").readResolve();
        Lit15 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = simpleSymbol70;
        SimpleSymbol simpleSymbol81 = simpleSymbol76;
        SyntaxRules syntaxRules13 = syntaxRules11;
        SimpleSymbol simpleSymbol82 = simpleSymbol71;
        SimpleSymbol simpleSymbol83 = simpleSymbol18;
        SyntaxRules syntaxRules14 = new SyntaxRules(new Object[]{simpleSymbol79}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\u0013", new Object[0], 3), "\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\u0011\u0018\u0014\b\u0011\u0018\u001c\u0011\u0018$\t\u000b\u0012", new Object[]{(SimpleSymbol) new SimpleSymbol("define-syntax").readResolve(), simpleSymbol15, PairWithPosition.make(simpleSymbol83, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 90129), (SimpleSymbol) new SimpleSymbol("syntax-case").readResolve(), simpleSymbol83}, 0)}, 3);
        Lit16 = syntaxRules14;
        SimpleSymbol simpleSymbol84 = (SimpleSymbol) new SimpleSymbol("define-macro").readResolve();
        Lit13 = simpleSymbol84;
        SyntaxRules syntaxRules15 = syntaxRules12;
        SyntaxRules syntaxRules16 = new SyntaxRules(new Object[]{simpleSymbol84}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\n\u0012", new Object[]{simpleSymbol16, simpleSymbol15}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol16}, 0)}, 3);
        Lit14 = syntaxRules16;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("defmacro").readResolve();
        Lit11 = simpleSymbol85;
        SyntaxRules syntaxRules17 = syntaxRules14;
        SyntaxRules syntaxRules18 = new SyntaxRules(new Object[]{simpleSymbol85}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\u0013", new Object[0], 3), "\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\u0012", new Object[]{simpleSymbol16, simpleSymbol15}, 0)}, 3);
        Lit12 = syntaxRules18;
        Lit8 = new SyntaxTemplate("\u0001\u0001\u0000", str2, new Object[0], 0);
        Lit7 = new SyntaxTemplate("\u0001\u0001\u0000", str3, new Object[0], 0);
        String str4 = "\u0001\u0000";
        Lit2 = new SyntaxTemplate(str4, "\n", new Object[0], 0);
        Lit1 = new SyntaxTemplate(str4, "\u0003", new Object[0], 0);
        syntax syntax = new syntax();
        $instance = syntax;
        defmacro = Macro.make(simpleSymbol85, syntaxRules18, syntax);
        define$Mnmacro = Macro.make(simpleSymbol84, syntaxRules16, syntax);
        define$Mnsyntax$Mncase = Macro.make(simpleSymbol79, syntaxRules17, syntax);
        when = Macro.make(simpleSymbol82, syntaxRules15, syntax);
        unless = Macro.make(simpleSymbol81, syntaxRules13, syntax);
        try$Mnfinally = Macro.make(simpleSymbol80, new ModuleMethod(syntax, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), syntax);
        f342synchronized = Macro.make(simpleSymbol78, new ModuleMethod(syntax, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), syntax);
        identifier$Mnlist$Qu = new ModuleMethod(syntax, 4, simpleSymbol77, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identifier$Mnpair$Mnlist$Qu = new ModuleMethod(syntax, 5, simpleSymbol75, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        import$Mnhandle$Mnonly = new ModuleMethod(syntax, 6, simpleSymbol74, 8194);
        import$Mnhandle$Mnexcept = new ModuleMethod(syntax, 7, simpleSymbol73, 8194);
        import$Mnhandle$Mnprefix = new ModuleMethod(syntax, 8, simpleSymbol72, 8194);
        import$Mnhandle$Mnrename = new ModuleMethod(syntax, 9, simpleSymbol68, 8194);
        import$Mnmapper = new ModuleMethod(syntax, 10, simpleSymbol67, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f341import = Macro.make(simpleSymbol66, syntaxRules10, syntax);
        $Pcimport = Macro.make(simpleSymbol65, new ModuleMethod(syntax, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), syntax);
        let$Mnvalues = Macro.make(simpleSymbol57, syntaxRules8, syntax);
        let$St$Mnvalues = Macro.make(simpleSymbol49, syntaxRules6, syntax);
        case$Mnlambda = Macro.make(simpleSymbol47, new ModuleMethod(syntax, 12, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), syntax);
        cond$Mnexpand = Macro.make(simpleSymbol46, syntaxRules4, syntax);
        ModuleMethod moduleMethod = new ModuleMethod(syntax, 13, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:227");
        $Pccond$Mnexpand = Macro.make(simpleSymbol45, moduleMethod, syntax);
        receive = Macro.make(simpleSymbol42, syntaxRules2, syntax);
        define$Mnalias$Mnparameter = Macro.make(simpleSymbol34, procedure, syntax);
        syntax.run();
    }

    public syntax() {
        ModuleInfo.register(this);
    }

    public static Object importHandlePrefix(Object obj, Object obj2) {
        return null;
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static Object lambda2(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        if (!Lit22.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        return new TryExp(SyntaxForms.rewrite(Lit23.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit24.execute(allocVars, TemplateScope.make())));
    }

    static Object lambda3(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        if (!Lit26.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        return new SynchronizedExp(SyntaxForms.rewrite(Lit27.execute(allocVars, TemplateScope.make())), SyntaxForms.rewriteBody(Lit28.execute(allocVars, TemplateScope.make())));
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 3) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 4) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 5) {
            switch (i) {
                case 10:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isIdentifierList(Object obj) {
        boolean z = true;
        boolean z2 = Translator.listLength(obj) >= 0;
        if (!z2) {
            return z2;
        }
        while (true) {
            Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
            if (Lit0.match(obj, allocVars, 0)) {
                boolean isIdentifier = std_syntax.isIdentifier(Lit1.execute(allocVars, TemplateScope.make()));
                if (!isIdentifier) {
                    z = isIdentifier;
                    break;
                }
                obj = Lit2.execute(allocVars, TemplateScope.make());
            } else if (!Lit3.match(obj, allocVars, 0) && (Lit4.match(obj, allocVars, 0) || syntax_case.error("syntax-case", obj) == Boolean.FALSE)) {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r1 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isIdentifierPairList(java.lang.Object r4) {
        /*
            int r0 = kawa.lang.Translator.listLength(r4)
            r1 = 1
            r2 = 0
            if (r0 < 0) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            if (r0 == 0) goto L_0x0066
        L_0x000d:
            r0 = 3
            r3 = 0
            java.lang.Object[] r0 = kawa.lang.SyntaxPattern.allocVars(r0, r3)
            kawa.lang.SyntaxPattern r3 = Lit5
            boolean r3 = r3.match(r4, r0, r2)
            if (r3 == 0) goto L_0x0048
            kawa.lang.TemplateScope r4 = kawa.lang.TemplateScope.make()
            kawa.lang.SyntaxTemplate r3 = Lit6
            java.lang.Object r4 = r3.execute(r0, r4)
            boolean r4 = kawa.lib.std_syntax.isIdentifier(r4)
            if (r4 == 0) goto L_0x0046
            kawa.lang.TemplateScope r4 = kawa.lang.TemplateScope.make()
            kawa.lang.SyntaxTemplate r3 = Lit7
            java.lang.Object r4 = r3.execute(r0, r4)
            boolean r4 = kawa.lib.std_syntax.isIdentifier(r4)
            if (r4 == 0) goto L_0x0046
            kawa.lang.TemplateScope r4 = kawa.lang.TemplateScope.make()
            kawa.lang.SyntaxTemplate r3 = Lit8
            java.lang.Object r4 = r3.execute(r0, r4)
            goto L_0x000d
        L_0x0046:
            r1 = r4
            goto L_0x0065
        L_0x0048:
            kawa.lang.SyntaxPattern r3 = Lit9
            boolean r3 = r3.match(r4, r0, r2)
            if (r3 == 0) goto L_0x0051
            goto L_0x0065
        L_0x0051:
            kawa.lang.SyntaxPattern r3 = Lit10
            boolean r0 = r3.match(r4, r0, r2)
            if (r0 == 0) goto L_0x005b
        L_0x0059:
            r1 = 0
            goto L_0x0065
        L_0x005b:
            java.lang.String r0 = "syntax-case"
            java.lang.Object r4 = kawa.standard.syntax_case.error(r0, r4)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            if (r4 == r0) goto L_0x0059
        L_0x0065:
            r0 = r1
        L_0x0066:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lib.syntax.isIdentifierPairList(java.lang.Object):boolean");
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 6:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object importHandleOnly(Object obj, Object obj2) {
        if (lists.memq(obj, obj2) != Boolean.FALSE) {
            return obj;
        }
        return null;
    }

    public static Object importHandleExcept(Object obj, Object obj2) {
        if (lists.memq(obj, obj2) != Boolean.FALSE) {
            return null;
        }
        return obj;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 6:
                return importHandleOnly(obj, obj2);
            case 7:
                return importHandleExcept(obj, obj2);
            case 8:
                return importHandlePrefix(obj, obj2);
            case 9:
                return importHandleRename(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Object importHandleRename(Object obj, Object obj2) {
        if (!lists.isPair(obj2)) {
            return obj;
        }
        if (obj == lists.caar.apply1(obj2)) {
            return lists.cadar.apply1(obj2);
        }
        return importHandleRename(obj, lists.cdr.apply1(obj2));
    }

    /* compiled from: syntax.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object list;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:83");
            this.lambda$Fn1 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda1(obj) : super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda1(Object obj) {
            Object obj2 = this.list;
            while (true) {
                boolean z = obj == null;
                if (z) {
                    if (z) {
                        break;
                    }
                    Object apply1 = lists.cdr.apply1(obj2);
                    obj = Scheme.applyToArgs.apply3(lists.caar.apply1(obj2), obj, lists.cdar.apply1(obj2));
                    obj2 = apply1;
                } else if (lists.isNull(obj2)) {
                    break;
                } else {
                    Object apply12 = lists.cdr.apply1(obj2);
                    obj = Scheme.applyToArgs.apply3(lists.caar.apply1(obj2), obj, lists.cdar.apply1(obj2));
                    obj2 = apply12;
                }
            }
            return obj;
        }
    }

    public static Procedure importMapper(Object obj) {
        frame frame2 = new frame();
        frame2.list = obj;
        return frame2.lambda$Fn1;
    }

    static Object lambda4(Object obj) {
        Object[] objArr;
        Object[] objArr2;
        Object[] objArr3;
        Object[] objArr4;
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        if (Lit39.match(obj, allocVars, 0)) {
            if (isIdentifierPairList(Lit40.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make = TemplateScope.make();
                return Quote.append$V(new Object[]{Lit41.execute(allocVars, make), Quote.consX$V(new Object[]{lists.cons(lists.cons(import$Mnhandle$Mnrename, Lit42.execute(allocVars, make)), Lit43.execute(allocVars, make)), Lit44.execute(allocVars, make)})});
            }
            Object execute = Lit45.execute(allocVars, TemplateScope.make());
            if (!("invalid 'rename' clause in import" instanceof Object[])) {
                objArr4 = new Object[]{"invalid 'rename' clause in import"};
            }
            return prim_syntax.syntaxError(execute, objArr4);
        } else if (Lit46.match(obj, allocVars, 0)) {
            if (isIdentifierList(Lit47.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make2 = TemplateScope.make();
                return Quote.append$V(new Object[]{Lit48.execute(allocVars, make2), Quote.consX$V(new Object[]{lists.cons(lists.cons(import$Mnhandle$Mnonly, Lit49.execute(allocVars, make2)), Lit50.execute(allocVars, make2)), Lit51.execute(allocVars, make2)})});
            }
            Object execute2 = Lit52.execute(allocVars, TemplateScope.make());
            if (!("invalid 'only' identifier list" instanceof Object[])) {
                objArr3 = new Object[]{"invalid 'only' identifier list"};
            }
            return prim_syntax.syntaxError(execute2, objArr3);
        } else if (Lit53.match(obj, allocVars, 0)) {
            if (isIdentifierList(Lit54.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make3 = TemplateScope.make();
                return Quote.append$V(new Object[]{Lit55.execute(allocVars, make3), Quote.consX$V(new Object[]{lists.cons(lists.cons(import$Mnhandle$Mnexcept, Lit56.execute(allocVars, make3)), Lit57.execute(allocVars, make3)), Lit58.execute(allocVars, make3)})});
            }
            Object execute3 = Lit59.execute(allocVars, TemplateScope.make());
            if (!("invalid 'except' identifier list" instanceof Object[])) {
                objArr2 = new Object[]{"invalid 'except' identifier list"};
            }
            return prim_syntax.syntaxError(execute3, objArr2);
        } else if (Lit60.match(obj, allocVars, 0)) {
            TemplateScope make4 = TemplateScope.make();
            return Quote.append$V(new Object[]{Lit61.execute(allocVars, make4), Quote.consX$V(new Object[]{lists.cons(lists.cons(import$Mnhandle$Mnprefix, Lit62.execute(allocVars, make4)), Lit63.execute(allocVars, make4)), Lit64.execute(allocVars, make4)})});
        } else if (Lit65.match(obj, allocVars, 0)) {
            Object execute4 = Lit66.execute(allocVars, TemplateScope.make());
            if (!("invalid prefix clause in import" instanceof Object[])) {
                objArr = new Object[]{"invalid prefix clause in import"};
            }
            return prim_syntax.syntaxError(execute4, objArr);
        } else if (Lit67.match(obj, allocVars, 0)) {
            TemplateScope make5 = TemplateScope.make();
            return Pair.make(Lit68.execute(allocVars, make5), Quote.append$V(new Object[]{Lit69.execute(allocVars, make5), Quote.consX$V(new Object[]{importMapper(std_syntax.syntaxObject$To$Datum(Lit70.execute(allocVars, make5))), Lit71.execute(allocVars, make5)})}));
        } else if (!Lit72.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            TemplateScope make6 = TemplateScope.make();
            return Pair.make(Lit73.execute(allocVars, make6), Quote.append$V(new Object[]{Lit74.execute(allocVars, make6), Quote.consX$V(new Object[]{importMapper(std_syntax.syntaxObject$To$Datum(Lit75.execute(allocVars, make6))), Lit76.execute(allocVars, make6)})}));
        }
    }

    static Object lambda5(Object obj) {
        frame0 frame02 = new frame0();
        frame02.$unnamed$1 = SyntaxPattern.allocVars(2, (Object[]) null);
        if (!Lit82.match(obj, frame02.$unnamed$1, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        frame02.$unnamed$0 = TemplateScope.make();
        return Pair.make(Lit83.execute(frame02.$unnamed$1, frame02.$unnamed$0), frame02.lambda6loop(Lit84.execute(frame02.$unnamed$1, frame02.$unnamed$0)));
    }

    /* compiled from: syntax.scm */
    public class frame0 extends ModuleBody {
        TemplateScope $unnamed$0;
        Object[] $unnamed$1;

        public Object lambda6loop(Object obj) {
            Object[] objArr;
            Object[] allocVars = SyntaxPattern.allocVars(5, this.$unnamed$1);
            if (syntax.Lit85.match(obj, allocVars, 0)) {
                return Pair.make(syntax.Lit86.execute(allocVars, this.$unnamed$0), lambda6loop(syntax.Lit87.execute(allocVars, this.$unnamed$0)));
            }
            if (syntax.Lit88.match(obj, allocVars, 0)) {
                return LList.Empty;
            }
            if (!syntax.Lit89.match(obj, allocVars, 0)) {
                return syntax_case.error("syntax-case", obj);
            }
            Object execute = syntax.Lit90.execute(allocVars, this.$unnamed$0);
            if (!("invalid case-lambda clause" instanceof Object[])) {
                objArr = new Object[]{"invalid case-lambda clause"};
            }
            return LList.list1(prim_syntax.syntaxError(execute, objArr));
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return lambda2(obj);
        }
        if (i == 3) {
            return lambda3(obj);
        }
        if (i == 4) {
            return isIdentifierList(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i == 5) {
            return isIdentifierPairList(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        switch (i) {
            case 10:
                return importMapper(obj);
            case 11:
                return lambda4(obj);
            case 12:
                return lambda5(obj);
            case 13:
                return lambda7(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    static Object lambda7(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        if (!Lit94.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        if (IfFeature.testFeature(Lit95.execute(allocVars, TemplateScope.make()))) {
            return Lit96.execute(allocVars, TemplateScope.make());
        }
        return Lit97.execute(allocVars, TemplateScope.make());
    }
}
