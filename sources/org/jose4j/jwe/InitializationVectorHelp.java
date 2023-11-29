package org.jose4j.jwe;

import java.security.SecureRandom;
import org.jose4j.lang.ByteUtil;

public class InitializationVectorHelp {
    static byte[] iv(int i, byte[] bArr, SecureRandom secureRandom) {
        return bArr == null ? ByteUtil.randomBytes(i, secureRandom) : bArr;
    }
}
