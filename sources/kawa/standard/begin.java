package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class begin extends Syntax {
    public static final begin begin;

    static {
        begin begin2 = new begin();
        begin = begin2;
        begin2.setName("begin");
    }

    public Expression rewrite(Object obj, Translator translator) {
        return translator.rewrite_body(obj);
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        LList scanBody = translator.scanBody(pair.getCdr(), scopeExp, true);
        if (scanBody != LList.Empty) {
            translator.formStack.add(Translator.makePair(pair, pair.getCar(), scanBody));
        }
    }
}
