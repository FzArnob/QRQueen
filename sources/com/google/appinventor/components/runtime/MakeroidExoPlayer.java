package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.QUtil;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;

@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "", iconName = "images/exoplayer.png", nonVisible = true, version = 4)
@UsesLibraries({"exoplayer.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE"})
public class MakeroidExoPlayer extends AndroidNonvisibleComponent implements AudioManager.OnAudioFocusChangeListener, Component, OnDestroyListener {
    /* access modifiers changed from: private */
    public boolean AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = false;
    /* access modifiers changed from: private */
    public boolean UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = false;
    /* access modifiers changed from: private */
    public String W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = "";
    private ComponentContainer container;
    private Context context;
    private String d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq;
    private int e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = 50;
    private PackageManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SimpleExoPlayer f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public boolean isPause = false;
    /* access modifiers changed from: private */
    public boolean isPlaying = false;

    public MakeroidExoPlayer(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Activity $context = componentContainer.$context();
        this.context = $context;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context.getPackageManager();
        this.d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq = AppName();
        if (componentContainer.$form() instanceof ReplForm) {
            this.isCompanion = true;
        }
        componentContainer.$form().registerForOnDestroy(this);
        componentContainer.$form().setVolumeControlStream(3);
        SimpleExoPlayer newSimpleInstance = ExoPlayerFactory.newSimpleInstance(this.context, new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = newSimpleInstance;
        newSimpleInstance.addListener(new Player.EventListener() {
            public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }

            public final void onPositionDiscontinuity(int i) {
            }

            public final void onRepeatModeChanged(int i) {
            }

            public final void onSeekProcessed() {
            }

            public final void onShuffleModeEnabledChanged(boolean z) {
            }

            public final void onTimelineChanged(Timeline timeline, Object obj, int i) {
            }

            public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                TrackGroupArray trackGroupArray2 = trackGroupArray;
                String str = "ERROR";
                String str2 = str;
                String str3 = str2;
                String str4 = str3;
                String str5 = str4;
                for (int i = 0; i < trackGroupArray2.length; i++) {
                    TrackGroup trackGroup = trackGroupArray2.get(i);
                    for (int i2 = 0; i2 < trackGroup.length; i2++) {
                        Metadata metadata = trackGroup.getFormat(i2).metadata;
                        if (metadata != null) {
                            for (int i3 = 0; i3 < metadata.length(); i3++) {
                                TextInformationFrame textInformationFrame = metadata.get(i3);
                                if (textInformationFrame instanceof TextInformationFrame) {
                                    TextInformationFrame textInformationFrame2 = textInformationFrame;
                                    String str6 = textInformationFrame2.id;
                                    if (str6 != null && str6.equals("TALB")) {
                                        str3 = textInformationFrame2.value;
                                    }
                                    if (str6 != null && str6.equals("TIT2")) {
                                        str2 = textInformationFrame2.value;
                                    }
                                    if (str6 != null && str6.equals("TPE1")) {
                                        str = textInformationFrame2.value;
                                    }
                                    if (str6 != null && str6.equals("TPE2")) {
                                        str4 = textInformationFrame2.value;
                                    }
                                    if (str6 != null && str6.equals("TRCK")) {
                                        str5 = textInformationFrame2.value;
                                    }
                                    MakeroidExoPlayer.this.GotMetaData(str, str2, str3, str4, str5);
                                }
                            }
                        }
                    }
                }
            }

            public final void onLoadingChanged(boolean z) {
                MakeroidExoPlayer makeroidExoPlayer = MakeroidExoPlayer.this;
                makeroidExoPlayer.StatusChanged(MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(makeroidExoPlayer), MakeroidExoPlayer.this.isPause, MakeroidExoPlayer.this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5, z);
            }

            public final void onPlayerStateChanged(boolean z, int i) {
                if (i == 4) {
                    try {
                        if (!MakeroidExoPlayer.this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl) {
                            boolean unused = MakeroidExoPlayer.this.isPlaying = false;
                            boolean unused2 = MakeroidExoPlayer.this.isPause = false;
                            boolean unused3 = MakeroidExoPlayer.this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = true;
                        }
                        MakeroidExoPlayer.this.Completed();
                    } catch (Exception e) {
                        Log.e("ExoPlayer", String.valueOf(e));
                    }
                }
            }

            public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
                MakeroidExoPlayer makeroidExoPlayer = MakeroidExoPlayer.this;
                makeroidExoPlayer.OnPlayerError(exoPlaybackException.getCause().getMessage());
                Log.e("ExoPlayer", exoPlaybackException.getCause().getMessage());
            }
        });
        SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
        }
        Log.d("ExoPlayer", "ExoPlayer Created");
    }

    public void onAudioFocusChange(int i) {
        if (i == -3 || i == -2) {
            Pause();
        } else if (i == -1) {
            OtherPlayerStarted();
        } else if (i == 1) {
            if (this.isPlaying || this.isPause) {
                OtherPlayerStopped();
            }
        }
    }

    @SimpleEvent(description = "This event is signaled when another player has started (and the current player is playing or paused, but not stopped).")
    public void OtherPlayerStarted() {
        EventDispatcher.dispatchEvent(this, "OtherPlayerStarted", new Object[0]);
    }

    @SimpleEvent(description = "This event is signaled when another player has stopped (and the current player is playing or paused, but not stopped).")
    public void OtherPlayerStopped() {
        EventDispatcher.dispatchEvent(this, "OtherPlayerStopped", new Object[0]);
    }

    @SimpleFunction(description = "Stop the player.")
    public void Stop() {
        try {
            SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (simpleExoPlayer == null) {
                return;
            }
            if (this.isPlaying || this.isPause) {
                simpleExoPlayer.setPlayWhenReady(false);
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(0);
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = true;
                this.isPlaying = false;
                this.isPause = false;
                StatusChanged(false, false, true, false);
            }
        } catch (Exception e) {
            Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Pause the player.")
    public void Pause() {
        try {
            SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (simpleExoPlayer != null && this.isPlaying) {
                simpleExoPlayer.setPlayWhenReady(false);
                this.isPause = true;
                this.isPlaying = false;
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = false;
                StatusChanged(false, true, false, this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading());
            }
        } catch (Exception e) {
            Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Start the player.")
    public void Start() {
        try {
            SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (simpleExoPlayer != null) {
                if (simpleExoPlayer.getPlaybackState() == 4) {
                    this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo(0);
                }
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPlayWhenReady(true);
                this.isPlaying = true;
                this.isPause = false;
                this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5 = false;
                StatusChanged(true, false, false, this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading());
                Volume(this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT);
                Loop(this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl);
            }
        } catch (Exception e) {
            Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Resume the player.")
    public void Resume() {
        try {
            if (this.isPlaying) {
                Pause();
            } else {
                Start();
            }
        } catch (Exception e) {
            Log.e("ExoPlayer", String.valueOf(e));
        }
    }

    @SimpleEvent(description = "This event returns true or false for the respective simpleExoPlayer statuses.")
    public void StatusChanged(boolean z, boolean z2, boolean z3, boolean z4) {
        EventDispatcher.dispatchEvent(this, "StatusChanged", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z4));
    }

    @SimpleEvent(description = "This event returns meta data from the audio stream. Works for files but not for streams as example radio streams.")
    public void GotMetaData(String str, String str2, String str3, String str4, String str5) {
        EventDispatcher.dispatchEvent(this, "GotMetaData", str, str2, str3, str4, str5);
    }

    @SimpleEvent(description = "This event returns the error reason for any problems.")
    public void OnPlayerError(String str) {
        EventDispatcher.dispatchEvent(this, "OnPlayerError", str);
    }

    @SimpleEvent(description = "This event is invoked if the player state is completed.")
    public void Completed() {
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
    @SimpleProperty(description = "Sets the volume to a number between 0 and 100")
    public void Volume(int i) {
        if (i > 100 || i < 0) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Volume", ErrorMessages.ERROR_PLAYER_INVALID_VOLUME, Integer.valueOf(i));
            return;
        }
        if (this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVolume((float) (1.0d - (Math.log((double) (100 - i)) / Math.log(100.0d))));
        }
        this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = i;
    }

    @SimpleProperty(description = "Return the player volume.")
    public float Volume() {
        return (float) this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT;
    }

    @SimpleFunction(description = "Returns true if the player is current playing.")
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @SimpleFunction(description = "Returns true if the player is current in pause mode.")
    public boolean isPause() {
        return this.isPause;
    }

    @SimpleFunction(description = "Returns true if the player is current stopped.")
    public boolean isStopped() {
        return this.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5;
    }

    @SimpleFunction(description = "Returns true if the player is current loading.")
    public boolean isLoading() {
        return this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLoading();
    }

    @SimpleProperty(description = "Returns the duration of the source file.")
    public int Duration() {
        SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (simpleExoPlayer != null) {
            return (int) simpleExoPlayer.getDuration();
        }
        return 0;
    }

    @SimpleProperty(description = "Returns the current position of the source file that is playing in milliseconds.")
    public int CurrentPosition() {
        SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (simpleExoPlayer != null) {
            return (int) simpleExoPlayer.getCurrentPosition();
        }
        return 0;
    }

    @SimpleFunction(description = "Set a position where the source file should start playing in milliseconds.")
    public void SeekTo(int i) {
        SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (simpleExoPlayer != null) {
            int currentPosition = (int) simpleExoPlayer.getCurrentPosition();
            int i2 = i + currentPosition;
            if (i2 <= ((int) this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDuration())) {
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo((long) i2);
            } else {
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.seekTo((long) currentPosition);
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty(description = "Set the path to the audio source. Can be a asset file, from external card, or from a online stream.")
    public void Source(String str) {
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = str;
        boolean z = false;
        if (str.isEmpty()) {
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, str);
        } else if (this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            String str2 = this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
            if (str2.startsWith("http") || !str2.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                z = true;
            }
            if (z) {
                SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Context context2 = this.context;
                ExtractorMediaSource.Factory extractorsFactory = new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context2, Util.getUserAgent(context2, this.d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq), new DefaultBandwidthMeter())).setExtractorsFactory(new DefaultExtractorsFactory());
                String str3 = this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
                if (!str3.startsWith("http") && !str3.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    str3 = this.isCompanion ? "file:///mnt/sdcard/Kodular/assets/".concat(String.valueOf(str3)) : Form.ASSETS_PREFIX.concat(String.valueOf(str3));
                }
                simpleExoPlayer.prepare(extractorsFactory.createMediaSource(Uri.parse(str3)));
                return;
            }
            String str4 = this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
            StringBuilder sb = new StringBuilder();
            sb.append(this.context.getExternalFilesDir(""));
            if (str4.contains(sb.toString())) {
                try {
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(new File(this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa));
                } catch (Exception e) {
                    Log.e("ExoPlayer", String.valueOf(e));
                }
            } else {
                this.form.runOnUiThread(new Runnable() {
                    public final void run() {
                        MakeroidExoPlayer.this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                            public final void HandlePermissionResponse(String str, boolean z) {
                                if (z) {
                                    try {
                                        if (MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this).startsWith("file:///")) {
                                            String unused = MakeroidExoPlayer.this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this).substring(7);
                                        }
                                        if (MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this).startsWith("/storage/emulated/0/") || MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this).startsWith("/storage/emulated/1/")) {
                                            String unused2 = MakeroidExoPlayer.this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this).substring(19);
                                        }
                                        MakeroidExoPlayer.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(new File(QUtil.getExternalStoragePath(MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this)) + MakeroidExoPlayer.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidExoPlayer.this)));
                                    } catch (Exception e) {
                                        Log.e("ExoPlayer", String.valueOf(e));
                                    }
                                } else {
                                    MakeroidExoPlayer.this.form.dispatchPermissionDeniedEvent((Component) MakeroidExoPlayer.this, "Source", "android.permission.READ_EXTERNAL_STORAGE");
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    @SimpleProperty
    public String Source() {
        return this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
    }

    /* access modifiers changed from: private */
    public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file) {
        Log.i("ExoPlayer", "ExoPlayer source path: " + this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa);
        if (file.exists()) {
            Context context2 = this.context;
            Uri uriForFile = FileProvider.getUriForFile(context2, this.context.getPackageName() + ".provider", file);
            SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            Context context3 = this.context;
            simpleExoPlayer.prepare(new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context3, Util.getUserAgent(context3, this.d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq), new DefaultBandwidthMeter())).setExtractorsFactory(new DefaultExtractorsFactory()).createMediaSource(uriForFile));
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_CANNOT_FIND_FILE, this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Loop(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (simpleExoPlayer != null) {
            if (z) {
                simpleExoPlayer.setRepeatMode(1);
            } else {
                simpleExoPlayer.setRepeatMode(0);
            }
        }
        this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = z;
    }

    @SimpleProperty(description = "If true, the player will loop when it plays.")
    public boolean Loop() {
        return this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    }

    private String AppName() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getApplicationInfo(this.context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getApplicationLabel(applicationInfo) : "not found");
    }

    public void onDestroy() {
        try {
            SimpleExoPlayer simpleExoPlayer = this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (simpleExoPlayer != null) {
                simpleExoPlayer.stop();
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                this.f205hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
        } catch (Exception e) {
            Log.e("ExoPlayer", String.valueOf(e));
        }
    }
}
