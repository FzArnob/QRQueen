package org.jose4j.jwk;

import java.security.Key;
import java.security.KeyPair;
import java.security.SecureRandom;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.keys.RsaKeyUtil;
import org.jose4j.lang.JoseException;

public class RsaJwkGenerator {
    public static RsaJsonWebKey generateJwk(int i) throws JoseException {
        return generateJwk(i, (String) null, (SecureRandom) null);
    }

    public static RsaJsonWebKey generateJwk(int i, String str, SecureRandom secureRandom) throws JoseException {
        KeyPair generateKeyPair = new RsaKeyUtil(str, secureRandom).generateKeyPair(i);
        RsaJsonWebKey rsaJsonWebKey = (RsaJsonWebKey) PublicJsonWebKey.Factory.newPublicJwk((Key) generateKeyPair.getPublic());
        rsaJsonWebKey.setPrivateKey(generateKeyPair.getPrivate());
        return rsaJsonWebKey;
    }
}
