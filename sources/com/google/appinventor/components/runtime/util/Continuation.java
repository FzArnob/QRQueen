package com.google.appinventor.components.runtime.util;

public interface Continuation<T> {
    void call(T t);
}
