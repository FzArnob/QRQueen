package com.google.appinventor.components.runtime.util;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class PolyUtil {
    private final Path hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Path();

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final RectF f303hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new RectF();

    private static double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(double d) {
        return (d * 6.283185307179586d) / 360.0d;
    }

    public void drawPolygon(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, Paint paint) {
        constructPolygonPath(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i, f, f2, f3, f4, f5);
        Canvas canvas2 = canvas;
        canvas.drawPath(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, paint);
    }

    public void constructPolygonPath(Path path, int i, float f, float f2, float f3, float f4, float f5) {
        Path path2 = path;
        int i2 = i;
        float f6 = f;
        float f7 = f2;
        float f8 = f4;
        path.reset();
        double d = (double) f3;
        double d2 = (double) i2;
        double d3 = 180.0d / d2;
        float cos = (float) (Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d3)) * d);
        if (cos < f8) {
            path2.addCircle(f6, f7, cos, Path.Direction.CW);
            return;
        }
        if (((double) Math.abs(f4)) < 0.01d) {
            for (int i3 = 0; i3 < i2; i3++) {
                double d4 = ((double) i3) * (360.0d / d2);
                float cos2 = (float) (((double) f6) + (Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d4)) * d));
                float sin = (float) (((double) f7) + (Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d4)) * d));
                if (i3 == 0) {
                    path2.moveTo(cos2, sin);
                } else {
                    path2.lineTo(cos2, sin);
                }
            }
            path.close();
        } else {
            double d5 = 90.0d - d3;
            float f9 = (float) (90.0d - d5);
            double sin2 = d - (((double) f8) / Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d5)));
            int i4 = 0;
            while (i4 < i2) {
                double d6 = ((double) i4) * (360.0d / d2);
                double d7 = d2;
                float cos3 = (float) (((double) f6) + (Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d6)) * sin2));
                float sin3 = (float) (((double) f7) + (Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d6)) * sin2));
                this.f303hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.set(cos3 - f8, sin3 - f8, cos3 + f8, sin3 + f8);
                path2.arcTo(this.f303hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (float) (d6 - ((double) f9)), 2.0f * f9);
                i4++;
                i2 = i;
                f6 = f;
                d2 = d7;
            }
            path.close();
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(f5, f, f7);
        path2.transform(matrix);
    }
}
