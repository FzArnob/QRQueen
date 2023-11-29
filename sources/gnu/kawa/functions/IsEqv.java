package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.text.Char;

public class IsEqv extends Procedure2 {
    IsEq isEq;
    Language language;

    public IsEqv(Language language2, String str, IsEq isEq2) {
        this.language = language2;
        this.isEq = isEq2;
        setName(str);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateIsEqv");
    }

    public static boolean apply(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return IsEqual.numberEquals((Number) obj, (Number) obj2);
        }
        if ((obj instanceof Char) || (obj instanceof Symbol)) {
            return obj.equals(obj2);
        }
        return false;
    }

    public Object apply2(Object obj, Object obj2) {
        return this.language.booleanObject(apply(obj, obj2));
    }
}
