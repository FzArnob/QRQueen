package org.jose4j.jwe;

import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwe.SimpleAeadCipher;
import org.jose4j.jwx.Headers;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

public class AesGcmContentEncryptionAlgorithm extends AlgorithmInfo implements ContentEncryptionAlgorithm {
    private static final int IV_BYTE_LENGTH = 12;
    private static final int TAG_BYTE_LENGTH = 16;
    private ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor;
    private SimpleAeadCipher simpleAeadCipher = new SimpleAeadCipher(getJavaAlgorithm(), 16);

    public AesGcmContentEncryptionAlgorithm(String str, int i) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(SimpleAeadCipher.GCM_TRANSFORMATION_NAME);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(AesKey.ALGORITHM);
        this.contentEncryptionKeyDescriptor = new ContentEncryptionKeyDescriptor(ByteUtil.byteLength(i), AesKey.ALGORITHM);
    }

    public ContentEncryptionKeyDescriptor getContentEncryptionKeyDescriptor() {
        return this.contentEncryptionKeyDescriptor;
    }

    public ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, Headers headers, byte[] bArr4, ProviderContext providerContext) throws JoseException {
        return encrypt(bArr, bArr2, bArr3, InitializationVectorHelp.iv(12, bArr4, providerContext.getSecureRandom()), ContentEncryptionHelp.getCipherProvider(headers, providerContext));
    }

    public ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str) throws JoseException {
        SimpleAeadCipher.CipherOutput encrypt = this.simpleAeadCipher.encrypt(new AesKey(bArr3), bArr4, bArr, bArr2, str);
        return new ContentEncryptionParts(bArr4, encrypt.getCiphertext(), encrypt.getTag());
    }

    public byte[] decrypt(ContentEncryptionParts contentEncryptionParts, byte[] bArr, byte[] bArr2, Headers headers, ProviderContext providerContext) throws JoseException {
        byte[] iv = contentEncryptionParts.getIv();
        return this.simpleAeadCipher.decrypt(new AesKey(bArr2), iv, contentEncryptionParts.getCiphertext(), contentEncryptionParts.getAuthenticationTag(), bArr, ContentEncryptionHelp.getCipherProvider(headers, providerContext));
    }

    public boolean isAvailable() {
        return this.simpleAeadCipher.isAvailable(this.log, getContentEncryptionKeyDescriptor().getContentEncryptionKeyByteLength(), 12, getAlgorithmIdentifier());
    }

    public static class Aes256Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes256Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM, 256);
        }
    }

    public static class Aes192Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes192Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_192_GCM, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE);
        }
    }

    public static class Aes128Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes128Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM, 128);
        }
    }
}
