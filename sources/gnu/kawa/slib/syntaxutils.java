package gnu.kawa.slib;

import com.microsoft.appcenter.Constants;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Convert;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.SourceMessages;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import org.jose4j.jwt.ReservedClaimNames;

/* compiled from: syntaxutils.scm */
public class syntaxutils extends ModuleBody {
    public static final Macro $Prvt$$Ex;
    public static final Macro $Prvt$typecase$Pc;
    public static final syntaxutils $instance;
    static final Keyword Lit0 = Keyword.make("env");
    static final PairWithPosition Lit1;
    static final PairWithPosition Lit10;
    static final PairWithPosition Lit11;
    static final PairWithPosition Lit12 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 634896);
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2 = Keyword.make("lang");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final PairWithPosition Lit3 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("set").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 368647);
    static final PairWithPosition Lit4;
    static final PairWithPosition Lit5;
    static final PairWithPosition Lit6 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("if").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 417799);
    static final IntNum Lit7 = IntNum.make(0);
    static final IntNum Lit8 = IntNum.make(1);
    static final PairWithPosition Lit9;
    public static final ModuleMethod expand;

    /* compiled from: syntaxutils.scm */
    public class frame extends ModuleBody {
        LList pack;
    }

    /* compiled from: syntaxutils.scm */
    public class frame0 extends ModuleBody {
        LList pack;
    }

    /* compiled from: syntaxutils.scm */
    public class frame1 extends ModuleBody {
        LList pack;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit26 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("as").readResolve();
        Lit25 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit24 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit23 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("cond").readResolve();
        Lit22 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit21 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit20 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit19 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("eql").readResolve();
        Lit18 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("expand").readResolve();
        Lit17 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("!").readResolve();
        Lit15 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = simpleSymbol10;
        SimpleSymbol simpleSymbol13 = simpleSymbol2;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol11}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b)\u0011\u0018\f\b\u0003\b\u0015\u0013", new Object[]{(SimpleSymbol) new SimpleSymbol("invoke").readResolve(), simpleSymbol8}, 1)}, 3);
        Lit16 = syntaxRules;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("typecase%").readResolve();
        Lit13 = simpleSymbol14;
        SyntaxRules syntaxRules2 = syntaxRules;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol14, simpleSymbol9, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{Boolean.TRUE}, 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{simpleSymbol6}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol9}, 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004yY\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\b\u000b\b\u0015\u0013\b\u0011\u0018\u001c\b\u0011\u0018$\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol5, (SimpleSymbol) new SimpleSymbol("eqv?").readResolve(), simpleSymbol8, simpleSymbol3, simpleSymbol14}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol7}, 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\t\u0003)\t\u000b\b\u0015\u0013\b\u001d\u001b", new Object[]{simpleSymbol14}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007l<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol7}, 4), "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0011\u0018\u0014\u0011\b\u0003\b\u0011\u0018\u001c\b\u0015\u0013\b\u0011\u0018$\t\u0003I\r\t\u000b\b\u0011\u0018\f\b\u0003\b\u0011\u0018,\b\u0011\u0018$\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol4, (SimpleSymbol) new SimpleSymbol("f").readResolve(), simpleSymbol, simpleSymbol6, simpleSymbol14, Boolean.TRUE}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004ñ9\u0011\u0018\f\t\u0003\b\u000b\b\u0011\u0018\u0014Q\b\t\u0003\u0011\u0018\u001c\t\u000b\b\u0003\b\u0011\u0018$\b\u0015\u0013\b\u0011\u0018,\b\u0011\u00184\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol5, (SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve(), simpleSymbol4, (SimpleSymbol) new SimpleSymbol("::").readResolve(), simpleSymbol6, simpleSymbol3, simpleSymbol14}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\u0011\u0018,\b\u0003", new Object[]{(SimpleSymbol) new SimpleSymbol("error").readResolve(), "typecase% failed", simpleSymbol11, (SimpleSymbol) new SimpleSymbol("getClass").readResolve(), simpleSymbol13, (SimpleSymbol) new SimpleSymbol("<object>").readResolve()}, 0)}, 4);
        Lit14 = syntaxRules3;
        Lit11 = PairWithPosition.make(simpleSymbol13, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 626704);
        Lit10 = PairWithPosition.make(simpleSymbol8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 552972);
        Lit9 = PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 479236);
        Lit5 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 409627);
        Lit4 = PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 376839);
        Lit1 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 278557);
        syntaxutils syntaxutils = new syntaxutils();
        $instance = syntaxutils;
        $Prvt$typecase$Pc = Macro.make(simpleSymbol14, syntaxRules3, syntaxutils);
        $Prvt$$Ex = Macro.make(simpleSymbol11, syntaxRules2, syntaxutils);
        expand = new ModuleMethod(syntaxutils, 1, simpleSymbol12, -4095);
        syntaxutils.run();
    }

    public syntaxutils() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public static Object expand$V(Object obj, Object[] objArr) {
        Keyword keyword = Lit0;
        Object searchForKeyword = Keyword.searchForKeyword(objArr, 0, keyword);
        if (searchForKeyword == Special.dfault) {
            searchForKeyword = misc.interactionEnvironment();
        }
        return unrewrite(rewriteForm$V(Quote.append$V(new Object[]{Lit1, Quote.consX$V(new Object[]{obj, LList.Empty})}), new Object[]{keyword, searchForKeyword}));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 1) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return expand$V(obj, objArr2);
            }
            objArr2[length] = objArr[length + 1];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    static Expression rewriteForm$V(Object obj, Object[] objArr) {
        Object searchForKeyword = Keyword.searchForKeyword(objArr, 0, Lit2);
        if (searchForKeyword == Special.dfault) {
            searchForKeyword = Language.getDefaultLanguage();
        }
        Object searchForKeyword2 = Keyword.searchForKeyword(objArr, 0, Lit0);
        if (searchForKeyword2 == Special.dfault) {
            searchForKeyword2 = misc.interactionEnvironment();
        }
        try {
            try {
                try {
                    Translator translator = new Translator((Language) searchForKeyword, new SourceMessages(), NameLookup.getInstance((Environment) searchForKeyword2, (Language) searchForKeyword));
                    translator.pushNewModule((String) null);
                    Compilation saveCurrent = Compilation.setSaveCurrent(translator);
                    try {
                        return translator.rewrite(obj);
                    } finally {
                        Compilation.restoreCurrent(saveCurrent);
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "kawa.lang.Translator.<init>(gnu.expr.Language,gnu.text.SourceMessages,gnu.expr.NameLookup)", 1, searchForKeyword);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 2, searchForKeyword);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 1, searchForKeyword2);
        }
    }

    static Object unrewrite(Expression expression) {
        Object obj;
        frame frame2 = new frame();
        if (expression instanceof LetExp) {
            try {
                return unrewriteLet((LetExp) expression);
            } catch (ClassCastException e) {
                throw new WrongType(e, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof QuoteExp) {
            try {
                return unrewriteQuote((QuoteExp) expression);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof SetExp) {
            try {
                SetExp setExp = (SetExp) expression;
                return Quote.append$V(new Object[]{Lit3, Quote.consX$V(new Object[]{setExp.getSymbol(), Quote.consX$V(new Object[]{unrewrite(setExp.getNewValue()), LList.Empty})})});
            } catch (ClassCastException e3) {
                throw new WrongType(e3, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof LambdaExp) {
            try {
                LambdaExp lambdaExp = (LambdaExp) expression;
                Object[] objArr = new Object[2];
                objArr[0] = Lit4;
                Object[] objArr2 = new Object[2];
                frame2.pack = LList.Empty;
                for (Declaration firstDecl = lambdaExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
                    frame2.pack = lists.cons(firstDecl.getSymbol(), frame2.pack);
                }
                objArr2[0] = lists.reverse$Ex(frame2.pack);
                objArr2[1] = Quote.consX$V(new Object[]{unrewrite(lambdaExp.body), LList.Empty});
                objArr[1] = Quote.consX$V(objArr2);
                return Quote.append$V(objArr);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof ReferenceExp) {
            try {
                return ((ReferenceExp) expression).getSymbol();
            } catch (ClassCastException e5) {
                throw new WrongType(e5, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof ApplyExp) {
            try {
                return unrewriteApply((ApplyExp) expression);
            } catch (ClassCastException e6) {
                throw new WrongType(e6, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (expression instanceof BeginExp) {
            try {
                return Quote.append$V(new Object[]{Lit5, unrewrite$St(((BeginExp) expression).getExpressions())});
            } catch (ClassCastException e7) {
                throw new WrongType(e7, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        } else if (!(expression instanceof IfExp)) {
            return expression;
        } else {
            try {
                IfExp ifExp = (IfExp) expression;
                Object[] objArr3 = new Object[2];
                objArr3[0] = Lit6;
                Object[] objArr4 = new Object[2];
                objArr4[0] = unrewrite(ifExp.getTest());
                Object[] objArr5 = new Object[2];
                objArr5[0] = unrewrite(ifExp.getThenClause());
                Object[] objArr6 = new Object[2];
                Expression elseClause = ifExp.getElseClause();
                if (elseClause == null) {
                    obj = LList.Empty;
                } else {
                    obj = LList.list1(unrewrite(elseClause));
                }
                objArr6[0] = obj;
                objArr6[1] = LList.Empty;
                objArr5[1] = Quote.append$V(objArr6);
                objArr4[1] = Quote.consX$V(objArr5);
                objArr3[1] = Quote.consX$V(objArr4);
                return Quote.append$V(objArr3);
            } catch (ClassCastException e8) {
                throw new WrongType(e8, ReservedClaimNames.EXPIRATION_TIME, -2, (Object) expression);
            }
        }
    }

    static Object unrewrite$St(Expression[] expressionArr) {
        frame0 frame02 = new frame0();
        frame02.pack = LList.Empty;
        Integer valueOf = Integer.valueOf(expressionArr.length);
        for (Object obj = Lit7; Scheme.numEqu.apply2(obj, valueOf) == Boolean.FALSE; obj = AddOp.$Pl.apply2(obj, Lit8)) {
            frame02.pack = lists.cons(unrewrite(expressionArr[((Number) obj).intValue()]), frame02.pack);
        }
        return lists.reverse$Ex(frame02.pack);
    }

    static Object unrewriteLet(LetExp letExp) {
        frame1 frame12 = new frame1();
        Object[] objArr = new Object[2];
        objArr[0] = Lit9;
        Object[] objArr2 = new Object[2];
        frame12.pack = LList.Empty;
        Declaration firstDecl = letExp.firstDecl();
        Object obj = Lit7;
        while (firstDecl != null) {
            frame12.pack = lists.cons(LList.list2(firstDecl.getSymbol(), unrewrite(letExp.inits[((Number) obj).intValue()])), frame12.pack);
            firstDecl = firstDecl.nextDecl();
            obj = AddOp.$Pl.apply2(obj, Lit8);
        }
        objArr2[0] = lists.reverse$Ex(frame12.pack);
        objArr2[1] = Quote.consX$V(new Object[]{unrewrite(letExp.body), LList.Empty});
        objArr[1] = Quote.consX$V(objArr2);
        return Quote.append$V(objArr);
    }

    static Object unrewriteQuote(QuoteExp quoteExp) {
        String str;
        Object value = quoteExp.getValue();
        if (Numeric.asNumericOrNull(value) != null) {
            try {
                return LangObjType.coerceNumeric(value);
            } catch (ClassCastException e) {
                throw new WrongType(e, "val", -2, value);
            }
        } else {
            boolean z = true;
            if (value instanceof Boolean) {
                try {
                    if (value == Boolean.FALSE) {
                        z = false;
                    }
                    return z ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "val", -2, value);
                }
            } else if (value instanceof Char) {
                try {
                    return (Char) value;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "val", -2, value);
                }
            } else if (value instanceof Keyword) {
                try {
                    return (Keyword) value;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "val", -2, value);
                }
            } else if (value instanceof CharSequence) {
                try {
                    return (CharSequence) value;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "val", -2, value);
                }
            } else if (value == Special.undefined || value == EofClass.eofValue) {
                return value;
            } else {
                if (value instanceof Type) {
                    try {
                        str = ((Type) value).getName();
                    } catch (ClassCastException e6) {
                        throw new WrongType(e6, "val", -2, value);
                    }
                } else if (value instanceof Class) {
                    try {
                        str = ((Class) value).getName();
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "val", -2, value);
                    }
                } else {
                    return Quote.append$V(new Object[]{Lit10, Quote.consX$V(new Object[]{value, LList.Empty})});
                }
                return misc.string$To$Symbol(Format.formatToString(0, "<~a>", str));
            }
        }
    }

    static Object unrewriteApply(ApplyExp applyExp) {
        Declaration declaration;
        Expression function = applyExp.getFunction();
        Object unrewrite$St = unrewrite$St(applyExp.getArgs());
        if (function instanceof ReferenceExp) {
            try {
                declaration = ((ReferenceExp) function).getBinding();
            } catch (ClassCastException e) {
                throw new WrongType(e, "fun", -2, (Object) function);
            }
        } else {
            declaration = null;
        }
        Declaration declaration2 = declaration;
        Declaration declarationFromStatic = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
        Object functionValue = applyExp.getFunctionValue();
        int i = ((declaration2 == null ? 1 : 0) + 1) & 1;
        if (i != 0) {
            int i2 = ((declarationFromStatic == null ? 1 : 0) + 1) & 1;
            if (i2 != 0) {
                if (SlotGet.getSlotValue(false, declaration2, "field", "field", "getField", "isField", Scheme.instance) == declarationFromStatic.field) {
                    return unrewrite$St;
                }
            } else if (i2 != 0) {
                return unrewrite$St;
            }
        } else if (i != 0) {
            return unrewrite$St;
        }
        Object append$V = functionValue instanceof Convert ? Quote.append$V(new Object[]{Lit11, unrewrite$St}) : functionValue instanceof GetNamedPart ? Quote.append$V(new Object[]{Lit12, unrewrite$St}) : Boolean.FALSE;
        if (append$V != Boolean.FALSE) {
            return append$V;
        }
        return Quote.consX$V(new Object[]{unrewrite(function), unrewrite$St});
    }
}
