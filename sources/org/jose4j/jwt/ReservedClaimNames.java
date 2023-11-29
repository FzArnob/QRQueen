package org.jose4j.jwt;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ReservedClaimNames {
    public static final String AUDIENCE = "aud";
    public static final String EXPIRATION_TIME = "exp";
    public static final Set<String> INITIAL_REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{ISSUER, SUBJECT, AUDIENCE, EXPIRATION_TIME, NOT_BEFORE, ISSUED_AT, JWT_ID})));
    public static final String ISSUED_AT = "iat";
    public static final String ISSUER = "iss";
    public static final String JWT_ID = "jti";
    public static final String NOT_BEFORE = "nbf";
    public static final String SUBJECT = "sub";
    public static final String TYPE = "typ";
}
