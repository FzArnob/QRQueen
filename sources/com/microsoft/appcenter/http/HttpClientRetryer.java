package com.microsoft.appcenter.http;

import android.os.Handler;
import android.os.Looper;
import com.microsoft.appcenter.http.HttpClient;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpClientRetryer extends HttpClientDecorator {
    static final long[] RETRY_INTERVALS = {TimeUnit.SECONDS.toMillis(10), TimeUnit.MINUTES.toMillis(5), TimeUnit.MINUTES.toMillis(20)};
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final Random mRandom;

    HttpClientRetryer(HttpClient httpClient) {
        this(httpClient, new Handler(Looper.getMainLooper()));
    }

    HttpClientRetryer(HttpClient httpClient, Handler handler) {
        super(httpClient);
        this.mRandom = new Random();
        this.mHandler = handler;
    }

    public ServiceCall callAsync(String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback) {
        RetryableCall retryableCall = new RetryableCall(this.mDecoratedApi, str, str2, map, callTemplate, serviceCallback);
        retryableCall.run();
        return retryableCall;
    }

    private class RetryableCall extends HttpClientCallDecorator {
        private int mRetryCount;

        RetryableCall(HttpClient httpClient, String str, String str2, Map<String, String> map, HttpClient.CallTemplate callTemplate, ServiceCallback serviceCallback) {
            super(httpClient, str, str2, map, callTemplate, serviceCallback);
        }

        public synchronized void cancel() {
            HttpClientRetryer.this.mHandler.removeCallbacks(this);
            super.cancel();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
            r0 = ((com.microsoft.appcenter.http.HttpException) r6).getHttpResponse().getHeaders().get("x-ms-retry-after-ms");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCallFailed(java.lang.Exception r6) {
            /*
                r5 = this;
                int r0 = r5.mRetryCount
                long[] r1 = com.microsoft.appcenter.http.HttpClientRetryer.RETRY_INTERVALS
                int r1 = r1.length
                if (r0 >= r1) goto L_0x0090
                boolean r0 = com.microsoft.appcenter.http.HttpUtils.isRecoverableError(r6)
                if (r0 == 0) goto L_0x0090
                boolean r0 = r6 instanceof com.microsoft.appcenter.http.HttpException
                r1 = 0
                if (r0 == 0) goto L_0x002d
                r0 = r6
                com.microsoft.appcenter.http.HttpException r0 = (com.microsoft.appcenter.http.HttpException) r0
                com.microsoft.appcenter.http.HttpResponse r0 = r0.getHttpResponse()
                java.util.Map r0 = r0.getHeaders()
                java.lang.String r3 = "x-ms-retry-after-ms"
                java.lang.Object r0 = r0.get(r3)
                java.lang.String r0 = (java.lang.String) r0
                if (r0 == 0) goto L_0x002d
                long r3 = java.lang.Long.parseLong(r0)
                goto L_0x002e
            L_0x002d:
                r3 = r1
            L_0x002e:
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 != 0) goto L_0x004c
                long[] r0 = com.microsoft.appcenter.http.HttpClientRetryer.RETRY_INTERVALS
                int r1 = r5.mRetryCount
                int r2 = r1 + 1
                r5.mRetryCount = r2
                r1 = r0[r1]
                r3 = 2
                long r1 = r1 / r3
                com.microsoft.appcenter.http.HttpClientRetryer r0 = com.microsoft.appcenter.http.HttpClientRetryer.this
                java.util.Random r0 = r0.mRandom
                int r3 = (int) r1
                int r0 = r0.nextInt(r3)
                long r3 = (long) r0
                long r3 = r3 + r1
            L_0x004c:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Try #"
                r0.append(r1)
                int r1 = r5.mRetryCount
                r0.append(r1)
                java.lang.String r1 = " failed and will be retried in "
                r0.append(r1)
                r0.append(r3)
                java.lang.String r1 = " ms"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                boolean r1 = r6 instanceof java.net.UnknownHostException
                if (r1 == 0) goto L_0x0081
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r0)
                java.lang.String r0 = " (UnknownHostException)"
                r1.append(r0)
                java.lang.String r0 = r1.toString()
            L_0x0081:
                java.lang.String r1 = "AppCenter"
                com.microsoft.appcenter.utils.AppCenterLog.warn(r1, r0, r6)
                com.microsoft.appcenter.http.HttpClientRetryer r6 = com.microsoft.appcenter.http.HttpClientRetryer.this
                android.os.Handler r6 = r6.mHandler
                r6.postDelayed(r5, r3)
                goto L_0x0095
            L_0x0090:
                com.microsoft.appcenter.http.ServiceCallback r0 = r5.mServiceCallback
                r0.onCallFailed(r6)
            L_0x0095:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.http.HttpClientRetryer.RetryableCall.onCallFailed(java.lang.Exception):void");
        }
    }
}
