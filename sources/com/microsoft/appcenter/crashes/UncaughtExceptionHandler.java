package com.microsoft.appcenter.crashes;

import com.microsoft.appcenter.utils.ShutdownHelper;
import java.lang.Thread;

class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    private boolean mIgnoreDefaultExceptionHandler = false;

    UncaughtExceptionHandler() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Crashes.getInstance().saveUncaughtException(thread, th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultUncaughtExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            ShutdownHelper.shutdown(10);
        }
    }

    /* access modifiers changed from: package-private */
    public void setIgnoreDefaultExceptionHandler(boolean z) {
        this.mIgnoreDefaultExceptionHandler = z;
        if (z) {
            this.mDefaultUncaughtExceptionHandler = null;
        }
    }

    /* access modifiers changed from: package-private */
    public Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return this.mDefaultUncaughtExceptionHandler;
    }

    /* access modifiers changed from: package-private */
    public void register() {
        if (!this.mIgnoreDefaultExceptionHandler) {
            this.mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        } else {
            this.mDefaultUncaughtExceptionHandler = null;
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* access modifiers changed from: package-private */
    public void unregister() {
        Thread.setDefaultUncaughtExceptionHandler(this.mDefaultUncaughtExceptionHandler);
    }
}
