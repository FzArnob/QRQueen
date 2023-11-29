package com.google.zxing.client.android.camera.exposure;

import android.hardware.Camera;
import android.util.Log;

public final class FroyoExposureInterface implements ExposureInterface {
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final String TAG = "FroyoExposureInterface";

    public void setExposure(Camera.Parameters parameters, boolean z) {
        int i;
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        if (minExposureCompensation == 0 && maxExposureCompensation == 0) {
            Log.i(TAG, "Camera does not support exposure compensation");
            return;
        }
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (z) {
            i = Math.max((int) (0.0f / exposureCompensationStep), minExposureCompensation);
        } else {
            i = Math.min((int) (MAX_EXPOSURE_COMPENSATION / exposureCompensationStep), maxExposureCompensation);
        }
        String str = TAG;
        Log.i(str, "Setting exposure compensation to " + i + " / " + (exposureCompensationStep * ((float) i)));
        parameters.setExposureCompensation(i);
    }
}
