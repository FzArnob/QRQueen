package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorOrSelfAxis extends TreeScanner {
    public static AncestorOrSelfAxis make(NodePredicate nodePredicate) {
        AncestorOrSelfAxis ancestorOrSelfAxis = new AncestorOrSelfAxis();
        ancestorOrSelfAxis.type = nodePredicate;
        return ancestorOrSelfAxis;
    }

    private static void scan(AbstractSequence abstractSequence, int i, int i2, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        if (i != i2) {
            scan(abstractSequence, abstractSequence.parentPos(i), i2, nodePredicate, positionConsumer);
            if (nodePredicate.isInstancePos(abstractSequence, i)) {
                positionConsumer.writePosition(abstractSequence, i);
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        scan(abstractSequence, i, abstractSequence.endPos(), this.type, positionConsumer);
    }
}
