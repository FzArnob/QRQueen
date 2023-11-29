package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;

public class WeakIdentityHashMap<K, V> extends AbstractHashTable<WeakHashNode<K, V>, K, V> {
    ReferenceQueue<K> rqueue = new ReferenceQueue<>();

    /* access modifiers changed from: protected */
    public boolean matches(K k, Object obj) {
        return k == obj;
    }

    public WeakIdentityHashMap() {
        super(64);
    }

    public WeakIdentityHashMap(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public int getEntryHashCode(WeakHashNode<K, V> weakHashNode) {
        return weakHashNode.hash;
    }

    /* access modifiers changed from: protected */
    public WeakHashNode<K, V> getEntryNext(WeakHashNode<K, V> weakHashNode) {
        return weakHashNode.next;
    }

    /* access modifiers changed from: protected */
    public void setEntryNext(WeakHashNode<K, V> weakHashNode, WeakHashNode<K, V> weakHashNode2) {
        weakHashNode.next = weakHashNode2;
    }

    /* access modifiers changed from: protected */
    public WeakHashNode<K, V>[] allocEntries(int i) {
        return (WeakHashNode[]) new WeakHashNode[i];
    }

    public int hash(Object obj) {
        return System.identityHashCode(obj);
    }

    /* access modifiers changed from: protected */
    public WeakHashNode<K, V> makeEntry(K k, int i, V v) {
        WeakHashNode<K, V> weakHashNode = new WeakHashNode<>(k, this.rqueue, i);
        weakHashNode.value = v;
        return weakHashNode;
    }

    public V get(Object obj, V v) {
        cleanup();
        return super.get(obj, v);
    }

    public V put(K k, int i, V v) {
        cleanup();
        return super.put(k, i, v);
    }

    public V remove(Object obj) {
        cleanup();
        return super.remove(obj);
    }

    /* access modifiers changed from: package-private */
    public void cleanup() {
        AbstractWeakHashTable.cleanup(this, this.rqueue);
    }
}
