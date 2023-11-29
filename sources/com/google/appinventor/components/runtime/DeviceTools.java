package com.google.appinventor.components.runtime;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import androidx.core.os.EnvironmentCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.DeviceStorage;
import com.google.appinventor.components.runtime.util.QUtil;
import java.util.Arrays;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/devicetools.png", nonVisible = true, version = 5)
@UsesPermissions({"android.permission.READ_PHONE_STATE"})
public class DeviceTools extends AndroidNonvisibleComponent implements Component {
    private boolean BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = false;
    private Context context;
    private final long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = 1048576;
    private int tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;

    public DeviceTools(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("DeviceTools", "Device Tools Created");
    }

    @SimpleProperty(description = "The result is the code of your country.")
    public String CountryCode() {
        return this.context.getResources().getConfiguration().locale.getCountry();
    }

    @SimpleProperty(description = "The result is the code of your device language.")
    public String LanguageCode() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    @SimpleProperty(description = "Get the android version of device.")
    public String AndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    @SimpleProperty(description = "The user-visible SDK version of the framework.")
    public int ApiVersion() {
        return Build.VERSION.SDK_INT;
    }

    @SimpleProperty(description = "The name of the underlying board, like \"goldfish\".")
    public String Board() {
        return Build.BOARD;
    }

    @SimpleProperty(description = "The system bootloader version number.")
    public String BootloaderVersion() {
        return Build.BOOTLOADER;
    }

    @SimpleProperty(description = "The consumer-visible brand with which the product/hardware will be associated, if any.")
    public String Brand() {
        return Build.BRAND;
    }

    @SimpleProperty(description = "The name of the industrial design.")
    public String DeviceName() {
        return Build.DEVICE;
    }

    @SimpleProperty(description = "Get the build number(Software) of the device.")
    public String BuildNumber() {
        return Build.DISPLAY;
    }

    @SimpleProperty(description = "A string that uniquely identifies this build.")
    public String Fingerprint() {
        return Build.FINGERPRINT;
    }

    @SimpleProperty(description = "The name of the hardware (from the kernel command line or /proc).")
    public String Hardware() {
        return Build.HARDWARE;
    }

    @SimpleProperty(description = "Either a changelist number, or a label like \"M4-rc20\".")
    public String Id() {
        return Build.ID;
    }

