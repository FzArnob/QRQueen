package com.google.appinventor.components.runtime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import gnu.expr.Declaration;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "A component to send a notification to the users device", iconName = "images/notification.png", nonVisible = true, version = 1)
public class MakeroidNotification extends AndroidNonvisibleComponent {
    private int AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    private String G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = "MakeroidNotification";
    private int UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5;
    private ComponentContainer container;
    private Context context;
    private NotificationManagerCompat hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;

    public MakeroidNotification(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Priority(1);
        Visiblity(1);
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS();
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_priority")
    @SimpleProperty(description = "Set the priority level. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public void Priority(int i) {
        if (i == 2) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 1;
            if (Build.VERSION.SDK_INT >= 24) {
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = 4;
            }
        } else if (i == 3) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = -1;
            if (Build.VERSION.SDK_INT >= 24) {
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = 2;
            }
        } else if (i == 4) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 2;
            if (Build.VERSION.SDK_INT >= 24) {
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = 5;
            }
        } else if (i != 5) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 0;
            if (Build.VERSION.SDK_INT >= 24) {
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = 3;
            }
        } else {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = -2;
            if (Build.VERSION.SDK_INT >= 24) {
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = 1;
            }
        }
        Log.i("MakeroidNotification", "Notification Priority set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Priority of the notification")
    public int Priority() {
        Log.i("MakeroidNotification", "Notification Priority returned");
        return this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_visibility")
    @SimpleProperty(description = "Set the visibility property. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public void Visiblity(int i) {
        if (i == 2) {
            this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = 0;
        } else if (i != 3) {
            this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = 1;
        } else {
            this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = -1;
        }
        Log.i("MakeroidNotification", "Notification Visibility set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Visibility of the notification")
    public int Visiblity() {
        Log.i("MakeroidNotification", "Notification Visibility returned");
        return this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    }

    @SimpleFunction(description = "Send a simple notification")
    public void SimpleNotification(String str, String str2) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl).setSmallIcon(17301659).setContentTitle(str).setContentText(str2).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setPriority(this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc).setVisibility(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0));
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify((int) System.currentTimeMillis(), contentIntent.build());
        Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Send a normal notification")
    public void NormalNotification(int i, String str, String str2, String str3, boolean z) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl).setSmallIcon(17301659).setContentTitle(str).setContentText(str2).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setPriority(this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc).setVisibility(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl).setAutoCancel(z).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3), 0));
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i, contentIntent.build());
        Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Send a normal notification")
    public void BigPictureNotification(int i, String str, String str2, String str3, String str4, boolean z) {
        Bitmap bitmap;
        try {
            bitmap = MediaUtil.getBitmapDrawable(this.container.$form(), str3).getBitmap();
        } catch (Exception e) {
            Log.e("MakeroidNotification", String.valueOf(e));
            bitmap = null;
        }
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl).setSmallIcon(17301659).setContentTitle(str).setContentText(str2).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon((Bitmap) null)).setLargeIcon(bitmap).setPriority(this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc).setVisibility(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl).setAutoCancel(z).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str4), 0));
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i, contentIntent.build());
        Log.i("MakeroidNotification", "Notification Send");
    }

    public static void ScreenshotNotification(Context context2, String str, String str2, String str3, Uri uri, boolean z, boolean z2, Bitmap bitmap) {
        int currentTimeMillis = (int) System.currentTimeMillis();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(uri, "image/*");
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(context2, "MakeroidScreenshot").setContentTitle(str).setContentText(str2).setSmallIcon(17301567).setContentIntent(PendingIntent.getActivity(context2, 0, intent, 0)).setPriority(0).setAutoCancel(true);
        if (z2) {
            autoCancel.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        }
        if (z) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.SEND");
            intent2.putExtra("android.intent.extra.STREAM", uri);
            intent2.putExtra("EXTRA_DETAILS_ID", currentTimeMillis);
            intent2.setType("image/*");
            autoCancel.addAction(17301586, str3, PendingIntent.getActivity(context2, 0, intent2, Declaration.IS_DYNAMIC));
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("MakeroidScreenshot", "Makeroid", 3);
            notificationChannel.setDescription("Makeroid Notification Channel");
            ((NotificationManager) context2.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
        NotificationManagerCompat.from(context2).notify(currentTimeMillis, autoCancel.build());
        Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Check wether the app can send Notifications")
    public boolean AreNotificationsEnabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.areNotificationsEnabled();
    }

    @SimpleFunction(description = "Cancel all Notifications")
    public void CancelAll() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancelAll();
        Log.i("MakeroidNotification", "All Notifications Cancelled");
    }

    @SimpleFunction(description = "Cancel a Notification with an id")
    public void CancelNotification(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel(i);
        Log.i("MakeroidNotification", "Notification with id " + i + " canceled");
    }

    private Intent hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra("APP_INVENTOR_START", str);
        }
        return launchIntentForPackage;
    }

    private void oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl, "Makeroid", this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5);
            notificationChannel.setDescription("Makeroid Notification Channel");
            ((NotificationManager) this.context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = NotificationManagerCompat.from(this.context);
    }
}
