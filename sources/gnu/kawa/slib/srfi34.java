package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

/* compiled from: srfi34.scm */
public class srfi34 extends ModuleBody {
    public static final Class $Prvt$$Lsraise$Mnobject$Mnexception$Gr = raise$Mnobject$Mnexception.class;
    public static final Macro $Prvt$guard$Mnaux;
    public static final srfi34 $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro guard;
    public static final ModuleMethod raise;
    public static final ModuleMethod with$Mnexception$Mnhandler;

    public srfi34() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("<raise-object-exception>").readResolve();
        Lit13 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("ex").readResolve();
        Lit12 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit11 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit10 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit9 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("temp").readResolve();
        Lit8 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("=>").readResolve();
        Lit7 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit6 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("guard-aux").readResolve();
        Lit4 = simpleSymbol9;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol9, simpleSymbol8, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007L\f\u0002\f\u000f\r\u0017\u0010\b\b\b", new Object[]{simpleSymbol8}, 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol3}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\f\u0002\f\u0017\b\b", new Object[]{simpleSymbol7}, 3), "\u0001\u0001\u0001", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f!\t\u0013\u0018\u001c\b\u0003", new Object[]{simpleSymbol5, simpleSymbol6, simpleSymbol4, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 274452)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\f\u0002\f\u0017\b\f\u001f\r' \b\b", new Object[]{simpleSymbol7}, 5), "\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f!\t\u0013\u0018\u001c\b\u0011\u0018$\t\u0003\t\u001b\b%#", new Object[]{simpleSymbol5, simpleSymbol6, simpleSymbol4, PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 294932), simpleSymbol9}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\u001c\f\u000f\b\b", new Object[0], 2), "\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\u001c\f\u000f\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f\u0011\u0018\f\b\u0011\u0018\u001c\t\u0003\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol5, simpleSymbol6, simpleSymbol4, simpleSymbol9}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007L\f\u000f\f\u0017\r\u001f\u0018\b\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000bA\u0011\u0018\f\t\u0013\b\u001d\u001b\b\u0003", new Object[]{simpleSymbol4, simpleSymbol3}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007L\f\u000f\f\u0017\r\u001f\u0018\b\b\f'\r/(\b\b", new Object[0], 6), "\u0001\u0001\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\t\u000bA\u0011\u0018\f\t\u0013\b\u001d\u001b\b\u0011\u0018\u0014\t\u0003\t#\b-+", new Object[]{simpleSymbol4, simpleSymbol3, simpleSymbol9}, 1)}, 6);
        Lit5 = syntaxRules;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("guard").readResolve();
        Lit2 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = simpleSymbol;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol10}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004!\u0011\u0018\f\u0012\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$)\b\t\u0003\u0018,\b\u0011\u00184\u0011\u0018<\n", new Object[]{(SimpleSymbol) new SimpleSymbol("try-catch").readResolve(), simpleSymbol3, simpleSymbol2, (SimpleSymbol) new SimpleSymbol("<java.lang.Throwable>").readResolve(), simpleSymbol5, PairWithPosition.make(PairWithPosition.make(simpleSymbol4, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve(), PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110614), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110611), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("field").readResolve(), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("as").readResolve(), PairWithPosition.make(simpleSymbol11, PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114732), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol(CommonProperties.VALUE).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114736), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 118792), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596), simpleSymbol9, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("primitive-throw").readResolve(), PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122914), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122897)}, 0)}, 3);
        Lit3 = syntaxRules2;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("raise").readResolve();
        Lit1 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("with-exception-handler").readResolve();
        Lit0 = simpleSymbol13;
        srfi34 srfi34 = new srfi34();
        $instance = srfi34;
        with$Mnexception$Mnhandler = new ModuleMethod(srfi34, 1, simpleSymbol13, 8194);
        raise = new ModuleMethod(srfi34, 2, simpleSymbol12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        guard = Macro.make(simpleSymbol10, syntaxRules2, srfi34);
        $Prvt$guard$Mnaux = Macro.make(simpleSymbol9, syntaxRules, srfi34);
        srfi34.run();
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 1 ? withExceptionHandler(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
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

    public static Object withExceptionHandler(Object obj, Object obj2) {
        try {
            return Scheme.applyToArgs.apply1(obj2);
        } catch (raise$Mnobject$Mnexception e) {
            return Scheme.applyToArgs.apply2(obj, e.value);
        } catch (Throwable th) {
            return Scheme.applyToArgs.apply2(obj, th);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector != 2) {
            return super.apply1(moduleMethod, obj);
        }
        raise(obj);
        return Values.empty;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 2) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static void raise(Object obj) {
        throw new raise$Mnobject$Mnexception(obj);
    }
}
