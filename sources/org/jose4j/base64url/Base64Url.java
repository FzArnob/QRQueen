package org.jose4j.base64url;

import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;
import org.jose4j.lang.StringUtil;

public class Base64Url {
    private Base64 base64urlCodec = new Base64(-1, (byte[]) null, true);

    public String base64UrlDecodeToUtf8String(String str) {
        return base64UrlDecodeToString(str, "UTF-8");
    }

    public String base64UrlDecodeToString(String str, String str2) {
        return StringUtil.newString(base64UrlDecode(str), str2);
    }

    public byte[] base64UrlDecode(String str) {
        return this.base64urlCodec.decode(str);
    }

    public String base64UrlEncodeUtf8ByteRepresentation(String str) {
        return base64UrlEncode(str, "UTF-8");
    }

    public String base64UrlEncode(String str, String str2) {
        return base64UrlEncode(StringUtil.getBytesUnchecked(str, str2));
    }

    public String base64UrlEncode(byte[] bArr) {
        return this.base64urlCodec.encodeToString(bArr);
    }

    private static Base64Url getOne() {
        return new Base64Url();
    }

    public static String decodeToUtf8String(String str) {
        return getOne().base64UrlDecodeToString(str, "UTF-8");
    }

    public static String decodeToString(String str, String str2) {
        return getOne().base64UrlDecodeToString(str, str2);
    }

    public static byte[] decode(String str) {
        return getOne().base64UrlDecode(str);
    }

    public static String encodeUtf8ByteRepresentation(String str) {
        return getOne().base64UrlEncodeUtf8ByteRepresentation(str);
    }

    public static String encode(String str, String str2) {
        return getOne().base64UrlEncode(str, str2);
    }

    public static String encode(byte[] bArr) {
        return getOne().base64UrlEncode(bArr);
    }
}
