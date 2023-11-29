package com.microsoft.appcenter.ingestion.models.properties;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LongTypedProperty extends TypedProperty {
    public static final String TYPE = "long";
    private long value;

    public String getType() {
        return TYPE;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long j) {
        this.value = j;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setValue(jSONObject.getLong(CommonProperties.VALUE));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        jSONStringer.key(CommonProperties.VALUE).value(getValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj) || this.value != ((LongTypedProperty) obj).value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.value;
        return (super.hashCode() * 31) + ((int) (j ^ (j >>> 32)));
    }
}
