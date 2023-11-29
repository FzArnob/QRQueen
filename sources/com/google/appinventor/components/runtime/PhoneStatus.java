package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesNativeLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.PackageInstaller;
import com.google.appinventor.components.runtime.util.WebRTCNativeMgr;
import java.security.MessageDigest;
import java.util.Formatter;

@UsesNativeLibraries(v7aLibraries = "libjingle_peerconnection_so.so", v8aLibraries = "libjingle_peerconnection_so.so", x86_64Libraries = "libjingle_peerconnection_so.so")
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Component that returns information about the phone.", iconName = "images/phoneip.png", nonVisible = true, version = 1)
@UsesLibraries({"webrtc.jar", "google-http-client-beta.jar", "google-http-client-android2-beta.jar", "google-http-client-android3-beta.jar"})
@SimpleObject
public class PhoneStatus extends AndroidNonvisibleComponent implements Component {
    private static Activity activity = null;
    private static PhoneStatus hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private static boolean l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0 = true;
    /* access modifiers changed from: private */
    public final Form form;
    private String ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = null;
    private String yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = null;

    public PhoneStatus(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        activity = componentContainer.$context();
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this;
        }
    }

    @SimpleFunction(description = "Returns the IP address of the phone in the form of a String")
    public static String GetWifiIpAddress() {
        return isConnected() ? intToIp(((WifiManager) activity.getSystemService("wifi")).getDhcpInfo().ipAddress) : "Error: No Wifi Connection";
    }

    @SimpleFunction(description = "Returns TRUE if the phone is on Wifi, FALSE otherwise")
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getNetworkInfo(1) : null;
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    @SimpleFunction(description = "Establish the secret seed for HOTP generation. Return the SHA1 of the provided seed, this will be used to contact the rendezvous server. Note: This code also starts the connection negotiation process if we are using WebRTC. This is a bit of a kludge...")
    public String setHmacSeedReturnCode(String str, String str2) {
        if (str.equals("")) {
            return "";
        }
        String str3 = this.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc;
        if (str3 != null) {
            if (!str3.equals(str)) {
                Notifier.oneButtonAlert((Activity) this.form, "You cannot use two codes with one start up of the Companion. You should restart the Companion and try again.", "Warning", "OK", (Runnable) new Runnable() {
                    public final void run() {
                        PhoneStatus.this.form.finish();
                        System.exit(0);
                    }
                });
            }
            return this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb;
        }
        this.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = str;
        if (!l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0) {
            AppInvHTTPD.setHmacKey(str);
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer(digest.length << 1);
            Formatter formatter = new Formatter(stringBuffer);
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                formatter.format("%02x", new Object[]{Byte.valueOf(digest[i])});
            }
            Log.d("PhoneStatus", "Seed = ".concat(String.valueOf(str)));
            Log.d("PhoneStatus", "Code = " + stringBuffer.toString());
            String stringBuffer2 = stringBuffer.toString();
            this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = stringBuffer2;
            return stringBuffer2;
        } catch (Exception e) {
            Log.e("PhoneStatus", "Exception getting SHA1 Instance", e);
            return "";
        }
    }

    @SimpleFunction(description = "Returns true if we are running in the emulator or USB Connection")
    public boolean isDirect() {
        Log.d("PhoneStatus", "android.os.Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
        Log.d("PhoneStatus", "android.os.Build.PRODUCT = " + Build.PRODUCT);
        if (Build.PRODUCT.contains("google_sdk")) {
            return true;
        }
        Form form2 = this.form;
        if (form2 instanceof ReplForm) {
            return ((ReplForm) form2).isDirect();
        }
        return false;
    }

    @SimpleFunction(description = "Start the WebRTC engine")
    public void startWebRTC(String str, String str2) {
        if (l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0) {
            WebRTCNativeMgr webRTCNativeMgr = new WebRTCNativeMgr(str, str2);
            webRTCNativeMgr.initiate((ReplForm) this.form, activity, this.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc);
            ((ReplForm) this.form).setWebRTCMgr(webRTCNativeMgr);
        }
    }

    @SimpleFunction(description = "Start the internal AppInvHTTPD to listen for incoming forms. FOR REPL USE ONLY!")
    public void startHTTPD(boolean z) {
        if (this.form.isRepl()) {
            ((ReplForm) this.form).startHTTPD(z);
        }
    }

    @SimpleFunction(description = "Declare that we have loaded our initial assets and other assets should come from the sdcard")
    public void setAssetsLoaded() {
        Form form2 = this.form;
        if (form2 instanceof ReplForm) {
            ((ReplForm) form2).setAssetsLoaded();
        }
    }

    @SimpleFunction(description = "Causes an Exception, used to debug exception processing.")
    public static void doFault() throws Exception {
        throw new Exception("doFault called!");
    }

    @SimpleFunction(description = "Downloads the URL and installs it as an Android Package")
    public void installURL(String str) {
        PackageInstaller.doPackageInstall(this.form, str);
    }

    @SimpleFunction(description = "Really Exit the Application")
    public void shutdown() {
        this.form.finish();
        System.exit(0);
    }

    @SimpleEvent
    public void OnSettings() {
        EventDispatcher.dispatchEvent(this, "OnSettings", new Object[0]);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void WebRTC(boolean z) {
        l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0 = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If True we are using WebRTC to talk to the server.")
    public boolean WebRTC() {
        return l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0;
    }

    @SimpleFunction(description = "Get the current Android SDK Level")
    public int SdkLevel() {
        return Build.VERSION.SDK_INT;
    }

    @SimpleFunction(description = "Return the our VersionName property")
    public String GetVersionName() {
        try {
            return this.form.getPackageManager().getPackageInfo(this.form.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("PhoneStatus", "Unable to get VersionName", e);
            return "UNKNOWN";
        }
    }

    @SimpleFunction(description = "Return the app that installed us")
    public String GetInstaller() {
        String installerPackageName = this.form.getPackageManager().getInstallerPackageName(YaVersion.ACCEPTABLE_COMPANION_PACKAGE);
        return installerPackageName == null ? "sideloaded" : installerPackageName;
    }

    public static boolean getUseWebRTC() {
        return l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0;
    }

    public static String intToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + (i >>> 24);
    }
}
