package com.google.appinventor.components.runtime.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.Texting;
import com.microsoft.appcenter.Constants;
import gnu.expr.Declaration;
import java.util.Locale;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 8647;
    public static final String TAG = "SmsBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(intent);
        String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(intent);
        Log.i(TAG, "Received " + hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME + " : " + B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        int isReceivingEnabled = Texting.isReceivingEnabled(context);
        if (isReceivingEnabled == 1) {
            Log.i(TAG, context.getApplicationInfo().packageName + " Receiving is not enabled, ignoring message.");
        } else if ((isReceivingEnabled == 2 || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context)) && !Texting.isRunning()) {
            Log.i(TAG, context.getApplicationInfo().packageName + " Texting isn't running, and either receivingEnabled is FOREGROUND or we are the repl.");
        } else {
            Texting.handledReceivedMessage(context, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            if (Texting.isRunning()) {
                Log.i(TAG, context.getApplicationInfo().packageName + " App in Foreground, delivering message.");
                return;
            }
            Log.i(TAG, context.getApplicationInfo().packageName + " Texting isn't running, but receivingEnabled == 2, sending notification.");
            Log.i(TAG, "sendingNotification " + hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            String packageName = context.getPackageName();
            Log.i(TAG, "Package name : ".concat(String.valueOf(packageName)));
            try {
                String str = packageName + ".Screen1";
                Intent intent2 = new Intent(context, Class.forName(str));
                intent2.setAction("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.addFlags(805306368);
                ((NotificationManager) context.getSystemService("notification")).notify((String) null, NOTIFICATION_ID, new NotificationCompat.Builder(context).setSmallIcon(17301648).setTicker(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME + " : " + B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).setWhen(System.currentTimeMillis()).setAutoCancel(true).setDefaults(1).setContentTitle("Sms from ".concat(String.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME))).setContentText(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).setContentIntent(PendingIntent.getActivity(context, 0, intent2, Declaration.PACKAGE_ACCESS)).setNumber(Texting.getCachedMsgCount()).build());
                Log.i(TAG, "Notification sent, classname: ".concat(String.valueOf(str)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Intent intent) {
        String str = "";
        try {
            if (intent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
                return PhoneNumberUtils.formatNumber(intent.getExtras().getString(Texting.PHONE_NUMBER_TAG));
            }
            for (SmsMessage next : KitkatUtil.getMessagesFromIntent(intent)) {
                if (next != null) {
                    str = PhoneNumberUtils.formatNumber(next.getOriginatingAddress(), Locale.getDefault().getCountry());
                }
            }
            return str;
        } catch (NullPointerException e) {
            Log.w(TAG, "Unable to retrieve originating address from SmsMessage", e);
        }
    }

    private static String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Intent intent) {
        try {
            if (intent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
                return intent.getExtras().getString(Texting.MESSAGE_TAG);
            }
            StringBuilder sb = new StringBuilder();
            for (SmsMessage next : KitkatUtil.getMessagesFromIntent(intent)) {
                if (next != null) {
                    sb.append(next.getMessageBody());
                }
            }
            return sb.toString();
        } catch (NullPointerException e) {
            Log.w(TAG, "Unable to retrieve message body from SmsMessage", e);
            return "";
        }
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) {
        try {
            String packageName = context.getPackageName();
            if (Class.forName(packageName + ".Screen1").getSuperclass().equals(ReplForm.class)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
