package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.QUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", helpUrl = "https://docs.kodular.io/components/layout/surface-view/", iconName = "images/surface.png", version = 2)
@UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.FLASHLIGHT"})
public final class Surface extends AndroidViewComponent implements Camera.FaceDetectionListener, Camera.PreviewCallback, SurfaceHolder.Callback, ComponentContainer, OnDestroyListener {
    private static final String LOG_TAG = "Surface";
    private final Activity activity;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler = new Handler();
    private int cameraID = 0;
    private boolean clickable = false;
    /* access modifiers changed from: private */
    public Context context;
    private boolean faceDetect = false;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private int horizontalAlignment = 1;
    private boolean isPause = false;
    private boolean isPlaying = false;
    private boolean isStop = false;
    private final ViewGroup mainLayout;
    private Camera myCamera;
    private PackageManager pm;
    private SurfaceHolder sHolder;
    private boolean saveAsFile = false;
    private SurfaceView surfaceView;
    private boolean useBackCamera = true;
    private boolean useFlashlight = false;
    private int verticalAlignment = 1;
    private final LinearLayout viewLayout;

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public Surface(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
        SurfaceView surfaceView2 = new SurfaceView(this.context);
        this.surfaceView = surfaceView2;
        SurfaceHolder holder = surfaceView2.getHolder();
        this.sHolder = holder;
        holder.addCallback(this);
        this.pm = this.context.getPackageManager();
        LinearLayout linearLayout = new LinearLayout(this.context, 1, 100, 100);
        this.viewLayout = linearLayout;
        linearLayout.setBaselineAligned(false);
        AlignmentUtil alignmentUtil = new AlignmentUtil(linearLayout);
        this.alignmentSetter = alignmentUtil;
        alignmentUtil.setHorizontalAlignment(this.horizontalAlignment);
        this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
        FrameLayout frameLayout = new FrameLayout(this.context);
        this.mainLayout = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        frameLayout.addView(this.surfaceView);
        frameLayout.addView(linearLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
        componentContainer.$add(this);
        componentContainer.$form().registerForOnDestroy(this);
        Clickable(false);
        FaceDetection(false);
        SavePreviewAsFile(false);
        UseBackCamera(true);
        Log.d(LOG_TAG, "Surface Created");
    }

    public final View getView() {
        return this.mainLayout;
    }

    public final Activity $context() {
        return this.activity;
    }

    public final Form $form() {
        return this.container.$form();
    }

    public final void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public final void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        setChildWidth(androidViewComponent, i, 0);
    }

