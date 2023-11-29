package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class LList extends ExtSequence implements Sequence, Externalizable, Comparable {
    public static final LList Empty = new LList();

    public boolean equals(Object obj) {
        return this == obj;
    }

    public boolean hasNext(int i) {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public int nextPos(int i) {
        return 0;
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
    }

    public int size() {
        return 0;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
    }

    public static int listLength(Object obj, boolean z) {
        Object obj2 = obj;
        int i = 0;
        while (true) {
            LList lList = Empty;
            if (obj == lList) {
                return i;
            }
            if (obj instanceof Pair) {
                Pair pair = (Pair) obj;
                if (pair.cdr == lList) {
                    return i + 1;
                }
                if (obj == obj2 && i > 0) {
                    return -1;
                }
                if (!(pair.cdr instanceof Pair)) {
                    i++;
                    obj = pair.cdr;
                } else if (!(obj2 instanceof Pair)) {
                    return -2;
                } else {
                    obj2 = ((Pair) obj2).cdr;
                    obj = ((Pair) pair.cdr).cdr;
                    i += 2;
                }
            } else if (!(obj instanceof Sequence) || !z) {
                return -2;
            } else {
                int size = ((Sequence) obj).size();
                return size >= 0 ? size + i : size;
            }
        }
    }

    public int compareTo(Object obj) {
        return obj == Empty ? 0 : -1;
    }

    public SeqPosition getIterator(int i) {
        return new LListPosition(this, i, false);
    }

    public int createPos(int i, boolean z) {
        return PositionManager.manager.register(new LListPosition(this, i, z));
    }

    public int createRelativePos(int i, int i2, boolean z) {
        boolean isAfterPos = isAfterPos(i);
        if (i2 < 0 || i == 0) {
            return super.createRelativePos(i, i2, z);
        }
        if (i2 == 0) {
            if (z == isAfterPos) {
                return copyPos(i);
            }
            if (z && !isAfterPos) {
                return super.createRelativePos(i, i2, z);
            }
        }
        if (i >= 0) {
            LListPosition lListPosition = (LListPosition) PositionManager.getPositionObject(i);
            if (lListPosition.xpos == null) {
                return super.createRelativePos(i, i2, z);
            }
            LListPosition lListPosition2 = new LListPosition(lListPosition);
            int i3 = lListPosition2.ipos;
            if (z && !isAfterPos) {
                i2--;
                i3 += 3;
            }
            if (!z && isAfterPos) {
                i2++;
                i3 -= 3;
            }
            for (Object obj = lListPosition2.xpos; obj instanceof Pair; obj = ((Pair) obj).cdr) {
                i2--;
                if (i2 < 0) {
                    lListPosition2.ipos = i3;
                    lListPosition2.xpos = obj;
                    return PositionManager.manager.register(lListPosition2);
                }
                i3 += 2;
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IndexOutOfBoundsException();
    }

    public Object getPosNext(int i) {
        return eofValue;
    }

    public Object getPosPrevious(int i) {
        return eofValue;
    }

    /* access modifiers changed from: protected */
    public void setPosNext(int i, Object obj) {
        if (i > 0) {
            PositionManager.getPositionObject(i).setNext(obj);
        } else if (i == -1 || !(this instanceof Pair)) {
            throw new IndexOutOfBoundsException();
        } else {
            ((Pair) this).car = obj;
        }
    }

    /* access modifiers changed from: protected */
    public void setPosPrevious(int i, Object obj) {
        if (i > 0) {
            PositionManager.getPositionObject(i).setPrevious(obj);
        } else if (i == 0 || !(this instanceof Pair)) {
            throw new IndexOutOfBoundsException();
        } else {
            ((Pair) this).lastPair().car = obj;
        }
    }

    public Object get(int i) {
        throw new IndexOutOfBoundsException();
    }

    public static final int length(Object obj) {
        int i = 0;
        while (obj instanceof Pair) {
            i++;
            obj = ((Pair) obj).cdr;
        }
        return i;
    }

    public static LList makeList(List list) {
        LList lList = Empty;
        Pair pair = null;
        for (Object pair2 : list) {
            Pair pair3 = new Pair(pair2, Empty);
            if (pair == null) {
                lList = pair3;
            } else {
                pair.cdr = pair3;
            }
            pair = pair3;
        }
        return lList;
    }

    public static LList makeList(Object[] objArr, int i, int i2) {
        LList lList = Empty;
        while (true) {
            i2--;
            if (i2 < 0) {
                return lList;
            }
            lList = new Pair(objArr[i + i2], lList);
        }
    }

    public static LList makeList(Object[] objArr, int i) {
        LList lList = Empty;
        int length = objArr.length - i;
        while (true) {
            length--;
            if (length < 0) {
                return lList;
            }
            lList = new Pair(objArr[i + length], lList);
        }
    }

    public void consume(Consumer consumer) {
        consumer.startElement("list");
        Object obj = this;
        while (obj instanceof Pair) {
            if (obj != this) {
                consumer.write(32);
            }
            Pair pair = (Pair) obj;
            consumer.writeObject(pair.car);
            obj = pair.cdr;
        }
        if (obj != Empty) {
            consumer.write(32);
            consumer.write(". ");
            consumer.writeObject(checkNonList(obj));
        }
        consumer.endElement();
    }

    public Object readResolve() throws ObjectStreamException {
        return Empty;
    }

    public static Pair list1(Object obj) {
        return new Pair(obj, Empty);
    }

    public static Pair list2(Object obj, Object obj2) {
        return new Pair(obj, new Pair(obj2, Empty));
    }

    public static Pair list3(Object obj, Object obj2, Object obj3) {
        return new Pair(obj, new Pair(obj2, new Pair(obj3, Empty)));
    }

    public static Pair list4(Object obj, Object obj2, Object obj3, Object obj4) {
        return new Pair(obj, new Pair(obj2, new Pair(obj3, new Pair(obj4, Empty))));
    }

    public static Pair chain1(Pair pair, Object obj) {
        Pair pair2 = new Pair(obj, Empty);
        pair.cdr = pair2;
        return pair2;
    }

    public static Pair chain4(Pair pair, Object obj, Object obj2, Object obj3, Object obj4) {
        Pair pair2 = new Pair(obj4, Empty);
        pair.cdr = new Pair(obj, new Pair(obj2, new Pair(obj3, pair2)));
        return pair2;
    }

    public static LList reverseInPlace(Object obj) {
        LList lList = Empty;
        while (obj != Empty) {
            Pair pair = (Pair) obj;
            Object obj2 = pair.cdr;
            pair.cdr = lList;
            lList = pair;
            obj = obj2;
        }
        return lList;
    }

    public static Object listTail(Object obj, int i) {
        while (true) {
            i--;
            if (i < 0) {
                return obj;
            }
            if (obj instanceof Pair) {
                obj = ((Pair) obj).cdr;
            } else {
                throw new IndexOutOfBoundsException("List is too short.");
            }
        }
    }

    public static Object consX(Object[] objArr) {
        Object obj = objArr[0];
        int i = 1;
        int length = objArr.length - 1;
        if (length <= 0) {
            return obj;
        }
        Pair pair = new Pair(obj, (Object) null);
        Pair pair2 = pair;
        while (i < length) {
            Pair pair3 = new Pair(objArr[i], (Object) null);
            pair2.cdr = pair3;
            i++;
            pair2 = pair3;
        }
        pair2.cdr = objArr[length];
        return pair;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append('(');
        int i = 0;
        Object obj = this;
        while (true) {
            if (obj == Empty) {
                break;
            }
            if (i > 0) {
                stringBuffer.append(' ');
            }
            if (i < 10) {
                if (!(obj instanceof Pair)) {
                    stringBuffer.append(". ");
                    stringBuffer.append(checkNonList(obj));
                    break;
                }
                Pair pair = (Pair) obj;
                stringBuffer.append(pair.car);
                obj = pair.cdr;
                i++;
            } else {
                stringBuffer.append("...");
                break;
            }
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public static Object checkNonList(Object obj) {
        return obj instanceof LList ? "#<not a pair>" : obj;
    }
}
