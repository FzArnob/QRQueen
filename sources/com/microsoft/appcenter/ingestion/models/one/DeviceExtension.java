package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class DeviceExtension implements Model {
    private static final String LOCAL_ID = "localId";
    private String localId;

    public String getLocalId() {
        return this.localId;
    }

    public void setLocalId(String str) {
        this.localId = str;
    }

    public void read(JSONObject jSONObject) {
        setLocalId(jSONObject.optString(LOCAL_ID, (String) null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, LOCAL_ID, getLocalId());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.localId;
        String str2 = ((DeviceExtension) obj).localId;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.localId;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
