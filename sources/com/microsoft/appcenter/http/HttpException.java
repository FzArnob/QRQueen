package com.microsoft.appcenter.http;

import android.text.TextUtils;
import java.io.IOException;

public class HttpException extends IOException {
    private final HttpResponse mHttpResponse;

    public HttpException(HttpResponse httpResponse) {
        super(getDetailMessage(httpResponse.getStatusCode(), httpResponse.getPayload()));
        this.mHttpResponse = httpResponse;
    }

    private static String getDetailMessage(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return String.valueOf(i);
        }
        return i + " - " + str;
    }

    public HttpResponse getHttpResponse() {
        return this.mHttpResponse;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mHttpResponse.equals(((HttpException) obj).mHttpResponse);
    }

    public int hashCode() {
        return this.mHttpResponse.hashCode();
    }
}
