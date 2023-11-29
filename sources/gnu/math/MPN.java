package gnu.math;

import net.lingala.zip4j.util.InternalZipConstants;

class MPN {
    public static int chars_per_word(int i) {
        if (i < 10) {
            if (i >= 8) {
                return 10;
            }
            if (i <= 2) {
                return 32;
            }
            if (i == 3) {
                return 20;
            }
            if (i == 4) {
                return 16;
            }
            return 18 - i;
        } else if (i < 12) {
            return 9;
        } else {
            if (i <= 16) {
                return 8;
            }
            if (i <= 23) {
                return 7;
            }
            if (i <= 40) {
                return 6;
            }
            return i <= 256 ? 4 : 1;
        }
    }

    public static int count_leading_zeros(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 0;
        for (int i3 = 16; i3 > 0; i3 >>= 1) {
            int i4 = i >>> i3;
            if (i4 == 0) {
                i2 += i3;
            } else {
                i = i4;
            }
        }
        return i2;
    }

    static int findLowestBit(int i) {
        int i2 = 0;
        while ((i & 15) == 0) {
            i >>= 4;
            i2 += 4;
        }
        if ((i & 3) == 0) {
            i >>= 2;
            i2 += 2;
        }
        return (i & 1) == 0 ? i2 + 1 : i2;
    }

    MPN() {
    }

