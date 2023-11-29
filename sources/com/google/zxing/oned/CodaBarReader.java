package com.google.zxing.oned;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import gnu.bytecode.Access;

public final class CodaBarReader extends OneDReader {
    static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
    static final int[] CHARACTER_ENCODINGS = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final int MAX_ACCEPTABLE = 512;
    private static final int MIN_CHARACTER_LENGTH = 3;
    private static final int PADDING = 384;
    private static final char[] STARTEND_ENCODING = {'A', 'B', Access.CLASS_CONTEXT, 'D'};
    private int counterLength = 0;
    private int[] counters = new int[80];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r10, com.google.zxing.common.BitArray r11, java.util.Map<com.google.zxing.DecodeHintType, ?> r12) throws com.google.zxing.NotFoundException {
        /*
            r9 = this;
            r9.setCounters(r11)
            int r11 = r9.findStartPattern()
            java.lang.StringBuilder r12 = r9.decodeRowResult
            r0 = 0
            r12.setLength(r0)
            r12 = r11
        L_0x000e:
            int r1 = r9.toNarrowWidePattern(r12)
            r2 = -1
            if (r1 == r2) goto L_0x00f3
            java.lang.StringBuilder r3 = r9.decodeRowResult
            char r4 = (char) r1
            r3.append(r4)
            int r12 = r12 + 8
            java.lang.StringBuilder r3 = r9.decodeRowResult
            int r3 = r3.length()
            r4 = 1
            if (r3 <= r4) goto L_0x0033
            char[] r3 = STARTEND_ENCODING
            char[] r5 = ALPHABET
            char r1 = r5[r1]
            boolean r1 = arrayContains(r3, r1)
            if (r1 == 0) goto L_0x0033
            goto L_0x0037
        L_0x0033:
            int r1 = r9.counterLength
            if (r12 < r1) goto L_0x000e
        L_0x0037:
            int[] r1 = r9.counters
            int r3 = r12 + -1
            r1 = r1[r3]
            r5 = -8
            r6 = 0
        L_0x003f:
            if (r5 >= r2) goto L_0x004b
            int[] r7 = r9.counters
            int r8 = r12 + r5
            r7 = r7[r8]
            int r6 = r6 + r7
            int r5 = r5 + 1
            goto L_0x003f
        L_0x004b:
            int r2 = r9.counterLength
            r5 = 2
            if (r12 >= r2) goto L_0x0059
            int r6 = r6 / r5
            if (r1 < r6) goto L_0x0054
            goto L_0x0059
        L_0x0054:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0059:
            r9.validatePattern(r11)
            r12 = 0
        L_0x005d:
            java.lang.StringBuilder r1 = r9.decodeRowResult
            int r1 = r1.length()
            if (r12 >= r1) goto L_0x0075
            java.lang.StringBuilder r1 = r9.decodeRowResult
            char[] r2 = ALPHABET
            char r6 = r1.charAt(r12)
            char r2 = r2[r6]
            r1.setCharAt(r12, r2)
            int r12 = r12 + 1
            goto L_0x005d
        L_0x0075:
            java.lang.StringBuilder r12 = r9.decodeRowResult
            char r12 = r12.charAt(r0)
            char[] r1 = STARTEND_ENCODING
            boolean r12 = arrayContains(r1, r12)
            if (r12 == 0) goto L_0x00ee
            java.lang.StringBuilder r12 = r9.decodeRowResult
            int r2 = r12.length()
            int r2 = r2 - r4
            char r12 = r12.charAt(r2)
            boolean r12 = arrayContains(r1, r12)
            if (r12 == 0) goto L_0x00e9
            java.lang.StringBuilder r12 = r9.decodeRowResult
            int r12 = r12.length()
            r1 = 3
            if (r12 <= r1) goto L_0x00e4
            java.lang.StringBuilder r12 = r9.decodeRowResult
            int r1 = r12.length()
            int r1 = r1 - r4
            r12.deleteCharAt(r1)
            java.lang.StringBuilder r12 = r9.decodeRowResult
            r12.deleteCharAt(r0)
            r12 = 0
            r1 = 0
        L_0x00ae:
            if (r12 >= r11) goto L_0x00b8
            int[] r2 = r9.counters
            r2 = r2[r12]
            int r1 = r1 + r2
            int r12 = r12 + 1
            goto L_0x00ae
        L_0x00b8:
            float r12 = (float) r1
        L_0x00b9:
            if (r11 >= r3) goto L_0x00c3
            int[] r2 = r9.counters
            r2 = r2[r11]
            int r1 = r1 + r2
            int r11 = r11 + 1
            goto L_0x00b9
        L_0x00c3:
            float r11 = (float) r1
            com.google.zxing.Result r1 = new com.google.zxing.Result
            java.lang.StringBuilder r2 = r9.decodeRowResult
            java.lang.String r2 = r2.toString()
            r3 = 0
            com.google.zxing.ResultPoint[] r5 = new com.google.zxing.ResultPoint[r5]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            float r10 = (float) r10
            r6.<init>(r12, r10)
            r5[r0] = r6
            com.google.zxing.ResultPoint r12 = new com.google.zxing.ResultPoint
            r12.<init>(r11, r10)
            r5[r4] = r12
            com.google.zxing.BarcodeFormat r10 = com.google.zxing.BarcodeFormat.CODABAR
            r1.<init>(r2, r3, r5, r10)
            return r1
        L_0x00e4:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00e9:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00ee:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00f3:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    /* access modifiers changed from: package-private */
    public void validatePattern(int i) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.decodeRowResult.length() - 1;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) * 2);
                iArr[i7] = iArr[i7] + this.counters[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        for (int i8 = 0; i8 < 2; i8++) {
            iArr4[i8] = 0;
            int i9 = i8 + 2;
            int i10 = (((iArr[i8] << 8) / iArr2[i8]) + ((iArr[i9] << 8) / iArr2[i9])) >> 1;
            iArr4[i9] = i10;
            iArr3[i8] = i10;
            iArr3[i9] = ((iArr[i9] * 512) + PADDING) / iArr2[i9];
        }
        loop3:
        while (true) {
            int i11 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i2)];
            int i12 = 6;
            while (i12 >= 0) {
                int i13 = (i12 & 1) + ((i11 & 1) * 2);
                int i14 = this.counters[i + i12] << 8;
                if (i14 >= iArr4[i13] && i14 <= iArr3[i13]) {
                    i11 >>= 1;
                    i12--;
                }
            }
            if (i2 < length) {
                i += 8;
                i2++;
            } else {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void setCounters(BitArray bitArray) throws NotFoundException {
        int i = 0;
        this.counterLength = 0;
        int nextUnset = bitArray.getNextUnset(0);
        int size = bitArray.getSize();
        if (nextUnset < size) {
            boolean z = true;
            while (nextUnset < size) {
                if (bitArray.get(nextUnset) ^ z) {
                    i++;
                } else {
                    counterAppend(i);
                    z = !z;
                    i = 1;
                }
                nextUnset++;
            }
            counterAppend(i);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void counterAppend(int i) {
        int[] iArr = this.counters;
        int i2 = this.counterLength;
        iArr[i2] = i;
        int i3 = i2 + 1;
        this.counterLength = i3;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[(i3 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.counters = iArr2;
        }
    }

    private int findStartPattern() throws NotFoundException {
        for (int i = 1; i < this.counterLength; i += 2) {
            int narrowWidePattern = toNarrowWidePattern(i);
            if (narrowWidePattern != -1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.counters[i3];
                }
                if (i == 1 || this.counters[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static boolean arrayContains(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    private int toNarrowWidePattern(int i) {
        int i2 = i + 7;
        if (i2 >= this.counterLength) {
            return -1;
        }
        int[] iArr = {0, 0};
        int[] iArr2 = {ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        int[] iArr3 = {0, 0};
        int i3 = 0;
        for (int i4 = 0; i4 < 2; i4++) {
            for (int i5 = i + i4; i5 < i2; i5 += 2) {
                int[] iArr4 = this.counters;
                int i6 = iArr4[i5];
                if (i6 < iArr2[i4]) {
                    iArr2[i4] = i6;
                }
                int i7 = iArr4[i5];
                if (i7 > iArr[i4]) {
                    iArr[i4] = i7;
                }
            }
            iArr3[i4] = (iArr2[i4] + iArr[i4]) / 2;
        }
        int i8 = 128;
        int i9 = 0;
        for (int i10 = 0; i10 < 7; i10++) {
            i8 >>= 1;
            if (this.counters[i + i10] > iArr3[i10 & 1]) {
                i9 |= i8;
            }
        }
        while (true) {
            int[] iArr5 = CHARACTER_ENCODINGS;
            if (i3 >= iArr5.length) {
                return -1;
            }
            if (iArr5[i3] == i9) {
                return i3;
            }
            i3++;
        }
    }
}
