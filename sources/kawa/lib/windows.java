package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.GuiConsole;
import kawa.standard.Scheme;

/* compiled from: windows.scm */
public class windows extends ModuleBody {
    public static final windows $instance;
    static final SimpleSymbol Lit0;
    public static final ModuleMethod scheme$Mnwindow;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("scheme-window").readResolve();
        Lit0 = simpleSymbol;
        windows windows = new windows();
        $instance = windows;
        scheme$Mnwindow = new ModuleMethod(windows, 1, simpleSymbol, 4096);
        windows.run();
    }

    public windows() {
        ModuleInfo.register(this);
    }

    public static void schemeWindow() {
        schemeWindow(Boolean.FALSE);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public Object apply0(ModuleMethod moduleMethod) {
        if (moduleMethod.selector != 1) {
            return super.apply0(moduleMethod);
        }
        schemeWindow();
        return Values.empty;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector != 1) {
            return super.apply1(moduleMethod, obj);
        }
        schemeWindow(obj);
        return Values.empty;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match0(moduleMethod, callContext);
        }
        callContext.proc = moduleMethod;
        callContext.pc = 0;
        return 0;
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

    public static void schemeWindow(Object obj) {
        Environment environment;
        Scheme instance = Scheme.getInstance();
        if (obj != Boolean.FALSE) {
            environment = misc.interactionEnvironment();
        } else {
            environment = instance.getNewEnvironment();
        }
        try {
            new GuiConsole(instance, environment, obj != Boolean.FALSE);
        } catch (ClassCastException e) {
            throw new WrongType(e, "kawa.GuiConsole.<init>(gnu.expr.Language,gnu.mapping.Environment,boolean)", 3, obj);
        }
    }
}
