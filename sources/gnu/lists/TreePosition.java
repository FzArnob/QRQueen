package gnu.lists;

import java.io.PrintStream;

public class TreePosition extends SeqPosition implements Cloneable {
    int depth;
    int[] istack;
    AbstractSequence[] sstack;
    int start;
    private Object xpos;

    public TreePosition() {
        this.depth = -1;
    }

    public TreePosition(Object obj) {
        this.xpos = obj;
        this.depth = -1;
    }

    public TreePosition(AbstractSequence abstractSequence, int i) {
        super(abstractSequence, i, false);
    }

    public TreePosition(TreePosition treePosition) {
        set(treePosition);
    }

    public Object clone() {
        return new TreePosition(this);
    }

    public void set(TreePosition treePosition) {
        release();
        int i = treePosition.depth;
        this.depth = i;
        if (i < 0) {
            this.xpos = treePosition.xpos;
            return;
        }
        AbstractSequence[] abstractSequenceArr = this.sstack;
        if (abstractSequenceArr == null || abstractSequenceArr.length <= i) {
            this.sstack = new AbstractSequence[(i + 10)];
        }
        int[] iArr = this.istack;
        if (iArr == null || iArr.length <= i) {
            this.istack = new int[(i + 10)];
        }
        int i2 = 0;
        while (true) {
            int i3 = this.depth;
            if (i2 < i3) {
                int i4 = treePosition.start + i2;
                AbstractSequence abstractSequence = treePosition.sstack[i4];
                this.sstack[i3 - 1] = abstractSequence;
                this.istack[i3 - i2] = abstractSequence.copyPos(treePosition.istack[i4]);
                i2++;
            } else {
                AbstractSequence abstractSequence2 = treePosition.sequence;
                this.sequence = abstractSequence2;
                this.ipos = abstractSequence2.copyPos(treePosition.ipos);
                return;
            }
        }
    }

    public int getDepth() {
        return this.depth + 1;
    }

    public AbstractSequence getRoot() {
        return this.depth == 0 ? this.sequence : this.sstack[this.start];
    }

    public Object getPosNext() {
        return this.sequence == null ? this.xpos : this.sequence.getPosNext(this.ipos);
    }

    public void push(AbstractSequence abstractSequence, int i) {
        int i2 = this.depth;
        int i3 = this.start + i2;
        if (i3 >= 0) {
            if (i3 == 0) {
                this.istack = new int[8];
                this.sstack = new AbstractSequence[8];
            } else {
                int[] iArr = this.istack;
                if (i3 >= iArr.length) {
                    int i4 = i3 * 2;
                    int[] iArr2 = new int[i4];
                    Object[] objArr = new Object[i4];
                    AbstractSequence[] abstractSequenceArr = new AbstractSequence[i4];
                    System.arraycopy(iArr, 0, iArr2, 0, i2);
                    System.arraycopy(this.sstack, 0, abstractSequenceArr, 0, this.depth);
                    this.istack = iArr2;
                    this.sstack = abstractSequenceArr;
                }
            }
            this.sstack[i3] = this.sequence;
            this.istack[i3] = this.ipos;
        }
        this.depth++;
        this.sequence = abstractSequence;
        this.ipos = i;
    }

    public void pop() {
        this.sequence.releasePos(this.ipos);
        popNoRelease();
    }

    public void popNoRelease() {
        int i = this.depth - 1;
        this.depth = i;
        if (i < 0) {
            this.xpos = this.sequence;
            this.sequence = null;
            return;
        }
        this.sequence = this.sstack[this.start + i];
        this.ipos = this.istack[this.start + this.depth];
    }

    public final boolean gotoParent() {
        if (this.sequence == null) {
            return false;
        }
        return this.sequence.gotoParent(this);
    }

    public boolean gotoChildrenStart() {
        if (this.sequence == null) {
            Object obj = this.xpos;
            if (!(obj instanceof AbstractSequence)) {
                return false;
            }
            this.depth = 0;
            this.sequence = (AbstractSequence) obj;
            setPos(this.sequence.startPos());
            return true;
        } else if (!this.sequence.gotoChildrenStart(this)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean gotoAttributesStart() {
        if (this.sequence != null) {
            return this.sequence.gotoAttributesStart(this);
        }
        boolean z = this.xpos instanceof AbstractSequence;
        return false;
    }

    public Object getAncestor(int i) {
        if (i == 0) {
            return this.sequence.getPosNext(this.ipos);
        }
        int i2 = this.depth - i;
        if (i2 <= 0) {
            return getRoot();
        }
        int i3 = i2 + this.start;
        return this.sstack[i3].getPosNext(this.istack[i3]);
    }

    public void release() {
        while (this.sequence != null) {
            this.sequence.releasePos(this.ipos);
            pop();
        }
        this.xpos = null;
    }

    public void dump() {
        PrintStream printStream = System.err;
        printStream.println("TreePosition dump depth:" + this.depth + " start:" + this.start);
        int i = 0;
        while (true) {
            int i2 = this.depth;
            if (i <= i2) {
                AbstractSequence abstractSequence = i == 0 ? this.sequence : this.sstack[i2 - i];
                PrintStream printStream2 = System.err;
                printStream2.print("#" + i + " seq:" + abstractSequence);
                PrintStream printStream3 = System.err;
                StringBuilder sb = new StringBuilder();
                sb.append(" ipos:");
                sb.append(i == 0 ? this.ipos : this.istack[this.depth - i]);
                printStream3.println(sb.toString());
                i++;
            } else {
                return;
            }
        }
    }
}
