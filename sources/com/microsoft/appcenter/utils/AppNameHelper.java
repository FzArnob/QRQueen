package com.microsoft.appcenter.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class AppNameHelper {
    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        if (i == 0) {
            return String.valueOf(applicationInfo.nonLocalizedLabel);
        }
        return context.getString(i);
    }
}
