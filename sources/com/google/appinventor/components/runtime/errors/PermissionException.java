package com.google.appinventor.components.runtime.errors;

public class PermissionException extends RuntimeException {
    private final String SOcHIyDQIvBEoO60i96YNU6xXO9daDM3Vqs4QxvYjxPTRo7vL8265rTCHGsT8wn;

    public PermissionException(String str) {
        this.SOcHIyDQIvBEoO60i96YNU6xXO9daDM3Vqs4QxvYjxPTRo7vL8265rTCHGsT8wn = str;
    }

    public String getPermissionNeeded() {
        return this.SOcHIyDQIvBEoO60i96YNU6xXO9daDM3Vqs4QxvYjxPTRo7vL8265rTCHGsT8wn;
    }

    public String getMessage() {
        return "Unable to complete the operation because the user denied permission: " + this.SOcHIyDQIvBEoO60i96YNU6xXO9daDM3Vqs4QxvYjxPTRo7vL8265rTCHGsT8wn;
    }
}
