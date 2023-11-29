package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final int MAX_AVG_VARIANCE = 64;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 == 5) {
                    int i3 = 64;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        int patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < i3) {
                            i4 = i5;
                            i3 = patternMatchVariance;
                        }
                    }
                    if (i4 < 0 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        i += iArr[0] + iArr[1];
                        System.arraycopy(iArr, 2, iArr, 0, 4);
                        iArr[4] = 0;
                        iArr[5] = 0;
                        i2--;
                    } else {
                        return new int[]{i, nextSet, i4};
                    }
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        int i2 = 64;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i4 >= iArr2.length) {
                break;
            }
            int patternMatchVariance = patternMatchVariance(iArr, iArr2[i4], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < i2) {
                i3 = i4;
                i2 = patternMatchVariance;
            }
            i4++;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009d, code lost:
        r9 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b5, code lost:
        if (r12 != 106) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b7, code lost:
        r9 = r7;
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ba, code lost:
        r9 = r7;
        r7 = false;
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c1, code lost:
        r7 = false;
        r9 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c8, code lost:
        r24 = r9;
        r9 = r7;
        r7 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f2, code lost:
        if (r17 == false) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f4, code lost:
        if (r7 != 'e') goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f6, code lost:
        r7 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f9, code lost:
        r7 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00fb, code lost:
        r17 = r9;
        r13 = r11;
        r11 = r22;
        r9 = 0;
        r14 = 6;
        r24 = r18;
        r18 = r12;
        r12 = r24;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r26, com.google.zxing.common.BitArray r27, java.util.Map<com.google.zxing.DecodeHintType, ?> r28) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r25 = this;
            r0 = r27
            int[] r1 = findStartPattern(r27)
            r2 = 2
            r3 = r1[r2]
            r4 = 99
            r5 = 100
            r6 = 101(0x65, float:1.42E-43)
            switch(r3) {
                case 103: goto L_0x001d;
                case 104: goto L_0x001a;
                case 105: goto L_0x0017;
                default: goto L_0x0012;
            }
        L_0x0012:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0017:
            r7 = 99
            goto L_0x001f
        L_0x001a:
            r7 = 100
            goto L_0x001f
        L_0x001d:
            r7 = 101(0x65, float:1.42E-43)
        L_0x001f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r9 = 20
            r8.<init>(r9)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>(r9)
            r9 = 0
            r11 = r1[r9]
            r12 = 1
            r13 = r1[r12]
            r14 = 6
            int[] r15 = new int[r14]
            r12 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 1
            r24 = r13
            r13 = r11
            r11 = r24
        L_0x0044:
            if (r16 != 0) goto L_0x010a
            int r12 = decodeCode(r0, r15, r11)
            byte r13 = (byte) r12
            java.lang.Byte r13 = java.lang.Byte.valueOf(r13)
            r10.add(r13)
            r13 = 106(0x6a, float:1.49E-43)
            if (r12 == r13) goto L_0x0058
            r20 = 1
        L_0x0058:
            if (r12 == r13) goto L_0x0060
            int r19 = r19 + 1
            int r21 = r19 * r12
            int r3 = r3 + r21
        L_0x0060:
            r22 = r11
        L_0x0062:
            if (r9 >= r14) goto L_0x006b
            r23 = r15[r9]
            int r22 = r22 + r23
            int r9 = r9 + 1
            goto L_0x0062
        L_0x006b:
            switch(r12) {
                case 103: goto L_0x0075;
                case 104: goto L_0x0075;
                case 105: goto L_0x0075;
                default: goto L_0x006e;
            }
        L_0x006e:
            r9 = 96
            switch(r7) {
                case 99: goto L_0x00ce;
                case 100: goto L_0x00a0;
                case 101: goto L_0x007a;
                default: goto L_0x0073;
            }
        L_0x0073:
            goto L_0x00f1
        L_0x0075:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x007a:
            r14 = 64
            if (r12 >= r14) goto L_0x0086
            int r9 = r12 + 32
            char r9 = (char) r9
            r8.append(r9)
            goto L_0x00f1
        L_0x0086:
            if (r12 >= r9) goto L_0x0090
            int r9 = r12 + -64
            char r9 = (char) r9
            r8.append(r9)
            goto L_0x00f1
        L_0x0090:
            if (r12 == r13) goto L_0x0094
            r20 = 0
        L_0x0094:
            if (r12 == r13) goto L_0x00ba
            switch(r12) {
                case 98: goto L_0x009c;
                case 99: goto L_0x00c1;
                case 100: goto L_0x009a;
                default: goto L_0x0099;
            }
        L_0x0099:
            goto L_0x00b7
        L_0x009a:
            r7 = 0
            goto L_0x009d
        L_0x009c:
            r7 = 1
        L_0x009d:
            r9 = 100
            goto L_0x00c8
        L_0x00a0:
            if (r12 >= r9) goto L_0x00a9
            int r9 = r12 + 32
            char r9 = (char) r9
            r8.append(r9)
            goto L_0x00f1
        L_0x00a9:
            if (r12 == r13) goto L_0x00ad
            r20 = 0
        L_0x00ad:
            r9 = 98
            if (r12 == r9) goto L_0x00c5
            if (r12 == r4) goto L_0x00c1
            if (r12 == r6) goto L_0x00bf
            if (r12 == r13) goto L_0x00ba
        L_0x00b7:
            r9 = r7
            r7 = 0
            goto L_0x00c8
        L_0x00ba:
            r9 = r7
            r7 = 0
            r16 = 1
            goto L_0x00c8
        L_0x00bf:
            r7 = 0
            goto L_0x00c6
        L_0x00c1:
            r7 = 0
            r9 = 99
            goto L_0x00c8
        L_0x00c5:
            r7 = 1
        L_0x00c6:
            r9 = 101(0x65, float:1.42E-43)
        L_0x00c8:
            r24 = r9
            r9 = r7
            r7 = r24
            goto L_0x00f2
        L_0x00ce:
            if (r12 >= r5) goto L_0x00dd
            r9 = 10
            if (r12 >= r9) goto L_0x00d9
            r9 = 48
            r8.append(r9)
        L_0x00d9:
            r8.append(r12)
            goto L_0x00f1
        L_0x00dd:
            if (r12 == r13) goto L_0x00e1
            r20 = 0
        L_0x00e1:
            if (r12 == r5) goto L_0x00ef
            if (r12 == r6) goto L_0x00ec
            if (r12 == r13) goto L_0x00e8
            goto L_0x00f1
        L_0x00e8:
            r9 = 0
            r16 = 1
            goto L_0x00f2
        L_0x00ec:
            r7 = 101(0x65, float:1.42E-43)
            goto L_0x00f1
        L_0x00ef:
            r7 = 100
        L_0x00f1:
            r9 = 0
        L_0x00f2:
            if (r17 == 0) goto L_0x00fb
            if (r7 != r6) goto L_0x00f9
            r7 = 100
            goto L_0x00fb
        L_0x00f9:
            r7 = 101(0x65, float:1.42E-43)
        L_0x00fb:
            r17 = r9
            r13 = r11
            r11 = r22
            r9 = 0
            r14 = 6
            r24 = r18
            r18 = r12
            r12 = r24
            goto L_0x0044
        L_0x010a:
            int r5 = r0.getNextUnset(r11)
            int r6 = r27.getSize()
            int r9 = r5 - r13
            int r9 = r9 / r2
            int r9 = r9 + r5
            int r6 = java.lang.Math.min(r6, r9)
            r9 = 0
            boolean r0 = r0.isRange(r5, r6, r9)
            if (r0 == 0) goto L_0x0191
            int r19 = r19 * r12
            int r3 = r3 - r19
            int r3 = r3 % 103
            if (r3 != r12) goto L_0x018c
            int r0 = r8.length()
            if (r0 == 0) goto L_0x0187
            if (r0 <= 0) goto L_0x0140
            if (r20 == 0) goto L_0x0140
            if (r7 != r4) goto L_0x013b
            int r3 = r0 + -2
            r8.delete(r3, r0)
            goto L_0x0140
        L_0x013b:
            int r3 = r0 + -1
            r8.delete(r3, r0)
        L_0x0140:
            r0 = 1
            r3 = r1[r0]
            r0 = 0
            r1 = r1[r0]
            int r3 = r3 + r1
            float r0 = (float) r3
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            int r5 = r5 + r13
            float r3 = (float) r5
            float r3 = r3 / r1
            int r1 = r10.size()
            byte[] r4 = new byte[r1]
            r9 = 0
        L_0x0155:
            if (r9 >= r1) goto L_0x0166
            java.lang.Object r5 = r10.get(r9)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r4[r9] = r5
            int r9 = r9 + 1
            goto L_0x0155
        L_0x0166:
            com.google.zxing.Result r1 = new com.google.zxing.Result
            java.lang.String r5 = r8.toString()
            com.google.zxing.ResultPoint[] r2 = new com.google.zxing.ResultPoint[r2]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r26
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r2[r0] = r6
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r3, r7)
            r3 = 1
            r2[r3] = r0
            com.google.zxing.BarcodeFormat r0 = com.google.zxing.BarcodeFormat.CODE_128
            r1.<init>(r5, r4, r2, r0)
            return r1
        L_0x0187:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x018c:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x0191:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
