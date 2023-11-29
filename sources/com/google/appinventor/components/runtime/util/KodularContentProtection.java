package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;

public class KodularContentProtection {
    private static final String LOG_TAG = "Content Protection";
    private String CURRENT_COUNTRY = "";
    private String[] ENGLISH = {"Action required!", "Your app has not been approved to serve ads. Please go to my.kodular.io and request approval for your app.", "Request now", "Later"};
    private String[] GERMAN = {"Handlung erforderlich!", "Ihre App wurde nicht für das Anzeigen von Werbung genehmigt. Bitte beantragen Sie die Aktivierung in ihren Kodular Account.", "Anfrage", "Später"};
    private String[] SPANISH = {"Acción requerida", "Esta app no ha sido aprobada para mostrar anuncios. Por favor, ve a My Kodular y envía tu solicitud.", "Solicitar ahora", "Luego"};
    /* access modifiers changed from: private */
    public AlertDialog alertDialog;
    private boolean canStartValidation = true;
    /* access modifiers changed from: private */
    public String failReason = "";
    /* access modifiers changed from: private */
    public boolean isAccepted = false;
    /* access modifiers changed from: private */
    public Activity kActivity;
    private String kAppId = "";
    public OnValidationResultListener kOnValidationResultListener;
    private TelephonyManager telephonyManager;

    public interface OnValidationResultListener {
        void onResult(boolean z, boolean z2, String str);
    }

    public KodularContentProtection(Activity activity) {
        this.kActivity = activity;
        TelephonyManager telephonyManager2 = (TelephonyManager) activity.getSystemService("phone");
        this.telephonyManager = telephonyManager2;
        if (telephonyManager2 == null || telephonyManager2.getNetworkCountryIso() == null) {
            this.CURRENT_COUNTRY = "default";
        } else {
            this.CURRENT_COUNTRY = this.telephonyManager.getNetworkCountryIso();
        }
    }

    public void setOnValidationResultListener(OnValidationResultListener onValidationResultListener) {
        this.kOnValidationResultListener = onValidationResultListener;
    }

    public void startContentValidation(String str) {
        this.kAppId = str;
        if (!this.canStartValidation) {
            Log.w(LOG_TAG, "Can not load a new ad until the old requested one has finished loading or was shown.");
            setInterfaceHelper(false, false, "Can not load a new ad until the old requested one has finished loading or was shown.");
        } else if (isInstalledFromGooglePlay()) {
            Log.i(LOG_TAG, "Application was installed from Play Store");
            setInterfaceHelper(true, true, "");
        } else {
            try {
                if (((Form) this.kActivity).IsCompanion()) {
                    Log.i(LOG_TAG, "Application is Companion");
                    setInterfaceHelper(true, true, "");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isConnected()) {
                Log.i(LOG_TAG, "Start now application content check.");
                new a(this, (byte) 0).execute(new String[0]);
                return;
            }
            Log.w(LOG_TAG, "There was an internal error while loading the ad. No internet connection.");
            setInterfaceHelper(false, false, "There was an internal error while loading the ad. No internet connection.");
        }
    }

    class a extends AsyncTask<String, String, Boolean> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            KodularContentProtection.this.setInterfaceHelper(((Boolean) obj).booleanValue(), KodularContentProtection.this.isAccepted, KodularContentProtection.this.failReason);
        }

        private a() {
        }

