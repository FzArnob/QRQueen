package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent implements LoggingEvent {
    Object[] argArray;
    Level level;
    SubstituteLogger logger;
    String loggerName;
    Marker marker;
    String message;
    String threadName;
    Throwable throwable;
    long timeStamp;

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level2) {
        this.level = level2;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public void setMarker(Marker marker2) {
        this.marker = marker2;
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    public void setLogger(SubstituteLogger substituteLogger) {
        this.logger = substituteLogger;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Object[] getArgumentArray() {
        return this.argArray;
    }

    public void setArgumentArray(Object[] objArr) {
        this.argArray = objArr;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String str) {
        this.threadName = str;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable th) {
        this.throwable = th;
    }
}
