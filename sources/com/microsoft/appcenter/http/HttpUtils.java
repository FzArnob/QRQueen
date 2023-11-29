package com.microsoft.appcenter.http;

import android.content.Context;
import android.os.Build;
import com.microsoft.appcenter.utils.NetworkStateHelper;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

public class HttpUtils {
    private static final Pattern API_KEY_PATTERN = Pattern.compile("-[^,]+(?<!-)(,|$)");
    private static final Pattern CONNECTION_ISSUE_PATTERN = Pattern.compile("connection (time|reset|abort)|failure in ssl library, usually a protocol error|anchor for certification path not found");
    public static final int CONNECT_TIMEOUT = 10000;
    static final int MAX_CHARACTERS_DISPLAYED_FOR_SECRET = 8;
    public static final int READ_BUFFER_SIZE = 1024;
    public static final int READ_TIMEOUT = 10000;
    private static final Class[] RECOVERABLE_EXCEPTIONS = {EOFException.class, InterruptedIOException.class, SocketException.class, UnknownHostException.class, RejectedExecutionException.class};
    public static final int THREAD_STATS_TAG = -667034599;
    private static final Pattern TOKEN_VALUE_PATTERN = Pattern.compile(":[^\"]+");
    public static final int WRITE_BUFFER_SIZE = 1024;

    HttpUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        r8 = r8.getMessage();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRecoverableError(java.lang.Throwable r8) {
        /*
            boolean r0 = r8 instanceof com.microsoft.appcenter.http.HttpException
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001e
            com.microsoft.appcenter.http.HttpException r8 = (com.microsoft.appcenter.http.HttpException) r8
            com.microsoft.appcenter.http.HttpResponse r8 = r8.getHttpResponse()
            int r8 = r8.getStatusCode()
            r0 = 500(0x1f4, float:7.0E-43)
            if (r8 >= r0) goto L_0x001c
            r0 = 408(0x198, float:5.72E-43)
            if (r8 == r0) goto L_0x001c
            r0 = 429(0x1ad, float:6.01E-43)
            if (r8 != r0) goto L_0x001d
        L_0x001c:
            r1 = 1
        L_0x001d:
            return r1
        L_0x001e:
            java.lang.Class[] r0 = RECOVERABLE_EXCEPTIONS
            int r3 = r0.length
            r4 = 0
        L_0x0022:
            if (r4 >= r3) goto L_0x0034
            r5 = r0[r4]
            java.lang.Class r6 = r8.getClass()
            boolean r5 = r5.isAssignableFrom(r6)
            if (r5 == 0) goto L_0x0031
            return r2
        L_0x0031:
            int r4 = r4 + 1
            goto L_0x0022
        L_0x0034:
            java.lang.Throwable r0 = r8.getCause()
            if (r0 == 0) goto L_0x0050
            java.lang.Class[] r3 = RECOVERABLE_EXCEPTIONS
            int r4 = r3.length
            r5 = 0
        L_0x003e:
            if (r5 >= r4) goto L_0x0050
            r6 = r3[r5]
            java.lang.Class r7 = r0.getClass()
            boolean r6 = r6.isAssignableFrom(r7)
            if (r6 == 0) goto L_0x004d
            return r2
        L_0x004d:
            int r5 = r5 + 1
            goto L_0x003e
        L_0x0050:
            boolean r0 = r8 instanceof javax.net.ssl.SSLException
            if (r0 == 0) goto L_0x006d
            java.lang.String r8 = r8.getMessage()
            if (r8 == 0) goto L_0x006d
            java.util.regex.Pattern r0 = CONNECTION_ISSUE_PATTERN
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r8 = r8.toLowerCase(r3)
            java.util.regex.Matcher r8 = r0.matcher(r8)
            boolean r8 = r8.find()
            if (r8 == 0) goto L_0x006d
            return r2
        L_0x006d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.http.HttpUtils.isRecoverableError(java.lang.Throwable):boolean");
    }

    public static String hideSecret(String str) {
        int length = str.length();
        int i = 8;
        if (str.length() < 8) {
            i = 0;
        }
        int i2 = length - i;
        char[] cArr = new char[i2];
        Arrays.fill(cArr, '*');
        return new String(cArr) + str.substring(i2);
    }

    public static String hideApiKeys(String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = API_KEY_PATTERN.matcher(str);
        int i = 0;
        while (matcher.find()) {
            sb.append(str.substring(i, matcher.start()));
            sb.append("-***");
            sb.append(matcher.group(1));
            i = matcher.end();
        }
        if (i < str.length()) {
            sb.append(str.substring(i));
        }
        return sb.toString();
    }

    public static String hideTickets(String str) {
        return TOKEN_VALUE_PATTERN.matcher(str).replaceAll(":***");
    }

    public static HttpClient createHttpClient(Context context) {
        return createHttpClient(context, true);
    }

    public static HttpClient createHttpClient(Context context, boolean z) {
        return new HttpClientRetryer(createHttpClientWithoutRetryer(context, z));
    }

    public static HttpClient createHttpClientWithoutRetryer(Context context, boolean z) {
        return new HttpClientNetworkStateHandler(new DefaultHttpClient(z), NetworkStateHelper.getSharedInstance(context));
    }

    public static HttpsURLConnection createHttpsConnection(URL url) throws IOException {
        if ("https".equals(url.getProtocol())) {
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                if (Build.VERSION.SDK_INT <= 21) {
                    httpsURLConnection.setSSLSocketFactory(new TLS1_2SocketFactory());
                }
                httpsURLConnection.setConnectTimeout(10000);
                httpsURLConnection.setReadTimeout(10000);
                return httpsURLConnection;
            }
            throw new IOException("App Center supports only HTTPS connection.");
        }
        throw new IOException("App Center support only HTTPS connection.");
    }
}
