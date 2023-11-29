package com.google.appinventor.components.runtime;

import android.app.Application;
import android.content.Context;
import com.google.appinventor.components.runtime.multidex.MultiDex;

public class ReplApplication extends Application {
    public static boolean installed = true;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        installed = MultiDex.install(this, false);
    }
}
