package com.microsoft.appcenter.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private final Map<String, String> headers;
    private final String payload;
    private final int statusCode;

    public HttpResponse(int i) {
        this(i, "");
    }

    public HttpResponse(int i, String str) {
        this(i, str, new HashMap());
    }

    public HttpResponse(int i, String str, Map<String, String> map) {
        this.payload = str;
        this.statusCode = i;
        this.headers = map;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getPayload() {
        return this.payload;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpResponse httpResponse = (HttpResponse) obj;
        if (this.statusCode != httpResponse.statusCode || !this.payload.equals(httpResponse.payload) || !this.headers.equals(httpResponse.headers)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.statusCode * 31) + this.payload.hashCode()) * 31) + this.headers.hashCode();
    }
}
