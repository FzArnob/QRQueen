package net.lingala.zip4j.model;

import java.util.ArrayList;

public class LocalFileHeader {
    private AESExtraDataRecord aesExtraDataRecord;
    private long compressedSize;
    private int compressionMethod;
    private long crc32 = 0;
    private byte[] crcBuff;
    private boolean dataDescriptorExists;
    private int encryptionMethod = -1;
    private ArrayList extraDataRecords;
    private byte[] extraField;
    private int extraFieldLength;
    private String fileName;
    private int fileNameLength;
    private boolean fileNameUTF8Encoded;
    private byte[] generalPurposeFlag;
    private boolean isEncrypted;
    private int lastModFileTime;
    private long offsetStartOfData;
    private char[] password;
    private int signature;
    private long uncompressedSize = 0;
    private int versionNeededToExtract;
    private boolean writeComprSizeInZip64ExtraRecord = false;
    private Zip64ExtendedInfo zip64ExtendedInfo;

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        this.signature = i;
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
        return this.crc32;
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

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public byte[] getExtraField() {
        return this.extraField;
    }

    public void setExtraField(byte[] bArr) {
        this.extraField = bArr;
    }

    public long getOffsetStartOfData() {
        return this.offsetStartOfData;
    }

    public void setOffsetStartOfData(long j) {
        this.offsetStartOfData = j;
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

    public byte[] getCrcBuff() {
        return this.crcBuff;
    }

    public void setCrcBuff(byte[] bArr) {
        this.crcBuff = bArr;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
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

    public boolean isWriteComprSizeInZip64ExtraRecord() {
        return this.writeComprSizeInZip64ExtraRecord;
    }

    public void setWriteComprSizeInZip64ExtraRecord(boolean z) {
        this.writeComprSizeInZip64ExtraRecord = z;
    }

    public boolean isFileNameUTF8Encoded() {
        return this.fileNameUTF8Encoded;
    }

    public void setFileNameUTF8Encoded(boolean z) {
        this.fileNameUTF8Encoded = z;
    }
}
