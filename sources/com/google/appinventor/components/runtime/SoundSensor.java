package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TimerInternal;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Physical world component that can detect such data as: sound amplitude (measurement of the degree of change [positive or negative] ).", iconName = "images/soundSensor.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.RECORD_AUDIO"})
public class SoundSensor extends AndroidNonvisibleComponent implements AlarmHandler, Deleteable, OnResumeListener, OnStopListener {
    private MediaRecorder B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
    private String LOG_TAG = "SoundSensor";
    private boolean enabled = false;
    private double f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = 100.0d;
    private TimerInternal hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TimerInternal(this, false, 100);
    private double zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = 0.0d;

    public SoundSensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleProperty(description = "Returns the max sound level.")
    public double MaxSoundlevel() {
        return this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    }

    @DesignerProperty(defaultValue = "100", editorType = "integer")
    @SimpleProperty
    public void MaxSoundlevel(int i) {
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = (double) i;
    }

    @SimpleEvent(description = "Triggered when the sound level has changed")
    public void SoundChanged(double d) {
        EventDispatcher.dispatchEvent(this, "SoundChanged", Double.valueOf(d));
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Starts or Stops listening to sound changes")
    public void Listen(boolean z) {
        this.enabled = z;
        if (z) {
            if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
                try {
                    MediaRecorder mediaRecorder = new MediaRecorder();
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = mediaRecorder;
                    mediaRecorder.setAudioSource(1);
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setOutputFormat(1);
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setAudioEncoder(1);
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setOutputFile("/dev/null");
                } catch (Exception e) {
                    Log.e(this.LOG_TAG, String.valueOf(e));
                    return;
                }
            }
            try {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.prepare();
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.start();
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(true);
            } catch (Exception e2) {
                Log.e(this.LOG_TAG, String.valueOf(e2));
            }
        } else {
            stopListening();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true if listening to sound changes, else false.")
    public boolean Listen() {
        return this.enabled;
    }

    public void stopListening() {
        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(false);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.stop();
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.release();
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the sound level.")
    public double SoundLevel() {
        return this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
    }

    public void alarm() {
        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
            double Amplitude = Amplitude() * (this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ / 32768.0d);
            this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = Amplitude;
            SoundChanged(Amplitude);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the real sound amplitude which can be between 0 to 32768.")
    public double Amplitude() {
        MediaRecorder mediaRecorder = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (mediaRecorder != null) {
            return (double) mediaRecorder.getMaxAmplitude();
        }
        return 0.0d;
    }

    public double Decibel() {
        return Math.log10(Amplitude()) * 20.0d;
    }

    public void onResume() {
        Listen(this.enabled);
    }

    public void onStop() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }
}
