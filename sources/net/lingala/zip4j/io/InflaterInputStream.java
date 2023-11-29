package net.lingala.zip4j.io;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import net.lingala.zip4j.unzip.UnzipEngine;

public class InflaterInputStream extends PartInputStream {
    private byte[] buff = new byte[4096];
    private long bytesWritten;
    private Inflater inflater = new Inflater(true);
    private byte[] oneByteBuff = new byte[1];
    private long uncompressedSize;
    private UnzipEngine unzipEngine;

    public InflaterInputStream(RandomAccessFile randomAccessFile, long j, long j2, UnzipEngine unzipEngine2) {
        super(randomAccessFile, j, j2, unzipEngine2);
        this.unzipEngine = unzipEngine2;
        this.bytesWritten = 0;
        this.uncompressedSize = unzipEngine2.getFileHeader().getUncompressedSize();
    }

    public int read() throws IOException {
        if (read(this.oneByteBuff, 0, 1) == -1) {
            return -1;
        }
        return this.oneByteBuff[0] & Ev3Constants.Opcode.TST;
    }

    public int read(byte[] bArr) throws IOException {
        if (bArr != null) {
            return read(bArr, 0, bArr.length);
        }
        throw new NullPointerException("input buffer is null");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("input buffer is null");
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            try {
                if (this.bytesWritten >= this.uncompressedSize) {
                    finishInflating();
                    return -1;
                }
                while (true) {
                    int inflate = this.inflater.inflate(bArr, i, i2);
                    if (inflate != 0) {
                        this.bytesWritten += (long) inflate;
                        return inflate;
                    } else if (this.inflater.finished()) {
                        break;
                    } else if (this.inflater.needsDictionary()) {
                        break;
                    } else if (this.inflater.needsInput()) {
                        fill();
                    }
                }
                finishInflating();
                return -1;
            } catch (DataFormatException e) {
                String message = e.getMessage() != null ? e.getMessage() : "Invalid ZLIB data format";
                UnzipEngine unzipEngine2 = this.unzipEngine;
                if (unzipEngine2 != null && unzipEngine2.getLocalFileHeader().isEncrypted() && this.unzipEngine.getLocalFileHeader().getEncryptionMethod() == 0) {
                    message = new StringBuffer(String.valueOf(message)).append(" - Wrong Password?").toString();
                }
                throw new IOException(message);
            }
        }
    }

    private void finishInflating() throws IOException {
        do {
        } while (super.read(new byte[1024], 0, 1024) != -1);
        checkAndReadAESMacBytes();
    }

    private void fill() throws IOException {
        byte[] bArr = this.buff;
        int read = super.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.inflater.setInput(this.buff, 0, read);
            return;
        }
        throw new EOFException("Unexpected end of ZLIB input stream");
    }

    public long skip(long j) throws IOException {
        if (j >= 0) {
            int min = (int) Math.min(j, 2147483647L);
            byte[] bArr = new byte[512];
            int i = 0;
            while (i < min) {
                int i2 = min - i;
                if (i2 > 512) {
                    i2 = 512;
                }
                int read = read(bArr, 0, i2);
                if (read == -1) {
                    break;
                }
                i += read;
            }
            return (long) i;
        }
        throw new IllegalArgumentException("negative skip length");
    }

    public void seek(long j) throws IOException {
        super.seek(j);
    }

    public int available() {
        return this.inflater.finished() ^ true ? 1 : 0;
    }

    public void close() throws IOException {
        this.inflater.end();
        super.close();
    }

    public UnzipEngine getUnzipEngine() {
        return super.getUnzipEngine();
    }
}
