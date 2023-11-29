package com.google.zxing.qrcode.encoder;

final class MaskUtil {
    private static final int N1 = 3;
    private static final int N2 = 3;
    private static final int N3 = 40;
    private static final int N4 = 10;

    private MaskUtil() {
    }

    static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        for (int i2 = 0; i2 < height - 1; i2++) {
            int i3 = 0;
            while (i3 < width - 1) {
                byte[] bArr = array[i2];
                byte b = bArr[i3];
                int i4 = i3 + 1;
                if (b == bArr[i4]) {
                    byte[] bArr2 = array[i2 + 1];
                    if (b == bArr2[i3] && b == bArr2[i4]) {
                        i++;
                    }
                }
                i3 = i4;
            }
        }
        return i * 3;
    }

    static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        int i;
        int i2;
        int i3;
        int i4;
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i5 = 0;
        for (int i6 = 0; i6 < height; i6++) {
            for (int i7 = 0; i7 < width; i7++) {
                int i8 = i7 + 6;
                if (i8 < width) {
                    byte[] bArr = array[i6];
                    if (bArr[i7] == 1 && bArr[i7 + 1] == 0 && bArr[i7 + 2] == 1 && bArr[i7 + 3] == 1 && bArr[i7 + 4] == 1 && bArr[i7 + 5] == 0 && bArr[i8] == 1 && (((i3 = i7 + 10) < width && bArr[i7 + 7] == 0 && bArr[i7 + 8] == 0 && bArr[i7 + 9] == 0 && bArr[i3] == 0) || (i7 - 4 >= 0 && bArr[i7 - 1] == 0 && bArr[i7 - 2] == 0 && bArr[i7 - 3] == 0 && bArr[i4] == 0))) {
                        i5 += 40;
                    }
                }
                int i9 = i6 + 6;
                if (i9 < height && array[i6][i7] == 1 && array[i6 + 1][i7] == 0 && array[i6 + 2][i7] == 1 && array[i6 + 3][i7] == 1 && array[i6 + 4][i7] == 1 && array[i6 + 5][i7] == 0 && array[i9][i7] == 1 && (((i = i6 + 10) < height && array[i6 + 7][i7] == 0 && array[i6 + 8][i7] == 0 && array[i6 + 9][i7] == 0 && array[i][i7] == 0) || (i6 - 4 >= 0 && array[i6 - 1][i7] == 0 && array[i6 - 2][i7] == 0 && array[i6 - 3][i7] == 0 && array[i2][i7] == 0))) {
                    i5 += 40;
                }
            }
        }
        return i5;
    }

    static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            byte[] bArr = array[i2];
            for (int i3 = 0; i3 < width; i3++) {
                if (bArr[i3] == 1) {
                    i++;
                }
            }
        }
        return ((int) (Math.abs((((double) i) / ((double) (byteMatrix.getHeight() * byteMatrix.getWidth()))) - 0.5d) * 20.0d)) * 10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r1 = r3 & 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        if (r1 != 0) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0037, code lost:
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        r1 = r1 & 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean getDataMaskBit(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L_0x0041;
                case 1: goto L_0x0042;
                case 2: goto L_0x003e;
                case 3: goto L_0x003a;
                case 4: goto L_0x0033;
                case 5: goto L_0x002b;
                case 6: goto L_0x0023;
                case 7: goto L_0x001b;
                default: goto L_0x0004;
            }
        L_0x0004:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Invalid mask pattern: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x001b:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            goto L_0x0037
        L_0x0023:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L_0x0038
        L_0x002b:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L_0x0044
        L_0x0033:
            int r1 = r3 >>> 1
            int r2 = r2 / 3
        L_0x0037:
            int r1 = r1 + r2
        L_0x0038:
            r1 = r1 & r0
            goto L_0x0044
        L_0x003a:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L_0x0044
        L_0x003e:
            int r1 = r2 % 3
            goto L_0x0044
        L_0x0041:
            int r3 = r3 + r2
        L_0x0042:
            r1 = r3 & 1
        L_0x0044:
            if (r1 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r0 = 0
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MaskUtil.getDataMaskBit(int, int, int):boolean");
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            byte b = -1;
            int i3 = 0;
            for (int i4 = 0; i4 < width; i4++) {
                byte b2 = z ? array[i2][i4] : array[i4][i2];
                if (b2 == b) {
                    i3++;
                } else {
                    if (i3 >= 5) {
                        i += (i3 - 5) + 3;
                    }
                    b = b2;
                    i3 = 1;
                }
            }
            if (i3 > 5) {
                i += (i3 - 5) + 3;
            }
        }
        return i;
    }
}
