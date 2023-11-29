package gnu.lists;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractSequence {
    public int copyPos(int i) {
        return i;
    }

    public abstract int createPos(int i, boolean z);

    public int endPos() {
        return -1;
    }

    public int firstAttributePos(int i) {
        return 0;
    }

    public int firstChildPos(int i) {
        return 0;
    }

    public abstract Object get(int i);

    public Object getAttribute(int i) {
        return null;
    }

    public int getAttributeLength() {
        return 0;
    }

    public int getLowBound(int i) {
        return 0;
    }

    public String getNextTypeName(int i) {
        return null;
    }

    public Object getNextTypeObject(int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean gotoAttributesStart(TreePosition treePosition) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return (i & 1) != 0;
    }

    public int rank() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void releasePos(int i) {
    }

    public abstract int size();

    public int startPos() {
        return 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int getEffectiveIndex(int[] iArr) {
        return iArr[0];
    }

    public Object get(int[] iArr) {
        return get(iArr[0]);
    }

    public Object set(int[] iArr, Object obj) {
        return set(iArr[0], obj);
    }

    public int getSize(int i) {
        if (i == 0) {
            return size();
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public RuntimeException unsupported(String str) {
        return unsupportedException(getClass().getName() + " does not implement " + str);
    }

    public static RuntimeException unsupportedException(String str) {
        return new UnsupportedOperationException(str);
    }

    public Object set(int i, Object obj) {
        throw unsupported("set");
    }

    public void fill(Object obj) {
        int startPos = startPos();
        while (true) {
            startPos = nextPos(startPos);
            if (startPos != 0) {
                setPosPrevious(startPos, obj);
            } else {
                return;
            }
        }
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int copyPos = copyPos(i);
        while (compare(copyPos, i2) < 0) {
            setPosNext(copyPos, obj);
            copyPos = nextPos(copyPos);
        }
        releasePos(copyPos);
    }

    public void fill(int i, int i2, Object obj) {
        int createPos = createPos(i, false);
        int createPos2 = createPos(i2, true);
        while (compare(createPos, createPos2) < 0) {
            setPosNext(createPos, obj);
            createPos = nextPos(createPos);
        }
        releasePos(createPos);
        releasePos(createPos2);
    }

    public int indexOf(Object obj) {
        int startPos = startPos();
        int i = 0;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                return -1;
            }
            Object posPrevious = getPosPrevious(startPos);
            if (obj == null) {
                if (posPrevious == null) {
                    break;
                }
                i++;
            } else if (obj.equals(posPrevious)) {
                break;
            } else {
                i++;
            }
        }
        releasePos(startPos);
        return i;
    }

    public int lastIndexOf(Object obj) {
        int size = size();
        while (true) {
            size--;
            if (size < 0) {
                return -1;
            }
            Object obj2 = get(size);
            if (obj == null) {
                if (obj2 == null) {
                    break;
                }
            } else if (obj.equals(obj2)) {
                break;
            }
        }
        return size;
    }

    public int nextMatching(int i, ItemPredicate itemPredicate, int i2, boolean z) {
        if (!z) {
            while (compare(i, i2) < 0) {
                i = nextPos(i);
                if (itemPredicate.isInstancePos(this, i)) {
                    return i;
                }
            }
            return 0;
        }
        throw unsupported("nextMatching with descend");
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final Enumeration elements() {
        return getIterator();
    }

    public final SeqPosition getIterator() {
        return getIterator(0);
    }

    public SeqPosition getIterator(int i) {
        return new SeqPosition(this, i, false);
    }

    public SeqPosition getIteratorAtPos(int i) {
        return new SeqPosition(this, copyPos(i));
    }

    public final Iterator iterator() {
        return getIterator();
    }

    public final ListIterator listIterator() {
        return getIterator(0);
    }

    public final ListIterator listIterator(int i) {
        return getIterator(i);
    }

    /* access modifiers changed from: protected */
    public int addPos(int i, Object obj) {
        throw unsupported("addPos");
    }

    public boolean add(Object obj) {
        addPos(endPos(), obj);
        return true;
    }

    public void add(int i, Object obj) {
        int createPos = createPos(i, false);
        addPos(createPos, obj);
        releasePos(createPos);
    }

    public boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    public boolean addAll(int i, Collection collection) {
        boolean z = false;
        int createPos = createPos(i, false);
        for (Object addPos : collection) {
            createPos = addPos(createPos, addPos);
            z = true;
        }
        releasePos(createPos);
        return z;
    }

    public void removePos(int i, int i2) {
        int createRelativePos = createRelativePos(i, i2, false);
        if (i2 >= 0) {
            removePosRange(i, createRelativePos);
        } else {
            removePosRange(createRelativePos, i);
        }
        releasePos(createRelativePos);
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int i, int i2) {
        throw unsupported("removePosRange");
    }

    public Object remove(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int createPos = createPos(i, false);
        Object posNext = getPosNext(createPos);
        removePos(createPos, 1);
        releasePos(createPos);
        return posNext;
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        int createPos = createPos(indexOf, false);
        removePos(createPos, 1);
        releasePos(createPos);
        return true;
    }

    public boolean removeAll(Collection collection) {
        int startPos = startPos();
        boolean z = false;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                return z;
            }
            if (collection.contains(getPosPrevious(startPos))) {
                removePos(startPos, -1);
                z = true;
            }
        }
    }

    public boolean retainAll(Collection collection) {
        int startPos = startPos();
        boolean z = false;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                return z;
            }
            if (!collection.contains(getPosPrevious(startPos))) {
                removePos(startPos, -1);
                z = true;
            }
        }
    }

    public void clear() {
        removePos(startPos(), endPos());
    }

    public int createRelativePos(int i, int i2, boolean z) {
        return createPos(nextIndex(i) + i2, z);
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int i, int i2) {
        return nextIndex(i) - nextIndex(i2);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        return getIndexDifference(i, startPos());
    }

    /* access modifiers changed from: protected */
    public int fromEndIndex(int i) {
        return size() - nextIndex(i);
    }

    /* access modifiers changed from: protected */
    public int getContainingSequenceSize(int i) {
        return size();
    }

    public boolean hasNext(int i) {
        return nextIndex(i) != size();
    }

    public int getNextKind(int i) {
        return hasNext(i) ? 32 : 0;
    }

    /* access modifiers changed from: protected */
    public boolean hasPrevious(int i) {
        return nextIndex(i) != 0;
    }

    public int nextPos(int i) {
        if (!hasNext(i)) {
            return 0;
        }
        int createRelativePos = createRelativePos(i, 1, true);
        releasePos(i);
        return createRelativePos;
    }

    public int previousPos(int i) {
        if (!hasPrevious(i)) {
            return 0;
        }
        int createRelativePos = createRelativePos(i, -1, false);
        releasePos(i);
        return createRelativePos;
    }

    public final boolean gotoChildrenStart(TreePosition treePosition) {
        int firstChildPos = firstChildPos(treePosition.getPos());
        if (firstChildPos == 0) {
            return false;
        }
        treePosition.push(this, firstChildPos);
        return true;
    }

    public int firstChildPos(int i, ItemPredicate itemPredicate) {
        int firstChildPos = firstChildPos(i);
        if (firstChildPos == 0) {
            return 0;
        }
        if (itemPredicate.isInstancePos(this, firstChildPos)) {
            return firstChildPos;
        }
        return nextMatching(firstChildPos, itemPredicate, endPos(), false);
    }

    public int parentPos(int i) {
        return endPos();
    }

    /* access modifiers changed from: protected */
    public boolean gotoParent(TreePosition treePosition) {
        if (treePosition.depth < 0) {
            return false;
        }
        treePosition.pop();
        return true;
    }

    public Object getPosNext(int i) {
        if (!hasNext(i)) {
            return Sequence.eofValue;
        }
        return get(nextIndex(i));
    }

    public Object getPosPrevious(int i) {
        int nextIndex = nextIndex(i);
        if (nextIndex <= 0) {
            return Sequence.eofValue;
        }
        return get(nextIndex - 1);
    }

    /* access modifiers changed from: protected */
    public void setPosNext(int i, Object obj) {
        int nextIndex = nextIndex(i);
        if (nextIndex < size()) {
            set(nextIndex, obj);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: protected */
    public void setPosPrevious(int i, Object obj) {
        int nextIndex = nextIndex(i);
        if (nextIndex != 0) {
            set(nextIndex - 1, obj);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final int nextIndex(SeqPosition seqPosition) {
        return nextIndex(seqPosition.ipos);
    }

    public boolean equals(int i, int i2) {
        return compare(i, i2) == 0;
    }

    public int compare(int i, int i2) {
        int nextIndex = nextIndex(i);
        int nextIndex2 = nextIndex(i2);
        if (nextIndex < nextIndex2) {
            return -1;
        }
        return nextIndex > nextIndex2 ? 1 : 0;
    }

    public final int compare(SeqPosition seqPosition, SeqPosition seqPosition2) {
        return compare(seqPosition.ipos, seqPosition2.ipos);
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int startPos = startPos();
        int i = 0;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                return objArr;
            }
            objArr[i] = getPosPrevious(startPos);
            i++;
        }
    }

    public Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int size = size();
        if (size > length) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            length = size;
        }
        int startPos = startPos();
        int i = 0;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                break;
            }
            objArr[i] = getPosPrevious(startPos);
            i++;
        }
        if (size < length) {
            objArr[size] = null;
        }
        return objArr;
    }

    public int stableCompare(AbstractSequence abstractSequence) {
        int identityHashCode = System.identityHashCode(this);
        int identityHashCode2 = System.identityHashCode(abstractSequence);
        if (identityHashCode < identityHashCode2) {
            return -1;
        }
        return identityHashCode > identityHashCode2 ? 1 : 0;
    }

    public static int compare(AbstractSequence abstractSequence, int i, AbstractSequence abstractSequence2, int i2) {
        if (abstractSequence == abstractSequence2) {
            return abstractSequence.compare(i, i2);
        }
        return abstractSequence.stableCompare(abstractSequence2);
    }

    public int hashCode() {
        int i;
        int startPos = startPos();
        int i2 = 1;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos == 0) {
                return i2;
            }
            Object posPrevious = getPosPrevious(startPos);
            int i3 = i2 * 31;
            if (posPrevious == null) {
                i = 0;
            } else {
                i = posPrevious.hashCode();
            }
            i2 = i3 + i;
        }
    }

    public boolean equals(Object obj) {
        if ((this instanceof List) && (obj instanceof List)) {
            Iterator it = iterator();
            Iterator it2 = ((List) obj).iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                if (hasNext != it2.hasNext()) {
                    return false;
                }
                if (!hasNext) {
                    return true;
                }
                Object next = it.next();
                Object next2 = it2.next();
                if (next == null) {
                    if (next2 != null) {
                        return false;
                    }
                } else if (!next.equals(next2)) {
                    return false;
                }
            }
        } else if (this == obj) {
            return true;
        } else {
            return false;
        }
    }

    public Sequence subSequence(SeqPosition seqPosition, SeqPosition seqPosition2) {
        return subSequencePos(seqPosition.ipos, seqPosition2.ipos);
    }

    /* access modifiers changed from: protected */
    public Sequence subSequencePos(int i, int i2) {
        return new SubSequence(this, i, i2);
    }

    public List subList(int i, int i2) {
        return subSequencePos(createPos(i, false), createPos(i2, true));
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int nextPos = nextPos(i);
        if (nextPos == 0) {
            return false;
        }
        consumePosRange(i, nextPos, consumer);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int copyPos = copyPos(i);
            while (!equals(copyPos, i2)) {
                if (hasNext(copyPos)) {
                    consumer.writeObject(getPosNext(copyPos));
                    copyPos = nextPos(copyPos);
                } else {
                    throw new RuntimeException();
                }
            }
            releasePos(copyPos);
        }
    }

    public void consume(Consumer consumer) {
        boolean z = this instanceof Sequence;
        if (z) {
            consumer.startElement("#sequence");
        }
        consumePosRange(startPos(), endPos(), consumer);
        if (z) {
            consumer.endElement();
        }
    }

    public void toString(String str, StringBuffer stringBuffer) {
        int startPos = startPos();
        boolean z = false;
        while (true) {
            startPos = nextPos(startPos);
            if (startPos != 0) {
                if (z) {
                    stringBuffer.append(str);
                } else {
                    z = true;
                }
                stringBuffer.append(getPosPrevious(startPos));
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        boolean z = this instanceof Sequence;
        if (z) {
            stringBuffer.append('[');
        }
        toString(", ", stringBuffer);
        if (z) {
            stringBuffer.append(']');
        }
        return stringBuffer.toString();
    }
}
