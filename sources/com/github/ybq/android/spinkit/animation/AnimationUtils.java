package com.github.ybq.android.spinkit.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class AnimationUtils {
    public static void start(Animator animator) {
        if (animator != null && !animator.isStarted()) {
            animator.start();
        }
    }

    public static void stop(Animator animator) {
        if (animator != null && !animator.isRunning()) {
            animator.end();
        }
    }

    public static void start(Sprite... spriteArr) {
        for (Sprite start : spriteArr) {
            start.start();
        }
    }

    public static void stop(Sprite... spriteArr) {
        for (Sprite stop : spriteArr) {
            stop.stop();
        }
    }

    public static boolean isRunning(Sprite... spriteArr) {
        for (Sprite isRunning : spriteArr) {
            if (isRunning.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRunning(ValueAnimator valueAnimator) {
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public static boolean isStarted(ValueAnimator valueAnimator) {
        return valueAnimator != null && valueAnimator.isStarted();
    }
}
