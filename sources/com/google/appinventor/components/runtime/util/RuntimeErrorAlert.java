package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public final class RuntimeErrorAlert {
    public static void alert(final Object obj, String str, String str2, String str3) {
        Class<RuntimeErrorAlert> cls = RuntimeErrorAlert.class;
        Log.i("RuntimeErrorAlert", "in alert");
        AlertDialog create = new AlertDialog.Builder((Context) obj).create();
        create.setTitle(str2);
        create.setMessage(str);
        create.setButton(str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) obj).finish();
            }
        });
        if (str == null) {
            Log.e(cls.getName(), "No error message available");
        } else {
            Log.e(cls.getName(), str);
        }
        create.show();
    }
}
