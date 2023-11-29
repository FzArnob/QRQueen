package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.Symbols;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: std_syntax.scm */
public class std_syntax extends ModuleBody {
    public static final Macro $Prvt$$Pccase;
    public static final Macro $Prvt$$Pccase$Mnmatch;
    public static final Macro $Prvt$$Pcdo$Mninit;
    public static final Macro $Prvt$$Pcdo$Mnlambda1;
    public static final Macro $Prvt$$Pcdo$Mnlambda2;
    public static final Macro $Prvt$$Pcdo$Mnstep;
    public static final Macro $Prvt$$Pclet$Mninit;
    public static final Macro $Prvt$$Pclet$Mnlambda1;
    public static final Macro $Prvt$$Pclet$Mnlambda2;
    public static final Location $Prvt$define = StaticFieldLocation.make("kawa.lib.prim_syntax", "define");
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final Location $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    public static final Location $Prvt$letrec = StaticFieldLocation.make("kawa.lib.prim_syntax", "letrec");
    public static final std_syntax $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SyntaxPattern Lit11 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
    static final SyntaxPattern Lit12 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
    static final SyntaxTemplate Lit13 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit14 = new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
    static final SyntaxPattern Lit18 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
    static final SyntaxTemplate Lit19 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SimpleSymbol Lit2;
    static final SyntaxPattern Lit20 = new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit30;
    static final SyntaxRules Lit31;
    static final SimpleSymbol Lit32;
    static final SyntaxRules Lit33;
    static final SimpleSymbol Lit34;
    static final SyntaxRules Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxRules Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SyntaxRules Lit41;
    static final SimpleSymbol Lit42;
    static final SyntaxRules Lit43;
    static final SimpleSymbol Lit44;
    static final SyntaxRules Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SyntaxPattern Lit55 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
    static final SimpleSymbol Lit56;
    static final SyntaxTemplate Lit57 = new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
    static final SyntaxTemplate Lit58 = new SyntaxTemplate("\u0001\u0000", "\u0018\u0004", new Object[]{Values.empty}, 0);
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit60;
    static final SimpleSymbol Lit61;
    static final SyntaxRules Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro and;
    public static final Macro begin$Mnfor$Mnsyntax;

    /* renamed from: case  reason: not valid java name */
    public static final Macro f339case;
    public static final Macro cond;
    public static final ModuleMethod datum$Mn$Grsyntax$Mnobject;
    public static final Macro define$Mnfor$Mnsyntax;
    public static final Macro define$Mnprocedure;
    public static final Macro delay;

