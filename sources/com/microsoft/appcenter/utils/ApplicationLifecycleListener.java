package com.microsoft.appcenter.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ApplicationLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private static final long TIMEOUT_MS = 700;
    private Runnable mDelayedPauseRunnable = new Runnable() {
        public void run() {
            ApplicationLifecycleListener.this.dispatchPauseIfNeeded();
            ApplicationLifecycleListener.this.dispatchStopIfNeeded();
        }
    };
    private Handler mHandler;
    private final Set<ApplicationLifecycleCallbacks> mLifecycleCallbacks = new CopyOnWriteArraySet();
    private boolean mPauseSent = true;
    private int mResumedCounter = 0;
    private int mStartedCounter = 0;
    private boolean mStopSent = true;

    public interface ApplicationLifecycleCallbacks {
        void onApplicationEnterBackground();

        void onApplicationEnterForeground();
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public ApplicationLifecycleListener(Handler handler) {
        this.mHandler = handler;
    }

    public void registerApplicationLifecycleCallbacks(ApplicationLifecycleCallbacks applicationLifecycleCallbacks) {
        this.mLifecycleCallbacks.add(applicationLifecycleCallbacks);
    }

    /* access modifiers changed from: private */
    public void dispatchPauseIfNeeded() {
        if (this.mResumedCounter == 0) {
            this.mPauseSent = true;
        }
    }

    /* access modifiers changed from: private */
    public void dispatchStopIfNeeded() {
        if (this.mStartedCounter == 0 && this.mPauseSent) {
            for (ApplicationLifecycleCallbacks onApplicationEnterBackground : this.mLifecycleCallbacks) {
                onApplicationEnterBackground.onApplicationEnterBackground();
            }
            this.mStopSent = true;
        }
    }

    public void onActivityStarted(Activity activity) {
        int i = this.mStartedCounter + 1;
        this.mStartedCounter = i;
        if (i == 1 && this.mStopSent) {
            for (ApplicationLifecycleCallbacks onApplicationEnterForeground : this.mLifecycleCallbacks) {
                onApplicationEnterForeground.onApplicationEnterForeground();
            }
            this.mStopSent = false;
        }
    }

    public void onActivityResumed(Activity activity) {
        int i = this.mResumedCounter + 1;
        this.mResumedCounter = i;
        if (i != 1) {
            return;
        }
        if (this.mPauseSent) {
            this.mPauseSent = false;
        } else {
            this.mHandler.removeCallbacks(this.mDelayedPauseRunnable);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.mStartedCounter == 0) {
            this.mStopSent = false;
        }
        int i = this.mResumedCounter;
        if (i == 0) {
            this.mPauseSent = false;
        }
        int max = Math.max(i - 1, 0);
        this.mResumedCounter = max;
        if (max == 0) {
            this.mHandler.postDelayed(this.mDelayedPauseRunnable, TIMEOUT_MS);
        }
    }

    public void onActivityStopped(Activity activity) {
        this.mStartedCounter = Math.max(this.mStartedCounter - 1, 0);
        dispatchStopIfNeeded();
    }
}
