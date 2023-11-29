package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.repackaged.org.json.XML;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.jwx.HeaderParameterNames;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Non-visible component that provides functions for HTTP GET, POST, PUT, and DELETE requests.", iconName = "images/web.png", nonVisible = true, version = 6)
@UsesLibraries({"json.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class Web extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Web";
    private static final Map<String, String> mimeTypeToExtension;
    private final Activity activity;
    /* access modifiers changed from: private */
    public boolean allowCookies;
    /* access modifiers changed from: private */
    public final CookieHandler cookieHandler;
    /* access modifiers changed from: private */
    public boolean haveReadPermission;
    /* access modifiers changed from: private */
    public boolean haveWritePermission;
    /* access modifiers changed from: private */
    public YailList requestHeaders;
    /* access modifiers changed from: private */
    public String responseFileName;
    /* access modifiers changed from: private */
    public boolean saveResponse;
    /* access modifiers changed from: private */
    public int timeout;
    /* access modifiers changed from: private */
    public String urlString;

    static class b extends Exception {
        final int eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i;
        final int rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs;

        b(int i, int i2) {
            this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = i;
            this.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i = i2;
        }
    }

    static class a extends Exception {
        final int eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i;
        final int rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs;

        a(int i, int i2) {
            this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = i;
            this.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i = i2;
        }
    }

    static class CapturedProperties {
        final boolean allowCookies;
        final Map<String, List<String>> cookies;
        final Map<String, List<String>> requestHeaders;
        final String responseFileName;
        final boolean saveResponse;
        final int timeout;
        final URL url;
        final String urlString;

        CapturedProperties(Web web) throws MalformedURLException, b {
            Map<String, List<String>> map;
            String access$000 = web.urlString;
            this.urlString = access$000;
            URL url2 = new URL(access$000);
            this.url = url2;
            boolean access$100 = web.allowCookies;
            this.allowCookies = access$100;
            this.saveResponse = web.saveResponse;
            this.responseFileName = web.responseFileName;
            this.timeout = web.timeout;
            Map<String, List<String>> access$600 = Web.processRequestHeaders(web.requestHeaders);
            this.requestHeaders = access$600;
            if (access$100 && web.cookieHandler != null) {
                try {
                    map = web.cookieHandler.get(url2.toURI(), access$600);
                } catch (Exception | URISyntaxException unused) {
                }
                this.cookies = map;
            }
            map = null;
            this.cookies = map;
        }
    }

    static {
        HashMap newHashMap = Maps.newHashMap();
        mimeTypeToExtension = newHashMap;
        newHashMap.put("application/pdf", "pdf");
        newHashMap.put("application/zip", HeaderParameterNames.ZIP);
        newHashMap.put("audio/mpeg", "mpeg");
        newHashMap.put("audio/mp3", "mp3");
        newHashMap.put("audio/mp4", "mp4");
        newHashMap.put("image/gif", "gif");
        newHashMap.put("image/jpeg", "jpg");
        newHashMap.put("image/png", "png");
        newHashMap.put("image/tiff", "tiff");
        newHashMap.put("text/plain", "txt");
        newHashMap.put(NanoHTTPD.MIME_HTML, "html");
        newHashMap.put(NanoHTTPD.MIME_XML, "xml");
    }

    public Web(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.urlString = "";
        this.requestHeaders = new YailList();
        this.responseFileName = "";
        this.timeout = 0;
        this.haveReadPermission = false;
        this.haveWritePermission = false;
        this.activity = componentContainer.$context();
        this.cookieHandler = new CookieManager();
    }

    protected Web() {
        super((Form) null);
        this.urlString = "";
        this.requestHeaders = new YailList();
        this.responseFileName = "";
        this.timeout = 0;
        this.haveReadPermission = false;
        this.haveWritePermission = false;
        this.activity = null;
        this.cookieHandler = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL for the web request.")
    public String Url() {
        return this.urlString;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Url(String str) {
        this.urlString = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The request headers, as a list of two-element sublists. The first element of each sublist represents the request header field name. The second element of each sublist represents the request header field values, either a single value or a list containing multiple values.")
    public YailList RequestHeaders() {
        return this.requestHeaders;
    }

    @SimpleProperty
    public void RequestHeaders(YailList yailList) {
        try {
            processRequestHeaders(yailList);
            this.requestHeaders = yailList;
        } catch (b e) {
            this.form.dispatchErrorOccurredEvent(this, "RequestHeaders", e.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs, Integer.valueOf(e.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i));
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the cookies from a response should be saved and used in subsequent requests. Cookies are only supported on Android version 2.3 or greater.")
    public boolean AllowCookies() {
        return this.allowCookies;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void AllowCookies(boolean z) {
        this.allowCookies = z;
        if (z && this.cookieHandler == null) {
            this.form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the response should be saved in a file.")
    public boolean SaveResponse() {
        return this.saveResponse;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void SaveResponse(boolean z) {
        this.saveResponse = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file where the response should be saved. If SaveResponse is true and ResponseFileName is empty, then a new file name will be generated.")
    public String ResponseFileName() {
        return this.responseFileName;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ResponseFileName(String str) {
        this.responseFileName = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of milliseconds that a web request will wait for a response before giving up. If set to 0, then there is no time limit on how long the request will wait.")
    public int Timeout() {
        return this.timeout;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void Timeout(int i) {
        if (i >= 0) {
            this.timeout = i;
            return;
        }
        throw new IllegalArgumentError("Web Timeout must be a non-negative integer.");
    }

    @SimpleFunction(description = "Clears all cookies for this Web component.")
    public void ClearCookies() {
        CookieHandler cookieHandler2 = this.cookieHandler;
        if (cookieHandler2 != null) {
            GingerbreadUtil.clearCookies(cookieHandler2);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
        }
    }

    @SimpleFunction
    public void Get() {
        final CapturedProperties capturePropertyValues = capturePropertyValues("Get");
        if (capturePropertyValues != null) {
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    Web.this.performRequest(capturePropertyValues, (byte[]) null, (String) null, DefaultHttpClient.METHOD_GET, "Get");
                }
            });
        }
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostText(String str) {
        requestTextImpl(str, "UTF-8", "PostText", DefaultHttpClient.METHOD_POST);
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostTextWithEncoding(String str, String str2) {
        requestTextImpl(str, str2, "PostTextWithEncoding", DefaultHttpClient.METHOD_POST);
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PostFile(final String str) {
        final CapturedProperties capturePropertyValues = capturePropertyValues("PostFile");
        if (capturePropertyValues != null) {
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    Web.this.performRequest(capturePropertyValues, (byte[]) null, str, DefaultHttpClient.METHOD_POST, "PostFile");
                }
            });
        }
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutText(String str) {
        requestTextImpl(str, "UTF-8", "PutText", "PUT");
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutTextWithEncoding(String str, String str2) {
        requestTextImpl(str, str2, "PutTextWithEncoding", "PUT");
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutFile(final String str) {
        final CapturedProperties capturePropertyValues = capturePropertyValues("PutFile");
        if (capturePropertyValues != null) {
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    Web.this.performRequest(capturePropertyValues, (byte[]) null, str, "PUT", "PutFile");
                }
            });
        }
    }

    @SimpleFunction
    public void Delete() {
        final CapturedProperties capturePropertyValues = capturePropertyValues("Delete");
        if (capturePropertyValues != null) {
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    Web.this.performRequest(capturePropertyValues, (byte[]) null, (String) null, DefaultHttpClient.METHOD_DELETE, "Delete");
                }
            });
        }
    }

    private void requestTextImpl(String str, String str2, String str3, String str4) {
        final CapturedProperties capturePropertyValues = capturePropertyValues(str3);
        if (capturePropertyValues != null) {
            final String str5 = str2;
            final String str6 = str;
            final String str7 = str3;
            final String str8 = str4;
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    byte[] bArr;
                    try {
                        String str = str5;
                        if (str != null) {
                            if (str.length() != 0) {
                                bArr = str6.getBytes(str5);
                                Web.this.performRequest(capturePropertyValues, bArr, (String) null, str8, str7);
                            }
                        }
                        bArr = str6.getBytes("UTF-8");
                        Web.this.performRequest(capturePropertyValues, bArr, (String) null, str8, str7);
                    } catch (UnsupportedEncodingException unused) {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, str7, ErrorMessages.ERROR_WEB_UNSUPPORTED_ENCODING, str5);
                    }
                }
            });
        }
    }

    @SimpleEvent
    public void GotText(String str, int i, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "GotText", str, Integer.valueOf(i), str2, str3);
    }

    @SimpleEvent
    public void GotFile(String str, int i, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "GotFile", str, Integer.valueOf(i), str2, str3);
    }

    @SimpleEvent
    public void TimedOut(String str) {
        EventDispatcher.dispatchEvent(this, "TimedOut", str);
    }

    @SimpleFunction
    public String BuildRequestData(YailList yailList) {
        try {
            return buildRequestData(yailList);
        } catch (a e) {
            this.form.dispatchErrorOccurredEvent(this, "BuildRequestData", e.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs, Integer.valueOf(e.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i));
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public String buildRequestData(YailList yailList) throws a {
        StringBuilder sb = new StringBuilder();
        String str = "";
        int i = 0;
        while (i < yailList.size()) {
            Object object = yailList.getObject(i);
            if (object instanceof YailList) {
                YailList yailList2 = (YailList) object;
                if (yailList2.size() == 2) {
                    String obj = yailList2.getObject(0).toString();
                    String obj2 = yailList2.getObject(1).toString();
                    sb.append(str);
                    sb.append(UriEncode(obj));
                    sb.append('=');
                    sb.append(UriEncode(obj2));
                    i++;
                    str = "&";
                } else {
                    throw new a(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_TWO_ELEMENTS, i + 1);
                }
            } else {
                throw new a(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_LIST, i + 1);
            }
        }
        return sb.toString();
    }

    @SimpleFunction
    public String UriEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public String UriDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public Object JsonTextDecode(String str) {
        try {
            return decodeJsonText(str);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "JsonTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, str);
            return "";
        }
    }

    static Object decodeJsonText(String str) throws IllegalArgumentException {
        try {
            return JsonUtil.getObjectFromJson(str);
        } catch (JSONException unused) {
            throw new IllegalArgumentException("jsonText is not a legal JSON value");
        }
    }

    @SimpleFunction(description = "Decodes the given XML string to produce a list structure.  See the App Inventor documentation on \"Other topics, notes, and details\" for information.")
    public Object XMLTextDecode(String str) {
        try {
            return JsonTextDecode(XML.toJSONObject(str).toString());
        } catch (com.google.appinventor.components.runtime.repackaged.org.json.JSONException e) {
            Log.e("Exception in XMLTextDecode", e.getMessage());
            Form form = this.form;
            form.dispatchErrorOccurredEvent(this, "XMLTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, e.getMessage());
            return YailList.makeEmptyList();
        }
    }

    @SimpleFunction(description = "Decodes the given HTML text value. HTML character entities such as &amp;amp;, &amp;lt;, &amp;gt;, &amp;apos;, and &amp;quot; are changed to &amp;, &lt;, &gt;, &#39;, and &quot;. Entities such as &amp;#xhhhh, and &amp;#nnnn are changed to the appropriate characters.")
    public String HtmlTextDecode(String str) {
        try {
            return HtmlEntities.decodeHtmlText(str);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", ErrorMessages.ERROR_WEB_HTML_TEXT_DECODE_FAILED, str);
            return "";
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:40|41|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r13.activity.runOnUiThread(new com.google.appinventor.components.runtime.Web.AnonymousClass9(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00dd, code lost:
        throw new com.google.appinventor.components.runtime.errors.RequestTimeoutException();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00ce */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performRequest(com.google.appinventor.components.runtime.Web.CapturedProperties r17, byte[] r18, java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
            r16 = this;
            r13 = r16
            r8 = r17
            r0 = r18
            r10 = r19
            r12 = r21
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            if (r10 == 0) goto L_0x0022
            com.google.appinventor.components.runtime.Form r1 = r13.form
            boolean r1 = com.google.appinventor.components.runtime.util.FileUtil.needsPermission(r1, r10)
            if (r1 == 0) goto L_0x0022
            boolean r1 = r13.haveReadPermission
            if (r1 != 0) goto L_0x0022
            java.lang.String r1 = "android.permission.READ_EXTERNAL_STORAGE"
            r6.add(r1)
        L_0x0022:
            boolean r1 = r13.saveResponse
            if (r1 == 0) goto L_0x0045
            com.google.appinventor.components.runtime.Form r1 = r13.form
            java.lang.String r2 = r8.responseFileName
            com.google.appinventor.components.runtime.Form r3 = r13.form
            com.google.appinventor.components.common.FileScope r3 = r3.DefaultFileScope()
            java.lang.String r1 = com.google.appinventor.components.runtime.util.FileUtil.resolveFileName(r1, r2, r3)
            com.google.appinventor.components.runtime.Form r2 = r13.form
            boolean r1 = com.google.appinventor.components.runtime.util.FileUtil.needsPermission(r2, r1)
            if (r1 == 0) goto L_0x0045
            boolean r1 = r13.haveWritePermission
            if (r1 != 0) goto L_0x0045
            java.lang.String r1 = "android.permission.WRITE_EXTERNAL_STORAGE"
            r6.add(r1)
        L_0x0045:
            int r1 = r6.size()
            r7 = 1
            r9 = 0
            if (r1 <= 0) goto L_0x004f
            r1 = 1
            goto L_0x0050
        L_0x004f:
            r1 = 0
        L_0x0050:
            boolean r2 = r13.haveReadPermission
            r2 = r2 ^ r7
            r1 = r1 & r2
            if (r1 == 0) goto L_0x007d
            com.google.appinventor.components.runtime.Form r14 = r13.form
            com.google.appinventor.components.runtime.Web$6 r15 = new com.google.appinventor.components.runtime.Web$6
            java.lang.String[] r1 = new java.lang.String[r9]
            java.lang.Object[] r1 = r6.toArray(r1)
            r5 = r1
            java.lang.String[] r5 = (java.lang.String[]) r5
            r1 = r15
            r2 = r16
            r3 = r16
            r4 = r21
            r7 = r16
            r8 = r17
            r9 = r18
            r10 = r19
            r11 = r20
            r12 = r21
            r1.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r14.askPermission(r15)
            return
        L_0x007d:
            r1 = r20
            java.net.HttpURLConnection r11 = openConnection(r8, r1)     // Catch:{ PermissionException -> 0x0146, FileException -> 0x0139, DispatchableError -> 0x012a, RequestTimeoutException -> 0x011c, Exception -> 0x00e3 }
            if (r11 == 0) goto L_0x00e2
            if (r0 == 0) goto L_0x008d
            writeRequestData(r11, r0)     // Catch:{ SocketTimeoutException -> 0x00ce }
            goto L_0x0092
        L_0x008b:
            r0 = move-exception
            goto L_0x00de
        L_0x008d:
            if (r10 == 0) goto L_0x0092
            r13.writeRequestFile(r11, r10)     // Catch:{ SocketTimeoutException -> 0x00ce }
        L_0x0092:
            int r4 = r11.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x00ce }
            java.lang.String r5 = getResponseType(r11)     // Catch:{ SocketTimeoutException -> 0x00ce }
            r13.processResponseCookies(r11)     // Catch:{ SocketTimeoutException -> 0x00ce }
            boolean r0 = r13.saveResponse     // Catch:{ SocketTimeoutException -> 0x00ce }
            if (r0 == 0) goto L_0x00b7
            java.lang.String r0 = r8.responseFileName     // Catch:{ SocketTimeoutException -> 0x00ce }
            java.lang.String r6 = r13.saveResponseContent(r11, r0, r5)     // Catch:{ SocketTimeoutException -> 0x00ce }
            android.app.Activity r0 = r13.activity     // Catch:{ SocketTimeoutException -> 0x00ce }
            com.google.appinventor.components.runtime.Web$7 r10 = new com.google.appinventor.components.runtime.Web$7     // Catch:{ SocketTimeoutException -> 0x00ce }
            r1 = r10
            r2 = r16
            r3 = r17
            r1.<init>(r3, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x00ce }
            r0.runOnUiThread(r10)     // Catch:{ SocketTimeoutException -> 0x00ce }
            goto L_0x00ca
        L_0x00b7:
            java.lang.String r6 = getResponseContent(r11)     // Catch:{ SocketTimeoutException -> 0x00ce }
            android.app.Activity r0 = r13.activity     // Catch:{ SocketTimeoutException -> 0x00ce }
            com.google.appinventor.components.runtime.Web$8 r10 = new com.google.appinventor.components.runtime.Web$8     // Catch:{ SocketTimeoutException -> 0x00ce }
            r1 = r10
            r2 = r16
            r3 = r17
            r1.<init>(r3, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x00ce }
            r0.runOnUiThread(r10)     // Catch:{ SocketTimeoutException -> 0x00ce }
        L_0x00ca:
            r11.disconnect()     // Catch:{ PermissionException -> 0x0146, FileException -> 0x0139, DispatchableError -> 0x012a, RequestTimeoutException -> 0x011c, Exception -> 0x00e3 }
            return
        L_0x00ce:
            android.app.Activity r0 = r13.activity     // Catch:{ all -> 0x008b }
            com.google.appinventor.components.runtime.Web$9 r1 = new com.google.appinventor.components.runtime.Web$9     // Catch:{ all -> 0x008b }
            r1.<init>(r8)     // Catch:{ all -> 0x008b }
            r0.runOnUiThread(r1)     // Catch:{ all -> 0x008b }
            com.google.appinventor.components.runtime.errors.RequestTimeoutException r0 = new com.google.appinventor.components.runtime.errors.RequestTimeoutException     // Catch:{ all -> 0x008b }
            r0.<init>()     // Catch:{ all -> 0x008b }
            throw r0     // Catch:{ all -> 0x008b }
        L_0x00de:
            r11.disconnect()     // Catch:{ PermissionException -> 0x0146, FileException -> 0x0139, DispatchableError -> 0x012a, RequestTimeoutException -> 0x011c, Exception -> 0x00e3 }
            throw r0     // Catch:{ PermissionException -> 0x0146, FileException -> 0x0139, DispatchableError -> 0x012a, RequestTimeoutException -> 0x011c, Exception -> 0x00e3 }
        L_0x00e2:
            return
        L_0x00e3:
            java.lang.String r0 = "Get"
            boolean r0 = r12.equals(r0)
            r1 = 1104(0x450, float:1.547E-42)
            if (r0 == 0) goto L_0x00f1
            r1 = 1101(0x44d, float:1.543E-42)
            goto L_0x0110
        L_0x00f1:
            java.lang.String r0 = "PostFile"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L_0x00fa
            goto L_0x0110
        L_0x00fa:
            java.lang.String r0 = "PutFile"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L_0x0103
            goto L_0x0110
        L_0x0103:
            java.lang.String r0 = "Delete"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L_0x010e
            r1 = 1114(0x45a, float:1.561E-42)
            goto L_0x0110
        L_0x010e:
            r1 = 1103(0x44f, float:1.546E-42)
        L_0x0110:
            com.google.appinventor.components.runtime.Form r0 = r13.form
            java.lang.Object[] r2 = new java.lang.Object[r7]
            java.lang.String r3 = r8.urlString
            r2[r9] = r3
            r0.dispatchErrorOccurredEvent(r13, r12, r1, r2)
            return
        L_0x011c:
            com.google.appinventor.components.runtime.Form r0 = r13.form
            r1 = 1117(0x45d, float:1.565E-42)
            java.lang.Object[] r2 = new java.lang.Object[r7]
            java.lang.String r3 = r8.urlString
            r2[r9] = r3
            r0.dispatchErrorOccurredEvent(r13, r12, r1, r2)
            return
        L_0x012a:
            r0 = move-exception
            com.google.appinventor.components.runtime.Form r1 = r13.form
            int r2 = r0.getErrorCode()
            java.lang.Object[] r0 = r0.getArguments()
            r1.dispatchErrorOccurredEvent(r13, r12, r2, r0)
            return
        L_0x0139:
            r0 = move-exception
            com.google.appinventor.components.runtime.Form r1 = r13.form
            int r0 = r0.getErrorMessageNumber()
            java.lang.Object[] r2 = new java.lang.Object[r9]
            r1.dispatchErrorOccurredEvent(r13, r12, r0, r2)
            return
        L_0x0146:
            r0 = move-exception
            com.google.appinventor.components.runtime.Form r1 = r13.form
            r1.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r13, (java.lang.String) r12, (com.google.appinventor.components.runtime.errors.PermissionException) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Web.performRequest(com.google.appinventor.components.runtime.Web$CapturedProperties, byte[], java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static HttpURLConnection openConnection(CapturedProperties capturedProperties, String str) throws IOException, ClassCastException, ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) capturedProperties.url.openConnection();
        httpURLConnection.setConnectTimeout(capturedProperties.timeout);
        httpURLConnection.setReadTimeout(capturedProperties.timeout);
        if (str.equals("PUT") || str.equals(DefaultHttpClient.METHOD_DELETE)) {
            httpURLConnection.setRequestMethod(str);
        }
        for (Map.Entry next : capturedProperties.requestHeaders.entrySet()) {
            String str2 = (String) next.getKey();
            for (String addRequestProperty : (List) next.getValue()) {
                httpURLConnection.addRequestProperty(str2, addRequestProperty);
            }
        }
        if (capturedProperties.cookies != null) {
            for (Map.Entry next2 : capturedProperties.cookies.entrySet()) {
                String str3 = (String) next2.getKey();
                for (String addRequestProperty2 : (List) next2.getValue()) {
                    httpURLConnection.addRequestProperty(str3, addRequestProperty2);
                }
            }
        }
        return httpURLConnection;
    }

    private static void writeRequestData(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        try {
            bufferedOutputStream.write(bArr, 0, bArr.length);
            bufferedOutputStream.flush();
        } finally {
            bufferedOutputStream.close();
        }
    }

    private void writeRequestFile(HttpURLConnection httpURLConnection, String str) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(MediaUtil.openMedia(this.form, str));
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setChunkedStreamingMode(0);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            while (true) {
                int read = bufferedInputStream.read();
                if (read != -1) {
                    bufferedOutputStream.write(read);
                } else {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            bufferedInputStream.close();
            throw th;
        }
    }

    private static String getResponseType(HttpURLConnection httpURLConnection) {
        String contentType = httpURLConnection.getContentType();
        return contentType != null ? contentType : "";
    }

    private void processResponseCookies(HttpURLConnection httpURLConnection) {
        if (this.allowCookies && this.cookieHandler != null) {
            try {
                this.cookieHandler.put(httpURLConnection.getURL().toURI(), httpURLConnection.getHeaderFields());
            } catch (Exception | URISyntaxException unused) {
            }
        }
    }

    private static String getResponseContent(HttpURLConnection httpURLConnection) throws IOException {
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (contentEncoding == null) {
            contentEncoding = "UTF-8";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(getConnectionStream(httpURLConnection), contentEncoding);
        try {
            int contentLength = httpURLConnection.getContentLength();
            StringBuilder sb = contentLength != -1 ? new StringBuilder(contentLength) : new StringBuilder();
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } finally {
            inputStreamReader.close();
        }
    }

    private String saveResponseContent(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        File createFile = createFile(str, str2);
        File parentFile = createFile.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(getConnectionStream(httpURLConnection), 4096);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(createFile), 4096);
                while (true) {
                    int read = bufferedInputStream.read();
                    if (read != -1) {
                        bufferedOutputStream.write(read);
                    } else {
                        bufferedOutputStream.close();
                        bufferedInputStream.close();
                        return createFile.getAbsolutePath();
                    }
                }
            } catch (Throwable th) {
                bufferedInputStream.close();
                throw th;
            }
        } else {
            throw new DispatchableError(ErrorMessages.ERROR_CANNOT_MAKE_DIRECTORY, parentFile.getAbsolutePath());
        }
    }

    private static InputStream getConnectionStream(HttpURLConnection httpURLConnection) throws SocketTimeoutException {
        try {
            return httpURLConnection.getInputStream();
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private File createFile(String str, String str2) throws IOException, FileUtil.FileException {
        if (!TextUtils.isEmpty(str)) {
            return FileUtil.getExternalFile(this.form, str);
        }
        int indexOf = str2.indexOf(59);
        if (indexOf != -1) {
            str2 = str2.substring(0, indexOf);
        }
        String str3 = mimeTypeToExtension.get(str2);
        if (str3 == null) {
            str3 = "tmp";
        }
        return FileUtil.getDownloadFile(this.form, str3);
    }

    /* access modifiers changed from: private */
    public static Map<String, List<String>> processRequestHeaders(YailList yailList) throws b {
        HashMap newHashMap = Maps.newHashMap();
        int i = 0;
        while (i < yailList.size()) {
            Object object = yailList.getObject(i);
            if (object instanceof YailList) {
                YailList yailList2 = (YailList) object;
                if (yailList2.size() == 2) {
                    String obj = yailList2.getObject(0).toString();
                    Object object2 = yailList2.getObject(1);
                    ArrayList newArrayList = Lists.newArrayList();
                    if (object2 instanceof YailList) {
                        YailList yailList3 = (YailList) object2;
                        for (int i2 = 0; i2 < yailList3.size(); i2++) {
                            newArrayList.add(yailList3.getObject(i2).toString());
                        }
                    } else {
                        newArrayList.add(object2.toString());
                    }
                    newHashMap.put(obj, newArrayList);
                    i++;
                } else {
                    throw new b(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_TWO_ELEMENTS, i + 1);
                }
            } else {
                throw new b(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_LIST, i + 1);
            }
        }
        return newHashMap;
    }

    private CapturedProperties capturePropertyValues(String str) {
        try {
            return new CapturedProperties(this);
        } catch (MalformedURLException unused) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_WEB_MALFORMED_URL, this.urlString);
            return null;
        } catch (b e) {
            this.form.dispatchErrorOccurredEvent(this, str, e.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs, Integer.valueOf(e.eCKrLERXZY2Z3jwuVt55PeHUkE4lFRkPVtMcgtoMaoQxt1GsNCdNb2NNztke1B7i));
            return null;
        }
    }
}
