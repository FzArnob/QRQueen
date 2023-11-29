package gnu.kawa.xml;

import gnu.lists.AbstractSequence;

public class SortedNodes extends Nodes {
    int nesting = 0;

    /* access modifiers changed from: package-private */
    public int compareIndex(int i, AbstractSequence abstractSequence, int i2) {
        if (this.data[i] == 61711) {
            return AbstractSequence.compare((AbstractSequence) this.objects[getIntN(i + 1)], getIntN(i + 3), abstractSequence, i2);
        }
        throw new RuntimeException("invalid kind of value to compare");
    }

    /* access modifiers changed from: package-private */
    public int find(int i, int i2, AbstractSequence abstractSequence, int i3) {
        int i4 = 0;
        while (i4 < i2) {
            int i5 = (i4 + i2) >>> 1;
            int compareIndex = compareIndex((i5 * 5) + i, abstractSequence, i3);
            if (compareIndex == 0) {
                return -1;
            }
            if (compareIndex > 0) {
                i2 = i5;
            } else {
                i4 = i5 + 1;
            }
        }
        return i + (i4 * 5);
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        int find;
        if (this.count > 0) {
            int i2 = this.gapStart - 5;
            int compareIndex = compareIndex(i2, abstractSequence, i);
            if (compareIndex < 0) {
                int i3 = this.gapEnd;
                int find2 = find(i3, (this.data.length - i3) / 5, abstractSequence, i);
                if (find2 >= 0) {
                    int i4 = find2 - this.gapEnd;
                    if (i4 > 0) {
                        System.arraycopy(this.data, this.gapEnd, this.data, this.gapStart, i4);
                        this.gapEnd = find2;
                        this.gapStart += i4;
                    }
                } else {
                    return;
                }
            } else if (compareIndex != 0 && (find = find(0, i2 / 5, abstractSequence, i)) >= 0) {
                int i5 = this.gapStart - find;
                if (i5 > 0) {
                    System.arraycopy(this.data, find, this.data, this.gapEnd - i5, i5);
                    this.gapStart = find;
                    this.gapEnd -= i5;
                }
            } else {
                return;
            }
        }
        super.writePosition(abstractSequence, i);
    }
}
