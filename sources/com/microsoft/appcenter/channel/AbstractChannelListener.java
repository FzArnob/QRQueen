package com.microsoft.appcenter.channel;

import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.Log;

public class AbstractChannelListener implements Channel.Listener {
    public void onClear(String str) {
    }

    public void onGloballyEnabled(boolean z) {
    }

    public void onGroupAdded(String str, Channel.GroupListener groupListener, long j) {
    }

    public void onGroupRemoved(String str) {
    }

    public void onPaused(String str, String str2) {
    }

    public void onPreparedLog(Log log, String str, int i) {
    }

    public void onPreparingLog(Log log, String str) {
    }

    public void onResumed(String str, String str2) {
    }

    public boolean shouldFilter(Log log) {
        return false;
    }
}
