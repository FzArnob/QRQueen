package com.microsoft.appcenter.http;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAppCallTemplate implements HttpClient.CallTemplate {
    public void onBeforeCalling(URL url, Map<String, String> map) {
        if (AppCenterLog.getLogLevel() <= 2) {
            AppCenterLog.verbose("AppCenter", "Calling " + url + "...");
            HashMap hashMap = new HashMap(map);
            String str = (String) hashMap.get(Constants.APP_SECRET);
            if (str != null) {
                hashMap.put(Constants.APP_SECRET, HttpUtils.hideSecret(str));
            }
            AppCenterLog.verbose("AppCenter", "Headers: " + hashMap);
        }
    }
}
