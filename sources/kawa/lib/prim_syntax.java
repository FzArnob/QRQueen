package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import com.microsoft.appcenter.Constants;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;
import kawa.standard.syntax_error;
import kawa.standard.try_catch;
import org.slf4j.Marker;

/* compiled from: prim_syntax.scm */
public class prim_syntax extends ModuleBody {
    public static final prim_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SyntaxRules Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxPattern Lit14 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
    static final SyntaxTemplate Lit15 = new SyntaxTemplate("\u0001\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit16 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
    static final SyntaxTemplate Lit18 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit19 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SimpleSymbol Lit2;
    static final SyntaxTemplate Lit20 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0);
    static final SyntaxPattern Lit21 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\f'+", new Object[0], 6);
    static final SyntaxTemplate Lit22 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001\u0000", "#", new Object[0], 0);
    static final SyntaxPattern Lit23 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
    static final SyntaxTemplate Lit24 = new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26 = new SyntaxPattern("\f\u0007\f\u000f-\f\u0017\f\u001f#\u0010\u0018\b", new Object[0], 5);
    static final SyntaxTemplate Lit27 = new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxRules Lit3;
    static final SyntaxPattern Lit30 = new SyntaxPattern("\f\u0007\f\u000f\u0013", new Object[0], 3);
    static final SyntaxTemplate Lit31 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit32 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("%let").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 512011)}, 0);
    static final SyntaxTemplate Lit33 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0012", new Object[0], 0);
    static final SyntaxPattern Lit34 = new SyntaxPattern("\b", new Object[0], 3);
    static final SyntaxPattern Lit35 = new SyntaxPattern(",\f\u001f\f'\b+", new Object[0], 6);
    static final SyntaxTemplate Lit36 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\t\u001b\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 450594)}, 0);
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", Marker.ANY_MARKER, new Object[0], 0);
    static final SyntaxPattern Lit39 = new SyntaxPattern("L\f\u001f\f'\f/\f7\b;", new Object[0], 8);
    static final SimpleSymbol Lit4;
    static final SyntaxTemplate Lit40 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\t\u001b\t#\t+\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 471102)}, 0);
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, new Object[0], 0);
    static final SyntaxPattern Lit43 = new SyntaxPattern("\u001c\f\u001f\b#", new Object[0], 5);
    static final SyntaxPattern Lit44 = new SyntaxPattern("\u001b", new Object[0], 4);
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final IntNum Lit51;
    static final IntNum Lit52;
    static final IntNum Lit53;
    static final IntNum Lit54;
    static final IntNum Lit55;
    static final IntNum Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro define;
    public static final Macro define$Mnconstant;
    public static final Macro define$Mnprivate;
    public static final Macro define$Mnsyntax;

    /* renamed from: if  reason: not valid java name */
    public static final Macro f338if;
    public static final Macro letrec;
    public static final Macro syntax$Mn$Grexpression;
    public static final Macro syntax$Mnbody$Mn$Grexpression;
    public static final ModuleMethod syntax$Mnerror;
    public static final Macro try$Mncatch;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit58 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("%define-syntax").readResolve();
        Lit57 = simpleSymbol2;
        IntNum make = IntNum.make(0);
        Lit56 = make;
        IntNum make2 = IntNum.make(1);
        Lit55 = make2;
        IntNum make3 = IntNum.make(4);
        Lit54 = make3;
        IntNum make4 = IntNum.make(5);
        Lit53 = make4;
        IntNum make5 = IntNum.make(8);
        Lit52 = make5;
        IntNum make6 = IntNum.make(9);
        Lit51 = make6;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("%define").readResolve();
        Lit50 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit49 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit48 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("kawa.lang.SyntaxForms").readResolve();
        Lit47 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit46 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("set!").readResolve();
        Lit45 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = simpleSymbol;
        SimpleSymbol simpleSymbol10 = simpleSymbol2;
        IntNum intNum = make;
        Lit41 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b3", new Object[]{simpleSymbol8}, 0);
        IntNum intNum2 = make2;
        IntNum intNum3 = make3;
        Lit37 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b#", new Object[]{simpleSymbol8}, 0);
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("letrec").readResolve();
        Lit29 = simpleSymbol11;
        Lit28 = new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "(\b\u0015A\b\t\u0013\u0011\u0018\u0004\b\u001b\"", new Object[]{simpleSymbol4}, 1);
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("try-catch").readResolve();
        Lit25 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = simpleSymbol11;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit13 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("syntax-body->expression").readResolve();
        Lit11 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol14;
        SimpleSymbol simpleSymbol17 = simpleSymbol12;
        SimpleSymbol simpleSymbol18 = simpleSymbol15;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol15}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol7, Pair.make(simpleSymbol6, Pair.make(Pair.make(simpleSymbol5, Pair.make((SimpleSymbol) new SimpleSymbol("rewriteBody").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 270343)}, 0)}, 1);
        Lit12 = syntaxRules;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("syntax->expression").readResolve();
        Lit9 = simpleSymbol19;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol20 = simpleSymbol19;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol19}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol7, Pair.make(simpleSymbol6, Pair.make(Pair.make(simpleSymbol5, Pair.make((SimpleSymbol) new SimpleSymbol("rewrite").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 249863)}, 0)}, 1);
        Lit10 = syntaxRules3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("syntax-error").readResolve();
        Lit8 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("define-constant").readResolve();
        Lit6 = simpleSymbol22;
        SyntaxRules syntaxRules4 = syntaxRules3;
        SimpleSymbol simpleSymbol23 = simpleSymbol21;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{simpleSymbol7}, 4);
        Object[] objArr = new Object[4];
        objArr[0] = simpleSymbol3;
        objArr[1] = simpleSymbol7;
        objArr[2] = make5;
        SimpleSymbol simpleSymbol24 = simpleSymbol22;
        String str = "\f\u0018\u001c\f\u0007\u000b\u0013";
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr2 = new Object[3];
        objArr2[0] = simpleSymbol3;
        objArr2[1] = make5;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol22, simpleSymbol4, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", new Object[]{simpleSymbol3, simpleSymbol7, make6}, 0), new SyntaxRule(syntaxPattern, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", new Object[]{simpleSymbol3, IntNum.make(10), Boolean.TRUE}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", new Object[]{simpleSymbol3, make6}, 0), new SyntaxRule(syntaxPattern2, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr2, 0)}, 5);
        Lit7 = syntaxRules5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("define-private").readResolve();
        Lit4 = simpleSymbol25;
        SyntaxRules syntaxRules6 = syntaxRules5;
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5);
        Object[] objArr3 = {simpleSymbol3, simpleSymbol7, make4};
        String str2 = "\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b";
        SyntaxPattern syntaxPattern4 = new SyntaxPattern(str2, new Object[]{simpleSymbol7}, 4);
        Object[] objArr4 = new Object[4];
        objArr4[0] = simpleSymbol3;
        objArr4[1] = simpleSymbol7;
        objArr4[2] = intNum3;
        SimpleSymbol simpleSymbol26 = simpleSymbol25;
        SyntaxPattern syntaxPattern5 = new SyntaxPattern(str, new Object[0], 3);
        Object[] objArr5 = {simpleSymbol3, IntNum.make(6), Boolean.TRUE};
        SyntaxPattern syntaxPattern6 = new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3);
        Object[] objArr6 = {simpleSymbol3, make4};
        SyntaxPattern syntaxPattern7 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr7 = new Object[3];
        objArr7[0] = simpleSymbol3;
        objArr7[1] = intNum3;
        SyntaxRules syntaxRules7 = new SyntaxRules(new Object[]{simpleSymbol25, simpleSymbol4, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(syntaxPattern3, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr3, 0), new SyntaxRule(syntaxPattern4, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr4, 0), new SyntaxRule(syntaxPattern5, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr5, 0), new SyntaxRule(syntaxPattern6, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr6, 0), new SyntaxRule(syntaxPattern7, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr7, 0)}, 5);
        Lit5 = syntaxRules7;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
        Lit2 = simpleSymbol27;
        SyntaxRules syntaxRules8 = syntaxRules7;
        SyntaxPattern syntaxPattern8 = new SyntaxPattern(str2, new Object[]{simpleSymbol7}, 4);
        Object[] objArr8 = new Object[4];
        objArr8[0] = simpleSymbol3;
        objArr8[1] = simpleSymbol7;
        objArr8[2] = intNum;
        String str3 = str;
        SyntaxPattern syntaxPattern9 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr9 = new Object[3];
        objArr9[0] = simpleSymbol3;
        objArr9[1] = intNum;
        SyntaxRules syntaxRules9 = new SyntaxRules(new Object[]{simpleSymbol27, simpleSymbol4, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", new Object[]{simpleSymbol3, simpleSymbol7, intNum2}, 0), new SyntaxRule(syntaxPattern8, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr8, 0), new SyntaxRule(new SyntaxPattern(str3, new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", new Object[]{simpleSymbol3, IntNum.make(2), Boolean.TRUE}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", new Object[]{simpleSymbol3, intNum2}, 0), new SyntaxRule(syntaxPattern9, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr9, 0)}, 5);
        Lit3 = syntaxRules9;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("define-syntax").readResolve();
        Lit0 = simpleSymbol28;
        SyntaxRules syntaxRules10 = new SyntaxRules(new Object[]{simpleSymbol28, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018l\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\u001b#", new Object[]{simpleSymbol7}, 5), "\u0001\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u0011\u0018\u0014\t\u001a\"", new Object[]{simpleSymbol10, simpleSymbol7, simpleSymbol9}, 0), new SyntaxRule(new SyntaxPattern(str2, new Object[]{simpleSymbol7}, 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u001b", new Object[]{simpleSymbol10, simpleSymbol7}, 0), new SyntaxRule(new SyntaxPattern(str3, new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\n\u0012", new Object[]{simpleSymbol10, simpleSymbol9}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol10}, 0)}, 5);
        Lit1 = syntaxRules10;
        prim_syntax prim_syntax = new prim_syntax();
        $instance = prim_syntax;
        define$Mnsyntax = Macro.make(simpleSymbol28, syntaxRules10, prim_syntax);
        define = Macro.make(simpleSymbol27, syntaxRules9, prim_syntax);
        define$Mnprivate = Macro.make(simpleSymbol26, syntaxRules8, prim_syntax);
        define$Mnconstant = Macro.make(simpleSymbol24, syntaxRules6, prim_syntax);
        syntax$Mnerror = new ModuleMethod(prim_syntax, 1, simpleSymbol23, -4095);
        syntax$Mn$Grexpression = Macro.make(simpleSymbol20, syntaxRules4, prim_syntax);
        syntax$Mnbody$Mn$Grexpression = Macro.make(simpleSymbol18, syntaxRules2, prim_syntax);
        ModuleMethod moduleMethod = new ModuleMethod(prim_syntax, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:69");
        f338if = Macro.make(simpleSymbol16, moduleMethod, prim_syntax);
        ModuleMethod moduleMethod2 = new ModuleMethod(prim_syntax, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:89");
        try$Mncatch = Macro.make(simpleSymbol17, moduleMethod2, prim_syntax);
        ModuleMethod moduleMethod3 = new ModuleMethod(prim_syntax, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:98");
        letrec = Macro.make(simpleSymbol13, moduleMethod3, prim_syntax);
        prim_syntax.run();
    }

    public prim_syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 1) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return syntaxError(obj, objArr2);
            }
            objArr2[length] = objArr[length + 1];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    public static Expression syntaxError(Object obj, Object... objArr) {
        return syntax_error.error(obj, objArr);
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
        } else if (i != 4) {
            return super.match1(moduleMethod, obj, callContext);
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    static Object lambda1(Object obj) {
        Object[] objArr;
        Object[] objArr2;
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        if (Lit14.match(obj, allocVars, 0)) {
            return new IfExp(SyntaxForms.rewrite(Lit15.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit16.execute(allocVars, TemplateScope.make())), (Expression) null);
        } else if (Lit17.match(obj, allocVars, 0)) {
            return new IfExp(SyntaxForms.rewrite(Lit18.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit19.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit20.execute(allocVars, TemplateScope.make())));
        } else if (Lit21.match(obj, allocVars, 0)) {
            Object execute = Lit22.execute(allocVars, TemplateScope.make());
            if (!("too many expressions for 'if'" instanceof Object[])) {
                objArr2 = new Object[]{"too many expressions for 'if'"};
            }
            return syntaxError(execute, objArr2);
        } else if (!Lit23.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            Object execute2 = Lit24.execute(allocVars, TemplateScope.make());
            if (!("too few expressions for 'if'" instanceof Object[])) {
                objArr = new Object[]{"too few expressions for 'if'"};
            }
            return syntaxError(execute2, objArr);
        }
    }

    static Object lambda2(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(5, (Object[]) null);
        if (!Lit26.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        return try_catch.rewrite(Lit27.execute(allocVars, TemplateScope.make()), Lit28.execute(allocVars, TemplateScope.make()));
    }

    static Object lambda3(Object obj) {
        frame frame2 = new frame();
        LList lList = LList.Empty;
        frame2.out$Mninits = LList.Empty;
        frame2.out$Mnbindings = lList;
        frame2.$unnamed$0 = SyntaxPattern.allocVars(3, (Object[]) null);
        if (!Lit30.match(obj, frame2.$unnamed$0, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        frame2.lambda4processBinding(Lit31.execute(frame2.$unnamed$0, TemplateScope.make()));
        frame2.out$Mnbindings = LList.reverseInPlace(frame2.out$Mnbindings);
        frame2.out$Mninits = LList.reverseInPlace(frame2.out$Mninits);
        TemplateScope make = TemplateScope.make();
        return Quote.append$V(new Object[]{Lit32.execute(frame2.$unnamed$0, make), Quote.consX$V(new Object[]{frame2.out$Mnbindings, Quote.append$V(new Object[]{frame2.out$Mninits, Lit33.execute(frame2.$unnamed$0, make)})})});
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return lambda1(obj);
        }
        if (i == 3) {
            return lambda2(obj);
        }
        if (i != 4) {
            return super.apply1(moduleMethod, obj);
        }
        return lambda3(obj);
    }

    /* compiled from: prim_syntax.scm */
    public class frame extends ModuleBody {
        Object[] $unnamed$0;
        Object out$Mnbindings;
        Object out$Mninits;

        public Object lambda4processBinding(Object obj) {
            Object[] objArr;
            Object[] objArr2;
            Object[] allocVars = SyntaxPattern.allocVars(8, this.$unnamed$0);
            if (prim_syntax.Lit34.match(obj, allocVars, 0)) {
                return Values.empty;
            }
            if (prim_syntax.Lit35.match(obj, allocVars, 0)) {
                this.out$Mnbindings = new Pair(prim_syntax.Lit36.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mninits = new Pair(prim_syntax.Lit37.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                return lambda4processBinding(prim_syntax.Lit38.execute(allocVars, TemplateScope.make()));
            } else if (prim_syntax.Lit39.match(obj, allocVars, 0)) {
                this.out$Mnbindings = new Pair(prim_syntax.Lit40.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mninits = new Pair(prim_syntax.Lit41.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                return lambda4processBinding(prim_syntax.Lit42.execute(allocVars, TemplateScope.make()));
            } else if (prim_syntax.Lit43.match(obj, allocVars, 0)) {
                if (!("missing initializion in letrec" instanceof Object[])) {
                    objArr2 = new Object[]{"missing initializion in letrec"};
                }
                return prim_syntax.syntaxError(obj, objArr2);
            } else if (!prim_syntax.Lit44.match(obj, allocVars, 0)) {
                return syntax_case.error("syntax-case", obj);
            } else {
                if (!("invalid bindings syntax in letrec" instanceof Object[])) {
                    objArr = new Object[]{"invalid bindings syntax in letrec"};
                }
                return prim_syntax.syntaxError(obj, objArr);
            }
        }
    }
}
