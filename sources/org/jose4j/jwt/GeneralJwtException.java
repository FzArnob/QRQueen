package org.jose4j.jwt;

public class GeneralJwtException extends Exception {
    public GeneralJwtException(String str) {
        super(str);
    }

    public GeneralJwtException(String str, Throwable th) {
        super(str, th);
    }
}
