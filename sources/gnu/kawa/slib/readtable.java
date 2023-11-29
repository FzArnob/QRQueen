package gnu.kawa.slib;

import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReadTableEntry;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMacro;
import gnu.kawa.lispexpr.ReaderMacro;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

/* compiled from: readtable.scm */
public class readtable extends ModuleBody {
    public static final readtable $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    public static final ModuleMethod current$Mnreadtable;
    public static final ModuleMethod define$Mnreader$Mnctor;
    public static final ModuleMethod get$Mndispatch$Mnmacro$Mntable;
    public static final ModuleMethod make$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod readtable$Qu;
    public static final ModuleMethod set$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod set$Mnmacro$Mncharacter;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("define-reader-ctor").readResolve();
        Lit6 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("get-dispatch-macro-table").readResolve();
        Lit5 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("set-dispatch-macro-character").readResolve();
        Lit4 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("make-dispatch-macro-character").readResolve();
        Lit3 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("set-macro-character").readResolve();
        Lit2 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("readtable?").readResolve();
        Lit1 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("current-readtable").readResolve();
        Lit0 = simpleSymbol7;
        readtable readtable = new readtable();
        $instance = readtable;
        current$Mnreadtable = new ModuleMethod(readtable, 1, simpleSymbol7, 0);
        readtable$Qu = new ModuleMethod(readtable, 2, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mnmacro$Mncharacter = new ModuleMethod(readtable, 3, simpleSymbol5, InputDeviceCompat.SOURCE_STYLUS);
        make$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable, 6, simpleSymbol4, 12289);
        set$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable, 9, simpleSymbol3, 16387);
        get$Mndispatch$Mnmacro$Mntable = new ModuleMethod(readtable, 11, simpleSymbol2, 12290);
        define$Mnreader$Mnctor = new ModuleMethod(readtable, 13, simpleSymbol, 12290);
        readtable.run();
    }

    public readtable() {
        ModuleInfo.register(this);
    }

    public static void defineReaderCtor(Symbol symbol, Procedure procedure) {
        defineReaderCtor(symbol, procedure, currentReadtable());
    }

    public static Object getDispatchMacroTable(char c, char c2) {
        return getDispatchMacroTable(c, c2, currentReadtable());
    }

    public static void makeDispatchMacroCharacter(char c) {
        makeDispatchMacroCharacter(c, false);
    }

    public static void makeDispatchMacroCharacter(char c, boolean z) {
        makeDispatchMacroCharacter(c, z, currentReadtable());
    }

    public static void setDispatchMacroCharacter(char c, char c2, Object obj) {
        setDispatchMacroCharacter(c, c2, obj, currentReadtable());
    }

    public static void setMacroCharacter(char c, Object obj) {
        setMacroCharacter(c, obj, false);
    }

    public static void setMacroCharacter(char c, Object obj, boolean z) {
        setMacroCharacter(c, obj, z, currentReadtable());
    }

    public Object apply0(ModuleMethod moduleMethod) {
        return moduleMethod.selector == 1 ? currentReadtable() : super.apply0(moduleMethod);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match0(moduleMethod, callContext);
        }
        callContext.proc = moduleMethod;
        callContext.pc = 0;
        return 0;
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public static ReadTable currentReadtable() {
        return ReadTable.getCurrent();
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 6) {
            return super.match1(moduleMethod, obj, callContext);
        } else {
            if (!(obj instanceof Char)) {
                return -786431;
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isReadtable(Object obj) {
        return obj instanceof ReadTable;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 3) {
            if (i != 6) {
                if (i != 11) {
                    if (i != 13) {
                        return super.match2(moduleMethod, obj, obj2, callContext);
                    }
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Procedure)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                } else if (!(obj instanceof Char)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Char)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
            } else if (!(obj instanceof Char)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (!(obj instanceof Char)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 3) {
            if (i != 6) {
                if (i != 9) {
                    if (i != 11) {
                        if (i != 13) {
                            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                        }
                        if (!(obj instanceof Symbol)) {
                            return -786431;
                        }
                        callContext.value1 = obj;
                        if (!(obj2 instanceof Procedure)) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        if (!(obj3 instanceof ReadTable)) {
                            return -786429;
                        }
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    } else if (!(obj instanceof Char)) {
                        return -786431;
                    } else {
                        callContext.value1 = obj;
                        if (!(obj2 instanceof Char)) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        if (!(obj3 instanceof ReadTable)) {
                            return -786429;
                        }
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    }
                } else if (!(obj instanceof Char)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Char)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                }
            } else if (!(obj instanceof Char)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                if (!(obj3 instanceof ReadTable)) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (!(obj instanceof Char)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 3) {
            if (i != 9) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            if (!(obj instanceof Char)) {
                return -786431;
            }
            callContext.value1 = obj;
            if (!(obj2 instanceof Char)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            if (!(obj4 instanceof ReadTable)) {
                return -786428;
            }
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (!(obj instanceof Char)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            if (!(obj4 instanceof ReadTable)) {
                return -786428;
            }
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static void setMacroCharacter(char c, Object obj, boolean z, ReadTable readTable) {
        try {
            readTable.set(c, new ReaderMacro((Procedure) obj, z));
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.kawa.lispexpr.ReaderMacro.<init>(gnu.mapping.Procedure,boolean)", 1, obj);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return isReadtable(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i != 6) {
            return super.apply1(moduleMethod, obj);
        }
        try {
            makeDispatchMacroCharacter(((Char) obj).charValue());
            return Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "make-dispatch-macro-character", 1, obj);
        }
    }

    public static void makeDispatchMacroCharacter(char c, boolean z, ReadTable readTable) {
        readTable.set(c, new ReaderDispatch(z));
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        boolean z = true;
        if (i == 3) {
            try {
                char charValue = ((Char) obj).charValue();
                try {
                    if (obj3 == Boolean.FALSE) {
                        z = false;
                    }
                    try {
                        setMacroCharacter(charValue, obj2, z, (ReadTable) obj4);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "set-macro-character", 4, obj4);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-macro-character", 3, obj3);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "set-macro-character", 1, obj);
            }
        } else if (i != 9) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        } else {
            try {
                try {
                    try {
                        setDispatchMacroCharacter(((Char) obj).charValue(), ((Char) obj2).charValue(), obj3, (ReadTable) obj4);
                        return Values.empty;
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "set-dispatch-macro-character", 4, obj4);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "set-dispatch-macro-character", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "set-dispatch-macro-character", 1, obj);
            }
        }
    }

    public static void setDispatchMacroCharacter(char c, char c2, Object obj, ReadTable readTable) {
        ReadTableEntry lookup = readTable.lookup(c);
        try {
            try {
                ((ReaderDispatch) lookup).set(c2, new ReaderDispatchMacro((Procedure) obj));
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.kawa.lispexpr.ReaderDispatchMacro.<init>(gnu.mapping.Procedure)", 1, obj);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "entry", -2, (Object) lookup);
        }
    }

    public static Object getDispatchMacroTable(char c, char c2, ReadTable readTable) {
        ReadTableEntry lookup = readTable.lookup(c);
        try {
            ReadTableEntry lookup2 = ((ReaderDispatch) lookup).lookup(c2);
            return lookup2 == null ? Boolean.FALSE : lookup2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "disp-entry", -2, (Object) lookup);
        }
    }

    public static void defineReaderCtor(Symbol symbol, Procedure procedure, ReadTable readTable) {
        readTable.putReaderCtor(symbol == null ? null : symbol.toString(), procedure);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        boolean z = true;
        if (i == 3) {
            try {
                setMacroCharacter(((Char) obj).charValue(), obj2);
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-macro-character", 1, obj);
            }
        } else if (i == 6) {
            try {
                char charValue = ((Char) obj).charValue();
                try {
                    if (obj2 == Boolean.FALSE) {
                        z = false;
                    }
                    makeDispatchMacroCharacter(charValue, z);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-dispatch-macro-character", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "make-dispatch-macro-character", 1, obj);
            }
        } else if (i == 11) {
            try {
                try {
                    return getDispatchMacroTable(((Char) obj).charValue(), ((Char) obj2).charValue());
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "get-dispatch-macro-table", 2, obj2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "get-dispatch-macro-table", 1, obj);
            }
        } else if (i != 13) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    defineReaderCtor((Symbol) obj, (Procedure) obj2);
                    return Values.empty;
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "define-reader-ctor", 2, obj2);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "define-reader-ctor", 1, obj);
            }
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        boolean z = false;
        if (i == 3) {
            try {
                char charValue = ((Char) obj).charValue();
                try {
                    if (obj3 != Boolean.FALSE) {
                        z = true;
                    }
                    setMacroCharacter(charValue, obj2, z);
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-macro-character", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "set-macro-character", 1, obj);
            }
        } else if (i == 6) {
            try {
                char charValue2 = ((Char) obj).charValue();
                try {
                    if (obj2 != Boolean.FALSE) {
                        z = true;
                    }
                    try {
                        makeDispatchMacroCharacter(charValue2, z, (ReadTable) obj3);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "make-dispatch-macro-character", 3, obj3);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "make-dispatch-macro-character", 2, obj2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "make-dispatch-macro-character", 1, obj);
            }
        } else if (i == 9) {
            try {
                try {
                    setDispatchMacroCharacter(((Char) obj).charValue(), ((Char) obj2).charValue(), obj3);
                    return Values.empty;
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "set-dispatch-macro-character", 2, obj2);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "set-dispatch-macro-character", 1, obj);
            }
        } else if (i == 11) {
            try {
                try {
                    try {
                        return getDispatchMacroTable(((Char) obj).charValue(), ((Char) obj2).charValue(), (ReadTable) obj3);
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "get-dispatch-macro-table", 3, obj3);
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "get-dispatch-macro-table", 2, obj2);
                }
            } catch (ClassCastException e10) {
                throw new WrongType(e10, "get-dispatch-macro-table", 1, obj);
            }
        } else if (i != 13) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        } else {
            try {
                try {
                    try {
                        defineReaderCtor((Symbol) obj, (Procedure) obj2, (ReadTable) obj3);
                        return Values.empty;
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, "define-reader-ctor", 3, obj3);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "define-reader-ctor", 2, obj2);
                }
            } catch (ClassCastException e13) {
                throw new WrongType(e13, "define-reader-ctor", 1, obj);
            }
        }
    }
}
