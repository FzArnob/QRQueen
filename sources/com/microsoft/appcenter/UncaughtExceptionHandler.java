package com.microsoft.appcenter;

import android.os.Handler;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.ShutdownHelper;
import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final int SHUTDOWN_TIMEOUT = 5000;
    /* access modifiers changed from: private */
    public final Channel mChannel;
    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    private final Handler mHandler;

    UncaughtExceptionHandler(Handler handler, Channel channel) {
        this.mHandler = handler;
        this.mChannel = channel;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (AppCenter.getInstance().isInstanceEnabled()) {
            final Semaphore semaphore = new Semaphore(0);
            this.mHandler.post(new Runnable() {
                public void run() {
                    UncaughtExceptionHandler.this.mChannel.shutdown();
                    AppCenterLog.debug("AppCenter", "Channel completed shutdown.");
                    semaphore.release();
                }
            });
            try {
                if (!semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                    AppCenterLog.error("AppCenter", "Timeout waiting for looper tasks to complete.");
                }
            } catch (InterruptedException e) {
                AppCenterLog.warn("AppCenter", "Interrupted while waiting looper to flush.", e);
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultUncaughtExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            ShutdownHelper.shutdown(10);
        }
    }

    /* access modifiers changed from: package-private */
    public Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return this.mDefaultUncaughtExceptionHandler;
    }

    /* access modifiers changed from: package-private */
    public void register() {
        this.mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* access modifiers changed from: package-private */
    public void unregister() {
        Thread.setDefaultUncaughtExceptionHandler(this.mDefaultUncaughtExceptionHandler);
    }
}
