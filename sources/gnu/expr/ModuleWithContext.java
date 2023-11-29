package gnu.expr;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.mapping.CallContext;
import gnu.mapping.ProcedureN;

public abstract class ModuleWithContext extends ModuleBody {
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 0) {
            return -983040 | i;
        }
        callContext.count = 0;
        callContext.where = 0;
        if (numArgs < 0) {
            return matchN(moduleMethod, ProcedureN.noArgs, callContext);
        }
        callContext.next = 0;
        callContext.proc = this;
        callContext.pc = moduleMethod.selector;
        return 0;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 1) {
            return -983040 | i;
        }
        if (numArgs >= 0) {
            int i2 = numArgs >> 12;
            if (i2 < 1) {
                return -917504 | i2;
            }
            callContext.value1 = obj;
            callContext.count = 1;
            callContext.where = 1;
            callContext.next = 0;
            callContext.proc = this;
            callContext.pc = moduleMethod.selector;
            return 0;
        }
        callContext.where = 0;
        return matchN(moduleMethod, new Object[]{obj}, callContext);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 2) {
            return -983040 | i;
        }
        if (numArgs >= 0) {
            int i2 = numArgs >> 12;
            if (i2 < 2) {
                return -917504 | i2;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.count = 2;
            callContext.where = 33;
            callContext.next = 0;
            callContext.proc = this;
            callContext.pc = moduleMethod.selector;
            return 0;
        }
        callContext.where = 0;
        return matchN(moduleMethod, new Object[]{obj, obj2}, callContext);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 3) {
            return -983040 | i;
        }
        if (numArgs >= 0) {
            int i2 = numArgs >> 12;
            if (i2 < 3) {
                return -917504 | i2;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.count = 3;
            callContext.where = ErrorMessages.ERROR_SOUND_RECORDER;
            callContext.next = 0;
            callContext.proc = this;
            callContext.pc = moduleMethod.selector;
            return 0;
        }
        callContext.where = 0;
        return matchN(moduleMethod, new Object[]{obj, obj2, obj3}, callContext);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 4) {
            return -983040 | i;
        }
        if (numArgs >= 0) {
            int i2 = numArgs >> 12;
            if (i2 < 4) {
                return -917504 | i2;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.count = 4;
            callContext.where = 17185;
            callContext.next = 0;
            callContext.proc = this;
            callContext.pc = moduleMethod.selector;
            return 0;
        }
        callContext.where = 0;
        return matchN(moduleMethod, new Object[]{obj, obj2, obj3, obj4}, callContext);
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (objArr.length < i) {
            return -983040 | i;
        }
        if (numArgs >= 0) {
            int length = objArr.length;
            if (length == 0) {
                return match0(moduleMethod, callContext);
            }
            if (length == 1) {
                return match1(moduleMethod, objArr[0], callContext);
            }
            if (length == 2) {
                return match2(moduleMethod, objArr[0], objArr[1], callContext);
            }
            if (length == 3) {
                return match3(moduleMethod, objArr[0], objArr[1], objArr[2], callContext);
            } else if (length != 4) {
                int i2 = numArgs >> 12;
                if (objArr.length > i2) {
                    return -917504 | i2;
                }
            } else {
                return match4(moduleMethod, objArr[0], objArr[1], objArr[2], objArr[3], callContext);
            }
        }
        callContext.values = objArr;
        callContext.count = objArr.length;
        callContext.where = 0;
        callContext.next = 0;
        callContext.proc = this;
        callContext.pc = moduleMethod.selector;
        return 0;
    }

    public Object apply0(ModuleMethod moduleMethod) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.check0(instance);
        return instance.runUntilValue();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.check1(obj, instance);
        return instance.runUntilValue();
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.check2(obj, obj2, instance);
        return instance.runUntilValue();
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.check3(obj, obj2, obj3, instance);
        return instance.runUntilValue();
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.check4(obj, obj2, obj3, obj4, instance);
        return instance.runUntilValue();
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) throws Throwable {
        CallContext instance = CallContext.getInstance();
        moduleMethod.checkN(objArr, instance);
        return instance.runUntilValue();
    }
}
