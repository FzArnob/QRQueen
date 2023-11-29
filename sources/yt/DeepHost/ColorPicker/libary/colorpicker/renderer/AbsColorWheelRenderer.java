package yt.DeepHost.ColorPicker.libary.colorpicker.renderer;

import java.util.ArrayList;
import java.util.List;
import yt.DeepHost.ColorPicker.libary.colorpicker.ColorCircle;

public abstract class AbsColorWheelRenderer implements ColorWheelRenderer {
    protected List<ColorCircle> colorCircleList = new ArrayList();
    protected ColorWheelRenderOption colorWheelRenderOption;

    public void initWith(ColorWheelRenderOption colorWheelRenderOption2) {
        this.colorWheelRenderOption = colorWheelRenderOption2;
        this.colorCircleList.clear();
    }

    public ColorWheelRenderOption getRenderOption() {
        if (this.colorWheelRenderOption == null) {
            this.colorWheelRenderOption = new ColorWheelRenderOption();
        }
        return this.colorWheelRenderOption;
    }

    public List<ColorCircle> getColorCircleList() {
        return this.colorCircleList;
    }

    /* access modifiers changed from: protected */
    public int getAlphaValueAsInt() {
        return Math.round(this.colorWheelRenderOption.alpha * 255.0f);
    }

    /* access modifiers changed from: protected */
    public int calcTotalCount(float f, float f2) {
        return Math.max(1, (int) ((3.063052912151454d / Math.asin((double) (f2 / f))) + 0.5d));
    }
}
