package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEq;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.slib.condition;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;
import org.slf4j.Marker;

/* compiled from: conditions.scm */
public class conditions extends ModuleBody {
    public static Object $Amcondition;
    public static Object $Amerror;
    public static Object $Ammessage;
    public static Object $Amserious;
    static final Class $Lscondition$Mntype$Gr = condition.Mntype.class;
    public static final Class $Prvt$$Lscondition$Gr = condition.class;
    public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
    public static final conditions $instance;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("&condition").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("&message").readResolve());
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final PairWithPosition Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("&serious").readResolve());
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("&error").readResolve());
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro condition;
    public static final ModuleMethod condition$Mnhas$Mntype$Qu;
    public static final ModuleMethod condition$Mnref;
    static final Macro condition$Mntype$Mnfield$Mnalist;
    public static final ModuleMethod condition$Mntype$Qu;
    public static final ModuleMethod condition$Qu;
    public static final Macro define$Mncondition$Mntype;
    public static final ModuleMethod extract$Mncondition;
    public static final ModuleMethod make$Mncompound$Mncondition;
    public static final ModuleMethod make$Mncondition;
    public static final ModuleMethod make$Mncondition$Mntype;

    public conditions() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        condition.Mntype mntype = new condition.Mntype(Lit0, Boolean.FALSE, LList.Empty, LList.Empty);
        $Amcondition = mntype;
        try {
            condition.Mntype mntype2 = mntype;
            $Ammessage = makeConditionType(Lit1, mntype, Lit2);
            SimpleSymbol simpleSymbol = Lit3;
            Object obj = $Amcondition;
            try {
                condition.Mntype makeConditionType = makeConditionType(simpleSymbol, (condition.Mntype) obj, LList.Empty);
                $Amserious = makeConditionType;
                try {
                    condition.Mntype mntype3 = makeConditionType;
                    $Amerror = makeConditionType(Lit4, makeConditionType, LList.Empty);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-condition-type", 1, (Object) makeConditionType);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "make-condition-type", 1, obj);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "make-condition-type", 1, (Object) mntype);
        }
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("thing").readResolve();
        Lit22 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit21 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("type-field-alist->condition").readResolve();
        Lit20 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("condition").readResolve();
        Lit18 = simpleSymbol4;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol4}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018]\f\u0007-\f\u000f\f\u0017\b\b\u0010\b\u0000\u0018\b", new Object[0], 3), "\u0003\u0005\u0005", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0005\u0011\u0018\u0014\t\u0003\b\u0011\u0018\f\b\r\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\b\u0013", new Object[]{simpleSymbol3, (SimpleSymbol) new SimpleSymbol("list").readResolve(), (SimpleSymbol) new SimpleSymbol("cons").readResolve(), simpleSymbol2}, 2)}, 3);
        Lit19 = syntaxRules;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("extract-condition").readResolve();
        Lit17 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("make-compound-condition").readResolve();
        Lit16 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("condition-ref").readResolve();
        Lit15 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("condition-type-field-alist").readResolve();
        Lit13 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = simpleSymbol3;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol10 = simpleSymbol8;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol8}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("$lookup$").readResolve(), Pair.make((SimpleSymbol) new SimpleSymbol(Marker.ANY_MARKER).readResolve(), Pair.make(Pair.make((SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve(), Pair.make((SimpleSymbol) new SimpleSymbol(".type-field-alist").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 581639), (SimpleSymbol) new SimpleSymbol("as").readResolve(), (SimpleSymbol) new SimpleSymbol("<condition>").readResolve()}, 0)}, 1);
        Lit14 = syntaxRules3;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("condition-has-type?").readResolve();
        Lit12 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("make-condition").readResolve();
        Lit11 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("condition?").readResolve();
        Lit10 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("define-condition-type").readResolve();
        Lit8 = simpleSymbol14;
        Object[] objArr = {simpleSymbol14};
        SyntaxRules syntaxRules4 = syntaxRules3;
        SimpleSymbol simpleSymbol15 = simpleSymbol12;
        SimpleSymbol simpleSymbol16 = simpleSymbol14;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017-\f\u001f\f'\b\u0018\u0010\b", new Object[0], 5);
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("make-condition-type").readResolve();
        Lit7 = simpleSymbol17;
        SyntaxRules syntaxRules5 = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004É\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014)\u0011\u0018\u001c\b\u0003\t\u000b\b\u0011\u0018\u001c\b\b\u001d\u001bÁ\u0011\u0018\f!\t\u0013\u0018$\b\u0011\u0018,\u0011\u00184\b\u0011\u0018<\u0011\u0018D\b\u0003\b%\u0011\u0018\f!\t#\u0018L\b\u0011\u0018TA\u0011\u0018\\\u0011\u0018d\b\u0003\b\u0011\u0018\u001c\b\u001b", new Object[]{(SimpleSymbol) new SimpleSymbol("begin").readResolve(), (SimpleSymbol) new SimpleSymbol("define").readResolve(), simpleSymbol17, simpleSymbol2, PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 327708), (SimpleSymbol) new SimpleSymbol("and").readResolve(), PairWithPosition.make(simpleSymbol13, PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331803), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331791), simpleSymbol11, simpleSymbol, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 339996), simpleSymbol7, simpleSymbol5, simpleSymbol4}, 1)}, 5);
        Lit9 = syntaxRules5;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("condition-type?").readResolve();
        Lit6 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("message").readResolve();
        Lit5 = simpleSymbol19;
        Lit2 = PairWithPosition.make(simpleSymbol19, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 925699);
        conditions conditions = new conditions();
        $instance = conditions;
        condition$Mntype$Qu = new ModuleMethod(conditions, 2, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mncondition$Mntype = new ModuleMethod(conditions, 3, simpleSymbol17, 12291);
        define$Mncondition$Mntype = Macro.make(simpleSymbol16, syntaxRules5, conditions);
        condition$Qu = new ModuleMethod(conditions, 4, simpleSymbol13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mncondition = new ModuleMethod(conditions, 5, simpleSymbol15, -4095);
        condition$Mnhas$Mntype$Qu = new ModuleMethod(conditions, 6, simpleSymbol11, 8194);
        condition$Mntype$Mnfield$Mnalist = Macro.make(simpleSymbol10, syntaxRules4, conditions);
        condition$Mnref = new ModuleMethod(conditions, 7, simpleSymbol7, 8194);
        make$Mncompound$Mncondition = new ModuleMethod(conditions, 8, simpleSymbol6, -4095);
        extract$Mncondition = new ModuleMethod(conditions, 9, simpleSymbol5, 8194);
        condition = Macro.make(simpleSymbol4, syntaxRules2, conditions);
        $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = new ModuleMethod(conditions, 10, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        conditions.run();
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 4) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 10) {
            return super.match1(moduleMethod, obj, callContext);
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isConditionType(Object obj) {
        return obj instanceof condition.Mntype;
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 3) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            try {
                return makeConditionType((Symbol) obj, (condition.Mntype) obj2, obj3);
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-condition-type", 2, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "make-condition-type", 1, obj);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 3) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof Symbol)) {
            return -786431;
        }
        callContext.value1 = obj;
        if (!(obj2 instanceof condition.Mntype)) {
            return -786430;
        }
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    public static condition.Mntype makeConditionType(Symbol symbol, condition.Mntype mntype, Object obj) {
        if (!lists.isNull(srfi1.lsetIntersection$V(Scheme.isEq, mntype.all$Mnfields, new Object[]{obj}))) {
            misc.error$V("duplicate field name", new Object[0]);
        }
        return new condition.Mntype(symbol, mntype, obj, append.append$V(new Object[]{mntype.all$Mnfields, obj}));
    }

    static boolean isError(Object obj) {
        boolean isCondition = isCondition(obj);
        if (!isCondition) {
            return isCondition;
        }
        Object obj2 = $Amerror;
        try {
            return isConditionHasType(obj, (condition.Mntype) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "condition-has-type?", 1, obj2);
        }
    }

    static boolean isMessageCondition(Object obj) {
        boolean isCondition = isCondition(obj);
        if (!isCondition) {
            return isCondition;
        }
        Object obj2 = $Ammessage;
        try {
            return isConditionHasType(obj, (condition.Mntype) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "condition-has-type?", 1, obj2);
        }
    }

    static boolean isSeriousCondition(Object obj) {
        boolean isCondition = isCondition(obj);
        if (!isCondition) {
            return isCondition;
        }
        Object obj2 = $Amserious;
        try {
            return isConditionHasType(obj, (condition.Mntype) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "condition-has-type?", 1, obj2);
        }
    }

    static boolean isConditionSubtype(condition.Mntype mntype, condition.Mntype mntype2) {
        while (mntype != Boolean.FALSE) {
            if (mntype == mntype2) {
                return true;
            }
            mntype = (condition.Mntype) mntype.supertype;
        }
        return false;
    }

    static Object conditionTypeFieldSupertype(condition.Mntype mntype, Object obj) {
        while (mntype != Boolean.FALSE) {
            if (lists.memq(obj, mntype.fields) != Boolean.FALSE) {
                return mntype;
            }
            mntype = (condition.Mntype) mntype.supertype;
        }
        return Boolean.FALSE;
    }

    public static boolean isCondition(Object obj) {
        return obj instanceof condition;
    }

    public static condition makeCondition$V(condition.Mntype mntype, Object[] objArr) {
        Object lambda1label = lambda1label(LList.makeList(objArr, 0));
        IsEq isEq = Scheme.isEq;
        Object[] objArr2 = new Object[2];
        objArr2[0] = mntype.all$Mnfields;
        Object obj = LList.Empty;
        Object obj2 = lambda1label;
        while (obj2 != LList.Empty) {
            try {
                Pair pair = (Pair) obj2;
                Object cdr = pair.getCdr();
                obj = Pair.make(lists.car.apply1(pair.getCar()), obj);
                obj2 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj2);
            }
        }
        objArr2[1] = LList.reverseInPlace(obj);
        if (srfi1.lset$Eq$V(isEq, objArr2) == Boolean.FALSE) {
            misc.error$V("condition fields don't match condition type", new Object[0]);
        }
        return new condition(LList.list1(lists.cons(mntype, lambda1label)));
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 5) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 8) {
            return super.matchN(moduleMethod, objArr, callContext);
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Object lambda1label(Object obj) {
        if (lists.isNull(obj)) {
            return LList.Empty;
        }
        return lists.cons(lists.cons(lists.car.apply1(obj), lists.cadr.apply1(obj)), lambda1label(lists.cddr.apply1(obj)));
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 6) {
            callContext.value1 = obj;
            if (!(obj2 instanceof condition.Mntype)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 7) {
            if (i != 9) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            if (!(obj instanceof condition)) {
                return -786431;
            }
            callContext.value1 = obj;
            if (!(obj2 instanceof condition.Mntype)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (!(obj instanceof condition)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static boolean isConditionHasType(Object obj, condition.Mntype mntype) {
        Object conditionTypes = conditionTypes(obj);
        while (true) {
            Object apply1 = lists.car.apply1(conditionTypes);
            try {
                boolean isConditionSubtype = isConditionSubtype((condition.Mntype) apply1, mntype);
                if (isConditionSubtype) {
                    return isConditionSubtype;
                }
                conditionTypes = lists.cdr.apply1(conditionTypes);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-subtype?", 0, apply1);
            }
        }
    }

    public static Object conditionRef(condition condition2, Object obj) {
        return typeFieldAlistRef(condition2.type$Mnfield$Mnalist, obj);
    }

    static Object typeFieldAlistRef(Object obj, Object obj2) {
        while (!lists.isNull(obj)) {
            Object assq = lists.assq(obj2, lists.cdr.apply1(lists.car.apply1(obj)));
            if (assq != Boolean.FALSE) {
                return lists.cdr.apply1(assq);
            }
            obj = lists.cdr.apply1(obj);
        }
        return misc.error$V("type-field-alist-ref: field not found", new Object[]{obj, obj2});
    }

    public static condition makeCompoundCondition$V(Object obj, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        Apply apply = Scheme.apply;
        append append = append.append;
        Object cons = lists.cons(obj, makeList);
        Object obj2 = LList.Empty;
        while (cons != LList.Empty) {
            try {
                Pair pair = (Pair) cons;
                Object cdr = pair.getCdr();
                obj2 = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, pair.getCar()), obj2);
                cons = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, cons);
            }
        }
        return new condition(apply.apply2(append, LList.reverseInPlace(obj2)));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 5) {
            condition.Mntype mntype = objArr[0];
            try {
                condition.Mntype mntype2 = mntype;
                int length = objArr.length - 1;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return makeCondition$V(mntype2, objArr2);
                    }
                    objArr2[length] = objArr[length + 1];
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-condition", 1, (Object) mntype);
            }
        } else if (i != 8) {
            return super.applyN(moduleMethod, objArr);
        } else {
            Object obj = objArr[0];
            int length2 = objArr.length - 1;
            Object[] objArr3 = new Object[length2];
            while (true) {
                length2--;
                if (length2 < 0) {
                    return makeCompoundCondition$V(obj, objArr3);
                }
                objArr3[length2] = objArr[length2 + 1];
            }
        }
    }

    public static condition extractCondition(condition condition2, condition.Mntype mntype) {
        frame frame2 = new frame();
        frame2.type = mntype;
        Object find = srfi1.find(frame2.lambda$Fn1, condition2.type$Mnfield$Mnalist);
        if (find == Boolean.FALSE) {
            misc.error$V("extract-condition: invalid condition type", new Object[]{condition2, frame2.type});
        }
        condition.Mntype mntype2 = frame2.type;
        Object obj = frame2.type.all$Mnfields;
        Object obj2 = LList.Empty;
        while (obj != LList.Empty) {
            try {
                Pair pair = (Pair) obj;
                Object cdr = pair.getCdr();
                obj2 = Pair.make(lists.assq(pair.getCar(), lists.cdr.apply1(find)), obj2);
                obj = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj);
            }
        }
        return new condition(LList.list1(lists.cons(mntype2, LList.reverseInPlace(obj2))));
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 6) {
            try {
                return isConditionHasType(obj, (condition.Mntype) obj2) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-has-type?", 2, obj2);
            }
        } else if (i == 7) {
            try {
                return conditionRef((condition) obj, obj2);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "condition-ref", 1, obj);
            }
        } else if (i != 9) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    return extractCondition((condition) obj, (condition.Mntype) obj2);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "extract-condition", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "extract-condition", 1, obj);
            }
        }
    }

    /* compiled from: conditions.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        condition.Mntype type;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm:166");
            this.lambda$Fn1 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 1) {
                return lambda2(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
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
        public boolean lambda2(Object obj) {
            Object apply1 = lists.car.apply1(obj);
            try {
                return conditions.isConditionSubtype((condition.Mntype) apply1, this.type);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-subtype?", 0, apply1);
            }
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return isConditionType(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i == 4) {
            return isCondition(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i != 10) {
            return super.apply1(moduleMethod, obj);
        }
        return typeFieldAlist$To$Condition(obj);
    }

    public static condition typeFieldAlist$To$Condition(Object obj) {
        Object obj2 = LList.Empty;
        Object obj3 = obj;
        while (obj3 != LList.Empty) {
            try {
                Pair pair = (Pair) obj3;
                Object cdr = pair.getCdr();
                Object car = pair.getCar();
                Object apply1 = lists.car.apply1(car);
                Object obj4 = ((condition.Mntype) lists.car.apply1(car)).all$Mnfields;
                Object obj5 = LList.Empty;
                while (obj4 != LList.Empty) {
                    try {
                        Pair pair2 = (Pair) obj4;
                        Object cdr2 = pair2.getCdr();
                        Object car2 = pair2.getCar();
                        Object assq = lists.assq(car2, lists.cdr.apply1(car));
                        if (assq == Boolean.FALSE) {
                            assq = lists.cons(car2, typeFieldAlistRef(obj, car2));
                        }
                        obj5 = Pair.make(assq, obj5);
                        obj4 = cdr2;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, obj4);
                    }
                }
                obj2 = Pair.make(lists.cons(apply1, LList.reverseInPlace(obj5)), obj2);
                obj3 = cdr;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, obj3);
            }
        }
        return new condition(LList.reverseInPlace(obj2));
    }

    static Object conditionTypes(Object obj) {
        Object obj2 = ((condition) obj).type$Mnfield$Mnalist;
        Object obj3 = LList.Empty;
        while (obj2 != LList.Empty) {
            try {
                Pair pair = (Pair) obj2;
                Object cdr = pair.getCdr();
                obj3 = Pair.make(lists.car.apply1(pair.getCar()), obj3);
                obj2 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj2);
            }
        }
        return LList.reverseInPlace(obj3);
    }

    static Object checkConditionTypeFieldAlist(Object obj) {
        boolean isConditionSubtype;
        Object obj2 = obj;
        while (!lists.isNull(obj2)) {
            Object apply1 = lists.car.apply1(obj2);
            Object apply12 = lists.car.apply1(apply1);
            try {
                condition.Mntype mntype = (condition.Mntype) apply12;
                Object apply13 = lists.cdr.apply1(apply1);
                Object obj3 = LList.Empty;
                while (apply13 != LList.Empty) {
                    try {
                        Pair pair = (Pair) apply13;
                        Object cdr = pair.getCdr();
                        obj3 = Pair.make(lists.car.apply1(pair.getCar()), obj3);
                        apply13 = cdr;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, apply13);
                    }
                }
                LList reverseInPlace = LList.reverseInPlace(obj3);
                Object obj4 = mntype.all$Mnfields;
                Object lsetDifference$V = srfi1.lsetDifference$V(Scheme.isEq, obj4, new Object[]{reverseInPlace});
                while (lsetDifference$V != LList.Empty) {
                    try {
                        Pair pair2 = (Pair) lsetDifference$V;
                        Object car = pair2.getCar();
                        Object conditionTypeFieldSupertype = conditionTypeFieldSupertype(mntype, car);
                        Object obj5 = obj;
                        while (true) {
                            Object apply14 = lists.car.apply1(lists.car.apply1(obj5));
                            try {
                                try {
                                    isConditionSubtype = isConditionSubtype((condition.Mntype) apply14, (condition.Mntype) conditionTypeFieldSupertype);
                                    if (isConditionSubtype) {
                                        break;
                                    }
                                    obj5 = lists.cdr.apply1(obj5);
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "condition-subtype?", 1, conditionTypeFieldSupertype);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "condition-subtype?", 0, apply14);
                            }
                        }
                        if (!isConditionSubtype) {
                            misc.error$V("missing field in condition construction", new Object[]{mntype, car});
                        }
                        lsetDifference$V = pair2.getCdr();
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "arg0", -2, lsetDifference$V);
                    }
                }
                obj2 = lists.cdr.apply1(obj2);
            } catch (ClassCastException e5) {
                throw new WrongType(e5, CommonProperties.TYPE, -2, apply12);
            }
        }
        return Values.empty;
    }

    static Object conditionMessage(Object obj) {
        try {
            condition condition2 = (condition) obj;
            Object obj2 = $Ammessage;
            try {
                return conditionRef(extractCondition(condition2, (condition.Mntype) obj2), Lit5);
            } catch (ClassCastException e) {
                throw new WrongType(e, "extract-condition", 1, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "extract-condition", 0, obj);
        }
    }
}
