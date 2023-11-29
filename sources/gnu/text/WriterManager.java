package gnu.text;

import java.io.Writer;

public class WriterManager implements Runnable {
    public static final WriterManager instance = new WriterManager();
    WriterRef first;

    public synchronized WriterRef register(Writer writer) {
        WriterRef writerRef;
        writerRef = new WriterRef(writer);
        WriterRef writerRef2 = this.first;
        if (writerRef2 != null) {
            writerRef.next = writerRef2.next;
            writerRef2.prev = writerRef;
        }
        this.first = writerRef;
        return writerRef;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void unregister(java.lang.Object r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            gnu.text.WriterRef r3 = (gnu.text.WriterRef) r3     // Catch:{ all -> 0x001b }
            gnu.text.WriterRef r0 = r3.next     // Catch:{ all -> 0x001b }
            gnu.text.WriterRef r1 = r3.prev     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000f
            r0.prev = r1     // Catch:{ all -> 0x001b }
        L_0x000f:
            if (r1 == 0) goto L_0x0013
            r1.next = r0     // Catch:{ all -> 0x001b }
        L_0x0013:
            gnu.text.WriterRef r1 = r2.first     // Catch:{ all -> 0x001b }
            if (r3 != r1) goto L_0x0019
            r2.first = r0     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r2)
            return
        L_0x001b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.WriterManager.unregister(java.lang.Object):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:4|(2:6|7)|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void run() {
        /*
            r2 = this;
            monitor-enter(r2)
            gnu.text.WriterRef r0 = r2.first     // Catch:{ all -> 0x0018 }
        L_0x0003:
            if (r0 == 0) goto L_0x0013
            java.lang.Object r1 = r0.get()     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0010
            java.io.Writer r1 = (java.io.Writer) r1     // Catch:{ Exception -> 0x0010 }
            r1.close()     // Catch:{ Exception -> 0x0010 }
        L_0x0010:
            gnu.text.WriterRef r0 = r0.next     // Catch:{ all -> 0x0018 }
            goto L_0x0003
        L_0x0013:
            r0 = 0
            r2.first = r0     // Catch:{ all -> 0x0018 }
            monitor-exit(r2)
            return
        L_0x0018:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.WriterManager.run():void");
    }

    public boolean registerShutdownHook() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.getClass().getDeclaredMethod("addShutdownHook", new Class[]{Thread.class}).invoke(runtime, new Object[]{new Thread(this)});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
