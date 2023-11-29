package com.microsoft.appcenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.util.Map;

public abstract class AbstractAppCenterService implements AppCenterService {
    private static final String PREFERENCE_KEY_SEPARATOR = "_";
    /* access modifiers changed from: protected */
    public Channel mChannel;
    private AppCenterHandler mHandler;

    /* access modifiers changed from: protected */
    public Channel.GroupListener getChannelListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getGroupName();

    public Map<String, LogFactory> getLogFactories() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getLoggerTag();

    /* access modifiers changed from: protected */
    public int getTriggerCount() {
        return 50;
    }

    /* access modifiers changed from: protected */
    public long getTriggerInterval() {
        return 3000;
    }

    /* access modifiers changed from: protected */
    public int getTriggerMaxParallelRequests() {
        return 3;
    }

    public boolean isAppSecretRequired() {
        return true;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onApplicationEnterBackground() {
    }

    public void onApplicationEnterForeground() {
    }

    public void onConfigurationUpdated(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public synchronized AppCenterFuture<Boolean> isInstanceEnabledAsync() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        postAsyncGetter(new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(true);
            }
        }, defaultAppCenterFuture, false);
        return defaultAppCenterFuture;
    }

    /* access modifiers changed from: protected */
    public final synchronized AppCenterFuture<Void> setInstanceEnabledAsync(final boolean z) {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        AnonymousClass2 r1 = new Runnable() {
            public void run() {
                AppCenterLog.error("AppCenter", "App Center SDK is disabled.");
                defaultAppCenterFuture.complete(null);
            }
        };
        AnonymousClass3 r2 = new Runnable() {
            public void run() {
                AbstractAppCenterService.this.setInstanceEnabled(z);
                defaultAppCenterFuture.complete(null);
            }
        };
        if (!post(r2, r1, r2)) {
            defaultAppCenterFuture.complete(null);
        }
        return defaultAppCenterFuture;
    }

    public synchronized boolean isInstanceEnabled() {
        return SharedPreferencesManager.getBoolean(getEnabledPreferenceKey(), true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0082, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setInstanceEnabled(boolean r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            boolean r0 = r12.isInstanceEnabled()     // Catch:{ all -> 0x0083 }
            r1 = 1
            r2 = 0
            r3 = 2
            if (r13 != r0) goto L_0x002a
            java.lang.String r0 = r12.getLoggerTag()     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = "%s service has already been %s."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r12.getServiceName()     // Catch:{ all -> 0x0083 }
            r3[r2] = r5     // Catch:{ all -> 0x0083 }
            if (r13 == 0) goto L_0x001d
            java.lang.String r13 = "enabled"
            goto L_0x001f
        L_0x001d:
            java.lang.String r13 = "disabled"
        L_0x001f:
            r3[r1] = r13     // Catch:{ all -> 0x0083 }
            java.lang.String r13 = java.lang.String.format(r4, r3)     // Catch:{ all -> 0x0083 }
            com.microsoft.appcenter.utils.AppCenterLog.info(r0, r13)     // Catch:{ all -> 0x0083 }
            monitor-exit(r12)
            return
        L_0x002a:
            java.lang.String r5 = r12.getGroupName()     // Catch:{ all -> 0x0083 }
            com.microsoft.appcenter.channel.Channel r4 = r12.mChannel     // Catch:{ all -> 0x0083 }
            if (r4 == 0) goto L_0x0053
            if (r5 == 0) goto L_0x0053
            if (r13 == 0) goto L_0x004b
            int r6 = r12.getTriggerCount()     // Catch:{ all -> 0x0083 }
            long r7 = r12.getTriggerInterval()     // Catch:{ all -> 0x0083 }
            int r9 = r12.getTriggerMaxParallelRequests()     // Catch:{ all -> 0x0083 }
            r10 = 0
            com.microsoft.appcenter.channel.Channel$GroupListener r11 = r12.getChannelListener()     // Catch:{ all -> 0x0083 }
            r4.addGroup(r5, r6, r7, r9, r10, r11)     // Catch:{ all -> 0x0083 }
            goto L_0x0053
        L_0x004b:
            r4.clear(r5)     // Catch:{ all -> 0x0083 }
            com.microsoft.appcenter.channel.Channel r0 = r12.mChannel     // Catch:{ all -> 0x0083 }
            r0.removeGroup(r5)     // Catch:{ all -> 0x0083 }
        L_0x0053:
            java.lang.String r0 = r12.getEnabledPreferenceKey()     // Catch:{ all -> 0x0083 }
            com.microsoft.appcenter.utils.storage.SharedPreferencesManager.putBoolean(r0, r13)     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = r12.getLoggerTag()     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = "%s service has been %s."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r12.getServiceName()     // Catch:{ all -> 0x0083 }
            r3[r2] = r5     // Catch:{ all -> 0x0083 }
            if (r13 == 0) goto L_0x006d
            java.lang.String r2 = "enabled"
            goto L_0x006f
        L_0x006d:
            java.lang.String r2 = "disabled"
        L_0x006f:
            r3[r1] = r2     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = java.lang.String.format(r4, r3)     // Catch:{ all -> 0x0083 }
            com.microsoft.appcenter.utils.AppCenterLog.info(r0, r1)     // Catch:{ all -> 0x0083 }
            boolean r0 = r12.isStarted()     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0081
            r12.applyEnabledState(r13)     // Catch:{ all -> 0x0083 }
        L_0x0081:
            monitor-exit(r12)
            return
        L_0x0083:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.AbstractAppCenterService.setInstanceEnabled(boolean):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void applyEnabledState(boolean z) {
    }

    public final synchronized void onStarting(AppCenterHandler appCenterHandler) {
        this.mHandler = appCenterHandler;
    }

    public synchronized void onStarted(Context context, Channel channel, String str, String str2, boolean z) {
        String groupName = getGroupName();
        boolean isInstanceEnabled = isInstanceEnabled();
        if (groupName != null) {
            channel.removeGroup(groupName);
            if (isInstanceEnabled) {
                channel.addGroup(groupName, getTriggerCount(), getTriggerInterval(), getTriggerMaxParallelRequests(), (Ingestion) null, getChannelListener());
            } else {
                channel.clear(groupName);
            }
        }
        this.mChannel = channel;
        applyEnabledState(isInstanceEnabled);
    }

    /* access modifiers changed from: protected */
    public boolean isStarted() {
        return this.mChannel != null;
    }

    /* access modifiers changed from: protected */
    public String getEnabledPreferenceKey() {
        return "enabled_" + getServiceName();
    }

    /* access modifiers changed from: protected */
    public synchronized void post(Runnable runnable) {
        post(runnable, (Runnable) null, (Runnable) null);
    }

    /* access modifiers changed from: protected */
    public synchronized boolean post(final Runnable runnable, Runnable runnable2, final Runnable runnable3) {
        AppCenterHandler appCenterHandler = this.mHandler;
        if (appCenterHandler == null) {
            AppCenterLog.error("AppCenter", getServiceName() + " needs to be started before it can be used.");
            return false;
        }
        appCenterHandler.post(new Runnable() {
            public void run() {
                if (AbstractAppCenterService.this.isInstanceEnabled()) {
                    runnable.run();
                    return;
                }
                Runnable runnable = runnable3;
                if (runnable != null) {
                    runnable.run();
                    return;
                }
                AppCenterLog.info("AppCenter", AbstractAppCenterService.this.getServiceName() + " service disabled, discarding calls.");
            }
        }, runnable2);
        return true;
    }

    /* access modifiers changed from: protected */
    public synchronized <T> void postAsyncGetter(final Runnable runnable, final DefaultAppCenterFuture<T> defaultAppCenterFuture, final T t) {
        AnonymousClass5 r0 = new Runnable() {
            public void run() {
                defaultAppCenterFuture.complete(t);
            }
        };
        if (!post(new Runnable() {
            public void run() {
                runnable.run();
            }
        }, r0, r0)) {
            r0.run();
        }
    }
}
