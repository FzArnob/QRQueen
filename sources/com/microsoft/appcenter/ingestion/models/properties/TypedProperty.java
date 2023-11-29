package com.microsoft.appcenter.ingestion.models.properties;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class TypedProperty implements Model {
    private String name;

    public abstract String getType();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getString(CommonProperties.TYPE).equals(getType())) {
            setName(jSONObject.getString(CommonProperties.NAME));
            return;
        }
        throw new JSONException("Invalid type");
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        jSONStringer.key(CommonProperties.TYPE).value(getType());
        jSONStringer.key(CommonProperties.NAME).value(getName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.name;
        String str2 = ((TypedProperty) obj).name;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
