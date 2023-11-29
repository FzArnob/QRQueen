package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.standard.Scheme;
import kawa.standard.append;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: srfi95.scm */
public class srfi95 extends ModuleBody {
    public static final ModuleMethod $Pcsort$Mnlist;
    public static final ModuleMethod $Pcsort$Mnvector;
    public static final ModuleMethod $Pcvector$Mnsort$Ex;
    public static final srfi95 $instance;
    static final IntNum Lit0 = IntNum.make(-1);
    static final IntNum Lit1 = IntNum.make(2);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final IntNum Lit2 = IntNum.make(1);
    static final IntNum Lit3 = IntNum.make(0);
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    static final ModuleMethod identity;
    public static final ModuleMethod merge;
    public static final ModuleMethod merge$Ex;
    public static final ModuleMethod sort;
    public static final ModuleMethod sort$Ex;
    public static final ModuleMethod sorted$Qu;

    public static void $PcSortVector(Sequence sequence, Object obj) {
        $PcSortVector(sequence, obj, Boolean.FALSE);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("sort").readResolve();
        Lit12 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("%sort-vector").readResolve();
        Lit11 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("%vector-sort!").readResolve();
        Lit10 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("sort!").readResolve();
        Lit9 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("%sort-list").readResolve();
        Lit8 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("merge!").readResolve();
        Lit7 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("merge").readResolve();
        Lit6 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("sorted?").readResolve();
        Lit5 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("identity").readResolve();
        Lit4 = simpleSymbol9;
        srfi95 srfi95 = new srfi95();
        $instance = srfi95;
        identity = new ModuleMethod(srfi95, 1, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sorted$Qu = new ModuleMethod(srfi95, 2, simpleSymbol8, 12290);
        merge = new ModuleMethod(srfi95, 4, simpleSymbol7, 16387);
        merge$Ex = new ModuleMethod(srfi95, 6, simpleSymbol6, 16387);
        $Pcsort$Mnlist = new ModuleMethod(srfi95, 8, simpleSymbol5, 12291);
        sort$Ex = new ModuleMethod(srfi95, 9, simpleSymbol4, 12290);
        $Pcvector$Mnsort$Ex = new ModuleMethod(srfi95, 11, simpleSymbol3, 12291);
        $Pcsort$Mnvector = new ModuleMethod(srfi95, 12, simpleSymbol2, 12290);
        sort = new ModuleMethod(srfi95, 14, simpleSymbol, 12290);
        srfi95.run();
    }

    public srfi95() {
        ModuleInfo.register(this);
    }

    static Object identity(Object obj) {
        return obj;
    }

    public static Object isSorted(Object obj, Object obj2) {
        return isSorted(obj, obj2, identity);
    }

    public static Object merge(Object obj, Object obj2, Object obj3) {
        return merge(obj, obj2, obj3, identity);
    }

    public static Object merge$Ex(Object obj, Object obj2, Object obj3) {
        return merge$Ex(obj, obj2, obj3, identity);
    }

    public static Object sort(Sequence sequence, Object obj) {
        return sort(sequence, obj, Boolean.FALSE);
    }

    public static Object sort$Ex(Sequence sequence, Object obj) {
        return sort$Ex(sequence, obj, Boolean.FALSE);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 1 ? identity(obj) : super.apply1(moduleMethod, obj);
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

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 9) {
            if (i != 12) {
                if (i != 14) {
                    return super.match2(moduleMethod, obj, obj2, callContext);
                }
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (!(obj instanceof Sequence)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (!(obj instanceof Sequence)) {
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
        if (i == 2) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i == 4) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i == 6) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i != 14) {
            if (i == 8) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i != 9) {
                if (i != 11) {
                    if (i != 12) {
                        return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                    }
                    if (!(obj instanceof Sequence)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                } else if (!(obj instanceof Sequence)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                }
            } else if (!(obj instanceof Sequence)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (!(obj instanceof Sequence)) {
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

    public static Object isSorted(Object obj, Object obj2, Object obj3) {
        if (lists.isNull(obj)) {
            return Boolean.TRUE;
        }
        boolean z = false;
        if (obj instanceof Sequence) {
            try {
                Sequence sequence = (Sequence) obj;
                int size = sequence.size() - 1;
                if (size <= 1) {
                    z = true;
                }
                if (z) {
                    return z ? Boolean.TRUE : Boolean.FALSE;
                }
                Object valueOf = Integer.valueOf(size - 1);
                Object apply2 = Scheme.applyToArgs.apply2(obj3, sequence.get(size));
                while (true) {
                    try {
                        boolean isNegative = numbers.isNegative(LangObjType.coerceRealNum(valueOf));
                        if (isNegative) {
                            return isNegative ? Boolean.TRUE : Boolean.FALSE;
                        }
                        try {
                            Object apply22 = Scheme.applyToArgs.apply2(obj3, sequence.get(((Number) valueOf).intValue()));
                            Object apply3 = Scheme.applyToArgs.apply3(obj2, apply22, apply2);
                            if (apply3 == Boolean.FALSE) {
                                return apply3;
                            }
                            valueOf = AddOp.$Pl.apply2(Lit0, valueOf);
                            apply2 = apply22;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "gnu.lists.Sequence.get(int)", 2, valueOf);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "negative?", 1, valueOf);
                    }
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "arr", -2, obj);
            }
        } else if (lists.isNull(lists.cdr.apply1(obj))) {
            return Boolean.TRUE;
        } else {
            Object apply23 = Scheme.applyToArgs.apply2(obj3, lists.car.apply1(obj));
            Object apply1 = lists.cdr.apply1(obj);
            while (true) {
                boolean isNull = lists.isNull(apply1);
                if (isNull) {
                    return isNull ? Boolean.TRUE : Boolean.FALSE;
                }
                Object apply24 = Scheme.applyToArgs.apply2(obj3, lists.car.apply1(apply1));
                Object apply32 = Scheme.applyToArgs.apply3(obj2, apply24, apply23);
                try {
                    int i = ((apply32 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                    if (i == 0) {
                        return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                    }
                    apply1 = lists.cdr.apply1(apply1);
                    apply23 = apply24;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply32);
                }
            }
        }
    }

    public static Object merge(Object obj, Object obj2, Object obj3, Object obj4) {
        frame frame2 = new frame();
        frame2.less$Qu = obj3;
        frame2.key = obj4;
        if (lists.isNull(obj)) {
            return obj2;
        }
        return lists.isNull(obj2) ? obj : frame2.lambda1loop(lists.car.apply1(obj), Scheme.applyToArgs.apply2(frame2.key, lists.car.apply1(obj)), lists.cdr.apply1(obj), lists.car.apply1(obj2), Scheme.applyToArgs.apply2(frame2.key, lists.car.apply1(obj2)), lists.cdr.apply1(obj2));
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 4) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i != 6) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    /* compiled from: srfi95.scm */
    public class frame extends ModuleBody {
        Object key;
        Object less$Qu;

        public Object lambda1loop(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            if (Scheme.applyToArgs.apply3(this.less$Qu, obj5, obj2) != Boolean.FALSE) {
                if (lists.isNull(obj6)) {
                    return lists.cons(obj4, lists.cons(obj, obj3));
                }
                return lists.cons(obj4, lambda1loop(obj, obj2, obj3, lists.car.apply1(obj6), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(obj6)), lists.cdr.apply1(obj6)));
            } else if (lists.isNull(obj3)) {
                return lists.cons(obj, lists.cons(obj4, obj6));
            } else {
                return lists.cons(obj, lambda1loop(lists.car.apply1(obj3), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(obj3)), lists.cdr.apply1(obj3), obj4, obj5, obj6));
            }
        }
    }

    static Object sort$ClMerge$Ex(Object obj, Object obj2, Object obj3, Object obj4) {
        frame1 frame12 = new frame1();
        frame12.less$Qu = obj3;
        frame12.key = obj4;
        if (!lists.isNull(obj)) {
            if (lists.isNull(obj2)) {
                return obj;
            }
            Object apply2 = Scheme.applyToArgs.apply2(frame12.key, lists.car.apply1(obj));
            Object apply22 = Scheme.applyToArgs.apply2(frame12.key, lists.car.apply1(obj2));
            if (Scheme.applyToArgs.apply3(frame12.less$Qu, apply22, apply2) != Boolean.FALSE) {
                if (lists.isNull(lists.cdr.apply1(obj2))) {
                    try {
                        lists.setCdr$Ex((Pair) obj2, obj);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "set-cdr!", 1, obj2);
                    }
                } else {
                    frame12.lambda3loop(obj2, obj, apply2, lists.cdr.apply1(obj2), Scheme.applyToArgs.apply2(frame12.key, lists.cadr.apply1(obj2)));
                }
            } else if (lists.isNull(lists.cdr.apply1(obj))) {
                try {
                    lists.setCdr$Ex((Pair) obj, obj2);
                    return obj;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, obj);
                }
            } else {
                frame12.lambda3loop(obj, lists.cdr.apply1(obj), Scheme.applyToArgs.apply2(frame12.key, lists.cadr.apply1(obj)), obj2, apply22);
                return obj;
            }
        }
        return obj2;
    }

