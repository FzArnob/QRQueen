package com.google.zxing.client.android.common;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class PlatformSupportManager<T> {
    private static final String TAG = "PlatformSupportManager";
    private final T defaultImplementation;
    private final SortedMap<Integer, String> implementations;
    private final Class<T> managedInterface;

    protected PlatformSupportManager(Class<T> cls, T t) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException();
        } else if (cls.isInstance(t)) {
            this.managedInterface = cls;
            this.defaultImplementation = t;
            this.implementations = new TreeMap(Collections.reverseOrder());
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public void addImplementationClass(int i, String str) {
        this.implementations.put(Integer.valueOf(i), str);
    }

    public T build() {
        for (Integer next : this.implementations.keySet()) {
            if (Build.VERSION.SDK_INT >= next.intValue()) {
                try {
                    Class<? extends U> asSubclass = Class.forName((String) this.implementations.get(next)).asSubclass(this.managedInterface);
                    String str = TAG;
                    Log.i(str, "Using implementation " + asSubclass + " of " + this.managedInterface + " for SDK " + next);
                    return asSubclass.getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (ClassNotFoundException e) {
                    Log.w(TAG, e);
                } catch (IllegalAccessException e2) {
                    Log.w(TAG, e2);
                } catch (InstantiationException e3) {
                    Log.w(TAG, e3);
                } catch (NoSuchMethodException e4) {
                    Log.w(TAG, e4);
                } catch (InvocationTargetException e5) {
                    Log.w(TAG, e5);
                }
            }
        }
        String str2 = TAG;
        Log.i(str2, "Using default implementation " + this.defaultImplementation.getClass() + " of " + this.managedInterface);
        return this.defaultImplementation;
    }
}
