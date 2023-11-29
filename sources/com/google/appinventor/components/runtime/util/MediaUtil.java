package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.microsoft.appcenter.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MediaUtil {
    /* access modifiers changed from: private */
    public static boolean hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG = true;
    private static ConcurrentHashMap<String, String> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ConcurrentHashMap<>(2);
    private static final Map<String, File> tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = new HashMap();

    enum b {
        ASSET,
        REPL_ASSET,
        SDCARD,
        FILE_URL,
        URL,
        CONTENT_URI,
        CONTACT_URI,
        EXTERNAL_APP_FILES_DIRECTORY,
        INTERNAL_APP_FILES_DIRECTORY
    }

    private MediaUtil() {
    }

    private static String mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(String str) throws IOException {
        try {
            return new File(new URL(str).toURI()).getAbsolutePath();
        } catch (IllegalArgumentException unused) {
            throw new IOException("Unable to determine file path of file url ".concat(String.valueOf(str)));
        } catch (Exception unused2) {
            throw new IOException("Unable to determine file path of file url ".concat(String.valueOf(str)));
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static b m11hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(form.getExternalFilesDir(""));
        if (str.contains(sb.toString())) {
            return b.EXTERNAL_APP_FILES_DIRECTORY;
        }
        if (str.startsWith(form.getFilesDir().getAbsolutePath())) {
            return b.INTERNAL_APP_FILES_DIRECTORY;
        }
        if (str.startsWith(QUtil.getExternalStoragePath(form)) || str.startsWith("/sdcard/")) {
            return b.SDCARD;
        }
        if (str.startsWith("content://contacts/")) {
            return b.CONTACT_URI;
        }
        if (str.startsWith("content://")) {
            return b.CONTENT_URI;
        }
        try {
            new URL(str);
            if (str.startsWith("file:")) {
                return b.FILE_URL;
            }
            return b.URL;
        } catch (MalformedURLException unused) {
            if (!(form instanceof ReplForm)) {
                return b.ASSET;
            }
            if (((ReplForm) form).isAssetsLoaded()) {
                return b.REPL_ASSET;
            }
            return b.ASSET;
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static String m14hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        String str2;
        if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.containsKey(str)) {
            String[] list = form.getAssets().list("");
            int length = Array.getLength(list);
            int i = 0;
            while (true) {
                if (i >= length) {
                    str2 = null;
                    break;
                }
                str2 = list[i];
                if (str2.equalsIgnoreCase(str)) {
                    break;
                }
                i++;
            }
            if (str2 == null) {
                return null;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(str, str2);
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str);
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static InputStream m12hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        try {
            return form.getAssets().open(str);
        } catch (IOException e) {
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                return form.getAssets().open(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
            }
            throw e;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.appinventor.components.runtime.util.MediaUtil$b[] r0 = com.google.appinventor.components.runtime.util.MediaUtil.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = r0
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.ASSET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.INTERNAL_APP_FILES_DIRECTORY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.EXTERNAL_APP_FILES_DIRECTORY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.REPL_ASSET     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.SDCARD     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.FILE_URL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.URL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.CONTENT_URI     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.CONTACT_URI     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.MediaUtil.AnonymousClass3.<clinit>():void");
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static InputStream m13hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str, b bVar) throws IOException {
        switch (AnonymousClass3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt[bVar.ordinal()]) {
            case 1:
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
            case 2:
            case 3:
                return new FileInputStream(str);
            case 4:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return new FileInputStream(new File(URI.create(form.getAssetPath(str))));
            case 5:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return new FileInputStream(str);
            case 6:
                if (isExternalFileUrl(form, str) && RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                    break;
                }
            case 7:
                break;
            case 8:
                return form.getContentResolver().openInputStream(Uri.parse(str));
            case 9:
                InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(form.getContentResolver(), Uri.parse(str));
                if (openContactPhotoInputStream != null) {
                    return openContactPhotoInputStream;
                }
                throw new IOException("Unable to open contact photo " + str + ".");
            default:
                throw new IOException("Unable to open media " + str + ".");
        }
        return new URL(str).openStream();
    }

    public static InputStream openMedia(Form form, String str) throws IOException {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str));
    }

    public static File copyMediaToTempFile(Form form, String str) throws IOException {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str));
    }

    private static File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str, b bVar) throws IOException {
        InputStream hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str, bVar);
        File file = null;
        try {
            file = File.createTempFile("AI_Media_", (String) null);
            file.deleteOnExit();
            FileUtil.writeStreamToFile(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, file.getAbsolutePath());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.close();
            if (file != null) {
                file.delete();
            }
            return file;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.close();
            if (file != null) {
                file.delete();
            }
            throw th;
        }
    }

    private static File B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Form form, String str, b bVar) throws IOException {
        Map<String, File> map = tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
        File file = map.get(str);
        if (file != null && file.exists()) {
            return file;
        }
        Log.i("MediaUtil", "Copying media " + str + " to temp file...");
        File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str, bVar);
        Log.i("MediaUtil", "Finished copying media " + str + " to temp file " + hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getAbsolutePath());
        map.put(str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
    }

    public static BitmapDrawable getBitmapDrawable(Form form, String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (form.highQuality) {
            return (BitmapDrawable) getHighQualityImage(form, str);
        }
        final Synchronizer synchronizer = new Synchronizer();
        getBitmapDrawableAsync(form, str, new AsyncCallbackPair<BitmapDrawable>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                synchronizer.wakeup((BitmapDrawable) obj);
            }

            public final void onFailure(String str) {
                synchronizer.error(str);
            }
        });
        synchronizer.waitfor();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) synchronizer.getResult();
        if (bitmapDrawable != null) {
            return bitmapDrawable;
        }
        String error = synchronizer.getError();
        if (error.startsWith("PERMISSION_DENIED:")) {
            throw new PermissionException(error.split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)[1]);
        }
        throw new IOException(error);
    }

    public static void getBitmapDrawableAsync(final Form form, final String str, final AsyncCallbackPair<BitmapDrawable> asyncCallbackPair) {
        if (str == null || str.length() == 0) {
            asyncCallbackPair.onSuccess(null);
            return;
        }
        final b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
        AsynchUtil.runAsynchronously(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:65:0x016b A[Catch:{ all -> 0x01be }] */
            /* JADX WARNING: Removed duplicated region for block: B:74:0x0196  */
            /* JADX WARNING: Removed duplicated region for block: B:91:0x01db A[SYNTHETIC, Splitter:B:91:0x01db] */
            /* JADX WARNING: Removed duplicated region for block: B:99:0x01e9 A[SYNTHETIC, Splitter:B:99:0x01e9] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void run() {
                /*
                    r9 = this;
                    java.lang.String r0 = "Unexpected error on close"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    java.lang.String r2 = "mediaPath = "
                    r1.<init>(r2)
                    java.lang.String r2 = r3
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "MediaUtil"
                    android.util.Log.d(r2, r1)
                    java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
                    r1.<init>()
                    r3 = 4096(0x1000, float:5.74E-42)
                    byte[] r3 = new byte[r3]
                    r4 = 0
                    com.google.appinventor.components.runtime.Form r5 = r2     // Catch:{ PermissionException -> 0x01c1, Exception -> 0x0163 }
                    java.lang.String r6 = r3     // Catch:{ PermissionException -> 0x01c1, Exception -> 0x0163 }
                    com.google.appinventor.components.runtime.util.MediaUtil$b r7 = r0     // Catch:{ PermissionException -> 0x01c1, Exception -> 0x0163 }
                    java.io.InputStream r5 = com.google.appinventor.components.runtime.util.MediaUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((com.google.appinventor.components.runtime.Form) r5, (java.lang.String) r6, (com.google.appinventor.components.runtime.util.MediaUtil.b) r7)     // Catch:{ PermissionException -> 0x01c1, Exception -> 0x0163 }
                L_0x002b:
                    int r6 = r5.read(r3)     // Catch:{ PermissionException -> 0x015d, Exception -> 0x015b }
                    r7 = 0
                    if (r6 <= 0) goto L_0x0036
                    r1.write(r3, r7, r6)     // Catch:{ PermissionException -> 0x015d, Exception -> 0x015b }
                    goto L_0x002b
                L_0x0036:
                    byte[] r3 = r1.toByteArray()     // Catch:{ PermissionException -> 0x015d, Exception -> 0x015b }
                    if (r5 == 0) goto L_0x0044
                    r5.close()     // Catch:{ Exception -> 0x0040 }
                    goto L_0x0044
                L_0x0040:
                    r4 = move-exception
                    android.util.Log.w(r2, r0, r4)
                L_0x0044:
                    r1.close()     // Catch:{ Exception -> 0x0047 }
                L_0x0047:
                    java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
                    r1.<init>(r3)
                    int r3 = r3.length
                    r1.mark(r3)     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r3 = r2     // Catch:{ Exception -> 0x012e }
                    java.lang.String r4 = r3     // Catch:{ Exception -> 0x012e }
                    android.graphics.BitmapFactory$Options r3 = com.google.appinventor.components.runtime.util.MediaUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r3, (java.io.InputStream) r1, (java.lang.String) r4)     // Catch:{ Exception -> 0x012e }
                    r1.reset()     // Catch:{ Exception -> 0x012e }
                    android.graphics.drawable.BitmapDrawable r4 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r5 = r2     // Catch:{ Exception -> 0x012e }
                    android.content.res.Resources r5 = r5.getResources()     // Catch:{ Exception -> 0x012e }
                    android.graphics.Bitmap r6 = com.google.appinventor.components.runtime.util.MediaUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.io.InputStream) r1, (android.graphics.BitmapFactory.Options) r3)     // Catch:{ Exception -> 0x012e }
                    r4.<init>(r5, r6)     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r5 = r2     // Catch:{ Exception -> 0x012e }
                    android.content.res.Resources r5 = r5.getResources()     // Catch:{ Exception -> 0x012e }
                    android.util.DisplayMetrics r5 = r5.getDisplayMetrics()     // Catch:{ Exception -> 0x012e }
                    r4.setTargetDensity(r5)     // Catch:{ Exception -> 0x012e }
                    boolean r5 = com.google.appinventor.components.runtime.util.MediaUtil.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG     // Catch:{ Exception -> 0x012e }
                    if (r5 != 0) goto L_0x011e
                    int r3 = r3.inSampleSize     // Catch:{ Exception -> 0x012e }
                    r5 = 1
                    if (r3 != r5) goto L_0x011e
                    com.google.appinventor.components.runtime.Form r3 = r2     // Catch:{ Exception -> 0x012e }
                    float r3 = r3.deviceDensity()     // Catch:{ Exception -> 0x012e }
                    r5 = 1065353216(0x3f800000, float:1.0)
                    int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r3 != 0) goto L_0x0090
                    goto L_0x011e
                L_0x0090:
                    com.google.appinventor.components.runtime.Form r3 = r2     // Catch:{ Exception -> 0x012e }
                    float r3 = r3.deviceDensity()     // Catch:{ Exception -> 0x012e }
                    int r5 = r4.getIntrinsicWidth()     // Catch:{ Exception -> 0x012e }
                    float r5 = (float) r5     // Catch:{ Exception -> 0x012e }
                    float r3 = r3 * r5
                    int r3 = (int) r3     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r5 = r2     // Catch:{ Exception -> 0x012e }
                    float r5 = r5.deviceDensity()     // Catch:{ Exception -> 0x012e }
                    int r6 = r4.getIntrinsicHeight()     // Catch:{ Exception -> 0x012e }
                    float r6 = (float) r6     // Catch:{ Exception -> 0x012e }
                    float r5 = r5 * r6
                    int r5 = (int) r5     // Catch:{ Exception -> 0x012e }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e }
                    java.lang.String r8 = "form.deviceDensity() = "
                    r6.<init>(r8)     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r8 = r2     // Catch:{ Exception -> 0x012e }
                    float r8 = r8.deviceDensity()     // Catch:{ Exception -> 0x012e }
                    r6.append(r8)     // Catch:{ Exception -> 0x012e }
                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x012e }
                    android.util.Log.d(r2, r6)     // Catch:{ Exception -> 0x012e }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e }
                    java.lang.String r8 = "originalBitmapDrawable.getIntrinsicWidth() = "
                    r6.<init>(r8)     // Catch:{ Exception -> 0x012e }
                    int r8 = r4.getIntrinsicWidth()     // Catch:{ Exception -> 0x012e }
                    r6.append(r8)     // Catch:{ Exception -> 0x012e }
                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x012e }
                    android.util.Log.d(r2, r6)     // Catch:{ Exception -> 0x012e }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e }
                    java.lang.String r8 = "originalBitmapDrawable.getIntrinsicHeight() = "
                    r6.<init>(r8)     // Catch:{ Exception -> 0x012e }
                    int r8 = r4.getIntrinsicHeight()     // Catch:{ Exception -> 0x012e }
                    r6.append(r8)     // Catch:{ Exception -> 0x012e }
                    java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x012e }
                    android.util.Log.d(r2, r6)     // Catch:{ Exception -> 0x012e }
                    android.graphics.Bitmap r4 = r4.getBitmap()     // Catch:{ Exception -> 0x012e }
                    android.graphics.Bitmap r3 = android.graphics.Bitmap.createScaledBitmap(r4, r3, r5, r7)     // Catch:{ Exception -> 0x012e }
                    android.graphics.drawable.BitmapDrawable r4 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r5 = r2     // Catch:{ Exception -> 0x012e }
                    android.content.res.Resources r5 = r5.getResources()     // Catch:{ Exception -> 0x012e }
                    r4.<init>(r5, r3)     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.Form r3 = r2     // Catch:{ Exception -> 0x012e }
                    android.content.res.Resources r3 = r3.getResources()     // Catch:{ Exception -> 0x012e }
                    android.util.DisplayMetrics r3 = r3.getDisplayMetrics()     // Catch:{ Exception -> 0x012e }
                    r4.setTargetDensity(r3)     // Catch:{ Exception -> 0x012e }
                    java.lang.System.gc()     // Catch:{ Exception -> 0x012e }
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r3 = r4     // Catch:{ Exception -> 0x012e }
                    r3.onSuccess(r4)     // Catch:{ Exception -> 0x012e }
                    r1.close()     // Catch:{ Exception -> 0x0119 }
                    return
                L_0x0119:
                    r1 = move-exception
                    android.util.Log.w(r2, r0, r1)
                    return
                L_0x011e:
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r3 = r4     // Catch:{ Exception -> 0x012e }
                    r3.onSuccess(r4)     // Catch:{ Exception -> 0x012e }
                    r1.close()     // Catch:{ Exception -> 0x0127 }
                    return
                L_0x0127:
                    r1 = move-exception
                    android.util.Log.w(r2, r0, r1)
                    return
                L_0x012c:
                    r3 = move-exception
                    goto L_0x0152
                L_0x012e:
                    r3 = move-exception
                    java.lang.String r4 = "Exception while loading media."
                    android.util.Log.w(r2, r4, r3)     // Catch:{ all -> 0x012c }
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r4 = r4     // Catch:{ all -> 0x012c }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x012c }
                    r5.<init>()     // Catch:{ all -> 0x012c }
                    java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x012c }
                    r5.append(r3)     // Catch:{ all -> 0x012c }
                    java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x012c }
                    r4.onFailure(r3)     // Catch:{ all -> 0x012c }
                    r1.close()     // Catch:{ Exception -> 0x014d }
                    return
                L_0x014d:
                    r1 = move-exception
                    android.util.Log.w(r2, r0, r1)
                    return
                L_0x0152:
                    r1.close()     // Catch:{ Exception -> 0x0156 }
                    goto L_0x015a
                L_0x0156:
                    r1 = move-exception
                    android.util.Log.w(r2, r0, r1)
                L_0x015a:
                    throw r3
                L_0x015b:
                    r3 = move-exception
                    goto L_0x0165
                L_0x015d:
                    r3 = move-exception
                    r4 = r5
                    goto L_0x01c2
                L_0x0160:
                    r3 = move-exception
                    goto L_0x01e7
                L_0x0163:
                    r3 = move-exception
                    r5 = r4
                L_0x0165:
                    com.google.appinventor.components.runtime.util.MediaUtil$b r6 = r0     // Catch:{ all -> 0x01be }
                    com.google.appinventor.components.runtime.util.MediaUtil$b r7 = com.google.appinventor.components.runtime.util.MediaUtil.b.CONTACT_URI     // Catch:{ all -> 0x01be }
                    if (r6 != r7) goto L_0x0196
                    android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch:{ all -> 0x01be }
                    com.google.appinventor.components.runtime.Form r6 = r2     // Catch:{ all -> 0x01be }
                    android.content.res.Resources r6 = r6.getResources()     // Catch:{ all -> 0x01be }
                    com.google.appinventor.components.runtime.Form r7 = r2     // Catch:{ all -> 0x01be }
                    android.content.res.Resources r7 = r7.getResources()     // Catch:{ all -> 0x01be }
                    r8 = 17301606(0x1080066, float:2.497954E-38)
                    android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r7, r8, r4)     // Catch:{ all -> 0x01be }
                    r3.<init>(r6, r4)     // Catch:{ all -> 0x01be }
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r4 = r4     // Catch:{ all -> 0x01be }
                    r4.onSuccess(r3)     // Catch:{ all -> 0x01be }
                    if (r5 == 0) goto L_0x0192
                    r5.close()     // Catch:{ Exception -> 0x018e }
                    goto L_0x0192
                L_0x018e:
                    r3 = move-exception
                    android.util.Log.w(r2, r0, r3)
                L_0x0192:
                    r1.close()     // Catch:{ Exception -> 0x0195 }
                L_0x0195:
                    return
                L_0x0196:
                    java.lang.String r4 = "Exception reading file."
                    android.util.Log.d(r2, r4, r3)     // Catch:{ all -> 0x01be }
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r4 = r4     // Catch:{ all -> 0x01be }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01be }
                    r6.<init>()     // Catch:{ all -> 0x01be }
                    java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x01be }
                    r6.append(r3)     // Catch:{ all -> 0x01be }
                    java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x01be }
                    r4.onFailure(r3)     // Catch:{ all -> 0x01be }
                    if (r5 == 0) goto L_0x01ba
                    r5.close()     // Catch:{ Exception -> 0x01b6 }
                    goto L_0x01ba
                L_0x01b6:
                    r3 = move-exception
                    android.util.Log.w(r2, r0, r3)
                L_0x01ba:
                    r1.close()     // Catch:{ Exception -> 0x01bd }
                L_0x01bd:
                    return
                L_0x01be:
                    r3 = move-exception
                    r4 = r5
                    goto L_0x01e7
                L_0x01c1:
                    r3 = move-exception
                L_0x01c2:
                    com.google.appinventor.components.runtime.util.AsyncCallbackPair r5 = r4     // Catch:{ all -> 0x0160 }
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
                    java.lang.String r7 = "PERMISSION_DENIED:"
                    r6.<init>(r7)     // Catch:{ all -> 0x0160 }
                    java.lang.String r3 = r3.getPermissionNeeded()     // Catch:{ all -> 0x0160 }
                    r6.append(r3)     // Catch:{ all -> 0x0160 }
                    java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x0160 }
                    r5.onFailure(r3)     // Catch:{ all -> 0x0160 }
                    if (r4 == 0) goto L_0x01e3
                    r4.close()     // Catch:{ Exception -> 0x01df }
                    goto L_0x01e3
                L_0x01df:
                    r3 = move-exception
                    android.util.Log.w(r2, r0, r3)
                L_0x01e3:
                    r1.close()     // Catch:{ Exception -> 0x01e6 }
                L_0x01e6:
                    return
                L_0x01e7:
                    if (r4 == 0) goto L_0x01f1
                    r4.close()     // Catch:{ Exception -> 0x01ed }
                    goto L_0x01f1
                L_0x01ed:
                    r4 = move-exception
                    android.util.Log.w(r2, r0, r4)
                L_0x01f1:
                    r1.close()     // Catch:{ Exception -> 0x01f4 }
                L_0x01f4:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.MediaUtil.AnonymousClass2.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public static Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(InputStream inputStream, BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(new a(inputStream), (Rect) null, options);
    }

    static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        public final long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    private static AssetFileDescriptor hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        try {
            return form.getAssets().openFd(str);
        } catch (IOException e) {
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                return form.getAssets().openFd(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
            }
            throw e;
        }
    }

    public static int loadSoundPool(SoundPool soundPool, Form form, String str) throws IOException {
        b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
        if (b.EXTERNAL_APP_FILES_DIRECTORY.equals(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2)) {
            if (str.startsWith("file:")) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = b.FILE_URL;
            } else {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = b.SDCARD;
            }
        }
        int i = AnonymousClass3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.ordinal()];
        if (i == 1) {
            return soundPool.load(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str), 1);
        }
        String str2 = "android.permission.READ_MEDIA_AUDIO";
        switch (i) {
            case 4:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return soundPool.load(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(form.getAssetPath(str)), 1);
            case 5:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    if (Build.VERSION.SDK_INT < 33) {
                        str2 = "android.permission.READ_EXTERNAL_STORAGE";
                    }
                    form.assertPermission(str2);
                }
                return soundPool.load(str, 1);
            case 6:
                if (isExternalFileUrl(form, str) || RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    if (Build.VERSION.SDK_INT < 33) {
                        str2 = "android.permission.READ_EXTERNAL_STORAGE";
                    }
                    form.assertPermission(str2);
                }
                return soundPool.load(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str), 1);
            case 7:
            case 8:
                return soundPool.load(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(form, str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).getAbsolutePath(), 1);
            case 9:
                throw new IOException("Unable to load audio for contact " + str + ".");
            default:
                throw new IOException("Unable to load audio " + str + ".");
        }
    }

    public static void loadMediaPlayer(MediaPlayer mediaPlayer, Form form, String str) throws IOException {
        String str2 = "android.permission.READ_MEDIA_AUDIO";
        switch (AnonymousClass3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str).ordinal()]) {
            case 1:
                AssetFileDescriptor openFd = form.getAssets().openFd(str);
                try {
                    mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    return;
                } finally {
                    openFd.close();
                }
            case 3:
                mediaPlayer.setDataSource(new FileInputStream(str).getFD());
                return;
            case 4:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                mediaPlayer.setDataSource(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(form.getAssetPath(str)));
                return;
            case 5:
                if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    if (Build.VERSION.SDK_INT < 33) {
                        str2 = "android.permission.READ_EXTERNAL_STORAGE";
                    }
                    form.assertPermission(str2);
                }
                mediaPlayer.setDataSource(str);
                return;
            case 6:
                if (isExternalFileUrl(form, str) || RUtil.needsFilePermission(form, str, (FileScope) null)) {
                    if (Build.VERSION.SDK_INT < 33) {
                        str2 = "android.permission.READ_EXTERNAL_STORAGE";
                    }
                    form.assertPermission(str2);
                }
                mediaPlayer.setDataSource(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str));
                return;
            case 7:
                mediaPlayer.setDataSource(str);
                return;
            case 8:
                mediaPlayer.setDataSource(form, Uri.parse(str));
                return;
            case 9:
                throw new IOException("Unable to load audio or video for contact " + str + ".");
            default:
                throw new IOException("Unable to load audio or video " + str + ".");
        }
    }

    public static void loadVideoView(VideoView videoView, Form form, String str) throws IOException {
        b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str);
        int i = AnonymousClass3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.ordinal()];
        if (i != 1) {
            String str2 = "android.permission.READ_MEDIA_AUDIO";
            switch (i) {
                case 4:
                    if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                        form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                    }
                    videoView.setVideoPath(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(form.getAssetPath(str)));
                    return;
                case 5:
                    if (RUtil.needsFilePermission(form, str, (FileScope) null)) {
                        if (Build.VERSION.SDK_INT < 33) {
                            str2 = "android.permission.READ_EXTERNAL_STORAGE";
                        }
                        form.assertPermission(str2);
                    }
                    videoView.setVideoPath(str);
                    return;
                case 6:
                    if (isExternalFileUrl(form, str) || RUtil.needsFilePermission(form, str, (FileScope) null)) {
                        if (Build.VERSION.SDK_INT < 33) {
                            str2 = "android.permission.READ_EXTERNAL_STORAGE";
                        }
                        form.assertPermission(str2);
                    }
                    videoView.setVideoPath(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str));
                    return;
                case 7:
                    videoView.setVideoURI(Uri.parse(str));
                    return;
                case 8:
                    videoView.setVideoURI(Uri.parse(str));
                    return;
                case 9:
                    throw new IOException("Unable to load video for contact " + str + ".");
                default:
                    throw new IOException("Unable to load video " + str + ".");
            }
        } else {
            videoView.setVideoPath(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(form, str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).getAbsolutePath());
        }
    }

    public static String getAssetFilePath(Form form, String str) {
        if (!(form instanceof ReplForm) || !((ReplForm) form).isAssetsLoaded()) {
            return str;
        }
        return QUtil.getReplAssetPath(form) + str;
    }

    private static Drawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, Drawable drawable) {
        int i;
        int i2;
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            bitmapDrawable.setTargetDensity(form.getResources().getDisplayMetrics());
            if (Form.getCompatibilityMode()) {
                i = 720;
                i2 = 840;
            } else {
                int intrinsicHeight = (int) (((float) bitmapDrawable.getIntrinsicHeight()) * form.deviceDensity());
                i2 = (int) (((float) bitmapDrawable.getIntrinsicWidth()) * form.deviceDensity());
                i = intrinsicHeight;
            }
            bitmapDrawable.setBounds(0, 0, i, i2);
            return bitmapDrawable;
        } catch (Exception e) {
            Log.e("MediaUtil", String.valueOf(e));
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v10, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v17, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045 A[SYNTHETIC, Splitter:B:21:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056 A[SYNTHETIC, Splitter:B:27:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x012a A[SYNTHETIC, Splitter:B:87:0x012a] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x013c A[SYNTHETIC, Splitter:B:92:0x013c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable getHighQualityImage(com.google.appinventor.components.runtime.Form r5, java.lang.String r6) {
        /*
            com.google.appinventor.components.runtime.util.MediaUtil$b r0 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (java.lang.String) r6)
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.ASSET
            r2 = 0
            java.lang.String r3 = "MediaUtil"
            if (r0 != r1) goto L_0x0063
            android.content.res.AssetManager r0 = r5.getAssets()     // Catch:{ Exception -> 0x003a }
            java.lang.String r6 = getAssetFilePath(r5, r6)     // Catch:{ Exception -> 0x003a }
            java.io.InputStream r6 = r0.open(r6)     // Catch:{ Exception -> 0x003a }
            android.graphics.drawable.Drawable r2 = android.graphics.drawable.Drawable.createFromStream(r6, r2)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            android.graphics.drawable.Drawable r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (android.graphics.drawable.Drawable) r2)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            if (r6 == 0) goto L_0x0139
            r6.close()     // Catch:{ Exception -> 0x0026 }
            goto L_0x0139
        L_0x0026:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x0030:
            r5 = move-exception
            r2 = r6
            goto L_0x0054
        L_0x0033:
            r5 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L_0x003c
        L_0x0038:
            r5 = move-exception
            goto L_0x0054
        L_0x003a:
            r5 = move-exception
            r6 = r2
        L_0x003c:
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0038 }
            android.util.Log.e(r3, r5)     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x0051
            r2.close()     // Catch:{ Exception -> 0x0049 }
            goto L_0x0051
        L_0x0049:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
        L_0x0051:
            r2 = r6
            goto L_0x0139
        L_0x0054:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ Exception -> 0x005a }
            goto L_0x0062
        L_0x005a:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r3, r6)
        L_0x0062:
            throw r5
        L_0x0063:
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.REPL_ASSET
            if (r0 != r1) goto L_0x007f
            java.lang.String r6 = getAssetFilePath(r5, r6)     // Catch:{ Exception -> 0x0075 }
            android.graphics.drawable.Drawable r2 = android.graphics.drawable.Drawable.createFromPath(r6)     // Catch:{ Exception -> 0x0075 }
            android.graphics.drawable.Drawable r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (android.graphics.drawable.Drawable) r2)     // Catch:{ Exception -> 0x0075 }
            goto L_0x0139
        L_0x0075:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x007f:
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.SDCARD
            if (r0 != r1) goto L_0x009f
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6)     // Catch:{ Exception -> 0x0095 }
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x0095 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0095 }
            android.graphics.drawable.Drawable r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (android.graphics.drawable.Drawable) r0)     // Catch:{ Exception -> 0x0092 }
            goto L_0x0139
        L_0x0092:
            r5 = move-exception
            r2 = r0
            goto L_0x0096
        L_0x0095:
            r5 = move-exception
        L_0x0096:
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x009f:
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.CONTENT_URI
            if (r0 != r1) goto L_0x00c7
            android.content.ContentResolver r0 = r5.getContentResolver()     // Catch:{ Exception -> 0x00bd }
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ Exception -> 0x00bd }
            android.graphics.Bitmap r6 = android.provider.MediaStore.Images.Media.getBitmap(r0, r6)     // Catch:{ Exception -> 0x00bd }
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x00bd }
            r0.<init>(r6)     // Catch:{ Exception -> 0x00bd }
            android.graphics.drawable.Drawable r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (android.graphics.drawable.Drawable) r0)     // Catch:{ Exception -> 0x00ba }
            goto L_0x0139
        L_0x00ba:
            r5 = move-exception
            r2 = r0
            goto L_0x00be
        L_0x00bd:
            r5 = move-exception
        L_0x00be:
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x00c7:
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.FILE_URL
            if (r0 == r1) goto L_0x00f3
            com.google.appinventor.components.runtime.util.MediaUtil$b r1 = com.google.appinventor.components.runtime.util.MediaUtil.b.URL
            if (r0 != r1) goto L_0x00d0
            goto L_0x00f3
        L_0x00d0:
            com.google.appinventor.components.runtime.util.MediaUtil$b r6 = com.google.appinventor.components.runtime.util.MediaUtil.b.CONTACT_URI
            if (r0 != r6) goto L_0x0139
            android.graphics.drawable.BitmapDrawable r6 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x00ea }
            android.content.res.Resources r0 = r5.getResources()     // Catch:{ Exception -> 0x00ea }
            android.content.res.Resources r5 = r5.getResources()     // Catch:{ Exception -> 0x00ea }
            r1 = 17301606(0x1080066, float:2.497954E-38)
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeResource(r5, r1, r2)     // Catch:{ Exception -> 0x00ea }
            r6.<init>(r0, r5)     // Catch:{ Exception -> 0x00ea }
            goto L_0x0051
        L_0x00ea:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x00f3:
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x011f }
            r0.<init>(r6)     // Catch:{ Exception -> 0x011f }
            java.lang.Object r6 = r0.getContent()     // Catch:{ Exception -> 0x011f }
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ Exception -> 0x011f }
            android.graphics.drawable.Drawable r2 = android.graphics.drawable.Drawable.createFromStream(r6, r2)     // Catch:{ Exception -> 0x0118, all -> 0x0115 }
            android.graphics.drawable.Drawable r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Form) r5, (android.graphics.drawable.Drawable) r2)     // Catch:{ Exception -> 0x0118, all -> 0x0115 }
            if (r6 == 0) goto L_0x0139
            r6.close()     // Catch:{ Exception -> 0x010c }
            goto L_0x0139
        L_0x010c:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0139
        L_0x0115:
            r5 = move-exception
            r2 = r6
            goto L_0x013a
        L_0x0118:
            r5 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L_0x0121
        L_0x011d:
            r5 = move-exception
            goto L_0x013a
        L_0x011f:
            r5 = move-exception
            r6 = r2
        L_0x0121:
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x011d }
            android.util.Log.e(r3, r5)     // Catch:{ all -> 0x011d }
            if (r2 == 0) goto L_0x0051
            r2.close()     // Catch:{ Exception -> 0x012f }
            goto L_0x0051
        L_0x012f:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            android.util.Log.e(r3, r5)
            goto L_0x0051
        L_0x0139:
            return r2
        L_0x013a:
            if (r2 == 0) goto L_0x0148
            r2.close()     // Catch:{ Exception -> 0x0140 }
            goto L_0x0148
        L_0x0140:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r3, r6)
        L_0x0148:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.MediaUtil.getHighQualityImage(com.google.appinventor.components.runtime.Form, java.lang.String):android.graphics.drawable.Drawable");
    }

    @Deprecated
    public static boolean isExternalFileUrl(String str) {
        Log.w("MediaUtil", "Calling deprecated version of isExternalFileUrl", new IllegalAccessException());
        StringBuilder sb = new StringBuilder("file://");
        sb.append(QUtil.getExternalStoragePath(Form.getActiveForm()));
        return str.startsWith(sb.toString()) || str.startsWith("file:///sdcard/");
    }

    public static boolean isExternalFileUrl(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            return false;
        }
        if (str.startsWith("file://" + QUtil.getExternalStorageDir(context)) || str.startsWith("file:///sdcard")) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean isExternalFile(String str) {
        Log.w("MediaUtil", "Calling deprecated version of isExternalFile", new IllegalAccessException());
        return str.startsWith(QUtil.getExternalStoragePath(Form.getActiveForm())) || str.startsWith("/sdcard/") || isExternalFileUrl(Form.getActiveForm(), str);
    }

    public static boolean isExternalFile(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            return false;
        }
        if (str.startsWith(QUtil.getExternalStoragePath(context)) || str.startsWith("/sdcard/") || isExternalFileUrl(context, str)) {
            return true;
        }
        return false;
    }

    static /* synthetic */ BitmapFactory.Options hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, InputStream inputStream, String str) {
        int i;
        int i2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(inputStream, options);
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        Display defaultDisplay = ((WindowManager) form.getSystemService("window")).getDefaultDisplay();
        if (Form.getCompatibilityMode()) {
            i = 720;
            i2 = 840;
        } else {
            int width = (int) (((float) defaultDisplay.getWidth()) / form.deviceDensity());
            i2 = (int) (((float) defaultDisplay.getHeight()) / form.deviceDensity());
            i = width;
        }
        while (i4 / i3 > i && i5 / i3 > i2) {
            i3 <<= 1;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        Log.d("MediaUtil", "getBitmapOptions: sampleSize = " + i3 + " mediaPath = " + str + " maxWidth = " + i + " maxHeight = " + i2 + " display width = " + defaultDisplay.getWidth() + " display height = " + defaultDisplay.getHeight());
        options2.inSampleSize = i3;
        return options2;
    }
}
