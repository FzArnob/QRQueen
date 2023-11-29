package com.google.zxing.client.android;

import android.util.Log;
import androidx.appcompat.widget.ActivityChooserView;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class HttpHelper {
    private static final Collection<String> REDIRECTOR_DOMAINS = new HashSet(Arrays.asList(new String[]{"amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im"}));
    private static final String TAG = "HttpHelper";

    public enum ContentType {
        HTML,
        JSON,
        TEXT
    }

    private HttpHelper() {
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType) throws IOException {
        return downloadViaHttp(str, contentType, (int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    /* renamed from: com.google.zxing.client.android.HttpHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.zxing.client.android.HttpHelper$ContentType[] r0 = com.google.zxing.client.android.HttpHelper.ContentType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType = r0
                com.google.zxing.client.android.HttpHelper$ContentType r1 = com.google.zxing.client.android.HttpHelper.ContentType.HTML     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.client.android.HttpHelper$ContentType r1 = com.google.zxing.client.android.HttpHelper.ContentType.JSON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.client.android.HttpHelper$ContentType r1 = com.google.zxing.client.android.HttpHelper.ContentType.TEXT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.HttpHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType, int i) throws IOException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType[contentType.ordinal()];
        return downloadViaHttp(str, i2 != 1 ? i2 != 2 ? "text/*,*/*" : "application/json,text/*,*/*" : "application/xhtml+xml,text/html,text/*,*/*", i);
    }

    private static CharSequence downloadViaHttp(String str, String str2, int i) throws IOException {
        String str3 = TAG;
        Log.i(str3, "Downloading " + str);
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestProperty("Accept", str2);
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8,*");
            httpURLConnection.setRequestProperty("User-Agent", "ZXing (Android)");
            try {
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    Log.i(str3, "Consuming " + str);
                    CharSequence consume = consume(httpURLConnection, i);
                    httpURLConnection.disconnect();
                    return consume;
                }
                throw new IOException("Bad HTTP response: " + responseCode);
            } catch (NullPointerException e) {
                String str4 = TAG;
                Log.w(str4, "Bad URI? " + str);
                throw new IOException(e);
            } catch (IllegalArgumentException e2) {
                String str5 = TAG;
                Log.w(str5, "Bad URI? " + str);
                throw new IOException(e2);
            } catch (NullPointerException e3) {
                String str6 = TAG;
                Log.w(str6, "Bad URI? " + str);
                throw new IOException(e3);
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                throw th;
            }
        } else {
            throw new IOException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r1.indexOf("charset=");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getEncoding(java.net.URLConnection r1) {
        /*
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = r1.getHeaderField(r0)
            if (r1 == 0) goto L_0x0017
            java.lang.String r0 = "charset="
            int r0 = r1.indexOf(r0)
            if (r0 < 0) goto L_0x0017
            int r0 = r0 + 8
            java.lang.String r1 = r1.substring(r0)
            return r1
        L_0x0017:
            java.lang.String r1 = "UTF-8"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.HttpHelper.getEncoding(java.net.URLConnection):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.CharSequence consume(java.net.URLConnection r4, int r5) throws java.io.IOException {
        /*
            java.lang.String r0 = getEncoding(r4)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x002f }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ all -> 0x002f }
            r3.<init>(r4, r0)     // Catch:{ all -> 0x002f }
            r4 = 1024(0x400, float:1.435E-42)
            char[] r4 = new char[r4]     // Catch:{ all -> 0x002c }
        L_0x0017:
            int r0 = r1.length()     // Catch:{ all -> 0x002c }
            if (r0 >= r5) goto L_0x0028
            int r0 = r3.read(r4)     // Catch:{ all -> 0x002c }
            if (r0 <= 0) goto L_0x0028
            r2 = 0
            r1.append(r4, r2, r0)     // Catch:{ all -> 0x002c }
            goto L_0x0017
        L_0x0028:
            r3.close()     // Catch:{ IOException | NullPointerException -> 0x002b }
        L_0x002b:
            return r1
        L_0x002c:
            r4 = move-exception
            r2 = r3
            goto L_0x0030
        L_0x002f:
            r4 = move-exception
        L_0x0030:
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ IOException | NullPointerException -> 0x0035 }
        L_0x0035:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.HttpHelper.consume(java.net.URLConnection, int):java.lang.CharSequence");
    }

    public static URI unredirect(URI uri) throws IOException {
        if (!REDIRECTOR_DOMAINS.contains(uri.getHost())) {
            return uri;
        }
        URLConnection openConnection = uri.toURL().openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(false);
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.setRequestProperty("User-Agent", "ZXing (Android)");
            try {
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 307) {
                    switch (responseCode) {
                        case 300:
                        case ErrorMessages.ERROR_TWITTER_UNSUPPORTED_LOGIN_FUNCTION:
                        case ErrorMessages.ERROR_TWITTER_BLANK_CONSUMER_KEY_OR_SECRET:
                        case ErrorMessages.ERROR_TWITTER_EXCEPTION:
                            break;
                    }
                }
                String headerField = httpURLConnection.getHeaderField("Location");
                if (headerField != null) {
                    try {
                        URI uri2 = new URI(headerField);
                        httpURLConnection.disconnect();
                        return uri2;
                    } catch (URISyntaxException unused) {
                    }
                }
                httpURLConnection.disconnect();
                return uri;
            } catch (NullPointerException e) {
                String str = TAG;
                Log.w(str, "Bad URI? " + uri);
                throw new IOException(e);
            } catch (IllegalArgumentException e2) {
                String str2 = TAG;
                Log.w(str2, "Bad URI? " + uri);
                throw new IOException(e2);
            } catch (NullPointerException e3) {
                String str3 = TAG;
                Log.w(str3, "Bad URI? " + uri);
                throw new IOException(e3);
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                throw th;
            }
        } else {
            throw new IOException();
        }
    }
}
