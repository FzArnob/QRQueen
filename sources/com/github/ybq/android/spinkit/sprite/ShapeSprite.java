package com.github.ybq.android.spinkit.sprite;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

public abstract class ShapeSprite extends Sprite {
    private int mBaseColor;
    private Paint mPaint;
    private int mUseColor;

    public abstract void drawShape(Canvas canvas, Paint paint);

    public ShapeSprite() {
        setColor(-1);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(this.mUseColor);
    }

    public void setColor(int i) {
        this.mBaseColor = i;
        updateUseColor();
    }

    public int getColor() {
        return this.mBaseColor;
    }

    public int getUseColor() {
        return this.mUseColor;
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        updateUseColor();
    }

    private void updateUseColor() {
        int alpha = getAlpha();
        int i = this.mBaseColor;
        this.mUseColor = ((((i >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24) | ((i << 8) >>> 8);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: protected */
    public final void drawSelf(Canvas canvas) {
        this.mPaint.setColor(this.mUseColor);
        drawShape(canvas, this.mPaint);
    }
}
