package com.bumptech.glide.request.target;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public class GlideDrawableImageViewTarget extends ImageViewTarget<GlideDrawable> {
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private int maxLoopCount;
    private GlideDrawable resource;

    public GlideDrawableImageViewTarget(ImageView imageView) {
        this(imageView, -1);
    }

    public GlideDrawableImageViewTarget(ImageView imageView, int i) {
        super(imageView);
        this.maxLoopCount = i;
    }

    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
        if (!glideDrawable.isAnimated()) {
            float width = ((float) ((ImageView) this.view).getWidth()) / ((float) ((ImageView) this.view).getHeight());
            float intrinsicWidth = ((float) glideDrawable.getIntrinsicWidth()) / ((float) glideDrawable.getIntrinsicHeight());
            if (Math.abs(width - 1.0f) <= SQUARE_RATIO_MARGIN && Math.abs(intrinsicWidth - 1.0f) <= SQUARE_RATIO_MARGIN) {
                glideDrawable = new SquaringDrawable(glideDrawable, ((ImageView) this.view).getWidth());
            }
        }
        super.onResourceReady(glideDrawable, glideAnimation);
        this.resource = glideDrawable;
        glideDrawable.setLoopCount(this.maxLoopCount);
        glideDrawable.start();
    }

    /* access modifiers changed from: protected */
    public void setResource(GlideDrawable glideDrawable) {
        ((ImageView) this.view).setImageDrawable(glideDrawable);
    }

    public void onStart() {
        GlideDrawable glideDrawable = this.resource;
        if (glideDrawable != null) {
            glideDrawable.start();
        }
    }

    public void onStop() {
        GlideDrawable glideDrawable = this.resource;
        if (glideDrawable != null) {
            glideDrawable.stop();
        }
    }
}
