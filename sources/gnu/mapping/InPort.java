package gnu.mapping;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class InPort extends LineBufferedReader implements Printable {
    public static final ThreadLocation inLocation;
    private static InPort systemInPort = new TtyInPort(System.in, Path.valueOf("/dev/stdin"), OutPort.outInitial);

    public InPort(Reader reader) {
        super(reader);
    }

    public InPort(Reader reader, Path path) {
        this(reader);
        setPath(path);
    }

    public InPort(InputStream inputStream) {
        super(inputStream);
    }

    public InPort(InputStream inputStream, Path path) {
        this(inputStream);
        setPath(path);
    }

    public static Reader convertToReader(InputStream inputStream, Object obj) {
        if (obj == null || obj == Boolean.TRUE) {
            return new InputStreamReader(inputStream);
        }
        String obj2 = obj == Boolean.FALSE ? "8859_1" : obj.toString();
        try {
            return new InputStreamReader(inputStream, obj2);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("unknown character encoding: " + obj2);
        }
    }

    public InPort(InputStream inputStream, Path path, Object obj) throws UnsupportedEncodingException {
        this(convertToReader(inputStream, obj), path);
        if (obj == Boolean.FALSE) {
            try {
                setBuffer(new char[2048]);
            } catch (IOException unused) {
            }
        } else {
            setConvertCR(true);
        }
    }

    static {
        ThreadLocation threadLocation = new ThreadLocation("in-default");
        inLocation = threadLocation;
        threadLocation.setGlobal(systemInPort);
    }

    public static InPort inDefault() {
        return (InPort) inLocation.get();
    }

    public static void setInDefault(InPort inPort) {
        inLocation.set(inPort);
    }

    public static InPort openFile(Object obj) throws IOException {
        Path valueOf = Path.valueOf(obj);
        return openFile(new BufferedInputStream(valueOf.openInputStream()), valueOf);
    }

    public static InPort openFile(InputStream inputStream, Object obj) throws UnsupportedEncodingException {
        return new InPort(inputStream, Path.valueOf(obj), Environment.user().get((Object) "port-char-encoding"));
    }

    public void print(Consumer consumer) {
        consumer.write("#<input-port");
        String name = getName();
        if (name != null) {
            consumer.write(32);
            consumer.write(name);
        }
        consumer.write(62);
    }
}
