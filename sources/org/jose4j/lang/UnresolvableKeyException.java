package org.jose4j.lang;

public class UnresolvableKeyException extends JoseException {
    public UnresolvableKeyException(String str) {
        super(str);
    }

    public UnresolvableKeyException(String str, Throwable th) {
        super(str, th);
    }
}
