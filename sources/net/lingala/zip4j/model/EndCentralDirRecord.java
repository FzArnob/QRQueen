package net.lingala.zip4j.model;

public class EndCentralDirRecord {
    private String comment;
    private byte[] commentBytes;
    private int commentLength;
    private int noOfThisDisk;
    private int noOfThisDiskStartOfCentralDir;
    private long offsetOfStartOfCentralDir;
    private long signature;
    private int sizeOfCentralDir;
    private int totNoOfEntriesInCentralDir;
    private int totNoOfEntriesInCentralDirOnThisDisk;

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        this.signature = j;
    }

    public int getNoOfThisDisk() {
        return this.noOfThisDisk;
    }

    public void setNoOfThisDisk(int i) {
        this.noOfThisDisk = i;
    }

    public int getNoOfThisDiskStartOfCentralDir() {
        return this.noOfThisDiskStartOfCentralDir;
    }

    public void setNoOfThisDiskStartOfCentralDir(int i) {
        this.noOfThisDiskStartOfCentralDir = i;
    }

    public int getTotNoOfEntriesInCentralDirOnThisDisk() {
        return this.totNoOfEntriesInCentralDirOnThisDisk;
    }

    public void setTotNoOfEntriesInCentralDirOnThisDisk(int i) {
        this.totNoOfEntriesInCentralDirOnThisDisk = i;
    }

    public int getTotNoOfEntriesInCentralDir() {
        return this.totNoOfEntriesInCentralDir;
    }

    public void setTotNoOfEntriesInCentralDir(int i) {
        this.totNoOfEntriesInCentralDir = i;
    }

    public int getSizeOfCentralDir() {
        return this.sizeOfCentralDir;
    }

    public void setSizeOfCentralDir(int i) {
        this.sizeOfCentralDir = i;
    }

    public long getOffsetOfStartOfCentralDir() {
        return this.offsetOfStartOfCentralDir;
    }

    public void setOffsetOfStartOfCentralDir(long j) {
        this.offsetOfStartOfCentralDir = j;
    }

    public int getCommentLength() {
        return this.commentLength;
    }

    public void setCommentLength(int i) {
        this.commentLength = i;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public byte[] getCommentBytes() {
        return this.commentBytes;
    }

    public void setCommentBytes(byte[] bArr) {
        this.commentBytes = bArr;
    }
}
