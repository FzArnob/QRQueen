package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ParentAxis extends TreeScanner {
    public static ParentAxis make(NodePredicate nodePredicate) {
        ParentAxis parentAxis = new ParentAxis();
        parentAxis.type = nodePredicate;
        return parentAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int parentPos = abstractSequence.parentPos(i);
        if (parentPos != abstractSequence.endPos() && this.type.isInstancePos(abstractSequence, parentPos)) {
            positionConsumer.writePosition(abstractSequence, parentPos);
        }
    }
}
