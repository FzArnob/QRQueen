package com.microsoft.appcenter.ingestion;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.AbstractAppCallTemplate;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;

public class AppCenterIngestion extends AbstractAppCenterIngestion {
    static final String API_PATH = "/logs?api-version=1.0.0";
    public static final String DEFAULT_LOG_URL = "https://in.appcenter.ms";
    static final String INSTALL_ID = "Install-ID";
    private final LogSerializer mLogSerializer;

    public AppCenterIngestion(HttpClient httpClient, LogSerializer logSerializer) {
        super(httpClient, DEFAULT_LOG_URL);
        this.mLogSerializer = logSerializer;
    }

    public ServiceCall sendAsync(String str, UUID uuid, LogContainer logContainer, ServiceCallback serviceCallback) throws IllegalArgumentException {
        super.sendAsync(str, uuid, logContainer, serviceCallback);
        HashMap hashMap = new HashMap();
        hashMap.put(INSTALL_ID, uuid.toString());
        hashMap.put(Constants.APP_SECRET, str);
        IngestionCallTemplate ingestionCallTemplate = new IngestionCallTemplate(this.mLogSerializer, logContainer);
        return getServiceCall(getLogUrl() + API_PATH, DefaultHttpClient.METHOD_POST, hashMap, ingestionCallTemplate, serviceCallback);
    }

    private static class IngestionCallTemplate extends AbstractAppCallTemplate {
        private final LogContainer mLogContainer;
        private final LogSerializer mLogSerializer;

        IngestionCallTemplate(LogSerializer logSerializer, LogContainer logContainer) {
            this.mLogSerializer = logSerializer;
            this.mLogContainer = logContainer;
        }

        public String buildRequestBody() throws JSONException {
            return this.mLogSerializer.serializeContainer(this.mLogContainer);
        }
    }
}
