package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AttributeAxis extends TreeScanner {
    public static AttributeAxis make(NodePredicate nodePredicate) {
        AttributeAxis attributeAxis = new AttributeAxis();
        attributeAxis.type = nodePredicate;
        return attributeAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int firstAttributePos = abstractSequence.firstAttributePos(i);
        while (firstAttributePos != 0 && abstractSequence.getNextKind(firstAttributePos) == 35) {
            if (this.type.isInstancePos(abstractSequence, firstAttributePos)) {
                positionConsumer.writePosition(abstractSequence, firstAttributePos);
            } else if (abstractSequence.getNextKind(firstAttributePos) != 35) {
                return;
            }
            firstAttributePos = abstractSequence.nextPos(firstAttributePos);
        }
    }
}
