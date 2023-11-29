package com.microsoft.appcenter.http;

import android.net.TrafficStats;
import android.os.AsyncTask;
import com.microsoft.appcenter.http.HttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

class DefaultHttpClientCallTask extends AsyncTask<Void, Void, Object> {
    private static final int DEFAULT_STRING_BUILDER_CAPACITY = 16;
    private static final int MAX_PRETTIFY_LOG_LENGTH = 4096;
    private static final int MIN_GZIP_LENGTH = 1400;
    private static final Pattern REDIRECT_URI_REGEX_JSON = Pattern.compile("redirect_uri\":\"[^\"]+\"");
    private static final Pattern TOKEN_REGEX_JSON = Pattern.compile("token\":\"[^\"]+\"");
    private static final Pattern TOKEN_REGEX_URL_ENCODED = Pattern.compile("token=[^&]+");
    private final HttpClient.CallTemplate mCallTemplate;
    private final boolean mCompressionEnabled;
    private final Map<String, String> mHeaders;
    private final String mMethod;
    private final ServiceCallback mServiceCallback;
    private final Tracker mTracker;
    private final String mUrl;

    interface Tracker {
        void onFinish(DefaultHttpClientCallTask defaultHttpClientCallTask);

        void onStart(DefaultHttpClientCallTask defaultHttpClientCallTask);
    }

    DefaultHttpClientCallTask(String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback, Tracker tracker, boolean z) {
        this.mUrl = str;
        this.mMethod = str2;
        this.mHeaders = map;
        this.mCallTemplate = callTemplate;
        this.mServiceCallback = serviceCallback;
        this.mTracker = tracker;
        this.mCompressionEnabled = z;
    }

