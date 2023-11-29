package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

/* compiled from: genwrite.scm */
public class genwrite extends ModuleBody {
    public static final genwrite $instance;
    static final Char Lit0 = Char.make(10);
    static final IntNum Lit1 = IntNum.make(0);
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("and").readResolve());
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("or").readResolve());
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("let").readResolve());
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("begin").readResolve());
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("do").readResolve());
    static final IntNum Lit15 = IntNum.make(7);
    static final IntNum Lit16 = IntNum.make(8);
    static final IntNum Lit17 = IntNum.make(1);
    static final IntNum Lit18 = IntNum.make(50);
    static final IntNum Lit19 = IntNum.make(2);
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("lambda").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("pp-expr").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("pp-expr-list").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("pp-LAMBDA").readResolve());
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("pp-IF").readResolve());
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("pp-COND").readResolve());
    static final SimpleSymbol Lit25 = ((SimpleSymbol) new SimpleSymbol("pp-CASE").readResolve());
    static final SimpleSymbol Lit26 = ((SimpleSymbol) new SimpleSymbol("pp-AND").readResolve());
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("pp-LET").readResolve());
    static final SimpleSymbol Lit28 = ((SimpleSymbol) new SimpleSymbol("pp-BEGIN").readResolve());
    static final SimpleSymbol Lit29 = ((SimpleSymbol) new SimpleSymbol("pp-DO").readResolve());
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("let*").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve());
    static final SimpleSymbol Lit31 = ((SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve());
    static final SimpleSymbol Lit32 = ((SimpleSymbol) new SimpleSymbol(LispLanguage.unquote_sym).readResolve());
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol(LispLanguage.unquotesplicing_sym).readResolve());
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("letrec").readResolve());
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("define").readResolve());
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("if").readResolve());
    static final SimpleSymbol Lit7 = ((SimpleSymbol) new SimpleSymbol("set!").readResolve());
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("cond").readResolve());
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("case").readResolve());
    public static final ModuleMethod generic$Mnwrite;
    public static final ModuleMethod reverse$Mnstring$Mnappend;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("reverse-string-append").readResolve();
        Lit35 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("generic-write").readResolve();
        Lit34 = simpleSymbol2;
        genwrite genwrite = new genwrite();
        $instance = genwrite;
        generic$Mnwrite = new ModuleMethod(genwrite, 12, simpleSymbol2, 16388);
        reverse$Mnstring$Mnappend = new ModuleMethod(genwrite, 13, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        genwrite.run();
    }

    public genwrite() {
        ModuleInfo.register(this);
    }

    public static Object genericWrite(Object obj, Object obj2, Object obj3, Object obj4) {
        frame frame2 = new frame();
        frame2.display$Qu = obj2;
        frame2.width = obj3;
        frame2.output = obj4;
        if (frame2.width == Boolean.FALSE) {
            return frame2.lambda5wr(obj, Lit1);
        }
        CharSequence makeString = strings.makeString(1, Lit0);
        IntNum intNum = Lit1;
        frame0 frame02 = new frame0();
        frame02.staticLink = frame2;
        Procedure procedure = frame02.pp$Mnexpr;
        Procedure procedure2 = frame02.pp$Mnexpr$Mnlist;
        Procedure procedure3 = frame02.pp$MnLAMBDA;
        Procedure procedure4 = frame02.pp$MnIF;
        Procedure procedure5 = frame02.pp$MnCOND;
        Procedure procedure6 = frame02.pp$MnCASE;
        Procedure procedure7 = frame02.pp$MnAND;
        Procedure procedure8 = frame02.pp$MnLET;
        Procedure procedure9 = frame02.pp$MnBEGIN;
        frame02.pp$MnDO = frame02.pp$MnDO;
        frame02.pp$MnBEGIN = procedure9;
        frame02.pp$MnLET = procedure8;
        frame02.pp$MnAND = procedure7;
        frame02.pp$MnCASE = procedure6;
        frame02.pp$MnCOND = procedure5;
        frame02.pp$MnIF = procedure4;
        frame02.pp$MnLAMBDA = procedure3;
        frame02.pp$Mnexpr$Mnlist = procedure2;
        frame02.pp$Mnexpr = procedure;
        return frame2.lambda4out(makeString, frame02.lambda7pr(obj, intNum, intNum, frame02.pp$Mnexpr));
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        return moduleMethod.selector == 12 ? genericWrite(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 12) {
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

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    /* compiled from: genwrite.scm */
    public class frame extends ModuleBody {
        Object display$Qu;
        Object output;
        Object width;

        public static Object lambda1isReadMacro(Object obj) {
            Object apply2;
            Object apply22;
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            Object apply23 = Scheme.isEqv.apply2(apply1, genwrite.Lit30);
            if (apply23 == Boolean.FALSE ? !((apply2 = Scheme.isEqv.apply2(apply1, genwrite.Lit31)) == Boolean.FALSE ? (apply22 = Scheme.isEqv.apply2(apply1, genwrite.Lit32)) == Boolean.FALSE ? Scheme.isEqv.apply2(apply1, genwrite.Lit33) == Boolean.FALSE : apply22 == Boolean.FALSE : apply2 == Boolean.FALSE) : apply23 != Boolean.FALSE) {
                boolean isPair = lists.isPair(apply12);
                if (!isPair ? isPair : lists.isNull(lists.cdr.apply1(apply12))) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }

        public static Object lambda2readMacroBody(Object obj) {
            return lists.cadr.apply1(obj);
        }

        public static Object lambda3readMacroPrefix(Object obj) {
            Object apply1 = lists.car.apply1(obj);
            lists.cdr.apply1(obj);
            if (Scheme.isEqv.apply2(apply1, genwrite.Lit30) != Boolean.FALSE) {
                return "'";
            }
            if (Scheme.isEqv.apply2(apply1, genwrite.Lit31) != Boolean.FALSE) {
                return "`";
            }
            if (Scheme.isEqv.apply2(apply1, genwrite.Lit32) != Boolean.FALSE) {
                return ",";
            }
            if (Scheme.isEqv.apply2(apply1, genwrite.Lit33) != Boolean.FALSE) {
                return ",@";
            }
            return Values.empty;
        }

        public Object lambda4out(Object obj, Object obj2) {
            if (obj2 == Boolean.FALSE) {
                return obj2;
            }
            Object apply2 = Scheme.applyToArgs.apply2(this.output, obj);
            if (apply2 == Boolean.FALSE) {
                return apply2;
            }
            try {
                return AddOp.$Pl.apply2(obj2, Integer.valueOf(strings.stringLength((CharSequence) obj)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, obj);
            }
        }

        public Object lambda5wr(Object obj, Object obj2) {
            if (lists.isPair(obj)) {
                if (lambda1isReadMacro(obj) != Boolean.FALSE) {
                    return lambda5wr(lambda2readMacroBody(obj), lambda4out(lambda3readMacroPrefix(obj), obj2));
                }
            } else if (!lists.isNull(obj)) {
                if (vectors.isVector(obj)) {
                    try {
                        obj = vectors.vector$To$List((FVector) obj);
                        obj2 = lambda4out("#", obj2);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "vector->list", 1, obj);
                    }
                } else {
                    Object[] objArr = new Object[2];
                    objArr[0] = this.display$Qu != Boolean.FALSE ? "~a" : "~s";
                    objArr[1] = obj;
                    return lambda4out(Format.formatToString(0, objArr), obj2);
                }
            }
            if (!lists.isPair(obj)) {
                return lambda4out("()", obj2);
            }
            Object apply1 = lists.cdr.apply1(obj);
            if (obj2 != Boolean.FALSE) {
                obj2 = lambda5wr(lists.car.apply1(obj), lambda4out("(", obj2));
            }
            while (obj2 != Boolean.FALSE) {
                if (lists.isPair(apply1)) {
                    Object apply12 = lists.cdr.apply1(apply1);
                    obj2 = lambda5wr(lists.car.apply1(apply1), lambda4out(" ", obj2));
                    apply1 = apply12;
                } else if (lists.isNull(apply1)) {
                    return lambda4out(")", obj2);
                } else {
                    return lambda4out(")", lambda5wr(apply1, lambda4out(" . ", obj2)));
                }
            }
            return obj2;
        }
    }

    /* compiled from: genwrite.scm */
    public class frame0 extends ModuleBody {
        Procedure pp$MnAND = new ModuleMethod(this, 8, genwrite.Lit26, 12291);
        Procedure pp$MnBEGIN = new ModuleMethod(this, 10, genwrite.Lit28, 12291);
        Procedure pp$MnCASE = new ModuleMethod(this, 7, genwrite.Lit25, 12291);
        Procedure pp$MnCOND = new ModuleMethod(this, 6, genwrite.Lit24, 12291);
        Procedure pp$MnDO = new ModuleMethod(this, 11, genwrite.Lit29, 12291);
        Procedure pp$MnIF = new ModuleMethod(this, 5, genwrite.Lit23, 12291);
        Procedure pp$MnLAMBDA = new ModuleMethod(this, 4, genwrite.Lit22, 12291);
        Procedure pp$MnLET = new ModuleMethod(this, 9, genwrite.Lit27, 12291);
        Procedure pp$Mnexpr = new ModuleMethod(this, 2, genwrite.Lit20, 12291);
        Procedure pp$Mnexpr$Mnlist = new ModuleMethod(this, 3, genwrite.Lit21, 12291);
        frame staticLink;

        public Object lambda6indent(Object obj, Object obj2) {
            if (obj2 == Boolean.FALSE) {
                return obj2;
            }
            if (Scheme.numLss.apply2(obj, obj2) != Boolean.FALSE) {
                Object lambda4out = this.staticLink.lambda4out(strings.makeString(1, genwrite.Lit0), obj2);
                if (lambda4out == Boolean.FALSE) {
                    return lambda4out;
                }
                obj2 = genwrite.Lit1;
            } else {
                obj = AddOp.$Mn.apply2(obj, obj2);
            }
            while (Scheme.numGrt.apply2(obj, genwrite.Lit1) != Boolean.FALSE) {
                if (Scheme.numGrt.apply2(obj, genwrite.Lit15) != Boolean.FALSE) {
                    obj = AddOp.$Mn.apply2(obj, genwrite.Lit16);
                    obj2 = this.staticLink.lambda4out("        ", obj2);
                } else {
                    try {
                        return this.staticLink.lambda4out(strings.substring("        ", 0, ((Number) obj).intValue()), obj2);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, obj);
                    }
                }
            }
            return obj2;
        }

        public Object lambda7pr(Object obj, Object obj2, Object obj3, Object obj4) {
            frame1 frame1 = new frame1();
            frame1.staticLink = this;
            boolean isPair = lists.isPair(obj);
            if (!isPair ? !vectors.isVector(obj) : !isPair) {
                return this.staticLink.lambda5wr(obj, obj2);
            }
            LList lList = LList.Empty;
            frame1.left = numbers.min(AddOp.$Pl.apply2(AddOp.$Mn.apply2(AddOp.$Mn.apply2(this.staticLink.width, obj2), obj3), genwrite.Lit17), genwrite.Lit18);
            frame1.result = lList;
            genwrite.genericWrite(obj, this.staticLink.display$Qu, Boolean.FALSE, frame1.lambda$Fn1);
            if (Scheme.numGrt.apply2(frame1.left, genwrite.Lit1) != Boolean.FALSE) {
                return this.staticLink.lambda4out(genwrite.reverseStringAppend(frame1.result), obj2);
            }
            if (lists.isPair(obj)) {
                return Scheme.applyToArgs.apply4(obj4, obj, obj2, obj3);
            }
            try {
                return lambda10ppList(vectors.vector$To$List((FVector) obj), this.staticLink.lambda4out("#", obj2), obj3, this.pp$Mnexpr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "vector->list", 1, obj);
            }
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 2:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 3:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 4:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 5:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 6:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 7:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 8:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                default:
                    return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
        }

        public Object lambda8ppExpr(Object obj, Object obj2, Object obj3) {
            Object obj4;
            Object apply2;
            Object apply22;
            if (frame.lambda1isReadMacro(obj) != Boolean.FALSE) {
                return lambda7pr(frame.lambda2readMacroBody(obj), this.staticLink.lambda4out(frame.lambda3readMacroPrefix(obj), obj2), obj3, this.pp$Mnexpr);
            }
            Object apply1 = lists.car.apply1(obj);
            if (!misc.isSymbol(apply1)) {
                return lambda10ppList(obj, obj2, obj3, this.pp$Mnexpr);
            }
            Object apply23 = Scheme.isEqv.apply2(apply1, genwrite.Lit2);
            if (apply23 == Boolean.FALSE ? (apply2 = Scheme.isEqv.apply2(apply1, genwrite.Lit3)) == Boolean.FALSE ? (apply22 = Scheme.isEqv.apply2(apply1, genwrite.Lit4)) == Boolean.FALSE ? Scheme.isEqv.apply2(apply1, genwrite.Lit5) == Boolean.FALSE : apply22 == Boolean.FALSE : apply2 == Boolean.FALSE : apply23 == Boolean.FALSE) {
                Object apply24 = Scheme.isEqv.apply2(apply1, genwrite.Lit6);
                if (apply24 == Boolean.FALSE ? Scheme.isEqv.apply2(apply1, genwrite.Lit7) != Boolean.FALSE : apply24 != Boolean.FALSE) {
                    obj4 = this.pp$MnIF;
                } else if (Scheme.isEqv.apply2(apply1, genwrite.Lit8) != Boolean.FALSE) {
                    obj4 = this.pp$MnCOND;
                } else if (Scheme.isEqv.apply2(apply1, genwrite.Lit9) != Boolean.FALSE) {
                    obj4 = this.pp$MnCASE;
                } else {
                    Object apply25 = Scheme.isEqv.apply2(apply1, genwrite.Lit10);
                    if (apply25 == Boolean.FALSE ? Scheme.isEqv.apply2(apply1, genwrite.Lit11) != Boolean.FALSE : apply25 != Boolean.FALSE) {
                        obj4 = this.pp$MnAND;
                    } else if (Scheme.isEqv.apply2(apply1, genwrite.Lit12) != Boolean.FALSE) {
                        obj4 = this.pp$MnLET;
                    } else if (Scheme.isEqv.apply2(apply1, genwrite.Lit13) != Boolean.FALSE) {
                        obj4 = this.pp$MnBEGIN;
                    } else if (Scheme.isEqv.apply2(apply1, genwrite.Lit14) != Boolean.FALSE) {
                        obj4 = this.pp$MnDO;
                    } else {
                        obj4 = Boolean.FALSE;
                    }
                }
            } else {
                obj4 = this.pp$MnLAMBDA;
            }
            if (obj4 != Boolean.FALSE) {
                return Scheme.applyToArgs.apply4(obj4, obj, obj2, obj3);
            }
            try {
                if (strings.stringLength(misc.symbol$To$String((Symbol) apply1)) <= 5) {
                    return lambda9ppCall(obj, obj2, obj3, this.pp$Mnexpr);
                }
                return lambda12ppGeneral(obj, obj2, obj3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, apply1);
            }
        }

        public Object lambda9ppCall(Object obj, Object obj2, Object obj3, Object obj4) {
            Object lambda5wr = this.staticLink.lambda5wr(lists.car.apply1(obj), this.staticLink.lambda4out("(", obj2));
            if (obj2 == Boolean.FALSE) {
                return obj2;
            }
            return lambda11ppDown(lists.cdr.apply1(obj), lambda5wr, AddOp.$Pl.apply2(lambda5wr, genwrite.Lit17), obj3, obj4);
        }

        public Object lambda10ppList(Object obj, Object obj2, Object obj3, Object obj4) {
            Object lambda4out = this.staticLink.lambda4out("(", obj2);
            return lambda11ppDown(obj, lambda4out, lambda4out, obj3, obj4);
        }

        public Object lambda11ppDown(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            Object obj6;
            while (obj2 != Boolean.FALSE) {
                if (lists.isPair(obj)) {
                    Object apply1 = lists.cdr.apply1(obj);
                    obj2 = lambda7pr(lists.car.apply1(obj), lambda6indent(obj3, obj2), lists.isNull(apply1) ? AddOp.$Pl.apply2(obj4, genwrite.Lit17) : genwrite.Lit1, obj5);
                    obj = apply1;
                } else {
                    if (lists.isNull(obj)) {
                        obj6 = this.staticLink.lambda4out(")", obj2);
                    } else {
                        frame frame = this.staticLink;
                        obj6 = frame.lambda4out(")", lambda7pr(obj, lambda6indent(obj3, frame.lambda4out(".", lambda6indent(obj3, obj2))), AddOp.$Pl.apply2(obj4, genwrite.Lit17), obj5));
                    }
                    return obj6;
                }
            }
            return obj2;
        }

        public Object lambda12ppGeneral(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
            Object obj8;
            Object obj9;
            Object obj10;
            Object obj11;
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            frame frame = this.staticLink;
            Object lambda5wr = frame.lambda5wr(apply1, frame.lambda4out("(", obj2));
            if (obj4 == Boolean.FALSE ? obj4 == Boolean.FALSE : !lists.isPair(apply12)) {
                obj9 = AddOp.$Pl.apply2(obj2, genwrite.Lit19);
                obj8 = AddOp.$Pl.apply2(lambda5wr, genwrite.Lit17);
            } else {
                Object apply13 = lists.car.apply1(apply12);
                apply12 = lists.cdr.apply1(apply12);
                frame frame2 = this.staticLink;
                lambda5wr = frame2.lambda5wr(apply13, frame2.lambda4out(" ", lambda5wr));
                obj9 = AddOp.$Pl.apply2(obj2, genwrite.Lit19);
                obj8 = AddOp.$Pl.apply2(lambda5wr, genwrite.Lit17);
            }
            Object obj12 = obj9;
            if (obj5 == Boolean.FALSE ? obj5 != Boolean.FALSE : lists.isPair(apply12)) {
                Object apply14 = lists.car.apply1(apply12);
                apply12 = lists.cdr.apply1(apply12);
                lambda5wr = lambda7pr(apply14, lambda6indent(obj8, lambda5wr), lists.isNull(apply12) ? AddOp.$Pl.apply2(obj3, genwrite.Lit17) : genwrite.Lit1, obj5);
            }
            if (obj6 == Boolean.FALSE ? obj6 == Boolean.FALSE : !lists.isPair(apply12)) {
                obj11 = apply12;
                obj10 = lambda5wr;
            } else {
                Object apply15 = lists.car.apply1(apply12);
                Object apply16 = lists.cdr.apply1(apply12);
                obj11 = apply16;
                obj10 = lambda7pr(apply15, lambda6indent(obj8, lambda5wr), lists.isNull(apply16) ? AddOp.$Pl.apply2(obj3, genwrite.Lit17) : genwrite.Lit1, obj6);
            }
            return lambda11ppDown(obj11, obj10, obj12, obj3, obj7);
        }

        public Object lambda13ppExprList(Object obj, Object obj2, Object obj3) {
            return lambda10ppList(obj, obj2, obj3, this.pp$Mnexpr);
        }

        public Object lambda14pp$MnLAMBDA(Object obj, Object obj2, Object obj3) {
            return lambda12ppGeneral(obj, obj2, obj3, Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda15pp$MnIF(Object obj, Object obj2, Object obj3) {
            return lambda12ppGeneral(obj, obj2, obj3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda16pp$MnCOND(Object obj, Object obj2, Object obj3) {
            return lambda9ppCall(obj, obj2, obj3, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda17pp$MnCASE(Object obj, Object obj2, Object obj3) {
            return lambda12ppGeneral(obj, obj2, obj3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda18pp$MnAND(Object obj, Object obj2, Object obj3) {
            return lambda9ppCall(obj, obj2, obj3, this.pp$Mnexpr);
        }

        public Object lambda19pp$MnLET(Object obj, Object obj2, Object obj3) {
            Object apply1 = lists.cdr.apply1(obj);
            boolean isPair = lists.isPair(apply1);
            if (isPair) {
                isPair = misc.isSymbol(lists.car.apply1(apply1));
            }
            return lambda12ppGeneral(obj, obj2, obj3, isPair ? Boolean.TRUE : Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda20pp$MnBEGIN(Object obj, Object obj2, Object obj3) {
            return lambda12ppGeneral(obj, obj2, obj3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            switch (moduleMethod.selector) {
                case 2:
                    return lambda8ppExpr(obj, obj2, obj3);
                case 3:
                    return lambda13ppExprList(obj, obj2, obj3);
                case 4:
                    return lambda14pp$MnLAMBDA(obj, obj2, obj3);
                case 5:
                    return lambda15pp$MnIF(obj, obj2, obj3);
                case 6:
                    return lambda16pp$MnCOND(obj, obj2, obj3);
                case 7:
                    return lambda17pp$MnCASE(obj, obj2, obj3);
                case 8:
                    return lambda18pp$MnAND(obj, obj2, obj3);
                case 9:
                    return lambda19pp$MnLET(obj, obj2, obj3);
                case 10:
                    return lambda20pp$MnBEGIN(obj, obj2, obj3);
                case 11:
                    return lambda21pp$MnDO(obj, obj2, obj3);
                default:
                    return super.apply3(moduleMethod, obj, obj2, obj3);
            }
        }

        public Object lambda21pp$MnDO(Object obj, Object obj2, Object obj3) {
            Boolean bool = Boolean.FALSE;
            Procedure procedure = this.pp$Mnexpr$Mnlist;
            return lambda12ppGeneral(obj, obj2, obj3, bool, procedure, procedure, this.pp$Mnexpr);
        }
    }

    /* compiled from: genwrite.scm */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object left;
        Object result;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/genwrite.scm:72");
            this.lambda$Fn1 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 1) {
                return lambda22(obj) ? Boolean.TRUE : Boolean.FALSE;
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
        public boolean lambda22(Object obj) {
            this.result = lists.cons(obj, this.result);
            try {
                this.left = AddOp.$Mn.apply2(this.left, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                return ((Boolean) Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, obj);
            }
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 13 ? reverseStringAppend(obj) : super.apply1(moduleMethod, obj);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 13) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static Object lambda23revStringAppend(Object obj, Object obj2) {
        if (lists.isPair(obj)) {
            Object apply1 = lists.car.apply1(obj);
            try {
                int stringLength = strings.stringLength((CharSequence) apply1);
                Object lambda23revStringAppend = lambda23revStringAppend(lists.cdr.apply1(obj), AddOp.$Pl.apply2(obj2, Integer.valueOf(stringLength)));
                Object obj3 = Lit1;
                try {
                    Object apply2 = AddOp.$Mn.apply2(AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength((CharSequence) lambda23revStringAppend)), obj2), Integer.valueOf(stringLength));
                    while (Scheme.numLss.apply2(obj3, Integer.valueOf(stringLength)) != Boolean.FALSE) {
                        try {
                            try {
                                try {
                                    try {
                                        strings.stringSet$Ex((CharSeq) lambda23revStringAppend, ((Number) apply2).intValue(), strings.stringRef((CharSequence) apply1, ((Number) obj3).intValue()));
                                        AddOp addOp = AddOp.$Pl;
                                        IntNum intNum = Lit17;
                                        obj3 = addOp.apply2(obj3, intNum);
                                        apply2 = AddOp.$Pl.apply2(apply2, intNum);
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "string-ref", 2, obj3);
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "string-ref", 1, apply1);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-set!", 2, apply2);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-set!", 1, lambda23revStringAppend);
                        }
                    }
                    return lambda23revStringAppend;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-length", 1, lambda23revStringAppend);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-length", 1, apply1);
            }
        } else {
            try {
                return strings.makeString(((Number) obj2).intValue());
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "make-string", 1, obj2);
            }
        }
    }

    public static Object reverseStringAppend(Object obj) {
        return lambda23revStringAppend(obj, Lit1);
    }
}
