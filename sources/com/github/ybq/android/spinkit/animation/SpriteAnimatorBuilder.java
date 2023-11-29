package com.github.ybq.android.spinkit.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.util.Property;
import android.view.animation.Interpolator;
import com.github.ybq.android.spinkit.animation.interpolator.KeyFrameInterpolator;
import com.github.ybq.android.spinkit.sprite.Sprite;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpriteAnimatorBuilder {
    private static final String TAG = "SpriteAnimatorBuilder";
    private long duration = 2000;
    private Map<String, FrameData> fds = new HashMap();
    private Interpolator interpolator;
    private int repeatCount = -1;
    private Sprite sprite;
    private int startFrame = 0;

    class FrameData<T> {
        float[] fractions;
        Property property;
        T[] values;

        public FrameData(float[] fArr, Property property2, T[] tArr) {
            this.fractions = fArr;
            this.property = property2;
            this.values = tArr;
        }
    }

    class IntFrameData extends FrameData<Integer> {
        public IntFrameData(float[] fArr, Property property, Integer[] numArr) {
            super(fArr, property, numArr);
        }
    }

    class FloatFrameData extends FrameData<Float> {
        public FloatFrameData(float[] fArr, Property property, Float[] fArr2) {
            super(fArr, property, fArr2);
        }
    }

    public SpriteAnimatorBuilder(Sprite sprite2) {
        this.sprite = sprite2;
    }

    public SpriteAnimatorBuilder scale(float[] fArr, Float... fArr2) {
        holder(fArr, (Property) Sprite.SCALE, fArr2);
        return this;
    }

    public SpriteAnimatorBuilder alpha(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.ALPHA, numArr);
        return this;
    }

    public SpriteAnimatorBuilder scaleX(float[] fArr, Float... fArr2) {
        holder(fArr, (Property) Sprite.SCALE, fArr2);
        return this;
    }

    public SpriteAnimatorBuilder scaleY(float[] fArr, Float... fArr2) {
        holder(fArr, (Property) Sprite.SCALE_Y, fArr2);
        return this;
    }

    public SpriteAnimatorBuilder rotateX(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.ROTATE_X, numArr);
        return this;
    }

    public SpriteAnimatorBuilder rotateY(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.ROTATE_Y, numArr);
        return this;
    }

    public SpriteAnimatorBuilder translateX(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.TRANSLATE_X, numArr);
        return this;
    }

    public SpriteAnimatorBuilder translateY(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.TRANSLATE_Y, numArr);
        return this;
    }

    public SpriteAnimatorBuilder rotate(float[] fArr, Integer... numArr) {
        holder(fArr, (Property) Sprite.ROTATE, numArr);
        return this;
    }

    public SpriteAnimatorBuilder translateXPercentage(float[] fArr, Float... fArr2) {
        holder(fArr, (Property) Sprite.TRANSLATE_X_PERCENTAGE, fArr2);
        return this;
    }

    public SpriteAnimatorBuilder translateYPercentage(float[] fArr, Float... fArr2) {
        holder(fArr, (Property) Sprite.TRANSLATE_Y_PERCENTAGE, fArr2);
        return this;
    }

    private void holder(float[] fArr, Property property, Float[] fArr2) {
        ensurePair(fArr.length, fArr2.length);
        this.fds.put(property.getName(), new FloatFrameData(fArr, property, fArr2));
    }

    private void holder(float[] fArr, Property property, Integer[] numArr) {
        ensurePair(fArr.length, numArr.length);
        this.fds.put(property.getName(), new IntFrameData(fArr, property, numArr));
    }

    private void ensurePair(int i, int i2) {
        if (i != i2) {
            throw new IllegalStateException(String.format(Locale.getDefault(), "The fractions.length must equal values.length, fraction.length[%d], values.length[%d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public SpriteAnimatorBuilder interpolator(Interpolator interpolator2) {
        this.interpolator = interpolator2;
        return this;
    }

    public SpriteAnimatorBuilder easeInOut(float... fArr) {
        interpolator(KeyFrameInterpolator.easeInOut(fArr));
        return this;
    }

    public SpriteAnimatorBuilder duration(long j) {
        this.duration = j;
        return this;
    }

    public SpriteAnimatorBuilder repeatCount(int i) {
        this.repeatCount = i;
        return this;
    }

    public SpriteAnimatorBuilder startFrame(int i) {
        if (i < 0) {
            Log.w(TAG, "startFrame should always be non-negative");
            i = 0;
        }
        this.startFrame = i;
        return this;
    }

    public ObjectAnimator build() {
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[this.fds.size()];
        int i = 0;
        for (Map.Entry<String, FrameData> value : this.fds.entrySet()) {
            FrameData frameData = (FrameData) value.getValue();
            Keyframe[] keyframeArr = new Keyframe[frameData.fractions.length];
            float[] fArr = frameData.fractions;
            int i2 = this.startFrame;
            float f = fArr[i2];
            while (i2 < this.startFrame + frameData.values.length) {
                int i3 = i2 - this.startFrame;
                int length = i2 % frameData.values.length;
                float f2 = fArr[length] - f;
                if (f2 < 0.0f) {
                    f2 += fArr[fArr.length - 1];
                }
                if (frameData instanceof IntFrameData) {
                    keyframeArr[i3] = Keyframe.ofInt(f2, ((Integer) frameData.values[length]).intValue());
                } else if (frameData instanceof FloatFrameData) {
                    keyframeArr[i3] = Keyframe.ofFloat(f2, ((Float) frameData.values[length]).floatValue());
                } else {
                    keyframeArr[i3] = Keyframe.ofObject(f2, frameData.values[length]);
                }
                i2++;
            }
            propertyValuesHolderArr[i] = PropertyValuesHolder.ofKeyframe(frameData.property, keyframeArr);
            i++;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.sprite, propertyValuesHolderArr);
        ofPropertyValuesHolder.setDuration(this.duration);
        ofPropertyValuesHolder.setRepeatCount(this.repeatCount);
        ofPropertyValuesHolder.setInterpolator(this.interpolator);
        return ofPropertyValuesHolder;
    }
}
