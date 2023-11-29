package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class MetadataExtension implements Model {
    private JSONObject mMetadata = new JSONObject();

    public JSONObject getMetadata() {
        return this.mMetadata;
    }

    public void read(JSONObject jSONObject) {
        this.mMetadata = jSONObject;
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        Iterator<String> keys = this.mMetadata.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONStringer.key(next).value(this.mMetadata.get(next));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mMetadata.toString().equals(((MetadataExtension) obj).mMetadata.toString());
    }

    public int hashCode() {
        return this.mMetadata.toString().hashCode();
    }
}
