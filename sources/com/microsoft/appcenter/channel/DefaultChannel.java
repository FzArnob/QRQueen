package com.microsoft.appcenter.channel;

import android.content.Context;
import android.os.Handler;
import com.microsoft.appcenter.CancellationException;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.HttpResponse;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.AppCenterIngestion;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.one.PartAUtils;
import com.microsoft.appcenter.persistence.DatabasePersistence;
import com.microsoft.appcenter.persistence.Persistence;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.DeviceInfoHelper;
import com.microsoft.appcenter.utils.IdHelper;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DefaultChannel implements Channel {
    static final int CLEAR_BATCH_SIZE = 100;
    private static final long MINIMUM_TRANSMISSION_INTERVAL = 3000;
    static final String START_TIMER_PREFIX = "startTimerPrefix.";
    /* access modifiers changed from: private */
    public final Handler mAppCenterHandler;
    private String mAppSecret;
    private final Context mContext;
    private int mCurrentState;
    private Device mDevice;
    private boolean mDiscardLogs;
    private boolean mEnabled;
    private final Map<String, GroupState> mGroupStates;
    private final Ingestion mIngestion;
    private final Set<Ingestion> mIngestions;
    private final UUID mInstallId;
    private final Collection<Channel.Listener> mListeners;
    private final Persistence mPersistence;

    public DefaultChannel(Context context, String str, LogSerializer logSerializer, HttpClient httpClient, Handler handler) {
        this(context, str, buildDefaultPersistence(context, logSerializer), (Ingestion) new AppCenterIngestion(httpClient, logSerializer), handler);
    }

    DefaultChannel(Context context, String str, Persistence persistence, Ingestion ingestion, Handler handler) {
        this.mContext = context;
        this.mAppSecret = str;
        this.mInstallId = IdHelper.getInstallId();
        this.mGroupStates = new HashMap();
        this.mListeners = new LinkedHashSet();
        this.mPersistence = persistence;
        this.mIngestion = ingestion;
        HashSet hashSet = new HashSet();
        this.mIngestions = hashSet;
        hashSet.add(ingestion);
        this.mAppCenterHandler = handler;
        this.mEnabled = true;
    }

    private static Persistence buildDefaultPersistence(Context context, LogSerializer logSerializer) {
        DatabasePersistence databasePersistence = new DatabasePersistence(context);
        databasePersistence.setLogSerializer(logSerializer);
        return databasePersistence;
    }

    public boolean setMaxStorageSize(long j) {
        return this.mPersistence.setMaxStorageSize(j);
    }

    private boolean checkStateDidNotChange(GroupState groupState, int i) {
        return i == this.mCurrentState && groupState == this.mGroupStates.get(groupState.mName);
    }

    public void setAppSecret(String str) {
        this.mAppSecret = str;
        if (this.mEnabled) {
            for (GroupState next : this.mGroupStates.values()) {
                if (next.mIngestion == this.mIngestion) {
                    checkPendingLogs(next);
                }
            }
        }
    }

    public void addGroup(String str, int i, long j, int i2, Ingestion ingestion, Channel.GroupListener groupListener) {
        String str2 = str;
        AppCenterLog.debug("AppCenter", "addGroup(" + str + ")");
        Ingestion ingestion2 = ingestion == null ? this.mIngestion : ingestion;
        this.mIngestions.add(ingestion2);
        GroupState groupState = new GroupState(str, i, j, i2, ingestion2, groupListener);
        this.mGroupStates.put(str, groupState);
        groupState.mPendingLogCount = this.mPersistence.countLogs(str);
        if (!(this.mAppSecret == null && this.mIngestion == ingestion2)) {
            checkPendingLogs(groupState);
        }
        for (Channel.Listener onGroupAdded : this.mListeners) {
            onGroupAdded.onGroupAdded(str, groupListener, j);
        }
    }

    public void removeGroup(String str) {
        AppCenterLog.debug("AppCenter", "removeGroup(" + str + ")");
        GroupState remove = this.mGroupStates.remove(str);
        if (remove != null) {
            cancelTimer(remove);
        }
        for (Channel.Listener onGroupRemoved : this.mListeners) {
            onGroupRemoved.onGroupRemoved(str);
        }
    }

    public void pauseGroup(String str, String str2) {
        GroupState groupState = this.mGroupStates.get(str);
        if (groupState != null) {
            if (str2 != null) {
                String targetKey = PartAUtils.getTargetKey(str2);
                if (groupState.mPausedTargetKeys.add(targetKey)) {
                    AppCenterLog.debug("AppCenter", "pauseGroup(" + str + ", " + targetKey + ")");
                }
            } else if (!groupState.mPaused) {
                AppCenterLog.debug("AppCenter", "pauseGroup(" + str + ")");
                groupState.mPaused = true;
                cancelTimer(groupState);
            }
            for (Channel.Listener onPaused : this.mListeners) {
                onPaused.onPaused(str, str2);
            }
        }
    }

    public void resumeGroup(String str, String str2) {
        GroupState groupState = this.mGroupStates.get(str);
        if (groupState != null) {
            if (str2 != null) {
                String targetKey = PartAUtils.getTargetKey(str2);
                if (groupState.mPausedTargetKeys.remove(targetKey)) {
                    AppCenterLog.debug("AppCenter", "resumeGroup(" + str + ", " + targetKey + ")");
                    groupState.mPendingLogCount = this.mPersistence.countLogs(str);
                    checkPendingLogs(groupState);
                }
            } else if (groupState.mPaused) {
                AppCenterLog.debug("AppCenter", "resumeGroup(" + str + ")");
                groupState.mPaused = false;
                checkPendingLogs(groupState);
            }
            for (Channel.Listener onResumed : this.mListeners) {
                onResumed.onResumed(str, str2);
            }
        }
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            if (z) {
                this.mEnabled = true;
                this.mDiscardLogs = false;
                this.mCurrentState++;
                for (Ingestion reopen : this.mIngestions) {
                    reopen.reopen();
                }
                for (GroupState checkPendingLogs : this.mGroupStates.values()) {
                    checkPendingLogs(checkPendingLogs);
                }
            } else {
                this.mEnabled = false;
                suspend(true, new CancellationException());
            }
            for (Channel.Listener onGloballyEnabled : this.mListeners) {
                onGloballyEnabled.onGloballyEnabled(z);
            }
        }
    }

    public void setLogUrl(String str) {
        this.mIngestion.setLogUrl(str);
    }

    public void clear(String str) {
        if (this.mGroupStates.containsKey(str)) {
            AppCenterLog.debug("AppCenter", "clear(" + str + ")");
            this.mPersistence.deleteLogs(str);
            for (Channel.Listener onClear : this.mListeners) {
                onClear.onClear(str);
            }
        }
    }

    public void invalidateDeviceCache() {
        this.mDevice = null;
    }

    private void suspend(boolean z, Exception exc) {
        Channel.GroupListener groupListener;
        this.mDiscardLogs = z;
        this.mCurrentState++;
        for (GroupState next : this.mGroupStates.values()) {
            cancelTimer(next);
            Iterator<Map.Entry<String, List<Log>>> it = next.mSendingBatches.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next2 = it.next();
                it.remove();
                if (z && (groupListener = next.mListener) != null) {
                    for (Log onFailure : (List) next2.getValue()) {
                        groupListener.onFailure(onFailure, exc);
                    }
                }
            }
        }
        for (Ingestion next3 : this.mIngestions) {
            try {
                next3.close();
            } catch (IOException e) {
                AppCenterLog.error("AppCenter", "Failed to close ingestion: " + next3, e);
            }
        }
        if (z) {
            for (GroupState deleteLogsOnSuspended : this.mGroupStates.values()) {
                deleteLogsOnSuspended(deleteLogsOnSuspended);
            }
            return;
        }
        this.mPersistence.clearPendingLogState();
    }

    private void deleteLogsOnSuspended(GroupState groupState) {
        ArrayList<Log> arrayList = new ArrayList<>();
        this.mPersistence.getLogs(groupState.mName, Collections.emptyList(), 100, arrayList);
        if (arrayList.size() > 0 && groupState.mListener != null) {
            for (Log log : arrayList) {
                groupState.mListener.onBeforeSending(log);
                groupState.mListener.onFailure(log, new CancellationException());
            }
        }
        if (arrayList.size() < 100 || groupState.mListener == null) {
            this.mPersistence.deleteLogs(groupState.mName);
        } else {
            deleteLogsOnSuspended(groupState);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelTimer(GroupState groupState) {
        if (groupState.mScheduled) {
            groupState.mScheduled = false;
            this.mAppCenterHandler.removeCallbacks(groupState.mRunnable);
            SharedPreferencesManager.remove(START_TIMER_PREFIX + groupState.mName);
        }
    }

    /* access modifiers changed from: private */
    public void triggerIngestion(GroupState groupState) {
        if (this.mEnabled) {
            if (!this.mIngestion.isEnabled()) {
                AppCenterLog.debug("AppCenter", "SDK is in offline mode.");
                return;
            }
            int i = groupState.mPendingLogCount;
            int min = Math.min(i, groupState.mMaxLogsPerBatch);
            AppCenterLog.debug("AppCenter", "triggerIngestion(" + groupState.mName + ") pendingLogCount=" + i);
            cancelTimer(groupState);
            if (groupState.mSendingBatches.size() == groupState.mMaxParallelBatches) {
                AppCenterLog.debug("AppCenter", "Already sending " + groupState.mMaxParallelBatches + " batches of analytics data to the server.");
                return;
            }
            ArrayList<Log> arrayList = new ArrayList<>(min);
            String logs = this.mPersistence.getLogs(groupState.mName, groupState.mPausedTargetKeys, min, arrayList);
            groupState.mPendingLogCount -= min;
            if (logs != null) {
                AppCenterLog.debug("AppCenter", "ingestLogs(" + groupState.mName + "," + logs + ") pendingLogCount=" + groupState.mPendingLogCount);
                if (groupState.mListener != null) {
                    for (Log onBeforeSending : arrayList) {
                        groupState.mListener.onBeforeSending(onBeforeSending);
                    }
                }
                groupState.mSendingBatches.put(logs, arrayList);
                sendLogs(groupState, this.mCurrentState, arrayList, logs);
            }
        }
    }

    private void sendLogs(final GroupState groupState, final int i, List<Log> list, final String str) {
        LogContainer logContainer = new LogContainer();
        logContainer.setLogs(list);
        groupState.mIngestion.sendAsync(this.mAppSecret, this.mInstallId, logContainer, new ServiceCallback() {
            public void onCallSucceeded(HttpResponse httpResponse) {
                DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
                    public void run() {
                        DefaultChannel.this.handleSendingSuccess(groupState, str);
                    }
                });
            }

            public void onCallFailed(final Exception exc) {
                DefaultChannel.this.mAppCenterHandler.post(new Runnable() {
                    public void run() {
                        DefaultChannel.this.handleSendingFailure(groupState, str, exc);
                    }
                });
            }
        });
        this.mAppCenterHandler.post(new Runnable() {
            public void run() {
                DefaultChannel.this.checkPendingLogsAfterPost(groupState, i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void checkPendingLogsAfterPost(GroupState groupState, int i) {
        if (checkStateDidNotChange(groupState, i)) {
            checkPendingLogs(groupState);
        }
    }

    /* access modifiers changed from: private */
    public void handleSendingSuccess(GroupState groupState, String str) {
        List<Log> remove = groupState.mSendingBatches.remove(str);
        if (remove != null) {
            this.mPersistence.deleteLogs(groupState.mName, str);
            Channel.GroupListener groupListener = groupState.mListener;
            if (groupListener != null) {
                for (Log onSuccess : remove) {
                    groupListener.onSuccess(onSuccess);
                }
            }
            checkPendingLogs(groupState);
        }
    }

    /* access modifiers changed from: private */
    public void handleSendingFailure(GroupState groupState, String str, Exception exc) {
        String str2 = groupState.mName;
        List<Log> remove = groupState.mSendingBatches.remove(str);
        if (remove != null) {
            AppCenterLog.error("AppCenter", "Sending logs groupName=" + str2 + " id=" + str + " failed", exc);
            boolean isRecoverableError = HttpUtils.isRecoverableError(exc);
            if (isRecoverableError) {
                groupState.mPendingLogCount += remove.size();
            } else {
                Channel.GroupListener groupListener = groupState.mListener;
                if (groupListener != null) {
                    for (Log onFailure : remove) {
                        groupListener.onFailure(onFailure, exc);
                    }
                }
            }
            this.mEnabled = false;
            suspend(!isRecoverableError, exc);
        }
    }

    public void enqueue(Log log, String str, int i) {
        boolean z;
        GroupState groupState = this.mGroupStates.get(str);
        if (groupState == null) {
            AppCenterLog.error("AppCenter", "Invalid group name:" + str);
        } else if (this.mDiscardLogs) {
            AppCenterLog.warn("AppCenter", "Channel is disabled, the log is discarded.");
            if (groupState.mListener != null) {
                groupState.mListener.onBeforeSending(log);
                groupState.mListener.onFailure(log, new CancellationException());
            }
        } else {
            for (Channel.Listener onPreparingLog : this.mListeners) {
                onPreparingLog.onPreparingLog(log, str);
            }
            if (log.getDevice() == null) {
                if (this.mDevice == null) {
                    try {
                        this.mDevice = DeviceInfoHelper.getDeviceInfo(this.mContext);
                    } catch (DeviceInfoHelper.DeviceInfoException e) {
                        AppCenterLog.error("AppCenter", "Device log cannot be generated", e);
                        return;
                    }
                }
                log.setDevice(this.mDevice);
            }
            if (log.getTimestamp() == null) {
                log.setTimestamp(new Date());
            }
            for (Channel.Listener onPreparedLog : this.mListeners) {
                onPreparedLog.onPreparedLog(log, str, i);
            }
            Iterator<Channel.Listener> it = this.mListeners.iterator();
            loop2:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop2;
                    }
                    Channel.Listener next = it.next();
                    if (z || next.shouldFilter(log)) {
                        z = true;
                    }
                }
            }
            if (z) {
                AppCenterLog.debug("AppCenter", "Log of type '" + log.getType() + "' was filtered out by listener(s)");
            } else if (this.mAppSecret == null && groupState.mIngestion == this.mIngestion) {
                AppCenterLog.debug("AppCenter", "Log of type '" + log.getType() + "' was not filtered out by listener(s) but no app secret was provided. Not persisting/sending the log.");
            } else {
                try {
                    this.mPersistence.putLog(log, str, i);
                    Iterator<String> it2 = log.getTransmissionTargetTokens().iterator();
                    String targetKey = it2.hasNext() ? PartAUtils.getTargetKey(it2.next()) : null;
                    if (groupState.mPausedTargetKeys.contains(targetKey)) {
                        AppCenterLog.debug("AppCenter", "Transmission target ikey=" + targetKey + " is paused.");
                        return;
                    }
                    groupState.mPendingLogCount++;
                    AppCenterLog.debug("AppCenter", "enqueue(" + groupState.mName + ") pendingLogCount=" + groupState.mPendingLogCount);
                    if (this.mEnabled) {
                        checkPendingLogs(groupState);
                    } else {
                        AppCenterLog.debug("AppCenter", "Channel is temporarily disabled, log was saved to disk.");
                    }
                } catch (Persistence.PersistenceException e2) {
                    AppCenterLog.error("AppCenter", "Error persisting log", e2);
                    if (groupState.mListener != null) {
                        groupState.mListener.onBeforeSending(log);
                        groupState.mListener.onFailure(log, e2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkPendingLogs(GroupState groupState) {
        AppCenterLog.debug("AppCenter", String.format("checkPendingLogs(%s) pendingLogCount=%s batchTimeInterval=%s", new Object[]{groupState.mName, Integer.valueOf(groupState.mPendingLogCount), Long.valueOf(groupState.mBatchTimeInterval)}));
        Long resolveTriggerInterval = resolveTriggerInterval(groupState);
        if (resolveTriggerInterval != null && !groupState.mPaused) {
            if (resolveTriggerInterval.longValue() == 0) {
                triggerIngestion(groupState);
            } else if (!groupState.mScheduled) {
                groupState.mScheduled = true;
                this.mAppCenterHandler.postDelayed(groupState.mRunnable, resolveTriggerInterval.longValue());
            }
        }
    }

    private Long resolveTriggerInterval(GroupState groupState) {
        if (groupState.mBatchTimeInterval > MINIMUM_TRANSMISSION_INTERVAL) {
            return resolveCustomTriggerInterval(groupState);
        }
        return resolveDefaultTriggerInterval(groupState);
    }

    private Long resolveCustomTriggerInterval(GroupState groupState) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = SharedPreferencesManager.getLong(START_TIMER_PREFIX + groupState.mName);
        if (groupState.mPendingLogCount > 0) {
            if (j != 0 && j <= currentTimeMillis) {
                return Long.valueOf(Math.max(groupState.mBatchTimeInterval - (currentTimeMillis - j), 0));
            }
            SharedPreferencesManager.putLong(START_TIMER_PREFIX + groupState.mName, currentTimeMillis);
            AppCenterLog.debug("AppCenter", "The timer value for " + groupState.mName + " has been saved.");
            return Long.valueOf(groupState.mBatchTimeInterval);
        } else if (j + groupState.mBatchTimeInterval >= currentTimeMillis) {
            return null;
        } else {
            SharedPreferencesManager.remove(START_TIMER_PREFIX + groupState.mName);
            AppCenterLog.debug("AppCenter", "The timer for " + groupState.mName + " channel finished.");
            return null;
        }
    }

    private Long resolveDefaultTriggerInterval(GroupState groupState) {
        if (groupState.mPendingLogCount >= groupState.mMaxLogsPerBatch) {
            return 0L;
        }
        if (groupState.mPendingLogCount > 0) {
            return Long.valueOf(groupState.mBatchTimeInterval);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public GroupState getGroupState(String str) {
        return this.mGroupStates.get(str);
    }

    public void addListener(Channel.Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Channel.Listener listener) {
        this.mListeners.remove(listener);
    }

    public void shutdown() {
        this.mEnabled = false;
        suspend(false, new CancellationException());
    }

    public void setNetworkRequests(boolean z) {
        if (z) {
            this.mCurrentState++;
            for (GroupState checkPendingLogs : this.mGroupStates.values()) {
                checkPendingLogs(checkPendingLogs);
            }
            return;
        }
        this.mEnabled = true;
        suspend(false, new CancellationException());
    }

    class GroupState {
        final long mBatchTimeInterval;
        final Ingestion mIngestion;
        final Channel.GroupListener mListener;
        final int mMaxLogsPerBatch;
        final int mMaxParallelBatches;
        final String mName;
        boolean mPaused;
        final Collection<String> mPausedTargetKeys = new HashSet();
        int mPendingLogCount;
        final Runnable mRunnable = new Runnable() {
            public void run() {
                GroupState.this.mScheduled = false;
                DefaultChannel.this.triggerIngestion(GroupState.this);
            }
        };
        boolean mScheduled;
        final Map<String, List<Log>> mSendingBatches = new HashMap();

        GroupState(String str, int i, long j, int i2, Ingestion ingestion, Channel.GroupListener groupListener) {
            this.mName = str;
            this.mMaxLogsPerBatch = i;
            this.mBatchTimeInterval = j;
            this.mMaxParallelBatches = i2;
            this.mIngestion = ingestion;
            this.mListener = groupListener;
        }
    }
}
