package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONException;

public class AssetFetcher {
    /* access modifiers changed from: private */
    public static final String LOG_TAG = "AssetFetcher";
    private static ExecutorService background = Executors.newSingleThreadExecutor();
    private static volatile boolean inError = false;
    private static final Object semaphore = new Object();

    private AssetFetcher() {
    }

    public static void fetchAssets(final String str, final String str2, final String str3, final String str4) {
        background.submit(new Runnable() {
            public final void run() {
                if (AssetFetcher.getFile(str3 + "/ode/download/file/" + str2 + InternalZipConstants.ZIP_FILE_SEPARATOR + str4, str, str4, 0) != null) {
                    RetValManager.assetTransferred(str4);
                }
            }
        });
    }

    public static void upgradeCompanion(final String str, final String str2) {
        background.submit(new Runnable() {
            public final void run() {
                String[] split = str2.split(InternalZipConstants.ZIP_FILE_SEPARATOR, 0);
                File access$000 = AssetFetcher.getFile(str2, str, split[split.length - 1], 0);
                if (access$000 != null) {
                    try {
                        Form activeForm = Form.getActiveForm();
                        Intent intent = new Intent("android.intent.action.VIEW");
                        String packageName = activeForm.$context().getPackageName();
                        Activity $context = activeForm.$context();
                        intent.setDataAndType(FileProvider.getUriForFile($context, packageName + ".provider", access$000), "application/vnd.android.package-archive");
                        intent.setFlags(1);
                        activeForm.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(AssetFetcher.LOG_TAG, "ERROR_UNABLE_TO_GET", e);
                        RetValManager.sendError("Unable to Install new Companion Package.");
                    }
                }
            }
        });
    }

