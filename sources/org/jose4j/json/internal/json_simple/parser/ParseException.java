package org.jose4j.json.internal.json_simple.parser;

public class ParseException extends Exception {
    public static final int ERROR_UNEXPECTED_CHAR = 0;
    public static final int ERROR_UNEXPECTED_EXCEPTION = 2;
    public static final int ERROR_UNEXPECTED_TOKEN = 1;
    private static final long serialVersionUID = -7880698968187728547L;
    private int errorType;
    private int position;
    private Object unexpectedObject;

    public ParseException(int i) {
        this(-1, i, (Object) null);
    }

    public ParseException(int i, Object obj) {
        this(-1, i, obj);
    }

    public ParseException(int i, int i2, Object obj) {
        this.position = i;
        this.errorType = i2;
        this.unexpectedObject = obj;
    }

    public int getErrorType() {
        return this.errorType;
    }

    public void setErrorType(int i) {
        this.errorType = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public Object getUnexpectedObject() {
        return this.unexpectedObject;
    }

    public void setUnexpectedObject(Object obj) {
        this.unexpectedObject = obj;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        int i = this.errorType;
        if (i == 0) {
            sb.append("Unexpected character (");
            sb.append(this.unexpectedObject);
            sb.append(") at position ");
            sb.append(this.position);
            sb.append(".");
        } else if (i == 1) {
            sb.append("Unexpected token ");
            sb.append(this.unexpectedObject);
            sb.append(" at position ");
            sb.append(this.position);
            sb.append(".");
        } else if (i != 2) {
            sb.append("Unknown error at position ");
            sb.append(this.position);
            sb.append(".");
        } else {
            sb.append("Unexpected exception at position ");
            sb.append(this.position);
            sb.append(": ");
            sb.append(this.unexpectedObject);
        }
        return sb.toString();
    }
}
