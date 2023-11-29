package com.github.ybq.android.spinkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class SpinKitView extends ProgressBar {
    private int mColor;
    private Sprite mSprite;
    private Style mStyle;

    public SpinKitView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SpinKitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.SpinKitViewStyle);
    }

    public SpinKitView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.SpinKitView);
    }

    public SpinKitView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpinKitView, i, i2);
        this.mStyle = Style.values()[obtainStyledAttributes.getInt(R.styleable.SpinKitView_SpinKit_Style, 0)];
        this.mColor = obtainStyledAttributes.getColor(R.styleable.SpinKitView_SpinKit_Color, -1);
        obtainStyledAttributes.recycle();
        init();
        setIndeterminate(true);
    }

    private void init() {
        Sprite create = SpriteFactory.create(this.mStyle);
        create.setColor(this.mColor);
        setIndeterminateDrawable(create);
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable instanceof Sprite) {
            setIndeterminateDrawable((Sprite) drawable);
            return;
        }
        throw new IllegalArgumentException("this d must be instanceof Sprite");
    }

    public void setIndeterminateDrawable(Sprite sprite) {
        super.setIndeterminateDrawable(sprite);
        this.mSprite = sprite;
        if (sprite.getColor() == 0) {
            this.mSprite.setColor(this.mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    public Sprite getIndeterminateDrawable() {
        return this.mSprite;
    }

    public void setColor(int i) {
        this.mColor = i;
        Sprite sprite = this.mSprite;
        if (sprite != null) {
            sprite.setColor(i);
        }
        invalidate();
    }

    public void unscheduleDrawable(Drawable drawable) {
        super.unscheduleDrawable(drawable);
        if (drawable instanceof Sprite) {
            ((Sprite) drawable).stop();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.mSprite != null && getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    public void onScreenStateChanged(int i) {
        Sprite sprite;
        super.onScreenStateChanged(i);
        if (i == 0 && (sprite = this.mSprite) != null) {
            sprite.stop();
        }
    }
}
