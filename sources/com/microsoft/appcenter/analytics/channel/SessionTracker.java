package com.microsoft.appcenter.analytics.channel;

import android.os.SystemClock;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.analytics.ingestion.models.StartSessionLog;
import com.microsoft.appcenter.channel.AbstractChannelListener;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.StartServiceLog;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.context.SessionContext;
import java.util.Date;
import java.util.UUID;

public class SessionTracker extends AbstractChannelListener {
    private static final long SESSION_TIMEOUT = 20000;
    private boolean isManualSessionTrackerEnabled = false;
    private final Channel mChannel;
    private final String mGroupName;
    private Long mLastPausedTime;
    private long mLastQueuedLogTime;
    private Long mLastResumedTime;
    private UUID mSid;

    public SessionTracker(Channel channel, String str) {
        this.mChannel = channel;
        this.mGroupName = str;
    }

    public void onPreparingLog(Log log, String str) {
        if (!(log instanceof StartSessionLog) && !(log instanceof StartServiceLog)) {
            Date timestamp = log.getTimestamp();
            if (timestamp != null) {
                SessionContext.SessionInfo sessionAt = SessionContext.getInstance().getSessionAt(timestamp.getTime());
                if (sessionAt != null) {
                    log.setSid(sessionAt.getSessionId());
                    return;
                }
                return;
            }
            log.setSid(this.mSid);
            if (!this.isManualSessionTrackerEnabled) {
                this.mLastQueuedLogTime = SystemClock.elapsedRealtime();
            }
        }
    }

    private void sendStartSessionIfNeeded() {
        if (this.mSid == null || hasSessionTimedOut()) {
            this.mLastQueuedLogTime = SystemClock.elapsedRealtime();
            sendStartSession();
        }
    }

    private void sendStartSession() {
        this.mSid = UUID.randomUUID();
        SessionContext.getInstance().addSession(this.mSid);
        StartSessionLog startSessionLog = new StartSessionLog();
        startSessionLog.setSid(this.mSid);
        this.mChannel.enqueue(startSessionLog, this.mGroupName, 1);
    }

    public void onActivityResumed() {
        if (this.isManualSessionTrackerEnabled) {
            AppCenterLog.verbose(Analytics.LOG_TAG, "Manual session tracker is enabled. Skip tracking a session status request after resumed activity.");
            return;
        }
        AppCenterLog.debug(Analytics.LOG_TAG, "onActivityResumed");
        this.mLastResumedTime = Long.valueOf(SystemClock.elapsedRealtime());
        sendStartSessionIfNeeded();
    }

    public void onActivityPaused() {
        if (this.isManualSessionTrackerEnabled) {
            AppCenterLog.verbose(Analytics.LOG_TAG, "Manual session tracker is enabled. Skip tracking a session status request after paused activity.");
            return;
        }
        AppCenterLog.debug(Analytics.LOG_TAG, "onActivityPaused");
        this.mLastPausedTime = Long.valueOf(SystemClock.elapsedRealtime());
    }

    public void clearSessions() {
        SessionContext.getInstance().clearSessions();
    }

    public void enableManualSessionTracker() {
        this.isManualSessionTrackerEnabled = true;
        AppCenterLog.debug(Analytics.LOG_TAG, "Manual session tracker is enabled.");
    }

    public void startSession() {
        if (!this.isManualSessionTrackerEnabled) {
            AppCenterLog.debug(Analytics.LOG_TAG, "Manual session tracker is disabled. Skip start a new session request.");
            return;
        }
        sendStartSession();
        AppCenterLog.debug(Analytics.LOG_TAG, String.format("Started a new session with id: %s.", new Object[]{this.mSid}));
    }

    private boolean hasSessionTimedOut() {
        if (this.mLastPausedTime == null) {
            return false;
        }
        boolean z = SystemClock.elapsedRealtime() - this.mLastQueuedLogTime >= SESSION_TIMEOUT;
        boolean z2 = this.mLastResumedTime.longValue() - Math.max(this.mLastPausedTime.longValue(), this.mLastQueuedLogTime) >= SESSION_TIMEOUT;
        AppCenterLog.debug(Analytics.LOG_TAG, "noLogSentForLong=" + z + " wasBackgroundForLong=" + z2);
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
