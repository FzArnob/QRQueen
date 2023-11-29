package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import gnu.text.Path;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.standard.Scheme;
import kawa.standard.readchar;

/* compiled from: ppfile.scm */
public class ppfile extends ModuleBody {
    public static final ppfile $instance;
    static final Char Lit0 = Char.make(59);
    static final Char Lit1 = Char.make(10);
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod pprint$Mnfile;
    public static final ModuleMethod pprint$Mnfilter$Mnfile;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("pprint-file").readResolve();
        Lit3 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("pprint-filter-file").readResolve();
        Lit2 = simpleSymbol2;
        ppfile ppfile = new ppfile();
        $instance = ppfile;
        pprint$Mnfilter$Mnfile = new ModuleMethod(ppfile, 3, simpleSymbol2, -4094);
        ModuleMethod moduleMethod = new ModuleMethod(ppfile, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:70");
        lambda$Fn3 = moduleMethod;
        pprint$Mnfile = new ModuleMethod(ppfile, 5, simpleSymbol, 8193);
        ppfile.run();
    }

    public ppfile() {
        ModuleInfo.register(this);
    }

    static Object lambda3(Object obj) {
        return obj;
    }

    public static Object pprintFile(Object obj) {
        return pprintFile(obj, ports.current$Mnoutput$Mnport.apply0());
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public static Object pprintFilterFile$V(Object obj, Object obj2, Object[] objArr) {
        frame frame2 = new frame();
        frame2.filter = obj2;
        frame2.optarg = LList.makeList(objArr, 0);
        ModuleMethod moduleMethod = frame2.lambda$Fn1;
        if (ports.isInputPort(obj)) {
            return frame2.lambda1(obj);
        }
        try {
            return ports.callWithInputFile(Path.valueOf(obj), moduleMethod);
        } catch (ClassCastException e) {
            throw new WrongType(e, "call-with-input-file", 1, obj);
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 3) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        int length = objArr.length - 2;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return pprintFilterFile$V(obj, obj2, objArr2);
            }
            objArr2[length] = objArr[length + 2];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 3) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    /* compiled from: ppfile.scm */
    public class frame extends ModuleBody {
        Object filter;
        final ModuleMethod lambda$Fn1;
        LList optarg;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:27");
            this.lambda$Fn1 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 2 ? lambda1(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda1(Object obj) {
            frame0 frame0 = new frame0();
            frame0.staticLink = this;
            frame0.port = obj;
            ModuleMethod moduleMethod = frame0.lambda$Fn2;
            Object apply0 = lists.isNull(this.optarg) ? ports.current$Mnoutput$Mnport.apply0() : lists.car.apply1(this.optarg);
            if (ports.isOutputPort(apply0)) {
                return frame0.lambda2(apply0);
            }
            try {
                return ports.callWithOutputFile(Path.valueOf(apply0), moduleMethod);
            } catch (ClassCastException e) {
                throw new WrongType(e, "call-with-output-file", 1, apply0);
            }
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
    }

    /* compiled from: ppfile.scm */
    public class frame0 extends ModuleBody {
        final ModuleMethod lambda$Fn2;
        Object port;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/ppfile.scm:34");
            this.lambda$Fn2 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda2(obj) : super.apply1(moduleMethod, obj);
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

        /* access modifiers changed from: package-private */
        public Object lambda2(Object obj) {
            Object apply1 = readchar.peekChar.apply1(this.port);
            while (true) {
                boolean isEofObject = ports.isEofObject(apply1);
                if (isEofObject) {
                    return isEofObject ? Boolean.TRUE : Boolean.FALSE;
                }
                try {
                    if (unicode.isCharWhitespace((Char) apply1)) {
                        ports.display(readchar.readChar.apply1(this.port), obj);
                        apply1 = readchar.peekChar.apply1(this.port);
                    } else {
                        try {
                            if (characters.isChar$Eq(ppfile.Lit0, (Char) apply1)) {
                                while (true) {
                                    boolean isEofObject2 = ports.isEofObject(apply1);
                                    if (isEofObject2) {
                                        return isEofObject2 ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                    try {
                                        if (characters.isChar$Eq(ppfile.Lit1, (Char) apply1)) {
                                            ports.display(readchar.readChar.apply1(this.port), obj);
                                            apply1 = readchar.peekChar.apply1(this.port);
                                            break;
                                        }
                                        ports.display(readchar.readChar.apply1(this.port), obj);
                                        apply1 = readchar.peekChar.apply1(this.port);
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "char=?", 2, apply1);
                                    }
                                }
                            } else {
                                Object obj2 = this.port;
                                try {
                                    Object read = ports.read((InPort) obj2);
                                    boolean isEofObject3 = ports.isEofObject(read);
                                    if (isEofObject3) {
                                        return isEofObject3 ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                    pp.prettyPrint(Scheme.applyToArgs.apply2(this.staticLink.filter, read), obj);
                                    apply1 = readchar.peekChar.apply1(this.port);
                                    if (Scheme.isEqv.apply2(ppfile.Lit1, apply1) != Boolean.FALSE) {
                                        readchar.readChar.apply1(this.port);
                                        apply1 = readchar.peekChar.apply1(this.port);
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "read", 1, obj2);
                                }
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 2, apply1);
                        }
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "char-whitespace?", 1, apply1);
                }
            }
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i != 4) {
            return i != 5 ? super.apply1(moduleMethod, obj) : pprintFile(obj);
        }
        return lambda3(obj);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 5 ? pprintFile(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 5) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    public static Object pprintFile(Object obj, Object obj2) {
        return pprintFilterFile$V(obj, lambda$Fn3, new Object[]{obj2});
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 4) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 5) {
            return super.match1(moduleMethod, obj, callContext);
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }
}
