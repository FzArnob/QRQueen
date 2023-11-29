package com.github.ybq.android.spinkit.sprite;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.github.ybq.android.spinkit.animation.AnimationUtils;
import com.github.ybq.android.spinkit.animation.FloatProperty;
import com.github.ybq.android.spinkit.animation.IntProperty;

public abstract class Sprite extends Drawable implements ValueAnimator.AnimatorUpdateListener, Animatable, Drawable.Callback {
    public static final Property<Sprite, Integer> ALPHA = new IntProperty<Sprite>("alpha") {
        public void setValue(Sprite sprite, int i) {
            sprite.setAlpha(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getAlpha());
        }
    };
    public static final Property<Sprite, Integer> ROTATE = new IntProperty<Sprite>("rotate") {
        public void setValue(Sprite sprite, int i) {
            sprite.setRotate(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotate());
        }
    };
    public static final Property<Sprite, Integer> ROTATE_X = new IntProperty<Sprite>("rotateX") {
        public void setValue(Sprite sprite, int i) {
            sprite.setRotateX(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotateX());
        }
    };
    public static final Property<Sprite, Integer> ROTATE_Y = new IntProperty<Sprite>("rotateY") {
        public void setValue(Sprite sprite, int i) {
            sprite.setRotateY(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotateY());
        }
    };
    public static final Property<Sprite, Float> SCALE = new FloatProperty<Sprite>("scale") {
        public void setValue(Sprite sprite, float f) {
            sprite.setScale(f);
        }

        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScale());
        }
    };
    public static final Property<Sprite, Float> SCALE_X = new FloatProperty<Sprite>("scaleX") {
        public void setValue(Sprite sprite, float f) {
            sprite.setScaleX(f);
        }

        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScaleX());
        }
    };
    public static final Property<Sprite, Float> SCALE_Y = new FloatProperty<Sprite>("scaleY") {
        public void setValue(Sprite sprite, float f) {
            sprite.setScaleY(f);
        }

        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScaleY());
        }
    };
    public static final Property<Sprite, Integer> TRANSLATE_X = new IntProperty<Sprite>("translateX") {
        public void setValue(Sprite sprite, int i) {
            sprite.setTranslateX(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getTranslateX());
        }
    };
    public static final Property<Sprite, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<Sprite>("translateXPercentage") {
        public void setValue(Sprite sprite, float f) {
            sprite.setTranslateXPercentage(f);
        }

        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getTranslateXPercentage());
        }
    };
    public static final Property<Sprite, Integer> TRANSLATE_Y = new IntProperty<Sprite>("translateY") {
        public void setValue(Sprite sprite, int i) {
            sprite.setTranslateY(i);
        }

        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getTranslateY());
        }
    };
    public static final Property<Sprite, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<Sprite>("translateYPercentage") {
        public void setValue(Sprite sprite, float f) {
            sprite.setTranslateYPercentage(f);
        }

        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getTranslateYPercentage());
        }
    };
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private int alpha = 255;
    private int animationDelay;
    private ValueAnimator animator;
    protected Rect drawBounds = ZERO_BOUNDS_RECT;
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();
    private float pivotX;
    private float pivotY;
    private int rotate;
    private int rotateX;
    private int rotateY;
    private float scale = 1.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private int translateX;
    private float translateXPercentage;
    private int translateY;
    private float translateYPercentage;

    /* access modifiers changed from: protected */
    public abstract void drawSelf(Canvas canvas);

    public abstract int getColor();

    public int getOpacity() {
        return -3;
    }

    public abstract ValueAnimator onCreateAnimation();

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
    }

    public abstract void setColor(int i);

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
    }

    public void setAlpha(int i) {
        this.alpha = i;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public float getTranslateXPercentage() {
        return this.translateXPercentage;
    }

    public void setTranslateXPercentage(float f) {
        this.translateXPercentage = f;
    }

    public float getTranslateYPercentage() {
        return this.translateYPercentage;
    }

    public void setTranslateYPercentage(float f) {
        this.translateYPercentage = f;
    }

    public int getTranslateX() {
        return this.translateX;
    }

    public void setTranslateX(int i) {
        this.translateX = i;
    }

    public int getTranslateY() {
        return this.translateY;
    }

    public void setTranslateY(int i) {
        this.translateY = i;
    }

    public int getRotate() {
        return this.rotate;
    }

    public void setRotate(int i) {
        this.rotate = i;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f) {
        this.scale = f;
        setScaleX(f);
        setScaleY(f);
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    public int getRotateX() {
        return this.rotateX;
    }

    public void setRotateX(int i) {
        this.rotateX = i;
    }

    public int getRotateY() {
        return this.rotateY;
    }

    public void setRotateY(int i) {
        this.rotateY = i;
    }

    public float getPivotX() {
        return this.pivotX;
    }

    public void setPivotX(float f) {
        this.pivotX = f;
    }

    public float getPivotY() {
        return this.pivotY;
    }

    public void setPivotY(float f) {
        this.pivotY = f;
    }

    public int getAnimationDelay() {
        return this.animationDelay;
    }

    public Sprite setAnimationDelay(int i) {
        this.animationDelay = i;
        return this;
    }

    public void start() {
        if (!AnimationUtils.isStarted(this.animator)) {
            ValueAnimator obtainAnimation = obtainAnimation();
            this.animator = obtainAnimation;
            if (obtainAnimation != null) {
                AnimationUtils.start((Animator) obtainAnimation);
                invalidateSelf();
            }
        }
    }

    public ValueAnimator obtainAnimation() {
        if (this.animator == null) {
            this.animator = onCreateAnimation();
        }
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.addUpdateListener(this);
            this.animator.setStartDelay((long) this.animationDelay);
        }
        return this.animator;
    }

    public void stop() {
        if (AnimationUtils.isStarted(this.animator)) {
            this.animator.removeAllUpdateListeners();
            this.animator.end();
            reset();
        }
    }

    public void reset() {
        this.scale = 1.0f;
        this.rotateX = 0;
        this.rotateY = 0;
        this.translateX = 0;
        this.translateY = 0;
        this.rotate = 0;
        this.translateXPercentage = 0.0f;
        this.translateYPercentage = 0.0f;
    }

    public boolean isRunning() {
        return AnimationUtils.isRunning(this.animator);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setDrawBounds(rect);
    }

    public void setDrawBounds(Rect rect) {
        setDrawBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setDrawBounds(int i, int i2, int i3, int i4) {
        this.drawBounds = new Rect(i, i2, i3, i4);
        setPivotX((float) getDrawBounds().centerX());
        setPivotY((float) getDrawBounds().centerY());
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Rect getDrawBounds() {
        return this.drawBounds;
    }

    public void draw(Canvas canvas) {
        int translateX2 = getTranslateX();
        if (translateX2 == 0) {
            translateX2 = (int) (((float) getBounds().width()) * getTranslateXPercentage());
        }
        int translateY2 = getTranslateY();
        if (translateY2 == 0) {
            translateY2 = (int) (((float) getBounds().height()) * getTranslateYPercentage());
        }
        canvas.translate((float) translateX2, (float) translateY2);
        canvas.scale(getScaleX(), getScaleY(), getPivotX(), getPivotY());
        canvas.rotate((float) getRotate(), getPivotX(), getPivotY());
        if (!(getRotateX() == 0 && getRotateY() == 0)) {
            this.mCamera.save();
            this.mCamera.rotateX((float) getRotateX());
            this.mCamera.rotateY((float) getRotateY());
            this.mCamera.getMatrix(this.mMatrix);
            this.mMatrix.preTranslate(-getPivotX(), -getPivotY());
            this.mMatrix.postTranslate(getPivotX(), getPivotY());
            this.mCamera.restore();
            canvas.concat(this.mMatrix);
        }
        drawSelf(canvas);
    }

    public Rect clipSquare(Rect rect) {
        int min = Math.min(rect.width(), rect.height());
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        int i = min / 2;
        return new Rect(centerX - i, centerY - i, centerX + i, centerY + i);
    }
}
