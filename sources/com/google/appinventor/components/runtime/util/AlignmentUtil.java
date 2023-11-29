package com.google.appinventor.components.runtime.util;

import androidx.core.view.GravityCompat;
import com.google.appinventor.components.common.HorizontalAlignment;
import com.google.appinventor.components.common.VerticalAlignment;
import com.google.appinventor.components.runtime.LinearLayout;

public class AlignmentUtil {
    private LinearLayout viewLayout;

    public AlignmentUtil(LinearLayout linearLayout) {
        this.viewLayout = linearLayout;
    }

    public void setHorizontalAlignment(int i) throws IllegalArgumentException {
        if (i == 1) {
            this.viewLayout.setHorizontalGravity(GravityCompat.START);
        } else if (i == 2) {
            this.viewLayout.setHorizontalGravity(GravityCompat.END);
        } else if (i == 3) {
            this.viewLayout.setHorizontalGravity(1);
        } else {
            throw new IllegalArgumentException("Bad value to setHorizontalAlignment: ".concat(String.valueOf(i)));
        }
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        int i = AnonymousClass1.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq[horizontalAlignment.ordinal()];
        if (i == 1) {
            this.viewLayout.setHorizontalGravity(GravityCompat.START);
        } else if (i == 2) {
            this.viewLayout.setHorizontalGravity(1);
        } else if (i == 3) {
            this.viewLayout.setHorizontalGravity(GravityCompat.END);
        } else {
            throw new IllegalArgumentException("Bad value to setHorizontalAlignment: ".concat(String.valueOf(horizontalAlignment)));
        }
    }

    public void setVerticalAlignment(int i) throws IllegalArgumentException {
        if (i == 1) {
            this.viewLayout.setVerticalGravity(48);
        } else if (i == 2) {
            this.viewLayout.setVerticalGravity(16);
        } else if (i == 3) {
            this.viewLayout.setVerticalGravity(80);
        } else {
            throw new IllegalArgumentException("Bad value to setVerticalAlignment: ".concat(String.valueOf(i)));
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.AlignmentUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
        static final /* synthetic */ int[] vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        static {
            /*
                com.google.appinventor.components.common.VerticalAlignment[] r0 = com.google.appinventor.components.common.VerticalAlignment.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r0
                r1 = 1
                com.google.appinventor.components.common.VerticalAlignment r2 = com.google.appinventor.components.common.VerticalAlignment.Top     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.VerticalAlignment r3 = com.google.appinventor.components.common.VerticalAlignment.Center     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.common.VerticalAlignment r4 = com.google.appinventor.components.common.VerticalAlignment.Bottom     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.appinventor.components.common.HorizontalAlignment[] r3 = com.google.appinventor.components.common.HorizontalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
                com.google.appinventor.components.common.HorizontalAlignment r4 = com.google.appinventor.components.common.HorizontalAlignment.Left     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.appinventor.components.common.HorizontalAlignment r3 = com.google.appinventor.components.common.HorizontalAlignment.Center     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.appinventor.components.common.HorizontalAlignment r1 = com.google.appinventor.components.common.HorizontalAlignment.Right     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AlignmentUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        int i = AnonymousClass1.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO[verticalAlignment.ordinal()];
        if (i == 1) {
            this.viewLayout.setVerticalGravity(48);
        } else if (i == 2) {
            this.viewLayout.setVerticalGravity(16);
        } else if (i == 3) {
            this.viewLayout.setVerticalGravity(80);
        } else {
            throw new IllegalArgumentException("Bad value to setVerticalAlignment: ".concat(String.valueOf(verticalAlignment)));
        }
    }
}
