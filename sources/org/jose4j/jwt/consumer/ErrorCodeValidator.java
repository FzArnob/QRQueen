package org.jose4j.jwt.consumer;

import org.jose4j.jwt.MalformedClaimException;

public interface ErrorCodeValidator {
    Error validate(JwtContext jwtContext) throws MalformedClaimException;

    public static class Error {
        private int errorCode;
        private String errorMessage;

        public Error(int i, String str) {
            this.errorCode = i;
            this.errorMessage = str;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public String toString() {
            return "[" + this.errorCode + "] " + this.errorMessage;
        }
    }
}
