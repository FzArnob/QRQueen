package gnu.kawa.util;

import java.util.Map;

public class HashNode<K, V> implements Map.Entry<K, V> {
    int hash;
    K key;
    public HashNode<K, V> next;
    V value;

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public int hashCode() {
        K k = this.key;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    public HashNode(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public V get(V v) {
        return getValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof gnu.kawa.util.HashNode
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            gnu.kawa.util.HashNode r4 = (gnu.kawa.util.HashNode) r4
            K r0 = r3.key
            if (r0 != 0) goto L_0x0011
            K r0 = r4.key
            if (r0 != 0) goto L_0x0029
            goto L_0x0019
        L_0x0011:
            K r2 = r4.key
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0029
        L_0x0019:
            V r0 = r3.value
            V r4 = r4.value
            if (r0 != 0) goto L_0x0022
            if (r4 != 0) goto L_0x0029
            goto L_0x0028
        L_0x0022:
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0029
        L_0x0028:
            r1 = 1
        L_0x0029:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.HashNode.equals(java.lang.Object):boolean");
    }
}
