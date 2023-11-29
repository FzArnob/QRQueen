package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "A component to send a notification to the users device", iconName = "images/notification.png", nonVisible = true, version = 1)
public final class NotificationComponent extends AndroidNonvisibleComponent {
    private int IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = 0;
    private ComponentContainer container;
    private Context context;
    private NotificationCompat.Builder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private NotificationManagerCompat f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;
    private int x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;

    public NotificationComponent(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Activity $context = componentContainer.$context();
        this.context = $context;
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = NotificationManagerCompat.from($context);
    }

    @SimpleFunction(description = "Send a notification")
    public final void SendNotification(String str, String str2) {
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(0, new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis())).setChannelId(String.valueOf(System.currentTimeMillis())).setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setPriority(this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc).setVisibility(this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0)).build());
        Log.i("NotificationComponent", "Notification Send");
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_priority")
    @SimpleProperty(description = "Set the priority level. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public final void Priority(int i) {
        if (i == 1) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 0;
        } else if (i == 2) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 1;
        } else if (i == 3) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = -1;
        } else if (i == 4) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 2;
        } else if (i != 5) {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = 0;
        } else {
            this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = -2;
        }
        Log.i("NotificationComponent", "Notification Priority set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Priority of the notification")
    public final int Priority() {
        Log.i("NotificationComponent", "Notification Priority returned");
        return this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_visibility")
    @SimpleProperty(description = "Set the visibility property. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public final void Visiblity(int i) {
        if (i == 1) {
            this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = 1;
        } else if (i == 2) {
            this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = 0;
        } else if (i != 3) {
            this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = 1;
        } else {
            this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = -1;
        }
        Log.i("NotificationComponent", "Notification Visibity set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Visiblity of the notification")
    public final int Visiblity() {
        Log.i("NotificationComponent", "Notification Visibity returned");
        return this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;
    }

    @SimpleFunction(description = "Send a advanced notification (Android Wear Support)")
    public final void SendAdvancedNotification(int i, String str, String str2, String str3, int i2, int i3) {
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i, new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis())).setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setPriority(this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc).setVisibility(this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3), 0)).build());
        Log.i("NotificationComponent", "Advanced Notification Send");
    }

    @SimpleFunction(description = "Check whether the app can send Notifications")
    public final boolean AreNotificationsEnabled() {
        return this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.areNotificationsEnabled();
    }

    @SimpleFunction(description = "Cancel all Notifications")
    public final void CancelAll() {
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancelAll();
        Log.i("NotificationComponent", "All Notifications Cancelled");
    }

    @SimpleFunction(description = "Cancel a Notification with an id")
    public final void CancelNotification(int i) {
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel(i);
        Log.i("NotificationComponent", "Notification with id " + i + " canceled");
    }

    @SimpleFunction(description = "Start building a new notification")
    public final void NotificationBuilderStart(String str, String str2) {
        this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = 0;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis())).setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0));
        Log.i("NotificationComponent", "Notification Building Started");
    }

    @SimpleFunction(description = "Set the notification id.")
    public final void NotificationBuilderID(int i) {
        this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = i;
        Log.i("NotificationComponent", "Notification ID Added");
    }

    @SimpleFunction(description = "Set the start value of the notification.")
    public final void NotificationBuilderStartValue(String str) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str), 0));
        Log.i("NotificationComponent", "Notification Start Value Added");
    }

    @SimpleFunction(description = "Set whether the notification should me removed after the user clicks on it.")
    public final void NotificationBuilderAutoCancel(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAutoCancel(z);
        Log.i("NotificationComponent", "Notification Auto Cancel Added");
    }

    @SimpleFunction(description = "Set the big picture for the notification")
    public final void NotificationBuilderBigPicture(String str) {
        if (str == null) {
            str = "";
        }
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(MediaUtil.getBitmapDrawable(this.container.$form(), str).getBitmap()));
            Log.i("NotificationComponent", "Notifications Big Picture Added");
        } catch (Exception e) {
            Log.e("Notification IOException", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Set the background image for Android Wear devices")
    public final void NotificationBuilderAndroidWear(String str) {
        if (str == null) {
            str = "";
        }
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.extend(new NotificationCompat.WearableExtender().setBackground(MediaUtil.getBitmapDrawable(this.container.$form(), str).getBitmap()));
            Log.i("NotificationComponent", "Android Wear Background Image Added");
        } catch (Exception e) {
            Log.e("Notification IOException", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Set the accent color for Android Car devices")
    public final void NotificationBuilderAndroidCar(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.extend(new NotificationCompat.CarExtender().setColor(i));
        Log.i("NotificationComponent", "Android Car Accent Color Added");
    }

    @SimpleFunction(description = "Set the notification builder priority. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public final void NotificationBuilderPriority(int i) {
        int i2 = 0;
        if (i != 1) {
            if (i == 2) {
                i2 = 1;
            } else if (i == 3) {
                i2 = -1;
            } else if (i == 4) {
                i2 = 2;
            } else if (i == 5) {
                i2 = -2;
            }
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPriority(i2);
        Log.i("NotificationComponent", "Notification Priority Added");
    }

    @SimpleFunction(description = "Set the notification builder visibility. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public final void NotificationBuilderVisibility(int i) {
        int i2 = 1;
        if (i != 1) {
            if (i == 2) {
                i2 = 0;
            } else if (i == 3) {
                i2 = -1;
            }
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(i2);
        Log.i("NotificationComponent", "Notification Visiblity Added");
    }

    @SimpleFunction(description = "Send the notification that you build")
    public final void NotificationBuilderSend() {
        this.f241hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.build());
        Log.i("NotificationComponent", "Notification Send");
    }

    private Intent hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        launchIntentForPackage.putExtra("APP_INVENTOR_START", str);
        return launchIntentForPackage;
    }
}
