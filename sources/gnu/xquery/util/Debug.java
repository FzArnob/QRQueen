package gnu.xquery.util;

import gnu.mapping.OutPort;
import gnu.mapping.WrappedException;
import gnu.xml.XMLPrinter;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Debug {
    public static String traceFilename = "XQuery-trace.log";
    public static OutPort tracePort = null;
    public static String tracePrefix = "XQuery-trace: ";
    public static boolean traceShouldAppend = false;
    public static boolean traceShouldFlush = true;

    public static synchronized Object trace(Object obj, Object obj2) {
        synchronized (Debug.class) {
            OutPort outPort = tracePort;
            if (outPort == null) {
                try {
                    outPort = new OutPort((OutputStream) new FileOutputStream(traceFilename, traceShouldAppend));
                } catch (Throwable th) {
                    new WrappedException("Could not open '" + traceFilename + "' for fn:trace output", th);
                }
                tracePort = outPort;
            }
            outPort.print(tracePrefix);
            outPort.print(obj2);
            outPort.print(' ');
            new XMLPrinter(outPort, false).writeObject(obj);
            outPort.println();
            if (traceShouldFlush) {
                outPort.flush();
            }
        }
        return obj;
    }
}
