package net.lingala.zip4j.io;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.crypto.IDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.unzip.UnzipEngine;

public class PartInputStream extends BaseInputStream {
    private byte[] aesBlockByte = new byte[16];
    private int aesBytesReturned = 0;
    private long bytesRead;
    private int count = -1;
    private IDecrypter decrypter;
    private boolean isAESEncryptedFile = false;
    private long length;
    private byte[] oneByteBuff = new byte[1];
    private RandomAccessFile raf;
    private UnzipEngine unzipEngine;

    public PartInputStream(RandomAccessFile randomAccessFile, long j, long j2, UnzipEngine unzipEngine2) {
        boolean z = true;
        this.raf = randomAccessFile;
        this.unzipEngine = unzipEngine2;
        this.decrypter = unzipEngine2.getDecrypter();
        this.bytesRead = 0;
        this.length = j2;
        this.isAESEncryptedFile = (!unzipEngine2.getFileHeader().isEncrypted() || unzipEngine2.getFileHeader().getEncryptionMethod() != 99) ? false : z;
    }

    public int available() {
        long j = this.length - this.bytesRead;
        return j > 2147483647L ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) j;
    }

    public int read() throws IOException {
        if (this.bytesRead >= this.length) {
            return -1;
        }
        if (this.isAESEncryptedFile) {
            int i = this.aesBytesReturned;
            if (i == 0 || i == 16) {
                if (read(this.aesBlockByte) == -1) {
                    return -1;
                }
                this.aesBytesReturned = 0;
            }
            byte[] bArr = this.aesBlockByte;
            int i2 = this.aesBytesReturned;
            this.aesBytesReturned = i2 + 1;
            return bArr[i2] & Ev3Constants.Opcode.TST;
        } else if (read(this.oneByteBuff, 0, 1) == -1) {
            return -1;
        } else {
            return this.oneByteBuff[0] & Ev3Constants.Opcode.TST;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        long j = this.length;
        long j2 = this.bytesRead;
        if (((long) i2) <= j - j2 || (i2 = (int) (j - j2)) != 0) {
            if ((this.unzipEngine.getDecrypter() instanceof AESDecrypter) && this.bytesRead + ((long) i2) < this.length && (i3 = i2 % 16) != 0) {
                i2 -= i3;
            }
            synchronized (this.raf) {
                int read = this.raf.read(bArr, i, i2);
                this.count = read;
                if (read < i2 && this.unzipEngine.getZipModel().isSplitArchive()) {
                    this.raf.close();
                    RandomAccessFile startNextSplitFile = this.unzipEngine.startNextSplitFile();
                    this.raf = startNextSplitFile;
                    if (this.count < 0) {
                        this.count = 0;
                    }
                    int i4 = this.count;
                    int read2 = startNextSplitFile.read(bArr, i4, i2 - i4);
                    if (read2 > 0) {
                        this.count += read2;
                    }
                }
            }
            int i5 = this.count;
            if (i5 > 0) {
                IDecrypter iDecrypter = this.decrypter;
                if (iDecrypter != null) {
                    try {
                        iDecrypter.decryptData(bArr, i, i5);
                    } catch (ZipException e) {
                        throw new IOException(e.getMessage());
                    }
                }
                this.bytesRead += (long) this.count;
            }
            if (this.bytesRead >= this.length) {
                checkAndReadAESMacBytes();
            }
            return this.count;
        }
        checkAndReadAESMacBytes();
        return -1;
    }

    /* access modifiers changed from: protected */
    public void checkAndReadAESMacBytes() throws IOException {
        IDecrypter iDecrypter;
        if (this.isAESEncryptedFile && (iDecrypter = this.decrypter) != null && (iDecrypter instanceof AESDecrypter) && ((AESDecrypter) iDecrypter).getStoredMac() == null) {
            byte[] bArr = new byte[10];
            int read = this.raf.read(bArr);
            if (read != 10) {
                if (this.unzipEngine.getZipModel().isSplitArchive()) {
                    this.raf.close();
                    RandomAccessFile startNextSplitFile = this.unzipEngine.startNextSplitFile();
                    this.raf = startNextSplitFile;
                    startNextSplitFile.read(bArr, read, 10 - read);
                } else {
                    throw new IOException("Error occured while reading stored AES authentication bytes");
                }
            }
            ((AESDecrypter) this.unzipEngine.getDecrypter()).setStoredMac(bArr);
        }
    }

    public long skip(long j) throws IOException {
        if (j >= 0) {
            long j2 = this.length;
            long j3 = this.bytesRead;
            if (j > j2 - j3) {
                j = j2 - j3;
            }
            this.bytesRead = j3 + j;
            return j;
        }
        throw new IllegalArgumentException();
    }

    public void close() throws IOException {
        this.raf.close();
    }

    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    public UnzipEngine getUnzipEngine() {
        return this.unzipEngine;
    }
}
