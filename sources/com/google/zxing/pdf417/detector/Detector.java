package com.google.zxing.pdf417.detector;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import java.util.Arrays;
import java.util.Map;

public final class Detector {
    private static final int INTEGER_MATH_SHIFT = 8;
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 204;
    private static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
    private static final int SKEW_THRESHOLD = 3;
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] START_PATTERN_REVERSE = {3, 1, 1, 1, 1, 1, 1, 8};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};
    private static final int[] STOP_PATTERN_REVERSE = {1, 2, 1, 1, 1, 3, 1, 1, 7};
    private final BinaryBitmap image;

    public Detector(BinaryBitmap binaryBitmap) {
        this.image = binaryBitmap;
    }

    public DetectorResult detect() throws NotFoundException {
        return detect((Map<DecodeHintType, ?>) null);
    }

    public DetectorResult detect(Map<DecodeHintType, ?> map) throws NotFoundException {
        BitMatrix blackMatrix = this.image.getBlackMatrix();
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        ResultPoint[] findVertices = findVertices(blackMatrix, z);
        if (findVertices == null) {
            findVertices = findVertices180(blackMatrix, z);
            if (findVertices != null) {
                correctCodeWordVertices(findVertices, true);
            }
        } else {
            correctCodeWordVertices(findVertices, false);
        }
        ResultPoint[] resultPointArr = findVertices;
        if (resultPointArr != null) {
            float computeModuleWidth = computeModuleWidth(resultPointArr);
            if (computeModuleWidth >= 1.0f) {
                int computeDimension = computeDimension(resultPointArr[4], resultPointArr[6], resultPointArr[5], resultPointArr[7], computeModuleWidth);
                if (computeDimension >= 1) {
                    int computeYDimension = computeYDimension(resultPointArr[4], resultPointArr[6], resultPointArr[5], resultPointArr[7], computeModuleWidth);
                    return new DetectorResult(sampleGrid(blackMatrix, resultPointArr[4], resultPointArr[5], resultPointArr[6], resultPointArr[7], computeDimension, computeYDimension > computeDimension ? computeYDimension : computeDimension), new ResultPoint[]{resultPointArr[5], resultPointArr[4], resultPointArr[6], resultPointArr[7]});
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, boolean z) {
        boolean z2;
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        int[] iArr = new int[START_PATTERN.length];
        boolean z3 = true;
        int max = Math.max(1, height >> (z ? 9 : 7));
        int i = 0;
        while (true) {
            if (i >= height) {
                z2 = false;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, 0, i, width, false, START_PATTERN, iArr);
            if (findGuardPattern != null) {
                float f = (float) i;
                resultPointArr[0] = new ResultPoint((float) findGuardPattern[0], f);
                resultPointArr[4] = new ResultPoint((float) findGuardPattern[1], f);
                z2 = true;
                break;
            }
            i += max;
        }
        if (z2) {
            int i2 = height - 1;
            while (true) {
                if (i2 <= 0) {
                    z2 = false;
                    break;
                }
                int[] findGuardPattern2 = findGuardPattern(bitMatrix, 0, i2, width, false, START_PATTERN, iArr);
                if (findGuardPattern2 != null) {
                    float f2 = (float) i2;
                    resultPointArr[1] = new ResultPoint((float) findGuardPattern2[0], f2);
                    resultPointArr[5] = new ResultPoint((float) findGuardPattern2[1], f2);
                    z2 = true;
                    break;
                }
                i2 -= max;
            }
        }
        int[] iArr2 = new int[STOP_PATTERN.length];
        if (z2) {
            int i3 = 0;
            while (true) {
                if (i3 >= height) {
                    z2 = false;
                    break;
                }
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, 0, i3, width, false, STOP_PATTERN, iArr2);
                if (findGuardPattern3 != null) {
                    float f3 = (float) i3;
                    resultPointArr[2] = new ResultPoint((float) findGuardPattern3[1], f3);
                    resultPointArr[6] = new ResultPoint((float) findGuardPattern3[0], f3);
                    z2 = true;
                    break;
                }
                i3 += max;
            }
        }
        if (z2) {
            int i4 = height - 1;
            while (true) {
                if (i4 <= 0) {
                    z3 = false;
                    break;
                }
                int[] findGuardPattern4 = findGuardPattern(bitMatrix, 0, i4, width, false, STOP_PATTERN, iArr2);
                if (findGuardPattern4 != null) {
                    float f4 = (float) i4;
                    resultPointArr[3] = new ResultPoint((float) findGuardPattern4[1], f4);
                    resultPointArr[7] = new ResultPoint((float) findGuardPattern4[0], f4);
                    break;
                }
                i4 -= max;
            }
        } else {
            z3 = z2;
        }
        if (z3) {
            return resultPointArr;
        }
        return null;
    }

    private static ResultPoint[] findVertices180(BitMatrix bitMatrix, boolean z) {
        boolean z2;
        int height = bitMatrix.getHeight();
        boolean z3 = true;
        int width = bitMatrix.getWidth() >> 1;
        ResultPoint[] resultPointArr = new ResultPoint[8];
        int[] iArr = new int[START_PATTERN_REVERSE.length];
        int max = Math.max(1, height >> (z ? 9 : 7));
        int i = height - 1;
        int i2 = i;
        while (true) {
            if (i2 <= 0) {
                z2 = false;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, width, i2, width, true, START_PATTERN_REVERSE, iArr);
            if (findGuardPattern != null) {
                float f = (float) i2;
                resultPointArr[0] = new ResultPoint((float) findGuardPattern[1], f);
                resultPointArr[4] = new ResultPoint((float) findGuardPattern[0], f);
                z2 = true;
                break;
            }
            i2 -= max;
        }
        if (z2) {
            int i3 = 0;
            while (true) {
                if (i3 >= height) {
                    z2 = false;
                    break;
                }
                int[] findGuardPattern2 = findGuardPattern(bitMatrix, width, i3, width, true, START_PATTERN_REVERSE, iArr);
                if (findGuardPattern2 != null) {
                    float f2 = (float) i3;
                    resultPointArr[1] = new ResultPoint((float) findGuardPattern2[1], f2);
                    resultPointArr[5] = new ResultPoint((float) findGuardPattern2[0], f2);
                    z2 = true;
                    break;
                }
                i3 += max;
            }
        }
        int[] iArr2 = new int[STOP_PATTERN_REVERSE.length];
        if (z2) {
            while (true) {
                if (i <= 0) {
                    z2 = false;
                    break;
                }
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, 0, i, width, false, STOP_PATTERN_REVERSE, iArr2);
                if (findGuardPattern3 != null) {
                    float f3 = (float) i;
                    resultPointArr[2] = new ResultPoint((float) findGuardPattern3[0], f3);
                    resultPointArr[6] = new ResultPoint((float) findGuardPattern3[1], f3);
                    z2 = true;
                    break;
                }
                i -= max;
            }
        }
        if (z2) {
            int i4 = 0;
            while (true) {
                if (i4 >= height) {
                    z3 = false;
                    break;
                }
                int[] findGuardPattern4 = findGuardPattern(bitMatrix, 0, i4, width, false, STOP_PATTERN_REVERSE, iArr2);
                if (findGuardPattern4 != null) {
                    float f4 = (float) i4;
                    resultPointArr[3] = new ResultPoint((float) findGuardPattern4[0], f4);
                    resultPointArr[7] = new ResultPoint((float) findGuardPattern4[1], f4);
                    break;
                }
                i4 += max;
            }
        } else {
            z3 = z2;
        }
        if (z3) {
            return resultPointArr;
        }
        return null;
    }

    private static void correctCodeWordVertices(ResultPoint[] resultPointArr, boolean z) {
        float x = resultPointArr[0].getX();
        float y = resultPointArr[0].getY();
        float x2 = resultPointArr[2].getX();
        float y2 = resultPointArr[2].getY();
        float x3 = resultPointArr[4].getX();
        float y3 = resultPointArr[4].getY();
        float x4 = resultPointArr[6].getX();
        float y4 = resultPointArr[6].getY();
        float f = y3 - y4;
        if (z) {
            f = -f;
        }
        if (f > 3.0f) {
            float f2 = x4 - x;
            float f3 = y4 - y;
            float f4 = ((x3 - x) * f2) / ((f2 * f2) + (f3 * f3));
            resultPointArr[4] = new ResultPoint(x + (f2 * f4), y + (f4 * f3));
        } else if ((-f) > 3.0f) {
            float f5 = x2 - x3;
            float f6 = y2 - y3;
            float f7 = ((x2 - x4) * f5) / ((f5 * f5) + (f6 * f6));
            resultPointArr[6] = new ResultPoint(x2 - (f5 * f7), y2 - (f7 * f6));
        }
        float x5 = resultPointArr[1].getX();
        float y5 = resultPointArr[1].getY();
        float x6 = resultPointArr[3].getX();
        float y6 = resultPointArr[3].getY();
        float x7 = resultPointArr[5].getX();
        float y7 = resultPointArr[5].getY();
        float x8 = resultPointArr[7].getX();
        float y8 = resultPointArr[7].getY();
        float f8 = y8 - y7;
        if (z) {
            f8 = -f8;
        }
        if (f8 > 3.0f) {
            float f9 = x8 - x5;
            float f10 = y8 - y5;
            float f11 = ((x7 - x5) * f9) / ((f9 * f9) + (f10 * f10));
            resultPointArr[5] = new ResultPoint(x5 + (f9 * f11), y5 + (f11 * f10));
        } else if ((-f8) > 3.0f) {
            float f12 = x6 - x7;
            float f13 = y6 - y7;
            float f14 = ((x6 - x8) * f12) / ((f12 * f12) + (f13 * f13));
            resultPointArr[7] = new ResultPoint(x6 - (f12 * f14), y6 - (f14 * f13));
        }
    }

    private static float computeModuleWidth(ResultPoint[] resultPointArr) {
        return (((ResultPoint.distance(resultPointArr[0], resultPointArr[4]) + ResultPoint.distance(resultPointArr[1], resultPointArr[5])) / 34.0f) + ((ResultPoint.distance(resultPointArr[6], resultPointArr[2]) + ResultPoint.distance(resultPointArr[7], resultPointArr[3])) / 36.0f)) / 2.0f;
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, float f) {
        return ((((MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f) + MathUtils.round(ResultPoint.distance(resultPoint3, resultPoint4) / f)) >> 1) + 8) / 17) * 17;
    }

    private static int computeYDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, float f) {
        return (MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f) + MathUtils.round(ResultPoint.distance(resultPoint2, resultPoint4) / f)) >> 1;
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        float f = (float) i;
        float f2 = (float) i2;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i2, 0.0f, 0.0f, f, 0.0f, f, f2, 0.0f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        Arrays.fill(iArr4, 0, iArr4.length, 0);
        int length = iArr3.length;
        int i4 = i;
        int i5 = i4;
        boolean z2 = z;
        int i6 = 0;
        while (i4 < i + i3) {
            BitMatrix bitMatrix2 = bitMatrix;
            if (bitMatrix.get(i4, i2) ^ z2) {
                iArr4[i6] = iArr4[i6] + 1;
            } else {
                int i7 = length - 1;
                if (i6 != i7) {
                    i6++;
                } else if (patternMatchVariance(iArr4, iArr3, MAX_INDIVIDUAL_VARIANCE) < 107) {
                    return new int[]{i5, i4};
                } else {
                    i5 += iArr4[0] + iArr4[1];
                    int i8 = length - 2;
                    System.arraycopy(iArr4, 2, iArr4, 0, i8);
                    iArr4[i8] = 0;
                    iArr4[i7] = 0;
                    i6--;
                }
                iArr4[i6] = 1;
                z2 = !z2;
            }
            i4++;
        }
        return null;
    }

    private static int patternMatchVariance(int[] iArr, int[] iArr2, int i) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        int i5 = (i2 << 8) / i3;
        int i6 = (i * i5) >> 8;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = iArr[i8] << 8;
            int i10 = iArr2[i8] * i5;
            int i11 = i9 > i10 ? i9 - i10 : i10 - i9;
            if (i11 > i6) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            i7 += i11;
        }
        return i7 / i2;
    }
}
