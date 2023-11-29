package kawa;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Telnet implements Runnable {
    public static final int DO = 253;
    public static final int DONT = 254;
    public static final int ECHO = 1;
    static final int EOF = 236;
    static final int IAC = 255;
    static final int IP = 244;
    static final int LINEMODE = 34;
    static final int NAWS = 31;
    static final int NOP = 241;
    static final int OPTION_NO = 0;
    static final int OPTION_WANTNO = 1;
    static final int OPTION_WANTNO_OPPOSITE = 2;
    static final int OPTION_WANTYES = 3;
    static final int OPTION_WANTYES_OPPOSITE = 4;
    static final int OPTION_YES = 5;
    static final int SB = 250;
    static final int SE = 240;
    public static final int SUPPRESS_GO_AHEAD = 3;
    static final int TM = 6;
    static final int TTYPE = 24;
    public static final int WILL = 251;
    public static final int WONT = 252;
    TelnetInputStream in;
    boolean isServer;
    final byte[] optionsState = new byte[256];
    TelnetOutputStream out;
    final byte preferredLineMode = 3;
    InputStream sin;
    OutputStream sout;
    public byte[] terminalType;
    public short windowHeight;
    public short windowWidth;

    public TelnetInputStream getInputStream() {
        return this.in;
    }

    public TelnetOutputStream getOutputStream() {
        return this.out;
    }

    /* access modifiers changed from: package-private */
    public boolean change(int i, int i2) {
        if (i2 == 6) {
            return true;
        }
        boolean z = this.isServer;
        if (z && i2 == 31) {
            return true;
        }
        if (z && i == 251 && i2 == 34) {
            try {
                this.out.writeSubCommand(34, new byte[]{1, 3});
            } catch (IOException unused) {
            }
            return true;
        } else if (z && i == 251 && i2 == 24) {
            try {
                this.out.writeSubCommand(i2, new byte[]{1});
            } catch (IOException unused2) {
            }
            return true;
        } else if (z || i2 != 1 || i == 253 || i != 251) {
            return false;
        } else {
            return true;
        }
    }

    public void subCommand(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        if (b != 24) {
            int i3 = 2;
            if (b != 31) {
                if (b == 34) {
                    PrintStream printStream = System.err;
                    printStream.println("SBCommand LINEMODE " + bArr[1] + " len:" + i2);
                    if (bArr[1] == 3) {
                        while (true) {
                            int i4 = i3 + 2;
                            if (i4 < i2) {
                                PrintStream printStream2 = System.err;
                                printStream2.println("  " + bArr[i3] + "," + bArr[i3 + 1] + "," + bArr[i4]);
                                i3 += 3;
                            } else {
                                return;
                            }
                        }
                    }
                }
            } else if (i2 == 5) {
                this.windowWidth = (short) ((bArr[1] << 8) + (bArr[2] & Ev3Constants.Opcode.TST));
                this.windowHeight = (short) ((bArr[3] << 8) + (bArr[4] & Ev3Constants.Opcode.TST));
            }
        } else {
            int i5 = i2 - 1;
            byte[] bArr2 = new byte[i5];
            System.arraycopy(bArr, 1, bArr2, 0, i5);
            this.terminalType = bArr2;
            PrintStream printStream3 = System.err;
            printStream3.println("terminal type: '" + new String(bArr2) + "'");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handle(int r14, int r15) throws java.io.IOException {
        /*
            r13 = this;
            r0 = 253(0xfd, float:3.55E-43)
            r1 = 1
            r2 = 0
            if (r14 >= r0) goto L_0x0008
            r3 = 1
            goto L_0x0009
        L_0x0008:
            r3 = 0
        L_0x0009:
            r4 = r14 & 1
            if (r4 == 0) goto L_0x000f
            r4 = 1
            goto L_0x0010
        L_0x000f:
            r4 = 0
        L_0x0010:
            byte[] r5 = r13.optionsState
            byte r5 = r5[r15]
            if (r3 == 0) goto L_0x0019
            int r5 = r5 >> 3
            byte r5 = (byte) r5
        L_0x0019:
            int r6 = r5 >> 3
            r6 = r6 & 7
            r7 = 251(0xfb, float:3.52E-43)
            r8 = 254(0xfe, float:3.56E-43)
            r9 = 252(0xfc, float:3.53E-43)
            r10 = 5
            r11 = 3
            if (r6 == 0) goto L_0x0067
            if (r6 == r1) goto L_0x0065
            r12 = 2
            if (r6 == r12) goto L_0x0059
            if (r6 == r11) goto L_0x0052
            r0 = 4
            if (r6 == r0) goto L_0x0045
            if (r6 == r10) goto L_0x0034
            goto L_0x0085
        L_0x0034:
            if (r4 == 0) goto L_0x0037
            return
        L_0x0037:
            r13.change(r14, r15)
            kawa.TelnetOutputStream r14 = r13.out
            if (r3 == 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            r8 = 252(0xfc, float:3.53E-43)
        L_0x0041:
            r14.writeCommand(r8, r15)
            goto L_0x0065
        L_0x0045:
            if (r4 == 0) goto L_0x0065
            kawa.TelnetOutputStream r14 = r13.out
            if (r3 == 0) goto L_0x004c
            goto L_0x004e
        L_0x004c:
            r8 = 252(0xfc, float:3.53E-43)
        L_0x004e:
            r14.writeCommand(r8, r15)
            goto L_0x0086
        L_0x0052:
            if (r4 == 0) goto L_0x0065
            r13.change(r14, r15)
        L_0x0057:
            r1 = 5
            goto L_0x0086
        L_0x0059:
            kawa.TelnetOutputStream r14 = r13.out
            if (r3 == 0) goto L_0x005e
            goto L_0x0060
        L_0x005e:
            r0 = 251(0xfb, float:3.52E-43)
        L_0x0060:
            r14.writeCommand(r0, r15)
            r1 = 3
            goto L_0x0086
        L_0x0065:
            r1 = 0
            goto L_0x0086
        L_0x0067:
            if (r4 != 0) goto L_0x006a
            return
        L_0x006a:
            boolean r14 = r13.change(r14, r15)
            if (r14 == 0) goto L_0x007b
            kawa.TelnetOutputStream r14 = r13.out
            if (r3 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r0 = 251(0xfb, float:3.52E-43)
        L_0x0077:
            r14.writeCommand(r0, r15)
            goto L_0x0057
        L_0x007b:
            kawa.TelnetOutputStream r14 = r13.out
            if (r3 == 0) goto L_0x0080
            goto L_0x0082
        L_0x0080:
            r8 = 252(0xfc, float:3.53E-43)
        L_0x0082:
            r14.writeCommand(r8, r15)
        L_0x0085:
            r1 = r5
        L_0x0086:
            if (r3 == 0) goto L_0x0092
            byte[] r14 = r13.optionsState
            byte r14 = r14[r15]
            r14 = r14 & 199(0xc7, float:2.79E-43)
            int r0 = r1 << 3
            r14 = r14 | r0
            goto L_0x0099
        L_0x0092:
            byte[] r14 = r13.optionsState
            byte r14 = r14[r15]
            r14 = r14 & 248(0xf8, float:3.48E-43)
            r14 = r14 | r1
        L_0x0099:
            byte r14 = (byte) r14
            byte[] r0 = r13.optionsState
            r0[r15] = r14
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Telnet.handle(int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0037, code lost:
        if (r0 == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
        if (r0 == false) goto L_0x004b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void request(int r8, int r9) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            r2 = 253(0xfd, float:3.55E-43)
            if (r8 < r2) goto L_0x0008
            r2 = 1
            goto L_0x0009
        L_0x0008:
            r2 = 0
        L_0x0009:
            r3 = r8 & 1
            if (r3 == 0) goto L_0x000e
            r0 = 1
        L_0x000e:
            byte[] r3 = r7.optionsState
            byte r3 = r3[r9]
            if (r2 == 0) goto L_0x0017
            int r3 = r3 >> 3
            byte r3 = (byte) r3
        L_0x0017:
            r4 = r3 & 7
            r5 = 2
            r6 = 3
            if (r4 == 0) goto L_0x0042
            if (r4 == r1) goto L_0x003e
            if (r4 == r5) goto L_0x003b
            r5 = 4
            if (r4 == r6) goto L_0x0032
            if (r4 == r5) goto L_0x0036
            r5 = 5
            if (r4 == r5) goto L_0x002a
            goto L_0x004a
        L_0x002a:
            if (r0 != 0) goto L_0x004a
            kawa.TelnetOutputStream r0 = r7.out
            r0.writeCommand(r8, r9)
            goto L_0x004b
        L_0x0032:
            if (r0 != 0) goto L_0x0036
            r1 = 4
            goto L_0x0037
        L_0x0036:
            r1 = r3
        L_0x0037:
            if (r0 == 0) goto L_0x004b
        L_0x0039:
            r1 = 3
            goto L_0x004b
        L_0x003b:
            if (r0 != 0) goto L_0x004a
            goto L_0x004b
        L_0x003e:
            if (r0 == 0) goto L_0x004a
            r1 = 2
            goto L_0x004b
        L_0x0042:
            if (r0 == 0) goto L_0x004a
            kawa.TelnetOutputStream r0 = r7.out
            r0.writeCommand(r8, r9)
            goto L_0x0039
        L_0x004a:
            r1 = r3
        L_0x004b:
            if (r2 == 0) goto L_0x0057
            byte[] r8 = r7.optionsState
            byte r8 = r8[r9]
            r8 = r8 & 199(0xc7, float:2.79E-43)
            int r0 = r1 << 3
            r8 = r8 | r0
            goto L_0x005e
        L_0x0057:
            byte[] r8 = r7.optionsState
            byte r8 = r8[r9]
            r8 = r8 & 248(0xf8, float:3.48E-43)
            r8 = r8 | r1
        L_0x005e:
            byte r8 = (byte) r8
            byte[] r0 = r7.optionsState
            r0[r9] = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Telnet.request(int, int):void");
    }

    static void usage() {
        System.err.println("Usage:  [java] kawa.Telnet HOST [PORT#]");
        System.exit(-1);
    }

    public static void main(String[] strArr) {
        if (strArr.length == 0) {
            usage();
        }
        String str = strArr[0];
        int i = 23;
        if (strArr.length > 1) {
            i = Integer.parseInt(strArr[1]);
        }
        try {
            Telnet telnet = new Telnet(new Socket(str, i), false);
            TelnetOutputStream outputStream = telnet.getOutputStream();
            Thread thread = new Thread(telnet);
            thread.setPriority(Thread.currentThread().getPriority() + 1);
            thread.start();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = System.in.read();
                if (read < 0) {
                    thread.stop();
                    return;
                }
                bArr[0] = (byte) read;
                int available = System.in.available();
                if (available > 0) {
                    if (available > 1023) {
                        available = 1023;
                    }
                    available = System.in.read(bArr, 1, available);
                }
                outputStream.write(bArr, 0, available + 1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public Telnet(Socket socket, boolean z) throws IOException {
        this.sin = socket.getInputStream();
        this.sout = socket.getOutputStream();
        this.out = new TelnetOutputStream(this.sout);
        this.in = new TelnetInputStream(this.sin, this);
        this.isServer = z;
    }

    public void run() {
        try {
            TelnetInputStream inputStream = getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read();
                if (read >= 0) {
                    bArr[0] = (byte) read;
                    int available = inputStream.available();
                    if (available > 0) {
                        if (available > 1023) {
                            available = 1023;
                        }
                        available = inputStream.read(bArr, 1, available);
                    }
                    System.out.write(bArr, 0, available + 1);
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
}
