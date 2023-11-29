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
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AnimationUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an audio picker, the device's audio gallery appears, and the user can choose an audio file.", version = 6)
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
public class AudioPicker extends Picker implements ActivityResultListener {
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private String vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = "";

    public AudioPicker(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        Log.d("AudioPicker", "AudioPicker Created");
    }

    public void click() {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        AudioPicker.this.BeforePicking();
                        if (AudioPicker.this.requestCode == 0) {
                            AudioPicker audioPicker = AudioPicker.this;
                            audioPicker.requestCode = audioPicker.container.$form().registerForActivityResult(AudioPicker.this);
                        }
                        AudioPicker.this.container.$context().startActivityForResult(AudioPicker.this.getIntent(), AudioPicker.this.requestCode);
                        AnimationUtil.ApplyOpenScreenAnimation(AudioPicker.this.container.$context(), AudioPicker.this.container.$form().OpenScreenAnimation());
                    } catch (PermissionException e) {
                        AudioPicker.this.form.dispatchPermissionDeniedEvent((Component) AudioPicker.this, "Open", e);
                    } catch (Exception e2) {
                        Log.e("AudioPicker", String.valueOf(e2));
                    }
                } else {
                    AudioPicker.this.form.dispatchPermissionDeniedEvent((Component) AudioPicker.this, "Open", str);
                }
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the audio file that was selected.")
    public String Selection() {
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return new Intent("android.intent.action.PICK", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode && i2 == -1) {
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(intent.getData());
            this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            AfterPicking(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Uri uri) {
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
