package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.google.appinventor.components.runtime.Form;
import java.util.concurrent.atomic.AtomicBoolean;

public class KodularAdManager {
    private final SharedPreferences B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private final Context context;

    public KodularAdManager(Context context2, Form form) {
        this.context = context2;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(getKodularPackageName(), 0);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = sharedPreferences;
        if (!sharedPreferences.contains(getKodularPackageName() + ".kadm")) {
            new a((byte) 0).execute(new Form[]{form});
        }
    }

    public String getAdUnit() {
        SharedPreferences sharedPreferences = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        String string = sharedPreferences.getString(getKodularPackageName() + ".kadm", (String) null);
        Log.i("KodularAdManager", "KAdM Ad Unit: ".concat(String.valueOf(string)));
        return string;
    }

    private String getKodularPackageName() {
        String packageName = this.context.getPackageName();
        try {
            PackageManager packageManager = this.context.getPackageManager();
            return packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll(".Screen1", "");
        } catch (Exception unused) {
            return packageName;
        }
    }

    static class a extends AsyncTask<Form, Void, String> {
        private String LOG_TAG;
        private Form hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

        private a() {
            this.LOG_TAG = "KodularAdManagerTask";
        }

        /* synthetic */ a(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            SharedPreferences sharedPreferences = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.$context().getSharedPreferences(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName(), 0);
            if (str != null && sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName() + ".kadm", str).apply();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(Form... formArr) {
            try {
                Form form = formArr[0];
                this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = form;
                Bundle bundle = form.$context().getPackageManager().getApplicationInfo(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getPackageName(), 128).metaData;
                if (bundle == null) {
                    Log.e(this.LOG_TAG, "No bundle!");
                    return null;
                }
                String string = bundle.getString("KADM_CN_ADUNIT");
                if (string != null) {
                    return string;
                }
                Log.e(this.LOG_TAG, "No AD_UNIT!");
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(this.LOG_TAG, "Could not find PackageManager");
                return null;
            }
        }
    }

    public static class Consent {
        /* access modifiers changed from: private */
        public static ConsentInformation hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private static final AtomicBoolean f296hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AtomicBoolean(false);
        private static boolean vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj;

        public static void request(final Activity activity, Context context, boolean z) {
            ConsentRequestParameters.Builder tagForUnderAgeOfConsent = new ConsentRequestParameters.Builder().setTagForUnderAgeOfConsent(false);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = UserMessagingPlatform.getConsentInformation(activity);
            if (z) {
                tagForUnderAgeOfConsent.setConsentDebugSettings(new ConsentDebugSettings.Builder(activity).setDebugGeography(1).addTestDeviceHashedId(KodularAdsUtil.guessSelfDeviceId(context)).build());
                if (!vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj) {
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.reset();
                    vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = true;
                }
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestConsentInfoUpdate(activity, tagForUnderAgeOfConsent.build(), new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                public final void onConsentInfoUpdateSuccess() {
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity, new ConsentForm.OnConsentFormDismissedListener(activity) {
                        public final void onConsentFormDismissed(FormError formError) {
                            if (formError != null) {
                                Log.w("KodularAdManager", String.format("%s: %s", new Object[]{Integer.valueOf(formError.getErrorCode()), formError.getMessage()}));
                            }
                            if (Consent.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.canRequestAds()) {
                                Consent.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r1);
                            }
                        }
                    });
                }
            }, new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                public final void onConsentInfoUpdateFailure(FormError formError) {
                    Log.w("KodularAdManager", String.format("%s: %s", new Object[]{Integer.valueOf(formError.getErrorCode()), formError.getMessage()}));
                }
            });
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.canRequestAds()) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(activity);
            }
        }

        public static void destroy() {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            vvjaKRMN9H6lYkjQrf39wC72L2per26vSgUw9e5mZIMwW4gwULkxbiBNnCPVkUrj = false;
            f296hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.set(false);
        }

        /* access modifiers changed from: private */
        public static void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Context context) {
            if (!f296hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAndSet(true)) {
                MobileAds.initialize(context, new OnInitializationCompleteListener() {
                    public final void onInitializationComplete(InitializationStatus initializationStatus) {
                        Log.i("KodularAdManager", "MobileAds initialized.");
                    }
                });
            }
        }
    }
}
