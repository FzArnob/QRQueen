package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;

public class Pulse extends CircleSprite {
    public Pulse() {
        setScale(0.0f);
    }

    public ValueAnimator onCreateAnimation() {
        float[] fArr = {0.0f, 1.0f};
        return new SpriteAnimatorBuilder(this).scale(fArr, Float.valueOf(0.0f), Float.valueOf(1.0f)).alpha(fArr, 255, 0).duration(1000).easeInOut(fArr).build();
    }
}
