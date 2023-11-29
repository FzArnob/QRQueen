package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_implements extends Syntax {
    public static final module_implements module_implements;

    static {
        module_implements module_implements2 = new module_implements();
        module_implements = module_implements2;
        module_implements2.setName("module-implements");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        int listLength = LList.listLength(cdr, false);
        if (listLength < 0) {
            translator.syntaxError("improper argument list for " + getName());
            return;
        }
        ClassType[] classTypeArr = new ClassType[listLength];
        for (int i = 0; i < listLength; i++) {
            Pair pair2 = (Pair) cdr;
            classTypeArr[i] = (ClassType) translator.exp2Type(pair2);
            cdr = pair2.getCdr();
        }
        ModuleExp module = translator.getModule();
        module.setInterfaces(classTypeArr);
        module.setFlag(131072);
    }
}
