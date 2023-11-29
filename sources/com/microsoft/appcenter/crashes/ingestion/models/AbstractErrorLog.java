package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.ingestion.models.AbstractLog;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class AbstractErrorLog extends AbstractLog {
    private static final String APP_LAUNCH_TIMESTAMP = "appLaunchTimestamp";
    private static final String ARCHITECTURE = "architecture";
    private static final String ERROR_THREAD_ID = "errorThreadId";
    private static final String ERROR_THREAD_NAME = "errorThreadName";
    private static final String FATAL = "fatal";
    private static final String PARENT_PROCESS_ID = "parentProcessId";
    private static final String PARENT_PROCESS_NAME = "parentProcessName";
    private static final String PROCESS_ID = "processId";
    private static final String PROCESS_NAME = "processName";
    private Date appLaunchTimestamp;
    private String architecture;
    private Long errorThreadId;
    private String errorThreadName;
    private Boolean fatal;
    private UUID id;
    private Integer parentProcessId;
    private String parentProcessName;
    private Integer processId;
    private String processName;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public Integer getProcessId() {
        return this.processId;
    }

    public void setProcessId(Integer num) {
        this.processId = num;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public Integer getParentProcessId() {
        return this.parentProcessId;
    }

    public void setParentProcessId(Integer num) {
        this.parentProcessId = num;
    }

    public String getParentProcessName() {
        return this.parentProcessName;
    }

    public void setParentProcessName(String str) {
        this.parentProcessName = str;
    }

    public Long getErrorThreadId() {
        return this.errorThreadId;
    }

    public void setErrorThreadId(Long l) {
        this.errorThreadId = l;
    }

    public String getErrorThreadName() {
        return this.errorThreadName;
    }

    public void setErrorThreadName(String str) {
        this.errorThreadName = str;
    }

    public Boolean getFatal() {
        return this.fatal;
    }

    public void setFatal(Boolean bool) {
        this.fatal = bool;
    }

    public Date getAppLaunchTimestamp() {
        return this.appLaunchTimestamp;
    }

    public void setAppLaunchTimestamp(Date date) {
        this.appLaunchTimestamp = date;
    }

    public String getArchitecture() {
        return this.architecture;
    }

    public void setArchitecture(String str) {
        this.architecture = str;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setId(UUID.fromString(jSONObject.getString(CommonProperties.ID)));
        setProcessId(JSONUtils.readInteger(jSONObject, PROCESS_ID));
        setProcessName(jSONObject.optString(PROCESS_NAME, (String) null));
        setParentProcessId(JSONUtils.readInteger(jSONObject, PARENT_PROCESS_ID));
        setParentProcessName(jSONObject.optString(PARENT_PROCESS_NAME, (String) null));
        setErrorThreadId(JSONUtils.readLong(jSONObject, ERROR_THREAD_ID));
        setErrorThreadName(jSONObject.optString(ERROR_THREAD_NAME, (String) null));
        setFatal(JSONUtils.readBoolean(jSONObject, FATAL));
        setAppLaunchTimestamp(JSONDateUtils.toDate(jSONObject.getString(APP_LAUNCH_TIMESTAMP)));
        setArchitecture(jSONObject.optString(ARCHITECTURE, (String) null));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        JSONUtils.write(jSONStringer, CommonProperties.ID, getId());
        JSONUtils.write(jSONStringer, PROCESS_ID, getProcessId());
        JSONUtils.write(jSONStringer, PROCESS_NAME, getProcessName());
        JSONUtils.write(jSONStringer, PARENT_PROCESS_ID, getParentProcessId());
        JSONUtils.write(jSONStringer, PARENT_PROCESS_NAME, getParentProcessName());
        JSONUtils.write(jSONStringer, ERROR_THREAD_ID, getErrorThreadId());
        JSONUtils.write(jSONStringer, ERROR_THREAD_NAME, getErrorThreadName());
        JSONUtils.write(jSONStringer, FATAL, getFatal());
        JSONUtils.write(jSONStringer, APP_LAUNCH_TIMESTAMP, JSONDateUtils.toString(getAppLaunchTimestamp()));
        JSONUtils.write(jSONStringer, ARCHITECTURE, getArchitecture());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        AbstractErrorLog abstractErrorLog = (AbstractErrorLog) obj;
        UUID uuid = this.id;
        if (uuid == null ? abstractErrorLog.id != null : !uuid.equals(abstractErrorLog.id)) {
            return false;
        }
        Integer num = this.processId;
        if (num == null ? abstractErrorLog.processId != null : !num.equals(abstractErrorLog.processId)) {
            return false;
        }
        String str = this.processName;
        if (str == null ? abstractErrorLog.processName != null : !str.equals(abstractErrorLog.processName)) {
            return false;
        }
        Integer num2 = this.parentProcessId;
        if (num2 == null ? abstractErrorLog.parentProcessId != null : !num2.equals(abstractErrorLog.parentProcessId)) {
            return false;
        }
        String str2 = this.parentProcessName;
        if (str2 == null ? abstractErrorLog.parentProcessName != null : !str2.equals(abstractErrorLog.parentProcessName)) {
            return false;
        }
        Long l = this.errorThreadId;
        if (l == null ? abstractErrorLog.errorThreadId != null : !l.equals(abstractErrorLog.errorThreadId)) {
            return false;
        }
        String str3 = this.errorThreadName;
        if (str3 == null ? abstractErrorLog.errorThreadName != null : !str3.equals(abstractErrorLog.errorThreadName)) {
            return false;
        }
        Boolean bool = this.fatal;
        if (bool == null ? abstractErrorLog.fatal != null : !bool.equals(abstractErrorLog.fatal)) {
            return false;
        }
        Date date = this.appLaunchTimestamp;
        if (date == null ? abstractErrorLog.appLaunchTimestamp != null : !date.equals(abstractErrorLog.appLaunchTimestamp)) {
            return false;
        }
        String str4 = this.architecture;
        String str5 = abstractErrorLog.architecture;
        if (str4 != null) {
            return str4.equals(str5);
        }
        if (str5 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        UUID uuid = this.id;
        int i = 0;
        int hashCode2 = (hashCode + (uuid != null ? uuid.hashCode() : 0)) * 31;
        Integer num = this.processId;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        String str = this.processName;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        Integer num2 = this.parentProcessId;
        int hashCode5 = (hashCode4 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str2 = this.parentProcessName;
        int hashCode6 = (hashCode5 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l = this.errorThreadId;
        int hashCode7 = (hashCode6 + (l != null ? l.hashCode() : 0)) * 31;
        String str3 = this.errorThreadName;
        int hashCode8 = (hashCode7 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Boolean bool = this.fatal;
        int hashCode9 = (hashCode8 + (bool != null ? bool.hashCode() : 0)) * 31;
        Date date = this.appLaunchTimestamp;
        int hashCode10 = (hashCode9 + (date != null ? date.hashCode() : 0)) * 31;
        String str4 = this.architecture;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode10 + i;
    }
}
