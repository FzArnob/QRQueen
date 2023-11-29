package net.lingala.zip4j.unzip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.CRC32;
import net.lingala.zip4j.core.HeaderReader;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.crypto.IDecrypter;
import net.lingala.zip4j.crypto.StandardDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.InflaterInputStream;
import net.lingala.zip4j.io.PartInputStream;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class UnzipEngine {
    private CRC32 crc;
    private int currSplitFileCounter = 0;
    private IDecrypter decrypter;
    private FileHeader fileHeader;
    private LocalFileHeader localFileHeader;
    private ZipModel zipModel;

    public UnzipEngine(ZipModel zipModel2, FileHeader fileHeader2) throws ZipException {
        if (zipModel2 == null || fileHeader2 == null) {
            throw new ZipException("Invalid parameters passed to StoreUnzip. One or more of the parameters were null");
        }
        this.zipModel = zipModel2;
        this.fileHeader = fileHeader2;
        this.crc = new CRC32();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: net.lingala.zip4j.io.ZipInputStream} */
    /* JADX WARNING: type inference failed for: r9v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r1v10, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unzipFile(net.lingala.zip4j.progress.ProgressMonitor r8, java.lang.String r9, java.lang.String r10, net.lingala.zip4j.model.UnzipParameters r11) throws net.lingala.zip4j.exception.ZipException {
        /*
            r7 = this;
            net.lingala.zip4j.model.ZipModel r0 = r7.zipModel
            if (r0 == 0) goto L_0x0074
            net.lingala.zip4j.model.FileHeader r0 = r7.fileHeader
            if (r0 == 0) goto L_0x0074
            boolean r0 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r9)
            if (r0 == 0) goto L_0x0074
            r0 = 4096(0x1000, float:5.74E-42)
            r1 = 0
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0067, Exception -> 0x005f, all -> 0x005c }
            net.lingala.zip4j.io.ZipInputStream r2 = r7.getInputStream()     // Catch:{ IOException -> 0x0067, Exception -> 0x005f, all -> 0x005c }
            java.io.FileOutputStream r1 = r7.getOutputStream(r9, r10)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
        L_0x001b:
            int r3 = r2.read(r0)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r4 = -1
            if (r3 != r4) goto L_0x0037
            r7.closeStreams(r2, r1)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            net.lingala.zip4j.model.FileHeader r8 = r7.fileHeader     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            java.lang.String r9 = r7.getOutputFileNameWithPath(r9, r10)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r0.<init>(r9)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            net.lingala.zip4j.unzip.UnzipUtil.applyFileAttributes(r8, r0, r11)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r7.closeStreams(r2, r1)
            return
        L_0x0037:
            r4 = 0
            r1.write(r0, r4, r3)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            long r5 = (long) r3     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r8.updateWorkCompleted(r5)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            boolean r3 = r8.isCancelAllTasks()     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            if (r3 == 0) goto L_0x001b
            r9 = 3
            r8.setResult(r9)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r8.setState(r4)     // Catch:{ IOException -> 0x0058, Exception -> 0x0054, all -> 0x0050 }
            r7.closeStreams(r2, r1)
            return
        L_0x0050:
            r8 = move-exception
            r9 = r1
            r1 = r2
            goto L_0x0070
        L_0x0054:
            r8 = move-exception
            r9 = r1
            r1 = r2
            goto L_0x0061
        L_0x0058:
            r8 = move-exception
            r9 = r1
            r1 = r2
            goto L_0x0069
        L_0x005c:
            r8 = move-exception
            r9 = r1
            goto L_0x0070
        L_0x005f:
            r8 = move-exception
            r9 = r1
        L_0x0061:
            net.lingala.zip4j.exception.ZipException r10 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x006f }
            r10.<init>((java.lang.Throwable) r8)     // Catch:{ all -> 0x006f }
            throw r10     // Catch:{ all -> 0x006f }
        L_0x0067:
            r8 = move-exception
            r9 = r1
        L_0x0069:
            net.lingala.zip4j.exception.ZipException r10 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x006f }
            r10.<init>((java.lang.Throwable) r8)     // Catch:{ all -> 0x006f }
            throw r10     // Catch:{ all -> 0x006f }
        L_0x006f:
            r8 = move-exception
        L_0x0070:
            r7.closeStreams(r1, r9)
            throw r8
        L_0x0074:
            net.lingala.zip4j.exception.ZipException r8 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r9 = "Invalid parameters passed during unzipping file. One or more of the parameters were null"
            r8.<init>((java.lang.String) r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.unzip.UnzipEngine.unzipFile(net.lingala.zip4j.progress.ProgressMonitor, java.lang.String, java.lang.String, net.lingala.zip4j.model.UnzipParameters):void");
    }

    public ZipInputStream getInputStream() throws ZipException {
        long j;
        if (this.fileHeader != null) {
            RandomAccessFile randomAccessFile = null;
            try {
                RandomAccessFile createFileHandler = createFileHandler("r");
                if (checkLocalHeader()) {
                    init(createFileHandler);
                    long compressedSize = this.localFileHeader.getCompressedSize();
                    long offsetStartOfData = this.localFileHeader.getOffsetStartOfData();
                    if (this.localFileHeader.isEncrypted()) {
                        if (this.localFileHeader.getEncryptionMethod() == 99) {
                            IDecrypter iDecrypter = this.decrypter;
                            if (iDecrypter instanceof AESDecrypter) {
                                compressedSize -= (long) ((((AESDecrypter) iDecrypter).getSaltLength() + ((AESDecrypter) this.decrypter).getPasswordVerifierLength()) + 10);
                                j = (long) (((AESDecrypter) this.decrypter).getSaltLength() + ((AESDecrypter) this.decrypter).getPasswordVerifierLength());
                            } else {
                                throw new ZipException(new StringBuffer("invalid decryptor when trying to calculate compressed size for AES encrypted file: ").append(this.fileHeader.getFileName()).toString());
                            }
                        } else if (this.localFileHeader.getEncryptionMethod() == 0) {
                            j = 12;
                            compressedSize -= 12;
                        }
                        offsetStartOfData += j;
                    }
                    long j2 = compressedSize;
                    long j3 = offsetStartOfData;
                    int compressionMethod = this.fileHeader.getCompressionMethod();
                    if (this.fileHeader.getEncryptionMethod() == 99) {
                        if (this.fileHeader.getAesExtraDataRecord() != null) {
                            compressionMethod = this.fileHeader.getAesExtraDataRecord().getCompressionMethod();
                        } else {
                            throw new ZipException(new StringBuffer("AESExtraDataRecord does not exist for AES encrypted file: ").append(this.fileHeader.getFileName()).toString());
                        }
                    }
                    createFileHandler.seek(j3);
                    if (compressionMethod == 0) {
                        return new ZipInputStream(new PartInputStream(createFileHandler, j3, j2, this));
                    }
                    if (compressionMethod == 8) {
                        return new ZipInputStream(new InflaterInputStream(createFileHandler, j3, j2, this));
                    }
                    throw new ZipException("compression type not supported");
                }
                throw new ZipException("local header and file header do not match");
            } catch (ZipException e) {
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused) {
                    }
                }
                throw e;
            } catch (Exception e2) {
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused2) {
                    }
                }
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("file header is null, cannot get inputstream");
        }
    }

    private void init(RandomAccessFile randomAccessFile) throws ZipException {
        if (this.localFileHeader != null) {
            try {
                initDecrypter(randomAccessFile);
            } catch (ZipException e) {
                throw e;
            } catch (Exception e2) {
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("local file header is null, cannot initialize input stream");
        }
    }

    private void initDecrypter(RandomAccessFile randomAccessFile) throws ZipException {
        LocalFileHeader localFileHeader2 = this.localFileHeader;
        if (localFileHeader2 == null) {
            throw new ZipException("local file header is null, cannot init decrypter");
        } else if (!localFileHeader2.isEncrypted()) {
        } else {
            if (this.localFileHeader.getEncryptionMethod() == 0) {
                this.decrypter = new StandardDecrypter(this.fileHeader, getStandardDecrypterHeaderBytes(randomAccessFile));
            } else if (this.localFileHeader.getEncryptionMethod() == 99) {
                this.decrypter = new AESDecrypter(this.localFileHeader, getAESSalt(randomAccessFile), getAESPasswordVerifier(randomAccessFile));
            } else {
                throw new ZipException("unsupported encryption method");
            }
        }
    }

    private byte[] getStandardDecrypterHeaderBytes(RandomAccessFile randomAccessFile) throws ZipException {
        try {
            byte[] bArr = new byte[12];
            randomAccessFile.seek(this.localFileHeader.getOffsetStartOfData());
            randomAccessFile.read(bArr, 0, 12);
            return bArr;
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private byte[] getAESSalt(RandomAccessFile randomAccessFile) throws ZipException {
        if (this.localFileHeader.getAesExtraDataRecord() == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[calculateAESSaltLength(this.localFileHeader.getAesExtraDataRecord())];
            randomAccessFile.seek(this.localFileHeader.getOffsetStartOfData());
            randomAccessFile.read(bArr);
            return bArr;
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    private byte[] getAESPasswordVerifier(RandomAccessFile randomAccessFile) throws ZipException {
        try {
            byte[] bArr = new byte[2];
            randomAccessFile.read(bArr);
            return bArr;
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    private int calculateAESSaltLength(AESExtraDataRecord aESExtraDataRecord) throws ZipException {
        if (aESExtraDataRecord != null) {
            int aesStrength = aESExtraDataRecord.getAesStrength();
            if (aesStrength == 1) {
                return 8;
            }
            if (aesStrength == 2) {
                return 12;
            }
            if (aesStrength == 3) {
                return 16;
            }
            throw new ZipException("unable to determine salt length: invalid aes key strength");
        }
        throw new ZipException("unable to determine salt length: AESExtraDataRecord is null");
    }

    public void checkCRC() throws ZipException {
        FileHeader fileHeader2 = this.fileHeader;
        if (fileHeader2 == null) {
            return;
        }
        if (fileHeader2.getEncryptionMethod() == 99) {
            IDecrypter iDecrypter = this.decrypter;
            if (iDecrypter != null && (iDecrypter instanceof AESDecrypter)) {
                byte[] calculatedAuthenticationBytes = ((AESDecrypter) iDecrypter).getCalculatedAuthenticationBytes();
                byte[] storedMac = ((AESDecrypter) this.decrypter).getStoredMac();
                byte[] bArr = new byte[10];
                if (storedMac != null) {
                    System.arraycopy(calculatedAuthenticationBytes, 0, bArr, 0, 10);
                    if (!Arrays.equals(bArr, storedMac)) {
                        throw new ZipException(new StringBuffer("invalid CRC (MAC) for file: ").append(this.fileHeader.getFileName()).toString());
                    }
                    return;
                }
                throw new ZipException(new StringBuffer("CRC (MAC) check failed for ").append(this.fileHeader.getFileName()).toString());
            }
        } else if ((this.crc.getValue() & InternalZipConstants.ZIP_64_LIMIT) != this.fileHeader.getCrc32()) {
            String stringBuffer = new StringBuffer("invalid CRC for file: ").append(this.fileHeader.getFileName()).toString();
            if (this.localFileHeader.isEncrypted() && this.localFileHeader.getEncryptionMethod() == 0) {
                stringBuffer = new StringBuffer(String.valueOf(stringBuffer)).append(" - Wrong Password?").toString();
            }
            throw new ZipException(stringBuffer);
        }
    }

    private boolean checkLocalHeader() throws ZipException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = checkSplitFile();
            if (randomAccessFile == null) {
                randomAccessFile = new RandomAccessFile(new File(this.zipModel.getZipFile()), "r");
            }
            LocalFileHeader readLocalFileHeader = new HeaderReader(randomAccessFile).readLocalFileHeader(this.fileHeader);
            this.localFileHeader = readLocalFileHeader;
            if (readLocalFileHeader == null) {
                throw new ZipException("error reading local file header. Is this a valid zip file?");
            } else if (readLocalFileHeader.getCompressionMethod() != this.fileHeader.getCompressionMethod()) {
                if (randomAccessFile == null) {
                    return false;
                }
                try {
                    randomAccessFile.close();
                    return false;
                } catch (IOException | Exception unused) {
                    return false;
                }
            } else if (randomAccessFile == null) {
                return true;
            } else {
                try {
                    randomAccessFile.close();
                    return true;
                } catch (IOException | Exception unused2) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new ZipException((Throwable) e);
        } catch (Throwable th) {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException | Exception unused3) {
                }
            }
            throw th;
        }
    }

    private RandomAccessFile checkSplitFile() throws ZipException {
        String str;
        if (!this.zipModel.isSplitArchive()) {
            return null;
        }
        int diskNumberStart = this.fileHeader.getDiskNumberStart();
        int i = diskNumberStart + 1;
        this.currSplitFileCounter = i;
        String zipFile = this.zipModel.getZipFile();
        if (diskNumberStart == this.zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
            str = this.zipModel.getZipFile();
        } else if (diskNumberStart >= 9) {
            str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z").append(i).toString();
        } else {
            str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z0").append(i).toString();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            if (this.currSplitFileCounter == 1) {
                byte[] bArr = new byte[4];
                randomAccessFile.read(bArr);
                if (((long) Raw.readIntLittleEndian(bArr, 0)) != 134695760) {
                    throw new ZipException("invalid first part split file signature");
                }
            }
            return randomAccessFile;
        } catch (FileNotFoundException e) {
            throw new ZipException((Throwable) e);
        } catch (IOException e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private RandomAccessFile createFileHandler(String str) throws ZipException {
        ZipModel zipModel2 = this.zipModel;
        if (zipModel2 == null || !Zip4jUtil.isStringNotNullAndNotEmpty(zipModel2.getZipFile())) {
            throw new ZipException("input parameter is null in getFilePointer");
        }
        try {
            if (this.zipModel.isSplitArchive()) {
                return checkSplitFile();
            }
            return new RandomAccessFile(new File(this.zipModel.getZipFile()), str);
        } catch (FileNotFoundException e) {
            throw new ZipException((Throwable) e);
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private FileOutputStream getOutputStream(String str, String str2) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            try {
                File file = new File(getOutputFileNameWithPath(str, str2));
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid output path");
        }
    }

    private String getOutputFileNameWithPath(String str, String str2) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            str2 = this.fileHeader.getFileName();
        }
        return new StringBuffer(String.valueOf(str)).append(System.getProperty("file.separator")).append(str2).toString();
    }

    public RandomAccessFile startNextSplitFile() throws IOException, FileNotFoundException {
        String str;
        String zipFile = this.zipModel.getZipFile();
        if (this.currSplitFileCounter == this.zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
            str = this.zipModel.getZipFile();
        } else if (this.currSplitFileCounter >= 9) {
            str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z").append(this.currSplitFileCounter + 1).toString();
        } else {
            str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z0").append(this.currSplitFileCounter + 1).toString();
        }
        this.currSplitFileCounter++;
        try {
            if (Zip4jUtil.checkFileExists(str)) {
                return new RandomAccessFile(str, "r");
            }
            throw new IOException(new StringBuffer("zip split file does not exist: ").append(str).toString());
        } catch (ZipException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void closeStreams(InputStream inputStream, OutputStream outputStream) throws ZipException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                if (Zip4jUtil.isStringNotNullAndNotEmpty(e.getMessage())) {
                    if (e.getMessage().indexOf(" - Wrong Password?") >= 0) {
                        throw new ZipException(e.getMessage());
                    }
                }
                if (outputStream == null) {
                    return;
                }
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        }
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        } catch (IOException unused2) {
        }
    }

    public void updateCRC(int i) {
        this.crc.update(i);
    }

    public void updateCRC(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            this.crc.update(bArr, i, i2);
        }
    }

    public FileHeader getFileHeader() {
        return this.fileHeader;
    }

    public IDecrypter getDecrypter() {
        return this.decrypter;
    }

    public ZipModel getZipModel() {
        return this.zipModel;
    }

    public LocalFileHeader getLocalFileHeader() {
        return this.localFileHeader;
    }
}
