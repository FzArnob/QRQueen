package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KitkatUtil {
    private KitkatUtil() {
    }

    public static List<SmsMessage> getMessagesFromIntent(Intent intent) {
        ArrayList arrayList = new ArrayList();
        SmsMessage[] messagesFromIntent = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        if (messagesFromIntent != null && messagesFromIntent.length >= 0) {
            Collections.addAll(arrayList, messagesFromIntent);
        }
        return arrayList;
    }
}
