package yt.DeepHost.ColorPicker.libary.colorpicker.layout;

import android.content.Context;
import android.util.DisplayMetrics;

public class design_size {
    int alto;
    int altoImagen;
    int ancho;
    float density = this.display.density;
    DisplayMetrics display;
    int height = this.display.heightPixels;
    int width;

    public design_size(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.display = displayMetrics;
        this.width = displayMetrics.widthPixels;
        int pixels = this.width - (getPixels(8) * 2);
        this.ancho = pixels;
        this.alto = ((pixels * 9) / 16) + pixels;
        this.altoImagen = (pixels * 9) / 16;
    }

    public int getPixels(int i) {
        return (int) (((float) i) * this.density);
    }
}
