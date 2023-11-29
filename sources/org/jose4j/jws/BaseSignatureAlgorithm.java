package org.jose4j.jws;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSignatureAlgorithm extends AlgorithmInfo implements JsonWebSignatureAlgorithm {
    private AlgorithmParameterSpec algorithmParameterSpec;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public abstract void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException;

    public abstract void validatePublicKey(PublicKey publicKey) throws InvalidKeyException;

    public BaseSignatureAlgorithm(String str, String str2, String str3) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(str2);
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
        setKeyType(str3);
    }

    /* access modifiers changed from: protected */
    public void setAlgorithmParameterSpec(AlgorithmParameterSpec algorithmParameterSpec2) {
        this.algorithmParameterSpec = algorithmParameterSpec2;
    }

    public boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException {
        Signature signature = getSignature(providerContext);
        initForVerify(signature, key);
        try {
            signature.update(bArr2);
            return signature.verify(bArr);
        } catch (SignatureException e) {
            if (!this.log.isDebugEnabled()) {
                return false;
            }
            Logger logger = this.log;
            logger.debug("Problem verifying signature: " + e);
            return false;
        }
    }

    public byte[] sign(Key key, byte[] bArr, ProviderContext providerContext) throws JoseException {
        Signature signature = getSignature(providerContext);
        initForSign(signature, key, providerContext);
        try {
            signature.update(bArr);
            return signature.sign();
        } catch (SignatureException e) {
            throw new JoseException("Problem creating signature.", e);
        }
    }

    private void initForSign(Signature signature, Key key, ProviderContext providerContext) throws InvalidKeyException {
        try {
            PrivateKey privateKey = (PrivateKey) key;
            SecureRandom secureRandom = providerContext.getSecureRandom();
            if (secureRandom == null) {
                signature.initSign(privateKey);
            } else {
                signature.initSign(privateKey, secureRandom);
            }
        } catch (java.security.InvalidKeyException e) {
            throw new InvalidKeyException(getBadKeyMessage(key) + "for " + getJavaAlgorithm(), e);
        }
    }

    private void initForVerify(Signature signature, Key key) throws InvalidKeyException {
        try {
            signature.initVerify((PublicKey) key);
        } catch (java.security.InvalidKeyException e) {
            throw new InvalidKeyException(getBadKeyMessage(key) + "for " + getJavaAlgorithm(), e);
        }
    }

    private String getBadKeyMessage(Key key) {
        String str;
        if (key == null) {
            str = "key is null";
        } else {
            str = "algorithm=" + key.getAlgorithm();
        }
        return "The given key (" + str + ") is not valid ";
    }

    private Signature getSignature(ProviderContext providerContext) throws JoseException {
        Signature signature;
        String signatureProvider = providerContext.getSuppliedKeyProviderContext().getSignatureProvider();
        String javaAlgorithm = getJavaAlgorithm();
        if (signatureProvider == null) {
            try {
                signature = Signature.getInstance(javaAlgorithm);
            } catch (NoSuchAlgorithmException e) {
                throw new JoseException("Unable to get an implementation of algorithm name: " + javaAlgorithm, e);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new JoseException("Invalid algorithm parameter (" + this.algorithmParameterSpec + ") for: " + javaAlgorithm, e2);
            } catch (NoSuchProviderException e3) {
                throw new JoseException("Unable to get an implementation of " + javaAlgorithm + " for provider " + signatureProvider, e3);
            }
        } else {
            signature = Signature.getInstance(javaAlgorithm, signatureProvider);
        }
        AlgorithmParameterSpec algorithmParameterSpec2 = this.algorithmParameterSpec;
        if (algorithmParameterSpec2 != null) {
            try {
                signature.setParameter(algorithmParameterSpec2);
            } catch (UnsupportedOperationException e4) {
                if (this.log.isDebugEnabled()) {
                    Logger logger = this.log;
                    logger.debug("Unable to set algorithm parameter spec on Signature (java algorithm name: " + javaAlgorithm + ") so ignoring the UnsupportedOperationException and relying on the default parameters.", (Throwable) e4);
                }
            }
        }
        return signature;
    }

    public void validateSigningKey(Key key) throws InvalidKeyException {
        checkForNullKey(key);
        try {
            validatePrivateKey((PrivateKey) key);
        } catch (ClassCastException e) {
            throw new InvalidKeyException(getBadKeyMessage(key) + "(not a private key or is the wrong type of key) for " + getJavaAlgorithm() + " / " + getAlgorithmIdentifier() + " " + e);
        }
    }

    public void validateVerificationKey(Key key) throws InvalidKeyException {
        checkForNullKey(key);
        try {
            validatePublicKey((PublicKey) key);
        } catch (ClassCastException e) {
            throw new InvalidKeyException(getBadKeyMessage(key) + "(not a public key or is the wrong type of key) for " + getJavaAlgorithm() + InternalZipConstants.ZIP_FILE_SEPARATOR + getAlgorithmIdentifier() + " " + e);
        }
    }

    private void checkForNullKey(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Key cannot be null");
        }
    }

    public boolean isAvailable() {
        try {
            return getSignature(new ProviderContext()) != null;
        } catch (Exception e) {
            Logger logger = this.log;
            logger.debug(getAlgorithmIdentifier() + " vai " + getJavaAlgorithm() + " is NOT available from the underlying JCE (" + ExceptionHelp.toStringWithCauses(e) + ").");
            return false;
        }
    }
}
