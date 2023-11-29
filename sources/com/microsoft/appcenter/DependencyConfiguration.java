package com.microsoft.appcenter;

import com.microsoft.appcenter.http.HttpClient;

public final class DependencyConfiguration {
    private static HttpClient sHttpClient;

    DependencyConfiguration() {
    }

    public static HttpClient getHttpClient() {
        return sHttpClient;
    }

    public static void setHttpClient(HttpClient httpClient) {
        sHttpClient = httpClient;
    }
}
