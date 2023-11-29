package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingSiblingAxis extends TreeScanner {
    public static PrecedingSiblingAxis make(NodePredicate nodePredicate) {
        PrecedingSiblingAxis precedingSiblingAxis = new PrecedingSiblingAxis();
        precedingSiblingAxis.type = nodePredicate;
        return precedingSiblingAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int firstChildPos;
        int endPos = abstractSequence.endPos();
        int parentPos = abstractSequence.parentPos(i);
        if (parentPos != endPos && (firstChildPos = abstractSequence.firstChildPos(parentPos)) != 0) {
            if (this.type.isInstancePos(abstractSequence, firstChildPos)) {
                positionConsumer.writePosition(abstractSequence, firstChildPos);
            }
            while (true) {
                firstChildPos = abstractSequence.nextMatching(firstChildPos, this.type, i, false);
                if (firstChildPos != 0) {
                    positionConsumer.writePosition(abstractSequence, firstChildPos);
                } else {
                    return;
                }
            }
        }
    }
}
