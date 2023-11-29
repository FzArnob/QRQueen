package gnu.mapping;

public class WrappedException extends RuntimeException {
    public WrappedException() {
    }

    public WrappedException(String str) {
        super(str);
    }

    public WrappedException(Throwable th) {
        this(th.toString(), th);
    }

    public WrappedException(String str, Throwable th) {
        super(str, th);
    }

    public Throwable getException() {
        return getCause();
    }

    public String toString() {
        return getMessage();
    }

    public static RuntimeException wrapIfNeeded(Throwable th) {
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        return new WrappedException(th);
    }
}
