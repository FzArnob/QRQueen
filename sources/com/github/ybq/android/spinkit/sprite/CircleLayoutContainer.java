package com.github.ybq.android.spinkit.sprite;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class CircleLayoutContainer extends SpriteContainer {
    public void drawChild(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            Sprite childAt = getChildAt(i);
            int save = canvas.save();
            canvas.rotate((float) ((i * 360) / getChildCount()), (float) getBounds().centerX(), (float) getBounds().centerY());
            childAt.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect clipSquare = clipSquare(rect);
        int width = (int) (((((double) clipSquare.width()) * 3.141592653589793d) / 3.5999999046325684d) / ((double) getChildCount()));
        int centerX = clipSquare.centerX() - width;
        int centerX2 = clipSquare.centerX() + width;
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setDrawBounds(centerX, clipSquare.top, centerX2, clipSquare.top + (width * 2));
        }
    }
}
