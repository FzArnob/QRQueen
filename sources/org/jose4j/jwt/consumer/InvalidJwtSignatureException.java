package org.jose4j.jwt.consumer;

import java.util.Collections;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class InvalidJwtSignatureException extends InvalidJwtException {
    public InvalidJwtSignatureException(JsonWebSignature jsonWebSignature, JwtContext jwtContext) {
        super("JWT rejected due to invalid signature.", Collections.singletonList(new ErrorCodeValidator.Error(9, "Invalid JWS Signature: " + jsonWebSignature)), jwtContext);
    }
}
