package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;

public class HoneycombMR1Util {
    private HoneycombMR1Util() {
    }

    public static String[] getContactProjection() {
        return new String[]{"_id", "display_name", "photo_thumb_uri", "photo_uri"};
    }

    public static String[] getNameProjection() {
        return new String[]{"contact_id", "display_name", "photo_thumb_uri", "data1"};
    }

    public static String[] getDataProjection() {
        return new String[]{"mimetype", "data1", "data2", "data1", "data2"};
    }

    public static String[] getEmailAdapterProjection() {
        return new String[]{"_id", "display_name", "data1", "mimetype"};
    }

    public static Cursor getDataCursor(String str, Activity activity, String[] strArr) {
        return activity.getContentResolver().query(ContactsContract.Data.CONTENT_URI, strArr, "contact_id=? AND (mimetype=? OR mimetype=?)", new String[]{str, "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/email_v2"}, (String) null);
    }
}
