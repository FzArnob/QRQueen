package gnu.mapping;

import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class TtyInPort extends InPort {
    protected boolean promptEmitted;
    protected Procedure prompter;
    protected OutPort tie;

    public Procedure getPrompter() {
        return this.prompter;
    }

    public void setPrompter(Procedure procedure) {
        this.prompter = procedure;
    }

    public TtyInPort(InputStream inputStream, Path path, OutPort outPort) {
        super(inputStream, path);
        setConvertCR(true);
        this.tie = outPort;
    }

    public TtyInPort(Reader reader, Path path, OutPort outPort) {
        super(reader, path);
        setConvertCR(true);
        this.tie = outPort;
    }

    public int fill(int i) throws IOException {
        int read = this.in.read(this.buffer, this.pos, i);
        OutPort outPort = this.tie;
        if (outPort != null && read > 0) {
            outPort.echo(this.buffer, this.pos, read);
        }
        return read;
    }

    public void emitPrompt(String str) throws IOException {
        this.tie.print(str);
        this.tie.flush();
        this.tie.clearBuffer();
    }

    public void lineStart(boolean z) throws IOException {
        String obj;
        if (!z) {
            OutPort outPort = this.tie;
            if (outPort != null) {
                outPort.freshLine();
            }
            Procedure procedure = this.prompter;
            if (procedure != null) {
                try {
                    Object apply1 = procedure.apply1(this);
                    if (apply1 != null && (obj = apply1.toString()) != null && obj.length() > 0) {
                        emitPrompt(obj);
                        this.promptEmitted = true;
                    }
                } catch (Throwable th) {
                    throw new IOException("Error when evaluating prompt:" + th);
                }
            }
        }
    }

    public int read() throws IOException {
        OutPort outPort = this.tie;
        if (outPort != null) {
            outPort.flush();
        }
        int read = super.read();
        if (read < 0) {
            boolean z = this.promptEmitted;
            OutPort outPort2 = this.tie;
            if (z && (outPort2 != null)) {
                outPort2.println();
            }
        }
        this.promptEmitted = false;
        return read;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        OutPort outPort = this.tie;
        if (outPort != null) {
            outPort.flush();
        }
        int read = super.read(cArr, i, i2);
        if (read < 0) {
            boolean z = this.promptEmitted;
            OutPort outPort2 = this.tie;
            if (z && (outPort2 != null)) {
                outPort2.println();
            }
        }
        this.promptEmitted = false;
        return read;
    }
}
