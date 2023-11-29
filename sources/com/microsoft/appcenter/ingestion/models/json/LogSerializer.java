package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import java.util.Collection;
import org.json.JSONException;

public interface LogSerializer {
    void addLogFactory(String str, LogFactory logFactory);

    LogContainer deserializeContainer(String str, String str2) throws JSONException;

    Log deserializeLog(String str, String str2) throws JSONException;

    String serializeContainer(LogContainer logContainer) throws JSONException;

    String serializeLog(Log log) throws JSONException;

    Collection<CommonSchemaLog> toCommonSchemaLog(Log log);
}
