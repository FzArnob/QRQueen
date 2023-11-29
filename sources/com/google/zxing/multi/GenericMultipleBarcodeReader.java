package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i, int i2) {
        boolean z;
        float f;
        float f2;
        BinaryBitmap binaryBitmap2 = binaryBitmap;
        int i3 = i;
        int i4 = i2;
        try {
            Result decode = this.delegate.decode(binaryBitmap2, map);
            Iterator<Result> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getText().equals(decode.getText())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                list.add(translateResultPoints(decode, i3, i4));
                ResultPoint[] resultPoints = decode.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    int width = binaryBitmap.getWidth();
                    int height = binaryBitmap.getHeight();
                    float f3 = (float) width;
                    float f4 = 0.0f;
                    float f5 = (float) height;
                    float f6 = 0.0f;
                    for (ResultPoint resultPoint : resultPoints) {
                        float x = resultPoint.getX();
                        float y = resultPoint.getY();
                        if (x < f3) {
                            f3 = x;
                        }
                        if (y < f5) {
                            f5 = y;
                        }
                        if (x > f6) {
                            f6 = x;
                        }
                        if (y > f4) {
                            f4 = y;
                        }
                    }
                    if (f3 > 100.0f) {
                        f2 = f4;
                        f = f6;
                        doDecodeMultiple(binaryBitmap2.crop(0, 0, (int) f3, height), map, list, i, i2);
                    } else {
                        f2 = f4;
                        f = f6;
                    }
                    if (f5 > 100.0f) {
                        doDecodeMultiple(binaryBitmap2.crop(0, 0, width, (int) f5), map, list, i, i2);
                    }
                    float f7 = f;
                    if (f7 < ((float) (width - 100))) {
                        int i5 = (int) f7;
                        doDecodeMultiple(binaryBitmap2.crop(i5, 0, width - i5, height), map, list, i3 + i5, i2);
                    }
                    float f8 = f2;
                    if (f8 < ((float) (height - 100))) {
                        int i6 = (int) f8;
                        doDecodeMultiple(binaryBitmap2.crop(0, i6, width, height - i6), map, list, i, i4 + i6);
                    }
                }
            }
        } catch (ReaderException unused) {
        }
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
        }
        return new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
    }
}
