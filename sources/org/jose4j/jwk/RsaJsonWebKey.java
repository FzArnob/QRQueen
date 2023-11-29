package org.jose4j.jwk;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import org.jose4j.keys.RsaKeyUtil;
import org.jose4j.lang.JoseException;

public class RsaJsonWebKey extends PublicJsonWebKey {
    public static final String EXPONENT_MEMBER_NAME = "e";
    public static final String FACTOR_CRT_COEFFICIENT = "t";
    public static final String FACTOR_CRT_EXPONENT_OTHER_MEMBER_NAME = "d";
    public static final String FIRST_CRT_COEFFICIENT_MEMBER_NAME = "qi";
    public static final String FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME = "dp";
    public static final String FIRST_PRIME_FACTOR_MEMBER_NAME = "p";
    public static final String KEY_TYPE = "RSA";
    public static final String MODULUS_MEMBER_NAME = "n";
    public static final String OTHER_PRIMES_INFO_MEMBER_NAME = "oth";
    public static final String PRIME_FACTOR_OTHER_MEMBER_NAME = "r";
    public static final String PRIVATE_EXPONENT_MEMBER_NAME = "d";
    public static final String SECOND_FACTOR_CRT_EXPONENT_MEMBER_NAME = "dq";
    public static final String SECOND_PRIME_FACTOR_MEMBER_NAME = "q";

    public String getKeyType() {
        return "RSA";
    }

    public RsaJsonWebKey(RSAPublicKey rSAPublicKey) {
        super((PublicKey) rSAPublicKey);
    }

    public RsaJsonWebKey(Map<String, Object> map) throws JoseException {
        this(map, (String) null);
    }

    public RsaJsonWebKey(Map<String, Object> map, String str) throws JoseException {
        super(map, str);
        BigInteger bigIntFromBase64UrlEncodedParam = getBigIntFromBase64UrlEncodedParam(map, MODULUS_MEMBER_NAME, true);
        BigInteger bigIntFromBase64UrlEncodedParam2 = getBigIntFromBase64UrlEncodedParam(map, EXPONENT_MEMBER_NAME, true);
        RsaKeyUtil rsaKeyUtil = new RsaKeyUtil(str, (SecureRandom) null);
        this.key = rsaKeyUtil.publicKey(bigIntFromBase64UrlEncodedParam, bigIntFromBase64UrlEncodedParam2);
        checkForBareKeyCertMismatch();
        if (map.containsKey("d")) {
            BigInteger bigIntFromBase64UrlEncodedParam3 = getBigIntFromBase64UrlEncodedParam(map, "d", false);
            if (map.containsKey(FIRST_PRIME_FACTOR_MEMBER_NAME)) {
                this.privateKey = rsaKeyUtil.privateKey(bigIntFromBase64UrlEncodedParam, bigIntFromBase64UrlEncodedParam2, bigIntFromBase64UrlEncodedParam3, getBigIntFromBase64UrlEncodedParam(map, FIRST_PRIME_FACTOR_MEMBER_NAME, false), getBigIntFromBase64UrlEncodedParam(map, SECOND_PRIME_FACTOR_MEMBER_NAME, false), getBigIntFromBase64UrlEncodedParam(map, FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, false), getBigIntFromBase64UrlEncodedParam(map, SECOND_FACTOR_CRT_EXPONENT_MEMBER_NAME, false), getBigIntFromBase64UrlEncodedParam(map, FIRST_CRT_COEFFICIENT_MEMBER_NAME, false));
            } else {
                this.privateKey = rsaKeyUtil.privateKey(bigIntFromBase64UrlEncodedParam, bigIntFromBase64UrlEncodedParam3);
            }
        }
        removeFromOtherParams(MODULUS_MEMBER_NAME, EXPONENT_MEMBER_NAME, "d", FIRST_PRIME_FACTOR_MEMBER_NAME, SECOND_PRIME_FACTOR_MEMBER_NAME, FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, SECOND_FACTOR_CRT_EXPONENT_MEMBER_NAME, FIRST_CRT_COEFFICIENT_MEMBER_NAME);
    }

    public RSAPublicKey getRsaPublicKey() {
        return (RSAPublicKey) this.key;
    }

    public RSAPublicKey getRSAPublicKey() {
        return getRsaPublicKey();
    }

    public RSAPrivateKey getRsaPrivateKey() {
        return (RSAPrivateKey) this.privateKey;
    }

    /* access modifiers changed from: protected */
    public void fillPublicTypeSpecificParams(Map<String, Object> map) {
        RSAPublicKey rsaPublicKey = getRsaPublicKey();
        putBigIntAsBase64UrlEncodedParam(map, MODULUS_MEMBER_NAME, rsaPublicKey.getModulus());
        putBigIntAsBase64UrlEncodedParam(map, EXPONENT_MEMBER_NAME, rsaPublicKey.getPublicExponent());
    }

    /* access modifiers changed from: protected */
    public void fillPrivateTypeSpecificParams(Map<String, Object> map) {
        RSAPrivateKey rsaPrivateKey = getRsaPrivateKey();
        if (rsaPrivateKey != null) {
            putBigIntAsBase64UrlEncodedParam(map, "d", rsaPrivateKey.getPrivateExponent());
            if (rsaPrivateKey instanceof RSAPrivateCrtKey) {
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) rsaPrivateKey;
                putBigIntAsBase64UrlEncodedParam(map, FIRST_PRIME_FACTOR_MEMBER_NAME, rSAPrivateCrtKey.getPrimeP());
                putBigIntAsBase64UrlEncodedParam(map, SECOND_PRIME_FACTOR_MEMBER_NAME, rSAPrivateCrtKey.getPrimeQ());
                putBigIntAsBase64UrlEncodedParam(map, FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, rSAPrivateCrtKey.getPrimeExponentP());
                putBigIntAsBase64UrlEncodedParam(map, SECOND_FACTOR_CRT_EXPONENT_MEMBER_NAME, rSAPrivateCrtKey.getPrimeExponentQ());
                putBigIntAsBase64UrlEncodedParam(map, FIRST_CRT_COEFFICIENT_MEMBER_NAME, rSAPrivateCrtKey.getCrtCoefficient());
            }
        }
    }

    /* access modifiers changed from: protected */
    public String produceThumbprintHashInput() {
        HashMap hashMap = new HashMap();
        fillPublicTypeSpecificParams(hashMap);
        return String.format("{\"e\":\"%s\",\"kty\":\"RSA\",\"n\":\"%s\"}", new Object[]{hashMap.get(EXPONENT_MEMBER_NAME), hashMap.get(MODULUS_MEMBER_NAME)});
    }
}
