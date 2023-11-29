package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.srfi95;
import kawa.standard.append;

/* compiled from: sorting.scm */
public class sorting extends ModuleBody {
    public static final sorting $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod list$Mnsort;
    public static final ModuleMethod vector$Mnsort;
    public static final ModuleMethod vector$Mnsort$Ex;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("vector-sort!").readResolve();
        Lit2 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("vector-sort").readResolve();
        Lit1 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("list-sort").readResolve();
        Lit0 = simpleSymbol3;
        sorting sorting = new sorting();
        $instance = sorting;
        list$Mnsort = new ModuleMethod(sorting, 1, simpleSymbol3, 8194);
        vector$Mnsort = new ModuleMethod(sorting, 2, simpleSymbol2, 8194);
        vector$Mnsort$Ex = new ModuleMethod(sorting, 3, simpleSymbol, 8194);
        sorting.run();
    }

    public sorting() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i == 2) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 3) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object listSort(Object obj, Object obj2) {
        return srfi95.$PcSortList(append.append$V(new Object[]{obj2, LList.Empty}), obj, Boolean.FALSE);
    }

    public static void vectorSort(Object obj, Object obj2) {
        try {
            srfi95.$PcSortVector((Sequence) obj2, obj, Boolean.FALSE);
        } catch (ClassCastException e) {
            throw new WrongType(e, "%sort-vector", 0, obj2);
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return listSort(obj, obj2);
        }
        if (i == 2) {
            vectorSort(obj, obj2);
            return Values.empty;
        } else if (i != 3) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            vectorSort$Ex(obj, obj2);
            return Values.empty;
        }
    }

    public static void vectorSort$Ex(Object obj, Object obj2) {
        try {
            srfi95.$PcVectorSort$Ex((Sequence) obj2, obj, Boolean.FALSE);
        } catch (ClassCastException e) {
            throw new WrongType(e, "%vector-sort!", 0, obj2);
        }
    }
}