    @SimpleFunction(description = "Get the IMEI of the device. The result will be then at the 'Got IMEI' event.")
    public void GetIMEI() {
        this.form.runOnUiThread(new Runnable() {
            public final void run() {
                DeviceTools.this.form.askPermission("android.permission.READ_PHONE_STATE", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            DeviceTools.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceTools.this);
                        } else {
                            DeviceTools.this.form.dispatchPermissionDeniedEvent((Component) DeviceTools.this, "GetIMEI", "android.permission.READ_PHONE_STATE");
                        }
                    }
                });
            }
        });
    }

    @SimpleEvent(description = "Event to get the IMEI after it was requested.")
    public void GotIMEI(String str) {
        EventDispatcher.dispatchEvent(this, "GotIMEI", str);
    }

    @SimpleProperty(description = "The manufacturer of the product/hardware.")
    public String Manufacturer() {
        return Build.MANUFACTURER;
    }

    @SimpleProperty(description = "The end-user-visible name for the end product.")
    public String ModelName() {
        return Build.MODEL;
    }

    @SimpleProperty(description = "The name of the overall product.")
    public String Product() {
        return Build.PRODUCT;
    }

    @SimpleProperty(description = "Returns the version string for the radio firmware. May return null (if, for instance, the radio is not currently on).")
    public String RadioVersion() {
        return Build.getRadioVersion();
    }

    @Deprecated
    @SimpleProperty(description = "DEPRECATED. DO NOT USE THIS ANYMORE. USE 'Get Serial' INSTEAD!")
    public String Serial() {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return Build.getSerial();
            }
            return Build.SERIAL;
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "Serial", e);
            Log.e("DeviceTools", String.valueOf(e));
            return e.getMessage();
        } catch (Exception e2) {
            Log.e("DeviceTools", String.valueOf(e2));
            return e2.getMessage();
        }
    }

    @SimpleFunction(description = "A hardware serial number, if available. Alphanumeric only, case-insensitive. For apps targeting SDK higher than N_MR1 this field is set to UNKNOWN.")
    public void GetSerial() {
        this.form.runOnUiThread(new Runnable() {
            public final void run() {
                DeviceTools.this.form.askPermission("android.permission.READ_PHONE_STATE", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            DeviceTools.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceTools.this);
                        } else {
                            DeviceTools.this.form.dispatchPermissionDeniedEvent((Component) DeviceTools.this, "GetSerial", "android.permission.READ_PHONE_STATE");
                        }
                    }
                });
            }
        });
    }

    @SimpleEvent(description = "Event to get the serial number after it was requested.")
    public void GotSerial(String str) {
        EventDispatcher.dispatchEvent(this, "GotSerial", str);
    }

    @SimpleProperty(description = "Comma-separated tags describing the build, like \"unsigned,debug\".")
    public String Tags() {
        return Build.TAGS;
    }

    @SimpleProperty(description = "The type of build, like \"user\" or \"eng\".")
    public String Type() {
        return Build.TYPE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowSuccessToast(boolean z) {
        this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true if 'Show Success Toast' is enabled.")
    public boolean ShowSuccessToast() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @SimpleFunction(description = "Parse a text between two strings. Example: text = abcdef, start = a, end = d, result = bc. If there is a problem the 'if Text Not Found' will be returned.")
    public String Parse(String str, String str2, String str3, String str4) {
        if (str4.isEmpty()) {
            str4 = "text not found";
        }
        try {
            String substring = str.substring(str.indexOf(str2) + str2.length(), str.length());
            return substring.substring(0, substring.indexOf(str3));
        } catch (Exception e) {
            Log.e("DeviceTools", String.valueOf(e));
            return str4;
        }
    }

    @SimpleFunction(description = "Copy text to clipboard. In case 'Show Success Toast' is true, the toast with your message will be shown after copying a text to the clipboard.")
    public void Copy(String str, String str2) {
        try {
            ((ClipboardManager) this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied text", str));
            Log.d("DeviceTools", "Text copied: ".concat(String.valueOf(str)));
            if (this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS) {
                Toast.makeText(this.context, str2, 0).show();
            }
        } catch (Exception e) {
            Log.e("DeviceTools", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Paste text from clipboard. In case 'Show Success Toast' is true, the toast with your message will be shown after pasting a text from the clipboard.")
    public String Paste(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) this.context.getSystemService("clipboard");
        ClipData primaryClip = clipboardManager != null ? clipboardManager.getPrimaryClip() : null;
        if (primaryClip == null) {
            return "";
        }
        String charSequence = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(primaryClip.getItemAt(0)).toString();
        Log.d("DeviceTools", "Text pasted: ".concat(String.valueOf(charSequence)));
        if (this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS) {
            Toast.makeText(this.context, str, 0).show();
        }
        return charSequence;
    }

    /* JADX WARNING: type inference failed for: r8v4, types: [android.os.Bundle, java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r8v13, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.CharSequence hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.content.ClipData.Item r8) {
        /*
            r7 = this;
            java.lang.CharSequence r0 = r8.getText()
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            android.net.Uri r0 = r8.getUri()
            java.lang.String r1 = "DeviceTools"
            if (r0 == 0) goto L_0x00b6
            r8 = 0
            android.content.Context r2 = r7.context     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            java.lang.String r3 = "text/*"
            android.content.res.AssetFileDescriptor r2 = r2.openTypedAssetFileDescriptor(r0, r3, r8)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0022
            java.io.FileInputStream r8 = r2.createInputStream()     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
        L_0x0022:
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            r2.<init>(r8, r3)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            r4 = 128(0x80, float:1.794E-43)
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            r4 = 8192(0x2000, float:1.14794E-41)
            char[] r4 = new char[r4]     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
        L_0x0034:
            int r5 = r2.read(r4)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            if (r5 <= 0) goto L_0x003f
            r6 = 0
            r3.append(r4, r6, r5)     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            goto L_0x0034
        L_0x003f:
            java.lang.String r0 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0079, IOException -> 0x0054 }
            if (r8 == 0) goto L_0x0051
            r8.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x0051
        L_0x0049:
            r8 = move-exception
            java.lang.String r8 = java.lang.String.valueOf(r8)
            android.util.Log.e(r1, r8)
        L_0x0051:
            return r0
        L_0x0052:
            r0 = move-exception
            goto L_0x00a7
        L_0x0054:
            r0 = move-exception
            java.lang.String r2 = "Failure loading text"
            android.util.Log.w(r1, r2, r0)     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r2.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0052 }
            r2.append(r0)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0052 }
            if (r8 == 0) goto L_0x0078
            r8.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0078
        L_0x0070:
            r8 = move-exception
            java.lang.String r8 = java.lang.String.valueOf(r8)
            android.util.Log.e(r1, r8)
        L_0x0078:
            return r0
        L_0x0079:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = "Unable to open content URI as text, ignoring... "
            r3.<init>(r4)     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x0052 }
            r3.append(r4)     // Catch:{ all -> 0x0052 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0052 }
            android.util.Log.d(r1, r3, r2)     // Catch:{ all -> 0x0052 }
            if (r8 == 0) goto L_0x009d
            r8.close()     // Catch:{ IOException -> 0x0095 }
            goto L_0x009d
        L_0x0095:
            r8 = move-exception
            java.lang.String r8 = java.lang.String.valueOf(r8)
            android.util.Log.e(r1, r8)
        L_0x009d:
            java.lang.String r8 = "Couldn't open the URI as a stream, then the URI itself probably serves fairly well as a textual representation"
            android.util.Log.d(r1, r8)
            java.lang.String r8 = r0.toString()
            return r8
        L_0x00a7:
            if (r8 == 0) goto L_0x00b5
            r8.close()     // Catch:{ IOException -> 0x00ad }
            goto L_0x00b5
        L_0x00ad:
            r8 = move-exception
            java.lang.String r8 = java.lang.String.valueOf(r8)
            android.util.Log.e(r1, r8)
        L_0x00b5:
            throw r0
        L_0x00b6:
            android.content.Intent r8 = r8.getIntent()
            if (r8 == 0) goto L_0x00c7
            java.lang.String r0 = "all we have is an Intent, then we can just turn that into text"
            android.util.Log.d(r1, r0)
            r0 = 1
            java.lang.String r8 = r8.toUri(r0)
            return r8
        L_0x00c7:
            java.lang.String r8 = ""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DeviceTools.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.content.ClipData$Item):java.lang.CharSequence");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the unique device specific 'AndroidID'. Developed by Cian.")
    public String GetDeviceId() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    @SimpleFunction(description = "This returns TRUE if ADB debugging is enabled, which could be a sign of hacking your app, or a compromised device. Developed by Cian.")
    public boolean isADBDebuggingEnabled() throws Exception {
        int i = Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled");
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = i;
        return i > 0;
    }

    @SimpleFunction(description = "Returns TRUE if the device operating on an emulator. Developed by Cian.")
    public boolean isEmulator() {
        return Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.BRAND.equalsIgnoreCase("generic") || Build.BRAND.equalsIgnoreCase("generic_x86") || Build.BRAND.equalsIgnoreCase("TTVM") || Build.BRAND.contains("Andy") || Build.DEVICE.toLowerCase().contains("generic") || Build.DEVICE.toLowerCase().contains("generic_x86") || Build.DEVICE.contains("Andy") || Build.DEVICE.contains("ttVM_Hdragon") || Build.DEVICE.contains("Droid4X") || Build.DEVICE.contains("nox") || Build.DEVICE.contains("generic_x86_64") || Build.DEVICE.contains("vbox86p") || Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.contains("generic/sdk/generic") || Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") || Build.FINGERPRINT.contains("Andy") || Build.FINGERPRINT.contains("ttVM_Hdragon") || Build.FINGERPRINT.contains("generic_x86_64") || Build.FINGERPRINT.contains("generic/google_sdk/generic") || Build.FINGERPRINT.contains("vbox86p") || Build.FINGERPRINT.contains("generic/vbox86p/vbox86p") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.HARDWARE.equalsIgnoreCase("goldfish") || Build.HARDWARE.equalsIgnoreCase("vbox86") || Build.HARDWARE.contains("nox") || Build.HARDWARE.contains("ttVM_x86") || Build.MANUFACTURER.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN) || Build.MANUFACTURER.equalsIgnoreCase("Genymotion") || Build.MANUFACTURER.contains("Andy") || Build.MANUFACTURER.contains("MIT") || Build.MANUFACTURER.contains("nox") || Build.MANUFACTURER.contains("TiantianVM") || Build.MODEL.equalsIgnoreCase("sdk") || Build.MODEL.equalsIgnoreCase("google_sdk") || Build.MODEL.contains("Droid4X") || Build.MODEL.contains("TiantianVM") || Build.MODEL.contains("Andy") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86_64") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86") || Build.MODEL.contains("Emulator") || Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("Andy") || Build.PRODUCT.contains("ttVM_Hdragon") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("Droid4X") || Build.PRODUCT.contains("nox") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("vbox86p") || Build.SERIAL.contains("nox");
    }

    @SimpleFunction(description = "Returns TRUE if the app was installed from Play Store. Developed by Cian.")
    public boolean isPlayStoreInstalled() {
        List asList = Arrays.asList(new String[]{"com.android.vending", "com.google.android.feedback"});
        String installerPackageName = this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        return installerPackageName != null && asList.contains(installerPackageName);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0015  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns TRUE if one of 12 known patching or root emulating packages is installed. The name of the package is not returned, so the user does not know which package name to change. Developed by Cian.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean CheckForDangerousAPK() {
        /*
            r5 = this;
            android.content.Context r0 = r5.context
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r1 = 0
            java.util.List r0 = r0.getInstalledApplications(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0095
            java.lang.Object r2 = r0.next()
            android.content.pm.ApplicationInfo r2 = (android.content.pm.ApplicationInfo) r2
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = "cc.madkite.freedom"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = "devadvance.rootcloak"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".robv.android.xposed.installer"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".saurik.substrate"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".devadvance.rootcloakplus"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".zachspong.temprootremovejb"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".amphoras.hidemyroot"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".formyhm.hideroot"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".koushikdutta.rommanager"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".dimonvideo.luckypatcher"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".chelpus.lackypatch"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0093
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = ".ramdroid.appquarantine"
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x000f
        L_0x0093:
            r0 = 1
            return r0
        L_0x0095:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DeviceTools.CheckForDangerousAPK():boolean");
    }

    @SimpleFunction(description = "Total RAM size in Gigabytes.")
    public float MemoryTotal() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_TOTAL);
    }

    @SimpleFunction(description = "Total free RAM size in Gigabytes.")
    public float MemoryFree() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-memory in Gigabytes.")
    public float MemoryUsed() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_USED);
    }

    @SimpleFunction(description = "Total external storage size in Gigabytes.")
    public float ExternalStorageTotal() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Available size of external storage in Gigabytes.")
    public float ExternalStorageAvailable() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-external-storage in Gigabytes.")
    public float ExternalStorageUsed() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_USED);
    }

    @SimpleFunction(description = "Total size of internal storage in Gigabytes.")
    public float InternalStorageTotal() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Size of available internal storage in Gigabytes.")
    public float InternalStorageAvailable() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-internal-storage in Gigabytes.")
    public float InternalStorageUsed() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_USED);
    }

    private float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage deviceStorage) {
        float f;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0.0f;
        }
        StatFs statFs = new StatFs(QUtil.getExternalStorageDir(this.context).getPath());
        long blockSize = (long) statFs.getBlockSize();
        long blockCount = (((long) statFs.getBlockCount()) * blockSize) / 1048576;
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * blockSize) / 1048576;
        long j = blockCount - availableBlocks;
        int i = AnonymousClass3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 1) {
            f = (float) blockCount;
        } else if (i == 2) {
            f = (float) availableBlocks;
        } else if (i != 3) {
            return 0.0f;
        } else {
            f = (float) j;
        }
        return f / 1000.0f;
    }

    /* renamed from: com.google.appinventor.components.runtime.DeviceTools$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

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
                com.google.appinventor.components.runtime.util.DeviceStorage[] r0 = com.google.appinventor.components.runtime.util.DeviceStorage.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r0
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_TOTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_USED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_TOTAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_USED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_TOTAL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_USED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DeviceTools.AnonymousClass3.<clinit>():void");
        }
    }

    private static float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage deviceStorage) {
        float f;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = (long) statFs.getBlockSize();
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * blockSize) / 1048576;
        long blockCount = (((long) statFs.getBlockCount()) * blockSize) / 1048576;
        long j = blockCount - availableBlocks;
        int i = AnonymousClass3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 4) {
            f = (float) blockCount;
        } else if (i == 5) {
            f = (float) availableBlocks;
        } else if (i != 6) {
            return 0.0f;
        } else {
            f = (float) j;
        }
        return f / 1000.0f;
    }

    private float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage deviceStorage) {
        float f;
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem / 1048576;
        long j2 = memoryInfo.totalMem / 1048576;
        long j3 = j2 - j;
        int i = AnonymousClass3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 7) {
            f = (float) j2;
        } else if (i == 8) {
            f = (float) j;
        } else if (i != 9) {
            return 0.0f;
        } else {
            f = (float) j3;
        }
        return f / 1000.0f;
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceTools deviceTools) {
        try {
            deviceTools.GotIMEI(((TelephonyManager) deviceTools.context.getSystemService("phone")).getDeviceId());
        } catch (PermissionException e) {
            deviceTools.form.dispatchPermissionDeniedEvent((Component) deviceTools, "GetIMEI", e);
            Log.e("DeviceTools", String.valueOf(e));
        } catch (Exception e2) {
            Log.e("DeviceTools", String.valueOf(e2));
        }
    }

    static /* synthetic */ void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceTools deviceTools) {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                deviceTools.GotSerial(Build.getSerial());
                return;
            }
            deviceTools.GotSerial(Build.SERIAL);
        } catch (PermissionException e) {
            deviceTools.form.dispatchPermissionDeniedEvent((Component) deviceTools, "GetSerial", e);
            Log.e("DeviceTools", String.valueOf(e));
        } catch (Exception e2) {
            Log.e("DeviceTools", String.valueOf(e2));
        }
    }
}
