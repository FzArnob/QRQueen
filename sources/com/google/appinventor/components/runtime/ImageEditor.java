package com.google.appinventor.components.runtime;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "Non-visible component that allows users to edit images.", iconName = "images/imageEditor.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class ImageEditor extends AndroidNonvisibleComponent implements Component {
    private String AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt = "NewImage.png";
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;

    public ImageEditor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Log.d("ImageEditor", "ImageEditor Created");
    }

    @DesignerProperty(defaultValue = "NewImage.png", editorType = "string")
    @SimpleProperty(description = "Save the new created image to a folder/ name of your choice.")
    public void SaveNewImageAs(String str) {
        this.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt = str;
    }

    @SimpleProperty(description = "Save the new created image to a folder/ name of your choice")
    public String SaveNewImageAs() {
        return this.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt;
    }

    public void Initialize() {
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    boolean unused = ImageEditor.this.havePermission = true;
                } else {
                    ImageEditor.this.form.dispatchPermissionDeniedEvent((Component) ImageEditor.this, "Save", str);
                }
            }
        });
    }

    private String SaveUtil(Bitmap bitmap) {
        if (this.havePermission) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmap);
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "Save", "android.permission.WRITE_EXTERNAL_STORAGE");
        return "PERMISSION_DENIED_ERROR";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006a A[SYNTHETIC, Splitter:B:21:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088 A[SYNTHETIC, Splitter:B:29:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a A[SYNTHETIC, Splitter:B:35:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0061=Splitter:B:18:0x0061, B:26:0x007d=Splitter:B:26:0x007d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap r6) {
        /*
            r5 = this;
            java.lang.String r0 = "ImageEditor"
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r2.<init>()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r4 = 0
            r6.compress(r3, r4, r2)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.File r6 = new java.io.File     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.<init>()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            com.google.appinventor.components.runtime.Form r4 = r5.form     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.File r4 = com.google.appinventor.components.runtime.util.QUtil.getExternalStorageDir(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r4 = r5.AsUIHfRHW1pScN9YQW0IsOeuFdHXhbXb53xXbDg8x2ZIBxv57XORnQnTS1wprCIt     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r3 = r3.toString()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r6.<init>(r3)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.<init>(r6)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            byte[] r1 = r2.toByteArray()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.write(r1)     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.flush()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.close()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.flush()     // Catch:{ Exception -> 0x004c }
            r3.close()     // Catch:{ Exception -> 0x004c }
            goto L_0x0054
        L_0x004c:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x0054:
            return r6
        L_0x0055:
            r6 = move-exception
            r1 = r3
            goto L_0x0098
        L_0x0058:
            r6 = move-exception
            r1 = r3
            goto L_0x0061
        L_0x005b:
            r6 = move-exception
            r1 = r3
            goto L_0x007d
        L_0x005e:
            r6 = move-exception
            goto L_0x0098
        L_0x0060:
            r6 = move-exception
        L_0x0061:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x005e }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0079
            r1.flush()     // Catch:{ Exception -> 0x0071 }
            r1.close()     // Catch:{ Exception -> 0x0071 }
            goto L_0x0079
        L_0x0071:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r0, r6)
        L_0x0079:
            java.lang.String r6 = "ERROR"
            return r6
        L_0x007c:
            r6 = move-exception
        L_0x007d:
            com.google.appinventor.components.runtime.Form r2 = r5.form     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "Save"
            r2.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r3, (com.google.appinventor.components.runtime.errors.PermissionException) r6)     // Catch:{ all -> 0x005e }
            java.lang.String r6 = "PERMISSION_DENIED_ERROR"
            if (r1 == 0) goto L_0x0097
            r1.flush()     // Catch:{ Exception -> 0x008f }
            r1.close()     // Catch:{ Exception -> 0x008f }
            goto L_0x0097
        L_0x008f:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x0097:
            return r6
        L_0x0098:
            if (r1 == 0) goto L_0x00a9
            r1.flush()     // Catch:{ Exception -> 0x00a1 }
            r1.close()     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00a9
        L_0x00a1:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00a9:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ImageEditor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap):java.lang.String");
    }

    private Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        try {
            return MediaUtil.getBitmapDrawable(this.container.$form(), str).getBitmap();
        } catch (Exception e) {
            Log.e("ImageEditor", String.valueOf(e));
            return null;
        }
    }

    @SimpleFunction(description = "Grayscale is a simple image effect that changes colors to grayscale by default.")
    public String GreyscaleEffect(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i, i2);
                int alpha = Color.alpha(pixel);
                int red = ((Color.red(pixel) + Color.green(pixel)) + Color.blue(pixel)) / 3;
                createBitmap.setPixel(i, i2, Color.argb(alpha, red, red, red));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This changes the contrast of your image. For example: value = 1.0 means normal picture contrast. Below 1.0 like as example 0.7 means dark contrast, above 1.0 as example 1.5 means light contrast.")
    public String SetContrast(String str, float f) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        Bitmap createBitmap = Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, paint);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Flip your image vertical or horizontal. For example: type = 1 (vertical); type = 2 (horizontal).")
    public String FlipPicture(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Matrix matrix = new Matrix();
        if (i == 1) {
            matrix.preScale(1.0f, -1.0f);
        } else if (i != 2) {
            return null;
        } else {
            matrix.preScale(-1.0f, 1.0f);
        }
        return SaveUtil(Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), matrix, true));
    }

    @SimpleFunction(description = "Change the brightness of your image. For example: value = 50 (maximum = 255=100% brightness).")
    public String SetBrightness(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        for (int i2 = 0; i2 < width; i2++) {
            for (int i3 = 0; i3 < height; i3++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i2, i3);
                int alpha = Color.alpha(pixel);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int i4 = red + i;
                int i5 = 255;
                if (i4 > 255) {
                    i4 = 255;
                } else if (i4 < 0) {
                    i4 = 0;
                }
                int i6 = green + i;
                if (i6 > 255) {
                    i6 = 255;
                } else if (i6 < 0) {
                    i6 = 0;
                }
                int i7 = blue + i;
                if (i7 <= 255) {
                    i5 = i7 < 0 ? 0 : i7;
                }
                createBitmap.setPixel(i2, i3, Color.argb(alpha, i4, i6, i5));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This effect inverts your image.")
    public String InvertEffect(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i2, i);
                createBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), 255 - Color.red(pixel), 255 - Color.green(pixel), 255 - Color.blue(pixel)));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Make a new image with a shading filter. For example: shadingColor = green(rgb value).")
    public String ShadingFilter(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        int[] iArr = new int[(width * height)];
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (i2 * width) + i3;
                iArr[i4] = iArr[i4] & i;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a color filter to your image. For example: red = 30; green = 40; blue = 20.")
    public String ColorFilter(String str, double d, double d2, double d3) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i, i2);
                createBitmap.setPixel(i, i2, Color.argb(Color.alpha(pixel), (int) (((double) Color.red(pixel)) * d), (int) (((double) Color.green(pixel)) * d2), (int) (((double) Color.blue(pixel)) * d3)));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a sepia toning effect to your image. For example: depth = 20; red = 10; green = 20; blue = 25.")
    public String SepiaToningEffect(String str, int i, double d, double d2, double d3) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        int i2 = 0;
        while (i2 < width) {
            for (int i3 = 0; i3 < height; i3++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i2, i3);
                int alpha = Color.alpha(pixel);
                double red = (double) ((int) ((((double) Color.red(pixel)) * 0.3d) + (((double) Color.green(pixel)) * 0.59d) + (((double) Color.blue(pixel)) * 0.11d)));
                double d4 = (double) i;
                int i4 = (int) ((d4 * d) + red);
                int i5 = 255;
                if (i4 > 255) {
                    i4 = 255;
                }
                int i6 = i2;
                int i7 = (int) (red + (d4 * d2));
                if (i7 > 255) {
                    i7 = 255;
                }
                int i8 = (int) (red + (d4 * d3));
                if (i8 <= 255) {
                    i5 = i8;
                }
                i2 = i6;
                createBitmap.setPixel(i2, i3, Color.argb(alpha, i4, i7, i5));
            }
            int i9 = i;
            i2++;
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a round corner to your image. For example: round = 45.")
    public String RoundCorner(String str, float f) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-16777216);
        Rect rect = new Rect(0, 0, width, height);
        canvas.drawRoundRect(new RectF(rect), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, rect, rect, paint);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a highlight effect to your image.")
    public String HighlightEffect(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() + 96, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight() + 96, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setMaskFilter(new BlurMaskFilter(15.0f, BlurMaskFilter.Blur.NORMAL));
        int[] iArr = new int[2];
        Bitmap extractAlpha = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.extractAlpha(paint, iArr);
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        canvas.drawBitmap(extractAlpha, (float) iArr[0], (float) iArr[1], paint2);
        extractAlpha.recycle();
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a gamma effect to your image. For example: red = 5; green = 10; blue = 20.")
    public String GammaEffect(String str, double d, double d2, double d3) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int[] iArr3 = new int[256];
        int i = 0;
        for (int i2 = 256; i < i2; i2 = 256) {
            double d4 = ((double) i) / 255.0d;
            int[] iArr4 = iArr;
            iArr4[i] = Math.min(255, (int) ((Math.pow(d4, 1.0d / d) * 255.0d) + 0.5d));
            int i3 = i;
            iArr2[i3] = Math.min(255, (int) ((Math.pow(d4, 1.0d / d2) * 255.0d) + 0.5d));
            iArr3[i3] = Math.min(255, (int) ((Math.pow(d4, 1.0d / d3) * 255.0d) + 0.5d));
            i = i3 + 1;
            iArr = iArr4;
        }
        int[] iArr5 = iArr;
        for (int i4 = 0; i4 < width; i4++) {
            for (int i5 = 0; i5 < height; i5++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i4, i5);
                createBitmap.setPixel(i4, i5, Color.argb(Color.alpha(pixel), iArr5[Color.red(pixel)], iArr2[Color.green(pixel)], iArr3[Color.blue(pixel)]));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Change the color depth of an image as you wish. For example: bitOffset = 32 (bit) or bitOffset = 16 (bit).")
    public String SetColorDepth(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        for (int i2 = 0; i2 < width; i2++) {
            for (int i3 = 0; i3 < height; i3++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i2, i3);
                int alpha = Color.alpha(pixel);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int i4 = i / 2;
                int i5 = red + i4;
                int i6 = (i5 - (i5 % i)) - 1;
                if (i6 < 0) {
                    i6 = 0;
                }
                int i7 = green + i4;
                int i8 = (i7 - (i7 % i)) - 1;
                if (i8 < 0) {
                    i8 = 0;
                }
                int i9 = blue + i4;
                int i10 = (i9 - (i9 % i)) - 1;
                if (i10 < 0) {
                    i10 = 0;
                }
                createBitmap.setPixel(i2, i3, Color.argb(alpha, i6, i8, i10));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Change the hue of an image. For example: level = 1 or 2 or 3 or 4 etc.")
    public String HueFilter(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        int[] iArr = new int[(width * height)];
        float[] fArr = new float[3];
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (i2 * width) + i3;
                Color.colorToHSV(iArr[i4], fArr);
                float f = fArr[0] * ((float) i);
                fArr[0] = f;
                fArr[0] = (float) Math.max(0.0d, Math.min((double) f, 360.0d));
                iArr[i4] = iArr[i4] | Color.HSVToColor(fArr);
            }
            int i5 = i;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a watermark effect to an image and change the positon/size or color of the text. For example: text = hello; textSize = 25; textColor = red(rgb value); textAlphaValue = 255 (255= 100% visible,127.5= 50% visible, 0= 0% visible); pointX = 50; pointY = 100; text underline(boolean) = true or false.")
    public String WatermarkEffect(String str, String str2, int i, int i2, boolean z, int i3, int i4, int i5) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setColor(i2);
        paint.setAlpha(i3);
        paint.setTextSize((float) i);
        paint.setAntiAlias(true);
        paint.setUnderlineText(z);
        canvas.drawText(str2, (float) i4, (float) i5, paint);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "That's a pretty new other cool effect. It changes the tint color of your image. For example: degree = 100.")
    public String TintColorEffect(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        int[] iArr = new int[(width * height)];
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixels(iArr, 0, width, 0, 0, width, height);
        double d = (((double) i) * 3.14159d) / 180.0d;
        int sin = (int) (Math.sin(d) * 256.0d);
        int cos = (int) (Math.cos(d) * 256.0d);
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (i2 * width) + i3;
                int i5 = iArr[i4];
                int i6 = 255;
                int i7 = (i5 >> 16) & 255;
                int i8 = i5 & 255;
                int i9 = ((i5 >> 8) & 255) * 59;
                int i10 = i8 * 11;
                int i11 = (((i7 * 70) - i9) - i10) / 100;
                int i12 = (((i7 * -30) - i9) + (i8 * 89)) / 100;
                int i13 = (((i7 * 30) + i9) + i10) / 100;
                int i14 = ((sin * i12) + (cos * i11)) / 256;
                int i15 = ((i12 * cos) - (i11 * sin)) / 256;
                int i16 = ((i14 * -51) - (i15 * 19)) / 100;
                int i17 = i14 + i13;
                if (i17 < 0) {
                    i17 = 0;
                } else if (i17 > 255) {
                    i17 = 255;
                }
                int i18 = i16 + i13;
                if (i18 < 0) {
                    i18 = 0;
                } else if (i18 > 255) {
                    i18 = 255;
                }
                int i19 = i13 + i15;
                if (i19 < 0) {
                    i6 = 0;
                } else if (i19 <= 255) {
                    i6 = i19;
                }
                iArr[i4] = (i17 << 16) | -16777216 | (i18 << 8) | i6;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Rotate the image to the degree you need it. For example: degree = 100.")
    public String ImageRotation(String str, float f) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return SaveUtil(Bitmap.createBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), matrix, true));
    }

    @SimpleFunction(description = "Color boost technique is basically based on color filtering, which is to increase the intensity of a single color channel. For example: type = green/ blue or red; percent = 40%.")
    public String ColorBoostEffect(String str, String str2, float f) {
        String str3 = str2;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i, i2);
                int alpha = Color.alpha(pixel);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                if (str3.equals("red")) {
                    red = (int) (((float) red) * (f + 1.0f));
                    if (red > 255) {
                        red = 255;
                    }
                } else if (str3.equals("green")) {
                    green = (int) (((float) green) * (f + 1.0f));
                    if (green > 255) {
                        green = 255;
                    }
                } else if (str3.equals("blue") && (blue = (int) (((float) blue) * (f + 1.0f))) > 255) {
                    blue = 255;
                }
                createBitmap.setPixel(i, i2, Color.argb(alpha, red, green, blue));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This methods creates a new scale center crop image.")
    public String ScaleCenterCrop(String str, int i, int i2) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        float f = (float) i2;
        float width = (float) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        float f2 = (float) i;
        float height = (float) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        float max = Math.max(f / width, f2 / height);
        float f3 = width * max;
        float f4 = max * height;
        float f5 = (f - f3) / 2.0f;
        float f6 = (f2 - f4) / 2.0f;
        RectF rectF = new RectF(f5, f6, f3 + f5, f4 + f6);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConfig());
        new Canvas(createBitmap).drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (Rect) null, rectF, (Paint) null);
        return SaveUtil(createBitmap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: int[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v9, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v18, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v8, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v9, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "This methods creates a blur effect.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String BlurEffect(java.lang.String r39, float r40, int r41) {
        /*
            r38 = this;
            r0 = r41
            android.graphics.Bitmap r1 = r38.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r39)
            if (r1 != 0) goto L_0x000b
            java.lang.String r0 = ""
            return r0
        L_0x000b:
            int r2 = r1.getWidth()
            float r2 = (float) r2
            float r2 = r2 * r40
            int r2 = java.lang.Math.round(r2)
            int r3 = r1.getHeight()
            float r3 = (float) r3
            float r3 = r3 * r40
            int r3 = java.lang.Math.round(r3)
            r4 = 0
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createScaledBitmap(r1, r2, r3, r4)
            android.graphics.Bitmap$Config r2 = r1.getConfig()
            r3 = 1
            android.graphics.Bitmap r1 = r1.copy(r2, r3)
            if (r0 > 0) goto L_0x0033
            r0 = 0
            return r0
        L_0x0033:
            int r2 = r1.getWidth()
            int r13 = r1.getHeight()
            int r14 = r2 * r13
            int[] r15 = new int[r14]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r12 = " "
            r5.append(r12)
            r5.append(r13)
            r5.append(r12)
            r5.append(r14)
            java.lang.String r5 = r5.toString()
            java.lang.String r11 = "ImageEditor"
            android.util.Log.e(r11, r5)
            r7 = 0
            r9 = 0
            r10 = 0
            r5 = r1
            r6 = r15
            r8 = r2
            r16 = r11
            r11 = r2
            r17 = r12
            r12 = r13
            r5.getPixels(r6, r7, r8, r9, r10, r11, r12)
            int r5 = r2 + -1
            int r6 = r13 + -1
            int r7 = r0 + r0
            int r7 = r7 + r3
            int[] r8 = new int[r14]
            int[] r9 = new int[r14]
            int[] r10 = new int[r14]
            int r11 = java.lang.Math.max(r2, r13)
            int[] r11 = new int[r11]
            int r12 = r7 + 1
            int r12 = r12 >> r3
            int r12 = r12 * r12
            int r4 = r12 << 8
            int[] r3 = new int[r4]
            r18 = r1
            r1 = 0
        L_0x008c:
            if (r1 >= r4) goto L_0x0095
            int r19 = r1 / r12
            r3[r1] = r19
            int r1 = r1 + 1
            goto L_0x008c
        L_0x0095:
            r1 = 3
            r4 = 2
            int[] r12 = new int[r4]
            r19 = 1
            r12[r19] = r1
            r1 = 0
            r12[r1] = r7
            java.lang.Class<int> r1 = int.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r12)
            int[][] r1 = (int[][]) r1
            int r12 = r0 + 1
            r4 = 0
            r20 = 0
            r21 = 0
        L_0x00af:
            if (r4 >= r13) goto L_0x01ca
            r22 = r14
            int r14 = -r0
            r31 = r13
            r13 = r14
            r14 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
        L_0x00c8:
            if (r13 > r0) goto L_0x0123
            r32 = r6
            r33 = r11
            r6 = 0
            int r11 = java.lang.Math.max(r13, r6)
            int r11 = java.lang.Math.min(r5, r11)
            int r11 = r20 + r11
            r11 = r15[r11]
            int r34 = r13 + r0
            r34 = r1[r34]
            r35 = r15
            int r15 = r11 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r34[r6] = r15
            int r15 = r11 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r36 = 1
            r34[r36] = r15
            r11 = r11 & 255(0xff, float:3.57E-43)
            r15 = 2
            r34[r15] = r11
            int r11 = java.lang.Math.abs(r13)
            int r11 = r12 - r11
            r37 = r34[r6]
            int r6 = r37 * r11
            int r14 = r14 + r6
            r6 = r34[r36]
            int r19 = r6 * r11
            int r23 = r23 + r19
            r34 = r34[r15]
            int r11 = r11 * r34
            int r24 = r24 + r11
            if (r13 <= 0) goto L_0x0114
            int r28 = r28 + r37
            int r29 = r29 + r6
            int r30 = r30 + r34
            goto L_0x011a
        L_0x0114:
            int r25 = r25 + r37
            int r26 = r26 + r6
            int r27 = r27 + r34
        L_0x011a:
            int r13 = r13 + 1
            r6 = r32
            r11 = r33
            r15 = r35
            goto L_0x00c8
        L_0x0123:
            r32 = r6
            r33 = r11
            r35 = r15
            r11 = r0
            r6 = 0
        L_0x012b:
            if (r6 >= r2) goto L_0x01b6
            r13 = r3[r14]
            r8[r20] = r13
            r13 = r3[r23]
            r9[r20] = r13
            r13 = r3[r24]
            r10[r20] = r13
            int r14 = r14 - r25
            int r23 = r23 - r26
            int r24 = r24 - r27
            int r13 = r11 - r0
            int r13 = r13 + r7
            int r13 = r13 % r7
            r13 = r1[r13]
            r15 = 0
            r34 = r13[r15]
            int r25 = r25 - r34
            r15 = 1
            r34 = r13[r15]
            int r26 = r26 - r34
            r19 = 2
            r34 = r13[r19]
            int r27 = r27 - r34
            if (r4 != 0) goto L_0x0164
            int r34 = r6 + r0
            r36 = r3
            int r3 = r34 + 1
            int r3 = java.lang.Math.min(r3, r5)
            r33[r6] = r3
            goto L_0x0166
        L_0x0164:
            r36 = r3
        L_0x0166:
            r3 = r33[r6]
            int r3 = r21 + r3
            r3 = r35[r3]
            int r15 = r3 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r34 = 0
            r13[r34] = r15
            r34 = r5
            int r5 = r3 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            r37 = 1
            r13[r37] = r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            r19 = 2
            r13[r19] = r3
            int r28 = r28 + r15
            int r29 = r29 + r5
            int r30 = r30 + r3
            int r14 = r14 + r28
            int r23 = r23 + r29
            int r24 = r24 + r30
            int r11 = r11 + 1
            int r11 = r11 % r7
            int r3 = r11 % r7
            r3 = r1[r3]
            r5 = 0
            r13 = r3[r5]
            int r25 = r25 + r13
            r5 = 1
            r15 = r3[r5]
            int r26 = r26 + r15
            r5 = 2
            r3 = r3[r5]
            int r27 = r27 + r3
            int r28 = r28 - r13
            int r29 = r29 - r15
            int r30 = r30 - r3
            int r20 = r20 + 1
            int r6 = r6 + 1
            r5 = r34
            r3 = r36
            goto L_0x012b
        L_0x01b6:
            r36 = r3
            r34 = r5
            int r21 = r21 + r2
            int r4 = r4 + 1
            r14 = r22
            r13 = r31
            r6 = r32
            r11 = r33
            r15 = r35
            goto L_0x00af
        L_0x01ca:
            r36 = r3
            r32 = r6
            r33 = r11
            r31 = r13
            r22 = r14
            r35 = r15
            r3 = 0
        L_0x01d7:
            if (r3 >= r2) goto L_0x0306
            int r4 = -r0
            int r5 = r4 * r2
            r24 = r2
            r2 = r5
            r23 = r7
            r5 = 0
            r6 = 0
            r11 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r20 = 0
            r21 = 0
            r7 = r4
            r4 = 0
        L_0x01ed:
            if (r7 > r0) goto L_0x025b
            r25 = r14
            r14 = 0
            int r26 = java.lang.Math.max(r14, r2)
            int r26 = r26 + r3
            int r27 = r7 + r0
            r27 = r1[r27]
            r28 = r8[r26]
            r27[r14] = r28
            r14 = r9[r26]
            r28 = 1
            r27[r28] = r14
            r14 = r10[r26]
            r19 = 2
            r27[r19] = r14
            int r14 = java.lang.Math.abs(r7)
            int r14 = r12 - r14
            r28 = r8[r26]
            int r28 = r28 * r14
            int r4 = r4 + r28
            r28 = r9[r26]
            int r28 = r28 * r14
            int r5 = r5 + r28
            r26 = r10[r26]
            int r26 = r26 * r14
            int r6 = r6 + r26
            if (r7 <= 0) goto L_0x023c
            r14 = 0
            r26 = r27[r14]
            int r15 = r15 + r26
            r26 = 1
            r28 = r27[r26]
            int r20 = r20 + r28
            r19 = 2
            r27 = r27[r19]
            int r21 = r21 + r27
            r26 = r4
            r14 = r25
            goto L_0x024e
        L_0x023c:
            r14 = 0
            r19 = 2
            r26 = 1
            r28 = r27[r14]
            int r11 = r11 + r28
            r14 = r27[r26]
            int r13 = r13 + r14
            r14 = r27[r19]
            int r14 = r25 + r14
            r26 = r4
        L_0x024e:
            r4 = r32
            if (r7 >= r4) goto L_0x0254
            int r2 = r2 + r24
        L_0x0254:
            int r7 = r7 + 1
            r32 = r4
            r4 = r26
            goto L_0x01ed
        L_0x025b:
            r26 = r4
            r25 = r14
            r4 = r32
            r27 = r0
            r7 = r31
            r2 = 0
            r25 = r3
        L_0x0268:
            if (r2 >= r7) goto L_0x02f2
            r28 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r29 = r35[r25]
            r28 = r29 & r28
            r29 = r36[r26]
            int r29 = r29 << 16
            r28 = r28 | r29
            r29 = r36[r5]
            int r29 = r29 << 8
            r28 = r28 | r29
            r29 = r36[r6]
            r28 = r28 | r29
            r35[r25] = r28
            int r26 = r26 - r11
            int r5 = r5 - r13
            int r6 = r6 - r14
            int r28 = r27 - r0
            int r28 = r28 + r23
            int r28 = r28 % r23
            r28 = r1[r28]
            r29 = 0
            r30 = r28[r29]
            int r11 = r11 - r30
            r29 = 1
            r30 = r28[r29]
            int r13 = r13 - r30
            r19 = 2
            r29 = r28[r19]
            int r14 = r14 - r29
            if (r3 != 0) goto L_0x02ac
            int r0 = r2 + r12
            int r0 = java.lang.Math.min(r0, r4)
            int r0 = r0 * r24
            r33[r2] = r0
        L_0x02ac:
            r0 = r33[r2]
            int r0 = r0 + r3
            r29 = r8[r0]
            r30 = 0
            r28[r30] = r29
            r30 = r9[r0]
            r31 = 1
            r28[r31] = r30
            r0 = r10[r0]
            r19 = 2
            r28[r19] = r0
            int r15 = r15 + r29
            int r20 = r20 + r30
            int r21 = r21 + r0
            int r26 = r26 + r15
            int r5 = r5 + r20
            int r6 = r6 + r21
            int r27 = r27 + 1
            int r27 = r27 % r23
            r0 = r1[r27]
            r28 = 0
            r29 = r0[r28]
            int r11 = r11 + r29
            r30 = 1
            r31 = r0[r30]
            int r13 = r13 + r31
            r19 = 2
            r0 = r0[r19]
            int r14 = r14 + r0
            int r15 = r15 - r29
            int r20 = r20 - r31
            int r21 = r21 - r0
            int r25 = r25 + r24
            int r2 = r2 + 1
            r0 = r41
            goto L_0x0268
        L_0x02f2:
            r19 = 2
            r28 = 0
            r30 = 1
            int r3 = r3 + 1
            r0 = r41
            r32 = r4
            r31 = r7
            r7 = r23
            r2 = r24
            goto L_0x01d7
        L_0x0306:
            r24 = r2
            r7 = r31
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = r24
            r0.append(r1)
            r2 = r17
            r0.append(r2)
            r0.append(r7)
            r0.append(r2)
            r2 = r22
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r2 = r16
            android.util.Log.e(r2, r0)
            r0 = 0
            r9 = 0
            r10 = 0
            r5 = r18
            r6 = r35
            r2 = r7
            r7 = r0
            r8 = r1
            r11 = r1
            r12 = r2
            r5.setPixels(r6, r7, r8, r9, r10, r11, r12)
            r0 = r38
            r1 = r18
            java.lang.String r1 = r0.SaveUtil(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ImageEditor.BlurEffect(java.lang.String, float, int):java.lang.String");
    }

    @SimpleFunction(description = "This methods creates a pixelate image effect. Use as example as pixelation Amount '1' for a hugh pixel effect and '99' for a almost not visible pixel effect.")
    public String Pixelate(String str, int i) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
        int height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        return SaveUtil(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (width / 100) * i, (height / 100) * i), width, height));
    }

    private static Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    @SimpleFunction(description = "This methods creates a new side by side horizontal image.")
    public String MergeTwoImages(String str, String str2) {
        int i;
        int i2;
        int i3;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == null) {
            return "";
        }
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() > hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth()) {
            i2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
            i = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth();
        } else {
            i2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth();
            i = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth();
        }
        int i4 = i2 + i;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight() > hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getHeight()) {
            i3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        } else {
            i3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(i4, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, (float) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), 0.0f, (Paint) null);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Return true if image is in landscape format, else return false.")
    public boolean isLandscape(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() > hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
    }

    @SimpleFunction(description = "Return true if image is in portrait format, else return false.")
    public boolean isPortrait(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() < hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
    }

    @SimpleFunction(description = "Return true if image is in square format (means as high as wide or as wide as high), else return false.")
    public boolean isSquare(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() == hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
    }
}
