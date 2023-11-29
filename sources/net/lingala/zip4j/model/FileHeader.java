package net.lingala.zip4j.model;

import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.unzip.Unzip;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class FileHeader {
    private AESExtraDataRecord aesExtraDataRecord;
    private long compressedSize;
    private int compressionMethod;
    private long crc32 = 0;
    private byte[] crcBuff;
    private boolean dataDescriptorExists;
    private int diskNumberStart;
    private int encryptionMethod = -1;
    private byte[] externalFileAttr;
    private ArrayList extraDataRecords;
    private int extraFieldLength;
    private String fileComment;
    private int fileCommentLength;
    private String fileName;
    private int fileNameLength;
    private boolean fileNameUTF8Encoded;
    private byte[] generalPurposeFlag;
    private byte[] internalFileAttr;
    private boolean isDirectory;
    private boolean isEncrypted;
    private int lastModFileTime;
    private long offsetLocalHeader;
    private char[] password;
    private int signature;
    private long uncompressedSize = 0;
    private int versionMadeBy;
    private int versionNeededToExtract;
    private Zip64ExtendedInfo zip64ExtendedInfo;

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        this.signature = i;
    }

    public int getVersionMadeBy() {
        return this.versionMadeBy;
    }

    public void setVersionMadeBy(int i) {
        this.versionMadeBy = i;
    }

    public int getVersionNeededToExtract() {
        return this.versionNeededToExtract;
    }

    public void setVersionNeededToExtract(int i) {
        this.versionNeededToExtract = i;
    }

    public byte[] getGeneralPurposeFlag() {
        return this.generalPurposeFlag;
    }

    public void setGeneralPurposeFlag(byte[] bArr) {
        this.generalPurposeFlag = bArr;
    }

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        this.compressionMethod = i;
    }

    public int getLastModFileTime() {
        return this.lastModFileTime;
    }

    public void setLastModFileTime(int i) {
        this.lastModFileTime = i;
    }

    public long getCrc32() {
        return this.crc32 & InternalZipConstants.ZIP_64_LIMIT;
    }

    public void setCrc32(long j) {
        this.crc32 = j;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long j) {
        this.compressedSize = j;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setUncompressedSize(long j) {
        this.uncompressedSize = j;
    }

    public int getFileNameLength() {
        return this.fileNameLength;
    }

    public void setFileNameLength(int i) {
        this.fileNameLength = i;
    }

    public int getExtraFieldLength() {
        return this.extraFieldLength;
    }

    public void setExtraFieldLength(int i) {
        this.extraFieldLength = i;
    }

    public int getFileCommentLength() {
        return this.fileCommentLength;
    }

    public void setFileCommentLength(int i) {
        this.fileCommentLength = i;
    }

    public int getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public void setDiskNumberStart(int i) {
        this.diskNumberStart = i;
    }

    public byte[] getInternalFileAttr() {
        return this.internalFileAttr;
    }

    public void setInternalFileAttr(byte[] bArr) {
        this.internalFileAttr = bArr;
    }

    public byte[] getExternalFileAttr() {
        return this.externalFileAttr;
    }

    public void setExternalFileAttr(byte[] bArr) {
        this.externalFileAttr = bArr;
    }

    public long getOffsetLocalHeader() {
        return this.offsetLocalHeader;
    }

    public void setOffsetLocalHeader(long j) {
        this.offsetLocalHeader = j;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileComment() {
        return this.fileComment;
    }

    public void setFileComment(String str) {
        this.fileComment = str;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public void setDirectory(boolean z) {
        this.isDirectory = z;
    }

    public void extractFile(ZipModel zipModel, String str, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        extractFile(zipModel, str, (UnzipParameters) null, progressMonitor, z);
    }

    public void extractFile(ZipModel zipModel, String str, UnzipParameters unzipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        extractFile(zipModel, str, unzipParameters, (String) null, progressMonitor, z);
    }

    public void extractFile(ZipModel zipModel, String str, UnzipParameters unzipParameters, String str2, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        if (zipModel == null) {
            throw new ZipException("input zipModel is null");
        } else if (Zip4jUtil.checkOutputFolder(str)) {
            new Unzip(zipModel).extractFile(this, str, unzipParameters, str2, progressMonitor, z);
        } else {
            throw new ZipException("Invalid output path");
        }
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    public void setEncrypted(boolean z) {
        this.isEncrypted = z;
    }

    public int getEncryptionMethod() {
        return this.encryptionMethod;
    }

    public void setEncryptionMethod(int i) {
        this.encryptionMethod = i;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
    }

    public byte[] getCrcBuff() {
        return this.crcBuff;
    }

    public void setCrcBuff(byte[] bArr) {
        this.crcBuff = bArr;
    }

    public ArrayList getExtraDataRecords() {
        return this.extraDataRecords;
    }

    public void setExtraDataRecords(ArrayList arrayList) {
        this.extraDataRecords = arrayList;
    }

    public boolean isDataDescriptorExists() {
        return this.dataDescriptorExists;
    }

    public void setDataDescriptorExists(boolean z) {
        this.dataDescriptorExists = z;
    }

    public Zip64ExtendedInfo getZip64ExtendedInfo() {
        return this.zip64ExtendedInfo;
    }

    public void setZip64ExtendedInfo(Zip64ExtendedInfo zip64ExtendedInfo2) {
        this.zip64ExtendedInfo = zip64ExtendedInfo2;
    }

    public AESExtraDataRecord getAesExtraDataRecord() {
        return this.aesExtraDataRecord;
    }

    public void setAesExtraDataRecord(AESExtraDataRecord aESExtraDataRecord) {
        this.aesExtraDataRecord = aESExtraDataRecord;
    }

    public boolean isFileNameUTF8Encoded() {
        return this.fileNameUTF8Encoded;
    }

    public void setFileNameUTF8Encoded(boolean z) {
        this.fileNameUTF8Encoded = z;
    }
}
