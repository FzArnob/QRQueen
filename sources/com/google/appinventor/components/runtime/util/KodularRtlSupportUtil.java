package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toolbar;

public class KodularRtlSupportUtil {
    private static Context B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    private KodularRtlSupportUtil() {
    }

    public static void setContext(Context context) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = context;
    }

    public static void setSupportPadding(View view, int i, int i2, int i3, int i4) {
        if (view != null) {
            view.setPaddingRelative(i, i2, i3, i4);
            view.invalidate();
        }
    }

    public static void setSupportMargin(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        if (marginLayoutParams != null) {
            marginLayoutParams.setMargins(i, i2, i3, i4);
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(LinearLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(FrameLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(GridLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(RelativeLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(TableLayout.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(TableRow.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportMargin(Toolbar.LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        if (layoutParams != null) {
            layoutParams.setMargins(i, i2, i3, i4);
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
    }

    public static void setSupportTextDirection(TextView textView) {
        if (textView != null && textView.getTextDirection() != 2 && isLayoutDirectionRTL()) {
            textView.setTextDirection(2);
            textView.invalidate();
        }
    }

    public static void setSupportAutoMirrored(Drawable drawable) {
        if (drawable != null) {
            drawable.setAutoMirrored(true);
        }
    }

    public static boolean isLayoutDirectionRTL() {
        Context context = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (context == null || context.getResources().getConfiguration().getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }
}
