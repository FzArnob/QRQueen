package yt.DeepHost.ColorPicker.libary.colorpicker.layout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class color_selector {

    public static class layout extends LinearLayout {
        public layout(Context context) {
            super(context);
            design_size design_size = new design_size(context);
            setLayoutParams(new LinearLayout.LayoutParams(design_size.getPixels(40), design_size.getPixels(40)));
            setBackgroundColor(0);
            setPadding(design_size.getPixels(2), design_size.getPixels(2), design_size.getPixels(2), design_size.getPixels(2));
            ImageView imageView = new ImageView(context);
            imageView.setTag("image_preview");
            imageView.setLayoutParams(new LinearLayout.LayoutParams(design_size.getPixels(36), design_size.getPixels(36)));
            imageView.setImageDrawable(new ColorDrawable(0));
            addView(imageView);
        }
    }
}
