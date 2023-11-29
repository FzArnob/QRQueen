package gnu.math;

public class BitOps {
    static final byte[] bit4_count = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    public static int lowestBitSet(int i) {
        if (i == 0) {
            return -1;
        }
        int i2 = 0;
        while ((i & 255) == 0) {
            i >>>= 8;
            i2 += 8;
        }
        while ((i & 3) == 0) {
            i >>>= 2;
            i2 += 2;
        }
        return (i & 1) == 0 ? i2 + 1 : i2;
    }

    private BitOps() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean bitValue(gnu.math.IntNum r4, int r5) {
        /*
            int r0 = r4.ival
            int[] r1 = r4.words
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0016
            r4 = 32
            if (r5 < r4) goto L_0x000f
            if (r0 >= 0) goto L_0x0015
            goto L_0x0014
        L_0x000f:
            int r4 = r0 >> r5
            r4 = r4 & r3
            if (r4 == 0) goto L_0x0015
        L_0x0014:
            r2 = 1
        L_0x0015:
            return r2
        L_0x0016:
            int r1 = r5 >> 5
            int[] r4 = r4.words
            if (r1 < r0) goto L_0x0022
            int r0 = r0 - r3
            r4 = r4[r0]
            if (r4 >= 0) goto L_0x0029
            goto L_0x0028
        L_0x0022:
            r4 = r4[r1]
            int r4 = r4 >> r5
            r4 = r4 & r3
            if (r4 == 0) goto L_0x0029
        L_0x0028:
            r2 = 1
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.BitOps.bitValue(gnu.math.IntNum, int):boolean");
    }

    static int[] dataBufferFor(IntNum intNum, int i) {
        int i2 = intNum.ival;
        int i3 = (i + 1) >> 5;
        if (intNum.words == null) {
            if (i3 == 0) {
                i3 = 1;
            }
            int[] iArr = new int[i3];
            iArr[0] = i2;
            if (i2 >= 0) {
                return iArr;
            }
            for (int i4 = 1; i4 < i3; i4++) {
                iArr[i4] = -1;
            }
            return iArr;
        }
        int[] iArr2 = new int[(i3 > i2 ? i3 : i2)];
        int i5 = i2;
        while (true) {
            i5--;
            if (i5 < 0) {
                break;
            }
            iArr2[i5] = intNum.words[i5];
        }
        if (iArr2[i2 - 1] < 0) {
            while (i2 < i3) {
                iArr2[i2] = -1;
                i2++;
            }
        }
        return iArr2;
    }

    public static IntNum setBitValue(IntNum intNum, int i, int i2) {
        int i3;
        int i4 = i2 & 1;
        int i5 = intNum.ival;
        if (intNum.words == null) {
            if (((i5 >> (i < 31 ? i : 31)) & 1) == i4) {
                return intNum;
            }
            if (i < 63) {
                return IntNum.make(((long) (1 << i)) ^ ((long) i5));
            }
        } else {
            int i6 = i >> 5;
            if (i6 >= i5) {
                i3 = intNum.words[i5 - 1] < 0 ? 1 : 0;
            } else {
                i3 = (intNum.words[i6] >> i) & 1;
            }
            if (i3 == i4) {
                return intNum;
            }
        }
        int[] dataBufferFor = dataBufferFor(intNum, i);
        int i7 = i >> 5;
        dataBufferFor[i7] = (1 << (i & 31)) ^ dataBufferFor[i7];
        return IntNum.make(dataBufferFor, dataBufferFor.length);
    }

    public static boolean test(IntNum intNum, int i) {
        if (intNum.words == null) {
            if ((intNum.ival & i) != 0) {
                return true;
            }
            return false;
        } else if (i < 0 || (intNum.words[0] & i) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean test(IntNum intNum, IntNum intNum2) {
        if (intNum2.words == null) {
            return test(intNum, intNum2.ival);
        }
        if (intNum.words == null) {
            return test(intNum2, intNum.ival);
        }
        if (intNum.ival < intNum2.ival) {
            IntNum intNum3 = intNum2;
            intNum2 = intNum;
            intNum = intNum3;
        }
        for (int i = 0; i < intNum2.ival; i++) {
            if ((intNum.words[i] & intNum2.words[i]) != 0) {
                return true;
            }
        }
        return intNum2.isNegative();
    }

    public static IntNum and(IntNum intNum, int i) {
        if (intNum.words == null) {
            return IntNum.make(intNum.ival & i);
        }
        if (i >= 0) {
            return IntNum.make(intNum.words[0] & i);
        }
        int i2 = intNum.ival;
        int[] iArr = new int[i2];
        iArr[0] = i & intNum.words[0];
        while (true) {
            i2--;
            if (i2 <= 0) {
                return IntNum.make(iArr, intNum.ival);
            }
            iArr[i2] = intNum.words[i2];
        }
    }

    public static IntNum and(IntNum intNum, IntNum intNum2) {
        if (intNum2.words == null) {
            return and(intNum, intNum2.ival);
        }
        if (intNum.words == null) {
            return and(intNum2, intNum.ival);
        }
        if (intNum.ival >= intNum2.ival) {
            IntNum intNum3 = intNum2;
            intNum2 = intNum;
            intNum = intNum3;
        }
        int i = intNum.isNegative() ? intNum2.ival : intNum.ival;
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < intNum.ival) {
            iArr[i2] = intNum2.words[i2] & intNum.words[i2];
            i2++;
        }
        while (i2 < i) {
            iArr[i2] = intNum2.words[i2];
            i2++;
        }
        return IntNum.make(iArr, i);
    }

    public static IntNum ior(IntNum intNum, IntNum intNum2) {
        return bitOp(7, intNum, intNum2);
    }

    public static IntNum xor(IntNum intNum, IntNum intNum2) {
        return bitOp(6, intNum, intNum2);
    }

    public static IntNum not(IntNum intNum) {
        return bitOp(12, intNum, IntNum.zero());
    }

    public static int swappedOp(int i) {
        return "\u0000\u0001\u0004\u0005\u0002\u0003\u0006\u0007\b\t\f\r\n\u000b\u000e\u000f".charAt(i);
    }

    public static IntNum bitOp(int i, IntNum intNum, IntNum intNum2) {
        if (i == 0) {
            return IntNum.zero();
        }
        if (i == 1) {
            return and(intNum, intNum2);
        }
        if (i == 3) {
            return intNum;
        }
        if (i == 5) {
            return intNum2;
        }
        if (i == 15) {
            return IntNum.minusOne();
        }
        IntNum intNum3 = new IntNum();
        setBitOp(intNum3, i, intNum, intNum2);
        return intNum3.canonicalize();
    }

    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x017f, code lost:
        if (r11 != 0) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0181, code lost:
        if (r6 != null) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0183, code lost:
        r10.ival = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0185, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0186, code lost:
        r6[r11] = r4;
        r11 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0189, code lost:
        r10.ival = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x018b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        if (r0 < 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0064, code lost:
        if (r0 >= 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0076, code lost:
        r12 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0080, code lost:
        if (r0 < 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ac, code lost:
        if (r0 >= 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c3, code lost:
        if (r0 >= 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d8, code lost:
        if (r0 >= 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ee, code lost:
        if (r0 < 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0118, code lost:
        if (r0 < 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0129, code lost:
        r12 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0132, code lost:
        if (r0 >= 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0147, code lost:
        if (r0 < 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0159, code lost:
        r0 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x015b, code lost:
        if (r0 != r5) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x015e, code lost:
        r1 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x015f, code lost:
        if (r1 == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0161, code lost:
        if (r1 == true) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0163, code lost:
        if (r1 == true) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0166, code lost:
        r6[r11] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0168, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0169, code lost:
        if (r11 >= r5) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x016b, code lost:
        r6[r11] = ~r13.words[r11];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0173, code lost:
        r6[r11] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0175, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0176, code lost:
        if (r11 >= r5) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0178, code lost:
        r6[r11] = r13.words[r11];
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setBitOp(gnu.math.IntNum r10, int r11, gnu.math.IntNum r12, gnu.math.IntNum r13) {
        /*
            int[] r0 = r13.words
            if (r0 != 0) goto L_0x0005
            goto L_0x0010
        L_0x0005:
            int[] r0 = r12.words
            if (r0 == 0) goto L_0x0014
            int r0 = r12.ival
            int r1 = r13.ival
            if (r0 >= r1) goto L_0x0010
            goto L_0x0014
        L_0x0010:
            r9 = r13
            r13 = r12
            r12 = r9
            goto L_0x0018
        L_0x0014:
            int r11 = swappedOp(r11)
        L_0x0018:
            int[] r0 = r12.words
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0022
            int r0 = r12.ival
            r3 = 1
            goto L_0x0028
        L_0x0022:
            int[] r0 = r12.words
            r0 = r0[r1]
            int r3 = r12.ival
        L_0x0028:
            int[] r4 = r13.words
            if (r4 != 0) goto L_0x0030
            int r4 = r13.ival
            r5 = 1
            goto L_0x0036
        L_0x0030:
            int[] r4 = r13.words
            r4 = r4[r1]
            int r5 = r13.ival
        L_0x0036:
            if (r5 <= r2) goto L_0x003b
            r10.realloc(r5)
        L_0x003b:
            int[] r6 = r10.words
            r7 = 2
            switch(r11) {
                case 0: goto L_0x0156;
                case 1: goto L_0x0141;
                case 2: goto L_0x012b;
                case 3: goto L_0x0128;
                case 4: goto L_0x0111;
                case 5: goto L_0x00fd;
                case 6: goto L_0x00e8;
                case 7: goto L_0x00d2;
                case 8: goto L_0x00bc;
                case 9: goto L_0x00a5;
                case 10: goto L_0x0090;
                case 11: goto L_0x0079;
                case 12: goto L_0x0074;
                case 13: goto L_0x005d;
                case 14: goto L_0x0046;
                default: goto L_0x0041;
            }
        L_0x0041:
            r4 = -1
            r11 = 0
        L_0x0043:
            r12 = 0
            goto L_0x0159
        L_0x0046:
            r11 = 0
        L_0x0047:
            r4 = r4 & r0
            int r4 = ~r4
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x0051
            if (r0 >= 0) goto L_0x0043
            goto L_0x011a
        L_0x0051:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x0047
        L_0x005d:
            r11 = 0
        L_0x005e:
            int r4 = ~r4
            r4 = r4 | r0
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x0068
            if (r0 < 0) goto L_0x0043
            goto L_0x011a
        L_0x0068:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x005e
        L_0x0074:
            int r4 = ~r4
            r11 = 0
        L_0x0076:
            r12 = 2
            goto L_0x0159
        L_0x0079:
            r11 = 0
        L_0x007a:
            int r8 = ~r0
            r4 = r4 | r8
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x0084
            if (r0 >= 0) goto L_0x0043
            goto L_0x0149
        L_0x0084:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x007a
        L_0x0090:
            r11 = 0
        L_0x0091:
            int r4 = ~r0
            int r0 = r11 + 1
            if (r0 < r3) goto L_0x0097
            goto L_0x0043
        L_0x0097:
            r6[r11] = r4
            int[] r11 = r13.words
            r11 = r11[r0]
            int[] r11 = r12.words
            r11 = r11[r0]
            r9 = r0
            r0 = r11
            r11 = r9
            goto L_0x0091
        L_0x00a5:
            r11 = 0
        L_0x00a6:
            r4 = r4 ^ r0
            int r4 = ~r4
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x00b0
            if (r0 < 0) goto L_0x0129
            goto L_0x011a
        L_0x00b0:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x00a6
        L_0x00bc:
            r11 = 0
        L_0x00bd:
            r4 = r4 | r0
            int r4 = ~r4
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x00c6
            if (r0 < 0) goto L_0x0043
            goto L_0x011a
        L_0x00c6:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x00bd
        L_0x00d2:
            r11 = 0
        L_0x00d3:
            r4 = r4 | r0
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x00dc
            if (r0 < 0) goto L_0x0043
            goto L_0x0149
        L_0x00dc:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x00d3
        L_0x00e8:
            r11 = 0
        L_0x00e9:
            r4 = r4 ^ r0
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x00f1
            if (r0 >= 0) goto L_0x0129
            goto L_0x011a
        L_0x00f1:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x00e9
        L_0x00fd:
            r11 = 0
        L_0x00fe:
            int r4 = r11 + 1
            if (r4 < r3) goto L_0x0105
            r4 = r0
            goto L_0x0043
        L_0x0105:
            r6[r11] = r0
            int[] r11 = r13.words
            r11 = r11[r4]
            int[] r11 = r12.words
            r0 = r11[r4]
            r11 = r4
            goto L_0x00fe
        L_0x0111:
            r11 = 0
        L_0x0112:
            int r4 = ~r4
            r4 = r4 & r0
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x011c
            if (r0 >= 0) goto L_0x0043
        L_0x011a:
            goto L_0x0076
        L_0x011c:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x0112
        L_0x0128:
            r11 = 0
        L_0x0129:
            r12 = 1
            goto L_0x0159
        L_0x012b:
            r11 = 0
        L_0x012c:
            int r8 = ~r0
            r4 = r4 & r8
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x0135
            if (r0 < 0) goto L_0x0043
            goto L_0x0149
        L_0x0135:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x012c
        L_0x0141:
            r11 = 0
        L_0x0142:
            r4 = r4 & r0
            int r8 = r11 + 1
            if (r8 < r3) goto L_0x014a
            if (r0 >= 0) goto L_0x0043
        L_0x0149:
            goto L_0x0129
        L_0x014a:
            r6[r11] = r4
            int[] r11 = r13.words
            r4 = r11[r8]
            int[] r11 = r12.words
            r0 = r11[r8]
            r11 = r8
            goto L_0x0142
        L_0x0156:
            r11 = 0
            r12 = 0
            r4 = 0
        L_0x0159:
            int r0 = r11 + 1
            if (r0 != r5) goto L_0x015e
            goto L_0x015f
        L_0x015e:
            r1 = r12
        L_0x015f:
            if (r1 == 0) goto L_0x017f
            if (r1 == r2) goto L_0x0173
            if (r1 == r7) goto L_0x0166
            goto L_0x0189
        L_0x0166:
            r6[r11] = r4
        L_0x0168:
            int r11 = r11 + r2
            if (r11 >= r5) goto L_0x0189
            int[] r12 = r13.words
            r12 = r12[r11]
            int r12 = ~r12
            r6[r11] = r12
            goto L_0x0168
        L_0x0173:
            r6[r11] = r4
        L_0x0175:
            int r11 = r11 + r2
            if (r11 >= r5) goto L_0x0189
            int[] r12 = r13.words
            r12 = r12[r11]
            r6[r11] = r12
            goto L_0x0175
        L_0x017f:
            if (r11 != 0) goto L_0x0186
            if (r6 != 0) goto L_0x0186
            r10.ival = r4
            return
        L_0x0186:
            r6[r11] = r4
            r11 = r0
        L_0x0189:
            r10.ival = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.BitOps.setBitOp(gnu.math.IntNum, int, gnu.math.IntNum, gnu.math.IntNum):void");
    }

    public static IntNum extract(IntNum intNum, int i, int i2) {
        int i3;
        int i4;
        long j;
        int i5 = 0;
        if (i2 < 32) {
            return IntNum.make(((intNum.words == null ? intNum.ival : intNum.words[0]) & (~(-1 << i2))) >> i);
        }
        if (intNum.words != null) {
            i3 = intNum.ival;
        } else if (intNum.ival >= 0) {
            if (i < 31) {
                i5 = intNum.ival >> i;
            }
            return IntNum.make(i5);
        } else {
            i3 = 1;
        }
        boolean isNegative = intNum.isNegative();
        int i6 = i3 * 32;
        if (i2 <= i6) {
            i3 = (i2 + 31) >> 5;
        } else if (!isNegative && i == 0) {
            return intNum;
        } else {
            i2 = i6;
        }
        int i7 = i2 - i;
        if (i7 < 64) {
            if (intNum.words == null) {
                int i8 = intNum.ival;
                if (i >= 32) {
                    i = 31;
                }
                j = (long) (i8 >> i);
            } else {
                j = MPN.rshift_long(intNum.words, i3, i);
            }
            return IntNum.make(j & (~(-1 << i7)));
        }
        int i9 = i >> 5;
        int[] iArr = new int[(((i2 >> 5) + 1) - i9)];
        if (intNum.words == null) {
            if (i >= 32) {
                i4 = -1;
            } else {
                i4 = intNum.ival >> i;
            }
            iArr[0] = i4;
        } else {
            MPN.rshift0(iArr, intNum.words, i9, i3 - i9, i & 31);
        }
        int i10 = i7 >> 5;
        iArr[i10] = iArr[i10] & (~(-1 << i7));
        return IntNum.make(iArr, i10 + 1);
    }

    public static int lowestBitSet(IntNum intNum) {
        int[] iArr = intNum.words;
        if (iArr == null) {
            return lowestBitSet(intNum.ival);
        }
        int i = intNum.ival;
        while (i > 0) {
            int lowestBitSet = lowestBitSet(iArr[0]);
            if (lowestBitSet >= 0) {
                return 0 + lowestBitSet;
            }
        }
        return -1;
    }

    public static int bitCount(int i) {
        int i2 = 0;
        while (i != 0) {
            i2 += bit4_count[i & 15];
            i >>>= 4;
        }
        return i2;
    }

    public static int bitCount(int[] iArr, int i) {
        int i2 = 0;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            i2 += bitCount(iArr[i]);
        }
    }

    public static int bitCount(IntNum intNum) {
        int i;
        int i2;
        int[] iArr = intNum.words;
        if (iArr == null) {
            i2 = 1;
            i = bitCount(intNum.ival);
        } else {
            int i3 = intNum.ival;
            int i4 = i3;
            i = bitCount(iArr, i3);
            i2 = i4;
        }
        return intNum.isNegative() ? (i2 * 32) - i : i;
    }

    public static IntNum reverseBits(IntNum intNum, int i, int i2) {
        int i3;
        int i4 = intNum.ival;
        if (intNum.words != null || i2 >= 63) {
            int i5 = i2 - 1;
            int[] dataBufferFor = dataBufferFor(intNum, i5);
            while (i < i5) {
                int i6 = i >> 5;
                int i7 = i5 >> 5;
                int i8 = dataBufferFor[i6];
                int i9 = (i8 >> i) & 1;
                if (i6 == i7) {
                    i3 = (((i8 >> i5) & 1) << i) | ((int) (((long) i8) & (~((1 << i) | (1 << i5))))) | (i9 << i5);
                } else {
                    int i10 = dataBufferFor[i7];
                    int i11 = i5 & 31;
                    int i12 = i & 31;
                    int i13 = i8 & (~(1 << i12));
                    dataBufferFor[i7] = (i9 << i11) | (i10 & (~(1 << i11)));
                    i3 = i13 | (((i10 >> i11) & 1) << i12);
                }
                dataBufferFor[i6] = i3;
                i++;
                i5--;
            }
            return IntNum.make(dataBufferFor, dataBufferFor.length);
        }
        long j = (long) i4;
        for (int i14 = i2 - 1; i < i14; i14--) {
            j = (j & (~((1 << i) | (1 << i14)))) | (((j >> i) & 1) << i14) | (((j >> i14) & 1) << i);
            i++;
        }
        return IntNum.make(j);
    }
}
