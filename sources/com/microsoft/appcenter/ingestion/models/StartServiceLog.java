package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class StartServiceLog extends AbstractLog {
    private static final String IS_ONE_COLLECTOR_ENABLED = "isOneCollectorEnabled";
    private static final String SERVICES = "services";
    public static final String TYPE = "startService";
    private Boolean isOneCollectorEnabled = null;
    private List<String> services;

    public String getType() {
        return TYPE;
    }

    public List<String> getServices() {
        return this.services;
    }

    public void setServices(List<String> list) {
        this.services = list;
    }

    public void oneCollectorEnabled(Boolean bool) {
        this.isOneCollectorEnabled = bool;
    }

    public Boolean isOneCollectorEnabled() {
        return this.isOneCollectorEnabled;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setServices(JSONUtils.readStringArray(jSONObject, "services"));
        oneCollectorEnabled(JSONUtils.readBoolean(jSONObject, IS_ONE_COLLECTOR_ENABLED));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        JSONUtils.writeStringArray(jSONStringer, "services", getServices());
        JSONUtils.write(jSONStringer, IS_ONE_COLLECTOR_ENABLED, isOneCollectorEnabled());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        List<String> list = this.services;
        List<String> list2 = ((StartServiceLog) obj).services;
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
        List<String> list = this.services;
        return hashCode + (list != null ? list.hashCode() : 0);
    }
}
