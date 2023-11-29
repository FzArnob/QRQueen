package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum ScreenOrientation implements OptionList<String> {
    Unspecified("unspecified", 4),
    Landscape("landscape", 0),
    Portrait("portrait", 1),
    Sensor("sensor", 4),
    User("user", 2),
    Behind("behind", 3),
    NoSensor("nosensor", 5),
    FullSensor("fullSensor", 10),
    ReverseLandscape("reverseLandscape", 8),
    ReversePortrait("reversePortrait", 9),
    SensorLandscape("sensorLandscape", 6),
    SensorPortrait("sensorPortrait", 7);
    
    private static final Map<String, ScreenOrientation> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    static {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (ScreenOrientation screenOrientation : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(screenOrientation.toUnderlyingValue().toLowerCase(), screenOrientation);
        }
    }

    private ScreenOrientation(String str, int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = i;
    }

    public final String toUnderlyingValue() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public final int getOrientationConstant() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    public static ScreenOrientation fromUnderlyingValue(String str) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str.toLowerCase());
    }
}
