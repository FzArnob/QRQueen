package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;

/* compiled from: characters.scm */
public class characters extends ModuleBody {
    public static final characters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod char$Eq$Qu;
    public static final ModuleMethod char$Gr$Eq$Qu;
    public static final ModuleMethod char$Gr$Qu;
    public static final ModuleMethod char$Ls$Eq$Qu;
    public static final ModuleMethod char$Ls$Qu;
    public static final ModuleMethod char$Mn$Grinteger;
    public static final ModuleMethod char$Qu;
    public static final ModuleMethod integer$Mn$Grchar;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("char>=?").readResolve();
        Lit7 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("char<=?").readResolve();
        Lit6 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("char>?").readResolve();
        Lit5 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("char<?").readResolve();
        Lit4 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("char=?").readResolve();
        Lit3 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("integer->char").readResolve();
        Lit2 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("char->integer").readResolve();
        Lit1 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("char?").readResolve();
        Lit0 = simpleSymbol8;
        characters characters = new characters();
        $instance = characters;
        char$Qu = new ModuleMethod(characters, 1, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mn$Grinteger = new ModuleMethod(characters, 2, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        integer$Mn$Grchar = new ModuleMethod(characters, 3, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Eq$Qu = new ModuleMethod(characters, 4, simpleSymbol5, 8194);
        char$Ls$Qu = new ModuleMethod(characters, 5, simpleSymbol4, 8194);
        char$Gr$Qu = new ModuleMethod(characters, 6, simpleSymbol3, 8194);
        char$Ls$Eq$Qu = new ModuleMethod(characters, 7, simpleSymbol2, 8194);
        char$Gr$Eq$Qu = new ModuleMethod(characters, 8, simpleSymbol, 8194);
        characters.run();
    }

    public characters() {
        ModuleInfo.register(this);
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
        } else if (i != 2) {
            if (i != 3) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (!(obj instanceof Char)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isChar(Object obj) {
        return obj instanceof Char;
    }

    public static int char$To$Integer(Char charR) {
        return charR.intValue();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return isChar(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i == 2) {
            try {
                return Integer.valueOf(char$To$Integer((Char) obj));
            } catch (ClassCastException e) {
                throw new WrongType(e, "char->integer", 1, obj);
            }
        } else if (i != 3) {
            return super.apply1(moduleMethod, obj);
        } else {
            try {
                return integer$To$Char(((Number) obj).intValue());
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "integer->char", 1, obj);
            }
        }
    }

    public static Char integer$To$Char(int i) {
        return Char.make(i);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 4:
                if (!(obj instanceof Char)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Char)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 5:
                if (!(obj instanceof Char)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Char)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 6:
                if (!(obj instanceof Char)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Char)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 7:
                if (!(obj instanceof Char)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Char)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 8:
                if (!(obj instanceof Char)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Char)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static boolean isChar$Eq(Char charR, Char charR2) {
        return charR.intValue() == charR2.intValue();
    }

    public static boolean isChar$Ls(Char charR, Char charR2) {
        return charR.intValue() < charR2.intValue();
    }

    public static boolean isChar$Gr(Char charR, Char charR2) {
        return charR.intValue() > charR2.intValue();
    }

    public static boolean isChar$Ls$Eq(Char charR, Char charR2) {
        return charR.intValue() <= charR2.intValue();
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 4:
                try {
                    try {
                        return isChar$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "char=?", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "char=?", 1, obj);
                }
            case 5:
                try {
                    try {
                        return isChar$Ls((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "char<?", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "char<?", 1, obj);
                }
            case 6:
                try {
                    try {
                        return isChar$Gr((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "char>?", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "char>?", 1, obj);
                }
            case 7:
                try {
                    try {
                        return isChar$Ls$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "char<=?", 2, obj2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "char<=?", 1, obj);
                }
            case 8:
                try {
                    try {
                        return isChar$Gr$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "char>=?", 2, obj2);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "char>=?", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static boolean isChar$Gr$Eq(Char charR, Char charR2) {
        return charR.intValue() >= charR2.intValue();
    }
}
