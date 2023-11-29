package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceUtil {
    private static HttpClient B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
    private static final WebServiceUtil hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new WebServiceUtil();
    private static Object oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = new Object();

    private WebServiceUtil() {
    }

    public static WebServiceUtil getInstance() {
        synchronized (oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS) {
            if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
                HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
                ConnManagerParams.setMaxTotalConnections(basicHttpParams, 20);
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            }
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void postCommandReturningArray(String str, String str2, List<NameValuePair> list, final AsyncCallbackPair<JSONArray> asyncCallbackPair) {
        postCommand(str, str2, list, new AsyncCallbackPair<String>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                try {
                    asyncCallbackPair.onSuccess(new JSONArray((String) obj));
                } catch (JSONException e) {
                    AsyncCallbackPair asyncCallbackPair = asyncCallbackPair;
                    asyncCallbackPair.onFailure(e.getMessage());
                }
            }

            public final void onFailure(String str) {
                asyncCallbackPair.onFailure(str);
            }
        });
    }

    public void postCommandReturningObject(String str, String str2, List<NameValuePair> list, final AsyncCallbackPair<JSONObject> asyncCallbackPair) {
        postCommand(str, str2, list, new AsyncCallbackPair<String>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                try {
                    asyncCallbackPair.onSuccess(new JSONObject((String) obj));
                } catch (JSONException e) {
                    AsyncCallbackPair asyncCallbackPair = asyncCallbackPair;
                    asyncCallbackPair.onFailure(e.getMessage());
                }
            }

            public final void onFailure(String str) {
                asyncCallbackPair.onFailure(str);
            }
        });
    }

    public void postCommand(String str, String str2, List<NameValuePair> list, AsyncCallbackPair<String> asyncCallbackPair) {
        Log.d("WebServiceUtil", "Posting " + str2 + " to " + str + " with arguments " + list);
        if (str == null || str.equals("")) {
            asyncCallbackPair.onFailure("No service url to post command to.");
        }
        HttpPost httpPost = new HttpPost(str + InternalZipConstants.ZIP_FILE_SEPARATOR + str2);
        if (list == null) {
            list = new ArrayList<>();
        }
        try {
            BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            httpPost.setHeader("Accept", "application/json");
            asyncCallbackPair.onSuccess((String) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.execute(httpPost, basicResponseHandler));
        } catch (UnsupportedEncodingException e) {
            Log.w("WebServiceUtil", e);
            asyncCallbackPair.onFailure("Failed to encode params for web service call.");
        } catch (ClientProtocolException e2) {
            Log.w("WebServiceUtil", e2);
            asyncCallbackPair.onFailure("Communication with the web service encountered a protocol exception.");
        } catch (IOException e3) {
            Log.w("WebServiceUtil", e3);
            asyncCallbackPair.onFailure("Communication with the web service timed out.");
        }
    }
}
