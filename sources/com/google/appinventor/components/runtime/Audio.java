package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/audio.png", nonVisible = true, version = 3)
@UsesPermissions({"android.permission.MODIFY_AUDIO_SETTINGS"})
public class Audio extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;
    private AudioManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = false;

    public Audio(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Activity $context = componentContainer.$context();
        this.context = $context;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (AudioManager) $context.getSystemService("audio");
        Log.d("Audio", "Audio Created");
    }

    @SimpleEvent(description = "Event triggered when a error occurred.")
    public void ErrorOccurred(String str) {
        EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleFunction(description = "Sets the ringer mode to \"vibrate\".")
    public void RingerModeVibrate() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(1);
        } catch (Exception e) {
            Log.e("Audio", String.valueOf(e));
            ErrorOccurred(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the ringer mode to \"normal\".")
    public void RingerModeNormal() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(2);
        } catch (Exception e) {
            Log.e("Audio", String.valueOf(e));
            ErrorOccurred(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the ringer mode to \"silent\".")
    public void RingerModeSilent() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(0);
        } catch (Exception e) {
            Log.e("Audio", String.valueOf(e));
            ErrorOccurred(e.getMessage());
        }
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeRing() {
        return (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(2) * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(2);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeRing(int i) {
        boolean z = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(2, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(2) * i) / 100, z ? 1 : 0);
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeMusic() {
        return (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(3) * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(3);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeMusic(int i) {
        boolean z = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(3, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(3) * i) / 100, z ? 1 : 0);
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeAlarm() {
        return (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(4) * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(4);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeAlarm(int i) {
        boolean z = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(4, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(4) * i) / 100, z ? 1 : 0);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true you will see system ui.")
    public void ShowUI(boolean z) {
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = z;
    }

    @SimpleProperty
    public boolean ShowUI() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    }

    @SimpleProperty(description = "Returns the current audio mode as string. Possible returns are \"NORMAL\", \"RINGTONE\", \"CALL\" or \"COMMUNICATION\".")
    public String GetAudioMode() {
        int mode = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMode();
        if (mode == 0) {
            return "NORMAL";
        }
        if (mode == 1) {
            return "RINGTONE";
        }
        if (mode != 2) {
            return mode != 3 ? "Not found" : "COMMUNICATION";
        }
        return "CALL";
    }

    @SimpleFunction(description = "Set whether a component should have sound effects enabled for events such as clicking and touching.")
    public void SoundEffectsEnabled(AndroidViewComponent androidViewComponent, boolean z) {
        androidViewComponent.getView().setSoundEffectsEnabled(z);
    }

    @SimpleFunction(description = "Returns true whether a component should have sound effects enabled for events such as clicking and touching.")
    public boolean IsSoundEffectsEnabled(AndroidViewComponent androidViewComponent) {
        return androidViewComponent.getView().isSoundEffectsEnabled();
    }
}
