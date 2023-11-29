package gnu.mapping;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PrintConsumer;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import gnu.text.Printable;
import gnu.text.WriterManager;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.NumberFormat;

public class OutPort extends PrintConsumer implements Printable {
    private static OutPort errInitial = new OutPort(new LogWriter(new OutputStreamWriter(System.err)), true, true, Path.valueOf("/dev/stderr"));
    public static final ThreadLocation errLocation;
    static Writer logFile;
    static OutPort outInitial = new OutPort(new LogWriter(new BufferedWriter(new OutputStreamWriter(System.out))), true, true, Path.valueOf("/dev/stdout"));
    public static final ThreadLocation outLocation;
    private Writer base;
    protected PrettyWriter bout;
    NumberFormat numberFormat;
    public AbstractFormat objectFormat;
    Path path;
    public boolean printReadable;
    protected Object unregisterRef;

    /* access modifiers changed from: protected */
    public boolean closeOnExit() {
        return true;
    }

    protected OutPort(Writer writer, PrettyWriter prettyWriter, boolean z) {
        super((Writer) prettyWriter, z);
        this.bout = prettyWriter;
        this.base = writer;
        if (closeOnExit()) {
            this.unregisterRef = WriterManager.instance.register(prettyWriter);
        }
    }

    protected OutPort(OutPort outPort, boolean z) {
        this((Writer) outPort, outPort.bout, z);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected OutPort(Writer writer, boolean z) {
        this(writer, writer instanceof OutPort ? ((OutPort) writer).bout : new PrettyWriter(writer, true), z);
    }

    public OutPort(Writer writer, boolean z, boolean z2) {
        this(writer, new PrettyWriter(writer, z), z2);
    }

    public OutPort(Writer writer, boolean z, boolean z2, Path path2) {
        this(writer, new PrettyWriter(writer, z), z2);
        this.path = path2;
    }

    public OutPort(OutputStream outputStream) {
        this(outputStream, (Path) null);
    }

    public OutPort(OutputStream outputStream, Path path2) {
        this((Writer) new OutputStreamWriter(outputStream), true, path2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OutPort(Writer writer) {
        this(writer, writer instanceof OutPort ? ((OutPort) writer).bout : new PrettyWriter(writer, false), false);
    }

    public OutPort(Writer writer, Path path2) {
        this(writer, false, false);
        this.path = path2;
    }

    public OutPort(Writer writer, boolean z, Path path2) {
        this(writer, false, z);
        this.path = path2;
    }

    static {
        ThreadLocation threadLocation = new ThreadLocation("out-default");
        outLocation = threadLocation;
        threadLocation.setGlobal(outInitial);
        ThreadLocation threadLocation2 = new ThreadLocation("err-default");
        errLocation = threadLocation2;
        threadLocation2.setGlobal(errInitial);
    }

    public static OutPort outDefault() {
        return (OutPort) outLocation.get();
    }

    public static void setOutDefault(OutPort outPort) {
        outLocation.set(outPort);
    }

    public static OutPort errDefault() {
        return (OutPort) errLocation.get();
    }

    public static void setErrDefault(OutPort outPort) {
        errLocation.set(outPort);
    }

    public static OutPort openFile(Object obj) throws IOException {
        OutputStreamWriter outputStreamWriter;
        Object obj2 = Environment.user().get((Object) "port-char-encoding");
        Path valueOf = Path.valueOf(obj);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(valueOf.openOutputStream());
        if (obj2 == null || obj2 == Boolean.TRUE) {
            outputStreamWriter = new OutputStreamWriter(bufferedOutputStream);
        } else {
            if (obj2 == Boolean.FALSE) {
                obj2 = "8859_1";
            }
            outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, obj2.toString());
        }
        return new OutPort((Writer) outputStreamWriter, valueOf);
    }

    public void echo(char[] cArr, int i, int i2) throws IOException {
        Writer writer = this.base;
        if (writer instanceof LogWriter) {
            ((LogWriter) writer).echo(cArr, i, i2);
        }
    }

    public static void closeLogFile() throws IOException {
        Writer writer = logFile;
        if (writer != null) {
            writer.close();
            logFile = null;
        }
        Writer writer2 = outInitial.base;
        if (writer2 instanceof LogWriter) {
            Writer writer3 = null;
            ((LogWriter) writer2).setLogFile((Writer) null);
        }
        Writer writer4 = errInitial.base;
        if (writer4 instanceof LogWriter) {
            Writer writer5 = null;
            ((LogWriter) writer4).setLogFile((Writer) null);
        }
    }

    public static void setLogFile(String str) throws IOException {
        if (logFile != null) {
            closeLogFile();
        }
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(str)));
        logFile = printWriter;
        Writer writer = outInitial.base;
        if (writer instanceof LogWriter) {
            ((LogWriter) writer).setLogFile((Writer) printWriter);
        }
        Writer writer2 = errInitial.base;
        if (writer2 instanceof LogWriter) {
            ((LogWriter) writer2).setLogFile(logFile);
        }
    }

