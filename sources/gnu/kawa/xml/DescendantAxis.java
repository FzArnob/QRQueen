package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantAxis extends TreeScanner {
    public static DescendantAxis make(NodePredicate nodePredicate) {
        DescendantAxis descendantAxis = new DescendantAxis();
        descendantAxis.type = nodePredicate;
        return descendantAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        if (!(abstractSequence instanceof TreeList)) {
            int firstChildPos = abstractSequence.firstChildPos(i);
            while (firstChildPos != 0) {
                if (this.type.isInstancePos(abstractSequence, firstChildPos)) {
                    positionConsumer.writePosition(abstractSequence, firstChildPos);
                }
                scan(abstractSequence, firstChildPos, positionConsumer);
                firstChildPos = abstractSequence.nextPos(firstChildPos);
            }
            return;
        }
        int nextPos = abstractSequence.nextPos(i);
        while (true) {
            i = abstractSequence.nextMatching(i, this.type, nextPos, true);
            if (i != 0) {
                positionConsumer.writePosition(abstractSequence, i);
            } else {
                return;
            }
        }
    }
}
