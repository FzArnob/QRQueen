package com.google.appinventor.components.runtime.view;

import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.util.ViewUtil;
import org.osmdroid.views.MapView;
import org.slf4j.Marker;

public class ZoomControlView extends LinearLayout {
    /* access modifiers changed from: private */
    public final MapView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private float ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ;
    private final Button vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private final Button wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZoomControlView(MapView mapView) {
        super(mapView.getContext());
        this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ = mapView.getContext().getResources().getDisplayMetrics().density;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = mapView;
        setOrientation(1);
        Button button = new Button(mapView.getContext());
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = button;
        Button button2 = new Button(mapView.getContext());
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = button2;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(button, Marker.ANY_NON_NULL_MARKER);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(button2, "−");
        button.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ZoomControlView.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getController().zoomIn();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ZoomControlView.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getController().zoomOut();
            }
        });
        float f = (float) ((int) (this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ * 4.0f));
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f}, (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setColor(-1);
        ViewUtil.setBackgroundDrawable(button, shapeDrawable);
        float f2 = (float) ((int) (this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ * 4.0f));
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f2, f2, f2, f2}, (RectF) null, (float[]) null));
        shapeDrawable2.getPaint().setColor(-1);
        ViewUtil.setBackgroundDrawable(button2, shapeDrawable2);
        int[][] iArr = {new int[]{-16842910}, new int[]{16842910}};
        int[] iArr2 = {Component.COLOR_LIGHT_GRAY, -16777216};
        button.setTextColor(new ColorStateList(iArr, iArr2));
        button2.setTextColor(new ColorStateList(iArr, iArr2));
        addView(button);
        addView(button2);
        float f3 = this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ;
        setPadding((int) (f3 * 12.0f), (int) (f3 * 12.0f), 0, 0);
        updateButtons();
    }

    public final void updateButtons() {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setEnabled(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.canZoomIn());
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setEnabled(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.canZoomOut());
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Button button, String str) {
        button.setText(str);
        button.setTextSize(22.0f);
        button.setPadding(0, 0, 0, 0);
        button.setWidth((int) (this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ * 30.0f));
        button.setHeight((int) (this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ * 30.0f));
        button.setSingleLine();
        button.setGravity(17);
    }
}
