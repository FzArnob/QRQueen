package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_static extends Syntax {
    public static final module_static module_static;

    public Expression rewriteForm(Pair pair, Translator translator) {
        return null;
    }

    static {
        module_static module_static2 = new module_static();
        module_static = module_static2;
        module_static2.setName("module-static");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        if (!(scopeExp instanceof ModuleExp)) {
            translator.error('e', "'" + getName() + "' not at module level");
            return true;
        }
        ModuleExp moduleExp = (ModuleExp) scopeExp;
        boolean z = cdr instanceof Pair;
        if (z) {
            Pair pair2 = (Pair) cdr;
            if (pair2.getCdr() == LList.Empty && (pair2.getCar() instanceof Boolean)) {
                if (pair2.getCar() == Boolean.FALSE) {
                    moduleExp.setFlag(65536);
                } else {
                    moduleExp.setFlag(32768);
                }
                if (moduleExp.getFlag(65536) && moduleExp.getFlag(32768)) {
                    translator.error('e', "inconsistent module-static specifiers");
                }
                return true;
            }
        }
        if (z) {
            Pair pair3 = (Pair) cdr;
            if (pair3.getCdr() == LList.Empty && (pair3.getCar() instanceof Pair)) {
                Pair pair4 = (Pair) pair3.getCar();
                if (translator.matches(pair4.getCar(), LispLanguage.quote_sym)) {
                    Pair pair5 = (Pair) pair4.getCdr();
                    if (pair5 == LList.Empty || !(pair5.getCar() instanceof SimpleSymbol) || pair5.getCar().toString() != "init-run") {
                        translator.error('e', "invalid quoted symbol for '" + getName() + '\'');
                        return false;
                    }
                    moduleExp.setFlag(32768);
                    moduleExp.setFlag(262144);
                    translator.error('e', "inconsistent module-static specifiers");
                    return true;
                }
            }
        }
        moduleExp.setFlag(65536);
        while (cdr != LList.Empty) {
            if (cdr instanceof Pair) {
                Pair pair6 = (Pair) cdr;
                if (pair6.getCar() instanceof Symbol) {
                    Declaration noDefine = scopeExp.getNoDefine((Symbol) pair6.getCar());
                    if (noDefine.getFlag(512)) {
                        Translator.setLine(noDefine, (Object) pair6);
                    }
                    noDefine.setFlag(2048);
                    cdr = pair6.getCdr();
                }
            }
            translator.error('e', "invalid syntax in '" + getName() + '\'');
            return false;
        }
        translator.error('e', "inconsistent module-static specifiers");
        return true;
    }
}
