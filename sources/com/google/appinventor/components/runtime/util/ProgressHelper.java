package com.google.appinventor.components.runtime.util;

import java.util.Arrays;
import java.util.List;

public class ProgressHelper {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x011c, code lost:
        if (r9.equals("right") == false) goto L_0x0114;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setButtonProgressAnimation(android.widget.TextView r7, java.lang.String r8, java.lang.String r9, int r10, int r11) {
        /*
            java.lang.String r8 = r8.toLowerCase()
            java.lang.String r0 = "\\s"
            java.lang.String r1 = ""
            java.lang.String r8 = r8.replaceAll(r0, r1)
            r8.hashCode()
            int r2 = r8.hashCode()
            r3 = 2
            r4 = 1
            r5 = -1
            r6 = 0
            switch(r2) {
                case -1360216880: goto L_0x009f;
                case -817913340: goto L_0x0093;
                case -741786634: goto L_0x0088;
                case 3642105: goto L_0x007d;
                case 40036904: goto L_0x0072;
                case 105393403: goto L_0x0067;
                case 107027353: goto L_0x005c;
                case 509233141: goto L_0x0051;
                case 1143631270: goto L_0x0044;
                case 1217522153: goto L_0x0037;
                case 1384198729: goto L_0x002a;
                case 1471386009: goto L_0x001d;
                default: goto L_0x001a;
            }
        L_0x001a:
            r8 = -1
            goto L_0x00aa
        L_0x001d:
            java.lang.String r2 = "doublebounce"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0026
            goto L_0x001a
        L_0x0026:
            r8 = 11
            goto L_0x00aa
        L_0x002a:
            java.lang.String r2 = "fadingcircle"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0033
            goto L_0x001a
        L_0x0033:
            r8 = 10
            goto L_0x00aa
        L_0x0037:
            java.lang.String r2 = "wanderingcubes"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0040
            goto L_0x001a
        L_0x0040:
            r8 = 9
            goto L_0x00aa
        L_0x0044:
            java.lang.String r2 = "threebounce"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x004d
            goto L_0x001a
        L_0x004d:
            r8 = 8
            goto L_0x00aa
        L_0x0051:
            java.lang.String r2 = "chasingdots"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x005a
            goto L_0x001a
        L_0x005a:
            r8 = 7
            goto L_0x00aa
        L_0x005c:
            java.lang.String r2 = "pulse"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0065
            goto L_0x001a
        L_0x0065:
            r8 = 6
            goto L_0x00aa
        L_0x0067:
            java.lang.String r2 = "cubegrid"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0070
            goto L_0x001a
        L_0x0070:
            r8 = 5
            goto L_0x00aa
        L_0x0072:
            java.lang.String r2 = "rotatingcircle"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x007b
            goto L_0x001a
        L_0x007b:
            r8 = 4
            goto L_0x00aa
        L_0x007d:
            java.lang.String r2 = "wave"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0086
            goto L_0x001a
        L_0x0086:
            r8 = 3
            goto L_0x00aa
        L_0x0088:
            java.lang.String r2 = "foldingcube"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x0091
            goto L_0x001a
        L_0x0091:
            r8 = 2
            goto L_0x00aa
        L_0x0093:
            java.lang.String r2 = "rotatingplane"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x009d
            goto L_0x001a
        L_0x009d:
            r8 = 1
            goto L_0x00aa
        L_0x009f:
            java.lang.String r2 = "circle"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L_0x00a9
            goto L_0x001a
        L_0x00a9:
            r8 = 0
        L_0x00aa:
            r2 = 0
            switch(r8) {
                case 0: goto L_0x00f2;
                case 1: goto L_0x00ec;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00e0;
                case 4: goto L_0x00da;
                case 5: goto L_0x00d4;
                case 6: goto L_0x00ce;
                case 7: goto L_0x00c8;
                case 8: goto L_0x00c2;
                case 9: goto L_0x00bc;
                case 10: goto L_0x00b6;
                case 11: goto L_0x00b0;
                default: goto L_0x00ae;
            }
        L_0x00ae:
            r8 = r2
            goto L_0x00f7
        L_0x00b0:
            com.github.ybq.android.spinkit.style.DoubleBounce r8 = new com.github.ybq.android.spinkit.style.DoubleBounce
            r8.<init>()
            goto L_0x00f7
        L_0x00b6:
            com.github.ybq.android.spinkit.style.FadingCircle r8 = new com.github.ybq.android.spinkit.style.FadingCircle
            r8.<init>()
            goto L_0x00f7
        L_0x00bc:
            com.github.ybq.android.spinkit.style.WanderingCubes r8 = new com.github.ybq.android.spinkit.style.WanderingCubes
            r8.<init>()
            goto L_0x00f7
        L_0x00c2:
            com.github.ybq.android.spinkit.style.ThreeBounce r8 = new com.github.ybq.android.spinkit.style.ThreeBounce
            r8.<init>()
            goto L_0x00f7
        L_0x00c8:
            com.github.ybq.android.spinkit.style.ChasingDots r8 = new com.github.ybq.android.spinkit.style.ChasingDots
            r8.<init>()
            goto L_0x00f7
        L_0x00ce:
            com.github.ybq.android.spinkit.style.Pulse r8 = new com.github.ybq.android.spinkit.style.Pulse
            r8.<init>()
            goto L_0x00f7
        L_0x00d4:
            com.github.ybq.android.spinkit.style.CubeGrid r8 = new com.github.ybq.android.spinkit.style.CubeGrid
            r8.<init>()
            goto L_0x00f7
        L_0x00da:
            com.github.ybq.android.spinkit.style.RotatingCircle r8 = new com.github.ybq.android.spinkit.style.RotatingCircle
            r8.<init>()
            goto L_0x00f7
        L_0x00e0:
            com.github.ybq.android.spinkit.style.Wave r8 = new com.github.ybq.android.spinkit.style.Wave
            r8.<init>()
            goto L_0x00f7
        L_0x00e6:
            com.github.ybq.android.spinkit.style.FoldingCube r8 = new com.github.ybq.android.spinkit.style.FoldingCube
            r8.<init>()
            goto L_0x00f7
        L_0x00ec:
            com.github.ybq.android.spinkit.style.RotatingPlane r8 = new com.github.ybq.android.spinkit.style.RotatingPlane
            r8.<init>()
            goto L_0x00f7
        L_0x00f2:
            com.github.ybq.android.spinkit.style.Circle r8 = new com.github.ybq.android.spinkit.style.Circle
            r8.<init>()
        L_0x00f7:
            if (r8 == 0) goto L_0x0102
            r8.setBounds(r6, r6, r10, r10)
            r8.start()
            r8.setColor(r11)
        L_0x0102:
            java.lang.String r9 = r9.toLowerCase()
            java.lang.String r9 = r9.replaceAll(r0, r1)
            r9.hashCode()
            int r10 = r9.hashCode()
            switch(r10) {
                case -1383228885: goto L_0x012a;
                case 115029: goto L_0x011f;
                case 108511772: goto L_0x0116;
                default: goto L_0x0114;
            }
        L_0x0114:
            r3 = -1
            goto L_0x0134
        L_0x0116:
            java.lang.String r10 = "right"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0134
            goto L_0x0114
        L_0x011f:
            java.lang.String r10 = "top"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0128
            goto L_0x0114
        L_0x0128:
            r3 = 1
            goto L_0x0134
        L_0x012a:
            java.lang.String r10 = "bottom"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0133
            goto L_0x0114
        L_0x0133:
            r3 = 0
        L_0x0134:
            switch(r3) {
                case 0: goto L_0x0143;
                case 1: goto L_0x013f;
                case 2: goto L_0x013b;
                default: goto L_0x0137;
            }
        L_0x0137:
            r7.setCompoundDrawables(r8, r2, r2, r2)
            return
        L_0x013b:
            r7.setCompoundDrawables(r2, r2, r8, r2)
            return
        L_0x013f:
            r7.setCompoundDrawables(r2, r8, r2, r2)
            return
        L_0x0143:
            r7.setCompoundDrawables(r2, r2, r2, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.ProgressHelper.setButtonProgressAnimation(android.widget.TextView, java.lang.String, java.lang.String, int, int):void");
    }

    public static List<String> getAnimationStyles() {
        return Arrays.asList("ChasingDots,Circle,CubeGrid,DoubleBounce,FadingCircle,FoldingCube,Pulse,RotatingCircle,RotatingPlane,ThreeBounce,WanderingCubes,Wave".split("\\s*,\\s*"));
    }
}
