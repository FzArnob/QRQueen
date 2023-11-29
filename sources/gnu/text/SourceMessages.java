package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceMessages implements SourceLocator {
    public static boolean debugStackTraceOnError = false;
    public static boolean debugStackTraceOnWarning = false;
    int current_column;
    String current_filename;
    int current_line;
    private int errorCount = 0;
    SourceError firstError;
    SourceError lastError;
    SourceError lastPrevFilename = null;
    SourceLocator locator;
    public boolean sortMessages;

    public boolean isStableSourceLocation() {
        return false;
    }

    public SourceError getErrors() {
        return this.firstError;
    }

    public boolean seenErrors() {
        return this.errorCount > 0;
    }

    public boolean seenErrorsOrWarnings() {
        return this.firstError != null;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public void clearErrors() {
        this.errorCount = 0;
    }

    public void clear() {
        this.lastError = null;
        this.firstError = null;
        this.errorCount = 0;
    }

    public void error(SourceError sourceError) {
        SourceError sourceError2;
        if (sourceError.severity == 'f') {
            this.errorCount = 1000;
        } else if (sourceError.severity != 'w') {
            this.errorCount++;
        }
        if ((debugStackTraceOnError && (sourceError.severity == 'e' || sourceError.severity == 'f')) || (debugStackTraceOnWarning && sourceError.severity == 'w')) {
            sourceError.fakeException = new Throwable();
        }
        SourceError sourceError3 = this.lastError;
        if (!(sourceError3 == null || sourceError3.filename == null || this.lastError.filename.equals(sourceError.filename))) {
            this.lastPrevFilename = this.lastError;
        }
        SourceError sourceError4 = this.lastPrevFilename;
        if (this.sortMessages && sourceError.severity != 'f') {
            while (true) {
                if (sourceError4 == null) {
                    sourceError2 = this.firstError;
                } else {
                    sourceError2 = sourceError4.next;
                }
                if (sourceError2 == null || (sourceError.line != 0 && sourceError2.line != 0 && (sourceError.line < sourceError2.line || (sourceError.line == sourceError2.line && sourceError.column != 0 && sourceError2.column != 0 && sourceError.column < sourceError2.column)))) {
                    break;
                }
                sourceError4 = sourceError2;
            }
        } else {
            sourceError4 = this.lastError;
        }
        if (sourceError4 == null) {
            sourceError.next = this.firstError;
            this.firstError = sourceError;
        } else {
            sourceError.next = sourceError4.next;
            sourceError4.next = sourceError;
        }
        if (sourceError4 == this.lastError) {
            this.lastError = sourceError;
        }
    }

    public void error(char c, String str, int i, int i2, String str2) {
        error(new SourceError(c, str, i, i2, str2));
    }

    public void error(char c, SourceLocator sourceLocator, String str) {
        error(new SourceError(c, sourceLocator, str));
    }

    public void error(char c, String str, int i, int i2, String str2, String str3) {
        SourceError sourceError = new SourceError(c, str, i, i2, str2);
        sourceError.code = str3;
        error(sourceError);
    }

    public void error(char c, SourceLocator sourceLocator, String str, String str2) {
        SourceError sourceError = new SourceError(c, sourceLocator, str);
        sourceError.code = str2;
        error(sourceError);
    }

    public void error(char c, String str) {
        error(new SourceError(c, this.current_filename, this.current_line, this.current_column, str));
    }

    public void error(char c, String str, Throwable th) {
        SourceError sourceError = new SourceError(c, this.current_filename, this.current_line, this.current_column, str);
        sourceError.fakeException = th;
        error(sourceError);
    }

    public void error(char c, String str, String str2) {
        SourceError sourceError = new SourceError(c, this.current_filename, this.current_line, this.current_column, str);
        sourceError.code = str2;
        error(sourceError);
    }

    public void printAll(PrintStream printStream, int i) {
        SourceError sourceError = this.firstError;
        while (sourceError != null) {
            i--;
            if (i >= 0) {
                sourceError.println(printStream);
                sourceError = sourceError.next;
            } else {
                return;
            }
        }
    }

    public void printAll(PrintWriter printWriter, int i) {
        SourceError sourceError = this.firstError;
        while (sourceError != null) {
            i--;
            if (i >= 0) {
                sourceError.println(printWriter);
                sourceError = sourceError.next;
            } else {
                return;
            }
        }
    }

    public String toString(int i) {
        if (this.firstError == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (SourceError sourceError = this.firstError; sourceError != null; sourceError = sourceError.next) {
            i--;
            if (i < 0) {
                break;
            }
            stringBuffer.append(sourceError);
            stringBuffer.append(10);
        }
        return stringBuffer.toString();
    }

    public boolean checkErrors(PrintWriter printWriter, int i) {
        if (this.firstError == null) {
            return false;
        }
        printAll(printWriter, i);
        this.lastError = null;
        this.firstError = null;
        int i2 = this.errorCount;
        this.errorCount = 0;
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean checkErrors(PrintStream printStream, int i) {
        if (this.firstError == null) {
            return false;
        }
        printAll(printStream, i);
        this.lastError = null;
        this.firstError = null;
        int i2 = this.errorCount;
        this.errorCount = 0;
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public final void setSourceLocator(SourceLocator sourceLocator) {
        if (sourceLocator == this) {
            sourceLocator = null;
        }
        this.locator = sourceLocator;
    }

    public final SourceLocator swapSourceLocator(SourceLocator sourceLocator) {
        SourceLocator sourceLocator2 = this.locator;
        this.locator = sourceLocator;
        return sourceLocator2;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        this.locator = null;
        this.current_line = sourceLocator.getLineNumber();
        this.current_column = sourceLocator.getColumnNumber();
        this.current_filename = sourceLocator.getFileName();
    }

    public String getPublicId() {
        SourceLocator sourceLocator = this.locator;
        if (sourceLocator == null) {
            return null;
        }
        return sourceLocator.getPublicId();
    }

    public String getSystemId() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_filename : sourceLocator.getSystemId();
    }

    public final String getFileName() {
        return this.current_filename;
    }

    public final int getLineNumber() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_line : sourceLocator.getLineNumber();
    }

    public final int getColumnNumber() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_column : sourceLocator.getColumnNumber();
    }

    public void setFile(String str) {
        this.current_filename = str;
    }

    public void setLine(int i) {
        this.current_line = i;
    }

    public void setColumn(int i) {
        this.current_column = i;
    }

    public void setLine(String str, int i, int i2) {
        this.current_filename = str;
        this.current_line = i;
        this.current_column = i2;
    }
}
