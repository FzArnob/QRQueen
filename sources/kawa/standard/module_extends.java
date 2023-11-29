package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_extends extends Syntax {
    public static final module_extends module_extends;

    static {
        module_extends module_extends2 = new module_extends();
        module_extends = module_extends2;
        module_extends2.setName("module-extends");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Type exp2Type = translator.exp2Type((Pair) pair.getCdr());
        ModuleExp module = translator.getModule();
        module.setSuperType((ClassType) exp2Type);
        module.setFlag(131072);
    }
}
