package com.google.zxing.pdf417.encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.lang.reflect.Array;
import java.util.EnumMap;
import java.util.Map;

public final class PDF417Writer implements Writer {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.PDF_417) {
            PDF417 pdf417 = new PDF417();
            if (map != null) {
                if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                    pdf417.setCompact(((Boolean) map.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
                }
                if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                    pdf417.setCompaction((Compaction) map.get(EncodeHintType.PDF417_COMPACTION));
                }
                if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                    Dimensions dimensions = (Dimensions) map.get(EncodeHintType.PDF417_DIMENSIONS);
                    pdf417.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
                }
            }
            return bitMatrixFromEncoder(pdf417, str, i, i2);
        }
        throw new IllegalArgumentException("Can only encode PDF_417, but got " + barcodeFormat);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    @Deprecated
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, boolean z, int i, int i2, int i3, int i4, int i5, int i6, Compaction compaction) throws WriterException {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put(EncodeHintType.PDF417_COMPACT, Boolean.valueOf(z));
        enumMap.put(EncodeHintType.PDF417_COMPACTION, compaction);
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        enumMap.put(EncodeHintType.PDF417_DIMENSIONS, new Dimensions(i3, i4, i5, i6));
        return encode(str, barcodeFormat, i, i2, enumMap);
    }

    private static BitMatrix bitMatrixFromEncoder(PDF417 pdf417, String str, int i, int i2) throws WriterException {
        boolean z;
        pdf417.generateBarcodeLogic(str, 2);
        byte[][] scaledMatrix = pdf417.getBarcodeMatrix().getScaledMatrix(2, 8);
        if ((i2 > i) ^ (scaledMatrix[0].length < scaledMatrix.length)) {
            scaledMatrix = rotateArray(scaledMatrix);
            z = true;
        } else {
            z = false;
        }
        int length = i / scaledMatrix[0].length;
        int length2 = i2 / scaledMatrix.length;
        if (length >= length2) {
            length = length2;
        }
        if (length <= 1) {
            return bitMatrixFrombitArray(scaledMatrix);
        }
        byte[][] scaledMatrix2 = pdf417.getBarcodeMatrix().getScaledMatrix(length * 2, length * 4 * 2);
        if (z) {
            scaledMatrix2 = rotateArray(scaledMatrix2);
        }
        return bitMatrixFrombitArray(scaledMatrix2);
    }

    private static BitMatrix bitMatrixFrombitArray(byte[][] bArr) {
        BitMatrix bitMatrix = new BitMatrix(bArr[0].length + 60, bArr.length + 60);
        bitMatrix.clear();
        int height = bitMatrix.getHeight() - 30;
        int i = 0;
        while (i < bArr.length) {
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                if (bArr[i][i2] == 1) {
                    bitMatrix.set(i2 + 30, height);
                }
            }
            i++;
            height--;
        }
        return bitMatrix;
    }

    private static byte[][] rotateArray(byte[][] bArr) {
        int length = bArr[0].length;
        int[] iArr = new int[2];
        iArr[1] = bArr.length;
        iArr[0] = length;
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, iArr);
        for (int i = 0; i < bArr.length; i++) {
            int length2 = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length2] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