    public static int add_1(int[] iArr, int[] iArr2, int i, int i2) {
        long j = ((long) i2) & InternalZipConstants.ZIP_64_LIMIT;
        for (int i3 = 0; i3 < i; i3++) {
            long j2 = j + (((long) iArr2[i3]) & InternalZipConstants.ZIP_64_LIMIT);
            iArr[i3] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int add_n(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + (((long) iArr2[i2]) & InternalZipConstants.ZIP_64_LIMIT) + (InternalZipConstants.ZIP_64_LIMIT & ((long) iArr3[i2]));
            iArr[i2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int sub_n(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr3[i3];
            int i5 = iArr2[i3];
            int i6 = i4 + i2;
            int i7 = 1;
            int i8 = (i6 ^ Integer.MIN_VALUE) < (i2 ^ Integer.MIN_VALUE) ? 1 : 0;
            int i9 = i5 - i6;
            if ((i9 ^ Integer.MIN_VALUE) <= (i5 ^ Integer.MIN_VALUE)) {
                i7 = 0;
            }
            i2 = i8 + i7;
            iArr[i3] = i9;
        }
        return i2;
    }

    public static int mul_1(int[] iArr, int[] iArr2, int i, int i2) {
        long j = ((long) i2) & InternalZipConstants.ZIP_64_LIMIT;
        long j2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            long j3 = j2 + ((((long) iArr2[i3]) & InternalZipConstants.ZIP_64_LIMIT) * j);
            iArr[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static void mul(int[] iArr, int[] iArr2, int i, int[] iArr3, int i2) {
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int i3 = i;
        iArr4[i3] = mul_1(iArr4, iArr5, i3, iArr3[0]);
        int i4 = 1;
        int i5 = i2;
        while (i4 < i5) {
            long j = ((long) iArr3[i4]) & InternalZipConstants.ZIP_64_LIMIT;
            long j2 = 0;
            int i6 = 0;
            while (i6 < i3) {
                int i7 = i4 + i6;
                long j3 = j2 + ((((long) iArr5[i6]) & InternalZipConstants.ZIP_64_LIMIT) * j) + (((long) iArr4[i7]) & InternalZipConstants.ZIP_64_LIMIT);
                iArr4[i7] = (int) j3;
                j2 = j3 >>> 32;
                i6++;
                int i8 = i2;
                j = j;
            }
            iArr4[i4 + i3] = (int) j2;
            i4++;
            i5 = i2;
        }
    }

    public static long udiv_qrnnd(long j, int i) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        int i2 = i;
        long j7 = j >>> 32;
        long j8 = j & InternalZipConstants.ZIP_64_LIMIT;
        if (i2 >= 0) {
            long j9 = (long) i2;
            if (j7 < (((j9 - j7) - (j8 >>> 31)) & InternalZipConstants.ZIP_64_LIMIT)) {
                j3 = j / j9;
                j2 = j % j9;
            } else {
                long j10 = j - (j9 << 31);
                long j11 = j10 % j9;
                j3 = (j10 / j9) - 2147483648L;
                j2 = j11;
            }
        } else {
            long j12 = (long) (i2 >>> 1);
            long j13 = j >>> 1;
            int i3 = (j7 > j12 ? 1 : (j7 == j12 ? 0 : -1));
            if (i3 < 0 || (j7 >> 1) < j12) {
                long j14 = 1;
                if (i3 < 0) {
                    j4 = j13 / j12;
                    j5 = j13 % j12;
                } else {
                    long j15 = ~(j13 - (j12 << 32));
                    j4 = (~(j15 / j12)) & InternalZipConstants.ZIP_64_LIMIT;
                    j5 = (j12 - 1) - (j15 % j12);
                }
                long j16 = (j8 & 1) + (j5 * 2);
                if ((i2 & 1) != 0) {
                    if (j16 >= j4) {
                        j16 -= j4;
                    } else {
                        long j17 = (long) i2;
                        if (j4 - j16 <= (j17 & InternalZipConstants.ZIP_64_LIMIT)) {
                            j2 = (j16 - j4) + j17;
                        } else {
                            j2 = (j16 - j4) + j17 + j17;
                            j14 = 2;
                        }
                        j3 = j4 - j14;
                    }
                }
                j3 = j4;
            } else {
                if (j8 >= (((long) (-i2)) & InternalZipConstants.ZIP_64_LIMIT)) {
                    j3 = -1;
                    j6 = (long) i2;
                } else {
                    j3 = -2;
                    j6 = (long) i2;
                    j8 += j6;
                }
                j2 = j8 + j6;
            }
        }
        return (j2 << 32) | (j3 & InternalZipConstants.ZIP_64_LIMIT);
    }

    public static int divmod_1(int[] iArr, int[] iArr2, int i, int i2) {
        long j;
        int i3 = i - 1;
        long j2 = (long) iArr2[i3];
        if ((j2 & InternalZipConstants.ZIP_64_LIMIT) >= (((long) i2) & InternalZipConstants.ZIP_64_LIMIT)) {
            j = 0;
        } else {
            iArr[i3] = 0;
            j = j2 << 32;
            i3--;
        }
        while (i3 >= 0) {
            j = udiv_qrnnd((j & -4294967296L) | (((long) iArr2[i3]) & InternalZipConstants.ZIP_64_LIMIT), i2);
            iArr[i3] = (int) j;
            i3--;
        }
        return (int) (j >> 32);
    }

    public static int submul_1(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        long j = ((long) i3) & InternalZipConstants.ZIP_64_LIMIT;
        int i4 = 0;
        int i5 = 0;
        do {
            long j2 = (((long) iArr2[i4]) & InternalZipConstants.ZIP_64_LIMIT) * j;
            int i6 = ((int) j2) + i5;
            i5 = ((i6 ^ Integer.MIN_VALUE) < (i5 ^ Integer.MIN_VALUE) ? 1 : 0) + ((int) (j2 >> 32));
            int i7 = i + i4;
            int i8 = iArr[i7];
            int i9 = i8 - i6;
            if ((i9 ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ i8)) {
                i5++;
            }
            iArr[i7] = i9;
            i4++;
        } while (i4 < i2);
        return i5;
    }

    public static void divide(int[] iArr, int i, int[] iArr2, int i2) {
        int i3;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i4 = i2;
        int i5 = i;
        do {
            int i6 = iArr3[i5];
            int i7 = iArr4[i4 - 1];
            if (i6 == i7) {
                i3 = -1;
            } else {
                i3 = (int) udiv_qrnnd((((long) i6) << 32) + (((long) iArr3[i5 - 1]) & InternalZipConstants.ZIP_64_LIMIT), i7);
            }
            if (i3 != 0) {
                int i8 = i5 - i4;
                long submul_1 = (((long) iArr3[i5]) & InternalZipConstants.ZIP_64_LIMIT) - (((long) submul_1(iArr3, i8, iArr4, i4, i3)) & InternalZipConstants.ZIP_64_LIMIT);
                while (true) {
                    long j = 0;
                    if (submul_1 == 0) {
                        break;
                    }
                    int i9 = i3 - 1;
                    int i10 = 0;
                    while (i10 < i4) {
                        int i11 = i8 + i10;
                        long j2 = j + (((long) iArr3[i11]) & InternalZipConstants.ZIP_64_LIMIT) + (((long) iArr4[i10]) & InternalZipConstants.ZIP_64_LIMIT);
                        iArr3[i11] = (int) j2;
                        j = j2 >>> 32;
                        i10++;
                        i8 = i8;
                        i9 = i9;
                    }
                    iArr3[i5] = (int) (((long) iArr3[i5]) + j);
                    submul_1 = j - 1;
                    i8 = i8;
                    i3 = i9;
                }
            }
            iArr3[i5] = i3;
            i5--;
        } while (i5 >= i4);
    }

    public static int set_str(int[] iArr, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = 0;
        if (((i2 - 1) & i2) == 0) {
            int i5 = 0;
            while (true) {
                i2 >>= 1;
                if (i2 == 0) {
                    break;
                }
                i5++;
            }
            int i6 = 0;
            int i7 = 0;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                byte b = bArr[i];
                i4 |= b << i6;
                i6 += i5;
                if (i6 >= 32) {
                    iArr[i7] = i4;
                    i6 -= 32;
                    i4 = b >> (i5 - i6);
                    i7++;
                }
            }
            if (i4 == 0) {
                return i7;
            }
            iArr[i7] = i4;
            return i7 + 1;
        }
        int chars_per_word = chars_per_word(i2);
        int i8 = 0;
        while (i4 < i) {
            int i9 = i - i4;
            if (i9 > chars_per_word) {
                i9 = chars_per_word;
            }
            int i10 = i4 + 1;
            int i11 = i2;
            int i12 = bArr[i4];
            while (true) {
                i9--;
                if (i9 <= 0) {
                    i3 = i12;
                    break;
                }
                i12 = (i12 * i2) + bArr[i10];
                i11 *= i2;
                i10++;
            }
            if (i8 != 0) {
                i3 = add_1(iArr, iArr, i8, i12) + mul_1(iArr, iArr, i8, i11);
            }
            if (i3 != 0) {
                iArr[i8] = i3;
                i8++;
            }
            i4 = i10;
        }
        return i8;
    }

    public static int cmp(int[] iArr, int[] iArr2, int i) {
        int i2;
        int i3;
        do {
            i--;
            if (i < 0) {
                return 0;
            }
            i2 = iArr[i];
            i3 = iArr2[i];
        } while (i2 == i3);
        if ((i2 ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ i3)) {
            return 1;
        }
        return -1;
    }

    public static int cmp(int[] iArr, int i, int[] iArr2, int i2) {
        if (i > i2) {
            return 1;
        }
        if (i < i2) {
            return -1;
        }
        return cmp(iArr, iArr2, i);
    }

    public static int rshift(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4 = 32 - i3;
        int i5 = iArr2[i];
        int i6 = i5 << i4;
        int i7 = 1;
        while (i7 < i2) {
            int i8 = iArr2[i + i7];
            iArr[i7 - 1] = (i5 >>> i3) | (i8 << i4);
            i7++;
            i5 = i8;
        }
        iArr[i7 - 1] = i5 >>> i3;
        return i6;
    }

    public static void rshift0(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        if (i3 > 0) {
            rshift(iArr, iArr2, i, i2, i3);
            return;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            iArr[i4] = iArr2[i4 + i];
        }
    }

    public static long rshift_long(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = i2 >> 5;
        int i6 = i2 & 31;
        int i7 = iArr[i + -1] < 0 ? -1 : 0;
        if (i5 >= i) {
            i3 = i7;
        } else {
            i3 = iArr[i5];
        }
        int i8 = i5 + 1;
        if (i8 >= i) {
            i4 = i7;
        } else {
            i4 = iArr[i8];
        }
        if (i6 != 0) {
            int i9 = i8 + 1;
            if (i9 < i) {
                i7 = iArr[i9];
            }
            int i10 = 32 - i6;
            i3 = (i3 >>> i6) | (i4 << i10);
            i4 = (i4 >>> i6) | (i7 << i10);
        }
        return (((long) i4) << 32) | (((long) i3) & InternalZipConstants.ZIP_64_LIMIT);
    }

    public static int lshift(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = 32 - i3;
        int i5 = i2 - 1;
        int i6 = iArr2[i5];
        int i7 = i6 >>> i4;
        int i8 = i + 1;
        while (true) {
            i5--;
            if (i5 >= 0) {
                int i9 = iArr2[i5];
                iArr[i8 + i5] = (i6 << i3) | (i9 >>> i4);
                i6 = i9;
            } else {
                iArr[i8 + i5] = i6 << i3;
                return i7;
            }
        }
    }

    static int findLowestBit(int[] iArr) {
        int i = 0;
        while (true) {
            int i2 = iArr[i];
            if (i2 != 0) {
                return (i * 32) + findLowestBit(i2);
            }
            i++;
        }
    }

    public static int gcd(int[] iArr, int[] iArr2, int i) {
        int i2;
        int[] iArr3;
        int i3;
        int i4 = 0;
        while (true) {
            i2 = iArr[i4] | iArr2[i4];
            if (i2 != 0) {
                break;
            }
            i4++;
        }
        int findLowestBit = findLowestBit(i2);
        int i5 = i - i4;
        rshift0(iArr, iArr, i4, i5, findLowestBit);
        rshift0(iArr2, iArr2, i4, i5, findLowestBit);
        if ((iArr[0] & 1) != 0) {
            i3 = i5;
            iArr3 = iArr;
        } else {
            i3 = i5;
            iArr3 = iArr2;
            iArr2 = iArr;
        }
        while (true) {
            int i6 = 0;
            while (iArr2[i6] == 0) {
                i6++;
            }
            if (i6 > 0) {
                int i7 = 0;
                while (i7 < i3 - i6) {
                    iArr2[i7] = iArr2[i7 + i6];
                    i7++;
                }
                while (i7 < i3) {
                    iArr2[i7] = 0;
                    i7++;
                }
            }
            int findLowestBit2 = findLowestBit(iArr2[0]);
            if (findLowestBit2 > 0) {
                rshift(iArr2, iArr2, 0, i3, findLowestBit2);
            }
            int cmp = cmp(iArr3, iArr2, i3);
            if (cmp == 0) {
                break;
            }
            if (cmp > 0) {
                sub_n(iArr3, iArr3, iArr2, i3);
                int[] iArr4 = iArr3;
                iArr3 = iArr2;
                iArr2 = iArr4;
            } else {
                sub_n(iArr2, iArr2, iArr3, i3);
            }
            while (true) {
                int i8 = i3 - 1;
                if (iArr3[i8] == 0 && iArr2[i8] == 0) {
                    i3--;
                }
            }
        }
        if (i4 + findLowestBit <= 0) {
            return i3;
        }
        if (findLowestBit <= 0) {
            int i9 = i3;
            while (true) {
                i9--;
                if (i9 < 0) {
                    break;
                }
                iArr[i9 + i4] = iArr[i9];
            }
        } else {
            int lshift = lshift(iArr, i4, iArr, i3, findLowestBit);
            if (lshift != 0) {
                iArr[i3 + i4] = lshift;
                i3++;
            }
        }
        int i10 = i4;
        while (true) {
            i10--;
            if (i10 < 0) {
                return i3 + i4;
            }
            iArr[i10] = 0;
        }
    }

    public static int intLength(int i) {
        if (i < 0) {
            i = ~i;
        }
        return 32 - count_leading_zeros(i);
    }

    public static int intLength(int[] iArr, int i) {
        int i2 = i - 1;
        return intLength(iArr[i2]) + (i2 * 32);
    }
}
