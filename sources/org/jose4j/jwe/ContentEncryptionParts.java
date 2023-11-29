package org.jose4j.jwe;

public class ContentEncryptionParts {
    private byte[] authenticationTag;
    private byte[] ciphertext;
    private byte[] iv;

    public ContentEncryptionParts(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.iv = bArr;
        this.ciphertext = bArr2;
        this.authenticationTag = bArr3;
    }

    public byte[] getIv() {
        return this.iv;
    }

    public byte[] getCiphertext() {
        return this.ciphertext;
    }

    public byte[] getAuthenticationTag() {
        return this.authenticationTag;
    }
}
