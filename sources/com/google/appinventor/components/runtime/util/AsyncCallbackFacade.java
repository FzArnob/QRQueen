package com.google.appinventor.components.runtime.util;

public abstract class AsyncCallbackFacade<S, T> implements AsyncCallbackPair<S> {
    protected final AsyncCallbackPair<T> callback;

    public AsyncCallbackFacade(AsyncCallbackPair<T> asyncCallbackPair) {
        this.callback = asyncCallbackPair;
    }

    public void onFailure(String str) {
        this.callback.onFailure(str);
    }
}
