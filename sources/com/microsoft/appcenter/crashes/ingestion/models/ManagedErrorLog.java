package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.crashes.ingestion.models.json.ThreadFactory;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ManagedErrorLog extends AbstractErrorLog {
    private static final String EXCEPTION = "exception";
    private static final String THREADS = "threads";
    public static final String TYPE = "managedError";
    private Exception exception;
    private List<Thread> threads;

    public String getType() {
        return TYPE;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception2) {
        this.exception = exception2;
    }

    public List<Thread> getThreads() {
        return this.threads;
    }

    public void setThreads(List<Thread> list) {
        this.threads = list;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        if (jSONObject.has(EXCEPTION)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(EXCEPTION);
            Exception exception2 = new Exception();
            exception2.read(jSONObject2);
            setException(exception2);
        }
        setThreads(JSONUtils.readArray(jSONObject, THREADS, ThreadFactory.getInstance()));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        if (getException() != null) {
            jSONStringer.key(EXCEPTION).object();
            this.exception.write(jSONStringer);
            jSONStringer.endObject();
        }
        JSONUtils.writeArray(jSONStringer, THREADS, getThreads());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        ManagedErrorLog managedErrorLog = (ManagedErrorLog) obj;
        Exception exception2 = this.exception;
        if (exception2 == null ? managedErrorLog.exception != null : !exception2.equals(managedErrorLog.exception)) {
            return false;
        }
        List<Thread> list = this.threads;
        List<Thread> list2 = managedErrorLog.threads;
        if (list != null) {
            return list.equals(list2);
        }
        if (list2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Exception exception2 = this.exception;
        int i = 0;
        int hashCode2 = (hashCode + (exception2 != null ? exception2.hashCode() : 0)) * 31;
        List<Thread> list = this.threads;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }
}
