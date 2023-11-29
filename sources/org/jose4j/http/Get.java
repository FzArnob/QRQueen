package org.jose4j.http;

import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.jose4j.lang.UncheckedJoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Get implements SimpleGet {
    private static final long MAX_RETRY_WAIT = 8000;
    private static final Logger log = LoggerFactory.getLogger((Class<?>) Get.class);
    private int connectTimeout = 20000;
    private HostnameVerifier hostnameVerifier;
    private long initialRetryWaitTime = 180;
    private boolean progressiveRetryWait = true;
    private Proxy proxy;
    private int readTimeout = 20000;
    private int responseBodySizeLimit = 524288;
    private int retries = 3;
    private SSLSocketFactory sslSocketFactory;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x000e */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x000e A[LOOP:0: B:1:0x000e->B:21:0x000e, LOOP_START, PHI: r1 
      PHI: (r1v2 int) = (r1v1 int), (r1v3 int) binds: [B:0:0x0000, B:21:0x000e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:1:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jose4j.http.SimpleResponse get(java.lang.String r10) throws java.io.IOException {
        /*
            r9 = this;
            org.slf4j.Logger r0 = log
            java.lang.String r1 = "HTTP GET of {}"
            r0.debug((java.lang.String) r1, (java.lang.Object) r10)
            java.net.URL r0 = new java.net.URL
            r0.<init>(r10)
            r10 = 0
            r1 = 0
        L_0x000e:
            java.net.Proxy r2 = r9.proxy     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            if (r2 != 0) goto L_0x0017
            java.net.URLConnection r2 = r0.openConnection()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            goto L_0x001b
        L_0x0017:
            java.net.URLConnection r2 = r0.openConnection(r2)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
        L_0x001b:
            int r3 = r9.connectTimeout     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r2.setConnectTimeout(r3)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            int r3 = r9.readTimeout     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r2.setReadTimeout(r3)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r9.setUpTls(r2)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r3 = r2
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            int r4 = r3.getResponseCode()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r5 = r3.getResponseMessage()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r4 != r6) goto L_0x0050
            java.lang.String r6 = r9.getCharset(r2)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r2 = r9.getBody(r2, r6)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.util.Map r3 = r3.getHeaderFields()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            org.jose4j.http.Response r6 = new org.jose4j.http.Response     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r6.<init>(r4, r5, r3, r2)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            org.slf4j.Logger r2 = log     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r3 = "HTTP GET of {} returned {}"
            r2.debug((java.lang.String) r3, (java.lang.Object) r0, (java.lang.Object) r6)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            return r6
        L_0x0050:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r3.<init>()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r6 = "Non 200 status code ("
            r3.append(r6)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r3.append(r4)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r4 = " "
            r3.append(r4)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r3.append(r5)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r4 = ") returned from "
            r3.append(r4)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r3.append(r0)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            java.lang.String r3 = r3.toString()     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            r2.<init>(r3)     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
            throw r2     // Catch:{ SSLHandshakeException -> 0x00b3, SSLPeerUnverifiedException -> 0x00b1, FileNotFoundException -> 0x00af, ResponseBodyTooLargeException -> 0x00ad, IOException -> 0x0077 }
        L_0x0077:
            r2 = move-exception
            r3 = 1
            int r1 = r1 + r3
            int r4 = r9.retries
            if (r1 > r4) goto L_0x00ac
            long r4 = r9.getRetryWaitTime(r1)
            org.slf4j.Logger r6 = log
            r7 = 5
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.Long r8 = java.lang.Long.valueOf(r4)
            r7[r10] = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)
            r7[r3] = r8
            r3 = 2
            int r8 = r9.retries
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7[r3] = r8
            r3 = 3
            r7[r3] = r0
            r3 = 4
            r7[r3] = r2
            java.lang.String r2 = "Waiting {}ms before retrying ({} of {}) HTTP GET of {} after failed attempt: {}"
            r6.debug((java.lang.String) r2, (java.lang.Object[]) r7)
            java.lang.Thread.sleep(r4)     // Catch:{ InterruptedException -> 0x000e }
            goto L_0x000e
        L_0x00ac:
            throw r2
        L_0x00ad:
            r10 = move-exception
            goto L_0x00b4
        L_0x00af:
            r10 = move-exception
            goto L_0x00b4
        L_0x00b1:
            r10 = move-exception
            goto L_0x00b4
        L_0x00b3:
            r10 = move-exception
        L_0x00b4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.http.Get.get(java.lang.String):org.jose4j.http.SimpleResponse");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        log.debug("read {} characters", (java.lang.Object) java.lang.Integer.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r7 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        return r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r8.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0067, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006b, code lost:
        if (r7 != null) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0071, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0072, code lost:
        r8.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0075, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getBody(java.net.URLConnection r7, java.lang.String r8) throws java.io.IOException {
        /*
            r6 = this;
            java.io.StringWriter r0 = new java.io.StringWriter
            r0.<init>()
            java.io.InputStream r7 = r7.getInputStream()
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ all -> 0x0068 }
            r1.<init>(r7, r8)     // Catch:{ all -> 0x0068 }
            r8 = 1024(0x400, float:1.435E-42)
            char[] r8 = new char[r8]     // Catch:{ all -> 0x005c }
            r2 = 0
            r3 = 0
        L_0x0014:
            r4 = -1
            int r5 = r1.read(r8)     // Catch:{ all -> 0x005c }
            if (r4 == r5) goto L_0x0044
            r0.write(r8, r2, r5)     // Catch:{ all -> 0x005c }
            int r3 = r3 + r5
            int r4 = r6.responseBodySizeLimit     // Catch:{ all -> 0x005c }
            if (r4 <= 0) goto L_0x0014
            if (r3 > r4) goto L_0x0026
            goto L_0x0014
        L_0x0026:
            org.jose4j.http.Get$ResponseBodyTooLargeException r8 = new org.jose4j.http.Get$ResponseBodyTooLargeException     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r0.<init>()     // Catch:{ all -> 0x005c }
            java.lang.String r2 = "More than "
            r0.append(r2)     // Catch:{ all -> 0x005c }
            int r2 = r6.responseBodySizeLimit     // Catch:{ all -> 0x005c }
            r0.append(r2)     // Catch:{ all -> 0x005c }
            java.lang.String r2 = " characters have been read from the response body."
            r0.append(r2)     // Catch:{ all -> 0x005c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005c }
            r8.<init>(r0)     // Catch:{ all -> 0x005c }
            throw r8     // Catch:{ all -> 0x005c }
        L_0x0044:
            org.slf4j.Logger r8 = log     // Catch:{ all -> 0x005c }
            java.lang.String r2 = "read {} characters"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x005c }
            r8.debug((java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x005c }
            r1.close()     // Catch:{ all -> 0x0068 }
            if (r7 == 0) goto L_0x0057
            r7.close()
        L_0x0057:
            java.lang.String r7 = r0.toString()
            return r7
        L_0x005c:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x005e }
        L_0x005e:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r1 = move-exception
            r8.addSuppressed(r1)     // Catch:{ all -> 0x0068 }
        L_0x0067:
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x006a }
        L_0x006a:
            r0 = move-exception
            if (r7 == 0) goto L_0x0075
            r7.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r7 = move-exception
            r8.addSuppressed(r7)
        L_0x0075:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.http.Get.getBody(java.net.URLConnection, java.lang.String):java.lang.String");
    }

    private void setUpTls(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            SSLSocketFactory sSLSocketFactory = this.sslSocketFactory;
            if (sSLSocketFactory != null) {
                httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
            }
            HostnameVerifier hostnameVerifier2 = this.hostnameVerifier;
            if (hostnameVerifier2 != null) {
                httpsURLConnection.setHostnameVerifier(hostnameVerifier2);
            }
        }
    }

    private String getCharset(URLConnection uRLConnection) {
        String str;
        String headerField = uRLConnection.getHeaderField(DefaultHttpClient.CONTENT_TYPE_KEY);
        if (headerField == null) {
            return "UTF-8";
        }
        try {
            String[] split = headerField.replace(" ", "").split(";");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    str = "UTF-8";
                    break;
                }
                String str2 = split[i];
                if (str2.startsWith("charset=")) {
                    str = str2.substring(8);
                    break;
                }
                i++;
            }
            Charset.forName(str);
            return str;
        } catch (Exception e) {
            log.debug("Unexpected problem attempted to determine the charset from the Content-Type ({}) so will default to using UTF8: {}", (Object) headerField, (Object) e);
            return "UTF-8";
        }
    }

    private long getRetryWaitTime(int i) {
        if (this.progressiveRetryWait) {
            return Math.min((long) (Math.pow(2.0d, (double) (i - 1)) * ((double) this.initialRetryWaitTime)), MAX_RETRY_WAIT);
        }
        return this.initialRetryWaitTime;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier2) {
        this.hostnameVerifier = hostnameVerifier2;
    }

    public void setTrustedCertificates(X509Certificate... x509CertificateArr) {
        setTrustedCertificates((Collection<X509Certificate>) Arrays.asList(x509CertificateArr));
    }

    public void setRetries(int i) {
        this.retries = i;
    }

    public void setProgressiveRetryWait(boolean z) {
        this.progressiveRetryWait = z;
    }

    public void setInitialRetryWaitTime(long j) {
        this.initialRetryWaitTime = j;
    }

    public void setResponseBodySizeLimit(int i) {
        this.responseBodySizeLimit = i;
    }

    public void setTrustedCertificates(Collection<X509Certificate> collection) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("PKIX");
            KeyStore instance2 = KeyStore.getInstance("jks");
            instance2.load((InputStream) null, (char[]) null);
            int i = 0;
            for (X509Certificate certificateEntry : collection) {
                StringBuilder sb = new StringBuilder();
                sb.append("alias");
                int i2 = i + 1;
                sb.append(i);
                instance2.setCertificateEntry(sb.toString(), certificateEntry);
                i = i2;
            }
            instance.init(instance2);
            TrustManager[] trustManagers = instance.getTrustManagers();
            SSLContext instance3 = SSLContext.getInstance("TLS");
            instance3.init((KeyManager[]) null, trustManagers, (SecureRandom) null);
            this.sslSocketFactory = instance3.getSocketFactory();
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new UncheckedJoseException("Unable to initialize socket factory with custom trusted  certificates.", e);
        }
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
    }

    public void setHttpProxy(Proxy proxy2) {
        this.proxy = proxy2;
    }

    private static class ResponseBodyTooLargeException extends IOException {
        public ResponseBodyTooLargeException(String str) {
            super(str);
        }
    }
}
