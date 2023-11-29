package net.lingala.zip4j.model;

public class DataDescriptor {
    private int compressedSize;
    private String crc32;
    private int uncompressedSize;

    public String getCrc32() {
        return this.crc32;
    }

    public void setCrc32(String str) {
        this.crc32 = str;
    }

    public int getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(int i) {
        this.compressedSize = i;
    }

    public int getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setUncompressedSize(int i) {
        this.uncompressedSize = i;
    }
}
