package com.google.appinventor.components.runtime;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TiramisuUtil;
import java.util.HashMap;
import java.util.Map;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>A multimedia component that plays sound files and optionally vibrates for the number of milliseconds (thousandths of a second) specified in the Blocks Editor.  The name of the sound file to play can be specified either in the Designer or in the Blocks Editor.</p> <p>For supported sound file formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>This <code>Sound</code> component is best for short sound files, such as sound effects, while the <code>Player</code> component is more efficient for longer sounds, such as songs.</p><p>You might get an error if you attempt to play a sound immediately after setting the source.</p>", iconName = "images/soundEffect.png", nonVisible = true, version = 5)
@SimpleObject
@UsesPermissions({"android.permission.VIBRATE", "android.permission.INTERNET"})
public class Sound extends AndroidNonvisibleComponent implements Component, Deleteable, OnDestroyListener, OnResumeListener, OnStopListener {
    private static final int LOOP_MODE_NO_LOOP = 0;
    private static final int MAX_PLAY_DELAY_RETRIES = 10;
    private static final int MAX_STREAMS = 10;
    private static final float PLAYBACK_RATE_NORMAL = 1.0f;
    private static final int PLAY_DELAY_LENGTH = 50;
    private static final float VOLUME_FULL = 1.0f;
    /* access modifiers changed from: private */
    public int delayRetries;
    /* access modifiers changed from: private */
    public boolean loadComplete = true;
    private AudioManager mAudioManager;
    private int minimumInterval;
    private final Handler playWaitHandler = new Handler();
    private int soundId;
    private final Map<String, Integer> soundMap = new HashMap();
    private SoundPool soundPool = new SoundPool(10, 3, 0);
    /* access modifiers changed from: private */
    public String sourcePath = "";
    private int streamId;
    /* access modifiers changed from: private */
    public final Component thisComponent = this;
    private long timeLastPlayed;
    private final Vibrator vibe = ((Vibrator) this.form.getSystemService("vibrator"));
    private final boolean waitForLoadToComplete = true;

    static /* synthetic */ int access$410(Sound sound) {
        int i = sound.delayRetries;
        sound.delayRetries = i - 1;
        return i;
    }

    class a {
        private a() {
        }

        /* synthetic */ a(Sound sound, byte b) {
            this();
        }
    }

