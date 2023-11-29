package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.google.appinventor.components.common.PropertyTypeConstants;

public class KodularResourcesUtil {
    public static String getString(Context context, String str) {
        return context.getResources().getString(getIdentifier(context, str, "string"));
    }

    public static int getColor(Context context, String str) {
        return ContextCompat.getColor(context, getIdentifier(context, str, PropertyTypeConstants.PROPERTY_TYPE_COLOR));
    }

    public static int getDimension(Context context, String str) {
        return (int) context.getResources().getDimension(getIdentifier(context, str, "dimen"));
    }

    public static int getStyle(Context context, String str) {
        return getIdentifier(context, str, "style");
    }

    public static Drawable getDrawable(Context context, String str) {
        return ContextCompat.getDrawable(context, getIdentifier(context, str, "drawable"));
    }

    public static int getIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }
}
