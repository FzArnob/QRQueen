package org.jose4j.lang;

public class UncheckedJoseException extends RuntimeException {
    public UncheckedJoseException(String str) {
        super(str);
    }

    public UncheckedJoseException(String str, Throwable th) {
        super(str, th);
    }
}
