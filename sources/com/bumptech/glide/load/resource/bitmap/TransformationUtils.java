package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

public final class TransformationUtils {
    public static final int PAINT_FLAGS = 6;
    private static final String TAG = "TransformationUtils";

    public static int getExifOrientationDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    private TransformationUtils() {
    }

    public static Bitmap centerCrop(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        float f;
        float f2;
        if (bitmap2 == null) {
            return null;
        }
        if (bitmap2.getWidth() == i && bitmap2.getHeight() == i2) {
            return bitmap2;
        }
        Matrix matrix = new Matrix();
        float f3 = 0.0f;
        if (bitmap2.getWidth() * i2 > bitmap2.getHeight() * i) {
            f2 = ((float) i2) / ((float) bitmap2.getHeight());
            f3 = (((float) i) - (((float) bitmap2.getWidth()) * f2)) * 0.5f;
            f = 0.0f;
        } else {
            f2 = ((float) i) / ((float) bitmap2.getWidth());
            f = (((float) i2) - (((float) bitmap2.getHeight()) * f2)) * 0.5f;
        }
        matrix.setScale(f2, f2);
        matrix.postTranslate((float) ((int) (f3 + 0.5f)), (float) ((int) (f + 0.5f)));
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(i, i2, getSafeConfig(bitmap2));
        }
        setAlpha(bitmap2, bitmap);
        new Canvas(bitmap).drawBitmap(bitmap2, matrix, new Paint(6));
        return bitmap;
    }

    public static Bitmap fitCenter(Bitmap bitmap, BitmapPool bitmapPool, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "requested target size matches input, returning input");
            }
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int width = (int) (((float) bitmap.getWidth()) * min);
        int height = (int) (((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "adjusted target size matches input, returning input");
            }
            return bitmap;
        }
        Bitmap.Config safeConfig = getSafeConfig(bitmap);
        Bitmap bitmap2 = bitmapPool.get(width, height, safeConfig);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(width, height, safeConfig);
        }
        setAlpha(bitmap, bitmap2);
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "request: " + i + EllipticCurveJsonWebKey.X_MEMBER_NAME + i2);
            Log.v(TAG, "toFit:   " + bitmap.getWidth() + EllipticCurveJsonWebKey.X_MEMBER_NAME + bitmap.getHeight());
            Log.v(TAG, "toReuse: " + bitmap2.getWidth() + EllipticCurveJsonWebKey.X_MEMBER_NAME + bitmap2.getHeight());
            StringBuilder sb = new StringBuilder();
            sb.append("minPct:   ");
            sb.append(min);
            Log.v(TAG, sb.toString());
        }
        Canvas canvas = new Canvas(bitmap2);
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        canvas.drawBitmap(bitmap, matrix, new Paint(6));
        return bitmap2;
    }

    public static void setAlpha(Bitmap bitmap, Bitmap bitmap2) {
        if (Build.VERSION.SDK_INT >= 12 && bitmap2 != null) {
            bitmap2.setHasAlpha(bitmap.hasAlpha());
        }
    }

    @Deprecated
    public static int getOrientation(String str) {
        try {
            return getExifOrientationDegrees(new ExifInterface(str).getAttributeInt("Orientation", 0));
        } catch (Exception e) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Unable to get orientation for image with path=" + str, e);
            }
            return 0;
        }
    }

    @Deprecated
    public static Bitmap orientImage(String str, Bitmap bitmap) {
        return rotateImage(bitmap, getOrientation(str));
    }

    public static Bitmap rotateImage(Bitmap bitmap, int i) {
        if (i == 0) {
            return bitmap;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) i);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            if (!Log.isLoggable(TAG, 6)) {
                return bitmap;
            }
            Log.e(TAG, "Exception when trying to orient image", e);
            return bitmap;
        }
    }

    public static Bitmap rotateImageExif(Bitmap bitmap, BitmapPool bitmapPool, int i) {
        Matrix matrix = new Matrix();
        initializeMatrixForRotation(i, matrix);
        if (matrix.isIdentity()) {
            return bitmap;
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        matrix.mapRect(rectF);
        int round = Math.round(rectF.width());
        int round2 = Math.round(rectF.height());
        Bitmap.Config safeConfig = getSafeConfig(bitmap);
        Bitmap bitmap2 = bitmapPool.get(round, round2, safeConfig);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(round, round2, safeConfig);
        }
        matrix.postTranslate(-rectF.left, -rectF.top);
        new Canvas(bitmap2).drawBitmap(bitmap, matrix, new Paint(6));
        return bitmap2;
    }

    private static Bitmap.Config getSafeConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    static void initializeMatrixForRotation(int i, Matrix matrix) {
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix.setRotate(180.0f);
                return;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix.setRotate(90.0f);
                return;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}
