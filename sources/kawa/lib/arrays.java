package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Arrays;
import gnu.lists.Array;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: arrays.scm */
public class arrays extends ModuleBody {
    public static final Class $Lsarray$Gr = Array.class;
    public static final arrays $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod array;
    public static final ModuleMethod array$Mnend;
    public static final ModuleMethod array$Mnrank;
    public static final ModuleMethod array$Mnstart;
    public static final ModuleMethod array$Qu;
    public static final ModuleMethod make$Mnarray;
    public static final ModuleMethod shape;
    public static final ModuleMethod share$Mnarray;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("share-array").readResolve();
        Lit7 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("array-end").readResolve();
        Lit6 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("array-start").readResolve();
        Lit5 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("array-rank").readResolve();
        Lit4 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("array").readResolve();
        Lit3 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("make-array").readResolve();
        Lit2 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("shape").readResolve();
        Lit1 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("array?").readResolve();
        Lit0 = simpleSymbol8;
        arrays arrays = new arrays();
        $instance = arrays;
        array$Qu = new ModuleMethod(arrays, 1, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        shape = new ModuleMethod(arrays, 2, simpleSymbol7, -4096);
        make$Mnarray = new ModuleMethod(arrays, 3, simpleSymbol6, 8193);
        array = new ModuleMethod(arrays, 5, simpleSymbol5, -4095);
        array$Mnrank = new ModuleMethod(arrays, 6, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        array$Mnstart = new ModuleMethod(arrays, 7, simpleSymbol3, 8194);
        array$Mnend = new ModuleMethod(arrays, 8, simpleSymbol2, 8194);
        share$Mnarray = new ModuleMethod(arrays, 9, simpleSymbol, 12291);
        arrays.run();
    }

    public arrays() {
        ModuleInfo.register(this);
    }

    public static Array makeArray(Array array2) {
        return makeArray(array2, (Object) null);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 3) {
            if (i != 6) {
                return super.match1(moduleMethod, obj, callContext);
            }
            if (!(obj instanceof Array)) {
                return -786431;
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (!(obj instanceof Array)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isArray(Object obj) {
        return obj instanceof Array;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 5) {
            return super.matchN(moduleMethod, objArr, callContext);
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Array shape(Object... objArr) {
        return Arrays.shape(objArr);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 3) {
            if (i != 7) {
                if (i != 8) {
                    return super.match2(moduleMethod, obj, obj2, callContext);
                }
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (!(obj instanceof Array)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (!(obj instanceof Array)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Array makeArray(Array array2, Object obj) {
        return Arrays.make(array2, obj);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return shape(objArr);
        }
        if (i != 5) {
            return super.applyN(moduleMethod, objArr);
        }
        Array array2 = objArr[0];
        try {
            Array array3 = array2;
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            while (true) {
                length--;
                if (length < 0) {
                    return array(array3, objArr2);
                }
                objArr2[length] = objArr[length + 1];
            }
        } catch (ClassCastException e) {
            throw new WrongType(e, "array", 1, (Object) array2);
        }
    }

    public static Array array(Array array2, Object... objArr) {
        return Arrays.makeSimple(array2, new FVector(objArr));
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return isArray(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i == 3) {
            try {
                return makeArray((Array) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-array", 1, obj);
            }
        } else if (i != 6) {
            return super.apply1(moduleMethod, obj);
        } else {
            try {
                return Integer.valueOf(arrayRank((Array) obj));
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "array-rank", 1, obj);
            }
        }
    }

    public static int arrayRank(Array array2) {
        return array2.rank();
    }

    public static int arrayStart(Array array2, int i) {
        return array2.getLowBound(i);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 3) {
            try {
                return makeArray((Array) obj, obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-array", 1, obj);
            }
        } else if (i == 7) {
            try {
                try {
                    return Integer.valueOf(arrayStart((Array) obj, ((Number) obj2).intValue()));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "array-start", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "array-start", 1, obj);
            }
        } else if (i != 8) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    return Integer.valueOf(arrayEnd((Array) obj, ((Number) obj2).intValue()));
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "array-end", 2, obj2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "array-end", 1, obj);
            }
        }
    }

    public static int arrayEnd(Array array2, int i) {
        return array2.getLowBound(i) + array2.getSize(i);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 9) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            try {
                try {
                    return shareArray((Array) obj, (Array) obj2, (Procedure) obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "share-array", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "share-array", 2, obj2);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "share-array", 1, obj);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 9) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof Array)) {
            return -786431;
        }
        callContext.value1 = obj;
        if (!(obj2 instanceof Array)) {
            return -786430;
        }
        callContext.value2 = obj2;
        if (!(obj3 instanceof Procedure)) {
            return -786429;
        }
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    public static Array shareArray(Array array2, Array array3, Procedure procedure) {
        return Arrays.shareArray(array2, array3, procedure);
    }
}
