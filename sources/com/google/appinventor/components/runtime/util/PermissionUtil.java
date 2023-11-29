package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.content.ContextCompat;
import gnu.expr.Declaration;

public class PermissionUtil {
    private static String LOG_TAG = "Permission Util";

    private PermissionUtil() {
    }

    public static String[] getNeededPermissions(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(packageManager.getApplicationInfo(context.getPackageName(), 0).packageName, 4096).requestedPermissions;
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return new String[0];
        }
    }

    public static boolean arePermissionsGranted(Context context) {
        String[] neededPermissions = getNeededPermissions(context);
        if (!(neededPermissions == null || neededPermissions.length == 0)) {
            for (String checkSelfPermission : neededPermissions) {
                if (ContextCompat.checkSelfPermission((Activity) context, checkSelfPermission) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void appSettings(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", context.getPackageName(), (String) null));
        intent.addFlags(Declaration.IS_DYNAMIC);
        context.startActivity(intent);
    }

    public static void appSystemSettings(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(Declaration.IS_DYNAMIC);
            context.startActivity(intent);
        }
    }

    public static boolean writeSystemSettings(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.System.canWrite(context);
        }
        return true;
    }
}
