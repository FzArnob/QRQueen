package com.google.appinventor.components.common;

import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.util.HashMap;
import java.util.Map;

public enum NxtSensorMode implements OptionList<Integer> {
    Raw(0),
    Boolean(32),
    TransitionCount(96),
    PeriodCount(96),
    Percentage(128),
    RcxCelsius(ComponentConstants.TEXTBOX_PREFERRED_WIDTH),
    RcxFahrenheit(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE),
    RcxAngleSteps(224);
    
    private static final Map<Integer, NxtSensorMode> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        int i;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (NxtSensorMode nxtSensorMode : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(nxtSensorMode.toUnderlyingValue(), nxtSensorMode);
        }
    }

    private NxtSensorMode(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = i;
    }

    public final Integer toUnderlyingValue() {
        return Integer.valueOf(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    public static NxtSensorMode fromUnderlyingValue(Integer num) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(num);
    }
}