    public static void loadExtensions(String str) {
        String str2 = LOG_TAG;
        Log.d(str2, "loadExtensions called jsonString = ".concat(String.valueOf(str)));
        try {
            ReplForm replForm = (ReplForm) Form.getActiveForm();
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() == 0) {
                Log.d(str2, "loadExtensions: No Extensions");
                RetValManager.extensionsLoaded();
                return;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                String optString = jSONArray.optString(i);
                if (optString != null) {
                    Log.d(LOG_TAG, "loadExtensions, extensionName = ".concat(String.valueOf(optString)));
                    arrayList.add(optString);
                    i++;
                } else {
                    Log.e(LOG_TAG, "extensionName was null");
                    return;
                }
            }
            try {
                replForm.loadComponents(arrayList);
                RetValManager.extensionsLoaded();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in form.loadComponents", e);
            }
        } catch (JSONException e2) {
            Log.e(LOG_TAG, "JSON Exception parsing extension string", e2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf A[Catch:{ all -> 0x00b8, Exception -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d6 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File getFile(final java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11) {
        /*
        L_0x0000:
            com.google.appinventor.components.runtime.Form r0 = com.google.appinventor.components.runtime.Form.getActiveForm()
            r1 = 0
            r2 = 1
            if (r11 <= r2) goto L_0x0020
            java.lang.Object r3 = semaphore
            monitor-enter(r3)
            boolean r9 = inError     // Catch:{ all -> 0x001d }
            if (r9 == 0) goto L_0x0011
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            return r1
        L_0x0011:
            inError = r2     // Catch:{ all -> 0x001d }
            com.google.appinventor.components.runtime.util.AssetFetcher$3 r9 = new com.google.appinventor.components.runtime.util.AssetFetcher$3     // Catch:{ all -> 0x001d }
            r9.<init>(r8)     // Catch:{ all -> 0x001d }
            r0.runOnUiThread(r9)     // Catch:{ all -> 0x001d }
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            return r1
        L_0x001d:
            r8 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            throw r8
        L_0x0020:
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x00d7 }
            r4.<init>(r8)     // Catch:{ Exception -> 0x00d7 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ Exception -> 0x00d7 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Exception -> 0x00d7 }
            if (r4 == 0) goto L_0x00cd
            java.lang.String r1 = "GET"
            r4.setRequestMethod(r1)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r1 = "Cookie"
            java.lang.String r5 = "Kodular = "
            java.lang.String r6 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x00d7 }
            r4.addRequestProperty(r1, r5)     // Catch:{ Exception -> 0x00d7 }
            int r1 = r4.getResponseCode()     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r5 = LOG_TAG     // Catch:{ Exception -> 0x00d7 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r7 = "asset = "
            r6.<init>(r7)     // Catch:{ Exception -> 0x00d7 }
            r6.append(r10)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r7 = " responseCode = "
            r6.append(r7)     // Catch:{ Exception -> 0x00d7 }
            r6.append(r1)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r1 = r6.toString()     // Catch:{ Exception -> 0x00d7 }
            android.util.Log.d(r5, r1)     // Catch:{ Exception -> 0x00d7 }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r0 = com.google.appinventor.components.runtime.util.QUtil.getReplAssetPath(r0, r2)     // Catch:{ Exception -> 0x00d7 }
            r5 = 7
            java.lang.String r5 = r10.substring(r5)     // Catch:{ Exception -> 0x00d7 }
            r1.<init>(r0, r5)     // Catch:{ Exception -> 0x00d7 }
            java.io.File r0 = r1.getParentFile()     // Catch:{ Exception -> 0x00d7 }
            boolean r5 = r0.exists()     // Catch:{ Exception -> 0x00d7 }
            if (r5 != 0) goto L_0x0090
            boolean r5 = r0.mkdirs()     // Catch:{ Exception -> 0x00d7 }
            if (r5 == 0) goto L_0x0080
            goto L_0x0090
        L_0x0080:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r2 = "Unable to create assets directory "
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r0 = r2.concat(r0)     // Catch:{ Exception -> 0x00d7 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x00d7 }
            throw r1     // Catch:{ Exception -> 0x00d7 }
        L_0x0090:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00d7 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ Exception -> 0x00d7 }
            r6 = 4096(0x1000, float:5.74E-42)
            r0.<init>(r5, r6)     // Catch:{ Exception -> 0x00d7 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00d7 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00d7 }
            r7.<init>(r1)     // Catch:{ Exception -> 0x00d7 }
            r5.<init>(r7, r6)     // Catch:{ Exception -> 0x00d7 }
        L_0x00a5:
            int r6 = r0.read()     // Catch:{ IOException -> 0x00ba }
            r7 = -1
            if (r6 == r7) goto L_0x00b0
            r5.write(r6)     // Catch:{ IOException -> 0x00ba }
            goto L_0x00a5
        L_0x00b0:
            r5.flush()     // Catch:{ IOException -> 0x00ba }
            r5.close()     // Catch:{ Exception -> 0x00d7 }
            r2 = 0
            goto L_0x00c5
        L_0x00b8:
            r0 = move-exception
            goto L_0x00c9
        L_0x00ba:
            r0 = move-exception
            java.lang.String r3 = LOG_TAG     // Catch:{ all -> 0x00b8 }
            java.lang.String r6 = "copying assets"
            android.util.Log.e(r3, r6, r0)     // Catch:{ all -> 0x00b8 }
            r5.close()     // Catch:{ Exception -> 0x00d7 }
        L_0x00c5:
            r4.disconnect()     // Catch:{ Exception -> 0x00d7 }
            goto L_0x00cd
        L_0x00c9:
            r5.close()     // Catch:{ Exception -> 0x00d7 }
            throw r0     // Catch:{ Exception -> 0x00d7 }
        L_0x00cd:
            if (r2 == 0) goto L_0x00d6
            int r0 = r11 + 1
            java.io.File r8 = getFile(r8, r9, r10, r0)     // Catch:{ Exception -> 0x00d7 }
            return r8
        L_0x00d6:
            return r1
        L_0x00d7:
            r0 = move-exception
            java.lang.String r1 = LOG_TAG
            java.lang.String r2 = "Exception while fetching "
            java.lang.String r3 = java.lang.String.valueOf(r8)
            java.lang.String r2 = r2.concat(r3)
            android.util.Log.e(r1, r2, r0)
            int r11 = r11 + 1
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AssetFetcher.getFile(java.lang.String, java.lang.String, java.lang.String, int):java.io.File");
    }
}
