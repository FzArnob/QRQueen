package com.google.appinventor.components.runtime.util;

import android.util.Log;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import kawa.Shell;
import kawa.Telnet;
import kawa.TelnetInputStream;
import kawa.TelnetOutputStream;

public class TelnetRepl extends Procedure0 {
    private Language B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Socket f304B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public TelnetRepl(Language language, Socket socket) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = language;
        this.f304B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = socket;
    }

    public Object apply0() {
        Thread currentThread = Thread.currentThread();
        if (currentThread.getContextClassLoader() == null) {
            currentThread.setContextClassLoader(Telnet.class.getClassLoader());
        }
        try {
            Shell.run(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, Environment.getCurrent());
            Values values = Values.empty;
            try {
                this.f304B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.close();
            } catch (IOException unused) {
            }
            return values;
        } catch (RuntimeException e) {
            Log.d("TelnetRepl", "Repl is exiting with error " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Throwable th) {
            try {
                this.f304B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    public static Thread serve(Language language, Socket socket) throws IOException {
        Telnet telnet = new Telnet(socket, true);
        TelnetOutputStream outputStream = telnet.getOutputStream();
        TelnetInputStream inputStream = telnet.getInputStream();
        OutPort outPort = new OutPort((OutputStream) outputStream, (Path) FilePath.valueOf("/dev/stdout"));
        BiggerFuture biggerFuture = new BiggerFuture(new TelnetRepl(language, socket), new TtyInPort((InputStream) inputStream, (Path) FilePath.valueOf("/dev/stdin"), outPort), outPort, outPort, "Telnet Repl Thread", 262144);
        biggerFuture.start();
        return biggerFuture;
    }
}
