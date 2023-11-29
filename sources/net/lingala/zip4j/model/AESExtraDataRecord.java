package net.lingala.zip4j.model;

public class AESExtraDataRecord {
    private int aesStrength = -1;
    private int compressionMethod = -1;
    private int dataSize = -1;
    private long signature = -1;
    private String vendorID = null;
    private int versionNumber = -1;

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        this.signature = j;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(int i) {
        this.dataSize = i;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(int i) {
        this.versionNumber = i;
    }

    public String getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(String str) {
        this.vendorID = str;
    }

    public int getAesStrength() {
        return this.aesStrength;
    }

    public void setAesStrength(int i) {
        this.aesStrength = i;
    }

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        this.compressionMethod = i;
    }
}
