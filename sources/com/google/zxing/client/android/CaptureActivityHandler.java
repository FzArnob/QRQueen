package com.google.zxing.client.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.Collection;

public final class CaptureActivityHandler extends Handler {
    private static final String TAG = "CaptureActivityHandler";
    private final AppInvCaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    CaptureActivityHandler(AppInvCaptureActivity appInvCaptureActivity, Collection<BarcodeFormat> collection, String str, CameraManager cameraManager2) {
        this.activity = appInvCaptureActivity;
        DecodeThread decodeThread2 = new DecodeThread(appInvCaptureActivity, collection, str, new ViewfinderResultPointCallback(appInvCaptureActivity.getViewfinderView()));
        this.decodeThread = decodeThread2;
        decodeThread2.start();
        this.cameraManager = cameraManager2;
        cameraManager2.startPreview();
        restartPreviewAndDecode();
    }

    public void handleMessage(Message message) {
        Bitmap bitmap;
        int i = message.what;
        if (i == 2) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), 1);
        } else if (i == 3) {
            Log.d(TAG, "Got decode succeeded message");
            this.state = State.SUCCESS;
            Bundle data = message.getData();
            if (data == null) {
                bitmap = null;
            } else {
                bitmap = (Bitmap) data.getParcelable(DecodeThread.BARCODE_BITMAP);
            }
            this.activity.handleDecode((Result) message.obj, bitmap);
        } else if (i == 5) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
        } else if (i == 6) {
            Log.d(TAG, "Got return scan result message");
            this.activity.setResult(-1, (Intent) message.obj);
            this.activity.finish();
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), 4).sendToTarget();
        try {
            this.decodeThread.join(500);
        } catch (InterruptedException unused) {
        }
        removeMessages(3);
        removeMessages(2);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), 1);
            this.activity.drawViewfinder();
        }
    }
}
