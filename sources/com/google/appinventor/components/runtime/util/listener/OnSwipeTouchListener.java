package com.google.appinventor.components.runtime.util.listener;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private final String LOG_TAG = "OnSwipeTouchListener";

    public void onClick() {
    }

    public void onDoubleClick() {
    }

    public void onLongClick() {
    }

    public void onSwipeBottom() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeTop() {
    }

    public OnSwipeTouchListener(Context context) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new GestureDetector(context, new a(this, (byte) 0));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.onTouchEvent(motionEvent);
    }

    final class a extends GestureDetector.SimpleOnGestureListener {
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private a() {
        }

        /* synthetic */ a(OnSwipeTouchListener onSwipeTouchListener, byte b) {
            this();
        }

        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onClick();
            return super.onSingleTapUp(motionEvent);
        }

        public final boolean onDoubleTap(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onDoubleClick();
            return super.onDoubleTap(motionEvent);
        }

        public final void onLongPress(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onLongClick();
            super.onLongPress(motionEvent);
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                float y = motionEvent2.getY() - motionEvent.getY();
                float x = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(x) > Math.abs(y)) {
                    if (Math.abs(x) > 100.0f && Math.abs(f) > 100.0f) {
                        if (x > 0.0f) {
                            OnSwipeTouchListener.this.onSwipeRight();
                            return true;
                        }
                        OnSwipeTouchListener.this.onSwipeLeft();
                        return true;
                    }
                } else if (Math.abs(y) > 100.0f && Math.abs(f2) > 100.0f) {
                    if (y > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeBottom();
                        return true;
                    }
                    OnSwipeTouchListener.this.onSwipeTop();
                    return true;
                }
            } catch (Exception e) {
                Log.d("OnSwipeTouchListener", e.getMessage());
            }
            return false;
        }
    }
}
