package yt.DeepHost.ColorPicker.libary.colorpicker.renderer;

import java.util.List;
import yt.DeepHost.ColorPicker.libary.colorpicker.ColorCircle;

public interface ColorWheelRenderer {
    public static final float GAP_PERCENTAGE = 0.025f;

    void draw();

    List<ColorCircle> getColorCircleList();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);
}
