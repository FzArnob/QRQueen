package gnu.mapping;

import java.lang.ref.WeakReference;

public class Table2D {
    private static Table2D instance = new Table2D();
    int log2Size;
    int mask;
    int num_bindings;
    Entry[] table;

    public static final Table2D getInstance() {
        return instance;
    }

    public Table2D() {
        this(64);
    }

    public Table2D(int i) {
        this.log2Size = 4;
        while (true) {
            int i2 = this.log2Size;
            if (i > (1 << i2)) {
                this.log2Size = i2 + 1;
            } else {
                int i3 = 1 << i2;
                this.table = new Entry[i3];
                this.mask = i3 - 1;
                return;
            }
        }
    }

    public Object get(Object obj, Object obj2, Object obj3) {
        Entry lookup = lookup(obj, obj2, System.identityHashCode(obj), System.identityHashCode(obj2), false);
        return (lookup == null || lookup.value == lookup) ? obj3 : lookup.value;
    }

    public boolean isBound(Object obj, Object obj2) {
        Entry lookup = lookup(obj, obj2, System.identityHashCode(obj), System.identityHashCode(obj2), false);
        return (lookup == null || lookup.value == lookup) ? false : true;
    }

    public Object put(Object obj, Object obj2, Object obj3) {
        Entry lookup = lookup(obj, obj2, System.identityHashCode(obj), System.identityHashCode(obj2), true);
        Object value = lookup.getValue();
        lookup.value = obj3;
        return value;
    }

    public Object remove(Object obj, Object obj2) {
        return remove(obj, obj2, System.identityHashCode(obj) ^ System.identityHashCode(obj2));
    }

