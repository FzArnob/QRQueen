package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum NxtMotorMode implements OptionList<Integer> {
    On(1),
    Brake(2),
    Regulated(4),
    Coast(0);
    
    private static final Map<Integer, NxtMotorMode> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        int i;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (NxtMotorMode nxtMotorMode : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(nxtMotorMode.toUnderlyingValue(), nxtMotorMode);
        }
    }

    private NxtMotorMode(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = i;
    }

    public final Integer toUnderlyingValue() {
        return Integer.valueOf(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    public static NxtMotorMode fromUnderlyingValue(Integer num) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(num);
    }
}
