package com.microsoft.appcenter.utils.context;

import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class SessionContext {
    private static final String STORAGE_KEY = "sessions";
    private static final String STORAGE_KEY_VALUE_SEPARATOR = "/";
    private static final int STORAGE_MAX_SESSIONS = 10;
    private static SessionContext sInstance;
    private final long mAppLaunchTimestamp = System.currentTimeMillis();
    private final NavigableMap<Long, SessionInfo> mSessions = new TreeMap();

    private SessionContext() {
        Set<String> stringSet = SharedPreferencesManager.getStringSet(STORAGE_KEY);
        if (stringSet != null) {
            for (String next : stringSet) {
                String[] split = next.split("/", -1);
                try {
                    long parseLong = Long.parseLong(split[0]);
                    String str = split[1];
                    this.mSessions.put(Long.valueOf(parseLong), new SessionInfo(parseLong, str.isEmpty() ? null : UUID.fromString(str), split.length > 2 ? Long.parseLong(split[2]) : parseLong));
                } catch (RuntimeException e) {
                    AppCenterLog.warn("AppCenter", "Ignore invalid session in store: " + next, e);
                }
            }
        }
        AppCenterLog.debug("AppCenter", "Loaded stored sessions: " + this.mSessions);
        addSession((UUID) null);
    }

    public static synchronized SessionContext getInstance() {
        SessionContext sessionContext;
        synchronized (SessionContext.class) {
            if (sInstance == null) {
                sInstance = new SessionContext();
            }
            sessionContext = sInstance;
        }
        return sessionContext;
    }

    public static synchronized void unsetInstance() {
        synchronized (SessionContext.class) {
            sInstance = null;
        }
    }

    public synchronized void addSession(UUID uuid) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mSessions.put(Long.valueOf(currentTimeMillis), new SessionInfo(currentTimeMillis, uuid, this.mAppLaunchTimestamp));
        if (this.mSessions.size() > 10) {
            this.mSessions.pollFirstEntry();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (SessionInfo sessionInfo : this.mSessions.values()) {
            linkedHashSet.add(sessionInfo.toString());
        }
        SharedPreferencesManager.putStringSet(STORAGE_KEY, linkedHashSet);
    }

    public synchronized SessionInfo getSessionAt(long j) {
        Map.Entry<Long, SessionInfo> floorEntry = this.mSessions.floorEntry(Long.valueOf(j));
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getValue();
    }

    public synchronized void clearSessions() {
        this.mSessions.clear();
        SharedPreferencesManager.remove(STORAGE_KEY);
    }

    public static class SessionInfo {
        private final long mAppLaunchTimestamp;
        private final UUID mSessionId;
        private final long mTimestamp;

        SessionInfo(long j, UUID uuid, long j2) {
            this.mTimestamp = j;
            this.mSessionId = uuid;
            this.mAppLaunchTimestamp = j2;
        }

        /* access modifiers changed from: package-private */
        public long getTimestamp() {
            return this.mTimestamp;
        }

        public UUID getSessionId() {
            return this.mSessionId;
        }

        public long getAppLaunchTimestamp() {
            return this.mAppLaunchTimestamp;
        }

        public String toString() {
            String str = getTimestamp() + "/";
            if (getSessionId() != null) {
                str = str + getSessionId();
            }
            return str + "/" + getAppLaunchTimestamp();
        }
    }
}
