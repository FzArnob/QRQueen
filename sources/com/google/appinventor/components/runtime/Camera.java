package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.NougatUtil;
import com.google.appinventor.components.runtime.util.ScopedFile;
import java.io.File;
import java.net.URI;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A component to take a picture using the device's camera. After the picture is taken, the name of the file on the phone containing the picture is available as an argument to the AfterPicture event. The file name can be used, for example, to set the Picture property of an Image component.", iconName = "images/camera.png", nonVisible = true, version = 4)
@UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.FLASHLIGHT"})
public class Camera extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private boolean ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
    private String BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = "";
    private final ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private android.hardware.Camera f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Uri f57hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int requestCode;
    private boolean tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    private boolean wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;

    public Camera(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = false;
        Activity $context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context;
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = $context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        UseFront(false);
        Log.d("Camera", "Camera Created");
    }

    public void Initialize() {
        this.havePermission = !this.form.isDeniedPermission("android.permission.CAMERA");
        if (FileUtil.needsWritePermission(this.form.DefaultFileScope())) {
            this.havePermission &= !this.form.isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UseFront() {
        return this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;
    }

    @Deprecated
    @SimpleProperty(description = "Specifies whether the front-facing camera should be used (when available). If the device does not have a front-facing camera, this option will be ignored and the camera will open normally.")
    public void UseFront(boolean z) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = z;
    }

    @SimpleFunction(description = "Take a picture with the camera of your device.")
    public void TakePicture() {
        Uri uri;
        String[] strArr;
        ScopedFile scopedPictureFile = FileUtil.getScopedPictureFile(this.form, "png");
        if (!this.havePermission) {
            if (FileUtil.needsWritePermission(scopedPictureFile)) {
                strArr = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
            } else {
                strArr = new String[]{"android.permission.CAMERA"};
            }
            this.form.askPermission(new BulkPermissionRequest(this, "TakePicture", strArr) {
                public final void onGranted() {
                    boolean unused = Camera.this.havePermission = true;
                    Camera.this.TakePicture();
                }
            });
            return;
        }
        String externalStorageState = Environment.getExternalStorageState();
        if (!FileUtil.needsExternalStorage(this.form, scopedPictureFile) || "mounted".equals(externalStorageState)) {
            File file = new File(URI.create(FileUtil.resolveFileName(this.form, scopedPictureFile.getFileName(), scopedPictureFile.getScope())));
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            this.f57hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Uri.fromFile(file);
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", this.f57hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPath());
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("title", this.f57hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLastPathSegment());
            if (this.requestCode == 0) {
                this.requestCode = this.form.registerForActivityResult(this);
            }
            if (Build.VERSION.SDK_INT < 24) {
                uri = this.container.$context().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
            } else {
                uri = NougatUtil.getPackageUri(this.form, file);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", uri);
            if (this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0) {
                intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
            }
            this.container.$context().startActivityForResult(intent, this.requestCode);
        } else if ("mounted_ro".equals(externalStorageState)) {
            this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_READONLY, new Object[0]);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        Log.i("Camera", "Returning result. Request code = " + i + ", result code = " + i2);
        if (i != this.requestCode || i2 != -1) {
            return;
        }
        if (!this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS.isEmpty()) {
            File file = new File(this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS);
            if (file.exists()) {
                Uri packageUri = NougatUtil.getPackageUri(this.form, file);
                if (packageUri != null) {
                    Intent intent2 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent2.setData(packageUri);
                    this.container.$context().getApplicationContext().sendBroadcast(intent2);
                }
                Log.i("Camera", "Calling Camera.AfterPicture with image path " + this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS);
                AfterPicture(this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS);
            } else {
                Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
                this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, new Object[0]);
            }
            this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = "";
            return;
        }
        Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
        this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, new Object[0]);
    }

    @SimpleEvent(description = "Returns the taken picture.")
    public void AfterPicture(String str) {
        EventDispatcher.dispatchEvent(this, "AfterPicture", str);
    }

    @SimpleFunction(description = "Toggle the flash of your device to on or off.")
    public void ToggleLight() {
        if (!this.havePermission) {
            this.form.askPermission("android.permission.CAMERA", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        boolean unused = Camera.this.havePermission = true;
                        Camera.this.ToggleLight();
                        return;
                    }
                    Camera.this.form.dispatchPermissionDeniedEvent((Component) Camera.this, "ToggleLight", "android.permission.CAMERA");
                }
            });
        } else if (this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud) {
            android.hardware.Camera camera = this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (camera != null) {
                camera.stopPreview();
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
            this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = false;
        } else {
            try {
                android.hardware.Camera open = android.hardware.Camera.open();
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = open;
                Camera.Parameters parameters = open.getParameters();
                parameters.setFlashMode("torch");
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setParameters(parameters);
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPreviewTexture(new SurfaceTexture(0));
                this.f56hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startPreview();
                this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = true;
            } catch (PermissionException e) {
                Log.e("Camera", String.valueOf(e));
                Form form = this.form;
                form.dispatchPermissionDeniedEvent((Component) this, "ToggleLight", e.getMessage());
            } catch (Exception e2) {
                Log.e("Camera", String.valueOf(e2));
            }
        }
    }

    @SimpleFunction(description = "Returns true if your device has a flash.")
    public boolean HasFlash() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }
}
