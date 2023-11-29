package gnu.mapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class LogWriter extends FilterWriter {
    private Writer log;

    public LogWriter(Writer writer) {
        super(writer);
    }

    public final Writer getLogFile() {
        return this.log;
    }

    public void setLogFile(Writer writer) {
        this.log = writer;
    }

    public void setLogFile(String str) throws IOException {
        this.log = new PrintWriter(new BufferedWriter(new FileWriter(str)));
    }

    public void closeLogFile() throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.close();
        }
        this.log = null;
    }

    public void write(int i) throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.write(i);
        }
        super.write(i);
    }

    public void echo(char[] cArr, int i, int i2) throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.write(cArr, i, i2);
        }
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.write(cArr, i, i2);
        }
        super.write(cArr, i, i2);
    }

    public void write(String str, int i, int i2) throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.write(str, i, i2);
        }
        super.write(str, i, i2);
    }

    public void flush() throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.flush();
        }
        super.flush();
    }

    public void close() throws IOException {
        Writer writer = this.log;
        if (writer != null) {
            writer.close();
        }
        super.close();
    }
}
