package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public final class ScreenDensityUtil {
    public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
    public static final float MAXIMUM_ASPECT_RATIO = 1.7791667f;

    private ScreenDensityUtil() {
    }

    public static float computeCompatibleScaling(Context context) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Point point = new Point();
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 24) {
            defaultDisplay.getMetrics(displayMetrics2);
            point.x = displayMetrics2.widthPixels;
            point.y = displayMetrics2.heightPixels;
        } else {
            defaultDisplay.getRealSize(point);
        }
        int i3 = point.x;
        int i4 = point.y;
        if (i3 < i4) {
            i = i3;
            i2 = i4;
        } else {
            i2 = i3;
            i = i4;
        }
        int i5 = (int) ((displayMetrics.density * 320.0f) + 0.5f);
        float f = ((float) i2) / ((float) i);
        if (f > 1.7791667f) {
            f = 1.7791667f;
        }
        int i6 = (int) ((((float) i5) * f) + 0.5f);
        if (i3 >= i4) {
            int i7 = i6;
            i6 = i5;
            i5 = i7;
        }
        return Math.max(1.0f, Math.min(Math.min(((float) i3) / ((float) i5), ((float) i4) / ((float) i6)), 1.7791667f));
    }
}
