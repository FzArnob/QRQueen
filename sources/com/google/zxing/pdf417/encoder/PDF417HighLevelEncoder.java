package com.google.zxing.pdf417.encoder;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.AND32, 13, 9, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.MOVE32_32, 35, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.AND8, Ev3Constants.Opcode.INIT_BYTES, 43, Ev3Constants.Opcode.AND16, Ev3Constants.Opcode.XOR32, Ev3Constants.Opcode.MOVEF_16, Ev3Constants.Opcode.SELECT32, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {Ev3Constants.Opcode.MOVE32_F, Ev3Constants.Opcode.MOVEF_8, Ev3Constants.Opcode.MOVEF_32, Ev3Constants.Opcode.JR, Ev3Constants.Opcode.CP_GTEQF, Ev3Constants.Opcode.SELECT8, Ev3Constants.Opcode.SELECT16, Ev3Constants.Opcode.SELECTF, Ev3Constants.Opcode.SYSTEM, Ev3Constants.Opcode.MEMORY_WRITE, Ev3Constants.Opcode.OR16, 13, 9, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.MOVE32_32, 10, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.AND8, Ev3Constants.Opcode.INIT_BYTES, Ev3Constants.Opcode.OR32, Ev3Constants.Opcode.INFO, Ev3Constants.Opcode.XOR32, Ev3Constants.Opcode.XOR8, Ev3Constants.Opcode.XOR16, Ev3Constants.Opcode.MOVEF_F, Ev3Constants.Opcode.JR_GTEQF, Ev3Constants.Opcode.STRINGS, 39, 0};

    private static boolean isAlphaLower(char c) {
        return c == ' ' || (c >= 'a' && c <= 'z');
    }

    private static boolean isAlphaUpper(char c) {
        return c == ' ' || (c >= 'A' && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isText(char c) {
        return c == 9 || c == 10 || c == 13 || (c >= ' ' && c <= '~');
    }

    static {
        byte[] bArr = new byte[128];
        MIXED = bArr;
        Arrays.fill(bArr, (byte) -1);
        byte b = 0;
        byte b2 = 0;
        while (true) {
            byte[] bArr2 = TEXT_MIXED_RAW;
            if (b2 >= bArr2.length) {
                break;
            }
            byte b3 = bArr2[b2];
            if (b3 > 0) {
                MIXED[b3] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (b < bArr3.length) {
                byte b4 = bArr3[b];
                if (b4 > 0) {
                    PUNCTUATION[b4] = b;
                }
                b = (byte) (b + 1);
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    private static byte[] getBytesForMessage(String str) {
        return str.getBytes();
    }

    static String encodeHighLevel(String str, Compaction compaction) throws WriterException {
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytesForMessage = getBytesForMessage(str);
            encodeBinary(bytesForMessage, 0, bytesForMessage.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append(902);
            encodeNumeric(str, 0, length, sb);
        } else {
            byte[] bArr = null;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i);
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append(902);
                    encodeNumeric(str, i, determineConsecutiveDigitCount, sb);
                    i += determineConsecutiveDigitCount;
                    i2 = 0;
                    i3 = 2;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i);
                    if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                        if (i3 != 0) {
                            sb.append(900);
                            i2 = 0;
                            i3 = 0;
                        }
                        i2 = encodeText(str, i, determineConsecutiveTextCount, sb, i2);
                        i += determineConsecutiveTextCount;
                    } else {
                        if (bArr == null) {
                            bArr = getBytesForMessage(str);
                        }
                        int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, bArr, i);
                        if (determineConsecutiveBinaryCount == 0) {
                            determineConsecutiveBinaryCount = 1;
                        }
                        if (determineConsecutiveBinaryCount == 1 && i3 == 0) {
                            encodeBinary(bArr, i, 1, 0, sb);
                        } else {
                            encodeBinary(bArr, i, determineConsecutiveBinaryCount, i3, sb);
                            i2 = 0;
                            i3 = 1;
                        }
                        i += determineConsecutiveBinaryCount;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f6 A[EDGE_INSN: B:68:0x00f6->B:53:0x00f6 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0011 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 2
            r5 = 0
            r6 = 1
            r7 = r20
            r8 = 0
        L_0x0011:
            int r9 = r17 + r8
            char r10 = r0.charAt(r9)
            r11 = 26
            r12 = 32
            r13 = 28
            r14 = 27
            r15 = 29
            if (r7 == 0) goto L_0x00bc
            if (r7 == r6) goto L_0x0083
            if (r7 == r4) goto L_0x003c
            boolean r9 = isPunctuation(r10)
            if (r9 == 0) goto L_0x0037
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0037:
            r3.append(r15)
        L_0x003a:
            r7 = 0
            goto L_0x0011
        L_0x003c:
            boolean r11 = isMixed(r10)
            if (r11 == 0) goto L_0x004c
            byte[] r9 = MIXED
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x004c:
            boolean r11 = isAlphaUpper(r10)
            if (r11 == 0) goto L_0x0056
            r3.append(r13)
            goto L_0x003a
        L_0x0056:
            boolean r11 = isAlphaLower(r10)
            if (r11 == 0) goto L_0x0061
            r3.append(r14)
            goto L_0x00d8
        L_0x0061:
            int r9 = r9 + 1
            if (r9 >= r1) goto L_0x0076
            char r9 = r0.charAt(r9)
            boolean r9 = isPunctuation(r9)
            if (r9 == 0) goto L_0x0076
            r7 = 3
            r9 = 25
            r3.append(r9)
            goto L_0x0011
        L_0x0076:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0083:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x0096
            if (r10 != r12) goto L_0x008f
            r3.append(r11)
            goto L_0x00f2
        L_0x008f:
            int r10 = r10 + -97
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00f2
        L_0x0096:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x00a6
            r3.append(r14)
            int r10 = r10 + -65
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00f2
        L_0x00a6:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x00b0
            r3.append(r13)
            goto L_0x00e4
        L_0x00b0:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x00bc:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x00cf
            if (r10 != r12) goto L_0x00c8
            r3.append(r11)
            goto L_0x00f2
        L_0x00c8:
            int r10 = r10 + -65
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00f2
        L_0x00cf:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x00db
            r3.append(r14)
        L_0x00d8:
            r7 = 1
            goto L_0x0011
        L_0x00db:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x00e7
            r3.append(r13)
        L_0x00e4:
            r7 = 2
            goto L_0x0011
        L_0x00e7:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
        L_0x00f2:
            int r8 = r8 + 1
            if (r8 < r1) goto L_0x0011
            int r0 = r3.length()
            r1 = 0
            r8 = 0
        L_0x00fc:
            if (r1 >= r0) goto L_0x011a
            int r9 = r1 % 2
            if (r9 == 0) goto L_0x0104
            r9 = 1
            goto L_0x0105
        L_0x0104:
            r9 = 0
        L_0x0105:
            if (r9 == 0) goto L_0x0113
            int r8 = r8 * 30
            char r9 = r3.charAt(r1)
            int r8 = r8 + r9
            char r8 = (char) r8
            r2.append(r8)
            goto L_0x0117
        L_0x0113:
            char r8 = r3.charAt(r1)
        L_0x0117:
            int r1 = r1 + 1
            goto L_0x00fc
        L_0x011a:
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x0124
            int r8 = r8 * 30
            int r8 = r8 + r15
            char r0 = (char) r8
            r2.append(r0)
        L_0x0124:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        if (i2 == 1 && i3 == 0) {
            sb.append(913);
        }
        if (i2 >= 6) {
            sb.append(924);
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                long j = 0;
                for (int i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + ((long) (bArr[i4 + i5] & Ev3Constants.Opcode.TST));
                }
                for (int i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int i7 = 4; i7 >= 0; i7--) {
                    sb.append(cArr[i7]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        int i8 = i + i2;
        if (i4 < i8) {
            sb.append(901);
        }
        while (i4 < i8) {
            sb.append((char) (bArr[i4] & Ev3Constants.Opcode.TST));
            i4++;
        }
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2 - 1) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder();
            sb3.append('1');
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != -1;
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != -1;
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
            if (i3 >= 13) {
                return (i2 - i) - i3;
            }
            if (i3 <= 0) {
                if (!isText(charSequence.charAt(i2))) {
                    break;
                }
                i2++;
            }
        }
        return i2 - i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r4 = r4 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int determineConsecutiveBinaryCount(java.lang.CharSequence r7, byte[] r8, int r9) throws com.google.zxing.WriterException {
        /*
            int r0 = r7.length()
            r1 = r9
        L_0x0005:
            if (r1 >= r0) goto L_0x0074
            char r2 = r7.charAt(r1)
            r3 = 0
            r4 = 0
        L_0x000d:
            r5 = 13
            if (r4 >= r5) goto L_0x0023
            boolean r6 = isDigit(r2)
            if (r6 == 0) goto L_0x0023
            int r4 = r4 + 1
            int r6 = r1 + r4
            if (r6 < r0) goto L_0x001e
            goto L_0x0023
        L_0x001e:
            char r2 = r7.charAt(r6)
            goto L_0x000d
        L_0x0023:
            if (r4 < r5) goto L_0x0027
            int r1 = r1 - r9
            return r1
        L_0x0027:
            r4 = 5
            if (r3 >= r4) goto L_0x003c
            boolean r2 = isText(r2)
            if (r2 == 0) goto L_0x003c
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 < r0) goto L_0x0037
            goto L_0x003c
        L_0x0037:
            char r2 = r7.charAt(r2)
            goto L_0x0027
        L_0x003c:
            if (r3 < r4) goto L_0x0040
            int r1 = r1 - r9
            return r1
        L_0x0040:
            char r2 = r7.charAt(r1)
            byte r3 = r8[r1]
            r4 = 63
            if (r3 != r4) goto L_0x0071
            if (r2 != r4) goto L_0x004d
            goto L_0x0071
        L_0x004d:
            com.google.zxing.WriterException r7 = new com.google.zxing.WriterException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Non-encodable character detected: "
            r8.append(r9)
            r8.append(r2)
            java.lang.String r9 = " (Unicode: "
            r8.append(r9)
            r8.append(r2)
            r9 = 41
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>((java.lang.String) r8)
            throw r7
        L_0x0071:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0074:
            int r1 = r1 - r9
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.determineConsecutiveBinaryCount(java.lang.CharSequence, byte[], int):int");
    }
}
