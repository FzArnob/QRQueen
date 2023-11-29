package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingSiblingAxis extends TreeScanner {
    public static FollowingSiblingAxis make(NodePredicate nodePredicate) {
        FollowingSiblingAxis followingSiblingAxis = new FollowingSiblingAxis();
        followingSiblingAxis.type = nodePredicate;
        return followingSiblingAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int endPos = abstractSequence.endPos();
        while (true) {
            i = abstractSequence.nextMatching(i, this.type, endPos, false);
            if (i != 0) {
                positionConsumer.writePosition(abstractSequence, i);
            } else {
                return;
            }
        }
    }
}
