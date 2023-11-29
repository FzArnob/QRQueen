package gnu.kawa.util;

public class GeneralHashTable<K, V> extends AbstractHashTable<HashNode<K, V>, K, V> {
    public GeneralHashTable() {
    }

    public GeneralHashTable(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public int getEntryHashCode(HashNode<K, V> hashNode) {
        return hashNode.hash;
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V> getEntryNext(HashNode<K, V> hashNode) {
        return hashNode.next;
    }

    /* access modifiers changed from: protected */
    public void setEntryNext(HashNode<K, V> hashNode, HashNode<K, V> hashNode2) {
        hashNode.next = hashNode2;
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V>[] allocEntries(int i) {
        return (HashNode[]) new HashNode[i];
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V> makeEntry(K k, int i, V v) {
        HashNode<K, V> hashNode = new HashNode<>(k, v);
        hashNode.hash = i;
        return hashNode;
    }

    public HashNode<K, V> getNode(Object obj) {
        return (HashNode) super.getNode(obj);
    }
}
