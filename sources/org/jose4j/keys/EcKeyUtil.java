package org.jose4j.keys;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import org.jose4j.lang.JoseException;

public class EcKeyUtil extends KeyPairUtil {
    public static final String EC = "EC";

    /* access modifiers changed from: package-private */
    public String getAlgorithm() {
        return "EC";
    }

    public /* bridge */ /* synthetic */ PublicKey fromPemEncoded(String str) throws JoseException, InvalidKeySpecException {
        return super.fromPemEncoded(str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public EcKeyUtil() {
        this((String) null, (SecureRandom) null);
    }

    public EcKeyUtil(String str, SecureRandom secureRandom) {
        super(str, secureRandom);
    }

    public ECPublicKey publicKey(BigInteger bigInteger, BigInteger bigInteger2, ECParameterSpec eCParameterSpec) throws JoseException {
        try {
            return (ECPublicKey) getKeyFactory().generatePublic(new ECPublicKeySpec(new ECPoint(bigInteger, bigInteger2), eCParameterSpec));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    public ECPrivateKey privateKey(BigInteger bigInteger, ECParameterSpec eCParameterSpec) throws JoseException {
        try {
            return (ECPrivateKey) getKeyFactory().generatePrivate(new ECPrivateKeySpec(bigInteger, eCParameterSpec));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    public KeyPair generateKeyPair(ECParameterSpec eCParameterSpec) throws JoseException {
        KeyPairGenerator keyPairGenerator = getKeyPairGenerator();
        try {
            if (this.secureRandom == null) {
                keyPairGenerator.initialize(eCParameterSpec);
            } else {
                keyPairGenerator.initialize(eCParameterSpec, this.secureRandom);
            }
            return keyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            throw new JoseException("Unable to create EC key pair with spec " + eCParameterSpec, e);
        }
    }
}
