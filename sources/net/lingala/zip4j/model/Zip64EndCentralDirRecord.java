package net.lingala.zip4j.model;

public class Zip64EndCentralDirRecord {
    private byte[] extensibleDataSector;
    private int noOfThisDisk;
    private int noOfThisDiskStartOfCentralDir;
    private long offsetStartCenDirWRTStartDiskNo;
    private long signature;
    private long sizeOfCentralDir;
    private long sizeOfZip64EndCentralDirRec;
    private long totNoOfEntriesInCentralDir;
    private long totNoOfEntriesInCentralDirOnThisDisk;
    private int versionMadeBy;
    private int versionNeededToExtract;

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        this.signature = j;
    }

    public long getSizeOfZip64EndCentralDirRec() {
        return this.sizeOfZip64EndCentralDirRec;
    }

    public void setSizeOfZip64EndCentralDirRec(long j) {
        this.sizeOfZip64EndCentralDirRec = j;
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

    public long getTotNoOfEntriesInCentralDirOnThisDisk() {
        return this.totNoOfEntriesInCentralDirOnThisDisk;
    }

    public void setTotNoOfEntriesInCentralDirOnThisDisk(long j) {
        this.totNoOfEntriesInCentralDirOnThisDisk = j;
    }

    public long getTotNoOfEntriesInCentralDir() {
        return this.totNoOfEntriesInCentralDir;
    }

    public void setTotNoOfEntriesInCentralDir(long j) {
        this.totNoOfEntriesInCentralDir = j;
    }

    public long getSizeOfCentralDir() {
        return this.sizeOfCentralDir;
    }

    public void setSizeOfCentralDir(long j) {
        this.sizeOfCentralDir = j;
    }

    public long getOffsetStartCenDirWRTStartDiskNo() {
        return this.offsetStartCenDirWRTStartDiskNo;
    }

    public void setOffsetStartCenDirWRTStartDiskNo(long j) {
        this.offsetStartCenDirWRTStartDiskNo = j;
    }

    public byte[] getExtensibleDataSector() {
        return this.extensibleDataSector;
    }

    public void setExtensibleDataSector(byte[] bArr) {
        this.extensibleDataSector = bArr;
    }
}
