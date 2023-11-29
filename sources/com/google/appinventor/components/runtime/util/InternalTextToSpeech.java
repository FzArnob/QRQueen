package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import java.util.Locale;

public class InternalTextToSpeech implements ITextToSpeech {
    private static final String LOG_TAG = "InternalTTS";
    /* access modifiers changed from: private */
    public final Activity activity;
    /* access modifiers changed from: private */
    public final ITextToSpeech.TextToSpeechCallback callback;
    /* access modifiers changed from: private */
    public volatile boolean isTtsInitialized;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int nextUtteranceId = 1;
    private TextToSpeech tts;
    private int ttsMaxRetries = 20;
    private int ttsRetryDelay = 500;

    public InternalTextToSpeech(Activity activity2, ITextToSpeech.TextToSpeechCallback textToSpeechCallback) {
        this.activity = activity2;
        this.callback = textToSpeechCallback;
        initializeTts();
    }

    private void initializeTts() {
        if (this.tts == null) {
            Log.d(LOG_TAG, "INTERNAL TTS is reinitializing");
            this.tts = new TextToSpeech(this.activity, new TextToSpeech.OnInitListener() {
                public final void onInit(int i) {
                    if (i == 0) {
                        boolean unused = InternalTextToSpeech.this.isTtsInitialized = true;
                    }
                }
            });
        }
    }

    public void speak(String str, Locale locale) {
        Log.d(LOG_TAG, "Internal TTS got speak");
        speak(str, locale, 0);
    }

    public boolean isInitialized() {
        return this.isTtsInitialized;
    }

    /* access modifiers changed from: private */
    public void speak(final String str, final Locale locale, final int i) {
        Log.d(LOG_TAG, "InternalTTS speak called, message = ".concat(String.valueOf(str)));
        if (i > this.ttsMaxRetries) {
            Log.d(LOG_TAG, "max number of speak retries exceeded: speak will fail");
            this.callback.onFailure();
        }
        if (this.isTtsInitialized) {
            Log.d(LOG_TAG, "TTS initialized after " + i + " retries.");
            this.tts.setLanguage(locale);
            this.tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                public final void onError(String str) {
                }

                public final void onStart(String str) {
                }

                public final void onDone(String str) {
                    InternalTextToSpeech.this.activity.runOnUiThread(new Runnable() {
                        public final void run() {
                            InternalTextToSpeech.this.callback.onSuccess();
                        }
                    });
                }
            });
            TextToSpeech textToSpeech = this.tts;
            int i2 = this.nextUtteranceId;
            this.nextUtteranceId = i2 + 1;
            if (textToSpeech.speak(str, 0, (Bundle) null, Integer.toString(i2)) == -1) {
                Log.d(LOG_TAG, "speak called and tts.speak result was an error");
                this.callback.onFailure();
                return;
            }
            return;
        }
        Log.d(LOG_TAG, "speak called when TTS not initialized");
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                Log.d(InternalTextToSpeech.LOG_TAG, "delaying call to speak.  Retries is: " + i + " Message is: " + str);
                InternalTextToSpeech.this.speak(str, locale, i + 1);
            }
        }, (long) this.ttsRetryDelay);
    }

    public void onStop() {
        Log.d(LOG_TAG, "Internal TTS got onStop");
    }

    public void onDestroy() {
        Log.d(LOG_TAG, "Internal TTS got onDestroy");
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.isTtsInitialized = false;
            this.tts = null;
        }
    }

    public void onResume() {
        Log.d(LOG_TAG, "Internal TTS got onResume");
        initializeTts();
    }

    public void setPitch(float f) {
        this.tts.setPitch(f);
    }

    public void setSpeechRate(float f) {
        this.tts.setSpeechRate(f);
    }

    public int isLanguageAvailable(Locale locale) {
        return this.tts.isLanguageAvailable(locale);
    }
}
