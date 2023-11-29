package kawa.standard;

import gnu.expr.Special;
import kawa.lang.Lambda;
import kawa.repl;

public class SchemeCompilation {
    public static final Lambda lambda;
    public static final repl repl = new repl(Scheme.instance);

    static {
        Lambda lambda2 = new Lambda();
        lambda = lambda2;
        lambda2.setKeywords(Special.optional, Special.rest, Special.key);
    }
}
