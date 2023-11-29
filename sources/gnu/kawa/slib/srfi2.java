package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

/* compiled from: srfi2.scm */
public class srfi2 extends ModuleBody {
    public static final srfi2 $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1 = new SyntaxPattern("\f\u0007,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4);
    static final SyntaxTemplate Lit10 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit11 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\u001b\b", new Object[0], 4);
    static final SyntaxTemplate Lit12;
    static final SyntaxPattern Lit13 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\u0013\b", new Object[0], 3);
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15 = new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\b", new Object[0], 3);
    static final SyntaxTemplate Lit16 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit17;
    static final SyntaxTemplate Lit18 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit19 = new SyntaxPattern("\f\u0007\f\b\b", new Object[0], 1);
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\u0001\u0003\u0001\u0000", "\t\u0003\b\u0011\r\u000b\b\b\u0011\u0018\u0004\t\u0013\u001a", new Object[]{(SimpleSymbol) new SimpleSymbol("begin").readResolve()}, 1);
    static final SyntaxTemplate Lit20 = new SyntaxTemplate("\u0001", "\u0018\u0004", new Object[]{Boolean.TRUE}, 0);
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxPattern Lit3 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit4;
    static final SyntaxPattern Lit5 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\b\b", new Object[0], 2);
    static final SyntaxTemplate Lit6 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit7 = new SyntaxPattern("\f\u0007\u001c\f\u000f\b\b", new Object[0], 2);
    static final SyntaxTemplate Lit8 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit9 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    public static final Macro and$Mnlet$St;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit22 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit21 = simpleSymbol2;
        Lit17 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{simpleSymbol2}, 0);
        Lit14 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{simpleSymbol2}, 0);
        Lit12 = new SyntaxTemplate("\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u0011\u0018\f\t\u000b\b\t\u0003\b\u001a", new Object[]{simpleSymbol, simpleSymbol2}, 0);
        Lit4 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u000b", new Object[]{simpleSymbol}, 0);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("and-let*").readResolve();
        Lit0 = simpleSymbol3;
        srfi2 srfi2 = new srfi2();
        $instance = srfi2;
        and$Mnlet$St = Macro.make(simpleSymbol3, new ModuleMethod(srfi2, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), srfi2);
        srfi2.run();
    }

    public srfi2() {
        ModuleInfo.register(this);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 1 ? lambda1(obj) : super.apply1(moduleMethod, obj);
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

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static Object lambda1(Object obj) {
        Object[] objArr;
        Object[] objArr2;
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        if (Lit1.match(obj, allocVars, 0)) {
            return Lit2.execute(allocVars, TemplateScope.make());
        } else if (Lit3.match(obj, allocVars, 0)) {
            return Lit4.execute(allocVars, TemplateScope.make());
        } else if (Lit5.match(obj, allocVars, 0)) {
            return Lit6.execute(allocVars, TemplateScope.make());
        } else if (Lit7.match(obj, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit8.execute(allocVars, TemplateScope.make()))) {
                return Lit9.execute(allocVars, TemplateScope.make());
            }
            Object execute = Lit10.execute(allocVars, TemplateScope.make());
            if (!("expected a variable name" instanceof Object[])) {
                objArr2 = new Object[]{"expected a variable name"};
            }
            return prim_syntax.syntaxError(execute, objArr2);
        } else if (Lit11.match(obj, allocVars, 0)) {
            return Lit12.execute(allocVars, TemplateScope.make());
        } else if (Lit13.match(obj, allocVars, 0)) {
            return Lit14.execute(allocVars, TemplateScope.make());
        } else if (Lit15.match(obj, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit16.execute(allocVars, TemplateScope.make()))) {
                return Lit17.execute(allocVars, TemplateScope.make());
            }
            Object execute2 = Lit18.execute(allocVars, TemplateScope.make());
            if (!("expected a variable name" instanceof Object[])) {
                objArr = new Object[]{"expected a variable name"};
            }
            return prim_syntax.syntaxError(execute2, objArr);
        } else if (!Lit19.match(obj, allocVars, 0)) {
            return syntax_case.error("syntax-case", obj);
        } else {
            return Lit20.execute(allocVars, TemplateScope.make());
        }
    }
}
