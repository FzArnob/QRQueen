package com.github.ybq.android.spinkit;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.PulseRing;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;

public class SpriteFactory {

    /* renamed from: com.github.ybq.android.spinkit.SpriteFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$ybq$android$spinkit$Style;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.github.ybq.android.spinkit.Style[] r0 = com.github.ybq.android.spinkit.Style.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$github$ybq$android$spinkit$Style = r0
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.ROTATING_PLANE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x001d }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.DOUBLE_BOUNCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.WAVE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.WANDERING_CUBES     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x003e }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.PULSE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.CHASING_DOTS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.THREE_BOUNCE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x006c }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.CUBE_GRID     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.FADING_CIRCLE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.FOLDING_CUBE     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.ROTATING_CIRCLE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x009c }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.MULTIPLE_PULSE     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.PULSE_RING     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$github$ybq$android$spinkit$Style     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.github.ybq.android.spinkit.Style r1 = com.github.ybq.android.spinkit.Style.MULTIPLE_PULSE_RING     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.ybq.android.spinkit.SpriteFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public static Sprite create(Style style) {
        switch (AnonymousClass1.$SwitchMap$com$github$ybq$android$spinkit$Style[style.ordinal()]) {
            case 1:
                return new RotatingPlane();
            case 2:
                return new DoubleBounce();
            case 3:
                return new Wave();
            case 4:
                return new WanderingCubes();
            case 5:
                return new Pulse();
            case 6:
                return new ChasingDots();
            case 7:
                return new ThreeBounce();
            case 8:
                return new Circle();
            case 9:
                return new CubeGrid();
            case 10:
                return new FadingCircle();
            case 11:
                return new FoldingCube();
            case 12:
                return new RotatingCircle();
            case 13:
                return new MultiplePulse();
            case 14:
                return new PulseRing();
            case 15:
                return new MultiplePulseRing();
            default:
                return null;
        }
    }
}
