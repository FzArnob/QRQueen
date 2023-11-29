package com.google.appinventor.components.runtime.util;

public class OrientationSensorUtil {
    private OrientationSensorUtil() {
    }

    private static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(float f) {
        float f2 = f % 360.0f;
        return (f2 == 0.0f || Math.signum(f) == Math.signum(360.0f)) ? f2 : f2 + 360.0f;
    }

    public static float normalizeAzimuth(float f) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(f);
    }

    public static float normalizePitch(float f) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(f + 180.0f) - 180.0f;
    }

    public static float normalizeRoll(float f) {
        float max = Math.max(Math.min(f, 180.0f), -180.0f);
        if (max >= -90.0f && max <= 90.0f) {
            return max;
        }
        float f2 = 180.0f - max;
        return f2 >= 270.0f ? f2 - 360.0f : f2;
    }
}
