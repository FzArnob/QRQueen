package org.jose4j.jwt;

public class MalformedClaimException extends GeneralJwtException {
    public MalformedClaimException(String str) {
        super(str);
    }

    public MalformedClaimException(String str, Throwable th) {
        super(str, th);
    }
}
