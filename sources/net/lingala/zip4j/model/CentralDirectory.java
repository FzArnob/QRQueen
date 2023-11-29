package net.lingala.zip4j.model;

import java.util.ArrayList;

public class CentralDirectory {
    private DigitalSignature digitalSignature;
    private ArrayList fileHeaders;

    public ArrayList getFileHeaders() {
        return this.fileHeaders;
    }

    public void setFileHeaders(ArrayList arrayList) {
        this.fileHeaders = arrayList;
    }

    public DigitalSignature getDigitalSignature() {
        return this.digitalSignature;
    }

    public void setDigitalSignature(DigitalSignature digitalSignature2) {
        this.digitalSignature = digitalSignature2;
    }
}
