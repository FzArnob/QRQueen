package com.microsoft.appcenter.analytics;

import android.provider.Settings;
import com.microsoft.appcenter.channel.AbstractChannelListener;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.one.AppExtension;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import com.microsoft.appcenter.ingestion.models.one.DeviceExtension;
import com.microsoft.appcenter.ingestion.models.one.UserExtension;
import com.microsoft.appcenter.ingestion.models.properties.TypedProperty;
import com.microsoft.appcenter.utils.context.UserIdContext;
import java.util.Date;
import java.util.Map;

public class PropertyConfigurator extends AbstractChannelListener {
    private static final String ANDROID_DEVICE_ID_PREFIX = "a:";
    /* access modifiers changed from: private */
    public String mAppLocale;
    /* access modifiers changed from: private */
    public String mAppName;
    /* access modifiers changed from: private */
    public String mAppVersion;
    /* access modifiers changed from: private */
    public boolean mDeviceIdEnabled;
    private final EventProperties mEventProperties = new EventProperties();
    private final AnalyticsTransmissionTarget mTransmissionTarget;
    /* access modifiers changed from: private */
    public String mUserId;

    PropertyConfigurator(AnalyticsTransmissionTarget analyticsTransmissionTarget) {
        this.mTransmissionTarget = analyticsTransmissionTarget;
    }

    public void onPreparingLog(Log log, String str) {
        if (shouldOverridePartAProperties(log)) {
            CommonSchemaLog commonSchemaLog = (CommonSchemaLog) log;
            AppExtension app = commonSchemaLog.getExt().getApp();
            UserExtension user = commonSchemaLog.getExt().getUser();
            DeviceExtension device = commonSchemaLog.getExt().getDevice();
            String str2 = this.mAppName;
            if (str2 == null) {
                AnalyticsTransmissionTarget analyticsTransmissionTarget = this.mTransmissionTarget;
                while (true) {
                    analyticsTransmissionTarget = analyticsTransmissionTarget.mParentTarget;
                    if (analyticsTransmissionTarget != null) {
                        String appName = analyticsTransmissionTarget.getPropertyConfigurator().getAppName();
                        if (appName != null) {
                            app.setName(appName);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                app.setName(str2);
            }
            String str3 = this.mAppVersion;
            if (str3 == null) {
                AnalyticsTransmissionTarget analyticsTransmissionTarget2 = this.mTransmissionTarget;
                while (true) {
                    analyticsTransmissionTarget2 = analyticsTransmissionTarget2.mParentTarget;
                    if (analyticsTransmissionTarget2 != null) {
                        String appVersion = analyticsTransmissionTarget2.getPropertyConfigurator().getAppVersion();
                        if (appVersion != null) {
                            app.setVer(appVersion);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                app.setVer(str3);
            }
            String str4 = this.mAppLocale;
            if (str4 == null) {
                AnalyticsTransmissionTarget analyticsTransmissionTarget3 = this.mTransmissionTarget;
                while (true) {
                    analyticsTransmissionTarget3 = analyticsTransmissionTarget3.mParentTarget;
                    if (analyticsTransmissionTarget3 != null) {
                        String appLocale = analyticsTransmissionTarget3.getPropertyConfigurator().getAppLocale();
                        if (appLocale != null) {
                            app.setLocale(appLocale);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                app.setLocale(str4);
            }
            String str5 = this.mUserId;
            if (str5 == null) {
                AnalyticsTransmissionTarget analyticsTransmissionTarget4 = this.mTransmissionTarget;
                while (true) {
                    analyticsTransmissionTarget4 = analyticsTransmissionTarget4.mParentTarget;
                    if (analyticsTransmissionTarget4 != null) {
                        String userId = analyticsTransmissionTarget4.getPropertyConfigurator().getUserId();
                        if (userId != null) {
                            user.setLocalId(userId);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                user.setLocalId(str5);
            }
            if (this.mDeviceIdEnabled) {
                String string = Settings.Secure.getString(this.mTransmissionTarget.mContext.getContentResolver(), "android_id");
                device.setLocalId(ANDROID_DEVICE_ID_PREFIX + string);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r2 = r2.getTag();
        r0 = r1.mTransmissionTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldOverridePartAProperties(com.microsoft.appcenter.ingestion.models.Log r2) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog
            if (r0 == 0) goto L_0x0014
            java.lang.Object r2 = r2.getTag()
            com.microsoft.appcenter.analytics.AnalyticsTransmissionTarget r0 = r1.mTransmissionTarget
            if (r2 != r0) goto L_0x0014
            boolean r2 = r0.isEnabled()
            if (r2 == 0) goto L_0x0014
            r2 = 1
            goto L_0x0015
        L_0x0014:
            r2 = 0
        L_0x0015:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.analytics.PropertyConfigurator.shouldOverridePartAProperties(com.microsoft.appcenter.ingestion.models.Log):boolean");
    }

    private String getAppName() {
        return this.mAppName;
    }

    public void setAppName(final String str) {
        Analytics.getInstance().postCommandEvenIfDisabled(new Runnable() {
            public void run() {
                String unused = PropertyConfigurator.this.mAppName = str;
            }
        });
    }

    private String getAppVersion() {
        return this.mAppVersion;
    }

    public void setAppVersion(final String str) {
        Analytics.getInstance().postCommandEvenIfDisabled(new Runnable() {
            public void run() {
                String unused = PropertyConfigurator.this.mAppVersion = str;
            }
        });
    }

    private String getAppLocale() {
        return this.mAppLocale;
    }

    public void setAppLocale(final String str) {
        Analytics.getInstance().postCommandEvenIfDisabled(new Runnable() {
            public void run() {
                String unused = PropertyConfigurator.this.mAppLocale = str;
            }
        });
    }

    private String getUserId() {
        return this.mUserId;
    }

    public void setUserId(final String str) {
        if (UserIdContext.checkUserIdValidForOneCollector(str)) {
            Analytics.getInstance().postCommandEvenIfDisabled(new Runnable() {
                public void run() {
                    String unused = PropertyConfigurator.this.mUserId = UserIdContext.getPrefixedUserId(str);
                }
            });
        }
    }

    public synchronized void setEventProperty(String str, boolean z) {
        this.mEventProperties.set(str, z);
    }

    public synchronized void setEventProperty(String str, Date date) {
        this.mEventProperties.set(str, date);
    }

    public synchronized void setEventProperty(String str, double d) {
        this.mEventProperties.set(str, d);
    }

    public synchronized void setEventProperty(String str, long j) {
        this.mEventProperties.set(str, j);
    }

    public synchronized void setEventProperty(String str, String str2) {
        this.mEventProperties.set(str, str2);
    }

    public synchronized void removeEventProperty(String str) {
        this.mEventProperties.getProperties().remove(str);
    }

    public void collectDeviceId() {
        Analytics.getInstance().postCommandEvenIfDisabled(new Runnable() {
            public void run() {
                boolean unused = PropertyConfigurator.this.mDeviceIdEnabled = true;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public synchronized void mergeEventProperties(EventProperties eventProperties) {
        for (Map.Entry next : this.mEventProperties.getProperties().entrySet()) {
            String str = (String) next.getKey();
            if (!eventProperties.getProperties().containsKey(str)) {
                eventProperties.getProperties().put(str, (TypedProperty) next.getValue());
            }
        }
    }
}
