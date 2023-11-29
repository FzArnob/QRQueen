package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

/* compiled from: srfi37.scm */
public class srfi37 extends ModuleBody {
    public static final srfi37 $instance;
    static final IntNum Lit0 = IntNum.make(1);
    static final Char Lit1 = Char.make(45);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final Char Lit2 = Char.make(61);
    static final IntNum Lit3 = IntNum.make(3);
    static final IntNum Lit4 = IntNum.make(0);
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod args$Mnfold;
    public static final ModuleMethod option;
    public static final ModuleMethod option$Mnnames;
    public static final ModuleMethod option$Mnoptional$Mnarg$Qu;
    public static final ModuleMethod option$Mnprocessor;
    public static final ModuleMethod option$Mnrequired$Mnarg$Qu;
    static final Class option$Mntype = option$Mntype.class;
    public static final ModuleMethod option$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("args-fold").readResolve();
        Lit11 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("option-processor").readResolve();
        Lit10 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("option-optional-arg?").readResolve();
        Lit9 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("option-required-arg?").readResolve();
        Lit8 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("option-names").readResolve();
        Lit7 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("option").readResolve();
        Lit6 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("option?").readResolve();
        Lit5 = simpleSymbol7;
        srfi37 srfi37 = new srfi37();
        $instance = srfi37;
        option$Qu = new ModuleMethod(srfi37, 25, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option = new ModuleMethod(srfi37, 26, simpleSymbol6, 16388);
        option$Mnnames = new ModuleMethod(srfi37, 27, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnrequired$Mnarg$Qu = new ModuleMethod(srfi37, 28, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnoptional$Mnarg$Qu = new ModuleMethod(srfi37, 29, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnprocessor = new ModuleMethod(srfi37, 30, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        args$Mnfold = new ModuleMethod(srfi37, 31, simpleSymbol, -4092);
        srfi37.run();
    }

    public srfi37() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public static boolean isOption(Object obj) {
        return obj instanceof option$Mntype;
    }

    public static Object isOptionOptionalArg(option$Mntype option_mntype) {
        return option_mntype.optional$Mnarg$Qu;
    }

    public static Object isOptionRequiredArg(option$Mntype option_mntype) {
        return option_mntype.required$Mnarg$Qu;
    }

    public static option$Mntype option(Object obj, Object obj2, Object obj3, Object obj4) {
        option$Mntype option_mntype = new option$Mntype();
        option_mntype.names = obj;
        option_mntype.required$Mnarg$Qu = obj2;
        option_mntype.optional$Mnarg$Qu = obj3;
        option_mntype.processor = obj4;
        return option_mntype;
    }

    public static Object optionNames(option$Mntype option_mntype) {
        return option_mntype.names;
    }

    public static Object optionProcessor(option$Mntype option_mntype) {
        return option_mntype.processor;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        return moduleMethod.selector == 26 ? option(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 26) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.value4 = obj4;
        callContext.proc = moduleMethod;
        callContext.pc = 4;
        return 0;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 25:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 25:
                return isOption(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 27:
                try {
                    return optionNames((option$Mntype) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "option-names", 1, obj);
                }
            case 28:
                try {
                    return isOptionRequiredArg((option$Mntype) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "option-required-arg?", 1, obj);
                }
            case 29:
                try {
                    return isOptionOptionalArg((option$Mntype) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "option-optional-arg?", 1, obj);
                }
            case 30:
                try {
                    return optionProcessor((option$Mntype) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "option-processor", 1, obj);
                }
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static Object argsFold$V(Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        frame frame7 = new frame();
        frame7.options = obj2;
        frame7.unrecognized$Mnoption$Mnproc = obj3;
        frame7.operand$Mnproc = obj4;
        return frame7.lambda5scanArgs(obj, LList.makeList(objArr, 0));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 31) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object obj3 = objArr[2];
        Object obj4 = objArr[3];
        int length = objArr.length - 4;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return argsFold$V(obj, obj2, obj3, obj4, objArr2);
            }
            objArr2[length] = objArr[length + 4];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 31) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    /* compiled from: srfi37.scm */
    public class frame extends ModuleBody {
        Object operand$Mnproc;
        Object options;
        Object unrecognized$Mnoption$Mnproc;

        public static Object lambda1find(Object obj, Object obj2) {
            if (lists.isNull(obj)) {
                return Boolean.FALSE;
            }
            if (Scheme.applyToArgs.apply2(obj2, lists.car.apply1(obj)) != Boolean.FALSE) {
                return lists.car.apply1(obj);
            }
            return lambda1find(lists.cdr.apply1(obj), obj2);
        }

        public Object lambda2findOption(Object obj) {
            frame0 frame0 = new frame0();
            frame0.staticLink = this;
            frame0.name = obj;
            return lambda1find(this.options, frame0.lambda$Fn1);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a4, code lost:
            if (r4 != java.lang.Boolean.FALSE) goto L_0x00c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b1, code lost:
            if (gnu.kawa.slib.srfi37.isOptionOptionalArg((gnu.kawa.slib.option$Mntype) r4) != java.lang.Boolean.FALSE) goto L_0x00c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c4, code lost:
            if (r4 != false) goto L_0x00c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cf, code lost:
            r4 = r2.option;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d3, code lost:
            r4 = gnu.kawa.slib.srfi37.isOptionRequiredArg((gnu.kawa.slib.option$Mntype) r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d9, code lost:
            if (r4 == java.lang.Boolean.FALSE) goto L_0x00e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e1, code lost:
            if (kawa.lib.lists.isPair(r2.args) == false) goto L_0x00f1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e6, code lost:
            if (r4 == java.lang.Boolean.FALSE) goto L_0x00f1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fa, code lost:
            r7 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0100, code lost:
            throw new gnu.mapping.WrongType(r7, "option-required-arg?", 0, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
            return kawa.standard.call_with_values.callWithValues(r2.lambda$Fn7, r2.lambda$Fn8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
            return kawa.standard.call_with_values.callWithValues(r2.lambda$Fn5, r2.lambda$Fn6);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda3scanShortOptions(java.lang.Object r4, java.lang.Object r5, java.lang.Object r6, java.lang.Object r7) {
            /*
                r3 = this;
                java.lang.String r0 = "string-ref"
                java.lang.String r1 = "string-length"
                gnu.kawa.slib.srfi37$frame1 r2 = new gnu.kawa.slib.srfi37$frame1
                r2.<init>()
                r2.staticLink = r3
                r2.index = r4
                r2.shorts = r5
                r2.args = r6
                r2.seeds = r7
                gnu.kawa.functions.NumberCompare r4 = kawa.standard.Scheme.numEqu
                java.lang.Object r5 = r2.index
                java.lang.Object r6 = r2.shorts
                r7 = 1
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x0121 }
                int r6 = kawa.lib.strings.stringLength(r6)
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object r4 = r4.apply2(r5, r6)
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                if (r4 == r5) goto L_0x0036
                java.lang.Object r4 = r2.args
                java.lang.Object r5 = r2.seeds
                java.lang.Object r4 = r3.lambda5scanArgs(r4, r5)
                goto L_0x00f9
            L_0x0036:
                java.lang.Object r4 = r2.shorts
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x011a }
                java.lang.Object r5 = r2.index
                r6 = r5
                java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ ClassCastException -> 0x0112 }
                int r5 = r6.intValue()     // Catch:{ ClassCastException -> 0x0112 }
                char r4 = kawa.lib.strings.stringRef(r4, r5)
                r2.name = r4
                char r4 = r2.name
                gnu.text.Char r4 = gnu.text.Char.make(r4)
                java.lang.Object r4 = r3.lambda2findOption(r4)
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                if (r4 == r5) goto L_0x0058
                goto L_0x006c
            L_0x0058:
                char r4 = r2.name
                gnu.text.Char r4 = gnu.text.Char.make(r4)
                gnu.lists.Pair r4 = gnu.lists.LList.list1(r4)
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                java.lang.Boolean r6 = java.lang.Boolean.FALSE
                java.lang.Object r0 = r3.unrecognized$Mnoption$Mnproc
                gnu.kawa.slib.option$Mntype r4 = gnu.kawa.slib.srfi37.option(r4, r5, r6, r0)
            L_0x006c:
                r2.option = r4
                gnu.kawa.functions.NumberCompare r4 = kawa.standard.Scheme.numLss
                gnu.kawa.functions.AddOp r5 = gnu.kawa.functions.AddOp.$Pl
                java.lang.Object r6 = r2.index
                gnu.math.IntNum r0 = gnu.kawa.slib.srfi37.Lit0
                java.lang.Object r5 = r5.apply2(r6, r0)
                java.lang.Object r6 = r2.shorts
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x010b }
                int r6 = kawa.lib.strings.stringLength(r6)
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object r4 = r4.apply2(r5, r6)
                r5 = r4
                java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ ClassCastException -> 0x0101 }
                boolean r4 = r5.booleanValue()     // Catch:{ ClassCastException -> 0x0101 }
                java.lang.String r5 = "option-required-arg?"
                r6 = 0
                if (r4 == 0) goto L_0x00c4
                java.lang.Object r4 = r2.option
                gnu.kawa.slib.option$Mntype r4 = (gnu.kawa.slib.option$Mntype) r4     // Catch:{ ClassCastException -> 0x00bd }
                java.lang.Object r4 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r4)
                java.lang.Boolean r7 = java.lang.Boolean.FALSE
                if (r4 == r7) goto L_0x00a7
                java.lang.Boolean r7 = java.lang.Boolean.FALSE
                if (r4 == r7) goto L_0x00cf
                goto L_0x00c6
            L_0x00a7:
                java.lang.Object r4 = r2.option
                gnu.kawa.slib.option$Mntype r4 = (gnu.kawa.slib.option$Mntype) r4     // Catch:{ ClassCastException -> 0x00b4 }
                java.lang.Object r4 = gnu.kawa.slib.srfi37.isOptionOptionalArg(r4)
                java.lang.Boolean r7 = java.lang.Boolean.FALSE
                if (r4 == r7) goto L_0x00cf
                goto L_0x00c6
            L_0x00b4:
                r5 = move-exception
                gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
                java.lang.String r0 = "option-optional-arg?"
                r7.<init>((java.lang.ClassCastException) r5, (java.lang.String) r0, (int) r6, (java.lang.Object) r4)
                throw r7
            L_0x00bd:
                r7 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r7, (java.lang.String) r5, (int) r6, (java.lang.Object) r4)
                throw r0
            L_0x00c4:
                if (r4 == 0) goto L_0x00cf
            L_0x00c6:
                gnu.expr.ModuleMethod r4 = r2.lambda$Fn3
                gnu.expr.ModuleMethod r5 = r2.lambda$Fn4
                java.lang.Object r4 = kawa.standard.call_with_values.callWithValues(r4, r5)
                goto L_0x00f9
            L_0x00cf:
                java.lang.Object r4 = r2.option
                gnu.kawa.slib.option$Mntype r4 = (gnu.kawa.slib.option$Mntype) r4     // Catch:{ ClassCastException -> 0x00fa }
                java.lang.Object r4 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r4)
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                if (r4 == r5) goto L_0x00e4
                java.lang.Object r4 = r2.args
                boolean r4 = kawa.lib.lists.isPair(r4)
                if (r4 == 0) goto L_0x00f1
                goto L_0x00e8
            L_0x00e4:
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                if (r4 == r5) goto L_0x00f1
            L_0x00e8:
                gnu.expr.ModuleMethod r4 = r2.lambda$Fn5
                gnu.expr.ModuleMethod r5 = r2.lambda$Fn6
                java.lang.Object r4 = kawa.standard.call_with_values.callWithValues(r4, r5)
                goto L_0x00f9
            L_0x00f1:
                gnu.expr.ModuleMethod r4 = r2.lambda$Fn7
                gnu.expr.ModuleMethod r5 = r2.lambda$Fn8
                java.lang.Object r4 = kawa.standard.call_with_values.callWithValues(r4, r5)
            L_0x00f9:
                return r4
            L_0x00fa:
                r7 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r7, (java.lang.String) r5, (int) r6, (java.lang.Object) r4)
                throw r0
            L_0x0101:
                r5 = move-exception
                gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
                r7 = -2
                java.lang.String r0 = "x"
                r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r0, (int) r7, (java.lang.Object) r4)
                throw r6
            L_0x010b:
                r4 = move-exception
                gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
                r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r1, (int) r7, (java.lang.Object) r6)
                throw r5
            L_0x0112:
                r4 = move-exception
                gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
                r7 = 2
                r6.<init>((java.lang.ClassCastException) r4, (java.lang.String) r0, (int) r7, (java.lang.Object) r5)
                throw r6
            L_0x011a:
                r5 = move-exception
                gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
                r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r0, (int) r7, (java.lang.Object) r4)
                throw r6
            L_0x0121:
                r4 = move-exception
                gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
                r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r1, (int) r7, (java.lang.Object) r6)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda3scanShortOptions(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object lambda4scanOperands(Object obj, Object obj2) {
            frame2 frame2 = new frame2();
            frame2.staticLink = this;
            frame2.operands = obj;
            frame2.seeds = obj2;
            if (lists.isNull(frame2.operands)) {
                return Scheme.apply.apply2(misc.values, frame2.seeds);
            }
            return call_with_values.callWithValues(frame2.lambda$Fn9, frame2.lambda$Fn10);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:129:0x01e1, code lost:
            r9 = r1.arg;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x01e9, code lost:
            if (kawa.lib.strings.stringLength((java.lang.CharSequence) r9) <= 1) goto L_0x01ed;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x01eb, code lost:
            r9 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x01ed, code lost:
            r9 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x01ee, code lost:
            if (r9 == false) goto L_0x020c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x01f0, code lost:
            r9 = gnu.kawa.slib.srfi37.Lit1;
            r2 = r1.arg;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x0202, code lost:
            if (kawa.lib.characters.isChar$Eq(r9, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r2, 0))) == false) goto L_0x0237;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x0205, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:144:0x020b, code lost:
            throw new gnu.mapping.WrongType(r9, "string-ref", 1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x020c, code lost:
            if (r9 == false) goto L_0x0237;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:0x020e, code lost:
            r9 = r1.arg;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
            r9 = (java.lang.CharSequence) r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x0212, code lost:
            r2 = r1.arg;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x0229, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x022f, code lost:
            throw new gnu.mapping.WrongType(r9, "string-length", 1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:156:0x0230, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x0236, code lost:
            throw new gnu.mapping.WrongType(r0, "substring", 1, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:160:0x0240, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x0246, code lost:
            throw new gnu.mapping.WrongType(r1, "string-length", 1, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:?, code lost:
            return kawa.standard.call_with_values.callWithValues(r1.lambda$Fn23, r1.lambda$Fn24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
            return lambda3scanShortOptions(gnu.kawa.slib.srfi37.Lit4, kawa.lib.strings.substring(r9, 1, kawa.lib.strings.stringLength((java.lang.CharSequence) r2)), r1.args, r1.seeds);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e9, code lost:
            if (r9 != 0) goto L_0x0109;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f3, code lost:
            if (r9 != false) goto L_0x0109;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00fd, code lost:
            if (r9 != false) goto L_0x0109;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0107, code lost:
            if (r9 != false) goto L_0x0109;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x010c, code lost:
            r9 = java.lang.Boolean.FALSE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x0158, code lost:
            if (kawa.lib.characters.isChar$Eq(r9, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r6, 1))) != false) goto L_0x016e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x0162, code lost:
            if (r9 != false) goto L_0x016e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:99:0x016c, code lost:
            if (r9 != false) goto L_0x016e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x0116  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x0120  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object lambda5scanArgs(java.lang.Object r9, java.lang.Object r10) {
            /*
                r8 = this;
                java.lang.String r0 = "string-length"
                gnu.kawa.slib.srfi37$frame3 r1 = new gnu.kawa.slib.srfi37$frame3
                r1.<init>()
                r1.staticLink = r8
                r1.seeds = r10
                boolean r10 = kawa.lib.lists.isNull(r9)
                if (r10 == 0) goto L_0x001d
                gnu.kawa.functions.Apply r9 = kawa.standard.Scheme.apply
                gnu.expr.ModuleMethod r10 = kawa.lib.misc.values
                java.lang.Object r0 = r1.seeds
                java.lang.Object r9 = r9.apply2(r10, r0)
                goto L_0x023f
            L_0x001d:
                gnu.expr.GenericProc r10 = kawa.lib.lists.car
                java.lang.Object r10 = r10.apply1(r9)
                gnu.expr.GenericProc r2 = kawa.lib.lists.cdr
                java.lang.Object r9 = r2.apply1(r9)
                r1.args = r9
                r1.arg = r10
                java.lang.Object r9 = r1.arg
                java.lang.String r10 = "--"
                boolean r9 = kawa.lib.strings.isString$Eq(r10, r9)
                if (r9 == 0) goto L_0x0041
                java.lang.Object r9 = r1.args
                java.lang.Object r10 = r1.seeds
                java.lang.Object r9 = r8.lambda4scanOperands(r9, r10)
                goto L_0x023f
            L_0x0041:
                java.lang.Object r9 = r1.arg
                r10 = 1
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x024e }
                int r9 = kawa.lib.strings.stringLength(r9)
                r2 = 4
                r3 = 0
                if (r9 <= r2) goto L_0x0050
                r9 = 1
                goto L_0x0051
            L_0x0050:
                r9 = 0
            L_0x0051:
                r2 = 2
                java.lang.String r4 = "string-ref"
                if (r9 == 0) goto L_0x0107
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit1
                java.lang.Object r5 = r1.arg
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ ClassCastException -> 0x0100 }
                char r5 = kawa.lib.strings.stringRef(r5, r3)
                gnu.text.Char r5 = gnu.text.Char.make(r5)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r5)
                if (r9 == 0) goto L_0x00fd
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit1
                java.lang.Object r5 = r1.arg
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ ClassCastException -> 0x00f6 }
                char r5 = kawa.lib.strings.stringRef(r5, r10)
                gnu.text.Char r5 = gnu.text.Char.make(r5)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r5)
                if (r9 == 0) goto L_0x00f3
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit2
                java.lang.Object r5 = r1.arg
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ ClassCastException -> 0x00ec }
                char r5 = kawa.lib.strings.stringRef(r5, r2)
                gnu.text.Char r5 = gnu.text.Char.make(r5)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r5)
                int r9 = r9 + r10
                r9 = r9 & r10
                if (r9 == 0) goto L_0x00e9
                gnu.math.IntNum r9 = gnu.kawa.slib.srfi37.Lit3
            L_0x0096:
                gnu.kawa.functions.NumberCompare r5 = kawa.standard.Scheme.numEqu
                java.lang.Object r6 = r1.arg
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x00e2 }
                int r6 = kawa.lib.strings.stringLength(r6)
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object r5 = r5.apply2(r9, r6)
                java.lang.Boolean r6 = java.lang.Boolean.FALSE
                if (r5 == r6) goto L_0x00af
                java.lang.Boolean r9 = java.lang.Boolean.FALSE
                goto L_0x010e
            L_0x00af:
                gnu.text.Char r5 = gnu.kawa.slib.srfi37.Lit2
                java.lang.Object r6 = r1.arg
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x00db }
                r7 = r9
                java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x00d4 }
                int r7 = r7.intValue()     // Catch:{ ClassCastException -> 0x00d4 }
                char r6 = kawa.lib.strings.stringRef(r6, r7)
                gnu.text.Char r6 = gnu.text.Char.make(r6)
                boolean r5 = kawa.lib.characters.isChar$Eq(r5, r6)
                if (r5 == 0) goto L_0x00cb
                goto L_0x010e
            L_0x00cb:
                gnu.kawa.functions.AddOp r5 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r6 = gnu.kawa.slib.srfi37.Lit0
                java.lang.Object r9 = r5.apply2(r6, r9)
                goto L_0x0096
            L_0x00d4:
                r10 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r10, (java.lang.String) r4, (int) r2, (java.lang.Object) r9)
                throw r0
            L_0x00db:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r6)
                throw r0
            L_0x00e2:
                r9 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r1.<init>((java.lang.ClassCastException) r9, (java.lang.String) r0, (int) r10, (java.lang.Object) r6)
                throw r1
            L_0x00e9:
                if (r9 == 0) goto L_0x010c
                goto L_0x0109
            L_0x00ec:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r5)
                throw r0
            L_0x00f3:
                if (r9 == 0) goto L_0x010c
                goto L_0x0109
            L_0x00f6:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r5)
                throw r0
            L_0x00fd:
                if (r9 == 0) goto L_0x010c
                goto L_0x0109
            L_0x0100:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r5)
                throw r0
            L_0x0107:
                if (r9 == 0) goto L_0x010c
            L_0x0109:
                java.lang.Boolean r9 = java.lang.Boolean.TRUE
                goto L_0x010e
            L_0x010c:
                java.lang.Boolean r9 = java.lang.Boolean.FALSE
            L_0x010e:
                r1.temp = r9
                java.lang.Object r9 = r1.temp
                java.lang.Boolean r5 = java.lang.Boolean.FALSE
                if (r9 == r5) goto L_0x0120
                gnu.expr.ModuleMethod r9 = r1.lambda$Fn11
                gnu.expr.ModuleMethod r10 = r1.lambda$Fn12
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x023f
            L_0x0120:
                java.lang.Object r9 = r1.arg
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x0247 }
                int r9 = kawa.lib.strings.stringLength(r9)
                r5 = 3
                if (r9 <= r5) goto L_0x012d
                r9 = 1
                goto L_0x012e
            L_0x012d:
                r9 = 0
            L_0x012e:
                java.lang.String r5 = "substring"
                if (r9 == 0) goto L_0x016c
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit1
                java.lang.Object r6 = r1.arg
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x0165 }
                char r6 = kawa.lib.strings.stringRef(r6, r3)
                gnu.text.Char r6 = gnu.text.Char.make(r6)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r6)
                if (r9 == 0) goto L_0x0162
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit1
                java.lang.Object r6 = r1.arg
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ ClassCastException -> 0x015b }
                char r6 = kawa.lib.strings.stringRef(r6, r10)
                gnu.text.Char r6 = gnu.text.Char.make(r6)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r6)
                if (r9 == 0) goto L_0x01e1
                goto L_0x016e
            L_0x015b:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r6)
                throw r0
            L_0x0162:
                if (r9 == 0) goto L_0x01e1
                goto L_0x016e
            L_0x0165:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r6)
                throw r0
            L_0x016c:
                if (r9 == 0) goto L_0x01e1
            L_0x016e:
                java.lang.Object r9 = r1.arg
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x01da }
                java.lang.Object r4 = r1.arg
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x01d3 }
                int r10 = kawa.lib.strings.stringLength(r4)
                java.lang.CharSequence r9 = kawa.lib.strings.substring(r9, r2, r10)
                r1.name = r9
                java.lang.CharSequence r9 = r1.name
                java.lang.Object r9 = r8.lambda2findOption(r9)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x018b
                goto L_0x019b
            L_0x018b:
                java.lang.CharSequence r9 = r1.name
                gnu.lists.Pair r9 = gnu.lists.LList.list1(r9)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                java.lang.Boolean r0 = java.lang.Boolean.FALSE
                java.lang.Object r2 = r8.unrecognized$Mnoption$Mnproc
                gnu.kawa.slib.option$Mntype r9 = gnu.kawa.slib.srfi37.option(r9, r10, r0, r2)
            L_0x019b:
                r1.option = r9
                java.lang.Object r9 = r1.option
                gnu.kawa.slib.option$Mntype r9 = (gnu.kawa.slib.option$Mntype) r9     // Catch:{ ClassCastException -> 0x01ca }
                java.lang.Object r9 = gnu.kawa.slib.srfi37.isOptionRequiredArg(r9)
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x01b2
                java.lang.Object r9 = r1.args
                boolean r9 = kawa.lib.lists.isPair(r9)
                if (r9 == 0) goto L_0x01c0
                goto L_0x01b6
            L_0x01b2:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                if (r9 == r10) goto L_0x01c0
            L_0x01b6:
                gnu.expr.ModuleMethod r9 = r1.lambda$Fn19
                gnu.expr.ModuleMethod r10 = r1.lambda$Fn20
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x023f
            L_0x01c0:
                gnu.expr.ModuleMethod r9 = r1.lambda$Fn21
                gnu.expr.ModuleMethod r10 = r1.lambda$Fn22
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
                goto L_0x023f
            L_0x01ca:
                r10 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                java.lang.String r1 = "option-required-arg?"
                r0.<init>((java.lang.ClassCastException) r10, (java.lang.String) r1, (int) r3, (java.lang.Object) r9)
                throw r0
            L_0x01d3:
                r9 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r1.<init>((java.lang.ClassCastException) r9, (java.lang.String) r0, (int) r10, (java.lang.Object) r4)
                throw r1
            L_0x01da:
                r0 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r5, (int) r10, (java.lang.Object) r9)
                throw r1
            L_0x01e1:
                java.lang.Object r9 = r1.arg
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x0240 }
                int r9 = kawa.lib.strings.stringLength(r9)
                if (r9 <= r10) goto L_0x01ed
                r9 = 1
                goto L_0x01ee
            L_0x01ed:
                r9 = 0
            L_0x01ee:
                if (r9 == 0) goto L_0x020c
                gnu.text.Char r9 = gnu.kawa.slib.srfi37.Lit1
                java.lang.Object r2 = r1.arg
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ ClassCastException -> 0x0205 }
                char r2 = kawa.lib.strings.stringRef(r2, r3)
                gnu.text.Char r2 = gnu.text.Char.make(r2)
                boolean r9 = kawa.lib.characters.isChar$Eq(r9, r2)
                if (r9 == 0) goto L_0x0237
                goto L_0x020e
            L_0x0205:
                r9 = move-exception
                gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
                r0.<init>((java.lang.ClassCastException) r9, (java.lang.String) r4, (int) r10, (java.lang.Object) r2)
                throw r0
            L_0x020c:
                if (r9 == 0) goto L_0x0237
            L_0x020e:
                java.lang.Object r9 = r1.arg
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x0230 }
                java.lang.Object r2 = r1.arg
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ ClassCastException -> 0x0229 }
                int r0 = kawa.lib.strings.stringLength(r2)
                java.lang.CharSequence r9 = kawa.lib.strings.substring(r9, r10, r0)
                gnu.math.IntNum r10 = gnu.kawa.slib.srfi37.Lit4
                java.lang.Object r0 = r1.args
                java.lang.Object r1 = r1.seeds
                java.lang.Object r9 = r8.lambda3scanShortOptions(r10, r9, r0, r1)
                goto L_0x023f
            L_0x0229:
                r9 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r1.<init>((java.lang.ClassCastException) r9, (java.lang.String) r0, (int) r10, (java.lang.Object) r2)
                throw r1
            L_0x0230:
                r0 = move-exception
                gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
                r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r5, (int) r10, (java.lang.Object) r9)
                throw r1
            L_0x0237:
                gnu.expr.ModuleMethod r9 = r1.lambda$Fn23
                gnu.expr.ModuleMethod r10 = r1.lambda$Fn24
                java.lang.Object r9 = kawa.standard.call_with_values.callWithValues(r9, r10)
            L_0x023f:
                return r9
            L_0x0240:
                r1 = move-exception
                gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
                r2.<init>((java.lang.ClassCastException) r1, (java.lang.String) r0, (int) r10, (java.lang.Object) r9)
                throw r2
            L_0x0247:
                r1 = move-exception
                gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
                r2.<init>((java.lang.ClassCastException) r1, (java.lang.String) r0, (int) r10, (java.lang.Object) r9)
                throw r2
            L_0x024e:
                r1 = move-exception
                gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
                r2.<init>((java.lang.ClassCastException) r1, (java.lang.String) r0, (int) r10, (java.lang.Object) r9)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda5scanArgs(java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: srfi37.scm */
    public class frame0 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object name;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:75");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:72");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            int i = moduleMethod.selector;
            return i != 1 ? i != 2 ? super.apply1(moduleMethod, obj) : lambda6(obj) : lambda7(obj) ? Boolean.TRUE : Boolean.FALSE;
        }

        /* access modifiers changed from: package-private */
        public Object lambda6(Object obj) {
            try {
                return frame.lambda1find(srfi37.optionNames((option$Mntype) obj), this.lambda$Fn2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-names", 0, obj);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i == 1) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (i != 2) {
                return super.match1(moduleMethod, obj, callContext);
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda7(Object obj) {
            return IsEqual.apply(this.name, obj);
        }
    }

    /* compiled from: srfi37.scm */
    public class frame1 extends ModuleBody {
        Object args;
        Object index;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, (Object) null, 0);
        final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, (Object) null, -4096);
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, (Object) null, 0);
        final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, (Object) null, -4096);
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, (Object) null, 0);
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, (Object) null, -4096);
        char name;
        Object option;
        Object seeds;
        Object shorts;
        frame staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            int i = moduleMethod.selector;
            return i != 3 ? i != 5 ? i != 7 ? super.apply0(moduleMethod) : lambda12() : lambda10() : lambda8();
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            int i = moduleMethod.selector;
            return i != 4 ? i != 6 ? i != 8 ? super.applyN(moduleMethod, objArr) : lambda13$V(objArr) : lambda11$V(objArr) : lambda9$V(objArr);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 3 && i != 5 && i != 7) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 4 && i != 6 && i != 8) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda9$V(Object[] objArr) {
            return this.staticLink.lambda5scanArgs(this.args, LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda8() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                Object obj2 = this.shorts;
                try {
                    CharSequence charSequence = (CharSequence) obj2;
                    Object apply2 = AddOp.$Pl.apply2(this.index, srfi37.Lit0);
                    try {
                        int intValue = ((Number) apply2).intValue();
                        Object obj3 = this.shorts;
                        try {
                            objArr[3] = strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj3));
                            objArr[4] = this.seeds;
                            return apply.applyN(objArr);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-length", 1, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "substring", 2, apply2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "substring", 1, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "option-processor", 0, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda11$V(Object[] objArr) {
            return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda10() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                objArr[3] = lists.car.apply1(this.args);
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda13$V(Object[] objArr) {
            return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda12() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                objArr[3] = Boolean.FALSE;
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }
    }

    /* compiled from: srfi37.scm */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, (Object) null, -4096);
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, (Object) null, 0);
        Object operands;
        Object seeds;
        frame staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 9 ? lambda14() : super.apply0(moduleMethod);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            return moduleMethod.selector == 10 ? lambda15$V(objArr) : super.applyN(moduleMethod, objArr);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda14() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, lists.car.apply1(this.operands), this.seeds);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15$V(Object[] objArr) {
            return this.staticLink.lambda4scanOperands(lists.cdr.apply1(this.operands), LList.makeList(objArr, 0));
        }
    }

    /* compiled from: srfi37.scm */
    public class frame3 extends ModuleBody {
        Object arg;
        Object args;
        final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, (Object) null, 0);
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, (Object) null, 0);
        final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, (Object) null, -4096);
        final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, (Object) null, 0);
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, (Object) null, -4096);
        final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, (Object) null, 0);
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, (Object) null, -4096);
        CharSequence name;
        Object option;
        Object seeds;
        frame staticLink;
        Object temp;

        public Object apply0(ModuleMethod moduleMethod) {
            int i = moduleMethod.selector;
            return i != 17 ? i != 19 ? i != 21 ? i != 23 ? super.apply0(moduleMethod) : lambda28() : lambda26() : lambda24() : lambda16();
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 18 ? lambda17(obj) : super.apply1(moduleMethod, obj);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            int i = moduleMethod.selector;
            return i != 20 ? i != 22 ? i != 24 ? super.applyN(moduleMethod, objArr) : lambda29$V(objArr) : lambda27$V(objArr) : lambda25$V(objArr);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 17 && i != 19 && i != 21 && i != 23) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 18) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 20 && i != 22 && i != 24) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda17(Object obj) {
            frame4 frame4 = new frame4();
            frame4.staticLink = this;
            frame4.x = obj;
            return call_with_values.callWithValues(frame4.lambda$Fn13, frame4.lambda$Fn14);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda16() {
            Object obj = this.arg;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object obj2 = this.temp;
                try {
                    return strings.substring(charSequence, 2, ((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "substring", 3, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "substring", 1, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda25$V(Object[] objArr) {
            return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda24() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = this.name;
                objArr[3] = lists.car.apply1(this.args);
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda27$V(Object[] objArr) {
            return this.staticLink.lambda5scanArgs(this.args, LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda26() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = this.name;
                objArr[3] = Boolean.FALSE;
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda28() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
        }

        /* access modifiers changed from: package-private */
        public Object lambda29$V(Object[] objArr) {
            return this.staticLink.lambda5scanArgs(this.args, LList.makeList(objArr, 0));
        }
    }

    /* compiled from: srfi37.scm */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, (Object) null, 0);
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        frame3 staticLink;
        Object x;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 15 ? lambda18() : super.apply0(moduleMethod);
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 16 ? lambda19(obj) : super.apply1(moduleMethod, obj);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 16) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda19(Object obj) {
            frame5 frame5 = new frame5();
            frame5.staticLink = this;
            frame5.x = obj;
            return call_with_values.callWithValues(frame5.lambda$Fn15, frame5.lambda$Fn16);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda18() {
            Object obj = this.staticLink.arg;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply2 = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
                try {
                    int intValue = ((Number) apply2).intValue();
                    Object obj2 = this.staticLink.arg;
                    try {
                        return strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj2));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-length", 1, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj);
            }
        }
    }

    /* compiled from: srfi37.scm */
    public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, (Object) null, 0);
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        frame4 staticLink;
        Object x;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 13 ? lambda20() : super.apply0(moduleMethod);
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 14 ? lambda21(obj) : super.apply1(moduleMethod, obj);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 13) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda21(Object obj) {
            frame6 frame6 = new frame6();
            frame6.staticLink = this;
            frame6.x = obj;
            return call_with_values.callWithValues(frame6.lambda$Fn17, frame6.lambda$Fn18);
        }

        /* access modifiers changed from: package-private */
        public Object lambda20() {
            Object lambda2findOption = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
            return lambda2findOption != Boolean.FALSE ? lambda2findOption : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
    }

    /* compiled from: srfi37.scm */
    public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, (Object) null, 0);
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, (Object) null, -4096);
        frame5 staticLink;
        Object x;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 11 ? lambda22() : super.apply0(moduleMethod);
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            return moduleMethod.selector == 12 ? lambda23$V(objArr) : super.applyN(moduleMethod, objArr);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda23$V(Object[] objArr) {
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, LList.makeList(objArr, 0));
        }

        /* access modifiers changed from: package-private */
        public Object lambda22() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.x;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.x;
                objArr[2] = this.staticLink.staticLink.x;
                objArr[3] = this.staticLink.x;
                objArr[4] = this.staticLink.staticLink.staticLink.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }
    }
}
