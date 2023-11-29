package net.lingala.zip4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class SplitOutputStream extends OutputStream {
    private long bytesWrittenForThisPart;
    private int currSplitFileCounter;
    private File outFile;
    private RandomAccessFile raf;
    private long splitLength;
    private File zipFile;

    public void flush() throws IOException {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SplitOutputStream(String str) throws FileNotFoundException, ZipException {
        this(Zip4jUtil.isStringNotNullAndNotEmpty(str) ? new File(str) : null);
    }

    public SplitOutputStream(File file) throws FileNotFoundException, ZipException {
        this(file, -1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SplitOutputStream(String str, long j) throws FileNotFoundException, ZipException {
        this(!Zip4jUtil.isStringNotNullAndNotEmpty(str) ? new File(str) : null, j);
    }

    public SplitOutputStream(File file, long j) throws FileNotFoundException, ZipException {
        if (j < 0 || j >= 65536) {
            this.raf = new RandomAccessFile(file, InternalZipConstants.WRITE_MODE);
            this.splitLength = j;
            this.outFile = file;
            this.zipFile = file;
            this.currSplitFileCounter = 0;
            this.bytesWrittenForThisPart = 0;
            return;
        }
        throw new ZipException("split length less than minimum allowed split length of 65536 Bytes");
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            long j = this.splitLength;
            if (j == -1) {
                this.raf.write(bArr, i, i2);
                this.bytesWrittenForThisPart += (long) i2;
            } else if (j >= 65536) {
                long j2 = this.bytesWrittenForThisPart;
                if (j2 >= j) {
                    startNextSplitFile();
                    this.raf.write(bArr, i, i2);
                    this.bytesWrittenForThisPart = (long) i2;
                    return;
                }
                long j3 = (long) i2;
                if (j2 + j3 <= j) {
                    this.raf.write(bArr, i, i2);
                    this.bytesWrittenForThisPart += j3;
                } else if (isHeaderData(bArr)) {
                    startNextSplitFile();
                    this.raf.write(bArr, i, i2);
                    this.bytesWrittenForThisPart = j3;
                } else {
                    this.raf.write(bArr, i, (int) (this.splitLength - this.bytesWrittenForThisPart));
                    startNextSplitFile();
                    RandomAccessFile randomAccessFile = this.raf;
                    long j4 = this.splitLength;
                    long j5 = this.bytesWrittenForThisPart;
                    randomAccessFile.write(bArr, i + ((int) (j4 - j5)), (int) (j3 - (j4 - j5)));
                    this.bytesWrittenForThisPart = j3 - (this.splitLength - this.bytesWrittenForThisPart);
                }
            } else {
                throw new IOException("split length less than minimum allowed split length of 65536 Bytes");
            }
        }
    }

    private void startNextSplitFile() throws IOException {
        File file;
        try {
            String zipFileNameWithoutExt = Zip4jUtil.getZipFileNameWithoutExt(this.outFile.getName());
            String absolutePath = this.zipFile.getAbsolutePath();
            String stringBuffer = this.outFile.getParent() == null ? "" : new StringBuffer(String.valueOf(this.outFile.getParent())).append(System.getProperty("file.separator")).toString();
            if (this.currSplitFileCounter < 9) {
                file = new File(new StringBuffer(String.valueOf(stringBuffer)).append(zipFileNameWithoutExt).append(".z0").append(this.currSplitFileCounter + 1).toString());
            } else {
                file = new File(new StringBuffer(String.valueOf(stringBuffer)).append(zipFileNameWithoutExt).append(".z").append(this.currSplitFileCounter + 1).toString());
            }
            this.raf.close();
            if (file.exists()) {
                throw new IOException(new StringBuffer("split file: ").append(file.getName()).append(" already exists in the current directory, cannot rename this file").toString());
            } else if (this.zipFile.renameTo(file)) {
                this.zipFile = new File(absolutePath);
                this.raf = new RandomAccessFile(this.zipFile, InternalZipConstants.WRITE_MODE);
                this.currSplitFileCounter++;
            } else {
                throw new IOException("cannot rename newly created split file");
            }
        } catch (ZipException e) {
            throw new IOException(e.getMessage());
        }
    }

    private boolean isHeaderData(byte[] bArr) {
        if (bArr != null && bArr.length >= 4) {
            int readIntLittleEndian = Raw.readIntLittleEndian(bArr, 0);
            long[] allHeaderSignatures = Zip4jUtil.getAllHeaderSignatures();
            if (allHeaderSignatures != null && allHeaderSignatures.length > 0) {
                for (long j : allHeaderSignatures) {
                    if (j != 134695760 && j == ((long) readIntLittleEndian)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkBuffSizeAndStartNextSplitFile(int i) throws ZipException {
        if (i < 0) {
            throw new ZipException("negative buffersize for checkBuffSizeAndStartNextSplitFile");
        } else if (isBuffSizeFitForCurrSplitFile(i)) {
            return false;
        } else {
            try {
                startNextSplitFile();
                this.bytesWrittenForThisPart = 0;
                return true;
            } catch (IOException e) {
                throw new ZipException((Throwable) e);
            }
        }
    }

    public boolean isBuffSizeFitForCurrSplitFile(int i) throws ZipException {
        if (i >= 0) {
            long j = this.splitLength;
            if (j < 65536 || this.bytesWrittenForThisPart + ((long) i) <= j) {
                return true;
            }
            return false;
        }
        throw new ZipException("negative buffersize for isBuffSizeFitForCurrSplitFile");
    }

    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    public long getFilePointer() throws IOException {
        return this.raf.getFilePointer();
    }

    public boolean isSplitZipFile() {
        return this.splitLength != -1;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    public int getCurrSplitFileCounter() {
        return this.currSplitFileCounter;
    }
}
