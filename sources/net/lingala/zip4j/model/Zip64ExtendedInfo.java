package net.lingala.zip4j.model;

public class Zip64ExtendedInfo {
    private long compressedSize = -1;
    private int diskNumberStart = -1;
    private int header;
    private long offsetLocalHeader = -1;
    private int size;
    private long unCompressedSize = -1;

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int i) {
        this.header = i;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long j) {
        this.compressedSize = j;
    }

    public long getUnCompressedSize() {
        return this.unCompressedSize;
    }

    public void setUnCompressedSize(long j) {
        this.unCompressedSize = j;
    }

    public long getOffsetLocalHeader() {
        return this.offsetLocalHeader;
    }

    public void setOffsetLocalHeader(long j) {
        this.offsetLocalHeader = j;
    }

    public int getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public void setDiskNumberStart(int i) {
        this.diskNumberStart = i;
    }
}
