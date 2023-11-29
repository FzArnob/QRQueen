package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularAdsUtil {
    public static final String ADMOB_BANNER_TEST_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final String ADMOB_INTERSTITIAL_TEST_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String ADMOB_REWARDED_VIDEO_TEST_ID = "ca-app-pub-3940256099942544/5224354917";
    public static final String AD_COLONY_APP_ID = "appb235d39ba30f401783";
    public static final String AD_COLONY_ZONE_ID = "vzc8fd72c1e5494edca6";
    public static final String AD_MANAGER_BANNER_TEST_ID = "/6499/example/banner";
    public static final String AD_MANAGER_INTERSTITIAL_TEST_ID = "/6499/example/interstitial";
    public static final String AD_MANAGER_REWARDED_TEST_ID = "/6499/example/rewarded";
    private static String LOG_TAG = "Kodular Ads Util";
    public static final String STARTAPP_APP_ID = "204085028";
    public static final String UNITY_ADS_GAME_ID = "3140736";

    private KodularAdsUtil() {
    }

    private static String sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & Ev3Constants.Opcode.TST) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String guessSelfDeviceId(Context context) {
        String upperCase = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(Settings.Secure.getString(context.getContentResolver(), "android_id")).toUpperCase();
        Log.i(LOG_TAG, "Device id: ".concat(String.valueOf(upperCase)));
        return upperCase;
    }
}
