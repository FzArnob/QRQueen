package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaActionSound;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/screenshot.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class Screenshot extends AndroidNonvisibleComponent implements Component {
    private boolean G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = true;
    private boolean Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = false;
    private boolean YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = false;
    private Activity activity;
    private Context context;
    private float dimAmount;
    private String eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i = "Share";
    private String fileName = "Screenshot.png";
    private boolean hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    private int hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG;
    private MediaActionSound hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = "Screenshot..";
    private String uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR;
    private int vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj;
    /* access modifiers changed from: private */
    public Bitmap vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    private String wOjOOBWoVyNzDzaWXgy1S9rRvmLqhYagLoPiKuHlw4Kpu5E5zVoSLSrwTs2bXyKG;

    public Screenshot(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = true;
        this.dimAmount = 0.5f;
        PackageManager packageManager = this.context.getPackageManager();
        String packageName = this.context.getPackageName();
        this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = packageManager.checkPermission("android.permission.READ_EXTERNAL_STORAGE", packageName);
        this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG = packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageName);
        MediaActionSound mediaActionSound = new MediaActionSound();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mediaActionSound;
        mediaActionSound.load(0);
        Log.d("Screenshot", "Screenshot Created");
    }

    @SimpleFunction(description = "Take a screenshot of the current visible screen.")
    public void TakeScreenshot() {
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    new a(Screenshot.this, (byte) 0).execute(new String[]{""});
                } else {
                    Screenshot.this.form.dispatchPermissionDeniedEvent((Component) Screenshot.this, "TakeScreenshot", str);
                }
            }
        });
    }

    class a extends AsyncTask<String, String, String> {
        private View B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this).play(0);
            if (Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this)) {
                Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this);
            }
            if (Screenshot.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Screenshot.this) && Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this) != null) {
                MakeroidNotification.ScreenshotNotification(Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this), Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this), Screenshot.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Screenshot.this), Screenshot.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Screenshot.this), Uri.parse(Screenshot.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(Screenshot.this)), Screenshot.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Screenshot.this), Screenshot.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(Screenshot.this), Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this));
            }
            Screenshot.this.GotScreenshot(str);
            MediaScannerConnection.scanFile(Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this), new String[]{str}, new String[]{"image/*"}, (MediaScannerConnection.OnScanCompletedListener) null);
        }

        private a() {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
        }

        /* synthetic */ a(Screenshot screenshot, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            View rootView = Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Screenshot.this).getWindow().getDecorView().getRootView();
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = rootView;
            rootView.setDrawingCacheEnabled(true);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.buildDrawingCache(true);
        }

        private String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou() {
            try {
                Bitmap unused = Screenshot.this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Bitmap.createBitmap(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getDrawingCache());
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setDrawingCacheEnabled(false);
                Screenshot screenshot = Screenshot.this;
                return screenshot.SaveUtil(Screenshot.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(screenshot));
            } catch (Exception e) {
                String str = e.getMessage();
                Log.e("Screenshot", str);
                Bitmap unused2 = Screenshot.this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = null;
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
                return str;
            }
        }
    }

    @SimpleEvent(description = "Event to detect that a screenshot was made by the user. Returns the image path to the taken screenshot.")
    public void GotScreenshot(String str) {
        EventDispatcher.dispatchEvent(this, "GotScreenshot", str);
    }

    @DesignerProperty(defaultValue = "Screenshot.png", editorType = "string")
    @SimpleProperty(description = "This will be the name for the taken screenshot image file.")
    public void FileName(String str) {
        this.fileName = str;
    }

    @SimpleProperty(description = "Returns the given screenshot filename.")
    public String FileName() {
        return this.fileName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079 A[SYNTHETIC, Splitter:B:23:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a A[SYNTHETIC, Splitter:B:29:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b A[SYNTHETIC, Splitter:B:35:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b3 A[SYNTHETIC, Splitter:B:42:0x00b3] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0070=Splitter:B:20:0x0070, B:26:0x0081=Splitter:B:26:0x0081, B:32:0x0092=Splitter:B:32:0x0092} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String SaveUtil(android.graphics.Bitmap r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Screenshot"
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r2.<init>()     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r4 = 0
            r6.compress(r3, r4, r2)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.io.File r6 = new java.io.File     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r3.<init>()     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            android.content.Context r4 = r5.context     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.io.File r4 = com.google.appinventor.components.runtime.util.QUtil.getExternalStorageDir(r4)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r3.append(r4)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.lang.String r4 = r5.fileName     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r3.append(r4)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.lang.String r3 = r3.toString()     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r6.<init>(r3)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            r3.<init>(r6)     // Catch:{ PermissionException -> 0x0091, FileNotFoundException -> 0x0080, Exception -> 0x006f }
            byte[] r1 = r2.toByteArray()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r3.write(r1)     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r3.flush()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r3.close()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            java.lang.String r1 = r6.getAbsolutePath()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r5.wOjOOBWoVyNzDzaWXgy1S9rRvmLqhYagLoPiKuHlw4Kpu5E5zVoSLSrwTs2bXyKG = r1     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            java.lang.String r1 = r6.getName()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r5.uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR = r1     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ PermissionException -> 0x006a, FileNotFoundException -> 0x0067, Exception -> 0x0064, all -> 0x0061 }
            r3.flush()     // Catch:{ Exception -> 0x0058 }
            r3.close()     // Catch:{ Exception -> 0x0058 }
            goto L_0x0060
        L_0x0058:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x0060:
            return r6
        L_0x0061:
            r6 = move-exception
            r1 = r3
            goto L_0x00b1
        L_0x0064:
            r6 = move-exception
            r1 = r3
            goto L_0x0070
        L_0x0067:
            r6 = move-exception
            r1 = r3
            goto L_0x0081
        L_0x006a:
            r6 = move-exception
            r1 = r3
            goto L_0x0092
        L_0x006d:
            r6 = move-exception
            goto L_0x00b1
        L_0x006f:
            r6 = move-exception
        L_0x0070:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x006d }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x00aa
            r1.flush()     // Catch:{ Exception -> 0x00a2 }
            r1.close()     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00aa
        L_0x0080:
            r6 = move-exception
        L_0x0081:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x006d }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x00aa
            r1.flush()     // Catch:{ Exception -> 0x00a2 }
            r1.close()     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00aa
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            com.google.appinventor.components.runtime.Form r2 = r5.form     // Catch:{ all -> 0x006d }
            java.lang.String r3 = "TakeScreenshot"
            r2.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r3, (com.google.appinventor.components.runtime.errors.PermissionException) r6)     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x00aa
            r1.flush()     // Catch:{ Exception -> 0x00a2 }
            r1.close()     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00aa
        L_0x00a2:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r0, r6)
        L_0x00aa:
            java.lang.String r6 = "ERROR"
            r5.wOjOOBWoVyNzDzaWXgy1S9rRvmLqhYagLoPiKuHlw4Kpu5E5zVoSLSrwTs2bXyKG = r6
            r5.uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR = r6
            return r6
        L_0x00b1:
            if (r1 == 0) goto L_0x00c2
            r1.flush()     // Catch:{ Exception -> 0x00ba }
            r1.close()     // Catch:{ Exception -> 0x00ba }
            goto L_0x00c2
        L_0x00ba:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00c2:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Screenshot.SaveUtil(android.graphics.Bitmap):java.lang.String");
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see a short preview animation after the screenshot is taken.")
    public void ShowPreview(boolean z) {
        this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = z;
    }

    @SimpleProperty(description = "Returns true if \"Show Preview\" is enabled.")
    public boolean ShowPreview() {
        return this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see a notification in the statusbar after the screenshot is taken.")
    public void ShowNotification(boolean z) {
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = z;
    }

    @SimpleProperty(description = "Returns true if \"Show Notification\" is enabled.")
    public boolean ShowNotification() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @DesignerProperty(defaultValue = "Screenshot..", editorType = "string")
    @SimpleProperty(description = "This will set the notification title if the option \"Show Notification\" is enabled. ")
    public void NotificationTitle(String str) {
        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = str;
    }

    @SimpleProperty(description = "Returns the notification title.")
    public String NotificationTitle() {
        return this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs;
    }

    @DesignerProperty(defaultValue = "Share", editorType = "string")
    @SimpleProperty(description = "This will set the notification share button text if the option \"Show Notification\" is enabled.")
    public void NotificationShareTitle(String str) {
        this.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i = str;
    }

    @SimpleProperty(description = "Returns the notification share button text.")
    public String NotificationShareTitle() {
        return this.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "This will display the notification in a big style.")
    public void NotificationBigStyle(boolean z) {
        this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = z;
    }

    @SimpleProperty(description = "Returns the notification big style option status.")
    public boolean NotificationBigStyle() {
        return this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This feature allows users of your app to make or ban screenshots of their app. If disabled and a person tries to make a screenshot, they will receive then a default system message that this is not possible.")
    public void AllowScreenshots(boolean z) {
        if (z) {
            this.activity.getWindow().clearFlags(8192);
        } else {
            this.activity.getWindow().setFlags(8192, 8192);
        }
    }

    @SimpleProperty(description = "Returns true if the write and read permission is granted, else false.")
    public boolean arePermissionsGranted() {
        return this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj == 0 && this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG == 0;
    }

    @SimpleProperty(description = "Returns true if the read permission is granted, else false.")
    public boolean isReadPermissionGranted() {
        return this.vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj == 0;
    }

    @SimpleProperty(description = "Returns true if the write permission is granted, else false.")
    public boolean isWritePermissionGranted() {
        return this.hibTQF3buaJTulLZvSVkxWzq69D3X99LEonIrTaR8DG6SkVpYpvjF3tGUybbhvWG == 0;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see a notification with a share button after the screenshot is taken.")
    public void NotificationShareButton(boolean z) {
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = z;
    }

    @SimpleProperty
    public boolean NotificationShareButton() {
        return this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    }

    @DesignerProperty(defaultValue = "0.5", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the amount of dim behind the preview window if ShowPreview(...) is enabled. Use '0.0' for no dim and '1.0' for full dim.")
    public void DimAmount(float f) {
        this.dimAmount = f;
    }

    @SimpleProperty
    public float DimAmount() {
        return this.dimAmount;
    }
}
