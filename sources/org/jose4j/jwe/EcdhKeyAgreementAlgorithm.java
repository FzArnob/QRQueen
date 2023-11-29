package org.jose4j.jwe;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmAvailability;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwe.kdf.KdfUtil;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.EcKeyUtil;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;

public class EcdhKeyAgreementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    String algorithmIdHeaderName;

    public EcdhKeyAgreementAlgorithm() {
        this.algorithmIdHeaderName = "enc";
        setAlgorithmIdentifier(KeyManagementAlgorithmIdentifiers.ECDH_ES);
        setJavaAlgorithm("ECDH");
        setKeyType("EC");
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
    }

    public EcdhKeyAgreementAlgorithm(String str) {
        this();
        this.algorithmIdHeaderName = str;
    }

    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        KeyValidationSupport.cekNotAllowed(bArr, getAlgorithmIdentifier());
        return manageForEncrypt(key, contentEncryptionKeyDescriptor, headers, (PublicJsonWebKey) EcJwkGenerator.generateJwk(((ECPublicKey) key).getParams(), providerContext.getGeneralProviderContext().getKeyPairGeneratorProvider(), providerContext.getSecureRandom()), providerContext);
    }

    /* access modifiers changed from: package-private */
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, PublicJsonWebKey publicJsonWebKey, ProviderContext providerContext) throws JoseException {
        headers.setJwkHeaderValue(HeaderParameterNames.EPHEMERAL_PUBLIC_KEY, publicJsonWebKey);
        return new ContentEncryptionKeys(kdf(contentEncryptionKeyDescriptor, headers, generateEcdhSecret(publicJsonWebKey.getPrivateKey(), (PublicKey) key, providerContext), providerContext), (byte[]) null);
    }

    public Key manageForDecrypt(Key key, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        ECPublicKey eCPublicKey = (ECPublicKey) headers.getPublicJwkHeaderValue(HeaderParameterNames.EPHEMERAL_PUBLIC_KEY, providerContext.getGeneralProviderContext().getKeyFactoryProvider()).getKey();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) key;
        checkPointIsOnCurve(eCPublicKey, eCPrivateKey);
        return new SecretKeySpec(kdf(contentEncryptionKeyDescriptor, headers, generateEcdhSecret(eCPrivateKey, eCPublicKey, providerContext), providerContext), contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm());
    }

    private void checkPointIsOnCurve(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws JoseException {
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        ECPoint w = eCPublicKey.getW();
        BigInteger affineX = w.getAffineX();
        BigInteger affineY = w.getAffineY();
        BigInteger a = curve.getA();
        BigInteger b = curve.getB();
        BigInteger p = ((ECFieldFp) curve.getField()).getP();
        if (!affineY.pow(2).mod(p).equals(affineX.pow(3).add(a.multiply(affineX)).add(b).mod(p))) {
            throw new JoseException("epk is invalid for " + EllipticCurves.getName(curve));
        }
    }

    private byte[] kdf(ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) {
        return new KdfUtil(providerContext.getGeneralProviderContext().getMessageDigestProvider()).kdf(bArr, ByteUtil.bitLength(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength()), headers.getStringHeaderValue(this.algorithmIdHeaderName), headers.getStringHeaderValue(HeaderParameterNames.AGREEMENT_PARTY_U_INFO), headers.getStringHeaderValue(HeaderParameterNames.AGREEMENT_PARTY_V_INFO));
    }

    private KeyAgreement getKeyAgreement(String str) throws JoseException {
        String javaAlgorithm = getJavaAlgorithm();
        if (str != null) {
            return KeyAgreement.getInstance(javaAlgorithm, str);
        }
        try {
            return KeyAgreement.getInstance(javaAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new UncheckedJoseException("No " + javaAlgorithm + " KeyAgreement available.", e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get " + javaAlgorithm + " KeyAgreement with provider " + str, e2);
        }
    }

    private byte[] generateEcdhSecret(PrivateKey privateKey, PublicKey publicKey, ProviderContext providerContext) throws JoseException {
        KeyAgreement keyAgreement = getKeyAgreement(providerContext.getSuppliedKeyProviderContext().getKeyAgreementProvider());
        try {
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);
            return keyAgreement.generateSecret();
        } catch (InvalidKeyException e) {
            throw new org.jose4j.lang.InvalidKeyException("Invalid Key for " + getJavaAlgorithm() + " key agreement - " + e, e);
        }
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws org.jose4j.lang.InvalidKeyException {
        KeyValidationSupport.castKey(key, ECPublicKey.class);
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws org.jose4j.lang.InvalidKeyException {
        KeyValidationSupport.castKey(key, ECPrivateKey.class);
    }

    public boolean isAvailable() {
        return new EcKeyUtil().isAvailable() && AlgorithmAvailability.isAvailable("KeyAgreement", getJavaAlgorithm());
    }
}
