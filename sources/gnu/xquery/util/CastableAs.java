package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Target;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.xquery.lang.XQuery;

public class CastableAs extends InstanceOf {
    public static CastableAs castableAs = new CastableAs();

    CastableAs() {
        super(XQuery.getInstance(), "castable as");
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyCastableAs");
    }

    public Object apply2(Object obj, Object obj2) {
        boolean z;
        Type asType = this.language.asType(obj2);
        if (asType instanceof XDataType) {
            z = ((XDataType) asType).castable(obj);
        } else {
            z = asType.isInstance(obj);
        }
        return this.language.booleanObject(z);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp.compile(applyExp, compilation, target);
    }
}
