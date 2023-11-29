package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class DefaultLogSerializer implements LogSerializer {
    private static final String LOGS = "logs";
    private final Map<String, LogFactory> mLogFactories = new HashMap();

    private JSONStringer writeLog(JSONStringer jSONStringer, Log log) throws JSONException {
        jSONStringer.object();
        log.write(jSONStringer);
        jSONStringer.endObject();
        return jSONStringer;
    }

    private Log readLog(JSONObject jSONObject, String str) throws JSONException {
        if (str == null) {
            str = jSONObject.getString(CommonProperties.TYPE);
        }
        LogFactory logFactory = this.mLogFactories.get(str);
        if (logFactory != null) {
            Log create = logFactory.create();
            create.read(jSONObject);
            return create;
        }
        throw new JSONException("Unknown log type: " + str);
    }

    public String serializeLog(Log log) throws JSONException {
        return writeLog(new JSONStringer(), log).toString();
    }

    public Log deserializeLog(String str, String str2) throws JSONException {
        return readLog(new JSONObject(str), str2);
    }

    public Collection<CommonSchemaLog> toCommonSchemaLog(Log log) {
        return this.mLogFactories.get(log.getType()).toCommonSchemaLogs(log);
    }

    public String serializeContainer(LogContainer logContainer) throws JSONException {
        JSONStringer jSONStringer = new JSONStringer();
        jSONStringer.object();
        jSONStringer.key(LOGS).array();
        for (Log writeLog : logContainer.getLogs()) {
            writeLog(jSONStringer, writeLog);
        }
        jSONStringer.endArray();
        jSONStringer.endObject();
        return jSONStringer.toString();
    }

    public LogContainer deserializeContainer(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        LogContainer logContainer = new LogContainer();
        JSONArray jSONArray = jSONObject.getJSONArray(LOGS);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(readLog(jSONArray.getJSONObject(i), str2));
        }
        logContainer.setLogs(arrayList);
        return logContainer;
    }

    public void addLogFactory(String str, LogFactory logFactory) {
        this.mLogFactories.put(str, logFactory);
    }
}
