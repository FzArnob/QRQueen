package com.microsoft.appcenter.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import java.io.Closeable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public class NetworkStateHelper implements Closeable {
    private static NetworkStateHelper sSharedInstance;
    private final AtomicBoolean mConnected = new AtomicBoolean();
    private final ConnectivityManager mConnectivityManager;
    private final Set<Listener> mListeners = new CopyOnWriteArraySet();
    private ConnectivityManager.NetworkCallback mNetworkCallback;

    public interface Listener {
        void onNetworkStateUpdated(boolean z);
    }

    public NetworkStateHelper(Context context) {
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        reopen();
    }

    public static synchronized void unsetInstance() {
        synchronized (NetworkStateHelper.class) {
            sSharedInstance = null;
        }
    }

    public static synchronized NetworkStateHelper getSharedInstance(Context context) {
        NetworkStateHelper networkStateHelper;
        synchronized (NetworkStateHelper.class) {
            if (sSharedInstance == null) {
                sSharedInstance = new NetworkStateHelper(context);
            }
            networkStateHelper = sSharedInstance;
        }
        return networkStateHelper;
    }

    public void reopen() {
        try {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12);
            this.mNetworkCallback = new ConnectivityManager.NetworkCallback() {
                public void onAvailable(Network network) {
                    NetworkStateHelper.this.onNetworkAvailable(network);
                }

                public void onLost(Network network) {
                    NetworkStateHelper.this.onNetworkLost(network);
                }
            };
            this.mConnectivityManager.registerNetworkCallback(builder.build(), this.mNetworkCallback);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Cannot access network state information.", e);
            this.mConnected.set(true);
        }
    }

    public boolean isNetworkConnected() {
        return this.mConnected.get() || isAnyNetworkConnected();
    }

    private boolean isAnyNetworkConnected() {
        Network[] allNetworks = this.mConnectivityManager.getAllNetworks();
        if (allNetworks == null) {
            return false;
        }
        for (Network networkInfo : allNetworks) {
            NetworkInfo networkInfo2 = this.mConnectivityManager.getNetworkInfo(networkInfo);
            if (networkInfo2 != null && networkInfo2.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onNetworkAvailable(Network network) {
        AppCenterLog.debug("AppCenter", "Network " + network + " is available.");
        if (this.mConnected.compareAndSet(false, true)) {
            notifyNetworkStateUpdated(true);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNetworkLost(android.net.Network r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Network "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = " is lost."
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "AppCenter"
            com.microsoft.appcenter.utils.AppCenterLog.debug(r1, r0)
            android.net.ConnectivityManager r0 = r4.mConnectivityManager
            android.net.Network[] r0 = r0.getAllNetworks()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0035
            int r3 = r0.length
            if (r3 == 0) goto L_0x0035
            android.net.Network[] r3 = new android.net.Network[r1]
            r3[r2] = r5
            boolean r5 = java.util.Arrays.equals(r0, r3)
            if (r5 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r5 = 0
            goto L_0x0036
        L_0x0035:
            r5 = 1
        L_0x0036:
            if (r5 == 0) goto L_0x0043
            java.util.concurrent.atomic.AtomicBoolean r5 = r4.mConnected
            boolean r5 = r5.compareAndSet(r1, r2)
            if (r5 == 0) goto L_0x0043
            r4.notifyNetworkStateUpdated(r2)
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.NetworkStateHelper.onNetworkLost(android.net.Network):void");
    }

    private void notifyNetworkStateUpdated(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("Network has been ");
        sb.append(z ? "connected." : "disconnected.");
        AppCenterLog.debug("AppCenter", sb.toString());
        for (Listener onNetworkStateUpdated : this.mListeners) {
            onNetworkStateUpdated.onNetworkStateUpdated(z);
        }
    }

    public void close() {
        this.mConnected.set(false);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }
}
