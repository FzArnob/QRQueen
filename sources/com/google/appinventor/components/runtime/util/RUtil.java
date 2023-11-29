package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;
import net.lingala.zip4j.util.InternalZipConstants;

public class RUtil {
    public static boolean needsFilePermission(Form form, String str, FileScope fileScope) {
        if (fileScope != null) {
            return AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[fileScope.ordinal()] == 3;
        }
        if (str.startsWith("//")) {
            return false;
        }
        if (!str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && !str.startsWith("file:")) {
            return false;
        }
        if (str.startsWith("file:")) {
            str = str.substring(5);
        }
        String concat = "file:".concat(String.valueOf(str));
        return FileUtil.isExternalStorageUri(form, concat) && !FileUtil.isAppSpecificExternalUri(form, concat);
    }

    /* renamed from: com.google.appinventor.components.runtime.util.RUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.appinventor.components.common.FileScope[] r0 = com.google.appinventor.components.common.FileScope.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r0
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.App     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Asset     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Shared     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RUtil.AnonymousClass1.<clinit>():void");
        }
    }
}
