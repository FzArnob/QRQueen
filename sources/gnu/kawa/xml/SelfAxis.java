package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class SelfAxis extends TreeScanner {
    public static SelfAxis make(NodePredicate nodePredicate) {
        SelfAxis selfAxis = new SelfAxis();
        selfAxis.type = nodePredicate;
        return selfAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        if (this.type.isInstancePos(abstractSequence, i)) {
            positionConsumer.writePosition(abstractSequence, i);
        }
    }
}
