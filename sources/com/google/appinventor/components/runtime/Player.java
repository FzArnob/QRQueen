package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FroyoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TiramisuUtil;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "Multimedia component that plays audio and controls phone vibration.  The name of a multimedia field is specified in the <code>Source</code> property, which can be set in the Designer or in the Blocks Editor.  The length of time for a vibration is specified in the Blocks Editor in milliseconds (thousandths of a second).\n<p>For supported audio formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p>\n<p>This component is best for long sound files, such as songs, while the <code>Sound</code> component is more efficient for short files, such as sound effects.</p>", iconName = "images/player.png", nonVisible = true, version = 8)
@SimpleObject
@UsesPermissions({"android.permission.VIBRATE", "android.permission.INTERNET"})
public final class Player extends AndroidNonvisibleComponent implements MediaPlayer.OnCompletionListener, Component, Deleteable, OnDestroyListener, OnPauseListener, OnResumeListener, OnStopListener {
    private boolean ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd;
    private AudioManager B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private Object Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    private int TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = 50;
    private final Activity activity;
    private boolean bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb;
    private boolean haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5;
    private MediaPlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    public State playerState;
    private int q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = 50;
    private String sourcePath;
    private int symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = 50;
    private final Vibrator vibe;

    public enum State {
        INITIAL,
        PREPARED,
        PLAYING,
        PAUSED_BY_USER,
        PAUSED_BY_EVENT
    }

    @SimpleEvent(description = "The PlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public final void PlayerError(String str) {
    }

