package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import java.math.BigInteger;
import java.util.List;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', 13, 9, ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = {';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', '!', 13, 9, ',', ':', 10, '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        bigIntegerArr[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] iArr) throws FormatException {
        int i;
        StringBuilder sb = new StringBuilder(100);
        int i2 = iArr[1];
        int i3 = 2;
        while (i3 < iArr[0]) {
            if (i2 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                i = byteCompaction(i2, iArr, i3, sb);
            } else if (i2 != BYTE_COMPACTION_MODE_LATCH_6) {
                switch (i2) {
                    case TEXT_COMPACTION_MODE_LATCH /*900*/:
                        i = textCompaction(iArr, i3, sb);
                        break;
                    case 901:
                        i = byteCompaction(i2, iArr, i3, sb);
                        break;
                    case 902:
                        i = numericCompaction(iArr, i3, sb);
                        break;
                    default:
                        i = textCompaction(iArr, i3 - 1, sb);
                        break;
                }
            } else {
                i = byteCompaction(i2, iArr, i3, sb);
            }
            if (i < iArr.length) {
                i3 = i + 1;
                i2 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            return new DecoderResult((byte[]) null, sb.toString(), (List<byte[]>) null, (String) null);
        }
        throw FormatException.getFormatInstance();
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int i2 = iArr[0];
        int[] iArr2 = new int[(i2 << 1)];
        int[] iArr3 = new int[(i2 << 1)];
        boolean z = false;
        int i3 = 0;
        while (i < iArr[0] && !z) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i5 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i5 != BYTE_COMPACTION_MODE_LATCH_6) {
                    switch (i5) {
                        case TEXT_COMPACTION_MODE_LATCH /*900*/:
                            iArr2[i3] = TEXT_COMPACTION_MODE_LATCH;
                            i3++;
                            break;
                        case 901:
                        case 902:
                            break;
                    }
                }
                i = i4 - 1;
                z = true;
            } else {
                iArr2[i3] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i4 + 1;
                iArr3[i3] = iArr[i4];
                i3++;
            }
            i = i4;
        }
        decodeTextCompaction(iArr2, iArr3, i3, sb);
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b7, code lost:
        r10 = 0;
        r15 = r2;
        r2 = r1;
        r1 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d8, code lost:
        r10 = (char) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00fa, code lost:
        if (r10 == 0) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00fc, code lost:
        r0.append(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00ff, code lost:
        r5 = r5 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
            r0 = r19
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r3 = 0
            r4 = r18
            r5 = 0
        L_0x000a:
            if (r5 >= r4) goto L_0x0103
            r6 = r16[r5]
            int[] r7 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode
            int r8 = r1.ordinal()
            r7 = r7[r8]
            r8 = 28
            r9 = 27
            r10 = 32
            r11 = 913(0x391, float:1.28E-42)
            r12 = 900(0x384, float:1.261E-42)
            r13 = 29
            r14 = 26
            switch(r7) {
                case 1: goto L_0x00d4;
                case 2: goto L_0x00ab;
                case 3: goto L_0x0077;
                case 4: goto L_0x0059;
                case 5: goto L_0x0045;
                case 6: goto L_0x0029;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x00f9
        L_0x0029:
            if (r6 >= r13) goto L_0x0030
            char[] r1 = PUNCT_CHARS
            char r10 = r1[r6]
            goto L_0x004a
        L_0x0030:
            if (r6 != r13) goto L_0x0036
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0036:
            if (r6 != r11) goto L_0x003f
            r1 = r17[r5]
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0056
        L_0x003f:
            if (r6 != r12) goto L_0x0056
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0045:
            if (r6 >= r14) goto L_0x004d
            int r6 = r6 + 65
            char r10 = (char) r6
        L_0x004a:
            r1 = r2
            goto L_0x00fa
        L_0x004d:
            if (r6 != r14) goto L_0x0050
            goto L_0x004a
        L_0x0050:
            if (r6 != r12) goto L_0x0056
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0056:
            r1 = r2
            goto L_0x00f9
        L_0x0059:
            if (r6 >= r13) goto L_0x0061
            char[] r7 = PUNCT_CHARS
            char r10 = r7[r6]
            goto L_0x00fa
        L_0x0061:
            if (r6 != r13) goto L_0x0067
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0067:
            if (r6 != r11) goto L_0x0071
            r6 = r17[r5]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00f9
        L_0x0071:
            if (r6 != r12) goto L_0x00f9
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0077:
            r7 = 25
            if (r6 >= r7) goto L_0x0081
            char[] r7 = MIXED_CHARS
            char r10 = r7[r6]
            goto L_0x00fa
        L_0x0081:
            if (r6 != r7) goto L_0x0087
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            goto L_0x00f9
        L_0x0087:
            if (r6 != r14) goto L_0x008b
            goto L_0x00fa
        L_0x008b:
            if (r6 != r9) goto L_0x0091
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00f9
        L_0x0091:
            if (r6 != r8) goto L_0x0097
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x0097:
            if (r6 != r13) goto L_0x009c
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b7
        L_0x009c:
            if (r6 != r11) goto L_0x00a6
            r6 = r17[r5]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00f9
        L_0x00a6:
            if (r6 != r12) goto L_0x00f9
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x00ab:
            if (r6 >= r14) goto L_0x00b0
            int r6 = r6 + 97
            goto L_0x00d8
        L_0x00b0:
            if (r6 != r14) goto L_0x00b3
            goto L_0x00fa
        L_0x00b3:
            if (r6 != r9) goto L_0x00bc
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
        L_0x00b7:
            r10 = 0
            r15 = r2
            r2 = r1
            r1 = r15
            goto L_0x00fa
        L_0x00bc:
            if (r6 != r8) goto L_0x00c1
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00f9
        L_0x00c1:
            if (r6 != r13) goto L_0x00c6
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b7
        L_0x00c6:
            if (r6 != r11) goto L_0x00cf
            r6 = r17[r5]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00f9
        L_0x00cf:
            if (r6 != r12) goto L_0x00f9
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00f9
        L_0x00d4:
            if (r6 >= r14) goto L_0x00da
            int r6 = r6 + 65
        L_0x00d8:
            char r10 = (char) r6
            goto L_0x00fa
        L_0x00da:
            if (r6 != r14) goto L_0x00dd
            goto L_0x00fa
        L_0x00dd:
            if (r6 != r9) goto L_0x00e2
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00f9
        L_0x00e2:
            if (r6 != r8) goto L_0x00e7
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00f9
        L_0x00e7:
            if (r6 != r13) goto L_0x00ec
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00b7
        L_0x00ec:
            if (r6 != r11) goto L_0x00f5
            r6 = r17[r5]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00f9
        L_0x00f5:
            if (r6 != r12) goto L_0x00f9
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
        L_0x00f9:
            r10 = 0
        L_0x00fa:
            if (r10 == 0) goto L_0x00ff
            r0.append(r10)
        L_0x00ff:
            int r5 = r5 + 1
            goto L_0x000a
        L_0x0103:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = r0
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r2 >= r14) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        r0[5 - r2] = (char) ((int) (r17 % 256));
        r17 = r17 >> 8;
        r2 = r2 + 1;
        r15 = r15;
        r14 = 6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int byteCompaction(int r24, int[] r25, int r26, java.lang.StringBuilder r27) {
        /*
            r0 = r24
            r1 = r27
            r3 = 922(0x39a, float:1.292E-42)
            r4 = 923(0x39b, float:1.293E-42)
            r5 = 928(0x3a0, float:1.3E-42)
            r6 = 902(0x386, float:1.264E-42)
            r7 = 900(0x384, double:4.447E-321)
            r11 = 924(0x39c, float:1.295E-42)
            r12 = 901(0x385, float:1.263E-42)
            r13 = 900(0x384, float:1.261E-42)
            r14 = 6
            r16 = 0
            if (r0 != r12) goto L_0x00a7
            char[] r0 = new char[r14]
            int[] r9 = new int[r14]
            int r10 = r26 + 1
            r19 = r25[r26]
            r15 = r19
            r17 = 0
            r20 = 0
        L_0x0027:
            r21 = 0
        L_0x0029:
            r2 = r25[r16]
            if (r10 >= r2) goto L_0x008d
            if (r20 != 0) goto L_0x008d
            int r2 = r21 + 1
            r9[r21] = r15
            long r17 = r17 * r7
            long r7 = (long) r15
            long r17 = r17 + r7
            int r7 = r10 + 1
            r15 = r25[r10]
            if (r15 == r13) goto L_0x007f
            if (r15 == r12) goto L_0x007f
            if (r15 == r6) goto L_0x007f
            if (r15 == r11) goto L_0x007f
            if (r15 == r5) goto L_0x007f
            if (r15 == r4) goto L_0x007f
            if (r15 != r3) goto L_0x004b
            goto L_0x007f
        L_0x004b:
            int r8 = r2 % 5
            if (r8 != 0) goto L_0x0074
            if (r2 <= 0) goto L_0x0074
            r2 = 0
        L_0x0052:
            if (r2 >= r14) goto L_0x006a
            int r8 = 5 - r2
            r22 = 256(0x100, double:1.265E-321)
            r24 = r15
            long r14 = r17 % r22
            int r10 = (int) r14
            char r10 = (char) r10
            r0[r8] = r10
            r8 = 8
            long r17 = r17 >> r8
            int r2 = r2 + 1
            r15 = r24
            r14 = 6
            goto L_0x0052
        L_0x006a:
            r24 = r15
            r1.append(r0)
            r10 = r7
            r7 = 900(0x384, double:4.447E-321)
            r14 = 6
            goto L_0x0027
        L_0x0074:
            r24 = r15
            r15 = r24
            r21 = r2
            r10 = r7
            r7 = 900(0x384, double:4.447E-321)
            r14 = 6
            goto L_0x0029
        L_0x007f:
            r24 = r15
            int r10 = r7 + -1
            r15 = r24
            r21 = r2
            r7 = 900(0x384, double:4.447E-321)
            r14 = 6
            r20 = 1
            goto L_0x0029
        L_0x008d:
            if (r10 != r2) goto L_0x0096
            if (r15 >= r13) goto L_0x0096
            int r0 = r21 + 1
            r9[r21] = r15
            goto L_0x0098
        L_0x0096:
            r0 = r21
        L_0x0098:
            r2 = 0
        L_0x0099:
            if (r2 >= r0) goto L_0x00a4
            r3 = r9[r2]
            char r3 = (char) r3
            r1.append(r3)
            int r2 = r2 + 1
            goto L_0x0099
        L_0x00a4:
            r0 = r10
            goto L_0x0108
        L_0x00a7:
            if (r0 != r11) goto L_0x0106
            r0 = r26
            r2 = 0
            r7 = 0
            r9 = 0
        L_0x00af:
            r8 = r25[r16]
            if (r0 >= r8) goto L_0x0108
            if (r2 != 0) goto L_0x0108
            int r8 = r0 + 1
            r0 = r25[r0]
            if (r0 >= r13) goto L_0x00c5
            int r7 = r7 + 1
            r14 = 900(0x384, double:4.447E-321)
            long r9 = r9 * r14
            long r14 = (long) r0
            long r9 = r9 + r14
        L_0x00c3:
            r0 = r8
            goto L_0x00d7
        L_0x00c5:
            if (r0 == r13) goto L_0x00d3
            if (r0 == r12) goto L_0x00d3
            if (r0 == r6) goto L_0x00d3
            if (r0 == r11) goto L_0x00d3
            if (r0 == r5) goto L_0x00d3
            if (r0 == r4) goto L_0x00d3
            if (r0 != r3) goto L_0x00c3
        L_0x00d3:
            int r8 = r8 + -1
            r0 = r8
            r2 = 1
        L_0x00d7:
            int r8 = r7 % 5
            if (r8 != 0) goto L_0x00fe
            if (r7 <= 0) goto L_0x00fe
            r8 = 6
            char[] r7 = new char[r8]
            r14 = 0
        L_0x00e1:
            if (r14 >= r8) goto L_0x00f7
            int r15 = 5 - r14
            r17 = 255(0xff, double:1.26E-321)
            long r3 = r9 & r17
            int r4 = (int) r3
            char r3 = (char) r4
            r7[r15] = r3
            r3 = 8
            long r9 = r9 >> r3
            int r14 = r14 + 1
            r3 = 922(0x39a, float:1.292E-42)
            r4 = 923(0x39b, float:1.293E-42)
            goto L_0x00e1
        L_0x00f7:
            r3 = 8
            r1.append(r7)
            r7 = 0
            goto L_0x0101
        L_0x00fe:
            r3 = 8
            r8 = 6
        L_0x0101:
            r3 = 922(0x39a, float:1.292E-42)
            r4 = 923(0x39b, float:1.293E-42)
            goto L_0x00af
        L_0x0106:
            r0 = r26
        L_0x0108:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], int, java.lang.StringBuilder):int");
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (true) {
            int i3 = iArr[0];
            if (i >= i3 || z) {
                return i;
            }
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i4 == i3) {
                z = true;
            }
            if (i5 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i5;
                i2++;
            } else if (i5 == TEXT_COMPACTION_MODE_LATCH || i5 == 901 || i5 == BYTE_COMPACTION_MODE_LATCH_6 || i5 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i5 == MACRO_PDF417_TERMINATOR) {
                i4--;
                z = true;
            }
            if (i2 % 15 == 0 || i5 == 902 || z) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i4;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
