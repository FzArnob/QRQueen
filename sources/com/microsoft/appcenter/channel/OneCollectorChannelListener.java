package com.microsoft.appcenter.channel;

import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.OneCollectorIngestion;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import com.microsoft.appcenter.ingestion.models.one.SdkExtension;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OneCollectorChannelListener extends AbstractChannelListener {
    static final String ONE_COLLECTOR_GROUP_NAME_SUFFIX = "/one";
    static final int ONE_COLLECTOR_TRIGGER_COUNT = 50;
    static final int ONE_COLLECTOR_TRIGGER_MAX_PARALLEL_REQUESTS = 2;
    private final Channel mChannel;
    private final Map<String, EpochAndSeq> mEpochsAndSeqsByIKey;
    private final Ingestion mIngestion;
    private final UUID mInstallId;
    private final LogSerializer mLogSerializer;

    public OneCollectorChannelListener(Channel channel, LogSerializer logSerializer, HttpClient httpClient, UUID uuid) {
        this(new OneCollectorIngestion(httpClient, logSerializer), channel, logSerializer, uuid);
    }

    OneCollectorChannelListener(OneCollectorIngestion oneCollectorIngestion, Channel channel, LogSerializer logSerializer, UUID uuid) {
        this.mEpochsAndSeqsByIKey = new HashMap();
        this.mChannel = channel;
        this.mLogSerializer = logSerializer;
        this.mInstallId = uuid;
        this.mIngestion = oneCollectorIngestion;
    }

    public void setLogUrl(String str) {
        this.mIngestion.setLogUrl(str);
    }

    public void onGroupAdded(String str, Channel.GroupListener groupListener, long j) {
        if (!isOneCollectorGroup(str)) {
            this.mChannel.addGroup(getOneCollectorGroupName(str), 50, j, 2, this.mIngestion, groupListener);
        }
    }

    public void onGroupRemoved(String str) {
        if (!isOneCollectorGroup(str)) {
            this.mChannel.removeGroup(getOneCollectorGroupName(str));
        }
    }

    public void onPreparedLog(Log log, String str, int i) {
        if (isOneCollectorCompatible(log)) {
            try {
                Collection<CommonSchemaLog> commonSchemaLog = this.mLogSerializer.toCommonSchemaLog(log);
                for (CommonSchemaLog next : commonSchemaLog) {
                    next.setFlags(Long.valueOf((long) i));
                    EpochAndSeq epochAndSeq = this.mEpochsAndSeqsByIKey.get(next.getIKey());
                    if (epochAndSeq == null) {
                        epochAndSeq = new EpochAndSeq(UUID.randomUUID().toString());
                        this.mEpochsAndSeqsByIKey.put(next.getIKey(), epochAndSeq);
                    }
                    SdkExtension sdk = next.getExt().getSdk();
                    sdk.setEpoch(epochAndSeq.epoch);
                    long j = epochAndSeq.seq + 1;
                    epochAndSeq.seq = j;
                    sdk.setSeq(Long.valueOf(j));
                    sdk.setInstallId(this.mInstallId);
                }
                String oneCollectorGroupName = getOneCollectorGroupName(str);
                for (CommonSchemaLog enqueue : commonSchemaLog) {
                    this.mChannel.enqueue(enqueue, oneCollectorGroupName, i);
                }
            } catch (IllegalArgumentException e) {
                AppCenterLog.error("AppCenter", "Cannot send a log to one collector: " + e.getMessage());
            }
        }
    }

    public boolean shouldFilter(Log log) {
        return isOneCollectorCompatible(log);
    }

    private static String getOneCollectorGroupName(String str) {
        return str + ONE_COLLECTOR_GROUP_NAME_SUFFIX;
    }

    public void onClear(String str) {
        if (!isOneCollectorGroup(str)) {
            this.mChannel.clear(getOneCollectorGroupName(str));
        }
    }

    public void onPaused(String str, String str2) {
        if (!isOneCollectorGroup(str)) {
            this.mChannel.pauseGroup(getOneCollectorGroupName(str), str2);
        }
    }

    public void onResumed(String str, String str2) {
        if (!isOneCollectorGroup(str)) {
            this.mChannel.resumeGroup(getOneCollectorGroupName(str), str2);
        }
    }

    private static boolean isOneCollectorGroup(String str) {
        return str.endsWith(ONE_COLLECTOR_GROUP_NAME_SUFFIX);
    }

    private static boolean isOneCollectorCompatible(Log log) {
        return !(log instanceof CommonSchemaLog) && !log.getTransmissionTargetTokens().isEmpty();
    }

    public void onGloballyEnabled(boolean z) {
        if (!z) {
            this.mEpochsAndSeqsByIKey.clear();
        }
    }

    private static class EpochAndSeq {
        final String epoch;
        long seq;

        EpochAndSeq(String str) {
            this.epoch = str;
        }
    }
}
