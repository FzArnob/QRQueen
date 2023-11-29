package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.WebViewerUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@DesignerComponent(category = ComponentCategory.VIEWS, description = "Component for viewing Web pages. The Home URL can be specified in the Designer or in the Blocks Editor. The view can be set to follow links when they are tapped, and users can fill in Web forms. Warning: This is not a full browser. For example, pressing the phone's hardware Back key will exit the app, rather than move back in the browser history.<p />You can use the WebViewer.WebViewString property to communicate between your app and Javascript code running in the Webviewer page. In the app, you get and set WebViewString.  In the WebViewer, you include Javascript that references the window.Kodular object, using the methoods <em>getWebViewString()</em> and <em>setWebViewString(text)</em>. <p />For example, if the WebViewer opens to a page that contains the Javascript command <br /><em>document.write(\"The answer is\" + window.Kodular.getWebViewString());</em> <br />and if you set WebView.WebVewString to \"hello\", then the web page will show <br /><em>The answer is hello</em>. <br />And if the Web page contains Javascript that executes the command <br /><em>window.Kodular.setWebViewString(\"hello from Javascript\")</em>, <br />then the value of the WebViewString property will be <br /><em>hello from Javascript</em>.", version = 13)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public final class WebViewer extends AndroidViewComponent implements DownloadListener, ActivityResultListener, OnPauseListener, OnResumeListener {
    private static final String LOG_TAG = "WebViewer";
    /* access modifiers changed from: private */
    public static int REQUEST_CODE_FILE_PICKER;
    /* access modifiers changed from: private */
    public Activity activity;
    private final Handler androidUIHandler = new Handler();
    private Context context;
    private CookieManager cookieMgr = CookieManager.getInstance();
    private boolean desktopMode = false;
    private boolean followLinks = true;
    /* access modifiers changed from: private */
    public Form form;
    /* access modifiers changed from: private */
    public boolean haveLocationPermission = false;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private String homeUrl;
    /* access modifiers changed from: private */
    public boolean ignoreSslErrors = false;
    private boolean jsEnabled = true;
    private boolean loadImages = true;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> mFilePathCallback;
    private boolean prompt = true;
    private boolean scrollbar = true;
    private boolean useExternalBrowser = false;
    /* access modifiers changed from: private */
    public final WebView webview;
    WebViewInterface wvInterface;
    private boolean zoomControl = true;
    private boolean zoomEnabled = true;
    private int zoomPercent = 100;

    public WebViewer(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnPause(this);
        this.form.registerForOnResume(this);
        REQUEST_CODE_FILE_PICKER = this.form.registerForActivityResult(this);
        WebView webView = new WebView(this.context);
        this.webview = webView;
        resetWebViewClient();
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        settings.setDisplayZoomControls(this.zoomControl);
        settings.setBuiltInZoomControls(this.zoomEnabled);
        settings.setTextZoom(this.zoomPercent);
        settings.setLoadsImagesAutomatically(this.loadImages);
        settings.setBlockNetworkImage(!this.loadImages);
        webView.setFocusable(true);
        WebViewInterface webViewInterface = new WebViewInterface(webView.getContext());
        this.wvInterface = webViewInterface;
        webView.addJavascriptInterface(webViewInterface, "AppInventor");
        webView.addJavascriptInterface(this.wvInterface, "Makeroid");
        webView.addJavascriptInterface(this.wvInterface, "Kodular");
        webView.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if ((action != 0 && action != 1) || view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            }
        });
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setDownloadListener(this);
        componentContainer.$add(this);
        EnableJavaScript(this.jsEnabled);
        DesktopMode(this.desktopMode);
        HomeUrl("");
        Width(-2);
        Height(-2);
    }

    public final View getView() {
        return this.webview;
    }

    public final void onPause() {
        WebView webView = this.webview;
        if (webView != null) {
            webView.onPause();
        }
    }

    public final void onResume() {
        WebView webView = this.webview;
        if (webView != null) {
            webView.onResume();
        }
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        OnDownloadStart(str, str3, str4, j);
    }

    @SimpleEvent(description = "Event for listening download links.")
    public final void OnDownloadStart(String str, String str2, String str3, long j) {
        EventDispatcher.dispatchEvent(this, "OnDownloadStart", str, str2, str3, Long.valueOf(j));
    }

    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebViewer.this.PageLoaded(str);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (str.startsWith("http://localhost/") || str.startsWith(Form.ASSETS_PREFIX)) {
                return WebViewer.this.handleAppRequest(str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            Log.d(WebViewer.LOG_TAG, "scheme = " + webResourceRequest.getUrl().getScheme());
            if (webResourceRequest.getUrl().getAuthority().equals("localhost") || webResourceRequest.getUrl().toString().startsWith(Form.ASSETS_PREFIX)) {
                return WebViewer.this.handleAppRequest(webResourceRequest.getUrl().toString());
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return WebViewer.this.shouldOverrideUrlLoading(str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return WebViewer.this.shouldOverrideUrlLoading(webResourceRequest.getUrl().toString());
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (WebViewer.this.ignoreSslErrors) {
                sslErrorHandler.proceed();
                return;
            }
            sslErrorHandler.cancel();
            WebViewer.this.form.dispatchErrorOccurredEvent(WebViewer.this, "WebView", ErrorMessages.ERROR_WEBVIEW_SSL_ERROR, new Object[0]);
        }

        public void onReceivedError(WebView webView, final int i, final String str, final String str2) {
            WebViewer.this.form.runOnUiThread(new Runnable() {
                public final void run() {
                    EventDispatcher.dispatchEvent(WebViewer.this, "ErrorOccurred", Integer.valueOf(i), str, str2);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final WebResourceResponse handleAppRequest(String str) {
        String str2;
        String str3 = str;
        if (str3.startsWith(Form.ASSETS_PREFIX)) {
            str2 = str3.substring(22);
        } else {
            str2 = str3.substring(str3.indexOf("//localhost/") + 12);
        }
        try {
            Log.i(LOG_TAG, "webviewer requested path = ".concat(String.valueOf(str2)));
            try {
                InputStream openAsset = this.form.openAsset(str2);
                HashMap hashMap = new HashMap();
                hashMap.put("Access-Control-Allow-Origin", "localhost");
                String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str2);
                String str4 = "utf-8";
                Log.i(LOG_TAG, "Mime type = ".concat(String.valueOf(contentTypeFor)));
                if (!contentTypeFor.startsWith("text/")) {
                    str4 = null;
                }
                return new WebResourceResponse(contentTypeFor, str4, 200, "OK", hashMap, openAsset);
            } catch (IOException unused) {
                return new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", (Map) null, new ByteArrayInputStream(NanoHTTPD.HTTP_NOTFOUND.getBytes()));
            }
        } catch (IOException unused2) {
            return new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", (Map) null, new ByteArrayInputStream(NanoHTTPD.HTTP_NOTFOUND.getBytes()));
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldOverrideUrlLoading(String str) {
        if ((this.useExternalBrowser && (str.startsWith("http:") || str.startsWith("https:"))) || str.startsWith("tel:") || str.startsWith("mailto:") || str.startsWith("file:")) {
            try {
                this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        if (!this.followLinks) {
            return true;
        }
        return false;
    }

    public class MyWebChromeClient extends WebChromeClient {
        public MyWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            WebViewer.this.ProgressChanged(i);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            WebViewer.this.OnConsoleMessage(consoleMessage.message(), consoleMessage.lineNumber(), consoleMessage.sourceId());
            return true;
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (WebViewer.this.mFilePathCallback != null) {
                WebViewer.this.mFilePathCallback.onReceiveValue((Object) null);
                ValueCallback unused = WebViewer.this.mFilePathCallback = null;
            }
            ValueCallback unused2 = WebViewer.this.mFilePathCallback = valueCallback;
            Intent createIntent = fileChooserParams.createIntent();
            createIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            createIntent.setType("*/*");
            try {
                WebViewer.this.activity.startActivityForResult(Intent.createChooser(createIntent, "Choose file"), WebViewer.REQUEST_CODE_FILE_PICKER);
            } catch (Exception e) {
                Log.e(WebViewer.LOG_TAG, "No activity found to handle file chooser intent.", e);
                valueCallback.onReceiveValue((Object) null);
            }
            return true;
        }
    }

    @SimpleEvent(description = "Event to detect that the loading progress has changed.")
    public final void ProgressChanged(int i) {
        EventDispatcher.dispatchEvent(this, "ProgressChanged", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Get webpage console output")
    public final void OnConsoleMessage(String str, int i, String str2) {
        EventDispatcher.dispatchEvent(this, "OnConsoleMessage", str, Integer.valueOf(i), str2);
    }

    public final void resultReturned(int i, int i2, Intent intent) {
        if (i != REQUEST_CODE_FILE_PICKER) {
            return;
        }
        if (i2 != -1) {
            this.mFilePathCallback.onReceiveValue((Object) null);
            this.mFilePathCallback = null;
            return;
        }
        Uri[] parseResult = WebChromeClient.FileChooserParams.parseResult(i2, intent);
        if (parseResult == null) {
            ClipData clipData = intent.getClipData();
            int itemCount = clipData.getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i3 = 0; i3 < itemCount; i3++) {
                uriArr[i3] = clipData.getItemAt(i3).getUri();
            }
            parseResult = uriArr;
        }
        this.mFilePathCallback.onReceiveValue(parseResult);
        this.mFilePathCallback = null;
    }

    @SimpleProperty
    public final void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleProperty
    public final void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    @SimpleProperty(description = "URL of the page the WebViewer should initially open to. Setting this will load the page.")
    public final String HomeUrl() {
        return this.homeUrl;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public final void HomeUrl(String str) {
        this.homeUrl = str;
        this.webview.clearHistory();
        loadUrl("HomeUrl", this.homeUrl);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URL of the page currently viewed. This could be different from the Home URL if new pages were visited by following links.")
    public final String CurrentUrl() {
        return this.webview.getUrl() == null ? "" : this.webview.getUrl();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Title of the page currently viewed")
    public final String CurrentPageTitle() {
        return this.webview.getTitle() == null ? "" : this.webview.getTitle();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether to follow links when they are tapped in the WebViewer.  If you follow links, you can use GoBack and GoForward to navigate the browser history. ")
    public final boolean FollowLinks() {
        return this.followLinks;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void FollowLinks(boolean z) {
        this.followLinks = z;
        resetWebViewClient();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determine whether or not to ignore SSL errors. Set to true to ignore errors. Use this to accept self signed certificates from websites.")
    public final boolean IgnoreSslErrors() {
        return this.ignoreSslErrors;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void IgnoreSslErrors(boolean z) {
        this.ignoreSslErrors = z;
        resetWebViewClient();
    }

    @SimpleFunction(description = "Loads the home URL page. This happens automatically when the home URL is changed.")
    public final void GoHome() {
        loadUrl("GoHome", this.homeUrl);
    }

    @SimpleFunction(description = "Go back to the previous page in the history list. Does nothing if there is no previous page.")
    public final void GoBack() {
        if (this.webview.canGoBack()) {
            this.webview.goBack();
        }
    }

    @SimpleFunction(description = "Go forward to the next page in the history list. Does nothing if there is no next page.")
    public final void GoForward() {
        if (this.webview.canGoForward()) {
            this.webview.goForward();
        }
    }

    @SimpleFunction(description = "Go forward or backward a number of steps away from the current page. Steps is negative if backward and positive if forward.")
    public final void GoBackOrForward(int i) {
        this.webview.goBackOrForward(i);
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go forward in the history list.")
    public final boolean CanGoForward() {
        return this.webview.canGoForward();
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go back in the history list.")
    public final boolean CanGoBack() {
        return this.webview.canGoBack();
    }

    @SimpleFunction(description = "Returns true if the WebViewer can go back or forward the number of steps in the history list.")
    public final boolean CanGoBackOrForward(int i) {
        return this.webview.canGoBackOrForward(i);
    }

    @SimpleFunction(description = "Stops the current load.")
    public final void StopLoading() {
        this.webview.stopLoading();
    }

    @SimpleFunction(description = "Reloads the current page")
    public final void Reload() {
        this.webview.reload();
    }

    @SimpleFunction(description = "Load the page at the given URL.")
    public final void GoToUrl(String str) {
        if (str.startsWith("file:///mnt/sdcard/AppInventor/")) {
            str = str.replaceFirst("AppInventor", "Kodular");
        }
        loadUrl("GoToUrl", str);
    }

    private void loadUrl(final String str, final String str2) {
        if (str2.startsWith("file:///mnt/sdcard/AppInventor/")) {
            str2 = str2.replaceFirst("AppInventor", "Kodular");
        }
        if (this.havePermission || !MediaUtil.isExternalFileUrl(this.form, str2)) {
            this.webview.loadUrl(str2);
        } else {
            this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        boolean unused = WebViewer.this.havePermission = true;
                        WebViewer.this.webview.loadUrl(str2);
                        return;
                    }
                    WebViewer.this.form.dispatchPermissionDeniedEvent((Component) WebViewer.this, str, "android.permission.READ_EXTERNAL_STORAGE");
                }
            });
        }
    }

    @SimpleFunction(description = "Load HTML content using Base64-encoded data URI scheme")
    public final void LoadHtml(String str) {
        this.webview.loadData(Base64.encodeToString(str.getBytes(), 1), NanoHTTPD.MIME_HTML, "base64");
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Whether or not to give the application permission to use the Javascript geolocation API. This property is available only in the designer.", userVisible = false)
    public final void UsesLocation(boolean z) {
        if (z && !this.haveLocationPermission) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    WebViewer.this.form.askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = WebViewer.this.haveLocationPermission = true;
                                WebViewer.setupWebViewGeoLoc(WebViewer.this, WebViewer.this.webview, WebViewer.this.activity);
                                Log.d(WebViewer.LOG_TAG, "Permission Granted");
                                return;
                            }
                            boolean unused2 = WebViewer.this.haveLocationPermission = false;
                            WebViewer.this.form.dispatchPermissionDeniedEvent((Component) WebViewer.this, "UsesLocation", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        } else if (z) {
            setupWebViewGeoLoc(this, this.webview, this.activity);
        }
    }

    @SimpleProperty(description = "If True, then prompt the user of the WebView to give permission to access the geolocation API. If False, then assume permission is granted.")
    public final boolean PromptforPermission() {
        return this.prompt;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = true)
    public final void PromptforPermission(boolean z) {
        this.prompt = z;
    }

    @SimpleFunction(description = "Clear stored location permissions.")
    public final void ClearLocations() {
        GeolocationPermissions.getInstance().clearAll();
    }

    @SimpleFunction(description = "Clear WebView caches.")
    public final void ClearCaches() {
        this.webview.clearCache(true);
    }

    @SimpleFunction(description = "Start to clear the WebView cookies.")
    public final void ClearCookies() {
        Log.d(LOG_TAG, "Api is bigger than 21");
        this.cookieMgr.removeAllCookies(new ValueCallback<Boolean>() {
            public final /* synthetic */ void onReceiveValue(Object obj) {
                WebViewer.this.CookiesRemoved(((Boolean) obj).booleanValue());
            }
        });
        this.cookieMgr.flush();
    }

    @SimpleEvent(description = "This event return true when the cookies have been successfully removed. If the cookies was successfully cleared then the next run returns false as status, if in this time no new cookies was set.")
    public final void CookiesRemoved(boolean z) {
        EventDispatcher.dispatchEvent(this, "CookiesRemoved", Boolean.valueOf(z));
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar")
    public final void Scrollbar(boolean z) {
        this.webview.setVerticalScrollBarEnabled(z);
        this.webview.setHorizontalScrollBarEnabled(z);
        this.scrollbar = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final boolean Scrollbar() {
        return this.scrollbar;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Show or Hide the zoom display.")
    public final boolean ZoomDisplay() {
        return this.zoomControl;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void ZoomDisplay(boolean z) {
        this.zoomControl = z;
        this.webview.getSettings().setDisplayZoomControls(this.zoomControl);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable or Disable pinch zooming. This effects both pinch zooming and the zoom controls.")
    public final boolean ZoomEnabled() {
        return this.zoomEnabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void ZoomEnabled(boolean z) {
        this.zoomEnabled = z;
        this.webview.getSettings().setBuiltInZoomControls(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The zoom of the page in percent %")
    public final int ZoomPercent() {
        return this.zoomPercent;
    }

    @DesignerProperty(defaultValue = "100", editorType = "integer")
    public final void ZoomPercent(int i) {
        this.zoomPercent = i;
        this.webview.getSettings().setTextZoom(i);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to automatically load images")
    public final boolean LoadImages() {
        return this.loadImages;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    public final void LoadImages(boolean z) {
        this.loadImages = z;
        this.webview.getSettings().setLoadsImagesAutomatically(z);
        this.webview.getSettings().setBlockNetworkImage(!z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Open a link in the webviewer page using the external browser. If true the page will be loaded in the external browser and not in the webviewer itself.")
    public final boolean UseExternalBrowser() {
        return this.useExternalBrowser;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Open a link in the webviewer page using the external browser. If true the page will be loaded in the external browser and not in the webviewer itself.")
    public final void UseExternalBrowser(boolean z) {
        this.useExternalBrowser = z;
        resetWebViewClient();
    }

    @SimpleEvent(description = "Triggers when page finished loading")
    public final void PageLoaded(String str) {
        EventDispatcher.dispatchEvent(this, "PageLoaded", str);
    }

    @DesignerProperty(defaultValue = "", editorType = "string", propertyType = "advanced")
    @SimpleProperty
    public final void UserAgent(String str) {
        this.webview.getSettings().setUserAgentString(str);
    }

    @SimpleProperty(description = "Get User Agent")
    public final String UserAgent() {
        return this.webview.getSettings().getUserAgentString();
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public final void DesktopMode(boolean z) {
        this.desktopMode = z;
        WebSettings settings = this.webview.getSettings();
        if (z) {
            settings.setUserAgentString(settings.getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA"));
        } else {
            settings.setUserAgentString((String) null);
        }
    }

    @SimpleProperty(description = "Get/Set Desktop mode by altering the user agent string.")
    public final boolean DesktopMode() {
        return this.desktopMode;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Enable/Disable JavaScript. Enabled by default")
    public final void EnableJavaScript(boolean z) {
        this.jsEnabled = z;
        this.webview.getSettings().setJavaScriptEnabled(z);
    }

    @SimpleProperty(description = "Evaluate Javascript in current page context")
    public final boolean EnableJavaScript() {
        return this.jsEnabled;
    }

    @SimpleFunction(description = "Evaluate JS in the context of the current page")
    public final void EvaluateJS(String str) {
        this.webview.evaluateJavascript(str, new ValueCallback<String>() {
            public final /* synthetic */ void onReceiveValue(Object obj) {
                String str = (String) obj;
                if (str != null) {
                    WebViewer.this.AfterJSEvaluated(str);
                } else {
                    WebViewer.this.AfterJSEvaluated("null");
                }
            }
        });
    }

    @SimpleEvent(description = "Get the result of the evaluated JS")
    public final void AfterJSEvaluated(String str) {
        EventDispatcher.dispatchEvent(this, "AfterJSEvaluated", str);
    }

    private void resetWebViewClient() {
        this.webview.setWebViewClient(new MyWebViewClient());
    }

    public static void setupWebViewGeoLoc(WebViewer webViewer, WebView webView, final Activity activity2) {
        webView.getSettings().setGeolocationDatabasePath(activity2.getFilesDir().getAbsolutePath());
        webView.getSettings().setDatabaseEnabled(true);
        WebViewerUtil.initialize(activity2);
        webView.setWebChromeClient(new WebChromeClient(webViewer) {

            /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
            private /* synthetic */ WebViewer f279B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.f279B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r1;
            }

            public final void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
                if (this.f279B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.PromptforPermission()) {
                    AlertDialog create = new AlertDialog.Builder(activity2).create();
                    create.setTitle(WebViewerUtil.getPermissionTitleString());
                    String permissionApplicationString = str.equals("file://") ? WebViewerUtil.getPermissionApplicationString() : str;
                    create.setMessage(permissionApplicationString + WebViewerUtil.getPermissionMessageString());
                    create.setButton(-1, WebViewerUtil.getPermissionAllowString(), new DialogInterface.OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            callback.invoke(str, true, true);
                        }
                    });
                    create.setButton(-2, WebViewerUtil.getPermissionDeniedString(), new DialogInterface.OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            callback.invoke(str, false, true);
                        }
                    });
                    create.show();
                    return;
                }
                callback.invoke(str, true, true);
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the WebView's String, which is viewable through Javascript in the WebView as the window.AppInventor object")
    public final String WebViewString() {
        return this.wvInterface.getWebViewString();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final void WebViewString(String str) {
        this.wvInterface.setWebViewStringFromBlocks(str);
    }

    @SimpleEvent(description = "When the JavaScript calls AppInventor.setWebViewString this event is run.")
    public final void WebViewStringChange(String str) {
        EventDispatcher.dispatchEvent(this, "WebViewStringChange", str);
    }

    public class WebViewInterface {
        private String ATUMLOoHHffbHqXfKo1DqLB6OFxsAaTmX7VyjvAowYdVLTiF9JXThRDL9odeah0z = "";
        private Context hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        WebViewInterface(Context context) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = context;
        }

        @JavascriptInterface
        public String getWebViewString() {
            return this.ATUMLOoHHffbHqXfKo1DqLB6OFxsAaTmX7VyjvAowYdVLTiF9JXThRDL9odeah0z;
        }

        @JavascriptInterface
        public void setWebViewString(final String str) {
            this.ATUMLOoHHffbHqXfKo1DqLB6OFxsAaTmX7VyjvAowYdVLTiF9JXThRDL9odeah0z = str;
            WebViewer.this.form.runOnUiThread(new Runnable() {
                public final void run() {
                    WebViewer.this.WebViewStringChange(str);
                }
            });
        }

        public void setWebViewStringFromBlocks(String str) {
            this.ATUMLOoHHffbHqXfKo1DqLB6OFxsAaTmX7VyjvAowYdVLTiF9JXThRDL9odeah0z = str;
        }
    }
}
