package gnu.mapping;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.microsoft.appcenter.Constants;
import gnu.bytecode.Type;
import gnu.expr.Expression;

public abstract class Procedure extends PropertySet {
    public static final LazyPropertyKey<?> compilerKey = new LazyPropertyKey<>("compiler");
    private static final Symbol setterKey = Namespace.EmptyNamespace.getSymbol("setter");
    private static final String sourceLocationKey = "source-location";
    public static final Symbol validateApplyKey = Namespace.EmptyNamespace.getSymbol("validate-apply");

    public static int maxArgs(int i) {
        return i >> 12;
    }

    public static int minArgs(int i) {
        return i & 4095;
    }

    public abstract Object apply0() throws Throwable;

    public abstract Object apply1(Object obj) throws Throwable;

    public abstract Object apply2(Object obj, Object obj2) throws Throwable;

    public abstract Object apply3(Object obj, Object obj2, Object obj3) throws Throwable;

    public abstract Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable;

    public abstract Object applyN(Object[] objArr) throws Throwable;

    public boolean isSideEffectFree() {
        return false;
    }

    public int numArgs() {
        return -4096;
    }

    public void setSourceLocation(String str, int i) {
        setProperty(sourceLocationKey, str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + i);
    }

    public String getSourceLocation() {
        Object property = getProperty(sourceLocationKey, (Object) null);
        if (property == null) {
            return null;
        }
        return property.toString();
    }

    public Procedure() {
    }

    public Procedure(String str) {
        setName(str);
    }

    public final int minArgs() {
        return minArgs(numArgs());
    }

    public final int maxArgs() {
        return maxArgs(numArgs());
    }

    public static void checkArgCount(Procedure procedure, int i) {
        int numArgs = procedure.numArgs();
        if (i < minArgs(numArgs) || (numArgs >= 0 && i > maxArgs(numArgs))) {
            throw new WrongArguments(procedure, i);
        }
    }

    public void apply(CallContext callContext) throws Throwable {
        apply(this, callContext);
    }

    public static void apply(Procedure procedure, CallContext callContext) throws Throwable {
        int i = callContext.count;
        callContext.writeValue((callContext.where != 0 || i == 0) ? i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? procedure.applyN(callContext.getArgs()) : procedure.apply4(callContext.getNextArg(), callContext.getNextArg(), callContext.getNextArg(), callContext.getNextArg()) : procedure.apply3(callContext.getNextArg(), callContext.getNextArg(), callContext.getNextArg()) : procedure.apply2(callContext.getNextArg(), callContext.getNextArg()) : procedure.apply1(callContext.getNextArg()) : procedure.apply0() : procedure.applyN(callContext.values));
    }

    public int match0(CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (minArgs > 0) {
            return -983040 | minArgs;
        }
        if (numArgs < 0) {
            return matchN(ProcedureN.noArgs, callContext);
        }
        callContext.count = 0;
        callContext.where = 0;
        callContext.next = 0;
        callContext.proc = this;
        return 0;
    }

