package com.microsoft.appcenter.utils.async;

public interface AppCenterFuture<T> {
    T get();

    boolean isDone();

    void thenAccept(AppCenterConsumer<T> appCenterConsumer);
}
