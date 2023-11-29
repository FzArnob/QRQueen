package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Properties;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kawa.standard.Scheme;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

public class AppInvHTTPD extends NanoHTTPD {
    private static byte[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private static int nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE;
    private final Handler androidUIHandler = new Handler();
    /* access modifiers changed from: private */
    public ReplForm form;
    private File hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private Language hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean secure;

    public AppInvHTTPD(int i, File file, boolean z, ReplForm replForm) throws IOException {
        super(i, file);
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = file;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Scheme.getInstance("scheme");
        this.form = replForm;
        this.secure = z;
        ModuleExp.mustNeverCompile();
    }

    public NanoHTTPD.Response serve(String str, String str2, Properties properties, Properties properties2, Properties properties3, Socket socket) {
        Boolean bool;
        NanoHTTPD.Response response;
        NanoHTTPD.Response response2;
        String str3 = str;
        String str4 = str2;
        Properties properties4 = properties;
        Properties properties5 = properties2;
        Properties properties6 = properties3;
        Log.d("AppInvHTTPD", str4 + " '" + str3 + "' ");
        String str5 = "Access-Control-Allow-Headers";
        String str6 = "POST,OPTIONS,GET,HEAD,PUT";
        if (this.secure) {
            String hostAddress = socket.getInetAddress().getHostAddress();
            if (!hostAddress.equals("127.0.0.1")) {
                Log.d("AppInvHTTPD", "Debug: hostAddress = " + hostAddress + " while in secure mode, closing connection.");
                NanoHTTPD.Response response3 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Source Location " + hostAddress + "\"}");
                response3.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                response3.addHeader(str5, "origin, content-type");
                response3.addHeader("Access-Control-Allow-Methods", str6);
                response3.addHeader("Allow", str6);
                return response3;
            }
        }
        String str7 = "application/json";
        String str8 = "Allow";
        if (str4.equals("OPTIONS")) {
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String str9 = (String) propertyNames.nextElement();
                Log.d("AppInvHTTPD", "  HDR: '" + str9 + "' = '" + properties4.getProperty(str9) + "'");
                propertyNames = propertyNames;
                str6 = str6;
            }
            NanoHTTPD.Response response4 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, "text/plain", "OK");
            response4.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
            response4.addHeader(str5, "origin, content-type");
            String str10 = str6;
            response4.addHeader("Access-Control-Allow-Methods", str10);
            response4.addHeader(str8, str10);
            return response4;
        }
        String str11 = "'";
        String str12 = str6;
        String str13 = str8;
        String str14 = "' = '";
        if (str3.equals("/_newblocks")) {
            ClassLoader contextClassLoader = Looper.getMainLooper().getThread().getContextClassLoader();
            Thread currentThread = Thread.currentThread();
            if (currentThread.getContextClassLoader() != contextClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
            Properties properties7 = properties2;
            String property = properties7.getProperty("seq", "0");
            int parseInt = Integer.parseInt(property);
            String property2 = properties7.getProperty("blockid");
            String str15 = "text/plain";
            String property3 = properties7.getProperty("code");
            String str16 = str13;
            String property4 = properties7.getProperty("mac", "no key provided");
            if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
                try {
                    Mac instance = Mac.getInstance("HmacSHA1");
                    String str17 = str12;
                    String str18 = "Access-Control-Allow-Methods";
                    instance.init(new SecretKeySpec(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, "RAW"));
                    byte[] doFinal = instance.doFinal((property3 + property + property2).getBytes());
                    StringBuffer stringBuffer = new StringBuffer(doFinal.length << 1);
                    Formatter formatter = new Formatter(stringBuffer);
                    int length = doFinal.length;
                    String str19 = "origin, content-type";
                    int i = 0;
                    while (i < length) {
                        formatter.format("%02x", new Object[]{Byte.valueOf(doFinal[i])});
                        i++;
                        doFinal = doFinal;
                        length = length;
                        str5 = str5;
                    }
                    String str20 = str5;
                    String stringBuffer2 = stringBuffer.toString();
                    Log.d("AppInvHTTPD", "Incoming Mac = ".concat(String.valueOf(property4)));
                    Log.d("AppInvHTTPD", "Computed Mac = ".concat(String.valueOf(stringBuffer2)));
                    Log.d("AppInvHTTPD", "Incoming seq = ".concat(String.valueOf(property)));
                    Log.d("AppInvHTTPD", "Computed seq = " + nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE);
                    Log.d("AppInvHTTPD", "blockid = ".concat(String.valueOf(property2)));
                    if (!property4.equals(stringBuffer2)) {
                        Log.e("AppInvHTTPD", "Hmac does not match");
                        ReplForm replForm = this.form;
                        replForm.dispatchErrorOccurredEvent(replForm, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid HMAC");
                        return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str7, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
                    }
                    String str21 = str7;
                    int i2 = nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE;
                    if (i2 == parseInt || i2 == parseInt + 1) {
                        int i3 = parseInt + 1;
                        if (i2 == i3) {
                            Log.e("AppInvHTTPD", "Seq Fixup Invoked");
                        }
                        nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = i3;
                        String str22 = "(begin (require <com.google.youngandroid.runtime>) (process-repl-input " + property2 + " (begin " + property3 + " )))";
                        Log.d("AppInvHTTPD", "To Eval: ".concat(String.valueOf(str22)));
                        try {
                            if (property3.equals("#f")) {
                                Log.e("AppInvHTTPD", "Skipping evaluation of #f");
                            } else {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.eval(str22);
                            }
                            response2 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str21, RetValManager.fetch(false));
                        } catch (Throwable th) {
                            Log.e("AppInvHTTPD", "newblocks: Scheme Failure", th);
                            RetValManager.appendReturnValue(property2, "BAD", th.toString());
                            response2 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str21, RetValManager.fetch(false));
                        }
                        response2.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                        response2.addHeader(str20, str19);
                        String str23 = str17;
                        response2.addHeader(str18, str23);
                        response2.addHeader(str16, str23);
                        return response2;
                    }
                    Log.e("AppInvHTTPD", "Seq does not match");
                    ReplForm replForm2 = this.form;
                    replForm2.dispatchErrorOccurredEvent(replForm2, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid Seq");
                    return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str21, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Seq\"}");
                } catch (Exception e) {
                    Log.e("AppInvHTTPD", "Error working with hmac", e);
                    ReplForm replForm3 = this.form;
                    replForm3.dispatchErrorOccurredEvent(replForm3, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC");
                    return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str15, "NOT");
                }
            } else {
                Log.e("AppInvHTTPD", "No HMAC Key");
                ReplForm replForm4 = this.form;
                replForm4.dispatchErrorOccurredEvent(replForm4, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "No HMAC Key");
                return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str7, "{\"status\" : \"BAD\", \"message\" : \"Security Error: No HMAC Key\"}");
            }
        } else {
            String str24 = "Access-Control-Allow-Methods";
            String str25 = "origin, content-type";
            String str26 = str13;
            String str27 = "text/plain";
            String str28 = str5;
            Properties properties8 = properties2;
            String str29 = str12;
            String str30 = str7;
            String str31 = "mac";
            if (str3.equals("/_values")) {
                NanoHTTPD.Response response5 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, RetValManager.fetch(true));
                response5.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                response5.addHeader(str28, str25);
                response5.addHeader(str24, str29);
                response5.addHeader(str26, str29);
                return response5;
            } else if (str3.equals("/_getversion")) {
                try {
                    String packageName = this.form.getPackageName();
                    PackageInfo packageInfo = this.form.getPackageManager().getPackageInfo(packageName, 0);
                    String installerPackageName = this.form.getPackageManager().getInstallerPackageName(YaVersion.ACCEPTABLE_COMPANION_PACKAGE);
                    String str32 = packageInfo.versionName;
                    if (installerPackageName == null) {
                        installerPackageName = "Not Known";
                    }
                    response = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"version\" : \"" + str32 + "\", \"fingerprint\" : \"" + Build.FINGERPRINT + "\", \"installer\" : \"" + installerPackageName + "\", \"package\" : \"" + packageName + "\", \"fqcn\" : true }");
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                    response = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"verison\" : \"Unknown\"");
                }
                response.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                response.addHeader(str28, str25);
                response.addHeader(str24, str29);
                response.addHeader(str26, str29);
                if (this.secure) {
                    nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = 1;
                    this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            AppInvHTTPD.this.form.clear();
                        }
                    });
                }
                return response;
            } else if (str3.equals("/_update") || str3.equals("/_install")) {
                String str33 = str28;
                String str34 = str24;
                String str35 = str29;
                String str36 = str26;
                String property5 = properties8.getProperty("url", "");
                String property6 = properties8.getProperty(str31, "");
                if (property5.equals("") || B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null || property6.equals("")) {
                    NanoHTTPD.Response response6 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"BAD\", \"message\" : \"Missing Parameters\"}");
                    response6.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                    response6.addHeader(str33, str25);
                    response6.addHeader(str34, str35);
                    response6.addHeader(str36, str35);
                    return response6;
                }
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, "RAW");
                    Mac instance2 = Mac.getInstance("HmacSHA1");
                    instance2.init(secretKeySpec);
                    byte[] doFinal2 = instance2.doFinal(property5.getBytes());
                    StringBuffer stringBuffer3 = new StringBuffer(doFinal2.length << 1);
                    Formatter formatter2 = new Formatter(stringBuffer3);
                    String str37 = property5;
                    int length2 = doFinal2.length;
                    String str38 = str36;
                    int i4 = 0;
                    while (i4 < length2) {
                        try {
                            int i5 = length2;
                            String str39 = str34;
                            byte[] bArr = doFinal2;
                            try {
                                formatter2.format("%02x", new Object[]{Byte.valueOf(doFinal2[i4])});
                                i4++;
                                doFinal2 = bArr;
                                length2 = i5;
                                str34 = str39;
                            } catch (Exception e3) {
                                e = e3;
                                str36 = str38;
                                str34 = str39;
                                Log.e("AppInvHTTPD", "Error verifying update", e);
                                ReplForm replForm5 = this.form;
                                replForm5.dispatchErrorOccurredEvent(replForm5, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC for update");
                                NanoHTTPD.Response response7 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
                                response7.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                                response7.addHeader(str33, str25);
                                response7.addHeader(str34, str35);
                                response7.addHeader(str36, str35);
                                return response7;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            str36 = str38;
                            Log.e("AppInvHTTPD", "Error verifying update", e);
                            ReplForm replForm52 = this.form;
                            replForm52.dispatchErrorOccurredEvent(replForm52, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC for update");
                            NanoHTTPD.Response response72 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
                            response72.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                            response72.addHeader(str33, str25);
                            response72.addHeader(str34, str35);
                            response72.addHeader(str36, str35);
                            return response72;
                        }
                    }
                    String str40 = str34;
                    String stringBuffer4 = stringBuffer3.toString();
                    Log.d("AppInvHTTPD", "Incoming Mac (update) = ".concat(String.valueOf(property6)));
                    Log.d("AppInvHTTPD", "Computed Mac (update) = ".concat(String.valueOf(stringBuffer4)));
                    if (!property6.equals(stringBuffer4)) {
                        Log.e("AppInvHTTPD", "Hmac does not match");
                        ReplForm replForm6 = this.form;
                        replForm6.dispatchErrorOccurredEvent(replForm6, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid HMAC (update)");
                        NanoHTTPD.Response response8 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
                        response8.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                        response8.addHeader(str33, str25);
                        response8.addHeader(str40, str35);
                        response8.addHeader(str38, str35);
                        return response8;
                    }
                    PackageInstaller.doPackageInstall(this.form, str37);
                    NanoHTTPD.Response response9 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"OK\", \"message\" : \"Update Should Happen\"}");
                    response9.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                    response9.addHeader(str33, str25);
                    response9.addHeader(str40, str35);
                    response9.addHeader(str38, str35);
                    return response9;
                } catch (Exception e5) {
                    e = e5;
                    Log.e("AppInvHTTPD", "Error verifying update", e);
                    ReplForm replForm522 = this.form;
                    replForm522.dispatchErrorOccurredEvent(replForm522, "AppInvHTTPD", ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC for update");
                    NanoHTTPD.Response response722 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str30, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
                    response722.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                    response722.addHeader(str33, str25);
                    response722.addHeader(str34, str35);
                    response722.addHeader(str36, str35);
                    return response722;
                }
            } else if (str3.equals("/_package")) {
                String property7 = properties8.getProperty("package", (String) null);
                if (property7 == null) {
                    return new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str27, "NOT OK");
                }
                Log.d("AppInvHTTPD", this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + InternalZipConstants.ZIP_FILE_SEPARATOR + property7);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.fromFile(new File(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + InternalZipConstants.ZIP_FILE_SEPARATOR + property7)), "application/vnd.android.package-archive");
                this.form.startActivity(intent);
                NanoHTTPD.Response response10 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str27, "OK");
                response10.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                response10.addHeader(str28, str25);
                response10.addHeader(str24, str29);
                response10.addHeader(str26, str29);
                return response10;
            } else {
                String str41 = str26;
                if (str.equals("/_extensions")) {
                    return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(properties8);
                }
                String str42 = str11;
                String str43 = str29;
                if (str2.equals("PUT")) {
                    Boolean bool2 = Boolean.FALSE;
                    String str44 = str41;
                    String str45 = "OK";
                    String str46 = str43;
                    String property8 = properties3.getProperty("content", (String) null);
                    if (property8 != null) {
                        File file = new File(property8);
                        String property9 = properties8.getProperty("filename", (String) null);
                        if (property9 != null && (property9.startsWith("..") || property9.endsWith("..") || property9.indexOf("../") >= 0)) {
                            Log.d("AppInvHTTPD", " Ignoring invalid filename: ".concat(String.valueOf(property9)));
                            property9 = null;
                        }
                        if (property9 != null) {
                            File file2 = new File(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + InternalZipConstants.ZIP_FILE_SEPARATOR + property9);
                            File parentFile = file2.getParentFile();
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                            if (!file.renameTo(file2)) {
                                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file, file2);
                                file.delete();
                            }
                            bool = bool2;
                        } else {
                            file.delete();
                            Log.e("AppInvHTTPD", "Received content without a file name!");
                            bool = Boolean.TRUE;
                        }
                    } else {
                        Log.e("AppInvHTTPD", "Received PUT without content.");
                        bool = Boolean.TRUE;
                    }
                    if (bool.booleanValue()) {
                        NanoHTTPD.Response response11 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str27, "NOTOK");
                        response11.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                        response11.addHeader(str28, str25);
                        response11.addHeader(str24, str46);
                        response11.addHeader(str44, str46);
                        return response11;
                    }
                    NanoHTTPD.Response response12 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str27, str45);
                    response12.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                    response12.addHeader(str28, str25);
                    response12.addHeader(str24, str46);
                    response12.addHeader(str44, str46);
                    return response12;
                }
                String str47 = str41;
                String str48 = "OK";
                String str49 = str43;
                String str50 = str42;
                Properties properties9 = properties3;
                Enumeration<?> propertyNames2 = properties.propertyNames();
                while (propertyNames2.hasMoreElements()) {
                    String str51 = str49;
                    String str52 = (String) propertyNames2.nextElement();
                    Log.d("AppInvHTTPD", "  HDR: '" + str52 + str14 + properties.getProperty(str52) + str50);
                    str49 = str51;
                    str24 = str24;
                    str25 = str25;
                    str28 = str28;
                }
                String str53 = str25;
                String str54 = str49;
                String str55 = str28;
                String str56 = str24;
                String str57 = str14;
                Properties properties10 = properties;
                Enumeration<?> propertyNames3 = properties2.propertyNames();
                while (propertyNames3.hasMoreElements()) {
                    String str58 = (String) propertyNames3.nextElement();
                    Log.d("AppInvHTTPD", "  PRM: '" + str58 + str57 + properties8.getProperty(str58) + str50);
                    propertyNames3 = propertyNames3;
                    Properties properties11 = properties;
                }
                Enumeration<?> propertyNames4 = properties3.propertyNames();
                if (propertyNames4.hasMoreElements()) {
                    String str59 = (String) propertyNames4.nextElement();
                    String property10 = properties9.getProperty(str59);
                    String property11 = properties8.getProperty(str59);
                    if (property11.startsWith("..") || property11.endsWith("..") || property11.indexOf("../") >= 0) {
                        Log.d("AppInvHTTPD", " Ignoring invalid filename: ".concat(String.valueOf(property11)));
                        property11 = null;
                    }
                    File file3 = new File(property10);
                    if (property11 == null) {
                        file3.delete();
                    } else {
                        File file4 = new File(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + InternalZipConstants.ZIP_FILE_SEPARATOR + property11);
                        if (!file3.renameTo(file4)) {
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file3, file4);
                            file3.delete();
                        }
                    }
                    Log.d("AppInvHTTPD", " UPLOADED: '" + property11 + "' was at '" + property10 + str50);
                    NanoHTTPD.Response response13 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, str27, str48);
                    response13.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                    response13.addHeader(str55, str53);
                    String str60 = str54;
                    response13.addHeader(str56, str60);
                    response13.addHeader(str47, str60);
                    return response13;
                }
                return serveFile(str, properties, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, true);
            }
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[32768];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Properties properties) {
        try {
            JSONArray jSONArray = new JSONArray(properties.getProperty("extensions", "[]"));
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.optString(i);
                if (optString == null) {
                    return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Invalid JSON content at index ".concat(String.valueOf(i)));
                }
                arrayList.add(optString);
            }
            try {
                this.form.loadComponents(arrayList);
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, "text/plain", "OK"));
            } catch (Exception e) {
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Throwable) e);
            }
        } catch (JSONException e2) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Throwable) e2);
        }
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, "BAD");
            jSONObject.put("message", str);
        } catch (JSONException e) {
            Log.wtf("AppInvHTTPD", "Unable to write basic JSON content", e);
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, "application/json", jSONObject.toString()));
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Throwable th) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(th.toString());
    }

    private static NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(NanoHTTPD.Response response) {
        response.addHeader("Access-Control-Allow-Origin", Marker.ANY_MARKER);
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return response;
    }

    public static void setHmacKey(String str) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str.getBytes();
        nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = 1;
    }

    public void resetSeq() {
        nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = 1;
    }
}
