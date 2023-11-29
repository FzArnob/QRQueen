package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import java.io.IOException;

public class OAuth2Helper {
    private static String OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = "Error during OAuth";
    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String PREF_AUTH_TOKEN = "authToken";
    public static final String TAG = "OAuthHelper";

    public String getRefreshedAuthToken(Activity activity, String str) {
        AccountManagerFuture<Bundle> accountManagerFuture;
        Activity activity2 = activity;
        Log.i(TAG, "getRefreshedAuthToken()");
        if (!Looper.getMainLooper().getThread().equals(Thread.currentThread())) {
            SharedPreferences preferences = activity2.getPreferences(0);
            String string = preferences.getString(PREF_ACCOUNT_NAME, (String) null);
            String string2 = preferences.getString(PREF_AUTH_TOKEN, (String) null);
            GoogleCredential googleCredential = new GoogleCredential();
            googleCredential.setAccessToken(string2);
            GoogleAccountManager googleAccountManager = new GoogleAccountManager(activity2);
            googleAccountManager.invalidateAuthToken(googleCredential.getAccessToken());
            AccountManager.get(activity).invalidateAuthToken(str, (String) null);
            Account accountByName = googleAccountManager.getAccountByName(string);
            if (accountByName != null) {
                Log.i(TAG, "Getting token by account");
                accountManagerFuture = googleAccountManager.getAccountManager().getAuthToken(accountByName, str, true, (AccountManagerCallback) null, (Handler) null);
            } else {
                Log.i(TAG, "Getting token by features, possibly prompting user to select an account");
                accountManagerFuture = googleAccountManager.getAccountManager().getAuthTokenByFeatures("com.google", str, (String[]) null, activity, (Bundle) null, (Bundle) null, (AccountManagerCallback) null, (Handler) null);
            }
            try {
                Bundle result = accountManagerFuture.getResult();
                string2 = result.get("authtoken").toString();
                String string3 = result.getString("authAccount");
                Log.i(TAG, "Persisting credentials, acct =".concat(String.valueOf(string3)));
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString(PREF_ACCOUNT_NAME, string3);
                edit.putString(PREF_AUTH_TOKEN, string2);
                edit.commit();
                return string2;
            } catch (OperationCanceledException e) {
                e.printStackTrace();
                resetAccountCredential(activity);
                OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = "Error: operation cancelled";
                return string2;
            } catch (AuthenticatorException e2) {
                e2.printStackTrace();
                OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = "Error: Authenticator error";
                return string2;
            } catch (IOException e3) {
                e3.printStackTrace();
                OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = "Error: I/O error";
                return string2;
            }
        } else {
            throw new IllegalArgumentException("Can't get authtoken from UI thread");
        }
    }

    public static void resetAccountCredential(Activity activity) {
        Log.i(TAG, "Reset credentials");
        SharedPreferences.Editor edit = activity.getPreferences(0).edit();
        edit.remove(PREF_AUTH_TOKEN);
        edit.remove(PREF_ACCOUNT_NAME);
        edit.commit();
    }

    public static String getErrorMessage() {
        Log.i(TAG, "getErrorMessage = " + OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
        return OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    }
}
