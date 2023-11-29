package org.jose4j.lang;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import org.jose4j.base64url.Base64Url;

public class ByteUtil {
    public static final byte[] EMPTY_BYTES = new byte[0];
    private static final int MAX_BYTE_LENGTH = 268435455;

    public static int getInt(byte b) {
        return b >= 0 ? b : 256 - (~(b - 1));
    }

    public static byte[] convertUnsignedToSignedTwosComp(int[] iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = getByte(iArr[i]);
        }
        return bArr;
    }

    public static int[] convertSignedTwosCompToUnsigned(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            iArr[i] = getInt(bArr[i]);
        }
        return iArr;
    }

    public static byte getByte(int i) {
        byte[] bytes = getBytes(i);
        if (bytes[0] == 0 && bytes[1] == 0 && bytes[2] == 0) {
            return bytes[3];
        }
        throw new IllegalArgumentException("Integer value (" + i + ") too large to stuff into one byte.");
    }

    public static byte[] getBytes(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        return allocate.array();
    }

    public static byte[] getBytes(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(j);
        return allocate.array();
    }

    public static boolean secureEquals(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = EMPTY_BYTES;
        }
        if (bArr2 == null) {
            bArr2 = EMPTY_BYTES;
        }
        int min = Math.min(bArr.length, bArr2.length);
        int max = Math.max(bArr.length, bArr2.length);
        byte b = 0;
        for (int i = 0; i < min; i++) {
            b |= bArr[i] ^ bArr2[i];
        }
        if (b == 0 && min == max) {
            return true;
        }
        return false;
    }

    public static byte[] concat(byte[]... bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (byte[] write : bArr) {
                byteArrayOutputStream.write(write);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("IOEx from ByteArrayOutputStream?!", e);
        }
    }

    public static byte[] subArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static byte[] leftHalf(byte[] bArr) {
        return subArray(bArr, 0, bArr.length / 2);
    }

    public static byte[] rightHalf(byte[] bArr) {
        int length = bArr.length / 2;
        return subArray(bArr, length, length);
    }

    public static int bitLength(byte[] bArr) {
        return bitLength(bArr.length);
    }

    public static int bitLength(int i) {
        if (i <= MAX_BYTE_LENGTH && i >= 0) {
            return i * 8;
        }
        throw new UncheckedJoseException("Invalid byte length (" + i + ") for converting to bit length");
    }

    public static int byteLength(int i) {
        return i / 8;
    }

    public static byte[] randomBytes(int i, SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        byte[] bArr = new byte[i];
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    public static byte[] randomBytes(int i) {
        return randomBytes(i, (SecureRandom) null);
    }

    public static String toDebugString(byte[] bArr) {
        String base64UrlEncode = new Base64Url().base64UrlEncode(bArr);
        int[] convertSignedTwosCompToUnsigned = convertSignedTwosCompToUnsigned(bArr);
        return Arrays.toString(convertSignedTwosCompToUnsigned) + "(" + convertSignedTwosCompToUnsigned.length + "bytes/" + bitLength(convertSignedTwosCompToUnsigned.length) + "bits) | base64url encoded: " + base64UrlEncode;
    }
}
