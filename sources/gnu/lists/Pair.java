package gnu.lists;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Pair extends LList implements Externalizable {
    protected Object car;
    protected Object cdr;

    public boolean isEmpty() {
        return false;
    }

    public Object readResolve() throws ObjectStreamException {
        return this;
    }

    public Pair(Object obj, Object obj2) {
        this.car = obj;
        this.cdr = obj2;
    }

    public Pair() {
    }

    public Object getCar() {
        return this.car;
    }

    public Object getCdr() {
        return this.cdr;
    }

    public void setCar(Object obj) {
        this.car = obj;
    }

    public void setCdr(Object obj) {
        this.cdr = obj;
    }

    public void setCdrBackdoor(Object obj) {
        this.cdr = obj;
    }

    public int size() {
        int listLength = listLength(this, true);
        if (listLength >= 0) {
            return listLength;
        }
        if (listLength == -1) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        throw new RuntimeException("not a true list");
    }

    public int length() {
        int i = 0;
        Object obj = this;
        Object obj2 = obj;
        while (obj != Empty) {
            if (obj instanceof Pair) {
                Pair pair = (Pair) obj;
                if (pair.cdr == Empty) {
                    return i + 1;
                }
                if (obj == obj2 && i > 0) {
                    return -1;
                }
                obj = pair.cdr;
                if (!(obj instanceof Pair)) {
                    i++;
                } else if (!(obj2 instanceof Pair)) {
                    return -2;
                } else {
                    obj2 = ((Pair) obj2).cdr;
                    obj = ((Pair) obj).cdr;
                    i += 2;
                }
            } else if (!(obj instanceof Sequence)) {
                return -2;
            } else {
                int size = ((Sequence) obj).size();
                return size >= 0 ? size + i : size;
            }
        }
        return i;
    }

    public boolean hasNext(int i) {
        if (i <= 0) {
            return i == 0;
        }
        return PositionManager.getPositionObject(i).hasNext();
    }

    public int nextPos(int i) {
        if (i <= 0) {
            if (i < 0) {
                return 0;
            }
            return PositionManager.manager.register(new LListPosition(this, 1, true));
        } else if (((LListPosition) PositionManager.getPositionObject(i)).gotoNext()) {
            return i;
        } else {
            return 0;
        }
    }

    public Object getPosNext(int i) {
        if (i <= 0) {
            return i == 0 ? this.car : eofValue;
        }
        return PositionManager.getPositionObject(i).getNext();
    }

    public Object getPosPrevious(int i) {
        if (i <= 0) {
            return i == 0 ? eofValue : lastPair().car;
        }
        return PositionManager.getPositionObject(i).getPrevious();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final gnu.lists.Pair lastPair() {
        /*
            r3 = this;
            r0 = r3
        L_0x0001:
            java.lang.Object r1 = r0.cdr
            boolean r2 = r1 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x000b
            r0 = r1
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            goto L_0x0001
        L_0x000b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.Pair.lastPair():gnu.lists.Pair");
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        Object obj = this;
        while (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            Object obj2 = pair.car;
            int i3 = i2 * 31;
            if (obj2 == null) {
                i = 0;
            } else {
                i = obj2.hashCode();
            }
            i2 = i3 + i;
            obj = pair.cdr;
        }
        return (obj == LList.Empty || obj == null) ? i2 : i2 ^ obj.hashCode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        return r4.equals(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equals(gnu.lists.Pair r4, gnu.lists.Pair r5) {
        /*
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r4 == 0) goto L_0x0038
            if (r5 != 0) goto L_0x000a
            goto L_0x0038
        L_0x000a:
            java.lang.Object r2 = r4.car
            java.lang.Object r3 = r5.car
            if (r2 == r3) goto L_0x0019
            if (r2 == 0) goto L_0x0018
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0019
        L_0x0018:
            return r1
        L_0x0019:
            java.lang.Object r4 = r4.cdr
            java.lang.Object r5 = r5.cdr
            if (r4 != r5) goto L_0x0020
            return r0
        L_0x0020:
            if (r4 == 0) goto L_0x0038
            if (r5 != 0) goto L_0x0025
            goto L_0x0038
        L_0x0025:
            boolean r2 = r4 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x0033
            boolean r2 = r5 instanceof gnu.lists.Pair
            if (r2 != 0) goto L_0x002e
            goto L_0x0033
        L_0x002e:
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            goto L_0x000a
        L_0x0033:
            boolean r4 = r4.equals(r5)
            return r4
        L_0x0038:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.Pair.equals(gnu.lists.Pair, gnu.lists.Pair):boolean");
    }

    public static int compareTo(Pair pair, Pair pair2) {
        Object obj;
        Object obj2;
        if (pair == pair2) {
            return 0;
        }
        if (pair == null) {
            return -1;
        }
        if (pair2 == null) {
            return 1;
        }
        while (true) {
            int compareTo = ((Comparable) pair.car).compareTo((Comparable) pair2.car);
            if (compareTo != 0) {
                return compareTo;
            }
            obj = pair.cdr;
            obj2 = pair2.cdr;
            if (obj == obj2) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if ((obj instanceof Pair) && (obj2 instanceof Pair)) {
                pair = (Pair) obj;
                pair2 = (Pair) obj2;
            }
        }
        return ((Comparable) obj).compareTo((Comparable) obj2);
    }

    public int compareTo(Object obj) {
        if (obj == Empty) {
            return 1;
        }
        return compareTo(this, (Pair) obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(int r4) {
        /*
            r3 = this;
            r0 = r3
        L_0x0001:
            if (r4 <= 0) goto L_0x001a
            int r4 = r4 + -1
            java.lang.Object r1 = r0.cdr
            boolean r2 = r1 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x000f
            r0 = r1
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            goto L_0x0001
        L_0x000f:
            boolean r2 = r1 instanceof gnu.lists.Sequence
            if (r2 == 0) goto L_0x001a
            gnu.lists.Sequence r1 = (gnu.lists.Sequence) r1
            java.lang.Object r4 = r1.get(r4)
            return r4
        L_0x001a:
            if (r4 != 0) goto L_0x001f
            java.lang.Object r4 = r0.car
            return r4
        L_0x001f:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.Pair.get(int):java.lang.Object");
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pair)) {
            return false;
        }
        return equals(this, (Pair) obj);
    }

    public static Pair make(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public Object[] toArray() {
        int size = size();
        Object[] objArr = new Object[size];
        int i = 0;
        Sequence sequence = this;
        while (i < size && (sequence instanceof Pair)) {
            Pair pair = (Pair) sequence;
            objArr[i] = pair.car;
            sequence = (Sequence) pair.cdr;
            i++;
        }
        for (int i2 = i; i2 < size; i2++) {
            objArr[i2] = sequence.get(i2 - i);
        }
        return objArr;
    }

    public Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int length2 = length();
        if (length2 > length) {
            objArr = new Object[length2];
            length = length2;
        }
        int i = 0;
        Sequence sequence = this;
        while (i < length2 && (sequence instanceof Pair)) {
            Pair pair = (Pair) sequence;
            objArr[i] = pair.car;
            sequence = (Sequence) pair.cdr;
            i++;
        }
        for (int i2 = i; i2 < length2; i2++) {
            objArr[i2] = sequence.get(i2 - i);
        }
        if (length2 < length) {
            objArr[length2] = null;
        }
        return objArr;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.car);
        objectOutput.writeObject(this.cdr);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.car = objectInput.readObject();
        this.cdr = objectInput.readObject();
    }
}
