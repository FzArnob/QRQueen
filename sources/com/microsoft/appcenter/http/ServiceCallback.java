package com.microsoft.appcenter.http;

public interface ServiceCallback {
    void onCallFailed(Exception exc);

    void onCallSucceeded(HttpResponse httpResponse);
}
