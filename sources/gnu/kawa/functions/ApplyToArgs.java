package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.lang.reflect.Array;
import java.util.List;

public class ApplyToArgs extends ProcedureN {
    Language language;

    public int match1(Object obj, CallContext callContext) {
        if (obj instanceof Procedure) {
            return ((Procedure) obj).match0(callContext);
        }
        return super.match1(obj, callContext);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        if (obj instanceof Procedure) {
            return ((Procedure) obj).match1(obj2, callContext);
        }
        return super.match2(obj, obj2, callContext);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (obj instanceof Procedure) {
            return ((Procedure) obj).match2(obj2, obj3, callContext);
        }
        return super.match3(obj, obj2, obj3, callContext);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (obj instanceof Procedure) {
            return ((Procedure) obj).match3(obj2, obj3, obj4, callContext);
        }
        return super.match4(obj, obj2, obj3, obj4, callContext);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        int length = objArr.length;
        if (length > 0) {
            Procedure procedure = objArr[0];
            if (procedure instanceof Procedure) {
                Procedure procedure2 = procedure;
                if (length == 1) {
                    return procedure2.match0(callContext);
                }
                if (length == 2) {
                    return procedure2.match1(objArr[1], callContext);
                }
                if (length == 3) {
                    return procedure2.match2(objArr[1], objArr[2], callContext);
                }
                if (length == 4) {
                    return procedure2.match3(objArr[1], objArr[2], objArr[3], callContext);
                }
                if (length != 5) {
                    int i = length - 1;
                    Object[] objArr2 = new Object[i];
                    System.arraycopy(objArr, 1, objArr2, 0, i);
                    return procedure2.matchN(objArr2, callContext);
                }
                Object obj = objArr[1];
                return procedure2.match4(obj, objArr[2], objArr[3], objArr[4], callContext);
            }
        }
        return super.matchN(objArr, callContext);
    }

    public void check1(Object obj, CallContext callContext) {
        if (obj instanceof Procedure) {
            ((Procedure) obj).check0(callContext);
        } else {
            super.check1(obj, callContext);
        }
    }

    public void check2(Object obj, Object obj2, CallContext callContext) {
        if (obj instanceof Procedure) {
            ((Procedure) obj).check1(obj2, callContext);
        } else {
            super.check2(obj, obj2, callContext);
        }
    }

    public void check3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (obj instanceof Procedure) {
            ((Procedure) obj).check2(obj2, obj3, callContext);
        } else {
            super.check3(obj, obj2, obj3, callContext);
        }
    }

    public void check4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (obj instanceof Procedure) {
            ((Procedure) obj).check3(obj2, obj3, obj4, callContext);
        } else {
            super.check4(obj, obj2, obj3, obj4, callContext);
        }
    }

    public void checkN(Object[] objArr, CallContext callContext) {
        Procedure procedure;
        int matchN = matchN(objArr, callContext);
        if (matchN != 0) {
            if (objArr.length > 0) {
                Procedure procedure2 = objArr[0];
                if (procedure2 instanceof Procedure) {
                    procedure = procedure2;
                    int length = objArr.length - 1;
                    Object[] objArr2 = new Object[length];
                    System.arraycopy(objArr, 1, objArr2, 0, length);
                    objArr = objArr2;
                    throw MethodProc.matchFailAsException(matchN, procedure, objArr);
                }
            }
            procedure = this;
            throw MethodProc.matchFailAsException(matchN, procedure, objArr);
        }
    }

    public ApplyToArgs(String str, Language language2) {
        super(str);
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateApplyToArgs");
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Procedure procedure = objArr[0];
        if (procedure instanceof Procedure) {
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            System.arraycopy(objArr, 1, objArr2, 0, length);
            return procedure.applyN(objArr2);
        } else if ((procedure instanceof Type) || (procedure instanceof Class)) {
            return Invoke.make.applyN(objArr);
        } else {
            if (procedure instanceof List) {
                if (objArr.length == 2) {
                    return ((List) procedure).get(objArr[1].intValue());
                }
                throw new WrongArguments(this, objArr.length);
            } else if (!procedure.getClass().isArray()) {
                throw new WrongType((Procedure) this, 0, (Object) procedure, "procedure");
            } else if (objArr.length == 2) {
                return Array.get(procedure, objArr[1].intValue());
            } else {
                throw new WrongArguments(this, objArr.length);
            }
        }
    }
}
