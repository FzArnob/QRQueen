package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Children extends MethodProc {
    public static final Children children = new Children();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public static void children(TreeList treeList, int i, Consumer consumer) {
        int gotoChildrenStart = treeList.gotoChildrenStart(i);
        if (gotoChildrenStart >= 0) {
            int nextDataIndex = treeList.nextDataIndex(i);
            while (true) {
                int i2 = gotoChildrenStart << 1;
                int nextNodeIndex = treeList.nextNodeIndex(gotoChildrenStart, nextDataIndex);
                if (nextNodeIndex == gotoChildrenStart) {
                    nextNodeIndex = treeList.nextDataIndex(gotoChildrenStart);
                }
                if (nextNodeIndex >= 0) {
                    if (consumer instanceof PositionConsumer) {
                        ((PositionConsumer) consumer).writePosition(treeList, i2);
                    } else {
                        treeList.consumeIRange(gotoChildrenStart, nextNodeIndex, consumer);
                    }
                    gotoChildrenStart = nextNodeIndex;
                } else {
                    return;
                }
            }
        }
    }

    public static void children(Object obj, Consumer consumer) {
        if (obj instanceof TreeList) {
            children((TreeList) obj, 0, consumer);
        } else if ((obj instanceof SeqPosition) && !(obj instanceof TreePosition)) {
            SeqPosition seqPosition = (SeqPosition) obj;
            if (seqPosition.sequence instanceof TreeList) {
                children((TreeList) seqPosition.sequence, seqPosition.ipos >> 1, consumer);
            }
        }
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        Object nextArg = callContext.getNextArg();
        callContext.lastArg();
        if (nextArg instanceof Values) {
            TreeList treeList = (TreeList) nextArg;
            int i = 0;
            while (true) {
                int i2 = i << 1;
                int nextKind = treeList.getNextKind(i2);
                if (nextKind != 0) {
                    if (nextKind == 32) {
                        children(treeList.getPosNext(i2), consumer);
                    } else {
                        children(treeList, i, consumer);
                    }
                    i = treeList.nextDataIndex(i);
                } else {
                    return;
                }
            }
        } else {
            children(nextArg, consumer);
        }
    }
}
