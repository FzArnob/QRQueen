package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum NxtSensorType implements OptionList<Integer> {
    NoSensor(0),
    Touch(1),
    LightOn(5),
    LightOff(6),
    SoundDB(7),
    SoundDBA(8),
    ColorFull(13),
    ColorRed(14),
    ColorGreen(15),
    ColorBlue(16),
    ColorNone(17),
    Digital12C(10),
    Digital12C9V(11),
    RcxTemperature(2),
    RcxLight(3),
    RcxAngle(4);
    
    private static final Map<Integer, NxtSensorType> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (NxtSensorType nxtSensorType : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(nxtSensorType.toUnderlyingValue(), nxtSensorType);
        }
    }

    private NxtSensorType(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = i;
    }

    public final Integer toUnderlyingValue() {
        return Integer.valueOf(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    public static NxtSensorType fromUnderlyingValue(Integer num) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(num);
    }
}
