package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;

public class QUtil {
    public static String getExternalStoragePath(Context context, boolean z, boolean z2) {
        return getExternalStorageDir(context, z, z2).getAbsolutePath();
    }

    public static String getExternalStoragePath(Context context, boolean z) {
        return getExternalStoragePath(context, z, false);
    }

    public static String getExternalStoragePath(Context context) {
        return getExternalStoragePath(context, false, false);
    }

    public static File getExternalStorageDir(Context context) {
        return getExternalStorageDir(context, false);
    }

    public static File getExternalStorageDir(Context context, boolean z) {
        return getExternalStorageDir(context, z, false);
    }

    public static File getExternalStorageDir(Context context, boolean z, boolean z2) {
        if ((z2 || Build.VERSION.SDK_INT < 29) && !z) {
            return Environment.getExternalStorageDirectory();
        }
        return context.getExternalFilesDir((String) null);
    }

    public static String getReplAssetPath(Context context, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            return getExternalStoragePath(context, z) + "/assets/";
        }
        return getExternalStoragePath(context, z) + "/Kodular/assets/";
    }

    public static String getReplAssetPath(Context context) {
        return getReplAssetPath(context, false);
    }

    public static String getReplDataPath(Context context, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            return getExternalStoragePath(context, z) + "/data/";
        }
        return getExternalStoragePath(context, z) + "/Kodular/data/";
    }

    public static String getReplDataPath(Context context) {
        return getReplDataPath(context, false);
    }
}
