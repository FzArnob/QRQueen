package com.microsoft.appcenter.crashes.model;

import com.microsoft.appcenter.ingestion.models.Device;
import java.util.Date;

public class ErrorReport {
    private Date appErrorTime;
    private Date appStartTime;
    private Device device;
    private String id;
    private String stackTrace;
    private String threadName;

    @Deprecated
    public Throwable getThrowable() {
        return null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String str) {
        this.threadName = str;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public void setStackTrace(String str) {
        this.stackTrace = str;
    }

    public Date getAppStartTime() {
        return this.appStartTime;
    }

    public void setAppStartTime(Date date) {
        this.appStartTime = date;
    }

    public Date getAppErrorTime() {
        return this.appErrorTime;
    }

    public void setAppErrorTime(Date date) {
        this.appErrorTime = date;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device2) {
        this.device = device2;
    }
}
