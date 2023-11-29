package org.jose4j.jwe.kdf;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcatKeyDerivationFunction {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ConcatKeyDerivationFunction.class);
    private int digestLength;
    private MessageDigest messageDigest;

    private boolean traceLog() {
        return false;
    }

    public ConcatKeyDerivationFunction(String str) {
        this.messageDigest = HashUtil.getMessageDigest(str);
        init();
    }

    public ConcatKeyDerivationFunction(String str, String str2) {
        this.messageDigest = HashUtil.getMessageDigest(str, str2);
        init();
    }

    private void init() {
        this.digestLength = ByteUtil.bitLength(this.messageDigest.getDigestLength());
        if (traceLog()) {
            log.trace("Hash Algorithm: {} with hashlen: {} bits", (Object) this.messageDigest.getAlgorithm(), (Object) Integer.valueOf(this.digestLength));
        }
    }

    public byte[] kdf(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        if (traceLog()) {
            log.trace("KDF:" + "\n" + "  z: " + ByteUtil.toDebugString(bArr) + "\n" + "  keydatalen: " + i + "  algorithmId: " + ByteUtil.toDebugString(bArr2) + "\n" + "  partyUInfo: " + ByteUtil.toDebugString(bArr3) + "\n" + "  partyVInfo: " + ByteUtil.toDebugString(bArr4) + "\n" + "  suppPubInfo: " + ByteUtil.toDebugString(bArr5) + "\n" + "  suppPrivInfo: " + ByteUtil.toDebugString(bArr6));
        }
        return kdf(bArr, i, ByteUtil.concat(bArr2, bArr3, bArr4, bArr5, bArr6));
    }

    public byte[] kdf(byte[] bArr, int i, byte[] bArr2) {
        long reps = getReps(i);
        if (traceLog()) {
            Logger logger = log;
            logger.trace("reps: {}", (Object) String.valueOf(reps));
            logger.trace("otherInfo: {}", (Object) ByteUtil.toDebugString(bArr2));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 1; ((long) i2) <= reps; i2++) {
            byte[] bytes = ByteUtil.getBytes(i2);
            if (traceLog()) {
                Logger logger2 = log;
                logger2.trace("rep {} hashing ", (Object) Integer.valueOf(i2));
                logger2.trace(" counter: {}", (Object) ByteUtil.toDebugString(bytes));
                logger2.trace(" z: {}", (Object) ByteUtil.toDebugString(bArr));
                logger2.trace(" otherInfo: {}", (Object) ByteUtil.toDebugString(bArr2));
            }
            this.messageDigest.update(bytes);
            this.messageDigest.update(bArr);
            this.messageDigest.update(bArr2);
            byte[] digest = this.messageDigest.digest();
            if (traceLog()) {
                log.trace(" k({}): {}", (Object) Integer.valueOf(i2), (Object) ByteUtil.toDebugString(digest));
            }
            byteArrayOutputStream.write(digest, 0, digest.length);
        }
        int byteLength = ByteUtil.byteLength(i);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (traceLog()) {
            log.trace("derived key material: {}", (Object) ByteUtil.toDebugString(byteArray));
        }
        if (byteArray.length != byteLength) {
            byteArray = ByteUtil.subArray(byteArray, 0, byteLength);
            if (traceLog()) {
                log.trace("first {} bits of derived key material: {}", (Object) Integer.valueOf(i), (Object) ByteUtil.toDebugString(byteArray));
            }
        }
        if (traceLog()) {
            log.trace("final derived key material: {}", (Object) ByteUtil.toDebugString(byteArray));
        }
        return byteArray;
    }

    /* access modifiers changed from: package-private */
    public long getReps(int i) {
        return (long) ((int) Math.ceil((double) (((float) i) / ((float) this.digestLength))));
    }
}
