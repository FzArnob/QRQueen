package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;
import org.jose4j.jwk.RsaJsonWebKey;

/* compiled from: parameters.scm */
public class parameters extends ModuleBody {
    public static final ModuleMethod $Prvt$as$Mnlocation$Pc;
    public static final Macro $Prvt$parameterize$Pc;
    public static final parameters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnparameter;
    public static final Macro parameterize;

    public parameters() {
        ModuleInfo.register(this);
    }

    public static LocationProc makeParameter(Object obj) {
        return makeParameter(obj, (Object) null);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("save").readResolve();
        Lit12 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit11 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.Location").readResolve();
        Lit10 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit9 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("v").readResolve();
        Lit8 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME).readResolve();
        Lit7 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit6 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("parameterize").readResolve();
        Lit4 = simpleSymbol8;
        Object[] objArr = {simpleSymbol8};
        SimpleSymbol simpleSymbol9 = simpleSymbol8;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\u001b", new Object[0], 4);
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("parameterize%").readResolve();
        Lit2 = simpleSymbol10;
        SyntaxRules syntaxRules = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\u0003", new Object[0], 1), "\u0000", "\u0011\u0018\u0004\u0002", new Object[]{simpleSymbol7}, 0), new SyntaxRule(syntaxPattern, "\u0001\u0001\u0000\u0000", "\u0011\u0018\u00041!\t\u0003\b\u000b\u0012\t\u0010\u001a", new Object[]{simpleSymbol10}, 0)}, 4);
        Lit5 = syntaxRules;
        SyntaxRules syntaxRules2 = syntaxRules;
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\f\b\f\u0007\u000b", new Object[0], 2);
        Object[] objArr2 = {(SimpleSymbol) new SimpleSymbol("try-finally").readResolve(), simpleSymbol7};
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f#", new Object[0], 5);
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("as-location%").readResolve();
        Lit1 = simpleSymbol11;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol10}, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0001\u0000", "\u0011\u0018\u0004!\u0011\u0018\f\n\b\u0011\u0018\f\u0003", objArr2, 0), new SyntaxRule(syntaxPattern3, "\u0001\u0001\u0000\u0001\u0000", "\u0011\u0018\u0004Áy\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\b\u0003)\u0011\u0018,\b\u000b\u00184\b\u0011\u0018<\t\u0012!\u0011\u0018D\u001b\"", new Object[]{(SimpleSymbol) new SimpleSymbol("let*").readResolve(), simpleSymbol6, (SimpleSymbol) new SimpleSymbol("::").readResolve(), (SimpleSymbol) new SimpleSymbol("<gnu.mapping.Location>").readResolve(), simpleSymbol11, simpleSymbol5, PairWithPosition.make(PairWithPosition.make(simpleSymbol, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol4, Pair.make(simpleSymbol3, Pair.make(Pair.make(simpleSymbol2, Pair.make((SimpleSymbol) new SimpleSymbol("setWithSave").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122893), PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122928), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122926), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886), simpleSymbol10, PairWithPosition.make(PairWithPosition.make(simpleSymbol4, Pair.make(simpleSymbol3, Pair.make(Pair.make(simpleSymbol2, Pair.make((SimpleSymbol) new SimpleSymbol("setRestore").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131083), PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131117), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131115), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131082)}, 0)}, 5);
        Lit3 = syntaxRules3;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("make-parameter").readResolve();
        Lit0 = simpleSymbol12;
        parameters parameters = new parameters();
        $instance = parameters;
        make$Mnparameter = new ModuleMethod(parameters, 1, simpleSymbol12, 8193);
        $Prvt$as$Mnlocation$Pc = new ModuleMethod(parameters, 3, simpleSymbol11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$parameterize$Pc = Macro.make(simpleSymbol10, syntaxRules3, parameters);
        parameterize = Macro.make(simpleSymbol9, syntaxRules2, parameters);
        parameters.run();
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 1 ? makeParameter(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 3) {
            return super.match1(moduleMethod, obj, callContext);
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    public static LocationProc makeParameter(Object obj, Object obj2) {
        if (obj2 != null) {
            obj = Scheme.applyToArgs.apply2(obj2, obj);
        }
        ThreadLocation threadLocation = new ThreadLocation();
        threadLocation.setGlobal(obj);
        try {
            return new LocationProc(threadLocation, (Procedure) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.LocationProc.<init>(gnu.mapping.Location,gnu.mapping.Procedure)", 2, obj2);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return makeParameter(obj);
        }
        if (i != 3) {
            return super.apply1(moduleMethod, obj);
        }
        return asLocation$Pc(obj);
    }

    public static Location asLocation$Pc(Object obj) {
        Location location;
        if (obj instanceof LocationProc) {
            try {
                location = ((LocationProc) obj).getLocation();
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.mapping.LocationProc.getLocation()", 1, obj);
            }
        } else {
            location = (Location) obj;
        }
        if (location instanceof ThreadLocation) {
            try {
                location = ((ThreadLocation) location).getLocation();
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "gnu.mapping.ThreadLocation.getLocation()", 1, (Object) location);
            }
        }
        return location;
    }
}
