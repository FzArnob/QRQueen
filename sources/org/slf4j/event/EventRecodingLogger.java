package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.SubstituteLogger;

public class EventRecodingLogger implements Logger {
    static final boolean RECORD_ALL_EVENTS = true;
    Queue<SubstituteLoggingEvent> eventQueue;
    SubstituteLogger logger;
    String name;

    public boolean isDebugEnabled() {
        return true;
    }

    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    public boolean isTraceEnabled() {
        return true;
    }

    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    public EventRecodingLogger(SubstituteLogger substituteLogger, Queue<SubstituteLoggingEvent> queue) {
        this.logger = substituteLogger;
        this.name = substituteLogger.getName();
        this.eventQueue = queue;
    }

    public String getName() {
        return this.name;
    }

    public void trace(String str) {
        recordEvent_0Args(Level.TRACE, (Marker) null, str, (Throwable) null);
    }

    public void trace(String str, Object obj) {
        recordEvent_1Args(Level.TRACE, (Marker) null, str, obj);
    }

    public void trace(String str, Object obj, Object obj2) {
        recordEvent2Args(Level.TRACE, (Marker) null, str, obj, obj2);
    }

    public void trace(String str, Object... objArr) {
        recordEventArgArray(Level.TRACE, (Marker) null, str, objArr);
    }

    public void trace(String str, Throwable th) {
        recordEvent_0Args(Level.TRACE, (Marker) null, str, th);
    }

    public void trace(Marker marker, String str) {
        recordEvent_0Args(Level.TRACE, marker, str, (Throwable) null);
    }

    public void trace(Marker marker, String str, Object obj) {
        recordEvent_1Args(Level.TRACE, marker, str, obj);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2) {
        recordEvent2Args(Level.TRACE, marker, str, obj, obj2);
    }

    public void trace(Marker marker, String str, Object... objArr) {
        recordEventArgArray(Level.TRACE, marker, str, objArr);
    }

    public void trace(Marker marker, String str, Throwable th) {
        recordEvent_0Args(Level.TRACE, marker, str, th);
    }

    public void debug(String str) {
        recordEvent_0Args(Level.DEBUG, (Marker) null, str, (Throwable) null);
    }

    public void debug(String str, Object obj) {
        recordEvent_1Args(Level.DEBUG, (Marker) null, str, obj);
    }

    public void debug(String str, Object obj, Object obj2) {
        recordEvent2Args(Level.DEBUG, (Marker) null, str, obj, obj2);
    }

    public void debug(String str, Object... objArr) {
        recordEventArgArray(Level.DEBUG, (Marker) null, str, objArr);
    }

    public void debug(String str, Throwable th) {
        recordEvent_0Args(Level.DEBUG, (Marker) null, str, th);
    }

    public void debug(Marker marker, String str) {
        recordEvent_0Args(Level.DEBUG, marker, str, (Throwable) null);
    }

    public void debug(Marker marker, String str, Object obj) {
        recordEvent_1Args(Level.DEBUG, marker, str, obj);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2) {
        recordEvent2Args(Level.DEBUG, marker, str, obj, obj2);
    }

    public void debug(Marker marker, String str, Object... objArr) {
        recordEventArgArray(Level.DEBUG, marker, str, objArr);
    }

    public void debug(Marker marker, String str, Throwable th) {
        recordEvent_0Args(Level.DEBUG, marker, str, th);
    }

    public void info(String str) {
        recordEvent_0Args(Level.INFO, (Marker) null, str, (Throwable) null);
    }

    public void info(String str, Object obj) {
        recordEvent_1Args(Level.INFO, (Marker) null, str, obj);
    }

    public void info(String str, Object obj, Object obj2) {
        recordEvent2Args(Level.INFO, (Marker) null, str, obj, obj2);
    }

    public void info(String str, Object... objArr) {
        recordEventArgArray(Level.INFO, (Marker) null, str, objArr);
    }

    public void info(String str, Throwable th) {
        recordEvent_0Args(Level.INFO, (Marker) null, str, th);
    }

    public void info(Marker marker, String str) {
        recordEvent_0Args(Level.INFO, marker, str, (Throwable) null);
    }

    public void info(Marker marker, String str, Object obj) {
        recordEvent_1Args(Level.INFO, marker, str, obj);
    }

    public void info(Marker marker, String str, Object obj, Object obj2) {
        recordEvent2Args(Level.INFO, marker, str, obj, obj2);
    }

    public void info(Marker marker, String str, Object... objArr) {
        recordEventArgArray(Level.INFO, marker, str, objArr);
    }

