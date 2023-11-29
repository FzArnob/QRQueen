package com.google.appinventor.components.runtime.util;

import android.media.AudioManager;
import com.google.appinventor.components.runtime.Player;

public class FroyoUtil {
    private FroyoUtil() {
    }

    public static Object setAudioFocusChangeListener(final Player player) {
        return new AudioManager.OnAudioFocusChangeListener() {
            private boolean aWkZqinykMPj8SPm5jVT2wNdrJPpyMEjt10g4Ng570XD4n7VSaFhL3td3g0Xab2G = false;

            public final void onAudioFocusChange(int i) {
                Player player;
                if (i == -3 || i == -2) {
                    Player player2 = player;
                    if (player2 != null && player2.playerState == Player.State.PLAYING) {
                        player.pause();
                        this.aWkZqinykMPj8SPm5jVT2wNdrJPpyMEjt10g4Ng570XD4n7VSaFhL3td3g0Xab2G = true;
                    }
                } else if (i == -1) {
                    this.aWkZqinykMPj8SPm5jVT2wNdrJPpyMEjt10g4Ng570XD4n7VSaFhL3td3g0Xab2G = false;
                    player.OtherPlayerStarted();
                } else if (i == 1 && (player = player) != null && this.aWkZqinykMPj8SPm5jVT2wNdrJPpyMEjt10g4Ng570XD4n7VSaFhL3td3g0Xab2G && player.playerState == Player.State.PAUSED_BY_EVENT) {
                    player.Start();
                    this.aWkZqinykMPj8SPm5jVT2wNdrJPpyMEjt10g4Ng570XD4n7VSaFhL3td3g0Xab2G = false;
                }
            }
        };
    }

    public static boolean focusRequestGranted(AudioManager audioManager, Object obj) {
        return audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) obj, 3, 1) == 1;
    }

    public static void abandonFocus(AudioManager audioManager, Object obj) {
        audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) obj);
    }
}
