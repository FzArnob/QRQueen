package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

/* compiled from: enums.scm */
public class enums extends ModuleBody {
    public static final Macro $Prvt$$Pcdefine$Mnenum;
    public static final enums $instance;
    static final PairWithPosition Lit0;
    static final PairWithPosition Lit1;
    static final PairWithPosition Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxPattern Lit12 = new SyntaxPattern("\f\u0007\f\u0002\f\u000f,\r\u0017\u0010\b\b\f\u001f\f'\r/(\b\b", new Object[]{"findkeywords"}, 6);
    static final SyntaxTemplate Lit13 = new SyntaxTemplate("\u0001\u0001\u0003\u0001\u0001\u0003", "\u001b", new Object[0], 0);
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15 = new SyntaxPattern("\f\u0007\f\u0002\f\u000f,\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{"findkeywords"}, 4);
    static final SyntaxTemplate Lit16;
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
    static final SyntaxPattern Lit18 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
    static final SyntaxPattern Lit19 = new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
    static final PairWithPosition Lit2;
    static final SyntaxTemplate Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017,\r\u001f\u0018\b\b\r' \b\b", new Object[0], 5);
    static final SyntaxTemplate Lit23 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u000b", new Object[0], 0);
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("[]").readResolve());
    static final SyntaxTemplate Lit25 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\b\u001d\u001b", new Object[0], 1);
    static final SyntaxTemplate Lit26 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0013", new Object[0], 0);
    static final SyntaxTemplate Lit27 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\b%#", new Object[0], 1);
    static final SyntaxTemplate Lit28 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("define-simple-class").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 262154)}, 0);
    static final SyntaxTemplate Lit29;
    static final PairWithPosition Lit3;
    static final SyntaxTemplate Lit30;
    static final SyntaxTemplate Lit31;
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33;
    static final SyntaxTemplate Lit34;
    static final SyntaxTemplate Lit35;
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0010", new Object[0], 0);
    static final SyntaxTemplate Lit39 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0010", new Object[0], 0);
    static final PairWithPosition Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final Keyword Lit46;
    static final SimpleSymbol Lit47;
    static final Keyword Lit48;
    static final SimpleSymbol Lit49;
    static final PairWithPosition Lit5 = PairWithPosition.make(Keyword.make("init"), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 73741);
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final PairWithPosition Lit6;
    static final PairWithPosition Lit7 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("values").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126981);
    static final PairWithPosition Lit8;
    static final PairWithPosition Lit9;
    public static final Macro define$Mnenum;

    public enums() {
        ModuleInfo.register(this);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        return i != 1 ? i != 2 ? super.apply1(moduleMethod, obj) : lambda2(obj) : lambda1(obj);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 1 && i != 2) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("final").readResolve();
        Lit53 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("enum").readResolve();
        Lit52 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("num").readResolve();
        Lit51 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("str").readResolve();
        Lit50 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("*init*").readResolve();
        Lit49 = simpleSymbol5;
        Keyword make = Keyword.make("access");
        Lit48 = make;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("String").readResolve();
        Lit47 = simpleSymbol6;
        Keyword make2 = Keyword.make("allocation");
        Lit46 = make2;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("static").readResolve();
        Lit45 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("java.lang.Enum").readResolve();
        Lit44 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit43 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit42 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("s").readResolve();
        Lit41 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("valueOf").readResolve();
        Lit40 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = simpleSymbol5;
        SimpleSymbol simpleSymbol14 = simpleSymbol3;
        SimpleSymbol simpleSymbol15 = simpleSymbol4;
        Keyword keyword = make;
        Lit37 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290882)}, 0);
        SimpleSymbol simpleSymbol16 = simpleSymbol2;
        SimpleSymbol simpleSymbol17 = simpleSymbol;
        Lit36 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("$lookup$").readResolve(), Pair.make(simpleSymbol8, Pair.make(Pair.make((SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve(), Pair.make(simpleSymbol12, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290823)}, 0);
        Lit35 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make(simpleSymbol7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739)}, 0);
        Lit34 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(make2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286726)}, 0);
        Lit33 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol10, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282649)}, 0);
        Lit32 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol12, PairWithPosition.make(simpleSymbol11, PairWithPosition.make(simpleSymbol10, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282642), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282640), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282639), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282630)}, 0);
        SimpleSymbol simpleSymbol18 = simpleSymbol16;
        Lit31 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make(PairWithPosition.make(simpleSymbol18, PairWithPosition.make(simpleSymbol17, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278)}, 0);
        Keyword keyword2 = keyword;
        Lit30 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(keyword2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266269)}, 0);
        Lit29 = new SyntaxTemplate("\u0001\u0001\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266252)}, 0);
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("%define-enum").readResolve();
        Lit21 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("define-enum").readResolve();
        Lit11 = simpleSymbol20;
        Lit20 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\t\u000b\t\u0010\b\u0015\u0013", new Object[]{simpleSymbol20, "findkeywords"}, 1);
        Lit16 = new SyntaxTemplate("\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\t\u000b\u0019\b\u0015\u0013\b\u001d\u001b", new Object[]{simpleSymbol19}, 1);
        Lit14 = new SyntaxTemplate("\u0001\u0001\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\t\u000b9\t\u001b\t#\b\u0015\u0013\b-+", new Object[]{simpleSymbol20, "findkeywords"}, 1);
        Lit10 = PairWithPosition.make(simpleSymbol9, PairWithPosition.make(simpleSymbol7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013);
        Lit9 = PairWithPosition.make(make2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127000);
        Lit8 = PairWithPosition.make(simpleSymbol10, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126990);
        SimpleSymbol simpleSymbol21 = simpleSymbol15;
        SimpleSymbol simpleSymbol22 = simpleSymbol14;
        SimpleSymbol simpleSymbol23 = simpleSymbol13;
        SimpleSymbol simpleSymbol24 = simpleSymbol19;
        Lit6 = PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol21, PairWithPosition.make(simpleSymbol10, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90133), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90130), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125), PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(simpleSymbol10, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("int").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90149), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90146), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90117), PairWithPosition.make(keyword2, PairWithPosition.make(PairWithPosition.make(simpleSymbol9, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("private").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("invoke-special").readResolve(), PairWithPosition.make(simpleSymbol8, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("this").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), PairWithPosition.make(PairWithPosition.make(simpleSymbol9, PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), PairWithPosition.make(simpleSymbol21, PairWithPosition.make(simpleSymbol22, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98355), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98347), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98325), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94221), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94213), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90116);
        Lit4 = PairWithPosition.make(simpleSymbol9, PairWithPosition.make(PairWithPosition.make(simpleSymbol16, PairWithPosition.make(simpleSymbol17, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674);
        Lit3 = PairWithPosition.make(keyword2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69665);
        Lit2 = PairWithPosition.make(simpleSymbol9, PairWithPosition.make(simpleSymbol7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658);
        Lit1 = PairWithPosition.make(make2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69645);
        Lit0 = PairWithPosition.make(simpleSymbol10, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 65549);
        enums enums = new enums();
        $instance = enums;
        define$Mnenum = Macro.make(simpleSymbol20, new ModuleMethod(enums, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), enums);
        $Prvt$$Pcdefine$Mnenum = Macro.make(simpleSymbol24, new ModuleMethod(enums, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), enums);
        enums.run();
    }

    static SimpleSymbol symbolAppend$V(Object[] objArr) {
        Object makeList = LList.makeList(objArr, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = LList.Empty;
        while (makeList != LList.Empty) {
            try {
                Pair pair = (Pair) makeList;
                Object cdr = pair.getCdr();
                Object car = pair.getCar();
                try {
                    obj = Pair.make(misc.symbol$To$String((Symbol) car), obj);
                    makeList = cdr;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, makeList);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object makeFieldDesc(Symbol symbol, Symbol symbol2, int i) {
        return Quote.consX$V(new Object[]{symbol2, Quote.append$V(new Object[]{Lit0, Quote.consX$V(new Object[]{symbol, Quote.append$V(new Object[]{Lit1, Pair.make(Lit2, Quote.append$V(new Object[]{Lit3, Pair.make(Lit4, Quote.append$V(new Object[]{Lit5, Pair.make(Quote.consX$V(new Object[]{symbol, Quote.consX$V(new Object[]{misc.symbol$To$String(symbol2), Quote.consX$V(new Object[]{Integer.valueOf(i), LList.Empty})})}), LList.Empty)}))}))})})})});
    }

    static PairWithPosition makeInit() {
        return Lit6;
    }

    static Pair makeValues(Symbol symbol, LList lList) {
        return Pair.make(Lit7, Quote.append$V(new Object[]{Lit8, Quote.consX$V(new Object[]{symbol, Quote.append$V(new Object[]{Lit9, Pair.make(Lit10, Pair.make(Quote.consX$V(new Object[]{symbol, Quote.append$V(new Object[]{lList, LList.Empty})}), LList.Empty))})})}));
    }

    static Object lambda1(Object obj) {
        Object[] objArr;
        Object[] objArr2;
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        if (Lit12.match(obj, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit13.execute(allocVars, TemplateScope.make()))) {
                return Lit14.execute(allocVars, TemplateScope.make());
            }
        }
        if (Lit15.match(obj, allocVars, 0)) {
            return Lit16.execute(allocVars, TemplateScope.make());
        } else if (Lit17.match(obj, allocVars, 0)) {
            if (!("no enum type name given" instanceof Object[])) {
                objArr2 = new Object[]{"no enum type name given"};
            }
            return prim_syntax.syntaxError(obj, objArr2);
        } else if (Lit18.match(obj, allocVars, 0)) {
            if (!("no enum constants given" instanceof Object[])) {
                objArr = new Object[]{"no enum constants given"};
            }
            return prim_syntax.syntaxError(obj, objArr);
        } else if (!Lit19.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            return Lit20.execute(allocVars, TemplateScope.make());
        }
    }

    static LList mapNames(Symbol symbol, LList lList, int i) {
        if (lists.isNull(lList)) {
            return LList.Empty;
        }
        Object apply1 = lists.car.apply1(lList);
        try {
            Object makeFieldDesc = makeFieldDesc(symbol, (Symbol) apply1, i);
            Object apply12 = lists.cdr.apply1(lList);
            try {
                return lists.cons(makeFieldDesc, mapNames(symbol, (LList) apply12, i + 1));
            } catch (ClassCastException e) {
                throw new WrongType(e, "map-names", 1, apply12);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "make-field-desc", 1, apply1);
        }
    }

    static Object lambda2(Object obj) {
        Object obj2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(5, (Object[]) null);
        if (!Lit22.match(obj2, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj2);
        }
        Object execute = Lit23.execute(allocVars, TemplateScope.make());
        try {
            Symbol symbol = (Symbol) execute;
            SimpleSymbol symbolAppend$V = symbolAppend$V(new Object[]{symbol, Lit24});
            Object execute2 = Lit25.execute(allocVars, TemplateScope.make());
            try {
                LList lList = (LList) execute2;
                lists.length(lList);
                LList mapNames = mapNames(symbol, lList, 0);
                PairWithPosition makeInit = makeInit();
                Pair makeValues = makeValues(symbolAppend$V, lList);
                Object execute3 = Lit26.execute(allocVars, TemplateScope.make());
                try {
                    LList lList2 = (LList) execute3;
                    Object execute4 = Lit27.execute(allocVars, TemplateScope.make());
                    try {
                        LList lList3 = (LList) execute4;
                        TemplateScope make = TemplateScope.make();
                        Object[] objArr = new Object[2];
                        objArr[0] = Lit28.execute(allocVars, make);
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = std_syntax.datum$To$SyntaxObject(obj2, symbol);
                        Object execute5 = Lit29.execute(allocVars, make);
                        Object[] objArr3 = new Object[2];
                        objArr3[0] = Lit30.execute(allocVars, make);
                        Object execute6 = Lit31.execute(allocVars, make);
                        Object[] objArr4 = objArr;
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = std_syntax.datum$To$SyntaxObject(obj2, lList2);
                        Object[] objArr6 = objArr2;
                        Object obj3 = execute5;
                        Object[] objArr7 = objArr3;
                        Object[] objArr8 = objArr5;
                        objArr8[1] = Quote.consX$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, makeInit), Quote.consX$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, makeValues), Pair.make(Pair.make(Lit32.execute(allocVars, make), Quote.append$V(new Object[]{Lit33.execute(allocVars, make), Quote.consX$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, symbol), Quote.append$V(new Object[]{Lit34.execute(allocVars, make), Pair.make(Lit35.execute(allocVars, make), Pair.make(Pair.make(Lit36.execute(allocVars, make), Quote.consX$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, symbol), Lit37.execute(allocVars, make)})), Lit38.execute(allocVars, make)))})})})), Quote.append$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, mapNames), Quote.append$V(new Object[]{std_syntax.datum$To$SyntaxObject(obj2, lList3), Lit39.execute(allocVars, make)})}))})});
                        objArr7[1] = Pair.make(execute6, Quote.append$V(objArr8));
                        objArr6[1] = Pair.make(obj3, Quote.append$V(objArr7));
                        objArr4[1] = Quote.consX$V(objArr6);
                        return Quote.append$V(objArr4);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "other-defs", -2, execute4);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "opts", -2, execute3);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "e-names", -2, execute2);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "t-name", -2, execute);
        }
    }
}
