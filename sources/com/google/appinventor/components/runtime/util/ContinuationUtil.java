package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.mapping.Procedure;
import java.util.concurrent.Callable;

public final class ContinuationUtil {
    private ContinuationUtil() {
    }

    public static <T> Continuation<T> wrap(final Procedure procedure, final Class<T> cls) {
        return new Continuation<T>() {
            public final void call(T t) {
                try {
                    if (cls == Void.class) {
                        procedure.apply0();
                    } else {
                        procedure.apply1(t);
                    }
                } catch (Throwable th) {
                    throw new YailRuntimeError(th.getMessage(), th.getClass().getSimpleName());
                }
            }
        };
    }

    public static <T> void callWithContinuation(final Callable<T> callable, final Continuation<T> continuation) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    continuation.call(callable.call());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static <T> T callWithContinuationSync(final Callable<T> callable) {
        final Synchronizer synchronizer = new Synchronizer();
        callWithContinuation(new Callable<T>() {
            public final T call() {
                try {
                    return callable.call();
                } catch (Throwable th) {
                    synchronizer.caught(th);
                    return null;
                }
            }
        }, new Continuation<T>() {
            public final void call(T t) {
                if (synchronizer.getThrowable() != null) {
                    synchronizer.wakeup(t);
                }
            }
        });
        Throwable throwable = synchronizer.getThrowable();
        if (throwable == null) {
            return synchronizer.getResult();
        }
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        }
        throw new RuntimeException("Exception in call", throwable);
    }
}
