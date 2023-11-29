package gnu.kawa.util;

public class IdentityHashTable<K, V> extends GeneralHashTable<K, V> {
    public boolean matches(K k, Object obj) {
        return k == obj;
    }

    public IdentityHashTable() {
    }

    public IdentityHashTable(int i) {
        super(i);
    }

    public int hash(Object obj) {
        return System.identityHashCode(obj);
    }
}
