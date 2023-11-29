package org.jose4j.jwt.consumer;

public class ErrorCodes {
    public static final int AUDIENCE_INVALID = 8;
    public static final int AUDIENCE_MISSING = 7;
    public static final int ENCRYPTION_MISSING = 19;
    public static final int EXPIRATION_MISSING = 2;
    public static final int EXPIRATION_TOO_FAR_IN_FUTURE = 5;
    public static final int EXPIRED = 1;
    public static final int INTEGRITY_MISSING = 20;
    public static final int ISSUED_AT_INVALID_FUTURE = 23;
    public static final int ISSUED_AT_INVALID_PAST = 24;
    public static final int ISSUED_AT_MISSING = 3;
    public static final int ISSUER_INVALID = 12;
    public static final int ISSUER_MISSING = 11;
    public static final int JSON_INVALID = 16;
    public static final int JWT_ID_MISSING = 13;
    public static final int MALFORMED_CLAIM = 18;
    public static final int MISCELLANEOUS = 17;
    public static final int NOT_BEFORE_MISSING = 4;
    public static final int NOT_YET_VALID = 6;
    public static final int SIGNATURE_INVALID = 9;
    public static final int SIGNATURE_MISSING = 10;
    public static final int SUBJECT_INVALID = 15;
    public static final int SUBJECT_MISSING = 14;
    public static final int TYPE_INVALID = 22;
    public static final int TYPE_MISSING = 21;
}
