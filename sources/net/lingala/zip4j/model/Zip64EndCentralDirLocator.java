package net.lingala.zip4j.model;

public class Zip64EndCentralDirLocator {
    private int noOfDiskStartOfZip64EndOfCentralDirRec;
    private long offsetZip64EndOfCentralDirRec;
    private long signature;
    private int totNumberOfDiscs;

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        this.signature = j;
    }

    public int getNoOfDiskStartOfZip64EndOfCentralDirRec() {
        return this.noOfDiskStartOfZip64EndOfCentralDirRec;
    }

    public void setNoOfDiskStartOfZip64EndOfCentralDirRec(int i) {
        this.noOfDiskStartOfZip64EndOfCentralDirRec = i;
    }

    public long getOffsetZip64EndOfCentralDirRec() {
        return this.offsetZip64EndOfCentralDirRec;
    }

    public void setOffsetZip64EndOfCentralDirRec(long j) {
        this.offsetZip64EndOfCentralDirRec = j;
    }

    public int getTotNumberOfDiscs() {
        return this.totNumberOfDiscs;
    }

    public void setTotNumberOfDiscs(int i) {
        this.totNumberOfDiscs = i;
    }
}
