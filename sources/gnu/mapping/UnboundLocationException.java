package gnu.mapping;

import gnu.text.SourceLocator;

public class UnboundLocationException extends RuntimeException {
    int column;
    String filename;
    int line;
    Location location;
    public Object symbol;

    public UnboundLocationException() {
    }

    public UnboundLocationException(Object obj) {
        this.symbol = obj;
    }

    public UnboundLocationException(Object obj, String str, int i, int i2) {
        this.symbol = obj;
        this.filename = str;
        this.line = i;
        this.column = i2;
    }

    public UnboundLocationException(Object obj, SourceLocator sourceLocator) {
        this.symbol = obj;
        if (sourceLocator != null) {
            this.filename = sourceLocator.getFileName();
            this.line = sourceLocator.getLineNumber();
            this.column = sourceLocator.getColumnNumber();
        }
    }

    public UnboundLocationException(Location location2) {
        this.location = location2;
    }

    public UnboundLocationException(Object obj, String str) {
        super(str);
        this.symbol = obj;
    }

    public void setLine(String str, int i, int i2) {
        this.filename = str;
        this.line = i;
        this.column = i2;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message != null) {
            return message;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.filename;
        if (str != null || this.line > 0) {
            if (str != null) {
                stringBuffer.append(str);
            }
            if (this.line >= 0) {
                stringBuffer.append(':');
                stringBuffer.append(this.line);
                if (this.column > 0) {
                    stringBuffer.append(':');
                    stringBuffer.append(this.column);
                }
            }
            stringBuffer.append(": ");
        }
        Location location2 = this.location;
        Symbol keySymbol = location2 == null ? null : location2.getKeySymbol();
        if (keySymbol != null) {
            stringBuffer.append("unbound location ");
            stringBuffer.append(keySymbol);
            Object keyProperty = this.location.getKeyProperty();
            if (keyProperty != null) {
                stringBuffer.append(" (property ");
                stringBuffer.append(keyProperty);
                stringBuffer.append(')');
            }
        } else if (this.symbol != null) {
            stringBuffer.append("unbound location ");
            stringBuffer.append(this.symbol);
        } else {
            stringBuffer.append("unbound location");
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return getMessage();
    }
}
