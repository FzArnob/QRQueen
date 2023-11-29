package com.microsoft.appcenter;

import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.InstrumentationRegistryHelper;

class ServiceInstrumentationUtils {
    static final String DISABLE_ALL_SERVICES = "All";
    static final String DISABLE_SERVICES = "APP_CENTER_DISABLE";

    ServiceInstrumentationUtils() {
    }

    static boolean isServiceDisabledByInstrumentation(String str) {
        try {
            String string = InstrumentationRegistryHelper.getArguments().getString(DISABLE_SERVICES);
            if (string == null) {
                return false;
            }
            for (String trim : string.split(",")) {
                String trim2 = trim.trim();
                if (trim2.equals(DISABLE_ALL_SERVICES) || trim2.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (IllegalStateException | LinkageError unused) {
            AppCenterLog.debug("AppCenter", "Cannot read instrumentation variables in a non-test environment.");
            return false;
        }
    }
}
