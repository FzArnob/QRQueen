package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.google.appinventor.components.runtime.Form;

public class TextViewUtil {
    private static Context wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(boolean z, boolean z2) {
        return z2 ? z | true ? 1 : 0 : z ? 1 : 0;
    }

    private TextViewUtil() {
    }

    public static void setContext(Context context) {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = context;
    }

    public static void setAlignment(TextView textView, int i, boolean z) {
        int i2 = 1;
        if (i == 0) {
            i2 = GravityCompat.START;
        } else if (i != 1) {
            if (i == 2) {
                i2 = GravityCompat.END;
            } else {
                throw new IllegalArgumentException();
            }
        }
        textView.setGravity((z ? 16 : 48) | i2);
        textView.setTextDirection(5);
        textView.invalidate();
    }

    public static void setBackgroundColor(TextView textView, int i) {
        textView.setBackgroundColor(i);
        textView.invalidate();
    }

    public static boolean isEnabled(TextView textView) {
        return textView.isEnabled();
    }

    public static void setEnabled(TextView textView, boolean z) {
        textView.setEnabled(z);
        textView.invalidate();
    }

    public static float getFontSize(TextView textView, Context context) {
        return textView.getTextSize() / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static void setFontSize(TextView textView, float f) {
        textView.setTextSize(f);
        textView.requestLayout();
    }

    public static void setFontTypeface(TextView textView, int i, boolean z, boolean z2) {
        try {
            textView.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
            textView.requestLayout();
        } catch (Exception e) {
            Log.d("TextViewUtil", e.getMessage());
        }
    }

    public static Typeface getTitleBarTypeFace(int i) {
        return Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(false, false));
    }

    public static Typeface getTitleBarCustomTypeFace(Form form, String str) {
        return Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(false, false));
    }

    public static void setFontTypefaceCanvas(Paint paint, int i, boolean z, boolean z2) {
        try {
            paint.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
        } catch (Exception e) {
            Log.d("TextViewUtil", e.getMessage());
        }
    }

    public static void setCustomFontTypeface(Form form, TextView textView, String str, boolean z, boolean z2) {
        try {
            textView.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
            textView.requestLayout();
        } catch (Exception e) {
            Log.d("TextViewUtil", e.getMessage());
        }
    }

    public static void setCustomFontTypefaceCanvas(Form form, Paint paint, String str, boolean z, boolean z2) {
        try {
            paint.setTypeface(Typeface.create(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z, z2)));
        } catch (Exception e) {
            Log.d("TextViewUtil", e.getMessage());
        }
    }

    public static String getText(TextView textView) {
        return textView.getText().toString();
    }

    public static void setTextHTML(TextView textView, String str) {
        textView.setText(fromHtml(str));
        textView.requestLayout();
    }

    public static void setText(TextView textView, String str) {
        textView.setText(str);
        textView.requestLayout();
    }

    public static void setPadding(TextView textView, int i) {
        textView.setPadding(i, i, 0, 0);
        textView.requestLayout();
    }

    public static void setTextColor(TextView textView, int i) {
        textView.setTextColor(i);
        textView.invalidate();
    }

    public static void setTextColors(TextView textView, ColorStateList colorStateList) {
        textView.setTextColor(colorStateList);
    }

    public static void setHintColor(TextView textView, int i) {
        textView.setHintTextColor(i);
        textView.invalidate();
    }

    public static void setHint(TextView textView, String str) {
        textView.setHint(str);
        textView.invalidate();
    }

    private static Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        switch (i) {
            case 0:
                return Typeface.DEFAULT;
            case 1:
                return Typeface.SANS_SERIF;
            case 2:
                return Typeface.SERIF;
            case 3:
                return Typeface.MONOSPACE;
            case 4:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "Roboto-Thin.ttf");
            case 5:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "Roboto-Regular.ttf");
            case 6:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "fontawesome-webfont.ttf");
            case 7:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "MaterialIcons-Regular.ttf");
            case 8:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "fa-regular-400.ttf");
            case 9:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "fa-solid-900.ttf");
            case 10:
                return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), "fa-brands-400.ttf");
            default:
                throw new IllegalArgumentException();
        }
    }

    private static Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) {
        try {
            String assetFilePath = MediaUtil.getAssetFilePath(form, str);
            if (assetFilePath.startsWith(QUtil.getExternalStoragePath(form))) {
                return Typeface.createFromFile(assetFilePath);
            }
            return Typeface.createFromAsset(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getAssets(), assetFilePath);
        } catch (Exception e) {
            Log.d("TextViewUtil", e.getMessage());
            return null;
        }
    }

    public static Spanned fromHtml(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }

    public static SpannableString linkifyMessage(String str) {
        SpannableString spannableString = new SpannableString(str);
        Linkify.addLinks(spannableString, 15);
        return spannableString;
    }

    public static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        public CustomTypefaceSpan(String str, Typeface typeface) {
            super(str);
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(textPaint, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        }

        public void updateMeasureState(TextPaint textPaint) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(textPaint, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        }

        private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Paint paint, Typeface typeface) {
            int i;
            Typeface typeface2 = paint.getTypeface();
            if (typeface2 == null) {
                i = 0;
            } else {
                i = typeface2.getStyle();
            }
            int i2 = i & (~typeface.getStyle());
            if ((i2 & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((i2 & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.setTypeface(typeface);
        }
    }

    public static void setMinWidth(TextView textView, int i) {
        textView.setMinWidth(i);
        textView.setMinimumWidth(i);
    }

    public static void setMinHeight(TextView textView, int i) {
        textView.setMinHeight(i);
        textView.setMinimumHeight(i);
    }

    public static void setMinSize(TextView textView, int i, int i2) {
        setMinWidth(textView, i);
        setMinHeight(textView, i2);
    }
}
