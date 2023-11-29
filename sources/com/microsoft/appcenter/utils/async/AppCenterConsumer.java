package com.microsoft.appcenter.utils.async;

public interface AppCenterConsumer<T> {
    void accept(T t);
}
