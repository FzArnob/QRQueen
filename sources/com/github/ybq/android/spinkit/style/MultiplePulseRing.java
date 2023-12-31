package com.github.ybq.android.spinkit.style;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class MultiplePulseRing extends SpriteContainer {
    public Sprite[] onCreateChild() {
        return new Sprite[]{new PulseRing(), new PulseRing(), new PulseRing()};
    }

    public void onChildCreated(Sprite... spriteArr) {
        int i = 0;
        while (i < spriteArr.length) {
            Sprite sprite = spriteArr[i];
            i++;
            sprite.setAnimationDelay(i * 200);
        }
    }
}
