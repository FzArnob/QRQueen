package com.microsoft.appcenter.ingestion;

import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.utils.PrefStorageConstants;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractAppCenterIngestion implements Ingestion {
    private HttpClient mHttpClient;
    private String mLogUrl;

    public ServiceCall sendAsync(String str, UUID uuid, LogContainer logContainer, ServiceCallback serviceCallback) throws IllegalArgumentException {
        return null;
    }

    public AbstractAppCenterIngestion() {
    }

    public AbstractAppCenterIngestion(HttpClient httpClient, String str) {
        this.mLogUrl = str;
        this.mHttpClient = httpClient;
    }

    public void setLogUrl(String str) {
        this.mLogUrl = str;
    }

    public String getLogUrl() {
        return this.mLogUrl;
    }

    public boolean isEnabled() {
        return SharedPreferencesManager.getBoolean(PrefStorageConstants.ALLOWED_NETWORK_REQUEST, true);
    }

    public void reopen() {
        this.mHttpClient.reopen();
    }

    public void close() throws IOException {
        this.mHttpClient.close();
    }

    public void setHttpClient(HttpClient httpClient) {
        this.mHttpClient = httpClient;
    }

    public ServiceCall getServiceCall(String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback) {
        if (isEnabled()) {
            return this.mHttpClient.callAsync(str, str2, map, callTemplate, serviceCallback);
        }
        serviceCallback.onCallFailed(new ConnectException("SDK is in offline mode."));
        return null;
    }
}
