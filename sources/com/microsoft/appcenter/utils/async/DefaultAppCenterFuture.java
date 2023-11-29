package com.microsoft.appcenter.utils.async;

import com.microsoft.appcenter.utils.HandlerUtils;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class DefaultAppCenterFuture<T> implements AppCenterFuture<T> {
    /* access modifiers changed from: private */
    public Collection<AppCenterConsumer<T>> mConsumers;
    private final CountDownLatch mLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public T mResult;

    /* JADX WARNING: Can't wrap try/catch for region: R(5:0|1|4|2|3) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:1:?, LOOP_START, MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T get() {
        /*
            r1 = this;
        L_0x0000:
            java.util.concurrent.CountDownLatch r0 = r1.mLatch     // Catch:{ InterruptedException -> 0x0000 }
            r0.await()     // Catch:{ InterruptedException -> 0x0000 }
            T r0 = r1.mResult
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.async.DefaultAppCenterFuture.get():java.lang.Object");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:0|1|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isDone() {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.CountDownLatch r0 = r4.mLatch     // Catch:{ InterruptedException -> 0x0000 }
            r1 = 0
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0000 }
            boolean r0 = r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x0000 }
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.async.DefaultAppCenterFuture.isDone():boolean");
    }

    public synchronized void thenAccept(final AppCenterConsumer<T> appCenterConsumer) {
        if (isDone()) {
            HandlerUtils.runOnUiThread(new Runnable() {
                public void run() {
                    appCenterConsumer.accept(DefaultAppCenterFuture.this.mResult);
                }
            });
        } else {
            if (this.mConsumers == null) {
                this.mConsumers = new LinkedList();
            }
            this.mConsumers.add(appCenterConsumer);
        }
    }

    public synchronized void complete(final T t) {
        if (!isDone()) {
            this.mResult = t;
            this.mLatch.countDown();
            if (this.mConsumers != null) {
                HandlerUtils.runOnUiThread(new Runnable() {
                    public void run() {
                        for (AppCenterConsumer accept : DefaultAppCenterFuture.this.mConsumers) {
                            accept.accept(t);
                        }
                        Collection unused = DefaultAppCenterFuture.this.mConsumers = null;
                    }
                });
            }
        }
    }
}