    protected static final boolean isWordChar(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-' || c == '+';
    }

    public void print(int i) {
        NumberFormat numberFormat2 = this.numberFormat;
        if (numberFormat2 == null) {
            super.print(i);
        } else {
            print(numberFormat2.format((long) i));
        }
    }

    public void print(long j) {
        NumberFormat numberFormat2 = this.numberFormat;
        if (numberFormat2 == null) {
            super.print(j);
        } else {
            print(numberFormat2.format(j));
        }
    }

    public void print(double d) {
        NumberFormat numberFormat2 = this.numberFormat;
        if (numberFormat2 == null) {
            super.print(d);
        } else {
            print(numberFormat2.format(d));
        }
    }

    public void print(float f) {
        NumberFormat numberFormat2 = this.numberFormat;
        if (numberFormat2 == null) {
            super.print(f);
        } else {
            print(numberFormat2.format((double) f));
        }
    }

    public void print(boolean z) {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat == null) {
            super.print(z);
        } else {
            abstractFormat.writeBoolean(z, this);
        }
    }

    public void print(String str) {
        if (str == null) {
            str = "(null)";
        }
        write(str);
    }

    public void print(Object obj) {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat != null) {
            abstractFormat.writeObject(obj, (PrintConsumer) this);
        } else if (obj instanceof Consumable) {
            ((Consumable) obj).consume(this);
        } else {
            if (obj == null) {
                obj = "null";
            }
            super.print(obj);
        }
    }

    public void print(Consumer consumer) {
        consumer.write("#<output-port");
        if (this.path != null) {
            consumer.write(32);
            consumer.write(this.path.toString());
        }
        consumer.write(62);
    }

    public void startElement(Object obj) {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat != null) {
            abstractFormat.startElement(obj, this);
            return;
        }
        print('(');
        print(obj);
    }

    public void endElement() {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat != null) {
            abstractFormat.endElement(this);
        } else {
            print(')');
        }
    }

    public void startAttribute(Object obj) {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat != null) {
            abstractFormat.startAttribute(obj, this);
            return;
        }
        print(' ');
        print(obj);
        print(": ");
    }

    public void endAttribute() {
        AbstractFormat abstractFormat = this.objectFormat;
        if (abstractFormat != null) {
            abstractFormat.endAttribute(this);
        } else {
            print(' ');
        }
    }

    public void writeWordEnd() {
        this.bout.writeWordEnd();
    }

    public void writeWordStart() {
        this.bout.writeWordStart();
    }

    public void freshLine() {
        if (this.bout.getColumnNumber() != 0) {
            println();
        }
    }

    public int getColumnNumber() {
        return this.bout.getColumnNumber();
    }

    public void setColumnNumber(int i) {
        this.bout.setColumnNumber(i);
    }

    public void clearBuffer() {
        this.bout.clearBuffer();
    }

    public void closeThis() {
        try {
            Writer writer = this.base;
            if (!(writer instanceof OutPort) || ((OutPort) writer).bout != this.bout) {
                this.bout.closeThis();
            }
        } catch (IOException unused) {
            setError();
        }
        WriterManager.instance.unregister(this.unregisterRef);
    }

    public void close() {
        try {
            Writer writer = this.base;
            if (!(writer instanceof OutPort) || ((OutPort) writer).bout != this.bout) {
                this.out.close();
            } else {
                writer.close();
            }
        } catch (IOException unused) {
            setError();
        }
        WriterManager.instance.unregister(this.unregisterRef);
    }

    public static void runCleanups() {
        WriterManager.instance.run();
    }

    public void startLogicalBlock(String str, boolean z, String str2) {
        this.bout.startLogicalBlock(str, z, str2);
    }

    public void startLogicalBlock(String str, String str2, int i) {
        this.bout.startLogicalBlock(str, false, str2);
        PrettyWriter prettyWriter = this.bout;
        if (str != null) {
            i -= str.length();
        }
        prettyWriter.addIndentation(i, false);
    }

    public void endLogicalBlock(String str) {
        this.bout.endLogicalBlock(str);
    }

    public void writeBreak(int i) {
        this.bout.writeBreak(i);
    }

    public void writeSpaceLinear() {
        write(32);
        writeBreak(78);
    }

    public void writeBreakLinear() {
        writeBreak(78);
    }

    public void writeSpaceFill() {
        write(32);
        writeBreak(70);
    }

    public void writeBreakFill() {
        writeBreak(70);
    }

    public void setIndentation(int i, boolean z) {
        this.bout.addIndentation(i, z);
    }
}
