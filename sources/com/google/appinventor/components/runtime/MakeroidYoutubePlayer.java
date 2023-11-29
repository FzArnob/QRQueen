package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.PlayerUIController;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "", iconName = "images/youtubePlayer.png", version = 2)
@UsesLibraries({"youtube-player.jar", "youtube-player.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class MakeroidYoutubePlayer extends AndroidViewComponent {
    private float YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = 0.0f;
    private boolean ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = false;
    private ComponentContainer container;
    private Context context;
    private int e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = 50;
    /* access modifiers changed from: private */
    public YouTubePlayer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private YouTubePlayerView f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public PlayerUIController f226hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private YouTubePlayerTracker f227hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK;

    @SimpleProperty(description = "Use this block to test the youtube player. Powered by Kodular.io")
    public String TestVideoId() {
        return "_bZj-LOXdH8";
    }

    public MakeroidYoutubePlayer(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new YouTubePlayerView(this.context);
        this.f227hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new YouTubePlayerTracker();
        this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.initialize(new YouTubePlayerInitListener() {
            public final void onInitSuccess(YouTubePlayer youTubePlayer) {
                YouTubePlayer unused = MakeroidYoutubePlayer.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = youTubePlayer;
                MakeroidYoutubePlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidYoutubePlayer.this).addListener(MakeroidYoutubePlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidYoutubePlayer.this));
                MakeroidYoutubePlayer makeroidYoutubePlayer = MakeroidYoutubePlayer.this;
                PlayerUIController unused2 = makeroidYoutubePlayer.f226hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MakeroidYoutubePlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(makeroidYoutubePlayer).getPlayerUIController();
                MakeroidYoutubePlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidYoutubePlayer.this);
            }
        }, true);
        componentContainer.$add(this);
        StartSecond(0.0f);
        Volume(50);
        EnableLiveVideoUI(false);
        Log.d("Kodular Youtube Player", "Makeroid Youtube Player Created");
    }

    public YouTubePlayerView getView() {
        return this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleEvent(description = "Event to get notified when the player enters or exits fullscreen. The variable 'fullscreen' returns true or false.")
    public void Fullscreen(boolean z) {
        EventDispatcher.dispatchEvent(this, "Fullscreen", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Use this event to start the playing of a normal or instant youtube video.")
    public void Initialized() {
        EventDispatcher.dispatchEvent(this, "Initialized", new Object[0]);
    }

    @SimpleEvent(description = "Use this event to detect that the state changes. Return values: 'UNKNOWN', 'UNSTARTED', 'ENDED', 'PLAYING', 'PAUSED', 'BUFFERING' or 'VIDEO_CUED'.")
    public void StateChanged(String str) {
        EventDispatcher.dispatchEvent(this, "StateChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that the playback quality was changed. Return values: 'UNKNOWN', 'SMALL', 'MEDIUM', 'LARGE', 'HD720', 'HD1080', 'HIGH_RES' or 'DEFAULT'.")
    public void PlaybackQualityChanged(String str) {
        EventDispatcher.dispatchEvent(this, "PlaybackQualityChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that the playback rate was changed. Return values: 'UNKNOWN', 'RATE_0_25', 'RATE_0_5', 'RATE_1', 'RATE_1_5' or 'RATE_2'.")
    public void PlaybackRateChanged(String str) {
        EventDispatcher.dispatchEvent(this, "PlaybackRateChanged", str);
    }

    @SimpleEvent(description = "Use this event to detect that there was any error with the player. Return values: 'UNKNOWN', 'INVALID_PARAMETER_IN_REQUEST', 'HTML_5_PLAYER', 'VIDEO_NOT_FOUND', 'VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER', 'INVALID_VOLUME' or 'INVALID_SEEK_TO'.")
    public void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleFunction(description = "Use this block together with the 'Youtube Player' Initialized event. Loads and automatically plays the specified youtube video. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public void InstantLoad(String str) {
        YouTubePlayer youTubePlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (youTubePlayer != null) {
            youTubePlayer.loadVideo(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str), this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u);
        }
    }

    @SimpleFunction(description = "Loads the specified video's thumbnail and prepares the player to play the video. Does not automatically play the video. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public void Load(String str) {
        YouTubePlayer youTubePlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (youTubePlayer != null) {
            youTubePlayer.cueVideo(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str), this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u);
        }
    }

    @SimpleFunction(description = "Plays the youtube video.")
    public void Play() {
        YouTubePlayer youTubePlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (youTubePlayer != null) {
            youTubePlayer.play();
        }
    }

    @SimpleFunction(description = "Pause the youtube video.")
    public void Pause() {
        YouTubePlayer youTubePlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (youTubePlayer != null) {
            youTubePlayer.pause();
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "The time from which the video should start playing.")
    public void StartSecond(float f) {
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = f;
        this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = String.valueOf(f);
    }

    @SimpleProperty
    public float StartSecond() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @SimpleFunction(description = "Set a position where the youtube video should start playing in seconds.")
    public void SeekTo(float f) {
        YouTubePlayer youTubePlayer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (youTubePlayer != null) {
            try {
                youTubePlayer.seekTo(f);
            } catch (Exception e) {
                Log.e("Kodular Youtube Player", String.valueOf(e));
                Error("INVALID_SEEK_TO");
            }
        }
    }

    @DesignerProperty(defaultValue = "50", editorType = "integer")
    @SimpleProperty(description = "Set the volume to a number between 0 and 100.Use only integer numbers.")
    public void Volume(int i) {
        if (i > 100 || i < 0) {
            Error("INVALID_VOLUME");
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u);
            this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = sb.toString();
            this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = i;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume(i);
        }
    }

    @SimpleProperty(description = "Return the player volume.")
    public int Volume() {
        return this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT;
    }

    @SimpleProperty(description = "Returns true if the player is in fullscreen mode.")
    public boolean IsFullscreen() {
        return this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isFullScreen();
    }

    @SimpleFunction(description = "Toggle the state of the video player.")
    public void ToggleFullscreen() {
        this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toggleFullScreen();
    }

    @SimpleFunction(description = "Enter the video in fullscreen mode.")
    public void EnterFullscreen() {
        this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enterFullScreen();
    }

    @SimpleFunction(description = "Exit the video from fullscreen mode.")
    public void ExitFullscreen() {
        this.f225hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.exitFullScreen();
    }

    @SimpleProperty(description = "Returns the current second.")
    public float GetCurrentSecond() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f227hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentSecond();
        }
        return 0.0f;
    }

    @SimpleProperty(description = "Returns the video duration in seconds.")
    public float GetVideoDuration() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f227hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getVideoDuration();
        }
        return 0.0f;
    }

    @SimpleFunction(description = "This block will return the thumbnail image path from a video id. Use only as example '_bZj-LOXdH8' from a youtube video.")
    public String GetThumbnailFromVideoId(String str) {
        return "https://img.youtube.com/vi/" + vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str) + "/0.jpg";
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void EnableLiveVideoUI(boolean z) {
        this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = z;
        PlayerUIController playerUIController = this.f226hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (playerUIController != null) {
            playerUIController.enableLiveVideoUI(z);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If you want to play live videos you must setup the UI accordingly, by calling this method. If enabled, the user can not select a second on the video progress bar.")
    public boolean EnableLiveVideoUI() {
        return this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ;
    }

    private static String vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        if (str.contains("https://www.youtube.com/watch?v=")) {
            return str.replace("https://www.youtube.com/watch?v=", "");
        }
        return str.contains("www.youtube.com/watch?v=") ? str.replace("www.youtube.com/watch?v=", "") : str;
    }
}
