package org.jose4j.keys;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import org.jose4j.lang.JoseException;

public class RsaKeyUtil extends KeyPairUtil {
    public static final String RSA = "RSA";

    /* access modifiers changed from: package-private */
    public String getAlgorithm() {
        return "RSA";
    }

    public /* bridge */ /* synthetic */ PublicKey fromPemEncoded(String str) throws JoseException, InvalidKeySpecException {
        return super.fromPemEncoded(str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public RsaKeyUtil() {
        this((String) null, (SecureRandom) null);
    }

    public RsaKeyUtil(String str, SecureRandom secureRandom) {
        super(str, secureRandom);
    }

    public RSAPublicKey publicKey(BigInteger bigInteger, BigInteger bigInteger2) throws JoseException {
        try {
            return (RSAPublicKey) getKeyFactory().generatePublic(new RSAPublicKeySpec(bigInteger, bigInteger2));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    public RSAPrivateKey privateKey(BigInteger bigInteger, BigInteger bigInteger2) throws JoseException {
        return getRsaPrivateKey(new RSAPrivateKeySpec(bigInteger, bigInteger2));
    }

    public RSAPrivateKey privateKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) throws JoseException {
        return getRsaPrivateKey(new RSAPrivateCrtKeySpec(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigInteger6, bigInteger7, bigInteger8));
    }

    public RSAPrivateKey getRsaPrivateKey(RSAPrivateKeySpec rSAPrivateKeySpec) throws JoseException {
        try {
            return (RSAPrivateKey) getKeyFactory().generatePrivate(rSAPrivateKeySpec);
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    public KeyPair generateKeyPair(int i) throws JoseException {
        KeyPairGenerator keyPairGenerator = getKeyPairGenerator();
        if (this.secureRandom == null) {
            keyPairGenerator.initialize(i);
        } else {
            keyPairGenerator.initialize(i, this.secureRandom);
        }
        return keyPairGenerator.generateKeyPair();
    }
}
