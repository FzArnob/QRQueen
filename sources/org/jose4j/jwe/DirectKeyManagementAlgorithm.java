package org.jose4j.jwe;

import java.security.Key;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;

public class DirectKeyManagementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public boolean isAvailable() {
        return true;
    }

    public DirectKeyManagementAlgorithm() {
        setAlgorithmIdentifier(KeyManagementAlgorithmIdentifiers.DIRECT);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
    }

    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        KeyValidationSupport.cekNotAllowed(bArr, getAlgorithmIdentifier());
        return new ContentEncryptionKeys(key.getEncoded(), ByteUtil.EMPTY_BYTES);
    }

    public Key manageForDecrypt(Key key, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        if (bArr.length == 0) {
            return key;
        }
        throw new InvalidKeyException("An empty octet sequence is to be used as the JWE Encrypted Key value when utilizing direct encryption but this JWE has " + bArr.length + " octets in the encrypted key part.");
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key, contentEncryptionAlgorithm);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r5 = r5.getEncoded().length;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void validateKey(java.security.Key r5, org.jose4j.jwe.ContentEncryptionAlgorithm r6) throws org.jose4j.lang.InvalidKeyException {
        /*
            r4 = this;
            org.jose4j.jwx.KeyValidationSupport.notNull(r5)
            byte[] r0 = r5.getEncoded()
            if (r0 == 0) goto L_0x005d
            byte[] r5 = r5.getEncoded()
            int r5 = r5.length
            org.jose4j.jwe.ContentEncryptionKeyDescriptor r0 = r6.getContentEncryptionKeyDescriptor()
            int r0 = r0.getContentEncryptionKeyByteLength()
            if (r0 != r5) goto L_0x0019
            goto L_0x005d
        L_0x0019:
            org.jose4j.lang.InvalidKeyException r1 = new org.jose4j.lang.InvalidKeyException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid key for "
            r2.append(r3)
            java.lang.String r3 = r4.getAlgorithmIdentifier()
            r2.append(r3)
            java.lang.String r3 = " with "
            r2.append(r3)
            java.lang.String r6 = r6.getAlgorithmIdentifier()
            r2.append(r6)
            java.lang.String r6 = ", expected a "
            r2.append(r6)
            int r6 = org.jose4j.lang.ByteUtil.bitLength((int) r0)
            r2.append(r6)
            java.lang.String r6 = " bit key but a "
            r2.append(r6)
            int r5 = org.jose4j.lang.ByteUtil.bitLength((int) r5)
            r2.append(r5)
            java.lang.String r5 = " bit key was provided."
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1.<init>(r5)
            throw r1
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwe.DirectKeyManagementAlgorithm.validateKey(java.security.Key, org.jose4j.jwe.ContentEncryptionAlgorithm):void");
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key, contentEncryptionAlgorithm);
    }
}
