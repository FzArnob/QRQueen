package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class AssertionFailure extends RuntimeError {
    public AssertionFailure() {
    }

    public AssertionFailure(String str) {
        super(str);
    }
}
