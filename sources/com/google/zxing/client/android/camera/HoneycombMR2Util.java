package com.google.zxing.client.android.camera;

import android.graphics.Point;
import android.view.Display;

public class HoneycombMR2Util {
    private HoneycombMR2Util() {
    }

    public static void getSize(Display display, Point point) {
        display.getSize(point);
    }
}
