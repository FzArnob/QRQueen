package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ChildAxis extends TreeScanner {
    public static ChildAxis make(NodePredicate nodePredicate) {
        ChildAxis childAxis = new ChildAxis();
        childAxis.type = nodePredicate;
        return childAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int firstChildPos = abstractSequence.firstChildPos(i, this.type);
        while (firstChildPos != 0) {
            positionConsumer.writePosition(abstractSequence, firstChildPos);
            firstChildPos = abstractSequence.nextMatching(firstChildPos, this.type, -1, false);
        }
    }
}
