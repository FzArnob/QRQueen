package org.jose4j.lang;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class HashUtil {
    public static final String SHA_256 = "SHA-256";

    public static MessageDigest getMessageDigest(String str) {
        return getMessageDigest(str, (String) null);
    }

    public static MessageDigest getMessageDigest(String str, String str2) {
        if (str2 != null) {
            return MessageDigest.getInstance(str, str2);
        }
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            throw new UncheckedJoseException("Unable to get MessageDigest instance with " + str);
        } catch (NoSuchProviderException e) {
            throw new UncheckedJoseException("Unable to get a MessageDigest implementation of algorithm name: " + str + " using provider " + str2, e);
        }
    }
}
