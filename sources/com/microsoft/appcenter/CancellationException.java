package com.microsoft.appcenter;

public class CancellationException extends Exception {
    public CancellationException() {
        super("Request cancelled because Channel is disabled.");
    }
}
