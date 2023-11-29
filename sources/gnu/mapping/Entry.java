package gnu.mapping;

import java.lang.ref.WeakReference;

/* compiled from: Table2D */
class Entry {
    Entry chain;
    Object key1;
    Object key2;
    Object value;

    Entry() {
    }

    public Object getKey1() {
        Object obj = this.key1;
        return obj instanceof WeakReference ? ((WeakReference) obj).get() : obj;
    }

    public Object getKey2() {
        Object obj = this.key2;
        return obj instanceof WeakReference ? ((WeakReference) obj).get() : obj;
    }

    public boolean matches(Object obj, Object obj2) {
        return obj == getKey1() && obj2 == getKey2();
    }

    public Object getValue() {
        Object obj = this.value;
        if (obj == this) {
            return null;
        }
        return obj;
    }
}
