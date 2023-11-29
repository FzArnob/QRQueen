package org.jose4j.keys;

import javax.crypto.spec.SecretKeySpec;

public class HmacKey extends SecretKeySpec {
    public static final String ALGORITHM = "HMAC";

    public HmacKey(byte[] bArr) {
        super(bArr, ALGORITHM);
    }
}
