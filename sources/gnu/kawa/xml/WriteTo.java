package gnu.kawa.xml;

import gnu.mapping.OutPort;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.xml.XMLPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class WriteTo extends Procedure2 {
    public static final WriteTo writeTo = new WriteTo();
    public static final WriteTo writeToIfChanged;
    boolean ifChanged;

    static {
        WriteTo writeTo2 = new WriteTo();
        writeToIfChanged = writeTo2;
        writeTo2.ifChanged = true;
    }

    public static void writeTo(Object obj, Path path, OutputStream outputStream) throws Throwable {
        OutPort outPort = new OutPort(outputStream, path);
        XMLPrinter xMLPrinter = new XMLPrinter(outPort, false);
        if ("html".equals(path.getExtension())) {
            xMLPrinter.setStyle("html");
        }
        Values.writeValues(obj, xMLPrinter);
        outPort.close();
    }

    public static void writeTo(Object obj, Object obj2) throws Throwable {
        Path valueOf = Path.valueOf(obj2);
        writeTo(obj, valueOf, valueOf.openOutputStream());
    }

    public static void writeToIfChanged(Object obj, Object obj2) throws Throwable {
        Path valueOf = Path.valueOf(obj2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeTo(obj, valueOf, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(valueOf.openInputStream());
            int i = 0;
            while (true) {
                int read = bufferedInputStream.read();
                boolean z = i == byteArray.length;
                if (read >= 0) {
                    if (z) {
                        break;
                    }
                    int i2 = i + 1;
                    if (byteArray[i] != read) {
                        break;
                    }
                    i = i2;
                } else if (z) {
                    bufferedInputStream.close();
                    return;
                }
            }
            bufferedInputStream.close();
        } catch (Throwable unused) {
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(valueOf.openOutputStream());
        bufferedOutputStream.write(byteArray);
        bufferedOutputStream.close();
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        if (this.ifChanged) {
            writeToIfChanged(obj, obj2.toString());
        } else {
            writeTo(obj, obj2.toString());
        }
        return Values.empty;
    }
}
