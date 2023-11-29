package com.google.zxing.client.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 80;
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, 255, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, 128, 64};
    private CameraManager cameraManager;
    private final int laserColor = -3407872;
    private List<ResultPoint> lastPossibleResultPoints = null;
    private final int maskColor = 1610612736;
    private final Paint paint = new Paint(1);
    private List<ResultPoint> possibleResultPoints = new ArrayList(5);
    private Bitmap resultBitmap;
    private final int resultColor = -1;
    private final int resultPointColor = -1063662592;
    private int scannerAlpha = 0;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCameraManager(CameraManager cameraManager2) {
        this.cameraManager = cameraManager2;
    }

    public void onDraw(Canvas canvas) {
        Rect framingRect;
        CameraManager cameraManager2 = this.cameraManager;
        if (cameraManager2 != null && (framingRect = cameraManager2.getFramingRect()) != null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
            float f = (float) width;
            canvas.drawRect(0.0f, 0.0f, f, (float) framingRect.top, this.paint);
            canvas.drawRect(0.0f, (float) framingRect.top, (float) framingRect.left, (float) (framingRect.bottom + 1), this.paint);
            float f2 = f;
            canvas.drawRect((float) (framingRect.right + 1), (float) framingRect.top, f2, (float) (framingRect.bottom + 1), this.paint);
            canvas.drawRect(0.0f, (float) (framingRect.bottom + 1), f2, (float) height, this.paint);
            this.paint.setColor(-1);
            canvas.drawRect((float) (framingRect.left - 20), (float) (framingRect.top - 20), (float) framingRect.left, (float) (framingRect.top + 60), this.paint);
            canvas.drawRect((float) framingRect.left, (float) (framingRect.top - 20), (float) (framingRect.left + 60), (float) framingRect.top, this.paint);
            canvas.drawRect((float) framingRect.right, (float) (framingRect.top - 20), (float) (framingRect.right + 20), (float) (framingRect.top + 60), this.paint);
            canvas.drawRect((float) (framingRect.right - 60), (float) (framingRect.top - 20), (float) framingRect.right, (float) framingRect.top, this.paint);
            canvas.drawRect((float) (framingRect.left - 20), (float) (framingRect.bottom - 60), (float) framingRect.left, (float) (framingRect.bottom + 20), this.paint);
            canvas.drawRect((float) framingRect.left, (float) framingRect.bottom, (float) (framingRect.left + 60), (float) (framingRect.bottom + 20), this.paint);
            canvas.drawRect((float) framingRect.right, (float) (framingRect.bottom - 60), (float) (framingRect.right + 20), (float) (framingRect.bottom + 20), this.paint);
            canvas.drawRect((float) (framingRect.right - 60), (float) framingRect.bottom, (float) framingRect.right, (float) (framingRect.bottom + 20), this.paint);
            this.paint.setAlpha(160);
            canvas.drawLine((float) framingRect.left, (float) framingRect.top, (float) framingRect.right, (float) framingRect.top, this.paint);
            canvas.drawLine((float) framingRect.left, (float) framingRect.bottom, (float) framingRect.right, (float) framingRect.bottom, this.paint);
            canvas.drawLine((float) framingRect.left, (float) framingRect.top, (float) framingRect.left, (float) framingRect.bottom, this.paint);
            canvas.drawLine((float) framingRect.right, (float) framingRect.top, (float) framingRect.right, (float) framingRect.bottom, this.paint);
            if (this.resultBitmap != null) {
                this.paint.setAlpha(160);
                canvas.drawBitmap(this.resultBitmap, (Rect) null, framingRect, this.paint);
                return;
            }
            this.paint.setColor(this.laserColor);
            Paint paint2 = this.paint;
            int[] iArr = SCANNER_ALPHA;
            paint2.setAlpha(iArr[this.scannerAlpha]);
            this.scannerAlpha = (this.scannerAlpha + 1) % iArr.length;
            int height2 = (framingRect.height() / 2) + framingRect.top;
            canvas.drawRect((float) (framingRect.left + 2), (float) (height2 - 1), (float) (framingRect.right - 1), (float) (height2 + 2), this.paint);
            Rect framingRectInPreview = this.cameraManager.getFramingRectInPreview();
            float width2 = ((float) framingRect.width()) / ((float) framingRectInPreview.width());
            float height3 = ((float) framingRect.height()) / ((float) framingRectInPreview.height());
            List<ResultPoint> list = this.possibleResultPoints;
            List<ResultPoint> list2 = this.lastPossibleResultPoints;
            int i = framingRect.left;
            int i2 = framingRect.top;
            if (list.isEmpty()) {
                this.lastPossibleResultPoints = null;
            } else {
                this.possibleResultPoints = new ArrayList(5);
                this.lastPossibleResultPoints = list;
                this.paint.setAlpha(160);
                this.paint.setColor(this.resultPointColor);
                synchronized (list) {
                    for (ResultPoint next : list) {
                        canvas.drawCircle((float) (((int) (next.getX() * width2)) + i), (float) (((int) (next.getY() * height3)) + i2), 6.0f, this.paint);
                    }
                }
            }
            if (list2 != null) {
                this.paint.setAlpha(80);
                this.paint.setColor(this.resultPointColor);
                synchronized (list2) {
                    for (ResultPoint next2 : list2) {
                        canvas.drawCircle((float) (((int) (next2.getX() * width2)) + i), (float) (((int) (next2.getY() * height3)) + i2), 3.0f, this.paint);
                    }
                }
            }
            postInvalidateDelayed(ANIMATION_DELAY, framingRect.left - 6, framingRect.top - 6, framingRect.right + 6, framingRect.bottom + 6);
        }
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        List<ResultPoint> list = this.possibleResultPoints;
        synchronized (list) {
            list.add(resultPoint);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }
}
