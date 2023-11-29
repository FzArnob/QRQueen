package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export extends Syntax {
    public static final export export = new export();
    public static final export module_export;

    public Expression rewriteForm(Pair pair, Translator translator) {
        return null;
    }

    static {
        export export2 = new export();
        module_export = export2;
        export2.setName("module-export");
        export2.setName("export");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        Object pushPositionOf = translator.pushPositionOf(pair);
        try {
            if (scopeExp instanceof ModuleExp) {
                ((ModuleExp) scopeExp).setFlag(16384);
                while (cdr != LList.Empty) {
                    translator.pushPositionOf(cdr);
                    while (cdr instanceof SyntaxForm) {
                        cdr = ((SyntaxForm) cdr).getDatum();
                    }
                    if (cdr instanceof Pair) {
                        Pair pair2 = (Pair) cdr;
                        Object car = pair2.getCar();
                        while (car instanceof SyntaxForm) {
                            car = ((SyntaxForm) car).getDatum();
                        }
                        if (car instanceof String) {
                            String str = (String) car;
                            if (str.startsWith("namespace:")) {
                                translator.error('w', "'namespace:' prefix ignored");
                                car = str.substring(10).intern();
                            }
                        }
                        if ((car instanceof String) || (car instanceof Symbol)) {
                            Declaration noDefine = scopeExp.getNoDefine(car);
                            if (noDefine.getFlag(512)) {
                                Translator.setLine(noDefine, (Object) pair2);
                            }
                            noDefine.setFlag(1024);
                            cdr = pair2.getCdr();
                        }
                    }
                    translator.error('e', "invalid syntax in '" + getName() + '\'');
                    return false;
                }
                translator.popPositionOf(pushPositionOf);
                return true;
            }
            translator.error('e', "'" + getName() + "' not at module level");
            translator.popPositionOf(pushPositionOf);
            return true;
        } finally {
            translator.popPositionOf(pushPositionOf);
        }
    }
}
