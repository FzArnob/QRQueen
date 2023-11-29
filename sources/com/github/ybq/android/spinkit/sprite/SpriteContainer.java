package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.AnimationUtils;

public abstract class SpriteContainer extends Sprite {
    private int color;
    private Sprite[] sprites = onCreateChild();

    /* access modifiers changed from: protected */
    public void drawSelf(Canvas canvas) {
    }

    public void onChildCreated(Sprite... spriteArr) {
    }

    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public abstract Sprite[] onCreateChild();

    public SpriteContainer() {
        initCallBack();
        onChildCreated(this.sprites);
    }

    private void initCallBack() {
        Sprite[] spriteArr = this.sprites;
        if (spriteArr != null) {
            for (Sprite callback : spriteArr) {
                callback.setCallback(this);
            }
        }
    }

    public int getChildCount() {
        Sprite[] spriteArr = this.sprites;
        if (spriteArr == null) {
            return 0;
        }
        return spriteArr.length;
    }

    public Sprite getChildAt(int i) {
        Sprite[] spriteArr = this.sprites;
        if (spriteArr == null) {
            return null;
        }
        return spriteArr[i];
    }

    public void setColor(int i) {
        this.color = i;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            getChildAt(i2).setColor(i);
        }
    }

    public int getColor() {
        return this.color;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawChild(canvas);
    }

    public void drawChild(Canvas canvas) {
        Sprite[] spriteArr = this.sprites;
        if (spriteArr != null) {
            for (Sprite draw : spriteArr) {
                int save = canvas.save();
                draw.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        for (Sprite bounds : this.sprites) {
            bounds.setBounds(rect);
        }
    }

    public void start() {
        super.start();
        AnimationUtils.start(this.sprites);
    }

    public void stop() {
        super.stop();
        AnimationUtils.stop(this.sprites);
    }

    public boolean isRunning() {
        return AnimationUtils.isRunning(this.sprites) || super.isRunning();
    }
}
