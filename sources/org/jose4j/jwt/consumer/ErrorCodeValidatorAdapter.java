package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class ErrorCodeValidatorAdapter implements ErrorCodeValidator {
    private Validator validator;

    public ErrorCodeValidatorAdapter(Validator validator2) {
        this.validator = validator2;
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) throws MalformedClaimException {
        String validate = this.validator.validate(jwtContext);
        if (validate == null) {
            return null;
        }
        return new ErrorCodeValidator.Error(17, validate);
    }
}
