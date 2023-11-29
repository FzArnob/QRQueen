package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public final class LinearLayout implements Layout {
    private final android.widget.LinearLayout B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    LinearLayout(Context context) {
        this(context, 1, (Integer) null, (Integer) null);
    }

    LinearLayout(Context context, int i, final Integer num, final Integer num2) {
        if ((num != null || num2 == null) && (num == null || num2 != null)) {
            AnonymousClass1 r0 = new android.widget.LinearLayout(context) {
                /* access modifiers changed from: protected */
                public final void onMeasure(int i, int i2) {
                    if (num == null || num2 == null) {
                        super.onMeasure(i, i2);
                    } else if (getChildCount() != 0) {
                        super.onMeasure(i, i2);
                    } else {
                        setMeasuredDimension(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, num.intValue()), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2, num2.intValue()));
                    }
                }

                private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, int i2) {
                    int mode = View.MeasureSpec.getMode(i);
                    int size = View.MeasureSpec.getSize(i);
                    if (mode == 1073741824) {
                        return size;
                    }
                    return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
                }
            };
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r0;
            r0.setOrientation(i == 0 ? 0 : 1);
            return;
        }
        throw new IllegalArgumentException("LinearLayout - preferredEmptyWidth and preferredEmptyHeight must be either both null or both not null");
    }

    public final ViewGroup getLayoutManager() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public final void add(AndroidViewComponent androidViewComponent) {
        Log.i("LinearLayout", "I am adding: " + androidViewComponent.getView().toString());
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.addView(androidViewComponent.getView(), new LinearLayout.LayoutParams(-2, -2, 0.0f));
    }

    public final void setHorizontalGravity(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setHorizontalGravity(i);
    }

    public final void setVerticalGravity(int i) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVerticalGravity(i);
    }

    public final void setBaselineAligned(boolean z) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBaselineAligned(z);
    }
}
