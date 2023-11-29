package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingAxis extends TreeScanner {
    public static PrecedingAxis make(NodePredicate nodePredicate) {
        PrecedingAxis precedingAxis = new PrecedingAxis();
        precedingAxis.type = nodePredicate;
        return precedingAxis;
    }

    private static void scan(AbstractSequence abstractSequence, int i, int i2, NodePredicate nodePredicate, PositionConsumer positionConsumer) {
        int parentPos = abstractSequence.parentPos(i);
        if (parentPos != i2) {
            scan(abstractSequence, parentPos, i2, nodePredicate, positionConsumer);
            int firstChildPos = abstractSequence.firstChildPos(parentPos);
            if (firstChildPos != 0) {
                if (nodePredicate.isInstancePos(abstractSequence, firstChildPos)) {
                    positionConsumer.writePosition(abstractSequence, firstChildPos);
                }
                while (true) {
                    firstChildPos = abstractSequence.nextMatching(firstChildPos, nodePredicate, i, true);
                    if (firstChildPos != 0) {
                        positionConsumer.writePosition(abstractSequence, firstChildPos);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        scan(abstractSequence, i, abstractSequence.endPos(), this.type, positionConsumer);
    }
}
