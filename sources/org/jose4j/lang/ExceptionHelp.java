package org.jose4j.lang;

public class ExceptionHelp {
    public static String toStringWithCauses(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th);
        while (th.getCause() != null) {
            th = th.getCause();
            sb.append("; caused by: ");
            sb.append(th);
        }
        return sb.toString();
    }

    public static String toStringWithCausesAndAbbreviatedStack(Throwable th, Class cls) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        while (th != null) {
            if (!z) {
                sb.append("; caused by: ");
            }
            sb.append(th);
            sb.append(" at ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (stackTraceElement.getClassName().equals(cls.getName())) {
                    sb.append("...omitted...");
                    break;
                }
                sb.append(stackTraceElement);
                sb.append("; ");
                i++;
            }
            th = th.getCause();
            z = false;
        }
        return sb.toString();
    }
}
