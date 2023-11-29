package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.google.appinventor.components.common.ScreenAnimation;

public final class AnimationUtil {
    private AnimationUtil() {
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(View view, boolean z, int i) {
        float f = z ? 1.0f : -1.0f;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setRepeatCount(-1);
        animationSet.setRepeatMode(1);
        TranslateAnimation translateAnimation = new TranslateAnimation(2, f * 0.7f, 2, f * -0.7f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setStartOffset(0);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        view.startAnimation(animationSet);
    }

    public static void ApplyAnimation(View view, String str) {
        if (str.equals("ScrollRightSlow")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, false, 8000);
        } else if (str.equals("ScrollRight")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, false, 4000);
        } else if (str.equals("ScrollRightFast")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, false, 1000);
        } else if (str.equals("ScrollLeftSlow")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, true, 8000);
        } else if (str.equals("ScrollLeft")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, true, 4000);
        } else if (str.equals("ScrollLeftFast")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, true, 1000);
        } else if (str.equals("Stop")) {
            view.clearAnimation();
        }
    }

    public static void ApplyOpenScreenAnimation(Activity activity, String str) {
        ApplyOpenScreenAnimation(activity, ScreenAnimation.fromUnderlyingValue(str));
    }

    /* renamed from: com.google.appinventor.components.runtime.util.AnimationUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.appinventor.components.common.ScreenAnimation[] r0 = com.google.appinventor.components.common.ScreenAnimation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r0
                com.google.appinventor.components.common.ScreenAnimation r1 = com.google.appinventor.components.common.ScreenAnimation.Fade     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.ScreenAnimation r1 = com.google.appinventor.components.common.ScreenAnimation.Zoom     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.common.ScreenAnimation r1 = com.google.appinventor.components.common.ScreenAnimation.SlideHorizontal     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.appinventor.components.common.ScreenAnimation r1 = com.google.appinventor.components.common.ScreenAnimation.SlideVertical     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.appinventor.components.common.ScreenAnimation r1 = com.google.appinventor.components.common.ScreenAnimation.None     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AnimationUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static void ApplyOpenScreenAnimation(Activity activity, ScreenAnimation screenAnimation) {
        int i;
        int i2;
        int i3;
        if (screenAnimation != null) {
            int i4 = AnonymousClass1.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R[screenAnimation.ordinal()];
            int i5 = 0;
            if (i4 != 1) {
                if (i4 == 2) {
                    i3 = activity.getResources().getIdentifier("zoom_exit", "anim", activity.getPackageName());
                    i2 = activity.getResources().getIdentifier("zoom_enter", "anim", activity.getPackageName());
                } else if (i4 == 3) {
                    i3 = activity.getResources().getIdentifier("slide_exit", "anim", activity.getPackageName());
                    i2 = activity.getResources().getIdentifier("slide_enter", "anim", activity.getPackageName());
                } else if (i4 == 4) {
                    i3 = activity.getResources().getIdentifier("slide_v_exit", "anim", activity.getPackageName());
                    i2 = activity.getResources().getIdentifier("slide_v_enter", "anim", activity.getPackageName());
                } else if (i4 == 5) {
                    i = 0;
                } else {
                    return;
                }
                int i6 = i3;
                i5 = i2;
                i = i6;
            } else {
                i5 = activity.getResources().getIdentifier("fadein", "anim", activity.getPackageName());
                i = activity.getResources().getIdentifier("hold", "anim", activity.getPackageName());
            }
            activity.overridePendingTransition(i5, i);
        }
    }

    public static void ApplyCloseScreenAnimation(Activity activity, String str) {
        ApplyCloseScreenAnimation(activity, ScreenAnimation.fromUnderlyingValue(str));
    }

    public static void ApplyCloseScreenAnimation(Activity activity, ScreenAnimation screenAnimation) {
        int i;
        int i2;
        int i3;
        if (screenAnimation != null) {
            int i4 = AnonymousClass1.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R[screenAnimation.ordinal()];
            int i5 = 0;
            if (i4 == 1) {
                i3 = activity.getResources().getIdentifier("fadeout", "anim", activity.getPackageName());
                i2 = activity.getResources().getIdentifier("hold", "anim", activity.getPackageName());
            } else if (i4 == 2) {
                i3 = activity.getResources().getIdentifier("zoom_exit_reverse", "anim", activity.getPackageName());
                i2 = activity.getResources().getIdentifier("zoom_enter_reverse", "anim", activity.getPackageName());
            } else if (i4 == 3) {
                i3 = activity.getResources().getIdentifier("slide_exit_reverse", "anim", activity.getPackageName());
                i2 = activity.getResources().getIdentifier("slide_enter_reverse", "anim", activity.getPackageName());
            } else if (i4 == 4) {
                i3 = activity.getResources().getIdentifier("slide_v_exit_reverse", "anim", activity.getPackageName());
                i2 = activity.getResources().getIdentifier("slide_v_enter_reverse", "anim", activity.getPackageName());
            } else if (i4 == 5) {
                i = 0;
                activity.overridePendingTransition(i5, i);
            } else {
                return;
            }
            int i6 = i3;
            i5 = i2;
            i = i6;
            activity.overridePendingTransition(i5, i);
        }
    }
}
