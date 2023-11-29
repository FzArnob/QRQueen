package org.jose4j.lang;

public class JoseException extends Exception {
    public JoseException(String str) {
        super(str);
    }

    public JoseException(String str, Throwable th) {
        super(str, th);
    }
}
