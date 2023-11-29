package com.microsoft.appcenter.http;

import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.NetworkStateHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HttpClientNetworkStateHandler extends HttpClientDecorator implements NetworkStateHelper.Listener {
    private final Set<Call> mCalls = new HashSet();
    private final NetworkStateHelper mNetworkStateHelper;

    public HttpClientNetworkStateHandler(HttpClient httpClient, NetworkStateHelper networkStateHelper) {
        super(httpClient);
        this.mNetworkStateHelper = networkStateHelper;
        networkStateHelper.addListener(this);
    }

    public synchronized ServiceCall callAsync(String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback) {
        Call call;
        call = new Call(this.mDecoratedApi, str, str2, map, callTemplate, serviceCallback);
        if (this.mNetworkStateHelper.isNetworkConnected()) {
            call.run();
        } else {
            this.mCalls.add(call);
            AppCenterLog.debug("AppCenter", "Call triggered with no network connectivity, waiting network to become available...");
        }
        return call;
    }

    public synchronized void close() throws IOException {
        this.mNetworkStateHelper.removeListener(this);
        this.mCalls.clear();
        super.close();
    }

    public void reopen() {
        this.mNetworkStateHelper.addListener(this);
        super.reopen();
    }

    public synchronized void onNetworkStateUpdated(boolean z) {
        if (z) {
            if (this.mCalls.size() > 0) {
                AppCenterLog.debug("AppCenter", "Network is available. " + this.mCalls.size() + " pending call(s) to submit now.");
                for (Call run : this.mCalls) {
                    run.run();
                }
                this.mCalls.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void cancelCall(Call call) {
        if (call.mServiceCall != null) {
            call.mServiceCall.cancel();
        }
        this.mCalls.remove(call);
    }

    private class Call extends HttpClientCallDecorator {
        Call(HttpClient httpClient, String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback) {
            super(httpClient, str, str2, map, callTemplate, serviceCallback);
        }

        public void cancel() {
            HttpClientNetworkStateHandler.this.cancelCall(this);
        }
    }
}
