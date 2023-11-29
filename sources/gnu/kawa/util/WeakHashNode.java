package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public class WeakHashNode<K, V> extends WeakReference<K> implements Map.Entry<K, V> {
    public int hash;
    public WeakHashNode<K, V> next;
    public V value;

    public WeakHashNode(K k, ReferenceQueue<K> referenceQueue, int i) {
        super(k, referenceQueue);
        this.hash = i;
    }

    public K getKey() {
        return get();
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }
}
