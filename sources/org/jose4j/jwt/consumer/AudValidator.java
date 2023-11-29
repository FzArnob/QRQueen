package org.jose4j.jwt.consumer;

import java.util.List;
import java.util.Set;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class AudValidator implements ErrorCodeValidator {
    private static final ErrorCodeValidator.Error MISSING_AUD = new ErrorCodeValidator.Error(7, "No Audience (aud) claim present.");
    private Set<String> acceptableAudiences;
    private boolean requireAudience;

    public AudValidator(Set<String> set, boolean z) {
        this.acceptableAudiences = set;
        this.requireAudience = z;
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        JwtClaims jwtClaims = jwtContext.getJwtClaims();
        if (jwtClaims.hasAudience()) {
            List<String> audience = jwtClaims.getAudience();
            boolean z = false;
            for (String contains : audience) {
                if (this.acceptableAudiences.contains(contains)) {
                    z = true;
                }
            }
            if (z) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Audience (aud) claim ");
            sb.append(audience);
            if (this.acceptableAudiences.isEmpty()) {
                sb.append(" present in the JWT but no expected audience value(s) were provided to the JWT Consumer.");
            } else {
                sb.append(" doesn't contain an acceptable identifier.");
            }
            sb.append(" Expected ");
            if (this.acceptableAudiences.size() == 1) {
                sb.append(this.acceptableAudiences.iterator().next());
            } else {
                sb.append("one of ");
                sb.append(this.acceptableAudiences);
            }
            sb.append(" as an aud value.");
            return new ErrorCodeValidator.Error(8, sb.toString());
        } else if (this.requireAudience) {
            return MISSING_AUD;
        } else {
            return null;
        }
    }
}
