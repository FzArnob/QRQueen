package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum FileScope implements OptionList<String> {
    App,
    Asset,
    Cache,
    Legacy,
    Private,
    Shared;
    
    private static final Map<String, FileScope> wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;

    static {
        int i;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new HashMap();
        for (FileScope fileScope : values()) {
            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(fileScope.toUnderlyingValue(), fileScope);
        }
    }

    public static FileScope fromUnderlyingValue(String str) {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(str);
    }

    public final String toUnderlyingValue() {
        return name();
    }
}
