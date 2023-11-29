package kawa;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TelnetInputStream extends FilterInputStream {
    static final int SB_IAC = 400;
    protected byte[] buf = new byte[512];
    Telnet connection;
    int count;
    int pos;
    int state = 0;
    int subCommandLength = 0;

    public TelnetInputStream(InputStream inputStream, Telnet telnet) throws IOException {
        super(inputStream);
        this.connection = telnet;
    }

    public int read() throws IOException {
        while (true) {
            if (this.pos >= this.count) {
                int available = this.in.available();
                if (available <= 0) {
                    available = 1;
                } else {
                    byte[] bArr = this.buf;
                    int length = bArr.length;
                    int i = this.subCommandLength;
                    if (available > length - i) {
                        available = bArr.length - i;
                    }
                }
                int read = this.in.read(this.buf, this.subCommandLength, available);
                this.pos = this.subCommandLength;
                this.count = read;
                if (read <= 0) {
                    return -1;
                }
            }
            byte[] bArr2 = this.buf;
            int i2 = this.pos;
            this.pos = i2 + 1;
            byte b = bArr2[i2] & Ev3Constants.Opcode.TST;
            int i3 = this.state;
            if (i3 == 0) {
                if (b != 255) {
                    return b;
                }
                this.state = 255;
            } else if (i3 == 255) {
                if (b == 255) {
                    this.state = 0;
                    return 255;
                } else if (b == 251 || b == 252 || b == 253 || b == 254 || b == 250) {
                    this.state = b;
                } else if (b == 244) {
                    System.err.println("Interrupt Process");
                    this.state = 0;
                } else if (b == 236) {
                    return -1;
                } else {
                    this.state = 0;
                }
            } else if (i3 == 251 || i3 == 252 || i3 == 253 || i3 == 254) {
                this.connection.handle(i3, b);
                this.state = 0;
            } else if (i3 == 250) {
                if (b == 255) {
                    this.state = SB_IAC;
                } else {
                    int i4 = this.subCommandLength;
                    this.subCommandLength = i4 + 1;
                    bArr2[i4] = (byte) b;
                }
            } else if (i3 != SB_IAC) {
                PrintStream printStream = System.err;
                printStream.println("Bad state " + this.state);
            } else if (b == 255) {
                int i5 = this.subCommandLength;
                this.subCommandLength = i5 + 1;
                bArr2[i5] = (byte) b;
                this.state = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            } else if (b == 240) {
                this.connection.subCommand(bArr2, 0, this.subCommandLength);
                this.state = 0;
                this.subCommandLength = 0;
            } else {
                this.state = 0;
                this.subCommandLength = 0;
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte b;
        int i3 = 0;
        if (i2 <= 0) {
            return 0;
        }
        if (this.state != 0 || this.pos >= this.count) {
            int read = read();
            if (read < 0) {
                return read;
            }
            bArr[i] = (byte) read;
            i3 = 1;
            i++;
        }
        if (this.state == 0) {
            while (true) {
                int i4 = this.pos;
                if (i4 >= this.count || i3 >= i2 || (b = this.buf[i4]) == -1) {
                    break;
                }
                bArr[i] = b;
                i3++;
                this.pos = i4 + 1;
                i++;
            }
        }
        return i3;
    }
}
