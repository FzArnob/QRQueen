package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class Wave extends SpriteContainer {
    public Sprite[] onCreateChild() {
        WaveItem[] waveItemArr = new WaveItem[5];
        for (int i = 0; i < 5; i++) {
            waveItemArr[i] = new WaveItem();
            if (Build.VERSION.SDK_INT >= 24) {
                waveItemArr[i].setAnimationDelay((i * 100) + 600);
            } else {
                waveItemArr[i].setAnimationDelay((i * 100) - 1200);
            }
        }
        return waveItemArr;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect clipSquare = clipSquare(rect);
        int width = clipSquare.width() / getChildCount();
        int width2 = ((clipSquare.width() / 5) * 3) / 5;
        for (int i = 0; i < getChildCount(); i++) {
            Sprite childAt = getChildAt(i);
            int i2 = clipSquare.left + (i * width) + (width / 5);
            childAt.setDrawBounds(i2, clipSquare.top, i2 + width2, clipSquare.bottom);
        }
    }

    private class WaveItem extends RectSprite {
        WaveItem() {
            setScaleY(0.4f);
        }

        public ValueAnimator onCreateAnimation() {
            float[] fArr = {0.0f, 0.2f, 0.4f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(0.4f);
            return spriteAnimatorBuilder.scaleY(fArr, valueOf, Float.valueOf(1.0f), valueOf, valueOf).duration(1200).easeInOut(fArr).build();
        }
    }
}
