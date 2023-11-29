package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CircleSprite extends ShapeSprite {
    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            canvas.drawCircle((float) getDrawBounds().centerX(), (float) getDrawBounds().centerY(), (float) (Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2), paint);
        }
    }
}
