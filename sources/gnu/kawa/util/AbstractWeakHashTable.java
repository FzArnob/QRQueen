package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class AbstractWeakHashTable<K, V> extends AbstractHashTable<WEntry<K, V>, K, V> {
    ReferenceQueue<V> rqueue = new ReferenceQueue<>();

    /* access modifiers changed from: protected */
    public abstract K getKeyFromValue(V v);

    /* access modifiers changed from: protected */
    public boolean valuesEqual(V v, V v2) {
        return v == v2;
    }

    public AbstractWeakHashTable() {
        super(64);
    }

    public AbstractWeakHashTable(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public int getEntryHashCode(WEntry<K, V> wEntry) {
        return wEntry.hash;
    }

    /* access modifiers changed from: protected */
    public WEntry<K, V> getEntryNext(WEntry<K, V> wEntry) {
        return wEntry.next;
    }

    /* access modifiers changed from: protected */
    public void setEntryNext(WEntry<K, V> wEntry, WEntry<K, V> wEntry2) {
        wEntry.next = wEntry2;
    }

    /* access modifiers changed from: protected */
    public WEntry<K, V>[] allocEntries(int i) {
        return (WEntry[]) new WEntry[i];
    }

    /* access modifiers changed from: protected */
    public V getValueIfMatching(WEntry<K, V> wEntry, Object obj) {
        V value = wEntry.getValue();
        if (value == null || !matches(getKeyFromValue(value), obj)) {
            return null;
        }
        return value;
    }

    public V get(Object obj, V v) {
        cleanup();
        return super.get(obj, v);
    }

    public int hash(Object obj) {
        return System.identityHashCode(obj);
    }

    /* access modifiers changed from: protected */
    public WEntry<K, V> makeEntry(K k, int i, V v) {
        return new WEntry<>(v, this, i);
    }

    public V put(K k, V v) {
        cleanup();
        int hash = hash(k);
        int hashToIndex = hashToIndex(hash);
        WEntry wEntry = ((WEntry[]) this.table)[hashToIndex];
        WEntry wEntry2 = wEntry;
        V v2 = null;
        WEntry wEntry3 = null;
        while (wEntry2 != null) {
            V value = wEntry2.getValue();
            if (value == v) {
                return value;
            }
            WEntry wEntry4 = wEntry2.next;
            if (value == null || !valuesEqual(value, v)) {
                wEntry3 = wEntry2;
            } else {
                if (wEntry3 == null) {
                    ((WEntry[]) this.table)[hashToIndex] = wEntry4;
                } else {
                    wEntry3.next = wEntry4;
                }
                v2 = value;
            }
            wEntry2 = wEntry4;
        }
        int i = this.num_bindings + 1;
        this.num_bindings = i;
        if (i >= ((WEntry[]) this.table).length) {
            rehash();
            hashToIndex = hashToIndex(hash);
            wEntry = ((WEntry[]) this.table)[hashToIndex];
        }
        WEntry makeEntry = makeEntry((Object) null, hash, v);
        makeEntry.next = wEntry;
        ((WEntry[]) this.table)[hashToIndex] = makeEntry;
        return v2;
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        cleanup(this, this.rqueue);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.ref.ReferenceQueue<?>, java.lang.ref.ReferenceQueue] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <Entry extends java.util.Map.Entry<K, V>, K, V> void cleanup(gnu.kawa.util.AbstractHashTable<Entry, ?, ?> r6, java.lang.ref.ReferenceQueue<?> r7) {
        /*
        L_0x0000:
            java.lang.ref.Reference r0 = r7.poll()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            int r1 = r6.getEntryHashCode(r0)
            int r1 = r6.hashToIndex(r1)
            r2 = 0
            Entry[] r3 = r6.table
            r3 = r3[r1]
            r5 = r3
            r3 = r2
            r2 = r5
        L_0x0019:
            if (r2 == 0) goto L_0x002f
            java.util.Map$Entry r4 = r6.getEntryNext(r2)
            if (r2 != r0) goto L_0x002c
            if (r3 != 0) goto L_0x0028
            Entry[] r0 = r6.table
            r0[r1] = r4
            goto L_0x002f
        L_0x0028:
            r6.setEntryNext(r3, r4)
            goto L_0x002f
        L_0x002c:
            r3 = r2
            r2 = r4
            goto L_0x0019
        L_0x002f:
            int r0 = r6.num_bindings
            int r0 = r0 + -1
            r6.num_bindings = r0
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.AbstractWeakHashTable.cleanup(gnu.kawa.util.AbstractHashTable, java.lang.ref.ReferenceQueue):void");
    }

    public static class WEntry<K, V> extends WeakReference<V> implements Map.Entry<K, V> {
        public int hash;
        AbstractWeakHashTable<K, V> htable;
        public WEntry next;

        public WEntry(V v, AbstractWeakHashTable<K, V> abstractWeakHashTable, int i) {
            super(v, abstractWeakHashTable.rqueue);
            this.htable = abstractWeakHashTable;
            this.hash = i;
        }

        public K getKey() {
            Object obj = get();
            if (obj == null) {
                return null;
            }
            return this.htable.getKeyFromValue(obj);
        }

        public V getValue() {
            return get();
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }
}
