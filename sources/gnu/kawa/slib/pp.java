package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lib.ports;

/* compiled from: pp.scm */
public class pp extends ModuleBody {
    public static final pp $instance;
    static final IntNum Lit0 = IntNum.make(79);
    static final SimpleSymbol Lit1;
    public static final ModuleMethod pretty$Mnprint;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("pretty-print").readResolve();
        Lit1 = simpleSymbol;
        pp ppVar = new pp();
        $instance = ppVar;
        pretty$Mnprint = new ModuleMethod(ppVar, 2, simpleSymbol, 8193);
        ppVar.run();
    }

    public pp() {
        ModuleInfo.register(this);
    }

    public static Object prettyPrint(Object obj) {
        return prettyPrint(obj, ports.current$Mnoutput$Mnport.apply0());
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    /* compiled from: pp.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object port;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pp.scm:9");
            this.lambda$Fn1 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda1(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Boolean lambda1(Object obj) {
            ports.display(obj, this.port);
            return Boolean.TRUE;
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
    }

    public static Object prettyPrint(Object obj, Object obj2) {
        frame frame2 = new frame();
        frame2.port = obj2;
        return genwrite.genericWrite(obj, Boolean.FALSE, Lit0, frame2.lambda$Fn1);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 2 ? prettyPrint(obj) : super.apply1(moduleMethod, obj);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 2 ? prettyPrint(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
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

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 2) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }
}
