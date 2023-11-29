package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import java.util.Map;

public final class PDF417Reader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException, ChecksumException {
        return decode(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap).detect();
            DecoderResult decode = this.decoder.decode(detect.getBits());
            resultPointArr = detect.getPoints();
            decoderResult = decode;
        } else {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            resultPointArr = NO_POINTS;
        }
        return new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.PDF_417);
    }

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit == null || bottomRightOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int moduleSize = moduleSize(topLeftOnBit, bitMatrix);
        int i = topLeftOnBit[1];
        int i2 = bottomRightOnBit[1];
        int findPatternStart = findPatternStart(topLeftOnBit[0], i, bitMatrix);
        int findPatternEnd = ((findPatternEnd(topLeftOnBit[0], i, bitMatrix) - findPatternStart) + 1) / moduleSize;
        int i3 = ((i2 - i) + 1) / moduleSize;
        if (findPatternEnd <= 0 || i3 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i4 = moduleSize >> 1;
        int i5 = i + i4;
        int i6 = findPatternStart + i4;
        BitMatrix bitMatrix2 = new BitMatrix(findPatternEnd, i3);
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = (i7 * moduleSize) + i5;
            for (int i9 = 0; i9 < findPatternEnd; i9++) {
                if (bitMatrix.get((i9 * moduleSize) + i6, i8)) {
                    bitMatrix2.set(i9, i7);
                }
            }
        }
        return bitMatrix2;
    }

    private static int moduleSize(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int i = iArr[0];
        int i2 = iArr[1];
        int width = bitMatrix.getWidth();
        while (i < width && bitMatrix.get(i, i2)) {
            i++;
        }
        if (i != width) {
            int i3 = (i - iArr[0]) >>> 3;
            if (i3 != 0) {
                return i3;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int findPatternStart(int r6, int r7, com.google.zxing.common.BitMatrix r8) throws com.google.zxing.NotFoundException {
        /*
            int r0 = r8.getWidth()
            r1 = 1
            r2 = 0
            r3 = 1
        L_0x0007:
            int r4 = r0 + -1
            if (r6 >= r4) goto L_0x001b
            r5 = 8
            if (r2 >= r5) goto L_0x001b
            int r6 = r6 + 1
            boolean r4 = r8.get(r6, r7)
            if (r3 == r4) goto L_0x0019
            int r2 = r2 + 1
        L_0x0019:
            r3 = r4
            goto L_0x0007
        L_0x001b:
            if (r6 == r4) goto L_0x001e
            return r6
        L_0x001e:
            com.google.zxing.NotFoundException r6 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.PDF417Reader.findPatternStart(int, int, com.google.zxing.common.BitMatrix):int");
    }

    private static int findPatternEnd(int i, int i2, BitMatrix bitMatrix) throws NotFoundException {
        boolean z = true;
        int width = bitMatrix.getWidth() - 1;
        while (width > i && !bitMatrix.get(width, i2)) {
            width--;
        }
        int i3 = 0;
        while (width > i && i3 < 9) {
            width--;
            boolean z2 = bitMatrix.get(width, i2);
            if (z != z2) {
                i3++;
            }
            z = z2;
        }
        if (width != i) {
            return width;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
