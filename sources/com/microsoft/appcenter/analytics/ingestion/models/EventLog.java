package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import com.microsoft.appcenter.ingestion.models.properties.TypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.TypedPropertyUtils;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class EventLog extends LogWithNameAndProperties {
    public static final String TYPE = "event";
    private UUID id;
    private List<TypedProperty> typedProperties;

    public String getType() {
        return "event";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public List<TypedProperty> getTypedProperties() {
        return this.typedProperties;
    }

    public void setTypedProperties(List<TypedProperty> list) {
        this.typedProperties = list;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setId(UUID.fromString(jSONObject.getString(CommonProperties.ID)));
        setTypedProperties(TypedPropertyUtils.read(jSONObject));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        jSONStringer.key(CommonProperties.ID).value(getId());
        JSONUtils.writeArray(jSONStringer, CommonProperties.TYPED_PROPERTIES, getTypedProperties());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        EventLog eventLog = (EventLog) obj;
        UUID uuid = this.id;
        if (uuid == null ? eventLog.id != null : !uuid.equals(eventLog.id)) {
            return false;
        }
        List<TypedProperty> list = this.typedProperties;
        List<TypedProperty> list2 = eventLog.typedProperties;
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
        UUID uuid = this.id;
        int i = 0;
        int hashCode2 = (hashCode + (uuid != null ? uuid.hashCode() : 0)) * 31;
        List<TypedProperty> list = this.typedProperties;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }
}
