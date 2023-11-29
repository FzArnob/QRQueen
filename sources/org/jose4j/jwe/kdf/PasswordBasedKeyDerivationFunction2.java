package org.jose4j.jwe.kdf;

import java.io.ByteArrayOutputStream;
import javax.crypto.Mac;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;
import org.jose4j.mac.MacUtil;

public class PasswordBasedKeyDerivationFunction2 {
    private String hmacAlgorithm;

    public PasswordBasedKeyDerivationFunction2(String str) {
        this.hmacAlgorithm = str;
    }

    public byte[] derive(byte[] bArr, byte[] bArr2, int i, int i2) throws JoseException {
        return derive(bArr, bArr2, i, i2, (String) null);
    }

    public byte[] derive(byte[] bArr, byte[] bArr2, int i, int i2, String str) throws JoseException {
        Mac initializedMac = MacUtil.getInitializedMac(this.hmacAlgorithm, new HmacKey(bArr), str);
        int macLength = initializedMac.getMacLength();
        if (((long) i2) <= InternalZipConstants.ZIP_64_LIMIT) {
            int ceil = (int) Math.ceil(((double) i2) / ((double) macLength));
            int i3 = ceil - 1;
            int i4 = i2 - (macLength * i3);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i5 = 0;
            while (i5 < ceil) {
                int i6 = i5 + 1;
                byte[] f = f(bArr2, i, i6, initializedMac);
                if (i5 == i3) {
                    f = ByteUtil.subArray(f, 0, i4);
                }
                byteArrayOutputStream.write(f, 0, f.length);
                i5 = i6;
            }
            return byteArrayOutputStream.toByteArray();
        }
        throw new UncheckedJoseException("derived key too long " + i2);
    }

    /* access modifiers changed from: package-private */
    public byte[] f(byte[] bArr, int i, int i2, Mac mac) {
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        for (int i3 = 1; i3 <= i; i3++) {
            if (i3 == 1) {
                bArr2 = mac.doFinal(ByteUtil.concat(bArr, ByteUtil.getBytes(i2)));
                bArr3 = bArr2;
            } else {
                bArr3 = mac.doFinal(bArr3);
                for (int i4 = 0; i4 < bArr3.length; i4++) {
                    bArr2[i4] = (byte) (bArr3[i4] ^ bArr2[i4]);
                }
            }
        }
        return bArr2;
    }
}
