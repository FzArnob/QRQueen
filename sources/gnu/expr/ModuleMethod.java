package gnu.expr;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import java.lang.reflect.Method;

public class ModuleMethod extends MethodProc {
    public ModuleBody module;
    protected int numArgs;
    public int selector;

    public ModuleMethod(ModuleBody moduleBody, int i, Object obj, int i2) {
        init(moduleBody, i, obj, i2);
    }

    public ModuleMethod(ModuleBody moduleBody, int i, Object obj, int i2, Object obj2) {
        init(moduleBody, i, obj, i2);
        this.argTypes = obj2;
    }

    public ModuleMethod init(ModuleBody moduleBody, int i, Object obj, int i2) {
        this.module = moduleBody;
        this.selector = i;
        this.numArgs = i2;
        if (obj != null) {
            setSymbol(obj);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void resolveParameterTypes() {
        Language defaultLanguage;
        String name = getName();
        if (name != null) {
            try {
                Method[] declaredMethods = this.module.getClass().getDeclaredMethods();
                String mangleNameIfNeeded = Compilation.mangleNameIfNeeded(name);
                int length = declaredMethods.length;
                Method method = null;
                Method method2 = null;
                while (true) {
                    length--;
                    if (length < 0) {
                        method = method2;
                        break;
                    } else if (declaredMethods[length].getName().equals(mangleNameIfNeeded)) {
                        if (method2 != null) {
                            break;
                        }
                        method2 = declaredMethods[length];
                    }
                }
                if (!(method == null || (defaultLanguage = Language.getDefaultLanguage()) == null)) {
                    Class[] parameterTypes = method.getParameterTypes();
                    int length2 = parameterTypes.length;
                    Type[] typeArr = new Type[length2];
                    while (true) {
                        length2--;
                        if (length2 < 0) {
                            break;
                        }
                        typeArr[length2] = defaultLanguage.getTypeFor(parameterTypes[length2]);
                    }
                    this.argTypes = typeArr;
                }
            } catch (Throwable unused) {
            }
        }
        if (this.argTypes == null) {
            super.resolveParameterTypes();
        }
    }

    public int numArgs() {
        return this.numArgs;
    }

    public int match0(CallContext callContext) {
        callContext.count = 0;
        callContext.where = 0;
        return this.module.match0(this, callContext);
    }

    public int match1(Object obj, CallContext callContext) {
        callContext.count = 1;
        callContext.where = 1;
        return this.module.match1(this, obj, callContext);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        callContext.count = 2;
        callContext.where = 33;
        return this.module.match2(this, obj, obj2, callContext);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        callContext.count = 3;
        callContext.where = ErrorMessages.ERROR_SOUND_RECORDER;
        return this.module.match3(this, obj, obj2, obj3, callContext);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        callContext.count = 4;
        callContext.where = 17185;
        return this.module.match4(this, obj, obj2, obj3, obj4, callContext);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        callContext.count = objArr.length;
        callContext.where = 0;
        return this.module.matchN(this, objArr, callContext);
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        int i = callContext.pc;
        if (i == 0) {
            obj = apply0();
        } else if (i == 1) {
            obj = apply1(callContext.value1);
        } else if (i == 2) {
            obj = apply2(callContext.value1, callContext.value2);
        } else if (i == 3) {
            obj = apply3(callContext.value1, callContext.value2, callContext.value3);
        } else if (i == 4) {
            obj = apply4(callContext.value1, callContext.value2, callContext.value3, callContext.value4);
        } else if (i == 5) {
            obj = applyN(callContext.values);
        } else {
            throw new Error("internal error - apply " + this);
        }
        callContext.writeValue(obj);
    }

    public Object apply0() throws Throwable {
        return this.module.apply0(this);
    }

    public Object apply1(Object obj) throws Throwable {
        return this.module.apply1(this, obj);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        return this.module.apply2(this, obj, obj2);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        return this.module.apply3(this, obj, obj2, obj3);
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        return this.module.apply4(this, obj, obj2, obj3, obj4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        return this.module.applyN(this, objArr);
    }

    public static Object apply0Default(ModuleMethod moduleMethod) throws Throwable {
        return moduleMethod.module.applyN(moduleMethod, Values.noArgs);
    }

    public static Object apply1Default(ModuleMethod moduleMethod, Object obj) throws Throwable {
        return moduleMethod.module.applyN(moduleMethod, new Object[]{obj});
    }

    public static Object apply2Default(ModuleMethod moduleMethod, Object obj, Object obj2) throws Throwable {
        return moduleMethod.module.applyN(moduleMethod, new Object[]{obj, obj2});
    }

    public static Object apply3Default(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) throws Throwable {
        return moduleMethod.module.applyN(moduleMethod, new Object[]{obj, obj2, obj3});
    }

    public static Object apply4Default(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        return moduleMethod.module.applyN(moduleMethod, new Object[]{obj, obj2, obj3, obj4});
    }

    public static Object applyNDefault(ModuleMethod moduleMethod, Object[] objArr) throws Throwable {
        int length = objArr.length;
        int numArgs2 = moduleMethod.numArgs();
        ModuleBody moduleBody = moduleMethod.module;
        if (length >= (numArgs2 & 4095) && (numArgs2 < 0 || length <= (numArgs2 >> 12))) {
            if (length == 0) {
                return moduleBody.apply0(moduleMethod);
            }
            if (length == 1) {
                return moduleBody.apply1(moduleMethod, objArr[0]);
            }
            if (length == 2) {
                return moduleBody.apply2(moduleMethod, objArr[0], objArr[1]);
            }
            if (length == 3) {
                return moduleBody.apply3(moduleMethod, objArr[0], objArr[1], objArr[2]);
            }
            if (length == 4) {
                Object obj = objArr[0];
                return moduleBody.apply4(moduleMethod, obj, objArr[1], objArr[2], objArr[3]);
            }
        }
        throw new WrongArguments(moduleMethod, length);
    }

    public static void applyError() {
        throw new Error("internal error - bad selector");
    }
}
