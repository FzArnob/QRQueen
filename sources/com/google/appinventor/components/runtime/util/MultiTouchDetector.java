package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.view.ScaleGestureDetector;
import com.google.appinventor.components.runtime.Canvas;

public class MultiTouchDetector {
    /* access modifiers changed from: private */
    public Canvas B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public class MyOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public MyOnScaleGestureListener() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            MultiTouchDetector.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Scale((double) scaleGestureDetector.getScaleFactor());
            return true;
        }
    }

    public class MyPinchZoomDetector extends ScaleGestureDetector implements Canvas.ExtensionGestureDetector {
        MyPinchZoomDetector(Context context, ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener) {
            super(context, onScaleGestureListener);
        }
    }

    public MultiTouchDetector(Canvas canvas) {
        if (canvas != null) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = canvas;
            canvas.registerCustomGestureDetector(new MyPinchZoomDetector(canvas.getContext(), new MyOnScaleGestureListener()));
        }
    }
}
