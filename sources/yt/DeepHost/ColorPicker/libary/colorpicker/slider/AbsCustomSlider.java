package yt.DeepHost.ColorPicker.libary.colorpicker.slider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import yt.DeepHost.ColorPicker.libary.colorpicker.layout.design_size;

public abstract class AbsCustomSlider extends View {
    protected Bitmap bar;
    protected Canvas barCanvas;
    protected int barHeight = 5;
    protected int barOffsetX;
    protected Bitmap bitmap;
    protected Canvas bitmapCanvas;
    protected int handleRadius = 20;
    private boolean inVerticalOrientation = false;
    protected OnValueChangedListener onValueChangedListener;
    protected boolean showBorder = false;
    public design_size size;
    protected float value = 1.0f;

    /* access modifiers changed from: protected */
    public abstract void drawBar(Canvas canvas);

    /* access modifiers changed from: protected */
    public abstract void drawHandle(Canvas canvas, float f, float f2);

    /* access modifiers changed from: protected */
    public abstract void onValueChanged(float f);

    public AbsCustomSlider(Context context) {
        super(context);
        this.size = new design_size(context);
        init(context, (AttributeSet) null);
    }

    public AbsCustomSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AbsCustomSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.inVerticalOrientation = this.inVerticalOrientation;
    }

    /* access modifiers changed from: protected */
    public void updateBar() {
        this.handleRadius = this.size.getPixels(10);
        this.barHeight = this.size.getPixels(4);
        this.barOffsetX = this.handleRadius;
        if (this.bar == null) {
            createBitmaps();
        }
        drawBar(this.barCanvas);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void createBitmaps() {
        int i;
        int i2;
        if (this.inVerticalOrientation) {
            i2 = getHeight();
            i = getWidth();
        } else {
            i2 = getWidth();
            i = getHeight();
        }
        this.bar = Bitmap.createBitmap(Math.max(i2 - (this.barOffsetX * 2), 1), this.barHeight, Bitmap.Config.ARGB_8888);
        this.barCanvas = new Canvas(this.bar);
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 == null || bitmap2.getWidth() != i2 || this.bitmap.getHeight() != i) {
            Bitmap bitmap3 = this.bitmap;
            if (bitmap3 != null) {
                bitmap3.recycle();
            }
            this.bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
            this.bitmapCanvas = new Canvas(this.bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        Canvas canvas2;
        super.onDraw(canvas);
        if (this.inVerticalOrientation) {
            i2 = getHeight();
            i = getWidth();
            canvas.rotate(-90.0f);
            canvas.translate((float) (-i2), 0.0f);
        } else {
            i2 = getWidth();
            i = getHeight();
        }
        if (this.bar != null && (canvas2 = this.bitmapCanvas) != null) {
            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
            Canvas canvas3 = this.bitmapCanvas;
            Bitmap bitmap2 = this.bar;
            canvas3.drawBitmap(bitmap2, (float) this.barOffsetX, (float) ((i - bitmap2.getHeight()) / 2), (Paint) null);
            int i3 = this.handleRadius;
            drawHandle(this.bitmapCanvas, ((float) i3) + (this.value * ((float) (i2 - (i3 * 2)))), ((float) i) / 2.0f);
            canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBar();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != 0) {
            if (mode == Integer.MIN_VALUE) {
                i = View.MeasureSpec.getSize(i);
            } else {
                i = mode == 1073741824 ? View.MeasureSpec.getSize(i) : 0;
            }
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 != 0) {
            if (mode2 == Integer.MIN_VALUE) {
                i2 = View.MeasureSpec.getSize(i2);
            } else {
                i2 = mode2 == 1073741824 ? View.MeasureSpec.getSize(i2) : 0;
            }
        }
        setMeasuredDimension(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r0 != 2) goto L_0x0063;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x001f
            if (r0 == r1) goto L_0x000d
            r2 = 2
            if (r0 == r2) goto L_0x001f
            goto L_0x0063
        L_0x000d:
            float r4 = r3.value
            r3.onValueChanged(r4)
            yt.DeepHost.ColorPicker.libary.colorpicker.slider.OnValueChangedListener r4 = r3.onValueChangedListener
            if (r4 == 0) goto L_0x001b
            float r0 = r3.value
            r4.onValueChanged(r0)
        L_0x001b:
            r3.invalidate()
            goto L_0x0063
        L_0x001f:
            android.graphics.Bitmap r0 = r3.bar
            if (r0 == 0) goto L_0x0063
            boolean r0 = r3.inVerticalOrientation
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x003e
            float r4 = r4.getY()
            int r0 = r3.barOffsetX
            float r0 = (float) r0
            float r4 = r4 - r0
            android.graphics.Bitmap r0 = r3.bar
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r4 = r4 / r0
            float r4 = r2 - r4
            r3.value = r4
            goto L_0x0050
        L_0x003e:
            float r4 = r4.getX()
            int r0 = r3.barOffsetX
            float r0 = (float) r0
            float r4 = r4 - r0
            android.graphics.Bitmap r0 = r3.bar
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r4 = r4 / r0
            r3.value = r4
        L_0x0050:
            r4 = 0
            float r0 = r3.value
            float r0 = java.lang.Math.min(r0, r2)
            float r4 = java.lang.Math.max(r4, r0)
            r3.value = r4
            r3.onValueChanged(r4)
            r3.invalidate()
        L_0x0063:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: yt.DeepHost.ColorPicker.libary.colorpicker.slider.AbsCustomSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public int getDimension(int i) {
        return getResources().getDimensionPixelSize(i);
    }

    public void setShowBorder(boolean z) {
        this.showBorder = z;
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener2) {
        this.onValueChangedListener = onValueChangedListener2;
    }
}
