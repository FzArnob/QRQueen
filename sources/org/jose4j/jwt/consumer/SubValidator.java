package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class SubValidator implements ErrorCodeValidator {
    private static final ErrorCodeValidator.Error MISSING_SUB = new ErrorCodeValidator.Error(14, "No Subject (sub) claim is present.");
    private String expectedSubject;
    private boolean requireSubject;

    public SubValidator(boolean z) {
        this.requireSubject = z;
    }

    public SubValidator(String str) {
        this(true);
        this.expectedSubject = str;
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String subject = jwtContext.getJwtClaims().getSubject();
        if (subject == null && this.requireSubject) {
            return MISSING_SUB;
        }
        String str = this.expectedSubject;
        if (str == null || str.equals(subject)) {
            return null;
        }
        return new ErrorCodeValidator.Error(15, "Subject (sub) claim value (" + subject + ") doesn't match expected value of " + this.expectedSubject);
    }
}
