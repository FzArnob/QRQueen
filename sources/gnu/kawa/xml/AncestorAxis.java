package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorAxis extends TreeScanner {
    public static AncestorAxis make(NodePredicate nodePredicate) {
        AncestorAxis ancestorAxis = new AncestorAxis();
        ancestorAxis.type = nodePredicate;
        return ancestorAxis;
    }

    private static void scan(AbstractSequence abstractSequence, int i, int i2, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        int parentPos = abstractSequence.parentPos(i);
        if (parentPos != i2) {
            scan(abstractSequence, parentPos, i2, nodePredicate, positionConsumer);
            if (nodePredicate.isInstancePos(abstractSequence, parentPos)) {
                positionConsumer.writePosition(abstractSequence, parentPos);
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        scan(abstractSequence, i, abstractSequence.endPos(), this.type, positionConsumer);
    }
}
