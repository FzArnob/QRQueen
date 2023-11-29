package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class WanderingCubes extends SpriteContainer {
    public Sprite[] onCreateChild() {
        return new Sprite[]{new Cube(0), new Cube(3)};
    }

    public void onChildCreated(Sprite... spriteArr) {
        super.onChildCreated(spriteArr);
        if (Build.VERSION.SDK_INT < 24) {
            spriteArr[1].setAnimationDelay(-900);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect clipSquare = clipSquare(rect);
        super.onBoundsChange(clipSquare);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setDrawBounds(clipSquare.left, clipSquare.top, clipSquare.left + (clipSquare.width() / 4), clipSquare.top + (clipSquare.height() / 4));
        }
    }

    private class Cube extends RectSprite {
        int startFrame;

        public Cube(int i) {
            this.startFrame = i;
        }

        public ValueAnimator onCreateAnimation() {
            float[] fArr = {0.0f, 0.25f, 0.5f, 0.51f, 0.75f, 1.0f};
            SpriteAnimatorBuilder rotate = new SpriteAnimatorBuilder(this).rotate(fArr, 0, -90, -179, -180, -270, -360);
            Float valueOf = Float.valueOf(0.0f);
            Float valueOf2 = Float.valueOf(0.75f);
            SpriteAnimatorBuilder translateYPercentage = rotate.translateXPercentage(fArr, valueOf, valueOf2, valueOf2, valueOf2, valueOf, valueOf).translateYPercentage(fArr, valueOf, valueOf, valueOf2, valueOf2, valueOf2, valueOf);
            Float valueOf3 = Float.valueOf(1.0f);
            Float valueOf4 = Float.valueOf(0.5f);
            SpriteAnimatorBuilder easeInOut = translateYPercentage.scale(fArr, valueOf3, valueOf4, valueOf3, valueOf3, valueOf4, valueOf3).duration(1800).easeInOut(fArr);
            if (Build.VERSION.SDK_INT >= 24) {
                easeInOut.startFrame(this.startFrame);
            }
            return easeInOut.build();
        }
    }
}
