package net.lingala.zip4j.model;

public class ArchiveExtraDataRecord {
    private String extraFieldData;
    private int extraFieldLength;
    private int signature;

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        this.signature = i;
    }

    public int getExtraFieldLength() {
        return this.extraFieldLength;
    }

    public void setExtraFieldLength(int i) {
        this.extraFieldLength = i;
    }

    public String getExtraFieldData() {
        return this.extraFieldData;
    }

    public void setExtraFieldData(String str) {
        this.extraFieldData = str;
    }
}
