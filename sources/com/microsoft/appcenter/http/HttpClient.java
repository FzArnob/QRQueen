package com.microsoft.appcenter.http;

import java.io.Closeable;
import java.net.URL;
import java.util.Map;
import org.json.JSONException;

public interface HttpClient extends Closeable {

    public interface CallTemplate {
        String buildRequestBody() throws JSONException;

        void onBeforeCalling(URL url, Map<String, String> map);
    }

    ServiceCall callAsync(String str, String str2, Map<String, String> map, CallTemplate callTemplate, ServiceCallback serviceCallback);

    void reopen();
}
