package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.common.HtmlEntities;
import java.util.ArrayList;

public class KodularHelper {
    private KodularHelper() {
    }

    public static void setStatusBarColor(Activity activity, int i) {
        try {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
            Log.i("KodularHelper", "Set new statusBarColor successfully executed");
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setNavigationBarColor(Activity activity, int i) {
        try {
            activity.getWindow().setNavigationBarColor(i);
            Log.i("KodularHelper", "Set new navigationBarColor successfully executed");
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setAppBackgroundTaskInfo(Activity activity, String str, int i) {
        Drawable drawable;
        Bitmap packageIcon;
        try {
            drawable = activity.getPackageManager().getApplicationIcon(activity.getPackageName());
        } catch (Exception unused) {
            Log.w("KodularHelper", "wrong icon or icon was empty or null or not found. setAppBackgroundTaskInfo");
            drawable = null;
        }
        if (drawable != null && (packageIcon = getPackageIcon(drawable)) != null) {
            activity.setTaskDescription(new ActivityManager.TaskDescription(str, packageIcon, i));
        }
    }

    public static Bitmap getPackageIcon(Drawable drawable) {
        try {
            if (Build.VERSION.SDK_INT < 26) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            if (!(drawable instanceof AdaptiveIconDrawable)) {
                return null;
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{((AdaptiveIconDrawable) drawable).getBackground(), ((AdaptiveIconDrawable) drawable).getForeground()});
            Bitmap createBitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), layerDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            layerDrawable.draw(canvas);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void prepareRippleDrawable(View view, MotionEvent motionEvent, int i) {
        try {
            RippleDrawable rippleDrawable = (RippleDrawable) view.getBackground();
            rippleDrawable.setHotspot(motionEvent.getX(), motionEvent.getY());
            rippleDrawable.setColor(new ColorStateList(new int[][]{new int[]{16842910}}, new int[]{i}));
            Log.i("KodularHelper", "Prepare new RippleDrawable successfully executed");
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setRippleDrawable(View view, Drawable drawable, int i) {
        try {
            ViewUtil.setBackgroundDrawable(view, new RippleDrawable(new ColorStateList(new int[][]{new int[]{16842910}}, new int[]{i}), new LayerDrawable(new Drawable[]{drawable, drawable}), drawable));
            Log.i("KodularHelper", "Set new RippleDrawable successfully executed");
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setButtonTintList(CompoundButton compoundButton, int i) {
        try {
            compoundButton.setButtonTintList(ColorStateList.valueOf(i));
            compoundButton.invalidate();
            Log.i("KodularHelper", "Set new buttonTintList successfully executed");
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setPadding(View view, Context context, int i, int i2, int i3, int i4) {
        try {
            view.setPadding(KodularUnitUtil.DpToPixels(context, i), KodularUnitUtil.DpToPixels(context, i2), KodularUnitUtil.DpToPixels(context, i3), KodularUnitUtil.DpToPixels(context, i4));
            view.invalidate();
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setMarginToBtn(Button button, Context context, String str) {
        String trim = str.trim();
        if (!trim.isEmpty()) {
            String[] split = trim.split(",");
            ArrayList arrayList = new ArrayList();
            for (String trim2 : split) {
                arrayList.add(Integer.valueOf(KodularUnitUtil.DpToPixels(context, Integer.parseInt(trim2.trim()))));
            }
            if (split.length == 1) {
                try {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
                    layoutParams.setMargins(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue());
                    button.setLayoutParams(layoutParams);
                    button.invalidate();
                } catch (Exception e) {
                    Log.e("KodularHelper", String.valueOf(e));
                }
            } else if (split.length == 2) {
                try {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button.getLayoutParams();
                    layoutParams2.setMargins(0, ((Integer) arrayList.get(0)).intValue(), 0, ((Integer) arrayList.get(1)).intValue());
                    button.setLayoutParams(layoutParams2);
                    button.invalidate();
                } catch (Exception e2) {
                    Log.e("KodularHelper", String.valueOf(e2));
                }
            } else if (split.length == 4) {
                try {
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button.getLayoutParams();
                    layoutParams3.setMargins(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(2)).intValue(), ((Integer) arrayList.get(3)).intValue());
                    button.setLayoutParams(layoutParams3);
                    button.invalidate();
                } catch (Exception e3) {
                    Log.e("KodularHelper", String.valueOf(e3));
                }
            }
        }
    }

    public static void setMarginToButton(Button button, Context context, int i, int i2, int i3, int i4) {
        try {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.setMargins(KodularUnitUtil.DpToPixels(context, i), KodularUnitUtil.DpToPixels(context, i2), KodularUnitUtil.DpToPixels(context, i3), KodularUnitUtil.DpToPixels(context, i4));
            button.setLayoutParams(layoutParams);
            button.invalidate();
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setShape(View view, int i, int i2, boolean z) {
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(z ? 50.0f : 10.0f);
            gradientDrawable.setColor(i);
            gradientDrawable.setStroke(3, i2);
            view.setBackground(gradientDrawable);
            view.invalidate();
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static void setShadow(TextView textView, float f, float f2, float f3, int i) {
        try {
            textView.setShadowLayer(f3, f, f2, i);
            textView.invalidate();
        } catch (Exception e) {
            Log.e("KodularHelper", String.valueOf(e));
        }
    }

    public static Bitmap textToBitmap(Context context, String str, String str2, int i, float f) {
        try {
            Paint paint = new Paint(1);
            paint.setTextSize(f);
            paint.setColor(i);
            paint.setTextAlign(Paint.Align.CENTER);
            TextViewUtil.setContext(context);
            if (str.equalsIgnoreCase("material")) {
                TextViewUtil.setFontTypefaceCanvas(paint, 7, false, false);
            } else if (str.equalsIgnoreCase("font_awesome")) {
                TextViewUtil.setFontTypefaceCanvas(paint, 6, false, false);
            }
            int i2 = (int) f;
            Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawText(HtmlEntities.decodeHtmlText(str2), (float) (canvas.getWidth() / 2), (float) ((int) (((float) (canvas.getHeight() / 2)) - ((paint.descent() + paint.ascent()) / 2.0f))), paint);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }
}
