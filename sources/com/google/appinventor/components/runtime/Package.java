package com.google.appinventor.components.runtime;

import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularHelper;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/package.png", nonVisible = true, version = 3)
public class Package extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;

    public Package(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Log.d("Package", "Package Created");
    }

    @SimpleProperty(description = "This block will returns the version name of the current running app.")
    public String VersionName() {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(this.container.$form().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "0";
        }
    }

    @SimpleProperty(description = "This block will returns the version code of the current running app.")
    public int VersionCode() {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(this.container.$form().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    @SimpleFunction(description = "This block will returns the version name of the package name. Returns 'Package not found' if the package is not installed.")
    public String VersionNameFrom(String str) {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "Package not found";
        }
    }

    @SimpleFunction(description = "This block will returns the version code of the package name. Returns '-1' if the package is not installed.")
    public int VersionCodeFrom(String str) {
        try {
            return this.container.$form().getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    @SimpleFunction(description = "Check whether a particular package has been granted a particular permission.")
    public boolean isPermissionGranted(String str, String str2) {
        if (!str.contains(".")) {
            str = "android.permission.".concat(String.valueOf(str));
        }
        return ContextCompat.checkSelfPermission(this.container.$form(), str) == 0;
    }

    @SimpleFunction(description = "Try to show the application icon of the given package name. If the application cannot be found, \"Package not found\" is the output.")
    public String GetPackageIcon(String str) {
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularHelper.getPackageIcon(this.container.$form().getPackageManager().getApplicationIcon(str)), AppNameFrom(str));
        } catch (PackageManager.NameNotFoundException unused) {
            return "Package not found";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a3 A[SYNTHETIC, Splitter:B:29:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c0 A[SYNTHETIC, Splitter:B:39:0x00c0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Package"
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0095 }
            r2.<init>()     // Catch:{ Exception -> 0x0095 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0095 }
            r4 = 0
            r8.compress(r3, r4, r2)     // Catch:{ Exception -> 0x0095 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0095 }
            com.google.appinventor.components.runtime.ComponentContainer r4 = r7.container     // Catch:{ Exception -> 0x0095 }
            com.google.appinventor.components.runtime.Form r4 = r4.$form()     // Catch:{ Exception -> 0x0095 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ Exception -> 0x0095 }
            java.lang.String r5 = "/Kodular-App-Icons"
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x0095 }
            boolean r4 = r3.exists()     // Catch:{ Exception -> 0x0095 }
            if (r4 != 0) goto L_0x0028
            r3.mkdir()     // Catch:{ Exception -> 0x0095 }
        L_0x0028:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0095 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0095 }
            r5.<init>()     // Catch:{ Exception -> 0x0095 }
            java.lang.String r3 = r3.getPath()     // Catch:{ Exception -> 0x0095 }
            r5.append(r3)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r3 = java.io.File.separator     // Catch:{ Exception -> 0x0095 }
            r5.append(r3)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r3 = "."
            r5.append(r3)     // Catch:{ Exception -> 0x0095 }
            r5.append(r9)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r9 = ".png"
            r5.append(r9)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r9 = r5.toString()     // Catch:{ Exception -> 0x0095 }
            r4.<init>(r9)     // Catch:{ Exception -> 0x0095 }
            boolean r9 = r4.exists()     // Catch:{ Exception -> 0x0095 }
            if (r9 != 0) goto L_0x0058
            r4.createNewFile()     // Catch:{ Exception -> 0x0095 }
        L_0x0058:
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0095 }
            r9.<init>(r4)     // Catch:{ Exception -> 0x0095 }
            byte[] r1 = r2.toByteArray()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r9.write(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r9.flush()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r9.close()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = r4.getAbsolutePath()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r9.flush()     // Catch:{ Exception -> 0x0075 }
            r9.close()     // Catch:{ Exception -> 0x0075 }
            goto L_0x007d
        L_0x0075:
            r9 = move-exception
            java.lang.String r9 = java.lang.String.valueOf(r9)
            android.util.Log.e(r0, r9)
        L_0x007d:
            if (r8 == 0) goto L_0x0088
            boolean r9 = r8.isRecycled()
            if (r9 != 0) goto L_0x0088
            r8.recycle()
        L_0x0088:
            return r1
        L_0x0089:
            r1 = move-exception
            r6 = r1
            r1 = r9
            r9 = r6
            goto L_0x00be
        L_0x008e:
            r1 = move-exception
            r6 = r1
            r1 = r9
            r9 = r6
            goto L_0x0096
        L_0x0093:
            r9 = move-exception
            goto L_0x00be
        L_0x0095:
            r9 = move-exception
        L_0x0096:
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0093 }
            android.util.Log.e(r0, r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x00b2
            r1.flush()     // Catch:{ Exception -> 0x00aa }
            r1.close()     // Catch:{ Exception -> 0x00aa }
            goto L_0x00b2
        L_0x00aa:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00b2:
            if (r8 == 0) goto L_0x00bd
            boolean r0 = r8.isRecycled()
            if (r0 != 0) goto L_0x00bd
            r8.recycle()
        L_0x00bd:
            return r9
        L_0x00be:
            if (r1 == 0) goto L_0x00cf
            r1.flush()     // Catch:{ Exception -> 0x00c7 }
            r1.close()     // Catch:{ Exception -> 0x00c7 }
            goto L_0x00cf
        L_0x00c7:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00cf:
            if (r8 == 0) goto L_0x00da
            boolean r0 = r8.isRecycled()
            if (r0 != 0) goto L_0x00da
            r8.recycle()
        L_0x00da:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Package.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    @SimpleProperty(description = "Returns the name from the current running app.")
    public String AppName() {
        PackageManager packageManager = this.container.$form().getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.container.$form().getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException unused) {
            return "not found";
        }
    }

    @SimpleFunction(description = "Returns the name from the given package name.")
    public String AppNameFrom(String str) {
        PackageManager packageManager = this.container.$form().getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException unused) {
            return "Package not found";
        }
    }

    @SimpleProperty(description = "Returns the package name from the current running app.")
    public String PackageName() {
        return this.container.$form().getPackageName();
    }

    @SimpleFunction(description = "Returns true if a package (app) is installed and enabled.")
    public boolean IsPackageInstalled(String str) {
        try {
            return this.container.$form().getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
