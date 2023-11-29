package org.jose4j.base64url;

import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;

public class SimplePEMEncoder {
    public static String encode(byte[] bArr) {
        return getCodec().encodeToString(bArr);
    }

    public static byte[] decode(String str) {
        return getCodec().decode(str);
    }

    static Base64 getCodec() {
        return new Base64(64);
    }
}
