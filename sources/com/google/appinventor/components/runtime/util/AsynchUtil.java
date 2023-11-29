package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class AsynchUtil {
    private static final String LOG_TAG = "AsynchUtil";

    public static void runAsynchronously(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void runAsynchronously(final Handler handler, final Runnable runnable, final Runnable runnable2) {
        new Thread(new Runnable() {
            public final void run() {
                runnable.run();
                if (runnable2 != null) {
                    handler.post(new Runnable() {
                        public final void run() {
                            runnable2.run();
                        }
                    });
                }
            }
        }).start();
    }

    public static boolean isUiThread() {
        return Looper.getMainLooper().equals(Looper.myLooper());
    }

    public static <T> void finish(Synchronizer<T> synchronizer, Continuation<T> continuation) {
        Log.d(LOG_TAG, "Waiting for synchronizer result");
        synchronizer.waitfor();
        if (synchronizer.getThrowable() == null) {
            continuation.call(synchronizer.getResult());
            return;
        }
        Throwable throwable = synchronizer.getThrowable();
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        }
        throw new YailRuntimeError(throwable.toString(), throwable.getClass().getSimpleName());
    }
}
