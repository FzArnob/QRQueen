package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: keywords.scm */
public class keywords extends ModuleBody {
    public static final keywords $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod keyword$Mn$Grstring;
    public static final ModuleMethod keyword$Qu;
    public static final ModuleMethod string$Mn$Grkeyword;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("string->keyword").readResolve();
        Lit2 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("keyword->string").readResolve();
        Lit1 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("keyword?").readResolve();
        Lit0 = simpleSymbol3;
        keywords keywords = new keywords();
        $instance = keywords;
        keyword$Qu = new ModuleMethod(keywords, 1, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keyword$Mn$Grstring = new ModuleMethod(keywords, 2, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grkeyword = new ModuleMethod(keywords, 3, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keywords.run();
    }

    public keywords() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 2) {
            if (i != 3) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (!(obj instanceof Keyword)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static boolean isKeyword(Object obj) {
        return Keyword.isKeyword(obj);
    }

    public static CharSequence keyword$To$String(Keyword keyword) {
        return keyword.getName();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return isKeyword(obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (i == 2) {
            try {
                return keyword$To$String((Keyword) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "keyword->string", 1, obj);
            }
        } else if (i != 3) {
            return super.apply1(moduleMethod, obj);
        } else {
            return string$To$Keyword(obj == null ? null : obj.toString());
        }
    }

    public static Keyword string$To$Keyword(String str) {
        return Keyword.make(str);
    }
}
