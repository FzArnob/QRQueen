package com.microsoft.appcenter.ingestion;

import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import java.io.Closeable;
import java.util.UUID;

public interface Ingestion extends Closeable {
    boolean isEnabled();

    void reopen();

    ServiceCall sendAsync(String str, UUID uuid, LogContainer logContainer, ServiceCallback serviceCallback) throws IllegalArgumentException;

    void setLogUrl(String str);
}