    public Player(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Activity $context = componentContainer.$context();
        this.activity = $context;
        this.sourcePath = "";
        this.vibe = (Vibrator) this.form.getSystemService("vibrator");
        this.form.registerForOnDestroy(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnPause(this);
        this.form.registerForOnStop(this);
        this.form.setVolumeControlStream(3);
        this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = false;
        this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb = false;
        this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = false;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = (AudioManager) $context.getSystemService("audio");
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = FroyoUtil.setAudioFocusChangeListener(this);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final String Source() {
        return this.sourcePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "audio_asset")
    @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_MEDIA_AUDIO"})
    @SimpleProperty
    public final void Source(@Asset String str) {
        final String str2 = str == null ? "" : str;
        if (!TiramisuUtil.requestAudioPermissions(this.form, str, new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    Player.this.Source(str2);
                } else {
                    Player.this.form.dispatchPermissionDeniedEvent((Component) Player.this, "Source", str);
                }
            }
        })) {
            this.sourcePath = str2;
            if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
                this.playerState = State.INITIAL;
            }
            MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
            if (this.sourcePath.length() > 0) {
                MediaPlayer mediaPlayer2 = new MediaPlayer();
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mediaPlayer2;
                mediaPlayer2.setOnCompletionListener(this);
                try {
                    MediaUtil.loadMediaPlayer(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.form, this.sourcePath);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAudioStreamType(3);
                    PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC();
                    tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE();
                } catch (PermissionException e) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                    this.form.dispatchPermissionDeniedEvent((Component) this, "Source", e);
                } catch (Exception unused) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                    this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
                }
            }
        }
    }

    private void PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC() {
        boolean focusRequestGranted = FroyoUtil.focusRequestGranted(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = focusRequestGranted;
        if (!focusRequestGranted) {
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_FOCUS_MEDIA, this.sourcePath);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether the media is playing")
    public final boolean IsPlaying() {
        if (this.playerState == State.PREPARED || this.playerState == State.PLAYING) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying();
        }
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will loop when it plays. Setting Loop while the player is playing will affect the current playing.")
    public final boolean Loop() {
        return this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public final void Loop(boolean z) {
        if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLooping(z);
        }
        this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = z;
    }

    @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
    @SimpleProperty(description = "Sets the volume to a number between 0 and 100.")
    public final void Volume(int i) {
        if (this.playerState != State.PREPARED && this.playerState != State.PLAYING && this.playerState != State.PAUSED_BY_USER) {
            return;
        }
        if (i > 100 || i < 0) {
            this.form.dispatchErrorOccurredEvent(this, "Volume", ErrorMessages.ERROR_PLAYER_INVALID_VOLUME, Integer.valueOf(i));
            return;
        }
        float f = ((float) i) / 100.0f;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume(f, f);
        this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = i;
        this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = i;
        this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = i;
    }

    @SimpleProperty(description = "Return the player volume.")
    public final int Volume() {
        return this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq;
    }

    @SimpleFunction(description = "Control the left and right volume of the player. Set the volume to a number between 0 and 100.")
    public final void SetLeftRightVolume(int i, int i2) {
        if (this.playerState != State.PREPARED && this.playerState != State.PLAYING && this.playerState != State.PAUSED_BY_USER) {
            return;
        }
        if (i > 100 || i < 0) {
            this.form.dispatchErrorOccurredEvent(this, "Volume", ErrorMessages.ERROR_PLAYER_INVALID_VOLUME, Integer.valueOf(i));
        } else if (i2 > 100 || i2 < 0) {
            this.form.dispatchErrorOccurredEvent(this, "Volume", ErrorMessages.ERROR_PLAYER_INVALID_VOLUME, Integer.valueOf(i2));
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume(((float) i) / 100.0f, ((float) i2) / 100.0f);
            this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = i;
            this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = i2;
        }
    }

    @SimpleProperty(description = "Returns the current left volume.")
    public final int LeftVolume() {
        return this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK;
    }

    @SimpleProperty(description = "Returns the current right volume.")
    public final int RightVolume() {
        return this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4;
    }

    @SimpleProperty(description = "Returns the current position of the source file that is playing.")
    public final int CurrentPosition() {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @SimpleProperty(description = "Returns the duration of the source file.")
    public final int Duration() {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    @SimpleFunction(description = "Set a position where the source file should start playing.")
    public final void SeekTo(int i) {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int i2 = i + currentPosition;
            if (i2 <= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(i2);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(currentPosition);
            }
        }
    }

    @SimpleProperty(description = "Returns a array of track information.")
    public final String GetTrackInfo() {
        MediaPlayer.TrackInfo[] trackInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTrackInfo();
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || trackInfo == null || trackInfo.length <= 0) {
            return "0";
        }
        String str = "";
        for (int i = 0; i < trackInfo.length; i++) {
            str = str + trackInfo[i];
        }
        return str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will pause playing when leaving the current screen; if false (default option), the player continues playing whenever the current screen is displaying or not.")
    public final boolean PlayOnlyInForeground() {
        return this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public final void PlayOnlyInForeground(boolean z) {
        this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb = z;
    }

    @SimpleFunction
    public final void Start() {
        if (!this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5) {
            PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC();
        }
        if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER || this.playerState == State.PAUSED_BY_EVENT) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLooping(this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
            this.playerState = State.PLAYING;
        }
    }

    @SimpleFunction
    public final void Pause() {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null) {
            boolean isPlaying = mediaPlayer.isPlaying();
            if (this.playerState == State.PLAYING) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
                if (isPlaying) {
                    this.playerState = State.PAUSED_BY_USER;
                }
            }
        }
    }

    public final void pause() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.playerState == State.PLAYING) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
            this.playerState = State.PAUSED_BY_EVENT;
        }
    }

    @SimpleFunction
    public final void Stop() {
        if (this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5) {
            moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0();
        }
        if (this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER || this.playerState == State.PAUSED_BY_EVENT) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE();
            MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
            }
        }
    }

    private void moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0() {
        FroyoUtil.abandonFocus(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = false;
    }

    @SimpleFunction
    public final void Vibrate(long j) {
        this.vibe.vibrate(j);
    }

    private void tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.prepare();
            this.playerState = State.PREPARED;
        } catch (Exception unused) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            this.playerState = State.INITIAL;
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_PREPARE_MEDIA, this.sourcePath);
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Completed();
    }

    @SimpleEvent
    public final void Completed() {
        if (this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5) {
            moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0();
        }
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    @SimpleEvent(description = "This event is signaled when another player has started (and the current player is playing or paused, but not stopped).")
    public final void OtherPlayerStarted() {
        EventDispatcher.dispatchEvent(this, "OtherPlayerStarted", new Object[0]);
    }

    public final void onResume() {
        if (this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb && this.playerState == State.PAUSED_BY_EVENT) {
            Start();
        }
    }

    public final void onPause() {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null && this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb && mediaPlayer.isPlaying()) {
            pause();
        }
    }

    public final void onStop() {
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null && this.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb && mediaPlayer.isPlaying()) {
            pause();
        }
    }

    public final void onDestroy() {
        prepareToDie();
    }

    public final void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5) {
            moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0();
        }
        if (!(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || this.playerState == State.INITIAL)) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
        }
        this.playerState = State.INITIAL;
        MediaPlayer mediaPlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
        this.vibe.cancel();
    }
}
