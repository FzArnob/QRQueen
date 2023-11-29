package yt.DeepHost.ColorPicker.libary.colorpicker.layout;

import android.content.Context;
import android.widget.LinearLayout;

public class color_preview {

    public static class layout extends LinearLayout {
        public layout(Context context) {
            super(context);
            setLayoutParams(new LinearLayout.LayoutParams(-2, new design_size(context).getPixels(40)));
            setBackgroundColor(0);
            setGravity(17);
            setOrientation(0);
        }
    }
}
