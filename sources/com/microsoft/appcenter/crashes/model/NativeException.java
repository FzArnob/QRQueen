package com.microsoft.appcenter.crashes.model;

public class NativeException extends RuntimeException {
    private static final String CRASH_MESSAGE = "Native exception read from a minidump file";

    public NativeException() {
        super(CRASH_MESSAGE);
    }
}
