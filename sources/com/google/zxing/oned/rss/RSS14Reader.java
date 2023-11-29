package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i, map));
        bitArray.reverse();
        for (Pair next : this.possibleLeftPairs) {
            if (next.getCount() > 1) {
                for (Pair next2 : this.possibleRightPairs) {
                    if (next2.getCount() > 1 && checkChecksum(next, next2)) {
                        return constructResult(next, next2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            boolean z = false;
            Iterator<Pair> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair next = it.next();
                if (next.getValue() == pair.getValue()) {
                    next.incrementCount();
                    z = true;
                    break;
                }
            }
            if (!z) {
                collection.add(pair);
            }
        }
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + ((long) pair2.getValue()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int charAt = sb.charAt(i2) - '0';
            if ((i2 & 1) == 0) {
                charAt *= 3;
            }
            i += charAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        sb.append(i3);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(sb.toString()), (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int value = pair.getFinderPattern().getValue();
        int value2 = pair2.getFinderPattern().getValue();
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value3 = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (value3 > 72) {
            value3--;
        }
        if (value3 > 8) {
            value3--;
        }
        return checksumPortion == value3;
    }

    private Pair decodePair(BitArray bitArray, boolean z, int i, Map<DecodeHintType, ?> map) {
        ResultPointCallback resultPointCallback;
        try {
            int[] findFinderPattern = findFinderPattern(bitArray, 0, z);
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z, findFinderPattern);
            if (map == null) {
                resultPointCallback = null;
            } else {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            }
            if (resultPointCallback != null) {
                float f = ((float) (findFinderPattern[0] + findFinderPattern[1])) / 2.0f;
                if (z) {
                    f = ((float) (bitArray.getSize() - 1)) - f;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f, (float) i));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        boolean z2 = z;
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            recordPatternInReverse(bitArray2, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray2, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            int i = 0;
            for (int length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        int i3 = z2 ? 16 : 15;
        float count = ((float) count(dataCharacterCounters)) / ((float) i3);
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i4 = 0; i4 < dataCharacterCounters.length; i4++) {
            float f = ((float) dataCharacterCounters[i4]) / count;
            int i5 = (int) (0.5f + f);
            if (i5 < 1) {
                i5 = 1;
            } else if (i5 > 8) {
                i5 = 8;
            }
            int i6 = i4 >> 1;
            if ((i4 & 1) == 0) {
                oddCounts[i6] = i5;
                oddRoundingErrors[i6] = f - ((float) i5);
            } else {
                evenCounts[i6] = i5;
                evenRoundingErrors[i6] = f - ((float) i5);
            }
        }
        adjustOddEvenCounts(z2, i3);
        int i7 = 0;
        int i8 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            int i9 = oddCounts[length2];
            i7 = (i7 * 9) + i9;
            i8 += i9;
        }
        int i10 = 0;
        int i11 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            int i12 = evenCounts[length3];
            i10 = (i10 * 9) + i12;
            i11 += i12;
        }
        int i13 = i7 + (i10 * 3);
        if (z2) {
            if ((i8 & 1) != 0 || i8 > 12 || i8 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i14 = (12 - i8) / 2;
            int i15 = OUTSIDE_ODD_WIDEST[i14];
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i15, false) * OUTSIDE_EVEN_TOTAL_SUBSET[i14]) + RSSUtils.getRSSvalue(evenCounts, 9 - i15, true) + OUTSIDE_GSUM[i14], i13);
        } else if ((i11 & 1) != 0 || i11 > 10 || i11 < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int i16 = (10 - i11) / 2;
            int i17 = INSIDE_ODD_WIDEST[i16];
            return new DataCharacter((RSSUtils.getRSSvalue(evenCounts, 9 - i17, false) * INSIDE_ODD_TOTAL_SUBSET[i16]) + RSSUtils.getRSSvalue(oddCounts, i17, true) + INSIDE_GSUM[i16], i13);
        }
    }

    private int[] findFinderPattern(BitArray bitArray, int i, boolean z) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (z == z2) {
                break;
            }
            i++;
        }
        int i2 = i;
        int i3 = 0;
        while (i < size) {
            if (bitArray.get(i) ^ z2) {
                decodeFinderCounters[i3] = decodeFinderCounters[i3] + 1;
            } else {
                if (i3 != 3) {
                    i3++;
                } else if (isFinderPattern(decodeFinderCounters)) {
                    return new int[]{i2, i};
                } else {
                    i2 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i3--;
                }
                decodeFinderCounters[i3] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        int i2;
        int i3;
        boolean z2 = bitArray.get(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && (bitArray.get(i4) ^ z2)) {
            i4--;
        }
        int i5 = i4 + 1;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = iArr[0] - i5;
        int parseFinderValue = parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        int i6 = iArr[1];
        if (z) {
            i2 = (bitArray.getSize() - 1) - i6;
            i3 = (bitArray.getSize() - 1) - i5;
        } else {
            i2 = i6;
            i3 = i5;
        }
        return new FinderPattern(parseFinderValue, new int[]{i5, iArr[1]}, i3, i2, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        if (r1 < 4) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004d, code lost:
        if (r1 < 4) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
        r10 = false;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0052, code lost:
        r10 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(boolean r10, int r11) throws com.google.zxing.NotFoundException {
        /*
            r9 = this;
            int[] r0 = r9.getOddCounts()
            int r0 = count(r0)
            int[] r1 = r9.getEvenCounts()
            int r1 = count(r1)
            int r2 = r0 + r1
            int r2 = r2 - r11
            r11 = r0 & 1
            r3 = 0
            r4 = 1
            if (r11 != r10) goto L_0x001b
            r11 = 1
            goto L_0x001c
        L_0x001b:
            r11 = 0
        L_0x001c:
            r5 = r1 & 1
            if (r5 != r4) goto L_0x0022
            r5 = 1
            goto L_0x0023
        L_0x0022:
            r5 = 0
        L_0x0023:
            r6 = 4
            if (r10 == 0) goto L_0x0039
            r10 = 12
            if (r0 <= r10) goto L_0x002d
            r7 = 0
            r8 = 1
            goto L_0x0033
        L_0x002d:
            if (r0 >= r6) goto L_0x0031
            r7 = 1
            goto L_0x0032
        L_0x0031:
            r7 = 0
        L_0x0032:
            r8 = 0
        L_0x0033:
            if (r1 <= r10) goto L_0x0036
            goto L_0x004b
        L_0x0036:
            if (r1 >= r6) goto L_0x0052
            goto L_0x004f
        L_0x0039:
            r10 = 11
            if (r0 <= r10) goto L_0x0040
            r7 = 0
            r8 = 1
            goto L_0x0047
        L_0x0040:
            r10 = 5
            if (r0 >= r10) goto L_0x0045
            r7 = 1
            goto L_0x0046
        L_0x0045:
            r7 = 0
        L_0x0046:
            r8 = 0
        L_0x0047:
            r10 = 10
            if (r1 <= r10) goto L_0x004d
        L_0x004b:
            r10 = 1
            goto L_0x0053
        L_0x004d:
            if (r1 >= r6) goto L_0x0052
        L_0x004f:
            r10 = 0
            r3 = 1
            goto L_0x0053
        L_0x0052:
            r10 = 0
        L_0x0053:
            if (r2 != r4) goto L_0x006b
            if (r11 == 0) goto L_0x0061
            if (r5 != 0) goto L_0x005c
            r4 = r7
        L_0x005a:
            r8 = 1
            goto L_0x0096
        L_0x005c:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0061:
            if (r5 == 0) goto L_0x0066
            r4 = r7
        L_0x0064:
            r10 = 1
            goto L_0x0096
        L_0x0066:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x006b:
            r6 = -1
            if (r2 != r6) goto L_0x0082
            if (r11 == 0) goto L_0x0078
            if (r5 != 0) goto L_0x0073
            goto L_0x0096
        L_0x0073:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0078:
            if (r5 == 0) goto L_0x007d
            r4 = r7
            r3 = 1
            goto L_0x0096
        L_0x007d:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0082:
            if (r2 != 0) goto L_0x00e0
            if (r11 == 0) goto L_0x0093
            if (r5 == 0) goto L_0x008e
            if (r0 >= r1) goto L_0x008b
            goto L_0x0064
        L_0x008b:
            r4 = r7
            r3 = 1
            goto L_0x005a
        L_0x008e:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0093:
            if (r5 != 0) goto L_0x00db
            r4 = r7
        L_0x0096:
            if (r4 == 0) goto L_0x00ab
            if (r8 != 0) goto L_0x00a6
            int[] r11 = r9.getOddCounts()
            float[] r0 = r9.getOddRoundingErrors()
            increment(r11, r0)
            goto L_0x00ab
        L_0x00a6:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00ab:
            if (r8 == 0) goto L_0x00b8
            int[] r11 = r9.getOddCounts()
            float[] r0 = r9.getOddRoundingErrors()
            decrement(r11, r0)
        L_0x00b8:
            if (r3 == 0) goto L_0x00cd
            if (r10 != 0) goto L_0x00c8
            int[] r11 = r9.getEvenCounts()
            float[] r0 = r9.getOddRoundingErrors()
            increment(r11, r0)
            goto L_0x00cd
        L_0x00c8:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00cd:
            if (r10 == 0) goto L_0x00da
            int[] r10 = r9.getEvenCounts()
            float[] r11 = r9.getEvenRoundingErrors()
            decrement(r10, r11)
        L_0x00da:
            return
        L_0x00db:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00e0:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.adjustOddEvenCounts(boolean, int):void");
    }
}
