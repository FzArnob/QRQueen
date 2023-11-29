package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.request.Request;

public abstract class BaseTarget<Z> implements Target<Z> {
    private Request request;

    public void onDestroy() {
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void setRequest(Request request2) {
        this.request = request2;
    }

    public Request getRequest() {
        return this.request;
    }
}
