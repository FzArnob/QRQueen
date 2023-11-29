package org.jose4j.jwe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwx.Headers;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WrappingKeyManagementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    private AlgorithmParameterSpec algorithmParameterSpec;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected boolean useSuppliedKeyProviderContext = true;

    public WrappingKeyManagementAlgorithm(String str, String str2) {
        setJavaAlgorithm(str);
        setAlgorithmIdentifier(str2);
    }

    public void setAlgorithmParameterSpec(AlgorithmParameterSpec algorithmParameterSpec2) {
        this.algorithmParameterSpec = algorithmParameterSpec2;
    }

    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        if (bArr == null) {
            bArr = ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength());
        }
        return manageForEnc(key, contentEncryptionKeyDescriptor, bArr, providerContext);
    }

    /* access modifiers changed from: protected */
    public ContentEncryptionKeys manageForEnc(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, byte[] bArr, ProviderContext providerContext) throws JoseException {
        Cipher cipher = CipherUtil.getCipher(getJavaAlgorithm(), (this.useSuppliedKeyProviderContext ? providerContext.getSuppliedKeyProviderContext() : providerContext.getGeneralProviderContext()).getCipherProvider());
        try {
            initCipher(cipher, 3, key);
            return new ContentEncryptionKeys(bArr, cipher.wrap(new SecretKeySpec(bArr, contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm())));
        } catch (InvalidKeyException e) {
            throw new org.jose4j.lang.InvalidKeyException("Unable to encrypt (" + cipher.getAlgorithm() + ") the Content Encryption Key: " + e, e);
        } catch (InvalidAlgorithmParameterException | IllegalBlockSizeException e2) {
            throw new JoseException("Unable to encrypt (" + cipher.getAlgorithm() + ") the Content Encryption Key: " + e2, e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void initCipher(Cipher cipher, int i, Key key) throws InvalidAlgorithmParameterException, InvalidKeyException {
        AlgorithmParameterSpec algorithmParameterSpec2 = this.algorithmParameterSpec;
        if (algorithmParameterSpec2 == null) {
            cipher.init(i, key);
        } else {
            cipher.init(i, key, algorithmParameterSpec2);
        }
    }

    public Key manageForDecrypt(Key key, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        Cipher cipher = CipherUtil.getCipher(getJavaAlgorithm(), providerContext.getSuppliedKeyProviderContext().getCipherProvider());
        try {
            initCipher(cipher, 4, key);
            String contentEncryptionKeyAlgorithm = contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm();
            try {
                return cipher.unwrap(bArr, contentEncryptionKeyAlgorithm, 3);
            } catch (Exception e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Key unwrap failed. Substituting a randomly generated CEK and proceeding. {}", (Object) ExceptionHelp.toStringWithCausesAndAbbreviatedStack(e, JsonWebEncryption.class));
                }
                return new SecretKeySpec(ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength()), contentEncryptionKeyAlgorithm);
            }
        } catch (InvalidKeyException e2) {
            throw new org.jose4j.lang.InvalidKeyException("Unable to initialize cipher (" + cipher.getAlgorithm() + ") for key decryption - " + e2, e2);
        } catch (InvalidAlgorithmParameterException e3) {
            throw new JoseException("Unable to initialize cipher (" + cipher.getAlgorithm() + ") for key decryption - " + e3, e3);
        }
    }
}
