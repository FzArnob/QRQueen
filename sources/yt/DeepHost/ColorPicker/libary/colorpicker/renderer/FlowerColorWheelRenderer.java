package yt.DeepHost.ColorPicker.libary.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import yt.DeepHost.ColorPicker.libary.colorpicker.ColorCircle;
import yt.DeepHost.ColorPicker.libary.colorpicker.builder.PaintBuilder;

public class FlowerColorWheelRenderer extends AbsColorWheelRenderer {
    private float[] hsv = new float[3];
    private Paint selectorFill = PaintBuilder.newPaint().build();
    private float sizeJitter = 1.2f;

    public void draw() {
        float f;
        int size = this.colorCircleList.size();
        float f2 = 2.0f;
        float width = ((float) this.colorWheelRenderOption.targetCanvas.getWidth()) / 2.0f;
        int i = this.colorWheelRenderOption.density;
        float f3 = this.colorWheelRenderOption.strokeWidth;
        float f4 = this.colorWheelRenderOption.maxRadius;
        float f5 = this.colorWheelRenderOption.cSize;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            float f6 = (float) i2;
            float f7 = f6 / ((float) (i - 1));
            float f8 = (float) i;
            float f9 = (f6 - (f8 / f2)) / f8;
            float f10 = f7 * f4;
            float f11 = 1.5f + f3;
            if (i2 == 0) {
                f = 0.0f;
            } else {
                f = f9 * this.sizeJitter * f5;
            }
            float max = Math.max(f11, f + f5);
            int min = Math.min(calcTotalCount(f10, max), i * 2);
            int i4 = 0;
            while (i4 < min) {
                int i5 = i;
                int i6 = i2;
                double d = (double) min;
                int i7 = min;
                int i8 = i4;
                double d2 = ((((double) i4) * 6.283185307179586d) / d) + ((3.141592653589793d / d) * ((double) ((i6 + 1) % 2)));
                double d3 = (double) f10;
                float cos = ((float) (Math.cos(d2) * d3)) + width;
                float sin = ((float) (d3 * Math.sin(d2))) + width;
                float[] fArr = this.hsv;
                fArr[0] = (float) ((d2 * 180.0d) / 3.141592653589793d);
                fArr[1] = f10 / f4;
                fArr[2] = this.colorWheelRenderOption.lightness;
                this.selectorFill.setColor(Color.HSVToColor(this.hsv));
                this.selectorFill.setAlpha(getAlphaValueAsInt());
                this.colorWheelRenderOption.targetCanvas.drawCircle(cos, sin, max - f3, this.selectorFill);
                if (i3 >= size) {
                    this.colorCircleList.add(new ColorCircle(cos, sin, this.hsv));
                } else {
                    ((ColorCircle) this.colorCircleList.get(i3)).set(cos, sin, this.hsv);
                }
                i3++;
                i4 = i8 + 1;
                i2 = i6;
                i = i5;
                min = i7;
            }
            i2++;
            i = i;
            f2 = 2.0f;
        }
    }
}
