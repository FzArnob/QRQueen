package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class Not extends Procedure1 {
    Language language;

    public Not(Language language2) {
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyNot");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileMisc:forNot");
    }

    public Not(Language language2, String str) {
        this(language2);
        setName(str);
    }

    public Object apply1(Object obj) {
        Language language2 = this.language;
        return language2.booleanObject(!language2.isTrue(obj));
    }
}
