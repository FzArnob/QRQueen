package gnu.expr;

import com.google.appinventor.components.runtime.util.ErrorMessages;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.WriterManager;
import kawa.Shell;

public abstract class ModuleBody extends Procedure0 {
    private static int exitCounter;
    private static boolean mainPrintValues;
    protected boolean runDone;

    public void run(CallContext callContext) throws Throwable {
    }

    public void apply(CallContext callContext) throws Throwable {
        if (callContext.pc == 0) {
            run(callContext);
        }
    }

    public void run() {
        synchronized (this) {
            if (!this.runDone) {
                this.runDone = true;
                run((Consumer) VoidConsumer.instance);
            }
        }
    }

    public void run(Consumer consumer) {
        CallContext instance = CallContext.getInstance();
        Consumer consumer2 = instance.consumer;
        instance.consumer = consumer;
        try {
            run(instance);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        runCleanup(instance, th, consumer2);
    }

    public static void runCleanup(CallContext callContext, Throwable th, Consumer consumer) {
        if (th == null) {
            try {
                callContext.runUntilDone();
            } catch (Throwable th2) {
                th = th2;
            }
        }
        callContext.consumer = consumer;
        if (th == null) {
            return;
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new WrappedException(th);
        }
    }

    public Object apply0() throws Throwable {
        CallContext instance = CallContext.getInstance();
        match0(instance);
        return instance.runUntilValue();
    }

    public static boolean getMainPrintValues() {
        return mainPrintValues;
    }

    public static void setMainPrintValues(boolean z) {
        mainPrintValues = z;
    }

    public static synchronized void exitIncrement() {
        synchronized (ModuleBody.class) {
            int i = exitCounter;
            if (i == 0) {
                exitCounter = i + 1;
            }
            exitCounter++;
        }
    }

    public static synchronized void exitDecrement() {
        synchronized (ModuleBody.class) {
            int i = exitCounter;
            if (i > 0) {
                int i2 = i - 1;
                if (i2 == 0) {
                    System.exit(0);
                } else {
                    exitCounter = i2;
                }
            }
        }
    }

    public final void runAsMain() {
        WriterManager.instance.registerShutdownHook();
        try {
            CallContext instance = CallContext.getInstance();
            if (getMainPrintValues()) {
                OutPort outDefault = OutPort.outDefault();
                instance.consumer = Shell.getOutputConsumer(outDefault);
                run(instance);
                instance.runUntilDone();
                outDefault.freshLine();
            } else {
                run();
                instance.runUntilDone();
            }
            OutPort.runCleanups();
            exitDecrement();
        } catch (Throwable th) {
            th.printStackTrace();
            OutPort.runCleanups();
            System.exit(-1);
        }
    }

    public Object apply0(ModuleMethod moduleMethod) throws Throwable {
        return applyN(moduleMethod, Values.noArgs);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) throws Throwable {
        return applyN(moduleMethod, new Object[]{obj});
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) throws Throwable {
        return applyN(moduleMethod, new Object[]{obj, obj2});
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) throws Throwable {
        return applyN(moduleMethod, new Object[]{obj, obj2, obj3});
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        return applyN(moduleMethod, new Object[]{obj, obj2, obj3, obj4});
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) throws Throwable {
        int length = objArr.length;
        int numArgs = moduleMethod.numArgs();
        if (length >= (numArgs & 4095) && (numArgs < 0 || length <= (numArgs >> 12))) {
            if (length == 0) {
                return apply0(moduleMethod);
            }
            if (length == 1) {
                return apply1(moduleMethod, objArr[0]);
            }
            if (length == 2) {
                return apply2(moduleMethod, objArr[0], objArr[1]);
            }
            if (length == 3) {
                return apply3(moduleMethod, objArr[0], objArr[1], objArr[2]);
            }
            if (length == 4) {
                return apply4(moduleMethod, objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        }
        throw new WrongArguments(moduleMethod, length);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int numArgs = moduleMethod.numArgs();
        int i = numArgs & 4095;
        if (i > 0) {
            return -983040 | i;
        }
        if (numArgs < 0) {
            return matchN(moduleMethod, ProcedureN.noArgs, callContext);
        }
        callContext.count = 0;
        callContext.where = 0;
        callContext.next = 0;
        callContext.proc = moduleMethod;
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
            callContext.proc = moduleMethod;
            return 0;
        }
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
            callContext.proc = moduleMethod;
            return 0;
        }
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
            callContext.proc = moduleMethod;
            return 0;
        }
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
            callContext.proc = moduleMethod;
            return 0;
        }
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
        callContext.proc = moduleMethod;
        return 0;
    }
}
