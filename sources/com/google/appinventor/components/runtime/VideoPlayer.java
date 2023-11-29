package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
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
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TiramisuUtil;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "A multimedia component capable of playing videos. When the application is run, the VideoPlayer will be displayed as a rectangle on-screen.  If the user touches the rectangle, controls will appear to play/pause, skip ahead, and skip backward within the video.  The application can also control behavior by calling the <code>Start</code>, <code>Pause</code>, and <code>SeekTo</code> methods.  <p>Video files should be in 3GPP (.3gp) or MPEG-4 (.mp4) formats.  For more details about legal formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>App Inventor for Android only permits video files under 1 MB and limits the total size of an application to 5 MB, not all of which is available for media (video, audio, and sound) files.  If your media files are too large, you may get errors when packaging or installing your application, in which case you should reduce the number of media files or their sizes.  Most video editing software, such as Windows Movie Maker and Apple iMovie, can help you decrease the size of videos by shortening them or re-encoding the video into a more compact format.</p><p>You can also set the media source to a URL that points to a streaming video, but the URL must point to the video file itself, not to a program that plays the video.", version = 8)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public final class VideoPlayer extends AndroidViewComponent implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, Deleteable, OnDestroyListener {
    private MediaPlayer B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private boolean IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
    private int TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = 50;
    /* access modifiers changed from: private */
    public final Handler androidUIHandler = new Handler();
    private MediaController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final a f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = false;
    private String sourcePath;
    private boolean vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;

    @SimpleEvent(description = "The VideoPlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public final void VideoPlayerError(String str) {
    }

    public VideoPlayer(ComponentContainer componentContainer) {
        super(componentContainer);
        componentContainer.$form().registerForOnDestroy(this);
        a aVar = new a(componentContainer.$context());
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
        MediaController mediaController = new MediaController(componentContainer.$context());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mediaController;
        aVar.setMediaController(mediaController);
        aVar.setOnCompletionListener(this);
        aVar.setOnErrorListener(this);
        aVar.setOnPreparedListener(this);
        componentContainer.$add(this);
        componentContainer.setChildWidth(this, 176);
        componentContainer.setChildHeight(this, 144);
        componentContainer.$form().setVolumeControlStream(3);
        this.sourcePath = "";
    }

    public final View getView() {
        return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @DesignerProperty(defaultValue = "", editorType = "video_asset")
    @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The \"path\" to the video.  Usually, this will be the name of the video file, which should be added in the Designer.")
    public final void Source(@Asset String str) {
        final String str2 = str == null ? "" : str;
        if (!TiramisuUtil.requestVideoPermissions(this.container.$form(), str, new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    VideoPlayer.this.Source(str2);
                } else {
                    VideoPlayer.this.container.$form().dispatchPermissionDeniedEvent((Component) VideoPlayer.this, "Source", str);
                }
            }
        })) {
            if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
                this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, this, str);
                return;
            }
            if (str == null) {
                str = "";
            }
            this.sourcePath = str;
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5();
            if (this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying()) {
                this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopPlayback();
            }
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVideoURI((Uri) null);
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearAnimation();
            if (this.sourcePath.length() > 0) {
                Log.i("VideoPlayer", "Source path is " + this.sourcePath);
                try {
                    this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
                    MediaUtil.loadVideoView(this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.container.$form(), this.sourcePath);
                    Log.i("VideoPlayer", "loading video succeeded");
                } catch (PermissionException e) {
                    this.container.$form().dispatchPermissionDeniedEvent((Component) this, "Source", e);
                } catch (Exception unused) {
                    this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
                }
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether the media is playing.")
    public final boolean IsPlaying() {
        return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying();
    }

    @SimpleProperty(description = "Returns the current position of the source file that is playing.")
    public final int CurrentPosition() {
        return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition();
    }

    @SimpleFunction(description = "Starts playback of the video.")
    public final void Start() {
        Log.i("VideoPlayer", "Calling Start");
        if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, this, (Object) null);
        } else if (this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90) {
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
        } else {
            this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = true;
        }
    }

    @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
    @SimpleProperty(description = "Sets the volume to a number between 0 and 100. Values less than 0 will be treated as 0, and values greater than 100 will be treated as 100.")
    public final void Volume(int i) {
        int min = Math.min(Math.max(i, 0), 100);
        MediaPlayer mediaPlayer = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (mediaPlayer != null) {
            float f = ((float) min) / 100.0f;
            mediaPlayer.setVolume(f, f);
            this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = min;
        }
    }

    @SimpleProperty(description = "Return the volume.")
    public final int Volume() {
        return this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq;
    }

    public final void delayedStart() {
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = true;
        Start();
    }

    @SimpleFunction(description = "Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.")
    public final void Pause() {
        Log.i("VideoPlayer", "Calling Pause");
        if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, this, (Object) null);
            this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
            return;
        }
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
    }

    @SimpleFunction(description = "Resets to start of video and pauses it if video was playing.")
    public final void Stop() {
        Log.i("VideoPlayer", "Calling Stop");
        Start();
        SeekTo(0);
        Pause();
    }

    @SimpleFunction(description = "Seeks to the requested time (specified in milliseconds) in the video. If the video is paused, the frame shown will not be updated by the seek. The player can jump only to key frames in the video, so seeking to times that differ by short intervals may not actually move to different frames.")
    public final void SeekTo(int i) {
        Log.i("VideoPlayer", "Calling SeekTo");
        if (i < 0) {
            i = 0;
        }
        if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, this, Integer.valueOf(i));
        } else {
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(i);
        }
    }

    @SimpleFunction(description = "Returns duration of the video in milliseconds.")
    public final int GetDuration() {
        Log.i("VideoPlayer", "Calling GetDuration");
        if (!this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration();
        }
        Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, this, (Object) null);
        if (fullScreenVideoAction.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
            return fullScreenVideoAction.getInt(FullScreenVideoUtil.ACTION_DATA);
        }
        return 0;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Completed();
    }

    @SimpleEvent
    public final void Completed() {
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5();
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
        Log.e("VideoPlayer", "onError: what is " + i + " 0x" + Integer.toHexString(i) + ", extra is " + i2 + " 0x" + Integer.toHexString(i2));
        this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = true;
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = mediaPlayer;
        a aVar = this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        aVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = mediaPlayer;
        aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.TRUE;
        aVar.forceLayout();
        aVar.invalidate();
        if (this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ) {
            Start();
        }
    }

    public final void onDestroy() {
        prepareToDie();
    }

    public final void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying()) {
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopPlayback();
        }
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVideoURI((Uri) null);
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearAnimation();
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = false;
        this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = false;
        if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle);
        }
    }

    @SimpleProperty
    public final int Width() {
        return super.Width();
    }

    @SimpleProperty(userVisible = true)
    public final void Width(int i) {
        super.Width(i);
        a aVar = this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i, aVar.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T);
    }

    @SimpleProperty
    public final int Height() {
        return super.Height();
    }

    @SimpleProperty(userVisible = true)
    public final void Height(int i) {
        super.Height(i);
        a aVar = this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(aVar.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT, i);
    }

    @SimpleProperty
    public final boolean FullScreen() {
        return this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM;
    }

    @SimpleProperty(userVisible = true)
    public final void FullScreen(boolean z) {
        if (z == this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM) {
            return;
        }
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION, this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentPosition());
            bundle.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING, this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPlaying());
            this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
            bundle.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, true);
            bundle.putString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE, this.sourcePath);
            if (this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle).getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = true;
                return;
            }
            this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = false;
            this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_UNAVAILBLE, "");
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
        Bundle fullScreenVideoAction = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, bundle2);
        if (fullScreenVideoAction.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
            fullScreenKilled(fullScreenVideoAction);
            return;
        }
        this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = true;
        this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_CANT_EXIT, "");
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled the user will see the control buttons.")
    public final void ShowControls(boolean z) {
        if (z) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        }
    }

    public final void fullScreenKilled(Bundle bundle) {
        this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = false;
        String string = bundle.getString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE);
        if (string != null && !string.equals(this.sourcePath)) {
            Source(string);
        }
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestLayout();
        SeekTo(bundle.getInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION));
        if (bundle.getBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING)) {
            Start();
        }
    }

    public final int getPassedWidth() {
        return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT;
    }

    public final int getPassedHeight() {
        return this.f268hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
    }

    class a extends VideoView {
        public int roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = -1;
        Boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.FALSE;
        MediaPlayer wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
        public int xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = -1;

        public a(Context context) {
            super(context);
        }

        public final void onMeasure(int i, int i2) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, i2, 0);
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x00af, code lost:
            if (r0 != 1073741824) goto L_0x00a3;
         */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00cf  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x010f  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x011b  */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x0158  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x016e  */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x01ae  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int r18, int r19, int r20) {
            /*
                r17 = this;
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r20
                com.google.appinventor.components.runtime.VideoPlayer r0 = com.google.appinventor.components.runtime.VideoPlayer.this
                com.google.appinventor.components.runtime.ComponentContainer r0 = r0.container
                com.google.appinventor.components.runtime.Form r0 = r0.$form()
                float r5 = r0.deviceDensity()
                java.lang.String r0 = java.lang.String.valueOf(r5)
                java.lang.String r6 = "Device Density = "
                java.lang.String r0 = r6.concat(r0)
                java.lang.String r6 = "VideoPlayer..onMeasure"
                android.util.Log.i(r6, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r7 = "AI setting dimensions as:"
                r0.<init>(r7)
                int r7 = r1.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
                r0.append(r7)
                java.lang.String r7 = ":"
                r0.append(r7)
                int r8 = r1.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r0.append(r8)
                java.lang.String r0 = r0.toString()
                android.util.Log.i(r6, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r8 = "Dimenions from super>>"
                r0.<init>(r8)
                int r8 = android.view.View.MeasureSpec.getSize(r18)
                r0.append(r8)
                r0.append(r7)
                int r7 = android.view.View.MeasureSpec.getSize(r19)
                r0.append(r7)
                java.lang.String r0 = r0.toString()
                android.util.Log.i(r6, r0)
                int r0 = r1.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
                r8 = 1073741824(0x40000000, float:2.0)
                r9 = -2147483648(0xffffffff80000000, float:-0.0)
                r10 = -1
                r11 = -2
                java.lang.String r13 = "VideoPlayer.onMeasure"
                r14 = 176(0xb0, float:2.47E-43)
                if (r0 == r11) goto L_0x00a7
                if (r0 == r10) goto L_0x0071
                r7 = 1
                goto L_0x00c2
            L_0x0071:
                java.lang.Boolean r0 = r1.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L_0x00a3
                android.media.MediaPlayer r0 = r1.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou     // Catch:{ NullPointerException -> 0x008d }
                int r0 = r0.getVideoWidth()     // Catch:{ NullPointerException -> 0x008d }
                java.lang.String r15 = "Got width from MediaPlayer>"
                java.lang.String r7 = java.lang.String.valueOf(r0)     // Catch:{ NullPointerException -> 0x008d }
                java.lang.String r7 = r15.concat(r7)     // Catch:{ NullPointerException -> 0x008d }
                android.util.Log.i(r13, r7)     // Catch:{ NullPointerException -> 0x008d }
                goto L_0x00a5
            L_0x008d:
                r0 = move-exception
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                java.lang.String r15 = "Failed to get MediaPlayer for width:\n"
                r7.<init>(r15)
                java.lang.String r0 = r0.getMessage()
                r7.append(r0)
                java.lang.String r0 = r7.toString()
                android.util.Log.e(r6, r0)
            L_0x00a3:
                r0 = 176(0xb0, float:2.47E-43)
            L_0x00a5:
                r7 = 0
                goto L_0x00c2
            L_0x00a7:
                int r0 = android.view.View.MeasureSpec.getMode(r18)
                if (r0 == r9) goto L_0x00bd
                if (r0 == 0) goto L_0x00b2
                if (r0 == r8) goto L_0x00bd
                goto L_0x00a3
            L_0x00b2:
                android.view.ViewParent r0 = r17.getParent()     // Catch:{ ClassCastException | NullPointerException -> 0x00a3 }
                android.view.View r0 = (android.view.View) r0     // Catch:{ ClassCastException | NullPointerException -> 0x00a3 }
                int r0 = r0.getMeasuredWidth()     // Catch:{ ClassCastException | NullPointerException -> 0x00a3 }
                goto L_0x00a5
            L_0x00bd:
                int r0 = android.view.View.MeasureSpec.getSize(r18)
                goto L_0x00a5
            L_0x00c2:
                int r14 = r1.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
                java.lang.String r12 = ")"
                java.lang.String r15 = "VideoPlayer...onMeasure"
                r10 = 2
                r11 = -1000(0xfffffffffffffc18, float:NaN)
                r8 = 100
                if (r14 > r11) goto L_0x010f
                com.google.appinventor.components.runtime.VideoPlayer r7 = com.google.appinventor.components.runtime.VideoPlayer.this
                com.google.appinventor.components.runtime.ComponentContainer r7 = r7.container
                com.google.appinventor.components.runtime.Form r7 = r7.$form()
                int r7 = r7.Width()
                if (r7 != 0) goto L_0x0107
                if (r4 >= r10) goto L_0x0107
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r5 = "Width not stable... trying again (onMeasure "
                r0.<init>(r5)
                r0.append(r4)
                r0.append(r12)
                java.lang.String r0 = r0.toString()
                android.util.Log.d(r15, r0)
                com.google.appinventor.components.runtime.VideoPlayer r0 = com.google.appinventor.components.runtime.VideoPlayer.this
                android.os.Handler r0 = r0.androidUIHandler
                com.google.appinventor.components.runtime.VideoPlayer$a$1 r5 = new com.google.appinventor.components.runtime.VideoPlayer$a$1
                r5.<init>(r2, r3, r4)
                r2 = 100
                r0.postDelayed(r5, r2)
                r1.setMeasuredDimension(r8, r8)
                return
            L_0x0107:
                int r0 = r0 + 1000
                int r0 = -r0
                int r7 = r7 * r0
                int r7 = r7 / r8
                float r0 = (float) r7
                goto L_0x0112
            L_0x010f:
                if (r7 == 0) goto L_0x0115
                float r0 = (float) r0
            L_0x0112:
                float r0 = r0 * r5
                int r0 = (int) r0
            L_0x0115:
                r7 = r0
                int r0 = r1.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r14 = -2
                if (r0 == r14) goto L_0x0158
                r14 = -1
                if (r0 == r14) goto L_0x0121
                r16 = 1
                goto L_0x016a
            L_0x0121:
                java.lang.Boolean r0 = r1.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L_0x0153
                android.media.MediaPlayer r0 = r1.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou     // Catch:{ NullPointerException -> 0x013d }
                int r0 = r0.getVideoHeight()     // Catch:{ NullPointerException -> 0x013d }
                java.lang.String r14 = "Got height from MediaPlayer>"
                java.lang.String r9 = java.lang.String.valueOf(r0)     // Catch:{ NullPointerException -> 0x013d }
                java.lang.String r9 = r14.concat(r9)     // Catch:{ NullPointerException -> 0x013d }
                android.util.Log.i(r13, r9)     // Catch:{ NullPointerException -> 0x013d }
                goto L_0x0155
            L_0x013d:
                r0 = move-exception
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                java.lang.String r14 = "Failed to get MediaPlayer for height:\n"
                r9.<init>(r14)
                java.lang.String r0 = r0.getMessage()
                r9.append(r0)
                java.lang.String r0 = r9.toString()
                android.util.Log.e(r6, r0)
            L_0x0153:
                r0 = 144(0x90, float:2.02E-43)
            L_0x0155:
                r16 = 0
                goto L_0x016a
            L_0x0158:
                int r0 = android.view.View.MeasureSpec.getMode(r19)
                r6 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r0 == r6) goto L_0x0165
                r6 = 1073741824(0x40000000, float:2.0)
                if (r0 == r6) goto L_0x0165
                goto L_0x0153
            L_0x0165:
                int r0 = android.view.View.MeasureSpec.getSize(r19)
                goto L_0x0155
            L_0x016a:
                int r6 = r1.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                if (r6 > r11) goto L_0x01ae
                com.google.appinventor.components.runtime.VideoPlayer r6 = com.google.appinventor.components.runtime.VideoPlayer.this
                com.google.appinventor.components.runtime.ComponentContainer r6 = r6.container
                com.google.appinventor.components.runtime.Form r6 = r6.$form()
                int r6 = r6.Height()
                if (r6 != 0) goto L_0x01a6
                if (r4 >= r10) goto L_0x01a6
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r5 = "Height not stable... trying again (onMeasure "
                r0.<init>(r5)
                r0.append(r4)
                r0.append(r12)
                java.lang.String r0 = r0.toString()
                android.util.Log.d(r15, r0)
                com.google.appinventor.components.runtime.VideoPlayer r0 = com.google.appinventor.components.runtime.VideoPlayer.this
                android.os.Handler r0 = r0.androidUIHandler
                com.google.appinventor.components.runtime.VideoPlayer$a$2 r5 = new com.google.appinventor.components.runtime.VideoPlayer$a$2
                r5.<init>(r2, r3, r4)
                r2 = 100
                r0.postDelayed(r5, r2)
                r1.setMeasuredDimension(r8, r8)
                return
            L_0x01a6:
                int r0 = r0 + 1000
                int r0 = -r0
                int r6 = r6 * r0
                int r6 = r6 / r8
                float r0 = (float) r6
                goto L_0x01b1
            L_0x01ae:
                if (r16 == 0) goto L_0x01b4
                float r0 = (float) r0
            L_0x01b1:
                float r0 = r0 * r5
                int r0 = (int) r0
            L_0x01b4:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "Setting dimensions to:"
                r2.<init>(r3)
                r2.append(r7)
                java.lang.String r3 = "x"
                r2.append(r3)
                r2.append(r0)
                java.lang.String r2 = r2.toString()
                android.util.Log.i(r13, r2)
                android.view.SurfaceHolder r2 = r17.getHolder()
                r2.setFixedSize(r7, r0)
                r1.setMeasuredDimension(r7, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.VideoPlayer.a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int, int, int):void");
        }

        public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i, int i2) {
            this.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = i;
            this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = i2;
            forceLayout();
            invalidate();
        }

        public final void zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5() {
            this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Boolean.FALSE;
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
            forceLayout();
            invalidate();
        }
    }
}
