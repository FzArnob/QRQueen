package org.jose4j.keys;

import java.math.BigInteger;
import org.jose4j.base64url.Base64Url;
import org.jose4j.lang.ByteUtil;

public class BigEndianBigInteger {
    public static BigInteger fromBytes(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static BigInteger fromBase64Url(String str) {
        return fromBytes(new Base64Url().base64UrlDecode(str));
    }

    public static byte[] toByteArray(BigInteger bigInteger, int i) {
        byte[] byteArray = toByteArray(bigInteger);
        if (i <= byteArray.length) {
            return byteArray;
        }
        return ByteUtil.concat(new byte[(i - byteArray.length)], byteArray);
    }

    public static byte[] toByteArray(BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            byte[] byteArray = bigInteger.toByteArray();
            return (bigInteger.bitLength() % 8 == 0 && byteArray[0] == 0 && byteArray.length > 1) ? ByteUtil.subArray(byteArray, 1, byteArray.length - 1) : byteArray;
        }
        throw new IllegalArgumentException("Cannot convert negative values to an unsigned magnitude byte array: " + bigInteger);
    }

    public static String toBase64Url(BigInteger bigInteger) {
        return new Base64Url().base64UrlEncode(toByteArray(bigInteger));
    }

    public static String toBase64Url(BigInteger bigInteger, int i) {
        return new Base64Url().base64UrlEncode(toByteArray(bigInteger, i));
    }
}