    public Sound(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnDestroy(this);
        this.form.setVolumeControlStream(3);
        MinimumInterval(500);
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                boolean unused = Sound.this.loadComplete = true;
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the sound file.  Only certain formats are supported.  See http://developer.android.com/guide/appendix/media-formats.html.")
    public String Source() {
        return this.sourcePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "audio_asset")
    @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
    @SimpleProperty
    public void Source(@Asset String str) {
        final String str2 = str == null ? "" : str;
        if (!TiramisuUtil.requestAudioPermissions(this.form, str, new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    Sound.this.Source(str2);
                } else {
                    Sound.this.form.dispatchPermissionDeniedEvent(Sound.this.thisComponent, "Source", str);
                }
            }
        })) {
            this.sourcePath = str2;
            int i = this.streamId;
            if (i != 0) {
                this.soundPool.stop(i);
                this.streamId = 0;
            }
            this.soundId = 0;
            if (this.sourcePath.length() != 0) {
                Integer num = this.soundMap.get(this.sourcePath);
                if (num != null) {
                    this.soundId = num.intValue();
                    return;
                }
                Log.i("Sound", "No existing sound with path " + this.sourcePath + ".");
                try {
                    int loadSoundPool = MediaUtil.loadSoundPool(this.soundPool, this.form, this.sourcePath);
                    if (loadSoundPool != 0) {
                        this.soundMap.put(this.sourcePath, Integer.valueOf(loadSoundPool));
                        Log.i("Sound", "Successfully began loading sound: setting soundId to " + loadSoundPool + ".");
                        this.soundId = loadSoundPool;
                        this.loadComplete = false;
                        return;
                    }
                    this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
                } catch (PermissionException e) {
                    this.form.dispatchPermissionDeniedEvent((Component) this, "Source", e);
                } catch (Exception unused) {
                    this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
                }
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The minimum interval, in milliseconds, between sounds.  If you play a sound, all further Play() calls will be ignored until the interval has elapsed.")
    public int MinimumInterval() {
        return this.minimumInterval;
    }

    @DesignerProperty(defaultValue = "500", editorType = "non_negative_integer")
    @SimpleProperty
    public void MinimumInterval(int i) {
        this.minimumInterval = i;
    }

    @SimpleFunction(description = "Plays the sound specified by the Source property.")
    public void Play() {
        if (this.soundId != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.timeLastPlayed;
            if (j == 0 || currentTimeMillis >= j + ((long) this.minimumInterval)) {
                this.timeLastPlayed = currentTimeMillis;
                this.delayRetries = 10;
                playWhenLoadComplete();
                return;
            }
            Log.i("Sound", "Unable to play because MinimumInterval has not elapsed since last play.");
            return;
        }
        Log.i("Sound", "Sound Id was 0. Did you remember to set the Source property?");
        this.form.dispatchErrorOccurredEvent(this, "Play", ErrorMessages.ERROR_UNABLE_TO_PLAY_MEDIA, this.sourcePath);
    }

    /* access modifiers changed from: private */
    public void playWhenLoadComplete() {
        if (this.loadComplete) {
            playAndCheckResult();
            return;
        }
        Log.i("Sound", "Sound not ready:  retrying.  Remaining retries = " + this.delayRetries);
        this.playWaitHandler.postDelayed(new Runnable() {
            public final void run() {
                if (Sound.this.loadComplete) {
                    Sound.this.playAndCheckResult();
                } else if (Sound.this.delayRetries > 0) {
                    Sound.access$410(Sound.this);
                    Sound.this.playWhenLoadComplete();
                } else {
                    Sound.this.form.dispatchErrorOccurredEvent(Sound.this.thisComponent, "Play", ErrorMessages.ERROR_SOUND_NOT_READY, Sound.this.sourcePath);
                }
            }
        }, 50);
    }

    /* access modifiers changed from: private */
    public void playAndCheckResult() {
        SoundPool soundPool2 = this.soundPool;
        if (soundPool2 != null) {
            this.streamId = soundPool2.play(this.soundId, 1.0f, 1.0f, 0, 0, 1.0f);
            Log.i("Sound", "SoundPool.play returned stream id " + this.streamId);
            if (this.streamId == 0) {
                this.form.dispatchErrorOccurredEvent(this, "Play", ErrorMessages.ERROR_UNABLE_TO_PLAY_MEDIA, this.sourcePath);
            }
        }
    }

    @SimpleFunction(description = "Pauses playing the sound if it is being played.")
    public void Pause() {
        int i = this.streamId;
        if (i != 0) {
            this.soundPool.pause(i);
        } else {
            Log.i("Sound", "Unable to pause. Did you remember to call the Play function?");
        }
    }

    @SimpleFunction(description = "Resumes playing the sound after a pause.")
    public void Resume() {
        int i = this.streamId;
        if (i != 0) {
            this.soundPool.resume(i);
        } else {
            Log.i("Sound", "Unable to resume. Did you remember to call the Play function?");
        }
    }

    @SimpleFunction(description = "Stops playing the sound if it is being played.")
    public void Stop() {
        int i = this.streamId;
        if (i != 0) {
            this.soundPool.stop(i);
            this.streamId = 0;
            return;
        }
        Log.i("Sound", "Unable to stop. Did you remember to call the Play function?");
    }

    @SimpleFunction(description = "Vibrates for the specified number of milliseconds.")
    public void Vibrate(int i) {
        this.vibe.vibrate((long) i);
    }

    @SimpleFunction(description = "Vibrate with a given pattern")
    public void VibratePattern(int i, int i2, boolean z) {
        this.vibe.vibrate(new long[]{0, (long) i, (long) i2}, z ? 0 : -1);
    }

    @SimpleFunction(description = "Ringer mode that may be audible and may vibrate.")
    public void SoundNormal() {
        this.mAudioManager.setRingerMode(2);
    }

    @SimpleFunction(description = "Ringer mode that will be silent and will not vibrate.")
    public void SoundSilent() {
        this.mAudioManager.setRingerMode(0);
    }

    @SimpleFunction(description = "Ringer mode that will be silent and will vibrate.")
    public void SoundVibrate() {
        this.mAudioManager.setRingerMode(1);
    }

    public void onStop() {
        SoundPool soundPool2;
        Log.i("Sound", "Got onStop");
        int i = this.streamId;
        if (i != 0 && (soundPool2 = this.soundPool) != null) {
            soundPool2.pause(i);
        }
    }

    public void onResume() {
        SoundPool soundPool2;
        Log.i("Sound", "Got onResume");
        int i = this.streamId;
        if (i != 0 && (soundPool2 = this.soundPool) != null) {
            soundPool2.resume(i);
        }
    }

    public void onDestroy() {
        prepareToDie();
    }

    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        int i = this.streamId;
        if (i != 0) {
            this.soundPool.stop(i);
            this.soundPool.unload(this.streamId);
        }
        this.soundPool.release();
        this.vibe.cancel();
        this.soundPool = null;
    }
}
