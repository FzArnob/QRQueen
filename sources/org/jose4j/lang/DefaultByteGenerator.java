package org.jose4j.lang;

import java.security.SecureRandom;

public class DefaultByteGenerator implements ByteGenerator {
    private final SecureRandom random = new SecureRandom();

    public byte[] randomBytes(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }
}
