package gnu.lists;

public class StableVector extends GapVector {
    static final int END_POSITION = 1;
    protected static final int FREE_POSITION = -2;
    static final int START_POSITION = 0;
    protected int free;
    protected int[] positions;

    public int endPos() {
        return 1;
    }

    public int startPos() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void chainFreelist() {
        this.free = -1;
        int length = this.positions.length;
        while (true) {
            length--;
            if (length > 1) {
                int[] iArr = this.positions;
                if (iArr[length] == -2) {
                    iArr[length] = this.free;
                    this.free = length;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void unchainFreelist() {
        int i = this.free;
        while (i >= 0) {
            int[] iArr = this.positions;
            int i2 = iArr[i];
            iArr[i] = -2;
            i = i2;
        }
        this.free = -2;
    }

    public StableVector(SimpleVector simpleVector) {
        super(simpleVector);
        int[] iArr = new int[16];
        this.positions = iArr;
        iArr[0] = 0;
        iArr[1] = (simpleVector.getBufferLength() << 1) | 1;
        this.free = -1;
        int length = this.positions.length;
        while (true) {
            length--;
            if (length > 1) {
                this.positions[length] = this.free;
                this.free = length;
            } else {
                return;
            }
        }
    }

    protected StableVector() {
    }

    /* access modifiers changed from: protected */
    public int allocPositionIndex() {
        if (this.free == -2) {
            chainFreelist();
        }
        if (this.free < 0) {
            int[] iArr = this.positions;
            int length = iArr.length;
            int i = length * 2;
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            while (true) {
                i--;
                if (i < length) {
                    break;
                }
                iArr2[i] = this.free;
                this.free = i;
            }
            this.positions = iArr2;
        }
        int i2 = this.free;
        this.free = this.positions[i2];
        return i2;
    }

    public int createPos(int i, boolean z) {
        if (i == 0 && !z) {
            return 0;
        }
        if (z && i == size()) {
            return 1;
        }
        if (i > this.gapStart || (i == this.gapStart && z)) {
            i += this.gapEnd - this.gapStart;
        }
        int allocPositionIndex = allocPositionIndex();
        this.positions[allocPositionIndex] = (i << 1) | z;
        return allocPositionIndex;
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return (this.positions[i] & 1) != 0;
    }

    public boolean hasNext(int i) {
        int i2 = this.positions[i] >>> 1;
        if (i2 >= this.gapStart) {
            i2 += this.gapEnd - this.gapStart;
        }
        if (i2 < this.base.getBufferLength()) {
            return true;
        }
        return false;
    }

    public int nextPos(int i) {
        int i2 = this.positions[i];
        int i3 = i2 >>> 1;
        if (i3 >= this.gapStart) {
            i3 += this.gapEnd - this.gapStart;
        }
        if (i3 >= this.base.getBufferLength()) {
            releasePos(i);
            return 0;
        }
        if (i == 0) {
            i = createPos(0, true);
        }
        this.positions[i] = i2 | 1;
        return i;
    }

    public int nextIndex(int i) {
        int i2 = this.positions[i] >>> 1;
        return i2 > this.gapStart ? i2 - (this.gapEnd - this.gapStart) : i2;
    }

    public void releasePos(int i) {
        if (i >= 2) {
            if (this.free == -2) {
                chainFreelist();
            }
            this.positions[i] = this.free;
            this.free = i;
        }
    }

    public int copyPos(int i) {
        if (i <= 1) {
            return i;
        }
        int allocPositionIndex = allocPositionIndex();
        int[] iArr = this.positions;
        iArr[allocPositionIndex] = iArr[i];
        return allocPositionIndex;
    }

    public void fillPosRange(int i, int i2, Object obj) {
        int[] iArr = this.positions;
        fillPosRange(iArr[i], iArr[i2], obj);
    }

    /* access modifiers changed from: protected */
    public void shiftGap(int i) {
        int i2;
        int i3;
        int i4;
        int i5 = this.gapStart;
        int i6 = i - i5;
        if (i6 > 0) {
            int i7 = this.gapEnd;
            int i8 = i6 + i7;
            i4 = (i5 - i7) << 1;
            i2 = i7 << 1;
            i3 = (i8 << 1) - 1;
        } else if (i != i5) {
            i2 = (i << 1) + 1;
            i3 = i5 << 1;
            i4 = (this.gapEnd - i5) << 1;
        } else {
            return;
        }
        super.shiftGap(i);
        adjustPositions(i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void gapReserve(int i, int i2) {
        int i3 = this.gapEnd;
        int i4 = this.gapStart;
        if (i2 > i3 - i4) {
            int i5 = this.base.size;
            super.gapReserve(i, i2);
            int i6 = this.base.size;
            if (i == i4) {
                adjustPositions(i3 << 1, (i6 << 1) | 1, (i6 - i5) << 1);
                return;
            }
            adjustPositions(i3 << 1, (i5 << 1) | 1, (i4 - i3) << 1);
            adjustPositions(this.gapStart << 1, (i6 << 1) | 1, (this.gapEnd - this.gapStart) << 1);
        } else if (i != this.gapStart) {
            shiftGap(i);
        }
    }

    /* access modifiers changed from: protected */
    public void adjustPositions(int i, int i2, int i3) {
        int i4;
        if (this.free >= -1) {
            unchainFreelist();
        }
        int i5 = i ^ Integer.MIN_VALUE;
        int i6 = i2 ^ Integer.MIN_VALUE;
        int length = this.positions.length;
        while (true) {
            length--;
            if (length > 0) {
                int[] iArr = this.positions;
                int i7 = iArr[length];
                if (i7 != -2 && (i4 = i7 ^ Integer.MIN_VALUE) >= i5 && i4 <= i6) {
                    iArr[length] = i7 + i3;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int addPos(int i, Object obj) {
        int i2 = this.positions[i];
        int i3 = i2 >>> 1;
        if (i3 >= this.gapStart) {
            i3 += this.gapEnd - this.gapStart;
        }
        if ((i2 & 1) == 0) {
            if (i == 0) {
                i = createPos(0, true);
            } else {
                this.positions[i] = i2 | 1;
            }
        }
        add(i3, obj);
        return i;
    }

    /* access modifiers changed from: protected */
    public void removePosRange(int i, int i2) {
        int[] iArr = this.positions;
        super.removePosRange(iArr[i], iArr[i2]);
        int i3 = this.gapStart;
        int i4 = this.gapEnd;
        if (this.free >= -1) {
            unchainFreelist();
        }
        int length = this.positions.length;
        while (true) {
            length--;
            if (length > 0) {
                int[] iArr2 = this.positions;
                int i5 = iArr2[length];
                if (i5 != -2) {
                    int i6 = i5 >> 1;
                    if ((i5 & 1) != 0) {
                        if (i6 >= i3 && i6 < i4) {
                            iArr2[length] = (this.gapEnd << 1) | 1;
                        }
                    } else if (i6 > i3 && i6 <= i4) {
                        iArr2[length] = this.gapStart << 1;
                    }
                }
            } else {
                return;
            }
        }
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int[] iArr = this.positions;
        super.consumePosRange(iArr[i], iArr[i2], consumer);
    }
}
