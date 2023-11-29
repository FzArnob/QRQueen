package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LocExtension implements Model {
    private static final String TZ = "tz";
    private String tz;

    public String getTz() {
        return this.tz;
    }

    public void setTz(String str) {
        this.tz = str;
    }

    public void read(JSONObject jSONObject) {
        setTz(jSONObject.optString(TZ, (String) null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, TZ, getTz());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.tz;
        String str2 = ((LocExtension) obj).tz;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.tz;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
