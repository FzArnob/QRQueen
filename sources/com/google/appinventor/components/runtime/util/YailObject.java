package com.google.appinventor.components.runtime.util;

import java.util.Iterator;

public interface YailObject<T> extends Iterable<T> {
    Object getObject(int i);

    boolean isEmpty();

    Iterator<T> iterator();

    int size();
}
