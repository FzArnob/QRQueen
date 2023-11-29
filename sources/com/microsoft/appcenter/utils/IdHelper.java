package com.microsoft.appcenter.utils;

import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.util.UUID;

public class IdHelper {
    public static UUID getInstallId() {
        try {
            return UUID.fromString(SharedPreferencesManager.getString(PrefStorageConstants.KEY_INSTALL_ID, ""));
        } catch (Exception unused) {
            AppCenterLog.warn("AppCenter", "Unable to get installID from Shared Preferences");
            UUID randomUUID = UUID.randomUUID();
            SharedPreferencesManager.putString(PrefStorageConstants.KEY_INSTALL_ID, randomUUID.toString());
            return randomUUID;
        }
    }
}
