package com.google.appinventor.components.runtime.util;

import android.os.Build;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.PermissionResultHandler;

public class TiramisuUtil {
    public static boolean requestFilePermissions(Form form, String str, String str2, PermissionResultHandler permissionResultHandler) {
        if (!str.startsWith("content:") && ((!str.startsWith("file:") || !FileUtil.needsPermission(form, str)) && !FileUtil.needsReadPermission(new ScopedFile(form.DefaultFileScope(), str)))) {
            str2 = null;
        } else if (Build.VERSION.SDK_INT < 33) {
            str2 = "android.permission.READ_EXTERNAL_STORAGE";
        }
        if (str2 == null || !form.isDeniedPermission(str2)) {
            return false;
        }
        form.askPermission(str2, permissionResultHandler);
        return true;
    }

    public static boolean requestAudioPermissions(Form form, String str, PermissionResultHandler permissionResultHandler) {
        return requestFilePermissions(form, str, "android.permission.READ_MEDIA_AUDIO", permissionResultHandler);
    }

    public static boolean requestImagePermissions(Form form, String str, PermissionResultHandler permissionResultHandler) {
        return requestFilePermissions(form, str, "android.permission.READ_MEDIA_IMAGES", permissionResultHandler);
    }

    public static boolean requestVideoPermissions(Form form, String str, PermissionResultHandler permissionResultHandler) {
        return requestFilePermissions(form, str, "android.permission.READ_MEDIA_VIDEO", permissionResultHandler);
    }
}
