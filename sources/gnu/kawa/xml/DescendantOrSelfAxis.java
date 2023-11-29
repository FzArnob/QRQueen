package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantOrSelfAxis extends TreeScanner {
    public static final DescendantOrSelfAxis anyNode = new DescendantOrSelfAxis(NodeType.anyNodeTest);

    private DescendantOrSelfAxis(NodePredicate nodePredicate) {
        this.type = nodePredicate;
    }

    public static DescendantOrSelfAxis make(NodePredicate nodePredicate) {
        if (nodePredicate == NodeType.anyNodeTest) {
            return anyNode;
        }
        return new DescendantOrSelfAxis(nodePredicate);
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        if (this.type.isInstancePos(abstractSequence, i)) {
            positionConsumer.writePosition(abstractSequence, i);
        }
        if (!(abstractSequence instanceof TreeList)) {
            int firstChildPos = abstractSequence.firstChildPos(i);
            while (firstChildPos != 0) {
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
