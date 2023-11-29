package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceError implements SourceLocator {
    public String code;
    public int column;
    public Throwable fakeException;
    public String filename;
    public int line;
    public String message;
    public SourceError next;
    public char severity;

    public String getPublicId() {
        return null;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public SourceError(char c, String str, int i, int i2, String str2) {
        this.severity = c;
        this.filename = str;
        this.line = i;
        this.column = i2;
        this.message = str2;
    }

    public SourceError(char c, SourceLocator sourceLocator, String str) {
        this(c, sourceLocator.getFileName(), sourceLocator.getLineNumber(), sourceLocator.getColumnNumber(), str);
    }

    public SourceError(LineBufferedReader lineBufferedReader, char c, String str) {
        this(c, lineBufferedReader.getName(), lineBufferedReader.getLineNumber() + 1, lineBufferedReader.getColumnNumber(), str);
        int i = this.column;
        if (i >= 0) {
            this.column = i + 1;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.filename;
        if (str == null) {
            str = "<unknown>";
        }
        stringBuffer.append(str);
        if (this.line > 0 || this.column > 0) {
            stringBuffer.append(':');
            stringBuffer.append(this.line);
            if (this.column > 0) {
                stringBuffer.append(':');
                stringBuffer.append(this.column);
            }
        }
        stringBuffer.append(": ");
        if (this.severity == 'w') {
            stringBuffer.append("warning - ");
        }
        stringBuffer.append(this.message);
        if (this.code != null) {
            stringBuffer.append(" [");
            stringBuffer.append(this.code);
            stringBuffer.append("]");
        }
        Throwable th = this.fakeException;
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append("\n");
                stringBuffer.append("    ");
                stringBuffer.append(stackTraceElement.toString());
            }
        }
        return stringBuffer.toString();
    }

    public void print(PrintWriter printWriter) {
        printWriter.print(this);
    }

    public void println(PrintWriter printWriter) {
        String sourceError = toString();
        while (true) {
            int indexOf = sourceError.indexOf(10);
            if (indexOf < 0) {
                printWriter.println(sourceError);
                return;
            } else {
                printWriter.println(sourceError.substring(0, indexOf));
                sourceError = sourceError.substring(indexOf + 1);
            }
        }
    }

    public void println(PrintStream printStream) {
        String sourceError = toString();
        while (true) {
            int indexOf = sourceError.indexOf(10);
            if (indexOf < 0) {
                printStream.println(sourceError);
                return;
            } else {
                printStream.println(sourceError.substring(0, indexOf));
                sourceError = sourceError.substring(indexOf + 1);
            }
        }
    }

    public int getLineNumber() {
        int i = this.line;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public int getColumnNumber() {
        int i = this.column;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public String getSystemId() {
        return this.filename;
    }

    public String getFileName() {
        return this.filename;
    }
}
