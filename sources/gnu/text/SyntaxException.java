package gnu.text;

import java.io.PrintWriter;

public class SyntaxException extends Exception {
    String header;
    public int maxToPrint = 10;
    SourceMessages messages;

    public SyntaxException(SourceMessages sourceMessages) {
        this.messages = sourceMessages;
    }

    public SyntaxException(String str, SourceMessages sourceMessages) {
        this.header = str;
        this.messages = sourceMessages;
    }

    public final String getHeader() {
        return this.header;
    }

    public final void setHeader(String str) {
        this.header = str;
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void printAll(PrintWriter printWriter, int i) {
        String str = this.header;
        if (str != null) {
            printWriter.println(str);
        }
        this.messages.printAll(printWriter, i);
    }

    public void clear() {
        this.messages.clear();
    }

    public String getMessage() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.header;
        if (str != null) {
            stringBuffer.append(str);
        }
        int i = this.maxToPrint;
        for (SourceError sourceError = this.messages.firstError; sourceError != null; sourceError = sourceError.next) {
            i--;
            if (i < 0) {
                break;
            }
            stringBuffer.append(10);
            stringBuffer.append(sourceError);
        }
        return stringBuffer.toString();
    }
}
