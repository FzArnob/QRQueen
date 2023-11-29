package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;

public class ThreeBounce extends SpriteContainer {
    public Sprite[] onCreateChild() {
        return new Sprite[]{new Bounce(), new Bounce(), new Bounce()};
    }

    public void onChildCreated(Sprite... spriteArr) {
        super.onChildCreated(spriteArr);
        spriteArr[1].setAnimationDelay(ComponentConstants.TEXTBOX_PREFERRED_WIDTH);
        spriteArr[2].setAnimationDelay(ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect clipSquare = clipSquare(rect);
        int width = clipSquare.width() / 8;
        int centerY = clipSquare.centerY() - width;
        int centerY2 = clipSquare.centerY() + width;
        for (int i = 0; i < getChildCount(); i++) {
            int width2 = ((clipSquare.width() * i) / 3) + clipSquare.left;
            getChildAt(i).setDrawBounds(width2, centerY, (width * 2) + width2, centerY2);
        }
    }

    private class Bounce extends CircleSprite {
        Bounce() {
            setScale(0.0f);
        }

        public ValueAnimator onCreateAnimation() {
            float[] fArr = {0.0f, 0.4f, 0.8f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(0.0f);
            return spriteAnimatorBuilder.scale(fArr, valueOf, Float.valueOf(1.0f), valueOf, valueOf).duration(1400).easeInOut(fArr).build();
        }
    }
}
