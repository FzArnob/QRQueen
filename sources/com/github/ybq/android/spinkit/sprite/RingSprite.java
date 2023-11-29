package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RingSprite extends ShapeSprite {
    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            paint.setStyle(Paint.Style.STROKE);
            int min = Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2;
            paint.setStrokeWidth((float) (min / 12));
            canvas.drawCircle((float) getDrawBounds().centerX(), (float) getDrawBounds().centerY(), (float) min, paint);
        }
    }
}
