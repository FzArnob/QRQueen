package com.google.appinventor.components.runtime.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Log;
import android.view.View;

public class KodularRippleDrawable {
    private KodularRippleDrawable() {
    }

    public static void setRippleDrawable(View view, int i, int i2) {
        try {
            view.setBackground(new RippleDrawable(ColorStateList.valueOf(i2), view.getBackground(), (Drawable) null));
        } catch (Exception e) {
            Log.e("KodularRippleDrawable", String.valueOf(e));
        }
    }
}
