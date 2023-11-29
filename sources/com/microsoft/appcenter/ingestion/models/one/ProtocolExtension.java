package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ProtocolExtension implements Model {
    private static final String DEV_MAKE = "devMake";
    private static final String DEV_MODEL = "devModel";
    private static final String TICKET_KEYS = "ticketKeys";
    private String devMake;
    private String devModel;
    private List<String> ticketKeys;

    public List<String> getTicketKeys() {
        return this.ticketKeys;
    }

    public void setTicketKeys(List<String> list) {
        this.ticketKeys = list;
    }

    public String getDevMake() {
        return this.devMake;
    }

    public void setDevMake(String str) {
        this.devMake = str;
    }

    public String getDevModel() {
        return this.devModel;
    }

    public void setDevModel(String str) {
        this.devModel = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        setTicketKeys(JSONUtils.readStringArray(jSONObject, TICKET_KEYS));
        setDevMake(jSONObject.optString(DEV_MAKE, (String) null));
        setDevModel(jSONObject.optString(DEV_MODEL, (String) null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        JSONUtils.writeStringArray(jSONStringer, TICKET_KEYS, getTicketKeys());
        JSONUtils.write(jSONStringer, DEV_MAKE, getDevMake());
        JSONUtils.write(jSONStringer, DEV_MODEL, getDevModel());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProtocolExtension protocolExtension = (ProtocolExtension) obj;
        List<String> list = this.ticketKeys;
        if (list == null ? protocolExtension.ticketKeys != null : !list.equals(protocolExtension.ticketKeys)) {
            return false;
        }
        String str = this.devMake;
        if (str == null ? protocolExtension.devMake != null : !str.equals(protocolExtension.devMake)) {
            return false;
        }
        String str2 = this.devModel;
        String str3 = protocolExtension.devModel;
        if (str2 != null) {
            return str2.equals(str3);
        }
        if (str3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        List<String> list = this.ticketKeys;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.devMake;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.devModel;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }
}
