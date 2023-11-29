package com.microsoft.appcenter;

import com.microsoft.appcenter.utils.AppCenterLog;

public final class Flags {
    public static final int CRITICAL = 2;
    public static final int DEFAULTS = 1;
    public static final int NORMAL = 1;
    @Deprecated
    public static final int PERSISTENCE_CRITICAL = 2;
    private static final int PERSISTENCE_MASK = 255;
    @Deprecated
    public static final int PERSISTENCE_NORMAL = 1;

    public static int getPersistenceFlag(int i, boolean z) {
        int i2 = i & 255;
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        if (i2 != 0 && z) {
            AppCenterLog.warn("AppCenter", "Invalid value=" + i2 + " for persistence flag, using NORMAL as a default.");
        }
        return 1;
    }
}