    public void info(Marker marker, String str, Throwable th) {
        recordEvent_0Args(Level.INFO, marker, str, th);
    }

    public void warn(String str) {
        recordEvent_0Args(Level.WARN, (Marker) null, str, (Throwable) null);
    }

    public void warn(String str, Object obj) {
        recordEvent_1Args(Level.WARN, (Marker) null, str, obj);
    }

    public void warn(String str, Object obj, Object obj2) {
        recordEvent2Args(Level.WARN, (Marker) null, str, obj, obj2);
    }

    public void warn(String str, Object... objArr) {
        recordEventArgArray(Level.WARN, (Marker) null, str, objArr);
    }

    public void warn(String str, Throwable th) {
        recordEvent_0Args(Level.WARN, (Marker) null, str, th);
    }

    public void warn(Marker marker, String str) {
        recordEvent_0Args(Level.WARN, marker, str, (Throwable) null);
    }

    public void warn(Marker marker, String str, Object obj) {
        recordEvent_1Args(Level.WARN, marker, str, obj);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2) {
        recordEvent2Args(Level.WARN, marker, str, obj, obj2);
    }

    public void warn(Marker marker, String str, Object... objArr) {
        recordEventArgArray(Level.WARN, marker, str, objArr);
    }

    public void warn(Marker marker, String str, Throwable th) {
        recordEvent_0Args(Level.WARN, marker, str, th);
    }

    public void error(String str) {
        recordEvent_0Args(Level.ERROR, (Marker) null, str, (Throwable) null);
    }

    public void error(String str, Object obj) {
        recordEvent_1Args(Level.ERROR, (Marker) null, str, obj);
    }

    public void error(String str, Object obj, Object obj2) {
        recordEvent2Args(Level.ERROR, (Marker) null, str, obj, obj2);
    }

    public void error(String str, Object... objArr) {
        recordEventArgArray(Level.ERROR, (Marker) null, str, objArr);
    }

    public void error(String str, Throwable th) {
        recordEvent_0Args(Level.ERROR, (Marker) null, str, th);
    }

    public void error(Marker marker, String str) {
        recordEvent_0Args(Level.ERROR, marker, str, (Throwable) null);
    }

    public void error(Marker marker, String str, Object obj) {
        recordEvent_1Args(Level.ERROR, marker, str, obj);
    }

    public void error(Marker marker, String str, Object obj, Object obj2) {
        recordEvent2Args(Level.ERROR, marker, str, obj, obj2);
    }

    public void error(Marker marker, String str, Object... objArr) {
        recordEventArgArray(Level.ERROR, marker, str, objArr);
    }

    public void error(Marker marker, String str, Throwable th) {
        recordEvent_0Args(Level.ERROR, marker, str, th);
    }

    private void recordEvent_0Args(Level level, Marker marker, String str, Throwable th) {
        recordEvent(level, marker, str, (Object[]) null, th);
    }

    private void recordEvent_1Args(Level level, Marker marker, String str, Object obj) {
        recordEvent(level, marker, str, new Object[]{obj}, (Throwable) null);
    }

    private void recordEvent2Args(Level level, Marker marker, String str, Object obj, Object obj2) {
        if (obj2 instanceof Throwable) {
            Level level2 = level;
            Marker marker2 = marker;
            String str2 = str;
            recordEvent(level2, marker2, str2, new Object[]{obj}, (Throwable) obj2);
            return;
        }
        recordEvent(level, marker, str, new Object[]{obj, obj2}, (Throwable) null);
    }

    private void recordEventArgArray(Level level, Marker marker, String str, Object[] objArr) {
        Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(objArr);
        if (throwableCandidate != null) {
            recordEvent(level, marker, str, MessageFormatter.trimmedCopy(objArr), throwableCandidate);
            return;
        }
        recordEvent(level, marker, str, objArr, (Throwable) null);
    }

    private void recordEvent(Level level, Marker marker, String str, Object[] objArr, Throwable th) {
        SubstituteLoggingEvent substituteLoggingEvent = new SubstituteLoggingEvent();
        substituteLoggingEvent.setTimeStamp(System.currentTimeMillis());
        substituteLoggingEvent.setLevel(level);
        substituteLoggingEvent.setLogger(this.logger);
        substituteLoggingEvent.setLoggerName(this.name);
        substituteLoggingEvent.setMarker(marker);
        substituteLoggingEvent.setMessage(str);
        substituteLoggingEvent.setThreadName(Thread.currentThread().getName());
        substituteLoggingEvent.setArgumentArray(objArr);
        substituteLoggingEvent.setThrowable(th);
        this.eventQueue.add(substituteLoggingEvent);
    }
}