    /* compiled from: srfi95.scm */
    public class frame1 extends ModuleBody {
        Object key;
        Object less$Qu;

        public Object lambda3loop(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            if (Scheme.applyToArgs.apply3(this.less$Qu, obj5, obj3) != Boolean.FALSE) {
                try {
                    lists.setCdr$Ex((Pair) obj, obj4);
                    if (lists.isNull(lists.cdr.apply1(obj4))) {
                        try {
                            lists.setCdr$Ex((Pair) obj4, obj2);
                            return Values.empty;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-cdr!", 1, obj4);
                        }
                    } else {
                        return lambda3loop(obj4, obj2, obj3, lists.cdr.apply1(obj4), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(obj4)));
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, obj);
                }
            } else {
                try {
                    lists.setCdr$Ex((Pair) obj, obj2);
                    if (lists.isNull(lists.cdr.apply1(obj2))) {
                        try {
                            lists.setCdr$Ex((Pair) obj2, obj4);
                            return Values.empty;
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "set-cdr!", 1, obj2);
                        }
                    } else {
                        return lambda3loop(obj2, lists.cdr.apply1(obj2), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(obj2)), obj4, obj5);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "set-cdr!", 1, obj);
                }
            }
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        if (i == 4) {
            return merge(obj, obj2, obj3, obj4);
        }
        if (i != 6) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
        return merge$Ex(obj, obj2, obj3, obj4);
    }

    public static Object merge$Ex(Object obj, Object obj2, Object obj3, Object obj4) {
        return sort$ClMerge$Ex(obj, obj2, obj3, obj4);
    }

    public static Object $PcSortList(Object obj, Object obj2, Object obj3) {
        frame0 frame02 = new frame0();
        frame02.seq = obj;
        frame02.less$Qu = obj2;
        frame02.keyer = Special.undefined;
        frame02.keyer = obj3 != Boolean.FALSE ? lists.car : identity;
        if (obj3 != Boolean.FALSE) {
            Object obj4 = frame02.seq;
            while (!lists.isNull(obj4)) {
                try {
                    lists.setCar$Ex((Pair) obj4, lists.cons(Scheme.applyToArgs.apply2(obj3, lists.car.apply1(obj4)), lists.car.apply1(obj4)));
                    obj4 = lists.cdr.apply1(obj4);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-car!", 1, obj4);
                }
            }
            Object obj5 = frame02.seq;
            try {
                frame02.seq = frame02.lambda2step(Integer.valueOf(lists.length((LList) obj5)));
                Object obj6 = frame02.seq;
                while (!lists.isNull(obj6)) {
                    try {
                        lists.setCar$Ex((Pair) obj6, lists.cdar.apply1(obj6));
                        obj6 = lists.cdr.apply1(obj6);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "set-car!", 1, obj6);
                    }
                }
                return frame02.seq;
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "length", 1, obj5);
            }
        } else {
            Object obj7 = frame02.seq;
            try {
                return frame02.lambda2step(Integer.valueOf(lists.length((LList) obj7)));
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "length", 1, obj7);
            }
        }
    }

    /* compiled from: srfi95.scm */
    public class frame0 extends ModuleBody {
        Object keyer;
        Object less$Qu;
        Object seq;

        public Object lambda2step(Object obj) {
            if (Scheme.numGrt.apply2(obj, srfi95.Lit1) != Boolean.FALSE) {
                Object apply2 = DivideOp.quotient.apply2(obj, srfi95.Lit1);
                return srfi95.sort$ClMerge$Ex(lambda2step(apply2), lambda2step(AddOp.$Mn.apply2(obj, apply2)), this.less$Qu, this.keyer);
            } else if (Scheme.numEqu.apply2(obj, srfi95.Lit1) != Boolean.FALSE) {
                Object apply1 = lists.car.apply1(this.seq);
                Object apply12 = lists.cadr.apply1(this.seq);
                Object obj2 = this.seq;
                this.seq = lists.cddr.apply1(this.seq);
                if (Scheme.applyToArgs.apply3(this.less$Qu, Scheme.applyToArgs.apply2(this.keyer, apply12), Scheme.applyToArgs.apply2(this.keyer, apply1)) != Boolean.FALSE) {
                    try {
                        lists.setCar$Ex((Pair) obj2, apply12);
                        Object apply13 = lists.cdr.apply1(obj2);
                        try {
                            lists.setCar$Ex((Pair) apply13, apply1);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-car!", 1, apply13);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "set-car!", 1, obj2);
                    }
                }
                Object apply14 = lists.cdr.apply1(obj2);
                try {
                    lists.setCdr$Ex((Pair) apply14, LList.Empty);
                    return obj2;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "set-cdr!", 1, apply14);
                }
            } else if (Scheme.numEqu.apply2(obj, srfi95.Lit2) == Boolean.FALSE) {
                return LList.Empty;
            } else {
                Object obj3 = this.seq;
                this.seq = lists.cdr.apply1(this.seq);
                try {
                    lists.setCdr$Ex((Pair) obj3, LList.Empty);
                    return obj3;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "set-cdr!", 1, obj3);
                }
            }
        }
    }

    static Object rank$Mn1Array$To$List(Sequence sequence) {
        Object obj = LList.Empty;
        for (int size = sequence.size() - 1; size >= 0; size--) {
            obj = lists.cons(sequence.get(size), obj);
        }
        return obj;
    }

    public static Object sort$Ex(Sequence sequence, Object obj, Object obj2) {
        if (!lists.isList(sequence)) {
            return $PcVectorSort$Ex(sequence, obj, obj2);
        }
        Object $PcSortList = $PcSortList(sequence, obj, obj2);
        if ($PcSortList == sequence) {
            return sequence;
        }
        Object obj3 = $PcSortList;
        while (lists.cdr.apply1(obj3) != sequence) {
            obj3 = lists.cdr.apply1(obj3);
        }
        try {
            lists.setCdr$Ex((Pair) obj3, $PcSortList);
            Object apply1 = lists.car.apply1(sequence);
            Object apply12 = lists.cdr.apply1(sequence);
            try {
                lists.setCar$Ex((Pair) sequence, lists.car.apply1($PcSortList));
                try {
                    lists.setCdr$Ex((Pair) sequence, lists.cdr.apply1($PcSortList));
                    try {
                        lists.setCar$Ex((Pair) $PcSortList, apply1);
                        try {
                            lists.setCdr$Ex((Pair) $PcSortList, apply12);
                            return sequence;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-cdr!", 1, $PcSortList);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "set-car!", 1, $PcSortList);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "set-cdr!", 1, (Object) sequence);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "set-car!", 1, (Object) sequence);
            }
        } catch (ClassCastException e5) {
            throw new WrongType(e5, "set-cdr!", 1, obj3);
        }
    }

    public static Object $PcVectorSort$Ex(Sequence sequence, Object obj, Object obj2) {
        Object $PcSortList = $PcSortList(rank$Mn1Array$To$List(sequence), obj, obj2);
        Object obj3 = Lit3;
        while (!lists.isNull($PcSortList)) {
            sequence.set(((Number) obj3).intValue(), lists.car.apply1($PcSortList));
            $PcSortList = lists.cdr.apply1($PcSortList);
            obj3 = AddOp.$Pl.apply2(obj3, Lit2);
        }
        return sequence;
    }

    public static void $PcSortVector(Sequence sequence, Object obj, Object obj2) {
        FVector makeVector = vectors.makeVector(sequence.size());
        Object $PcSortList = $PcSortList(rank$Mn1Array$To$List(sequence), obj, obj2);
        Object obj3 = Lit3;
        while (!lists.isNull($PcSortList)) {
            try {
                vectors.vectorSet$Ex(makeVector, ((Number) obj3).intValue(), lists.car.apply1($PcSortList));
                $PcSortList = lists.cdr.apply1($PcSortList);
                obj3 = AddOp.$Pl.apply2(obj3, Lit2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "vector-set!", 1, obj3);
            }
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return isSorted(obj, obj2);
        }
        if (i == 9) {
            try {
                return sort$Ex((Sequence) obj, obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "sort!", 1, obj);
            }
        } else if (i == 12) {
            try {
                $PcSortVector((Sequence) obj, obj2);
                return Values.empty;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "%sort-vector", 1, obj);
            }
        } else if (i != 14) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                return sort((Sequence) obj, obj2);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "sort", 1, obj);
            }
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return isSorted(obj, obj2, obj3);
        }
        if (i == 4) {
            return merge(obj, obj2, obj3);
        }
        if (i == 6) {
            return merge$Ex(obj, obj2, obj3);
        }
        if (i == 14) {
            try {
                return sort((Sequence) obj, obj2, obj3);
            } catch (ClassCastException e) {
                throw new WrongType(e, "sort", 1, obj);
            }
        } else if (i == 8) {
            return $PcSortList(obj, obj2, obj3);
        } else {
            if (i == 9) {
                try {
                    return sort$Ex((Sequence) obj, obj2, obj3);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "sort!", 1, obj);
                }
            } else if (i == 11) {
                try {
                    return $PcVectorSort$Ex((Sequence) obj, obj2, obj3);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%vector-sort!", 1, obj);
                }
            } else if (i != 12) {
                return super.apply3(moduleMethod, obj, obj2, obj3);
            } else {
                try {
                    $PcSortVector((Sequence) obj, obj2, obj3);
                    return Values.empty;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "%sort-vector", 1, obj);
                }
            }
        }
    }

    public static Object sort(Sequence sequence, Object obj, Object obj2) {
        if (lists.isList(sequence)) {
            return $PcSortList(append.append$V(new Object[]{sequence, LList.Empty}), obj, obj2);
        }
        $PcSortVector(sequence, obj, obj2);
        return Values.empty;
    }
}
