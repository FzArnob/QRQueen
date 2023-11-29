package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;

public final class Decoder {
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private final ErrorCorrection errorCorrection = new ErrorCorrection();

    public DecoderResult decode(boolean[][] zArr) throws FormatException, ChecksumException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i2][i]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix);
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws FormatException, ChecksumException {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        int[] readCodewords = bitMatrixParser.readCodewords();
        if (readCodewords.length != 0) {
            int eCLevel = 1 << (bitMatrixParser.getECLevel() + 1);
            correctErrors(readCodewords, bitMatrixParser.getErasures(), eCLevel);
            verifyCodewordCount(readCodewords, eCLevel);
            return DecodedBitStreamParser.decode(readCodewords);
        }
        throw FormatException.getFormatInstance();
    }

    private static void verifyCodewordCount(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (i2 != 0) {
            } else {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private void correctErrors(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if (iArr2.length > (i / 2) + 3 || i < 0 || i > 512) {
            throw ChecksumException.getChecksumInstance();
        }
        this.errorCorrection.decode(iArr, i, iArr2);
    }
}
