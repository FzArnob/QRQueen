package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLeakUtil {
    private static final Map<String, WeakReference<Object>> ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = Maps.newTreeMap();
    private static final AtomicInteger hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new AtomicInteger(0);

    private MemoryLeakUtil() {
    }

    public static String trackObject(String str, Object obj) {
        String str2;
        if (str == null) {
            str2 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.incrementAndGet() + "_";
        } else {
            str2 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.incrementAndGet() + "_" + str;
        }
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud.put(str2, new WeakReference(obj));
        return str2;
    }

    public static boolean isTrackedObjectCollected(String str, boolean z) {
        System.gc();
        Map<String, WeakReference<Object>> map = ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
        WeakReference weakReference = map.get(str);
        if (weakReference != null) {
            Object obj = weakReference.get();
            String substring = str.substring(str.indexOf("_") + 1);
            StringBuilder sb = new StringBuilder("Object with tag ");
            sb.append(substring);
            sb.append(" has ");
            sb.append(obj != null ? "not " : "");
            sb.append("been garbage collected.");
            Log.i("MemoryLeakUtil", sb.toString());
            if (z && obj == null) {
                map.remove(str);
            }
            if (obj == null) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("key not found");
    }

    public static void checkAllTrackedObjects(boolean z, boolean z2) {
        Log.i("MemoryLeakUtil", "Checking Tracked Objects ----------------------------------------");
        System.gc();
        Iterator<Map.Entry<String, WeakReference<Object>>> it = ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud.entrySet().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            String str = (String) next.getKey();
            Object obj = ((WeakReference) next.getValue()).get();
            if (obj != null) {
                i2++;
            } else {
                i++;
                if (z2) {
                    it.remove();
                }
            }
            if (z) {
                String substring = str.substring(str.indexOf("_") + 1);
                StringBuilder sb = new StringBuilder("Object with tag ");
                sb.append(substring);
                sb.append(" has ");
                sb.append(obj != null ? "not " : "");
                sb.append("been garbage collected.");
                Log.i("MemoryLeakUtil", sb.toString());
            }
        }
        Log.i("MemoryLeakUtil", "summary: collected ".concat(String.valueOf(i)));
        Log.i("MemoryLeakUtil", "summary: remaining ".concat(String.valueOf(i2)));
        Log.i("MemoryLeakUtil", "-----------------------------------------------------------------");
    }
}
