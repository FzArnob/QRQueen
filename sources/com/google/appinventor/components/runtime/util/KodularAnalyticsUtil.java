package com.google.appinventor.components.runtime.util;

import android.app.Application;
import android.content.Context;
import com.google.appinventor.common.version.GitBuildId;
import com.google.appinventor.components.runtime.Form;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import java.util.HashMap;
import java.util.Map;

public class KodularAnalyticsUtil {

    public static class Ads {
        public static final String FORMAT_BANNER = "Banner";
        public static final String FORMAT_INTERSTITIAL = "Interstitial";
        public static final String FORMAT_SURVEY = "Rewarded Survey";
        public static final String FORMAT_VIDEO = "Rewarded Video";
        public static final String NETWORK_ADCOLONY = "AdColony";
        public static final String NETWORK_ADMANAGER = "Ad Manager";
        public static final String NETWORK_ADMOB = "Admob";
        public static final String NETWORK_AMAZON = "Amazon";
        public static final String NETWORK_APPLOVIN = "AppLovin";
        public static final String NETWORK_FACEBOOK = "Facebook";
        public static final String NETWORK_LEADBOLT = "Leadbolt";
        public static final String NETWORK_POLLFISH = "Pollfish";
        public static final String NETWORK_STARTAPP = "StartApp";
        public static final String NETWORK_UNITY = "Unity";
    }

    public static void init(Application application, Context context, Form form) {
        if (!AppCenter.isConfigured()) {
            AppCenter.start(application, form.IsCompanion() ? "18e6b6d5-7c2b-4a1a-9042-e3448b8c323c" : "34294338-ee8e-4726-a3aa-9a17934f7bcc", Analytics.class, Crashes.class);
        }
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App Started", new a().hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App ID", form.getAppId()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Regular Package Name", form.getKodularPackageName()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Custom Package Name", form.getPackageName()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App Name", Form.getApplicationName(context)).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Kodular Version", GitBuildId.getVersion()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Installed From", form.getInstalledFrom()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Is Custom", form.isCustomPackage() ? "Yes" : "No").moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
    }

    public static void screenOpen(String str, Form form) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Screen", new a().hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App ID", form.getAppId()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Screen Name", str).moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
    }

    public static void adEvent(String str, String str2, Form form) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Ads", new a().hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App ID", form.getAppId()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Network", str).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Format", str2).moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
    }

    public static void logError(String str, String str2, int i, String str3, Form form) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Error Occurred", new a().hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("App ID", form.getAppId()).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Component", str).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Block", str2).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Code", String.valueOf(i)).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Message", str3).moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Map<String, String> map) {
        if (map == null) {
            Analytics.trackEvent(str);
        } else {
            Analytics.trackEvent(str, map);
        }
    }

    static class a {
        final Map<String, String> moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = new HashMap();

        public final a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2) {
            this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0.put(str, str2);
            return this;
        }
    }
}
