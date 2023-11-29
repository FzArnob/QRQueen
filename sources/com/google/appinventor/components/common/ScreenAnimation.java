package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;
import org.jose4j.jws.AlgorithmIdentifiers;

public enum ScreenAnimation implements OptionList<String> {
    Default("default"),
    Fade("fade"),
    Zoom("zoom"),
    SlideHorizontal("slidehorizontal"),
    SlideVertical("slidevertical"),
    None(AlgorithmIdentifiers.NONE);
    
    private static final Map<String, ScreenAnimation> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        int i;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (ScreenAnimation screenAnimation : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(screenAnimation.toUnderlyingValue(), screenAnimation);
        }
    }

    private ScreenAnimation(String str) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str;
    }

    public final String toUnderlyingValue() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public static ScreenAnimation fromUnderlyingValue(String str) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str);
    }
}
