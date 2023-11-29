package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.kawa.functions.GetNamedPart;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: cut.scm */
public class cut extends ModuleBody {
    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncut;
    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncute;
    public static final cut $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro cut;
    public static final Macro cute;

    public cut() {
        ModuleInfo.register(this);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("rest-slot").readResolve();
        Lit14 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("apply").readResolve();
        Lit13 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(GetNamedPart.CLASSTYPE_FOR).readResolve();
        Lit12 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol(EllipticCurveJsonWebKey.X_MEMBER_NAME).readResolve();
        Lit11 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit10 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit9 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("<...>").readResolve();
        Lit8 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("cute").readResolve();
        Lit6 = simpleSymbol8;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("srfi-26-internal-cute").readResolve();
        Lit2 = simpleSymbol9;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol8}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0000", "\u0011\u0018\u0004\t\u0010\t\u0010\t\u0010\u0002", new Object[]{simpleSymbol9}, 0)}, 1);
        Lit7 = syntaxRules;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("cut").readResolve();
        Lit4 = simpleSymbol10;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol11 = simpleSymbol8;
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("srfi-26-internal-cut").readResolve();
        Lit0 = simpleSymbol12;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol10}, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0000", "\u0011\u0018\u0004\t\u0010\t\u0010\u0002", new Object[]{simpleSymbol12}, 0)}, 1);
        Lit5 = syntaxRules3;
        SyntaxRules syntaxRules4 = syntaxRules3;
        SimpleSymbol simpleSymbol13 = simpleSymbol10;
        SimpleSymbol simpleSymbol14 = simpleSymbol;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol9, simpleSymbol3, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f<\f\u0017\r\u001f\u0018\b\b\b", new Object[0], 4), "\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0011\u0018\f\u0019\b\u0005\u0003\b\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol6, simpleSymbol5}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f<\f\u0017\r\u001f\u0018\b\b\f\u0002\b", new Object[]{simpleSymbol7}, 4), "\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0011\u0018\f)\u0011\u0005\u0003\u0018\u0014\b\u0011\u0018\u001c\t\u0013\u0011\u001d\u001b\u0018$", new Object[]{simpleSymbol6, simpleSymbol5, simpleSymbol4, simpleSymbol2, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 356424)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f,\r\u0017\u0010\b\b\f\u0002\u001b", new Object[]{simpleSymbol3}, 4), "\u0003\u0001\u0003\u0000", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f\t\u000b)\u0011\u0015\u0013\u0018\u0014\u001a", new Object[]{simpleSymbol9, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 380950), PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 380987)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f,\r\u0017\u0010\b\b\f\u001f#", new Object[0], 5), "\u0001\u0001\u0003\u0001\u0000", "\u0011\u0018\u0004\t\u00039)\u0011\u0018\f\b\u001b\u000b)\u0011\u0015\u0013\u0018\u0014\"", new Object[]{simpleSymbol9, simpleSymbol4, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 401465)}, 1)}, 5);
        Lit3 = syntaxRules5;
        SyntaxRules syntaxRules6 = new SyntaxRules(new Object[]{simpleSymbol12, simpleSymbol3, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b<\f\u000f\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0003\u0001\u0003", "\u0011\u0018\u0004\u0019\b\u0005\u0003\b)\u0011\u0018\f\b\u000b\b\u0015\u0013", new Object[]{simpleSymbol5, (SimpleSymbol) new SimpleSymbol("begin").readResolve()}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b<\f\u000f\r\u0017\u0010\b\b\f\u0002\b", new Object[]{simpleSymbol7}, 3), "\u0003\u0001\u0003", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f\b\u0011\u0018\u0014\t\u000b\u0011\u0015\u0013\u0018\u001c", new Object[]{simpleSymbol5, simpleSymbol14, simpleSymbol2, PairWithPosition.make(simpleSymbol14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 249918)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b,\r\u000f\b\b\b\f\u0002\u0013", new Object[]{simpleSymbol3}, 3), "\u0003\u0003\u0000", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f)\u0011\r\u000b\u0018\u0014\u0012", new Object[]{simpleSymbol12, PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 266283), PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 266300)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4), "\u0003\u0003\u0001\u0000", "\u0011\u0018\u0004\u0019\b\u0005\u0003)\u0011\r\u000b\b\u0013\u001a", new Object[]{simpleSymbol12}, 1)}, 4);
        Lit1 = syntaxRules6;
        cut cut2 = new cut();
        $instance = cut2;
        $Prvt$srfi$Mn26$Mninternal$Mncut = Macro.make(simpleSymbol12, syntaxRules6, cut2);
        $Prvt$srfi$Mn26$Mninternal$Mncute = Macro.make(simpleSymbol9, syntaxRules5, cut2);
        cut = Macro.make(simpleSymbol13, syntaxRules4, cut2);
        cute = Macro.make(simpleSymbol11, syntaxRules2, cut2);
        cut2.run();
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }
}
