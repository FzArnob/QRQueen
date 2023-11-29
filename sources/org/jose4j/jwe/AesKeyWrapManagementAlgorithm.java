package org.jose4j.jwe;

import java.security.Key;
import org.jose4j.jwa.AlgorithmAvailability;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.InvalidKeyException;

public class AesKeyWrapManagementAlgorithm extends WrappingKeyManagementAlgorithm {
    int keyByteLength;

    public AesKeyWrapManagementAlgorithm(String str, int i) {
        super("AESWrap", str);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        this.keyByteLength = i;
    }

    /* access modifiers changed from: package-private */
    public int getKeyByteLength() {
        return this.keyByteLength;
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    /* access modifiers changed from: package-private */
    public void validateKey(Key key) throws InvalidKeyException {
        KeyValidationSupport.validateAesWrappingKey(key, getAlgorithmIdentifier(), getKeyByteLength());
    }

    public boolean isAvailable() {
        int keyByteLength2 = getKeyByteLength();
        String javaAlgorithm = getJavaAlgorithm();
        return AlgorithmAvailability.isAvailable("Cipher", javaAlgorithm) && CipherStrengthSupport.isAvailable(javaAlgorithm, keyByteLength2);
    }

    /* access modifiers changed from: package-private */
    public AesKeyWrapManagementAlgorithm setUseGeneralProviderContext() {
        this.useSuppliedKeyProviderContext = false;
        return this;
    }

    public static class Aes128 extends AesKeyWrapManagementAlgorithm {
        public Aes128() {
            super(KeyManagementAlgorithmIdentifiers.A128KW, 16);
        }
    }

    public static class Aes192 extends AesKeyWrapManagementAlgorithm {
        public Aes192() {
            super(KeyManagementAlgorithmIdentifiers.A192KW, 24);
        }
    }

    public static class Aes256 extends AesKeyWrapManagementAlgorithm {
        public Aes256() {
            super(KeyManagementAlgorithmIdentifiers.A256KW, 32);
        }
    }
}
