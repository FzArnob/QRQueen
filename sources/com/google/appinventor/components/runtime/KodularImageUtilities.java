package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.QUtil;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;

@DesignerComponent(category = ComponentCategory.UTILITIES, description = "write in ode", iconName = "images/imageUtilities.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE"})
public class KodularImageUtilities extends AndroidNonvisibleComponent implements Component {
    private Activity activity;
    private final Handler androidUIHandler = new Handler();
    private Form form;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private boolean isCompanion = false;

    public KodularImageUtilities(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        this.activity = componentContainer.$context();
        if (this.form instanceof ReplForm) {
            this.isCompanion = true;
        }
        Log.d("Kodular Image Utilities", "Kodular Image Utilities Created");
    }

    @SimpleFunction(description = "Load a new image from the given path to any component. You can load also images from the internet. Supported components: Image, Buttons, Layouts, Canvas.")
    public void LoadImageAsync(final AndroidViewComponent androidViewComponent, final String str) {
        if (this.havePermission || (!str.startsWith("http://") && !str.startsWith("https://") && !str.startsWith("HTTP://") && !str.startsWith("HTTPS://"))) {
            new a(androidViewComponent.getView()).execute(new String[]{str});
            return;
        }
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this).askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            boolean unused = KodularImageUtilities.this.havePermission = true;
                            KodularImageUtilities.this.LoadImageAsync(androidViewComponent, str);
                            return;
                        }
                        boolean unused2 = KodularImageUtilities.this.havePermission = false;
                        KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this).dispatchPermissionDeniedEvent((Component) KodularImageUtilities.this, "LoadImageAsync", "android.permission.READ_EXTERNAL_STORAGE");
                    }
                });
            }
        });
    }

    class a extends AsyncTask<String, Void, Bitmap> {
        private View B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            String str = ((String[]) objArr)[0];
            if (str.startsWith("http://") || str.startsWith("https://") || str.startsWith("HTTP://") || str.startsWith("HTTPS://")) {
                return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str);
            }
            if (str.startsWith("file:///")) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str.substring(7));
            }
            if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str);
            }
            if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || str.startsWith("file:///")) {
                Log.w("Kodular Image Utilities", "The image util was not able to load the given image.");
                return null;
            } else if (!KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this)) {
                return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str);
            } else {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(QUtil.getReplAssetPath(KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this)) + str);
            }
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            if (bitmap != null) {
                View view = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
                if (view instanceof AppCompatImageView) {
                    ((AppCompatImageView) view).setImageBitmap(bitmap);
                } else if (view instanceof ImageView) {
                    ((ImageView) view).setImageBitmap(bitmap);
                } else if ((view instanceof Button) || (view instanceof ScrollView) || (view instanceof HorizontalScrollView) || (view instanceof ViewGroup) || (view instanceof Canvas.CanvasView)) {
                    view.setBackground(new BitmapDrawable(KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this).getResources(), bitmap));
                } else {
                    Log.w("Kodular Image Utilities", "The image util did not found a supported view to load a image. The supported views are: Image, Buttons, Layouts, Canvas.");
                }
            } else {
                Log.w("Kodular Image Utilities", "The result after loading the image was NULL. Please try again and check if the path is an valid image path.");
            }
        }

        a(View view) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = view;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0031 A[SYNTHETIC, Splitter:B:18:0x0031] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0038 A[SYNTHETIC, Splitter:B:23:0x0038] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static android.graphics.Bitmap B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(java.lang.String r4) {
            /*
                java.lang.String r0 = "Kodular Image Utilities"
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0026, all -> 0x0021 }
                r2.<init>(r4)     // Catch:{ Exception -> 0x0026, all -> 0x0021 }
                java.io.InputStream r4 = r2.openStream()     // Catch:{ Exception -> 0x0026, all -> 0x0021 }
                android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch:{ Exception -> 0x001f }
                if (r4 == 0) goto L_0x0034
                r4.close()     // Catch:{ Exception -> 0x0016 }
                goto L_0x0034
            L_0x0016:
                r4 = move-exception
                java.lang.String r4 = java.lang.String.valueOf(r4)
                android.util.Log.e(r0, r4)
                goto L_0x0034
            L_0x001f:
                r2 = move-exception
                goto L_0x0028
            L_0x0021:
                r4 = move-exception
                r3 = r1
                r1 = r4
                r4 = r3
                goto L_0x0036
            L_0x0026:
                r2 = move-exception
                r4 = r1
            L_0x0028:
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0035 }
                android.util.Log.e(r0, r2)     // Catch:{ all -> 0x0035 }
                if (r4 == 0) goto L_0x0034
                r4.close()     // Catch:{ Exception -> 0x0016 }
            L_0x0034:
                return r1
            L_0x0035:
                r1 = move-exception
            L_0x0036:
                if (r4 == 0) goto L_0x0044
                r4.close()     // Catch:{ Exception -> 0x003c }
                goto L_0x0044
            L_0x003c:
                r4 = move-exception
                java.lang.String r4 = java.lang.String.valueOf(r4)
                android.util.Log.e(r0, r4)
            L_0x0044:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularImageUtilities.a.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(java.lang.String):android.graphics.Bitmap");
        }

        private Bitmap wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
            File file = new File(str);
            if (file.exists()) {
                try {
                    return BitmapFactory.decodeFile(file.getAbsolutePath());
                } catch (PermissionException e) {
                    boolean unused = KodularImageUtilities.this.havePermission = false;
                    KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities.this).dispatchPermissionDeniedEvent((Component) KodularImageUtilities.this, "LoadImageAsync", e);
                } catch (Exception e2) {
                    Log.e("Kodular Image Utilities", String.valueOf(e2));
                    return null;
                }
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0036 A[SYNTHETIC, Splitter:B:19:0x0036] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0046 A[SYNTHETIC, Splitter:B:26:0x0046] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.graphics.Bitmap vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(java.lang.String r5) {
            /*
                r4 = this;
                java.lang.String r0 = "Kodular Image Utilities"
                r1 = 0
                com.google.appinventor.components.runtime.KodularImageUtilities r2 = com.google.appinventor.components.runtime.KodularImageUtilities.this     // Catch:{ Exception -> 0x002b, all -> 0x0026 }
                android.app.Activity r2 = com.google.appinventor.components.runtime.KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.KodularImageUtilities) r2)     // Catch:{ Exception -> 0x002b, all -> 0x0026 }
                android.content.res.AssetManager r2 = r2.getAssets()     // Catch:{ Exception -> 0x002b, all -> 0x0026 }
                java.io.InputStream r5 = r2.open(r5)     // Catch:{ Exception -> 0x002b, all -> 0x0026 }
                android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch:{ Exception -> 0x0024 }
                if (r5 == 0) goto L_0x0023
                r5.close()     // Catch:{ Exception -> 0x001b }
                goto L_0x0023
            L_0x001b:
                r5 = move-exception
                java.lang.String r5 = java.lang.String.valueOf(r5)
                android.util.Log.e(r0, r5)
            L_0x0023:
                return r1
            L_0x0024:
                r2 = move-exception
                goto L_0x002d
            L_0x0026:
                r5 = move-exception
                r3 = r1
                r1 = r5
                r5 = r3
                goto L_0x0044
            L_0x002b:
                r2 = move-exception
                r5 = r1
            L_0x002d:
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0043 }
                android.util.Log.e(r0, r2)     // Catch:{ all -> 0x0043 }
                if (r5 == 0) goto L_0x0042
                r5.close()     // Catch:{ Exception -> 0x003a }
                goto L_0x0042
            L_0x003a:
                r5 = move-exception
                java.lang.String r5 = java.lang.String.valueOf(r5)
                android.util.Log.e(r0, r5)
            L_0x0042:
                return r1
            L_0x0043:
                r1 = move-exception
            L_0x0044:
                if (r5 == 0) goto L_0x0052
                r5.close()     // Catch:{ Exception -> 0x004a }
                goto L_0x0052
            L_0x004a:
                r5 = move-exception
                java.lang.String r5 = java.lang.String.valueOf(r5)
                android.util.Log.e(r0, r5)
            L_0x0052:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularImageUtilities.a.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(java.lang.String):android.graphics.Bitmap");
        }
    }
}
