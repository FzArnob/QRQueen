package com.google.appinventor.components.runtime.multidex;

import android.app.Application;
import android.content.Context;

public class MultiDexApplication extends Application {
    public static boolean installed = false;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this, true);
    }
}
