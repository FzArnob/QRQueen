package net.lingala.zip4j.exception;

public class ZipException extends Exception {
    private static final long serialVersionUID = 1;
    private int code;

    public ZipException() {
        this.code = -1;
    }

    public ZipException(String str) {
        super(str);
        this.code = -1;
    }

    public ZipException(String str, Throwable th) {
        super(str, th);
        this.code = -1;
    }

    public ZipException(String str, int i) {
        super(str);
        this.code = i;
    }

    public ZipException(String str, Throwable th, int i) {
        super(str, th);
        this.code = i;
    }

    public ZipException(Throwable th) {
        super(th);
        this.code = -1;
    }

    public ZipException(Throwable th, int i) {
        super(th);
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
