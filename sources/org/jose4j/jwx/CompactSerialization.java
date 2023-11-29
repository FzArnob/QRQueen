package org.jose4j.jwx;

import org.jose4j.lang.JoseException;

public class CompactSerialization {
    public static String[] deserialize(String str) {
        return CompactSerializer.deserialize(str);
    }

    public static String serialize(String... strArr) throws JoseException {
        return CompactSerializer.serialize(strArr);
    }
}
