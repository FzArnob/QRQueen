package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import com.google.zxing.PlanarYUVLuminanceSource;

public final class CameraManager {
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    private static final String TAG = "CameraManager";
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;

    public CameraManager(Context context2) {
        this.context = context2;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context2);
        this.configManager = cameraConfigurationManager;
        this.previewCallback = new PreviewCallback(cameraConfigurationManager);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0050 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x007d */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void openDriver(android.view.SurfaceHolder r6) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            android.hardware.Camera r0 = r5.camera     // Catch:{ all -> 0x0086 }
            if (r0 != 0) goto L_0x001f
            com.google.zxing.client.android.camera.open.OpenCameraManager r0 = new com.google.zxing.client.android.camera.open.OpenCameraManager     // Catch:{ all -> 0x0086 }
            r0.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.Object r0 = r0.build()     // Catch:{ all -> 0x0086 }
            com.google.zxing.client.android.camera.open.OpenCameraInterface r0 = (com.google.zxing.client.android.camera.open.OpenCameraInterface) r0     // Catch:{ all -> 0x0086 }
            android.hardware.Camera r0 = r0.open()     // Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x0019
            r5.camera = r0     // Catch:{ all -> 0x0086 }
            goto L_0x001f
        L_0x0019:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x0086 }
            r6.<init>()     // Catch:{ all -> 0x0086 }
            throw r6     // Catch:{ all -> 0x0086 }
        L_0x001f:
            r0.setPreviewDisplay(r6)     // Catch:{ all -> 0x0086 }
            boolean r6 = r5.initialized     // Catch:{ all -> 0x0086 }
            r1 = 1
            r2 = 0
            if (r6 != 0) goto L_0x003e
            r5.initialized = r1     // Catch:{ all -> 0x0086 }
            com.google.zxing.client.android.camera.CameraConfigurationManager r6 = r5.configManager     // Catch:{ all -> 0x0086 }
            r6.initFromCameraParameters(r0)     // Catch:{ all -> 0x0086 }
            int r6 = r5.requestedFramingRectWidth     // Catch:{ all -> 0x0086 }
            if (r6 <= 0) goto L_0x003e
            int r3 = r5.requestedFramingRectHeight     // Catch:{ all -> 0x0086 }
            if (r3 <= 0) goto L_0x003e
            r5.setManualFramingRect(r6, r3)     // Catch:{ all -> 0x0086 }
            r5.requestedFramingRectWidth = r2     // Catch:{ all -> 0x0086 }
            r5.requestedFramingRectHeight = r2     // Catch:{ all -> 0x0086 }
        L_0x003e:
            android.hardware.Camera$Parameters r6 = r0.getParameters()     // Catch:{ all -> 0x0086 }
            if (r6 != 0) goto L_0x0046
            r6 = 0
            goto L_0x004a
        L_0x0046:
            java.lang.String r6 = r6.flatten()     // Catch:{ all -> 0x0086 }
        L_0x004a:
            com.google.zxing.client.android.camera.CameraConfigurationManager r3 = r5.configManager     // Catch:{ RuntimeException -> 0x0050 }
            r3.setDesiredCameraParameters(r0, r2)     // Catch:{ RuntimeException -> 0x0050 }
            goto L_0x0084
        L_0x0050:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "Camera rejected parameters. Setting only minimal safe-mode parameters"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r3.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "Resetting to saved camera params: "
            r3.append(r4)     // Catch:{ all -> 0x0086 }
            r3.append(r6)     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0086 }
            android.util.Log.i(r2, r3)     // Catch:{ all -> 0x0086 }
            if (r6 == 0) goto L_0x0084
            android.hardware.Camera$Parameters r2 = r0.getParameters()     // Catch:{ all -> 0x0086 }
            r2.unflatten(r6)     // Catch:{ all -> 0x0086 }
            r0.setParameters(r2)     // Catch:{ RuntimeException -> 0x007d }
            com.google.zxing.client.android.camera.CameraConfigurationManager r6 = r5.configManager     // Catch:{ RuntimeException -> 0x007d }
            r6.setDesiredCameraParameters(r0, r1)     // Catch:{ RuntimeException -> 0x007d }
            goto L_0x0084
        L_0x007d:
            java.lang.String r6 = TAG     // Catch:{ all -> 0x0086 }
            java.lang.String r0 = "Camera rejected even safe-mode parameters! No configuration"
            android.util.Log.w(r6, r0)     // Catch:{ all -> 0x0086 }
        L_0x0084:
            monitor-exit(r5)
            return
        L_0x0086:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.camera.CameraManager.openDriver(android.view.SurfaceHolder):void");
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            camera2.release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public synchronized void startPreview() {
        Camera camera2 = this.camera;
        if (camera2 != null && !this.previewing) {
            camera2.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
        }
    }

    public synchronized void stopPreview() {
        AutoFocusManager autoFocusManager2 = this.autoFocusManager;
        if (autoFocusManager2 != null) {
            autoFocusManager2.stop();
            this.autoFocusManager = null;
        }
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            camera2.stopPreview();
            this.previewCallback.setHandler((Handler) null, 0);
            this.previewing = false;
        }
    }

    public synchronized void setTorch(boolean z) {
        if (this.camera != null) {
            AutoFocusManager autoFocusManager2 = this.autoFocusManager;
            if (autoFocusManager2 != null) {
                autoFocusManager2.stop();
            }
            this.configManager.setTorch(this.camera, z);
            AutoFocusManager autoFocusManager3 = this.autoFocusManager;
            if (autoFocusManager3 != null) {
                autoFocusManager3.start();
            }
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int i) {
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            this.previewCallback.setHandler(handler, i);
            camera2.setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized Rect getFramingRect() {
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            Point screenResolution = this.configManager.getScreenResolution();
            if (screenResolution == null) {
                return null;
            }
            int i = (screenResolution.x * 3) / 4;
            if (i < 240) {
                i = 240;
            }
            int i2 = (screenResolution.x - i) / 2;
            int i3 = (screenResolution.y - i) / 2;
            this.framingRect = new Rect(i2, i3, i2 + i, i + i3);
            String str = TAG;
            Log.d(str, "Calculated framing rect: " + this.framingRect);
        }
        return this.framingRect;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0054, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Rect getFramingRectInPreview() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.graphics.Rect r0 = r5.framingRectInPreview     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0055
            android.graphics.Rect r0 = r5.getFramingRect()     // Catch:{ all -> 0x0059 }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r5)
            return r1
        L_0x000e:
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x0059 }
            r2.<init>(r0)     // Catch:{ all -> 0x0059 }
            com.google.zxing.client.android.camera.CameraConfigurationManager r0 = r5.configManager     // Catch:{ all -> 0x0059 }
            android.graphics.Point r0 = r0.getCameraResolution()     // Catch:{ all -> 0x0059 }
            com.google.zxing.client.android.camera.CameraConfigurationManager r3 = r5.configManager     // Catch:{ all -> 0x0059 }
            android.graphics.Point r3 = r3.getScreenResolution()     // Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x0053
            if (r3 != 0) goto L_0x0024
            goto L_0x0053
        L_0x0024:
            int r1 = r2.left     // Catch:{ all -> 0x0059 }
            int r4 = r0.y     // Catch:{ all -> 0x0059 }
            int r1 = r1 * r4
            int r4 = r3.x     // Catch:{ all -> 0x0059 }
            int r1 = r1 / r4
            r2.left = r1     // Catch:{ all -> 0x0059 }
            int r1 = r2.right     // Catch:{ all -> 0x0059 }
            int r4 = r0.y     // Catch:{ all -> 0x0059 }
            int r1 = r1 * r4
            int r4 = r3.x     // Catch:{ all -> 0x0059 }
            int r1 = r1 / r4
            r2.right = r1     // Catch:{ all -> 0x0059 }
            int r1 = r2.top     // Catch:{ all -> 0x0059 }
            int r4 = r0.x     // Catch:{ all -> 0x0059 }
            int r1 = r1 * r4
            int r4 = r3.y     // Catch:{ all -> 0x0059 }
            int r1 = r1 / r4
            r2.top = r1     // Catch:{ all -> 0x0059 }
            int r1 = r2.bottom     // Catch:{ all -> 0x0059 }
            int r0 = r0.x     // Catch:{ all -> 0x0059 }
            int r1 = r1 * r0
            int r0 = r3.y     // Catch:{ all -> 0x0059 }
            int r1 = r1 / r0
            r2.bottom = r1     // Catch:{ all -> 0x0059 }
            r5.framingRectInPreview = r2     // Catch:{ all -> 0x0059 }
            goto L_0x0055
        L_0x0053:
            monitor-exit(r5)
            return r1
        L_0x0055:
            android.graphics.Rect r0 = r5.framingRectInPreview     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)
            return r0
        L_0x0059:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.camera.CameraManager.getFramingRectInPreview():android.graphics.Rect");
    }

    public synchronized void setManualFramingRect(int i, int i2) {
        if (this.initialized) {
            Point screenResolution = this.configManager.getScreenResolution();
            if (i > screenResolution.x) {
                i = screenResolution.x;
            }
            if (i2 > screenResolution.y) {
                i2 = screenResolution.y;
            }
            int i3 = (screenResolution.x - i) / 2;
            int i4 = (screenResolution.y - i2) / 2;
            this.framingRect = new Rect(i3, i4, i + i3, i2 + i4);
            String str = TAG;
            Log.d(str, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = i;
            this.requestedFramingRectHeight = i2;
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview2 = getFramingRectInPreview();
        if (framingRectInPreview2 == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview2.left, framingRectInPreview2.top, framingRectInPreview2.width(), framingRectInPreview2.height(), false);
    }
}
