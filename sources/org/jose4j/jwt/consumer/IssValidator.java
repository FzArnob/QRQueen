package org.jose4j.jwt.consumer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class IssValidator implements ErrorCodeValidator {
    private Set<String> expectedIssuers;
    private boolean requireIssuer;

    public IssValidator(String str, boolean z) {
        if (str != null) {
            this.expectedIssuers = Collections.singleton(str);
        }
        this.requireIssuer = z;
    }

    public IssValidator(boolean z, String... strArr) {
        this.requireIssuer = z;
        if (strArr != null && strArr.length > 0) {
            HashSet hashSet = new HashSet();
            this.expectedIssuers = hashSet;
            Collections.addAll(hashSet, strArr);
        }
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String issuer = jwtContext.getJwtClaims().getIssuer();
        if (issuer != null) {
            Set<String> set = this.expectedIssuers;
            if (set == null || set.contains(issuer)) {
                return null;
            }
            return new ErrorCodeValidator.Error(12, "Issuer (iss) claim value (" + issuer + ") doesn't match expected value of " + expectedValue());
        } else if (this.requireIssuer) {
            return new ErrorCodeValidator.Error(11, "No Issuer (iss) claim present.");
        } else {
            return null;
        }
    }

    private String expectedValue() {
        if (this.expectedIssuers.size() == 1) {
            return this.expectedIssuers.iterator().next();
        }
        return "one of " + this.expectedIssuers;
    }
}
