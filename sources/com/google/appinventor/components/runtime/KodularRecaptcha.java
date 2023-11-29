package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.HashMap;
import java.util.Map;

@DesignerComponent(category = ComponentCategory.GOOGLE, helpUrl = "https://docs.kodular.io/components/google/recaptcha/", iconName = "images/recaptcha.png", nonVisible = true, version = 1)
@UsesLibraries({"play-services-safetynet.aar", "play-services-safetynet.jar", "play-services-base.aar", "play-services-base.jar", "play-services-basement.aar", "play-services-basement.jar", "play-services-tasks.aar", "play-services-tasks.jar"})
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class KodularRecaptcha extends AndroidNonvisibleComponent {
    private String dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = "";
    private final Form form;
    private final Map<String, String> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = new HashMap<String, String>() {
        {
            put("missing-input-secret", "The secret parameter is missing.");
            put("invalid-input-secret", "The secret parameter is invalid or malformed.");
            put("missing-input-response", "The response parameter is missing.");
            put("invalid-input-response", "The response parameter is invalid or malformed.");
            put("bad-request", "The request is invalid or malformed.");
            put("timeout-or-duplicate", "The response is no longer valid: either is too old or has been used previously.");
        }
    };

    public KodularRecaptcha(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        Log.d("ReCaptcha", "Kodular reCaptcha component created");
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the Google reCaptcha API Site Key. It is mandatory to use reCaptcha, as it will identify user's request with the challenge prompt.")
    public void SiteKey(String str) {
        this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the Google reCaptcha API Site Key.")
    public String SiteKey() {
        return this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    }

    @SimpleFunction(description = "Prompt the user with a Google reCaptcha challenge. Needs a reCaptcha API Site Key to work.")
    public void Prompt() {
        SafetyNet.getClient(this.form).verifyWithRecaptcha(this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR).addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                String tokenResult = ((SafetyNetApi.RecaptchaTokenResponse) obj).getTokenResult();
                if (tokenResult == null || tokenResult.isEmpty()) {
                    KodularRecaptcha.this.PromptErrored(-2, "No UserResponse");
                } else {
                    KodularRecaptcha.this.PromptSucceeded(tokenResult);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            public final void onFailure(Exception exc) {
                if (exc instanceof ApiException) {
                    int statusCode = ((ApiException) exc).getStatusCode();
                    KodularRecaptcha.this.PromptErrored(statusCode, SafetyNetStatusCodes.getStatusCodeString(statusCode));
                    return;
                }
                KodularRecaptcha.this.PromptErrored(-1, "Unknown ApiException");
            }
        });
    }

    @SimpleEvent(description = "Event will be invoked Google reCaptcha returns a success status code, returning the user response token which should be verified later.")
    public void PromptSucceeded(String str) {
        EventDispatcher.dispatchEvent(this, "PromptSucceeded", str);
    }

    @SimpleEvent(description = "Event will be invoked when Google reCaptcha prompt returns an error status code.")
    public void PromptErrored(int i, String str) {
        EventDispatcher.dispatchEvent(this, "PromptErrored", Integer.valueOf(i), str);
    }

    @SimpleFunction(description = "Validate the received userResponseToken with your API Secret Key, for increased security. It is recommended to use the 'Obfuscated Text' block with the API Secret Key.")
    public void Validate(final String str, final String str2) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                KodularRecaptcha.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularRecaptcha.this, str, str2);
            }
        });
    }

    @SimpleEvent(description = "Event will be triggered after the Validate method gets a response. 'success' will return true when the prompt was completed successfully, 'challenge Timestamp' is the date the challenge was completed in ISO format, 'app Package Name' will return the package name of the app where the challenge was completed, and 'error Messages' is a list of all errors that occurred during the validation.")
    public void ValidateResponse(boolean z, String str, String str2, YailList yailList) {
        EventDispatcher.dispatchEvent(this, "ValidateResponse", Boolean.valueOf(z), str, str2, yailList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0085, code lost:
        if (r10 == null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008e, code lost:
        if (r2 == null) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c4, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c6, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c7, code lost:
        r10 = new java.util.ArrayList();
        r10.add(r9.getMessage());
        r7 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r10);
        r5 = "";
        r6 = r5;
        r4 = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x00bb */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c6 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:1:0x0003] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(com.google.appinventor.components.runtime.KodularRecaptcha r8, java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r3 = "secret="
            r2.<init>(r3)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.append(r10)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r10 = "&response="
            r2.append(r10)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.append(r9)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r9 = r2.toString()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r10 = "https://www.google.com/recaptcha/api/siteverify"
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.<init>(r10)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.net.URLConnection r10 = r2.openConnection()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2 = 1
            r10.setDoOutput(r2)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r2 = "POST"
            r10.setRequestMethod(r2)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded; charset=UTF-8"
            r10.setRequestProperty(r2, r3)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.io.OutputStream r2 = r10.getOutputStream()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            byte[] r9 = r9.getBytes(r3)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.write(r9)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.flush()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.close()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.io.InputStream r9 = r10.getInputStream()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.<init>(r9, r3)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r10.<init>(r2)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.<init>()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
        L_0x005e:
            int r3 = r10.read()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r4 = -1
            if (r3 == r4) goto L_0x006a
            char r3 = (char) r3     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r2.append(r3)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            goto L_0x005e
        L_0x006a:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r10.close()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r9.close()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r9 = "success"
            boolean r9 = r3.getBoolean(r9)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r10 = "challenge_ts"
            java.lang.String r10 = r3.getString(r10)     // Catch:{ JSONException -> 0x0087, IOException -> 0x00c6 }
            if (r10 != 0) goto L_0x0088
        L_0x0087:
            r10 = r0
        L_0x0088:
            java.lang.String r2 = "apk_package_name"
            java.lang.String r2 = r3.getString(r2)     // Catch:{ JSONException -> 0x0090, IOException -> 0x00c6 }
            if (r2 != 0) goto L_0x0091
        L_0x0090:
            r2 = r0
        L_0x0091:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r4.<init>()     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            java.lang.String r5 = "error-codes"
            org.json.JSONArray r3 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            if (r3 == 0) goto L_0x00bb
            int r5 = r3.length()     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            if (r5 <= 0) goto L_0x00bb
            r5 = 0
        L_0x00a5:
            int r6 = r3.length()     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            if (r5 >= r6) goto L_0x00bb
            java.util.Map<java.lang.String, java.lang.String> r6 = r8.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            java.lang.String r7 = r3.getString(r5)     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            r4.add(r6)     // Catch:{ JSONException -> 0x00bb, IOException -> 0x00c6 }
            int r5 = r5 + 1
            goto L_0x00a5
        L_0x00bb:
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r4)     // Catch:{ IOException -> 0x00c6, JSONException -> 0x00c4 }
            r4 = r9
            r5 = r10
            r7 = r0
            r6 = r2
            goto L_0x00db
        L_0x00c4:
            r9 = move-exception
            goto L_0x00c7
        L_0x00c6:
            r9 = move-exception
        L_0x00c7:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r9 = r9.getMessage()
            r10.add(r9)
            com.google.appinventor.components.runtime.util.YailList r9 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r10)
            r7 = r9
            r5 = r0
            r6 = r5
            r4 = 0
        L_0x00db:
            com.google.appinventor.components.runtime.Form r9 = r8.form
            com.google.appinventor.components.runtime.KodularRecaptcha$5 r10 = new com.google.appinventor.components.runtime.KodularRecaptcha$5
            r2 = r10
            r3 = r8
            r2.<init>(r4, r5, r6, r7)
            r9.runOnUiThread(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularRecaptcha.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(com.google.appinventor.components.runtime.KodularRecaptcha, java.lang.String, java.lang.String):void");
    }
}