    /* renamed from: do  reason: not valid java name */
    public static final Macro f340do;
    public static final ModuleMethod free$Mnidentifier$Eq$Qu;
    public static final ModuleMethod generate$Mntemporaries;
    public static final ModuleMethod identifier$Qu;
    public static final Macro let;
    public static final Macro let$St;
    public static final Macro or;
    public static final ModuleMethod syntax$Mncolumn;
    public static final ModuleMethod syntax$Mnline;
    public static final ModuleMethod syntax$Mnobject$Mn$Grdatum;
    public static final ModuleMethod syntax$Mnsource;
    public static final Macro with$Mnsyntax;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("temp").readResolve();
        Lit77 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("=>").readResolve();
        Lit76 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit75 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("eqv?").readResolve();
        Lit74 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol(EllipticCurveJsonWebKey.X_MEMBER_NAME).readResolve();
        Lit73 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit72 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("letrec").readResolve();
        Lit71 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("%let").readResolve();
        Lit70 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("%syntax-error").readResolve();
        Lit69 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit68 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("make").readResolve();
        Lit67 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit66 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("<gnu.expr.GenericProc>").readResolve();
        Lit65 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit64 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("syntax-case").readResolve();
        Lit63 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = simpleSymbol2;
        SimpleSymbol simpleSymbol18 = simpleSymbol3;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("with-syntax").readResolve();
        Lit61 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol19;
        SimpleSymbol simpleSymbol21 = simpleSymbol4;
        SimpleSymbol simpleSymbol22 = simpleSymbol5;
        SimpleSymbol simpleSymbol23 = simpleSymbol8;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\b\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit56 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = simpleSymbol9;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol19}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{simpleSymbol24}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\t\u0010\b\t\u0003\b\u0011\u0018\f\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol15, simpleSymbol24}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L-\f\u0007\f\u000f\b\u0000\u0010\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0003\u0003\u0001\u0003", "\u0011\u0018\u00041\u0011\u0018\f\b\r\u000b\t\u0010\b\u0019\b\u0005\u0003\b\u0011\u0018\u0014\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol15, (SimpleSymbol) new SimpleSymbol("list").readResolve(), simpleSymbol24}, 1)}, 4);
        Lit62 = syntaxRules;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("define-for-syntax").readResolve();
        Lit59 = simpleSymbol27;
        SyntaxRules syntaxRules2 = syntaxRules;
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol28 = simpleSymbol27;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("begin-for-syntax").readResolve();
        Lit54 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol29;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol27}, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0000", "\u0011\u0018\u0004\b\u0011\u0018\f\u0002", new Object[]{simpleSymbol29, (SimpleSymbol) new SimpleSymbol("define").readResolve()}, 0)}, 1);
        Lit60 = syntaxRules3;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("syntax-column").readResolve();
        Lit53 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("syntax-line").readResolve();
        Lit52 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("syntax-source").readResolve();
        Lit51 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("free-identifier=?").readResolve();
        Lit50 = simpleSymbol34;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("identifier?").readResolve();
        Lit49 = simpleSymbol35;
        SyntaxRules syntaxRules4 = syntaxRules3;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("generate-temporaries").readResolve();
        Lit48 = simpleSymbol36;
        SimpleSymbol simpleSymbol37 = simpleSymbol31;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("datum->syntax-object").readResolve();
        Lit47 = simpleSymbol38;
        SimpleSymbol simpleSymbol39 = simpleSymbol32;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("syntax-object->datum").readResolve();
        Lit46 = simpleSymbol40;
        SimpleSymbol simpleSymbol41 = simpleSymbol33;
        SimpleSymbol simpleSymbol42 = simpleSymbol34;
        SimpleSymbol simpleSymbol43 = simpleSymbol35;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("define-procedure").readResolve();
        Lit44 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = simpleSymbol36;
        SimpleSymbol simpleSymbol46 = simpleSymbol38;
        SimpleSymbol simpleSymbol47 = simpleSymbol40;
        SimpleSymbol simpleSymbol48 = simpleSymbol44;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol44, simpleSymbol14, simpleSymbol13}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004Á\u0011\u0018\f\t\u0003\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\u0011\u0018\u001c\b\u0011\u0018,\b\u0003\b\u0011\u00184\t\u0003\u0011\u0018<\b\u0011\u0018D\b\r\u000b", new Object[]{simpleSymbol24, (SimpleSymbol) new SimpleSymbol("define-constant").readResolve(), simpleSymbol14, simpleSymbol13, simpleSymbol11, simpleSymbol12, (SimpleSymbol) new SimpleSymbol("invoke").readResolve(), PairWithPosition.make(simpleSymbol12, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("setProperties").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), (SimpleSymbol) new SimpleSymbol("java.lang.Object[]").readResolve()}, 1)}, 2);
        Lit45 = syntaxRules5;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("delay").readResolve();
        Lit42 = simpleSymbol49;
        SyntaxRules syntaxRules6 = new SyntaxRules(new Object[]{simpleSymbol49}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0010\b\u0003", new Object[]{simpleSymbol11, (SimpleSymbol) new SimpleSymbol("<kawa.lang.Promise>").readResolve(), simpleSymbol10}, 0)}, 1);
        Lit43 = syntaxRules6;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("do").readResolve();
        Lit40 = simpleSymbol50;
        Object[] objArr = {simpleSymbol50, simpleSymbol14};
        SyntaxRules syntaxRules7 = syntaxRules5;
        SyntaxRules syntaxRules8 = syntaxRules6;
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\u001c\f\u000f\u0013\r\u001f\u0018\b\b", new Object[0], 4);
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("%do-lambda1").readResolve();
        Lit36 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = simpleSymbol49;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("%do-step").readResolve();
        Lit32 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol50;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("%do-init").readResolve();
        Lit34 = simpleSymbol55;
        SyntaxRules syntaxRules9 = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern3, "\u0003\u0001\u0000\u0003", "\u0011\u0018\u0004Ɖ\b\u0011\u0018\f\b\u0011\u0018\u0014\u0019\b\u0005\u0003\t\u0010\b\u0011\u0018\u001c)\u0011\u0018$\b\u000b\u0011\u0018,\u0011\u001d\u001b\b\u0011\u0018\f\b\u0005\u0011\u00184\u0003\b\u0011\u0018,\u0011\u0018<\u0012\b\u0011\u0018\f\b\u0005\u0011\u0018D\b\u0003", new Object[]{simpleSymbol7, (SimpleSymbol) new SimpleSymbol("%do%loop").readResolve(), simpleSymbol51, simpleSymbol26, (SimpleSymbol) new SimpleSymbol("not").readResolve(), simpleSymbol24, simpleSymbol53, Values.empty, simpleSymbol55}, 1)}, 4);
        Lit41 = syntaxRules9;
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("%do-lambda2").readResolve();
        Lit38 = simpleSymbol56;
        SyntaxRules syntaxRules10 = syntaxRules9;
        SimpleSymbol simpleSymbol57 = simpleSymbol24;
        SimpleSymbol simpleSymbol58 = simpleSymbol12;
        SyntaxRules syntaxRules11 = new SyntaxRules(new Object[]{simpleSymbol56}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\n\u0019\t\u0003\u0013\b\u001b", new Object[]{simpleSymbol56}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol10}, 0)}, 4);
        Lit39 = syntaxRules11;
        SyntaxRules syntaxRules12 = syntaxRules11;
        SimpleSymbol simpleSymbol59 = simpleSymbol10;
        SimpleSymbol simpleSymbol60 = simpleSymbol7;
        SyntaxRules syntaxRules13 = new SyntaxRules(new Object[]{simpleSymbol51, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018l\\\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b#\f/\f7\b", new Object[]{simpleSymbol14}, 7), "\u0001\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\"I9\t\u0003\u0011\u0018\f\b\u000b+\b3", new Object[]{simpleSymbol51, simpleSymbol14}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\\L\f\u0007\f\u0002\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[]{simpleSymbol14}, 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001aI9\t\u0003\u0011\u0018\f\b\u000b#\b+", new Object[]{simpleSymbol51, simpleSymbol14}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L<\f\u0007\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[0], 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a\u0019\t\u0003#\b+", new Object[]{simpleSymbol51}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u0012\u0019\t\u0003\u001b\b#", new Object[]{simpleSymbol51}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u0010\b\u000b", new Object[]{simpleSymbol56}, 0)}, 7);
        Lit37 = syntaxRules13;
        SimpleSymbol simpleSymbol61 = simpleSymbol56;
        SimpleSymbol simpleSymbol62 = simpleSymbol51;
        SyntaxRules syntaxRules14 = syntaxRules13;
        SimpleSymbol simpleSymbol63 = simpleSymbol25;
        SimpleSymbol simpleSymbol64 = simpleSymbol55;
        SyntaxRules syntaxRules15 = new SyntaxRules(new Object[]{simpleSymbol55, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b\b", new Object[]{simpleSymbol14}, 4), "\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u0002\f\u000f\f\u0017\b\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\b", new Object[0], 2), "\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("do binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794643), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794628)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("do binding must have syntax: (var [:: type] init [step])", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 806917), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 802820)}, 0)}, 4);
        Lit35 = syntaxRules15;
        SyntaxRules syntaxRules16 = syntaxRules15;
        SyntaxRules syntaxRules17 = new SyntaxRules(new Object[]{simpleSymbol53, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b", new Object[]{simpleSymbol14}, 4), "\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0003", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0003", new Object[0], 0)}, 4);
        Lit33 = syntaxRules17;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol("let*").readResolve();
        Lit30 = simpleSymbol65;
        SyntaxRules syntaxRules18 = syntaxRules17;
        SimpleSymbol simpleSymbol66 = simpleSymbol53;
        SyntaxRules syntaxRules19 = new SyntaxRules(new Object[]{simpleSymbol65}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\u0003", new Object[0], 1), "\u0000", "\u0011\u0018\u0004\t\u0010\u0002", new Object[]{simpleSymbol23}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\u000b", new Object[0], 2), "\u0001\u0000", "\u0011\u0018\u0004\u0011\b\u0003\n", new Object[]{simpleSymbol23}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\u0011\b\u0003\b\u0011\u0018\f\t\n\u0012", new Object[]{simpleSymbol23, simpleSymbol65}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\u000b", new Object[0], 2), "\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("invalid bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 679943), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 675846)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u0003", new Object[0], 1), "\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("missing bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 692231), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 688134)}, 0)}, 3);
        Lit31 = syntaxRules19;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit28 = simpleSymbol67;
        SyntaxRules syntaxRules20 = syntaxRules19;
        SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\u0018\f\u0007,\r\u000f\b\b\b\u0013", new Object[0], 3);
        SimpleSymbol simpleSymbol68 = (SimpleSymbol) new SimpleSymbol("%let-lambda1").readResolve();
        Lit22 = simpleSymbol68;
        SimpleSymbol simpleSymbol69 = simpleSymbol65;
        SimpleSymbol simpleSymbol70 = (SimpleSymbol) new SimpleSymbol("%let-init").readResolve();
        Lit26 = simpleSymbol70;
        SyntaxRules syntaxRules21 = new SyntaxRules(new Object[]{simpleSymbol67}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\u000b", new Object[0], 2), "\u0003\u0000", "\u0011\u0018\u0004\u0019\b\u0005\u0003\n", new Object[]{simpleSymbol23}, 1), new SyntaxRule(syntaxPattern4, "\u0001\u0003\u0000", "©\u0011\u0018\u0004y\b\t\u0003\b\u0011\u0018\f\u0019\b\r\u000b\t\u0010\b\u0012\b\u0003\b\r\u0011\u0018\u0014\b\u000b", new Object[]{simpleSymbol60, simpleSymbol68, simpleSymbol70}, 1)}, 3);
        Lit29 = syntaxRules21;
        SyntaxRules syntaxRules22 = syntaxRules21;
        SimpleSymbol simpleSymbol71 = simpleSymbol67;
        SimpleSymbol simpleSymbol72 = simpleSymbol70;
        SyntaxRules syntaxRules23 = new SyntaxRules(new Object[]{simpleSymbol70, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\b", new Object[0], 2), "\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u0002\f\u000f\f\u0017\b\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("let binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552979), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552964)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("let binding must have syntax: (var [type] init)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 565253), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 561156)}, 0)}, 4);
        Lit27 = syntaxRules23;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("%let-lambda2").readResolve();
        Lit24 = simpleSymbol73;
        SyntaxRules syntaxRules24 = new SyntaxRules(new Object[]{simpleSymbol73}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\n\u0019\t\u0003\u0013\b\u001b", new Object[]{simpleSymbol73}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u000b", new Object[]{simpleSymbol59}, 0)}, 4);
        Lit25 = syntaxRules24;
        SyntaxRules syntaxRules25 = syntaxRules23;
        SyntaxRules syntaxRules26 = new SyntaxRules(new Object[]{simpleSymbol68, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018L<\f\u0007\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[0], 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a1!\t\u0003\b\u000b#\b+", new Object[]{simpleSymbol68}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\\L\f\u0007\f\u0002\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[]{simpleSymbol14}, 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a1!\t\u0003\b\u000b#\b+", new Object[]{simpleSymbol68}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u0012\u0019\t\u0003\u001b\b#", new Object[]{simpleSymbol68}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u0010\b\u000b", new Object[]{simpleSymbol73}, 0)}, 6);
        Lit23 = syntaxRules26;
        Lit21 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f\u0011\u0018\f\b\t\u0003\b\u0015\u0013", new Object[]{simpleSymbol23, simpleSymbol22, simpleSymbol26}, 1);
        SimpleSymbol simpleSymbol74 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit16 = simpleSymbol74;
        Lit15 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f)\t\u0003\b\u0015\u0013\u0018\u001c", new Object[]{simpleSymbol23, simpleSymbol22, simpleSymbol26, PairWithPosition.make(simpleSymbol22, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 385052)}, 1);
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit10 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = (SimpleSymbol) new SimpleSymbol("%case-match").readResolve();
        Lit8 = simpleSymbol76;
        SyntaxRules syntaxRules27 = syntaxRules24;
        SimpleSymbol simpleSymbol77 = simpleSymbol73;
        SyntaxRules syntaxRules28 = new SyntaxRules(new Object[]{simpleSymbol76}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\u000b", new Object[]{simpleSymbol21, simpleSymbol58}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004Y\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\b\u000b\b\u0011\u0018\u001c\t\u0003\b\u0015\u0013", new Object[]{simpleSymbol74, simpleSymbol21, simpleSymbol58, simpleSymbol76}, 1)}, 3);
        Lit9 = syntaxRules28;
        SimpleSymbol simpleSymbol78 = (SimpleSymbol) new SimpleSymbol("%case").readResolve();
        Lit6 = simpleSymbol78;
        SimpleSymbol simpleSymbol79 = simpleSymbol68;
        SyntaxRules syntaxRules29 = syntaxRules26;
        SimpleSymbol simpleSymbol80 = simpleSymbol75;
        SyntaxRules syntaxRules30 = new SyntaxRules(new Object[]{simpleSymbol78, simpleSymbol18}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\b", new Object[]{simpleSymbol18}, 2), "\u0001\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{simpleSymbol57}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\u0013", new Object[]{simpleSymbol18}, 3), "\u0001\u0003\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("junk following else (in case)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 241674), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 237577)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\r\u000f\b\b\b\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004A\u0011\u0018\f\t\u0003\b\r\u000b\b\u0011\u0018\u0014\b\u0015\u0013", new Object[]{simpleSymbol26, simpleSymbol76, simpleSymbol57}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\r\u000f\b\b\b\r\u0017\u0010\b\b\f\u001f\r' \b\b", new Object[0], 5), "\u0001\u0003\u0003\u0001\u0003", "\u0011\u0018\u0004A\u0011\u0018\f\t\u0003\b\r\u000b1\u0011\u0018\u0014\b\u0015\u0013\b\u0011\u0018\u001c\t\u0003\t\u001b\b%#", new Object[]{simpleSymbol26, simpleSymbol76, simpleSymbol57, simpleSymbol78}, 1)}, 5);
        Lit7 = syntaxRules30;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("case").readResolve();
        Lit4 = simpleSymbol81;
        SyntaxRules syntaxRules31 = syntaxRules28;
        SyntaxRules syntaxRules32 = new SyntaxRules(new Object[]{simpleSymbol81}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f\b\r\u000b", new Object[]{simpleSymbol23, (SimpleSymbol) new SimpleSymbol("tmp").readResolve(), simpleSymbol78}, 1)}, 2);
        Lit5 = syntaxRules32;
        SimpleSymbol simpleSymbol82 = (SimpleSymbol) new SimpleSymbol("cond").readResolve();
        Lit2 = simpleSymbol82;
        SimpleSymbol simpleSymbol83 = simpleSymbol76;
        SyntaxRules syntaxRules33 = syntaxRules30;
        SimpleSymbol simpleSymbol84 = simpleSymbol78;
        SimpleSymbol simpleSymbol85 = simpleSymbol16;
        SyntaxRules syntaxRules34 = new SyntaxRules(new Object[]{simpleSymbol82, simpleSymbol18, simpleSymbol17}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0002\f\u0007\r\u000f\b\b\b\b", new Object[]{simpleSymbol18}, 2), "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{simpleSymbol57}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{simpleSymbol18}, 3), "\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol63, PairWithPosition.make("else clause must be last clause of cond", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86035), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86020)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u0002\f\u000f\b\b", new Object[]{simpleSymbol17}, 2), "\u0001\u0001", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f\b\t\u000b\u0018\u001c", new Object[]{simpleSymbol23, simpleSymbol16, simpleSymbol26, PairWithPosition.make(simpleSymbol85, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 102423)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u0002\f\u000f\b\f\u0017\r\u001f\u0018\b\b", new Object[]{simpleSymbol17}, 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f!\t\u000b\u0018\u001c\b\u0011\u0018$\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol23, simpleSymbol85, simpleSymbol26, PairWithPosition.make(simpleSymbol85, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 122898), simpleSymbol82}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0003", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol74, simpleSymbol82}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol26, simpleSymbol57}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\r\u0017\u0010\b\b\f\u001f\r' \b\b", new Object[0], 5), "\u0001\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\t\u0003A\u0011\u0018\f\t\u000b\b\u0015\u0013\b\u0011\u0018\u0014\t\u001b\b%#", new Object[]{simpleSymbol26, simpleSymbol57, simpleSymbol82}, 1)}, 5);
        Lit3 = syntaxRules34;
        std_syntax std_syntax = new std_syntax();
        $instance = std_syntax;
        cond = Macro.make(simpleSymbol82, syntaxRules34, std_syntax);
        f339case = Macro.make(simpleSymbol81, syntaxRules32, std_syntax);
        $Prvt$$Pccase = Macro.make(simpleSymbol84, syntaxRules33, std_syntax);
        $Prvt$$Pccase$Mnmatch = Macro.make(simpleSymbol83, syntaxRules31, std_syntax);
        and = Macro.make(simpleSymbol80, new ModuleMethod(std_syntax, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), std_syntax);
        or = Macro.make(simpleSymbol74, new ModuleMethod(std_syntax, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), std_syntax);
        $Prvt$$Pclet$Mnlambda1 = Macro.make(simpleSymbol79, syntaxRules29, std_syntax);
        $Prvt$$Pclet$Mnlambda2 = Macro.make(simpleSymbol77, syntaxRules27, std_syntax);
        $Prvt$$Pclet$Mninit = Macro.make(simpleSymbol72, syntaxRules25, std_syntax);
        let = Macro.make(simpleSymbol71, syntaxRules22, std_syntax);
        let$St = Macro.make(simpleSymbol69, syntaxRules20, std_syntax);
        $Prvt$$Pcdo$Mnstep = Macro.make(simpleSymbol66, syntaxRules18, std_syntax);
        $Prvt$$Pcdo$Mninit = Macro.make(simpleSymbol64, syntaxRules16, std_syntax);
        $Prvt$$Pcdo$Mnlambda1 = Macro.make(simpleSymbol62, syntaxRules14, std_syntax);
        $Prvt$$Pcdo$Mnlambda2 = Macro.make(simpleSymbol61, syntaxRules12, std_syntax);
        f340do = Macro.make(simpleSymbol54, syntaxRules10, std_syntax);
        delay = Macro.make(simpleSymbol52, syntaxRules8, std_syntax);
        define$Mnprocedure = Macro.make(simpleSymbol48, syntaxRules7, std_syntax);
        syntax$Mnobject$Mn$Grdatum = new ModuleMethod(std_syntax, 3, simpleSymbol47, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        datum$Mn$Grsyntax$Mnobject = new ModuleMethod(std_syntax, 4, simpleSymbol46, 8194);
        generate$Mntemporaries = new ModuleMethod(std_syntax, 5, simpleSymbol45, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identifier$Qu = new ModuleMethod(std_syntax, 6, simpleSymbol43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        free$Mnidentifier$Eq$Qu = new ModuleMethod(std_syntax, 7, simpleSymbol42, 8194);
        syntax$Mnsource = new ModuleMethod(std_syntax, 8, simpleSymbol41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        syntax$Mnline = new ModuleMethod(std_syntax, 9, simpleSymbol39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        syntax$Mncolumn = new ModuleMethod(std_syntax, 10, simpleSymbol37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod = new ModuleMethod(std_syntax, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm:298");
        begin$Mnfor$Mnsyntax = Macro.make(simpleSymbol30, moduleMethod, std_syntax);
        define$Mnfor$Mnsyntax = Macro.make(simpleSymbol28, syntaxRules4, std_syntax);
        with$Mnsyntax = Macro.make(simpleSymbol20, syntaxRules2, std_syntax);
        std_syntax.run();
    }

    public std_syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static Object lambda1(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        if (Lit11.match(obj, allocVars, 0)) {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(true));
        }
        if (Lit12.match(obj, allocVars, 0)) {
            return Lit13.execute(allocVars, TemplateScope.make());
        } else if (!Lit14.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            return Lit15.execute(allocVars, TemplateScope.make());
        }
    }

    static Object lambda2(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        if (Lit17.match(obj, allocVars, 0)) {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(false));
        }
        if (Lit18.match(obj, allocVars, 0)) {
            return Lit19.execute(allocVars, TemplateScope.make());
        } else if (!Lit20.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            return Lit21.execute(allocVars, TemplateScope.make());
        }
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
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
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
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static Object syntaxObject$To$Datum(Object obj) {
        return Quote.quote(obj);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 4) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 7) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object datum$To$SyntaxObject(Object obj, Object obj2) {
        return SyntaxForms.makeWithTemplate(obj, obj2);
    }

    public static Object generateTemporaries(Object obj) {
        Object valueOf = Integer.valueOf(Translator.listLength(obj));
        LList lList = LList.Empty;
        while (Scheme.numEqu.apply2(valueOf, Lit0) == Boolean.FALSE) {
            valueOf = AddOp.$Mn.apply2(valueOf, Lit1);
            lList = new Pair(datum$To$SyntaxObject(obj, Symbols.gentemp()), lList);
        }
        return lList;
    }

    public static boolean isIdentifier(Object obj) {
        boolean z = obj instanceof Symbol;
        if (z) {
            return z;
        }
        boolean z2 = obj instanceof SyntaxForm;
        if (!z2) {
            return z2;
        }
        try {
            return SyntaxForms.isIdentifier((SyntaxForm) obj);
        } catch (ClassCastException e) {
            throw new WrongType(e, "kawa.lang.SyntaxForms.isIdentifier(kawa.lang.SyntaxForm)", 1, obj);
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 4) {
            return datum$To$SyntaxObject(obj, obj2);
        }
        if (i != 7) {
            return super.apply2(moduleMethod, obj, obj2);
        }
        return isFreeIdentifier$Eq(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean isFreeIdentifier$Eq(Object obj, Object obj2) {
        try {
            try {
                return SyntaxForms.freeIdentifierEquals((SyntaxForm) obj, (SyntaxForm) obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 2, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 1, obj);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r1 = ((gnu.lists.PairWithPosition) r1).getFileName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object syntaxSource(java.lang.Object r1) {
        /*
            boolean r0 = r1 instanceof kawa.lang.SyntaxForm
            if (r0 == 0) goto L_0x000f
            kawa.lang.SyntaxForm r1 = (kawa.lang.SyntaxForm) r1
            java.lang.Object r1 = r1.getDatum()
            java.lang.Object r1 = syntaxSource(r1)
            goto L_0x001d
        L_0x000f:
            boolean r0 = r1 instanceof gnu.lists.PairWithPosition
            if (r0 == 0) goto L_0x001b
            gnu.lists.PairWithPosition r1 = (gnu.lists.PairWithPosition) r1
            java.lang.String r1 = r1.getFileName()
            if (r1 != 0) goto L_0x001d
        L_0x001b:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
        L_0x001d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lib.std_syntax.syntaxSource(java.lang.Object):java.lang.Object");
    }

    public static Object syntaxLine(Object obj) {
        if (obj instanceof SyntaxForm) {
            return syntaxLine(((SyntaxForm) obj).getDatum());
        }
        return obj instanceof PairWithPosition ? Integer.valueOf(((PairWithPosition) obj).getLineNumber()) : Boolean.FALSE;
    }

    public static Object syntaxColumn(Object obj) {
        if (obj instanceof SyntaxForm) {
            return syntaxLine(((SyntaxForm) obj).getDatum());
        }
        return obj instanceof PairWithPosition ? Integer.valueOf(((PairWithPosition) obj).getColumnNumber() + 0) : Boolean.FALSE;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return lambda1(obj);
            case 2:
                return lambda2(obj);
            case 3:
                return syntaxObject$To$Datum(obj);
            case 5:
                return generateTemporaries(obj);
            case 6:
                return isIdentifier(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return syntaxSource(obj);
            case 9:
                return syntaxLine(obj);
            case 10:
                return syntaxColumn(obj);
            case 11:
                return lambda3(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    static Object lambda3(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
        if (Lit55.match(obj, allocVars, 0)) {
            if (Eval.eval.apply1(syntaxObject$To$Datum(new Pair(Lit56, Lit57.execute(allocVars, TemplateScope.make())))) != Boolean.FALSE) {
                return Lit58.execute(allocVars, TemplateScope.make());
            }
        }
        return syntax_case.error("syntax-case", obj);
    }
}
