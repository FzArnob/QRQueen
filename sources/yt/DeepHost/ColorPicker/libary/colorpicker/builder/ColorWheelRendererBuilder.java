package yt.DeepHost.ColorPicker.libary.colorpicker.builder;

import yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView;
import yt.DeepHost.ColorPicker.libary.colorpicker.renderer.ColorWheelRenderer;
import yt.DeepHost.ColorPicker.libary.colorpicker.renderer.FlowerColorWheelRenderer;
import yt.DeepHost.ColorPicker.libary.colorpicker.renderer.SimpleColorWheelRenderer;

public class ColorWheelRendererBuilder {

    /* renamed from: yt.DeepHost.ColorPicker.libary.colorpicker.builder.ColorWheelRendererBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$yt$DeepHost$ColorPicker$libary$colorpicker$ColorPickerView$WHEEL_TYPE;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView$WHEEL_TYPE[] r0 = yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView.WHEEL_TYPE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$yt$DeepHost$ColorPicker$libary$colorpicker$ColorPickerView$WHEEL_TYPE = r0
                yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView$WHEEL_TYPE r1 = yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView.WHEEL_TYPE.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$yt$DeepHost$ColorPicker$libary$colorpicker$ColorPickerView$WHEEL_TYPE     // Catch:{ NoSuchFieldError -> 0x001d }
                yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView$WHEEL_TYPE r1 = yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView.WHEEL_TYPE.FLOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: yt.DeepHost.ColorPicker.libary.colorpicker.builder.ColorWheelRendererBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheel_type) {
        int i = AnonymousClass1.$SwitchMap$yt$DeepHost$ColorPicker$libary$colorpicker$ColorPickerView$WHEEL_TYPE[wheel_type.ordinal()];
        if (i == 1) {
            return new SimpleColorWheelRenderer();
        }
        if (i == 2) {
            return new FlowerColorWheelRenderer();
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}
