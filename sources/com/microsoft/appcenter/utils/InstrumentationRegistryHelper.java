package com.microsoft.appcenter.utils;

import android.os.Bundle;

public class InstrumentationRegistryHelper {
    private static final String[] LOCATIONS = {"androidx.test.platform.app.InstrumentationRegistry", "androidx.test.InstrumentationRegistry"};

    public static Bundle getArguments() throws IllegalStateException {
        String[] strArr = LOCATIONS;
        Exception e = null;
        int i = 0;
        while (i < strArr.length) {
            try {
                return (Bundle) getClass(strArr[i]).getMethod("getArguments", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception e2) {
                e = e2;
                i++;
            }
        }
        throw new IllegalStateException(e);
    }

    private static Class<?> getClass(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
