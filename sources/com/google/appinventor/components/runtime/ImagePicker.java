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
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an image picker, the device's image gallery appears, and the user can choose an image.", version = 11)
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
public class ImagePicker extends Picker implements ActivityResultListener {
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private String poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = "";

    public ImagePicker(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        Log.d("ImagePicker", "ImagePicker Created");
    }

    public void click() {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        ImagePicker.this.BeforePicking();
                        if (ImagePicker.this.requestCode == 0) {
                            ImagePicker imagePicker = ImagePicker.this;
                            imagePicker.requestCode = imagePicker.container.$form().registerForActivityResult(ImagePicker.this);
                        }
                        ImagePicker.this.container.$context().startActivityForResult(ImagePicker.this.getIntent(), ImagePicker.this.requestCode);
                        AnimationUtil.ApplyOpenScreenAnimation(ImagePicker.this.container.$context(), ImagePicker.this.container.$form().OpenScreenAnimation());
                    } catch (PermissionException e) {
                        ImagePicker.this.form.dispatchPermissionDeniedEvent((Component) ImagePicker.this, "Open", e);
                    } catch (Exception e2) {
                        Log.e("ImagePicker", String.valueOf(e2));
                    }
                } else {
                    ImagePicker.this.form.dispatchPermissionDeniedEvent((Component) ImagePicker.this, "Open", str);
                }
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the image that was selected.")
    public String Selection() {
        return this.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg;
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode && i2 == -1) {
            String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(intent.getData());
            this.poblgm1P6mADk8QKAia8LTNTlp3hP9LK4vL2LyACRyn6OaTobhLhKjphCbjAETmg = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
            AfterPicking(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        }
    }

    private String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Uri uri) {
        try {
            Cursor loadInBackground = new CursorLoader(this.context, uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null).loadInBackground();
            if (loadInBackground == null) {
                return "ERROR";
            }
            int columnIndexOrThrow = loadInBackground.getColumnIndexOrThrow("_data");
            loadInBackground.moveToFirst();
            String string = loadInBackground.getString(columnIndexOrThrow);
            loadInBackground.close();
            return string;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
