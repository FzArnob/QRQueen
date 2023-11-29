package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.google.appinventor.components.runtime.Form;
import java.io.File;

public final class NougatUtil {
    private static final String LOG_TAG = "NougatUtil";

    private NougatUtil() {
    }

    public static Uri getPackageUri(Form form, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        String packageName = form.$context().getPackageName();
        Log.d(LOG_TAG, "packageName = ".concat(String.valueOf(packageName)));
        Activity $context = form.$context();
        return FileProvider.getUriForFile($context, packageName + ".provider", file);
    }
}
