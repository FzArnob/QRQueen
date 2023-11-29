package org.jose4j.jwt.consumer;

import java.util.Collections;
import java.util.List;
import org.jose4j.jwt.consumer.ErrorCodeValidator;

public class InvalidJwtException extends Exception {
    private List<ErrorCodeValidator.Error> details;
    private JwtContext jwtContext;

    public InvalidJwtException(String str, List<ErrorCodeValidator.Error> list, JwtContext jwtContext2) {
        super(str);
        Collections.emptyList();
        this.details = list;
        this.jwtContext = jwtContext2;
    }

    public InvalidJwtException(String str, ErrorCodeValidator.Error error, Throwable th, JwtContext jwtContext2) {
        super(str, th);
        this.details = Collections.emptyList();
        this.jwtContext = jwtContext2;
        this.details = Collections.singletonList(error);
    }

    public boolean hasErrorCode(int i) {
        for (ErrorCodeValidator.Error errorCode : this.details) {
            if (i == errorCode.getErrorCode()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasExpired() {
        return hasErrorCode(1);
    }

    public List<ErrorCodeValidator.Error> getErrorDetails() {
        return this.details;
    }

    public JwtContext getJwtContext() {
        return this.jwtContext;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getMessage());
        if (!this.details.isEmpty()) {
            sb.append(" Additional details: ");
            sb.append(this.details);
        }
        return sb.toString();
    }
}
