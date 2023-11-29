package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsService;
import java.util.ArrayList;
import java.util.List;

public class KodularChromeCustomTabsHelper {
    private static String LOG_TAG = "KodularChromeCustomTabsHelper";
    private static String SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc;

    private KodularChromeCustomTabsHelper() {
    }

    public static String getPackageNameToUse(Context context) {
        String str = SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc;
        if (str != null) {
            return str;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String str2 = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(next.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(next.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = null;
        } else if (arrayList.size() == 1) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(str2) && !hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context, intent) && arrayList.contains(str2)) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = str2;
        } else if (arrayList.contains("com.android.chrome")) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = "com.android.chrome";
        } else if (arrayList.contains("com.chrome.beta")) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = "com.chrome.beta";
        } else if (arrayList.contains("com.chrome.dev")) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = "com.chrome.dev";
        } else if (arrayList.contains("com.google.android.apps.chrome")) {
            SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc = "com.google.android.apps.chrome";
        }
        return SZ5aRRQoo4RABtl0KnlkQDhNcppg3ZUNkmsRmBa9EW4UdQenZXJCu8cuK0SgtVYc;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo next : queryIntentActivities) {
                        IntentFilter intentFilter = next.filter;
                        if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && next.activityInfo != null) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            Log.e(LOG_TAG, "Runtime exception while getting specialized handlers");
        }
    }

    public static String[] getPackages() {
        return new String[]{"", "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.google.android.apps.chrome"};
    }
}
