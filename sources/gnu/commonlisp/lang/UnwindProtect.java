package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.TryExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class UnwindProtect extends Syntax {
    public Expression rewrite(Object obj, Translator translator) {
        if (!(obj instanceof Pair)) {
            return translator.syntaxError("invalid syntax for unwind-protect");
        }
        Pair pair = (Pair) obj;
        return new TryExp(translator.rewrite(pair.getCar()), translator.rewrite_body(pair.getCdr()));
    }
}
