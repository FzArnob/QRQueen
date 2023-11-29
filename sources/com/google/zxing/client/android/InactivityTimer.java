package com.google.zxing.client.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;

final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    /* access modifiers changed from: private */
    public static final String TAG = "InactivityTimer";
    /* access modifiers changed from: private */
    public final Activity activity;
    private InactivityAsyncTask inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver();
    private final AsyncTaskExecInterface taskExec = ((AsyncTaskExecInterface) new AsyncTaskExecManager().build());

    InactivityTimer(Activity activity2) {
        this.activity = activity2;
        onActivity();
    }

    /* access modifiers changed from: package-private */
    public synchronized void onActivity() {
        cancel();
        InactivityAsyncTask inactivityAsyncTask = new InactivityAsyncTask();
        this.inactivityTask = inactivityAsyncTask;
        this.taskExec.execute(inactivityAsyncTask, new Object[0]);
    }

    public void onPause() {
        cancel();
        this.activity.unregisterReceiver(this.powerStatusReceiver);
    }

    public void onResume() {
        this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        onActivity();
    }

    /* access modifiers changed from: private */
    public synchronized void cancel() {
        InactivityAsyncTask inactivityAsyncTask = this.inactivityTask;
        if (inactivityAsyncTask != null) {
            inactivityAsyncTask.cancel(true);
            this.inactivityTask = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        cancel();
    }

    private final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("plugged", -1) <= 0) {
                    InactivityTimer.this.onActivity();
                } else {
                    InactivityTimer.this.cancel();
                }
            }
        }
    }

    private final class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private InactivityAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(InactivityTimer.INACTIVITY_DELAY_MS);
                Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
                InactivityTimer.this.activity.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }
}
