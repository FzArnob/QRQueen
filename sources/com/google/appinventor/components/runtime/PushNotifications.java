package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Push Notifications component powered by OneSignal", iconName = "images/pushNotifications.png", nonVisible = true, version = 6)
@UsesLibraries({"OneSignal.jar", "OneSignal.aar", "play-services-base.aar", "play-services-base.jar", "play-services-basement.aar", "play-services-basement.jar", "play-services-location.aar", "play-services-location.jar", "play-services-stats.aar", "play-services-stats.jar", "play-services-tasks.aar", "play-services-tasks.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "com.google.android.c2dm.permission.RECEIVE", "android.permission.WAKE_LOCK", "android.permission.VIBRATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.READ_APP_BADGE", "com.sec.android.provider.badge.permission.READ", "com.sec.android.provider.badge.permission.WRITE", "com.htc.launcher.permission.READ_SETTINGS", "com.htc.launcher.permission.UPDATE_SHORTCUT", "com.sonyericsson.home.permission.BROADCAST_BADGE", "com.sonymobile.home.permission.PROVIDER_INSERT_BADGE", "com.anddoes.launcher.permission.UPDATE_COUNT", "com.majeur.launcher.permission.UPDATE_BADGE", "com.huawei.android.launcher.permission.CHANGE_BADGE", "com.huawei.android.launcher.permission.READ_SETTINGS", "com.huawei.android.launcher.permission.WRITE_SETTINGS", "com.oppo.launcher.permission.READ_SETTINGS", "com.oppo.launcher.permission.WRITE_SETTINGS", "me.everything.badger.permission.BADGE_COUNT_READ", "me.everything.badger.permission.BADGE_COUNT_WRITE"})
public final class PushNotifications extends AndroidNonvisibleComponent implements Component {
    private Activity activity;
    private String appId = "";
    private Context context;

    public PushNotifications(Form form) {
        super(form);
        this.context = form.$context();
        this.activity = form.$context();
        OneSignal.startInit(this.context).autoPromptLocation(false).unsubscribeWhenNotificationsAreDisabled(true).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).setNotificationReceivedHandler(new b(this, (byte) 0)).setNotificationOpenedHandler(new a(this, (byte) 0)).init();
        Log.d("Push Notifications", "OneSignal Initialized");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set here your One Signal App ID", userVisible = false)
    public final void OneSignalAppId(String str) {
        this.appId = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get Permission Status")
    public final boolean GetPermissionStatus() {
        return OneSignal.getPermissionSubscriptionState().getPermissionStatus().getEnabled();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the subscription Status")
    public final boolean GetSubscriptionStatus() {
        try {
            return OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getSubscribed();
        } catch (Exception unused) {
            return false;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the User ID. If there is no user id it will return '-1'.")
    public final String GetUserId() {
        try {
            OSPermissionSubscriptionState permissionSubscriptionState = OneSignal.getPermissionSubscriptionState();
            if (permissionSubscriptionState.getSubscriptionStatus().getUserId() == null) {
                return "-1";
            }
            return permissionSubscriptionState.getSubscriptionStatus().getUserId();
        } catch (Exception unused) {
            return "-1";
        }
    }

    @SimpleProperty(description = "If you want to subscribe then set it to true.")
    public final void SetSubscription(boolean z) {
        OneSignal.setSubscription(z);
    }

    @SimpleFunction(description = "Clear All Notifications.")
    public final void ClearAllNotifications() {
        OneSignal.clearOneSignalNotifications();
    }

    @SimpleProperty(description = "If you want to enable the log then set it to true.")
    public final void EnableLog(boolean z) {
        if (z) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.setLogLevel(log_level, log_level);
            return;
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.NONE);
    }

    @SimpleProperty(description = "Enable Vibration.")
    public final void EnableVibration(boolean z) {
        OneSignal.enableVibrate(z);
    }

    @SimpleProperty(description = "Enable Sound.")
    public final void EnableSound(boolean z) {
        OneSignal.enableSound(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the email subscription user id.")
    public final String GetEmailSubscriptionUserId() {
        String emailUserId = OneSignal.getPermissionSubscriptionState().getEmailSubscriptionStatus().getEmailUserId();
        return emailUserId != null ? emailUserId : "";
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the email subscription email address.")
    public final String GetEmailSubscriptionEmailAddress() {
        String emailAddress = OneSignal.getPermissionSubscriptionState().getEmailSubscriptionStatus().getEmailAddress();
        return emailAddress != null ? emailAddress : "";
    }

    @SimpleFunction(description = "Tag a user based on an app event of your choosing so later you can create segments in to target these users.")
    public final void SendTag(String str, String str2) {
        if (!str2.isEmpty()) {
            OneSignal.sendTag(str, str2);
        }
    }

    @SimpleFunction(description = "Deletes a single tag that was previously set on a user.")
    public final void DeleteTag(String str) {
        OneSignal.deleteTag(str);
    }

    @SimpleFunction(description = "Get a list of available tags.")
    public final void GetAvailableTags() {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                OneSignal.getTags(new OneSignal.GetTagsHandler() {
                    public final void tagsAvailable(JSONObject jSONObject) {
                        if (jSONObject == null) {
                            PushNotifications.this.GotAvailableTags("{}", YailList.makeEmptyList());
                            return;
                        }
                        Iterator<String> keys = jSONObject.keys();
                        int i = 0;
                        String[] strArr = new String[jSONObject.length()];
                        while (keys.hasNext()) {
                            strArr[i] = keys.next();
                            i++;
                        }
                        PushNotifications pushNotifications = PushNotifications.this;
                        pushNotifications.GotAvailableTags(jSONObject.toString(), YailList.makeList((Object[]) strArr));
                    }
                });
            }
        });
    }

    @SimpleFunction(description = "Get value for tag.")
    public final void GetValue(final String str, final String str2) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                OneSignal.getTags(new OneSignal.GetTagsHandler() {
                    public final void tagsAvailable(JSONObject jSONObject) {
                        String str;
                        if (jSONObject == null) {
                            PushNotifications.this.GotValue(str, str2);
                            return;
                        }
                        try {
                            str = jSONObject.getString(str);
                        } catch (JSONException unused) {
                            str = str2;
                        }
                        PushNotifications.this.GotValue(str, str);
                    }
                });
            }
        });
    }

    @SimpleEvent(description = "Event to detect available one signal tags.")
    public final void GotAvailableTags(final String str, final YailList yailList) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(PushNotifications.this, "GotAvailableTags", str, yailList);
            }
        });
    }

    @SimpleEvent(description = "Event to receive value for a tag.")
    public final void GotValue(final String str, final String str2) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(PushNotifications.this, "GotValue", str, str2);
            }
        });
    }

    class b implements OneSignal.NotificationReceivedHandler {
        private b() {
        }

        /* synthetic */ b(PushNotifications pushNotifications, byte b) {
            this();
        }

        public final void notificationReceived(OSNotification oSNotification) {
            String str = oSNotification.payload.notificationID;
            String str2 = oSNotification.payload.title;
            String str3 = oSNotification.payload.body;
            PushNotifications pushNotifications = PushNotifications.this;
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str3 == null) {
                str3 = "";
            }
            pushNotifications.NotificationReceived(str, str2, str3);
        }
    }

    @SimpleEvent(description = "User received a notification.")
    public final void NotificationReceived(String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "NotificationReceived", str, str2, str3);
    }

    class a implements OneSignal.NotificationOpenedHandler {
        private a() {
        }

        /* synthetic */ a(PushNotifications pushNotifications, byte b) {
            this();
        }

        public final void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult) {
            String str = oSNotificationOpenResult.notification.payload.notificationID;
            String str2 = oSNotificationOpenResult.notification.payload.title;
            String str3 = oSNotificationOpenResult.notification.payload.body;
            PushNotifications pushNotifications = PushNotifications.this;
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str3 == null) {
                str3 = "";
            }
            pushNotifications.NotificationOpened(str, str2, str3);
        }
    }

    @SimpleEvent(description = "User opened a notification.")
    public final void NotificationOpened(String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "NotificationOpened", str, str2, str3);
    }

    @SimpleEvent(description = "Returns true if your message was send with success out of your app to all users.")
    public final void SendMessageDone(boolean z) {
        EventDispatcher.dispatchEvent(this, "SendMessageDone", Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0081 }
            java.lang.String r3 = "https://onesignal.com/api/v1/notifications"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0081 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x0081 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x0081 }
            r2.setUseCaches(r0)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r1 = 1
            r2.setDoOutput(r1)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r2.setDoInput(r1)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/json; charset=UTF-8"
            r2.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = "Authorization"
            java.lang.String r4 = "Basic "
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r8 = r4.concat(r8)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r2.setRequestProperty(r3, r8)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r8 = "POST"
            r2.setRequestMethod(r8)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = "{\"app_id\": \""
            r8.<init>(r3)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = r5.appId     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r8.append(r3)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = "\",\"included_segments\": [\"All\"],\"headings\": {\"en\": \""
            r8.append(r3)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r8.append(r6)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r6 = "\"},\"contents\": {\"en\": \""
            r8.append(r6)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r8.append(r7)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r6 = "\"}}"
            r8.append(r6)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.lang.String r6 = r8.toString()     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            byte[] r6 = r6.getBytes(r7)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            int r7 = r6.length     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r2.setFixedLengthStreamingMode(r7)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            java.io.OutputStream r7 = r2.getOutputStream()     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r7.write(r6)     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            int r6 = r2.getResponseCode()     // Catch:{ Exception -> 0x007c, all -> 0x0079 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L_0x0073
            r0 = 1
        L_0x0073:
            if (r2 == 0) goto L_0x0078
            r2.disconnect()
        L_0x0078:
            return r0
        L_0x0079:
            r6 = move-exception
            r1 = r2
            goto L_0x0091
        L_0x007c:
            r6 = move-exception
            r1 = r2
            goto L_0x0082
        L_0x007f:
            r6 = move-exception
            goto L_0x0091
        L_0x0081:
            r6 = move-exception
        L_0x0082:
            java.lang.String r7 = "Push Notifications"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x007f }
            android.util.Log.e(r7, r6)     // Catch:{ all -> 0x007f }
            if (r1 == 0) goto L_0x0090
            r1.disconnect()
        L_0x0090:
            return r0
        L_0x0091:
            if (r1 == 0) goto L_0x0096
            r1.disconnect()
        L_0x0096:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.PushNotifications.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    @SimpleFunction(description = "Send a message to all users. The message and your REST Api Key can not be empty! You will find your REST Api Key in your OneSignal account settings.")
    public final void SendMessage(final String str, final String str2, final String str3) {
        new AsyncTask<Integer, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public final /* synthetic */ Object doInBackground(Object[] objArr) {
                return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
            }

            /* access modifiers changed from: protected */
            public final /* synthetic */ void onPostExecute(Object obj) {
                PushNotifications.this.SendMessageDone(((Boolean) obj).booleanValue());
            }

            private Boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
                try {
                    return Boolean.valueOf(PushNotifications.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str, str2, str3));
                } catch (Exception e) {
                    Log.e("Push Notifications", String.valueOf(e));
                    return Boolean.FALSE;
                }
            }
        }.execute(new Integer[0]);
    }
}
