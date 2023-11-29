package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity extends Activity {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 24) {
            webView.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    return WebViewActivity.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(WebViewActivity.this, webView, webResourceRequest.getUrl().toString());
                }
            });
        } else {
            webView.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    return WebViewActivity.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(WebViewActivity.this, webView, str);
                }
            });
        }
        setContentView(webView);
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            Uri data = intent.getData();
            String scheme = data.getScheme();
            String host = data.getHost();
            Log.i("WebView", "Got intent with URI: " + data + ", scheme=" + scheme + ", host=" + host);
            webView.loadUrl(data.toString());
        }
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(WebViewActivity webViewActivity, WebView webView, String str) {
        Log.i("WebView", "Handling url ".concat(String.valueOf(str)));
        Uri parse = Uri.parse(str);
        if (parse.getScheme().equals(Form.APPINVENTOR_URL_SCHEME)) {
            Intent intent = new Intent();
            intent.setData(parse);
            webViewActivity.setResult(-1, intent);
            webViewActivity.finish();
            return true;
        }
        webView.loadUrl(str);
        return true;
    }
}
