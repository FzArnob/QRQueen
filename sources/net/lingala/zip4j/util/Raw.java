package net.lingala.zip4j.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.DataInput;
import java.io.IOException;
import net.lingala.zip4j.exception.ZipException;

public class Raw {
    public static byte[] toByteArray(int i) {
        return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    public static long readLongLittleEndian(byte[] bArr, int i) {
        return ((long) (bArr[i] & Ev3Constants.Opcode.TST)) | ((((((((((((((((long) (bArr[i + 7] & Ev3Constants.Opcode.TST)) | 0) << 8) | ((long) (bArr[i + 6] & Ev3Constants.Opcode.TST))) << 8) | ((long) (bArr[i + 5] & Ev3Constants.Opcode.TST))) << 8) | ((long) (bArr[i + 4] & Ev3Constants.Opcode.TST))) << 8) | ((long) (bArr[i + 3] & Ev3Constants.Opcode.TST))) << 8) | ((long) (bArr[i + 2] & Ev3Constants.Opcode.TST))) << 8) | ((long) (bArr[i + 1] & Ev3Constants.Opcode.TST))) << 8);
    }

    public static int readLeInt(DataInput dataInput, byte[] bArr) throws ZipException {
        try {
            dataInput.readFully(bArr, 0, 4);
            return (bArr[0] & Ev3Constants.Opcode.TST) | ((bArr[1] & Ev3Constants.Opcode.TST) << 8) | ((((bArr[3] & Ev3Constants.Opcode.TST) << 8) | (bArr[2] & Ev3Constants.Opcode.TST)) << 16);
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    public static int readShortLittleEndian(byte[] bArr, int i) {
        return ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 8) | (bArr[i] & Ev3Constants.Opcode.TST);
    }

    public static final short readShortBigEndian(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] & Ev3Constants.Opcode.TST) | ((short) (((short) ((bArr[i] & Ev3Constants.Opcode.TST) | 0)) << 8)));
    }

    public static int readIntLittleEndian(byte[] bArr, int i) {
        return ((((bArr[i + 3] & Ev3Constants.Opcode.TST) << 8) | (bArr[i + 2] & Ev3Constants.Opcode.TST)) << 16) | (bArr[i] & Ev3Constants.Opcode.TST) | ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 8);
    }

    public static byte[] toByteArray(int i, int i2) {
        byte[] bArr = new byte[i2];
        byte[] byteArray = toByteArray(i);
        int i3 = 0;
        while (i3 < byteArray.length && i3 < i2) {
            bArr[i3] = byteArray[i3];
            i3++;
        }
        return bArr;
    }

    public static final void writeShortLittleEndian(byte[] bArr, int i, short s) {
        bArr[i + 1] = (byte) (s >>> 8);
        bArr[i] = (byte) (s & 255);
    }

    public static final void writeIntLittleEndian(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) (i2 >>> 24);
        bArr[i + 2] = (byte) (i2 >>> 16);
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }

    public static void writeLongLittleEndian(byte[] bArr, int i, long j) {
        bArr[i + 7] = (byte) ((int) (j >>> 56));
        bArr[i + 6] = (byte) ((int) (j >>> 48));
        bArr[i + 5] = (byte) ((int) (j >>> 40));
        bArr[i + 4] = (byte) ((int) (j >>> 32));
        bArr[i + 3] = (byte) ((int) (j >>> 24));
        bArr[i + 2] = (byte) ((int) (j >>> 16));
        bArr[i + 1] = (byte) ((int) (j >>> 8));
        bArr[i] = (byte) ((int) (j & 255));
    }

    public static byte bitArrayToByte(int[] iArr) throws ZipException {
        if (iArr == null) {
            throw new ZipException("bit array is null, cannot calculate byte from bits");
        } else if (iArr.length != 8) {
            throw new ZipException("invalid bit array length, cannot calculate byte");
        } else if (checkBits(iArr)) {
            int i = 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                i = (int) (((double) i) + (Math.pow(2.0d, (double) i2) * ((double) iArr[i2])));
            }
            return (byte) i;
        } else {
            throw new ZipException("invalid bits provided, bits contain other values than 0 or 1");
        }
    }

    private static boolean checkBits(int[] iArr) {
        for (int i : iArr) {
            if (i != 0 && i != 1) {
                return false;
            }
        }
        return true;
    }

    public static void prepareBuffAESIVBytes(byte[] bArr, int i, int i2) {
        bArr[0] = (byte) i;
        bArr[1] = (byte) (i >> 8);
        bArr[2] = (byte) (i >> 16);
        bArr[3] = (byte) (i >> 24);
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        bArr[11] = 0;
        bArr[12] = 0;
        bArr[13] = 0;
        bArr[14] = 0;
        bArr[15] = 0;
    }

    public static byte[] convertCharArrayToByteArray(char[] cArr) {
        cArr.getClass();
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }
}
