package org.jose4j.lang;

public class InvalidKeyException extends JoseException {
    public InvalidKeyException(String str) {
        super(str);
    }

    public InvalidKeyException(String str, Throwable th) {
        super(str, th);
    }
}
