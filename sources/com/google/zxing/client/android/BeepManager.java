package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

final class BeepManager {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = "BeepManager";
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;

    private static MediaPlayer buildMediaPlayer(Context context) {
        return null;
    }

    BeepManager(Activity activity2) {
        this.activity = activity2;
    }

    /* access modifiers changed from: package-private */
    public void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer2;
        if (this.playBeep && (mediaPlayer2 = this.mediaPlayer) != null) {
            mediaPlayer2.start();
        }
        if (this.vibrate) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private static boolean shouldBeep(SharedPreferences sharedPreferences, Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return false;
    }
}
