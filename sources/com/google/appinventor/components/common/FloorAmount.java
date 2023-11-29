package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum FloorAmount implements OptionList<String> {
    Optimized("optimized"),
    USD_04_50("d_04_c_50"),
    USD_04_00("d_04_c_00"),
    USD_03_50("d_03_c_50"),
    USD_03_00("d_03_c_00"),
    USD_02_50("d_02_c_50"),
    USD_02_00("d_02_c_00"),
    USD_01_50("d_01_c_50"),
    USD_01_00("d_01_c_00"),
    USD_00_80("d_00_c_80"),
    USD_00_50("d_00_c_50");
    
    private static final Map<String, FloorAmount> wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
    private final String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        int i;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new HashMap();
        for (FloorAmount floorAmount : values()) {
            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(floorAmount.toUnderlyingValue(), floorAmount);
        }
    }

    private FloorAmount(String str) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str;
    }

    public static FloorAmount fromUnderlyingValue(String str) {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(str);
    }

    public final String toUnderlyingValue() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }
}
