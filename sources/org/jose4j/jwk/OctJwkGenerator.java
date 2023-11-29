package org.jose4j.jwk;

import java.security.Key;
import java.security.SecureRandom;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;

public class OctJwkGenerator {
    public static OctetSequenceJsonWebKey generateJwk(int i) {
        return generateJwk(i, (SecureRandom) null);
    }

    public static OctetSequenceJsonWebKey generateJwk(int i, SecureRandom secureRandom) {
        return new OctetSequenceJsonWebKey((Key) new AesKey(ByteUtil.randomBytes(ByteUtil.byteLength(i), secureRandom)));
    }
}
