package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class JtiValidator implements ErrorCodeValidator {
    private static final ErrorCodeValidator.Error MISSING_JTI = new ErrorCodeValidator.Error(13, "The JWT ID (jti) claim is not present.");
    private boolean requireJti;

    public JtiValidator(boolean z) {
        this.requireJti = z;
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        if (jwtContext.getJwtClaims().getJwtId() != null || !this.requireJti) {
            return null;
        }
        return MISSING_JTI;
    }
}
