package com.google.appinventor.components.runtime;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;

@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/wallpaper.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions({"android.permission.WRITE_SETTINGS", "android.permission.SET_WALLPAPER", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class Wallpaper extends AndroidNonvisibleComponent implements Component {
    private String NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = "wallpaperPicture.png";
    private ComponentContainer container;
    private Context context;
    private Form form;
    private WallpaperManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public Wallpaper(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = WallpaperManager.getInstance(this.context);
        Log.d("Wallpaper", "Wallpaper Created");
    }

    @SimpleFunction(description = "Remove any currently set system wallpaper, reverting to the system's built-in wallpaper.")
    public void Clear() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clear();
            WallpaperCleared();
            Log.i("Wallpaper", "Wallpaper cleared");
        } catch (Exception e) {
            Log.e("Wallpaper", String.valueOf(e));
        }
    }

    @SimpleEvent(description = "Event to detect that the user has cleared/deleted the wallpaper.")
    public void WallpaperCleared() {
        EventDispatcher.dispatchEvent(this, "WallpaperCleared", new Object[0]);
    }

    @SimpleFunction(description = "Change the current system wallpaper.")
    public void SetWallpaper(String str) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, false);
    }

    @SimpleFunction(description = "Change the current lock screen wallpaper. This block works only on devices with Android 7+.")
    public void SetLockScreenWallpaper(String str) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, true);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, boolean z) {
        if (str != null && !str.isEmpty()) {
            try {
                new b(z).execute(new String[]{str});
            } catch (Exception e) {
                Log.e("Wallpaper", String.valueOf(e));
            }
        }
    }

    class b extends AsyncTask<String, String, String> {
        private boolean tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            if (((String) obj).equals("true")) {
                Wallpaper.this.WallpaperChanged(true);
            } else {
                Wallpaper.this.WallpaperChanged(false);
            }
        }

        b(boolean z) {
            this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = z;
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            try {
                Bitmap bitmap = MediaUtil.getBitmapDrawable(Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Wallpaper.this), strArr[0]).getBitmap();
                if (!this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA) {
                    Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Wallpaper.this).setBitmap(bitmap);
                } else if (Build.VERSION.SDK_INT < 24) {
                    return "false";
                } else {
                    Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Wallpaper.this).setBitmap(bitmap, (Rect) null, false, 2);
                }
                Log.i("Wallpaper", "Wallpaper changed successful");
                return "true";
            } catch (Exception e) {
                Log.e("Wallpaper", String.valueOf(e));
                return "false";
            }
        }
    }

    @SimpleEvent(description = "Event to detect that the user has changed the wallpaper. This event will be invoked by the \"Set Wallpaper\" function.")
    public void WallpaperChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "WallpaperChanged", Boolean.valueOf(z));
    }

    @SimpleProperty(description = "Returns the desired minimum height for the wallpaper.")
    public int DesiredMinimumHeight() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDesiredMinimumHeight();
    }

    @SimpleProperty(description = "Returns the desired minimum width for the wallpaper.")
    public int DesiredMinimumWidth() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDesiredMinimumWidth();
    }

    @SimpleProperty(description = "Returns whether the calling package is allowed to set the wallpaper for the calling user. This block works only on devices with Android 7+.")
    public boolean IsSetWallpaperAllowed() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSetWallpaperAllowed();
        }
        return false;
    }

    @SimpleProperty(description = "Returns whether wallpapers are supported for the calling user. This block works only on devices with Android 6+.")
    public boolean IsWallpaperSupported() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isWallpaperSupported();
        }
        return false;
    }

    @SimpleFunction(description = "Reset all wallpaper to the factory default. This block works only on devices with Android 9+.")
    public void ClearWallpaper() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clearWallpaper();
                WallpaperCleared();
                Log.i("Wallpaper", "Wallpaper cleared");
            }
        } catch (Exception e) {
            Log.e("Wallpaper", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Retrieve the current system wallpaper; if no wallpaper is set, the system built-in static wallpaper is returned.")
    public void GetWallpaper() {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    new a(Wallpaper.this, (byte) 0).execute(new String[]{""});
                } else {
                    Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Wallpaper.this).dispatchPermissionDeniedEvent((Component) Wallpaper.this, "GetWallpaper", str);
                }
            }
        });
    }

    @SimpleEvent(description = "Event to detect that the component got the current system wallpaper.")
    public void GotWallpaper(String str) {
        EventDispatcher.dispatchEvent(this, "GotWallpaper", str);
    }

    class a extends AsyncTask<String, String, String> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Wallpaper.this.GotWallpaper((String) obj);
        }

        private a() {
        }

        /* synthetic */ a(Wallpaper wallpaper, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Wallpaper wallpaper = Wallpaper.this;
            return wallpaper.SaveUtil(Wallpaper.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(wallpaper).getDrawable());
        }
    }

    @DesignerProperty(defaultValue = "wallpaperPicture.png", editorType = "string")
    @SimpleProperty(description = "After the user clicked on \"Get Wallpaper\" this will be the name for the wallpaper picture.")
    public void SaveWallpaperAs(String str) {
        if (str == null || str.isEmpty()) {
            this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = "wallpaperPicture.png";
        } else {
            this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa = str;
        }
        Log.i("Wallpaper", "Wallpaper save name is: " + this.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073 A[SYNTHETIC, Splitter:B:23:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084 A[SYNTHETIC, Splitter:B:29:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095 A[SYNTHETIC, Splitter:B:35:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9 A[SYNTHETIC, Splitter:B:40:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x006a=Splitter:B:20:0x006a, B:26:0x007b=Splitter:B:26:0x007b, B:32:0x008c=Splitter:B:32:0x008c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String SaveUtil(android.graphics.drawable.Drawable r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Wallpaper"
            r1 = 0
            android.graphics.drawable.BitmapDrawable r6 = (android.graphics.drawable.BitmapDrawable) r6     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            android.graphics.Bitmap r6 = r6.getBitmap()     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r2.<init>()     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r4 = 0
            r6.compress(r3, r4, r2)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.io.File r6 = new java.io.File     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r3.<init>()     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            android.content.Context r4 = r5.context     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.io.File r4 = com.google.appinventor.components.runtime.util.QUtil.getExternalStorageDir(r4)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.lang.String r4 = r5.NKl0wOol24QsTInLTCDxIHyqeqvhGzhrtbHVkm7sQvkPq9BLF5nrPQlR8efylAFa     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.lang.String r3 = r3.toString()     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r6.<init>(r3)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            r3.<init>(r6)     // Catch:{ PermissionException -> 0x008b, FileNotFoundException -> 0x007a, Exception -> 0x0069 }
            byte[] r1 = r2.toByteArray()     // Catch:{ PermissionException -> 0x0064, FileNotFoundException -> 0x0061, Exception -> 0x005e, all -> 0x005b }
            r3.write(r1)     // Catch:{ PermissionException -> 0x0064, FileNotFoundException -> 0x0061, Exception -> 0x005e, all -> 0x005b }
            r3.flush()     // Catch:{ PermissionException -> 0x0064, FileNotFoundException -> 0x0061, Exception -> 0x005e, all -> 0x005b }
            r3.close()     // Catch:{ PermissionException -> 0x0064, FileNotFoundException -> 0x0061, Exception -> 0x005e, all -> 0x005b }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ PermissionException -> 0x0064, FileNotFoundException -> 0x0061, Exception -> 0x005e, all -> 0x005b }
            r3.flush()     // Catch:{ Exception -> 0x0052 }
            r3.close()     // Catch:{ Exception -> 0x0052 }
            goto L_0x005a
        L_0x0052:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x005a:
            return r6
        L_0x005b:
            r6 = move-exception
            r1 = r3
            goto L_0x00a7
        L_0x005e:
            r6 = move-exception
            r1 = r3
            goto L_0x006a
        L_0x0061:
            r6 = move-exception
            r1 = r3
            goto L_0x007b
        L_0x0064:
            r6 = move-exception
            r1 = r3
            goto L_0x008c
        L_0x0067:
            r6 = move-exception
            goto L_0x00a7
        L_0x0069:
            r6 = move-exception
        L_0x006a:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0067 }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x00a4
            r1.flush()     // Catch:{ Exception -> 0x009c }
            r1.close()     // Catch:{ Exception -> 0x009c }
            goto L_0x00a4
        L_0x007a:
            r6 = move-exception
        L_0x007b:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0067 }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x00a4
            r1.flush()     // Catch:{ Exception -> 0x009c }
            r1.close()     // Catch:{ Exception -> 0x009c }
            goto L_0x00a4
        L_0x008b:
            r6 = move-exception
        L_0x008c:
            com.google.appinventor.components.runtime.Form r2 = r5.form     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "GetWallpaper"
            r2.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r3, (com.google.appinventor.components.runtime.errors.PermissionException) r6)     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x00a4
            r1.flush()     // Catch:{ Exception -> 0x009c }
            r1.close()     // Catch:{ Exception -> 0x009c }
            goto L_0x00a4
        L_0x009c:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r0, r6)
        L_0x00a4:
            java.lang.String r6 = "ERROR"
            return r6
        L_0x00a7:
            if (r1 == 0) goto L_0x00b8
            r1.flush()     // Catch:{ Exception -> 0x00b0 }
            r1.close()     // Catch:{ Exception -> 0x00b0 }
            goto L_0x00b8
        L_0x00b0:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00b8:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Wallpaper.SaveUtil(android.graphics.drawable.Drawable):java.lang.String");
    }
}
