package gnu.lists;

public class SubSequence extends AbstractSequence implements Sequence {
    AbstractSequence base;
    int ipos0;
    int ipos1;

    public SubSequence() {
    }

    public SubSequence(AbstractSequence abstractSequence, int i, int i2) {
        this.base = abstractSequence;
        this.ipos0 = i;
        this.ipos1 = i2;
    }

    public SubSequence(AbstractSequence abstractSequence) {
        this.base = abstractSequence;
    }

    public Object get(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.base.get(this.base.nextIndex(this.ipos0) + i);
    }

    public int size() {
        return this.base.getIndexDifference(this.ipos1, this.ipos0);
    }

    public void removePosRange(int i, int i2) {
        AbstractSequence abstractSequence = this.base;
        if (i == 0) {
            i = this.ipos0;
        } else if (i == -1) {
            i = this.ipos1;
        }
        if (i2 == -1) {
            i2 = this.ipos1;
        } else if (i2 == 0) {
            i2 = this.ipos0;
        }
        abstractSequence.removePosRange(i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return this.base.isAfterPos(i);
    }

    public int createPos(int i, boolean z) {
        return this.base.createRelativePos(this.ipos0, i, z);
    }

    public int createRelativePos(int i, int i2, boolean z) {
        return this.base.createRelativePos(i, i2, z);
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int i, int i2) {
        return this.base.getIndexDifference(i, i2);
    }

    public void releasePos(int i) {
        this.base.releasePos(i);
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        return getIndexDifference(i, this.ipos0);
    }

    public int compare(int i, int i2) {
        return this.base.compare(i, i2);
    }

    public Object getPosNext(int i) {
        if (this.base.compare(i, this.ipos1) >= 0) {
            return eofValue;
        }
        return this.base.getPosNext(i);
    }

    public int getNextKind(int i) {
        if (this.base.compare(i, this.ipos1) >= 0) {
            return 0;
        }
        return this.base.getNextKind(i);
    }

    public int startPos() {
        return this.ipos0;
    }

    public int endPos() {
        return this.ipos1;
    }

    public Object getPosPrevious(int i) {
        if (this.base.compare(i, this.ipos0) <= 0) {
            return eofValue;
        }
        return this.base.getPosPrevious(i);
    }

    /* access modifiers changed from: protected */
    public Sequence subSequencePos(int i, int i2) {
        return new SubSequence(this.base, i, i2);
    }

    public void clear() {
        removePosRange(this.ipos0, this.ipos1);
    }

    public void finalize() {
        this.base.releasePos(this.ipos0);
        this.base.releasePos(this.ipos1);
    }
}
