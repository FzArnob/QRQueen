package net.lingala.zip4j.model;

import java.util.List;

public class ZipModel implements Cloneable {
    private ArchiveExtraDataRecord archiveExtraDataRecord;
    private CentralDirectory centralDirectory;
    private List dataDescriptorList;
    private long end;
    private EndCentralDirRecord endCentralDirRecord;
    private String fileNameCharset;
    private boolean isNestedZipFile;
    private boolean isZip64Format;
    private List localFileHeaderList;
    private boolean splitArchive;
    private long splitLength = -1;
    private long start;
    private Zip64EndCentralDirLocator zip64EndCentralDirLocator;
    private Zip64EndCentralDirRecord zip64EndCentralDirRecord;
    private String zipFile;

    public List getLocalFileHeaderList() {
        return this.localFileHeaderList;
    }

    public void setLocalFileHeaderList(List list) {
        this.localFileHeaderList = list;
    }

    public List getDataDescriptorList() {
        return this.dataDescriptorList;
    }

    public void setDataDescriptorList(List list) {
        this.dataDescriptorList = list;
    }

    public CentralDirectory getCentralDirectory() {
        return this.centralDirectory;
    }

    public void setCentralDirectory(CentralDirectory centralDirectory2) {
        this.centralDirectory = centralDirectory2;
    }

    public EndCentralDirRecord getEndCentralDirRecord() {
        return this.endCentralDirRecord;
    }

    public void setEndCentralDirRecord(EndCentralDirRecord endCentralDirRecord2) {
        this.endCentralDirRecord = endCentralDirRecord2;
    }

    public ArchiveExtraDataRecord getArchiveExtraDataRecord() {
        return this.archiveExtraDataRecord;
    }

    public void setArchiveExtraDataRecord(ArchiveExtraDataRecord archiveExtraDataRecord2) {
        this.archiveExtraDataRecord = archiveExtraDataRecord2;
    }

    public boolean isSplitArchive() {
        return this.splitArchive;
    }

    public void setSplitArchive(boolean z) {
        this.splitArchive = z;
    }

    public String getZipFile() {
        return this.zipFile;
    }

    public void setZipFile(String str) {
        this.zipFile = str;
    }

    public Zip64EndCentralDirLocator getZip64EndCentralDirLocator() {
        return this.zip64EndCentralDirLocator;
    }

    public void setZip64EndCentralDirLocator(Zip64EndCentralDirLocator zip64EndCentralDirLocator2) {
        this.zip64EndCentralDirLocator = zip64EndCentralDirLocator2;
    }

    public Zip64EndCentralDirRecord getZip64EndCentralDirRecord() {
        return this.zip64EndCentralDirRecord;
    }

    public void setZip64EndCentralDirRecord(Zip64EndCentralDirRecord zip64EndCentralDirRecord2) {
        this.zip64EndCentralDirRecord = zip64EndCentralDirRecord2;
    }

    public boolean isZip64Format() {
        return this.isZip64Format;
    }

    public void setZip64Format(boolean z) {
        this.isZip64Format = z;
    }

    public boolean isNestedZipFile() {
        return this.isNestedZipFile;
    }

    public void setNestedZipFile(boolean z) {
        this.isNestedZipFile = z;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    public void setSplitLength(long j) {
        this.splitLength = j;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getFileNameCharset() {
        return this.fileNameCharset;
    }

    public void setFileNameCharset(String str) {
        this.fileNameCharset = str;
    }
}
