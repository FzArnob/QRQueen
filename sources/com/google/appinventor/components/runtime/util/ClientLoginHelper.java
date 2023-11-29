package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientLoginHelper implements IClientLoginHelper {
    private AccountChooser B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private Activity activity;
    private String authToken;
    private AccountManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private HttpClient f284hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean initialized = false;
    private String xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG;

    public ClientLoginHelper(Activity activity2, String str, String str2, DefaultHttpClient defaultHttpClient) {
        this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG = str;
        this.f284hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = defaultHttpClient == null ? new DefaultHttpClient() : defaultHttpClient;
        this.activity = activity2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = AccountManager.get(activity2);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new AccountChooser(activity2, str, str2, str);
    }

    public void forgetAccountName() {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.forgetAccountName();
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HttpUriRequest httpUriRequest, String str) {
        if (str != null) {
            Log.i("ClientLoginHelper", "adding auth token token: ".concat(String.valueOf(str)));
            httpUriRequest.addHeader(Constants.AUTHORIZATION_HEADER, "GoogleLogin auth=".concat(String.valueOf(str)));
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HttpUriRequest httpUriRequest) {
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (header.getName().equalsIgnoreCase(Constants.AUTHORIZATION_HEADER) && header.getValue().startsWith("GoogleLogin auth=")) {
                Log.i("ClientLoginHelper", "Removing header:".concat(String.valueOf(header)));
                httpUriRequest.removeHeader(header);
            }
        }
    }

    public String getAuthToken() throws ClientProtocolException {
        Account findAccount = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.findAccount();
        if (findAccount != null) {
            AccountManagerFuture<Bundle> authToken2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAuthToken(findAccount, this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG, (Bundle) null, this.activity, (AccountManagerCallback) null, (Handler) null);
            Log.i("ClientLoginHelper", "Have account, auth token: ".concat(String.valueOf(authToken2)));
            try {
                return authToken2.getResult().getString("authtoken");
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (OperationCanceledException e3) {
                e3.printStackTrace();
            }
        }
        throw new ClientProtocolException("Can't get valid authentication token");
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {
        if (!this.initialized) {
            Log.i("ClientLoginHelper", "initializing");
            if (!Looper.getMainLooper().getThread().equals(Thread.currentThread())) {
                this.authToken = getAuthToken();
                this.initialized = true;
            } else {
                throw new IllegalArgumentException("Can't initialize login helper from UI thread");
            }
        }
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(httpUriRequest, this.authToken);
        HttpResponse execute = this.f284hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.execute(httpUriRequest);
        if (execute.getStatusLine().getStatusCode() != 401) {
            return execute;
        }
        Log.i("ClientLoginHelper", "Invalid token: " + this.authToken);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidateAuthToken("com.google", this.authToken);
        this.authToken = getAuthToken();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(httpUriRequest);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(httpUriRequest, this.authToken);
        Log.i("ClientLoginHelper", "new token: " + this.authToken);
        return this.f284hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.execute(httpUriRequest);
    }
}
