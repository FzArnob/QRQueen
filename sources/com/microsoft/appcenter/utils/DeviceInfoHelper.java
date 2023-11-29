package com.microsoft.appcenter.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.WrapperSdk;
import java.util.Locale;
import java.util.TimeZone;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

public class DeviceInfoHelper {
    private static final String OS_NAME = "Android";
    private static String mCountryCode;
    private static WrapperSdk sWrapperSdk;

    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            AppCenterLog.error("AppCenter", "Cannot retrieve package info", e);
            return null;
        }
    }

    public static synchronized Device getDeviceInfo(Context context) throws DeviceInfoException {
        Device device;
        synchronized (DeviceInfoHelper.class) {
            device = new Device();
            PackageInfo packageInfo = getPackageInfo(context);
            if (packageInfo != null) {
                device.setAppVersion(packageInfo.versionName);
                device.setAppBuild(String.valueOf(getVersionCode(packageInfo)));
                device.setAppNamespace(context.getPackageName());
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    String networkCountryIso = telephonyManager.getNetworkCountryIso();
                    if (!TextUtils.isEmpty(networkCountryIso)) {
                        device.setCarrierCountry(networkCountryIso);
                    }
                    String networkOperatorName = telephonyManager.getNetworkOperatorName();
                    if (!TextUtils.isEmpty(networkOperatorName)) {
                        device.setCarrierName(networkOperatorName);
                    }
                } catch (Exception e) {
                    AppCenterLog.error("AppCenter", "Cannot retrieve carrier info", e);
                }
                String str = mCountryCode;
                if (str != null) {
                    device.setCarrierCountry(str);
                }
                device.setLocale(Locale.getDefault().toString());
                device.setModel(Build.MODEL);
                device.setOemName(Build.MANUFACTURER);
                device.setOsApiLevel(Integer.valueOf(Build.VERSION.SDK_INT));
                device.setOsName(OS_NAME);
                device.setOsVersion(Build.VERSION.RELEASE);
                device.setOsBuild(Build.ID);
                try {
                    device.setScreenSize(getScreenSize(context));
                } catch (Exception e2) {
                    AppCenterLog.error("AppCenter", "Cannot retrieve screen size", e2);
                }
                device.setSdkName("appcenter.android");
                device.setSdkVersion("5.0.0");
                device.setTimeZoneOffset(Integer.valueOf((TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 60) / 1000));
                WrapperSdk wrapperSdk = sWrapperSdk;
                if (wrapperSdk != null) {
                    device.setWrapperSdkVersion(wrapperSdk.getWrapperSdkVersion());
                    device.setWrapperSdkName(sWrapperSdk.getWrapperSdkName());
                    device.setWrapperRuntimeVersion(sWrapperSdk.getWrapperRuntimeVersion());
                    device.setLiveUpdateReleaseLabel(sWrapperSdk.getLiveUpdateReleaseLabel());
                    device.setLiveUpdateDeploymentKey(sWrapperSdk.getLiveUpdateDeploymentKey());
                    device.setLiveUpdatePackageHash(sWrapperSdk.getLiveUpdatePackageHash());
                }
            } else {
                throw new DeviceInfoException("Cannot retrieve package info");
            }
        }
        return device;
    }

    public static int getVersionCode(PackageInfo packageInfo) {
        return packageInfo.versionCode;
    }

    private static String getScreenSize(Context context) {
        int i;
        int i2;
        Point point = new Point();
        Display display = ((DisplayManager) context.getSystemService("display")).getDisplay(0);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        point.x = displayMetrics.widthPixels;
        point.y = displayMetrics.heightPixels;
        int rotation = display.getRotation();
        if (rotation == 1 || rotation == 3) {
            int i3 = point.x;
            int i4 = point.y;
            i2 = i3;
            i = i4;
        } else {
            i = point.x;
            i2 = point.y;
        }
        return i + EllipticCurveJsonWebKey.X_MEMBER_NAME + i2;
    }

    public static synchronized void setWrapperSdk(WrapperSdk wrapperSdk) {
        synchronized (DeviceInfoHelper.class) {
            sWrapperSdk = wrapperSdk;
        }
    }

    public static void setCountryCode(String str) {
        if (str == null || str.length() == 2) {
            mCountryCode = str;
            AppCenterLog.debug("AppCenter", String.format("Set country code: %s", new Object[]{str}));
            return;
        }
        AppCenterLog.error("AppCenter", "App Center accepts only the two-letter ISO country code.");
    }

    public static class DeviceInfoException extends Exception {
        public DeviceInfoException(String str) {
            super(str);
        }
    }
}
