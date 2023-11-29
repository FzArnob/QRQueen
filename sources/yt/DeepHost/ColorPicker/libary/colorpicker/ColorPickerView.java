package yt.DeepHost.ColorPicker.libary.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;
import yt.DeepHost.ColorPicker.libary.colorpicker.builder.ColorWheelRendererBuilder;
import yt.DeepHost.ColorPicker.libary.colorpicker.builder.PaintBuilder;
import yt.DeepHost.ColorPicker.libary.colorpicker.renderer.ColorWheelRenderOption;
import yt.DeepHost.ColorPicker.libary.colorpicker.renderer.ColorWheelRenderer;
import yt.DeepHost.ColorPicker.libary.colorpicker.slider.AlphaSlider;
import yt.DeepHost.ColorPicker.libary.colorpicker.slider.LightnessSlider;

public class ColorPickerView extends View {
    private static final float STROKE_RATIO = 1.5f;
    private float alpha = 1.0f;
    private Paint alphaPatternPaint = PaintBuilder.newPaint().build();
    private AlphaSlider alphaSlider;
    private int alphaSliderViewId;
    private int backgroundColor = 0;
    private ArrayList<OnColorChangedListener> colorChangedListeners = new ArrayList<>();
    private EditText colorEdit;
    private LinearLayout colorPreview;
    private int colorSelection = 0;
    private TextWatcher colorTextChange = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            try {
                ColorPickerView.this.setColor(Color.parseColor(charSequence.toString()), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private Bitmap colorWheel;
    private Canvas colorWheelCanvas;
    private Paint colorWheelFill = PaintBuilder.newPaint().color(0).build();
    private Bitmap currentColor;
    private Canvas currentColorCanvas;
    private ColorCircle currentColorCircle;
    private int density = 8;
    private Integer initialColor;
    private Integer[] initialColors = {null, null, null, null, null};
    private float lightness = 1.0f;
    private LightnessSlider lightnessSlider;
    private int lightnessSliderViewId;
    private ArrayList<OnColorSelectedListener> listeners = new ArrayList<>();
    private Integer pickerColorEditTextColor;
    private ColorWheelRenderer renderer;
    private Paint selectorStroke = PaintBuilder.newPaint().color(0).build();
    private boolean showBorder;

    public ColorPickerView(Context context) {
        super(context);
        initWith(context, (AttributeSet) null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initWith(context, attributeSet);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initWith(context, attributeSet);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initWith(context, attributeSet);
    }

    private void initWith(Context context, AttributeSet attributeSet) {
        this.density = 10;
        this.initialColor = -1;
        this.pickerColorEditTextColor = -1;
        ColorWheelRenderer renderer2 = ColorWheelRendererBuilder.getRenderer(WHEEL_TYPE.indexOf(0));
        this.alphaSliderViewId = 0;
        this.lightnessSliderViewId = 0;
        setRenderer(renderer2);
        setDensity(this.density);
        setInitialColor(this.initialColor.intValue(), true);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        updateColorWheel();
        this.currentColorCircle = findNearestByColor(this.initialColor.intValue());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.alphaSliderViewId != 0) {
            setAlphaSlider((AlphaSlider) getRootView().findViewById(this.alphaSliderViewId));
        }
        if (this.lightnessSliderViewId != 0) {
            setLightnessSlider((LightnessSlider) getRootView().findViewById(this.lightnessSliderViewId));
        }
        updateColorWheel();
        this.currentColorCircle = findNearestByColor(this.initialColor.intValue());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateColorWheel();
    }

    private void updateColorWheel() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight < measuredWidth) {
            measuredWidth = measuredHeight;
        }
        if (measuredWidth > 0) {
            Bitmap bitmap = this.colorWheel;
            if (bitmap == null || bitmap.getWidth() != measuredWidth) {
                this.colorWheel = Bitmap.createBitmap(measuredWidth, measuredWidth, Bitmap.Config.ARGB_8888);
                this.colorWheelCanvas = new Canvas(this.colorWheel);
                this.alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(26));
            }
            Bitmap bitmap2 = this.currentColor;
            if (bitmap2 == null || bitmap2.getWidth() != measuredWidth) {
                this.currentColor = Bitmap.createBitmap(measuredWidth, measuredWidth, Bitmap.Config.ARGB_8888);
                this.currentColorCanvas = new Canvas(this.currentColor);
            }
            drawColorWheel();
            invalidate();
        }
    }

    private void drawColorWheel() {
        this.colorWheelCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.currentColorCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (this.renderer != null) {
            float width = ((float) this.colorWheelCanvas.getWidth()) / 2.0f;
            int i = this.density;
            float f = (width - 1.5374999f) - (width / ((float) i));
            ColorWheelRenderOption renderOption = this.renderer.getRenderOption();
            renderOption.density = this.density;
            renderOption.maxRadius = f;
            renderOption.cSize = (f / ((float) (i - 1))) / 2.0f;
            renderOption.strokeWidth = 1.5374999f;
            renderOption.alpha = this.alpha;
            renderOption.lightness = this.lightness;
            renderOption.targetCanvas = this.colorWheelCanvas;
            this.renderer.initWith(renderOption);
            this.renderer.draw();
        }
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
        if (i2 < i) {
            i = i2;
        }
        setMeasuredDimension(i, i);
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
            if (r0 == 0) goto L_0x003b
            if (r0 == r1) goto L_0x000d
            r2 = 2
            if (r0 == r2) goto L_0x003b
            goto L_0x0063
        L_0x000d:
            int r4 = r3.getSelectedColor()
            java.util.ArrayList<yt.DeepHost.ColorPicker.libary.colorpicker.OnColorSelectedListener> r0 = r3.listeners
            if (r0 == 0) goto L_0x002e
            java.util.Iterator r0 = r0.iterator()
        L_0x0019:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r0.next()
            yt.DeepHost.ColorPicker.libary.colorpicker.OnColorSelectedListener r2 = (yt.DeepHost.ColorPicker.libary.colorpicker.OnColorSelectedListener) r2
            r2.onColorSelected(r4)     // Catch:{ Exception -> 0x0029 }
            goto L_0x0019
        L_0x0029:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0019
        L_0x002e:
            r3.setColorToSliders(r4)
            r3.setColorText(r4)
            r3.setColorPreviewColor(r4)
            r3.invalidate()
            goto L_0x0063
        L_0x003b:
            int r0 = r3.getSelectedColor()
            float r2 = r4.getX()
            float r4 = r4.getY()
            yt.DeepHost.ColorPicker.libary.colorpicker.ColorCircle r4 = r3.findNearestByPosition(r2, r4)
            r3.currentColorCircle = r4
            int r4 = r3.getSelectedColor()
            r3.callOnColorChangedListeners(r0, r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            r3.initialColor = r0
            r3.setColorToSliders(r4)
            r3.updateColorWheel()
            r3.invalidate()
        L_0x0063:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public void callOnColorChangedListeners(int i, int i2) {
        ArrayList<OnColorChangedListener> arrayList = this.colorChangedListeners;
        if (arrayList != null && i != i2) {
            Iterator<OnColorChangedListener> it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onColorChanged(i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        ColorCircle colorCircle;
        super.onDraw(canvas);
        canvas.drawColor(this.backgroundColor);
        float width = ((((float) canvas.getWidth()) / 1.025f) / ((float) this.density)) / 2.0f;
        if (this.colorWheel != null && (colorCircle = this.currentColorCircle) != null) {
            this.colorWheelFill.setColor(Color.HSVToColor(colorCircle.getHsvWithLightness(this.lightness)));
            this.colorWheelFill.setAlpha((int) (this.alpha * 255.0f));
            float f = 4.0f + width;
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), f, this.alphaPatternPaint);
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), f, this.colorWheelFill);
            this.selectorStroke = PaintBuilder.newPaint().color(-1).style(Paint.Style.STROKE).stroke(0.5f * width).xPerMode(PorterDuff.Mode.CLEAR).build();
            if (this.showBorder) {
                this.colorWheelCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), (this.selectorStroke.getStrokeWidth() / 2.0f) + width, this.selectorStroke);
            }
            canvas.drawBitmap(this.colorWheel, 0.0f, 0.0f, (Paint) null);
            this.currentColorCanvas.drawCircle(this.currentColorCircle.getX(), this.currentColorCircle.getY(), width + (this.selectorStroke.getStrokeWidth() / 2.0f), this.selectorStroke);
            canvas.drawBitmap(this.currentColor, 0.0f, 0.0f, (Paint) null);
        }
    }

    private ColorCircle findNearestByPosition(float f, float f2) {
        ColorCircle colorCircle = null;
        double d = Double.MAX_VALUE;
        for (ColorCircle next : this.renderer.getColorCircleList()) {
            double sqDist = next.sqDist(f, f2);
            if (d > sqDist) {
                colorCircle = next;
                d = sqDist;
            }
        }
        return colorCircle;
    }

    private ColorCircle findNearestByColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        char c = 1;
        char c2 = 0;
        double cos = ((double) fArr[1]) * Math.cos((((double) fArr[0]) * 3.141592653589793d) / 180.0d);
        double sin = ((double) fArr[1]) * Math.sin((((double) fArr[0]) * 3.141592653589793d) / 180.0d);
        ColorCircle colorCircle = null;
        double d = Double.MAX_VALUE;
        for (ColorCircle next : this.renderer.getColorCircleList()) {
            float[] hsv = next.getHsv();
            double d2 = sin;
            double cos2 = ((double) hsv[c]) * Math.cos((((double) hsv[c2]) * 3.141592653589793d) / 180.0d);
            double d3 = cos - cos2;
            double sin2 = d2 - (((double) hsv[1]) * Math.sin((((double) hsv[0]) * 3.141592653589793d) / 180.0d));
            double d4 = (d3 * d3) + (sin2 * sin2);
            if (d4 < d) {
                d = d4;
                colorCircle = next;
            }
            sin = d2;
            c = 1;
            c2 = 0;
        }
        return colorCircle;
    }

    public int getSelectedColor() {
        ColorCircle colorCircle = this.currentColorCircle;
        return Utils.adjustAlpha(this.alpha, colorCircle != null ? Utils.colorAtLightness(colorCircle.getColor(), this.lightness) : 0);
    }

    public Integer[] getAllColors() {
        return this.initialColors;
    }

    public void setInitialColors(Integer[] numArr, int i) {
        this.initialColors = numArr;
        this.colorSelection = i;
        Integer num = numArr[i];
        if (num == null) {
            num = -1;
        }
        setInitialColor(num.intValue(), true);
    }

    public void setInitialColor(int i, boolean z) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        this.alpha = Utils.getAlphaPercent(i);
        this.lightness = fArr[2];
        this.initialColors[this.colorSelection] = Integer.valueOf(i);
        this.initialColor = Integer.valueOf(i);
        setColorPreviewColor(i);
        setColorToSliders(i);
        if (this.colorEdit != null && z) {
            setColorText(i);
        }
        this.currentColorCircle = findNearestByColor(i);
    }

    public void setLightness(float f) {
        Integer num;
        int selectedColor = getSelectedColor();
        this.lightness = f;
        if (this.currentColorCircle != null) {
            Integer valueOf = Integer.valueOf(Color.HSVToColor(Utils.alphaValueAsInt(this.alpha), this.currentColorCircle.getHsvWithLightness(f)));
            this.initialColor = valueOf;
            EditText editText = this.colorEdit;
            if (editText != null) {
                editText.setText(Utils.getHexString(valueOf.intValue(), this.alphaSlider != null));
            }
            AlphaSlider alphaSlider2 = this.alphaSlider;
            if (!(alphaSlider2 == null || (num = this.initialColor) == null)) {
                alphaSlider2.setColor(num.intValue());
            }
            callOnColorChangedListeners(selectedColor, this.initialColor.intValue());
            updateColorWheel();
            invalidate();
        }
    }

    public void setColor(int i, boolean z) {
        setInitialColor(i, z);
        updateColorWheel();
        invalidate();
    }

    public void setAlphaValue(float f) {
        Integer num;
        int selectedColor = getSelectedColor();
        this.alpha = f;
        Integer valueOf = Integer.valueOf(Color.HSVToColor(Utils.alphaValueAsInt(f), this.currentColorCircle.getHsvWithLightness(this.lightness)));
        this.initialColor = valueOf;
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setText(Utils.getHexString(valueOf.intValue(), this.alphaSlider != null));
        }
        LightnessSlider lightnessSlider2 = this.lightnessSlider;
        if (!(lightnessSlider2 == null || (num = this.initialColor) == null)) {
            lightnessSlider2.setColor(num.intValue());
        }
        callOnColorChangedListeners(selectedColor, this.initialColor.intValue());
        updateColorWheel();
        invalidate();
    }

    public void addOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.colorChangedListeners.add(onColorChangedListener);
    }

    public void addOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener) {
        this.listeners.add(onColorSelectedListener);
    }

    public void setLightnessSlider(LightnessSlider lightnessSlider2) {
        this.lightnessSlider = lightnessSlider2;
        if (lightnessSlider2 != null) {
            lightnessSlider2.setColorPicker(this);
            this.lightnessSlider.setColor(getSelectedColor());
        }
    }

    public void setAlphaSlider(AlphaSlider alphaSlider2) {
        this.alphaSlider = alphaSlider2;
        if (alphaSlider2 != null) {
            alphaSlider2.setColorPicker(this);
            this.alphaSlider.setColor(getSelectedColor());
        }
    }

    public void setColorEdit(EditText editText) {
        this.colorEdit = editText;
        if (editText != null) {
            editText.setVisibility(0);
            this.colorEdit.addTextChangedListener(this.colorTextChange);
            setColorEditTextColor(this.pickerColorEditTextColor.intValue());
        }
    }

    public void setColorEditTextColor(int i) {
        this.pickerColorEditTextColor = Integer.valueOf(i);
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setTextColor(i);
        }
    }

    public void setDensity(int i) {
        this.density = Math.max(2, i);
        invalidate();
    }

    public void setRenderer(ColorWheelRenderer colorWheelRenderer) {
        this.renderer = colorWheelRenderer;
        invalidate();
    }

    public void setColorPreview(LinearLayout linearLayout, Integer num) {
        if (linearLayout != null) {
            this.colorPreview = linearLayout;
            if (num == null) {
                num = 0;
            }
            int childCount = linearLayout.getChildCount();
            if (childCount != 0 && linearLayout.getVisibility() == 0) {
                for (int i = 0; i < childCount; i++) {
                    View childAt = linearLayout.getChildAt(i);
                    if (childAt instanceof LinearLayout) {
                        LinearLayout linearLayout2 = (LinearLayout) childAt;
                        if (i == num.intValue()) {
                            linearLayout2.setBackgroundColor(-1);
                        }
                        ImageView imageView = (ImageView) linearLayout2.findViewWithTag("image_preview");
                        imageView.setClickable(true);
                        imageView.setTag(Integer.valueOf(i));
                        imageView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Object tag;
                                if (view != null && (tag = view.getTag()) != null && (tag instanceof Integer)) {
                                    ColorPickerView.this.setSelectedColor(((Integer) tag).intValue());
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public void setSelectedColor(int i) {
        Integer[] numArr = this.initialColors;
        if (numArr != null && numArr.length >= i) {
            this.colorSelection = i;
            setHighlightedColor(i);
            Integer num = this.initialColors[i];
            if (num != null) {
                setColor(num.intValue(), true);
            }
        }
    }

    public void setShowBorder(boolean z) {
        this.showBorder = z;
    }

    private void setHighlightedColor(int i) {
        int childCount = this.colorPreview.getChildCount();
        if (childCount != 0 && this.colorPreview.getVisibility() == 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.colorPreview.getChildAt(i2);
                if (childAt instanceof LinearLayout) {
                    LinearLayout linearLayout = (LinearLayout) childAt;
                    if (i2 == i) {
                        linearLayout.setBackgroundColor(-1);
                    } else {
                        linearLayout.setBackgroundColor(0);
                    }
                }
            }
        }
    }

    private void setColorPreviewColor(int i) {
        Integer[] numArr;
        int i2;
        LinearLayout linearLayout = this.colorPreview;
        if (linearLayout != null && (numArr = this.initialColors) != null && (i2 = this.colorSelection) <= numArr.length && numArr[i2] != null && linearLayout.getChildCount() != 0 && this.colorPreview.getVisibility() == 0) {
            View childAt = this.colorPreview.getChildAt(this.colorSelection);
            if (childAt instanceof LinearLayout) {
                ((ImageView) ((LinearLayout) childAt).findViewWithTag("image_preview")).setImageDrawable(new ColorCircleDrawable(i));
            }
        }
    }

    private void setColorText(int i) {
        EditText editText = this.colorEdit;
        if (editText != null) {
            editText.setText(Utils.getHexString(i, this.alphaSlider != null));
        }
    }

    private void setColorToSliders(int i) {
        LightnessSlider lightnessSlider2 = this.lightnessSlider;
        if (lightnessSlider2 != null) {
            lightnessSlider2.setColor(i);
        }
        AlphaSlider alphaSlider2 = this.alphaSlider;
        if (alphaSlider2 != null) {
            alphaSlider2.setColor(i);
        }
    }

    public enum WHEEL_TYPE {
        FLOWER,
        CIRCLE;

        public static WHEEL_TYPE indexOf(int i) {
            if (i == 0) {
                return FLOWER;
            }
            if (i != 1) {
                return FLOWER;
            }
            return CIRCLE;
        }
    }
}
