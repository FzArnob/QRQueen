package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class KodularCompanionUtil {
    private static String LOG_TAG = "KodularCompanionUtil";

    private KodularCompanionUtil() {
    }

    public static void drawerLayoutFix(DrawerLayout drawerLayout, boolean z, boolean z2) {
        if (z && z2) {
            try {
                drawerLayout.removeViewAt(1);
            } catch (Exception e) {
                String str = LOG_TAG;
                Log.e(str, "Trying to remove drawerLayout for makeroid companion failed. Reason: " + e.getMessage());
            }
        }
    }

    public static void statusBarColor(Activity activity, boolean z, int i) {
        if (z) {
            try {
                Log.i(LOG_TAG, "Trying to re-add statusbar color.");
                KodularHelper.setStatusBarColor(activity, i);
            } catch (Exception e) {
                String str = LOG_TAG;
                Log.e(str, "Trying to re-add statusbar color failed. Reason: " + e.getMessage());
            }
        }
    }

    public static void toolbarColor(Toolbar toolbar, boolean z, int i) {
        if (z) {
            try {
                Log.i(LOG_TAG, "Trying to re-add titlebar color.");
                toolbar.setBackground(new ColorDrawable(i));
            } catch (Exception e) {
                String str = LOG_TAG;
                Log.e(str, "Trying to re-add titlebar color failed. Reason: " + e.getMessage());
            }
        }
    }
}
