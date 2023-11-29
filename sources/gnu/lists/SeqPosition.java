package gnu.lists;

import gnu.kawa.functions.GetNamedPart;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SeqPosition implements ListIterator, Enumeration {
    public int ipos;
    public AbstractSequence sequence;

    public SeqPosition() {
    }

    public SeqPosition(AbstractSequence abstractSequence) {
        this.sequence = abstractSequence;
    }

    public SeqPosition(AbstractSequence abstractSequence, int i, boolean z) {
        this.sequence = abstractSequence;
        this.ipos = abstractSequence.createPos(i, z);
    }

    public SeqPosition(AbstractSequence abstractSequence, int i) {
        this.sequence = abstractSequence;
        this.ipos = i;
    }

    public static SeqPosition make(AbstractSequence abstractSequence, int i) {
        return new SeqPosition(abstractSequence, abstractSequence.copyPos(i));
    }

    public SeqPosition copy() {
        AbstractSequence abstractSequence = this.sequence;
        return new SeqPosition(abstractSequence, abstractSequence.copyPos(getPos()));
    }

    public final void gotoStart(AbstractSequence abstractSequence) {
        setPos(abstractSequence, abstractSequence.startPos());
    }

    public final void gotoEnd(AbstractSequence abstractSequence) {
        setPos(abstractSequence, abstractSequence.endPos());
    }

    public boolean gotoChildrenStart() {
        int firstChildPos = this.sequence.firstChildPos(getPos());
        if (firstChildPos == 0) {
            return false;
        }
        this.ipos = firstChildPos;
        return true;
    }

    public final boolean hasMoreElements() {
        return hasNext();
    }

    public boolean hasNext() {
        return this.sequence.hasNext(getPos());
    }

    public int getNextKind() {
        return this.sequence.getNextKind(getPos());
    }

    public String getNextTypeName() {
        return this.sequence.getNextTypeName(getPos());
    }

    public Object getNextTypeObject() {
        return this.sequence.getNextTypeObject(getPos());
    }

    public boolean hasPrevious() {
        return this.sequence.hasPrevious(getPos());
    }

    public Object next() {
        Object next = getNext();
        if (next != Sequence.eofValue && gotoNext()) {
            return next;
        }
        throw new NoSuchElementException();
    }

    public boolean gotoNext() {
        int nextPos = this.sequence.nextPos(this.ipos);
        if (nextPos != 0) {
            this.ipos = nextPos;
            return true;
        }
        this.ipos = -1;
        return false;
    }

    public boolean gotoPrevious() {
        int previousPos = this.sequence.previousPos(this.ipos);
        if (previousPos != -1) {
            this.ipos = previousPos;
            return true;
        }
        this.ipos = 0;
        return false;
    }

    public Object previous() {
        Object previous = getPrevious();
        if (previous != Sequence.eofValue && gotoPrevious()) {
            return previous;
        }
        throw new NoSuchElementException();
    }

    public final Object nextElement() {
        return next();
    }

    public Object getNext() {
        return this.sequence.getPosNext(getPos());
    }

    public Object getPrevious() {
        return this.sequence.getPosPrevious(getPos());
    }

    public int nextIndex() {
        return this.sequence.nextIndex(getPos());
    }

    public final int fromEndIndex() {
        return this.sequence.fromEndIndex(getPos());
    }

    public int getContainingSequenceSize() {
        return this.sequence.getContainingSequenceSize(getPos());
    }

    public final int previousIndex() {
        return this.sequence.nextIndex(getPos()) - 1;
    }

    public boolean isAfter() {
        return this.sequence.isAfterPos(getPos());
    }

    public final void set(Object obj) {
        if (isAfter()) {
            setPrevious(obj);
        } else {
            setNext(obj);
        }
    }

    public void setNext(Object obj) {
        this.sequence.setPosNext(getPos(), obj);
    }

    public void setPrevious(Object obj) {
        this.sequence.setPosPrevious(getPos(), obj);
    }

    public void remove() {
        this.sequence.removePos(getPos(), isAfter() ? -1 : 1);
    }

    public void add(Object obj) {
        setPos(this.sequence.addPos(getPos(), obj));
    }

    public int getPos() {
        return this.ipos;
    }

    public void setPos(AbstractSequence abstractSequence, int i) {
        AbstractSequence abstractSequence2 = this.sequence;
        if (abstractSequence2 != null) {
            abstractSequence2.releasePos(getPos());
        }
        this.ipos = i;
        this.sequence = abstractSequence;
    }

    public void setPos(int i) {
        AbstractSequence abstractSequence = this.sequence;
        if (abstractSequence != null) {
            abstractSequence.releasePos(getPos());
        }
        this.ipos = i;
    }

    public void set(AbstractSequence abstractSequence, int i, boolean z) {
        AbstractSequence abstractSequence2 = this.sequence;
        if (abstractSequence2 != null) {
            abstractSequence2.releasePos(this.ipos);
        }
        this.sequence = abstractSequence;
        this.ipos = abstractSequence.createPos(i, z);
    }

    public void set(SeqPosition seqPosition) {
        AbstractSequence abstractSequence = this.sequence;
        if (abstractSequence != null) {
            abstractSequence.releasePos(this.ipos);
        }
        AbstractSequence abstractSequence2 = seqPosition.sequence;
        this.sequence = abstractSequence2;
        seqPosition.ipos = abstractSequence2.copyPos(seqPosition.ipos);
    }

    public void release() {
        AbstractSequence abstractSequence = this.sequence;
        if (abstractSequence != null) {
            abstractSequence.releasePos(getPos());
            this.sequence = null;
        }
    }

    public void finalize() {
        release();
    }

    public String toString() {
        AbstractSequence abstractSequence = this.sequence;
        if (abstractSequence == null) {
            return toInfo();
        }
        Object posNext = abstractSequence.getPosNext(this.ipos);
        StringBuilder sb = new StringBuilder();
        sb.append(GetNamedPart.CAST_METHOD_NAME);
        sb.append(nextIndex());
        sb.append(": ");
        sb.append(posNext == null ? "(null)" : posNext.toString());
        return sb.toString();
    }

    public String toInfo() {
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append('{');
        AbstractSequence abstractSequence = this.sequence;
        if (abstractSequence == null) {
            stringBuffer.append("null sequence");
        } else {
            stringBuffer.append(abstractSequence.getClass().getName());
            stringBuffer.append('@');
            stringBuffer.append(System.identityHashCode(this.sequence));
        }
        stringBuffer.append(" ipos: ");
        stringBuffer.append(this.ipos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
