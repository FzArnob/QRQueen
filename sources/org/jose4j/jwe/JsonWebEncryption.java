package org.jose4j.jwe;

import java.security.Key;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmFactoryFactory;
import org.jose4j.jwx.CompactSerializer;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.zip.CompressionAlgorithmIdentifiers;

public class JsonWebEncryption extends JsonWebStructure {
    public static final short COMPACT_SERIALIZATION_PARTS = 5;
    private Base64Url base64url = new Base64Url();
    byte[] ciphertext;
    private AlgorithmConstraints contentEncryptionAlgorithmConstraints = AlgorithmConstraints.NO_CONSTRAINTS;
    byte[] contentEncryptionKey;
    byte[] encryptedKey;
    byte[] iv;
    private byte[] plaintext;
    private String plaintextCharEncoding = "UTF-8";

    public void setPlainTextCharEncoding(String str) {
        this.plaintextCharEncoding = str;
    }

    public void setPlaintext(byte[] bArr) {
        this.plaintext = bArr;
    }

    public void setPlaintext(String str) {
        this.plaintext = StringUtil.getBytesUnchecked(str, this.plaintextCharEncoding);
    }

    public String getPlaintextString() throws JoseException {
        return StringUtil.newString(getPlaintextBytes(), this.plaintextCharEncoding);
    }

    public byte[] getPlaintextBytes() throws JoseException {
        if (this.plaintext == null) {
            decrypt();
        }
        return this.plaintext;
    }

    public String getPayload() throws JoseException {
        return getPlaintextString();
    }

    public void setPayload(String str) {
        setPlaintext(str);
    }

    public void setEncryptionMethodHeaderParameter(String str) {
        setHeader("enc", str);
    }

    public String getEncryptionMethodHeaderParameter() {
        return getHeader("enc");
    }

    public void setCompressionAlgorithmHeaderParameter(String str) {
        setHeader(HeaderParameterNames.ZIP, str);
    }

    public String getCompressionAlgorithmHeaderParameter() {
        return getHeader(HeaderParameterNames.ZIP);
    }

    public void enableDefaultCompression() {
        setCompressionAlgorithmHeaderParameter(CompressionAlgorithmIdentifiers.DEFLATE);
    }

