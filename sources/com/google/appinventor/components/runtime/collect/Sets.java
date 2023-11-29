package com.google.appinventor.components.runtime.collect;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sets {
    public static <K> HashSet<K> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(((eArr.length << 2) / 3) + 1);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }

    public static <E> SortedSet<E> newSortedSet(E... eArr) {
        TreeSet treeSet = new TreeSet();
        Collections.addAll(treeSet, eArr);
        return treeSet;
    }
}
