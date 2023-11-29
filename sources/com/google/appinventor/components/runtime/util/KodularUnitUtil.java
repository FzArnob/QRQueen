package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.TypedValue;

public class KodularUnitUtil {
    public static int PixelsToDp(Context context, int i) {
        return (int) (((float) i) / context.getResources().getDisplayMetrics().density);
    }

    public static float PixelsToDp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().density;
    }

    public static int DpToPixels(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }

    public static float DpToPixels(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static int SpToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(2, (float) i, context.getResources().getDisplayMetrics());
    }

    public static float SpToPixels(Context context, float f) {
        return TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }
}
