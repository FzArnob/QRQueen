package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.loader.content.CursorLoader;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AnimationUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an video picker, the device's video gallery appears, and the user can choose an video.", version = 7)
public class VideoPicker extends Picker implements ActivityResultListener {
    private String O3i6lk19aLRv6A8OBJmvOyhyWMIS6dBGKDBTsHgoLny1aY87MqdZUecrDONaV4et = "";
    private Context context;
    /* access modifiers changed from: private */
    public Form form;

    public VideoPicker(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
    }

    public void click() {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        VideoPicker.this.BeforePicking();
                        if (VideoPicker.this.requestCode == 0) {
                            VideoPicker videoPicker = VideoPicker.this;
                            videoPicker.requestCode = videoPicker.container.$form().registerForActivityResult(VideoPicker.this);
                        }
                        VideoPicker.this.container.$context().startActivityForResult(VideoPicker.this.getIntent(), VideoPicker.this.requestCode);
                        AnimationUtil.ApplyOpenScreenAnimation(VideoPicker.this.container.$context(), VideoPicker.this.container.$form().OpenScreenAnimation());
                    } catch (PermissionException e) {
                        VideoPicker.this.form.dispatchPermissionDeniedEvent((Component) VideoPicker.this, "Open", e);
                    } catch (Exception e2) {
                        Log.e("VideoPicker", String.valueOf(e2));
                    }
                } else {
                    VideoPicker.this.form.dispatchPermissionDeniedEvent((Component) VideoPicker.this, "Open", str);
                }
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the video that was selected.")
    public String Selection() {
        return this.O3i6lk19aLRv6A8OBJmvOyhyWMIS6dBGKDBTsHgoLny1aY87MqdZUecrDONaV4et;
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return new Intent("android.intent.action.PICK", MediaStore.Video.Media.INTERNAL_CONTENT_URI);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode && i2 == -1) {
            String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(intent.getData());
            this.O3i6lk19aLRv6A8OBJmvOyhyWMIS6dBGKDBTsHgoLny1aY87MqdZUecrDONaV4et = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
            AfterPicking(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        }
    }

    private String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Uri uri) {
        try {
            Cursor loadInBackground = new CursorLoader(this.context, uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null).loadInBackground();
            int columnIndexOrThrow = loadInBackground != null ? loadInBackground.getColumnIndexOrThrow("_data") : 0;
            if (loadInBackground != null) {
                loadInBackground.moveToFirst();
            }
            return loadInBackground != null ? loadInBackground.getString(columnIndexOrThrow) : "ERROR";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
