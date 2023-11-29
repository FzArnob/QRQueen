package com.google.zxing.oned.rss;

public final class RSSUtils {
    private RSSUtils() {
    }

    static int[] getRSSwidths(int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int combins;
        int i6 = i3;
        int i7 = i4;
        int[] iArr = new int[i6];
        int i8 = i;
        int i9 = i2;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = i6 - 1;
            if (i10 < i12) {
                int i13 = 1 << i10;
                i11 |= i13;
                int i14 = 1;
                while (true) {
                    i5 = i9 - i14;
                    int i15 = i6 - i10;
                    int i16 = i15 - 2;
                    combins = combins(i5 - 1, i16);
                    if (z && i11 == 0) {
                        int i17 = i15 - 1;
                        if (i5 - i17 >= i17) {
                            combins -= combins(i5 - i15, i16);
                        }
                    }
                    if (i15 - 1 > 1) {
                        int i18 = i5 - i16;
                        int i19 = 0;
                        while (i18 > i7) {
                            i19 += combins((i5 - i18) - 1, i15 - 3);
                            i18--;
                            int i20 = i3;
                        }
                        combins -= i19 * (i12 - i10);
                    } else if (i5 > i7) {
                        combins--;
                    }
                    i8 -= combins;
                    if (i8 < 0) {
                        break;
                    }
                    i14++;
                    i11 &= ~i13;
                    i6 = i3;
                }
                i8 += combins;
                iArr[i10] = i14;
                i10++;
                i6 = i3;
                i9 = i5;
            } else {
                iArr[i10] = i9;
                return iArr;
            }
        }
    }

    public static int getRSSvalue(int[] iArr, int i, boolean z) {
        int[] iArr2 = iArr;
        int i2 = i;
        int length = iArr2.length;
        int i3 = 0;
        for (int i4 : iArr2) {
            i3 += i4;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = length - 1;
            if (i5 >= i8) {
                return i6;
            }
            int i9 = 1 << i5;
            i7 |= i9;
            int i10 = 1;
            while (i10 < iArr2[i5]) {
                int i11 = i3 - i10;
                int i12 = length - i5;
                int i13 = i12 - 2;
                int combins = combins(i11 - 1, i13);
                if (z && i7 == 0) {
                    int i14 = i12 - 1;
                    if (i11 - i14 >= i14) {
                        combins -= combins(i11 - i12, i13);
                    }
                }
                if (i12 - 1 > 1) {
                    int i15 = i11 - i13;
                    int i16 = 0;
                    while (i15 > i2) {
                        i16 += combins((i11 - i15) - 1, i12 - 3);
                        i15--;
                        int[] iArr3 = iArr;
                    }
                    combins -= i16 * (i8 - i5);
                } else if (i11 > i2) {
                    combins--;
                }
                i6 += combins;
                i10++;
                i7 &= ~i9;
                iArr2 = iArr;
            }
            i3 -= i10;
            i5++;
            iArr2 = iArr;
        }
    }

    private static int combins(int i, int i2) {
        int i3 = i - i2;
        if (i3 > i2) {
            int i4 = i3;
            i3 = i2;
            i2 = i4;
        }
        int i5 = 1;
        int i6 = 1;
        while (i > i2) {
            i5 *= i;
            if (i6 <= i3) {
                i5 /= i6;
                i6++;
            }
            i--;
        }
        while (i6 <= i3) {
            i5 /= i6;
            i6++;
        }
        return i5;
    }

    static int[] elements(int[] iArr, int i, int i2) {
        int[] iArr2 = new int[(iArr.length + 2)];
        int i3 = i2 << 1;
        iArr2[0] = 1;
        int i4 = 10;
        int i5 = 1;
        for (int i6 = 1; i6 < i3 - 2; i6 += 2) {
            int i7 = i6 - 1;
            int i8 = iArr[i7] - iArr2[i7];
            iArr2[i6] = i8;
            int i9 = iArr[i6] - i8;
            iArr2[i6 + 1] = i9;
            int i10 = iArr2[i6];
            i5 += i9 + i10;
            if (i10 < i4) {
                i4 = i10;
            }
        }
        int i11 = i - i5;
        iArr2[i3 - 1] = i11;
        if (i11 < i4) {
            i4 = i11;
        }
        if (i4 > 1) {
            for (int i12 = 0; i12 < i3; i12 += 2) {
                int i13 = i4 - 1;
                iArr2[i12] = iArr2[i12] + i13;
                int i14 = i12 + 1;
                iArr2[i14] = iArr2[i14] - i13;
            }
        }
        return iArr2;
    }
}
