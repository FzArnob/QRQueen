package com.google.zxing.client.android.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import java.util.ArrayList;
import java.util.Collection;

final class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = "AutoFocusManager";
    /* access modifiers changed from: private */
    public boolean active;
    private final Camera camera;
    private AutoFocusTask outstandingTask;
    private final AsyncTaskExecInterface taskExec = ((AsyncTaskExecInterface) new AsyncTaskExecManager().build());
    private final boolean useAutoFocus;

    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    AutoFocusManager(Context context, Camera camera2) {
        this.camera = camera2;
        PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera2.getParameters().getFocusMode();
        boolean contains = FOCUS_MODES_CALLING_AF.contains(focusMode);
        this.useAutoFocus = contains;
        String str = TAG;
        Log.i(str, "Current focus mode '" + focusMode + "'; use auto focus? " + contains);
        start();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera2) {
        if (this.active) {
            AutoFocusTask autoFocusTask = new AutoFocusTask();
            this.outstandingTask = autoFocusTask;
            this.taskExec.execute(autoFocusTask, new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void start() {
        if (this.useAutoFocus) {
            this.active = true;
            try {
                this.camera.autoFocus(this);
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while focusing", e);
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public synchronized void stop() {
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
        AutoFocusTask autoFocusTask = this.outstandingTask;
        if (autoFocusTask != null) {
            autoFocusTask.cancel(true);
            this.outstandingTask = null;
        }
        this.active = false;
    }

    private final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private AutoFocusTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(AutoFocusManager.AUTO_FOCUS_INTERVAL_MS);
            } catch (InterruptedException unused) {
            }
            synchronized (AutoFocusManager.this) {
                if (AutoFocusManager.this.active) {
                    AutoFocusManager.this.start();
                }
            }
            return null;
        }
    }
}
