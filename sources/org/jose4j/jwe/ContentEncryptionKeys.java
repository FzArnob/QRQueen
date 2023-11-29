package org.jose4j.jwe;

import org.jose4j.lang.ByteUtil;

public class ContentEncryptionKeys {
    private final byte[] contentEncryptionKey;
    private final byte[] encryptedKey;

    public ContentEncryptionKeys(byte[] bArr, byte[] bArr2) {
        this.contentEncryptionKey = bArr;
        this.encryptedKey = bArr2 == null ? ByteUtil.EMPTY_BYTES : bArr2;
    }

    public byte[] getContentEncryptionKey() {
        return this.contentEncryptionKey;
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }
}
