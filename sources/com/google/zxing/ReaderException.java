package com.google.zxing;

public abstract class ReaderException extends Exception {
    public final Throwable fillInStackTrace() {
        return null;
    }

    ReaderException() {
    }
}
