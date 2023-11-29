package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

/* compiled from: misc_syntax.scm */
public class misc_syntax extends ModuleBody {
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final misc_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1 = new SyntaxPattern("\f\u0007,\f\u000f\f\u0017\b\b", new Object[0], 3);
    static final SimpleSymbol Lit10;
    static final SyntaxRules Lit11;
    static final SimpleSymbol Lit12;
    static final SyntaxPattern Lit13 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
    static final SyntaxTemplate Lit14 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit15 = new SyntaxTemplate("\u0001\u0001", "\u0003", new Object[0], 0);
    static final SyntaxPattern Lit16 = new SyntaxPattern("\r\u0017\u0010\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxPattern Lit19 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{(SimpleSymbol) new SimpleSymbol("define-constant").readResolve()}, 0);
    static final SyntaxTemplate Lit20 = new SyntaxTemplate("\u0001\u0001", "\b\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit21 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit22 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxTemplate Lit3 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SyntaxTemplate Lit4 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("::").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<int>").readResolve(), PairWithPosition.make(IntNum.make(123), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53270), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53264), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 53260)}, 0);
    static final SyntaxPattern Lit5 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxPattern Lit9 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
    public static final Macro include;
    public static final Macro include$Mnrelative;
    public static final Macro module$Mnuri;
    public static final Macro provide;
    public static final Macro resource$Mnurl;
    public static final Macro test$Mnbegin;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("%test-begin").readResolve();
        Lit31 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit30 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("require").readResolve();
        Lit29 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit28 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("cond-expand").readResolve();
        Lit27 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("srfi-64").readResolve();
        Lit26 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit25 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit24 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit23 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("include-relative").readResolve();
        Lit18 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = simpleSymbol10;
        SimpleSymbol simpleSymbol12 = simpleSymbol;
        Lit17 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u0004\b\u0015\u0013", new Object[]{simpleSymbol7}, 1);
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("include").readResolve();
        Lit12 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("resource-url").readResolve();
        Lit10 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = simpleSymbol13;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol16 = simpleSymbol14;
        SimpleSymbol simpleSymbol17 = simpleSymbol5;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("module-uri").readResolve();
        Lit8 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = simpleSymbol4;
        SimpleSymbol simpleSymbol20 = simpleSymbol18;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001", "\u0011\u0018\u0004\b\b\u0011\u0018\f\b\u0011\u0018\fa\b\u0011\u0018\f)\u0011\u0018\u0014\b\u0003\u0018\u001c\u0018$\u0018,", new Object[]{PairWithPosition.make(simpleSymbol9, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.text.URLPath").readResolve(), Pair.make(Pair.make(simpleSymbol8, Pair.make((SimpleSymbol) new SimpleSymbol("valueOf").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 155655), simpleSymbol9, PairWithPosition.make(simpleSymbol9, Pair.make(PairWithPosition.make(simpleSymbol18, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 159755), Pair.make(Pair.make(simpleSymbol8, Pair.make((SimpleSymbol) new SimpleSymbol("resolve").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 159755), Pair.make(Pair.make(simpleSymbol8, Pair.make((SimpleSymbol) new SimpleSymbol("toURL").readResolve(), LList.Empty)), LList.Empty), Pair.make(Pair.make(simpleSymbol8, Pair.make((SimpleSymbol) new SimpleSymbol("openConnection").readResolve(), LList.Empty)), LList.Empty), Pair.make(Pair.make(simpleSymbol8, Pair.make((SimpleSymbol) new SimpleSymbol("getURL").readResolve(), LList.Empty)), LList.Empty)}, 0)}, 1);
        Lit11 = syntaxRules;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("test-begin").readResolve();
        Lit6 = simpleSymbol21;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol22 = simpleSymbol19;
        SimpleSymbol simpleSymbol23 = simpleSymbol21;
        SimpleSymbol simpleSymbol24 = simpleSymbol17;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol21}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0003\u0018\u001c", new Object[]{simpleSymbol7, PairWithPosition.make(simpleSymbol24, PairWithPosition.make(PairWithPosition.make(simpleSymbol6, PairWithPosition.make(Values.empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86046), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86037), PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86070), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86070), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86069), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86060), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86060), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86054), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86054), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86037), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 86024), simpleSymbol12, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 90144)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0003\b\u000b", new Object[]{simpleSymbol7, PairWithPosition.make(simpleSymbol24, PairWithPosition.make(PairWithPosition.make(simpleSymbol6, PairWithPosition.make(Values.empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102430), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102421), PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102454), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102454), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102453), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102444), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102444), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102438), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102438), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102421), "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm", 102408), simpleSymbol12}, 0)}, 2);
        Lit7 = syntaxRules3;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("provide").readResolve();
        Lit0 = simpleSymbol25;
        misc_syntax misc_syntax = new misc_syntax();
        $instance = misc_syntax;
        provide = Macro.make(simpleSymbol25, new ModuleMethod(misc_syntax, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), misc_syntax);
        test$Mnbegin = Macro.make(simpleSymbol23, syntaxRules3, misc_syntax);
        ModuleMethod moduleMethod = new ModuleMethod(misc_syntax, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm:29");
        module$Mnuri = Macro.make(simpleSymbol20, moduleMethod, misc_syntax);
        resource$Mnurl = Macro.make(simpleSymbol16, syntaxRules2, misc_syntax);
        ModuleMethod moduleMethod2 = new ModuleMethod(misc_syntax, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc_syntax.scm:54");
        include = Macro.make(simpleSymbol15, moduleMethod2, misc_syntax);
        include$Mnrelative = Macro.make(simpleSymbol11, new ModuleMethod(misc_syntax, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), misc_syntax);
        misc_syntax.run();
    }

    public misc_syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static Object lambda1(Object obj) {
        Object[] objArr;
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        if (Lit1.match(obj, allocVars, 0)) {
            Object execute = Lit2.execute(allocVars, TemplateScope.make());
            Object[] objArr2 = new Object[2];
            objArr2[0] = "%provide%";
            Object syntaxObject$To$Datum = std_syntax.syntaxObject$To$Datum(Lit3.execute(allocVars, TemplateScope.make()));
            try {
                objArr2[1] = misc.symbol$To$String((Symbol) syntaxObject$To$Datum);
                return lists.cons(execute, lists.cons(std_syntax.datum$To$SyntaxObject(obj, misc.string$To$Symbol(strings.stringAppend(objArr2))), Lit4.execute(allocVars, TemplateScope.make())));
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, syntaxObject$To$Datum);
            }
        } else if (!Lit5.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            if (!("provide requires a quoted feature-name" instanceof Object[])) {
                objArr = new Object[]{"provide requires a quoted feature-name"};
            }
            return prim_syntax.syntaxError(obj, objArr);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 2) {
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

    static Object lambda2(Object obj) {
        return Lit9.match(obj, SyntaxPattern.allocVars(1, (Object[]) null), 0) ? GetModuleClass.getModuleClassURI(Compilation.getCurrent()) : syntax_case.error("syntax-case", obj);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return lambda1(obj);
        }
        if (i == 2) {
            return lambda2(obj);
        }
        if (i == 3) {
            return lambda3(obj);
        }
        if (i != 4) {
            return super.apply1(moduleMethod, obj);
        }
        return lambda5(obj);
    }

    /* compiled from: misc_syntax.scm */
    public class frame extends ModuleBody {
        Object k;
        InPort p;

        public Object lambda4f() {
            Object read = ports.read(this.p);
            if (!ports.isEofObject(read)) {
                return new Pair(std_syntax.datum$To$SyntaxObject(this.k, read), lambda4f());
            }
            ports.closeInputPort(this.p);
            return LList.Empty;
        }
    }

    static Object lambda3(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
        if (Lit13.match(obj, allocVars, 0)) {
            Object syntaxObject$To$Datum = std_syntax.syntaxObject$To$Datum(Lit14.execute(allocVars, TemplateScope.make()));
            Object execute = Lit15.execute(allocVars, TemplateScope.make());
            frame frame2 = new frame();
            frame2.k = execute;
            try {
                frame2.p = ports.openInputFile(Path.valueOf(syntaxObject$To$Datum));
                obj = frame2.lambda4f();
                Object[] allocVars2 = SyntaxPattern.allocVars(3, allocVars);
                if (Lit16.match(obj, allocVars2, 0)) {
                    return Lit17.execute(allocVars2, TemplateScope.make());
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "open-input-file", 1, syntaxObject$To$Datum);
            }
        }
        return syntax_case.error("syntax-case", obj);
    }

    static Object lambda5(Object obj) {
        Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
        if (!Lit19.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        }
        Object syntaxObject$To$Datum = std_syntax.syntaxObject$To$Datum(Lit20.execute(allocVars, TemplateScope.make()));
        try {
            PairWithPosition pairWithPosition = (PairWithPosition) syntaxObject$To$Datum;
            Path valueOf = Path.valueOf(pairWithPosition.getFileName());
            String obj2 = pairWithPosition.getCar().toString();
            return LList.list2(std_syntax.datum$To$SyntaxObject(Lit21.execute(allocVars, TemplateScope.make()), Lit12), std_syntax.datum$To$SyntaxObject(Lit22.execute(allocVars, TemplateScope.make()), valueOf.resolve(obj2).toString()));
        } catch (ClassCastException e) {
            throw new WrongType(e, "path-pair", -2, syntaxObject$To$Datum);
        }
    }
}
