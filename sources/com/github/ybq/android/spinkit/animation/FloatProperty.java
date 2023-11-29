package com.github.ybq.android.spinkit.animation;

import android.util.Property;

public abstract class FloatProperty<T> extends Property<T, Float> {
    public abstract void setValue(T t, float f);

    public FloatProperty(String str) {
        super(Float.class, str);
    }

    public final void set(T t, Float f) {
        setValue(t, f.floatValue());
    }
}
