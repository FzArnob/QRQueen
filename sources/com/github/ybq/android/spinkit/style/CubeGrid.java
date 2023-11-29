package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class CubeGrid extends SpriteContainer {
    public Sprite[] onCreateChild() {
        int[] iArr = {200, 300, 400, 100, 200, 300, 0, 100, 200};
        GridItem[] gridItemArr = new GridItem[9];
        for (int i = 0; i < 9; i++) {
            GridItem gridItem = new GridItem();
            gridItemArr[i] = gridItem;
            gridItem.setAnimationDelay(iArr[i]);
        }
        return gridItemArr;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect clipSquare = clipSquare(rect);
        int width = (int) (((float) clipSquare.width()) * 0.33f);
        int height = (int) (((float) clipSquare.height()) * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int i2 = clipSquare.left + ((i % 3) * width);
            int i3 = clipSquare.top + ((i / 3) * height);
            getChildAt(i).setDrawBounds(i2, i3, i2 + width, i3 + height);
        }
    }

    private class GridItem extends RectSprite {
        private GridItem() {
        }

        public ValueAnimator onCreateAnimation() {
            float[] fArr = {0.0f, 0.35f, 0.7f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(1.0f);
            return spriteAnimatorBuilder.scale(fArr, valueOf, Float.valueOf(0.0f), valueOf, valueOf).duration(1300).easeInOut(fArr).build();
        }
    }
}
