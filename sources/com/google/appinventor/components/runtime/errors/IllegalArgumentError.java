package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class IllegalArgumentError extends RuntimeError {
    public IllegalArgumentError() {
    }

    public IllegalArgumentError(String str) {
        super(str);
    }
}
