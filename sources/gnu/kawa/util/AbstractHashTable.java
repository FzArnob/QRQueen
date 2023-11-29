package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractHashTable<Entry extends Map.Entry<K, V>, K, V> extends AbstractMap<K, V> {
    public static final int DEFAULT_INITIAL_SIZE = 64;
    protected int mask;
    protected int num_bindings;
    protected Entry[] table;

    /* access modifiers changed from: protected */
    public abstract Entry[] allocEntries(int i);

    /* access modifiers changed from: protected */
    public abstract int getEntryHashCode(Entry entry);

    /* access modifiers changed from: protected */
    public abstract Entry getEntryNext(Entry entry);

    /* access modifiers changed from: protected */
    public abstract Entry makeEntry(K k, int i, V v);

    /* access modifiers changed from: protected */
    public abstract void setEntryNext(Entry entry, Entry entry2);

    public AbstractHashTable() {
        this(64);
    }

    public AbstractHashTable(int i) {
        int i2 = 4;
        while (true) {
            int i3 = 1 << i2;
            if (i > i3) {
                i2++;
            } else {
                this.table = allocEntries(i3);
                this.mask = i3 - 1;
                return;
            }
        }
    }

    public int hash(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* access modifiers changed from: protected */
    public int hashToIndex(int i) {
        return (i ^ (i >>> 15)) & this.mask;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj, int i, Entry entry) {
        return getEntryHashCode(entry) == i && matches(entry.getKey(), obj);
    }

    /* access modifiers changed from: protected */
    public boolean matches(K k, Object obj) {
        return k == obj || (k != null && k.equals(obj));
    }

    public V get(Object obj) {
        return get(obj, (Object) null);
    }

    public Entry getNode(Object obj) {
        int hash = hash(obj);
        Entry entry = this.table[hashToIndex(hash)];
        while (entry != null) {
            if (matches(obj, hash, entry)) {
                return entry;
            }
            entry = getEntryNext(entry);
        }
        return null;
    }

    public V get(Object obj, V v) {
        Map.Entry node = getNode(obj);
        return node == null ? v : node.getValue();
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        int i = length * 2;
        Entry[] allocEntries = allocEntries(i);
        this.table = allocEntries;
        this.mask = i - 1;
        while (true) {
            length--;
            if (length >= 0) {
                Entry entry = entryArr[length];
                if (entry != null && getEntryNext(entry) != null) {
                    Entry entry2 = null;
                    while (true) {
                        Entry entryNext = getEntryNext(entry);
                        setEntryNext(entry, entry2);
                        if (entryNext == null) {
                            break;
                        }
                        entry2 = entry;
                        entry = entryNext;
                    }
                }
                while (entry != null) {
                    Entry entryNext2 = getEntryNext(entry);
                    int hashToIndex = hashToIndex(getEntryHashCode(entry));
                    setEntryNext(entry, allocEntries[hashToIndex]);
                    allocEntries[hashToIndex] = entry;
                    entry = entryNext2;
                }
            } else {
                return;
            }
        }
    }

    public V put(K k, V v) {
        return put(k, hash(k), v);
    }

    public V put(K k, int i, V v) {
        int hashToIndex = hashToIndex(i);
        Entry entry = this.table[hashToIndex];
        Entry entry2 = entry;
        while (entry2 != null) {
            if (matches(k, i, entry2)) {
                V value = entry2.getValue();
                entry2.setValue(v);
                return value;
            }
            entry2 = getEntryNext(entry2);
        }
        int i2 = this.num_bindings + 1;
        this.num_bindings = i2;
        if (i2 >= this.table.length) {
            rehash();
            hashToIndex = hashToIndex(i);
            entry = this.table[hashToIndex];
        }
        Entry makeEntry = makeEntry(k, i, v);
        setEntryNext(makeEntry, entry);
        this.table[hashToIndex] = makeEntry;
        return null;
    }

    public V remove(Object obj) {
        int hash = hash(obj);
        int hashToIndex = hashToIndex(hash);
        Entry entry = this.table[hashToIndex];
        Entry entry2 = null;
        while (entry != null) {
            Entry entryNext = getEntryNext(entry);
            if (matches(obj, hash, entry)) {
                if (entry2 == null) {
                    this.table[hashToIndex] = entryNext;
                } else {
                    setEntryNext(entry2, entryNext);
                }
                this.num_bindings--;
                return entry.getValue();
            }
            entry2 = entry;
            entry = entryNext;
        }
        return null;
    }

    public void clear() {
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                Entry entry = entryArr[length];
                while (entry != null) {
                    Entry entryNext = getEntryNext(entry);
                    setEntryNext(entry, (Entry) null);
                    entry = entryNext;
                }
                entryArr[length] = null;
            } else {
                this.num_bindings = 0;
                return;
            }
        }
    }

    public int size() {
        return this.num_bindings;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return new AbstractEntrySet(this);
    }

    static class AbstractEntrySet<Entry extends Map.Entry<K, V>, K, V> extends AbstractSet<Entry> {
        AbstractHashTable<Entry, K, V> htable;

        public AbstractEntrySet(AbstractHashTable<Entry, K, V> abstractHashTable) {
            this.htable = abstractHashTable;
        }

        public int size() {
            return this.htable.size();
        }

        public Iterator<Entry> iterator() {
            return new Iterator<Entry>() {
                int curIndex = -1;
                Entry currentEntry;
                Entry nextEntry;
                int nextIndex;
                Entry previousEntry;

                public boolean hasNext() {
                    if (this.curIndex < 0) {
                        int length = AbstractEntrySet.this.htable.table.length;
                        this.nextIndex = length;
                        this.curIndex = length;
                        advance();
                    }
                    return this.nextEntry != null;
                }

                private void advance() {
                    while (this.nextEntry == null) {
                        int i = this.nextIndex - 1;
                        this.nextIndex = i;
                        if (i >= 0) {
                            this.nextEntry = AbstractEntrySet.this.htable.table[this.nextIndex];
                        } else {
                            return;
                        }
                    }
                }

                public Entry next() {
                    Entry entry = this.nextEntry;
                    if (entry != null) {
                        this.previousEntry = this.currentEntry;
                        this.currentEntry = entry;
                        this.curIndex = this.nextIndex;
                        this.nextEntry = AbstractEntrySet.this.htable.getEntryNext(this.currentEntry);
                        advance();
                        return this.currentEntry;
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    Entry entry = this.previousEntry;
                    if (entry != this.currentEntry) {
                        if (entry == null) {
                            AbstractEntrySet.this.htable.table[this.curIndex] = this.nextEntry;
                        } else {
                            AbstractEntrySet.this.htable.setEntryNext(this.previousEntry, this.nextEntry);
                        }
                        AbstractHashTable<Entry, K, V> abstractHashTable = AbstractEntrySet.this.htable;
                        abstractHashTable.num_bindings--;
                        this.previousEntry = this.currentEntry;
                        return;
                    }
                    throw new IllegalStateException();
                }
            };
        }
    }
}