    public int match1(Object obj, CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (minArgs > 1) {
            return -983040 | minArgs;
        }
        if (numArgs >= 0) {
            int maxArgs = maxArgs(numArgs);
            if (maxArgs < 1) {
                return -917504 | maxArgs;
            }
            callContext.value1 = obj;
            callContext.count = 1;
            callContext.where = 1;
            callContext.next = 0;
            callContext.proc = this;
            return 0;
        }
        return matchN(new Object[]{obj}, callContext);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (minArgs > 2) {
            return -983040 | minArgs;
        }
        if (numArgs >= 0) {
            int maxArgs = maxArgs(numArgs);
            if (maxArgs < 2) {
                return -917504 | maxArgs;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.count = 2;
            callContext.where = 33;
            callContext.next = 0;
            callContext.proc = this;
            return 0;
        }
        return matchN(new Object[]{obj, obj2}, callContext);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (minArgs > 3) {
            return -983040 | minArgs;
        }
        if (numArgs >= 0) {
            int maxArgs = maxArgs(numArgs);
            if (maxArgs < 3) {
                return -917504 | maxArgs;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.count = 3;
            callContext.where = ErrorMessages.ERROR_SOUND_RECORDER;
            callContext.next = 0;
            callContext.proc = this;
            return 0;
        }
        return matchN(new Object[]{obj, obj2, obj3}, callContext);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (minArgs > 4) {
            return -983040 | minArgs;
        }
        if (numArgs >= 0) {
            int maxArgs = maxArgs(numArgs);
            if (maxArgs < 4) {
                return -917504 | maxArgs;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.count = 4;
            callContext.where = 17185;
            callContext.next = 0;
            callContext.proc = this;
            return 0;
        }
        return matchN(new Object[]{obj, obj2, obj3, obj4}, callContext);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        int numArgs = numArgs();
        int minArgs = minArgs(numArgs);
        if (objArr.length < minArgs) {
            return -983040 | minArgs;
        }
        if (numArgs >= 0) {
            int length = objArr.length;
            if (length == 0) {
                return match0(callContext);
            }
            if (length == 1) {
                return match1(objArr[0], callContext);
            }
            if (length == 2) {
                return match2(objArr[0], objArr[1], callContext);
            }
            if (length == 3) {
                return match3(objArr[0], objArr[1], objArr[2], callContext);
            }
            if (length != 4) {
                int maxArgs = maxArgs(numArgs);
                if (objArr.length > maxArgs) {
                    return -917504 | maxArgs;
                }
            } else {
                return match4(objArr[0], objArr[1], objArr[2], objArr[3], callContext);
            }
        }
        callContext.values = objArr;
        callContext.count = objArr.length;
        callContext.where = 0;
        callContext.next = 0;
        callContext.proc = this;
        return 0;
    }

    public void check0(CallContext callContext) {
        int match0 = match0(callContext);
        if (match0 != 0) {
            throw MethodProc.matchFailAsException(match0, this, ProcedureN.noArgs);
        }
    }

    public void check1(Object obj, CallContext callContext) {
        int match1 = match1(obj, callContext);
        if (match1 != 0) {
            throw MethodProc.matchFailAsException(match1, this, new Object[]{obj});
        }
    }

    public void check2(Object obj, Object obj2, CallContext callContext) {
        int match2 = match2(obj, obj2, callContext);
        if (match2 != 0) {
            throw MethodProc.matchFailAsException(match2, this, new Object[]{obj, obj2});
        }
    }

    public void check3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        int match3 = match3(obj, obj2, obj3, callContext);
        if (match3 != 0) {
            throw MethodProc.matchFailAsException(match3, this, new Object[]{obj, obj2, obj3});
        }
    }

    public void check4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int match4 = match4(obj, obj2, obj3, obj4, callContext);
        if (match4 != 0) {
            throw MethodProc.matchFailAsException(match4, this, new Object[]{obj, obj2, obj3, obj4});
        }
    }

    public void checkN(Object[] objArr, CallContext callContext) {
        int matchN = matchN(objArr, callContext);
        if (matchN != 0) {
            throw MethodProc.matchFailAsException(matchN, this, objArr);
        }
    }

    public Procedure getSetter() {
        if (!(this instanceof HasSetter)) {
            Object property = getProperty(setterKey, (Object) null);
            if (property instanceof Procedure) {
                return (Procedure) property;
            }
            throw new RuntimeException("procedure '" + getName() + "' has no setter");
        }
        int numArgs = numArgs();
        if (numArgs == 0) {
            return new Setter0(this);
        }
        if (numArgs == 4097) {
            return new Setter1(this);
        }
        return new Setter(this);
    }

    public void setSetter(Procedure procedure) {
        if (!(this instanceof HasSetter)) {
            setProperty(setterKey, procedure);
            return;
        }
        throw new RuntimeException("procedure '" + getName() + "' has builtin setter - cannot be modified");
    }

    public void set0(Object obj) throws Throwable {
        getSetter().apply1(obj);
    }

    public void set1(Object obj, Object obj2) throws Throwable {
        getSetter().apply2(obj, obj2);
    }

    public void setN(Object[] objArr) throws Throwable {
        getSetter().applyN(objArr);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.objectType;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#<procedure ");
        String name = getName();
        if (name == null) {
            name = getSourceLocation();
        }
        if (name == null) {
            name = getClass().getName();
        }
        stringBuffer.append(name);
        stringBuffer.append('>');
        return stringBuffer.toString();
    }
}
