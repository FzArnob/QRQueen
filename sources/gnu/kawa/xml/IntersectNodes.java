package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class IntersectNodes extends Procedure2 {
    public static final IntersectNodes exceptNodes = new IntersectNodes(true);
    public static final IntersectNodes intersectNodes = new IntersectNodes(false);
    boolean isExcept;

    public IntersectNodes(boolean z) {
        this.isExcept = z;
    }

    public Object apply2(Object obj, Object obj2) {
        SortedNodes sortedNodes = new SortedNodes();
        SortedNodes sortedNodes2 = new SortedNodes();
        SortedNodes sortedNodes3 = new SortedNodes();
        Values.writeValues(obj, sortedNodes);
        Values.writeValues(obj2, sortedNodes2);
        AbstractSequence abstractSequence = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            AbstractSequence seq = sortedNodes.getSeq(i);
            if (seq == null) {
                return sortedNodes3;
            }
            int pos = sortedNodes.getPos(i);
            boolean z = true;
            if (i2 == -1) {
                i2 = AbstractSequence.compare(seq, pos, abstractSequence, i3);
            } else if (i2 == 0) {
                i2 = 1;
            }
            while (true) {
                if (i2 <= 0) {
                    break;
                }
                abstractSequence = sortedNodes2.getSeq(i4);
                if (abstractSequence == null) {
                    i2 = -2;
                    break;
                }
                i3 = sortedNodes2.getPos(i4);
                i4++;
                i2 = AbstractSequence.compare(seq, pos, abstractSequence, i3);
            }
            if (i2 != 0) {
                z = false;
            }
            if (z != this.isExcept) {
                sortedNodes3.writePosition(seq, pos);
            }
            i++;
        }
    }
}
