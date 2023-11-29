package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Quantity;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.sleep;

/* compiled from: thread.scm */
public class thread extends ModuleBody {
    public static final ModuleMethod $Prvt$$Pcmake$Mnfuture;
    public static final thread $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SyntaxRules Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    public static final Macro future;
    public static final ModuleMethod runnable;
    public static final ModuleMethod sleep;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("runnable").readResolve();
        Lit4 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("%make-future").readResolve();
        Lit3 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("future").readResolve();
        Lit1 = simpleSymbol3;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol3}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\t\u0010\b\u0003", new Object[]{simpleSymbol2, (SimpleSymbol) new SimpleSymbol("lambda").readResolve()}, 0)}, 1);
        Lit2 = syntaxRules;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("sleep").readResolve();
        Lit0 = simpleSymbol4;
        thread thread = new thread();
        $instance = thread;
        sleep = new ModuleMethod(thread, 1, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        future = Macro.make(simpleSymbol3, syntaxRules, thread);
        $Prvt$$Pcmake$Mnfuture = new ModuleMethod(thread, 2, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        runnable = new ModuleMethod(thread, 3, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        thread.run();
    }

    public thread() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return super.match1(moduleMethod, obj, callContext);
                }
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (!(obj instanceof Procedure)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (!(obj instanceof Quantity)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static void sleep(Quantity quantity) {
        sleep.sleep(quantity);
    }

    public static Future $PcMakeFuture(Procedure procedure) {
        Future future2 = new Future(procedure);
        future2.start();
        return future2;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            try {
                sleep((Quantity) obj);
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "sleep", 1, obj);
            }
        } else if (i == 2) {
            try {
                return $PcMakeFuture((Procedure) obj);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "%make-future", 1, obj);
            }
        } else if (i != 3) {
            return super.apply1(moduleMethod, obj);
        } else {
            try {
                return runnable((Procedure) obj);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "runnable", 1, obj);
            }
        }
    }

    public static RunnableClosure runnable(Procedure procedure) {
        return new RunnableClosure(procedure);
    }
}