    public final void setChildWidth(final AndroidViewComponent androidViewComponent, final int i, final int i2) {
        int Width = this.container.$form().Width();
        if (Width == 0 && i2 < 2) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    Surface.this.setChildWidth(androidViewComponent, i, i2 + 1);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Width * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastWidth(i);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent.getView(), i);
    }

    public final void setChildHeight(final AndroidViewComponent androidViewComponent, final int i) {
        int Height = this.container.$form().Height();
        if (Height == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    Surface.this.setChildHeight(androidViewComponent, i);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Height * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastHeight(i);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent.getView(), i);
    }

    @SimpleProperty
    public final void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleProperty
    public final void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.isPlaying) {
            StartPreview();
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera camera = this.myCamera;
        if (camera != null) {
            try {
                camera.setPreviewDisplay(this.sHolder);
            } catch (Throwable th) {
                Log.e(LOG_TAG, String.valueOf(th));
            }
        }
    }

    public final void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.saveAsFile) {
            try {
                Camera camera2 = this.myCamera;
                if (camera2 == null || camera2.getParameters() == null) {
                    GotPreview("");
                    return;
                }
                Camera.Parameters parameters = this.myCamera.getParameters();
                Camera.Size previewSize = parameters.getPreviewSize();
                YuvImage yuvImage = new YuvImage(bArr, parameters.getPreviewFormat(), previewSize.width, previewSize.height, (int[]) null);
                File file = new File(QUtil.getExternalStoragePath(this.context) + "/preMak.jpg");
                yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 100, new FileOutputStream(file));
                GotPreview(file.getAbsolutePath());
            } catch (PermissionException e) {
                this.container.$form().dispatchPermissionDeniedEvent((Component) this, LOG_TAG, e);
            } catch (FileNotFoundException e2) {
                Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
    }

    public final void onFaceDetection(Camera.Face[] faceArr, Camera camera) {
        FaceDetected(String.valueOf(faceArr.length));
        Log.i(LOG_TAG, "Faces Detected = " + String.valueOf(faceArr.length));
    }

    @SimpleFunction(description = "Take a picture from the camera preview.")
    public final void TakePicture() {
        if (!this.havePermission) {
            $form().runOnUiThread(new Runnable() {
                public final void run() {
                    Surface.this.$form().askPermission("android.permission.CAMERA", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = this.havePermission = true;
                                this.loadTakePicture();
                                return;
                            }
                            Surface.this.$form().dispatchPermissionDeniedEvent((Component) this, "TakePicture", "android.permission.CAMERA");
                        }
                    });
                }
            });
        } else {
            loadTakePicture();
        }
    }

    /* access modifiers changed from: private */
    public void loadTakePicture() {
        if (this.myCamera != null) {
            try {
                this.myCamera.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, new Camera.PictureCallback() {
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[SYNTHETIC, Splitter:B:22:0x0064] */
                    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079 A[SYNTHETIC, Splitter:B:29:0x0079] */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082 A[SYNTHETIC, Splitter:B:32:0x0082] */
                    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
                    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
                    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x005b=Splitter:B:19:0x005b, B:26:0x006c=Splitter:B:26:0x006c} */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final void onPictureTaken(byte[] r5, android.hardware.Camera r6) {
                        /*
                            r4 = this;
                            java.lang.String r6 = "Surface"
                            r0 = 0
                            java.io.File r1 = new java.io.File     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            r2.<init>()     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            com.google.appinventor.components.runtime.Surface r3 = com.google.appinventor.components.runtime.Surface.this     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            android.content.Context r3 = r3.context     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            java.lang.String r3 = com.google.appinventor.components.runtime.util.QUtil.getExternalStoragePath(r3)     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            r2.append(r3)     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            java.lang.String r3 = "/takenPicture.jpg"
                            r2.append(r3)     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            java.lang.String r2 = r2.toString()     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            r1.<init>(r2)     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            r2.<init>(r1)     // Catch:{ PermissionException -> 0x006b, Exception -> 0x005a }
                            r2.write(r5)     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            r2.flush()     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            r2.close()     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            com.google.appinventor.components.runtime.Surface r5 = com.google.appinventor.components.runtime.Surface.this     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            java.lang.String r0 = r1.getAbsolutePath()     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            r5.PictureCreated(r0)     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            com.google.appinventor.components.runtime.Surface r5 = com.google.appinventor.components.runtime.Surface.this     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            r5.StartPreview()     // Catch:{ PermissionException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
                            r2.flush()     // Catch:{ Exception -> 0x0046 }
                            r2.close()     // Catch:{ Exception -> 0x0046 }
                            return
                        L_0x0046:
                            r5 = move-exception
                            java.lang.String r5 = java.lang.String.valueOf(r5)
                            android.util.Log.e(r6, r5)
                            return
                        L_0x004f:
                            r5 = move-exception
                            r0 = r2
                            goto L_0x0080
                        L_0x0052:
                            r5 = move-exception
                            r0 = r2
                            goto L_0x005b
                        L_0x0055:
                            r5 = move-exception
                            r0 = r2
                            goto L_0x006c
                        L_0x0058:
                            r5 = move-exception
                            goto L_0x0080
                        L_0x005a:
                            r5 = move-exception
                        L_0x005b:
                            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0058 }
                            android.util.Log.e(r6, r5)     // Catch:{ all -> 0x0058 }
                            if (r0 == 0) goto L_0x007f
                            r0.flush()     // Catch:{ Exception -> 0x0046 }
                            r0.close()     // Catch:{ Exception -> 0x0046 }
                            return
                        L_0x006b:
                            r5 = move-exception
                        L_0x006c:
                            com.google.appinventor.components.runtime.Surface r1 = com.google.appinventor.components.runtime.Surface.this     // Catch:{ all -> 0x0058 }
                            com.google.appinventor.components.runtime.Form r1 = r1.$form()     // Catch:{ all -> 0x0058 }
                            com.google.appinventor.components.runtime.Surface r2 = com.google.appinventor.components.runtime.Surface.this     // Catch:{ all -> 0x0058 }
                            r1.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r2, (java.lang.String) r6, (com.google.appinventor.components.runtime.errors.PermissionException) r5)     // Catch:{ all -> 0x0058 }
                            if (r0 == 0) goto L_0x007f
                            r0.flush()     // Catch:{ Exception -> 0x0046 }
                            r0.close()     // Catch:{ Exception -> 0x0046 }
                        L_0x007f:
                            return
                        L_0x0080:
                            if (r0 == 0) goto L_0x0091
                            r0.flush()     // Catch:{ Exception -> 0x0089 }
                            r0.close()     // Catch:{ Exception -> 0x0089 }
                            goto L_0x0091
                        L_0x0089:
                            r0 = move-exception
                            java.lang.String r0 = java.lang.String.valueOf(r0)
                            android.util.Log.e(r6, r0)
                        L_0x0091:
                            throw r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Surface.AnonymousClass4.onPictureTaken(byte[], android.hardware.Camera):void");
                    }
                });
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will open the back camera for the camera preview, else you open the front camera. The block detect automatic if there is a device camera available or not.")
    public final void UseBackCamera(boolean z) {
        if (z) {
            if (this.pm.hasSystemFeature("android.hardware.camera")) {
                this.cameraID = 0;
                this.useBackCamera = true;
            }
        } else if (this.pm.hasSystemFeature("android.hardware.camera.front")) {
            this.cameraID = 1;
            this.useBackCamera = false;
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you can detect how many faces are in the front of the camera.")
    public final void FaceDetection(boolean z) {
        this.faceDetect = z;
    }

    @SimpleProperty(description = "Turn on or off the device flashlight. The function knows automatic if there is a flash/ torch available.")
    public final void Flashlight(boolean z) {
        Camera.Parameters parameters = this.myCamera.getParameters();
        if (z) {
            try {
                parameters.setFlashMode("torch");
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        } else {
            try {
                parameters.setFlashMode("off");
            } catch (Exception e2) {
                Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
        this.useFlashlight = z;
        this.myCamera.setParameters(parameters);
    }

    @SimpleFunction(description = "Start the camera preview.")
    public final void StartPreview() {
        if (!this.havePermission) {
            $form().runOnUiThread(new Runnable() {
                public final void run() {
                    Surface.this.$form().askPermission("android.permission.CAMERA", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = this.havePermission = true;
                                this.loadStartPreview();
                                return;
                            }
                            Surface.this.$form().dispatchPermissionDeniedEvent((Component) this, "StartPreview", "android.permission.CAMERA");
                        }
                    });
                }
            });
        } else {
            loadStartPreview();
        }
    }

    /* access modifiers changed from: private */
    public void loadStartPreview() {
        Camera.Parameters parameters;
        Camera camera = this.myCamera;
        if (camera != null) {
            try {
                Camera.Parameters parameters2 = camera.getParameters();
                parameters2.setFocusMode("continuous-picture");
                this.myCamera.setParameters(parameters2);
                this.myCamera.setDisplayOrientation(previewHelper());
                this.myCamera.startPreview();
                this.isPlaying = true;
                this.isPause = false;
                this.isStop = false;
            } catch (PermissionException e) {
                $form().dispatchPermissionDeniedEvent((Component) this, "StartPreview", e);
            } catch (Exception e2) {
                Log.e(LOG_TAG, String.valueOf(e2));
            }
        } else {
            Camera open = Camera.open(this.cameraID);
            this.myCamera = open;
            open.setDisplayOrientation(previewHelper());
            try {
                parameters = this.myCamera.getParameters();
                parameters.setFocusMode("continuous-picture");
            } catch (Exception e3) {
                Log.e(LOG_TAG, String.valueOf(e3));
            } catch (Throwable th) {
                Log.e(LOG_TAG, String.valueOf(th));
            }
            this.myCamera.setParameters(parameters);
            this.myCamera.setPreviewDisplay(this.sHolder);
            this.myCamera.setPreviewCallback(this);
            this.myCamera.startPreview();
            this.isPlaying = true;
            this.isPause = false;
            this.isStop = false;
            if (this.faceDetect) {
                this.myCamera.setFaceDetectionListener(this);
                this.myCamera.startFaceDetection();
            }
        }
    }

    @SimpleFunction(description = "Pause the camera preview.")
    public final void PausePreview() {
        Camera camera = this.myCamera;
        if (camera != null) {
            camera.stopPreview();
            this.isPlaying = false;
            this.isPause = true;
            this.isStop = false;
            PreviewPaused();
        }
    }

    @SimpleFunction(description = "Stop the camera preview.")
    public final void StopPreview() {
        Camera camera = this.myCamera;
        if (camera != null) {
            camera.stopFaceDetection();
            this.myCamera.setPreviewCallback((Camera.PreviewCallback) null);
            this.myCamera.stopPreview();
            this.myCamera.release();
            this.myCamera = null;
            this.isPlaying = false;
            this.isPause = false;
            this.isStop = true;
            PreviewStoped();
        }
    }

    private void DestroySurface() {
        Camera camera = this.myCamera;
        if (camera != null) {
            camera.stopFaceDetection();
            this.myCamera.setPreviewCallback((Camera.PreviewCallback) null);
            this.myCamera.stopPreview();
            this.myCamera.release();
            this.myCamera = null;
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Enable this block to enable the \"Got Preview\" event to get a image file from the camera preview.")
    public final void SavePreviewAsFile(boolean z) {
        this.saveAsFile = z;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the surface view component clickable or not clickable.")
    public final void Clickable(boolean z) {
        this.clickable = z;
        if (z) {
            this.surfaceView.setClickable(true);
            this.surfaceView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    Surface.this.Clicked();
                }
            });
            this.surfaceView.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    Surface.this.LongClicked();
                    return true;
                }
            });
            return;
        }
        this.surfaceView.setOnClickListener((View.OnClickListener) null);
        this.surfaceView.setOnLongClickListener((View.OnLongClickListener) null);
        this.surfaceView.setClickable(false);
    }

    @SimpleEvent(description = "Event to detect clicks on the camera preview.")
    public final void Clicked() {
        EventDispatcher.dispatchEvent(this, "Clicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect long clicks on the camera preview.")
    public final void LongClicked() {
        EventDispatcher.dispatchEvent(this, "LongClicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the user has taken a picture from the preview.")
    public final void PictureCreated(String str) {
        EventDispatcher.dispatchEvent(this, "PictureCreated", str);
    }

    @SimpleEvent(description = "Event to get the response from the camera preview as image file. The picture is by default in landscape mode. This event will only work if the option \"Save Preview As File\" is enabled/ true.")
    public final void GotPreview(String str) {
        EventDispatcher.dispatchEvent(this, "GotPreview", str);
    }

    @SimpleEvent(description = "Event to detect that the user paused the camera preview.")
    public final void PreviewPaused() {
        EventDispatcher.dispatchEvent(this, "PreviewPaused", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the user stopped the camera preview.")
    public final void PreviewStoped() {
        EventDispatcher.dispatchEvent(this, "PreviewStoped", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that there are faces in the front of the camera.")
    public final void FaceDetected(String str) {
        EventDispatcher.dispatchEvent(this, "FaceDetected", str);
    }

    @SimpleProperty(description = "Returns Face Detection property status.")
    public final boolean FaceDetection() {
        return this.faceDetect;
    }

    @SimpleProperty(description = "Returns Flashlight property status.")
    public final boolean Flashlight() {
        return this.useFlashlight;
    }

    @SimpleProperty(description = "Returns Use Back Camera property status.")
    public final boolean UseBackCamera() {
        return this.useBackCamera;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public final void AlignHorizontal(int i) {
        try {
            this.alignmentSetter.setHorizontalAlignment(i);
            this.horizontalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public final int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public final void AlignVertical(int i) {
        try {
            this.alignmentSetter.setVerticalAlignment(i);
            this.verticalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    public final void onDestroy() {
        try {
            DestroySurface();
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public final int previewHelper() {
        this.myCamera.getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        int i = 0;
        int i2 = defaultDisplay.getRotation() == 0 ? 90 : 0;
        if (defaultDisplay.getRotation() != 1) {
            i = i2;
        }
        if (defaultDisplay.getRotation() == 2) {
            i = 270;
        }
        if (defaultDisplay.getRotation() == 3) {
            return 180;
        }
        return i;
    }
}
