package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

public class NodeCompare extends Procedure2 {
    public static final NodeCompare $Eq = make("is", 8);
    public static final NodeCompare $Gr = make(">>", 16);
    public static final NodeCompare $Ls = make("<<", 4);
    public static final NodeCompare $Ne = make("isnot", 20);
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int TRUE_IF_EQU = 8;
    static final int TRUE_IF_GRT = 16;
    static final int TRUE_IF_LSS = 4;
    int flags;

    public static NodeCompare make(String str, int i) {
        NodeCompare nodeCompare = new NodeCompare();
        nodeCompare.setName(str);
        nodeCompare.flags = i;
        return nodeCompare;
    }

    public Object apply2(Object obj, Object obj2) {
        AbstractSequence abstractSequence;
        int i;
        AbstractSequence abstractSequence2;
        int i2;
        int i3;
        if (obj == null || obj2 == null) {
            return null;
        }
        if (obj == Values.empty) {
            return obj;
        }
        if (obj2 == Values.empty) {
            return obj2;
        }
        if (obj instanceof AbstractSequence) {
            abstractSequence = (AbstractSequence) obj;
            i = abstractSequence.startPos();
        } else {
            try {
                SeqPosition seqPosition = (SeqPosition) obj;
                AbstractSequence abstractSequence3 = seqPosition.sequence;
                i = seqPosition.getPos();
                abstractSequence = abstractSequence3;
            } catch (ClassCastException e) {
                throw WrongType.make(e, (Procedure) this, 1, obj);
            }
        }
        if (obj2 instanceof AbstractSequence) {
            abstractSequence2 = (AbstractSequence) obj2;
            i2 = abstractSequence2.startPos();
        } else {
            try {
                SeqPosition seqPosition2 = (SeqPosition) obj2;
                AbstractSequence abstractSequence4 = seqPosition2.sequence;
                i2 = seqPosition2.getPos();
                abstractSequence2 = abstractSequence4;
            } catch (ClassCastException e2) {
                throw WrongType.make(e2, (Procedure) this, 2, obj2);
            }
        }
        if (abstractSequence == abstractSequence2) {
            i3 = abstractSequence.compare(i, i2);
        } else if (this == $Eq) {
            return Boolean.FALSE;
        } else {
            if (this == $Ne) {
                return Boolean.TRUE;
            }
            i3 = abstractSequence.stableCompare(abstractSequence2);
        }
        if (((1 << (i3 + 3)) & this.flags) != 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
