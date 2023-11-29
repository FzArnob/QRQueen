package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class CameraConfigurationManager {
    private static final int AREA_PER_1000 = 400;
    private static final double MAX_ASPECT_DISTORTION = 0.15d;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final String TAG = "CameraConfiguration";
    private Point cameraResolution;
    private final Context context;
    private Point screenResolution;

    CameraConfigurationManager(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: package-private */
    public void initFromCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        this.screenResolution = point;
        HoneycombMR2Util.getSize(defaultDisplay, point);
        Log.i(TAG, "Screen resolution: " + this.screenResolution);
        this.cameraResolution = findBestPreviewSizeValue(parameters, this.screenResolution);
        Log.i(TAG, "Camera resolution: " + this.cameraResolution);
    }

    /* access modifiers changed from: package-private */
    public void setDesiredCameraParameters(Camera camera, boolean z) {
        String str;
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        initializeTorch(parameters, z);
        if (z) {
            str = findSettableValue(parameters.getSupportedFocusModes(), "auto");
        } else {
            str = findSettableValue(parameters.getSupportedFocusModes(), "continuous-picture", "continuous-video", "auto");
        }
        if (!z && str == null) {
            str = findSettableValue(parameters.getSupportedFocusModes(), "macro", "edof");
        }
        if (str != null) {
            parameters.setFocusMode(str);
        }
        Log.i(TAG, "Setting Camera Preview Size to: " + this.cameraResolution);
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);
    }

    /* access modifiers changed from: package-private */
    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    /* access modifiers changed from: package-private */
    public Point getScreenResolution() {
        return this.screenResolution;
    }

    /* access modifiers changed from: package-private */
    public void setTorch(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        doSetTorch(parameters, z, false);
        camera.setParameters(parameters);
    }

    private void initializeTorch(Camera.Parameters parameters, boolean z) {
        doSetTorch(parameters, false, z);
    }

    private void doSetTorch(Camera.Parameters parameters, boolean z, boolean z2) {
        String str;
        if (z) {
            str = findSettableValue(parameters.getSupportedFlashModes(), "torch", "on");
        } else {
            str = findSettableValue(parameters.getSupportedFlashModes(), "off");
        }
        if (str != null) {
            parameters.setFlashMode(str);
        }
    }

    private Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        Point point2 = point;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        ArrayList<Camera.Size> arrayList = new ArrayList<>(supportedPreviewSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() {
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size.height * size.width;
                int i2 = size2.height * size2.width;
                if (i2 < i) {
                    return -1;
                }
                return i2 > i ? 1 : 0;
            }
        });
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i(TAG, "Supported preview sizes: " + sb);
        }
        double d = ((double) point2.x) / ((double) point2.y);
        Iterator it = arrayList.iterator();
        while (true) {
            boolean z = false;
            if (it.hasNext()) {
                Camera.Size size2 = (Camera.Size) it.next();
                int i = size2.width;
                int i2 = size2.height;
                if (i * i2 < MIN_PREVIEW_PIXELS) {
                    it.remove();
                } else {
                    if (i < i2) {
                        z = true;
                    }
                    int i3 = z ? i2 : i;
                    int i4 = z ? i : i2;
                    if (Math.abs((((double) i3) / ((double) i4)) - d) > MAX_ASPECT_DISTORTION) {
                        it.remove();
                    } else if (i3 == point2.x && i4 == point2.y) {
                        Point point3 = new Point(i, i2);
                        Log.i(TAG, "Found preview size exactly matching screen size: " + point3);
                        return point3;
                    }
                }
            } else if (!arrayList.isEmpty()) {
                Camera.Size size3 = (Camera.Size) arrayList.get(0);
                Point point4 = new Point(size3.width, size3.height);
                Log.i(TAG, "Using largest suitable preview size: " + point4);
                return point4;
            } else {
                Camera.Size previewSize2 = parameters.getPreviewSize();
                if (previewSize2 != null) {
                    Point point5 = new Point(previewSize2.width, previewSize2.height);
                    Log.i(TAG, "No suitable preview sizes, using default: " + point5);
                    return point5;
                }
                throw new IllegalStateException("Parameters contained no preview size!");
            }
        }
    }

    private static String findSettableValue(Collection<String> collection, String... strArr) {
        String str;
        Log.i(TAG, "Supported values: " + collection);
        if (collection != null) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                str = strArr[i];
                if (collection.contains(str)) {
                    break;
                }
                i++;
            }
        }
        str = null;
        Log.i(TAG, "Settable value: " + str);
        return str;
    }
}
