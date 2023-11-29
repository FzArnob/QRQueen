package com.google.appinventor.components.runtime.util;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.runtime.PhoneStatus;
import com.google.appinventor.components.runtime.ReplForm;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RetValManager {
    private static ArrayList<JSONObject> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = new ArrayList<>(10);
    private static final Object semaphore = new Object();

    private RetValManager() {
    }

    public static void appendReturnValue(String str, String str2, String str3) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, str2);
                jSONObject.put(CommonProperties.TYPE, "return");
                jSONObject.put(CommonProperties.VALUE, str3);
                jSONObject.put("blockid", str);
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    public static void sendError(String str) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
                jSONObject.put(CommonProperties.TYPE, "error");
                jSONObject.put(CommonProperties.VALUE, str);
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    public static void pushScreen(String str, Object obj) {
        Object obj2 = semaphore;
        synchronized (obj2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
                jSONObject.put(CommonProperties.TYPE, "pushScreen");
                jSONObject.put("screen", str);
                if (obj != null) {
                    jSONObject.put(CommonProperties.VALUE, obj.toString());
                }
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj2.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    public static void popScreen(String str) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
                jSONObject.put(CommonProperties.TYPE, "popScreen");
                if (str != null) {
                    jSONObject.put(CommonProperties.VALUE, str.toString());
                }
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    public static void assetTransferred(String str) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
                jSONObject.put(CommonProperties.TYPE, "assetTransferred");
                if (str != null) {
                    jSONObject.put(CommonProperties.VALUE, str.toString());
                }
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    public static void extensionsLoaded() {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
                jSONObject.put(CommonProperties.TYPE, "extensionsLoaded");
                boolean isEmpty = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.isEmpty();
                qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(jSONObject);
                if (PhoneStatus.getUseWebRTC()) {
                    J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU();
                } else if (isEmpty) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e("RetValManager", "Error building retval", e);
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0007 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0007 A[LOOP:0: B:2:0x0007->B:27:0x0007, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String fetch(boolean r8) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Object r2 = semaphore
            monitor-enter(r2)
        L_0x0007:
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0053 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0024
            if (r8 == 0) goto L_0x0024
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0053 }
            long r3 = r3 - r0
            r5 = 9900(0x26ac, double:4.8912E-320)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0024
            java.lang.Object r3 = semaphore     // Catch:{ InterruptedException -> 0x0007 }
            r4 = 10000(0x2710, double:4.9407E-320)
            r3.wait(r4)     // Catch:{ InterruptedException -> 0x0007 }
            goto L_0x0007
        L_0x0024:
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch:{ all -> 0x0053 }
            java.util.ArrayList<org.json.JSONObject> r0 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0053 }
            r8.<init>(r0)     // Catch:{ all -> 0x0053 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ all -> 0x0053 }
            r0.<init>()     // Catch:{ all -> 0x0053 }
            java.lang.String r1 = "status"
            java.lang.String r3 = "OK"
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r1 = "values"
            r0.put(r1, r8)     // Catch:{ JSONException -> 0x0047 }
            java.util.ArrayList<org.json.JSONObject> r8 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0053 }
            r8.clear()     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0053 }
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            return r8
        L_0x0047:
            r8 = move-exception
            java.lang.String r0 = "RetValManager"
            java.lang.String r1 = "Error fetching retvals"
            android.util.Log.e(r0, r1, r8)     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = "{\"status\" : \"BAD\", \"message\" : \"Failure in RetValManager\"}"
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            return r8
        L_0x0053:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.fetch(boolean):java.lang.String");
    }

    private static void J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, "OK");
            jSONObject.put("values", new JSONArray(qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE));
            ReplForm.returnRetvals(jSONObject.toString());
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.clear();
        } catch (JSONException e) {
            Log.e("RetValManager", "Error building retval", e);
        }
    }
}
