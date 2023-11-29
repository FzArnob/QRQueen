package gnu.lists;

public class GapVector extends AbstractSequence implements Sequence {
    public SimpleVector base;
    public int gapEnd;
    public int gapStart;

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return (i & 1) != 0;
    }

    public GapVector(SimpleVector simpleVector) {
        this.base = simpleVector;
        this.gapStart = 0;
        this.gapEnd = simpleVector.size;
    }

    protected GapVector() {
    }

    public int size() {
        return this.base.size - (this.gapEnd - this.gapStart);
    }

    public boolean hasNext(int i) {
        int i2 = i >>> 1;
        int i3 = this.gapStart;
        if (i2 >= i3) {
            i2 += this.gapEnd - i3;
        }
        if (i2 < this.base.size) {
            return true;
        }
        return false;
    }

    public int getNextKind(int i) {
        if (hasNext(i)) {
            return this.base.getElementKind();
        }
        return 0;
    }

    public Object get(int i) {
        int i2 = this.gapStart;
        if (i >= i2) {
            i += this.gapEnd - i2;
        }
        return this.base.get(i);
    }

    public Object set(int i, Object obj) {
        int i2 = this.gapStart;
        if (i >= i2) {
            i += this.gapEnd - i2;
        }
        return this.base.set(i, obj);
    }

    public void fill(Object obj) {
        SimpleVector simpleVector = this.base;
        simpleVector.fill(this.gapEnd, simpleVector.size, obj);
        this.base.fill(0, this.gapStart, obj);
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int i3 = i2 == -1 ? this.base.size : i2 >>> 1;
        int i4 = this.gapStart;
        if (i4 >= i3) {
            i4 = i3;
        }
        for (int i5 = i == -1 ? this.base.size : i >>> 1; i5 < i4; i5++) {
            this.base.setBuffer(i5, obj);
        }
        for (int i6 = this.gapEnd; i6 < i3; i6++) {
            this.base.setBuffer(i6, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void shiftGap(int i) {
        int i2 = this.gapStart;
        int i3 = i - i2;
        if (i3 > 0) {
            this.base.shift(this.gapEnd, i2, i3);
        } else if (i3 < 0) {
            this.base.shift(i, this.gapEnd + i3, -i3);
        }
        this.gapEnd += i3;
        this.gapStart = i;
    }

    /* access modifiers changed from: protected */
    public final void gapReserve(int i) {
        gapReserve(this.gapStart, i);
    }

    /* access modifiers changed from: protected */
    public void gapReserve(int i, int i2) {
        int i3 = this.gapEnd;
        int i4 = this.gapStart;
        if (i2 > i3 - i4) {
            int i5 = this.base.size;
            int i6 = 16;
            if (i5 >= 16) {
                i6 = i5 * 2;
            }
            int i7 = this.gapEnd;
            int i8 = this.gapStart;
            int i9 = i5 - (i7 - i8);
            int i10 = i2 + i9;
            if (i6 < i10) {
                i6 = i10;
            }
            int i11 = (i6 - i9) + i;
            this.base.resizeShift(i8, i7, i, i11);
            this.gapStart = i;
            this.gapEnd = i11;
        } else if (i != i4) {
            shiftGap(i);
        }
    }

    public int getSegment(int i, int i2) {
        int i3;
        int size = size();
        if (i < 0 || i > size) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i + i2 > size) {
            i2 = size - i;
        }
        int i4 = i + i2;
        int i5 = this.gapStart;
        if (i4 <= i5) {
            return i;
        }
        if (i >= i5) {
            i3 = this.gapEnd - i5;
        } else if (i5 - i > (i2 >> 1)) {
            shiftGap(i4);
            return i;
        } else {
            shiftGap(i);
            i3 = this.gapEnd - this.gapStart;
        }
        return i + i3;
    }

    /* access modifiers changed from: protected */
    public int addPos(int i, Object obj) {
        int i2 = i >>> 1;
        int i3 = this.gapStart;
        if (i2 >= i3) {
            i2 += this.gapEnd - i3;
        }
        add(i2, obj);
        return ((i2 + 1) << 1) | 1;
    }

    public void add(int i, Object obj) {
        gapReserve(i, 1);
        this.base.set(i, obj);
        this.gapStart++;
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int i, int i2) {
        int i3 = i >>> 1;
        int i4 = i2 >>> 1;
        int i5 = this.gapEnd;
        if (i3 > i5) {
            shiftGap((i3 - i5) + this.gapStart);
        } else if (i4 < this.gapStart) {
            shiftGap(i4);
        }
        int i6 = this.gapStart;
        if (i3 < i6) {
            this.base.clearBuffer(i3, i6 - i3);
            this.gapStart = i3;
        }
        int i7 = this.gapEnd;
        if (i4 > i7) {
            this.base.clearBuffer(i7, i4 - i7);
            this.gapEnd = i4;
        }
    }

    public int createPos(int i, boolean z) {
        int i2 = this.gapStart;
        if (i > i2) {
            i += this.gapEnd - i2;
        }
        return (i << 1) | z ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        int i2 = i == -1 ? this.base.size : i >>> 1;
        int i3 = this.gapStart;
        return i2 > i3 ? i2 - (this.gapEnd - i3) : i2;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            int i3 = i >>> 1;
            int i4 = i2 >>> 1;
            int i5 = this.gapStart;
            if (i3 < i5) {
                if (i4 > i5) {
                    i5 = i4;
                }
                consumePosRange(i, i5 << 1, consumer);
            }
            int i6 = this.gapEnd;
            if (i4 > i6) {
                if (i3 < i6) {
                    i3 = i6;
                }
                consumePosRange(i3 << 1, i2, consumer);
            }
        }
    }
}