        /* synthetic */ a(KodularContentProtection kodularContentProtection, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            if (KodularContentProtection.this.isApproved()) {
                boolean unused = KodularContentProtection.this.isAccepted = true;
                return Boolean.TRUE;
            }
            boolean unused2 = KodularContentProtection.this.isAccepted = false;
            return Boolean.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public void setInterfaceHelper(boolean z, boolean z2, String str) {
        OnValidationResultListener onValidationResultListener = this.kOnValidationResultListener;
        if (onValidationResultListener != null) {
            onValidationResultListener.onResult(z, z2, str);
        }
        if (str.equals("Your app has not been approved to serve ads. Please go to my.kodular.io and request approval for your app.")) {
            showWarningDialog();
        }
        this.canStartValidation = true;
    }

    private void showWarningDialog() {
        AlertDialog create = new AlertDialog.Builder(this.kActivity, 16974393).create();
        this.alertDialog = create;
        create.setTitle(getResult(0));
        this.alertDialog.setMessage(getResult(1));
        this.alertDialog.setCancelable(false);
        this.alertDialog.setButton(-1, getResult(2), new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                try {
                    ((Form) KodularContentProtection.this.kActivity).startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://my.kodular.io/")));
                } catch (Exception e) {
                    Log.e(KodularContentProtection.LOG_TAG, String.valueOf(e));
                }
            }
        });
        this.alertDialog.setButton(-2, getResult(3), new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                KodularContentProtection.this.alertDialog.dismiss();
            }
        });
        this.alertDialog.show();
    }

    private String getResult(int i) {
        String lowerCase = this.CURRENT_COUNTRY.toLowerCase();
        lowerCase.hashCode();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case 3123:
                if (lowerCase.equals("at")) {
                    c = 0;
                    break;
                }
                break;
            case 3173:
                if (lowerCase.equals("ch")) {
                    c = 1;
                    break;
                }
                break;
            case ErrorMessages.ERROR_INDEX_MISSING_IN_LIST:
                if (lowerCase.equals("de")) {
                    c = 2;
                    break;
                }
                break;
            case 3246:
                if (lowerCase.equals("es")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return this.GERMAN[i];
            case 3:
                return this.SPANISH[i];
            default:
                return this.ENGLISH[i];
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.kActivity.getSystemService("connectivity");
        return connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean isInstalledFromGooglePlay() {
        String installerPackageName = this.kActivity.getPackageManager().getInstallerPackageName(this.kActivity.getPackageName());
        if (installerPackageName != null) {
            return installerPackageName.equals("com.google.android.feedback") || installerPackageName.equals("com.android.vending");
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isApproved() {
        /*
            r8 = this;
            java.lang.String r0 = "success"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "https://api.creator.kodular.io/ads/"
            r1.append(r2)
            java.lang.String r2 = r8.kAppId
            r1.append(r2)
            java.lang.String r2 = "/check/v1"
            r1.append(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Checking for App ID "
            r2.<init>(r3)
            java.lang.String r3 = r8.kAppId
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Content Protection"
            android.util.Log.i(r3, r2)
            r2 = 0
            r4 = 0
            java.net.URL r5 = new java.net.URL     // Catch:{ Exception -> 0x00db }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00db }
            r5.<init>(r1)     // Catch:{ Exception -> 0x00db }
            java.net.URLConnection r1 = r5.openConnection()     // Catch:{ Exception -> 0x00db }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x00db }
            java.lang.String r4 = "GET"
            r1.setRequestMethod(r4)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            int r4 = r1.getResponseCode()     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 != r5) goto L_0x00c4
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.io.InputStream r6 = r1.getInputStream()     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            r5.<init>()     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
        L_0x005c:
            java.lang.String r6 = r4.readLine()     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r6 == 0) goto L_0x0066
            r5.append(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            goto L_0x005c
        L_0x0066:
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            boolean r6 = r5.getBoolean(r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r7 = "approved"
            if (r6 == 0) goto L_0x008d
            boolean r6 = r5.getBoolean(r7)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r6 == 0) goto L_0x008d
            java.lang.String r0 = ""
            r8.failReason = r0     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = "The application is ready to serve ads."
            android.util.Log.i(r3, r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r1 == 0) goto L_0x008b
            r1.disconnect()
        L_0x008b:
            r0 = 1
            return r0
        L_0x008d:
            boolean r0 = r5.getBoolean(r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r0 == 0) goto L_0x00a8
            boolean r0 = r5.getBoolean(r7)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r0 != 0) goto L_0x00a8
            java.lang.String r0 = "Your app has not been approved to serve ads. Please go to my.kodular.io and request approval for your app."
            r8.failReason = r0     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = "The application is not approved to serve ads."
            android.util.Log.i(r3, r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r1 == 0) goto L_0x00a7
            r1.disconnect()
        L_0x00a7:
            return r2
        L_0x00a8:
            java.lang.String r0 = "An unexpected response from Admob Validation System was received. Please try again."
            r8.failReason = r0     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = "Internal error! The server response is NULL. Please try again."
            android.util.Log.w(r3, r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = "Response from server: "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = r0.concat(r4)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            android.util.Log.i(r3, r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r1 == 0) goto L_0x00c3
            r1.disconnect()
        L_0x00c3:
            return r2
        L_0x00c4:
            java.lang.String r0 = "No response got from validation System. Please try again."
            r8.failReason = r0     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            java.lang.String r0 = "Internal error! No response was received from server."
            android.util.Log.w(r3, r0)     // Catch:{ Exception -> 0x00d6, all -> 0x00d3 }
            if (r1 == 0) goto L_0x00d2
            r1.disconnect()
        L_0x00d2:
            return r2
        L_0x00d3:
            r0 = move-exception
            r4 = r1
            goto L_0x00e9
        L_0x00d6:
            r0 = move-exception
            r4 = r1
            goto L_0x00dc
        L_0x00d9:
            r0 = move-exception
            goto L_0x00e9
        L_0x00db:
            r0 = move-exception
        L_0x00dc:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00d9 }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x00d9 }
            if (r4 == 0) goto L_0x00e8
            r4.disconnect()
        L_0x00e8:
            return r2
        L_0x00e9:
            if (r4 == 0) goto L_0x00ee
            r4.disconnect()
        L_0x00ee:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.KodularContentProtection.isApproved():boolean");
    }
}
