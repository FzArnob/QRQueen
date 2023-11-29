package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class AbstractLog implements Log {
    static final String DEVICE = "device";
    private static final String DISTRIBUTION_GROUP_ID = "distributionGroupId";
    private static final String SID = "sid";
    private static final String TIMESTAMP = "timestamp";
    private static final String USER_ID = "userId";
    private Device device;
    private String distributionGroupId;
    private UUID sid;
    private Object tag;
    private Date timestamp;
    private final Set<String> transmissionTargetTokens = new LinkedHashSet();
    private String userId;

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public UUID getSid() {
        return this.sid;
    }

    public void setSid(UUID uuid) {
        this.sid = uuid;
    }

    public String getDistributionGroupId() {
        return this.distributionGroupId;
    }

    public void setDistributionGroupId(String str) {
        this.distributionGroupId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device2) {
        this.device = device2;
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized void addTransmissionTarget(String str) {
        this.transmissionTargetTokens.add(str);
    }

    public synchronized Set<String> getTransmissionTargetTokens() {
        return Collections.unmodifiableSet(this.transmissionTargetTokens);
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.write(jSONStringer, CommonProperties.TYPE, getType());
        jSONStringer.key(TIMESTAMP).value(JSONDateUtils.toString(getTimestamp()));
        JSONUtils.write(jSONStringer, SID, getSid());
        JSONUtils.write(jSONStringer, DISTRIBUTION_GROUP_ID, getDistributionGroupId());
        JSONUtils.write(jSONStringer, USER_ID, getUserId());
        if (getDevice() != null) {
            jSONStringer.key(DEVICE).object();
            getDevice().write(jSONStringer);
            jSONStringer.endObject();
        }
    }

    public void read(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getString(CommonProperties.TYPE).equals(getType())) {
            setTimestamp(JSONDateUtils.toDate(jSONObject.getString(TIMESTAMP)));
            if (jSONObject.has(SID)) {
                setSid(UUID.fromString(jSONObject.getString(SID)));
            }
            setDistributionGroupId(jSONObject.optString(DISTRIBUTION_GROUP_ID, (String) null));
            setUserId(jSONObject.optString(USER_ID, (String) null));
            if (jSONObject.has(DEVICE)) {
                Device device2 = new Device();
                device2.read(jSONObject.getJSONObject(DEVICE));
                setDevice(device2);
                return;
            }
            return;
        }
        throw new JSONException("Invalid type");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractLog abstractLog = (AbstractLog) obj;
        if (!this.transmissionTargetTokens.equals(abstractLog.transmissionTargetTokens)) {
            return false;
        }
        Date date = this.timestamp;
        if (date == null ? abstractLog.timestamp != null : !date.equals(abstractLog.timestamp)) {
            return false;
        }
        UUID uuid = this.sid;
        if (uuid == null ? abstractLog.sid != null : !uuid.equals(abstractLog.sid)) {
            return false;
        }
        String str = this.distributionGroupId;
        if (str == null ? abstractLog.distributionGroupId != null : !str.equals(abstractLog.distributionGroupId)) {
            return false;
        }
        String str2 = this.userId;
        if (str2 == null ? abstractLog.userId != null : !str2.equals(abstractLog.userId)) {
            return false;
        }
        Device device2 = this.device;
        if (device2 == null ? abstractLog.device != null : !device2.equals(abstractLog.device)) {
            return false;
        }
        Object obj2 = this.tag;
        Object obj3 = abstractLog.tag;
        if (obj2 != null) {
            return obj2.equals(obj3);
        }
        if (obj3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.transmissionTargetTokens.hashCode() * 31;
        Date date = this.timestamp;
        int i = 0;
        int hashCode2 = (hashCode + (date != null ? date.hashCode() : 0)) * 31;
        UUID uuid = this.sid;
        int hashCode3 = (hashCode2 + (uuid != null ? uuid.hashCode() : 0)) * 31;
        String str = this.distributionGroupId;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.userId;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Device device2 = this.device;
        int hashCode6 = (hashCode5 + (device2 != null ? device2.hashCode() : 0)) * 31;
        Object obj = this.tag;
        if (obj != null) {
            i = obj.hashCode();
        }
        return hashCode6 + i;
    }
}
