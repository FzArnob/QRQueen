package kawa;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TelnetOutputStream extends FilterOutputStream {
    public TelnetOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) throws IOException {
        if (i == 255) {
            this.out.write(i);
        }
        this.out.write(i);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            if (bArr[i] == -1) {
                this.out.write(bArr, i4, (i + 1) - i4);
                i4 = i;
            }
            i++;
        }
        this.out.write(bArr, i4, i3 - i4);
    }

    public void writeCommand(int i) throws IOException {
        this.out.write(255);
        this.out.write(i);
    }

    public final void writeCommand(int i, int i2) throws IOException {
        this.out.write(255);
        this.out.write(i);
        this.out.write(i2);
    }

    public final void writeDo(int i) throws IOException {
        writeCommand(Telnet.DO, i);
    }

    public final void writeDont(int i) throws IOException {
        writeCommand(Telnet.DONT, i);
    }

    public final void writeWill(int i) throws IOException {
        writeCommand(Telnet.WILL, i);
    }

    public final void writeWont(int i) throws IOException {
        writeCommand(Telnet.WONT, i);
    }

    public final void writeSubCommand(int i, byte[] bArr) throws IOException {
        writeCommand(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i);
        write(bArr);
        writeCommand(240);
    }
}
