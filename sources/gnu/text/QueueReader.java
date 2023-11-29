package gnu.text;

import gnu.lists.CharSeq;
import java.io.Reader;

public class QueueReader extends Reader implements Appendable {
    boolean EOFseen;
    char[] buffer;
    int limit;
    int mark;
    int pos;
    int readAheadLimit;

    public void checkAvailable() {
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized void mark(int i) {
        this.readAheadLimit = i;
        this.mark = this.pos;
    }

    public synchronized void reset() {
        if (this.readAheadLimit > 0) {
            this.pos = this.mark;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resize(int r7) {
        /*
            r6 = this;
            int r0 = r6.limit
            int r1 = r6.pos
            int r2 = r0 - r1
            int r3 = r6.readAheadLimit
            if (r3 <= 0) goto L_0x0013
            int r4 = r6.mark
            int r5 = r1 - r4
            if (r5 > r3) goto L_0x0013
            int r2 = r0 - r4
            goto L_0x0015
        L_0x0013:
            r6.mark = r1
        L_0x0015:
            char[] r0 = r6.buffer
            int r1 = r0.length
            int r3 = r2 + r7
            if (r1 >= r3) goto L_0x0022
            int r1 = r2 * 2
            int r1 = r1 + r7
            char[] r7 = new char[r1]
            goto L_0x0023
        L_0x0022:
            r7 = r0
        L_0x0023:
            int r1 = r6.mark
            r3 = 0
            java.lang.System.arraycopy(r0, r1, r7, r3, r2)
            r6.buffer = r7
            int r7 = r6.pos
            int r0 = r6.mark
            int r7 = r7 - r0
            r6.pos = r7
            r6.mark = r3
            r6.limit = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.resize(int):void");
    }

    public QueueReader append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        return append(charSequence, 0, charSequence.length());
    }

    public synchronized QueueReader append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        int i3 = i2 - i;
        reserveSpace(i3);
        int i4 = this.limit;
        char[] cArr = this.buffer;
        if (charSequence instanceof String) {
            ((String) charSequence).getChars(i, i2, cArr, i4);
        } else if (charSequence instanceof CharSeq) {
            ((CharSeq) charSequence).getChars(i, i2, cArr, i4);
        } else {
            int i5 = i4;
            while (i < i2) {
                cArr[i5] = charSequence.charAt(i);
                i++;
                i5++;
            }
        }
        this.limit = i4 + i3;
        notifyAll();
        return this;
    }

    public void append(char[] cArr) {
        append(cArr, 0, cArr.length);
    }

    public synchronized void append(char[] cArr, int i, int i2) {
        reserveSpace(i2);
        System.arraycopy(cArr, i, this.buffer, this.limit, i2);
        this.limit += i2;
        notifyAll();
    }

    public synchronized QueueReader append(char c) {
        reserveSpace(1);
        char[] cArr = this.buffer;
        int i = this.limit;
        this.limit = i + 1;
        cArr[i] = c;
        notifyAll();
        return this;
    }

    public synchronized void appendEOF() {
        this.EOFseen = true;
    }

    /* access modifiers changed from: protected */
    public void reserveSpace(int i) {
        char[] cArr = this.buffer;
        if (cArr == null) {
            this.buffer = new char[(i + 100)];
        } else if (cArr.length < this.limit + i) {
            resize(i);
        }
    }

    public synchronized boolean ready() {
        return this.pos < this.limit || this.EOFseen;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:20:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() {
        /*
            r3 = this;
            monitor-enter(r3)
        L_0x0001:
            int r0 = r3.pos     // Catch:{ all -> 0x001f }
            int r1 = r3.limit     // Catch:{ all -> 0x001f }
            if (r0 < r1) goto L_0x0015
            boolean r0 = r3.EOFseen     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000e
            r0 = -1
            monitor-exit(r3)
            return r0
        L_0x000e:
            r3.checkAvailable()     // Catch:{ all -> 0x001f }
            r3.wait()     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x0015:
            char[] r1 = r3.buffer     // Catch:{ all -> 0x001f }
            int r2 = r0 + 1
            r3.pos = r2     // Catch:{ all -> 0x001f }
            char r0 = r1[r0]     // Catch:{ all -> 0x001f }
            monitor-exit(r3)
            return r0
        L_0x001f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.read():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001a, code lost:
        r1 = r1 - r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        if (r5 <= r1) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001d, code lost:
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        java.lang.System.arraycopy(r2.buffer, r0, r3, r4, r5);
        r2.pos += r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0029, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0006 */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0006 A[LOOP:0: B:5:0x0006->B:27:0x0006, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(char[] r3, int r4, int r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r5 != 0) goto L_0x0006
            r3 = 0
            monitor-exit(r2)
            return r3
        L_0x0006:
            int r0 = r2.pos     // Catch:{ all -> 0x002a }
            int r1 = r2.limit     // Catch:{ all -> 0x002a }
            if (r0 < r1) goto L_0x001a
            boolean r0 = r2.EOFseen     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0013
            r3 = -1
            monitor-exit(r2)
            return r3
        L_0x0013:
            r2.checkAvailable()     // Catch:{ all -> 0x002a }
            r2.wait()     // Catch:{ InterruptedException -> 0x0006 }
            goto L_0x0006
        L_0x001a:
            int r1 = r1 - r0
            if (r5 <= r1) goto L_0x001e
            r5 = r1
        L_0x001e:
            char[] r1 = r2.buffer     // Catch:{ all -> 0x002a }
            java.lang.System.arraycopy(r1, r0, r3, r4, r5)     // Catch:{ all -> 0x002a }
            int r3 = r2.pos     // Catch:{ all -> 0x002a }
            int r3 = r3 + r5
            r2.pos = r3     // Catch:{ all -> 0x002a }
            monitor-exit(r2)
            return r5
        L_0x002a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.read(char[], int, int):int");
    }

    public synchronized void close() {
        this.pos = 0;
        this.limit = 0;
        this.mark = 0;
        this.EOFseen = true;
        this.buffer = null;
    }
}
