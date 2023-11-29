package com.microsoft.appcenter.utils.context;

import android.text.TextUtils;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserIdContext {
    private static final String CUSTOM_PREFIX = "c";
    public static final int USER_ID_APP_CENTER_MAX_LENGTH = 256;
    private static UserIdContext sInstance;
    private final Set<Listener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap());
    private String mUserId;

    public interface Listener {
        void onNewUserId(String str);
    }

    public static synchronized UserIdContext getInstance() {
        UserIdContext userIdContext;
        synchronized (UserIdContext.class) {
            if (sInstance == null) {
                sInstance = new UserIdContext();
            }
            userIdContext = sInstance;
        }
        return userIdContext;
    }

    public static synchronized void unsetInstance() {
        synchronized (UserIdContext.class) {
            sInstance = null;
        }
    }

    public static boolean checkUserIdValidForOneCollector(String str) {
        if (str == null) {
            return true;
        }
        if (str.isEmpty()) {
            AppCenterLog.error("AppCenter", "userId must not be empty.");
            return false;
        }
        int indexOf = str.indexOf(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (indexOf >= 0) {
            String substring = str.substring(0, indexOf);
            if (!substring.equals(CUSTOM_PREFIX)) {
                AppCenterLog.error("AppCenter", String.format("userId prefix must be '%s%s', '%s%s' is not supported.", new Object[]{CUSTOM_PREFIX, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, substring, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR}));
                return false;
            } else if (indexOf == str.length() - 1) {
                AppCenterLog.error("AppCenter", "userId must not be empty.");
                return false;
            }
        }
        return true;
    }

    public static boolean checkUserIdValidForAppCenter(String str) {
        if (str == null || str.length() <= 256) {
            return true;
        }
        AppCenterLog.error("AppCenter", "userId is limited to 256 characters.");
        return false;
    }

    public static String getPrefixedUserId(String str) {
        if (str == null || str.contains(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) {
            return str;
        }
        return "c:" + str;
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public synchronized String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        if (updateUserId(str)) {
            for (Listener onNewUserId : this.mListeners) {
                onNewUserId.onNewUserId(this.mUserId);
            }
        }
    }

    private synchronized boolean updateUserId(String str) {
        if (TextUtils.equals(this.mUserId, str)) {
            return false;
        }
        this.mUserId = str;
        return true;
    }
}
