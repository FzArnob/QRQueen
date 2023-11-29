package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class LogWithProperties extends AbstractLog {
    private static final String PROPERTIES = "properties";
    private Map<String, String> properties;

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> map) {
        this.properties = map;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setProperties(JSONUtils.readMap(jSONObject, PROPERTIES));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        JSONUtils.writeMap(jSONStringer, PROPERTIES, getProperties());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Map<String, String> map = this.properties;
        Map<String, String> map2 = ((LogWithProperties) obj).properties;
        if (map != null) {
            return map.equals(map2);
        }
        if (map2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Map<String, String> map = this.properties;
        return hashCode + (map != null ? map.hashCode() : 0);
    }
}
