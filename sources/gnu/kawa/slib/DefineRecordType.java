package gnu.kawa.slib;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

/* compiled from: DefineRecordType.scm */
public class DefineRecordType extends ModuleBody {
    public static final Macro $Prvt$$Pcdefine$Mnrecord$Mnfield;
    public static final DefineRecordType $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro define$Mnrecord$Mntype;

    public DefineRecordType() {
        ModuleInfo.register(this);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("tmp").readResolve();
        Lit12 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("slot-set!").readResolve();
        Lit11 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit10 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol(CommonProperties.VALUE).readResolve();
        Lit9 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit8 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("slot-ref").readResolve();
        Lit7 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit6 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("obj").readResolve();
        Lit5 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
        Lit4 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("%define-record-field").readResolve();
        Lit2 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = simpleSymbol10;
        SimpleSymbol simpleSymbol12 = simpleSymbol;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol10}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004Y\t\u0013\b\u0011\u0018\f\u0011\u0018\u0014\b\u0003\b\u0011\u0018\u001c\u0011\u0018\f\b\u0011\u0018$\b\u000b", new Object[]{simpleSymbol9, simpleSymbol8, simpleSymbol7, simpleSymbol6, simpleSymbol5}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004á\u0011\u0018\fY\t\u0013\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0003\b\u0011\u0018$\u0011\u0018\u0014\b\u0011\u0018,\b\u000b\b\u0011\u0018\fi\t\u001bA\u0011\u0018\u0014\u0011\u0018\u001c\b\u0003\u00184\u0011\u0018\u001c\u0011\u0018<\b\u0011\u0018D\u0011\u0018\u0014)\u0011\u0018,\b\u000b\u0018L", new Object[]{simpleSymbol3, simpleSymbol9, simpleSymbol8, simpleSymbol7, simpleSymbol6, simpleSymbol5, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/DefineRecordType.scm", 208936), (SimpleSymbol) new SimpleSymbol("<void>").readResolve(), simpleSymbol2, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/DefineRecordType.scm", 213021)}, 0)}, 4);
        Lit3 = syntaxRules;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("define-record-type").readResolve();
        Lit0 = simpleSymbol13;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol13}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\r\u0017\u0010\b\b\f\u001f-\f'\f/3 \u0018\b", new Object[0], 7), "\u0001\u0001\u0003\u0001\u0003\u0003\u0002", "\u0011\u0018\u0004Y\u0011\u0018\f\t\u0003\t\u0010\b%\b#¹\u0011\u0018\u0014!\t\u001b\u0018\u001c\u0011\u0018$\u0011\u0018,\b\u0011\u00184\u0011\u0018<\b\u0003ǁ\u0011\u0018\u0014)\t\u000b\b\u0015\u0013\u0011\u0018$\t\u0003\b\u0011\u0018Dy\b\u0011\u0018L\u0011\u0018$\t\u0003\b\u0011\u0018T\b\u0003\u0011\u0018\u0004\b\u0015\u0011\u0018\\\u0011\u0018L)\u0011\u0018d\b\u0013\b\u0013\u0018l\b%\u0011\u0018t\t\u0003\t#\t+2", new Object[]{simpleSymbol3, (SimpleSymbol) new SimpleSymbol("define-simple-class").readResolve(), simpleSymbol9, PairWithPosition.make(simpleSymbol8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/DefineRecordType.scm", 122907), simpleSymbol7, (SimpleSymbol) new SimpleSymbol("<boolean>").readResolve(), (SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve(), simpleSymbol8, (SimpleSymbol) new SimpleSymbol("let").readResolve(), simpleSymbol12, (SimpleSymbol) new SimpleSymbol("make").readResolve(), simpleSymbol2, simpleSymbol5, PairWithPosition.make(simpleSymbol12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/DefineRecordType.scm", 143365), simpleSymbol11}, 1)}, 7);
        Lit1 = syntaxRules2;
        DefineRecordType defineRecordType = new DefineRecordType();
        $instance = defineRecordType;
        define$Mnrecord$Mntype = Macro.make(simpleSymbol13, syntaxRules2, defineRecordType);
        $Prvt$$Pcdefine$Mnrecord$Mnfield = Macro.make(simpleSymbol11, syntaxRules, defineRecordType);
        defineRecordType.run();
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }
}
