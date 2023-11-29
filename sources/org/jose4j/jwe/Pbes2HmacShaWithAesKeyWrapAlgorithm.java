package org.jose4j.jwe;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwe.AesKeyWrapManagementAlgorithm;
import org.jose4j.jwe.kdf.PasswordBasedKeyDerivationFunction2;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.keys.PbkdfKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.mac.MacUtil;

public class Pbes2HmacShaWithAesKeyWrapAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    private static final byte[] ZERO_BYTE = {0};
    private long defaultIterationCount = 8192;
    private int defaultSaltByteLength = 12;
    private AesKeyWrapManagementAlgorithm keyWrap;
    private ContentEncryptionKeyDescriptor keyWrapKeyDescriptor;
    private PasswordBasedKeyDerivationFunction2 pbkdf2;

    public Pbes2HmacShaWithAesKeyWrapAlgorithm(String str, String str2, AesKeyWrapManagementAlgorithm aesKeyWrapManagementAlgorithm) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm("n/a");
        this.pbkdf2 = new PasswordBasedKeyDerivationFunction2(str2);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(PbkdfKey.ALGORITHM);
        this.keyWrap = aesKeyWrapManagementAlgorithm;
        this.keyWrapKeyDescriptor = new ContentEncryptionKeyDescriptor(aesKeyWrapManagementAlgorithm.getKeyByteLength(), AesKey.ALGORITHM);
    }

    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        return this.keyWrap.manageForEncrypt(deriveForEncrypt(key, headers, providerContext), contentEncryptionKeyDescriptor, headers, bArr, providerContext);
    }

    /* access modifiers changed from: protected */
    public Key deriveForEncrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        byte[] bArr;
        Long longHeaderValue = headers.getLongHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT);
        if (longHeaderValue == null) {
            longHeaderValue = Long.valueOf(this.defaultIterationCount);
            headers.setObjectHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT, longHeaderValue);
        }
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT);
        Base64Url base64Url = new Base64Url();
        if (stringHeaderValue == null) {
            bArr = ByteUtil.randomBytes(this.defaultSaltByteLength, providerContext.getSecureRandom());
            headers.setStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT, base64Url.base64UrlEncode(bArr));
        } else {
            bArr = base64Url.base64UrlDecode(stringHeaderValue);
        }
        return deriveKey(key, longHeaderValue, bArr, providerContext);
    }

    public Key manageForDecrypt(Key key, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        return this.keyWrap.manageForDecrypt(deriveKey(key, headers.getLongHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT), new Base64Url().base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT)), providerContext), bArr, contentEncryptionKeyDescriptor, headers, providerContext);
    }

    private Key deriveKey(Key key, Long l, byte[] bArr, ProviderContext providerContext) throws JoseException {
        return new SecretKeySpec(this.pbkdf2.derive(key.getEncoded(), ByteUtil.concat(StringUtil.getBytesUtf8(getAlgorithmIdentifier()), ZERO_BYTE, bArr), l.intValue(), this.keyWrapKeyDescriptor.getContentEncryptionKeyByteLength(), providerContext.getSuppliedKeyProviderContext().getMacProvider()), this.keyWrapKeyDescriptor.getContentEncryptionKeyAlgorithm());
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    public void validateKey(Key key) throws InvalidKeyException {
        KeyValidationSupport.notNull(key);
    }

    public boolean isAvailable() {
        return this.keyWrap.isAvailable();
    }

    public long getDefaultIterationCount() {
        return this.defaultIterationCount;
    }

    public void setDefaultIterationCount(long j) {
        this.defaultIterationCount = j;
    }

    public int getDefaultSaltByteLength() {
        return this.defaultSaltByteLength;
    }

    public void setDefaultSaltByteLength(int i) {
        this.defaultSaltByteLength = i;
    }

    public static class HmacSha256Aes128 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha256Aes128() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS256_A128KW, MacUtil.HMAC_SHA256, new AesKeyWrapManagementAlgorithm.Aes128().setUseGeneralProviderContext());
        }
    }

    public static class HmacSha384Aes192 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha384Aes192() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS384_A192KW, MacUtil.HMAC_SHA384, new AesKeyWrapManagementAlgorithm.Aes192().setUseGeneralProviderContext());
        }
    }

    public static class HmacSha512Aes256 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha512Aes256() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS512_A256KW, MacUtil.HMAC_SHA512, new AesKeyWrapManagementAlgorithm.Aes256().setUseGeneralProviderContext());
        }
    }
}
