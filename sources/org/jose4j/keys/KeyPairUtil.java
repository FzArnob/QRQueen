package org.jose4j.keys;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Set;
import org.jose4j.base64url.SimplePEMEncoder;
import org.jose4j.lang.JoseException;

abstract class KeyPairUtil {
    private static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
    private static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";
    protected String provider;
    protected SecureRandom secureRandom;

    /* access modifiers changed from: package-private */
    public abstract String getAlgorithm();

    protected KeyPairUtil(String str, SecureRandom secureRandom2) {
        this.provider = str;
        this.secureRandom = secureRandom2;
    }

    /* access modifiers changed from: protected */
    public KeyFactory getKeyFactory() throws JoseException {
        String algorithm = getAlgorithm();
        try {
            String str = this.provider;
            return str == null ? KeyFactory.getInstance(algorithm) : KeyFactory.getInstance(algorithm, str);
        } catch (NoSuchAlgorithmException e) {
            throw new JoseException("Couldn't find " + algorithm + " KeyFactory! " + e, e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get KeyFactory instance with provider " + this.provider, e2);
        }
    }

    /* access modifiers changed from: protected */
    public KeyPairGenerator getKeyPairGenerator() throws JoseException {
        String algorithm = getAlgorithm();
        try {
            String str = this.provider;
            return str == null ? KeyPairGenerator.getInstance(algorithm) : KeyPairGenerator.getInstance(algorithm, str);
        } catch (NoSuchAlgorithmException e) {
            throw new JoseException("Couldn't find " + algorithm + " KeyPairGenerator! " + e, e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get KeyPairGenerator instance with provider " + this.provider, e2);
        }
    }

    public PublicKey fromPemEncoded(String str) throws JoseException, InvalidKeySpecException {
        return getKeyFactory().generatePublic(new X509EncodedKeySpec(SimplePEMEncoder.decode(str.substring(str.indexOf(BEGIN_PUBLIC_KEY) + 26, str.indexOf(END_PUBLIC_KEY)).trim())));
    }

    public static String pemEncode(PublicKey publicKey) {
        byte[] encoded = publicKey.getEncoded();
        return "-----BEGIN PUBLIC KEY-----\r\n" + SimplePEMEncoder.encode(encoded) + END_PUBLIC_KEY;
    }

    public boolean isAvailable() {
        Set<String> algorithms = Security.getAlgorithms("KeyFactory");
        Set<String> algorithms2 = Security.getAlgorithms("KeyPairGenerator");
        String algorithm = getAlgorithm();
        return algorithms2.contains(algorithm) && algorithms.contains(algorithm);
    }
}
