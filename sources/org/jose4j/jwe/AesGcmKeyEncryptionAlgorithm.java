package org.jose4j.jwe;

import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwe.SimpleAeadCipher;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;

public class AesGcmKeyEncryptionAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    private static final int IV_BYTE_LENGTH = 12;
    private static final int TAG_BYTE_LENGTH = 16;
    private int keyByteLength;
    private SimpleAeadCipher simpleAeadCipher = new SimpleAeadCipher(getJavaAlgorithm(), 16);

    public AesGcmKeyEncryptionAlgorithm(String str, int i) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(SimpleAeadCipher.GCM_TRANSFORMATION_NAME);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
        this.keyByteLength = i;
    }

    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        byte[] bArr2;
        SecureRandom secureRandom = providerContext.getSecureRandom();
        if (bArr == null) {
            bArr = ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength(), secureRandom);
        }
        Base64Url base64Url = new Base64Url();
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR);
        if (stringHeaderValue == null) {
            bArr2 = ByteUtil.randomBytes(12, secureRandom);
            headers.setStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR, base64Url.base64UrlEncode(bArr2));
        } else {
            bArr2 = base64Url.base64UrlDecode(stringHeaderValue);
        }
        String cipherProvider = providerContext.getSuppliedKeyProviderContext().getCipherProvider();
        SimpleAeadCipher.CipherOutput encrypt = this.simpleAeadCipher.encrypt(key, bArr2, bArr, (byte[]) null, cipherProvider);
        byte[] ciphertext = encrypt.getCiphertext();
        headers.setStringHeaderValue(HeaderParameterNames.AUTHENTICATION_TAG, base64Url.base64UrlEncode(encrypt.getTag()));
        return new ContentEncryptionKeys(bArr, ciphertext);
    }

    public Key manageForDecrypt(Key key, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        Base64Url base64Url = new Base64Url();
        return new SecretKeySpec(this.simpleAeadCipher.decrypt(key, base64Url.base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR)), bArr, base64Url.base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.AUTHENTICATION_TAG)), (byte[]) null, providerContext.getSuppliedKeyProviderContext().getCipherProvider()), contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm());
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    /* access modifiers changed from: package-private */
    public void validateKey(Key key) throws InvalidKeyException {
        KeyValidationSupport.validateAesWrappingKey(key, getAlgorithmIdentifier(), this.keyByteLength);
    }

    public boolean isAvailable() {
        return this.simpleAeadCipher.isAvailable(this.log, this.keyByteLength, 12, getAlgorithmIdentifier());
    }

    public static class Aes128Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes128Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A128GCMKW, ByteUtil.byteLength(128));
        }
    }

    public static class Aes192Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes192Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A192GCMKW, ByteUtil.byteLength(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE));
        }
    }

    public static class Aes256Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes256Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A256GCMKW, ByteUtil.byteLength(256));
        }
    }
}
