package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;

public class RotatingCircle extends CircleSprite {
    public ValueAnimator onCreateAnimation() {
        float[] fArr = {0.0f, 0.5f, 1.0f};
        return new SpriteAnimatorBuilder(this).rotateX(fArr, 0, -180, -180).rotateY(fArr, 0, 0, -180).duration(1200).easeInOut(fArr).build();
    }
}
