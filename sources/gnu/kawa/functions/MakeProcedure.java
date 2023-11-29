package gnu.kawa.functions;

import gnu.expr.GenericProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

public class MakeProcedure extends ProcedureN {
    public static final MakeProcedure makeProcedure = new MakeProcedure("make-procedure");

    public MakeProcedure(String str) {
        super(str);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyMakeProcedure");
    }

    public static GenericProc makeProcedure$V(Object[] objArr) {
        return GenericProc.make(objArr);
    }

    public Object applyN(Object[] objArr) {
        return GenericProc.make(objArr);
    }
}