    public void setContentEncryptionAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.contentEncryptionAlgorithmConstraints = algorithmConstraints;
    }

    public ContentEncryptionAlgorithm getContentEncryptionAlgorithm() throws InvalidAlgorithmException {
        String encryptionMethodHeaderParameter = getEncryptionMethodHeaderParameter();
        if (encryptionMethodHeaderParameter != null) {
            this.contentEncryptionAlgorithmConstraints.checkConstraint(encryptionMethodHeaderParameter);
            return AlgorithmFactoryFactory.getInstance().getJweContentEncryptionAlgorithmFactory().getAlgorithm(encryptionMethodHeaderParameter);
        }
        throw new InvalidAlgorithmException("Content encryption header (enc) not set.");
    }

    public KeyManagementAlgorithm getKeyManagementModeAlgorithm() throws InvalidAlgorithmException {
        return getKeyManagementModeAlgorithm(true);
    }

    /* access modifiers changed from: package-private */
    public KeyManagementAlgorithm getKeyManagementModeAlgorithm(boolean z) throws InvalidAlgorithmException {
        String algorithmHeaderValue = getAlgorithmHeaderValue();
        if (algorithmHeaderValue != null) {
            if (z) {
                getAlgorithmConstraints().checkConstraint(algorithmHeaderValue);
            }
            return AlgorithmFactoryFactory.getInstance().getJweKeyManagementAlgorithmFactory().getAlgorithm(algorithmHeaderValue);
        }
        throw new InvalidAlgorithmException("Encryption key management algorithm header (alg) not set.");
    }

    public KeyManagementAlgorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException {
        return getKeyManagementModeAlgorithm(false);
    }

    public KeyManagementAlgorithm getAlgorithm() throws InvalidAlgorithmException {
        return getKeyManagementModeAlgorithm();
    }

    /* access modifiers changed from: protected */
    public void setCompactSerializationParts(String[] strArr) throws JoseException {
        if (strArr.length == 5) {
            setEncodedHeader(strArr[0]);
            this.encryptedKey = this.base64url.base64UrlDecode(strArr[1]);
            setEncodedIv(strArr[2]);
            String str = strArr[3];
            checkNotEmptyPart(str, "Encoded JWE Ciphertext");
            this.ciphertext = this.base64url.base64UrlDecode(str);
            String str2 = strArr[4];
            checkNotEmptyPart(str2, "Encoded JWE Authentication Tag");
            setIntegrity(this.base64url.base64UrlDecode(str2));
            return;
        }
        throw new JoseException("A JWE Compact Serialization must have exactly 5 parts separated by period ('.') characters");
    }

    private void decrypt() throws JoseException {
        KeyManagementAlgorithm keyManagementModeAlgorithm = getKeyManagementModeAlgorithm();
        ContentEncryptionAlgorithm contentEncryptionAlgorithm = getContentEncryptionAlgorithm();
        ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor = contentEncryptionAlgorithm.getContentEncryptionKeyDescriptor();
        if (isDoKeyValidation()) {
            keyManagementModeAlgorithm.validateDecryptionKey(getKey(), contentEncryptionAlgorithm);
        }
        checkCrit();
        ContentEncryptionAlgorithm contentEncryptionAlgorithm2 = contentEncryptionAlgorithm;
        setPlaintext(decompress(getHeaders(), contentEncryptionAlgorithm2.decrypt(new ContentEncryptionParts(this.iv, this.ciphertext, getIntegrity()), getEncodedHeaderAsciiBytesForAdditionalAuthenticatedData(), keyManagementModeAlgorithm.manageForDecrypt(getKey(), getEncryptedKey(), contentEncryptionKeyDescriptor, getHeaders(), getProviderCtx()).getEncoded(), getHeaders(), getProviderCtx())));
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }

    /* access modifiers changed from: package-private */
    public byte[] getEncodedHeaderAsciiBytesForAdditionalAuthenticatedData() {
        return StringUtil.getBytesAscii(getEncodedHeader());
    }

    /* access modifiers changed from: package-private */
    public byte[] decompress(Headers headers, byte[] bArr) throws JoseException {
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.ZIP);
        return stringHeaderValue != null ? AlgorithmFactoryFactory.getInstance().getCompressionAlgorithmFactory().getAlgorithm(stringHeaderValue).decompress(bArr) : bArr;
    }

    /* access modifiers changed from: package-private */
    public byte[] compress(Headers headers, byte[] bArr) throws InvalidAlgorithmException {
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.ZIP);
        return stringHeaderValue != null ? AlgorithmFactoryFactory.getInstance().getCompressionAlgorithmFactory().getAlgorithm(stringHeaderValue).compress(bArr) : bArr;
    }

    public String getCompactSerialization() throws JoseException {
        KeyManagementAlgorithm keyManagementModeAlgorithm = getKeyManagementModeAlgorithm();
        ContentEncryptionAlgorithm contentEncryptionAlgorithm = getContentEncryptionAlgorithm();
        ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor = contentEncryptionAlgorithm.getContentEncryptionKeyDescriptor();
        Key key = getKey();
        if (isDoKeyValidation()) {
            keyManagementModeAlgorithm.validateEncryptionKey(getKey(), contentEncryptionAlgorithm);
        }
        ContentEncryptionKeys manageForEncrypt = keyManagementModeAlgorithm.manageForEncrypt(key, contentEncryptionKeyDescriptor, getHeaders(), this.contentEncryptionKey, getProviderCtx());
        setContentEncryptionKey(manageForEncrypt.getContentEncryptionKey());
        this.encryptedKey = manageForEncrypt.getEncryptedKey();
        byte[] encodedHeaderAsciiBytesForAdditionalAuthenticatedData = getEncodedHeaderAsciiBytesForAdditionalAuthenticatedData();
        byte[] contentEncryptionKey2 = manageForEncrypt.getContentEncryptionKey();
        byte[] bArr = this.plaintext;
        if (bArr != null) {
            ContentEncryptionParts encrypt = contentEncryptionAlgorithm.encrypt(compress(getHeaders(), bArr), encodedHeaderAsciiBytesForAdditionalAuthenticatedData, contentEncryptionKey2, getHeaders(), getIv(), getProviderCtx());
            setIv(encrypt.getIv());
            this.ciphertext = encrypt.getCiphertext();
            String base64UrlEncode = this.base64url.base64UrlEncode(encrypt.getIv());
            String base64UrlEncode2 = this.base64url.base64UrlEncode(encrypt.getCiphertext());
            String base64UrlEncode3 = this.base64url.base64UrlEncode(encrypt.getAuthenticationTag());
            return CompactSerializer.serialize(getEncodedHeader(), this.base64url.base64UrlEncode(manageForEncrypt.getEncryptedKey()), base64UrlEncode, base64UrlEncode2, base64UrlEncode3);
        }
        throw new NullPointerException("The plaintext payload for the JWE has not been set.");
    }

    public byte[] getContentEncryptionKey() {
        return this.contentEncryptionKey;
    }

    public void setContentEncryptionKey(byte[] bArr) {
        this.contentEncryptionKey = bArr;
    }

    public void setEncodedContentEncryptionKey(String str) {
        setContentEncryptionKey(Base64Url.decode(str));
    }

    public byte[] getIv() {
        return this.iv;
    }

    public void setIv(byte[] bArr) {
        this.iv = bArr;
    }

    public void setEncodedIv(String str) {
        setIv(this.base64url.base64UrlDecode(str));
    }
}