    public Object remove(Object obj, Object obj2, int i) {
        return remove(obj, obj2, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0016, code lost:
        r2 = ((java.lang.ref.WeakReference) r2).get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object remove(java.lang.Object r10, java.lang.Object r11, int r12, int r13) {
        /*
            r9 = this;
            r12 = r12 ^ r13
            int r13 = r9.mask
            r12 = r12 & r13
            gnu.mapping.Entry[] r13 = r9.table
            r13 = r13[r12]
            r0 = 0
            r1 = r0
        L_0x000a:
            if (r13 == 0) goto L_0x0055
            java.lang.Object r2 = r13.key1
            java.lang.Object r3 = r13.key2
            boolean r4 = r2 instanceof java.lang.ref.WeakReference
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0020
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            java.lang.Object r2 = r2.get()
            if (r2 != 0) goto L_0x0020
            r4 = 1
            goto L_0x0021
        L_0x0020:
            r4 = 0
        L_0x0021:
            boolean r7 = r3 instanceof java.lang.ref.WeakReference
            if (r7 == 0) goto L_0x0030
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            java.lang.Object r3 = r3.get()
            if (r3 != 0) goto L_0x002f
            r4 = 1
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            gnu.mapping.Entry r7 = r13.chain
            java.lang.Object r8 = r13.value
            if (r2 != r10) goto L_0x0039
            if (r3 != r11) goto L_0x0039
            r6 = 1
        L_0x0039:
            if (r4 != 0) goto L_0x0043
            if (r6 == 0) goto L_0x003e
            goto L_0x0043
        L_0x003e:
            if (r6 == 0) goto L_0x0041
            return r8
        L_0x0041:
            r1 = r13
            goto L_0x0053
        L_0x0043:
            if (r1 != 0) goto L_0x004a
            gnu.mapping.Entry[] r2 = r9.table
            r2[r12] = r7
            goto L_0x004c
        L_0x004a:
            r1.chain = r7
        L_0x004c:
            int r2 = r9.num_bindings
            int r2 = r2 - r5
            r9.num_bindings = r2
            r13.value = r13
        L_0x0053:
            r13 = r7
            goto L_0x000a
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.Table2D.remove(java.lang.Object, java.lang.Object, int, int):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r7 = ((java.lang.ref.WeakReference) r7).get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rehash() {
        /*
            r11 = this;
            gnu.mapping.Entry[] r0 = r11.table
            int r1 = r0.length
            int r2 = r1 * 2
            gnu.mapping.Entry[] r3 = new gnu.mapping.Entry[r2]
            r4 = 1
            int r2 = r2 - r4
            r5 = 0
            r11.num_bindings = r5
        L_0x000c:
            int r1 = r1 + -1
            if (r1 < 0) goto L_0x0054
            r6 = r0[r1]
        L_0x0012:
            if (r6 == 0) goto L_0x000c
            java.lang.Object r7 = r6.key1
            java.lang.Object r8 = r6.key2
            boolean r9 = r7 instanceof java.lang.ref.WeakReference
            if (r9 == 0) goto L_0x0026
            java.lang.ref.WeakReference r7 = (java.lang.ref.WeakReference) r7
            java.lang.Object r7 = r7.get()
            if (r7 != 0) goto L_0x0026
            r9 = 1
            goto L_0x0027
        L_0x0026:
            r9 = 0
        L_0x0027:
            boolean r10 = r8 instanceof java.lang.ref.WeakReference
            if (r10 == 0) goto L_0x0036
            java.lang.ref.WeakReference r8 = (java.lang.ref.WeakReference) r8
            java.lang.Object r8 = r8.get()
            if (r8 != 0) goto L_0x0035
            r9 = 1
            goto L_0x0036
        L_0x0035:
            r9 = 0
        L_0x0036:
            gnu.mapping.Entry r10 = r6.chain
            if (r9 == 0) goto L_0x003d
            r6.value = r6
            goto L_0x0052
        L_0x003d:
            int r7 = java.lang.System.identityHashCode(r7)
            int r8 = java.lang.System.identityHashCode(r8)
            r7 = r7 ^ r8
            r7 = r7 & r2
            r8 = r3[r7]
            r6.chain = r8
            r3[r7] = r6
            int r6 = r11.num_bindings
            int r6 = r6 + r4
            r11.num_bindings = r6
        L_0x0052:
            r6 = r10
            goto L_0x0012
        L_0x0054:
            r11.table = r3
            int r0 = r11.log2Size
            int r0 = r0 + r4
            r11.log2Size = r0
            r11.mask = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.Table2D.rehash():void");
    }

    /* access modifiers changed from: protected */
    public Entry lookup(Object obj, Object obj2, int i, int i2, boolean z) {
        int i3 = (i ^ i2) & this.mask;
        Entry entry = this.table[i3];
        Entry entry2 = entry;
        Entry entry3 = null;
        while (entry2 != null) {
            Object obj3 = entry2.key1;
            Object obj4 = entry2.key2;
            boolean z2 = false;
            if ((obj3 instanceof WeakReference) && (obj3 = ((WeakReference) obj3).get()) == null) {
                z2 = true;
            }
            if (obj4 instanceof WeakReference) {
                obj4 = ((WeakReference) obj4).get();
                z2 = true;
            }
            Entry entry4 = entry2.chain;
            if (z2) {
                if (entry3 == null) {
                    this.table[i3] = entry4;
                } else {
                    entry3.chain = entry4;
                }
                this.num_bindings--;
                entry2.value = entry2;
            } else if (obj3 == obj && obj4 == obj2) {
                return entry2;
            } else {
                entry3 = entry2;
            }
            entry2 = entry4;
        }
        if (!z) {
            return null;
        }
        Entry entry5 = new Entry();
        Object wrapReference = wrapReference(obj);
        Object wrapReference2 = wrapReference(obj2);
        entry5.key1 = wrapReference;
        entry5.key2 = wrapReference2;
        this.num_bindings++;
        entry5.chain = entry;
        this.table[i3] = entry5;
        entry5.value = entry5;
        return entry5;
    }

    /* access modifiers changed from: protected */
    public Object wrapReference(Object obj) {
        return (obj == null || (obj instanceof Symbol)) ? obj : new WeakReference(obj);
    }
}
