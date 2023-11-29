package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingAxis extends TreeScanner {
    public static FollowingAxis make(NodePredicate nodePredicate) {
        FollowingAxis followingAxis = new FollowingAxis();
        followingAxis.type = nodePredicate;
        return followingAxis;
    }

    public void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer) {
        int endPos = abstractSequence.endPos();
        int nextPos = abstractSequence.nextPos(i);
        if (nextPos != 0 && this.type.isInstancePos(abstractSequence, nextPos)) {
            positionConsumer.writePosition(abstractSequence, nextPos);
        }
        while (true) {
            nextPos = abstractSequence.nextMatching(nextPos, this.type, endPos, true);
            if (nextPos != 0) {
                positionConsumer.writePosition(abstractSequence, nextPos);
            } else {
                return;
            }
        }
    }
}
