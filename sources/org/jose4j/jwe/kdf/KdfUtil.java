package org.jose4j.jwe.kdf;

import org.jose4j.base64url.Base64Url;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.HashUtil;
import org.jose4j.lang.StringUtil;

public class KdfUtil {
    private Base64Url base64Url;
    private ConcatKeyDerivationFunction kdf;

    public KdfUtil() {
        this.base64Url = new Base64Url();
        this.kdf = new ConcatKeyDerivationFunction(HashUtil.SHA_256);
    }

    public KdfUtil(String str) {
        this.base64Url = new Base64Url();
        this.kdf = new ConcatKeyDerivationFunction(HashUtil.SHA_256, str);
    }

    public byte[] kdf(byte[] bArr, int i, String str, String str2, String str3) {
        return this.kdf.kdf(bArr, i, prependDatalen(StringUtil.getBytesUtf8(str)), getDatalenDataFormat(str2), getDatalenDataFormat(str3), ByteUtil.getBytes(i), ByteUtil.EMPTY_BYTES);
    }

    /* access modifiers changed from: package-private */
    public byte[] prependDatalen(byte[] bArr) {
        if (bArr == null) {
            bArr = ByteUtil.EMPTY_BYTES;
        }
        return ByteUtil.concat(ByteUtil.getBytes(bArr.length), bArr);
    }

    /* access modifiers changed from: package-private */
    public byte[] getDatalenDataFormat(String str) {
        return prependDatalen(this.base64Url.base64UrlDecode(str));
    }
}
