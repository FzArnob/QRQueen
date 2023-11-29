package com.microsoft.appcenter.utils;

import android.os.Handler;
import android.os.Looper;

public class HandlerUtils {
    static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        Handler handler = sMainHandler;
        if (currentThread == handler.getLooper().getThread()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    public static Handler getMainHandler() {
        return sMainHandler;
    }
}
