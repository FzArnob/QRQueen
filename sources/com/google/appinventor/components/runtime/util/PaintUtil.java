package com.google.appinventor.components.runtime.util;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;

public class PaintUtil {
    private PaintUtil() {
    }

    public static void changePaint(Paint paint, int i) {
        paint.setColor(16777215 & i);
        paint.setAlpha(i >>> 24);
        paint.setXfermode((Xfermode) null);
    }

    public static void changePaintTransparent(Paint paint) {
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public static int hexStringToInt(String str) {
        if (str.startsWith("#x") || str.startsWith("&H")) {
            str = str.substring(2);
        }
        return (int) Long.parseLong(str, 16);
    }
}
