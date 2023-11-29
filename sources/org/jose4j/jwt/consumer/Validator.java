package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;

public interface Validator {
    String validate(JwtContext jwtContext) throws MalformedClaimException;
}