    private static InputStream getInputStream(HttpsURLConnection httpsURLConnection) throws IOException {
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode < 200 || responseCode >= 400) {
            return httpsURLConnection.getErrorStream();
        }
        return httpsURLConnection.getInputStream();
    }

    private void writePayload(OutputStream outputStream, byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            outputStream.write(bArr, i, Math.min(bArr.length - i, 1024));
            if (!isCancelled()) {
                i += 1024;
            } else {
                return;
            }
        }
    }

    private String readResponse(HttpsURLConnection httpsURLConnection) throws IOException {
        StringBuilder sb = new StringBuilder(Math.max(httpsURLConnection.getContentLength(), 16));
        InputStream inputStream = getInputStream(httpsURLConnection);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            char[] cArr = new char[1024];
            do {
                int read = inputStreamReader.read(cArr);
                if (read <= 0) {
                    break;
                }
                sb.append(cArr, 0, read);
            } while (!isCancelled());
            return sb.toString();
        } finally {
            inputStream.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[Catch:{ all -> 0x00ed, all -> 0x01a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[Catch:{ all -> 0x00ed, all -> 0x01a6 }, LOOP:0: B:20:0x005e->B:22:0x0064, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084 A[SYNTHETIC, Splitter:B:27:0x0084] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microsoft.appcenter.http.HttpResponse doHttpCall() throws java.lang.Exception {
        /*
            r12 = this;
            java.net.URL r0 = new java.net.URL
            java.lang.String r1 = r12.mUrl
            r0.<init>(r1)
            javax.net.ssl.HttpsURLConnection r1 = com.microsoft.appcenter.http.HttpUtils.createHttpsConnection(r0)
            java.lang.String r2 = r12.mMethod     // Catch:{ all -> 0x01a6 }
            r1.setRequestMethod(r2)     // Catch:{ all -> 0x01a6 }
            java.lang.String r2 = r12.mMethod     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = "POST"
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = "application/json"
            r4 = 1
            r5 = 0
            java.lang.String r6 = "Content-Type"
            r7 = 0
            if (r2 == 0) goto L_0x0047
            com.microsoft.appcenter.http.HttpClient$CallTemplate r2 = r12.mCallTemplate     // Catch:{ all -> 0x01a6 }
            if (r2 == 0) goto L_0x0047
            java.lang.String r2 = r2.buildRequestBody()     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = "UTF-8"
            byte[] r8 = r2.getBytes(r8)     // Catch:{ all -> 0x01a6 }
            boolean r9 = r12.mCompressionEnabled     // Catch:{ all -> 0x01a6 }
            if (r9 == 0) goto L_0x0039
            int r9 = r8.length     // Catch:{ all -> 0x01a6 }
            r10 = 1400(0x578, float:1.962E-42)
            if (r9 < r10) goto L_0x0039
            r5 = 1
        L_0x0039:
            java.util.Map<java.lang.String, java.lang.String> r9 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            boolean r9 = r9.containsKey(r6)     // Catch:{ all -> 0x01a6 }
            if (r9 != 0) goto L_0x0049
            java.util.Map<java.lang.String, java.lang.String> r9 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            r9.put(r6, r3)     // Catch:{ all -> 0x01a6 }
            goto L_0x0049
        L_0x0047:
            r2 = r7
            r8 = r2
        L_0x0049:
            if (r5 == 0) goto L_0x0054
            java.util.Map<java.lang.String, java.lang.String> r9 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            java.lang.String r10 = "Content-Encoding"
            java.lang.String r11 = "gzip"
            r9.put(r10, r11)     // Catch:{ all -> 0x01a6 }
        L_0x0054:
            java.util.Map<java.lang.String, java.lang.String> r9 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            java.util.Set r9 = r9.entrySet()     // Catch:{ all -> 0x01a6 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x01a6 }
        L_0x005e:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x01a6 }
            if (r10 == 0) goto L_0x007a
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x01a6 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ all -> 0x01a6 }
            java.lang.Object r11 = r10.getKey()     // Catch:{ all -> 0x01a6 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x01a6 }
            java.lang.Object r10 = r10.getValue()     // Catch:{ all -> 0x01a6 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x01a6 }
            r1.setRequestProperty(r11, r10)     // Catch:{ all -> 0x01a6 }
            goto L_0x005e
        L_0x007a:
            boolean r9 = r12.isCancelled()     // Catch:{ all -> 0x01a6 }
            if (r9 == 0) goto L_0x0084
            r1.disconnect()
            return r7
        L_0x0084:
            com.microsoft.appcenter.http.HttpClient$CallTemplate r9 = r12.mCallTemplate     // Catch:{ all -> 0x01a6 }
            if (r9 == 0) goto L_0x008d
            java.util.Map<java.lang.String, java.lang.String> r10 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            r9.onBeforeCalling(r0, r10)     // Catch:{ all -> 0x01a6 }
        L_0x008d:
            java.lang.String r0 = "AppCenter"
            r9 = 2
            if (r8 == 0) goto L_0x00f2
            int r10 = com.microsoft.appcenter.utils.AppCenterLog.getLogLevel()     // Catch:{ all -> 0x01a6 }
            if (r10 > r9) goto L_0x00c4
            int r10 = r2.length()     // Catch:{ all -> 0x01a6 }
            r11 = 4096(0x1000, float:5.74E-42)
            if (r10 >= r11) goto L_0x00c1
            java.util.regex.Pattern r10 = TOKEN_REGEX_URL_ENCODED     // Catch:{ all -> 0x01a6 }
            java.util.regex.Matcher r2 = r10.matcher(r2)     // Catch:{ all -> 0x01a6 }
            java.lang.String r10 = "token=***"
            java.lang.String r2 = r2.replaceAll(r10)     // Catch:{ all -> 0x01a6 }
            java.util.Map<java.lang.String, java.lang.String> r10 = r12.mHeaders     // Catch:{ all -> 0x01a6 }
            java.lang.Object r10 = r10.get(r6)     // Catch:{ all -> 0x01a6 }
            boolean r3 = r3.equals(r10)     // Catch:{ all -> 0x01a6 }
            if (r3 == 0) goto L_0x00c1
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x01a6 }
            r3.<init>(r2)     // Catch:{ all -> 0x01a6 }
            java.lang.String r2 = r3.toString(r9)     // Catch:{ all -> 0x01a6 }
        L_0x00c1:
            com.microsoft.appcenter.utils.AppCenterLog.verbose(r0, r2)     // Catch:{ all -> 0x01a6 }
        L_0x00c4:
            if (r5 == 0) goto L_0x00db
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x01a6 }
            int r3 = r8.length     // Catch:{ all -> 0x01a6 }
            r2.<init>(r3)     // Catch:{ all -> 0x01a6 }
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x01a6 }
            r3.<init>(r2)     // Catch:{ all -> 0x01a6 }
            r3.write(r8)     // Catch:{ all -> 0x01a6 }
            r3.close()     // Catch:{ all -> 0x01a6 }
            byte[] r8 = r2.toByteArray()     // Catch:{ all -> 0x01a6 }
        L_0x00db:
            r1.setDoOutput(r4)     // Catch:{ all -> 0x01a6 }
            int r2 = r8.length     // Catch:{ all -> 0x01a6 }
            r1.setFixedLengthStreamingMode(r2)     // Catch:{ all -> 0x01a6 }
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ all -> 0x01a6 }
            r12.writePayload(r2, r8)     // Catch:{ all -> 0x00ed }
            r2.close()     // Catch:{ all -> 0x01a6 }
            goto L_0x00f2
        L_0x00ed:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x01a6 }
            throw r0     // Catch:{ all -> 0x01a6 }
        L_0x00f2:
            boolean r2 = r12.isCancelled()     // Catch:{ all -> 0x01a6 }
            if (r2 == 0) goto L_0x00fc
            r1.disconnect()
            return r7
        L_0x00fc:
            int r2 = r1.getResponseCode()     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = r12.readResponse(r1)     // Catch:{ all -> 0x01a6 }
            int r4 = com.microsoft.appcenter.utils.AppCenterLog.getLogLevel()     // Catch:{ all -> 0x01a6 }
            if (r4 > r9) goto L_0x0158
            java.lang.String r4 = r1.getHeaderField(r6)     // Catch:{ all -> 0x01a6 }
            if (r4 == 0) goto L_0x0124
            java.lang.String r5 = "text/"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x01a6 }
            if (r5 != 0) goto L_0x0124
            java.lang.String r5 = "application/"
            boolean r4 = r4.startsWith(r5)     // Catch:{ all -> 0x01a6 }
            if (r4 == 0) goto L_0x0121
            goto L_0x0124
        L_0x0121:
            java.lang.String r4 = "<binary>"
            goto L_0x013c
        L_0x0124:
            java.util.regex.Pattern r4 = TOKEN_REGEX_JSON     // Catch:{ all -> 0x01a6 }
            java.util.regex.Matcher r4 = r4.matcher(r3)     // Catch:{ all -> 0x01a6 }
            java.lang.String r5 = "token\":\"***\""
            java.lang.String r4 = r4.replaceAll(r5)     // Catch:{ all -> 0x01a6 }
            java.util.regex.Pattern r5 = REDIRECT_URI_REGEX_JSON     // Catch:{ all -> 0x01a6 }
            java.util.regex.Matcher r4 = r5.matcher(r4)     // Catch:{ all -> 0x01a6 }
            java.lang.String r5 = "redirect_uri\":\"***\""
            java.lang.String r4 = r4.replaceAll(r5)     // Catch:{ all -> 0x01a6 }
        L_0x013c:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a6 }
            r5.<init>()     // Catch:{ all -> 0x01a6 }
            java.lang.String r6 = "HTTP response status="
            r5.append(r6)     // Catch:{ all -> 0x01a6 }
            r5.append(r2)     // Catch:{ all -> 0x01a6 }
            java.lang.String r6 = " payload="
            r5.append(r6)     // Catch:{ all -> 0x01a6 }
            r5.append(r4)     // Catch:{ all -> 0x01a6 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x01a6 }
            com.microsoft.appcenter.utils.AppCenterLog.verbose(r0, r4)     // Catch:{ all -> 0x01a6 }
        L_0x0158:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x01a6 }
            r0.<init>()     // Catch:{ all -> 0x01a6 }
            java.util.Map r4 = r1.getHeaderFields()     // Catch:{ all -> 0x01a6 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ all -> 0x01a6 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x01a6 }
        L_0x0169:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x01a6 }
            if (r5 == 0) goto L_0x018f
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x01a6 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ all -> 0x01a6 }
            java.lang.Object r6 = r5.getKey()     // Catch:{ all -> 0x01a6 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x01a6 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x01a6 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x01a6 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x01a6 }
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x01a6 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x01a6 }
            r0.put(r6, r5)     // Catch:{ all -> 0x01a6 }
            goto L_0x0169
        L_0x018f:
            com.microsoft.appcenter.http.HttpResponse r4 = new com.microsoft.appcenter.http.HttpResponse     // Catch:{ all -> 0x01a6 }
            r4.<init>(r2, r3, r0)     // Catch:{ all -> 0x01a6 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r2 < r0) goto L_0x01a0
            r0 = 300(0x12c, float:4.2E-43)
            if (r2 >= r0) goto L_0x01a0
            r1.disconnect()
            return r4
        L_0x01a0:
            com.microsoft.appcenter.http.HttpException r0 = new com.microsoft.appcenter.http.HttpException     // Catch:{ all -> 0x01a6 }
            r0.<init>(r4)     // Catch:{ all -> 0x01a6 }
            throw r0     // Catch:{ all -> 0x01a6 }
        L_0x01a6:
            r0 = move-exception
            r1.disconnect()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.http.DefaultHttpClientCallTask.doHttpCall():com.microsoft.appcenter.http.HttpResponse");
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Void... voidArr) {
        TrafficStats.setThreadStatsTag(HttpUtils.THREAD_STATS_TAG);
        try {
            return doHttpCall();
        } catch (Exception e) {
            return e;
        } finally {
            TrafficStats.clearThreadStatsTag();
        }
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        this.mTracker.onStart(this);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        this.mTracker.onFinish(this);
        if (obj instanceof Exception) {
            this.mServiceCallback.onCallFailed((Exception) obj);
            return;
        }
        this.mServiceCallback.onCallSucceeded((HttpResponse) obj);
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Object obj) {
        if ((obj instanceof HttpResponse) || (obj instanceof HttpException)) {
            onPostExecute(obj);
        } else {
            this.mTracker.onFinish(this);
        }
    }
}
